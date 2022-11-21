import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import WindowOpener from 'react-window-opener';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Button } from '@mui/material';

import { DownloadOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj, scrollGridCnt } from '../../lib/common';
import Loader from '../../components/Loader';
import SearchAuditHistory from '../Common/Search/SearchAuditHistory';

const AuditHistoryIndex = () => {
    const resetSchItem = {
        startDate: moment().subtract('24', 'hours').add('1', 'seconds').toDate(),
        startHours: moment().subtract('24', 'hours').add('1', 'seconds').format("HH"),
        startMinutes: moment().subtract('24', 'hours').add('1', 'seconds').format("mm"),
        startSeconds: moment().subtract('24', 'hours').add('1', 'seconds').format("ss"),
        endDate: moment().toDate(),
        endHours: moment().format("HH"),
        endMinutes: moment().format("mm"),
        endSeconds: moment().format("ss"),
        autoDate: "24hours",
        action: ["LOGIN", "LOGOUT", "CREATE", "UPDATE", "DELETE", "ACTIVE", "INACTIVE", "APPLY", "DOWNLOAD"],
        userId: []
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);

    const [showLoader, setShowLoader] = useState(false);
    const [totalCnt, setTotalCnt] = useState(0);
    const [policyData, setPolicyData] = useState([]);

    const [currentPage, setCurrentPage] = useState(0);
    const [maxPage, setMaxPage] = useState(0);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const policyColumnDefs = [{
        headerName: '사용자',
        field: 'loginId',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '작업 구분',
        field: 'action',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '메뉴 1',
        field: 'menuDepth1',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '메뉴 2',
        field: 'menuDepth2',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '메뉴 3',
        field: 'menuDepth3',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '메뉴 4',
        field: 'menuDepth4',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '작업 대상',
        field: 'target',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '사용자 IP',
        field: 'ip',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Page URL',
        field: 'pageUrl',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Action URL',
        field: 'actionUrl',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '작업 일자',
        field: 'date',
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

    const autoComponentSize = useCallback(() => {
        if (document.getElementById('policyNoticeGrid')) {
            const mainHeight = document.body.clientHeight - document.getElementById("searchEl").clientHeight;
            document.getElementById('policyNoticeGrid').style.height = mainHeight - 35 + 'px';
        }

        handleResize();
    }, []);

    const formatSchReqData = (targetData) => {
        let requestData = {
            begin: moment(targetData.startDate).format("YYYYMMDD") + targetData.startHours + targetData.startMinutes + targetData.startSeconds,
            end: moment(targetData.endDate).format("YYYYMMDD") + targetData.endHours + targetData.endMinutes + targetData.endSeconds,
            limit: scrollGridCnt,
            userId: targetData.userId.length === 0 ? null : _.map(targetData.userId, "userId"),
            action: targetData.action.length === 0 ? null : targetData.action
        };

        return requestData;
    }

    const getPolicyList = useCallback((e, bridgeData, resetItem) => {
        if (bridgeData) {
            setSchItem({ ...schItem, [bridgeData.target.id]: bridgeData.target.value });
        } else {
            setShowLoader(true);

            const targetData = resetItem ? resetItem : schItem;
            let requestData = formatSchReqData(targetData);

            requestData.offset = 0;

            axiosConf.post('/api/setting/audit/search', requestData).then(res => {
                if (gridApiObj.policyNoticeGridApi) {
                    gridApiObj.policyNoticeGridApi.api.ensureIndexVisible(0, 'top');
                }

                const maxPage = _.floor(res.data.total / scrollGridCnt);

                _.forEach(res.data.results, (obj) => {
                    obj.date = obj.date ? moment(obj.date).format("YYYY-MM-DD HH:mm:ss") : "";
                });

                setTotalCnt(res.data.total);
                setMaxPage(maxPage);
                setCurrentPage(0);
                setPolicyData(res.data.results);
                setShowLoader(false);
            });
        }
    }, [schItem]);

    const addPolicyList = (page) => {
        const offset = page * scrollGridCnt;
        let requestData = formatSchReqData(schItem);

        // Grid Size
        requestData.offset = offset;

        axiosConf.post('/api/setting/audit/search', requestData).then(res => {
            _.forEach(res.data.results, (obj) => {
                obj.date = obj.date ? moment(obj.date).format("YYYY-MM-DD HH:mm:ss") : "";
            });

            setPolicyData([...policyData, ...res.data.results]);
            setShowLoader(false);
        });
    }

    const onScrollLoadEvent = (e) => {
        if (e.target.classList.value.includes("ag-body-viewport") && policyData.length > 0) {
            if ((e.target.scrollTop + e.target.clientHeight) === e.target.scrollHeight) {
                if (maxPage > currentPage) {
                    setShowLoader(true);
                    setCurrentPage(prev => prev + 1);
                    addPolicyList(currentPage + 1);
                }
            }
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

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);
        getPolicyList(null, null, resetSchItem);
    }, [schItem]);

    // import content
    const SearchContent = useMemo(() => (
        <SearchAuditHistory schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={getPolicyList} resetEvt={resetEvt} />
    ), [schItem]);

    return (
        <>
            {SearchContent}

            <Grid container>
                <Grid item md={6}>
                    Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(totalCnt)}</Typography>
                </Grid>

                <Grid item md={12} id="policyNoticeGrid">
                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()} onScrollCapture={onScrollLoadEvent}>
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
                    <WindowOpener className="inline-block" url="/popup/common/export?category=audit" width="600" height="530" bridge={getPolicyList}
                        state={{ columnDefs: policyColumnDefs, schItem: schItem, filter: ["action", "userId"] }}>
                        <Button color="primary" size="small" startIcon={<DownloadOutlined />}>내보내기</Button>
                    </WindowOpener>
                </Grid>
            </Grid>

            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
            {showLoader && (<Loader />)}
        </>
    );
};

export default AuditHistoryIndex;