import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj } from '../../../lib/common';
import Loader from '../../../components/Loader';

const HttpIndex = () => {
    const resetSchItem = {
        ipAddr: ""
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [showLoader, setShowLoader] = useState(false);
    const [httpTypeData, setHttpTypeData] = useState([]);
    const [httpDetailColumnDefs, setHttpDetailColumnDefs] = useState([]);
    const [httpDetailData, setHttpDetailData] = useState([]);
    const [selectHttpData, setSelectHttpData] = useState({
        type: null,
    });
    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);
    const httpTypeColumnDefs = [
        {
            headerName: 'HTTP Type',
            field: 'type',
            cellClass: ['cursorp'],
            filter: "agTextColumnFilter"
        }];

    // const httpDetailColumnDefs = [
    //     {
    //         headerName: 'name',
    //         field: 'name',
    //         filter: "agTextColumnFilter",
    //         cellClass: ['text-left'],
    //     },
    //     {
    //         headerName: 'description',
    //         field: 'description',
    //         filter: "agTextColumnFilter",
    //         cellClass: ['text-left'],
    //     },
    //     {
    //         headerName: 'sub_type',
    //         field: 'subType',
    //         filter: "agTextColumnFilter",
    //         cellClass: ['text-left'],
    //     }
    // ];

    const httpHeaderColumnDefs = [
        {
            headerName: 'name',
            field: 'name',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        },
        {
            headerName: 'description',
            field: 'description',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        },
        {
            headerName: 'sub_type',
            field: 'subType',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        }
    ];

    const httpMethodColumnDefs = [
        {
            headerName: 'name',
            field: 'name',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        }
    ];

    const httpResponseCodeColumnDefs = [
        {
            headerName: 'name',
            field: 'name',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        },
        {
            headerName: 'status_eng',
            field: 'description',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        },
        {
            headerName: 'status',
            field: 'description1',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        },
        {
            headerName: 'description',
            field: 'description2',
            filter: "agTextColumnFilter",
            cellClass: ['text-left'],
        }
    ];

    useEffect(() => {
        setHttpDetailColumnDefs(httpHeaderColumnDefs);
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.httpTypeGridApi) {
                gridApiObj.httpTypeGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [httpTypeData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.httpDetailGridApi) {
                gridApiObj.httpDetailGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [httpDetailData]);

    useEffect(() => {
        getPolicyRegionData();
    }, [selectHttpData]);

    const autoComponentSize = () => {
        if (document.getElementById('httpTypeGrid')) {
            const mainHeight = document.body.clientHeight - 5;
            document.getElementById('httpTypeGrid').style.height = mainHeight + 'px';
        }
        if (document.getElementById('httpDetailGrid')) {
            const mainHeight = document.body.clientHeight - 5;
            document.getElementById('httpDetailGrid').style.height = mainHeight + 'px';
        }
        handleResize();
    }

    const getPolicyRegionData = (e, data) => {
        if (data) {
            setSelectHttpData(data);
        } else {
            if (selectHttpData.type) {
                getHttpTypeData();
                getHttpDetail();
            } else {
                getHttpTypeData();
            }
        }
    }

    const getHttpTypeData = () => {
        setShowLoader(true);
        axiosConf.get('/api/setting/code/http/findHttpTypeCategory').then(res => {
            setHttpTypeData(res.data);
            setShowLoader(false);
        });
    }

    const getHttpDetail = () => {
        axiosConf.get('/api/setting/code/http/findHttpName/' + selectHttpData.type).then(res => {
            if (selectHttpData.type === 'HTTP Header') {
                setHttpDetailColumnDefs(httpHeaderColumnDefs);
            } else if (selectHttpData.type === 'HTTP Request Method') {
                setHttpDetailColumnDefs(httpMethodColumnDefs);
            } else if (selectHttpData.type === 'HTTP Response Code') {
                setHttpDetailColumnDefs(httpResponseCodeColumnDefs);
            }

            setHttpDetailData(res.data);
            setShowLoader(false);
        });
    }

    const onCellHttpTypeClick = (params) => {
        if (params.data) {
            setShowLoader(true);
            setSelectHttpData({
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
                                <Grid item sm={12}>
                                    HTTP ( Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(httpTypeData.length)}</Typography> )
                                </Grid>
                            </Grid>
                        )} />

                        <CardContent className="policyGridEl" id="httpTypeGrid">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="httpTypeGridApi"
                                    columnDefs={httpTypeColumnDefs}
                                    rowData={httpTypeData}
                                    rowMultiSelectWithClick={false}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellHttpTypeClick}
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
                                    Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(httpDetailData.length)}</Typography>
                                </Grid>
                                <Grid item sm={2} textAlign="right"> </Grid>
                            </Grid>
                        )} />
                        <CardContent className="policyGrid" id="httpDetailGrid">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="httpDetailGridApi"
                                    columnDefs={httpDetailColumnDefs}
                                    rowData={httpDetailData}
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

export default HttpIndex;