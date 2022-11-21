import React, { useEffect, useState, useRef } from 'react';
import _ from 'lodash';
import WindowOpener from 'react-window-opener';
import {
    Grid, Box, Typography, Button, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper, Checkbox,
    List, ListItemText, TextField, FormControlLabel, ListItem
} from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft, faArrowRight, faArrowsLeftRight, faWindowMaximize, faRotateRight } from "@fortawesome/free-solid-svg-icons";
import { DownloadOutlined, PlusSquareOutlined, MinusSquareOutlined, EditOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import { numberWithCommas, gridApiObj } from '../../lib/common';
import Loader from '../../components/Loader';

const listItemStyle = {
    overflowY: "auto",
    maxHeight: "7rem",
    "& li": { padding: 0 },
    "& div": { marginTop: "2px", marginBottom: "2px" }
};

const BandIndex = () => {
    const inputRef = useRef();
    const localGetColumn = localStorage.getItem("bandColumnWidth");

    const [showLoader, setShowLoader] = useState(false);

    const [drag, setDrag] = useState(false);
    const [columnSize, setColumnSize] = useState(localGetColumn ? JSON.parse(localGetColumn) : {
        "ruleType": "10rem",
        "ruleName": "10rem",
        "sourceIp": "14rem",
        "ipFlow": "3rem",
        "targetIp": "14rem",
        "sourceMac": "9rem",
        "macFlow": "3rem",
        "targetMac": "9rem",
        "protocolType": "4rem",
        "sourcePort": "6rem",
        "portFlow": "3rem"
    });

    const [editPolicyField, setEditPolicyField] = useState("");
    const [allPolicyCheck, setAllPolicyCheck] = useState(false);

    const [policyData, setPolicyData] = useState([]);

    useEffect(() => {
        getPolicyList();
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyNoticeGridApi) {
                gridApiObj.policyNoticeGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [policyData]);

    useEffect(() => {
        localStorage.setItem("bandColumnWidth", JSON.stringify(columnSize));
    }, [columnSize]);

    const autoComponentSize = () => {
        if (document.getElementById('policyNoticeGrid')) {
            const mainHeight = document.body.clientHeight;
            document.getElementById('policyNoticeGrid').style.height = mainHeight - 35 + 'px';
        }
    }

    const getPolicyList = () => {
        setShowLoader(true);

        axiosConf.get('/api/setting/band/findAll').then(res => {
            let policyData = [];

            let testData = {
                "title" : "ㅠㅠ",
                "resourceItem": [
                    {
                        "resource_warning": 299,
                        "resource_info": 199,
                        "resource_fatal": 499,
                        "resource_code": "tsPageTransferRes",
                        "resource_danger": 399
                    },
                    {
                        "resource_warning": 699,
                        "resource_info": 599,
                        "resource_fatal": 899,
                        "resource_code": "tsPageRttConnReqMin",
                        "resource_danger": 997
                    }
                ]
            };

                _.forEach(res.data, (obj) => {
                let ruleData = [];
                obj.bandRuleItem = _.map(obj.bandRuleItem, "bandRule");

                _.forEach(obj.bandRuleItem, (subObj) => {
                    let sourceIpData = [];

                    // Source IP > sourceIpItem
                    _.forEach(subObj.sourceIpItem, (ipObj) => {
                        sourceIpData.push({
                            isRange: ipObj.type === 0 ? false : true,
                            startField: ipObj.sourceipStart,
                            endField: ipObj.sourceipEnd
                        });
                    });

                    // Source IP > Geo > ISP
                    _.forEach(subObj.ispItem, (ispObj) => {
                        sourceIpData.push({
                            isRange: false,
                            startField: "IDC>" + ispObj.name,
                            endField: "IDC>" + ispObj.name
                        });
                    });

                    // Source IP > Geo > IDC
                    _.forEach(subObj.idcItem, (idcObj) => {
                        sourceIpData.push({
                            isRange: false,
                            startField: "ISP>" + idcObj.name,
                            endField: "ISP>" + idcObj.name
                        });
                    });

                    if (subObj.isDomestic) {
                        // Source IP > Geo > Domestic
                        if (subObj.domesticItem.primary && subObj.domesticItem.sub1 && subObj.domesticItem.sub2) {
                            const primaryName = _.map(subObj.domesticItem.primary, "name");
                            const sub1Name = _.map(subObj.domesticItem.sub1, "name");

                            _.forEach(subObj.domesticItem.sub2, (sub2Obj) => {
                                sourceIpData.push({
                                    isRange: false,
                                    startField: primaryName[0] + ">" + sub1Name[0] + ">" + sub2Obj.name,
                                    endField: primaryName[0] + ">" + sub1Name[0] + ">" + sub2Obj.name
                                });
                            });
                        } else if (subObj.domesticItem.primary && subObj.domesticItem.sub1) {
                            const primaryName = _.map(subObj.domesticItem.primary, "name");

                            _.forEach(subObj.domesticItem.sub1, (sub1Obj) => {
                                sourceIpData.push({
                                    isRange: false,
                                    startField: primaryName[0] + ">" + sub1Obj.name,
                                    endField: primaryName[0] + ">" + sub1Obj.name
                                });
                            });
                        } else if (subObj.domesticItem.primary) {
                            _.forEach(subObj.domesticItem.primary, (primaryObj) => {
                                sourceIpData.push({
                                    isRange: false,
                                    startField: primaryObj.name,
                                    endField: primaryObj.name
                                });
                            });
                        }
                    } else if (subObj.isCountry) {
                        // Source IP > Geo > Country
                        _.forEach(subObj.countryItem, (countryObj) => {
                            sourceIpData.push({
                                isRange: false,
                                startField: countryObj.continentName + ">" + countryObj.countryName,
                                endField: countryObj.continentName + ">" + countryObj.countryName
                            });
                        });
                    } else if (subObj.isContinent) {
                        // Source IP > Geo > Continent
                        _.forEach(subObj.continentItem, (countryObj) => {
                            sourceIpData.push({
                                isRange: false,
                                startField: countryObj.continentName,
                                endField: countryObj.continentName
                            });
                        });
                    }

                    // Source Port
                    subObj.sourcePortItem = _.map(subObj.sourcePortItem, (portObj) => {
                        return {
                            isRange: portObj.type === 0 ? false : true,
                            startField: portObj.sourceportStart,
                            endField: portObj.sourceportEnd
                        };
                    });

                    ruleData.push({
                        isRuleCheck: false,
                        isActive: true,
                        policyId: obj.bandItem.bandId,
                        ruleId: subObj.ruleId,
                        ruleType: subObj.isType,
                        ruleName: subObj.title,
                        applicationName: subObj.application ? subObj.application.application.title : "",
                        ipFlow: subObj.ipFlow,
                        sourceIp: sourceIpData,
                        targetIpIsRange: subObj.destinationipType === 1 ? true : false,
                        targetIpStart: subObj.destinationipStart || "",
                        targetIpEnd: subObj.destinationipEnd || "",
                        macFlow: subObj.macFlow,
                        sourceMac: _.map(subObj.sourceMacItem, "sourceMac"),
                        targetMac: subObj.destinationmac || "",
                        portFlow: subObj.portFlow,
                        sourcePort: subObj.sourcePortItem,
                        targetPortIsRange: subObj.destinationportType === 1 ? true : false,
                        targetPortStart: subObj.destinationportStart || "",
                        targetPortEnd: subObj.destinationportEnd || "",
                        protocolType: subObj.portocolType === 0 ? "TCP" : "UDP"
                    });
                });

                policyData.push({
                    isPolicyCheck: false,
                    allRuleCheck: false,
                    isEditMode: false,
                    policyName: obj.bandItem.title,
                    policyId: obj.bandItem.bandId,
                    ruleData: ruleData
                });
            });

            setPolicyData(policyData);
            setShowLoader(false);
        });
    }

    const handleKeyPress = (e) => {
        setEditPolicyField(e.target.value);
    }

    const onBlur = (idx) => {
        if (editPolicyField === "") {
            alert("정책 명을 입력해주세요.");

            setTimeout(() => {
                inputRef.current.focus();
            }, 100);
        } else {
            const reqUrl = policyData[idx].policyId ? "updateBand/" + policyData[idx].policyId : "insertBand";

            axiosConf.post("/api/setting/band/" + reqUrl, { title: editPolicyField }).then(res => {
                getPolicyList();
                setEditPolicyField("");
            });
        }
    }

    const evtEditPolicyName = (idx) => {
        let cloneData = _.cloneDeep(policyData);

        cloneData[idx].isEditMode = true;

        setPolicyData(cloneData);
        setEditPolicyField(cloneData[idx].policyName);
    }

    const evtActivePolicy = (idx, subIdx) => {
        /* let cloneData = _.cloneDeep(policyData);
        cloneData[idx].ruleData[subIdx].isActive = !cloneData[idx].ruleData[subIdx].isActive;
        setPolicyData(cloneData); */
    }

    const handleAllPolicyCheck = (e) => {
        let cloneData = _.cloneDeep(policyData);

        _.forEach(cloneData, (obj) => {
            obj.isPolicyCheck = e.target.checked;
        });

        setPolicyData(cloneData);
        setAllPolicyCheck(e.target.checked);
    };

    const handleIsPolicyCheck = (e, idx) => {
        let cloneData = _.cloneDeep(policyData);
        cloneData[idx].isPolicyCheck = e.target.checked;
        setPolicyData(cloneData);
    }

    const handleAllRuleCheck = (e, idx) => {
        let cloneData = _.cloneDeep(policyData);

        cloneData[idx].allRuleCheck = e.target.checked;

        _.forEach(cloneData[idx].ruleData, (obj) => {
            obj.isRuleCheck = e.target.checked;
        });

        setPolicyData(cloneData);
    }

    const handleIsRuleCheck = (e, idx, subIdx) => {
        let cloneData = _.cloneDeep(policyData);
        cloneData[idx].ruleData[subIdx].isRuleCheck = e.target.checked;
        setPolicyData(cloneData);
    }

    const onAddListPolicy = () => {
        const addData = {
            isPolicyCheck: false,
            allRuleCheck: false,
            policyName: "",
            isEditMode: true,
            ruleData: []
        };

        setPolicyData([...policyData, addData]);
    }

    const onDeleteRuleItem = (idx) => {
        const filterData = _.filter(policyData[idx].ruleData, (obj) => {
            return obj.isRuleCheck;
        });

        if (filterData.length === 0) {
            alert("삭제할 항목을 선택해주세요.");
            return;
        }

        if (window.confirm("삭제하시겠습니까?")) {
            const selectedValueData = _.map(filterData, "ruleId").join("|");

            axiosConf.get("/api/setting/band/deleteRule/" + policyData[idx].policyId + "/" + selectedValueData).then(res => {
                alert("삭제되었습니다.");
                getPolicyList();
            });
        }
    }

    const onDeleteListPolicy = () => {
        const filterData = _.filter(policyData, (obj) => {
            return obj.isPolicyCheck;
        });

        if (filterData.length === 0) {
            alert("삭제할 항목을 선택해주세요.");
            return;
        }

        if (window.confirm("삭제하시겠습니까?")) {
            const selectedValueData = _.map(filterData, "policyId").join("|");

            axiosConf.get("/api/setting/band/DeleteBand/" + selectedValueData).then(res => {
                alert("삭제되었습니다.");
                setAllPolicyCheck(false);
                getPolicyList();
            });
        }
    }

    const applyServer = () => {
        /* axiosConf.post("/api/set/applyApplicationSettings").then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        }); */
    }

    const handleStart = (e, element) => {
        const iniSize = document.getElementById(`${element}`).offsetWidth;

        setDrag({
            iniMouse: e.clientX,
            iniSize: iniSize
        });
    }

    const handleMove = (e, element) => {
        if (e.clientX) {
            const endSize = drag.iniSize + (e.clientX - drag.iniMouse);

            _.forEach(policyData, (obj, i) => {
                document.getElementById(`${element}_${i}`).style.width = `${endSize}px`;
            });
        }
    }

    const handleEnd = (element) => {
        const columnKey = element.split("_")[0];
        const columnWidth = document.getElementById(`${element}`).style.width;

        setColumnSize({ ...columnSize, [columnKey]: columnWidth });
    }

    const resetColumnWidth = () => {
        const resetData = {
            "ruleType": "10rem",
            "ruleName": "10rem",
            "sourceIp": "14rem",
            "ipFlow": "3rem",
            "targetIp": "14rem",
            "sourceMac": "9rem",
            "macFlow": "3rem",
            "targetMac": "9rem",
            "protocolType": "4rem",
            "sourcePort": "6rem",
            "portFlow": "3rem"
        };

        _.forEach(policyData, (obj, i) => {
            _.map(resetData, (columnWidth, columnKey) => {
                document.getElementById(`${columnKey}_${i}`).style.width = `${columnWidth}`;
            });
        });

        setColumnSize(resetData);
    }

    return (
        <Grid container>
            <Grid item md={6}>
                Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(policyData.length)}</Typography>
                <Typography component="span" ml={1}>
                    ( <FormControlLabel control={<Checkbox name="allPolicyChk" checked={allPolicyCheck} onChange={handleAllPolicyCheck} />} sx={{ mr: "5px" }} label="전체 선택" />)
                </Typography>
            </Grid>
            <Grid item md={6} textAlign="right">
                <Button color="primary" size="small" startIcon={<FontAwesomeIcon icon={faRotateRight} />} onClick={resetColumnWidth}>컬럼 너비 초기화</Button>
            </Grid>

            <Grid item md={12}>
                <Box sx={{ overflowY: "auto" }} id="policyNoticeGrid">
                    {
                        _.map(policyData, (obj, i) => (
                            <TableContainer component={Paper} className="border" sx={{ mt: .5 }} key={i}>
                                <Table stickyHeader size="small" className="table-bordered">
                                    <TableHead onDragOver={(e) => e.preventDefault()}>
                                        <TableRow className="thead-light">
                                            <TableCell align="center" sx={{ width: "3rem" }}>
                                                <Checkbox name="isPolicyCheck" checked={obj.isPolicyCheck} onChange={(e) => handleIsPolicyCheck(e, i)} />
                                            </TableCell>
                                            <TableCell colSpan={13}>
                                                <Grid container>
                                                    <Grid item md={.4}>정책 명:</Grid>
                                                    {
                                                        obj.isEditMode ? <Grid item md={11.6}>
                                                            <TextField
                                                                fullWidth
                                                                inputRef={inputRef}
                                                                name="editPolicyField"
                                                                placeholder="정책 명"
                                                                size="small"
                                                                value={editPolicyField}
                                                                autoFocus
                                                                onBlur={() => onBlur(i)}
                                                                onChange={handleKeyPress}
                                                            />
                                                        </Grid> : <Grid item md={11.6}>
                                                            {obj.policyName} <Typography component="span" color="primary" className="cursorp" onClick={() => evtEditPolicyName(i)}>
                                                            <EditOutlined style={{ fontSize: "1rem" }} />
                                                        </Typography>
                                                        </Grid>
                                                    }
                                                </Grid>
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "4rem" }}>
                                                <WindowOpener className="inline-block" url={"/popup/setting/band/reg/" + obj.policyId + "/0"} width={1600} height={window.innerHeight - 100} bridge={getPolicyList}>
                                                    <Typography component="span" color="primary" className="cursorp" >
                                                        <PlusSquareOutlined style={{ fontSize: "1.3rem", marginTop: ".1rem" }} />
                                                    </Typography>
                                                </WindowOpener>
                                                <Typography component="span" color="primary" className="cursorp" onClick={() => onDeleteRuleItem(i)}>
                                                    <MinusSquareOutlined style={{ fontSize: "1.3rem", marginTop: ".1rem", marginLeft: ".1rem" }} />
                                                </Typography>
                                            </TableCell>
                                        </TableRow>
                                        <TableRow className="thead-light">
                                            <TableCell align="center" sx={{ width: "3rem" }} rowSpan={2}>
                                                <Checkbox name="allRuleCheck" checked={obj.allRuleCheck} onChange={(e) => handleAllRuleCheck(e, i)} />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "2rem" }} rowSpan={2}></TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.ruleType }} rowSpan={2} id={"ruleType_" + i}>
                                                규칙 구분
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "ruleType_" + i)}
                                                     onDrag={(e) => handleMove(e, "ruleType")}
                                                     onDragEnd={() => handleEnd("ruleType_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.ruleName }} rowSpan={2} id={"ruleName_" + i}>
                                                규칙 명
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "ruleName_" + i)}
                                                     onDrag={(e) => handleMove(e, "ruleName")}
                                                     onDragEnd={() => handleEnd("ruleName_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" colSpan={3}>IP</TableCell>
                                            <TableCell align="center" colSpan={3}>MAC</TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.protocolType }} rowSpan={2} id={"protocolType_" + i}>
                                                Protocol
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "protocolType_" + i)}
                                                     onDrag={(e) => handleMove(e, "protocolType")}
                                                     onDragEnd={() => handleEnd("protocolType_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" colSpan={3}>Port</TableCell>
                                            <TableCell align="center" sx={{ width: "4.5rem" }} rowSpan={2}>Active</TableCell>
                                        </TableRow>
                                        <TableRow className="thead-dark">
                                            <TableCell align="center" sx={{ width: columnSize.sourceIp }} id={"sourceIp_" + i}>
                                                Source IP
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "sourceIp_" + i)}
                                                     onDrag={(e) => handleMove(e, "sourceIp")}
                                                     onDragEnd={() => handleEnd("sourceIp_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.ipFlow }} id={"ipFlow_" + i}>
                                                Flow
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "ipFlow_" + i)}
                                                     onDrag={(e) => handleMove(e, "ipFlow")}
                                                     onDragEnd={() => handleEnd("ipFlow_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.targetIp }} id={"targetIp_" + i}>
                                                Destination IP
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "targetIp_" + i)}
                                                     onDrag={(e) => handleMove(e, "targetIp")}
                                                     onDragEnd={() => handleEnd("targetIp_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.sourceMac }} id={"sourceMac_" + i}>
                                                Source MAC
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "sourceMac_" + i)}
                                                     onDrag={(e) => handleMove(e, "sourceMac")}
                                                     onDragEnd={() => handleEnd("sourceMac_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.macFlow }} id={"macFlow_" + i}>
                                                Flow
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "macFlow_" + i)}
                                                     onDrag={(e) => handleMove(e, "macFlow")}
                                                     onDragEnd={() => handleEnd("macFlow_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.targetMac }} id={"targetMac_" + i}>
                                                Destination MAC
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "targetMac_" + i)}
                                                     onDrag={(e) => handleMove(e, "targetMac")}
                                                     onDragEnd={() => handleEnd("targetMac_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.sourcePort }} id={"sourcePort_" + i}>
                                                Source Port
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "sourcePort_" + i)}
                                                     onDrag={(e) => handleMove(e, "sourcePort")}
                                                     onDragEnd={() => handleEnd("sourcePort_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: columnSize.portFlow }} id={"portFlow_" + i}>
                                                Flow
                                                <Box component="div" className="Dragger" draggable={true}
                                                     onDragStart={(e) => handleStart(e, "portFlow_" + i)}
                                                     onDrag={(e) => handleMove(e, "portFlow")}
                                                     onDragEnd={() => handleEnd("portFlow_" + i)}
                                                />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "7rem" }}>Destination Port</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {
                                            _.map(obj.ruleData, (subObj, subI) => (
                                                <TableRow key={i + "_" + subI}>
                                                    <TableCell align="center">
                                                        <Checkbox name="isRuleCheck" checked={subObj.isRuleCheck} onChange={(e) => handleIsRuleCheck(e, i, subI)} />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <WindowOpener className="inline-block" url={"/popup/setting/band/reg/" + obj.policyId + "/" + subObj.ruleId} width={window.innerWidth - 100} height={window.innerHeight - 200} bridge={getPolicyList}>
                                                            <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
                                                        </WindowOpener>
                                                    </TableCell>
                                                    <TableCell>
                                                        <Box sx={{ overflowY: "auto", maxHeight: "7rem" }}>
                                                            {subObj.ruleType === 0 ? "사용자 정의" : "어플리케이션 > " + subObj.applicationName}
                                                        </Box>
                                                    </TableCell>
                                                    <TableCell>
                                                        <Box sx={{ overflowY: "auto", maxHeight: "7rem" }}>{subObj.ruleName}</Box>
                                                    </TableCell>
                                                    <TableCell>
                                                        <List component="nav" sx={listItemStyle}>
                                                            {
                                                                _.map(subObj.sourceIp, (mappingObj, mappingI) => (
                                                                    <ListItem key={mappingI}>
                                                                        <ListItemText
                                                                            primary={<Typography variant="h6">{mappingObj.isRange ? mappingObj.startField + " ~ " + mappingObj.endField : mappingObj.startField}</Typography>}
                                                                        />
                                                                    </ListItem>
                                                                ))
                                                            }
                                                        </List>
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <FontAwesomeIcon icon={subObj.ipFlow === 0 ? faArrowRight : subObj.ipFlow === 1 ? faArrowLeft : faArrowsLeftRight} />
                                                    </TableCell>
                                                    <TableCell>
                                                        <List component="nav" sx={listItemStyle}>
                                                            <ListItem>
                                                                <ListItemText
                                                                    primary={<Typography variant="h6">{subObj.targetIpIsRange ? subObj.targetIpStart + " ~ " + subObj.targetIpEnd : subObj.targetIpStart}</Typography>}
                                                                />
                                                            </ListItem>
                                                        </List>
                                                    </TableCell>
                                                    <TableCell>
                                                        <List component="nav" sx={listItemStyle}>
                                                            {
                                                                _.map(subObj.sourceMac, (mappingObj, mappingI) => (
                                                                    <ListItem key={mappingI}>
                                                                        <ListItemText primary={<Typography variant="h6">{mappingObj}</Typography>} />
                                                                    </ListItem>
                                                                ))
                                                            }
                                                        </List>
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <FontAwesomeIcon icon={subObj.macFlow === 0 ? faArrowRight : subObj.macFlow === 1 ? faArrowLeft : faArrowsLeftRight} />
                                                    </TableCell>
                                                    <TableCell>
                                                        <List component="nav" sx={listItemStyle}>
                                                            <ListItem>
                                                                <ListItemText primary={<Typography variant="h6">{subObj.targetMac}</Typography>} />
                                                            </ListItem>
                                                        </List>
                                                    </TableCell>
                                                    <TableCell align="center">{subObj.protocolType}</TableCell>
                                                    <TableCell>
                                                        <List component="nav" sx={listItemStyle}>
                                                            {
                                                                _.map(subObj.sourcePort, (mappingObj, mappingI) => (
                                                                    <ListItem key={mappingI}>
                                                                        <ListItemText
                                                                            primary={<Typography variant="h6">{mappingObj.isRange ? mappingObj.startField + " ~ " + mappingObj.endField : mappingObj.startField}</Typography>}
                                                                        />
                                                                    </ListItem>
                                                                ))
                                                            }
                                                        </List>
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <FontAwesomeIcon icon={subObj.portFlow === 0 ? faArrowRight : subObj.portFlow === 1 ? faArrowLeft : faArrowsLeftRight} />
                                                    </TableCell>
                                                    <TableCell>
                                                        <List component="nav" sx={listItemStyle}>
                                                            <ListItem>
                                                                <ListItemText
                                                                    primary={<Typography variant="h6">{subObj.targetPortIsRange ? subObj.targetPortStart + " ~ " + subObj.targetPortEnd : subObj.targetPortStart}</Typography>}
                                                                />
                                                            </ListItem>
                                                        </List>
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        {
                                                            subObj.isActive ? <Button variant="contained" color="info" size="small" onClick={() => evtActivePolicy(i, subI)}>활성</Button>
                                                                : <Button variant="contained" color="error" size="small" onClick={() => evtActivePolicy(i, subI)}>비활성</Button>
                                                        }
                                                    </TableCell>
                                                </TableRow>
                                            ))
                                        }
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        ))
                    }
                </Box>
            </Grid>

            <Grid item md={6} mt={.5}>
                <Button color="primary" size="small" startIcon={<DownloadOutlined />}>내보내기</Button>
            </Grid>
            <Grid item md={6} mt={.5} align="right">
                <Button variant="contained" color="primary" size="small" sx={{ mr: .5 }} onClick={onAddListPolicy}>추가</Button>
                <Button variant="contained" color="secondary" size="small" onClick={onDeleteListPolicy}>삭제</Button>
            </Grid>

            {showLoader && (<Loader />)}
        </Grid>
    );
};

export default BandIndex;