import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, TextField, Checkbox } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj } from '../../../lib/common';

const PopupSetIpBandIspRegRegion = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);
    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        id: null,
        name: "",
        nameEng: "",
    });

    const [regionType, setRegionType] = useState("");

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

            if (param) {
                const jsonParam = JSON.parse(param.value);
                if (params.id === "0") {
                    // 그룹 추가
                } else {
                    // 그룹 수정
                    axiosConf.get("/api/setting/geoIsp/findIspPrimary/" + jsonParam.id).then(res => {
                        setParamValue(jsonParam);
                        setRegionType(regionType);

                        setState({ ...state, name: res.data.name, nameEng: res.data.nameEng });
                        setShowLoader(false);
                    });
                }

                setShowLoader(false);

            } else {
                setShowLoader(false);
            }
        }, 100);
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const onSavePolicyValidation = () => {
        if (state.name === "") {
            alert(regionType + " 를 입력해주세요.");
            return;
        }

        let requestUrl = "";
        let requestData = {
            name: state.name,
            nameEng: state.nameEng
        };

        requestUrl = params.id === "0" ? "createIspPrimary" : "updateIspPrimary/" + params.id;

        applyPolicySave(requestUrl, requestData);
    }

    const applyPolicySave = (requestUrl, requestData) => {
        axiosConf.post("/api/setting/geoIsp/" + requestUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess(paramValue);
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="ISP 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{ '& .MuiTextField-root, .MuiFormControlLabel-root': { mb: 1 }  }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "15%", mt: .5 }}>Name</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="name"
                            placeholder={regionType}
                            size="small"
                            value={state.name}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: .5 }}>English Name</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="nameEng"
                            placeholder={regionType}
                            size="small"
                            value={state.nameEng}
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

export default PopupSetIpBandIspRegRegion;