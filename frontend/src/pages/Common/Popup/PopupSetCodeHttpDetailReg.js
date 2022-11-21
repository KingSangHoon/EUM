import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, TextField, Checkbox } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj } from '../../../lib/common';

const PopupSetCodeHttpDetailReg = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);
    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        name: "",
        subType: "",
        description: "",
        type: "",
        id: 0,
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
                setState({ ...state, type: jsonParam.type, id: jsonParam.id });
                if (params.id === "0") {
                    // 그룹 추가
                } else {
                    // 그룹 수정
                    axiosConf.get("/api/setting/code/http/findHttpDetailById/" + jsonParam.id).then(res => {
                        setParamValue(jsonParam);
                        setRegionType(regionType);

                        setState({ ...state, subType: res.data.subType, description: res.data.description, name: res.data.name, type: jsonParam.type, id: jsonParam.id});
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
            subType: state.subType,
            description: state.description,
            type: state.type,
            id: state.id
        };

        requestUrl = params.id === "0" ? "createHttpDetail" : "updateHttpDetail/" + params.id;

        applyPolicySave(requestUrl, requestData);
    }

    const applyPolicySave = (requestUrl, requestData) => {
        axiosConf.post("/api/setting/code/http/" + requestUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess(requestData);
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="HTTP 설정"></CardHeader>
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

                    <FormControl sx={{ width: "15%", mt: .5 }}>sub_type</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="subType"
                            placeholder={regionType}
                            size="small"
                            value={state.subType}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: .5 }}>description</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="description"
                            placeholder={regionType}
                            size="small"
                            value={state.description}
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

export default PopupSetCodeHttpDetailReg;