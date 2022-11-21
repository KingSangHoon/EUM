import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleDown, faAngleUp } from "@fortawesome/free-solid-svg-icons";
import { Grid, Box, Button, List, ListSubheader, CardContent, Collapse, Typography, FormGroup, FormControlLabel, Checkbox } from '@mui/material';

import { hiddenComponentPopup } from '../../../lib/common';

const staticLayout = {
    0: "TCP 가용성",
    1: "TCP 에러 - Out Of Order",
    2: "TCP 에러 - Lost Segment",
    3: "TCP 에러 - Duplication Ack",
    4: "TCP 에러 - Zero Window",
    5: "TCP 에러 - Retransmission",
    6: "TCP 에러 - Connection Refuse",
    7: "TCP 에러 - Connection Timeout",
    8: "TCP 에러 - Not Responding",
    9: "HTTP 가용성",
    10: "HTTP 응답 - 2xx (성공)",
    11: "HTTP 응답 - 3xx (Redirection)",
    12: "HTTP 응답 - 4xx (단말 에러)",
    13: "HTTP 응답 - 5xx (서버 에러)",
    14: "HTTP 응답 - xxx (Etc...)",
    15: "Stop Transaction",
    16: "Transaction 성능",
    17: "Transaction 응답시간",
    18: "ransaction 응답시간 - Request Data Transfer",
    19: "Transaction 응답시간 - Response Data Transfer",
    20: "Transaction 응답시간 - Data Ack RTT",
    21: "Transaction 응답시간 - Connection RTT",
    22: "단말 응답시간 - Page Design Time",
    23: "단말 응답시간 - Think Time (Gap)",
    24: "서버 시간이 느린 Transaction",
    25: "서버 응답시간 - Initial",
    26: "서버 응답시간 - Application (Buffer Push)",
    27: "서버 응답시간 - Processing",
    28: "Uses",
    29: "Sessions",
    30: "Transactions",
    31: "네트워크 - 사이즈",
    32: "네트워크 - 수",
    33: "네트워크 - 사이즈 (에러)",
    34: "네트워크 - 수 (에러)",
    35: "네트워크 - 사이즈 (Payload)",
    36: "네트워크 - 수 (Payload)",
    37: "네트워크 - 사이즈 (Payload 에러)",
    38: "네트워크 - 수 (Payload 에러)",
};

const formatReqChkData = () => {
    let formatArr = [];

    _.map(staticLayout, (val, key) => {
        formatArr.push({
            key: key,
            name: val,
            check: false
        });
    });

    return formatArr;
}

