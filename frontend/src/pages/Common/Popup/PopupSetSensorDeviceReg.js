import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import moment from 'moment';
import { Card, CardHeader, CardContent, Button, Box, TextField, FormControl, MenuItem, Tooltip, Grid } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faQuestionCircle } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';

const PopupSetSensorDeviceReg = () => {
    const params = useParams();

    const [state, setState] = useState({
        sensorName: "",
        sensorAlias: "",
        sensorOs: "",
        sensorVersion: "",
        bpfFilter: "",
        description: "",
        active: "0",
        deviceActivated: "0",
        deviceActivatedFalseDate: "",
        deviceActivatedTrueDate: "",
        lastConnectDate: ""
    });

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
        axiosConf.get("/api/setting/sensorDevice/find/" + params.id).then(res => {
            setState({
                sensorName: res.data.sensorName,
                sensorAlias: res.data.sensorAlias,
                sensorOs: res.data.sensorOs,
                sensorVersion: res.data.sensorVersion,
                bpfFilter: res.data.bpfFilter,
                description: res.data.description,
                active: res.data.active ? "0" : "1",
                deviceActivated: res.data.deviceActivated ? "0" : "1",
                deviceActivatedFalseDate: res.data.deviceActivatedFalseDate ? moment(res.data.deviceActivatedFalseDate).format("YYYY-MM-DD HH:mm:ss") : "",
                deviceActivatedTrueDate: res.data.deviceActivatedTrueDate ? moment(res.data.deviceActivatedTrueDate).format("YYYY-MM-DD HH:mm:ss") : "",
                lastConnectDate: res.data.lastConnectDate ? moment(res.data.lastConnectDate).format("YYYY-MM-DD HH:mm:ss") : ""
            });
        });
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const onSavePolicyValidation = () => {
        const requestData = {
            sensorId: params.id,
            sensorAlias: state.sensorAlias,
            active: state.active === "0" ? true : false,
            description: state.description,
            sensorOs: state.sensorOs,
            sensorVersion: state.sensorVersion,
            bpfFilter: state.bpfFilter
        };

        applyPolicySave(requestData);
    }

    const applyPolicySave = (requestData) => {
        axiosConf.post("/api/setting/sensorDevice/update", requestData).then(res => {
            applyServer();
        });
    }

    const applyServer = () => {
        axiosConf.post("/api/set/applyPCapBpf/" + params.id).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="Sensor Device 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root': { mb: 1 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "25%", mt: .5 }}>Sensor Name</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="sensorName"
                            placeholder="Sensor Name"
                            size="small"
                            InputProps={{
                                readOnly: true
                            }}
                            value={state.sensorName}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Sensor Alias</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="sensorAlias"
                            placeholder="Sensor Alias"
                            size="small"
                            value={state.sensorAlias}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Sensor OS</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="sensorOs"
                            placeholder="Sensor OS"
                            size="small"
                            InputProps={{
                                readOnly: true
                            }}
                            value={state.sensorOs}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Sensor Version</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="sensorVersion"
                            placeholder="Sensor Version"
                            size="small"
                            InputProps={{
                                readOnly: true
                            }}
                            value={state.sensorVersion}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>
                        <Grid container>
                            <Grid item sm={9}> NIC Packet Capture Filter</Grid>
                            <Grid item sm={3}>
                                <Tooltip title="입력 형식: BPF(Berkeley Packet Filter)">
                                    <FontAwesomeIcon icon={faQuestionCircle} className="font-primary cursorp" />
                                </Tooltip>
                            </Grid>
                        </Grid>
                    </FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            multiline
                            rows={2}
                            name="bpfFilter"
                            placeholder="입력 형식: BPF(Berkeley Packet Filter)"
                            size="small"
                            value={state.bpfFilter}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Description</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            multiline
                            rows={2}
                            name="description"
                            placeholder="Description"
                            size="small"
                            value={state.description}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Active</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            select
                            name="active"
                            size="small"
                            value={state.active}
                            onChange={handleKeyPress}
                        >
                            <MenuItem value="0">활성</MenuItem>
                            <MenuItem value="1">비활성</MenuItem>
                        </TextField>
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Device Activated</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            select
                            name="deviceActivated"
                            size="small"
                            value={state.deviceActivated}
                            onChange={handleKeyPress}
                        >
                            <MenuItem value="0">활성</MenuItem>
                            <MenuItem value="1">비활성</MenuItem>
                        </TextField>
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Device Activated False Date</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="deviceActivatedFalseDate"
                            placeholder="YYYY-MM-DD HH:mm:ss"
                            size="small"
                            InputProps={{
                                readOnly: true
                            }}
                            value={state.deviceActivatedFalseDate}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Device Activated True Date</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="deviceActivatedTrueDate"
                            placeholder="YYYY-MM-DD HH:mm:ss"
                            size="small"
                            InputProps={{
                                readOnly: true
                            }}
                            value={state.deviceActivatedTrueDate}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>Last Connect Date</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="lastConnectDate"
                            placeholder="YYYY-MM-DD HH:mm:ss"
                            size="small"
                            InputProps={{
                                readOnly: true
                            }}
                            value={state.lastConnectDate}
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

export default PopupSetSensorDeviceReg;