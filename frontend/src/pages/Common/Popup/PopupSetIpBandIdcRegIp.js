import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, TextField } from '@mui/material';

import axiosConf from '../../../axios';
import { hiddenComponentPopup, gridApiObj, ipToLong} from '../../../lib/common';

const PopupSetIpBandIdcRegIp = () => {
    const params = useParams();

    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        ipType: "0",
        startIp: "",
        endIp: "",
        chkMapping: false,
        mappingId:0
    });

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
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);
                setParamValue(jsonParam);

                if (params.id === "0") {
                    setState({
                        ...state,
                        mappingId: jsonParam.id
                    });
                } else {
                    setState({
                        ...state,
                        id: jsonParam.id,
                        startIp: jsonParam.startIp,
                        endIp: jsonParam.endIp,
                        mappingId: jsonParam.mappingId,
                    });
                }
            }
        }, 100);
    }

    const handleKeyPress = (e) => { setState({ ...state, [e.target.name]: e.target.value }); }
    const handleResize = () => { _.map(gridApiObj, (obj) => { if (obj) obj.api.sizeColumnsToFit(); }); }

    const onSavePolicyValidation = () => {
        if (!_.isEmpty(paramValue)) {
            let requestData = [];
            let requestUrl = "";

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


            requestUrl = params.id === "0" ? "createIdcMappingIp" : "updateIdcMappingIp/" + params.id;

            requestData = {
                id: Number(params.id),
                mappingId: state.mappingId,
                startIp: state.startIp,
                endIp: state.endIp
            };

            applyPolicySave(requestUrl, requestData);
        }
    }

    const applyPolicySave = (requestUrl, requestData) => {
        axiosConf.post("/api/setting/geoIdc/" + requestUrl, requestData).then(res => {
            if (res.data) {
                alert("저장 되었습니다.");
                window.opener.onSuccess(requestData);
                window.close();
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

                </Box>

                <Box component="div" textAlign="center" sx={{ pt: 1, pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>
        </Card>
    );
};

export default PopupSetIpBandIdcRegIp;