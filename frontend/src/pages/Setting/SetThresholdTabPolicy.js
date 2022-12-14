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

const SetThresholdTabPolicy = (props) => {
    const [showLoader, setShowLoader] = useState(false);

    const [policyData, setPolicyData] = useState([]);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [policyColumnDefs, setPolicyColumnDefs] = useState([]);

    useEffect(() => {
        getPolicyList();
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, [props.menuKey]);

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
            document.getElementById('policyNoticeGrid').style.height = mainHeight - 100 + 'px';
        }

        handleResize();
    }

    const getPolicyList = () => {
        setShowLoader(true);
        setPolicyData([]);
        setPolicyColumnDefs([]);

        axiosConf.get("/api/setting/threshold/findAll/policy/" + props.target).then(res => {
            _.forEach(res.data, (obj) => {
                obj.regDate = obj.regDate ? moment(obj.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.modifyDate = obj.modifyDate ? moment(obj.modifyDate).format("YYYY-MM-DD HH:mm:ss") : "";
            });

            setPolicyData(res.data);
            setPolicyColumnDefs(_.cloneDeep(getDefaultPolicyColumn()));
            setShowLoader(false);
        });
    }

    const getDefaultPolicyColumn = () => {
        return [{
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
                return <WindowOpener className="inline-block" url={"/popup/setting/threshold/policy/reg/" + params.data.id + "?target=" + props.target} width="1400" height="800" bridge={getPolicyList}>
                    <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
                </WindowOpener>;
            },
            lockPosition: true
        },
        {
            headerName: '?????? ???',
            field: 'policyName',
            filter: "agTextColumnFilter"
        },
        {
            headerName: '?????????',
            field: 'firstWriter',
            filter: "agTextColumnFilter"
        },
        {
            headerName: '?????? ?????????',
            field: 'lastWriter',
            filter: "agTextColumnFilter"
        },
        {
            headerName: '?????? ?????? ??????',
            field: 'modifyDate',
            cellClass: ['text-center'],
            filter: "agTextColumnFilter"
        }];
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
                alert("????????? ????????? ??????????????????.");
                return;
            }

            if (window.confirm("?????????????????????????")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.get("/api/setting/threshold/delete/policy/" + props.target + "/" + selectedValueData).then(res => {
                    alert("?????????????????????.");
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
                <Button color="primary" size="small" startIcon={<DownloadOutlined />}>????????????</Button>
            </Grid>
            <Grid item md={6} mt={.5} align="right">
                <WindowOpener className="inline-block" url={"/popup/setting/threshold/policy/reg/0?target=" + props.target} width="1400" height="800" bridge={getPolicyList}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: .5 }}>??????</Button>
                </WindowOpener>
                <Button variant="contained" color="secondary" size="small" onClick={onDeleteListPolicy}>??????</Button>
            </Grid>

            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
            {showLoader && (<Loader />)}
        </Grid>
    );
};

export default SetThresholdTabPolicy;