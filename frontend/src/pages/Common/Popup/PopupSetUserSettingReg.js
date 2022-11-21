import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, TextField, FormControl, MenuItem, RadioGroup, FormControlLabel, Radio, OutlinedInput, InputAdornment, IconButton } from '@mui/material';

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';

// assets
import { EyeOutlined, EyeInvisibleOutlined } from '@ant-design/icons';

const PopupSetUserSettingReg = () => {
    const params = useParams();

    const [state, setState] = useState({
        loginId: "",
        password: "",
        username: "",
        email: "",
        phoneNumber: "",
        role: "ROLE_USER",
        active: "0"
    });

    const [showPassword, setShowPassword] = useState(false);

    useEffect(() => {
        if (params.id !== "0") {
            getFindoneInfo();
        }

        hiddenComponentPopup();
        window.addEventListener("resize", hiddenComponentPopup);

        return () => {
            window.removeEventListener('resize', hiddenComponentPopup);
        }
    }, []);

    const getFindoneInfo = () => {
        axiosConf.get("/api/setting/user/find/" + params.id).then(res => {
            setState({
                ...state,
                loginId: res.data.loginId || "",
                username: res.data.username || "",
                email: res.data.email || "",
                phoneNumber: res.data.phoneNumber || "",
                role: res.data.role || "ROLE_USER",
                active: res.data.active ? "0" : "1"
            });
        });
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    }

    const onSavePolicyValidation = () => {
        if (state.loginId === "") {
            alert("로그인 아이디를 입력해주세요.");
            return;
        }

        if (state.password === "") {
            alert("비밀번호를 입력해 주세요.");
            return;
        }

        if (state.username === "") {
            alert("이름을 입력해 주세요.");
            return;
        }

        if (state.email === "") {
            alert("이메일을 입력해 주세요.");
            return;
        }

        if (state.phoneNumber === "") {
            alert("전화번호를 입력해 주세요.");
            return;
        }

        applyPolicySave(state);
    }

    const applyPolicySave = (requestData) => {
        let requestUrl = "/api/setting/user";

        requestData.active = requestData.active === "0" ? true : false;

        if (params.id === "0") {
            requestUrl += "/insert";
        } else {
            requestData.userId = parseFloat(params.id);
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
            <CardHeader title="사용자 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root': { mb: 1 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "10%", mt: .5 }}>아이디</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            name="loginId"
                            placeholder="아이디"
                            size="small"
                            InputProps={{
                                readOnly: params.id === "0" ? false : true,
                            }}
                            value={state.loginId}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: .5 }}>비밀번호</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <OutlinedInput
                            type={showPassword ? 'text' : 'password'}
                            name="password"
                            placeholder="비밀번호"
                            size="small"
                            value={state.password}
                            onChange={handleKeyPress}
                            sx={{ mb: 1 }}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={handleClickShowPassword}
                                        edge="end"
                                        size="large"
                                    >
                                        {showPassword ? <EyeOutlined /> : <EyeInvisibleOutlined />}
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: .5 }}>이름</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            name="username"
                            placeholder="이름"
                            size="small"
                            value={state.username}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: .5 }}>이메일</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            name="email"
                            placeholder="이메일"
                            size="small"
                            value={state.email}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: .5 }}>전화번호</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            name="phoneNumber"
                            placeholder="전화번호"
                            size="small"
                            value={state.phoneNumber}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: .5 }}>권한</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            select
                            name="role"
                            size="small"
                            value={state.role}
                            onChange={handleKeyPress}
                        >
                            <MenuItem value="ROLE_USER">사용자</MenuItem>
                            <MenuItem value="ROLE_ADMIN">관리자</MenuItem>
                        </TextField>
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: .5 }}>활성</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <RadioGroup row name="active" onChange={handleKeyPress} value={state.active}>
                            <FormControlLabel value="0" control={<Radio />} label="활성" />
                            <FormControlLabel value="1" control={<Radio />} label="비활성" />
                        </RadioGroup>
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

export default PopupSetUserSettingReg;