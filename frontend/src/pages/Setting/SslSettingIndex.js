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

const SslSettingIndex = () => {
    const [showLoader, setShowLoader] = useState(false);
    const [policyData, setPolicyData] = useState([]);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const policyColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
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
            return <WindowOpener className="inline-block" url={"/popup/setting/sslSetting/reg/" + params.data.id} width="800" height="580" bridge={getPolicyList}>
                <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
            </WindowOpener>;
        },
        lockPosition: true
    },
    {
        headerName: 'IP',
        field: 'formatIp',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Port',
        field: 'formatPort',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'SSL Key',
        field: 'sslKeyFile',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Virtual Host 개수',
        field: 'vhostCnt',
        cellClass: ['text-right'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Sensor Device 맵핑 개수',
        field: 'sensorDeviceCnt',
        cellClass: ['text-right'],
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

        axiosConf.get('/api/setting/ssl/findAll').then(res => {
            _.forEach(res.data, (obj) => {
                obj.formatIp = obj.startIp === obj.endIp ? obj.startIp : obj.startIp + " ~ " + obj.endIp;
                obj.formatPort = obj.startPort === obj.endPort ? obj.startPort : obj.startPort + " ~ " + obj.endPort;
                obj.regDate = obj.regDate ? moment(obj.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.modifyDate = obj.modifyDate ? moment(obj.modifyDate).format("YYYY-MM-DD HH:mm:ss") : "";
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

    const onDeleteListPolicy = () => {
        if (gridApiObj.policyNoticeGridApi) {
            const selectedNodes = gridApiObj.policyNoticeGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.get("/api/setting/ssl/delete/" + selectedValueData).then(res => {
                    alert("삭제되었습니다.");
                    getPolicyList();
                });
            }
        }
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
                        onCellMouseDown={onCellMouseDown}
                        onGridReady={onGridReady}
                        handleResize={handleResize} />
                </Box>
            </Grid>

            <Grid item md={6} mt={.5}>
                <Button color="primary" size="small" startIcon={<DownloadOutlined />}>내보내기</Button>
            </Grid>
            <Grid item md={6} mt={.5} align="right">
                <WindowOpener className="inline-block" url="/popup/setting/sslSetting/reg/0" width="800" height="580" bridge={getPolicyList}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: .5 }}>추가</Button>
                </WindowOpener>
                <Button variant="contained" color="secondary" size="small" onClick={onDeleteListPolicy}>삭제</Button>
            </Grid>

            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
            {showLoader && (<Loader />)}
        </Grid>
    );
};

export default SslSettingIndex;