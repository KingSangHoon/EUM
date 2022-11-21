import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, TextField, FormControl, MenuItem, IconButton, OutlinedInput, InputAdornment } from '@mui/material';

import { EyeOutlined, EyeInvisibleOutlined, PlusSquareOutlined, MinusSquareOutlined } from '@ant-design/icons';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { hiddenComponentPopup, gridApiObj } from '../../../lib/common';

const PopupSetSslSettingReg = () => {
    const params = useParams();

    const [state, setState] = useState({
        ipType: "0",
        ipSingle: "",
        ipRangeStart: "",
        ipRangeEnd: "",
        portType: "0",
        portSingle: "",
        portRangeStart: "",
        portRangeEnd: "",
        password: "",
        sslKey: "",
        selectedFile: null,
        isFileUpdate: false
    });

    const [virtualHost, setVirtualHost] = useState([""]);
    const [showPassword, setShowPassword] = useState(false);

    const [gridData, setGridData] = useState([]);

    const gridColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        lockPosition: true
    },
    {
        headerName: 'Sensor Name',
        field: 'sensorName',
        filter: 'agTextColumnFilter'
    },
    {
        headerName: 'Sensor Version',
        field: 'sensorVersion',
        filter: 'agTextColumnFilter'
    }];

    useEffect(() => {
        getSensorDeviceData();

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

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyListGridApi) {
                gridApiObj.policyListGridApi.api.sizeColumnsToFit();

                if (params.id !== "0") {
                    getFindoneInfo();
                }
            }
        }, 200);
    }, [gridData]);

    useEffect(() => {
        handleResize();
    }, [virtualHost]);

    const getSensorDeviceData = () => {
        axiosConf.get("/api/setting/sensorDevice/findAllActive").then(res => {
            setGridData(res.data);
        });
    }

    const getFindoneInfo = () => {
        axiosConf.get("/api/setting/ssl/find/" + params.id).then(res => {
            const isIpSingle = res.data.startIp === res.data.endIp;
            const isPortSingle = res.data.startPort === res.data.endPort;
            const filterSensorData = _.map(res.data.mappingSensorDevice, "sensorId");
            const filterVhostData = _.map(res.data.mappingVhost, "vhost");
            const formatVhostData = filterVhostData.length === 0 ? [""] : filterVhostData;

            setState({
                ipType: isIpSingle ? "0" : "1",
                ipSingle: isIpSingle ? res.data.startIp : "",
                ipRangeStart: isIpSingle ? "" : res.data.startIp,
                ipRangeEnd: isIpSingle ? "" : res.data.endIp,
                portType: isPortSingle ? "0" : "1",
                portSingle: isPortSingle ? res.data.startPort : "",
                portRangeStart: isPortSingle ? "" : res.data.startPort,
                portRangeEnd: isPortSingle ? "" : res.data.endPort,
                password: res.data.password,
                sslKey: res.data.sslKeyFile ? res.data.sslKeyFile : "",
                selectedFile: null,
                isFileUpdate: false
            });

            setVirtualHost(formatVhostData);

            chkSensorDeviceGrid(filterSensorData);
        });
    }

    const chkSensorDeviceGrid = (selectIdArr) => {
        gridApiObj.policyListGridApi.api.forEachNode((node) => {
            if (selectIdArr.indexOf(node.data.sensorId) !== -1) {
                node.setSelected(true);
            }
        });
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleNumberPress = (e) => {
        const re = /^[0-9\b]+$/;
        const isNumberValue = re.test(e.target.value);

        if (isNumberValue || e.target.value === "") {
            setState({ ...state, [e.target.name]: e.target.value });
        }
    }

    const handleClickShowPassword = () => {
        setShowPassword(!showPassword);
    }

    const handleHostKeyPress = (e, i) => {
        let cloneField = _.cloneDeep(virtualHost);
        cloneField[i] = e.target.value;

        setVirtualHost(cloneField);
    }

    const addVirtualHostField = () => {
        setVirtualHost([...virtualHost, ""]);
    }

    const removeVirtualHostField = (index) => {
        let cloneField = _.cloneDeep(virtualHost);
        cloneField.splice(index, 1);

        setVirtualHost(cloneField);
    }

    const clearFileInput = () => {
        if (params.id === "0") {
            setState({ ...state, sslKey: "", selectedFile: null });
        } else {
            setState({ ...state, sslKey: "", selectedFile: null, isFileUpdate: true });
        }
    }

    const handleFileInput = (e) => {
        if (params.id === "0") {
            setState({ ...state, [e.target.name]: e.target.files[0].name, selectedFile: e.target.files[0] });
        } else {
            setState({ ...state, [e.target.name]: e.target.files[0].name, selectedFile: e.target.files[0], isFileUpdate: true });
        }
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
        const ipFormat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;

        if (state.ipType === "0") {
            if (!ipFormat.test(state.ipSingle)) {
                alert("올바른 IP 주소를 입력해주세요.");
                return;
            }
        } else {
            if (!ipFormat.test(state.ipRangeStart) || !ipFormat.test(state.ipRangeEnd)) {
                alert("올바른 IP 주소를 입력해주세요.");
                return;
            }
        }

        const formData = new FormData();
        const selectedNodes = gridApiObj.policyListGridApi.api.getSelectedRows();
        const selectedValueData = _.map(selectedNodes, "sensorId").join("|");
        const formatStartIp = state.ipType === "0" ? state.ipSingle : state.ipRangeStart;
        const formatEndIp = state.ipType === "0" ? state.ipSingle : state.ipRangeEnd;
        const formatStartPort = state.portType === "0" ? parseFloat(state.portSingle) : parseFloat(state.portRangeStart);
        const formatEndPort = state.portType === "0" ? parseFloat(state.portSingle) : parseFloat(state.portRangeEnd);
        const filterVhostList = _.filter(virtualHost, (obj) => {
            return obj !== "";
        });

        formData.append('policyId', parseFloat(params.id));
        formData.append('startIp', formatStartIp);
        formData.append('endIp', formatEndIp);
        formData.append('startPort', formatStartPort);
        formData.append('endPort', formatEndPort);
        formData.append('password', state.password);
        formData.append('file', state.selectedFile);
        formData.append('isFileUpdate', state.isFileUpdate);
        formData.append('deviceIdStr', selectedValueData);
        formData.append('vHostStr', filterVhostList);

        applyPolicySave(formData);
    }

    const applyPolicySave = (requestData) => {
        axiosConf.post("/api/setting/ssl/save", requestData).then(res => {
            if (res.data.key === "Success") {
                applyServer();
            } else {
                alert("중복된 IP/Port가 존재합니다.");
            }
        });
    }

    const applyServer = () => {
        axiosConf.post("/api/set/applySslKey/" + params.id).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="SSL 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{
                        '& .MuiTextField-root, .ag-theme-alpine': { mb: 1 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "15%", mt: .5 }}>IP</FormControl>
                    <FormControl sx={{ width: "15%" }}>
                        <TextField
                            select
                            name="ipType"
                            size="small"
                            value={state.ipType}
                            onChange={handleKeyPress}
                        >
                            <MenuItem value="0">IP</MenuItem>
                            <MenuItem value="1">IP Range</MenuItem>
                        </TextField>
                    </FormControl>
                    <FormControl sx={{ width: "1%" }}></FormControl>
                    {
                        state.ipType === "0" ? <FormControl sx={{ width: "69%" }}>
                            <TextField
                                name="ipSingle"
                                placeholder="IP"
                                size="small"
                                value={state.ipSingle}
                                onChange={handleKeyPress}
                            />
                        </FormControl> : <>
                            <FormControl sx={{ width: "33%" }}>
                                <TextField
                                    name="ipRangeStart"
                                    placeholder="Start IP"
                                    size="small"
                                    value={state.ipRangeStart}
                                    onChange={handleKeyPress}
                                />
                            </FormControl>
                            <FormControl sx={{ width: "3%", textAlign: "center" }}>~</FormControl>
                            <FormControl sx={{ width: "33%" }}>
                                <TextField
                                    name="ipRangeEnd"
                                    placeholder="End IP"
                                    size="small"
                                    value={state.ipRangeEnd}
                                    onChange={handleKeyPress}
                                />
                            </FormControl>
                        </>
                    }

                    <FormControl sx={{ width: "15%", mt: .5 }}>Port</FormControl>
                    <FormControl sx={{ width: "15%" }}>
                        <TextField
                            select
                            name="portType"
                            size="small"
                            value={state.portType}
                            onChange={handleKeyPress}
                        >
                            <MenuItem value="0">Port</MenuItem>
                            <MenuItem value="1">Port Range</MenuItem>
                        </TextField>
                    </FormControl>
                    <FormControl sx={{ width: "1%" }}></FormControl>
                    {
                        state.portType === "0" ? <FormControl sx={{ width: "69%" }}>
                            <TextField
                                name="portSingle"
                                placeholder="Port"
                                size="small"
                                value={state.portSingle}
                                onChange={handleNumberPress}
                            />
                        </FormControl> : <>
                            <FormControl sx={{ width: "33%" }}>
                                <TextField
                                    name="portRangeStart"
                                    placeholder="Start Port"
                                    size="small"
                                    value={state.portRangeStart}
                                    onChange={handleNumberPress}
                                />
                            </FormControl>
                            <FormControl sx={{ width: "3%", textAlign: "center" }}>~</FormControl>
                            <FormControl sx={{ width: "33%" }}>
                                <TextField
                                    name="portRangeEnd"
                                    placeholder="End Port"
                                    size="small"
                                    value={state.portRangeEnd}
                                    onChange={handleNumberPress}
                                />
                            </FormControl>
                        </>
                    }

                    <FormControl sx={{ width: "15%", mt: .5 }}>비밀번호</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <OutlinedInput
                            type={showPassword ? "text" : "password"}
                            name="password"
                            placeholder="비밀번호"
                            size="small"
                            value={state.password}
                            onChange={handleKeyPress}
                            sx={{ mb: 1 }}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
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

                    <FormControl sx={{ width: "15%", mt: .5 }}>SSL Key</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <OutlinedInput
                            type={state.sslKey === "" ? "file" : "text"}
                            name="sslKey"
                            size="small"
                            value={state.sslKey}
                            onChange={handleFileInput}
                            sx={{ mb: 1 }}
                            readOnly
                            endAdornment={
                                state.sslKey !== "" &&
                                <InputAdornment position="end">
                                    <IconButton
                                        onClick={clearFileInput}
                                        edge="end"
                                        size="large"
                                    >
                                        <MinusSquareOutlined />
                                    </IconButton>
                                </InputAdornment>
                            }
                        />
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: .5 }}>Virtual Host</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        {
                            _.map(virtualHost, (obj, i) => (
                                <FormControl fullWidth key={i}>
                                    <OutlinedInput
                                        name="virtualHost"
                                        placeholder="Virtual Host"
                                        size="small"
                                        value={obj}
                                        onChange={(e) => handleHostKeyPress(e, i)}
                                        sx={{ mb: 1 }}
                                        endAdornment={
                                            <InputAdornment position="end" sx={{ fontSize: "1rem" }}>
                                                {
                                                    i === 0 ? <IconButton
                                                        onClick={addVirtualHostField}
                                                        edge="end"
                                                        size="large"
                                                        color="primary"
                                                    >
                                                        <PlusSquareOutlined />
                                                    </IconButton> : <IconButton
                                                        onClick={() => removeVirtualHostField(i)}
                                                        edge="end"
                                                        size="large"
                                                    >
                                                        <MinusSquareOutlined />
                                                    </IconButton>
                                                }
                                            </InputAdornment>
                                        } />
                                </FormControl>
                            ))
                        }
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: .5 }}>Sensor Device</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <Box component="div" className="ag-theme-alpine" sx={{ height: 300 }} onContextMenu={(e) => e.preventDefault()}>
                            <AgGridModule
                                gridName="policyListGridApi"
                                columnDefs={gridColumnDefs}
                                rowData={gridData}
                                onGridReady={onGridReady}
                                handleResize={handleResize} />
                        </Box>
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

export default PopupSetSslSettingReg;