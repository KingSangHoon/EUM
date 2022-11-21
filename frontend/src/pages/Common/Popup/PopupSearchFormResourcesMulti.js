import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import PropTypes from 'prop-types';
import { useSearchParams } from 'react-router-dom';
import { Grid, Box, Button, ListItem, CardContent, Collapse, FormGroup, FormControlLabel, Checkbox, Tabs, Tab } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleDown, faAngleUp } from "@fortawesome/free-solid-svg-icons";

import { hiddenComponentPopup } from '../../../lib/common';

function TabPanel(props) {
    const { listItem, selectItem, evtCollapseListGroup, handleCheckboxChange } = props;
    const selectItemKey = _.map(selectItem, "key");

    return (
        _.map(listItem, (obj, i) => (
            obj.group ? <React.Fragment key={i}>
                <ListItem component="div" className="border cursorp" onClick={() => evtCollapseListGroup(i)}>
                    {obj.name}
                    <Box sx={{ position: "absolute", top: ".4rem", right: ".5rem", lineHeight: "0" }}>
                        {obj.open ? <FontAwesomeIcon icon={faAngleUp} /> : <FontAwesomeIcon icon={faAngleDown} />}
                    </Box>
                </ListItem>
                <Collapse in={obj.open} timeout="auto" unmountOnExit>
                    <CardContent className="bg-gray">
                        <Grid container sx={{ pl: 1, pr: 1 }}>
                            {
                                _.map(obj.children, (subObj, subI) => (
                                    <Grid item sm={4} key={i + "_" + subI}>
                                        <FormGroup>
                                            <FormControlLabel control={<Checkbox id={subObj.key} checked={selectItemKey.indexOf(subObj.key) !== -1} onChange={(e) => handleCheckboxChange(e, i)} />} label={subObj.name} />
                                        </FormGroup>
                                    </Grid>
                                ))
                            }
                        </Grid>
                    </CardContent>
                </Collapse>
            </React.Fragment> : <CardContent className="bg-gray" key={i}>
                <Grid container sx={{ pl: 1, pr: 1 }}>
                    {
                        _.map(obj.children, (subObj, subI) => (
                            <Grid item sm={4} key={i + "_" + subI}>
                                <FormGroup>
                                    <FormControlLabel control={<Checkbox id={subObj.key} checked={selectItemKey.indexOf(subObj.key) !== -1} onChange={(e) => handleCheckboxChange(e, i)} />} label={subObj.name} />
                                </FormGroup>
                            </Grid>
                        ))
                    }
                </Grid>
            </CardContent>
        ))
    );
}

TabPanel.propTypes = {
    listItem: PropTypes.array,
    selectItem: PropTypes.array
};

