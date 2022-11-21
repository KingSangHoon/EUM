import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from "lodash";
import { useParams } from 'react-router-dom';
import {
    Card, CardHeader, CardContent, Button, Box, TextField, FormControl, MenuItem, Grid, TableContainer, Paper,
    Table, TableHead, TableRow, TableCell, Checkbox, TableBody
} from '@mui/material';

import { SearchOutlined } from "@ant-design/icons";

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';
import ModalSearchFormApplicationSetting from "../Modal/ModalSearchFormApplicationSetting";

const ipFormat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
//const macFormat = /^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$/;
const numberFormat = /^[0-9]+$/;

const applicationIpListVHost = {
    vHostDeatil: ""
};

const applicationIpListRow = {
    twoDeptIpAddr: "",
    twoDeptMac: "",
    twoDeptIsTcpUdp: "tcp",
    twoDeptPortType: "range",
    twoDeptPortStart: "",
    twoDeptPortEnd: "",
    isTwoDeptVHostType: false,
    isDeleteFlag: false,
    applicationIpListVHostList: [applicationIpListVHost],
};

const PopupSetApplicationReg = (props) => {
    const params = useParams();

    const [state, setState] = useState({
        applicationId: 0,
        applicationName: ""
    });

    const [selectModalIndex, setSelectModalIndex] = useState({
        firstIndex: 0,
        secondIndex: 0,
    });

    const [applicationIpList, setApplicationIpList] = useState([{
        oneDeptIpType: "RIP",
        oneDeptIpAddr: "",
        oneDeptPortStart: "",
        oneDeptPortEnd: "",
        isOneDeptPortType: "range",
        applicationIpListRow: [applicationIpListRow],
    }]);

    const [masterModalOpen, setMasterModalOpen] = useState(false);
    const [protocolList, setProtocolList] = useState([]);
    const [selectMasterResource, setSelectMasterResource] = useState([]);

    useEffect(() => {
    }, [selectModalIndex]);

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
        axiosConf.get("/api/setting/application/find/" + params.id).then(res => {

            axiosConf.get("/api/search/findAllRedInfo").then(res => {
                console.log(res.data)
            })


            setState({
                applicationName: res.data.applicationInfo.title,
                id: res.data.applicationInfo.id
            });
            const ripArr = _.cloneDeep(res.data.ripInfo);
            let settingRipVipArr = [];
            _.map(ripArr, (obj) => {
                let ripItem = {};
                let vHostlist = [];

                let ipListRow = [];

                _.map(obj.vipInfo, (vipItem) => {
                    let vHostList = [];

                    _.map(vipItem.vHost, (vHostItem) => {
                        let vHostObj = {
                            id: vHostItem.mappingId,
                            vHostDeatil: vHostItem.vhost
                        };
                        vHostList.push(vHostObj);
                    });

                    if (vHostList.length == 0) {
                        vHostList.push({
                            id: 0,
                            vHostDeatil: ""
                        });
                    }

                    let vipObj = {
                        applicationIpListVipId: vipItem.vipInfo.id,
                        twoDeptIpAddr: vipItem.vipInfo.ipAddr,
                        twoDeptMac: vipItem.vipInfo.ipMac,
                        twoDeptIsTcpUdp: vipItem.vipInfo.protocol,
                        twoDeptPortType: vipItem.vipInfo.portTypeIsRange,
                        twoDeptPortStart: vipItem.vipInfo.startPort.toString(),
                        twoDeptPortEnd: vipItem.vipInfo.endPort.toString(),
                        isTwoDeptVHostType: vipItem.vipInfo.isVHost,
                        applicationIpListVHostList: vHostList,
                    };
                    ipListRow.push(vipObj);
                })
                ripItem = {
                    applicationIpListRipId: obj.ripInfo.id,
                    oneDeptIpType: obj.type,
                    oneDeptIpAddr: obj.ripInfo.ipAddr,
                    oneDeptPortStart: obj.ripInfo.startPort.toString(),
                    oneDeptPortEnd: obj.ripInfo.endPort.toString(),
                    isOneDeptPortType: obj.ripInfo.portTypeIsRange,
                    applicationIpListRow: ipListRow,
                }
                // };

                settingRipVipArr.push(ripItem);
            });
            setApplicationIpList(settingRipVipArr);
        });
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const onSavePolicySave = () => {
        if (state.applicationName === "") {
            alert("어플리케이션 이름을 입력해주세요.");
            return;
        }

        const validationCheck = applicationIpListValidationCheck();

        if (validationCheck) {
            let requestData = {
                title: state.applicationName,
                id: state.applicationId,
                applicationIpList: applicationIpList
            }

            let reqUrl = "insert";

            if (params.id !== "0") {
                reqUrl = "update";
                requestData.id = parseFloat(params.id);
            }

            axiosConf.post("/api/setting/application/" + reqUrl, requestData).then(res => {
                alert("저장 되었습니다.");
                applyServer();
                window.opener.onSuccess();
                window.close();
            });
        } else {
            alert("입력값을 확인해주세요.")
            return false;
        }
    }

    const applicationIpListValidationCheck = () => {
        let result = true;
        _.map(applicationIpList, (oneItem) => {
            _.map(oneItem.applicationIpListRow, (ripItem) => {
                if (!ipFormat.test(ripItem.twoDeptIpAddr)) { result = false; }
                if (ripItem.twoDeptPortType === "range") {
                    if (!numberFormat.test(ripItem.twoDeptPortStart)) { result = false; }
                    if (!numberFormat.test(ripItem.twoDeptPortEnd)) { result = false; }
                } else {
                    if (!numberFormat.test(ripItem.twoDeptPortStart)) { result = false; }
                }
                if (ripItem.isTwoDeptVHostType) {
                    _.map(ripItem.applicationIpListVHostList, (vHostItem) => {
                        if (vHostItem.vHostDeatil === "") { result = false; }
                    })
                }
            })

            /* if (oneItem.oneDeptIpType === "VIP") {
                if (!ipFormat.test(oneItem.oneDeptIpAddr)) {
                    result = false;
                }
                if (oneItem.isOneDeptPortType === "range") {
                    if (!numberFormat.test(oneItem.oneDeptPortStart)) { result = false; }
                    if (!numberFormat.test(oneItem.oneDeptPortEnd)) { result = false; }
                } else {
                    if (!numberFormat.test(oneItem.oneDeptPortStart)) { result = false; }
                }
            } */
        });

        return result;
    }

    const applyServer = () => {
        axiosConf.post("/api/set/applyPCapBpf/" + params.id).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    const addApplicationIpListField = () => {
        let cloneField = _.cloneDeep(applicationIpList);
        const addItem = {
            oneDeptIpType: "RIP",
            oneDeptIpAddr: "",
            oneDeptPortStart: "",
            oneDeptPortEnd: "",
            isOneDeptPortType: "range",
            applicationIpListRow: [applicationIpListRow],
        };
        cloneField.push(addItem);
        setApplicationIpList(cloneField);
    }

    const addApplicationIpListRow = (i) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i].applicationIpListRow.push(applicationIpListRow);
        setApplicationIpList(cloneField);
    }

    const addApplicationIpListVHost = (i, j) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i].applicationIpListRow[j].applicationIpListVHostList.push(applicationIpListVHost);
        setApplicationIpList(cloneField);
    }

    const removeApplicationIpListVHost = (i, j, k) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i].applicationIpListRow[j].applicationIpListVHostList.splice(k, 1);
        setApplicationIpList(cloneField);
    }

    const handleIpListKeyPress = (e, i) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i][e.target.name] = e.target.value;
        setApplicationIpList(cloneField);
    }

    const handleIpListRowKeyPress = (e, i, j) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i].applicationIpListRow[j][e.target.name] = e.target.value;
        setApplicationIpList(cloneField);
    }

    const handleIpListVHostChecked = (e, i, j) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i].applicationIpListRow[j].isTwoDeptVHostType = e.target.checked;
        setApplicationIpList(cloneField);
    }

    const handleIpListPortChecked = (e, i) => {
        let cloneField = _.cloneDeep(applicationIpList);
        let isCheckedFlag = '';
        if (e.target.checked) isCheckedFlag = 'range'
        else isCheckedFlag = 'single'
        cloneField[i].isOneDeptPortType = isCheckedFlag;
        setApplicationIpList(cloneField);
    }

    const removeApplicationIpList = (i, j) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i].applicationIpListRow.splice(j, 1);
        setApplicationIpList(cloneField);
    }

    const handleIpListVHostKeyPress = (e, i, j, k) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField[i].applicationIpListRow[j].applicationIpListVHostList[k].vHostDeatil = e.target.value;
        setApplicationIpList(cloneField);
    }

    const resetApplicationIpList = () => {
        const resetItem = [{
            oneDeptIpType: "RIP",
            oneDeptIpAddr: "",
            oneDeptPortStart: "",
            oneDeptPortEnd: "",
            isOneDeptPortType: "range",
            applicationIpListRow: [applicationIpListRow],
        }]
        setApplicationIpList(resetItem);
    }

    const resetApplicationIpListRow = (i) => {
        let cloneField = _.cloneDeep(applicationIpList);
        const resetItem = {
            oneDeptIpType: "RIP",
            oneDeptIpAddr: "",
            oneDeptPortStart: "",
            oneDeptPortEnd: "",
            isOneDeptPortType: "range",
            applicationIpListRow: [applicationIpListRow],
        };
        cloneField[i] = resetItem;
        setApplicationIpList(cloneField);
    }

    const removeApplicationIpListRow = (i) => {
        let cloneField = _.cloneDeep(applicationIpList);
        cloneField.splice(i, 1);
        setApplicationIpList(cloneField);
    }

    const handleMasterModalOpen = (e, i, j) => {
        setSelectModalIndex({ firstIndex: i, secondIndex: j });
        setMasterModalOpen(true);
    }
    const handleMasterModalClose = useCallback(() => {
        setMasterModalOpen(false);
    }, []);

    const bridgeMasterModalResource = useCallback((data) => {
        let cloneField = _.cloneDeep(applicationIpList);

        cloneField[selectModalIndex.firstIndex].applicationIpListRow[selectModalIndex.secondIndex].twoDeptIpAddr = data[0].dstIp;
        cloneField[selectModalIndex.firstIndex].applicationIpListRow[selectModalIndex.secondIndex].twoDeptMac = data[0].dstMac;
        cloneField[selectModalIndex.firstIndex].applicationIpListRow[selectModalIndex.secondIndex].twoDeptPortStart = data[0].dstPort;
        cloneField[selectModalIndex.firstIndex].applicationIpListRow[selectModalIndex.secondIndex].twoDeptPortEnd = data[0].dstPort;

        setApplicationIpList(cloneField);
    }, []);

    // import content
    const ModalMasterContent = useMemo(() => (
        <ModalSearchFormApplicationSetting open={masterModalOpen} resourceItem={protocolList} selectResource={selectMasterResource} onClose={handleMasterModalClose} onSuccess={bridgeMasterModalResource} />
    ), [masterModalOpen]);

    return (
        <Card>
            <CardHeader title="어플리케이션 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{ '& .MuiTextField-root': { mb: 1 } }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "25%", mt: .5 }}>어플리케이션 명</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="applicationName"
                            placeholder="Alias"
                            size="small"
                            value={state.applicationName}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "25%", mt: .5 }}>IP List</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <Grid item sm={6}>
                            <Button variant="outlined" color="inherit" size="small" onClick={resetApplicationIpList}>초기화</Button>
                            <Button variant="outlined" color="primary" size="small" sx={{ mr: .5, ml: .5 }} onClick={addApplicationIpListField}>추가</Button>
                        </Grid>
                        <Grid item sm={6} textAlign="right" pt={.7}>
                        </Grid>
                    </FormControl>

                    <Grid container mt={1}>
                        {
                            _.map(applicationIpList, (obj, i) => (
                                <Grid item sm={12} mt={.5} sx={{ border: "1px solid #bbbbbb", padding: 1 }} key={i}>
                                    <FormControl sx={{ width: "10%" }}>
                                        <TextField
                                            select
                                            name="oneDeptIpType"
                                            size="small"
                                            value={obj.oneDeptIpType}
                                            onChange={(e) => handleIpListKeyPress(e, i)}
                                        >
                                            <MenuItem value="RIP">RIP</MenuItem>
                                            <MenuItem value="VIP">VIP</MenuItem>
                                        </TextField>
                                    </FormControl>

                                    <FormControl sx={{ width: "5%", ml: 0.5 }}>
                                        <Button variant="outlined" color="primary" size="small" onClick={() => addApplicationIpListRow(i)}>추가</Button>
                                    </FormControl>

                                    <FormControl sx={{ ml: 0.5, width: "5%" }}>
                                        <Button variant="outlined" color="inherit" size="small" onClick={() => resetApplicationIpListRow(i)}>초기화</Button>
                                    </FormControl>
                                    <FormControl sx={{ ml: 0.5, width: "5%" }}>
                                        <Button variant="outlined" color="error" size="small" onClick={() => removeApplicationIpListRow(i)}>삭제</Button>
                                    </FormControl>

                                    <TableContainer component={Paper} className="border">
                                        <Table size="small" className="table-bordered">
                                            <TableHead className="thead-light">
                                                <TableRow>
                                                    <TableCell align="center" sx={{ width: "3rem" }}></TableCell>
                                                    <TableCell align="center" sx={{ width: "15rem" }}>IP Addr</TableCell>
                                                    <TableCell align="center" sx={{ width: "15rem" }}>MAC Addr</TableCell>
                                                    <TableCell align="center" sx={{ width: "10rem" }}>TCP/UDP</TableCell>
                                                    <TableCell align="center" sx={{ width: "10rem" }}>Port Type</TableCell>
                                                    <TableCell align="center" sx={{ width: "20rem" }}>Port Addr</TableCell>
                                                    <TableCell align="center" sx={{ width: "3rem" }}>vHost</TableCell>
                                                </TableRow>
                                            </TableHead>
                                            {
                                                _.map(obj.applicationIpListRow, (ipListRow, j) => (
                                                    <TableBody key={j}>
                                                        <TableRow>
                                                            {/*checkBox*/}
                                                            <TableCell align="center">
                                                                {j == 0 ?
                                                                    <span className="fa fa-plus-circle text-primary cursorp" onClick={() => addApplicationIpListRow(i)}></span>
                                                                    :
                                                                    <span className="fa fa-minus-circle text-primary cursorp" onClick={() => removeApplicationIpList(i, j)}></span>
                                                                }

                                                            </TableCell>
                                                            {/*Ip Addr*/}
                                                            <TableCell align="center">
                                                                <div>
                                                                    <FormControl sx={{ width: "80%" }}>
                                                                        <TextField
                                                                            fullWidth
                                                                            name="twoDeptIpAddr"
                                                                            placeholder="IP"
                                                                            size="small"
                                                                            value={ipListRow.twoDeptIpAddr}
                                                                            onChange={(e) => handleIpListRowKeyPress(e, i, j)}
                                                                        />
                                                                    </FormControl>
                                                                    <FormControl sx={{ width: "20%" }}>
                                                                        <Button variant="contained" color="primary" size="small" startIcon={<SearchOutlined />} sx={{ width: "3rem", '& .MuiButton-startIcon': { m: 0 } }} onClick={(e) => handleMasterModalOpen(e, i, j)}></Button>
                                                                    </FormControl>
                                                                </div>
                                                            </TableCell>

                                                            {/*IP MAC*/}
                                                            <TableCell align="center">
                                                                <TextField
                                                                    fullWidth
                                                                    name="twoDeptMac"
                                                                    placeholder="MAC"
                                                                    size="small"
                                                                    value={ipListRow.twoDeptMac}
                                                                    onChange={(e) => handleIpListRowKeyPress(e, i, j)}
                                                                />
                                                            </TableCell>

                                                            {/*TCP/UDP*/}
                                                            <TableCell align="center">
                                                                <TextField
                                                                    fullWidth
                                                                    select
                                                                    name="twoDeptIsTcpUdp"
                                                                    size="small"
                                                                    value={ipListRow.twoDeptIsTcpUdp}
                                                                    onChange={(e) => handleIpListRowKeyPress(e, i, j)}
                                                                >
                                                                    <MenuItem value="tcp">TCP</MenuItem>
                                                                    <MenuItem value="udp">UDP</MenuItem>
                                                                </TextField>
                                                            </TableCell>
                                                            {/*Port Type*/}
                                                            <TableCell align="center">
                                                                <TextField
                                                                    fullWidth
                                                                    select
                                                                    name="twoDeptPortType"
                                                                    size="small"
                                                                    value={ipListRow.twoDeptPortType}
                                                                    onChange={(e) => handleIpListRowKeyPress(e, i, j)}
                                                                >
                                                                    <MenuItem value="range">Range</MenuItem>
                                                                    <MenuItem value="single">단일</MenuItem>
                                                                </TextField>
                                                            </TableCell>
                                                            {/*Port Addr*/}
                                                            <TableCell align="center">

                                                                {
                                                                    ipListRow.twoDeptPortType == 'range' ?
                                                                        <div>
                                                                            <FormControl sx={{ width: "45%" }}>
                                                                                <TextField
                                                                                    fullWidth
                                                                                    name="twoDeptPortStart"
                                                                                    placeholder="Port"
                                                                                    size="small"
                                                                                    value={ipListRow.twoDeptPortStart}
                                                                                    onChange={(e) => handleIpListRowKeyPress(e, i, j)}
                                                                                />
                                                                            </FormControl>
                                                                            <FormControl sx={{ width: "10%" }}> ~
                                                                            </FormControl>
                                                                            <FormControl sx={{ width: "45%" }}>
                                                                                <TextField
                                                                                    fullWidth
                                                                                    name="twoDeptPortEnd"
                                                                                    placeholder="Port"
                                                                                    size="small"
                                                                                    value={ipListRow.twoDeptPortEnd}
                                                                                    onChange={(e) => handleIpListRowKeyPress(e, i, j)}
                                                                                />
                                                                            </FormControl>
                                                                        </div>
                                                                        :
                                                                        <TextField
                                                                            fullWidth
                                                                            name="twoDeptPortStart"
                                                                            placeholder="Port"
                                                                            size="small"
                                                                            value={ipListRow.twoDeptPortStart}
                                                                            onChange={(e) => handleIpListRowKeyPress(e, i, j)}
                                                                        />
                                                                }
                                                            </TableCell>

                                                            {/*vHost*/}
                                                            <TableCell align="center">
                                                                <Checkbox id="isTwoDeptVHostType" checked={ipListRow.isTwoDeptVHostType} onChange={(e) => handleIpListVHostChecked(e, i, j)} />
                                                            </TableCell>

                                                        </TableRow>
                                                        {
                                                            ipListRow.isTwoDeptVHostType && <>
                                                                {
                                                                    _.map(ipListRow.applicationIpListVHostList, (vHostList, k) => (
                                                                        <TableRow className={"bg-gray"} key={k}>
                                                                            <TableCell align="center" colSpan={2}>
                                                                                vHost
                                                                                {
                                                                                    k == 0 ?
                                                                                        <span className="fa fa-plus-circle text-primary cursorp" onClick={() => addApplicationIpListVHost(i, j)}></span>
                                                                                        :
                                                                                        <span className="fa fa-minus-circle text-primary cursorp" onClick={() => removeApplicationIpListVHost(i, j, k)}></span>
                                                                                }
                                                                            </TableCell>
                                                                            <TableCell align="center" colSpan={5}>
                                                                                <TextField
                                                                                    fullWidth
                                                                                    name="vHostDetail"
                                                                                    placeholder="vHost"
                                                                                    size="small"
                                                                                    value={vHostList.vHostDeatil}
                                                                                    onChange={(e) => handleIpListVHostKeyPress(e, i, j, k)}
                                                                                />
                                                                            </TableCell>
                                                                        </TableRow>
                                                                    ))
                                                                }
                                                            </>
                                                        }
                                                    </TableBody>
                                                ))
                                            }
                                        </Table>
                                    </TableContainer>
                                </Grid>
                            ))
                        }
                    </Grid>
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1, pt: 2 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicySave}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>
            {ModalMasterContent}
        </Card>
    );
};

export default PopupSetApplicationReg;