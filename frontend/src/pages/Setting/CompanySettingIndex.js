import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import moment from 'moment';
import WindowOpener from 'react-window-opener';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWindowMaximize, faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj } from '../../lib/common';
import Loader from '../../components/Loader';

const CompanySettingIndex = () => {
    const [showLoader, setShowLoader] = useState(false);
    const [groupData, setGroupData] = useState([]);
    const [selectGroupData, setSelectGroupData] = useState({ id: null, name: "-" });
    const [listData, setListData] = useState([]);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const groupColumnDefs = [{
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
            return <WindowOpener className="inline-block" url={"/popup/setting/companySetting/reg/" + params.data.companyId} width="600" height="300" bridge={getPolicyGroupData} state={{ data: params.data }}>
                <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
            </WindowOpener>;
        },
        lockPosition: true
    },
    {
        headerName: '이름',
        field: 'companyName',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '설명',
        field: 'description',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '등록 일자',
        field: 'regDate',
        cellClass: ['text-center', 'cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '수정 일자',
        field: 'modifyDate',
        cellClass: ['text-center', 'cursorp'],
        filter: "agTextColumnFilter"
    }];

    const listColumnDefs = [{
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
    }];

    useEffect(() => {
        getPolicyGroupData();
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyGroupGridApi) {
                gridApiObj.policyGroupGridApi.api.sizeColumnsToFit();

                let updateUserList = [];

                if (selectGroupData.id && gridApiObj.policyGroupGridApi) {
                    gridApiObj.policyGroupGridApi.api.forEachNode((node) => {
                        if (node.data.companyId === selectGroupData.id) {
                            updateUserList = node.data.userList;
                            node.setSelected(true);
                        }
                    });
                }

                setListData(updateUserList);
            }
        }, 200);
    }, [groupData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyListGridApi) {
                gridApiObj.policyListGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [listData]);

    const autoComponentSize = () => {
        if (document.getElementById('policyGroupGrid')) {
            const mainHeight = document.body.clientHeight;
            document.getElementById('policyGroupGrid').style.height = mainHeight - 10 + 'px';
            document.getElementById('policyListGrid').style.height = mainHeight - 10 + 'px';
        }

        handleResize();
    }

    const getPolicyGroupData = () => {
        setShowLoader(true);

        axiosConf.get('/api/setting/company/findAll').then(res => {
            _.forEach(res.data, (obj) => {
                obj.regDate = obj.regDate ? moment(obj.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.modifyDate = obj.modifyDate ? moment(obj.modifyDate).format("YYYY-MM-DD HH:mm:ss") : "";

                _.forEach(obj.userList, (userObj) => {
                    userObj.role = userObj.role = "ROLE_USER" ? "사용자" : "관리자";
                });
            });

            setGroupData(res.data);
            setShowLoader(false);
        });
    }

    const onCellGroupClick = (params) => {
        if (params.data) {
            setSelectGroupData({ id: params.data.companyId, name: params.data.companyName });
            setListData(params.data.userList);
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

    const onDeleteGroupPolicy = () => {
        if (gridApiObj.policyGroupGridApi) {
            const selectedNodes = gridApiObj.policyGroupGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "companyId").join("|");

                axiosConf.get("/api/setting/company/delete/" + selectedValueData).then(res => {
                    alert("삭제되었습니다.");
                    setSelectGroupData({ id: null, name: "-" });
                    getPolicyGroupData();
                });
            }
        }
    }

    const onDeleteListPolicy = () => {
        if (gridApiObj.policyListGridApi) {
            const selectedNodes = gridApiObj.policyListGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "userId");
                const mappingId = [];

                gridApiObj.policyListGridApi.api.forEachNode((node) => {
                    if (selectedValueData.indexOf(node.data.userId) === -1) {
                        mappingId.push(node.data.userId);
                    }
                });

                axiosConf.get("/api/setting/company/mapping/user/" + selectGroupData.id + "/" + mappingId.join("|")).then(res => {
                    alert("삭제되었습니다.");
                    getPolicyGroupData();
                });
            }
        }
    }

    return (
        <Grid container spacing={.5}>
            <Grid item md={6}>
                <Card>
                    <CardHeader title={(
                        <Grid container>
                            <Grid item sm={11}>
                                고객사 ( Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(groupData.length)}</Typography> )
                            </Grid>
                            <Grid item sm={1} textAlign="right">
                                <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: 1.5, cursor: "pointer" }}>
                                    <FontAwesomeIcon icon={faMinus} onClick={onDeleteGroupPolicy} />
                                </Typography>
                                <WindowOpener className="inline-block" url="/popup/setting/companySetting/reg/0" width="600" height="300" bridge={getPolicyGroupData}>
                                    <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: .5, cursor: "pointer" }}>
                                        <FontAwesomeIcon icon={faPlus} />
                                    </Typography>
                                </WindowOpener>
                            </Grid>
                        </Grid>
                    )} />
                    <CardContent id="policyGroupGrid">
                        <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                            <AgGridModule
                                gridName="policyGroupGridApi"
                                columnDefs={groupColumnDefs}
                                rowData={groupData}
                                rowMultiSelectWithClick={false}
                                onCellMouseDown={onCellMouseDown}
                                onCellClicked={onCellGroupClick}
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
                            <Grid item sm={11}>
                                사용자 목록 ( 선택한 고객사: <Typography component="span" className="font-blue">{selectGroupData.name}</Typography>, Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(listData.length)}</Typography> )
                            </Grid>
                            {
                                selectGroupData.id && <Grid item sm={1} textAlign="right">
                                    <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: 1.5, cursor: "pointer" }}>
                                        <FontAwesomeIcon icon={faMinus} onClick={onDeleteListPolicy} />
                                    </Typography>
                                    <WindowOpener className="inline-block" url={"/popup/setting/companySetting/mapping/" + selectGroupData.id} width="1000" height="480" bridge={getPolicyGroupData} state={{ checkData: listData }}>
                                        <Typography component="span" color="textPrimary" variant="h6" sx={{ mr: .5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faPlus} />
                                        </Typography>
                                    </WindowOpener>
                                </Grid>
                            }
                        </Grid>
                    )} />
                    <CardContent id="policyListGrid">
                        <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                            <AgGridModule
                                gridName="policyListGridApi"
                                columnDefs={listColumnDefs}
                                rowData={listData}
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
    );
};

export default CompanySettingIndex;