const PopupSearchFormResourcesMulti = () => {
    const [searchParams] = useSearchParams();
    const paramKey = searchParams.get("key");
    const staticMenuKey = { 0: "http", 1: "ip", 2: "tcp", 3: "udp" };

    const [paramApp, setParamApp] = useState(false);

    const [menuKey, setMenuKey] = useState(0);

    const [selectResourceObj, setSelectResourceObj] = useState([]);
    const [resourceItem, setResourceItem] = useState([]);

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
                const getItem = jsonParam[searchParams.get("key")] ? jsonParam[searchParams.get("key")][0] : [{ name: "Page Response Time", key: "tsPageAvg", tab: "http" }];

                if (jsonParam.application) {
                    setParamApp(jsonParam.application.length > 0);
                }

                setSelectResourceObj(getItem);
                setResourceItem(resourceOption(getItem.tab));
            } else {
                const getItem = [{ name: "Page Response Time", key: "tsPageAvg", tab: "http" }];

                setSelectResourceObj(getItem);
                setResourceItem(resourceOption(getItem[0].tab));
            }
        }, 100);
    }

    const resourceOption = (selectKey) => {
        const trafficResource = {
            name: "Traffic",
            group: true,
            open: true,
            children: [
                { name: "Traffic Len (request)", key: "lenReqPerSecAvg" },
                { name: "Traffic Len (response)", key: "lenResPerSecAvg" },
                { name: "Traffic Pkts (request)", key: "pktsReqPerSecAvg" },
                { name: "Traffic Pkts (response)", key: "pktsResPerSecAvg" },
                { name: "Traffic Len Delta (request)", key: "lenReqDeltaSum" },
                { name: "Traffic Len Delta (response)", key: "lenResDeltaSum" },
                { name: "Traffic Pkts Delta (request)", key: "pktsReqDeltaSum" },
                { name: "Traffic Pkts Delta (response)", key: "pktsResDeltaSum" },
                { name: "Traffic Pkt Len Min (request)", key: "pktLenMinReqMin" },
                { name: "Traffic Pkt Len Min (response)", key: "pktLenMinResMin" },
                { name: "Traffic Pkt Len Max (request)", key: "pktLenMaxReqMax" },
                { name: "Traffic Pkt Len Max (response)", key: "pktLenMaxResMax" }
            ]
        };
        const ipResource = {
            name: "IP",
            group: true,
            open: true,
            children: [
                { name: "Packet Fragment (request)", key: "fragPktsPerSecReqAvg" },
                { name: "Packet Fragment (response)", key: "fragPktsPerSecResAvg" },
                { name: "Packet Fragment Delta (request)", key: "fragPktsDeltaReqSum" },
                { name: "Packet Fragment Delta (response)", key: "fragPktsDeltaResSum" },
                { name: "TTL Min (request)", key: "ttlMinReqMin" },
                { name: "TTL Min (response)", key: "ttlMinResMin" },
                { name: "TTL Max (request)", key: "ttlMaxReqMax" },
                { name: "TTL Max (response)", key: "ttlMaxResMax" },
                { name: "Overlap Pkts (request)", key: "overlapPktsPerSecReqAvg" },
                { name: "Overlap Pkts (response)", key: "overlapPktsPerSecResAvg" },
                { name: "Overlap Bytes (request)", key: "overlapBytesPerSecReqAvg" },
                { name: "Overlap Bytes (response)", key: "overlapBytesPerSecResAvg" },
                { name: "Overlap Attack Pkts (request)", key: "overlapAttackPktsPerSecReqAvg" },
                { name: "Overlap Attack Pkts (response)", key: "overlapAttackPktsPerSecResAvg" },
                { name: "Overlap Attack Bytes (request)", key: "overlapAttackBytesPerSecReqAvg" },
                { name: "Overlap Attack Bytes (response)", key: "overlapAttackBytesPerSecResAvg" },
                { name: "Overlap Pkts Delta (request)", key: "overlapPktsDeltaReqSum" },
                { name: "Overlap Pkts Delta (response)", key: "overlapPktsDeltaResSum" },
                { name: "Overlap Bytes Delta (request)", key: "overlapBytesDeltaReqSum" },
                { name: "Overlap Bytes Delta (response)", key: "overlapBytesDeltaResSum" },
                { name: "Overlap Attack Pkts Delta (request)", key: "overlapAttackPktsDeltaReqSum" },
                { name: "Overlap Attack Pkts Delta (response)", key: "overlapAttackPktsDeltaResSum" },
                { name: "Overlap Attack Bytes Delta (request)", key: "overlapAttackBytesDeltaReqSum" },
                { name: "Overlap Attack Bytes Delta (response)", key: "overlapAttackBytesDeltaResSum" }
            ]
        };
        const tcpResource = {
            name: "TCP",
            group: true,
            open: true,
            children: [
                { name: "Len Pdu (request)", key: "lenPduReqPerSecAvg" },
                { name: "Len Pdu (response)", key: "lenPduResPerSecAvg" },
                { name: "Pkts Pdu (request)", key: "pktsPduReqPerSecAvg" },
                { name: "Pkts Pdu (response)", key: "pktsPduResPerSecAvg" },
                { name: "Len Pdu Delta (request)", key: "lenPduReqDeltaSum" },
                { name: "Len Pdu Delta (response)", key: "lenPduResDeltaSum" },
                { name: "Pkts Pdu Delta (request)", key: "pktsPduReqDeltaSum" },
                { name: "Pkts Pdu Delta (response)", key: "pktsPduResDeltaSum" },
                { name: "RTT SYN", key: "tsRttSynAvg" },
                { name: "RTT SYN ACK", key: "tsRttSynAckAvg" },
                { name: "RTT First Ack (request)", key: "tsRttFirstAckReqAvg" },
                { name: "RTT First Ack (response)", key: "tsRttFirstAckResAvg" },
                { name: "ACK RTT Sum (request)", key: "ackRttSumReqSum" },
                { name: "ACK RTT Sum (request)", key: "ackRttSumResSum" },
                { name: "ACK RTO Sum (request)", key: "ackRtoSumReqSum" },
                { name: "ACK RTO Sum (request)", key: "ackRtoSumResSum" },
                { name: "ACK RTT Count (request)", key: "ackRttCntReqSum" },
                { name: "ACK RTT Count (response)", key: "ackRttCntResSum" },
                { name: "ACK RTO Count (request)", key: "ackRtoCntReqSum" },
                { name: "ACK RTO Count (response)", key: "ackRtoCntResSum" },
                { name: "ACK RTT Total Min (request)", key: "ackRttReqTotMin" },
                { name: "ACK RTT Total Min (response)", key: "ackRttResTotMin" },
                { name: "ACK RTT Total Max (request)", key: "ackRttReqTotMax" },
                { name: "ACK RTT Total Max (response)", key: "ackRttResTotMax" },
                { name: "ACK RTT Current Min (request)", key: "ackRttReqCurrMin" },
                { name: "ACK RTT Current Min (response)", key: "ackRttResCurrMin" },
                { name: "ACK RTT Current Max (request)", key: "ackRttReqCurrMax" },
                { name: "ACK RTT Current Max (response)", key: "ackRttResCurrMax" },
                { name: "TCP Flag Stat Fin (request)", key: "tcpFlagStatFinReqPerSecAvg" },
                { name: "TCP Flag Stat Fin (response)", key: "tcpFlagStatFinResPerSecAvg" },
                { name: "TCP Flag Stat SYN (request)", key: "tcpFlagStatSynReqPerSecAvg" },
                { name: "TCP Flag Stat SYN (response)", key: "tcpFlagStatSynResPerSecAvg" },
                { name: "TCP Flag Stat Rst (request)", key: "tcpFlagStatRstReqPerSecAvg" },
                { name: "TCP Flag Stat Rst (response)", key: "tcpFlagStatRstResPerSecAvg" },
                { name: "TCP Flag Stat Psh (request)", key: "tcpFlagStatPshReqPerSecAvg" },
                { name: "TCP Flag Stat Psh (response)", key: "tcpFlagStatPshResPerSecAvg" },
                { name: "TCP Flag Stat ACK (request)", key: "tcpFlagStatAckReqPerSecAvg" },
                { name: "TCP Flag Stat ACK (response)", key: "tcpFlagStatAckResPerSecAvg" },
                { name: "TCP Flag Stat Urg (request)", key: "tcpFlagStatUrgReqPerSecAvg" },
                { name: "TCP Flag Stat Urg (response)", key: "tcpFlagStatUrgResPerSecAvg" },
                { name: "TCP Flag Stat Fin Delta (request)", key: "tcpFlagStatFinReqDeltaSum" },
                { name: "TCP Flag Stat Fin Delta (response)", key: "tcpFlagStatFinResDeltaSum" },
                { name: "TCP Flag Stat SYN Delta (request)", key: "tcpFlagStatSynReqDeltaSum" },
                { name: "TCP Flag Stat SYN Delta (response)", key: "tcpFlagStatSynResDeltaSum" },
                { name: "TCP Flag Stat Rst Delta (request)", key: "tcpFlagStatRstReqDeltaSum" },
                { name: "TCP Flag Stat Rst Delta (response)", key: "tcpFlagStatRstResDeltaSum" },
                { name: "TCP Flag Stat Psh Delta (request)", key: "tcpFlagStatPshReqDeltaSum" },
                { name: "TCP Flag Stat Psh Delta (response)", key: "tcpFlagStatPshResDeltaSum" },
                { name: "TCP Flag Stat ACK Delta (request)", key: "tcpFlagStatAckReqDeltaSum" },
                { name: "TCP Flag Stat ACK Delta (response)", key: "tcpFlagStatAckResDeltaSum" },
                { name: "TCP Flag Stat Urg Delta (request)", key: "tcpFlagStatUrgReqDeltaSum" },
                { name: "TCP Flag Stat Urg Delta (response)", key: "tcpFlagStatUrgResDeltaSum" }
            ]
        };
        const udpResource = {
            name: "UDP",
            group: true,
            open: true,
            children: [
                { name: "Len Pdu (request)", key: "lenPduReqPerSecAvg" },
                { name: "Len Pdu (response)", key: "lenPduResPerSecAvg" },
                { name: "Pkts Pdu (request)", key: "pktsPduReqPerSecAvg" },
                { name: "Pkts Pdu (response)", key: "pktsPduResPerSecAvg" },
                { name: "Len Pdu Delta (request)", key: "lenPduReqDeltaSum" },
                { name: "Len Pdu Delta (response)", key: "lenPduResDeltaSum" },
                { name: "Pkts Pdu Delta (request)", key: "pktsPduReqDeltaSum" },
                { name: "Pkts Pdu Delta (response)", key: "pktsPduResDeltaSum" }
            ]
        };
        const httpResource = [{
            group: false,
            children: [
                { name: "Page Response Time", key: "tsPageAvg" },
                { name: "Page Gap Time", key: "tsPageGapAvg" },
                { name: "Page Response Init Time", key: "tsPageResInitAvg" },
                { name: "Page Response Init Gap Time", key: "tsPageResInitGapAvg" },
                { name: "Page Response App Time", key: "tsPageResAppAvg" },
                { name: "Page Response Gap Time", key: "tsPageResGapAvg" },
                { name: "Page Transfer Request Time", key: "tsPageTransferReqAvg" },
                { name: "Page Transfer Request Gap Time", key: "tsPageTransferReqGapAvg" },
                { name: "Page Transfer Response Time", key: "tsPageTrnasferResAvg" },
                { name: "Page Transfer Response Gap Time", key: "tsPageTransferResGapAvg" },
                { name: "Page Request Making Time", key: "pageReqMakingCntSum" },
                { name: "Page Connection RTT (request)", key: "tsPageRttConnSumReqSum" },
                { name: "Page Connection RTT (response)", key: "tsPageRttConnSumResSum" },
                { name: "Page ACK RTT (request)", key: "tsPageRttAckSumReqSum" },
                { name: "Page ACK RTT (response)", key: "tsPageRttAckSumResSum" },
                { name: "Page RTO (request)", key: "tsPageRtoSumReqSum" },
                { name: "Page RTO (response)", key: "tsPageRtoSumResSum" },
                { name: "Page Connection RTT Count (request)", key: "pageRttConnCntReqSum" },
                { name: "Page Connection RTT Count (response)", key: "pageRttConnCntResSum" },
                { name: "Page ACK RTT Count (request)", key: "pageRttAckCntReqSum" },
                { name: "Page ACK RTT Count (response)", key: "pageRttAckCntResSum" },
                { name: "Page Request Making Count", key: "pageReqMakingCntSum" },
                { name: "HTTP Length (request)", key: "pageHttpLenReqSum" },
                { name: "HTTP Length (response)", key: "pageHttpLenResSum" },
                { name: "HTTP Count (request)", key: "pageHttpCntReqSum" },
                { name: "HTTP Count (response)", key: "pageHttpCntResSum" },
                { name: "Packet Length (request)", key: "pagePktLenReqSum" },
                { name: "Packet Length (response)", key: "pagePktLenResSum" },
                { name: "Packet Count (request)", key: "pagePktCntReqSum" },
                { name: "Packet Count (response)", key: "pagePktCntResSum" },
                { name: "Session Count", key: "pageSessionCntSum" },
                { name: "Page RTO Count (request)", key: "pageRtoCntReqSum" },
                { name: "Page RTO Count (response)", key: "pageRtoCntResSum" },
                { name: "TCP Length (request)", key: "pageTcpLenReqSum" },
                { name: "TCP Length (response)", key: "pageTcpLenResSum" },
                { name: "TCP Count (request)", key: "pageTcpCntReqSum" },
                { name: "TCP Count (response)", key: "pageTcpCntResSum" },
                { name: "Connection Error Packet Count", key: "connErrPktCntSum" },
                { name: "Connection Error Session Count", key: "connErrSessionCntSum" },
                { name: "Connection Error Session Length (request)", key: "reqCononErrSessionLenSum" },
                { name: "Connection Error Session Length (response)", key: "resCononErrSessionLenSum" },
                { name: "TCP Error(%)", key: "tcpError" },
                { name: "HTTP Error(%)", key: "httpError" }
            ]
        },
        {
            name: "TCP Error",
            group: true,
            open: true,
            children: [
                { name: "Retransmission Count (request)", key: "retransmissionCntReqSum" },
                { name: "Retransmission Count (response)", key: "retransmissionCntResSum" },
                { name: "Retransmission Length (request)", key: "retransmissionLenReqSum" },
                { name: "Retransmission Length (response)", key: "retransmissionLenResSum" },
                { name: "Fast Retransmission Count (request)", key: "fastRetransmissionCntReqSum" },
                { name: "Fast Retransmission Count (response)", key: "fastRetransmissionCntResSum" },
                { name: "Fast Retransmission Length (request)", key: "fastRetransmissionLenReqSum" },
                { name: "Fast Retransmission Length (response)", key: "fastRetransmissionLenResSum" },
                { name: "Out of Order Count (request)", key: "outOfOrderCntReqSum" },
                { name: "Out of Order Count (response)", key: "outOfOrderCntResSum" },
                { name: "Out of Order Length (request)", key: "outOfOrderLenReqSum" },
                { name: "Out of Order Length (response)", key: "outOfOrderLenResSum" },
                { name: "Lost Segment Count (request)", key: "lostSegCntReqSum" },
                { name: "Lost Segment Count (response)", key: "lostSegCntResSum" },
                { name: "Lost Segment Length (request)", key: "lostSegLenReqSum" },
                { name: "Lost Segment Length (response)", key: "lostSegLenResSum" },
                { name: "ACK Lost Count (request)", key: "ackLostCntReqSum" },
                { name: "ACK Lost Count (response)", key: "ackLostCntResSum" },
                { name: "ACK Lost Length (request)", key: "ackLostLenReqSum" },
                { name: "ACK Lost Length (response)", key: "ackLostLenResSum" },
                { name: "Dup ACK Count (request)", key: "dupAckCntReqSum" },
                { name: "Dup ACK Count (response)", key: "dupAckCntResSum" },
                { name: "Dup ACK Length (request)", key: "dupAckLenReqSum" },
                { name: "Dup ACK Length (response)", key: "dupAckLenResSum" },
                { name: "Zero Window Count (request)", key: "zeroWinCntReqSum" },
                { name: "Zero Window Count (response)", key: "zeroWinCntResSum" },
                { name: "Zero Window Length (request)", key: "zeroWinLenReqSum" },
                { name: "Zero Window Length (response)", key: "zeroWinLenResSum" },
                { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReqSum" },
                { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntResSum" },
                { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReqSum" },
                { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenResSum" },
                { name: "Overlap Count (request)", key: "overlapCntReqSum" },
                { name: "Overlap Count (response)", key: "overlapCntResSum" },
                { name: "Overlap Length (request)", key: "overlapLenReqSum" },
                { name: "Overlap Length (response)", key: "overlapLenResSum" },
                { name: "Overlap Attack Count (request)", key: "overlapAttackCntReqSum" },
                { name: "Overlap Attack Count (response)", key: "overlapAttackCntResSum" },
                { name: "Overlap Attack Length (request)", key: "overlapAttackLenReqSum" },
                { name: "Overlap Attack Length (response)", key: "overlapAttackLenResSum" },
                { name: "Zero Window Ack Probe Count (request)", key: "zeroWinProbeAckCntReqSum" },
                { name: "Zero Window Ack Probe Count (response)", key: "zeroWinProbeAckCntResSum" },
                { name: "Zero Window Ack Probe Length (request)", key: "zeroWinProbeAckLenReqSum" },
                { name: "Zero Window Ack Probe Length (response)", key: "zeroWinProbeAckLenResSum" },
                { name: "Keep Alive Count (request)", key: "keepAliveCntReqSum" },
                { name: "Keep Alive Count (response)", key: "keepAliveCntResSum" },
                { name: "Keep Alive Length (request)", key: "keepAliveLenReqSum" },
                { name: "Keep Alive Length (response)", key: "keepAliveLenResSum" },
                { name: "Window Update Count (request)", key: "winUpdateCntReqSum" },
                { name: "Window Update Count (response)", key: "winUpdateCntResSum" },
                { name: "Window Update Length (request)", key: "winUpdateLenReqSum" },
                { name: "Window Update Length (response)", key: "winUpdateLenResSum" }
            ]
        },
        {
            name: "Response Code",
            group: true,
            open: true,
            children: [
                { name: "2xx Count", key: "resCode2xxCntSum" },
                { name: "3xx Count", key: "resCode3xxCntSum" },
                { name: "401 Count", key: "resCode401CntSum" },
                { name: "404 Count", key: "resCode404CntSum" },
                { name: "4xx Count", key: "resCode4xxCntSum" },
                { name: "5xx Count", key: "resCode5xxCntSum" },
                { name: "Other Count", key: "resCodeOthCntSum" }
            ]
        }];
        let resourceItem = [trafficResource];

        if (selectKey === "http") {
            resourceItem = httpResource;
        } else if (selectKey === "ip") {
            resourceItem.push(ipResource);
        } else if (selectKey === "tcp") {
            resourceItem.push(ipResource);
            resourceItem.push(tcpResource);
        } else if (selectKey === "udp") {
            resourceItem.push(ipResource);
            resourceItem.push(udpResource);
        }

        return resourceItem;
    }

    const handleChange = (event, newValue) => {
        setMenuKey(newValue);
        setResourceItem(resourceOption(staticMenuKey[newValue]));
    };

    const handleCheckboxChange = (e, target) => {
        if (e.target.checked) {
            _.forEach(resourceItem[target].children, (obj) => {
                if (obj.key === e.target.id) {
                    setSelectResourceObj([...selectResourceObj, { name: obj.name, key: obj.key, tab: staticMenuKey[menuKey] }]);
                }
            });
        } else {
            const filterObj = _.filter(selectResourceObj, (obj) => {
                return obj.key !== e.target.id;
            });

            setSelectResourceObj(filterObj);
        }
    }

    const evtCollapseListGroup = (target) => {
        let cloneResourceItem = _.cloneDeep(resourceItem);
        cloneResourceItem[target].open = !cloneResourceItem[target].open;
        setResourceItem(cloneResourceItem);
    }

    const onSavePolicyValidation = () => {
        window.opener.onSuccess({ target: { id: paramKey, value: selectResourceObj } });
        window.close();
    }

    return (
        <Grid container>
            <Grid item sm={12} pb={1}>
                <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper' }}>
                    <Tabs value={menuKey} onChange={handleChange} variant="fullWidth" className="small">
                        <Tab label="HTTP" />
                        <Tab label="IP" />
                        <Tab label="TCP" />
                        <Tab label="UDP" />
                    </Tabs>
                </Box>

                <TabPanel listItem={resourceItem} selectItem={selectResourceObj} handleCheckboxChange={handleCheckboxChange} evtCollapseListGroup={evtCollapseListGroup} />
            </Grid>

            <Grid item sm={12} pb={1} textAlign="center">
                <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
            </Grid>
        </Grid>
    );
};

export default PopupSearchFormResourcesMulti;