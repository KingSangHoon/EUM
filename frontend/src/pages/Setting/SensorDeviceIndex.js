import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import moment from 'moment';
import WindowOpener from 'react-window-opener';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Button } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWindowMaximize } from "@fortawesome/free-solid-svg-icons";
import { DownloadOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj } from '../../lib/common';
import Loader from '../../components/Loader';

const SensorDeviceIndex = () => {
    const [showLoader, setShowLoader] = useState(false);
    const [policyData, setPolicyData] = useState([]);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const policyColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            return <WindowOpener className="inline-block" url={"/popup/setting/sensorDevice/reg/" + params.data.sensorId} width="800" height="580" bridge={getPolicyList}>
                <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
            </WindowOpener>;
        },
        lockPosition: true
    },
    {
        headerName: 'Sensor Name',
        field: 'sensorName',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Sensor Alias',
        field: 'sensorAlias',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Sensor Version',
        field: 'sensorVersion',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Sensor OS',
        field: 'sensorOs',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Active',
        field: 'active',
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            if (params.value) {
                return <Typography component="span" className="font-green">활성</Typography>;
            } else {
                return <Typography component="span" className="font-red">비활성</Typography>;
            }
        }
    },
    {
        headerName: 'Device Activated',
        field: 'deviceActivated',
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            if (params.value) {
                return <Typography component="span" className="font-green">활성</Typography>;
            } else {
                return <Typography component="span" className="font-red">비활성</Typography>;
            }
        }
    },
    {
        headerName: '최근 접속 일자',
        field: 'lastConnectDate',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '등록 일자',
        field: 'regDate',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '수정 일자',
        field: 'modifyDate',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    }];

    useEffect(() => {
        getPolicyList();
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyNoticeGridApi) {
                gridApiObj.policyNoticeGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [policyData]);

    const autoComponentSize = () => {
        if (document.getElementById('policyNoticeGrid')) {
            const mainHeight = document.body.clientHeight;
            document.getElementById('policyNoticeGrid').style.height = mainHeight - 35 + 'px';
        }

        handleResize();
    }

    const getPolicyList = () => {
        setShowLoader(true);

        axiosConf.get('/api/setting/sensorDevice/findAll').then(res => {
            _.forEach(res.data, (obj) => {
                obj.regDate = obj.regDate ? moment(obj.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.modifyDate = obj.modifyDate ? moment(obj.modifyDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.lastConnectDate = obj.lastConnectDate ? moment(obj.lastConnectDate).format("YYYY-MM-DD HH:mm:ss") : "";
            });

            setPolicyData(res.data);
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

    return (
        <Grid container>
            <Grid item md={6}>
                Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(policyData.length)}</Typography>
            </Grid>

            <Grid item md={12} id="policyNoticeGrid">
                <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                    <AgGridModule
                        gridName="policyNoticeGridApi"
                        columnDefs={policyColumnDefs}
                        rowData={policyData}
                        rowMultiSelectWithClick={false}
                        onCellMouseDown={onCellMouseDown}
                        onGridReady={onGridReady}
                        handleResize={handleResize} />
                </Box>
            </Grid>

            <Grid item md={6} mt={.5}>
                <Button color="primary" size="small" startIcon={<DownloadOutlined />}>내보내기</Button>
            </Grid>

            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
            {showLoader && (<Loader />)}
        </Grid>
    );
};

export default SensorDeviceIndex;