import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, TextField, RadioGroup, Radio, Checkbox, FormHelperText, Grid, Typography } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj, ipTypeCheck, ipToLong, scrollGridCnt, numberWithCommas } from '../../../lib/common';

const PopupSetIpBandDomesticRegIp = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);

    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        ipType: "0",
        startIp: "",
        endIp: "",
        chkMapping: false
    });

    const [groupData, setGroupData] = useState([]);
    const [listData, setListData] = useState([]);

    const [currentPageIpList, setCurrentPageIpList] = useState(0);
    const [maxPageIpList, setMaxPageIpList] = useState(0);
    const [totalIpList, setTotalIpList] = useState(0);

    const [selectRegionData, setSelectRegionData] = useState({
        primaryId: null,
        sub1Id: null,
        sub2Id: null,
        selectName: "-"
    });
    const [selectIpIdArr, setSelectIpIdArr] = useState([]);
    const [selectIpData, setSelectIpData] = useState([]);

    const groupColumnDefs = [{
        headerName: "Name",
        field: 'name',
        cellClass: ['cursorp'],
        cellStyle: function (params) {
            if (params.data.selected) return { backgroundColor: '#BCDFFB' };
        },
        cellRendererParams: {
            suppressCount: true
        },
        cellRenderer: 'agGroupCellRenderer',
        filter: 'agTextColumnFilter'
    }];

    const listColumnDefs = [{
        headerName: '',
        width: 30,
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true
    },
    {
        headerName: 'Start IP',
        field: 'startIp',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'End IP',
        field: 'endIp',
        filter: "agTextColumnFilter"
    }];

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
            if (gridApiObj.policyListGridApi) {
                gridApiObj.policyListGridApi.api.sizeColumnsToFit();
                chkListSelectIpData();
            }
        }, 200);
    }, [listData]);

    useEffect(() => {
        if (selectRegionData.primaryId) {
            getPolicyIpListMaxCount();
        }
    }, [selectRegionData]);

    const getFindoneStateData = () => {
        setShowLoader(true);

        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);
                const sub1Id = jsonParam.sub1Id || 0;
                const sub2Id = jsonParam.sub2Id || 0;

                if (!jsonParam.primaryId) {
                    return;
                }

                setParamValue(jsonParam);

                if (params.id === "0") {
                    // ip 맵핑 지역 그리드
                    axiosConf.get("/api/setting/geoDomestic/findDomesticAll/" + jsonParam.primaryId + "/" + sub1Id + "/" + sub2Id).then(res => {
                        _.forEach(res.data, (obj) => {
                            obj.groupName = obj.name;
                            obj.isPrimary = true;

                            if (obj.sub1.length === 0) {
                                delete obj.sub1;
                            }

                            _.forEach(obj.sub1, (sub1Obj) => {
                                sub1Obj.groupName = obj.name + " > " + sub1Obj.name;
                                sub1Obj.isSub1 = true;

                                if (sub1Obj.sub2.length === 0) {
                                    delete sub1Obj.sub2;
                                }

                                _.forEach(sub1Obj.sub2, (sub2Obj) => {
                                    sub2Obj.groupName = sub1Obj.groupName + " > " + sub2Obj.name;
                                    sub2Obj.isSub2 = true;
                                });
                            });
                        });

                        setGroupData(res.data);
                        setShowLoader(false);
                    });
                } else {
                    // 사설 ip 수정
                    setState({
                        ...state,
                        startIp: jsonParam.startIp,
                        endIp: jsonParam.endIp
                    });

                    setShowLoader(false);
                }
            } else {
                setShowLoader(false);
            }
        }, 100);
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleCheckboxChange = (e) => {
        setState({ ...state, [e.target.name]: e.target.checked });
        handleResize();
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const formatGroupChild = (data) => {
        if (data.sub1 || data.sub2) {
            return {
                group: true,
                children: data.sub1 || data.sub2
            };
        } else {
            return null;
        }
    }

    const onGroupClickEvt = (event) => {
        if (event.data) {
            setShowLoader(true);

            if (event.data.isPrimary) {
                setSelectRegionData({
                    primaryId: event.data.id,
                    sub1Id: null,
                    sub2Id: null,
                    selectName: event.data.groupName
                });
            } else if (event.data.isSub1) {
                setSelectRegionData({
                    primaryId: event.data.primaryId,
                    sub1Id: event.data.id,
                    sub2Id: null,
                    selectName: event.data.groupName
                });
            } else if (event.data.isSub2) {
                setSelectRegionData({
                    primaryId: event.data.primaryId,
                    sub1Id: event.data.sub1Id,
                    sub2Id: event.data.id,
                    selectName: event.data.groupName
                });
            }
        }
    }

    const getPolicyIpListMaxCount = () => {
        let regionIdParam = selectRegionData.primaryId;

        if (selectRegionData.primaryId && selectRegionData.sub1Id && selectRegionData.sub2Id) {
            regionIdParam += "/" + selectRegionData.sub1Id + "/" + selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            regionIdParam += "/" + selectRegionData.sub1Id;
        }

        axiosConf.get('/api/setting/geoDomestic/countPartDomesticIpRange/' + regionIdParam).then(res => {
            const maxPage = _.floor(res.data / scrollGridCnt);

            setMaxPageIpList(maxPage);
            setCurrentPageIpList(0);
            setTotalIpList(res.data);

            getPolicyIpListData(selectRegionData);
        });
    }

    const getPolicyIpListData = () => {
        let regionIdParam = selectRegionData.primaryId;

        if (selectRegionData.sub1Id && selectRegionData.sub2Id) {
            regionIdParam += "/" + selectRegionData.sub1Id + "/" + selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            regionIdParam += "/" + selectRegionData.sub1Id;
        }

        axiosConf.get('/api/setting/geoDomestic/findPartDomesticIpRange/' + regionIdParam + "/" + scrollGridCnt + "/0").then(res => {
            setListData(res.data);
            setShowLoader(false);

            if (gridApiObj.policyListGridApi) {
                gridApiObj.policyListGridApi.api.ensureIndexVisible(0, 'top');
            }
        });
    }

    const addPolicyIpListData = (page) => {
        const offset = page * scrollGridCnt;
        let regionIdParam = selectRegionData.primaryId;

        if (selectRegionData.sub1Id && selectRegionData.sub2Id) {
            regionIdParam += "/" + selectRegionData.sub1Id + "/" + selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            regionIdParam += "/" + selectRegionData.sub1Id;
        }

        axiosConf.get('/api/setting/geoDomestic/findPartDomesticIpRange/' + regionIdParam + "/" + scrollGridCnt + "/" + offset).then(res => {
            setListData([...listData, ...res.data]);
            setShowLoader(false);
        });
    }

    const onScrollLoadEvent = (e) => {
        if (e.target.classList.value.includes("ag-body-viewport") && listData.length > 0) {
            if ((e.target.scrollTop + e.target.clientHeight) === e.target.scrollHeight) {
                if (maxPageIpList > currentPageIpList) {
                    setShowLoader(true);
                    setCurrentPageIpList(prev => prev + 1);
                    addPolicyIpListData(currentPageIpList + 1);
                }
            }
        }
    }

    const chkListSelectIpData = () => {
        gridApiObj.policyListGridApi.api.forEachNode((node) => {
            if (selectIpIdArr.indexOf(node.data.id) !== -1) {
                node.setSelected(true);
            }
        });
    }

    const onRowIpSelected = (params) => {
        if (params.data) {
            if (params.node.isSelected()) {
                if (selectIpIdArr.indexOf(params.data.id) === -1) {
                    setSelectIpIdArr([...selectIpIdArr, params.data.id]);
                    setSelectIpData([...selectIpData, params.data]);
                }
            } else {
                if (selectIpIdArr.indexOf(params.data.id) !== -1) {
                    selectIpIdArr.splice(selectIpIdArr.indexOf(params.data.id), 1);

                    const filterIpData = _.filter(selectIpData, (obj) => {
                        return obj.id !== params.data.id;
                    });

                    setSelectIpIdArr(selectIpIdArr);
                    setSelectIpData(filterIpData);
                }
            }
        }
    }

    const onSavePolicyValidation = () => {
        if (!_.isEmpty(paramValue)) {
            let requestData = [];
            let requestUrl = "";
            let isPublic = false;

            if (state.chkMapping) {
                if (selectIpData.length === 0) {
                    alert("항목을 선택해주세요.");
                    return;
                }

                _.forEach(selectIpData, (obj) => {
                    requestData.push({
                        primaryId: paramValue.primaryId,
                        sub1Id: paramValue.sub1Id || 0,
                        sub2Id: paramValue.sub2Id || 0,
                        startIp: obj.startIp,
                        endIp: obj.endIp,
                        type: "public"
                    });
                });

                requestUrl = "createMultiMappingDomesticPublic";
                isPublic = true;
            } else {
                const ipFormat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;

                if (state.startIp === "") {
                    alert("Start IP를 입력해주세요.");
                    return;
                }

                if (state.endIp === "") {
                    alert("End IP를 입력해주세요.");
                    return;
                }

                if (!ipFormat.test(state.startIp)) {
                    alert("올바른 Start IP 주소를 입력해주세요.");
                    return;
                }

                if (!ipFormat.test(state.endIp)) {
                    alert("올바른 End IP 주소를 입력해주세요.");
                    return;
                }

                if (ipToLong(state.startIp) >= ipToLong(state.endIp)) {
                    alert("Start IP가 End IP보다 크거나 같습니다.");
                    return;
                }

                if (state.ipType === "0") {
                    if (ipTypeCheck(ipToLong(state.startIp))) {
                        alert("올바른 Start IP 사설IP 대역을 입력해주세요.");
                        return;
                    }
                    if (ipTypeCheck(ipToLong(state.endIp))) {
                        alert("올바른 End IP 사설IP 대역을 입력해주세요.");
                        return;
                    }

                    requestUrl = params.id === "0" ? "createMappingDomesticPrivate" : "updateMappingDomesticPrivate/" + params.id;

                    requestData = {
                        primaryId: paramValue.primaryId,
                        sub1Id: paramValue.sub1Id || 0,
                        sub2Id: paramValue.sub2Id || 0,
                        startIp: state.startIp,
                        endIp: state.endIp,
                        type: "private"
                    };
                } else {
                    requestUrl = params.id === "0" ? "createMappingDomesticPublic" : "updateMappingDomesticPublic/" + params.id;
                    isPublic = true;

                    requestData = {
                        primaryId: paramValue.primaryId,
                        sub1Id: paramValue.sub1Id || 0,
                        sub2Id: paramValue.sub2Id || 0,
                        startIp: state.startIp,
                        endIp: state.endIp,
                        type: "public"
                    };
                }
            }

            applyPolicySave(requestUrl, requestData, isPublic);
        }
    }

    const applyPolicySave = (requestUrl, requestData, isPublic) => {
        axiosConf.post("/api/setting/geoDomestic/" + requestUrl, requestData).then(res => {
            if (res.data) {
                alert("저장 되었습니다.");
                window.opener.onSuccess(paramValue);
                window.close();
            } else {
                if (isPublic) {
                    alert("IP 대역이 중복 되었습니다.");
                }
            }
        });
    }

    return (
        <Card>
            <CardHeader title="IP 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{
                        '& .MuiFormControl-root': { mb: .5 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    {
                        !state.chkMapping && <>
                            <FormControl sx={{ width: "15%", mt: .5 }}>유형</FormControl>
                            <FormControl sx={{ width: "85%" }}>
                                <RadioGroup row name="ipType" onChange={handleKeyPress} value={state.ipType}>
                                    <FormControlLabel value="0" control={<Radio disabled={params.id !== "0"} />} label="사설 IP" />
                                    <FormControlLabel value="1" control={<Radio disabled={params.id !== "0"} />} label="공인 IP" />
                                </RadioGroup>
                            </FormControl>

                            <FormControl sx={{ width: "15%", mt: .5 }}>Start IP</FormControl>
                            <FormControl sx={{ width: "85%" }}>
                                <TextField
                                    name="startIp"
                                    placeholder="Start IP"
                                    size="small"
                                    value={state.startIp}
                                    onChange={handleKeyPress}
                                />
                            </FormControl>

                            <FormControl sx={{ width: "15%", mt: .5 }}>End IP</FormControl>
                            <FormControl sx={{ width: "85%" }}>
                                <TextField
                                    name="endIp"
                                    placeholder="End IP"
                                    size="small"
                                    value={state.endIp}
                                    onChange={handleKeyPress}
                                />
                            </FormControl>
                        </>
                    }

                    {
                        (params.id === "0" && paramValue.modifyFlag) && <>
                            <FormControl sx={{ width: "15%", mt: .5 }}>IP 맵핑</FormControl>
                            <FormControl sx={{ width: "85%" }}>
                                <FormControlLabel control={<Checkbox name="chkMapping" checked={state.chkMapping} onChange={handleCheckboxChange} />} />
                            </FormControl>
                        </>
                    }
                </Box>

                {
                    (params.id === "0" && paramValue.modifyFlag) && <Grid container spacing={.5} className={state.chkMapping ? "" : "none"}>
                        <Grid item sm={6}>
                            <Card>
                                <CardHeader title="지역" />
                                <CardContent>
                                    <Box component="div" className="ag-theme-alpine" sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()}>
                                        <AgGridModule
                                            gridName="policyGroupGridApi"
                                            columnDefs={groupColumnDefs}
                                            rowData={groupData}
                                            rowMultiSelectWithClick={false}
                                            formatGroupChild={formatGroupChild}
                                            onCellClicked={onGroupClickEvt}
                                            onGridReady={onGridReady}
                                            handleResize={handleResize} />
                                    </Box>
                                </CardContent>
                            </Card>
                        </Grid>
                        <Grid item sm={6}>
                            <Card>
                                <CardHeader title={(
                                    <>
                                        IP ( 선택한 지역: <Typography component="span" className="font-blue">{selectRegionData.selectName}</Typography>, Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(totalIpList)}</Typography> )
                                    </>
                                )} />
                                <CardContent>
                                    <Box component="div" className="ag-theme-alpine" sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()} onScrollCapture={onScrollLoadEvent}>
                                        <AgGridModule
                                            gridName="policyListGridApi"
                                            columnDefs={listColumnDefs}
                                            rowData={listData}
                                            onRowSelected={onRowIpSelected}
                                            onGridReady={onGridReady}
                                            handleResize={handleResize} />
                                    </Box>
                                </CardContent>
                            </Card>
                        </Grid>
                    </Grid>
                }

                <Box component="div" textAlign="center" sx={{ pt: 1, pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
        </Card>
    );
};

export default PopupSetIpBandDomesticRegIp;