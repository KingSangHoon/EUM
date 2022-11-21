import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, TextField, RadioGroup, Radio, Checkbox, FormHelperText, Grid, Typography } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj, ipTypeCheck, ipToLong, scrollGridCnt, numberWithCommas } from '../../../lib/common';

const PopupSetIpBandCountryRegIp = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);

    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        ipType: "0",
        startIp: "",
        endIp: "",
        chkMapping: false
    });

    const [listData, setListData] = useState([]);

    const [selectRegionData, setSelectRegionData] = useState({
        primaryId: null,
        sub1Id: null,
        sub2Id: null,
        selectName: "-"
    });


    useEffect(() => {
        getFindOneStateData();

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

    const getFindOneStateData = () => {
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);

                if (!jsonParam.primaryId) {
                    return;
                }

                setParamValue(jsonParam);

                if (params.id === "0") {
                    // ip 맵핑 지역 그리드

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
            setShowLoader(false);

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

    const onSavePolicyValidation = () => {
        console.log(paramValue)
        if (!_.isEmpty(paramValue)) {
            let requestData = [];
            let requestUrl = "";
            let isPublic = false;


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
console.log(requestData)
            //applyPolicySave(requestUrl, requestData, isPublic);
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

export default PopupSetIpBandCountryRegIp;