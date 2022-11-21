import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { onCopyGridCell, gridApiObj, numberWithCommas } from '../../../lib/common';
import Loader from '../../../components/Loader';

const MimeIndex = () => {
    const resetSchItem = {
        ipAddr: ""
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [showLoader, setShowLoader] = useState(false);
    const [mimeTypeData, setMimeTypeData] = useState([]);
    const [mimeDetailData, setMimeDetailData] = useState([]);
    const [selectMimeData, setSelectMimeData] = useState({
        type: null,
        id: null
    });
    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);
    const mimeTypeColumnDefs = [
        {
            headerName: 'MIME Type',
            field: 'type',
            cellClass: ['cursorp'],
            filter: "agTextColumnFilter",
        }];

    const mimeDetailColumnDefs = [
        {
            headerName: 'name',
            field: 'name',
            filter: "agTextColumnFilter",
            cellClass: [''],
        },
        {
            headerName: 'template',
            field: 'template',
            filter: "agTextColumnFilter",
            cellClass: [''],
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
            if (gridApiObj.mimeTypeGridApi) {
                gridApiObj.mimeTypeGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [mimeTypeData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.mimeDetailGridApi) {
                gridApiObj.mimeDetailGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [mimeDetailData]);

    useEffect(() => {
        getPolicyRegionData();
    }, [selectMimeData]);

    const autoComponentSize = () => {
        if (document.getElementById('mimeTypeGrid')) {
            const mainHeight = document.body.clientHeight - 5;
            document.getElementById('mimeTypeGrid').style.height = mainHeight + 'px';
        }
        if (document.getElementById('mimeDetailGrid')) {
            const mainHeight = document.body.clientHeight - 5;
            document.getElementById('mimeDetailGrid').style.height = mainHeight + 'px';
        }
        handleResize();
    }

    const getPolicyRegionData = (e, data) => {
        if (data) {
            setSelectMimeData(data);
        } else {
            if (selectMimeData.type) {
                getMimeTypeData();
                getMimeDetail();
            } else {
                getMimeTypeData();
            }
        }
    }

    const getMimeTypeData = () => {
        setShowLoader(true);
        axiosConf.get('/api/setting/code/mime/findMimeTypeCategory').then(res => {
            setMimeTypeData(res.data);
            setShowLoader(false);
        });
    }

    const getMimeDetail = () => {
        axiosConf.get('/api/setting/code/mime/findMimeName/' + selectMimeData.type).then(res => {
            setMimeDetailData(res.data);
            setShowLoader(false);
        });
    }

    const onCellMimeTypeClick = (params) => {
        if (params.data) {
            setShowLoader(true);
            setSelectMimeData({
                type: params.data.type,
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

    return (
        <>
            <Grid container spacing={.5}>
                <Grid item md={4}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    MIME ( Total: <Typography component="span" className="font-blue font-bold"> {numberWithCommas(mimeTypeData.length)} </Typography>)
                                </Grid>
                                <Grid item sm={2} textAlign="right"> </Grid>
                            </Grid>
                        )} />
                        <CardContent className="policyGridEl" id="mimeTypeGrid">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="mimeTypeGridApi"
                                    columnDefs={mimeTypeColumnDefs}
                                    rowData={mimeTypeData}
                                    rowMultiSelectWithClick={false}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellMimeTypeClick}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={8}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(mimeDetailData.length)}</Typography>
                                </Grid>
                                <Grid item sm={2} textAlign="right"></Grid>
                            </Grid>
                        )} />
                        <CardContent className="policyGrid" id="mimeDetailGrid">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="mimeDetailGridApi"
                                    columnDefs={mimeDetailColumnDefs}
                                    rowData={mimeDetailData}
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

export default MimeIndex;