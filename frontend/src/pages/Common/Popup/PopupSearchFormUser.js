import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useSearchParams } from 'react-router-dom';
import {
    Card, CardHeader, CardContent, Button, Box, Slide, Snackbar, Alert, Typography
} from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { hiddenComponentPopup, gridApiObj, onCopyGridCell } from '../../../lib/common';
import Loader from '../../../components/Loader';

const PopupSearchFormUser = () => {
    const [searchParams] = useSearchParams();
    const paramKey = searchParams.get("key");

    const [showLoader, setShowLoader] = useState(false);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [categoryData, setCategoryData] = useState([]);

    const [categoryColumnDefs] = useState([{
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
        headerName: '아이디',
        field: 'loginId',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '이름',
        field: 'username',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '전화번호',
        field: 'phoneNumber',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '이메일',
        field: 'email',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '권한',
        field: 'role',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '계정 활성',
        field: 'active',
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            if (params.value) {
                return <Typography component="span" className="font-green">활성</Typography>;
            } else {
                return <Typography component="span" className="font-red">비활성</Typography>;
            }
        }
    }]);

    useEffect(() => {
        getFindoneStateData();

        hiddenComponentPopup();
        window.addEventListener("resize", () => {
            hiddenComponentPopup();
            handleResize();
        });

        return () => {
            window.removeEventListener('resize', () => {
                hiddenComponentPopup();
                handleResize();
            });
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            handleResize();
        }, 200);
    }, [categoryData]);

    const getFindoneStateData = () => {
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);
                getCategoryData(jsonParam);
            }
        }, 100);
    }

    const getCategoryData = (stateData) => {
        setShowLoader(true);

        const requestData = {
            email: null,
            loginId: null,
            phoneNumber: null,
            roles: ["ROLE_USER", "ROLE_ADMIN"],
            username: null
        };

        axiosConf.post("/api/setting/user/search", requestData).then(res => {
            _.forEach(res.data, (obj) => {
                obj.name = obj.loginId;
                obj.role = obj.role = "ROLE_USER" ? "사용자" : "관리자";
            });

            setCategoryData(res.data);
            setShowLoader(false);

            setTimeout(() => {
                if (stateData[paramKey] && stateData[paramKey].length > 0) {
                    const selectedData = _.map(stateData[paramKey], "userId");
                    chkGridSelectedData(selectedData);
                }
            }, 100);
        });
    }

    const chkGridSelectedData = (selectedData) => {
        gridApiObj.categoryGridApi.api.forEachNode((node) => {
            if (selectedData.indexOf(node.data.userId) !== -1) {
                node.setSelected(true);
            }
        });
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const TransitionUp = (props) => {
        return <Slide {...props} direction="up" />;
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

    const onSavePolicyValidation = () => {
        const selectNodes = gridApiObj.categoryGridApi.api.getSelectedRows();

        window.opener.onSuccess({ target: { id: paramKey, value: selectNodes } });
        window.close();
    }

    return (
        <Card>
            <CardHeader title="사용자 검색"></CardHeader>
            <CardContent>
                <Box component="div" className="ag-theme-alpine" mt={1} pb={1} sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()}>
                    <AgGridModule
                        gridName="categoryGridApi"
                        columnDefs={categoryColumnDefs}
                        rowData={categoryData}
                        onCellMouseDown={onCellMouseDown}
                        onGridReady={onGridReady}
                        handleResize={handleResize} />
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
        </Card>
    );
};

export default PopupSearchFormUser;