const PopupDashboardSetWidget = () => {
    const [reqCheckbox, setReqCheckbox] = useState(formatReqChkData());
    const [state, setState] = useState({
        allCheckboxTcpError: false,
        allCheckboxHttpRes: false,
        allCheckboxTransactionRes: false,
        allCheckboxTerminalRes: false,
        allCheckboxServerRes: false,
        allCheckboxNetwork: false,
        openListGroup: {
            tcpErrorToggler: true,
            httpResponseToggler: true,
            transactionResponseToggler: true,
            terminalResponseToggler: true,
            serverResponseToggler: true,
            networkToggler: true,
        }
    });

    useEffect(() => {
        hiddenComponentPopup();
        window.addEventListener("resize", hiddenComponentPopup);

        return () => {
            window.removeEventListener('resize', hiddenComponentPopup);
        }
    }, []);

    const handleCheckboxChange = e => {
        let cloneChk = _.cloneDeep(reqCheckbox);
        const splitId = e.target.id.split("-")[1];

        _.forEach(cloneChk, (obj) => {
            if (obj.key === splitId) {
                obj.check = e.target.checked;
            }
        });

        setReqCheckbox(cloneChk);
    }

    const allSectionCheckboxChange = (e, targetId) => {
        let cloneChk = _.cloneDeep(reqCheckbox);

        _.forEach(cloneChk, (obj) => {
            if (targetId.indexOf(obj.key) !== -1) {
                obj.check = e.target.checked;
            }
        });

        setReqCheckbox(cloneChk);
        setState({ ...state, [e.target.id]: e.target.checked });
    }

    const onSavePolicyValidation = () => {
        alert("저장 되었습니다.");
        window.opener.onSuccess({ saveChk: reqCheckbox });
        window.close();

        //applyPolicySave(requestData);
    }

    /* const applyPolicySave = (requestData) => {
        const requestUrl = state.paramIndex === "0" ? "/setting/createServer" : "/setting/updateServer/" + state.paramIndex;

        axiosConf.post(requestUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    } */

    const evtCollapseListGroup = (target) => {
        let cloneOpenListGroup = _.cloneDeep(state.openListGroup);

        cloneOpenListGroup[target] = !cloneOpenListGroup[target];

        setState({ ...state, openListGroup: cloneOpenListGroup });
    }

    return (
        <Grid container spacing={1}>
            <Grid item sm={4}>
                <Typography variant="h5">가용성</Typography>

                <List component="nav" aria-labelledby="nested-list-subheader">
                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-0" checked={reqCheckbox[0].check} onChange={handleCheckboxChange} />} label="TCP 가용성" />
                        </FormGroup>
                    </ListSubheader>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="allCheckboxTcpError" checked={state.allCheckboxTcpError} onChange={e => allSectionCheckboxChange(e, ["1", "2", "3", "4", "5", "6", "7", "8"])} />} label="TCP 에러" />
                        </FormGroup>
                        <Box sx={{ position: "absolute", top: ".4rem", right: ".5rem", lineHeight: "0" }} className="cursorp" onClick={() => evtCollapseListGroup("tcpErrorToggler")}>
                            {state.openListGroup.tcpErrorToggler ? <FontAwesomeIcon icon={faAngleDown} /> : <FontAwesomeIcon icon={faAngleUp} />}
                        </Box>
                    </ListSubheader>
                    <Collapse in={state.openListGroup.tcpErrorToggler} timeout="auto" unmountOnExit>
                        <CardContent className="bg-gray bottom_underline">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-1" checked={reqCheckbox[1].check} onChange={handleCheckboxChange} />} label="Out Of Order" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-2" checked={reqCheckbox[2].check} onChange={handleCheckboxChange} />} label="Lost Segment" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-3" checked={reqCheckbox[3].check} onChange={handleCheckboxChange} />} label="Duplication Ack" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-4" checked={reqCheckbox[4].check} onChange={handleCheckboxChange} />} label="Zero Window" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-5" checked={reqCheckbox[5].check} onChange={handleCheckboxChange} />} label="Retransmission" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                        <CardContent className="bg-gray">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-6" checked={reqCheckbox[6].check} onChange={handleCheckboxChange} />} label="Connection Refuse" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-7" checked={reqCheckbox[7].check} onChange={handleCheckboxChange} />} label="Connection Timeout" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-8" checked={reqCheckbox[8].check} onChange={handleCheckboxChange} />} label="Not Responding" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                    </Collapse>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-9" checked={reqCheckbox[9].check} onChange={handleCheckboxChange} />} label="HTTP 가용성" />
                        </FormGroup>
                    </ListSubheader>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="allCheckboxHttpRes" checked={state.allCheckboxHttpRes} onChange={e => allSectionCheckboxChange(e, ["10", "11", "12", "13", "14"])} />} label="HTTP 응답" />
                        </FormGroup>
                        <Box sx={{ position: "absolute", top: ".4rem", right: ".5rem", lineHeight: "0" }} className="cursorp" onClick={() => evtCollapseListGroup("httpResponseToggler")}>
                            {state.openListGroup.httpResponseToggler ? <FontAwesomeIcon icon={faAngleDown} /> : <FontAwesomeIcon icon={faAngleUp} />}
                        </Box>
                    </ListSubheader>
                    <Collapse in={state.openListGroup.httpResponseToggler} timeout="auto" unmountOnExit>
                        <CardContent className="bg-gray">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-10" checked={reqCheckbox[10].check} onChange={handleCheckboxChange} />} label="2xx (성공)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-11" checked={reqCheckbox[11].check} onChange={handleCheckboxChange} />} label="3xx (Redirection)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-12" checked={reqCheckbox[12].check} onChange={handleCheckboxChange} />} label="4xx (단말 에러)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-13" checked={reqCheckbox[13].check} onChange={handleCheckboxChange} />} label="5xx (서버 에러)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-14" checked={reqCheckbox[14].check} onChange={handleCheckboxChange} />} label="xxx (Etc...)" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                    </Collapse>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-15" checked={reqCheckbox[15].check} onChange={handleCheckboxChange} />} label="Stop Transaction" />
                        </FormGroup>
                    </ListSubheader>
                </List>
            </Grid>

            <Grid item sm={4}>
                <Typography variant="h5">성능</Typography>

                <List component="nav" aria-labelledby="nested-list-subheader">
                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-16" checked={reqCheckbox[16].check} onChange={handleCheckboxChange} />} label="Transaction 성능" />
                        </FormGroup>
                    </ListSubheader>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="allCheckboxTransactionRes" checked={state.allCheckboxTransactionRes} onChange={e => allSectionCheckboxChange(e, ["17", "18", "19", "20", "21"])} />} label="Transaction 응답 시간" />
                        </FormGroup>
                        <Box sx={{ position: "absolute", top: ".4rem", right: ".5rem", lineHeight: "0" }} className="cursorp" onClick={() => evtCollapseListGroup("transactionResponseToggler")}>
                            {state.openListGroup.transactionResponseToggler ? <FontAwesomeIcon icon={faAngleDown} /> : <FontAwesomeIcon icon={faAngleUp} />}
                        </Box>
                    </ListSubheader>
                    <Collapse in={state.openListGroup.transactionResponseToggler} timeout="auto" unmountOnExit>
                        <CardContent className="bg-gray">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-17" checked={reqCheckbox[17].check} onChange={handleCheckboxChange} />} label="Transaction 응답 시간" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-18" checked={reqCheckbox[18].check} onChange={handleCheckboxChange} />} label="Request Data Transfer" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-19" checked={reqCheckbox[19].check} onChange={handleCheckboxChange} />} label="Response Data Transfer" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-20" checked={reqCheckbox[20].check} onChange={handleCheckboxChange} />} label="Data Ack RTT" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-21" checked={reqCheckbox[21].check} onChange={handleCheckboxChange} />} label="Connection RTT" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                    </Collapse>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="allCheckboxTerminalRes" checked={state.allCheckboxTerminalRes} onChange={e => allSectionCheckboxChange(e, ["22", "23"])} />} label="단말 응답 시간" />
                        </FormGroup>
                        <Box sx={{ position: "absolute", top: ".4rem", right: ".5rem", lineHeight: "0" }} className="cursorp" onClick={() => evtCollapseListGroup("terminalResponseToggler")}>
                            {state.openListGroup.terminalResponseToggler ? <FontAwesomeIcon icon={faAngleDown} /> : <FontAwesomeIcon icon={faAngleUp} />}
                        </Box>
                    </ListSubheader>
                    <Collapse in={state.openListGroup.terminalResponseToggler} timeout="auto" unmountOnExit>
                        <CardContent className="bg-gray">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-22" checked={reqCheckbox[22].check} onChange={handleCheckboxChange} />} label="Page Design Time" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-23" checked={reqCheckbox[23].check} onChange={handleCheckboxChange} />} label="Think Time (Gap)" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                    </Collapse>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-24" checked={reqCheckbox[24].check} onChange={handleCheckboxChange} />} label="서버 시간이 느린 Transaction" />
                        </FormGroup>
                    </ListSubheader>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="allCheckboxServerRes" checked={state.allCheckboxServerRes} onChange={e => allSectionCheckboxChange(e, ["25", "26", "27"])} />} label="서버 응답 시간" />
                        </FormGroup>
                        <Box sx={{ position: "absolute", top: ".4rem", right: ".5rem", lineHeight: "0" }} className="cursorp" onClick={() => evtCollapseListGroup("serverResponseToggler")}>
                            {state.openListGroup.serverResponseToggler ? <FontAwesomeIcon icon={faAngleDown} /> : <FontAwesomeIcon icon={faAngleUp} />}
                        </Box>
                    </ListSubheader>
                    <Collapse in={state.openListGroup.serverResponseToggler} timeout="auto" unmountOnExit>
                        <CardContent className="bg-gray">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-25" checked={reqCheckbox[25].check} onChange={handleCheckboxChange} />} label="Initial" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-26" checked={reqCheckbox[26].check} onChange={handleCheckboxChange} />} label="Application (Buffer Push)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-27" checked={reqCheckbox[27].check} onChange={handleCheckboxChange} />} label="Processing" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                    </Collapse>
                </List>
            </Grid>

            <Grid item sm={4}>
                <Typography variant="h5">사용량</Typography>

                <List component="nav" aria-labelledby="nested-list-subheader">
                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-28" checked={reqCheckbox[28].check} onChange={handleCheckboxChange} />} label="Uses" />
                        </FormGroup>
                    </ListSubheader>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-29" checked={reqCheckbox[29].check} onChange={handleCheckboxChange} />} label="Sessions" />
                        </FormGroup>
                    </ListSubheader>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="reqCheckbox-30" checked={reqCheckbox[30].check} onChange={handleCheckboxChange} />} label="Transactions" />
                        </FormGroup>
                    </ListSubheader>

                    <ListSubheader component="div">
                        <FormGroup>
                            <FormControlLabel control={<Checkbox id="allCheckboxNetwork" checked={state.allCheckboxNetwork} onChange={e => allSectionCheckboxChange(e, ["31", "32", "33", "34", "35", "36", "37", "38"])} />} label="네트워크" />
                        </FormGroup>
                        <Box sx={{ position: "absolute", top: ".4rem", right: ".5rem", lineHeight: "0" }} className="cursorp" onClick={() => evtCollapseListGroup("networkToggler")}>
                            {state.openListGroup.networkToggler ? <FontAwesomeIcon icon={faAngleDown} /> : <FontAwesomeIcon icon={faAngleUp} />}
                        </Box>
                    </ListSubheader>
                    <Collapse in={state.openListGroup.networkToggler} timeout="auto" unmountOnExit>
                        <CardContent className="bg-gray bottom_underline">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-31" checked={reqCheckbox[31].check} onChange={handleCheckboxChange} />} label="사이즈" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-32" checked={reqCheckbox[32].check} onChange={handleCheckboxChange} />} label="수" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-33" checked={reqCheckbox[33].check} onChange={handleCheckboxChange} />} label="사이즈 (에러)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-34" checked={reqCheckbox[34].check} onChange={handleCheckboxChange} />} label="수 (에러)" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                        <CardContent className="bg-gray">
                            <Grid container sx={{ pl: 1, pr: 1 }}>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-35" checked={reqCheckbox[35].check} onChange={handleCheckboxChange} />} label="사이즈 (Payload)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-36" checked={reqCheckbox[36].check} onChange={handleCheckboxChange} />} label="수 (Payload)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-37" checked={reqCheckbox[37].check} onChange={handleCheckboxChange} />} label="사이즈 (Payload 에러)" />
                                    </FormGroup>
                                </Grid>
                                <Grid item sm={6}>
                                    <FormGroup>
                                        <FormControlLabel control={<Checkbox id="reqCheckbox-38" checked={reqCheckbox[38].check} onChange={handleCheckboxChange} />} label="수 (Payload 에러)" />
                                    </FormGroup>
                                </Grid>
                            </Grid>
                        </CardContent>
                    </Collapse>
                </List>
            </Grid>

            <Grid item sm={12} textAlign="center">
                <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
            </Grid>
        </Grid>
    );
};

export default PopupDashboardSetWidget;