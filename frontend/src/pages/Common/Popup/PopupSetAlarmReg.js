import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from "lodash";
import { useParams } from 'react-router-dom';
import {
    Card, CardHeader, CardContent, Button, Box, TextField, FormControl, Grid, TableContainer, Paper, Table, TableHead, TableRow, TableCell, TableBody,
    FormLabel, IconButton, Chip, Tabs, Tab, Badge
} from '@mui/material';
import { styled } from "@mui/material/styles";

import { SearchOutlined } from "@ant-design/icons";

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';
import ModalSearchFormResources from "../Modal/ModalSearchFormResources";
import { alarmPageResources, alarmUriResources, alarmTcpResources, alarmUdpResources, alarmIpResources, alarmTrafficResources } from '../../../lib/common';
import Loader from '../../../components/Loader';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const PopupSetApplicationReg = (props) => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);

    const [subMenuKey, setSubMenuKey] = useState(0);
    const [state, setState] = useState({
        alarmId: 0,
        alarmTitle: ""
    });
    const [open, setOpen] = useState(false);
    const [selectResource, setSelectResource] = useState([]);
    const [criticalResource, setCriticalResource] = useState([]);

    const [resourceItem, setResourceItem] = useState([]);

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

    useEffect(() => {
        if (subMenuKey === 0) setResourceItem(alarmPageResources);
        else if (subMenuKey === 1) setResourceItem(alarmUriResources);
        else if (subMenuKey === 2) setResourceItem(alarmTcpResources);
        else if (subMenuKey === 3) setResourceItem(alarmUdpResources);
        else if (subMenuKey === 4) setResourceItem(alarmIpResources);
        else if (subMenuKey === 5) setResourceItem(alarmTrafficResources);
    }, [subMenuKey]);


    const getFindoneInfo = () => {
        axiosConf.get("/api/setting/alarm/find/" + params.id).then(res => {
            setState({
                alarmTitle: res.data.alarmTitle,
                alarmId: res.data.alarmId
            });
        });
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const onSavePolicySave = () => {
        if (state.alarmTitle === "") {
            alert("알림 이름을 입력해주세요.");
            return;
        }

        let requestData = {
            title: state.alarmTitle,
            id: state.alarmId,
        }

        let reqUrl = "insert";

        if (params.id !== "0") {
            reqUrl = "update";
            requestData.id = parseFloat(params.id);
        }

        axiosConf.post("/api/setting/alarm/" + reqUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            applyServer();
            window.opener.onSuccess();
            window.close();
        });

    }

    const applyServer = () => {
        axiosConf.post("/api/set/applyPCapBpf/" + params.id).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    const handleResourceDelete = (targetId) => {
        const filterSelectData = _.filter(selectResource, (obj) => {
            return obj.key !== targetId;
        });

        const filterCriticalData = _.filter(criticalResource, (obj) => {
            return obj.key !== targetId;
        });

        setSelectResource(filterSelectData);
        setCriticalResource(filterCriticalData);
    }

    const applyPolicySave = (requestData) => {
        axiosConf.post("/api/setting/threshold/insert/" + props.target, requestData).then(res => {
            alert("저장 되었습니다.");
        });
    }

    const handleOpen = () => setOpen(true);

    const handleClose = useCallback(() => {
        setOpen(false);
    }, []);

    const handleSubChange = (event, newValue) => {
        setSubMenuKey(newValue);
    }

    const handleKeyPressCritical = (e, targetIdx) => {

    }

    const bridgeModalResource = useCallback((data) => {
        setShowLoader(true);

        setTimeout(() => {
            let levelDataArr = [];

            _.forEach(data, (obj) => {
                const filterExistData = _.filter(criticalResource, (existObj) => {
                    return existObj.key === obj.key;
                });

                if (filterExistData.length > 0) {
                    levelDataArr.push(filterExistData[0]);
                } else {
                    levelDataArr.push({
                        name: obj.name,
                        key: obj.key,
                        defaultCheck: false,
                        isTcpError: obj.isTcpError,
                        level1Field: "",
                        level2Field: "",
                        level3Field: "",
                        level4Field: "",
                        level5Field: ""
                    });
                }
            });

            setSelectResource(data);
            setCriticalResource(levelDataArr);
            setShowLoader(false);
        }, 100);
    }, []);

    // import content
    const ModalResourceContent = useMemo(() => (
        <ModalSearchFormResources open={open} resourceItem={resourceItem} selectResource={selectResource} onClose={handleClose} onSuccess={bridgeModalResource} />
    ), [open]);

    return (
        <Card>
            <CardHeader title="알림 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{ '& .MuiTextField-root': { mb: 1 } }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "25%", mt: .5 }}>알림 명</FormControl>
                    <FormControl sx={{ width: "75%" }}>
                        <TextField
                            name="alarmTitle"
                            placeholder="Alias"
                            size="small"
                            value={state.alarmTitle}
                            onChange={handleKeyPress}
                        />
                    </FormControl>
                </Box>
                <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper' }}>
                    <Tabs value={subMenuKey} onChange={handleSubChange} className="small" sx={{ bgcolor: "#f6f6f6" }}>
                        <Tab label="HTTP-Transaction" />
                        <Tab label="HTTP-URI" />
                        <Tab label="L4-TCP" />
                        <Tab label="L4-UDP" />
                        <Tab label="L3-IP" />
                        <Tab label="Traffic" />
                    </Tabs>
                </Box>
                <Box sx={{ pb: 1, pt: 2 }}>
                    <Grid container>
                        <Grid item sm={.5}>
                            <FormLabel>자원</FormLabel>
                        </Grid>
                        <Grid item sm={11.5}>
                            <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }} className="popupEl">
                                <IconButton edge="start" size="small" className="search-btn" onClick={handleOpen}>
                                    <SearchOutlined />
                                </IconButton>
                                <Box component="div" flex="1 1">
                                    <Paper
                                        sx={{
                                            display: 'flex',
                                            flexWrap: 'wrap',
                                            listStyle: 'none',
                                            pl: .5,
                                            m: 0,
                                            '& .MuiListItem-root': { p: 0 }
                                        }}
                                        className="border"
                                        component="ul"
                                    >
                                        {
                                            selectResource.length === 0 && <ListItem sx={{ mt: .3 }} className="font-gray">Choose Resources</ListItem>
                                        }

                                        {
                                            _.map(selectResource, (obj, i) => (
                                                <ListItem key={i}>
                                                    <Chip size="small" label={obj.name} onDelete={() => handleResourceDelete(obj.key)} />
                                                </ListItem>
                                            ))
                                        }
                                    </Paper>
                                </Box>
                            </Box>
                        </Grid>
                    </Grid>
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1, pt: 2 }}>
                    {
                        selectResource.length !== 0 &&
                        <Grid item sm={12} mt={1}>
                            <TableContainer component={Paper} className="border" sx={{ maxHeight: props.height || 600 }}>
                                <Table stickyHeader className="table-bordered" size="small">
                                    <TableHead className="thead-light">
                                        <TableRow sx={{ '& .MuiBadge-badge': { width: "12rem", height: "1.2rem" } }}>
                                            <TableCell align="center" sx={{ width: "10rem" }}>
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "7rem" }}>
                                                <Badge badgeContent={"주의"} classes={{ badge: "bg-yellow font-white" }}></Badge>
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "7rem" }}>
                                                <Badge badgeContent={"경고"} classes={{ badge: "bg-orange font-white" }}></Badge>
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "7rem" }}>
                                                <Badge badgeContent={"위험"} classes={{ badge: "bg-redorange font-white" }}></Badge>
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "7rem" }}>
                                                <Badge badgeContent={"장애"} classes={{ badge: "bg-danger font-white" }}></Badge>
                                            </TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {
                                            _.map(selectResource, (obj, i) => (
                                                <TableRow sx={{ '&:last-child td, &:last-child th': { border: 0 } }} key={i} >
                                                    <TableCell align="center">{obj.name} </TableCell>
                                                    <TableCell align="center">
                                                        <TextField
                                                            name="alarmCaution"
                                                            size="small"
                                                            sx={{ input: { textAlign: "center" } }}
                                                            // InputProps={{ }}
                                                            value={1}
                                                            onChange={(e) => handleKeyPressCritical(e, i)}
                                                        />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <TextField
                                                            name="alarmWarning"
                                                            size="small"
                                                            sx={{ input: { textAlign: "center" } }}
                                                            // InputProps={{ }}
                                                            value={2}
                                                            onChange={(e) => handleKeyPressCritical(e, i)}
                                                        />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <TextField
                                                            name="alarmDanger"
                                                            size="small"
                                                            sx={{ input: { textAlign: "center" } }}
                                                            // InputProps={{ }}
                                                            value={3}
                                                            onChange={(e) => handleKeyPressCritical(e, i)}
                                                        />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <TextField
                                                            name="alarmObstacle"
                                                            size="small"
                                                            sx={{ input: { textAlign: "center" } }}
                                                            // InputProps={{ }}
                                                            value={4}
                                                            onChange={(e) => handleKeyPressCritical(e, i)}
                                                        />
                                                    </TableCell>
                                                </TableRow>
                                            ))
                                        }
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </Grid>
                    }
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1, pt: 2 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicySave}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
            {ModalResourceContent}
        </Card>
    );
};

export default PopupSetApplicationReg;