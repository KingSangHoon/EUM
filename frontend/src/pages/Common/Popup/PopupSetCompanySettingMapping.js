import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj } from '../../../lib/common';

const PopupSetCompanySettingGroupReg = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);
    const [gridData, setGridData] = useState([]);

    const gridColumnDefs = [{
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
        headerName: '이메일',
        field: 'email',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '전화번호',
        field: 'phoneNumber',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '권한',
        field: 'role',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '고객사',
        field: 'companyName',
        filter: "agTextColumnFilter"
    }];

    useEffect(() => {
        getFindMappingData();

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
        if (params.id !== "0") {
            getFindoneStateData();
        }
    }, [gridData]);

    const getFindMappingData = () => {
        setShowLoader(true);

        axiosConf.get("/api/setting/user/find/mapping/company").then(res => {
            _.forEach(res.data, (obj) => {
                obj.role = obj.role = "ROLE_USER" ? "사용자" : "관리자";
            });

            setGridData(res.data);
            setShowLoader(false);
        });
    }

    const getFindoneStateData = () => {
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);
                const userIdArr = _.map(jsonParam.checkData, "userId");

                if (gridApiObj.policyListGridApi) {
                    gridApiObj.policyListGridApi.api.forEachNode((node) => {
                        if (userIdArr.indexOf(node.data.userId) !== -1) {
                            node.setSelected(true);
                        }
                    });
                }
            }
        }, 100);
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const onSavePolicyValidation = () => {
        if (gridApiObj.policyListGridApi) {
            const selectedNodes = gridApiObj.policyListGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            const selectedValueData = _.map(selectedNodes, "userId").join("|");

            applyPolicySave(selectedValueData);
        }
    }

    const applyPolicySave = (userIdStr) => {
        axiosConf.get("/api/setting/company/mapping/user/" + params.id + "/" + userIdStr).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="사용자 맵핑"></CardHeader>
            <CardContent>
                <Box component="div" className="ag-theme-alpine" sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()}>
                    <AgGridModule
                        gridName="policyListGridApi"
                        columnDefs={gridColumnDefs}
                        rowData={gridData}
                        onGridReady={onGridReady}
                        handleResize={handleResize} />
                </Box>

                <Box component="div" textAlign="center" sx={{ pt: 1, pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
        </Card>
    );
};

export default PopupSetCompanySettingGroupReg;