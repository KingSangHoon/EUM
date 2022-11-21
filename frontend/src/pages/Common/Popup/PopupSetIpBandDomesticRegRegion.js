import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, TextField, Checkbox } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj } from '../../../lib/common';

const PopupSetIpBandDomesticRegRegion = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);

    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        name: "",
        chkUnknown: false,
        chkArea: false
    });

    const [regionType, setRegionType] = useState("");
    const [gridData, setGridData] = useState([]);

    const gridColumnDefs = [{
        headerName: 'Name',
        field: 'name',
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        cellRenderer: 'agGroupCellRenderer',
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

    const getFindoneStateData = () => {
        setShowLoader(true);

        setTimeout(() => {
            const param = document.getElementById("stateInput");
            let paramId = "";
            let regionType = "시/도";

            if (param) {
                const jsonParam = JSON.parse(param.value);

                if (jsonParam.primaryId && jsonParam.sub1Id) {
                    regionType = "읍/면/동";

                    if (params.id === "0") {
                        // 읍/면/동 추가
                        paramId += "findAllForPrimaryInSub1AndSub2/" + jsonParam.primaryId + "/" + jsonParam.sub1Id;
                    } else {
                        // 읍/면/동 수정
                        paramId += "findInfoDomesticSub2/" + jsonParam.primaryId + "/" + jsonParam.sub1Id + "/" + params.id;
                    }
                } else if (jsonParam.primaryId) {
                    regionType = "시/군/구";

                    if (params.id === "0") {
                        // 시/군/구 추가
                        paramId += "findAllForPrimaryInSub1/" + jsonParam.primaryId;
                    } else {
                        // 시/군/구 수정
                        regionType = "시/군/구";
                        paramId += "findInfoDomesticSub1/" + jsonParam.primaryId + "/" + params.id;
                    }
                } else {
                    if (params.id === "0") {
                        // 시/도 추가
                        paramId += "findAllForprimary";
                    } else {
                        // 시/도 수정
                        paramId += "findInfoDomesticPrimary/" + params.id;
                    }
                }

                axiosConf.get("/api/setting/geoDomestic/" + paramId).then(res => {
                    setParamValue(jsonParam);
                    setRegionType(regionType);

                    if (params.id === "0") {
                        setGridData(res.data);
                    } else {
                        const targetGridData = res.data.domestic.geoDomestic ? res.data.domestic.geoDomestic : res.data.domestic;
                        setGridData(targetGridData);

                        const targetSelectedData = res.data.selected.geo ? res.data.selected.geo : res.data.selected;
                        chkGridSelectedData(targetSelectedData, regionType);

                        setState({ ...state, name: res.data.name, chkArea: targetSelectedData.length > 0 });
                    }

                    setShowLoader(false);
                });
            } else {
                setShowLoader(false);
            }
        }, 100);
    }

    const chkGridSelectedData = (selectedData, regionType) => {
        let selectedId = [];

        if (regionType === "시/도") {
            _.forEach(selectedData, (obj) => {
                if (obj.primary_id) {
                    selectedId.push(obj.primary_id);
                }
            });

            gridApiObj.policyListGridApi.api.forEachNode((node) => {
                if (selectedId.indexOf(node.data.id) !== -1) {
                    node.setSelected(true);
                }
            });
        } else if (regionType === "시/군/구") {
            _.forEach(selectedData, (obj) => {
                if (obj.primaryId && obj.sub1Id) {
                    selectedId.push(obj.primaryId + "_" + obj.sub1Id);
                }
            });

            gridApiObj.policyListGridApi.api.forEachNode((node) => {
                if (node.data.primaryId && node.data.id) {
                    if (selectedId.indexOf(node.data.primaryId + "_" + node.data.id) !== -1) {
                        node.setSelected(true);
                    }
                }
            });
        } else {
            _.forEach(selectedData, (obj) => {
                if (obj.primaryId && obj.sub1Id && obj.sub2Id) {
                    selectedId.push(obj.primaryId + "_" + obj.sub1Id + "_" + obj.sub2Id);
                }
            });

            gridApiObj.policyListGridApi.api.forEachNode((node) => {
                if (node.data.primaryId && node.data.sub1Id && node.data.id) {
                    if (selectedId.indexOf(node.data.primaryId + "_" + node.data.sub1Id + "_" + node.data.id) !== -1) {
                        node.setSelected(true);
                    }
                }
            });
        }
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

    const onSavePolicyValidation = () => {
        if (state.name === "") {
            alert(regionType + " 를 입력해주세요.");
            return;
        }

        const selectedNodes = gridApiObj.policyListGridApi.api.getSelectedRows();

        let geoData = [];
        let requestUrl = "";
        let requestData = {
            name: state.name
        };

        if (params.id === "0") {
            if (regionType === "시/도") {
                requestData.unKnownFlag = state.chkUnknown;
            } else if (regionType === "시/군/구") {
                requestData.primaryId = paramValue.primaryId;
                requestData.unKnownFlag = state.chkUnknown;
            } else if (regionType === "읍/면/동") {
                requestData.primaryId = paramValue.primaryId;
                requestData.sub1Id = paramValue.sub1Id;
            }
        }

        if (regionType === "시/도") {
            requestUrl = params.id === "0" ? "createDomesticPrimary" : "updateDomesticPrimary/" + params.id;

            _.forEach(selectedNodes, (obj) => {
                geoData.push({
                    "customPrimaryId": 0,
                    "customSub1Id": 0,
                    "customSub2Id": 0,
                    "primaryId": obj.id,
                    "sub1Id": 0,
                    "sub2Id": 0
                });
            });
        } else if (regionType === "시/군/구") {
            requestUrl = params.id === "0" ? "createDomesticSub1" : "updateDomesticSub1/" + paramValue.primaryId + "/" + params.id;

            _.forEach(selectedNodes, (obj) => {
                geoData.push({
                    "customPrimaryId": paramValue.primaryId,
                    "customSub1Id": 0,
                    "customSub2Id": 0,
                    "primaryId": obj.primaryId,
                    "sub1Id": obj.id,
                    "sub2Id": 0
                });
            });
        } else {
            requestUrl = params.id === "0" ? "createDomesticSub2" : "updateDomesticSub2/" + paramValue.primaryId + "/" + paramValue.sub1Id + "/" + params.id;

            _.forEach(selectedNodes, (obj) => {
                geoData.push({
                    "customPrimaryId": paramValue.primaryId,
                    "customSub1Id": paramValue.sub1Id,
                    "customSub2Id": 0,
                    "primaryId": obj.primaryId,
                    "sub1Id": obj.sub1Id,
                    "sub2Id": obj.id
                });
            });
        }

        requestData.geo = geoData;

        applyPolicySave(requestUrl, requestData);
    }

    const applyPolicySave = (requestUrl, requestData) => {
        axiosConf.post("/api/setting/geoDomestic/" + requestUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess(paramValue);
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="지역 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root, .MuiFormControlLabel-root': { mb: 1 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "15%", mt: .5 }}>{regionType}</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="name"
                            placeholder={regionType}
                            size="small"
                            value={state.name}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    {
                        params.id === "0" && regionType !== "읍/면/동" && <>
                            <FormControl sx={{ width: "15%", mt: .5 }}>Unknown 생성</FormControl>
                            <FormControl sx={{ width: "85%" }}>
                                <FormControlLabel control={<Checkbox name="chkUnknown" checked={state.chkUnknown} onChange={handleCheckboxChange} />} />
                            </FormControl>
                        </>
                    }

                    <FormControl sx={{ width: "15%", mt: .5 }}>지역 정보 포함</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <FormControlLabel control={<Checkbox name="chkArea" checked={state.chkArea} onChange={handleCheckboxChange} />} />
                    </FormControl>
                </Box>

                <Box component="div" className={state.chkArea ? "ag-theme-alpine" : "none"} sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()}>
                    <AgGridModule
                        gridName="policyListGridApi"
                        columnDefs={gridColumnDefs}
                        rowData={gridData}
                        formatGroupChild={formatGroupChild}
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

export default PopupSetIpBandDomesticRegRegion;