import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import WindowOpener from 'react-window-opener';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent, Button } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWindowMaximize, faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj } from '../../../lib/common';
import Loader from '../../../components/Loader';
import SearchIpBandRegion from '../../Common/Search/SearchIpBandRegion';

const IspIndex = () => {
    const resetSchItem = {
        id: null,
        name: "",
        nameEng: "",
        modifyFlag: false,
        ipAddr: ""
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);
    const [showLoader, setShowLoader] = useState(false);
    const [ispData, setIspData] = useState([]);
    const [ipRangeData, setIpRangeData] = useState([]);
    const [selectIspData, setSelectIspData] = useState({
        id: null,
        name: "",
        nameEng: "",
        modifyFlag: false
    });
    const [totalIpList, setTotalIpList] = useState(0);
    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);
    const ispColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        checkboxSelection: (params) => {
            return params.data.modifyFlag;
        },
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        lockPosition: true
    },
    {
        headerName: 'Name',
        field: 'name',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            return params.data.modifyFlag ? <>
                <WindowOpener className="inline-block" url={"/popup/setting/ipBand/isp/reg/region/" + params.data.id} width="800" height="200" bridge={getPolicyIspData}
                    state={{ id: params.data.id, name: params.data.name, nameEng: params.data.nameEng }}>
                    <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
                </WindowOpener>
                <Typography component="span" ml={1}>{params.value}</Typography>
            </> : <Typography component="span">{params.value}</Typography>;
        },
        filter: "agTextColumnFilter"
    },
    {
        headerName: "English Name",
        field: "nameEng",
        filter: "agTextColumnFilter"
    },
    {
        headerName: "기본 그룹",
        field: "modifyFalg",
        cellClass: ['text-center'],
        cellRenderer: (params) => {
            return params.data.modifyFlag ? "X" : "O";
        }
    }];
    const ipListColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellRendererParams: { checkbox: true },
        checkboxSelection: (params) => {
            return params.data.modifyFlag;
        },
        lockPosition: true
    },
    {
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellRendererFramework: (params) => {
            return params.data.modifyFlag ?
                <WindowOpener className="inline-block" url={"/popup/setting/ipBand/isp/reg/ip/" + params.data.id} width="1000" height="200" bridge={getPolicyIspData}
                    state={{
                        startIp: params.data.startIp,
                        endIp: params.data.endIp,
                        mappingId: params.data.ispId
                    }}>
                    <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
                </WindowOpener> : "";
        },
        lockPosition: true
    },
    {
        headerName: 'Start IP',
        field: 'startIp',
        filter: "agTextColumnFilter",
    },
    {
        headerName: 'End IP',
        field: 'endIp',
        filter: "agTextColumnFilter",
    }
    ];

    useEffect(() => {
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);
        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyIspGridApi) {
                gridApiObj.policyIspGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [ispData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyIpGridApi) {
                gridApiObj.policyIpGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [ipRangeData]);

    useEffect(() => {
        getPolicyIspData();
    }, [selectIspData]);

    const autoComponentSize = useCallback(() => {
        const mainHeight = document.body.clientHeight - document.getElementById("searchEl").clientHeight;

        _.each(document.getElementsByClassName("policyGridEl"), (obj) => {
            obj.style.height = mainHeight - 30 + 'px';
        });

        handleResize();
    }, []);

    const getPolicyIspData = (e, data) => {
        if (selectIspData.id) {
            getIspData();
            getIpData();
        } else {
            getIspData();
        }

    }

    const getIspData = () => {
        setShowLoader(true);

        axiosConf.get('/api/setting/geoIsp/findAllIsp').then(res => {
            setIspData(res.data);
            setShowLoader(false);
        });
    }

    const getIpData = () => {
        axiosConf.get('/api/setting/geoIsp/findIpById/' + selectIspData.id).then(res => {
            setIpRangeData(res.data);
            setTotalIpList(res.data.length);
            setShowLoader(false);
        });
    }

    const onCellIspClick = (params) => {

        if (params.data) {
            setShowLoader(true);
            setSelectIspData({
                id: params.data.id,
                name: params.data.name,
                nameEng: params.data.nameEng,
                modifyFlag: params.data.modifyFlag
            });
        }
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

    const onDeleteIspPolicy = () => {
        if (gridApiObj.policyIspGridApi) {
            const selectedNodes = gridApiObj.policyIspGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.post("/api/setting/geoIsp/deleteIspGroup", { id: selectedValueData }).then(res => {
                    alert("삭제되었습니다.");

                    setTotalIpList(0);
                    setSelectIspData({
                        id: null,
                        name: "",
                        nameEng: "",
                        modifyFlag: false
                    });
                });
            }
        }
    }

    const onDeleteIpListPolicy = () => {
        if (gridApiObj.policyIpGridApi) {
            const selectedNodes = gridApiObj.policyIpGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.post("/api/setting/geoIsp/deleteIpMapping", { id: selectedValueData }).then(res => {
                    alert("삭제되었습니다.");
                });
                getPolicyIspData();
            }
        }
    }

    const searchIpBandData = useCallback(() => {
        if (schItem.ipAddr === "") {
            alert("IP를 입력해주세요.");
            return;
        }

        axiosConf.get('/api/setting/geoIsp/findIspIpRangeByIp?ip=' + schItem.ipAddr).then(res => {
            setTotalIpList(res.data.length);
            setIspData(new Array(res.data.isp));
            setIpRangeData(res.data.ipRange);
        });
    }, [schItem]);

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);

        setTotalIpList(0);
        setIspData([]);
        setIpRangeData([]);

        setSelectIspData({
            id: null,
            name: "",
            nameEng: "",
            modifyFlag: false
        });
    }, []);

    const applyIpServer = () => {
        if (window.confirm("적용하시겠습니까?")) {
            axiosConf.post("/api/set/applyIsp", {}).then(res => {
                alert("적용되었습니다.");
            });
        }
    }

    // import content
    const SearchContent = useMemo(() => (
        <SearchIpBandRegion schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={searchIpBandData} resetEvt={resetEvt} />
    ), [schItem]);

    return (
        <>
            {SearchContent}

            <Grid container mt={2.5} spacing={.5}>
                <Grid item md={6}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    ISP ( Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(ispData.length)}</Typography> )
                                </Grid>
                                <Grid item sm={2} textAlign="right">
                                    <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: 1.5, cursor: "pointer" }}>
                                        <FontAwesomeIcon icon={faMinus} onClick={onDeleteIspPolicy} />
                                    </Typography>
                                    <WindowOpener className="inline-block" url="/popup/setting/ipBand/isp/reg/region/0" width="800" height="200" bridge={getPolicyIspData} state={{ id: null, name: "", nameEng: "" }}>
                                        <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: .5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faPlus} />
                                        </Typography>
                                    </WindowOpener>
                                </Grid>
                            </Grid>
                        )} />
                        <CardContent className="policyGridEl">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="policyIspGridApi"
                                    columnDefs={ispColumnDefs}
                                    rowData={ispData}
                                    rowMultiSelectWithClick={false}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellIspClick}
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
                                    IP ( 선택한 ISP: <Typography component="span" className="font-blue">{selectIspData.name}</Typography>, Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(totalIpList)}</Typography> )
                                </Grid>
                                <Grid item sm={2} textAlign="right">
                                    <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: 1.5, cursor: "pointer" }}>
                                        <FontAwesomeIcon icon={faMinus} onClick={onDeleteIpListPolicy} />
                                    </Typography>
                                    <WindowOpener className="inline-block" url="/popup/setting/ipBand/isp/reg/ip/0" width="1000" height="200" bridge={getPolicyIspData} state={selectIspData}>
                                        <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: .5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faPlus} />
                                        </Typography>
                                    </WindowOpener>
                                    <Button variant="outlined" size="small" color="inherit" sx={{ height: "0 !important", padding: ".53rem !important" }} onClick={applyIpServer}>서버 적용</Button>
                                </Grid>
                            </Grid>
                        )} />
                        <CardContent className="policyGridEl">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="policyIpGridApi"
                                    columnDefs={ipListColumnDefs}
                                    rowData={ipRangeData}
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

export default IspIndex;