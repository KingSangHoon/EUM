import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, TextField, FormControl, Tabs, Tab, Divider, FormControlLabel, Checkbox } from '@mui/material';

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';

/* function TabPanel(props) {
    const { children, handleSwitchChange, ...other } = props;

    return (
        <List sx={{ width: '75%', overflowY: "auto", bgcolor: 'background.paper' }} {...other}>
            {
                _.map(children, (obj) => (
                    <React.Fragment key={obj.key}>
                        <ListItem disablePadding>
                            <ListItemIcon onClick={(e) => handleSwitchChange(e, obj)}>
                                <Switch edge="start" checked={obj.active} disableRipple />
                            </ListItemIcon>
                            <ListItemText primary={obj.name} />
                        </ListItem>
                        <Divider variant="inset" component="li" />
                    </React.Fragment>
                ))
            }
        </List>
    );
}

TabPanel.propTypes = {
    children: PropTypes.array,
    handleSwitchChange: PropTypes.func
}; */

const PopupSetGridSettingReg = () => {
    const params = useParams();

    const [tabValue, setTabValue] = useState(0);

    const [state, setState] = useState({
        gridGroupName: "",
        desc: ""
    });

    const [allCheckbox, setAllCheckbox] = useState(false);

    const [gridState, setGridState] = useState([{
        key: "gridGroupUser",
        name: "설정 > 사용자 설정",
        items: [
            { name: "아이디", key: "loginId", active: true },
            { name: "이름", key: "username", active: true },
            { name: "이메일", key: "email", active: true },
            { name: "전화번호", key: "phoneNumber", active: true },
            { name: "권한", key: "role", active: true },
            { name: "등록 일자", key: "regDate", active: true },
            { name: "수정 일자", key: "modifyDate", active: true },
            { name: "계정 활성", key: "active", active: true }
        ]
    },
    {
        key: "gridGroupRealtimePage",
        name: "Protocol > HTTP > Pages",
        items: [
            { name: "Level", key: "level", active: true },
            { name: "Frame Arrival Time", key: "tsFrameArrival", active: true },
            { name: "Src Geo", key: "srcGeo", active: true },
            { name: 'Src Domestic', key: 'srcDomestic', active: true },
            { name: 'Src ISP', key: 'ispNameReq', active: true },
            { name: 'Src IDC', key: 'idcNameReq', active: false },
            { name: 'Src IP', key: 'srcIp', active: true },
            { name: 'Src Port', key: 'srcPort', active: true },
            { name: "Dst Geo", key: "dstGeo", active: true },
            { name: 'Dst Domestic', key: 'dstDomestic', active: true },
            { name: 'Dst ISP', key: 'ispNameRes', active: true },
            { name: 'Dst IDC', key: 'idcNameRes', active: false },
            { name: 'Dst IP', key: 'dstIp', active: true },
            { name: 'Dst Port', key: 'dstPort', active: true },
            { name: "Response Code", key: "httpResCode", active: true },
            { name: 'Host', key: 'httpHost', active: true },
            { name: 'Page', key: 'page', active: true },
            { name: 'User Agent', key: 'httpUserAgent', active: true },
            { name: "Page Response Time", key: "tsPage", active: true },
            { name: "Mbps", key: "mbps", active: true },
            { name: "URI Count", key: "uriCnt", active: true },
            { name: "Session Count", key: "sessionCnt", active: true },
            { name: "Connection Error Session Count", key: "connErrSessionCnt", active: true },
            { name: "Stopped Transaction Count", key: "stoppedTransactionCnt", active: true },
            { name: "Connection Error Packet Count", key: "connErrPktCnt", active: true },
            { name: "TCP Error", key: "tcpError", active: true },
            { name: "HTTP Error", key: "httpError", active: true },
            { name: "Src Mac", key: "srcMac", active: true },
            { name: "Dst Mac", key: "dstMac", active: true },
            { name: "HTTP Method", key: "httpMethod", active: false },
            { name: "HTTP Version", key: "httpVersion", active: false },
            { name: "HTTP Version(req)", key: "httpVersionReq", active: false },
            { name: "HTTP Version(res)", key: "httpVersionRes", active: false },
            { name: "HTTP Res Phrase", key: "httpResPhrase", active: false },
            { name: "HTTP Content Type", key: "httpContentType", active: false },
            { name: "HTTP Cookie", key: "httpCookie", active: false },
            { name: "HTTP Location", key: "httpLocation", active: false },
            { name: "HTTP URI", key: "httpUri", active: false },
            { name: "HTTP URI Split", key: "httpUriSplit", active: false },
            { name: "HTTP Referer", key: "httpReferer", active: false },
            { name: "HTTP Content Length", key: "httpContentLength", active: false },
            { name: "Response Code 2xx Count", key: "resCode2xxCnt", active: false },
            { name: "Response Code 3xx Count", key: "resCode3xxCnt", active: false },
            { name: "Response Code 401 Count", key: "resCode401Cnt", active: false },
            { name: "Response Code 404 Count", key: "resCode404Cnt", active: false },
            { name: "Response Code 4xx Count", key: "resCode4xxCnt", active: false },
            { name: "Response Code 5xx Count", key: "resCode5xxCnt", active: false },
            { name: "Response Code other Count", key: "resCodeOthCnt", active: false },
            { name: "Application", key: "applicationName", active: false },
            { name: "DPI Protocol App", key: "ndpiProtocolApp", active: false },
            { name: "DPI Protocol Master", key: "ndpiProtocolMaster", active: false },
            { name: "User Agent Software Name", key: "userAgentSoftwareName", active: false },
            { name: "User Agent Operating System Name", key: "userAgentOperatingSystemName", active: false },
            { name: "User Agent Operating Platform", key: "userAgentOperatingPlatform", active: false },
            { name: "User Agent Hardware Type", key: "userAgentHardwareType", active: false },
            { name: "Frame Landoff Time", key: "tsFrameLandoff", active: false },
            { name: "Page Begin Time", key: "tsPageBegin", active: false },
            { name: "Page End Time", key: "tsPageEnd", active: false },
            { name: "Page Request SYN Time", key: "tsPageReqSyn", active: false },
            { name: "Page Gap Time", key: "tsPageGap", active: false },
            { name: "Page Response Init Time", key: "tsPageResInit", active: false },
            { name: "Page Response Init Gap Time", key: "tsPageResInitGap", active: false },
            { name: "Page Response App Time", key: "tsPageResApp", active: false },
            { name: "Page Response App Gap Time", key: "tsPageResAppGap", active: false },
            { name: "Page Response Response Time", key: "tsPageRes", active: false },
            { name: "Page Response Gap Time", key: "tsPageResGap", active: false },
            { name: "Page Transfer Request Time", key: "tsPageTransferReq", active: false },
            { name: "Page Transfer Request Gap Time", key: "tsPageTransferReqGap", active: false },
            { name: "Page Transfer Response Time", key: "tsPageTransferRes", active: false },
            { name: "Page Transfer Response Gap Time", key: "tsPageTransferResGap", active: false },
            { name: "Page Connection RTT (request)", key: "tsPageRttConnSumReq", active: false },
            { name: "Page Connection RTT (response)", key: "tsPageRttConnSumRes", active: false },
            { name: "Page ACK RTT (request)", key: "tsPageRttAckSumReq", active: false },
            { name: "Page ACK RTT (response)", key: "tsPageRttAckSumRes", active: false },
            { name: "Page Request Making Time", key: "tsPageReqMakingSum", active: false },
            { name: "Page Connection RTT Count (request)", key: "pageRttConnCntReq", active: false },
            { name: "Page Connection RTT Count (response)", key: "pageRttConnCntRes", active: false },
            { name: "Page ACK RTT Count (request)", key: "pageRttAckCntReq", active: false },
            { name: "Page ACK RTT Count (response)", key: "pageRttAckCntRes", active: false },
            { name: "Page Request Making Count", key: "pageReqMakingCnt", active: false },
            { name: "HTTP Length (request)", key: "pageHttpLenReq", active: false },
            { name: "HTTP Length (response)", key: "pageHttpLenRes", active: false },
            { name: "HTTP Count (request)", key: "pageHttpCntReq", active: false },
            { name: "HTTP Count (response)", key: "pageHttpCntRes", active: false },
            { name: "Packet Length (request)", key: "pagePktLenReq", active: false },
            { name: "Packet Length (response)", key: "pagePktLenRes", active: false },
            { name: "Packet Count (request)", key: "pagePktCntReq", active: false },
            { name: "Packet Count (response)", key: "pagePktCntRes", active: false },
            { name: "TCP Length (request)", key: "pageTcpLenReq", active: false },
            { name: "TCP Length (response)", key: "pageTcpLenRes", active: false },
            { name: "TCP Count (request)", key: "pageTcpCntReq", active: false },
            { name: "TCP Count (response)", key: "pageTcpCntRes", active: false },
            { name: "Connection Error Session Length (request)", key: "reqConnErrSessionLen", active: false },
            { name: "Connection Error Session Length (response)", key: "resConnErrSessionLen", active: false },
            { name: "Page RTT Connection Min (request)", key: "tsPageRttConnReqMin", active: false },
            { name: "Page RTT Connection Min (response)", key: "tsPageRttConnResMin", active: false },
            { name: "Page RTT Connection Max (request)", key: "tsPageRttConnReqMax", active: false },
            { name: "Page RTT Connection Max (response)", key: "tsPageRttConnResMax", active: false },
            { name: "Page ACK RTT Min (request)", key: "tsPageRttAckReqMin", active: false },
            { name: "Page ACK RTT Min (response)", key: "tsPageRttAckResMin", active: false },
            { name: "Page ACK RTT Max (request)", key: "tsPageRttAckReqMax", active: false },
            { name: "Page ACK RTT Max (response)", key: "tsPageRttAckResMax", active: false },
            { name: "Page Request Making Min", key: "tsPageReqMakingMin", active: false },
            { name: "Page Request Making Max", key: "tsPageReqMakingMax", active: false },
            { name: "Page RTO (request)", key: "tsPageRtoSumReq", active: false },
            { name: "Page RTO (response)", key: "tsPageRtoSumRes", active: false },
            { name: "Page RTO Count (request)", key: "tsPageRtoCntReq", active: false },
            { name: "Page RTO Count (response)", key: "tsPageRtoCntRes", active: false },
            { name: "Retransmission Count (request)", key: "retransmissionCntReq", active: false },
            { name: "Retransmission Count (response)", key: "retransmissionCntRes", active: false },
            { name: "Retransmission Length (request)", key: "retransmissionLenReq", active: false },
            { name: "Retransmission Length (response)", key: "retransmissionLenRes", active: false },
            { name: "Fast Retransmission Count (request)", key: "fastRetransmissionCntReq", active: false },
            { name: "Fast Retransmission Count (response)", key: "fastRetransmissionCntRes", active: false },
            { name: "Fast Retransmission Length (request)", key: "fastRetransmissionLenReq", active: false },
            { name: "Fast Retransmission Length (response)", key: "fastRetransmissionLenRes", active: false },
            { name: "Out of Order Count (request)", key: "outOfOrderCntReq", active: false },
            { name: "Out of Order Count (response)", key: "outOfOrderCntRes", active: false },
            { name: "Out of Order Length (request)", key: "outOfOrderLenReq", active: false },
            { name: "Out of Order Length (response)", key: "outOfOrderLenRes", active: false },
            { name: "Lost Segment Count (request)", key: "lostSegCntReq", active: false },
            { name: "Lost Segment Count (response)", key: "lostSegCntRes", active: false },
            { name: "Lost Segment Length (request)", key: "lostSegLenReq", active: false },
            { name: "Lost Segment Length (response)", key: "lostSegLenRes", active: false },
            { name: "ACK Lost Count (request)", key: "ackLostCntReq", active: false },
            { name: "ACK Lost Count (response)", key: "ackLostCntRes", active: false },
            { name: "ACK Lost Length (request)", key: "ackLostLenReq", active: false },
            { name: "ACK Lost Length (response)", key: "ackLostLenRes", active: false },
            { name: "Window Update Count (request)", key: "winUpdateCntReq", active: false },
            { name: "Window Update Count (response)", key: "winUpdateCntRes", active: false },
            { name: "Window Update Length (request)", key: "winUpdateLenReq", active: false },
            { name: "Window Update Length (response)", key: "winUpdateLenRes", active: false },
            { name: "Dup ACK Count (request)", key: "dupAckCntReq", active: false },
            { name: "Dup ACK Count (response)", key: "dupAckCntRes", active: false },
            { name: "Dup ACK Length (request)", key: "dupAckLenReq", active: false },
            { name: "Dup ACK Length (response)", key: "dupAckLenRes", active: false },
            { name: "Zero Window Count (request)", key: "zeroWinCntReq", active: false },
            { name: "Zero Window Count (response)", key: "zeroWinCntRes", active: false },
            { name: "Zero Window Length (request)", key: "zeroWinLenReq", active: false },
            { name: "Zero Window Length (response)", key: "zeroWinLenRes", active: false },
            { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReq", active: false },
            { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntRes", active: false },
            { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReq", active: false },
            { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenRes", active: false },
            { name: "Overlap Count (request)", key: "overlapCntReq", active: false },
            { name: "Overlap Count (response)", key: "overlapCntRes", active: false },
            { name: "Overlap Length (request)", key: "overlapLenReq", active: false },
            { name: "Overlap Length (response)", key: "overlapLenRes", active: false },
            { name: "Overlap Attack Count (request)", key: "overlapAttackCntReq", active: false },
            { name: "Overlap Attack Count (response)", key: "overlapAttackCntRes", active: false },
            { name: "Overlap Attack Length (request)", key: "overlapAttackLenReq", active: false },
            { name: "Overlap Attack Length (response)", key: "overlapAttackLenRes", active: false },
            { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReq", active: false },
            { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntRes", active: false },
            { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReq", active: false },
            { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenRes", active: false },
            { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReq", active: false },
            { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntRes", active: false },
            { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReq", active: false },
            { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenRes", active: false },
            { name: "Keep Alive Count (request)", key: "keepAliveCntReq", active: false },
            { name: "Keep Alive Count (response)", key: "keepAliveCntRes", active: false },
            { name: "Keep Alive Length (request)", key: "keepAliveLenReq", active: false },
            { name: "Keep Alive Length (response)", key: "keepAliveLenRes", active: false },
            { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReq", active: false },
            { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntRes", active: false },
            { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReq", active: false },
            { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenRes", active: false },
            { name: "Src As", key: "asNameReq", active: false },
            { name: "Dst As", key: "asNameRes", active: false }
        ]
    },
    {
        key: "gridGroupRealtimeUri",
        name: "Protocol > HTTP > URI",
        items: [
            { name: "Level", key: "level", active: true },
            { name: "Frame Arrival Time", key: "tsFrameArrival", active: true },
            { name: "Src Geo", key: "srcGeo", active: true },
            { name: 'Src Domestic', key: 'srcDomestic', active: true },
            { name: 'Src ISP', key: 'ispNameReq', active: true },
            { name: 'Src IDC', key: 'idcNameReq', active: false },
            { name: 'Src IP', key: 'srcIp', active: true },
            { name: 'Src Port', key: 'srcPort', active: true },
            { name: "Dst Geo", key: "dstGeo", active: true },
            { name: 'Dst Domestic', key: 'dstDomestic', active: true },
            { name: 'Dst ISP', key: 'ispNameRes', active: true },
            { name: 'Dst IDC', key: 'idcNameRes', active: false },
            { name: 'Dst IP', key: 'dstIp', active: true },
            { name: 'Dst Port', key: 'dstPort', active: true },
            { name: "Response Code", key: "httpResCode", active: true },
            { name: 'Host', key: 'httpHost', active: true },
            { name: 'URI', key: 'page', active: true },
            { name: 'User Agent', key: 'httpUserAgent', active: true },
            { name: "Response Time", key: "tsRsqDelayResponse", active: true },
            { name: 'Mbps', key: 'mbps', active: true },
            { name: 'TCP Error', key: 'tcpError', active: true },
            { name: "Stopped Transaction", key: "isStoppedTransaction", active: true },
            { name: "Src Mac", key: "srcMac", active: true },
            { name: "Dst Mac", key: "dstMac", active: true },
            { name: "HTTP Method", key: "httpMethod", active: false },
            { name: "HTTP Version", key: "httpVersion", active: false },
            { name: "HTTP Version (request)", key: "httpVersionReq", active: false },
            { name: "HTTP Version (response)", key: "httpVersionRes", active: false },
            { name: "HTTP Content Length", key: "httpContentLength", active: false },
            { name: "HTTP Res Phrase", key: "httpResPhrase", active: false },
            { name: "HTTP Content Type", key: "httpContentType", active: false },
            { name: "HTTP Cookie", key: "httpCookie", active: false },
            { name: "HTTP Location", key: "httpLocation", active: false },
            { name: "HTTP URI", key: "httpUri", active: false },
            { name: "HTTP URI Split", key: "httpUriSplit", active: false },
            { name: "HTTP Referer", key: "httpReferer", active: false },
            { name: "Application", key: "applicationName", active: false },
            { name: "DPI Protocol App", key: "ndpiProtocolApp", active: false },
            { name: "DPI Protocol Master", key: "ndpiProtocolMaster", active: false },
            { name: "User Agent Software Name", key: "userAgentSoftwareName", active: false },
            { name: "User Agent Software Version", key: "userAgentSoftwareVersion", active: false },
            { name: "User Agent Software Version Full", key: "userAgentSoftwareVersionFull", active: false },
            { name: "User Agent Operating System Name", key: "userAgentOperatingSystemName", active: false },
            { name: "User Agent Operating System Version", key: "userAgentOperatingSystemVersion", active: false },
            { name: "User Agent Operating System Version Full", key: "userAgentOperatingSystemVersionFull", active: false },
            { name: "User Agent Operating System Flavour", key: "userAgentOperatingSystemFlavour", active: false },
            { name: "User Agent Operating Platform", key: "userAgentOperatingPlatform", active: false },
            { name: "User Agent Operating Platform Code", key: "userAgentOperatingPlatformCode", active: false },
            { name: "User Agent Operating Platform Code Name", key: "userAgentOperatingPlatformCodeName", active: false },
            { name: "User Agent Operating Platform Vendor Name", key: "userAgentOperatingPlatformVendorName", active: false },
            { name: "User Agent Software Type", key: "userAgentSoftwareType", active: false },
            { name: "User Agent Software Subtype", key: "userAgentSoftwareSubType", active: false },
            { name: "User Agent Hardware Type", key: "userAgentHardwareType", active: false },
            { name: "User Agent Hardware Subtype", key: "userAgentHardwareSubType", active: false },
            { name: "User Agent Layout Engine Name", key: "userAgentLayoutEngineName", active: false },
            { name: "Frame Landoff Time", key: "tsFrameLandoff", active: false },
            { name: "Frame Arrival Page Time", key: "tsFrameArrivalPage", active: false },
            { name: "Frame Landoff Page Time", key: "tsFrameLandoffPage", active: false },
            { name: "Sequence First (request)", key: "reqSeqFirst", active: false },
            { name: "ACK First (request)", key: "reqAckFirst", active: false },
            { name: "Sequence Last (request)", key: "reqSeqLast", active: false },
            { name: "ACK Last (request)", key: "reqAckLast", active: false },
            { name: "Sequence First (response)", key: "resSeqFirst", active: false },
            { name: "ACK First (response)", key: "resAckFirst", active: false },
            { name: "Pkt First Time (request)", key: "tsReqPktFirst", active: false },
            { name: "Pkt Push Time (request)", key: "tsReqPktPush", active: false },
            { name: "Pkt Last Time (request)", key: "tsReqPktLast", active: false },
            { name: "Pkt First Time (response)", key: "tsResPktFirst", active: false },
            { name: "Pkt Push Time (response)", key: "tsResPktPush", active: false },
            { name: "Pkt Last Time (response)", key: "tsResPktLast", active: false },
            { name: "Delay Transfer (request)", key: "tsReqDelayTransfer", active: false },
            { name: "Delay Transfer (response)", key: "tsResDelayTransfer", active: false },
            { name: "Response Process First", key: "tsResProcessFirst", active: false },
            { name: "Response Process Push", key: "tsResProcessPush", active: false },
            { name: "Response Process Last", key: "tsResProcessLast", active: false },
            { name: "Rtt SYN Time", key: "tsRttSyn", active: false },
            { name: "RTT SYN ACK Time", key: "tsRttSynAck", active: false },
            { name: "RTT First ACK Time", key: "tsRttFirstAck", active: false },
            { name: "RTT Request ACK Time", key: "tsRttReqAck", active: false },
            { name: "RTT ACK Request ACK Time", key: "tsRttAckReqAck", active: false },
            { name: "RTT Response ACK Time", key: "tsRttResAck", active: false },
            { name: "RTT ACK Response ACK Time", key: "tsRttAckResAck", active: false },
            { name: "RTT Connection (request)", key: "tsRttConnReq", active: false },
            { name: "RTT Connection (response)", key: "tsRttConnRes", active: false },
            { name: "RTT First ACK (request)", key: "tsRttFirstAckReq", active: false },
            { name: "RTT First ACK (response)", key: "tsRttFirstAckRes", active: false },
            { name: "Connection Delay (request)", key: "tsConnDelayReq", active: false },
            { name: "Connection Delay (response)", key: "tsConnDelayRes", active: false },
            { name: "First ACK Delay (request)", key: "tsFirstAckDelayReq", active: false },
            { name: "First ACK Delay (response)", key: "tsFirstAckDelayRes", active: false },
            { name: "Request Making", key: "tsReqMaking", active: false },
            { name: "Packet Length (request)", key: "pktLenReq", active: false },
            { name: "Packet Length (response)", key: "pktLenRes", active: false },
            { name: "Packet Count (request)", key: "pktCntReq", active: false },
            { name: "Packet Count (response)", key: "pktCntRes", active: false },
            { name: "HTTP Length (request)", key: "httpLenReq", active: false },
            { name: "HTTP Length (response)", key: "httpLenRes", active: false },
            { name: "HTTP Count (request)", key: "httpCntReq", active: false },
            { name: "HTTP Count (response)", key: "httpCntRes", active: false },
            { name: "TCP Length (request)", key: "tcpLenReq", active: false },
            { name: "TCP Length (response)", key: "tcpLenRes", active: false },
            { name: "TCP Count (request)", key: "tcpCntReq", active: false },
            { name: "TCP Count (response)", key: "tcpCntRes", active: false },
            { name: "ACK RTT Sum (request)", key: "ackRttSumReq", active: false },
            { name: "ACK RTT Sum (response)", key: "ackRttSumRes", active: false },
            { name: "ACK RTO Sum (request)", key: "ackRtoSumReq", active: false },
            { name: "ACK RTO Sum (response)", key: "ackRtoSumRes", active: false },
            { name: "ACK RTT Count (request)", key: "ackRttCntReq", active: false },
            { name: "ACK RTT Count (response)", key: "ackRttCntRes", active: false },
            { name: "ACK RTO Count (request)", key: "ackRtoCntReq", active: false },
            { name: "ACK RTO Count (response)", key: "ackRtoCntRes", active: false },
            { name: "ACK Delay (request)", key: "ackDelayReq", active: false },
            { name: "ACK Delay (response)", key: "ackDelayRes", active: false },
            { name: "ACK RTT Min (request)", key: "ackRttMinReq", active: false },
            { name: "ACK RTT Min (response)", key: "ackRttMinRes", active: false },
            { name: "ACK RTT Max (request)", key: "ackRttMaxReq", active: false },
            { name: "ACK RTT Max (response)", key: "ackRttMaxRes", active: false },
            { name: "Retransmission Count (request)", key: "retransmissionCntReq", active: false },
            { name: "Retransmission Count (response)", key: "retransmissionCntRes", active: false },
            { name: "Retransmission Length (request)", key: "retransmissionLenReq", active: false },
            { name: "Retransmission Length (response)", key: "retransmissionLenRes", active: false },
            { name: "Fast Retransmission Count (request)", key: "fastRetransmissionCntReq", active: false },
            { name: "Fast Retransmission Count (response)", key: "fastRetransmissionCntRes", active: false },
            { name: "Fast Retransmission Length (request)", key: "fastRetransmissionLenReq", active: false },
            { name: "Fast Retransmission Length (response)", key: "fastRetransmissionLenRes", active: false },
            { name: "Out of Order Count (request)", key: "outOfOrderCntReq", active: false },
            { name: "Out of Order Count (response)", key: "outOfOrderCntRes", active: false },
            { name: "Out of Order Length (request)", key: "outOfOrderLenReq", active: false },
            { name: "Out of Order Length (response)", key: "outOfOrderLenRes", active: false },
            { name: "Lost Segment Count (request)", key: "lostSegCntReq", active: false },
            { name: "Lost Segment Count (response)", key: "lostSegCntRes", active: false },
            { name: "Lost Segment Length (request)", key: "lostSegLenReq", active: false },
            { name: "Lost Segment Length (response)", key: "lostSegLenRes", active: false },
            { name: "Ack Lost Count (request)", key: "ackLostCntReq", active: false },
            { name: "Ack Lost Count (response)", key: "ackLostCntRes", active: false },
            { name: "Ack Lost Length (request)", key: "ackLostLenReq", active: false },
            { name: "Ack Lost Length (response)", key: "ackLostLenRes", active: false },
            { name: "Window Update Count (request)", key: "winUpdateCntReq", active: false },
            { name: "Window Update Count (response)", key: "winUpdateCntRes", active: false },
            { name: "Window Update Length (request)", key: "winUpdateLenReq", active: false },
            { name: "Window Update Length (response)", key: "winUpdateLenRes", active: false },
            { name: "Dup Ack Count (request)", key: "dupAckCntReq", active: false },
            { name: "Dup Ack Count (response)", key: "dupAckCntRes", active: false },
            { name: "Dup Ack Length (request)", key: "dupAckLenReq", active: false },
            { name: "Dup Ack Length (response)", key: "dupAckLenRes", active: false },
            { name: "Zero Window Count (request)", key: "zeroWinCntReq", active: false },
            { name: "Zero Window Count (response)", key: "zeroWinCntRes", active: false },
            { name: "Zero Window Length (request)", key: "zeroWinLenReq", active: false },
            { name: "Zero Window Length (response)", key: "zeroWinLenRes", active: false },
            { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReq", active: false },
            { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntRes", active: false },
            { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReq", active: false },
            { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenRes", active: false },
            { name: "Overlap Count (request)", key: "overlapCntReq", active: false },
            { name: "Overlap Count (response)", key: "overlapCntRes", active: false },
            { name: "Overlap Length (request)", key: "overlapLenReq", active: false },
            { name: "Overlap Length (response)", key: "overlapLenRes", active: false },
            { name: "Overlap Attack Count (request)", key: "overlapAttackCntReq", active: false },
            { name: "Overlap Attack Count (response)", key: "overlapAttackCntRes", active: false },
            { name: "Overlap Attack Length (request)", key: "overlapAttackLenReq", active: false },
            { name: "Overlap Attack Length (response)", key: "overlapAttackLenRes", active: false },
            { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReq", active: false },
            { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntRes", active: false },
            { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReq", active: false },
            { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenRes", active: false },
            { name: "Zero Window Ack Probe Count (request)", key: "zeroWinProbeAckCntReq", active: false },
            { name: "Zero Window Ack Probe Count (response)", key: "zeroWinProbeAckCntRes", active: false },
            { name: "Zero Window Ack Probe Length (request)", key: "zeroWinProbeAckLenReq", active: false },
            { name: "Zero Window Ack Probe Length (response)", key: "zeroWinProbeAckLenRes", active: false },
            { name: "Keep Alive Count (request)", key: "keepAliveCntReq", active: false },
            { name: "Keep Alive Count (response)", key: "keepAliveCntRes", active: false },
            { name: "Keep Alive Length (request)", key: "keepAliveLenReq", active: false },
            { name: "Keep Alive Length (response)", key: "keepAliveLenRes", active: false },
            { name: "Keep Alive Ack Count (request)", key: "keepAliveAckCntReq", active: false },
            { name: "Keep Alive Ack Count (response)", key: "keepAliveAckCntRes", active: false },
            { name: "Keep Alive Ack Length (request)", key: "keepAliveAckLenReq", active: false },
            { name: "Keep Alive Ack Length (response)", key: "keepAliveAckLenRes", active: false },
            { name: "Src As", key: "asNameReq", active: false },
            { name: "Dst As", key: "asNameRes", active: false }
        ]
    }]);

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
        axiosConf.get("/api/setting/grid/findGroup/" + params.id).then(res => {
            let cloneGridState = _.cloneDeep(gridState);

            _.forEach(cloneGridState, (obj) => {
                if (res.data[obj.key]) {
                    const targetData = res.data[obj.key];

                    _.forEach(obj.items, (itemObj) => {
                        itemObj.active = targetData[itemObj.key] ? targetData[itemObj.key] : false;
                    });
                }
            });

            setState({
                gridGroupName: res.data.group.gridGroupName || "",
                desc: res.data.group.description || ""
            });

            setGridState(cloneGridState);
        });
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleTabChange = (event, newValue) => {
        setTabValue(newValue);
        setAllCheckbox(false);
    }

    const handleAllCheckbox = (e) => {
        let cloneGridItem = _.cloneDeep(gridState);

        _.forEach(cloneGridItem[tabValue].items, (obj) => {
            obj.active = e.target.checked;
        });

        setAllCheckbox(e.target.checked);
        setGridState(cloneGridItem);
    }

    const handleCheckboxChange = (e) => {
        const splitId = e.target.id.split("_");
        let cloneGridItem = _.cloneDeep(gridState);

        const filterData = _.filter(cloneGridItem[tabValue].items, (obj) => {
            return obj.key === splitId[1];
        });

        if (filterData.length > 0) {
            filterData[0].active = e.target.checked;
            setGridState(cloneGridItem);
        }
    }

    const onSavePolicyValidation = () => {
        if (state.gridGroupName === "") {
            alert("그리드 정책 명을 입력해주세요.");
            return;
        }

        let requestData = {
            gridGroupName: state.gridGroupName,
            desc: state.desc
        };

        _.forEach(gridState, (obj) => {
            requestData[obj.key] = {};

            _.forEach(obj.items, (itemObj) => {
                requestData[obj.key][itemObj.key] = itemObj.active;
            });
        });

        applyPolicySave(requestData);
    }

    const applyPolicySave = (requestData) => {
        let requestUrl = "/api/setting/grid";

        if (params.id === "0") {
            requestUrl += "/insertGroup";
        } else {
            requestData.gridGroupId = parseFloat(params.id);
            requestUrl += "/updateGroup";
        }

        axiosConf.post(requestUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    return (
        <Card>
            <CardHeader title="그리드 설정"></CardHeader>
            <CardContent>
                <FormControl fullWidth>
                    <TextField
                        name="gridGroupName"
                        placeholder="그룹 명을 입력해주세요."
                        size="small"
                        value={state.gridGroupName}
                        onChange={handleKeyPress}
                    />
                </FormControl>

                <FormControl fullWidth>
                    <TextField
                        sx={{ mt: .5 }}
                        multiline
                        rows={5}
                        name="desc"
                        placeholder="설명"
                        size="small"
                        value={state.desc}
                        onChange={handleKeyPress}
                    />
                </FormControl>

                <Box sx={{ flexGrow: 1, bgcolor: 'background.paper', display: 'flex', height: 500 }}>
                    <Tabs orientation="vertical" variant="scrollable" value={tabValue} onChange={handleTabChange}
                        sx={{ '& button': { alignItems: "start", textAlign: "left" }, borderRight: 1, borderColor: 'divider', width: "25%" }} >
                        {
                            _.map(gridState, (obj, i) => (
                                <Tab label={obj.name} key={i} />
                            ))
                        }
                    </Tabs>
                    {/* <TabPanel children={gridState[tabValue].items} handleSwitchChange={handleSwitchChange} /> */}

                    <FormControl sx={{ width: "75%", pl: 1 }}>
                        <FormControlLabel control={<Checkbox id={"field_all"} checked={allCheckbox} onChange={handleAllCheckbox} />} label="전체" />
                        <Divider />

                        <Box sx={{ overflowY: "auto" }}>
                            {
                                _.map(gridState[tabValue].items, (obj, i) => (
                                    <FormControl fullWidth key={i}>
                                        <FormControlLabel control={<Checkbox id={"field_" + obj.key} checked={obj.active} onChange={handleCheckboxChange} />} label={obj.name} />
                                    </FormControl>
                                ))
                            }
                        </Box>
                    </FormControl>
                </Box>

                <Divider sx={{ mb: 1 }} />

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>
        </Card>
    );
};

export default PopupSetGridSettingReg;