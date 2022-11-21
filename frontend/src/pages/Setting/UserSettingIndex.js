import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import WindowOpener from 'react-window-opener';
import { useLocation } from 'react-router-dom';
import { Grid, Button, Box, Typography, Menu, ListItemText, Divider, Checkbox, FormControl, FormControlLabel, Alert, Snackbar, Slide } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWindowMaximize } from "@fortawesome/free-solid-svg-icons";
import { SettingOutlined, DownloadOutlined, CheckCircleOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import { numberWithCommas, getGridFindColumn, handleShowGrid, handleAllShowGrid, onSaveGridSetting, onSaveIdxGridSetting, onCopyGridCell, gridApiObj, gridSetMenuStyles, exportExcelData } from '../../lib/common';
import Loader from '../../components/Loader';
import SearchUserSetting from '../Common/Search/SearchUserSetting';

const UserSettingIndex = () => {
    const resetSchItem = {
        loginId: "",
        username: "",
        email: "",
        phoneNumber: "",
        roles: ["ROLE_USER", "ROLE_ADMIN"],
        allActive: true,
        active: "0"
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);

    const [showLoader, setShowLoader] = useState(false);
    const [policyData, setPolicyData] = useState([]);
    const [gridState, setGridState] = useState({
        stateField: [],
        checkField: []
    });

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [anchorEl, setAnchorEl] = useState(null);
    const anchorOpen = Boolean(anchorEl);

    const [policyColumnDefs] = useState([{
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
            return <WindowOpener className="inline-block" url={"/popup/setting/userSetting/reg/" + params.data.userId} width="600" height="340" bridge={getPolicyList}>
                <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
            </WindowOpener>;
        },
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

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyNoticeGridApi) {
                gridApiObj.policyNoticeGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [gridState]);

    const autoComponentSize = useCallback(() => {
        if (document.getElementById('policyNoticeGrid')) {
            const mainHeight = document.body.clientHeight - document.getElementById("searchEl").clientHeight;
            document.getElementById('policyNoticeGrid').style.height = mainHeight - 40 + 'px';
        }

        handleResize();
    }, []);

    const getPolicyList = useCallback((e, data) => {
        setShowLoader(true);

        const targetData = data ? data : schItem;
        let requestData = {};

        requestData.loginId = targetData.loginId === "" ? null : targetData.loginId;
        requestData.username = targetData.username === "" ? null : targetData.username;
        requestData.email = targetData.email === "" ? null : targetData.email;
        requestData.phoneNumber = targetData.phoneNumber === "" ? null : targetData.phoneNumber;
        requestData.roles = targetData.roles;
        if (!targetData.allActive) requestData.active = targetData.active === "0" ? true : false;

        axiosConf.post('/api/setting/user/search', requestData).then(res => {
            _.forEach(res.data, (obj) => {
                obj.regDate = obj.regDate ? moment(obj.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.modifyDate = obj.modifyDate ? moment(obj.modifyDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.role = obj.role = "ROLE_USER" ? "사용자" : "관리자";
            });

            setPolicyData(res.data);
            setShowLoader(false);
        });
    }, [schItem]);

    const handleGridSetOpen = (event) => {
        setAnchorEl(event.currentTarget);
    }

    const handleGridSetClose = () => {
        setAnchorEl(null);
    }

    const getGridColumnField = () => {
        if (gridApiObj.policyNoticeGridApi) {
            getGridFindColumn("user", gridApiObj.policyNoticeGridApi, setGridState);
        }
    }

    const handleCheckboxChange = (e) => {
        if (gridApiObj.policyNoticeGridApi) {
            handleShowGrid(e.target, gridApiObj.policyNoticeGridApi, gridState, setGridState);
        }
    }

    const handleCheckboxAllChange = (e) => {
        if (gridApiObj.policyNoticeGridApi) {
            handleAllShowGrid(e.target, gridApiObj.policyNoticeGridApi, gridState, setGridState);
        }
    }

    const onSaveGridField = () => {
        onSaveGridSetting("user", gridState, handleGridSetClose);
    }

    const onDragStopped = () => {
        if (gridApiObj.policyNoticeGridApi) {
            onSaveIdxGridSetting("user", gridApiObj.policyNoticeGridApi);
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
        getGridColumnField();
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
                const selectedValueData = _.map(selectedNodes, "userId").join("|");

                axiosConf.get("/api/setting/user/delete/" + selectedValueData).then(res => {
                    alert("삭제되었습니다.");
                    getPolicyList();
                });
            }
        }
    }

    const onActiveListPolicy = (targetTxt) => {
        if (gridApiObj.policyNoticeGridApi) {
            const selectedNodes = gridApiObj.policyNoticeGridApi.api.getSelectedRows();
            const flag = targetTxt === "활성" ? true : false;

            if (selectedNodes.length === 0) {
                alert(targetTxt + "할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm(targetTxt + "하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "userId").join("|");

                axiosConf.get("/api/setting/user/active/" + flag + "/" + selectedValueData).then(res => {
                    alert(targetTxt + "되었습니다.");
                    getPolicyList();
                });
            }
        }
    }

    const onDownloadExcel = () => {
        setShowLoader(true);

        const data = {};

        axiosConf.post("/api/excel/user", data, { responseType: 'blob' }).then(res => {
            const name = res.headers['content-disposition'].split('Filename=')[1];
            const url = window.URL.createObjectURL(new Blob([res.data]))
            const link = document.createElement('a')
            link.href = url
            link.setAttribute('download', name)
            link.style.cssText = 'display:none'
            document.body.appendChild(link)
            link.click()
            link.remove()

            setShowLoader(false);
        });
    }

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);
        getPolicyList(null, resetSchItem);
    }, [schItem]);

    // import content
    const SearchContent = useMemo(() => (
        <SearchUserSetting schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={getPolicyList} resetEvt={resetEvt} />
    ), [schItem]);

    return (
        <>
            {SearchContent}

            <Grid container>
                <Grid item md={6}>
                    Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(policyData.length)}</Typography>
                </Grid>
                <Grid item md={6} align="right">
                    <Button color="inherit" size="small" startIcon={<SettingOutlined />} onClick={handleGridSetOpen}>그리드 설정</Button>
                    <Menu
                        anchorEl={anchorEl}
                        open={anchorOpen}
                        onClose={handleGridSetClose}
                        PaperProps={{
                            style: gridSetMenuStyles
                        }}
                    >
                        <ListItemText>
                            <FormControlLabel control={<Checkbox checked={gridState.stateField.length === gridState.checkField.length}
                                indeterminate={gridState.checkField.length > 0 && gridState.checkField.length !== gridState.stateField.length} onChange={handleCheckboxAllChange} />} label="전체" sx={{ pl: 2 }} />
                            <Typography component="span" className="cursorp" sx={{ float: "right", pr: .5 }} onClick={onSaveGridField}>
                                <CheckCircleOutlined /> 저장
                            </Typography>
                        </ListItemText>
                        <Divider />
                        {
                            _.map(gridState.stateField, (obj, i) => (
                                <FormControl fullWidth key={i}>
                                    <FormControlLabel control={<Checkbox id={"gridField_" + obj.colId} checked={obj.show} onChange={handleCheckboxChange} />} label={obj.name} sx={{ pl: 2 }} />
                                </FormControl>
                            ))
                        }
                    </Menu>
                </Grid>

                <Grid item md={12} id="policyNoticeGrid">
                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                        <AgGridModule
                            gridName="policyNoticeGridApi"
                            columnDefs={policyColumnDefs}
                            rowData={policyData}
                            onCellMouseDown={onCellMouseDown}
                            onDragStopped={onDragStopped}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>
                </Grid>

                <Grid item md={6} mt={.5}>
                    <Button variant="outlined" color="primary" size="small" sx={{ mr: .5 }} onClick={() => onActiveListPolicy("활성")}>활성</Button>
                    <Button variant="outlined" color="secondary" size="small" onClick={() => onActiveListPolicy("비활성")}>비활성</Button>
                    <Button color="primary" size="small" sx={{ ml: 1 }} startIcon={<DownloadOutlined />} onClick={() => onDownloadExcel()}>내보내기</Button>
                </Grid>
                <Grid item md={6} mt={.5} align="right">
                    <WindowOpener className="inline-block" url="/popup/setting/userSetting/reg/0" width="600" height="340" bridge={getPolicyList}>
                        <Button variant="contained" color="primary" size="small" sx={{ mr: .5 }}>추가</Button>
                    </WindowOpener>
                    <Button variant="contained" color="secondary" size="small" onClick={onDeleteListPolicy}>삭제</Button>
                </Grid>

                <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                    <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
                </Snackbar>
                {showLoader && (<Loader />)}
            </Grid>
        </>
    );
};

export default UserSettingIndex;