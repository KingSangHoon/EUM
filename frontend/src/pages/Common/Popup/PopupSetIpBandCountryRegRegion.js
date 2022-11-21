import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, TextField, Checkbox } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj } from '../../../lib/common';

const PopupSetIpBandCountryRegRegion = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);
    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        name: "",
        code: "",
        chkUnknown: false,
        chkArea: false
    });

    const [regionType, setRegionType] = useState("");
    const [gridData, setGridData] = useState([]);

    const gridColumnDefs = [{
        headerName: 'Name',
        field: 'name',
        cellRendererParams: {
            checkbox: true
        },
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
            let regionType = "대륙";

            if (param) {
                const jsonParam = JSON.parse(param.value);

                if (jsonParam.countryId != null) {
                    regionType = "국가";

                    if (params.id === "0") {
                        paramId += "findAllForContinentInCountry/" + jsonParam.continentId;
                    } else {
                        paramId += "findContinentInCountry/" + jsonParam.continentId + "/" + jsonParam.countryId;
                    }
                } else {
                    if (params.id === "0") {
                        paramId += "findCountryByModifyFlagFalse";
                    } else {
                        paramId += "findInfoContinent/" + jsonParam.continentId;
                    }
                }
                axiosConf.get("/api/setting/geoCountry/" + paramId).then(res => {
                    setParamValue(jsonParam);
                    setRegionType(regionType);

                    if (params.id === "0") {
                        setGridData(res.data);
                    } else {
                        setGridData(res.data.geo);
                        chkGridSelectedData(res.data.checked, regionType);

                        setState({ ...state, name: res.data.name, code: res.data.continentCode ? res.data.continentCode : res.data.countryCode, chkArea: res.data.checked.length > 0 });
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

        if (regionType === "대륙") {
            _.forEach(selectedData, (obj) => {
                if (obj.continentId) {
                    selectedId.push(obj.continentId);
                }
            });

            gridApiObj.policyListGridApi.api.forEachNode((node) => {
                if (selectedId.indexOf(node.data.continentId) !== -1) {
                    node.setSelected(true);
                }
            });
        } else if (regionType === "국가") {
            _.forEach(selectedData, (obj) => {
                if (obj.continentId && obj.countryId) {
                    selectedId.push(obj.continentId + "_" + obj.countryId);
                }
            });

            gridApiObj.policyListGridApi.api.forEachNode((node) => {
                if (node.data.continentId && node.data.countryId) {
                    if (selectedId.indexOf(node.data.continentId + "_" + node.data.countryId) !== -1) {
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
        if (data.country) {
            return {
                group: true,
                children: data.country
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
            name: state.name,
            code: state.code
        };

        if (params.id === "0") {
            if (regionType === "대륙") {
                requestData.unKnownFlag = state.chkUnknown;
            } else if (regionType === "국가") {
                requestData.continentId = paramValue.continentId;
                requestData.unKnownFlag = state.chkUnknown;
            }
        }

        if (regionType === "대륙") {
            requestUrl = params.id === "0" ? "createContinent" : "updateContinent/" + params.id;

            _.forEach(selectedNodes, (obj) => {
                geoData.push({
                    "continentId": obj.continentId,
                    "countryId": 0,
                });
            });
        } else if (regionType === "국가") {
            const param = document.getElementById("stateInput");
            const jsonParam = JSON.parse(param.value);

            requestUrl = params.id === "0" ? "createCountry" : "updateCountry/" + params.id;

            _.forEach(selectedNodes, (obj) => {

                geoData.push({
                    "continentId": obj.continentId,
                    "countryId": obj.countryId,
                    "customContinentId": jsonParam.continentId,
                });
            });
        }

        requestData.geo = geoData;
        applyPolicySave(requestUrl, requestData);
    }

    const applyPolicySave = (requestUrl, requestData) => {
        axiosConf.post("/api/setting/geoCountry/" + requestUrl, requestData).then(res => {
            if(res.data){
                alert("저장 되었습니다.");
                window.opener.onSuccess(paramValue);
                window.close();
            }else{
                alert("대륙(국가)코드가 중복되었습니다.");
            }
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

                    <FormControl sx={{ width: "15%", mt: .5 }}>{regionType === "대륙" ? "대륙코드" : "국가코드"}</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="code"
                            placeholder={regionType === "대륙" ? "대륙코드" : "국가코드"}
                            size="small"
                            value={state.code}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

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

export default PopupSetIpBandCountryRegRegion;