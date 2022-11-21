import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import WindowOpener from 'react-window-opener';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWindowMaximize, faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj } from '../../../lib/common';
import Loader from '../../../components/Loader';

const ProtocolIndex = () => {
    const resetSchItem = {
        ipAddr: ""
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [showLoader, setShowLoader] = useState(false);
    const [protocolTypeData, setProtocolTypeData] = useState([]);
    const [protocolMappingData, setProtocolMappingData] = useState([]);
    const [selectHttpData, setSelectHttpData] = useState({
        type: null,
    });

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);
    const protocolTypeColumnDefs = [
        {
            headerName: 'Protocol',
            field: 'ndpiProtocolName',
            cellClass: ['cursorp'],
            filter: "agTextColumnFilter"
        }];

    const protocolColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellRendererParams: { checkbox: true },
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        lockPosition: true
    },
    {
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            return <WindowOpener className="inline-block"
                url={"/popup/setting/code/protocol/reg/" + params.data.mappingId} width="1000"
                height="600" bridge={getPolicyRegionData}
                state={{ id: params.data.mappingId }}>
                <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
            </WindowOpener>;
        },
        lockPosition: true
    },
    {
        headerName: 'App',
        field: 'appName',
        filter: "agTextColumnFilter",
        cellClass: ['text-center'],
    },
    {
        headerName: 'Master',
        field: 'masterName',
        filter: "agTextColumnFilter",
        cellClass: ['text-center'],
    },
    {
        headerName: 'Type',
        field: 'isWhiteList',
        filter: "agTextColumnFilter",
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            let result = "-";
            if (params.data.isWhiteList) {
                result = "White List"
            } else {
                result = "Black List"
            }
            return result;
        }
    }];

    useEffect(() => {
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);
        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.protocolTypeGridApi) {
                gridApiObj.protocolTypeGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [protocolTypeData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.protocolGridApi) {
                gridApiObj.protocolGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [protocolMappingData]);

    useEffect(() => {
        getPolicyRegionData();
    }, [selectHttpData]);

    const autoComponentSize = () => {
        if (document.getElementById('protocolTypeGrid')) {
            const mainHeight = document.body.clientHeight;
            document.getElementById('protocolTypeGrid').style.height = mainHeight - 5 + 'px';
        }
        if (document.getElementById('protocolDetailGrid')) {
            const mainHeight = document.body.clientHeight;
            document.getElementById('protocolDetailGrid').style.height = mainHeight - 5 + 'px';
        }
        handleResize();
    }

    const getPolicyRegionData = (e, data) => {
        getProtocolTypeData();
        getProtocolMappingData();
    }

    const getProtocolTypeData = () => {
        setShowLoader(true);
        axiosConf.get('/api/setting/code/protocol/findAllProtocol').then(res => {
            setProtocolTypeData(res.data);
            setShowLoader(false);
        });
    }

    const getProtocolMappingData = () => {
        axiosConf.get('/api/setting/code/protocol/findAllMappingProtocol').then(res => {
            setProtocolMappingData(res.data);
            setShowLoader(false);
        });
    }

    const onCellMouseDown = (params) => {
        const { which } = params.event;

        if (which === 3) {
            onCopyGridCell(params.value);

            setTransition(() => TransitionUp);
            setCopySuccess(true);

            setTimeout(() => {
                setCopySuccess(false);
            }, 1000);
        }
    }

    const TransitionUp = (props) => {
        return <Slide {...props} direction="up" />;
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const onDeleteProtocolMapping = () => {
        if (gridApiObj.protocolGridApi) {
            const selectedNodes = gridApiObj.protocolGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }
            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "mappingId").join("|");

                axiosConf.post("/api/setting/code/protocol/deleteProtocolMapping", { id: selectedValueData }).then(res => {
                    getPolicyRegionData();
                    alert("삭제되었습니다.");
                });
            }
        }
    }

    return (
        <>
            <Grid container spacing={.5}>
                <Grid item md={6}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={12}>
                                    Protocol ( Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(protocolTypeData.length)}</Typography> )
                                </Grid>
                            </Grid>
                        )} />

                        <CardContent className="policyGridEl" id="protocolTypeGrid">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="protocolTypeGridApi"
                                    columnDefs={protocolTypeColumnDefs}
                                    rowData={protocolTypeData}
                                    rowMultiSelectWithClick={false}
                                    onCellMouseDown={onCellMouseDown}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={6}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(protocolMappingData.length)}</Typography>
                                </Grid>
                                <Grid item sm={2} textAlign="right">
                                    <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: 1.5, cursor: "pointer" }}>
                                        <FontAwesomeIcon icon={faMinus} onClick={onDeleteProtocolMapping} />
                                    </Typography>
                                    <WindowOpener className="inline-block" url="/popup/setting/code/protocol/reg/0" width="1000" height="600" bridge={getPolicyRegionData} state={selectHttpData}>
                                        <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: .5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faPlus} />
                                        </Typography>
                                    </WindowOpener>
                                </Grid>

                            </Grid>
                        )} />
                        <CardContent className="policyGrid" id="protocolDetailGrid">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="protocolGridApi"
                                    columnDefs={protocolColumnDefs}
                                    rowData={protocolMappingData}
                                    onCellMouseDown={onCellMouseDown}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                    <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
                </Snackbar>
                {showLoader && (<Loader />)}
            </Grid>
        </>
    );
};

export default ProtocolIndex;