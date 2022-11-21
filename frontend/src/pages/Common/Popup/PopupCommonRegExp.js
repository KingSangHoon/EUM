import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { Card, CardHeader, CardContent, Box, TextField, Button, Divider } from '@mui/material';

import { hiddenComponentPopup } from '../../../lib/common';

const matchStyle = {
    "& .MuiOutlinedInput-root.Mui-focused": {
        boxShadow: "0 0 0 2px rgb(160 221 114 / 20%)"
    },
    "& .Mui-focused .MuiOutlinedInput-notchedOutline": {
        borderColor: "#a0dd72",
    },
    "& .MuiOutlinedInput-root:hover .MuiOutlinedInput-notchedOutline": {
        borderColor: "#a0dd72"
    },
    "& .MuiOutlinedInput-notchedOutline": {
        borderColor: "#1bb934"
    },
    "& .MuiFormHelperText-root": {
        color: "#1bb934"
    }
};

const misMatchStyle = {
    "& .MuiOutlinedInput-root.Mui-focused": {
        boxShadow: "0 0 0 2px rgb(255 77 79 / 20%)"
    },
    "& .Mui-focused .MuiOutlinedInput-notchedOutline": {
        borderColor: "#ffa39e",
    },
    "& .MuiOutlinedInput-root:hover .MuiOutlinedInput-notchedOutline": {
        borderColor: "#ffa39e"
    },
    ".MuiOutlinedInput-notchedOutline": {
        borderColor: "#ff4d4f"
    },
    "& .MuiFormHelperText-root": {
        color: "#ff4d4f"
    }
};

const PopupCommonRegExp = () => {
    const [field, setField] = useState("");
    const [chkField, setChkField] = useState("");

    const [inputStyles, setInputStyles] = useState({});
    const [inputHelperTxt, setInputHelperTxt] = useState("");

    useEffect(() => {
        getFindoneStateData();

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
                setField(jsonParam.field);
            }
        }, 100);
    }

    const handleKeyPress = (e) => {
        if (e.target.name === "field") {
            setField(e.target.value);
        } else {
            setChkField(e.target.value);
        }
    }

    const cleanTitle = (title) => {
        return title.replace(/[\\/:*?"<>|]/g, function (ch) {
            return "__i!__" + ch.charCodeAt(0) + "__!i__";
        });
    }

    const chkRegex = () => {
        const regex = new RegExp(cleanTitle(field));

        if (regex.test(cleanTitle(chkField))) {
            setInputHelperTxt("일치");
            setInputStyles(matchStyle);
        } else {
            setInputHelperTxt("불일치");
            setInputStyles(misMatchStyle);
        }
    }

    return (
        <Card>
            <CardHeader title="패턴 테스트"></CardHeader>
            <CardContent>
                <Box>
                    <TextField
                        fullWidth
                        multiline
                        rows={5}
                        name="field"
                        placeholder="문자열"
                        size="small"
                        value={field}
                        onChange={handleKeyPress}
                    />
                </Box>

                <Box mt={1} mb={2}>
                    <TextField
                        fullWidth
                        multiline
                        rows={5}
                        name="chkField"
                        placeholder="패턴 테스트 문자열"
                        size="small"
                        value={chkField}
                        onChange={handleKeyPress}
                        sx={inputStyles}
                        helperText={inputHelperTxt}
                    />
                </Box>

                <Divider sx={{ mb: 1 }} />

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={chkRegex}>Check</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>
        </Card>
    );
};

export default PopupCommonRegExp;