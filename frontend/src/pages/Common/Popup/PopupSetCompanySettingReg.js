import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, TextField, FormControl } from '@mui/material';

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';

const PopupSetCompanySettingReg = () => {
    const params = useParams();

    const [state, setState] = useState({
        companyName: "",
        description: ""
    });

    useEffect(() => {
        if (params.id !== "0") {
            getFindoneStateData();
        }

        hiddenComponentPopup();
        window.addEventListener("resize", hiddenComponentPopup);

        return () => {
            window.removeEventListener('resize', hiddenComponentPopup);
        }
    }, []);

    const getFindoneStateData = () => {
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);

                setState({
                    companyName: jsonParam.data.companyName,
                    description: jsonParam.data.description
                });
            }
        }, 100);
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const onSavePolicyValidation = () => {
        if (state.companyName === "") {
            alert("이름을 입력해주세요.");
            return;
        }

        applyPolicySave(state);
    }

    const applyPolicySave = (requestData) => {
        let requestUrl = "/api/setting/company";

        if (params.id === "0") {
            requestUrl += "/insert";
        } else {
            requestData.companyId = parseFloat(params.id);
            requestUrl += "/update";
        }

        axiosConf.post(requestUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="고객사 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root': { mb: 1 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "10%", mt: .5 }}>이름</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            name="companyName"
                            placeholder="이름"
                            size="small"
                            value={state.companyName}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: .5 }}>설명</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            multiline
                            rows={5}
                            name="description"
                            placeholder="설명"
                            size="small"
                            value={state.description}
                            onChange={handleKeyPress}
                        />
                    </FormControl>
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>
        </Card>
    );
};

export default PopupSetCompanySettingReg;