import axiosConf from '../axios';
import _ from 'lodash';

/**
 * ag-grid 셀 높이
 * 
 */
export const gridHeight = 23;

/**
 * ag-grid api object
 * 
 * @return
 */
export const gridApiObj = {};

/**
* ag-grid 무한 스크롤 page count
* 
* @return
*/
export const scrollGridCnt = 100;

/**
 * moment 날짜 함수
 * 
 */
export const moment = require('moment');

/**
 * Modal Style
 * 
 */
export const modalStyles = {
  maxWidth: "1000px",
  margin: "1.75rem auto",
  transform: "none"
};

/**
 * Grid Setting Menu Style
 * 
 */
export const gridSetMenuStyles = {
  maxHeight: 400,
  width: "50ch",
  paddingBottom: ".5rem",
  border: "1px solid #babfc7"
};

/**
 * Resource - Page
 * 
 */
export const pageResources = [{
  name: "Page",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Page Response Time", key: "tsPage" },
    { name: "Page Gap Time", key: "tsPageGap" },
    { name: "Page Response Init Time", key: "tsPageResInit" },
    { name: "Page Response Init Gap Time", key: "tsPageResInitGap" },
    { name: "Page Response App Time", key: "tsPageResApp" },
    { name: "Page Response App Gap Time", key: "tsPageResAppGap" },
    { name: "Page Response Response Time", key: "tsPageRes" },
    { name: "Page Response Gap Time", key: "tsPageResGap" },
    { name: "Page Transfer Request Time", key: "tsPageTransferReq" },
    { name: "Page Transfer Request Gap Time", key: "tsPageTransferReqGap" },
    { name: "Page Transfer Response Time", key: "tsPageTransferRes" },
    { name: "Page Transfer Response Gap Time", key: "tsPageTransferResGap" },
    { name: "Page Request Making Time", key: "tsPageReqMakingSum" },
    { name: "Page Request Making Count", key: "pageReqMakingCnt" },
    //{ name: "Stopped Transaction Count", key: "stoppedTransactionCnt" }
  ]
},
{
  name: "RTT",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Page Connection RTT (request)", key: "tsPageRttConnSumReq" },
    { name: "Page Connection RTT (response)", key: "tsPageRttConnSumRes" },
    { name: "Page ACK RTT (request)", key: "tsPageRttAckSumReq" },
    { name: "Page ACK RTT (response)", key: "tsPageRttAckSumRes" },
    { name: "Page Connection RTT Count (request)", key: "pageRttConnCntReq" },
    { name: "Page Connection RTT Count (response)", key: "pageRttConnCntRes" },
    { name: "Page ACK RTT Count (request)", key: "pageRttAckCntReq" },
    { name: "Page ACK RTT Count (response)", key: "pageRttAckCntRes" },
    { name: "Page ACK RTT Min (request)", key: "tsPageRttAckReqMin" },
    { name: "Page ACK RTT Min (response)", key: "tsPageRttAckResMin" },
    { name: "Page ACK RTT Max (request)", key: "tsPageRttAckReqMax" },
    { name: "Page ACK RTT Max (response)", key: "tsPageRttAckResMax" }
  ]
},
{
  name: "RTO",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Page RTO (request)", key: "tsPageRtoSumReq" },
    { name: "Page RTO (response)", key: "tsPageRtoSumRes" },
    { name: "Page RTO Count (request)", key: "tsPageRtoCntReq" },
    { name: "Page RTO Count (response)", key: "tsPageRtoCntRes" }
  ]
},
{
  name: "HTTP",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "HTTP Length (request)", key: "pageHttpLenReq" },
    { name: "HTTP Length (response)", key: "pageHttpLenRes" },
    { name: "HTTP Count (request)", key: "pageHttpCntReq" },
    { name: "HTTP Count (response)", key: "pageHttpCntRes" }
  ]
},
{
  name: "Packet",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Packet Length (request)", key: "pagePktLenReq" },
    { name: "Packet Length (response)", key: "pagePktLenRes" },
    { name: "Packet Count (request)", key: "pagePktCntReq" },
    { name: "Packet Count (response)", key: "pagePktCntRes" },
    { name: "Connection Error Packet Count", key: "connErrPktCnt" }
  ]
},
{
  name: "Session",
  group: true,
  open: true,
  checked: false,
  children: [
    //{ name: "Session Count", key: "pageSessionCnt" },
    //{ name: "Connection Error Session Count", key: "connErrSessionCnt" },
    { name: "Connection Error Session Length (request)", key: "reqConnErrSessionLen" },
    { name: "Connection Error Session Length (response)", key: "resConnErrSessionLen" }
  ]
},
{
  name: "TCP",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TCP Length (request)", key: "pageTcpLenReq" },
    { name: "TCP Length (response)", key: "pageTcpLenRes" },
    { name: "TCP Count (request)", key: "pageTcpCntReq" },
    { name: "TCP Count (response)", key: "pageTcpCntRes" }
  ]
},
{
  name: "TCP Error",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Retransmission Count (request)", key: "retransmissionCntReq" },
    { name: "Retransmission Count (response)", key: "retransmissionCntRes" },
    { name: "Retransmission Length (request)", key: "retransmissionLenReq" },
    { name: "Retransmission Length (response)", key: "retransmissionLenRes" },
    { name: "Fast Retransmission Count (request)", key: "fastRetransmissionCntReq" },
    { name: "Fast Retransmission Count (response)", key: "fastRetransmissionCntRes" },
    { name: "Fast Retransmission Length (request)", key: "fastRetransmissionLenReq" },
    { name: "Fast Retransmission Length (response)", key: "fastRetransmissionLenRes" },
    { name: "Out of Order Count (request)", key: "outOfOrderCntReq" },
    { name: "Out of Order Count (response)", key: "outOfOrderCntRes" },
    { name: "Out of Order Length (request)", key: "outOfOrderLenReq" },
    { name: "Out of Order Length (response)", key: "outOfOrderLenRes" },
    { name: "Lost Segment Count (request)", key: "lostSegCntReq" },
    { name: "Lost Segment Count (response)", key: "lostSegCntRes" },
    { name: "Lost Segment Length (request)", key: "lostSegLenReq" },
    { name: "Lost Segment Length (response)", key: "lostSegLenRes" },
    { name: "ACK Lost Count (request)", key: "ackLostCntReq" },
    { name: "ACK Lost Count (response)", key: "ackLostCntRes" },
    { name: "ACK Lost Length (request)", key: "ackLostLenReq" },
    { name: "ACK Lost Length (response)", key: "ackLostLenRes" },
    { name: "Dup ACK Count (request)", key: "dupAckCntReq" },
    { name: "Dup ACK Count (response)", key: "dupAckCntRes" },
    { name: "Dup ACK Length (request)", key: "dupAckLenReq" },
    { name: "Dup ACK Length (response)", key: "dupAckLenRes" },
    { name: "Zero Window Count (request)", key: "zeroWinCntReq" },
    { name: "Zero Window Count (response)", key: "zeroWinCntRes" },
    { name: "Zero Window Length (request)", key: "zeroWinLenReq" },
    { name: "Zero Window Length (response)", key: "zeroWinLenRes" },
    { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReq" },
    { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntRes" },
    { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReq" },
    { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenRes" },
    { name: "Overlap Count (request)", key: "overlapCntReq" },
    { name: "Overlap Count (response)", key: "overlapCntRes" },
    { name: "Overlap Length (request)", key: "overlapLenReq" },
    { name: "Overlap Length (response)", key: "overlapLenRes" },
    { name: "Overlap Attack Count (request)", key: "overlapAttackCntReq" },
    { name: "Overlap Attack Count (response)", key: "overlapAttackCntRes" },
    { name: "Overlap Attack Length (request)", key: "overlapAttackLenReq" },
    { name: "Overlap Attack Length (response)", key: "overlapAttackLenRes" },
    { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReq" },
    { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntRes" },
    { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReq" },
    { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenRes" },
    { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReq" },
    { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntRes" },
    { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReq" },
    { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenRes" },
    { name: "Keep Alive Count (request)", key: "keepAliveCntReq" },
    { name: "Keep Alive Count (response)", key: "keepAliveCntRes" },
    { name: "Keep Alive Length (request)", key: "keepAliveLenReq" },
    { name: "Keep Alive Length (response)", key: "keepAliveLenRes" },
    { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReq" },
    { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntRes" },
    { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReq" },
    { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenRes" },
    { name: "Window Update Count (request)", key: "winUpdateCntReq" },
    { name: "Window Update Count (response)", key: "winUpdateCntRes" },
    { name: "Window Update Length (request)", key: "winUpdateLenReq" },
    { name: "Window Update Length (response)", key: "winUpdateLenRes" }
  ]
},
{
  name: "Response Code",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "2xx Count", key: "resCode2xxCnt" },
    { name: "3xx Count", key: "resCode3xxCnt" },
    { name: "401 Count", key: "resCode401Cnt" },
    { name: "404 Count", key: "resCode404Cnt" },
    { name: "4xx Count", key: "resCode4xxCnt" },
    { name: "5xx Count", key: "resCode5xxCnt" },
    { name: "Other Count", key: "resCodeOthCnt" }
  ]
}];

/**
 * Resource - URI
 * 
 */
export const uriResources = [{
  name: "URI",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Response Time", key: "tsRsqDelayResponse" },
    { name: "Delay Transfer (request)", key: "tsReqDelayTransfer" },
    { name: "Delay Transfer (response)", key: "tsResDelayTransfer" },
    { name: "Response Process First", key: "tsResProcessFirst" },
    { name: "Response Process Push", key: "tsResProcessPush" },
    { name: "Response Process Last", key: "tsResProcessLast" },
    { name: "First ACK Delay (request)", key: "tsFirstAckDelayReq" },
    { name: "First ACK Delay (response)", key: "tsFirstAckDelayRes" },
    { name: "Connection Delay (request)", key: "tsConnDelayReq" },
    { name: "Connection Delay (response)", key: "tsConnDelayRes" },
    { name: "Request Making Time", key: "tsReqMaking" },
    { name: "ACK Delay (request)", key: "ackDelayReq" },
    { name: "ACK Delay (response)", key: "ackDelayRes" }
  ]
},
{
  name: "RTT",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "RTT SYN", key: "tsRttSyn" },
    { name: "RTT SYN ACK", key: "tsRttSynAck" },
    { name: "RTT First ACK", key: "tsRttFirstAck" },
    { name: "RTT ACK (request)", key: "tsRttReqAck" },
    { name: "RTT ACK (response)", key: "tsRttResAck" },
    { name: "RTT ACK Request ACK", key: "tsRttAckReqAck" },
    { name: "RTT ACK Response ACK", key: "tsRttAckResAck" },
    { name: "RTT First ACK (request)", key: "tsRttFirstAckReq" },
    { name: "RTT First ACK (response)", key: "tsRttFirstAckRes" },
    { name: "Connection RTT (request)", key: "tsRttConnReq" },
    { name: "Connection RTT (response)", key: "tsRttConnRes" },
    { name: "ACK RTT Sum (request)", key: "ackRttSumReq" },
    { name: "ACK RTT Sum (response)", key: "ackRttSumRes" },
    { name: "ACK RTT Count (request)", key: "ackRttCntReq" },
    { name: "ACK RTT Count (response)", key: "ackRttCntRes" },
    { name: "ACK RTT Min (request)", key: "ackRttMinReq" },
    { name: "ACK RTT Min (response)", key: "ackRttMinRes" },
    { name: "ACK RTT Max (request)", key: "ackRttMaxReq" },
    { name: "ACK RTT Max (response)", key: "ackRttMaxRes" },
  ]
},
{
  name: "RTO",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "ACK RTO Sum (request)", key: "ackRtoSumReq" },
    { name: "ACK RTO Sum (response)", key: "ackRtoSumRes" },
    { name: "ACK RTO Count (request)", key: "ackRtoCntReq" },
    { name: "ACK RTO Count (response)", key: "ackRtoCntRes" }
  ]
},
{
  name: "HTTP",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "HTTP Length (request)", key: "httpLenReq" },
    { name: "HTTP Length (response)", key: "httpLenRes" },
    { name: "HTTP Count (request)", key: "httpCntReq" },
    { name: "HTTP Count (response)", key: "httpCntRes" }
  ]
},
{
  name: "Packet",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Packet Length (request)", key: "pktLenReq" },
    { name: "Packet Length (response)", key: "pktLenRes" },
    { name: "Packet Count (request)", key: "pktCntReq" },
    { name: "Packet Count (response)", key: "pktCntRes" }
  ]
},
{
  name: "TCP",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TCP Length (request)", key: "tcpLenReq" },
    { name: "TCP Length (response)", key: "tcpLenRes" },
    { name: "TCP Count (request)", key: "tcpCntReq" },
    { name: "TCP Count (response)", key: "tcpCntRes" }
  ]
},
{
  name: "TCP Error",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Retransmission Count (request)", key: "retransmissionCntReq" },
    { name: "Retransmission Count (response)", key: "retransmissionCntRes" },
    { name: "Retransmission Length (request)", key: "retransmissionLenReq" },
    { name: "Retransmission Length (response)", key: "retransmissionLenRes" },
    { name: "Fast Retransmission Count (request)", key: "fastRetransmissionCntReq" },
    { name: "Fast Retransmission Count (response)", key: "fastRetransmissionCntRes" },
    { name: "Fast Retransmission Length (request)", key: "fastRetransmissionLenReq" },
    { name: "Fast Retransmission Length (response)", key: "fastRetransmissionLenRes" },
    { name: "Out of Order Count (request)", key: "outOfOrderCntReq" },
    { name: "Out of Order Count (response)", key: "outOfOrderCntRes" },
    { name: "Out of Order Length (request)", key: "outOfOrderLenReq" },
    { name: "Out of Order Length (response)", key: "outOfOrderLenRes" },
    { name: "Lost Segment Count (request)", key: "lostSegCntReq" },
    { name: "Lost Segment Count (response)", key: "lostSegCntRes" },
    { name: "Lost Segment Length (request)", key: "lostSegLenReq" },
    { name: "Lost Segment Length (response)", key: "lostSegLenRes" },
    { name: "ACK Lost Count (request)", key: "ackLostCntReq" },
    { name: "ACK Lost Count (response)", key: "ackLostCntRes" },
    { name: "ACK Lost Length (request)", key: "ackLostLenReq" },
    { name: "ACK Lost Length (response)", key: "ackLostLenRes" },
    { name: "Dup ACK Count (request)", key: "dupAckCntReq" },
    { name: "Dup ACK Count (response)", key: "dupAckCntRes" },
    { name: "Dup ACK Length (request)", key: "dupAckLenReq" },
    { name: "Dup ACK Length (response)", key: "dupAckLenRes" },
    { name: "Zero Window Count (request)", key: "zeroWinCntReq" },
    { name: "Zero Window Count (response)", key: "zeroWinCntRes" },
    { name: "Zero Window Length (request)", key: "zeroWinLenReq" },
    { name: "Zero Window Length (response)", key: "zeroWinLenRes" },
    { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReq" },
    { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntRes" },
    { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReq" },
    { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenRes" },
    { name: "Overlap Count (request)", key: "overlapCntReq" },
    { name: "Overlap Count (response)", key: "overlapCntRes" },
    { name: "Overlap Length (request)", key: "overlapLenReq" },
    { name: "Overlap Length (response)", key: "overlapLenRes" },
    { name: "Overlap Attack Count (request)", key: "overlapAttackCntReq" },
    { name: "Overlap Attack Count (response)", key: "overlapAttackCntRes" },
    { name: "Overlap Attack Length (request)", key: "overlapAttackLenReq" },
    { name: "Overlap Attack Length (response)", key: "overlapAttackLenRes" },
    { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReq" },
    { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntRes" },
    { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReq" },
    { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenRes" },
    { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReq" },
    { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntRes" },
    { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReq" },
    { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenRes" },
    { name: "Keep Alive Count (request)", key: "keepAliveCntReq" },
    { name: "Keep Alive Count (response)", key: "keepAliveCntRes" },
    { name: "Keep Alive Length (request)", key: "keepAliveLenReq" },
    { name: "Keep Alive Length (response)", key: "keepAliveLenRes" },
    { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReq" },
    { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntRes" },
    { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReq" },
    { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenRes" },
    { name: "Window Update Count (request)", key: "winUpdateCntReq" },
    { name: "Window Update Count (response)", key: "winUpdateCntRes" },
    { name: "Window Update Length (request)", key: "winUpdateLenReq" },
    { name: "Window Update Length (response)", key: "winUpdateLenRes" }
  ]
}];

/**
 * Resource - L4-TCP
 * 
 */
export const tcpResources = [{
  name: "TCP",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "SYN", key: "tsSyn" },
    { name: "SYN ACK", key: "tsSynAck" },
    { name: "SYN Delay", key: "tsSynDelay" },
    { name: "SYN ACK Delay", key: "tsSynackDelay" },
    { name: "First ACK", key: "tsFirstAck" },
    { name: "First ACK (request)", key: "tsFirstAckReq" },
    { name: "First ACK (response)", key: "tsFirstAckRes" },
    { name: "First ACK Request ACK", key: "tsFirstAckReqAck" },
    { name: "First ACK Response ACK", key: "tsFirstAckResAck" },
    { name: "First Push (request)", key: "tsFirstPushReq" },
    { name: "First Push (response)", key: "tsFirstPushRes" },
    { name: "ACK Delay First (request)", key: "tsAckDelayFirstReq" },
    { name: "ACK Delay First (response)", key: "tsAckDelayFirstRes" },
    { name: "ACK Delay Last (request)", key: "tsAckDelayLastReq" },
    { name: "ACK Delay Last (response)", key: "tsAckDelayLastRes" },
    { name: "Request Making", key: "tsReqMaking" },
    { name: "Stopped Transaction", key: "stoppedTransaction" }
  ]
},
{
  name: "PDU",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Length PDU Total (request)", key: "lenPduReqTot" },
    { name: "Length PDU Total (response)", key: "lenPduResTot" },
    { name: "Length PDU Per Sec (request)", key: "lenPduReqPerSec" },
    { name: "Length PDU Per Sec (response)", key: "lenPduResPerSec" },
    { name: "Length PDU Delta (request)", key: "lenPduReqDelta" },
    { name: "Length PDU Delta (response)", key: "lenPduResDelta" },
    { name: "Packets PDU Total (request)", key: "pktsPduReqTot" },
    { name: "Packets PDU Total (response)", key: "pktsPduResTot" },
    { name: "Packets PDU Per Sec (request)", key: "pktsPduReqPerSec" },
    { name: "Packets PDU Per Sec (response)", key: "pktsPduResPerSec" },
    { name: "Packets PDU Delta (request)", key: "pktsPduReqDelta" },
    { name: "Packets PDU Delta (response)", key: "pktsPduResDelta" }
  ]
},
{
  name: "RTT",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "RTT SYN", key: "tsRttSyn" },
    { name: "RTT SYN ACK", key: "tsRttSynAck" },
    { name: "RTT First ACK (request)", key: "tsRttFirstAckReq" },
    { name: "RTT First ACK (response)", key: "tsRttFirstAckRes" },
    { name: "RTT Last ACK (request)", key: "tsRttLastAckReq" },
    { name: "RTT Last ACK (response)", key: "tsRttLastAckRes" },
    { name: "ACK RTT Sum (request)", key: "ackRttSumReq" },
    { name: "ACK RTT Sum (response)", key: "ackRttSumRes" },
    { name: "ACK RTT Count (request)", key: "ackRttCntReq" },
    { name: "ACK RTT Count (response)", key: "ackRttCntRes" },
    { name: "ACK RTT Min Total (request)", key: "ackRttMinReqTot" },
    { name: "ACK RTT Min Total (response)", key: "ackRttMinResTot" },
    { name: "ACK RTT Max Total (request)", key: "ackRttMaxReqTot" },
    { name: "ACK RTT Max Total (response)", key: "ackRttMaxResTot" },
    { name: "ACK RTT Min Current (request)", key: "ackRttMinReqCurr" },
    { name: "ACK RTT Min Current (response)", key: "ackRttMinResCurr" },
    { name: "ACK RTT Max Current (request)", key: "ackRttMaxReqCurr" },
    { name: "ACK RTT Max Current (response)", key: "ackRttMaxResCurr" }
  ]
},
{
  name: "RTO",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "ACK RTO Sum (request)", key: "ackRtoSumReq" },
    { name: "ACK RTO Sum (response)", key: "ackRtoSumRes" },
    { name: "ACK RTO Count (request)", key: "ackRtoCntReq" },
    { name: "ACK RTO Count (response)", key: "ackRtoCntRes" }
  ]
},
{
  name: "TCP Error (Total)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Retransmission Count (request)", key: "retransmissionCntReqTot", isTcpError: true },
    { name: "Retransmission Count (response)", key: "retransmissionCntResTot", isTcpError: true },
    { name: "Retransmission Length (request)", key: "retransmissionLenReqTot", isTcpError: true },
    { name: "Retransmission Length (response)", key: "retransmissionLenResTot", isTcpError: true },
    { name: "Fast Retransmission Count (request)", key: "fastretransmissionCntReqTot", isTcpError: true },
    { name: "Fast Retransmission Count (response)", key: "fastretransmissionCntResTot", isTcpError: true },
    { name: "Fast Retransmission Length (request)", key: "fastretransmissionLenReqTot", isTcpError: true },
    { name: "Fast Retransmission Length (response)", key: "fastretransmissionLenResTot", isTcpError: true },
    { name: "Out of Order Count (request)", key: "outoforderCntReqTot", isTcpError: true },
    { name: "Out of Order Count (response)", key: "outoforderCntResTot", isTcpError: true },
    { name: "Out of Order Length (request)", key: "outoforderLenReqTot", isTcpError: true },
    { name: "Out of Order Length (response)", key: "outoforderLenResTot", isTcpError: true },
    { name: "Lost Segment Count (request)", key: "lostsegCntReqTot", isTcpError: true },
    { name: "Lost Segment Count (response)", key: "lostsegCntResTot", isTcpError: true },
    { name: "Lost Segment Length (request)", key: "lostsegLenReqTot", isTcpError: true },
    { name: "Lost Segment Length (response)", key: "lostsegLenResTot", isTcpError: true },
    { name: "ACK Lost Count (request)", key: "acklostCntReqTot", isTcpError: true },
    { name: "ACK Lost Count (response)", key: "acklostCntResTot", isTcpError: true },
    { name: "ACK Lost Length (request)", key: "acklostLenReqTot", isTcpError: true },
    { name: "ACK Lost Length (response)", key: "acklostLenResTot", isTcpError: true },
    { name: "Dup ACK Count (request)", key: "dupackCntReqTot", isTcpError: true },
    { name: "Dup ACK Count (response)", key: "dupackCntResTot", isTcpError: true },
    { name: "Dup ACK Length (request)", key: "dupackLenReqTot", isTcpError: true },
    { name: "Dup ACK Length (response)", key: "dupackLenResTot", isTcpError: true },
    { name: "Zero Window Count (request)", key: "zerowinCntReqTot", isTcpError: true },
    { name: "Zero Window Count (response)", key: "zerowinCntResTot", isTcpError: true },
    { name: "Zero Window Length (request)", key: "zerowinLenReqTot", isTcpError: true },
    { name: "Zero Window Length (response)", key: "zerowinLenResTot", isTcpError: true },
    { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReqTot", isTcpError: true },
    { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntResTot", isTcpError: true },
    { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReqTot", isTcpError: true },
    { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenResTot", isTcpError: true },
    { name: "Overlap Count (request)", key: "overlapCntReqTot", isTcpError: true },
    { name: "Overlap Count (response)", key: "overlapCntResTot", isTcpError: true },
    { name: "Overlap Length (request)", key: "overlapLenReqTot", isTcpError: true },
    { name: "Overlap Length (response)", key: "overlapLenResTot", isTcpError: true },
    { name: "Overlap Attack Count (request)", key: "overlapAttackCntReqTot", isTcpError: true },
    { name: "Overlap Attack Count (response)", key: "overlapAttackCntResTot", isTcpError: true },
    { name: "Overlap Attack Length (request)", key: "overlapAttackLenReqTot", isTcpError: true },
    { name: "Overlap Attack Length (response)", key: "overlapAttackLenResTot", isTcpError: true },
    { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReqTot", isTcpError: true },
    { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntResTot", isTcpError: true },
    { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReqTot", isTcpError: true },
    { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenResTot", isTcpError: true },
    { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReqTot", isTcpError: true },
    { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntResTot", isTcpError: true },
    { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReqTot", isTcpError: true },
    { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenResTot", isTcpError: true },
    { name: "Keep Alive Count (request)", key: "keepAliveCntReqTot", isTcpError: true },
    { name: "Keep Alive Count (response)", key: "keepAliveCntResTot", isTcpError: true },
    { name: "Keep Alive Length (request)", key: "keepAliveLenReqTot", isTcpError: true },
    { name: "Keep Alive Length (response)", key: "keepAliveLenResTot", isTcpError: true },
    { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReqTot", isTcpError: true },
    { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntResTot", isTcpError: true },
    { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReqTot", isTcpError: true },
    { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenResTot", isTcpError: true },
    { name: "Window Update Count (request)", key: "winupdateCntReqTot", isTcpError: true },
    { name: "Window Update Count (response)", key: "winupdateCntResTot", isTcpError: true },
    { name: "Window Update Length (request)", key: "winupdateLenReqTot", isTcpError: true },
    { name: "Window Update Length (response)", key: "winupdateLenResTot", isTcpError: true }
  ]
},
{
  name: "TCP Error (Per Sec)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Retransmission Count (request)", key: "retransmissionCntReqPerSec", isTcpError: true },
    { name: "Retransmission Count (response)", key: "retransmissionCntResPerSec", isTcpError: true },
    { name: "Retransmission Length (request)", key: "retransmissionLenReqPerSec", isTcpError: true },
    { name: "Retransmission Length (response)", key: "retransmissionLenResPerSec", isTcpError: true },
    { name: "Fast Retransmission Count (request)", key: "fastretransmissionCntReqPerSec", isTcpError: true },
    { name: "Fast Retransmission Count (response)", key: "fastretransmissionCntResPerSec", isTcpError: true },
    { name: "Fast Retransmission Length (request)", key: "fastretransmissionLenReqPerSec", isTcpError: true },
    { name: "Fast Retransmission Length (response)", key: "fastretransmissionLenResPerSec", isTcpError: true },
    { name: "Out of Order Count (request)", key: "outoforderCntReqPerSec", isTcpError: true },
    { name: "Out of Order Count (response)", key: "outoforderCntResPerSec", isTcpError: true },
    { name: "Out of Order Length (request)", key: "outoforderLenReqPerSec", isTcpError: true },
    { name: "Out of Order Length (response)", key: "outoforderLenResPerSec", isTcpError: true },
    { name: "Lost Segment Count (request)", key: "lostsegCntReqPerSec", isTcpError: true },
    { name: "Lost Segment Count (response)", key: "lostsegCntResPerSec", isTcpError: true },
    { name: "Lost Segment Length (request)", key: "lostsegLenReqPerSec", isTcpError: true },
    { name: "Lost Segment Length (response)", key: "lostsegLenResPerSec", isTcpError: true },
    { name: "ACK Lost Count (request)", key: "acklostCntReqPerSec", isTcpError: true },
    { name: "ACK Lost Count (response)", key: "acklostCntResPerSec", isTcpError: true },
    { name: "ACK Lost Length (request)", key: "acklostLenReqPerSec", isTcpError: true },
    { name: "ACK Lost Length (response)", key: "acklostLenResPerSec", isTcpError: true },
    { name: "Dup ACK Count (request)", key: "dupackCntReqPerSec", isTcpError: true },
    { name: "Dup ACK Count (response)", key: "dupackCntResPerSec", isTcpError: true },
    { name: "Dup ACK Length (request)", key: "dupackLenReqPerSec", isTcpError: true },
    { name: "Dup ACK Length (response)", key: "dupackLenResPerSec", isTcpError: true },
    { name: "Zero Window Count (request)", key: "zerowinCntReqPerSec", isTcpError: true },
    { name: "Zero Window Count (response)", key: "zerowinCntResPerSec", isTcpError: true },
    { name: "Zero Window Length (request)", key: "zerowinLenReqPerSec", isTcpError: true },
    { name: "Zero Window Length (response)", key: "zerowinLenResPerSec", isTcpError: true },
    { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReqPerSec", isTcpError: true },
    { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntResPerSec", isTcpError: true },
    { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReqPerSec", isTcpError: true },
    { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenResPerSec", isTcpError: true },
    { name: "Overlap Count (request)", key: "overlapCntReqPerSec", isTcpError: true },
    { name: "Overlap Count (response)", key: "overlapCntResPerSec", isTcpError: true },
    { name: "Overlap Length (request)", key: "overlapLenReqPerSec", isTcpError: true },
    { name: "Overlap Length (response)", key: "overlapLenResPerSec", isTcpError: true },
    { name: "Overlap Attack Count (request)", key: "overlapAttackCntReqPerSec", isTcpError: true },
    { name: "Overlap Attack Count (response)", key: "overlapAttackCntResPerSec", isTcpError: true },
    { name: "Overlap Attack Length (request)", key: "overlapAttackLenReqPerSec", isTcpError: true },
    { name: "Overlap Attack Length (response)", key: "overlapAttackLenResPerSec", isTcpError: true },
    { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReqPerSec", isTcpError: true },
    { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntResPerSec", isTcpError: true },
    { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReqPerSec", isTcpError: true },
    { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenResPerSec", isTcpError: true },
    { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReqPerSec", isTcpError: true },
    { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntResPerSec", isTcpError: true },
    { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReqPerSec", isTcpError: true },
    { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenResPerSec", isTcpError: true },
    { name: "Keep Alive Count (request)", key: "keepAliveCntReqPerSec", isTcpError: true },
    { name: "Keep Alive Count (response)", key: "keepAliveCntResPerSec", isTcpError: true },
    { name: "Keep Alive Length (request)", key: "keepAliveLenReqPerSec", isTcpError: true },
    { name: "Keep Alive Length (response)", key: "keepAliveLenResPerSec", isTcpError: true },
    { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReqPerSec", isTcpError: true },
    { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntResPerSec", isTcpError: true },
    { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReqPerSec", isTcpError: true },
    { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenResPerSec", isTcpError: true },
    { name: "Window Update Count (request)", key: "winupdateCntReqPerSec", isTcpError: true },
    { name: "Window Update Count (response)", key: "winupdateCntResPerSec", isTcpError: true },
    { name: "Window Update Length (request)", key: "winupdateLenReqPerSec", isTcpError: true },
    { name: "Window Update Length (response)", key: "winupdateLenResPerSec", isTcpError: true }
  ]
},
{
  name: "TCP Error (Delta)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Retransmission Count (request)", key: "retransmissionCntReqDelta", isTcpError: true },
    { name: "Retransmission Count (response)", key: "retransmissionCntResDelta", isTcpError: true },
    { name: "Retransmission Length (request)", key: "retransmissionLenReqDelta", isTcpError: true },
    { name: "Retransmission Length (response)", key: "retransmissionLenResDelta", isTcpError: true },
    { name: "Fast Retransmission Count (request)", key: "fastretransmissionCntReqDelta", isTcpError: true },
    { name: "Fast Retransmission Count (response)", key: "fastretransmissionCntResDelta", isTcpError: true },
    { name: "Fast Retransmission Length (request)", key: "fastretransmissionLenReqDelta", isTcpError: true },
    { name: "Fast Retransmission Length (response)", key: "fastretransmissionLenResDelta", isTcpError: true },
    { name: "Out of Order Count (request)", key: "outoforderCntReqDelta", isTcpError: true },
    { name: "Out of Order Count (response)", key: "outoforderCntResDelta", isTcpError: true },
    { name: "Out of Order Length (request)", key: "outoforderLenReqDelta", isTcpError: true },
    { name: "Out of Order Length (response)", key: "outoforderLenResDelta", isTcpError: true },
    { name: "Lost Segment Count (request)", key: "lostsegCntReqDelta", isTcpError: true },
    { name: "Lost Segment Count (response)", key: "lostsegCntResDelta", isTcpError: true },
    { name: "Lost Segment Length (request)", key: "lostsegLenReqDelta", isTcpError: true },
    { name: "Lost Segment Length (response)", key: "lostsegLenResDelta", isTcpError: true },
    { name: "ACK Lost Count (request)", key: "acklostCntReqDelta", isTcpError: true },
    { name: "ACK Lost Count (response)", key: "acklostCntResDelta", isTcpError: true },
    { name: "ACK Lost Length (request)", key: "acklostLenReqDelta", isTcpError: true },
    { name: "ACK Lost Length (response)", key: "acklostLenResDelta", isTcpError: true },
    { name: "Dup ACK Count (request)", key: "dupackCntReqDelta", isTcpError: true },
    { name: "Dup ACK Count (response)", key: "dupackCntResDelta", isTcpError: true },
    { name: "Dup ACK Length (request)", key: "dupackLenReqDelta", isTcpError: true },
    { name: "Dup ACK Length (response)", key: "dupackLenResDelta", isTcpError: true },
    { name: "Zero Window Count (request)", key: "zerowinCntReqDelta", isTcpError: true },
    { name: "Zero Window Count (response)", key: "zerowinCntResDelta", isTcpError: true },
    { name: "Zero Window Length (request)", key: "zerowinLenReqDelta", isTcpError: true },
    { name: "Zero Window Length (response)", key: "zerowinLenResDelta", isTcpError: true },
    { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReqDelta", isTcpError: true },
    { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntResDelta", isTcpError: true },
    { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReqDelta", isTcpError: true },
    { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenResDelta", isTcpError: true },
    { name: "Overlap Count (request)", key: "overlapCntReqDelta", isTcpError: true },
    { name: "Overlap Count (response)", key: "overlapCntResDelta", isTcpError: true },
    { name: "Overlap Length (request)", key: "overlapLenReqDelta", isTcpError: true },
    { name: "Overlap Length (response)", key: "overlapLenResDelta", isTcpError: true },
    { name: "Overlap Attack Count (request)", key: "overlapAttackCntReqDelta", isTcpError: true },
    { name: "Overlap Attack Count (response)", key: "overlapAttackCntResDelta", isTcpError: true },
    { name: "Overlap Attack Length (request)", key: "overlapAttackLenReqDelta", isTcpError: true },
    { name: "Overlap Attack Length (response)", key: "overlapAttackLenResDelta", isTcpError: true },
    { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReqDelta", isTcpError: true },
    { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntResDelta", isTcpError: true },
    { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReqDelta", isTcpError: true },
    { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenResDelta", isTcpError: true },
    { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReqDelta", isTcpError: true },
    { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntResDelta", isTcpError: true },
    { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReqDelta", isTcpError: true },
    { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenResDelta", isTcpError: true },
    { name: "Keep Alive Count (request)", key: "keepAliveCntReqDelta", isTcpError: true },
    { name: "Keep Alive Count (response)", key: "keepAliveCntResDelta", isTcpError: true },
    { name: "Keep Alive Length (request)", key: "keepAliveLenReqDelta", isTcpError: true },
    { name: "Keep Alive Length (response)", key: "keepAliveLenResDelta", isTcpError: true },
    { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReqDelta", isTcpError: true },
    { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntResDelta", isTcpError: true },
    { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReqDelta", isTcpError: true },
    { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenResDelta", isTcpError: true },
    { name: "Window Update Count (request)", key: "winupdateCntReqDelta", isTcpError: true },
    { name: "Window Update Count (response)", key: "winupdateCntResDelta", isTcpError: true },
    { name: "Window Update Length (request)", key: "winupdateLenReqDelta", isTcpError: true },
    { name: "Window Update Length (response)", key: "winupdateLenResDelta", isTcpError: true }
  ]
},
{
  name: "TCP Flag Stat (Total)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TCP Flag Stat Fin (request)", key: "tcpFlagStatFinReqTot" },
    { name: "TCP Flag Stat Fin (response)", key: "tcpFlagStatFinResTot" },
    { name: "TCP Flag Stat SYN (request)", key: "tcpFlagStatSynReqTot" },
    { name: "TCP Flag Stat SYN (response)", key: "tcpFlagStatSynResTot" },
    { name: "TCP Flag Stat Rst (request)", key: "tcpFlagStatRstReqTot" },
    { name: "TCP Flag Stat Rst (response)", key: "tcpFlagStatRstResTot" },
    { name: "TCP Flag Stat Psh (request)", key: "tcpFlagStatPshReqTot" },
    { name: "TCP Flag Stat Psh (response)", key: "tcpFlagStatPshResTot" },
    { name: "TCP Flag Stat ACK (request)", key: "tcpFlagStatAckReqTot" },
    { name: "TCP Flag Stat ACK (response)", key: "tcpFlagStatAckResTot" },
    { name: "TCP Flag Stat Urg (request)", key: "tcpFlagStatUrgReqTot" },
    { name: "TCP Flag Stat Urg (response)", key: "tcpFlagStatUrgResTot" }
  ]
},
{
  name: "TCP Flag Stat (Per Sec)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TCP Flag Stat Fin (request)", key: "tcpFlagStatFinReqPerSec" },
    { name: "TCP Flag Stat Fin (response)", key: "tcpFlagStatFinResPerSec" },
    { name: "TCP Flag Stat SYN (request)", key: "tcpFlagStatSynReqPerSec" },
    { name: "TCP Flag Stat SYN (response)", key: "tcpFlagStatSynResPerSec" },
    { name: "TCP Flag Stat Rst (request)", key: "tcpFlagStatRstReqPerSec" },
    { name: "TCP Flag Stat Rst (response)", key: "tcpFlagStatRstResPerSec" },
    { name: "TCP Flag Stat Psh (request)", key: "tcpFlagStatPshReqPerSec" },
    { name: "TCP Flag Stat Psh (response)", key: "tcpFlagStatPshResPerSec" },
    { name: "TCP Flag Stat ACK (request)", key: "tcpFlagStatAckReqPerSec" },
    { name: "TCP Flag Stat ACK (response)", key: "tcpFlagStatAckResPerSec" },
    { name: "TCP Flag Stat Urg (request)", key: "tcpFlagStatUrgReqPerSec" },
    { name: "TCP Flag Stat Urg (response)", key: "tcpFlagStatUrgResPerSec" }
  ]
},
{
  name: "TCP Flag Stat (Delta)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TCP Flag Stat Fin (request)", key: "tcpFlagStatFinReqDelta" },
    { name: "TCP Flag Stat Fin (response)", key: "tcpFlagStatFinResDelta" },
    { name: "TCP Flag Stat SYN (request)", key: "tcpFlagStatSynReqDelta" },
    { name: "TCP Flag Stat SYN (response)", key: "tcpFlagStatSynResDelta" },
    { name: "TCP Flag Stat Rst (request)", key: "tcpFlagStatRstReqDelta" },
    { name: "TCP Flag Stat Rst (response)", key: "tcpFlagStatRstResDelta" },
    { name: "TCP Flag Stat Psh (request)", key: "tcpFlagStatPshReqDelta" },
    { name: "TCP Flag Stat Psh (response)", key: "tcpFlagStatPshResDelta" },
    { name: "TCP Flag Stat ACK (request)", key: "tcpFlagStatAckReqDelta" },
    { name: "TCP Flag Stat ACK (response)", key: "tcpFlagStatAckResDelta" },
    { name: "TCP Flag Stat Urg (request)", key: "tcpFlagStatUrgReqDelta" },
    { name: "TCP Flag Stat Urg (response)", key: "tcpFlagStatUrgResDelta" }
  ]
},
{
  name: "Connect Refused/Timeout",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Connect Refused (request)", key: "reqConnRefused" },
    { name: "Connect Refused (response)", key: "resConnRefused" },
    { name: "SYN Timeout", key: "synTimeout" },
    { name: "SYN ACK Timeout", key: "synackTimeout" },
    { name: "ACK Timeout Count (request)", key: "reqAckTimeoutCnt" },
    { name: "ACK Timeout Count (response)", key: "resAckTimeoutCnt" },
    { name: "Session Timeout", key: "sessionTimeout" },
    { name: "Fin Wait Timeout", key: "finWaitTimeout" },
    { name: "Close Wait Timeout", key: "closeWaitTimeout" },
    { name: "Last ACK Timeout", key: "lastAckTimeout" }
  ]
}];

/**
 * Resource - L4-UDP
 * 
 */
export const udpResources = [{
  name: "PDU",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Length PDU Total (request)", key: "lenPduReqTot" },
    { name: "Length PDU Total (response)", key: "lenPduResTot" },
    { name: "Length PDU Per Sec (request)", key: "lenPduReqPerSec" },
    { name: "Length PDU Per Sec (response)", key: "lenPduResPerSec" },
    { name: "Length PDU Delta (request)", key: "lenPduReqDelta" },
    { name: "Length PDU Delta (response)", key: "lenPduResDelta" },
    { name: "Packets PDU Total (request)", key: "pktsPduReqTot" },
    { name: "Packets PDU Total (response)", key: "pktsPduResTot" },
    { name: "Packets PDU Per Sec (request)", key: "pktsPduReqPerSec" },
    { name: "Packets PDU Per Sec (response)", key: "pktsPduResPerSec" },
    { name: "Packets PDU Delta (request)", key: "pktsPduReqDelta" },
    { name: "Packets PDU Delta (response)", key: "pktsPduResDelta" }
  ]
}];

/**
 * Resource - L3-IP
 * 
 */
export const ipResources = [{
  name: "Fragmented Packets",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Fragmented Packets Total (request)", key: "fragPktsTotReq" },
    { name: "Fragmented Packets Total (response)", key: "fragPktsTotRes" },
    { name: "Fragmented Packets Per Sec (request)", key: "fragPktsPerSecReq" },
    { name: "Fragmented Packets Per Sec (response)", key: "fragPktsPerSecRes" },
    { name: "Fragmented Packets Delta (request)", key: "fragPktsDeltaReq" },
    { name: "Fragmented Packets Delta (response)", key: "fragPktsDeltaRes" }
  ]
},
{
  name: "TTL Min/Max",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TTL Min (request)", key: "ttlMinReq" },
    { name: "TTL Min (response)", key: "ttlMinRes" },
    { name: "TTL Max (request)", key: "ttlMaxReq" },
    { name: "TTL Max (response)", key: "ttlMaxRes" }
  ]
},
{
  name: "TTL Stat (Total)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TTL Stat 1 (request)", key: "ttlStat1ReqTot" },
    { name: "TTL Stat 2 To 5 (request)", key: "ttlStat2To5ReqTot" },
    { name: "TTL Stat 6 To 32 (request)", key: "ttlStat6To32ReqTot" },
    { name: "TTL Stat 33 To 64 (request)", key: "ttlStat33To64ReqTot" },
    { name: "TTL Stat 65 To 96 (request)", key: "ttlStat65To96ReqTot" },
    { name: "TTL Stat 97 To 128 (request)", key: "ttlStat97To128ReqTot" },
    { name: "TTL Stat 129 To 160 (request)", key: "ttlStat129To160ReqTot" },
    { name: "TTL Stat 161 To 192 (request)", key: "ttlStat161To192ReqTot" },
    { name: "TTL Stat 193 To 224 (request)", key: "ttlStat193To224ReqTot" },
    { name: "TTL Stat 225 To 255 (request)", key: "ttlStat225To255ReqTot" },
    { name: "TTL Stat 1 (response)", key: "ttlStat1ResTot" },
    { name: "TTL Stat 2 To 5 (response)", key: "ttlStat2To5ResTot" },
    { name: "TTL Stat 6 To 32 (response)", key: "ttlStat6To32ResTot" },
    { name: "TTL Stat 33 To 64 (response)", key: "ttlStat33To64ResTot" },
    { name: "TTL Stat 65 To 96 (response)", key: "ttlStat65To96ResTot" },
    { name: "TTL Stat 97 To 128 (response)", key: "ttlStat97To128ResTot" },
    { name: "TTL Stat 129 To 160 (response)", key: "ttlStat129To160ResTot" },
    { name: "TTL Stat 161 To 192 (response)", key: "ttlStat161To192ResTot" },
    { name: "TTL Stat 193 To 224 (response)", key: "ttlStat193To224ResTot" },
    { name: "TTL Stat 225 To 255 (response)", key: "ttlStat225To255ResTot" }
  ]
},
{
  name: "TTL Stat (Per Sec)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TTL Stat 1 (request)", key: "ttlStat1ReqPerSec" },
    { name: "TTL Stat 2 To 5 (request)", key: "ttlStat2To5ReqPerSec" },
    { name: "TTL Stat 6 To 32 (request)", key: "ttlStat6To32ReqPerSec" },
    { name: "TTL Stat 33 To 64 (request)", key: "ttlStat33To64ReqPerSec" },
    { name: "TTL Stat 65 To 96 (request)", key: "ttlStat65To96ReqPerSec" },
    { name: "TTL Stat 97 To 128 (request)", key: "ttlStat97To128ReqPerSec" },
    { name: "TTL Stat 129 To 160 (request)", key: "ttlStat129To160ReqPerSec" },
    { name: "TTL Stat 161 To 192 (request)", key: "ttlStat161To192ReqPerSec" },
    { name: "TTL Stat 193 To 224 (request)", key: "ttlStat193To224ReqPerSec" },
    { name: "TTL Stat 225 To 255 (request)", key: "ttlStat225To255ReqPerSec" },
    { name: "TTL Stat 1 (response)", key: "ttlStat1ResPerSec" },
    { name: "TTL Stat 2 To 5 (response)", key: "ttlStat2To5ResPerSec" },
    { name: "TTL Stat 6 To 32 (response)", key: "ttlStat6To32ResPerSec" },
    { name: "TTL Stat 33 To 64 (response)", key: "ttlStat33To64ResPerSec" },
    { name: "TTL Stat 65 To 96 (response)", key: "ttlStat65To96ResPerSec" },
    { name: "TTL Stat 97 To 128 (response)", key: "ttlStat97To128ResPerSec" },
    { name: "TTL Stat 129 To 160 (response)", key: "ttlStat129To160ResPerSec" },
    { name: "TTL Stat 161 To 192 (response)", key: "ttlStat161To192ResPerSec" },
    { name: "TTL Stat 193 To 224 (response)", key: "ttlStat193To224ResPerSec" },
    { name: "TTL Stat 225 To 255 (response)", key: "ttlStat225To255ResPerSec" }
  ]
},
{
  name: "TTL Stat (Delta)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "TTL Stat 1 (request)", key: "ttlStat1ReqDelta" },
    { name: "TTL Stat 2 To 5 (request)", key: "ttlStat2To5ReqDelta" },
    { name: "TTL Stat 6 To 32 (request)", key: "ttlStat6To32ReqDelta" },
    { name: "TTL Stat 33 To 64 (request)", key: "ttlStat33To64ReqDelta" },
    { name: "TTL Stat 65 To 96 (request)", key: "ttlStat65To96ReqDelta" },
    { name: "TTL Stat 97 To 128 (request)", key: "ttlStat97To128ReqDelta" },
    { name: "TTL Stat 129 To 160 (request)", key: "ttlStat129To160ReqDelta" },
    { name: "TTL Stat 161 To 192 (request)", key: "ttlStat161To192ReqDelta" },
    { name: "TTL Stat 193 To 224 (request)", key: "ttlStat193To224ReqDelta" },
    { name: "TTL Stat 225 To 255 (request)", key: "ttlStat225To255ReqDelta" },
    { name: "TTL Stat 1 (response)", key: "ttlStat1ResDelta" },
    { name: "TTL Stat 2 To 5 (response)", key: "ttlStat2To5ResDelta" },
    { name: "TTL Stat 6 To 32 (response)", key: "ttlStat6To32ResDelta" },
    { name: "TTL Stat 33 To 64 (response)", key: "ttlStat33To64ResDelta" },
    { name: "TTL Stat 65 To 96 (response)", key: "ttlStat65To96ResDelta" },
    { name: "TTL Stat 97 To 128 (response)", key: "ttlStat97To128ResDelta" },
    { name: "TTL Stat 129 To 160 (response)", key: "ttlStat129To160ResDelta" },
    { name: "TTL Stat 161 To 192 (response)", key: "ttlStat161To192ResDelta" },
    { name: "TTL Stat 193 To 224 (response)", key: "ttlStat193To224ResDelta" },
    { name: "TTL Stat 225 To 255 (response)", key: "ttlStat225To255ResDelta" }
  ]
},
{
  name: "Overlap",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Overlap Count Total (request)", key: "overlapCntReqTot" },
    { name: "Overlap Count Total (response)", key: "overlapCntResTot" },
    { name: "Overlap Length Total (request)", key: "overlapLenReqTot" },
    { name: "Overlap Length Total (response)", key: "overlapLenResTot" },
    { name: "Overlap Count Per Sec (request)", key: "overlapCntReqPerSec" },
    { name: "Overlap Count Per Sec (response)", key: "overlapCntResPerSec" },
    { name: "Overlap Length Per Sec (request)", key: "overlapLenReqPerSec" },
    { name: "Overlap Length Per Sec (response)", key: "overlapLenResPerSec" },
    { name: "Overlap Count Delta (request)", key: "overlapCntReqDelta" },
    { name: "Overlap Count Delta (response)", key: "overlapCntResDelta" },
    { name: "Overlap Length Delta (request)", key: "overlapLenReqDelta" },
    { name: "Overlap Length Delta (response)", key: "overlapLenResDelta" }
  ]
},
{
  name: "Overlap Attack",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Overlap Attack Count Total (request)", key: "overlapAttackCntReqTot" },
    { name: "Overlap Attack Count Total (response)", key: "overlapAttackCntResTot" },
    { name: "Overlap Attack Length Total (request)", key: "overlapAttackLenReqTot" },
    { name: "Overlap Attack Length Total (response)", key: "overlapAttackLenResTot" },
    { name: "Overlap Attack Count Per Sec (request)", key: "overlapAttackCntReqPerSec" },
    { name: "Overlap Attack Count Per Sec (response)", key: "overlapAttackCntResPerSec" },
    { name: "Overlap Attack Length Per Sec (request)", key: "overlapAttackLenReqPerSec" },
    { name: "Overlap Attack Length Per Sec (response)", key: "overlapAttackLenResPerSec" },
    { name: "Overlap Attack Count Delta (request)", key: "overlapAttackCntReqDelta" },
    { name: "Overlap Attack Count Delta (response)", key: "overlapAttackCntResDelta" },
    { name: "Overlap Attack Length Delta (request)", key: "overlapAttackLenReqDelta" },
    { name: "Overlap Attack Length Delta (response)", key: "overlapAttackLenResDelta" }
  ]
}];

/**
 * Resource - Traffic
 * 
 */
export const trafficResources = [{
  name: "Length",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Length Total (request)", key: "lenReqTot" },
    { name: "Length Total (response)", key: "lenResTot" },
    { name: "Length Per Sec (request)", key: "lenReqPerSec" },
    { name: "Length Per Sec (response)", key: "lenResPerSec" },
    { name: "Length Delta (request)", key: "lenReqDelta" },
    { name: "Length Delta (response)", key: "lenResDelta" }
  ]
},
{
  name: "Packets",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Packets Total (request)", key: "pktsReqTot" },
    { name: "Packets Total (response)", key: "pktsResTot" },
    { name: "Packets Per Sec (request)", key: "pktsReqPerSec" },
    { name: "Packets Per Sec (response)", key: "pktsResPerSec" },
    { name: "Packets Delta (request)", key: "pktsReqDelta" },
    { name: "Packets Delta (response)", key: "pktsResDelta" }
  ]
},
{
  name: "Packet Length",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Packet Length Min (request)", key: "pktLenMinReq" },
    { name: "Packet Length Min (response)", key: "pktLenMinRes" },
    { name: "Packet Length Max (request)", key: "pktLenMaxReq" },
    { name: "Packet Length Max (response)", key: "pktLenMaxRes" }
  ]
},
{
  name: "Packet Length Stat (Total)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Packet Length Stat 1 To 128 (request)", key: "pktLenStat1To128ReqTot" },
    { name: "Packet Length Stat 129 To 256 (request)", key: "pktLenStat129To256ReqTot" },
    { name: "Packet Length Stat 257 To 512 (request)", key: "pktLenStat257To512ReqTot" },
    { name: "Packet Length Stat 513 To 1024 (request)", key: "pktLenStat513To1024ReqTot" },
    { name: "Packet Length Stat 1025 To 1514 (request)", key: "pktLenStat1025To1514ReqTot" },
    { name: "Packet Length Stat Jumbo (request)", key: "pktLenStatJumboReqTot" },
    { name: "Packet Length Stat 1 To 128 (response)", key: "pktLenStat1To128ResTot" },
    { name: "Packet Length Stat 129 To 256 (response)", key: "pktLenStat129To256ResTot" },
    { name: "Packet Length Stat 257 To 512 (response)", key: "pktLenStat257To512ResTot" },
    { name: "Packet Length Stat 513 To 1024 (response)", key: "pktLenStat513To1024ResTot" },
    { name: "Packet Length Stat 1025 To 1514 (response)", key: "pktLenStat1025To1514ResTot" },
    { name: "Packet Length Stat Jumbo (response)", key: "pktLenStatJumboResTot" }
  ]
},
{
  name: "Packet Length Stat (Per Sec)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Packet Length Stat 1 To 128 (request)", key: "pktLenStat1To128ReqPerSec" },
    { name: "Packet Length Stat 129 To 256 (request)", key: "pktLenStat129To256ReqPerSec" },
    { name: "Packet Length Stat 257 To 512 (request)", key: "pktLenStat257To512ReqPerSec" },
    { name: "Packet Length Stat 513 To 1024 (request)", key: "pktLenStat513To1024ReqPerSec" },
    { name: "Packet Length Stat 1025 To 1514 (request)", key: "pktLenStat1025To1514ReqPerSec" },
    { name: "Packet Length Stat Jumbo (request)", key: "pktLenStatJumboReqPerSec" },
    { name: "Packet Length Stat 1 To 128 (response)", key: "pktLenStat1To128ResPerSec" },
    { name: "Packet Length Stat 129 To 256 (response)", key: "pktLenStat129To256ResPerSec" },
    { name: "Packet Length Stat 257 To 512 (response)", key: "pktLenStat257To512ResPerSec" },
    { name: "Packet Length Stat 513 To 1024 (response)", key: "pktLenStat513To1024ResPerSec" },
    { name: "Packet Length Stat 1025 To 1514 (response)", key: "pktLenStat1025To1514ResPerSec" },
    { name: "Packet Length Stat Jumbo (response)", key: "pktLenStatJumboResPerSec" }
  ]
},
{
  name: "Packet Length Stat (Delta)",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Packet Length Stat 1 To 128 (request)", key: "pktLenStat1To128ReqDelta" },
    { name: "Packet Length Stat 129 To 256 (request)", key: "pktLenStat129To256ReqDelta" },
    { name: "Packet Length Stat 257 To 512 (request)", key: "pktLenStat257To512ReqDelta" },
    { name: "Packet Length Stat 513 To 1024 (request)", key: "pktLenStat513To1024ReqDelta" },
    { name: "Packet Length Stat 1025 To 1514 (request)", key: "pktLenStat1025To1514ReqDelta" },
    { name: "Packet Length Stat Jumbo (request)", key: "pktLenStatJumboReqDelta" },
    { name: "Packet Length Stat 1 To 128 (response)", key: "pktLenStat1To128ResDelta" },
    { name: "Packet Length Stat 129 To 256 (response)", key: "pktLenStat129To256ResDelta" },
    { name: "Packet Length Stat 257 To 512 (response)", key: "pktLenStat257To512ResDelta" },
    { name: "Packet Length Stat 513 To 1024 (response)", key: "pktLenStat513To1024ResDelta" },
    { name: "Packet Length Stat 1025 To 1514 (response)", key: "pktLenStat1025To1514ResDelta" },
    { name: "Packet Length Stat Jumbo (response)", key: "pktLenStatJumboResDelta" }
  ]
}];

/**
 * Alarm Resource - Page
 *
 */
export const alarmPageResources = [{
  name: "Page",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Page Response Time", key: "tsPage" },
    { name: "Page Gap Time", key: "tsPageGap" },
    { name: "Page Response Init Time", key: "tsPageResInit" },
    { name: "Page Response Init Gap Time", key: "tsPageResInitGap" },
    { name: "Page Response App Time", key: "tsPageResApp" },
    { name: "Page Response App Gap Time", key: "tsPageResAppGap" },
    { name: "Page Response Response Time", key: "tsPageRes" },
    { name: "Page Response Gap Time", key: "tsPageResGap" },
    { name: "Page Transfer Request Time", key: "tsPageTransferReq" },
    { name: "Page Transfer Request Gap Time", key: "tsPageTransferReqGap" },
    { name: "Page Transfer Response Time", key: "tsPageTransferRes" },
    { name: "Page Transfer Response Gap Time", key: "tsPageTransferResGap" },
    { name: "Page Request Making Time", key: "tsPageReqMakingSum" },
    { name: "Page Request Making Count", key: "pageReqMakingCnt" },
    //{ name: "Stopped Transaction Count", key: "stoppedTransactionCnt" }
  ]
},
  {
    name: "RTT",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Page Connection RTT (request)", key: "tsPageRttConnSumReq" },
      { name: "Page Connection RTT (response)", key: "tsPageRttConnSumRes" },
      { name: "Page ACK RTT (request)", key: "tsPageRttAckSumReq" },
      { name: "Page ACK RTT (response)", key: "tsPageRttAckSumRes" },
      { name: "Page Connection RTT Count (request)", key: "pageRttConnCntReq" },
      { name: "Page Connection RTT Count (response)", key: "pageRttConnCntRes" },
      { name: "Page ACK RTT Count (request)", key: "pageRttAckCntReq" },
      { name: "Page ACK RTT Count (response)", key: "pageRttAckCntRes" },
      { name: "Page ACK RTT Min (request)", key: "tsPageRttAckReqMin" },
      { name: "Page ACK RTT Min (response)", key: "tsPageRttAckResMin" },
      { name: "Page ACK RTT Max (request)", key: "tsPageRttAckReqMax" },
      { name: "Page ACK RTT Max (response)", key: "tsPageRttAckResMax" }
    ]
  },
  {
    name: "RTO",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Page RTO (request)", key: "tsPageRtoSumReq" },
      { name: "Page RTO (response)", key: "tsPageRtoSumRes" },
      { name: "Page RTO Count (request)", key: "tsPageRtoCntReq" },
      { name: "Page RTO Count (response)", key: "tsPageRtoCntRes" }
    ]
  },
  {
    name: "HTTP",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "HTTP Length (request)", key: "pageHttpLenReq" },
      { name: "HTTP Length (response)", key: "pageHttpLenRes" },
      { name: "HTTP Count (request)", key: "pageHttpCntReq" },
      { name: "HTTP Count (response)", key: "pageHttpCntRes" }
    ]
  },
  {
    name: "Packet",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Packet Length (request)", key: "pagePktLenReq" },
      { name: "Packet Length (response)", key: "pagePktLenRes" },
      { name: "Packet Count (request)", key: "pagePktCntReq" },
      { name: "Packet Count (response)", key: "pagePktCntRes" },
      { name: "Connection Error Packet Count", key: "connErrPktCnt" }
    ]
  },
  {
    name: "Session",
    group: true,
    open: true,
    checked: false,
    children: [
      //{ name: "Session Count", key: "pageSessionCnt" },
      //{ name: "Connection Error Session Count", key: "connErrSessionCnt" },
      { name: "Connection Error Session Length (request)", key: "reqConnErrSessionLen" },
      { name: "Connection Error Session Length (response)", key: "resConnErrSessionLen" }
    ]
  },
  {
    name: "TCP",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TCP Length (request)", key: "pageTcpLenReq" },
      { name: "TCP Length (response)", key: "pageTcpLenRes" },
      { name: "TCP Count (request)", key: "pageTcpCntReq" },
      { name: "TCP Count (response)", key: "pageTcpCntRes" }
    ]
  },
  {
    name: "TCP Error",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Retransmission Count (request)", key: "retransmissionCntReq" },
      { name: "Retransmission Count (response)", key: "retransmissionCntRes" },
      { name: "Retransmission Length (request)", key: "retransmissionLenReq" },
      { name: "Retransmission Length (response)", key: "retransmissionLenRes" },
      { name: "Fast Retransmission Count (request)", key: "fastRetransmissionCntReq" },
      { name: "Fast Retransmission Count (response)", key: "fastRetransmissionCntRes" },
      { name: "Fast Retransmission Length (request)", key: "fastRetransmissionLenReq" },
      { name: "Fast Retransmission Length (response)", key: "fastRetransmissionLenRes" },
      { name: "Out of Order Count (request)", key: "outOfOrderCntReq" },
      { name: "Out of Order Count (response)", key: "outOfOrderCntRes" },
      { name: "Out of Order Length (request)", key: "outOfOrderLenReq" },
      { name: "Out of Order Length (response)", key: "outOfOrderLenRes" },
      { name: "Lost Segment Count (request)", key: "lostSegCntReq" },
      { name: "Lost Segment Count (response)", key: "lostSegCntRes" },
      { name: "Lost Segment Length (request)", key: "lostSegLenReq" },
      { name: "Lost Segment Length (response)", key: "lostSegLenRes" },
      { name: "ACK Lost Count (request)", key: "ackLostCntReq" },
      { name: "ACK Lost Count (response)", key: "ackLostCntRes" },
      { name: "ACK Lost Length (request)", key: "ackLostLenReq" },
      { name: "ACK Lost Length (response)", key: "ackLostLenRes" },
      { name: "Dup ACK Count (request)", key: "dupAckCntReq" },
      { name: "Dup ACK Count (response)", key: "dupAckCntRes" },
      { name: "Dup ACK Length (request)", key: "dupAckLenReq" },
      { name: "Dup ACK Length (response)", key: "dupAckLenRes" },
      { name: "Zero Window Count (request)", key: "zeroWinCntReq" },
      { name: "Zero Window Count (response)", key: "zeroWinCntRes" },
      { name: "Zero Window Length (request)", key: "zeroWinLenReq" },
      { name: "Zero Window Length (response)", key: "zeroWinLenRes" },
      { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReq" },
      { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntRes" },
      { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReq" },
      { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenRes" },
      { name: "Overlap Count (request)", key: "overlapCntReq" },
      { name: "Overlap Count (response)", key: "overlapCntRes" },
      { name: "Overlap Length (request)", key: "overlapLenReq" },
      { name: "Overlap Length (response)", key: "overlapLenRes" },
      { name: "Overlap Attack Count (request)", key: "overlapAttackCntReq" },
      { name: "Overlap Attack Count (response)", key: "overlapAttackCntRes" },
      { name: "Overlap Attack Length (request)", key: "overlapAttackLenReq" },
      { name: "Overlap Attack Length (response)", key: "overlapAttackLenRes" },
      { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReq" },
      { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntRes" },
      { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReq" },
      { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenRes" },
      { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReq" },
      { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntRes" },
      { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReq" },
      { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenRes" },
      { name: "Keep Alive Count (request)", key: "keepAliveCntReq" },
      { name: "Keep Alive Count (response)", key: "keepAliveCntRes" },
      { name: "Keep Alive Length (request)", key: "keepAliveLenReq" },
      { name: "Keep Alive Length (response)", key: "keepAliveLenRes" },
      { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReq" },
      { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntRes" },
      { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReq" },
      { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenRes" },
      { name: "Window Update Count (request)", key: "winUpdateCntReq" },
      { name: "Window Update Count (response)", key: "winUpdateCntRes" },
      { name: "Window Update Length (request)", key: "winUpdateLenReq" },
      { name: "Window Update Length (response)", key: "winUpdateLenRes" }
    ]
  },
  {
    name: "Response Code",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "2xx Count", key: "resCode2xxCnt" },
      { name: "3xx Count", key: "resCode3xxCnt" },
      { name: "401 Count", key: "resCode401Cnt" },
      { name: "404 Count", key: "resCode404Cnt" },
      { name: "4xx Count", key: "resCode4xxCnt" },
      { name: "5xx Count", key: "resCode5xxCnt" },
      { name: "Other Count", key: "resCodeOthCnt" }
    ]
  }];

/**
 * Alarm Resource - URI
 *
 */
export const alarmUriResources = [{
  name: "URI",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "URI Response Time", key: "tsRsqDelayResponse" },
    { name: "Delay Transfer (request)", key: "tsReqDelayTransfer" },
    { name: "Delay Transfer (response)", key: "tsResDelayTransfer" },
    { name: "Response Process First", key: "tsResProcessFirst" },
    { name: "Response Process Push", key: "tsResProcessPush" },
    { name: "Response Process Last", key: "tsResProcessLast" },
    { name: "First ACK Delay (request)", key: "tsFirstAckDelayReq" },
    { name: "First ACK Delay (response)", key: "tsFirstAckDelayRes" },
    { name: "Connection Delay (request)", key: "tsConnDelayReq" },
    { name: "Connection Delay (response)", key: "tsConnDelayRes" },
    { name: "Request Making Time", key: "tsReqMaking" },
    { name: "ACK Delay (request)", key: "ackDelayReq" },
    { name: "ACK Delay (response)", key: "ackDelayRes" }
  ]
},
  {
    name: "RTT",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "RTT SYN", key: "tsRttSyn" },
      { name: "RTT SYN ACK", key: "tsRttSynAck" },
      { name: "RTT First ACK", key: "tsRttFirstAck" },
      { name: "RTT ACK (request)", key: "tsRttReqAck" },
      { name: "RTT ACK (response)", key: "tsRttResAck" },
      { name: "RTT ACK Request ACK", key: "tsRttAckReqAck" },
      { name: "RTT ACK Response ACK", key: "tsRttAckResAck" },
      { name: "RTT First ACK (request)", key: "tsRttFirstAckReq" },
      { name: "RTT First ACK (response)", key: "tsRttFirstAckRes" },
      { name: "Connection RTT (request)", key: "tsRttConnReq" },
      { name: "Connection RTT (response)", key: "tsRttConnRes" },
      { name: "ACK RTT Sum (request)", key: "ackRttSumReq" },
      { name: "ACK RTT Sum (response)", key: "ackRttSumRes" },
      { name: "ACK RTT Count (request)", key: "ackRttCntReq" },
      { name: "ACK RTT Count (response)", key: "ackRttCntRes" },
      { name: "ACK RTT Min (request)", key: "ackRttMinReq" },
      { name: "ACK RTT Min (response)", key: "ackRttMinRes" },
      { name: "ACK RTT Max (request)", key: "ackRttMaxReq" },
      { name: "ACK RTT Max (response)", key: "ackRttMaxRes" },
    ]
  },
  {
    name: "RTO",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "ACK RTO Sum (request)", key: "ackRtoSumReq" },
      { name: "ACK RTO Sum (response)", key: "ackRtoSumRes" },
      { name: "ACK RTO Count (request)", key: "ackRtoCntReq" },
      { name: "ACK RTO Count (response)", key: "ackRtoCntRes" }
    ]
  },
  {
    name: "HTTP",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "HTTP Length (request)", key: "httpLenReq" },
      { name: "HTTP Length (response)", key: "httpLenRes" },
      { name: "HTTP Count (request)", key: "httpCntReq" },
      { name: "HTTP Count (response)", key: "httpCntRes" }
    ]
  },
  {
    name: "Packet",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Packet Length (request)", key: "pktLenReq" },
      { name: "Packet Length (response)", key: "pktLenRes" },
      { name: "Packet Count (request)", key: "pktCntReq" },
      { name: "Packet Count (response)", key: "pktCntRes" }
    ]
  },
  {
    name: "TCP",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TCP Length (request)", key: "tcpLenReq" },
      { name: "TCP Length (response)", key: "tcpLenRes" },
      { name: "TCP Count (request)", key: "tcpCntReq" },
      { name: "TCP Count (response)", key: "tcpCntRes" }
    ]
  },
  {
    name: "TCP Error",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Retransmission Count (request)", key: "retransmissionCntReq" },
      { name: "Retransmission Count (response)", key: "retransmissionCntRes" },
      { name: "Retransmission Length (request)", key: "retransmissionLenReq" },
      { name: "Retransmission Length (response)", key: "retransmissionLenRes" },
      { name: "Fast Retransmission Count (request)", key: "fastRetransmissionCntReq" },
      { name: "Fast Retransmission Count (response)", key: "fastRetransmissionCntRes" },
      { name: "Fast Retransmission Length (request)", key: "fastRetransmissionLenReq" },
      { name: "Fast Retransmission Length (response)", key: "fastRetransmissionLenRes" },
      { name: "Out of Order Count (request)", key: "outOfOrderCntReq" },
      { name: "Out of Order Count (response)", key: "outOfOrderCntRes" },
      { name: "Out of Order Length (request)", key: "outOfOrderLenReq" },
      { name: "Out of Order Length (response)", key: "outOfOrderLenRes" },
      { name: "Lost Segment Count (request)", key: "lostSegCntReq" },
      { name: "Lost Segment Count (response)", key: "lostSegCntRes" },
      { name: "Lost Segment Length (request)", key: "lostSegLenReq" },
      { name: "Lost Segment Length (response)", key: "lostSegLenRes" },
      { name: "ACK Lost Count (request)", key: "ackLostCntReq" },
      { name: "ACK Lost Count (response)", key: "ackLostCntRes" },
      { name: "ACK Lost Length (request)", key: "ackLostLenReq" },
      { name: "ACK Lost Length (response)", key: "ackLostLenRes" },
      { name: "Dup ACK Count (request)", key: "dupAckCntReq" },
      { name: "Dup ACK Count (response)", key: "dupAckCntRes" },
      { name: "Dup ACK Length (request)", key: "dupAckLenReq" },
      { name: "Dup ACK Length (response)", key: "dupAckLenRes" },
      { name: "Zero Window Count (request)", key: "zeroWinCntReq" },
      { name: "Zero Window Count (response)", key: "zeroWinCntRes" },
      { name: "Zero Window Length (request)", key: "zeroWinLenReq" },
      { name: "Zero Window Length (response)", key: "zeroWinLenRes" },
      { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReq" },
      { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntRes" },
      { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReq" },
      { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenRes" },
      { name: "Overlap Count (request)", key: "overlapCntReq" },
      { name: "Overlap Count (response)", key: "overlapCntRes" },
      { name: "Overlap Length (request)", key: "overlapLenReq" },
      { name: "Overlap Length (response)", key: "overlapLenRes" },
      { name: "Overlap Attack Count (request)", key: "overlapAttackCntReq" },
      { name: "Overlap Attack Count (response)", key: "overlapAttackCntRes" },
      { name: "Overlap Attack Length (request)", key: "overlapAttackLenReq" },
      { name: "Overlap Attack Length (response)", key: "overlapAttackLenRes" },
      { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReq" },
      { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntRes" },
      { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReq" },
      { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenRes" },
      { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReq" },
      { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntRes" },
      { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReq" },
      { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenRes" },
      { name: "Keep Alive Count (request)", key: "keepAliveCntReq" },
      { name: "Keep Alive Count (response)", key: "keepAliveCntRes" },
      { name: "Keep Alive Length (request)", key: "keepAliveLenReq" },
      { name: "Keep Alive Length (response)", key: "keepAliveLenRes" },
      { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReq" },
      { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntRes" },
      { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReq" },
      { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenRes" },
      { name: "Window Update Count (request)", key: "winUpdateCntReq" },
      { name: "Window Update Count (response)", key: "winUpdateCntRes" },
      { name: "Window Update Length (request)", key: "winUpdateLenReq" },
      { name: "Window Update Length (response)", key: "winUpdateLenRes" }
    ]
  }];

/**
 * Alarm Resource - L4-TCP
 *
 */
export const alarmTcpResources = [{
  name: "TCP",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "SYN", key: "tsSyn" },
    { name: "SYN ACK", key: "tsSynAck" },
    { name: "SYN Delay", key: "tsSynDelay" },
    { name: "SYN ACK Delay", key: "tsSynackDelay" },
    { name: "First ACK", key: "tsFirstAck" },
    { name: "First ACK (request)", key: "tsFirstAckReq" },
    { name: "First ACK (response)", key: "tsFirstAckRes" },
    { name: "First ACK Request ACK", key: "tsFirstAckReqAck" },
    { name: "First ACK Response ACK", key: "tsFirstAckResAck" },
    { name: "First Push (request)", key: "tsFirstPushReq" },
    { name: "First Push (response)", key: "tsFirstPushRes" },
    { name: "ACK Delay First (request)", key: "tsAckDelayFirstReq" },
    { name: "ACK Delay First (response)", key: "tsAckDelayFirstRes" },
    { name: "ACK Delay Last (request)", key: "tsAckDelayLastReq" },
    { name: "ACK Delay Last (response)", key: "tsAckDelayLastRes" },
    { name: "Request Making", key: "tsReqMaking" },
    { name: "Stopped Transaction", key: "stoppedTransaction" }
  ]
},
  {
    name: "PDU",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Length PDU Total (request)", key: "lenPduReqTot" },
      { name: "Length PDU Total (response)", key: "lenPduResTot" },
      { name: "Length PDU Per Sec (request)", key: "lenPduReqPerSec" },
      { name: "Length PDU Per Sec (response)", key: "lenPduResPerSec" },
      { name: "Length PDU Delta (request)", key: "lenPduReqDelta" },
      { name: "Length PDU Delta (response)", key: "lenPduResDelta" },
      { name: "Packets PDU Total (request)", key: "pktsPduReqTot" },
      { name: "Packets PDU Total (response)", key: "pktsPduResTot" },
      { name: "Packets PDU Per Sec (request)", key: "pktsPduReqPerSec" },
      { name: "Packets PDU Per Sec (response)", key: "pktsPduResPerSec" },
      { name: "Packets PDU Delta (request)", key: "pktsPduReqDelta" },
      { name: "Packets PDU Delta (response)", key: "pktsPduResDelta" }
    ]
  },
  {
    name: "RTT",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "RTT SYN", key: "tsRttSyn" },
      { name: "RTT SYN ACK", key: "tsRttSynAck" },
      { name: "RTT First ACK (request)", key: "tsRttFirstAckReq" },
      { name: "RTT First ACK (response)", key: "tsRttFirstAckRes" },
      { name: "RTT Last ACK (request)", key: "tsRttLastAckReq" },
      { name: "RTT Last ACK (response)", key: "tsRttLastAckRes" },
      { name: "ACK RTT Sum (request)", key: "ackRttSumReq" },
      { name: "ACK RTT Sum (response)", key: "ackRttSumRes" },
      { name: "ACK RTT Count (request)", key: "ackRttCntReq" },
      { name: "ACK RTT Count (response)", key: "ackRttCntRes" },
      { name: "ACK RTT Min Total (request)", key: "ackRttMinReqTot" },
      { name: "ACK RTT Min Total (response)", key: "ackRttMinResTot" },
      { name: "ACK RTT Max Total (request)", key: "ackRttMaxReqTot" },
      { name: "ACK RTT Max Total (response)", key: "ackRttMaxResTot" },
      { name: "ACK RTT Min Current (request)", key: "ackRttMinReqCurr" },
      { name: "ACK RTT Min Current (response)", key: "ackRttMinResCurr" },
      { name: "ACK RTT Max Current (request)", key: "ackRttMaxReqCurr" },
      { name: "ACK RTT Max Current (response)", key: "ackRttMaxResCurr" }
    ]
  },
  {
    name: "RTO",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "ACK RTO Sum (request)", key: "ackRtoSumReq" },
      { name: "ACK RTO Sum (response)", key: "ackRtoSumRes" },
      { name: "ACK RTO Count (request)", key: "ackRtoCntReq" },
      { name: "ACK RTO Count (response)", key: "ackRtoCntRes" }
    ]
  },
  {
    name: "TCP Error (Total)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Retransmission Count (request)", key: "retransmissionCntReqTot", isTcpError: true },
      { name: "Retransmission Count (response)", key: "retransmissionCntResTot", isTcpError: true },
      { name: "Retransmission Length (request)", key: "retransmissionLenReqTot", isTcpError: true },
      { name: "Retransmission Length (response)", key: "retransmissionLenResTot", isTcpError: true },
      { name: "Fast Retransmission Count (request)", key: "fastretransmissionCntReqTot", isTcpError: true },
      { name: "Fast Retransmission Count (response)", key: "fastretransmissionCntResTot", isTcpError: true },
      { name: "Fast Retransmission Length (request)", key: "fastretransmissionLenReqTot", isTcpError: true },
      { name: "Fast Retransmission Length (response)", key: "fastretransmissionLenResTot", isTcpError: true },
      { name: "Out of Order Count (request)", key: "outoforderCntReqTot", isTcpError: true },
      { name: "Out of Order Count (response)", key: "outoforderCntResTot", isTcpError: true },
      { name: "Out of Order Length (request)", key: "outoforderLenReqTot", isTcpError: true },
      { name: "Out of Order Length (response)", key: "outoforderLenResTot", isTcpError: true },
      { name: "Lost Segment Count (request)", key: "lostsegCntReqTot", isTcpError: true },
      { name: "Lost Segment Count (response)", key: "lostsegCntResTot", isTcpError: true },
      { name: "Lost Segment Length (request)", key: "lostsegLenReqTot", isTcpError: true },
      { name: "Lost Segment Length (response)", key: "lostsegLenResTot", isTcpError: true },
      { name: "ACK Lost Count (request)", key: "acklostCntReqTot", isTcpError: true },
      { name: "ACK Lost Count (response)", key: "acklostCntResTot", isTcpError: true },
      { name: "ACK Lost Length (request)", key: "acklostLenReqTot", isTcpError: true },
      { name: "ACK Lost Length (response)", key: "acklostLenResTot", isTcpError: true },
      { name: "Dup ACK Count (request)", key: "dupackCntReqTot", isTcpError: true },
      { name: "Dup ACK Count (response)", key: "dupackCntResTot", isTcpError: true },
      { name: "Dup ACK Length (request)", key: "dupackLenReqTot", isTcpError: true },
      { name: "Dup ACK Length (response)", key: "dupackLenResTot", isTcpError: true },
      { name: "Zero Window Count (request)", key: "zerowinCntReqTot", isTcpError: true },
      { name: "Zero Window Count (response)", key: "zerowinCntResTot", isTcpError: true },
      { name: "Zero Window Length (request)", key: "zerowinLenReqTot", isTcpError: true },
      { name: "Zero Window Length (response)", key: "zerowinLenResTot", isTcpError: true },
      { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReqTot", isTcpError: true },
      { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntResTot", isTcpError: true },
      { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReqTot", isTcpError: true },
      { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenResTot", isTcpError: true },
      { name: "Overlap Count (request)", key: "overlapCntReqTot", isTcpError: true },
      { name: "Overlap Count (response)", key: "overlapCntResTot", isTcpError: true },
      { name: "Overlap Length (request)", key: "overlapLenReqTot", isTcpError: true },
      { name: "Overlap Length (response)", key: "overlapLenResTot", isTcpError: true },
      { name: "Overlap Attack Count (request)", key: "overlapAttackCntReqTot", isTcpError: true },
      { name: "Overlap Attack Count (response)", key: "overlapAttackCntResTot", isTcpError: true },
      { name: "Overlap Attack Length (request)", key: "overlapAttackLenReqTot", isTcpError: true },
      { name: "Overlap Attack Length (response)", key: "overlapAttackLenResTot", isTcpError: true },
      { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReqTot", isTcpError: true },
      { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntResTot", isTcpError: true },
      { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReqTot", isTcpError: true },
      { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenResTot", isTcpError: true },
      { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReqTot", isTcpError: true },
      { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntResTot", isTcpError: true },
      { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReqTot", isTcpError: true },
      { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenResTot", isTcpError: true },
      { name: "Keep Alive Count (request)", key: "keepAliveCntReqTot", isTcpError: true },
      { name: "Keep Alive Count (response)", key: "keepAliveCntResTot", isTcpError: true },
      { name: "Keep Alive Length (request)", key: "keepAliveLenReqTot", isTcpError: true },
      { name: "Keep Alive Length (response)", key: "keepAliveLenResTot", isTcpError: true },
      { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReqTot", isTcpError: true },
      { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntResTot", isTcpError: true },
      { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReqTot", isTcpError: true },
      { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenResTot", isTcpError: true },
      { name: "Window Update Count (request)", key: "winupdateCntReqTot", isTcpError: true },
      { name: "Window Update Count (response)", key: "winupdateCntResTot", isTcpError: true },
      { name: "Window Update Length (request)", key: "winupdateLenReqTot", isTcpError: true },
      { name: "Window Update Length (response)", key: "winupdateLenResTot", isTcpError: true }
    ]
  },
  {
    name: "TCP Error (Per Sec)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Retransmission Count (request)", key: "retransmissionCntReqPerSec", isTcpError: true },
      { name: "Retransmission Count (response)", key: "retransmissionCntResPerSec", isTcpError: true },
      { name: "Retransmission Length (request)", key: "retransmissionLenReqPerSec", isTcpError: true },
      { name: "Retransmission Length (response)", key: "retransmissionLenResPerSec", isTcpError: true },
      { name: "Fast Retransmission Count (request)", key: "fastretransmissionCntReqPerSec", isTcpError: true },
      { name: "Fast Retransmission Count (response)", key: "fastretransmissionCntResPerSec", isTcpError: true },
      { name: "Fast Retransmission Length (request)", key: "fastretransmissionLenReqPerSec", isTcpError: true },
      { name: "Fast Retransmission Length (response)", key: "fastretransmissionLenResPerSec", isTcpError: true },
      { name: "Out of Order Count (request)", key: "outoforderCntReqPerSec", isTcpError: true },
      { name: "Out of Order Count (response)", key: "outoforderCntResPerSec", isTcpError: true },
      { name: "Out of Order Length (request)", key: "outoforderLenReqPerSec", isTcpError: true },
      { name: "Out of Order Length (response)", key: "outoforderLenResPerSec", isTcpError: true },
      { name: "Lost Segment Count (request)", key: "lostsegCntReqPerSec", isTcpError: true },
      { name: "Lost Segment Count (response)", key: "lostsegCntResPerSec", isTcpError: true },
      { name: "Lost Segment Length (request)", key: "lostsegLenReqPerSec", isTcpError: true },
      { name: "Lost Segment Length (response)", key: "lostsegLenResPerSec", isTcpError: true },
      { name: "ACK Lost Count (request)", key: "acklostCntReqPerSec", isTcpError: true },
      { name: "ACK Lost Count (response)", key: "acklostCntResPerSec", isTcpError: true },
      { name: "ACK Lost Length (request)", key: "acklostLenReqPerSec", isTcpError: true },
      { name: "ACK Lost Length (response)", key: "acklostLenResPerSec", isTcpError: true },
      { name: "Dup ACK Count (request)", key: "dupackCntReqPerSec", isTcpError: true },
      { name: "Dup ACK Count (response)", key: "dupackCntResPerSec", isTcpError: true },
      { name: "Dup ACK Length (request)", key: "dupackLenReqPerSec", isTcpError: true },
      { name: "Dup ACK Length (response)", key: "dupackLenResPerSec", isTcpError: true },
      { name: "Zero Window Count (request)", key: "zerowinCntReqPerSec", isTcpError: true },
      { name: "Zero Window Count (response)", key: "zerowinCntResPerSec", isTcpError: true },
      { name: "Zero Window Length (request)", key: "zerowinLenReqPerSec", isTcpError: true },
      { name: "Zero Window Length (response)", key: "zerowinLenResPerSec", isTcpError: true },
      { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReqPerSec", isTcpError: true },
      { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntResPerSec", isTcpError: true },
      { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReqPerSec", isTcpError: true },
      { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenResPerSec", isTcpError: true },
      { name: "Overlap Count (request)", key: "overlapCntReqPerSec", isTcpError: true },
      { name: "Overlap Count (response)", key: "overlapCntResPerSec", isTcpError: true },
      { name: "Overlap Length (request)", key: "overlapLenReqPerSec", isTcpError: true },
      { name: "Overlap Length (response)", key: "overlapLenResPerSec", isTcpError: true },
      { name: "Overlap Attack Count (request)", key: "overlapAttackCntReqPerSec", isTcpError: true },
      { name: "Overlap Attack Count (response)", key: "overlapAttackCntResPerSec", isTcpError: true },
      { name: "Overlap Attack Length (request)", key: "overlapAttackLenReqPerSec", isTcpError: true },
      { name: "Overlap Attack Length (response)", key: "overlapAttackLenResPerSec", isTcpError: true },
      { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReqPerSec", isTcpError: true },
      { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntResPerSec", isTcpError: true },
      { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReqPerSec", isTcpError: true },
      { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenResPerSec", isTcpError: true },
      { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReqPerSec", isTcpError: true },
      { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntResPerSec", isTcpError: true },
      { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReqPerSec", isTcpError: true },
      { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenResPerSec", isTcpError: true },
      { name: "Keep Alive Count (request)", key: "keepAliveCntReqPerSec", isTcpError: true },
      { name: "Keep Alive Count (response)", key: "keepAliveCntResPerSec", isTcpError: true },
      { name: "Keep Alive Length (request)", key: "keepAliveLenReqPerSec", isTcpError: true },
      { name: "Keep Alive Length (response)", key: "keepAliveLenResPerSec", isTcpError: true },
      { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReqPerSec", isTcpError: true },
      { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntResPerSec", isTcpError: true },
      { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReqPerSec", isTcpError: true },
      { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenResPerSec", isTcpError: true },
      { name: "Window Update Count (request)", key: "winupdateCntReqPerSec", isTcpError: true },
      { name: "Window Update Count (response)", key: "winupdateCntResPerSec", isTcpError: true },
      { name: "Window Update Length (request)", key: "winupdateLenReqPerSec", isTcpError: true },
      { name: "Window Update Length (response)", key: "winupdateLenResPerSec", isTcpError: true }
    ]
  },
  {
    name: "TCP Error (Delta)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Retransmission Count (request)", key: "retransmissionCntReqDelta", isTcpError: true },
      { name: "Retransmission Count (response)", key: "retransmissionCntResDelta", isTcpError: true },
      { name: "Retransmission Length (request)", key: "retransmissionLenReqDelta", isTcpError: true },
      { name: "Retransmission Length (response)", key: "retransmissionLenResDelta", isTcpError: true },
      { name: "Fast Retransmission Count (request)", key: "fastretransmissionCntReqDelta", isTcpError: true },
      { name: "Fast Retransmission Count (response)", key: "fastretransmissionCntResDelta", isTcpError: true },
      { name: "Fast Retransmission Length (request)", key: "fastretransmissionLenReqDelta", isTcpError: true },
      { name: "Fast Retransmission Length (response)", key: "fastretransmissionLenResDelta", isTcpError: true },
      { name: "Out of Order Count (request)", key: "outoforderCntReqDelta", isTcpError: true },
      { name: "Out of Order Count (response)", key: "outoforderCntResDelta", isTcpError: true },
      { name: "Out of Order Length (request)", key: "outoforderLenReqDelta", isTcpError: true },
      { name: "Out of Order Length (response)", key: "outoforderLenResDelta", isTcpError: true },
      { name: "Lost Segment Count (request)", key: "lostsegCntReqDelta", isTcpError: true },
      { name: "Lost Segment Count (response)", key: "lostsegCntResDelta", isTcpError: true },
      { name: "Lost Segment Length (request)", key: "lostsegLenReqDelta", isTcpError: true },
      { name: "Lost Segment Length (response)", key: "lostsegLenResDelta", isTcpError: true },
      { name: "ACK Lost Count (request)", key: "acklostCntReqDelta", isTcpError: true },
      { name: "ACK Lost Count (response)", key: "acklostCntResDelta", isTcpError: true },
      { name: "ACK Lost Length (request)", key: "acklostLenReqDelta", isTcpError: true },
      { name: "ACK Lost Length (response)", key: "acklostLenResDelta", isTcpError: true },
      { name: "Dup ACK Count (request)", key: "dupackCntReqDelta", isTcpError: true },
      { name: "Dup ACK Count (response)", key: "dupackCntResDelta", isTcpError: true },
      { name: "Dup ACK Length (request)", key: "dupackLenReqDelta", isTcpError: true },
      { name: "Dup ACK Length (response)", key: "dupackLenResDelta", isTcpError: true },
      { name: "Zero Window Count (request)", key: "zerowinCntReqDelta", isTcpError: true },
      { name: "Zero Window Count (response)", key: "zerowinCntResDelta", isTcpError: true },
      { name: "Zero Window Length (request)", key: "zerowinLenReqDelta", isTcpError: true },
      { name: "Zero Window Length (response)", key: "zerowinLenResDelta", isTcpError: true },
      { name: "Spurious Retransmission Count (request)", key: "spuriousRetransmissionCntReqDelta", isTcpError: true },
      { name: "Spurious Retransmission Count (response)", key: "spuriousRetransmissionCntResDelta", isTcpError: true },
      { name: "Spurious Retransmission Length (request)", key: "spuriousRetransmissionLenReqDelta", isTcpError: true },
      { name: "Spurious Retransmission Length (response)", key: "spuriousRetransmissionLenResDelta", isTcpError: true },
      { name: "Overlap Count (request)", key: "overlapCntReqDelta", isTcpError: true },
      { name: "Overlap Count (response)", key: "overlapCntResDelta", isTcpError: true },
      { name: "Overlap Length (request)", key: "overlapLenReqDelta", isTcpError: true },
      { name: "Overlap Length (response)", key: "overlapLenResDelta", isTcpError: true },
      { name: "Overlap Attack Count (request)", key: "overlapAttackCntReqDelta", isTcpError: true },
      { name: "Overlap Attack Count (response)", key: "overlapAttackCntResDelta", isTcpError: true },
      { name: "Overlap Attack Length (request)", key: "overlapAttackLenReqDelta", isTcpError: true },
      { name: "Overlap Attack Length (response)", key: "overlapAttackLenResDelta", isTcpError: true },
      { name: "Zero Window Probe Count (request)", key: "zeroWinProbeCntReqDelta", isTcpError: true },
      { name: "Zero Window Probe Count (response)", key: "zeroWinProbeCntResDelta", isTcpError: true },
      { name: "Zero Window Probe Length (request)", key: "zeroWinProbeLenReqDelta", isTcpError: true },
      { name: "Zero Window Probe Length (response)", key: "zeroWinProbeLenResDelta", isTcpError: true },
      { name: "Zero Window ACK Probe Count (request)", key: "zeroWinProbeAckCntReqDelta", isTcpError: true },
      { name: "Zero Window ACK Probe Count (response)", key: "zeroWinProbeAckCntResDelta", isTcpError: true },
      { name: "Zero Window ACK Probe Length (request)", key: "zeroWinProbeAckLenReqDelta", isTcpError: true },
      { name: "Zero Window ACK Probe Length (response)", key: "zeroWinProbeAckLenResDelta", isTcpError: true },
      { name: "Keep Alive Count (request)", key: "keepAliveCntReqDelta", isTcpError: true },
      { name: "Keep Alive Count (response)", key: "keepAliveCntResDelta", isTcpError: true },
      { name: "Keep Alive Length (request)", key: "keepAliveLenReqDelta", isTcpError: true },
      { name: "Keep Alive Length (response)", key: "keepAliveLenResDelta", isTcpError: true },
      { name: "Keep Alive ACK Count (request)", key: "keepAliveAckCntReqDelta", isTcpError: true },
      { name: "Keep Alive ACK Count (response)", key: "keepAliveAckCntResDelta", isTcpError: true },
      { name: "Keep Alive ACK Length (request)", key: "keepAliveAckLenReqDelta", isTcpError: true },
      { name: "Keep Alive ACK Length (response)", key: "keepAliveAckLenResDelta", isTcpError: true },
      { name: "Window Update Count (request)", key: "winupdateCntReqDelta", isTcpError: true },
      { name: "Window Update Count (response)", key: "winupdateCntResDelta", isTcpError: true },
      { name: "Window Update Length (request)", key: "winupdateLenReqDelta", isTcpError: true },
      { name: "Window Update Length (response)", key: "winupdateLenResDelta", isTcpError: true }
    ]
  },
  {
    name: "TCP Flag Stat (Total)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TCP Flag Stat Fin (request)", key: "tcpFlagStatFinReqTot" },
      { name: "TCP Flag Stat Fin (response)", key: "tcpFlagStatFinResTot" },
      { name: "TCP Flag Stat SYN (request)", key: "tcpFlagStatSynReqTot" },
      { name: "TCP Flag Stat SYN (response)", key: "tcpFlagStatSynResTot" },
      { name: "TCP Flag Stat Rst (request)", key: "tcpFlagStatRstReqTot" },
      { name: "TCP Flag Stat Rst (response)", key: "tcpFlagStatRstResTot" },
      { name: "TCP Flag Stat Psh (request)", key: "tcpFlagStatPshReqTot" },
      { name: "TCP Flag Stat Psh (response)", key: "tcpFlagStatPshResTot" },
      { name: "TCP Flag Stat ACK (request)", key: "tcpFlagStatAckReqTot" },
      { name: "TCP Flag Stat ACK (response)", key: "tcpFlagStatAckResTot" },
      { name: "TCP Flag Stat Urg (request)", key: "tcpFlagStatUrgReqTot" },
      { name: "TCP Flag Stat Urg (response)", key: "tcpFlagStatUrgResTot" }
    ]
  },
  {
    name: "TCP Flag Stat (Per Sec)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TCP Flag Stat Fin (request)", key: "tcpFlagStatFinReqPerSec" },
      { name: "TCP Flag Stat Fin (response)", key: "tcpFlagStatFinResPerSec" },
      { name: "TCP Flag Stat SYN (request)", key: "tcpFlagStatSynReqPerSec" },
      { name: "TCP Flag Stat SYN (response)", key: "tcpFlagStatSynResPerSec" },
      { name: "TCP Flag Stat Rst (request)", key: "tcpFlagStatRstReqPerSec" },
      { name: "TCP Flag Stat Rst (response)", key: "tcpFlagStatRstResPerSec" },
      { name: "TCP Flag Stat Psh (request)", key: "tcpFlagStatPshReqPerSec" },
      { name: "TCP Flag Stat Psh (response)", key: "tcpFlagStatPshResPerSec" },
      { name: "TCP Flag Stat ACK (request)", key: "tcpFlagStatAckReqPerSec" },
      { name: "TCP Flag Stat ACK (response)", key: "tcpFlagStatAckResPerSec" },
      { name: "TCP Flag Stat Urg (request)", key: "tcpFlagStatUrgReqPerSec" },
      { name: "TCP Flag Stat Urg (response)", key: "tcpFlagStatUrgResPerSec" }
    ]
  },
  {
    name: "TCP Flag Stat (Delta)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TCP Flag Stat Fin (request)", key: "tcpFlagStatFinReqDelta" },
      { name: "TCP Flag Stat Fin (response)", key: "tcpFlagStatFinResDelta" },
      { name: "TCP Flag Stat SYN (request)", key: "tcpFlagStatSynReqDelta" },
      { name: "TCP Flag Stat SYN (response)", key: "tcpFlagStatSynResDelta" },
      { name: "TCP Flag Stat Rst (request)", key: "tcpFlagStatRstReqDelta" },
      { name: "TCP Flag Stat Rst (response)", key: "tcpFlagStatRstResDelta" },
      { name: "TCP Flag Stat Psh (request)", key: "tcpFlagStatPshReqDelta" },
      { name: "TCP Flag Stat Psh (response)", key: "tcpFlagStatPshResDelta" },
      { name: "TCP Flag Stat ACK (request)", key: "tcpFlagStatAckReqDelta" },
      { name: "TCP Flag Stat ACK (response)", key: "tcpFlagStatAckResDelta" },
      { name: "TCP Flag Stat Urg (request)", key: "tcpFlagStatUrgReqDelta" },
      { name: "TCP Flag Stat Urg (response)", key: "tcpFlagStatUrgResDelta" }
    ]
  },
  {
    name: "Connect Refused/Timeout",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Connect Refused (request)", key: "reqConnRefused" },
      { name: "Connect Refused (response)", key: "resConnRefused" },
      { name: "SYN Timeout", key: "synTimeout" },
      { name: "SYN ACK Timeout", key: "synackTimeout" },
      { name: "ACK Timeout Count (request)", key: "reqAckTimeoutCnt" },
      { name: "ACK Timeout Count (response)", key: "resAckTimeoutCnt" },
      { name: "Session Timeout", key: "sessionTimeout" },
      { name: "Fin Wait Timeout", key: "finWaitTimeout" },
      { name: "Close Wait Timeout", key: "closeWaitTimeout" },
      { name: "Last ACK Timeout", key: "lastAckTimeout" }
    ]
  }];

/**
 * Alarm Resource - L4-UDP
 *
 */
export const alarmUdpResources = [{
  name: "PDU",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Length PDU Total (request)", key: "lenPduReqTot" },
    { name: "Length PDU Total (response)", key: "lenPduResTot" },
    { name: "Length PDU Per Sec (request)", key: "lenPduReqPerSec" },
    { name: "Length PDU Per Sec (response)", key: "lenPduResPerSec" },
    { name: "Length PDU Delta (request)", key: "lenPduReqDelta" },
    { name: "Length PDU Delta (response)", key: "lenPduResDelta" },
    { name: "Packets PDU Total (request)", key: "pktsPduReqTot" },
    { name: "Packets PDU Total (response)", key: "pktsPduResTot" },
    { name: "Packets PDU Per Sec (request)", key: "pktsPduReqPerSec" },
    { name: "Packets PDU Per Sec (response)", key: "pktsPduResPerSec" },
    { name: "Packets PDU Delta (request)", key: "pktsPduReqDelta" },
    { name: "Packets PDU Delta (response)", key: "pktsPduResDelta" }
  ]
}];

/**
 * Alarm Resource - L3-IP
 *
 */
export const alarmIpResources = [{
  name: "Fragmented Packets",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Fragmented Packets Total (request)", key: "fragPktsTotReq" },
    { name: "Fragmented Packets Total (response)", key: "fragPktsTotRes" },
    { name: "Fragmented Packets Per Sec (request)", key: "fragPktsPerSecReq" },
    { name: "Fragmented Packets Per Sec (response)", key: "fragPktsPerSecRes" },
    { name: "Fragmented Packets Delta (request)", key: "fragPktsDeltaReq" },
    { name: "Fragmented Packets Delta (response)", key: "fragPktsDeltaRes" }
  ]
},
  {
    name: "TTL Min/Max",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TTL Min (request)", key: "ttlMinReq" },
      { name: "TTL Min (response)", key: "ttlMinRes" },
      { name: "TTL Max (request)", key: "ttlMaxReq" },
      { name: "TTL Max (response)", key: "ttlMaxRes" }
    ]
  },
  {
    name: "TTL Stat (Total)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TTL Stat 1 (request)", key: "ttlStat1ReqTot" },
      { name: "TTL Stat 2 To 5 (request)", key: "ttlStat2To5ReqTot" },
      { name: "TTL Stat 6 To 32 (request)", key: "ttlStat6To32ReqTot" },
      { name: "TTL Stat 33 To 64 (request)", key: "ttlStat33To64ReqTot" },
      { name: "TTL Stat 65 To 96 (request)", key: "ttlStat65To96ReqTot" },
      { name: "TTL Stat 97 To 128 (request)", key: "ttlStat97To128ReqTot" },
      { name: "TTL Stat 129 To 160 (request)", key: "ttlStat129To160ReqTot" },
      { name: "TTL Stat 161 To 192 (request)", key: "ttlStat161To192ReqTot" },
      { name: "TTL Stat 193 To 224 (request)", key: "ttlStat193To224ReqTot" },
      { name: "TTL Stat 225 To 255 (request)", key: "ttlStat225To255ReqTot" },
      { name: "TTL Stat 1 (response)", key: "ttlStat1ResTot" },
      { name: "TTL Stat 2 To 5 (response)", key: "ttlStat2To5ResTot" },
      { name: "TTL Stat 6 To 32 (response)", key: "ttlStat6To32ResTot" },
      { name: "TTL Stat 33 To 64 (response)", key: "ttlStat33To64ResTot" },
      { name: "TTL Stat 65 To 96 (response)", key: "ttlStat65To96ResTot" },
      { name: "TTL Stat 97 To 128 (response)", key: "ttlStat97To128ResTot" },
      { name: "TTL Stat 129 To 160 (response)", key: "ttlStat129To160ResTot" },
      { name: "TTL Stat 161 To 192 (response)", key: "ttlStat161To192ResTot" },
      { name: "TTL Stat 193 To 224 (response)", key: "ttlStat193To224ResTot" },
      { name: "TTL Stat 225 To 255 (response)", key: "ttlStat225To255ResTot" }
    ]
  },
  {
    name: "TTL Stat (Per Sec)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TTL Stat 1 (request)", key: "ttlStat1ReqPerSec" },
      { name: "TTL Stat 2 To 5 (request)", key: "ttlStat2To5ReqPerSec" },
      { name: "TTL Stat 6 To 32 (request)", key: "ttlStat6To32ReqPerSec" },
      { name: "TTL Stat 33 To 64 (request)", key: "ttlStat33To64ReqPerSec" },
      { name: "TTL Stat 65 To 96 (request)", key: "ttlStat65To96ReqPerSec" },
      { name: "TTL Stat 97 To 128 (request)", key: "ttlStat97To128ReqPerSec" },
      { name: "TTL Stat 129 To 160 (request)", key: "ttlStat129To160ReqPerSec" },
      { name: "TTL Stat 161 To 192 (request)", key: "ttlStat161To192ReqPerSec" },
      { name: "TTL Stat 193 To 224 (request)", key: "ttlStat193To224ReqPerSec" },
      { name: "TTL Stat 225 To 255 (request)", key: "ttlStat225To255ReqPerSec" },
      { name: "TTL Stat 1 (response)", key: "ttlStat1ResPerSec" },
      { name: "TTL Stat 2 To 5 (response)", key: "ttlStat2To5ResPerSec" },
      { name: "TTL Stat 6 To 32 (response)", key: "ttlStat6To32ResPerSec" },
      { name: "TTL Stat 33 To 64 (response)", key: "ttlStat33To64ResPerSec" },
      { name: "TTL Stat 65 To 96 (response)", key: "ttlStat65To96ResPerSec" },
      { name: "TTL Stat 97 To 128 (response)", key: "ttlStat97To128ResPerSec" },
      { name: "TTL Stat 129 To 160 (response)", key: "ttlStat129To160ResPerSec" },
      { name: "TTL Stat 161 To 192 (response)", key: "ttlStat161To192ResPerSec" },
      { name: "TTL Stat 193 To 224 (response)", key: "ttlStat193To224ResPerSec" },
      { name: "TTL Stat 225 To 255 (response)", key: "ttlStat225To255ResPerSec" }
    ]
  },
  {
    name: "TTL Stat (Delta)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "TTL Stat 1 (request)", key: "ttlStat1ReqDelta" },
      { name: "TTL Stat 2 To 5 (request)", key: "ttlStat2To5ReqDelta" },
      { name: "TTL Stat 6 To 32 (request)", key: "ttlStat6To32ReqDelta" },
      { name: "TTL Stat 33 To 64 (request)", key: "ttlStat33To64ReqDelta" },
      { name: "TTL Stat 65 To 96 (request)", key: "ttlStat65To96ReqDelta" },
      { name: "TTL Stat 97 To 128 (request)", key: "ttlStat97To128ReqDelta" },
      { name: "TTL Stat 129 To 160 (request)", key: "ttlStat129To160ReqDelta" },
      { name: "TTL Stat 161 To 192 (request)", key: "ttlStat161To192ReqDelta" },
      { name: "TTL Stat 193 To 224 (request)", key: "ttlStat193To224ReqDelta" },
      { name: "TTL Stat 225 To 255 (request)", key: "ttlStat225To255ReqDelta" },
      { name: "TTL Stat 1 (response)", key: "ttlStat1ResDelta" },
      { name: "TTL Stat 2 To 5 (response)", key: "ttlStat2To5ResDelta" },
      { name: "TTL Stat 6 To 32 (response)", key: "ttlStat6To32ResDelta" },
      { name: "TTL Stat 33 To 64 (response)", key: "ttlStat33To64ResDelta" },
      { name: "TTL Stat 65 To 96 (response)", key: "ttlStat65To96ResDelta" },
      { name: "TTL Stat 97 To 128 (response)", key: "ttlStat97To128ResDelta" },
      { name: "TTL Stat 129 To 160 (response)", key: "ttlStat129To160ResDelta" },
      { name: "TTL Stat 161 To 192 (response)", key: "ttlStat161To192ResDelta" },
      { name: "TTL Stat 193 To 224 (response)", key: "ttlStat193To224ResDelta" },
      { name: "TTL Stat 225 To 255 (response)", key: "ttlStat225To255ResDelta" }
    ]
  },
  {
    name: "Overlap",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Overlap Count Total (request)", key: "overlapCntReqTot" },
      { name: "Overlap Count Total (response)", key: "overlapCntResTot" },
      { name: "Overlap Length Total (request)", key: "overlapLenReqTot" },
      { name: "Overlap Length Total (response)", key: "overlapLenResTot" },
      { name: "Overlap Count Per Sec (request)", key: "overlapCntReqPerSec" },
      { name: "Overlap Count Per Sec (response)", key: "overlapCntResPerSec" },
      { name: "Overlap Length Per Sec (request)", key: "overlapLenReqPerSec" },
      { name: "Overlap Length Per Sec (response)", key: "overlapLenResPerSec" },
      { name: "Overlap Count Delta (request)", key: "overlapCntReqDelta" },
      { name: "Overlap Count Delta (response)", key: "overlapCntResDelta" },
      { name: "Overlap Length Delta (request)", key: "overlapLenReqDelta" },
      { name: "Overlap Length Delta (response)", key: "overlapLenResDelta" }
    ]
  },
  {
    name: "Overlap Attack",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Overlap Attack Count Total (request)", key: "overlapAttackCntReqTot" },
      { name: "Overlap Attack Count Total (response)", key: "overlapAttackCntResTot" },
      { name: "Overlap Attack Length Total (request)", key: "overlapAttackLenReqTot" },
      { name: "Overlap Attack Length Total (response)", key: "overlapAttackLenResTot" },
      { name: "Overlap Attack Count Per Sec (request)", key: "overlapAttackCntReqPerSec" },
      { name: "Overlap Attack Count Per Sec (response)", key: "overlapAttackCntResPerSec" },
      { name: "Overlap Attack Length Per Sec (request)", key: "overlapAttackLenReqPerSec" },
      { name: "Overlap Attack Length Per Sec (response)", key: "overlapAttackLenResPerSec" },
      { name: "Overlap Attack Count Delta (request)", key: "overlapAttackCntReqDelta" },
      { name: "Overlap Attack Count Delta (response)", key: "overlapAttackCntResDelta" },
      { name: "Overlap Attack Length Delta (request)", key: "overlapAttackLenReqDelta" },
      { name: "Overlap Attack Length Delta (response)", key: "overlapAttackLenResDelta" }
    ]
  }];

/**
 * Alarm Resource - Traffic
 *
 */
export const alarmTrafficResources = [{
  name: "Length",
  group: true,
  open: true,
  checked: false,
  children: [
    { name: "Length Total (request)", key: "lenReqTot" },
    { name: "Length Total (response)", key: "lenResTot" },
    { name: "Length Per Sec (request)", key: "lenReqPerSec" },
    { name: "Length Per Sec (response)", key: "lenResPerSec" },
    { name: "Length Delta (request)", key: "lenReqDelta" },
    { name: "Length Delta (response)", key: "lenResDelta" }
  ]
},
  {
    name: "Packets",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Packets Total (request)", key: "pktsReqTot" },
      { name: "Packets Total (response)", key: "pktsResTot" },
      { name: "Packets Per Sec (request)", key: "pktsReqPerSec" },
      { name: "Packets Per Sec (response)", key: "pktsResPerSec" },
      { name: "Packets Delta (request)", key: "pktsReqDelta" },
      { name: "Packets Delta (response)", key: "pktsResDelta" }
    ]
  },
  {
    name: "Packet Length",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Packet Length Min (request)", key: "pktLenMinReq" },
      { name: "Packet Length Min (response)", key: "pktLenMinRes" },
      { name: "Packet Length Max (request)", key: "pktLenMaxReq" },
      { name: "Packet Length Max (response)", key: "pktLenMaxRes" }
    ]
  },
  {
    name: "Packet Length Stat (Total)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Packet Length Stat 1 To 128 (request)", key: "pktLenStat1To128ReqTot" },
      { name: "Packet Length Stat 129 To 256 (request)", key: "pktLenStat129To256ReqTot" },
      { name: "Packet Length Stat 257 To 512 (request)", key: "pktLenStat257To512ReqTot" },
      { name: "Packet Length Stat 513 To 1024 (request)", key: "pktLenStat513To1024ReqTot" },
      { name: "Packet Length Stat 1025 To 1514 (request)", key: "pktLenStat1025To1514ReqTot" },
      { name: "Packet Length Stat Jumbo (request)", key: "pktLenStatJumboReqTot" },
      { name: "Packet Length Stat 1 To 128 (response)", key: "pktLenStat1To128ResTot" },
      { name: "Packet Length Stat 129 To 256 (response)", key: "pktLenStat129To256ResTot" },
      { name: "Packet Length Stat 257 To 512 (response)", key: "pktLenStat257To512ResTot" },
      { name: "Packet Length Stat 513 To 1024 (response)", key: "pktLenStat513To1024ResTot" },
      { name: "Packet Length Stat 1025 To 1514 (response)", key: "pktLenStat1025To1514ResTot" },
      { name: "Packet Length Stat Jumbo (response)", key: "pktLenStatJumboResTot" }
    ]
  },
  {
    name: "Packet Length Stat (Per Sec)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Packet Length Stat 1 To 128 (request)", key: "pktLenStat1To128ReqPerSec" },
      { name: "Packet Length Stat 129 To 256 (request)", key: "pktLenStat129To256ReqPerSec" },
      { name: "Packet Length Stat 257 To 512 (request)", key: "pktLenStat257To512ReqPerSec" },
      { name: "Packet Length Stat 513 To 1024 (request)", key: "pktLenStat513To1024ReqPerSec" },
      { name: "Packet Length Stat 1025 To 1514 (request)", key: "pktLenStat1025To1514ReqPerSec" },
      { name: "Packet Length Stat Jumbo (request)", key: "pktLenStatJumboReqPerSec" },
      { name: "Packet Length Stat 1 To 128 (response)", key: "pktLenStat1To128ResPerSec" },
      { name: "Packet Length Stat 129 To 256 (response)", key: "pktLenStat129To256ResPerSec" },
      { name: "Packet Length Stat 257 To 512 (response)", key: "pktLenStat257To512ResPerSec" },
      { name: "Packet Length Stat 513 To 1024 (response)", key: "pktLenStat513To1024ResPerSec" },
      { name: "Packet Length Stat 1025 To 1514 (response)", key: "pktLenStat1025To1514ResPerSec" },
      { name: "Packet Length Stat Jumbo (response)", key: "pktLenStatJumboResPerSec" }
    ]
  },
  {
    name: "Packet Length Stat (Delta)",
    group: true,
    open: true,
    checked: false,
    children: [
      { name: "Packet Length Stat 1 To 128 (request)", key: "pktLenStat1To128ReqDelta" },
      { name: "Packet Length Stat 129 To 256 (request)", key: "pktLenStat129To256ReqDelta" },
      { name: "Packet Length Stat 257 To 512 (request)", key: "pktLenStat257To512ReqDelta" },
      { name: "Packet Length Stat 513 To 1024 (request)", key: "pktLenStat513To1024ReqDelta" },
      { name: "Packet Length Stat 1025 To 1514 (request)", key: "pktLenStat1025To1514ReqDelta" },
      { name: "Packet Length Stat Jumbo (request)", key: "pktLenStatJumboReqDelta" },
      { name: "Packet Length Stat 1 To 128 (response)", key: "pktLenStat1To128ResDelta" },
      { name: "Packet Length Stat 129 To 256 (response)", key: "pktLenStat129To256ResDelta" },
      { name: "Packet Length Stat 257 To 512 (response)", key: "pktLenStat257To512ResDelta" },
      { name: "Packet Length Stat 513 To 1024 (response)", key: "pktLenStat513To1024ResDelta" },
      { name: "Packet Length Stat 1025 To 1514 (response)", key: "pktLenStat1025To1514ResDelta" },
      { name: "Packet Length Stat Jumbo (response)", key: "pktLenStatJumboResDelta" }
    ]
  }];

/**
 * flowstat static
 * 
 * @return
 */
export function staticFlowstat(reqNum) {
  const formatData = {
    "Eth_Total": 1,
    "Eth_Conversation": 8,
    "Eth_Endpoint": 15,
    "IP_Total": 2,
    "IP_IP": 2,
    "IP_Conversation": 9,
    "IP_Endpoint": 16,
    "IP_TCP_Total": 4,
    "IP_TCP_TCP": 4,
    "IP_TCP_IP": 4,
    "IP_TCP_Conversation": 10,
    "IP_TCP_Endpoint": 17,
    "IP_UDP_Total": 5,
    "IP_UDP_UDP": 5,
    "IP_UDP_IP": 5,
    "IP_UDP_Conversation": 11,
    "IP_UDP_Endpoint": 18,
    "HTTP_Endpoint_Ethernet": 4,
    "HTTP_Endpoint_IP": 5,
    "HTTP_Endpoint_TCP": 6,
    "HTTP_Conversation_Ethernet": 1,
    "HTTP_Conversation_IP": 2,
    "HTTP_Conversation_TCP": 3
  };

  return formatData[reqNum];
}

/**
 * uniqId(랜덤 아이디 값 생성)
 * 
 * @return
 */
export function uniqId() {
  return Math.round(new Date().getTime() + (Math.random() * 100));
}

/**
 * comma(세 자리 수 콤마 찍기)
 * 
 * @return
 */
export function numberWithCommas(x) {
  x = x || x === 0 ? x.toString() : '';
  var regx = new RegExp(/(-?\d+)(\d{3})/);
  var bExists = x.indexOf(".", 0);
  var strArr = x.split('.');
  while (regx.test(strArr[0])) {
    strArr[0] = strArr[0].replace(regx, "$1,$2");
  }
  if (bExists > -1) {
    x = strArr[0] + "." + strArr[1];
  } else {
    x = strArr[0];
  }

  return x;
}

/**
 * comma(세 자리 수 콤마 삭제)
 * 
 * @return
 */
export function removeWithCommas(x) {
  return x.toString().replace(/[^\d]+/g, '');
}

/**
 * escape(문자열 치환)
 * 
 * @return
 */
export function escapeHtml(str) {
  if (str) return str.replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&#39;/g, "'").replace(/&#34;/g, '"').replace(/&#35;/g, '#').replace(/&amp;/g, '&').replace(/&quot;/g, '"');
  else return '';
}

/**
 * random(랜덤 값 생성)
 * 
 * @return
 */
export function randomArray(arr) {
  const index = Math.round(Math.random() * (arr.length - 1));
  return arr[index];
}

export function getNs(str) {
  if (str) {
    str = str.toString();
    var posPoint = str.indexOf('.');
    return (parseInt(str.substring(0, posPoint)) * 1000000000) + parseInt(str.substring(posPoint + 1));
  } else {
    return "";
  }
}

export function setNewTimeFromStr(date, str) {
  date.setTime(Math.floor(getNs(str) / 1000000));
  return date;
}

export function getTimeStampStrNs(nsec) {
  if (isNaN(nsec)) return '0.000000000';

  var secStr = Math.floor(nsec / 1000000000) + '.';
  var nsecStr = '' + (nsec % 1000000000);
  var nsecPrefix = '';

  for (var i = 9 - nsecStr.length; i > 0; --i)
    nsecPrefix += '0';

  return secStr + nsecPrefix + nsecStr;
}

export function timeSubNs(strSecA, strSecB) {
  return timeDeltaNs(strSecA, strSecB);
}

export function timeSubSec(strSecA, strSecB) {
  return timeDeltaSec(strSecA, strSecB);
}

export function timeDeltaNs(strSecBegin, strSecEnd) {
  return getNs(strSecEnd) - getNs(strSecBegin);
}

export function timeDeltaSec(strSecBegin, strSecEnd) {
  return (getNs(strSecEnd) - getNs(strSecBegin)) / 1000000000;
}

export function timeAverageNs(strSec, strCnt) {
  var avg = Math.floor(getNs(strSec) / parseInt(strCnt));
  return getTimeStampStrNs(avg);
}

export function getFloatSec(nsec) {
  return nsec / 1000000;
  //return nsec / 1000000000.0;
}

//Get Country geo_key BY country_id
export function getGeoKeyByCountryId(countryId) {
  let countryCode = '';

  if (countryId === 1) countryCode = 'AF';
  if (countryId === 2) countryCode = 'AL';
  if (countryId === 3) countryCode = 'DZ';
  if (countryId === 4) countryCode = 'AS';
  if (countryId === 5) countryCode = 'AD';
  if (countryId === 6) countryCode = 'AO';
  if (countryId === 7) countryCode = 'AI';
  if (countryId === 8) countryCode = 'AQ';
  if (countryId === 9) countryCode = 'AG';
  if (countryId === 10) countryCode = 'AR';
  if (countryId === 11) countryCode = 'AM';
  if (countryId === 12) countryCode = 'AW';
  if (countryId === 13) countryCode = 'AU';
  if (countryId === 14) countryCode = 'AT';
  if (countryId === 15) countryCode = 'AZ';
  if (countryId === 16) countryCode = 'BS';
  if (countryId === 17) countryCode = 'BH';
  if (countryId === 18) countryCode = 'BD';
  if (countryId === 19) countryCode = 'BB';
  if (countryId === 20) countryCode = 'BY';
  if (countryId === 21) countryCode = 'BE';
  if (countryId === 22) countryCode = 'BZ';
  if (countryId === 23) countryCode = 'BJ';
  if (countryId === 24) countryCode = 'BM';
  if (countryId === 25) countryCode = 'BT';
  if (countryId === 26) countryCode = 'BO';
  if (countryId === 27) countryCode = 'BA';
  if (countryId === 28) countryCode = 'BW';
  if (countryId === 29) countryCode = 'BV';
  if (countryId === 30) countryCode = 'BR';
  if (countryId === 31) countryCode = 'IO';
  if (countryId === 32) countryCode = 'VG';
  if (countryId === 33) countryCode = 'BN';
  if (countryId === 34) countryCode = 'BG';
  if (countryId === 35) countryCode = 'BF';
  if (countryId === 36) countryCode = 'BI';
  if (countryId === 37) countryCode = 'KH';
  if (countryId === 38) countryCode = 'CM';
  if (countryId === 39) countryCode = 'CA';
  if (countryId === 40) countryCode = 'CV';
  if (countryId === 41) countryCode = 'KY';
  if (countryId === 42) countryCode = 'CF';
  if (countryId === 43) countryCode = 'TD';
  if (countryId === 44) countryCode = 'CL';
  if (countryId === 45) countryCode = 'CN';
  if (countryId === 46) countryCode = 'CX';
  if (countryId === 47) countryCode = 'CC';
  if (countryId === 48) countryCode = 'CO';
  if (countryId === 49) countryCode = 'KM';
  if (countryId === 50) countryCode = 'CG';
  if (countryId === 51) countryCode = 'CK';
  if (countryId === 52) countryCode = 'CR';
  if (countryId === 53) countryCode = 'HR';
  if (countryId === 54) countryCode = 'CU';
  if (countryId === 55) countryCode = 'CY';
  if (countryId === 56) countryCode = 'CZ';
  if (countryId === 57) countryCode = 'DK';
  if (countryId === 58) countryCode = 'DJ';
  if (countryId === 59) countryCode = 'DM';
  if (countryId === 60) countryCode = 'DO';
  if (countryId === 61) countryCode = 'EC';
  if (countryId === 62) countryCode = 'EG';
  if (countryId === 63) countryCode = 'SV';
  if (countryId === 64) countryCode = 'GQ';
  if (countryId === 65) countryCode = 'ER';
  if (countryId === 66) countryCode = 'EE';
  if (countryId === 67) countryCode = 'ET';
  if (countryId === 68) countryCode = 'FK';
  if (countryId === 69) countryCode = 'FO';
  if (countryId === 70) countryCode = 'FJ';
  if (countryId === 71) countryCode = 'FI';
  if (countryId === 72) countryCode = 'FR';
  if (countryId === 73) countryCode = 'GF';
  if (countryId === 74) countryCode = 'PF';
  if (countryId === 75) countryCode = 'TF';
  if (countryId === 76) countryCode = 'GA';
  if (countryId === 77) countryCode = 'GM';
  if (countryId === 78) countryCode = 'GE';
  if (countryId === 79) countryCode = 'DE';
  if (countryId === 80) countryCode = 'GH';
  if (countryId === 81) countryCode = 'GI';
  if (countryId === 82) countryCode = 'GR';
  if (countryId === 83) countryCode = 'GL';
  if (countryId === 84) countryCode = 'GD';
  if (countryId === 85) countryCode = 'GP';
  if (countryId === 86) countryCode = 'GU';
  if (countryId === 87) countryCode = 'GT';
  if (countryId === 88) countryCode = 'GG';
  if (countryId === 89) countryCode = 'GN';
  if (countryId === 90) countryCode = 'GW';
  if (countryId === 91) countryCode = 'GY';
  if (countryId === 92) countryCode = 'HT';
  if (countryId === 93) countryCode = 'HM';
  if (countryId === 94) countryCode = 'HN';
  if (countryId === 95) countryCode = 'HK';
  if (countryId === 96) countryCode = 'HU';
  if (countryId === 97) countryCode = 'IS';
  if (countryId === 98) countryCode = 'IN';
  if (countryId === 99) countryCode = 'ID';
  if (countryId === 100) countryCode = 'IR';
  if (countryId === 101) countryCode = 'IQ';
  if (countryId === 102) countryCode = 'IE';
  if (countryId === 103) countryCode = 'IM';
  if (countryId === 104) countryCode = 'IL';
  if (countryId === 105) countryCode = 'IT';
  if (countryId === 106) countryCode = 'JM';
  if (countryId === 107) countryCode = 'JP';
  if (countryId === 108) countryCode = 'JE';
  if (countryId === 109) countryCode = 'JO';
  if (countryId === 110) countryCode = 'KZ';
  if (countryId === 111) countryCode = 'KE';
  if (countryId === 112) countryCode = 'KI';
  if (countryId === 113) countryCode = 'XK';
  if (countryId === 114) countryCode = 'KW';
  if (countryId === 115) countryCode = 'KG';
  if (countryId === 116) countryCode = 'LA';
  if (countryId === 117) countryCode = 'LV';
  if (countryId === 118) countryCode = 'LB';
  if (countryId === 119) countryCode = 'LS';
  if (countryId === 120) countryCode = 'LR';
  if (countryId === 121) countryCode = 'LY';
  if (countryId === 122) countryCode = 'LI';
  if (countryId === 123) countryCode = 'LT';
  if (countryId === 124) countryCode = 'LU';
  if (countryId === 125) countryCode = 'MO';
  if (countryId === 126) countryCode = 'MK';
  if (countryId === 127) countryCode = 'MG';
  if (countryId === 128) countryCode = 'MW';
  if (countryId === 129) countryCode = 'MY';
  if (countryId === 130) countryCode = 'MV';
  if (countryId === 131) countryCode = 'ML';
  if (countryId === 132) countryCode = 'MT';
  if (countryId === 133) countryCode = 'MH';
  if (countryId === 134) countryCode = 'MQ';
  if (countryId === 135) countryCode = 'MR';
  if (countryId === 136) countryCode = 'MU';
  if (countryId === 137) countryCode = 'YT';
  if (countryId === 138) countryCode = 'MX';
  if (countryId === 139) countryCode = 'MD';
  if (countryId === 140) countryCode = 'MC';
  if (countryId === 141) countryCode = 'MN';
  if (countryId === 142) countryCode = 'ME';
  if (countryId === 143) countryCode = 'MS';
  if (countryId === 144) countryCode = 'MA';
  if (countryId === 145) countryCode = 'MZ';
  if (countryId === 146) countryCode = 'MM';
  if (countryId === 147) countryCode = 'NA';
  if (countryId === 148) countryCode = 'NR';
  if (countryId === 149) countryCode = 'NP';
  if (countryId === 150) countryCode = 'NL';
  if (countryId === 151) countryCode = 'NC';
  if (countryId === 152) countryCode = 'NZ';
  if (countryId === 153) countryCode = 'NI';
  if (countryId === 154) countryCode = 'NE';
  if (countryId === 155) countryCode = 'NG';
  if (countryId === 156) countryCode = 'NU';
  if (countryId === 157) countryCode = 'NF';
  if (countryId === 158) countryCode = 'KP';
  if (countryId === 159) countryCode = 'MP';
  if (countryId === 160) countryCode = 'NO';
  if (countryId === 161) countryCode = 'OM';
  if (countryId === 162) countryCode = 'PK';
  if (countryId === 163) countryCode = 'PW';
  if (countryId === 164) countryCode = 'PS';
  if (countryId === 165) countryCode = 'PA';
  if (countryId === 166) countryCode = 'PG';
  if (countryId === 167) countryCode = 'PY';
  if (countryId === 168) countryCode = 'PE';
  if (countryId === 169) countryCode = 'PH';
  if (countryId === 170) countryCode = 'PN';
  if (countryId === 171) countryCode = 'PL';
  if (countryId === 172) countryCode = 'PT';
  if (countryId === 173) countryCode = 'PR';
  if (countryId === 174) countryCode = 'QA';
  if (countryId === 175) countryCode = 'RO';
  if (countryId === 176) countryCode = 'RU';
  if (countryId === 177) countryCode = 'RW';
  if (countryId === 178) countryCode = 'SH';
  if (countryId === 179) countryCode = 'LC';
  if (countryId === 180) countryCode = 'PM';
  if (countryId === 181) countryCode = 'VC';
  if (countryId === 182) countryCode = 'WS';
  if (countryId === 183) countryCode = 'SM';
  if (countryId === 184) countryCode = 'SA';
  if (countryId === 185) countryCode = 'SN';
  if (countryId === 186) countryCode = 'RS';
  if (countryId === 187) countryCode = 'SC';
  if (countryId === 188) countryCode = 'SL';
  if (countryId === 189) countryCode = 'SG';
  if (countryId === 190) countryCode = 'SK';
  if (countryId === 191) countryCode = 'SI';
  if (countryId === 192) countryCode = 'SB';
  if (countryId === 193) countryCode = 'SO';
  if (countryId === 194) countryCode = 'ZA';
  if (countryId === 195) countryCode = 'GS';
  if (countryId === 196) countryCode = 'KR';
  if (countryId === 197) countryCode = 'ES';
  if (countryId === 198) countryCode = 'LK';
  if (countryId === 199) countryCode = 'SD';
  if (countryId === 200) countryCode = 'SR';
  if (countryId === 201) countryCode = 'SJ';
  if (countryId === 202) countryCode = 'SZ';
  if (countryId === 203) countryCode = 'SE';
  if (countryId === 204) countryCode = 'CH';
  if (countryId === 205) countryCode = 'SY';
  if (countryId === 206) countryCode = 'TW';
  if (countryId === 207) countryCode = 'TJ';
  if (countryId === 208) countryCode = 'TZ';
  if (countryId === 209) countryCode = 'TH';
  if (countryId === 210) countryCode = 'TL';
  if (countryId === 211) countryCode = 'TG';
  if (countryId === 212) countryCode = 'TK';
  if (countryId === 213) countryCode = 'TO';
  if (countryId === 214) countryCode = 'TT';
  if (countryId === 215) countryCode = 'TN';
  if (countryId === 216) countryCode = 'TR';
  if (countryId === 217) countryCode = 'TM';
  if (countryId === 218) countryCode = 'TC';
  if (countryId === 219) countryCode = 'TV';
  if (countryId === 220) countryCode = 'VI';
  if (countryId === 221) countryCode = 'UG';
  if (countryId === 222) countryCode = 'UA';
  if (countryId === 223) countryCode = 'AE';
  if (countryId === 224) countryCode = 'GB';
  if (countryId === 225) countryCode = 'US';
  if (countryId === 226) countryCode = 'UY';
  if (countryId === 227) countryCode = 'UZ';
  if (countryId === 228) countryCode = 'VU';
  if (countryId === 229) countryCode = 'VA';
  if (countryId === 230) countryCode = 'VE';
  if (countryId === 231) countryCode = 'VN';
  if (countryId === 232) countryCode = 'WF';
  if (countryId === 233) countryCode = 'EH';
  if (countryId === 234) countryCode = 'YE';
  if (countryId === 235) countryCode = 'ZM';
  if (countryId === 236) countryCode = 'ZW';

  return countryCode;
}

/**
 * 엑셀 내보내기
 * 
 */
export function exportExcelData(excelData, setShowLoader) {
  axiosConf.post("/api/excel/data", excelData, { responseType: 'blob' }).then(res => {
    const name = res.headers['content-disposition'].split('Filename=')[1];
    const url = window.URL.createObjectURL(new Blob([res.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', name);
    link.style.cssText = 'display:none';
    document.body.appendChild(link);
    link.click();
    link.remove();

    setShowLoader(false);
  });
}

/**
 * 그리드 설정 - get Column
 * 
 */
export function getGridFindColumn(gridUrl, gridOptions, setGridState) {
  const getField = gridOptions.api.columnController.gridColumns;
  let setField = [];
  let setChkField = [];

  axiosConf.get('/api/setting/grid/find/' + gridUrl).then(res => {
    let formatIndex = _.map(res.data.index, (value, key) => {
      return {
        colId: key,
        index: value
      };
    });

    formatIndex = _.sortBy(formatIndex, "index");

    _.forEach(formatIndex, (obj) => {
      if (obj.colId !== "userId") {
        const filterFieldName = _.filter(getField, (fieldObj) => {
          return fieldObj.colId === obj.colId;
        });

        if (filterFieldName.length > 0) {
          const flagShow = res.data.grid[obj.colId];

          setField.push({
            colId: obj.colId,
            name: filterFieldName[0].userProvidedColDef.headerName,
            show: flagShow ? flagShow : false
          });

          if (flagShow) {
            setChkField.push(obj.colId);
          }

          // 그리드 적용
          setTimeout(() => {
            gridOptions.columnApi.moveColumn(obj.colId, (obj.index + 2));
            gridOptions.columnApi.setColumnVisible(obj.colId, flagShow);
          }, 0);
        }
      }
    });

    setGridState({ stateField: setField, checkField: setChkField });
  });
}

/**
 * 그리드 설정 - check
 * 
 */
export function handleShowGrid(target, gridOptions, gridState, setGridState) {
  const splitId = target.id.split("_");

  const filterField = _.filter(gridState.stateField, (obj) => {
    return obj.colId === splitId[1];
  });

  filterField[0].show = target.checked;
  gridOptions.columnApi.setColumnVisible(splitId[1], target.checked);

  if (target.checked) {
    gridState.checkField.push(splitId[1]);
  } else {
    gridState.checkField = _.without(gridState.checkField, splitId[1]);
  }

  setGridState({ stateField: gridState.stateField, checkField: gridState.checkField });
}

/**
 * 그리드 설정 - all check
 * 
 */
export function handleAllShowGrid(target, gridOptions, gridState, setGridState) {
  gridState.checkField = [];

  _.forEach(gridState.stateField, (obj) => {
    obj.show = target.checked;
    gridOptions.columnApi.setColumnVisible(obj.colId, target.checked);

    if (target.checked) {
      gridState.checkField.push(obj.colId);
    }
  });

  setGridState({ stateField: gridState.stateField, checkField: gridState.checkField });
}

/**
 * 그리드 설정 - save show/hide
 * 
 */
export function onSaveGridSetting(gridUrl, gridState, handleGridSetClose) {
  let requestData = {};

  _.forEach(gridState.stateField, (obj) => {
    requestData[obj.colId] = obj.show;
  });

  axiosConf.post('/api/setting/grid/update/' + gridUrl, requestData).then(res => {
    handleGridSetClose();
  });
}

/**
 * 그리드 설정 - save index
 * 
 */
export function onSaveIdxGridSetting(gridUrl, gridOptions) {
  const getField = gridOptions.api.columnController.gridColumns;
  let requestData = {};
  let idxNum = 0;

  _.forEach(getField, (obj) => {
    if (!obj.userProvidedColDef.lockPosition) {
      requestData[obj.colId] = idxNum;
      idxNum += 1;
    }
  });

  axiosConf.post('/api/setting/grid/update/index/' + gridUrl, requestData);
}

/**
 * ag-grid cell copy
 * 
 */
export function onCopyGridCell(value) {
  const textarea = document.createElement("textarea");
  document.body.appendChild(textarea);
  textarea.value = value;
  textarea.select();
  document.execCommand("copy");
  document.body.removeChild(textarea);
}

/**
 * Popup Header/Side Menu Component Hide
 * 
 */
export function hiddenComponentPopup() {
  document.getElementsByTagName("header")[0].classList.add("none");
  document.getElementsByClassName("MuiToolbar-root")[1].classList.add("none");
  document.getElementsByClassName("MuiDrawer-root")[0].classList.add("none");
  document.getElementsByTagName("body")[0].style.overflowY = "auto";
  document.getElementsByTagName("body")[0].style.overflowX = "auto";
}

/**
 * 검색 필터 onChange(type: Text)
 * 
 */
export function setInputValue(e, schItem, setSchItem) {
  setSchItem({ ...schItem, [e.target.name]: e.target.value });
}

/**
 * 검색 필터 onChange(type: Checkbox)
 * 
 */
export function setInputChecked(e, schItem, setSchItem) {
  setSchItem({ ...schItem, [e.target.name]: e.target.checked });
}

/**
 * 검색 필터 onChange(type: Multiple Checkbox)
 * 
 */
export function setInputMultiChecked(e, schItem, setSchItem) {
  const splitId = e.target.id.split("-");

  if (e.target.checked) {
    setSchItem({ ...schItem, [e.target.name]: [...schItem[e.target.name], splitId[1]] });
  } else {
    const filterValue = _.filter(schItem[e.target.name], (obj) => {
      return obj !== splitId[1];
    });

    setSchItem({ ...schItem, [e.target.name]: filterValue });
  }
}

/**
 * 검색 필터 onChange(type: All Checkbox)
 * 
 */
export function setInputAllChecked(e, schItem, setSchItem, targetValue) {
  setSchItem({ ...schItem, [e.target.name]: e.target.checked ? targetValue : [] });
}

/**
 * 검색 필터 auto Date
 * 
 */
export function autoDatePicker(schItem, setSchItem, time, unit) {
  const autoDate = time + unit;
  const startDate = autoDate === "1day" ? moment().startOf("day").toDate() : moment().subtract(time, unit).toDate();
  const formatStartDate = moment(startDate).add(1, "seconds").toDate();
  const endDate = moment().toDate();

  setSchItem({
    ...schItem,
    autoDate: autoDate,
    startDate: formatStartDate,
    startHours: moment(formatStartDate).format("HH"),
    startMinutes: moment(formatStartDate).format("mm"),
    startSeconds: moment(formatStartDate).format("ss"),
    endDate: endDate,
    endHours: moment(endDate).format("HH"),
    endMinutes: moment(endDate).format("mm"),
    endSeconds: moment(endDate).format("ss"),
  });
}

export function ipToLong(ip) {
  let result = 0;

  ip.split('.').forEach(function (item) {
    result <<= 8;
    result += parseInt(item);
  })

  return (result >>> 0);
}

export function longToIp(num) {
  return ((num >>> 24) + '.' + (num >> 16 & 255) + '.' + (num >> 8 & 255) + '.' + (num & 255));
}

/**
 * 공인,사설 Ip Check
 * 
 * @return
 * 공인 = false
 * 사설 = true
 * 
 */
export function ipTypeCheck(num) {
  var result = false;
  // check 10.0.0.0 ~ 10.255.255.255
  if (167772160 <= num && num <= 184549375) result = false;
  // check 172.16.0.0 ~ 172.31.255.255
  else if (2886729728 <= num && num <= 2887778303) result = false;
  // check 192.168.0.0 ~ 192.168.255.255
  else if (3232235520 <= num && num <= 3232301055) result = false;
  // 공인 IP
  else result = true;

  return result;
}