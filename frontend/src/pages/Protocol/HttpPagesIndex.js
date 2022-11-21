import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { useLocation, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import {
    Grid, Button, Box, Typography, Menu, ListItemText, Divider, Checkbox, FormControl, FormControlLabel, Alert, Snackbar, Slide,
    MenuItem, Card, CardHeader, CardContent, Badge, Tooltip
} from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faQuestionCircle } from "@fortawesome/free-solid-svg-icons";
import { SettingOutlined, DownloadOutlined, CheckCircleOutlined, TableOutlined, DotChartOutlined, CaretDownOutlined, ReloadOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import {
    numberWithCommas, getGridFindColumn, handleShowGrid, handleAllShowGrid, onSaveGridSetting, onSaveIdxGridSetting, onCopyGridCell, gridApiObj, gridSetMenuStyles,
    getGeoKeyByCountryId, scrollGridCnt, timeDeltaSec, exportExcelData
} from '../../lib/common';
import Loader from '../../components/Loader';
import EchartsComponent from '../../lib/EchartsComponent';
import SearchCommonDetail from '../Common/Search/SearchCommonDetail';
import ModalSearchFormResources from '../Common/Modal/ModalSearchFormResources';
import ModalSearchFormPages from '../Common/Modal/ModalSearchFormPages';

const pageResources = [{
    group: false,
    children: [
        { name: "Mbps", key: "mbps" },
        { name: "URI Count", key: "uriCnt" },
        { name: "HTTP Error (%)", key: "httpError" },
        { name: "TCP Error (%)", key: "tcpError" }
    ]
},
{
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
        { name: "Page Request Making Min", key: "tsPageReqMakingMin" },
        { name: "Page Request Making Max", key: "tsPageReqMakingMax" },
        { name: "Stopped Transaction Count", key: "stoppedTransactionCnt" }
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
        { name: "Page Connection RTT Count (request)", key: "pageRttConnCntReq" },
        { name: "Page Connection RTT Count (response)", key: "pageRttConnCntRes" },
        { name: "Page RTT Connection Min (request)", key: "tsPageRttConnReqMin" },
        { name: "Page RTT Connection Min (response)", key: "tsPageRttConnResMin" },
        { name: "Page RTT Connection Max (request)", key: "tsPageRttConnReqMax" },
        { name: "Page RTT Connection Max (response)", key: "tsPageRttConnResMax" },
        { name: "Page ACK RTT (request)", key: "tsPageRttAckSumReq" },
        { name: "Page ACK RTT (response)", key: "tsPageRttAckSumRes" },
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
        { name: "Session Count", key: "pageSessionCnt" },
        { name: "Connection Error Session Count", key: "connErrSessionCnt" },
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

const HttpPagesIndex = () => {
    const localGetViewType = localStorage.getItem("pageViewType");
    const resetSchItem = {
        startDate: moment().subtract('59', 'seconds').toDate(),
        startHours: moment().subtract('59', 'seconds').format("HH"),
        startMinutes: moment().subtract('59', 'seconds').format("mm"),
        startSeconds: moment().subtract('59', 'seconds').format("ss"),
        endDate: moment().toDate(),
        endHours: moment().format("HH"),
        endMinutes: moment().format("mm"),
        endSeconds: moment().format("ss"),
        autoDate: "1minutes",
        /* 상세 */
        srcZone: [],
        dstZone: [],
        bothZone: [],
        isTextSrcIp: false,
        srcIp: [],
        srcIpTxt: "",
        isTextDstIp: false,
        dstIp: [],
        dstIpTxt: "",
        isTextBothIp: false,
        bothIp: [],
        bothIpTxt: "",
        isTextSrcPort: false,
        srcPort: [],
        srcPortTxt: "",
        isTextDstPort: false,
        dstPort: [],
        dstPortTxt: "",
        isTextBothPort: false,
        bothPort: [],
        bothPortTxt: "",
        resTimeRangeChk: false,
        resTimeSingleVal: "",
        resTimeSingleUnit: "goe",
        resTimeRangeFirstVal: "",
        resTimeRangeFirstUnit: "goe",
        resTimeRangeLastVal: "",
        resTimeRangeLastUnit: "loe",
        isTextResponseCode: false,
        responseCode: [],
        responseCodeTxt: "",
        httpMethod: [],
        application: [],
        isTextPage: false,
        page: [],
        pageTxt: "",
        isTextUseragent: false,
        useragent: [],
        useragentTxt: "",
        isTextHost: false,
        host: [],
        hostTxt: "",
        isTextReferer: false,
        referer: [],
        refererTxt: "",
        browser: [],
        hardware: [],
        os: [],
        platform: [],
        protocol: [],
        transaction: []
    };

    const location = useLocation();
    const navigate = useNavigate();
    const { responseCode } = useSelector((state) => state.staticVar);

    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);
    const [searchOpen, setSearchOpen] = useState(false);

    const [showLoader, setShowLoader] = useState(false);

    const [totalCnt, setTotalCnt] = useState(0);
    const [policyData, setPolicyData] = useState([]);
    const [gridState, setGridState] = useState({
        stateField: [],
        checkField: []
    });

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [anchorEl, setAnchorEl] = useState(null);
    const anchorOpen = Boolean(anchorEl);

    const [viewTypeEl, setViewTypeEl] = useState(null);
    const viewTypeOpen = Boolean(viewTypeEl);
    const [viewType, setViewType] = useState(localGetViewType ? localGetViewType : "Chart");

    const [currentPage, setCurrentPage] = useState(0);
    const [maxPage, setMaxPage] = useState(0);

    const [resourceOpen, setResourceOpen] = useState(false);
    const [selectResource, setSelectResource] = useState([{ name: "Page Response Time", key: "tsPage" }]);

    const [chartData, setChartData] = useState([]);
    const [chartHeight, setChartHeight] = useState(0);
    const [chartOption, setChartOption] = useState({});

    const [snapDate, setSnapDate] = useState("");
    const [snapGridFlag, setSnapGridFlag] = useState(false);
    const [snapGridData, setSnapGridData] = useState([]);

    const [policyColumnDefs] = useState([{
        headerName: 'Level',
        field: 'level',
        maxWidth: 80,
        cellClass: ['text-center', 'cursorp'],
        cellRendererFramework: (params) => {
            if (params.value === 1) {
                return <Badge badgeContent={"Level-1"} color="success"></Badge>;
            } else if (params.value === 2) {
                return <Badge badgeContent={"Level-2"} classes={{ badge: "bg-yellow font-white" }}></Badge>;
            } else if (params.value === 3) {
                return <Badge badgeContent={"Level-3"} classes={{ badge: "bg-orange font-white" }}></Badge>;
            } else if (params.value === 4) {
                return <Badge badgeContent={"Level-4"} classes={{ badge: "bg-redorange font-white" }}></Badge>;
            } else if (params.value === 5) {
                return <Badge badgeContent={"Level-5"} classes={{ badge: "bg-danger font-white" }}></Badge>;
            } else {
                return "";
            }
        }
    },
    { headerName: 'Frame Arrival Time', field: 'tsFrameArrival', cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    {
        headerName: 'Src Geo',
        field: 'srcGeo',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            try {
                const resGeoCode = _.lowerCase(getGeoKeyByCountryId(params.data.countryIdReq));
                const requireGeoJson = require('../../images/country_icon/' + resGeoCode + '.png');
                return <><img src={requireGeoJson.default} style={{ marginRight: 5 }} />{params.value}</>;
            } catch {
                return params.value;
            }
        },
        filter: "agTextColumnFilter"
    },
    { headerName: 'Src Domestic', field: 'srcDomestic', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Src ISP', field: 'ispNameReq', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Src IDC', field: 'idcNameReq', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Src IP', field: 'srcIp', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Src Port', field: 'srcPort', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    {
        headerName: 'Dst Geo',
        field: 'dstGeo',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            try {
                const resGeoCode = _.lowerCase(getGeoKeyByCountryId(params.data.countryIdRes));
                const requireGeoJson = require('../../images/country_icon/' + resGeoCode + '.png');
                return <><img src={requireGeoJson.default} style={{ marginRight: 5 }} />{params.value}</>;
            } catch {
                return params.value;
            }
        },
        filter: "agTextColumnFilter"
    },
    { headerName: 'Dst Domestic', field: 'dstDomestic', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Dst ISP', field: 'ispNameRes', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Dst IDC', field: 'idcNameRes', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Dst IP', field: 'dstIp', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Dst Port', field: 'dstPort', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    {
        headerName: 'Response Code',
        field: 'httpResCode',
        cellClass: ['text-center', 'cursorp'],
        cellRendererFramework: (params) => {
            let badgeColor = "";

            if (params.value < 300) {
                badgeColor = "success";
            } else if (params.value >= 300 && params.value < 400) {
                badgeColor = "info";
            } else if (params.value >= 400 && params.value < 500) {
                badgeColor = "danger";
            } else if (params.value >= 500) {
                badgeColor = "warning";
            }

            return <>
                {
                    params.value && <Badge badgeContent={params.value} classes={{ badge: "badge-" + badgeColor }} max={999}></Badge>
                }
                {
                    params.data.httpResCodeDesc && <Tooltip title={params.data.httpResCodeDesc}>
                        <FontAwesomeIcon icon={faQuestionCircle} className="font-primary cursorp" style={params.value ? { marginLeft: "1rem" } : {}} />
                    </Tooltip>
                }
            </>;
        },
        filter: "agTextColumnFilter"
    },
    { headerName: 'Host', field: 'httpHost', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: 'Page', width: 400, field: 'page', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    {
        headerName: 'User Agent',
        width: 500,
        field: 'httpUserAgent',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            try {
                function getUseragentIcon(category, val) {
                    const useragentNm = _.lowerCase(val).replace(/(\s*)/g, "");
                    const requireUseragent = require('../../images/useragent_icon/' + category + '/' + useragentNm + '.png');
                    return requireUseragent;
                }

                const softwareImg = getUseragentIcon('software', params.data.userAgentSoftwareName);
                const osImg = getUseragentIcon('os', params.data.userAgentOperatingSystemName);

                return <>
                    <img src={softwareImg.default} style={{ width: "1.1rem", marginRight: 3 }} />
                    <img src={osImg.default} style={{ width: "1.1rem", marginRight: 5 }} />{params.value}
                </>;
            } catch {
                return params.value;
            }
        },
        filter: "agTextColumnFilter"
    },
    { headerName: "Page Response Time", field: "tsPage", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Mbps", field: "mbps", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "URI Count", field: "uriCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Session Count", field: "sessionCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Connection Error Session Count", field: "connErrSessionCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Stopped Transaction Count", field: "stoppedTransactionCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Connection Error Packet Count", field: "connErrPktCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Error", field: "tcpError", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Error", field: "httpError", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Src Mac", field: "srcMac", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dst Mac", field: "dstMac", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Method", field: "httpMethod", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Version", field: "httpVersion", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Version (request)", field: "httpVersionReq", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Version (response)", field: "httpVersionRes", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Res Phrase", field: "httpResPhrase", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Content Type", field: "httpContentType", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Cookie", field: "httpCookie", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Location", field: "httpLocation", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP URI", field: "httpUri", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP URI Split", field: "httpUriSplit", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Referer", field: "httpReferer", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Content Length", field: "httpContentLength", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Code 2xx Count", field: "resCode2xxCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Code 3xx Count", field: "resCode3xxCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Code 401 Count", field: "resCode401Cnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Code 404 Count", field: "resCode404Cnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Code 4xx Count", field: "resCode4xxCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Code 5xx Count", field: "resCode5xxCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Code other Count", field: "resCodeOthCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Application", field: "applicationName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "DPI Protocol App", field: "ndpiProtocolApp", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "DPI Protocol Master", field: "ndpiProtocolMaster", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Software Name", field: "userAgentSoftwareName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating System Name", field: "userAgentOperatingSystemName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating Platform", field: "userAgentOperatingPlatform", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Hardware Type", field: "userAgentHardwareType", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Frame Landoff Time", field: "tsFrameLandoff", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Begin Time", field: "tsPageBegin", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page End Time", field: "tsPageEnd", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Request SYN Time", field: "tsPageReqSyn", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Gap Time", field: "tsPageGap", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Response Init Time", field: "tsPageResInit", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Response Init Gap Time", field: "tsPageResInitGap", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Response App Time", field: "tsPageResApp", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Response App Gap Time", field: "tsPageResAppGap", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Response Response Time", field: "tsPageRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Response Gap Time", field: "tsPageResGap", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Transfer Request Time", field: "tsPageTransferReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Transfer Request Gap Time", field: "tsPageTransferReqGap", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Transfer Response Time", field: "tsPageTransferRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Transfer Response Gap Time", field: "tsPageTransferResGap", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Connection RTT (request)", field: "tsPageRttConnSumReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Connection RTT (response)", field: "tsPageRttConnSumRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT (request)", field: "tsPageRttAckSumReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT (response)", field: "tsPageRttAckSumRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Request Making Time", field: "tsPageReqMakingSum", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Connection RTT Count (request)", field: "pageRttConnCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Connection RTT Count (response)", field: "pageRttConnCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT Count (request)", field: "pageRttAckCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT Count (response)", field: "pageRttAckCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Request Making Count", field: "pageReqMakingCnt", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Length (request)", field: "pageHttpLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Length (response)", field: "pageHttpLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Count (request)", field: "pageHttpCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Count (response)", field: "pageHttpCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Length (request)", field: "pagePktLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Length (response)", field: "pagePktLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Count (request)", field: "pagePktCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Count (response)", field: "pagePktCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Length (request)", field: "pageTcpLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Length (response)", field: "pageTcpLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Count (request)", field: "pageTcpCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Count (response)", field: "pageTcpCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Connection Error Session Length (request)", field: "reqConnErrSessionLen", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Connection Error Session Length (response)", field: "resConnErrSessionLen", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTT Connection Min (request)", field: "tsPageRttConnReqMin", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTT Connection Min (response)", field: "tsPageRttConnResMin", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTT Connection Max (request)", field: "tsPageRttConnReqMax", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTT Connection Max (response)", field: "tsPageRttConnResMax", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT Min (request)", field: "tsPageRttAckReqMin", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT Min (response)", field: "tsPageRttAckResMin", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT Max (request)", field: "tsPageRttAckReqMax", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page ACK RTT Max (response)", field: "tsPageRttAckResMax", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Request Making Min", field: "tsPageReqMakingMin", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page Request Making Max", field: "tsPageReqMakingMax", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTO (request)", field: "tsPageRtoSumReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTO (response)", field: "tsPageRtoSumRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTO Count (request)", field: "tsPageRtoCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Page RTO Count (response)", field: "tsPageRtoCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Retransmission Count (request)", field: "retransmissionCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Retransmission Count (response)", field: "retransmissionCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Retransmission Length (request)", field: "retransmissionLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Retransmission Length (response)", field: "retransmissionLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Fast Retransmission Count (request)", field: "fastRetransmissionCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Fast Retransmission Count (response)", field: "fastRetransmissionCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Fast Retransmission Length (request)", field: "fastRetransmissionLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Fast Retransmission Length (response)", field: "fastRetransmissionLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Out of Order Count (request)", field: "outOfOrderCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Out of Order Count (response)", field: "outOfOrderCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Out of Order Length (request)", field: "outOfOrderLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Out of Order Length (response)", field: "outOfOrderLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Lost Segment Count (request)", field: "lostSegCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Lost Segment Count (response)", field: "lostSegCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Lost Segment Length (request)", field: "lostSegLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Lost Segment Length (response)", field: "lostSegLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK Lost Count (request)", field: "ackLostCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK Lost Count (response)", field: "ackLostCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK Lost Length (request)", field: "ackLostLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK Lost Length (response)", field: "ackLostLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Count (request)", field: "winUpdateCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Count (response)", field: "winUpdateCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Length (request)", field: "winUpdateLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Length (response)", field: "winUpdateLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup ACK Count (request)", field: "dupAckCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup ACK Count (response)", field: "dupAckCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup ACK Length (request)", field: "dupAckLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup ACK Length (response)", field: "dupAckLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Count (request)", field: "zeroWinCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Count (response)", field: "zeroWinCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Length (request)", field: "zeroWinLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Length (response)", field: "zeroWinLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Spurious Retransmission Count (request)", field: "spuriousRetransmissionCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Spurious Retransmission Count (response)", field: "spuriousRetransmissionCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Spurious Retransmission Length (request)", field: "spuriousRetransmissionLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Spurious Retransmission Length (response)", field: "spuriousRetransmissionLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Count (request)", field: "overlapCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Count (response)", field: "overlapCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Length (request)", field: "overlapLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Length (response)", field: "overlapLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Attack Count (request)", field: "overlapAttackCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Attack Count (response)", field: "overlapAttackCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Attack Length (request)", field: "overlapAttackLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Overlap Attack Length (response)", field: "overlapAttackLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Probe Count (request)", field: "zeroWinProbeCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Probe Count (response)", field: "zeroWinProbeCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Probe Length (request)", field: "zeroWinProbeLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Probe Length (response)", field: "zeroWinProbeLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window ACK Probe Count (request)", field: "zeroWinProbeAckCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window ACK Probe Count (response)", field: "zeroWinProbeAckCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window ACK Probe Length (request)", field: "zeroWinProbeAckLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window ACK Probe Length (response)", field: "zeroWinProbeAckLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Count (request)", field: "keepAliveCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Count (response)", field: "keepAliveCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Length (request)", field: "keepAliveLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Length (response)", field: "keepAliveLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive ACK Count (request)", field: "keepAliveAckCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive ACK Count (response)", field: "keepAliveAckCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive ACK Length (request)", field: "keepAliveAckLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive ACK Length (response)", field: "keepAliveAckLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Src As", field: "asNameReq", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dst As", field: "asNameRes", cellClass: ['cursorp'], filter: "agTextColumnFilter" }
    ]);

    useEffect(() => {
        // Location State Clear
        if (location.state && location.state.schItem) {
            window.history.replaceState({}, "/protocol/http/pages/detail");
        }

        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        localStorage.setItem("pageViewType", viewType);

        if (viewType === "Grid" && policyData.length === 0) {
            getPageData(schItem);
        } else if (viewType === "Chart" && chartData.length === 0) {
            getPageData(schItem);
        }

        handleResize();
    }, [viewType]);

    useEffect(() => {
        if (snapGridFlag) {
            handleResize();
        }
    }, [snapGridFlag]);

    const autoComponentSize = useCallback(() => {
        if (document.getElementById('policyNoticeGrid')) {
            const mainHeight = document.body.clientHeight - document.getElementById("searchEl").clientHeight;
            document.getElementById('policyNoticeGrid').style.height = mainHeight - 40 + 'px';
            setChartHeight(mainHeight - 40);
        }

        handleResize();
    }, []);

    const formatZoneData = (targetKey, requestData, targetData) => {
        let selectedData = {
            isp: [],
            idc: [],
            continent: [],
            country: [],
            primary: [],
            sub1: [],
            sub2: []
        };

        _.forEach(targetData[targetKey], (obj) => {
            if (obj.ispId) {
                selectedData.isp.push(parseFloat(obj.ispId));
            } else if (obj.idcId) {
                selectedData.idc.push(parseFloat(obj.idcId));
            } else if (obj.continentId) {
                selectedData.continent.push(parseFloat(obj.continentId));
            } else if (obj.countryId) {
                selectedData.country.push(parseFloat(obj.countryId));
            } else if (obj.primaryId && obj.sub1Id && obj.id) {
                selectedData.sub2.push(parseFloat(obj.id));
            } else if (obj.primaryId && obj.id) {
                selectedData.sub1.push(parseFloat(obj.id));
            } else {
                selectedData.primary.push(parseFloat(obj.id));
            }
        });

        if (selectedData.isp.length > 0) {
            requestData[targetKey + "Isp"] = selectedData.isp;
        }

        if (selectedData.idc.length > 0) {
            requestData[targetKey + "Idc"] = selectedData.idc;
        }

        if (selectedData.continent.length > 0) {
            requestData[targetKey + "Continent"] = selectedData.continent;
        }

        if (selectedData.country.length > 0) {
            requestData[targetKey + "Country"] = selectedData.country;
        }

        if (selectedData.primary.length > 0) {
            requestData[targetKey + "Primary"] = selectedData.primary;
        }

        if (selectedData.sub1.length > 0) {
            requestData[targetKey + "Sub1"] = selectedData.sub1;
        }

        if (selectedData.sub2.length > 0) {
            requestData[targetKey + "Sub2"] = selectedData.sub2;
        }
    }

    const formatIsTxtDefaultData = (targetKey, requestData, targetData) => {
        const lowerKey = _.lowerFirst(targetKey);

        requestData["isText" + targetKey] = targetData["isText" + targetKey];

        if (targetData["isText" + targetKey]) {
            if (targetKey === "SrcPort" || targetKey === "DstPort" || targetKey === "BothPort" || targetKey === "ResponseCode") {
                requestData["text" + targetKey] = parseFloat(targetData[lowerKey + "Txt"]) || null;
            } else {
                requestData["text" + targetKey] = targetData[lowerKey + "Txt"] || null;
            }
        } else {
            if (targetData[lowerKey].length > 0) {
                requestData[lowerKey] = _.map(targetData[lowerKey], "name");
            }
        }
    }

    const formatSchReqData = (targetData) => {
        let requestData = {
            type: _.lowerFirst(viewType),
            begin: moment(targetData.startDate).format("YYYYMMDD") + targetData.startHours + targetData.startMinutes + targetData.startSeconds,
            end: moment(targetData.endDate).format("YYYYMMDD") + targetData.endHours + targetData.endMinutes + targetData.endSeconds
        };

        // Zone
        formatZoneData("srcZone", requestData, targetData);
        formatZoneData("dstZone", requestData, targetData);
        formatZoneData("bothZone", requestData, targetData);

        // IP
        formatIsTxtDefaultData("SrcIp", requestData, targetData);
        formatIsTxtDefaultData("DstIp", requestData, targetData);
        formatIsTxtDefaultData("BothIp", requestData, targetData);

        // Port
        formatIsTxtDefaultData("SrcPort", requestData, targetData);
        formatIsTxtDefaultData("DstPort", requestData, targetData);
        formatIsTxtDefaultData("BothPort", requestData, targetData);

        // Application
        const appId = _.map(targetData.application, "applicationId");

        if (appId.length > 0) {
            requestData.applicationId = appId;
        }

        // Transaction
        const transactionId = _.map(targetData.transaction, "transactionId");

        if (appId.length > 0) {
            requestData.transactionId = transactionId;
        }

        // Host
        formatIsTxtDefaultData("Host", requestData, targetData);

        // Page
        formatIsTxtDefaultData("Page", requestData, targetData);

        // User Agent
        formatIsTxtDefaultData("Useragent", requestData, targetData);

        // Referer
        formatIsTxtDefaultData("Referer", requestData, targetData);

        // Response Code
        formatIsTxtDefaultData("ResponseCode", requestData, targetData);

        if (requestData.responseCode) {
            requestData.responseCode = _.map(requestData.responseCode, (obj) => {
                return parseFloat(obj);
            });
        }

        // HTTP Method
        if (targetData.httpMethod.length > 0) {
            requestData.method = _.map(targetData.httpMethod, "name");
        }

        // Browser
        if (targetData.browser.length > 0) {
            requestData.browser = _.map(targetData.browser, "name");
        }

        // Hardware
        if (targetData.hardware.length > 0) {
            requestData.hardware = _.map(targetData.hardware, "name");
        }

        // OS
        if (targetData.os.length > 0) {
            requestData.os = _.map(targetData.os, "name");
        }

        // Platform
        if (targetData.platform.length > 0) {
            requestData.platform = _.map(targetData.platform, "name");
        }

        // Protocol
        if (targetData.protocol.length > 0) {
            requestData.protocol = _.map(targetData.protocol, (obj) => {
                return {
                    appId: obj.appId,
                    masterId: obj.masterId
                };
            });
        }

        // Response Time
        requestData.isRangeResTime = targetData.resTimeRangeChk;

        if (targetData.resTimeRangeChk) {
            if (targetData.resTimeRangeFirstVal !== "") {
                requestData.moreUnit = targetData.resTimeRangeFirstUnit;
                requestData.moreVal = parseFloat(targetData.resTimeRangeFirstVal);
            }

            if (targetData.resTimeRangeLastVal !== "") {
                requestData.lessUnit = targetData.resTimeRangeLastUnit;
                requestData.lessVal = parseFloat(targetData.resTimeRangeLastVal);
            }
        } else {
            if (targetData.resTimeSingleVal !== "") {
                requestData.lessUnit = targetData.resTimeSingleUnit;
                requestData.lessVal = parseFloat(targetData.resTimeSingleVal);
            }
        }

        return requestData;
    }

    const getPolicyList = useCallback((e, bridgeData, resetItem) => {
        if (bridgeData) {
            setSchItem({ ...schItem, [bridgeData.target.id]: bridgeData.target.value });
        } else {
            setPolicyData([]);
            setChartData([]);
            setSnapDate("");
            setSnapGridFlag(false);

            const targetData = resetItem ? resetItem : schItem;
            getPageData(targetData);
        }
    }, [schItem, viewType, selectResource]);

    const getPageData = (targetData) => {
        setShowLoader(true);

        let requestData = formatSchReqData(targetData);

        // Grid Size
        if (requestData.type === "grid") {
            requestData.limit = scrollGridCnt;
            requestData.offset = 0;
        }

        axiosConf.post('/api/protocol/http/find', requestData).then(res => {
            formatPageCurrData(res.data.results || res.data);

            if (requestData.type === "grid") {
                if (gridApiObj.policyNoticeGridApi) {
                    gridApiObj.policyNoticeGridApi.api.ensureIndexVisible(0, 'top');
                }

                const maxPage = _.floor(res.data.total / scrollGridCnt);

                setTotalCnt(res.data.total);
                setMaxPage(maxPage);
                setCurrentPage(0);
                setPolicyData(res.data.results);
            } else {
                setChartData(res.data);
                setTotalCnt(res.data.length);
                createScatterChart(res.data, selectResource[0].key);
            }

            setShowLoader(false);
        });
    }

    const createScatterChart = (resData, targetKey) => {
        let cloneOptions = _.cloneDeep(scatterOption());

        _.forEach(resData, (obj) => {
            cloneOptions.series[obj.level].data.push([obj.timeFrameArrival, obj[targetKey], obj]);
        });

        setChartOption(cloneOptions);
    }

    const scatterOption = () => {
        return {
            xAxis: {
                type: 'time',
                boundaryGap: false,
                axisLabel: {
                    formatter: (value) => {
                        return moment(value).format('MM-DD') + "\n" + moment(value).format('HH:mm:ss');
                    }
                }
            },
            yAxis: {},
            tooltip: {
                trigger: 'item',
                axisPointer: {
                    type: "cross"
                },
                formatter: (params) => {
                    return `${params.data[2].tsFrameArrival}<br/>${params.data[2].page}<br/>${params.marker} ${params.seriesName}<b>${params.data[1]}</b><br/>`;
                }
            },
            series: [{
                type: 'scatter',
                name: "Level-미지정",
                symbolSize: 3,
                color: "#6dd0ff",
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },
                data: []
            },
            {
                type: 'scatter',
                name: "Level-1",
                symbolSize: 3,
                color: "#a0dd72",
                data: []
            },
            {
                type: 'scatter',
                name: "Level-2",
                symbolSize: 3,
                color: "#ffd042",
                data: []
            },
            {
                type: 'scatter',
                name: "Level-3",
                symbolSize: 3,
                color: "#fc9b2c",
                data: []
            },
            {
                type: 'scatter',
                name: "Level-4",
                symbolSize: 3,
                color: "#ff7434",
                data: []
            },
            {
                type: 'scatter',
                name: "Level-5",
                symbolSize: 3,
                color: "#e62020",
                data: []
            }]
        };
    }

    const formatPageCurrData = (data) => {
        _.forEach(data, (obj) => {
            const errUriCnt = obj.resCode401Cnt + obj.resCode404Cnt + obj.resCode4xxCnt + obj.resCode5xxCnt + obj.resCodeOthCnt;
            const successUriCnt = obj.resCode3xxCnt + obj.resCode2xxCnt;
            const mbps = (((obj.pagePktLenReq + obj.pagePktLenRes) * 8) / timeDeltaSec(obj.realtimePageKey.tsFrameArrival.toFixed(9), obj.realtimePageKey.tsFrameLandOff.toFixed(9))) / 1000000;
            const httpError = (errUriCnt / (successUriCnt + errUriCnt)) * 100;
            const tcpErrorCnt = obj.retransmissionCntReq + obj.retransmissionCntRes + obj.fastRetransmissionCntReq + obj.fastRetransmissionCntRes +
                obj.outOfOrderCntReq + obj.outOfOrderCntRes + obj.lostSegCntReq + obj.lostSegCntRes +
                obj.ackLostCntReq + obj.ackLostCntRes + obj.winUpdateCntReq + obj.winUpdateCntRes +
                obj.dupAckCntReq + obj.dupAckCntRes + obj.zeroWinCntReq + obj.zeroWinCntRes +
                obj.spuriousRetransmissionCntReq + obj.spuriousRetransmissionCntRes + obj.overlapCntReq + obj.overlapCntRes +
                obj.overlapAttackCntReq + obj.overlapAttackCntRes + obj.zeroWinProbeCntReq + obj.zeroWinProbeCntRes +
                obj.zeroWinProbeAckCntReq + obj.zeroWinProbeAckCntRes + obj.keepAliveCntReq + obj.keepAliveCntRes +
                obj.keepAliveAckCntReq + obj.keepAliveAckCntRes;
            const tcpError = (tcpErrorCnt / (obj.pagePktCntReq + obj.pagePktCntRes)) * 100;
            const httpResCodeDesc = responseCode[obj.httpResCode] || [];

            obj.tsFrameLandoff = obj.realtimePageKey.tsFrameLandOff ? moment(obj.realtimePageKey.tsFrameLandOff * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsFrameArrival = obj.realtimePageKey.tsFrameArrival ? moment(obj.realtimePageKey.tsFrameArrival * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.timeFrameArrival = obj.realtimePageKey.tsFrameArrival ? parseFloat(moment(obj.realtimePageKey.tsFrameArrival * 1000).format("x")) : "";
            obj.tsPageBegin = obj.tsPageBegin ? moment(obj.tsPageBegin * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsPageEnd = obj.tsPageEnd ? moment(obj.tsPageEnd * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsPageReqSyn = obj.tsPageReqSyn ? moment(obj.tsPageReqSyn * 1000).format("YYYY-MM-DD HH:mm:ss") : "";

            obj.srcIp = obj.realtimePageKey.srcIp;
            obj.srcPort = obj.realtimePageKey.srcPort;
            obj.dstIp = obj.realtimePageKey.dstIp;
            obj.dstPort = obj.realtimePageKey.dstPort;
            // Level 필드 새로 정의된 걸로 수정
            obj.level = obj.levelOverall;
            obj.srcGeo = obj.continentNameReq ? obj.continentNameReq + ">" + obj.countryNameReq : "";
            obj.srcDomestic = obj.domesticPrimaryNameReq ? obj.domesticPrimaryNameReq + ">" + obj.domesticSub1NameReq + ">" + obj.domesticSub2NameReq : "";
            obj.dstGeo = obj.continentNameRes ? obj.continentNameRes + ">" + obj.countryNameRes : "";
            obj.dstDomestic = obj.domesticPrimaryNameRes ? obj.domesticPrimaryNameRes + ">" + obj.domesticSub1NameRes + ">" + obj.domesticSub2NameRes : "";
            obj.page = obj.httpUri + obj.httpUriSplit;
            obj.ispNameReq = obj.ispNameReq ? obj.ispNameReq + "(" + obj.ispNameEngReq + ")" : "";
            obj.idcNameReq = obj.idcNameReq ? obj.idcNameReq + "(" + obj.idcNameEngReq + ")" : "";
            obj.ispNameRes = obj.ispNameRes ? obj.ispNameRes + "(" + obj.ispNameEngRes + ")" : "";
            obj.idcNameRes = obj.idcNameRes ? obj.idcNameRes + "(" + obj.idcNameEngRes + ")" : "";
            obj.asNameReq = obj.asNameReq ? obj.asNameReq + "(" + obj.asNameEngReq + ")" : "";
            obj.asNameRes = obj.asNameRes ? obj.asNameRes + "(" + obj.asNameEngRes + ")" : "";
            obj.httpResCodeDesc = httpResCodeDesc.length > 0 ? httpResCodeDesc[0].description + " (" + httpResCodeDesc[0].description1 + "), " + httpResCodeDesc[0].description2 : null;

            obj.tsPage = parseFloat(obj.tsPage.toFixed(6));
            obj.mbps = parseFloat(mbps.toFixed(6));
            obj.uriCnt = successUriCnt + errUriCnt;
            obj.sessionCnt = parseFloat(obj.pageSessionCnt);
            obj.tcpError = parseFloat(tcpError.toFixed(2));
            obj.httpError = parseFloat(httpError.toFixed(2));
            obj.tsPageGap = parseFloat(obj.tsPageGap.toFixed(6));
            obj.tsPageResInit = parseFloat(obj.tsPageResInit.toFixed(6));
            obj.tsPageResInitGap = parseFloat(obj.tsPageResInitGap.toFixed(6));
            obj.tsPageResApp = parseFloat(obj.tsPageResApp.toFixed(6));
            obj.tsPageResAppGap = parseFloat(obj.tsPageResAppGap.toFixed(6));
            obj.tsPageRes = parseFloat(obj.tsPageRes.toFixed(6));
            obj.tsPageResGap = parseFloat(obj.tsPageResGap.toFixed(6));
            obj.tsPageTransferReq = parseFloat(obj.tsPageTransferReq.toFixed(6));
            obj.tsPageTransferReqGap = parseFloat(obj.tsPageTransferReqGap.toFixed(6));
            obj.tsPageTransferRes = parseFloat(obj.tsPageTransferRes.toFixed(6));
            obj.tsPageTransferResGap = parseFloat(obj.tsPageTransferResGap.toFixed(6));
            obj.tsPageRttConnSumReq = parseFloat(obj.tsPageRttConnSumReq.toFixed(6));
            obj.tsPageRttConnSumRes = parseFloat(obj.tsPageRttConnSumRes.toFixed(6));
            obj.tsPageRttAckSumReq = parseFloat(obj.tsPageRttAckSumReq.toFixed(6));
            obj.tsPageRttAckSumRes = parseFloat(obj.tsPageRttAckSumRes.toFixed(6));
            obj.tsPageReqMakingSum = parseFloat(obj.tsPageReqMakingSum.toFixed(6));
            obj.tsPageRttConnReqMin = parseFloat(obj.tsPageRttConnReqMin.toFixed(6));
            obj.tsPageRttConnResMin = parseFloat(obj.tsPageRttConnResMin.toFixed(6));
            obj.tsPageRttConnReqMax = parseFloat(obj.tsPageRttConnReqMax.toFixed(6));
            obj.tsPageRttConnResMax = parseFloat(obj.tsPageRttConnResMax.toFixed(6));
            obj.tsPageRttAckReqMin = parseFloat(obj.tsPageRttAckReqMin.toFixed(6));
            obj.tsPageRttAckResMin = parseFloat(obj.tsPageRttAckResMin.toFixed(6));
            obj.tsPageRttAckReqMax = parseFloat(obj.tsPageRttAckReqMax.toFixed(6));
            obj.tsPageRttAckResMax = parseFloat(obj.tsPageRttAckResMax.toFixed(6));
            obj.tsPageReqMakingMin = parseFloat(obj.tsPageReqMakingMin.toFixed(6));
            obj.tsPageReqMakingMax = parseFloat(obj.tsPageReqMakingMax.toFixed(6));
            obj.tsPageRtoSumReq = parseFloat(obj.tsPageRtoSumReq.toFixed(6));
            obj.tsPageRtoSumRes = parseFloat(obj.tsPageRtoSumRes.toFixed(6));
        });
    }

    const addPolicyList = (page) => {
        const offset = page * scrollGridCnt;
        let requestData = formatSchReqData(schItem);

        // Grid Size
        requestData.limit = scrollGridCnt;
        requestData.offset = offset;

        axiosConf.post('/api/protocol/http/find', requestData).then(res => {
            formatPageCurrData(res.data.results);
            setPolicyData([...policyData, ...res.data.results]);
            setShowLoader(false);
        });
    }

    const onScrollLoadEvent = (e) => {
        if (e.target.classList.value.includes("ag-body-viewport") && policyData.length > 0) {
            if ((e.target.scrollTop + e.target.clientHeight) === e.target.scrollHeight) {
                if (maxPage > currentPage) {
                    setShowLoader(true);
                    setCurrentPage(prev => prev + 1);
                    addPolicyList(currentPage + 1);
                }
            }
        }
    }

    const handleSearchOpen = useCallback(() => {
        setSearchOpen(true);
    }, []);

    const handleSearchClose = useCallback(() => {
        setSearchOpen(false);
    }, []);

    const handleResourceOpen = () => setResourceOpen(true);

    const handleResourceClose = useCallback(() => {
        setResourceOpen(false);
    }, []);

    const bridgeModalResource = useCallback((data) => {
        if (data) {
            setSelectResource(data);
            createScatterChart(chartData, data[0].key);
        }
    }, [chartData]);

    const handleGridSetOpen = (event) => {
        setAnchorEl(event.currentTarget);
    }

    const handleGridSetClose = () => {
        setAnchorEl(null);
    }

    const handleViewTypeOpen = (event) => {
        setViewTypeEl(event.currentTarget);
    }

    const handleViewTypeClose = () => {
        setViewTypeEl(null);
    }

    const handleViewType = (type) => {
        setViewType(type);
        setViewTypeEl(null);
    }

    const getGridColumnField = (target) => {
        if (gridApiObj[target]) {
            getGridFindColumn("realtimePage", gridApiObj[target], setGridState);
        }
    }

    const handleCheckboxChange = (e) => {
        if (gridApiObj.policyNoticeGridApi) {
            handleShowGrid(e.target, gridApiObj.policyNoticeGridApi, gridState, setGridState);
        }

        if (gridApiObj.policySnapGridApi) {
            handleShowGrid(e.target, gridApiObj.policySnapGridApi, gridState, setGridState);
        }
    }

    const handleCheckboxAllChange = (e) => {
        if (gridApiObj.policyNoticeGridApi) {
            handleAllShowGrid(e.target, gridApiObj.policyNoticeGridApi, gridState, setGridState);
        }

        if (gridApiObj.policySnapGridApi) {
            handleAllShowGrid(e.target, gridApiObj.policySnapGridApi, gridState, setGridState);
        }
    }

    const onSaveGridField = () => {
        onSaveGridSetting("realtimePage", gridState, handleGridSetClose);
    }

    const onDragStopped = () => {
        if (gridApiObj.policyNoticeGridApi) {
            onSaveIdxGridSetting("realtimePage", gridApiObj.policyNoticeGridApi);
        }

        if (gridApiObj.policySnapGridApi) {
            onSaveIdxGridSetting("realtimePage", gridApiObj.policySnapGridApi);
        }
    }

    const onCellMouseDown = (params) => {
        const { which } = params.event;

        if (which === 3) {
            onCopyGridCell(params.value);

            setTransition(() => TransitionUp);
            setCopySuccess(true);

            setTimeout(() => {
                setCopySuccess(false);
            }, 1000);
        }
    }

    const TransitionUp = (props) => {
        return <Slide {...props} direction="up" />;
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
        getGridColumnField(target);
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) {
                let allColumnIds = [];

                obj.columnApi.getAllColumns().forEach((column) => {
                    if (column.colId !== 'page' && column.colId !== 'httpUserAgent') allColumnIds.push(column.colId);
                });

                obj.columnApi.autoSizeColumns(allColumnIds, false);
            }
        });
    }

    const onCellClicked = (params) => {
        if (params.data) {
            moveToDetail(params.data);
        }
    }

    const moveToDetail = (dataset) => {
        const stateParam = {
            request: {
                pageIdx: dataset.realtimePageKey.pageIdx.toString(),
                tsFrameArrival: dataset.realtimePageKey.tsFrameArrival.toString(),
                tsFrameLandOff: dataset.realtimePageKey.tsFrameLandOff.toString(),
                srcIp: dataset.srcIp,
                dstIp: dataset.dstIp,
                srcPort: dataset.srcPort,
                dstPort: dataset.dstPort
            },
            schItem: schItem
        };

        navigate("/protocol/http/pages/detail", { state: stateParam });
    }

    const onChartEvents = {
        click: (params) => {
            const dataset = params.data[2];
            moveToDetail(dataset);
        },
        datazoom: (params) => {
            const startValue = params.batch[0].startValue;
            const endValue = params.batch[0].endValue;

            if (startValue && endValue) {
                const startDate = moment(startValue).format("YYYY-MM-DD HH:mm:ss");
                const endDate = moment(endValue).format("YYYY-MM-DD HH:mm:ss");

                let filterPickerData = _.filter(chartData, (obj) => {
                    return obj.timeFrameArrival >= startValue && obj.timeFrameArrival <= endValue;
                });

                filterPickerData = _.sortBy(filterPickerData, "timeFrameArrival");

                setSnapDate(startDate + " ~ " + endDate);
                setSnapGridData(filterPickerData);
            } else {
                setSnapDate("");
                setSnapGridFlag(false);
            }
        }
    }

    const handleSnapGrid = () => {
        setSnapGridFlag(!snapGridFlag);
    }

    const onDownloadExcel = () => {
        if (gridApiObj.policyNoticeGridApi) {
            alert("데이터가 많을 시 로딩 시간이 소요됩니다.");
            setShowLoader(true);

            let requestData = formatSchReqData(schItem);
            requestData.type = "excel";

            axiosConf.post('/api/protocol/http/find', requestData).then(res => {
                formatPageCurrData(res.data);

                const getField = gridApiObj.policyNoticeGridApi.columnApi.getDisplayedCenterColumns();
                let excelData = {
                    category: 'page',
                    activeColumnName: [],
                    data: []
                };
                let activeColumnId = [];

                _.forEach(getField, (obj) => {
                    if (obj.colId === "srcGeo") {
                        excelData.activeColumnName.push("SRC 대륙");
                        excelData.activeColumnName.push("SRC 국가");
                        activeColumnId.push("continentNameReq");
                        activeColumnId.push("countryNameReq");
                    } else if (obj.colId === "srcDomestic") {
                        excelData.activeColumnName.push("SRC 시/도");
                        excelData.activeColumnName.push("SRC 시/군/구");
                        excelData.activeColumnName.push("SRC 읍/면/동");
                        activeColumnId.push("domesticPrimaryNameReq");
                        activeColumnId.push("domesticSub1NameReq");
                        activeColumnId.push("domesticSub2NameReq");
                    } else if (obj.colId === "dstGeo") {
                        excelData.activeColumnName.push("DST 대륙");
                        excelData.activeColumnName.push("DST 국가");
                        activeColumnId.push("continentNameRes");
                        activeColumnId.push("countryNameRes");
                    } else if (obj.colId === "dstDomestic") {
                        excelData.activeColumnName.push("DST 시/도");
                        excelData.activeColumnName.push("DST 시/군/구");
                        excelData.activeColumnName.push("DST 읍/면/동");
                        activeColumnId.push("domesticPrimaryNameRes");
                        activeColumnId.push("domesticSub1NameRes");
                        activeColumnId.push("domesticSub2NameRes");
                    } else {
                        excelData.activeColumnName.push(obj.colDef.headerName);
                        activeColumnId.push(obj.colId);
                    }
                });

                _.forEach(res.data, (obj) => {
                    let innerAry = [];

                    _.forEach(activeColumnId, (columnId) => {
                        if (columnId === "level") {
                            if (obj[columnId] === 0) innerAry.push({ value: '', sort: 'left' });
                        } else if (columnId === "srcPort" || columnId === "dstPort" || columnId === "httpResCode") {
                            innerAry.push({ value: obj[columnId], sort: "left" });
                        } else {
                            innerAry.push({ value: obj[columnId], sort: _.isNumber(obj[columnId]) ? "right" : "left" });
                        }
                    });

                    excelData.data.push(innerAry);
                });

                exportExcelData(excelData, setShowLoader);
            });
        }
    }

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);
        getPolicyList(null, null, resetSchItem);
    }, [schItem, viewType, selectResource]);

    // import content
    const SearchContent = useMemo(() => (
        <SearchCommonDetail schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={getPolicyList} resetEvt={resetEvt} handleOpen={handleSearchOpen} />
    ), [schItem, selectResource, viewType]);

    const ModalResourceContent = useMemo(() => (
        <ModalSearchFormResources open={resourceOpen} resourceItem={pageResources} selectResource={selectResource} single={true} onClose={handleResourceClose} onSuccess={bridgeModalResource} />
    ), [resourceOpen]);

    const ModalSearchContent = useMemo(() => (
        <ModalSearchFormPages open={searchOpen} schItem={schItem} onClose={handleSearchClose} setSchItem={setSchItem} searchEvt={getPolicyList} />
    ), [searchOpen, schItem]);

    const ChartsContent = useMemo(() => (
        <>
            {!_.isEmpty(chartOption) && <EchartsComponent option={chartOption} style={{ width: "100%", height: "100%" }} onEvents={onChartEvents} />}
        </>
    ), [chartOption]);

    return (
        <>
            {SearchContent}

            <Grid container>
                <Grid item md={6}>
                    Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(totalCnt)}</Typography>
                </Grid>
                <Grid item md={6} align="right">
                    <Button color="inherit" size="small" startIcon={<SettingOutlined />} onClick={handleGridSetOpen} className={viewType === "Grid" ? "" : "none"}>그리드 설정</Button>
                    <Menu
                        anchorEl={anchorEl}
                        open={anchorOpen}
                        onClose={handleGridSetClose}
                        PaperProps={{
                            style: gridSetMenuStyles
                        }}
                    >
                        <ListItemText>
                            <FormControlLabel control={<Checkbox checked={gridState.stateField.length === gridState.checkField.length}
                                indeterminate={gridState.checkField.length > 0 && gridState.checkField.length !== gridState.stateField.length} onChange={handleCheckboxAllChange} />} label="전체" sx={{ pl: 2 }} />
                            <Typography component="span" className="cursorp" sx={{ float: "right", pr: .5 }} onClick={onSaveGridField}>
                                <CheckCircleOutlined /> 저장
                            </Typography>
                        </ListItemText>
                        <Divider />
                        {
                            _.map(gridState.stateField, (obj, i) => (
                                <FormControl fullWidth key={i}>
                                    <FormControlLabel control={<Checkbox id={"gridField_" + obj.colId} checked={obj.show} onChange={handleCheckboxChange} />} label={obj.name} sx={{ pl: 2 }} />
                                </FormControl>
                            ))
                        }
                    </Menu>

                    <Button color="inherit" size="small" startIcon={viewType === "Grid" ? <TableOutlined /> : <DotChartOutlined />} onClick={handleViewTypeOpen}>
                        {viewType} <CaretDownOutlined style={{ marginLeft: 5 }} />
                    </Button>
                    <Menu
                        anchorEl={viewTypeEl}
                        open={viewTypeOpen}
                        onClose={handleViewTypeClose}
                        PaperProps={{
                            style: {
                                border: "1px solid #babfc7",
                                width: "7rem"
                            }
                        }}
                    >
                        <MenuItem onClick={() => handleViewType("Grid")} selected={viewType === "Grid"}><TableOutlined style={{ marginRight: 5 }} />Grid</MenuItem>
                        <MenuItem onClick={() => handleViewType("Chart")} selected={viewType === "Chart"}><DotChartOutlined style={{ marginRight: 5 }} />Chart</MenuItem>
                    </Menu>
                </Grid>

                <Grid item md={12} id="policyNoticeGrid">
                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()} onScrollCapture={onScrollLoadEvent} hidden={viewType !== "Grid"}>
                        <AgGridModule
                            gridName="policyNoticeGridApi"
                            columnDefs={policyColumnDefs}
                            rowData={policyData}
                            onCellClicked={onCellClicked}
                            onCellMouseDown={onCellMouseDown}
                            onDragStopped={onDragStopped}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>

                    <Card hidden={viewType !== "Chart"}>
                        <CardHeader title={<>
                            {
                                snapDate === "" ? <Typography component="span" className="cursorp" onClick={handleResourceOpen}><SettingOutlined /> {selectResource[0].name}</Typography>
                                    : <Grid container>
                                        <Grid item md={6}>
                                            Snap Count: <Typography component="span" className="font-blue font-bold">{numberWithCommas(snapGridData.length)}</Typography> ( 조회기간: {snapDate} )
                                        </Grid>
                                        <Grid item md={6} textAlign="right">
                                            {
                                                snapGridFlag ? <Typography component="span" className="btn-page" mr={1} onClick={handleSnapGrid}><ReloadOutlined /> 이전</Typography>
                                                    : <Typography component="span" className="btn-page" mr={1} onClick={handleSnapGrid}><TableOutlined /> 그리드 보기</Typography>
                                            }
                                        </Grid>
                                    </Grid>
                            }
                        </>} />
                        <CardContent sx={{ height: chartHeight + 5 }}>
                            <Box sx={{ height: chartHeight }} hidden={snapGridFlag}>
                                {ChartsContent}
                            </Box>
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()} hidden={!snapGridFlag}>
                                <AgGridModule
                                    gridName="policySnapGridApi"
                                    columnDefs={policyColumnDefs}
                                    rowData={snapGridData}
                                    onCellClicked={onCellClicked}
                                    onCellMouseDown={onCellMouseDown}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={6} mt={.5} hidden={viewType !== "Grid"}>
                    <Button color="primary" size="small" startIcon={<DownloadOutlined />} onClick={() => onDownloadExcel()}>내보내기</Button>
                </Grid>

                <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                    <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
                </Snackbar>
                {showLoader && (<Loader />)}

                {ModalResourceContent}
                {ModalSearchContent}
            </Grid>
        </>
    );
};

export default HttpPagesIndex;