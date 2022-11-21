import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { useLocation, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import {
    Grid, Button, Box, Typography, Menu, ListItemText, Divider, Checkbox, FormControl, FormControlLabel, Alert, Snackbar, Slide,
    MenuItem, Card, CardHeader, CardContent, Badge, Tooltip
} from '@mui/material';
import Chart from 'react-apexcharts';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faQuestionCircle, faChartGantt } from "@fortawesome/free-solid-svg-icons";
import { SettingOutlined, DownloadOutlined, CheckCircleOutlined, TableOutlined, CaretDownOutlined, DotChartOutlined, ReloadOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import {
    numberWithCommas, getGridFindColumn, handleShowGrid, handleAllShowGrid, onSaveGridSetting, onSaveIdxGridSetting, onCopyGridCell, gridApiObj, gridSetMenuStyles,
    getGeoKeyByCountryId, scrollGridCnt, getFloatSec, getNs, timeDeltaSec, uriResources, exportExcelData
} from '../../lib/common';
import Loader from '../../components/Loader';
import EchartsComponent from '../../lib/EchartsComponent';
import SearchCommonDetail from '../Common/Search/SearchCommonDetail';
import ModalSearchFormResources from '../Common/Modal/ModalSearchFormResources';
import ModalSearchFormUri from '../Common/Modal/ModalSearchFormUri';

const HttpUriIndex = () => {
    const localGetViewType = localStorage.getItem("uriViewType");
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
        isTextSrcIp: false,
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
    const [selectResource, setSelectResource] = useState([{ name: "Response Time", key: "tsRsqDelayResponse" }]);

    let staticChartResources = _.cloneDeep(uriResources);

    staticChartResources[0].children = [
        { name: "Response Time", key: "tsRsqDelayResponse" },
        { name: "TCP Error (%)", key: "tcpError" },
        { name: "Delay Transfer (request)", key: "tsReqDelayTransfer" },
        { name: "Delay Transfer (response)", key: "tsResDelayTransfer" },
        { name: "Response Process First", key: "tsResProcessFirst" },
        { name: "Response Process Push", key: "tsResProcessPush" },
        { name: "Response Process Last", key: "tsResProcessLast" },
        { name: "Request Making Time", key: "tsReqMaking" },
        { name: "Connection RTT (request)", key: "tsRttConnReq" },
        { name: "Connection RTT (response)", key: "tsRttConnRes" },
        { name: "RTT First ACK (request)", key: "tsRttFirstAckReq" },
        { name: "RTT First ACK (response)", key: "tsRttFirstAckRes" },
        { name: "Connection Delay (request)", key: "tsConnDelayReq" },
        { name: "Connection Delay (response)", key: "tsConnDelayRes" },
        { name: "First ACK Delay (request)", key: "tsFirstAckDelayReq" },
        { name: "First ACK Delay (response)", key: "tsFirstAckDelayRes" },
        { name: "Mbps", key: "mbps" },
        { name: "ACK Delay (request)", key: "ackDelayReq" },
        { name: "ACK Delay (response)", key: "ackDelayRes" }
    ];

    staticChartResources[1].children = [
        { name: "ACK RTT Sum (request)", key: "ackRttSumReq" },
        { name: "ACK RTT Sum (response)", key: "ackRttSumRes" },
        { name: "ACK RTT Count (request)", key: "ackRttCntReq" },
        { name: "ACK RTT Count (response)", key: "ackRttCntRes" },
        { name: "ACK RTT Min (request)", key: "ackRttMinReq" },
        { name: "ACK RTT Min (response)", key: "ackRttMinRes" },
        { name: "ACK RTT Max (request)", key: "ackRttMaxReq" },
        { name: "ACK RTT Max (response)", key: "ackRttMaxRes" }
    ];

    const [chartData, setChartData] = useState([]);
    const [chartHeight, setChartHeight] = useState(0);
    const [chartOption, setChartOption] = useState({});

    const [snapDate, setSnapDate] = useState("");
    const [snapGridFlag, setSnapGridFlag] = useState(false);
    const [snapGridData, setSnapGridData] = useState([]);

    const [latencyData, setLatencyData] = useState([]);
    const [latencyChartSeries, setLatencyChartSeries] = useState([]);
    const [latencyChartOption] = useState({
        grid: {
            row: {
                colors: ['#f3f4f5', '#fff'],
                opacity: 1
            }
        },
        plotOptions: {
            bar: {
                horizontal: true,
                barHeight: "50%",
                rangeBarGroupRows: true
            }
        },
        fill: {
            type: "solid"
        },
        xaxis: {
            labels: {
                formatter: (value) => {
                    return value + " ms";
                }
            }
        },
        yaxis: {
            labels: {
                style: {
                    whiteSpace: "nowrap",
                    overflow: "hidden",
                    textOverflow: "ellipsis",
                    width: "400px",
                    fontWeight: 400,
                    fontFamily: "FontAwesome, Arial",
                    cssClass: "btn-page"
                },
                formatter: (value) => {
                    if (_.includes(value, "Empty Name")) {
                        return "";
                    } else {
                        const splitIdx = _.split(value, "|idx=");
                        return splitIdx[0] + "\n\n- Info: " + splitIdx[1];
                    }
                }
            }
        },
        legend: {
            position: "top"
        },
        tooltip: {
            custom: ({ seriesIndex, dataPointIndex, w }) => {
                const target = w.globals.initialSeries[seriesIndex];
                const data = target.data[dataPointIndex];
                const resultData = parseFloat((data.y[1] - data.y[0]).toFixed(6));
                let category = "";

                if (_.includes(data.x, "")) {
                    const splitExp = data.x.split(" ");
                    const splitIdx = splitExp[1].split("|idx=");
                    category = splitIdx[0];
                }

                return `<div class="apexcharts-tooltip-rangebar"><span style="color: ${target.color}">● </span><b>${target.name}:</b><div>
                        <span class="category">${category}: </span>
                        <span class="value">${resultData}</span></div></div>`;
            }
        }
    });

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
    { headerName: 'URI', width: 400, field: 'page', cellClass: ['cursorp'], filter: "agTextColumnFilter" },
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
    { headerName: "Response Time", field: "tsRsqDelayResponse", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Mbps", field: "mbps", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Error", field: "tcpError", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    {
        headerName: 'Stopped Transaction',
        field: 'isStoppedTransaction',
        cellClass: ['text-center'],
        valueGetter: (params) => {
            return params.data.isStoppedTransaction === 1 ? "stopped" : "";
        },
        filter: "agTextColumnFilter"
    },
    { headerName: "Src Mac", field: "srcMac", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dst Mac", field: "dstMac", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Method", field: "httpMethod", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Version", field: "httpVersion", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Version (request)", field: "httpVersionReq", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Version (response)", field: "httpVersionRes", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Content Length", field: "httpContentLength", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Res Phrase", field: "httpResPhrase", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Content Type", field: "httpContentType", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Cookie", field: "httpCookie", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Location", field: "httpLocation", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP URI", field: "httpUri", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP URI Split", field: "httpUriSplit", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Referer", field: "httpReferer", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Application", field: "applicationName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "DPI Protocol App", field: "ndpiProtocolApp", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "DPI Protocol Master", field: "ndpiProtocolMaster", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Software Name", field: "userAgentSoftwareName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Software Version", field: "userAgentSoftwareVersion", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Software Version Full", field: "userAgentSoftwareVersionFull", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating System Name", field: "userAgentOperatingSystemName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating System Version", field: "userAgentOperatingSystemVersion", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating System Version Full", field: "userAgentOperatingSystemVersionFull", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating System Flavour", field: "userAgentOperatingSystemFlavour", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating Platform", field: "userAgentOperatingPlatform", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating Platform Code", field: "userAgentOperatingPlatformCode", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating Platform Code Name", field: "userAgentOperatingPlatformCodeName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Operating Platform Vendor Name", field: "userAgentOperatingPlatformVendorName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Software Type", field: "userAgentSoftwareType", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Software Subtype", field: "userAgentSoftwareSubType", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Hardware Type", field: "userAgentHardwareType", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Hardware Subtype", field: "userAgentHardwareSubType", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "User Agent Layout Engine Name", field: "userAgentLayoutEngineName", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Frame Landoff Time", field: "tsFrameLandoff", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Frame Arrival Page Time", field: "tsFrameArrivalPage", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Frame Landoff Page Time", field: "tsFrameLandoffPage", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Sequence First (request)", field: "reqSeqFirst", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK First (request)", field: "reqAckFirst", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Sequence Last (request)", field: "reqSeqLast", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK Last (request)", field: "reqAckLast", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Sequence First (response)", field: "resSeqFirst", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK First (response)", field: "resAckFirst", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Pkt First Time (request)", field: "tsReqPktFirst", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Pkt Push Time (request)", field: "tsReqPktPush", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Pkt Last Time (request)", field: "tsReqPktLast", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Pkt First Time (response)", field: "tsResPktFirst", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Pkt Push Time (response)", field: "tsResPktPush", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Pkt Last Time (response)", field: "tsResPktLast", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Delay Transfer (request)", field: "tsReqDelayTransfer", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Delay Transfer (response)", field: "tsResDelayTransfer", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Process First", field: "tsResProcessFirst", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Process Push", field: "tsResProcessPush", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Response Process Last", field: "tsResProcessLast", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Rtt SYN Time", field: "tsRttSyn", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT SYN ACK Time", field: "tsRttSynAck", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT First ACK Time", field: "tsRttFirstAck", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT Request ACK Time", field: "tsRttReqAck", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT ACK Request ACK Time", field: "tsRttAckReqAck", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT Response ACK Time", field: "tsRttResAck", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT ACK Response ACK Time", field: "tsRttAckResAck", cellClass: ['text-center', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT Connection (request)", field: "tsRttConnReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT Connection (response)", field: "tsRttConnRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT First ACK (request)", field: "tsRttFirstAckReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "RTT First ACK (response)", field: "tsRttFirstAckRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Connection Delay (request)", field: "tsConnDelayReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Connection Delay (response)", field: "tsConnDelayRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "First ACK Delay (request)", field: "tsFirstAckDelayReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "First ACK Delay (response)", field: "tsFirstAckDelayRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Request Making", field: "tsReqMaking", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Length (request)", field: "pktLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Length (response)", field: "pktLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Count (request)", field: "pktCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Packet Count (response)", field: "pktCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Length (request)", field: "httpLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Length (response)", field: "httpLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Count (request)", field: "httpCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "HTTP Count (response)", field: "httpCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Length (request)", field: "tcpLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Length (response)", field: "tcpLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Count (request)", field: "tcpCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "TCP Count (response)", field: "tcpCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Sum (request)", field: "ackRttSumReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Sum (response)", field: "ackRttSumRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTO Sum (request)", field: "ackRtoSumReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTO Sum (response)", field: "ackRtoSumRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Count (request)", field: "ackRttCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Count (response)", field: "ackRttCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTO Count (request)", field: "ackRtoCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTO Count (response)", field: "ackRtoCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK Delay (request)", field: "ackDelayReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK Delay (response)", field: "ackDelayRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Min (request)", field: "ackRttMinReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Min (response)", field: "ackRttMinRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Max (request)", field: "ackRttMaxReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "ACK RTT Max (response)", field: "ackRttMaxRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
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
    { headerName: "Ack Lost Count (request)", field: "ackLostCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Ack Lost Count (response)", field: "ackLostCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Ack Lost Length (request)", field: "ackLostLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Ack Lost Length (response)", field: "ackLostLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Count (request)", field: "winUpdateCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Count (response)", field: "winUpdateCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Length (request)", field: "winUpdateLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Window Update Length (response)", field: "winUpdateLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup Ack Count (request)", field: "dupAckCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup Ack Count (response)", field: "dupAckCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup Ack Length (request)", field: "dupAckLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dup Ack Length (response)", field: "dupAckLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
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
    { headerName: "Zero Window Ack Probe Count (request)", field: "zeroWinProbeAckCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Ack Probe Count (response)", field: "zeroWinProbeAckCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Ack Probe Length (request)", field: "zeroWinProbeAckLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Zero Window Ack Probe Length (response)", field: "zeroWinProbeAckLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Count (request)", field: "keepAliveCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Count (response)", field: "keepAliveCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Length (request)", field: "keepAliveLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Length (response)", field: "keepAliveLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Ack Count (request)", field: "keepAliveAckCntReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Ack Count (response)", field: "keepAliveAckCntRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Ack Length (request)", field: "keepAliveAckLenReq", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Keep Alive Ack Length (response)", field: "keepAliveAckLenRes", cellClass: ['text-right', 'cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Src As", field: "asNameReq", cellClass: ['cursorp'], filter: "agTextColumnFilter" },
    { headerName: "Dst As", field: "asNameRes", cellClass: ['cursorp'], filter: "agTextColumnFilter" }
    ]);

    useEffect(() => {
        // Location State Clear
        if (location.state && location.state.schItem) {
            window.history.replaceState({}, "/protocol/http/uri/detail");
        }

        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);
        document.getElementById("latencyChartEl").addEventListener('mousedown', handleClickOutside);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        localStorage.setItem("uriViewType", viewType);

        if (viewType === "Grid" && policyData.length === 0) {
            getUriData(schItem);
        } else if (viewType === "Chart" && chartData.length === 0) {
            getUriData(schItem);
        } else if (viewType === "Latency" && latencyData.length === 0) {
            getUriData(schItem);
        }

        handleResize();
    }, [viewType]);

    useEffect(() => {
        if (snapGridFlag) {
            handleResize();
        }
    }, [snapGridFlag]);

    const handleClickOutside = (event) => {
        if (_.includes(event.target.innerHTML, " ")) {
            if (event.target.nextElementSibling) {
                const titleParam = event.target.nextElementSibling.innerHTML;
                latencyLabelClick(titleParam);
            }
        }
    }

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
            type: viewType === "Latency" ? "grid" : _.lowerFirst(viewType),
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

        // Uri
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
            setLatencyData([]);
            setSnapDate("");
            setSnapGridFlag(false);

            const targetData = resetItem ? resetItem : schItem;
            getUriData(targetData);
        }
    }, [schItem, viewType, selectResource, currentPage]);

    const getUriData = (targetData) => {
        setShowLoader(true);

        let requestData = formatSchReqData(targetData);

        // Grid Size
        if (requestData.type === "grid") {
            requestData.limit = scrollGridCnt;
            requestData.offset = 0;
        }

        if (viewType === "Latency") {
            requestData.sort = "asc";
        }

        axiosConf.post('/api/protocol/uri/find', requestData).then(res => {
            if (viewType === "Grid") {
                if (gridApiObj.policyNoticeGridApi) {
                    gridApiObj.policyNoticeGridApi.api.ensureIndexVisible(0, 'top');
                }

                const maxPage = _.floor(res.data.total / scrollGridCnt);

                formatUriCurrData(res.data.results);
                setTotalCnt(res.data.total);
                setMaxPage(maxPage);
                setCurrentPage(0);
                setPolicyData(res.data.results);
            } else if (viewType === "Chart") {
                formatUriCurrData(res.data);
                setChartData(res.data);
                setTotalCnt(res.data.length);
                createScatterChart(res.data, selectResource[0].key);
            } else {
                document.getElementById('latencyChartEl').scrollTop = 0;

                const maxPage = _.floor(res.data.total / scrollGridCnt);

                createLatencyChart(res.data.results);
                setTotalCnt(res.data.total);
                setMaxPage(maxPage);
                setCurrentPage(0);
            }

            setShowLoader(false);
        });
    }

    const latencyLabelClick = (titleParam) => {
        const splitPageIdx = titleParam.split("|pageIdx=");
        const splitUriIdx = titleParam.split("|uriIdx=");
        const splitFrameArrival = titleParam.split("|tsFrameArrival=");
        const splitFrameLandOff = titleParam.split("|tsFrameLandOff=");
        const splitSrcIp = titleParam.split("|srcIp=");
        const splitDstIp = titleParam.split("|dstIp=");
        const splitSrcPort = titleParam.split("|srcPort=");
        const splitDstPort = titleParam.split("|dstPort=");

        const stateParam = {
            request: {
                pageIdx: splitPageIdx[1].split("|")[0],
                uriIdx: splitUriIdx[1].split("|")[0],
                tsFrameArrival: splitFrameArrival[1].split("|")[0],
                tsFrameLandOff: splitFrameLandOff[1].split("|")[0],
                srcIp: splitSrcIp[1].split("|")[0],
                dstIp: splitDstIp[1].split("|")[0],
                srcPort: parseFloat(splitSrcPort[1].split("|")[0]),
                dstPort: parseFloat(splitDstPort[1].split("|")[0])
            },
            schItem: schItem
        };

        navigate("/protocol/http/uri/detail", { state: stateParam });
    }

    const createLatencyChart = (uriData) => {
        let latencySeries = [{
            name: "Response Time",
            color: "#90ED7D",
            data: []
        },
        {
            name: "Wait Time",
            color: "#8085E9",
            data: []
        },
        {
            name: "Request Time",
            color: "#F15C80",
            data: []
        },
        {
            name: "Connection Time",
            color: "#1EB7FF",
            data: []
        }];

        formatUriLatencyData(uriData, latencySeries);

        if (uriData.length < 50) {
            _.forEach(latencySeries, (obj) => {
                _.forEach(_.range(50 - uriData.length), (i) => {
                    obj.data.push({ x: "Empty Name" + i, y: [0, 0] });
                });
            });
        }

        setLatencyData(uriData);
        setLatencyChartSeries(latencySeries);
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

    const formatUriLatencyData = (data, latencySeries) => {
        const offset = currentPage * scrollGridCnt;
        const tsFirstFrameArrivalNs = data.length > 0 ? getNs(data[0].realtimeUriKey.tsFrameArrival.toFixed(9)) : 0;

        _.forEach(data, (obj, i) => {
            const tsConnBeginSec = getFloatSec(getNs(obj.realtimeUriKey.tsFrameArrival.toFixed(9)) - tsFirstFrameArrivalNs);
            const tsReqBeginNSec = getNs(obj.tsReqPktFirst.toFixed(9)) - tsFirstFrameArrivalNs;
            const tsReqTransferNSec = getNs(obj.tsReqDelayTransfer.toFixed(9));
            const tsResTransferNSec = getNs(obj.tsResDelayTransfer.toFixed(9));
            const tsRsqResponseNSec = getNs(obj.tsRsqDelayResponse.toFixed(9));
            const tsWaitNSec = tsRsqResponseNSec - tsReqTransferNSec - tsResTransferNSec;

            const tsReqBeginSec = getFloatSec(tsReqBeginNSec);
            const tsReqEndNSec = tsReqBeginNSec + tsReqTransferNSec;
            const tsReqEndSec = getFloatSec(tsReqEndNSec);
            const tsResBeginNSec = tsReqEndNSec + tsWaitNSec;
            const tsResBeginSec = getFloatSec(tsResBeginNSec);
            const tsResEndNSec = tsResBeginNSec + tsResTransferNSec;
            const tsResEndSec = getFloatSec(tsResEndNSec);

            const idx = offset + i;
            const param = "|srcIp=" + obj.realtimeUriKey.srcIp + "|dstIp=" + obj.realtimeUriKey.dstIp + "|srcPort=" + obj.realtimeUriKey.srcPort + "|dstPort=" + obj.realtimeUriKey.dstPort
                + "|tsFrameArrival=" + obj.realtimeUriKey.tsFrameArrival + "|tsFrameLandOff=" + obj.realtimeUriKey.tsFrameLandOff
                + "|uriIdx=" + obj.realtimeUriKey.uriIdx + "|pageIdx=" + obj.realtimeUriKey.pageIdx;

            obj.uri = obj.httpUri + obj.httpUriSplit + "|idx=" + idx + param;

            latencySeries[0].data.push({
                x: " " + obj.uri,
                y: [tsResBeginSec, tsResEndSec]
            });

            latencySeries[1].data.push({
                x: " " + obj.uri,
                y: [tsReqEndSec, tsResBeginSec]
            });

            latencySeries[2].data.push({
                x: " " + obj.uri,
                y: [tsReqBeginSec, tsReqEndSec]
            });

            latencySeries[3].data.push({
                x: " " + obj.uri,
                y: [tsConnBeginSec, tsReqBeginSec]
            });
        });
    }

    const formatUriCurrData = (data) => {
        _.forEach(data, (obj) => {
            const tcpErrorCnt = obj.retransmissionCntReq + obj.retransmissionCntRes + obj.fastRetransmissionCntReq + obj.fastRetransmissionCntRes +
                obj.outOfOrderCntReq + obj.outOfOrderCntRes + obj.lostSegCntReq + obj.lostSegCntRes +
                obj.ackLostCntReq + obj.ackLostCntRes + obj.winUpdateCntReq + obj.winUpdateCntRes +
                obj.dupAckCntReq + obj.dupAckCntRes + obj.zeroWinCntReq + obj.zeroWinCntRes +
                obj.spuriousRetransmissionCntReq + obj.spuriousRetransmissionCntRes + obj.overlapCntReq + obj.overlapCntRes +
                obj.overlapAttackCntReq + obj.overlapAttackCntRes + obj.zeroWinProbeCntReq + obj.zeroWinProbeCntRes +
                obj.zeroWinProbeAckCntReq + obj.zeroWinProbeAckCntRes + obj.keepAliveCntReq + obj.keepAliveCntRes +
                obj.keepAliveAckCntReq + obj.keepAliveAckCntRes;
            const tcpError = (tcpErrorCnt / (obj.pktCntReq + obj.pktCntRes)) * 100;
            const mbps = (((obj.pktLenReq + obj.pktLenRes) * 8) / timeDeltaSec(obj.realtimeUriKey.tsFrameArrival.toFixed(9), obj.realtimeUriKey.tsFrameLandOff.toFixed(9))) / 1000000;
            const httpResCodeDesc = responseCode[obj.httpResCode] || [];

            obj.tsFrameLandoff = obj.realtimeUriKey.tsFrameLandOff ? moment(obj.realtimeUriKey.tsFrameLandOff * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsFrameArrival = obj.realtimeUriKey.tsFrameArrival ? moment(obj.realtimeUriKey.tsFrameArrival * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.timeFrameArrival = obj.realtimeUriKey.tsFrameArrival ? parseFloat(moment(obj.realtimeUriKey.tsFrameArrival * 1000).format("x")) : "";
            obj.tsFrameArrivalPage = obj.tsFrameArrivalPage ? moment(obj.tsFrameArrivalPage * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsFrameLandoffPage = obj.tsFrameLandoffPage ? moment(obj.tsFrameLandoffPage * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsReqPktFirst = obj.tsReqPktFirst ? moment(obj.tsReqPktFirst * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsReqPktPush = obj.tsReqPktPush ? moment(obj.tsReqPktPush * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsReqPktLast = obj.tsReqPktLast ? moment(obj.tsReqPktLast * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsResPktFirst = obj.tsResPktFirst ? moment(obj.tsResPktFirst * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsResPktPush = obj.tsResPktPush ? moment(obj.tsResPktPush * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsResPktLast = obj.tsResPktLast ? moment(obj.tsResPktLast * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsRttSyn = obj.tsRttSyn ? moment(obj.tsRttSyn * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsRttSynAck = obj.tsRttSynAck ? moment(obj.tsRttSynAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsRttFirstAck = obj.tsRttFirstAck ? moment(obj.tsRttFirstAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsRttReqAck = obj.tsRttReqAck ? moment(obj.tsRttReqAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsRttAckReqAck = obj.tsRttAckReqAck ? moment(obj.tsRttAckReqAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsRttResAck = obj.tsRttResAck ? moment(obj.tsRttResAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.tsRttAckResAck = obj.tsRttAckResAck ? moment(obj.tsRttAckResAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";

            obj.srcIp = obj.realtimeUriKey.srcIp;
            obj.srcPort = obj.realtimeUriKey.srcPort;
            obj.dstIp = obj.realtimeUriKey.dstIp;
            obj.dstPort = obj.realtimeUriKey.dstPort;
            // Level 필드 새로 정의된 걸로 수정
            //obj.level = obj.levelOverall;
            obj.level = 0;
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

            obj.tcpError = parseFloat(tcpError.toFixed(2));
            obj.mbps = parseFloat(mbps.toFixed(2));
            obj.tsRsqDelayResponse = parseFloat(obj.tsRsqDelayResponse.toFixed(6));
            obj.tsReqDelayTransfer = parseFloat(obj.tsReqDelayTransfer.toFixed(6));
            obj.tsResDelayTransfer = parseFloat(obj.tsResDelayTransfer.toFixed(6));
            obj.tsResProcessFirst = parseFloat(obj.tsResProcessFirst.toFixed(6));
            obj.tsResProcessPush = parseFloat(obj.tsResProcessPush.toFixed(6));
            obj.tsResProcessLast = parseFloat(obj.tsResProcessLast.toFixed(6));
            obj.tsRttConnReq = parseFloat(obj.tsRttConnReq.toFixed(6));
            obj.tsRttConnRes = parseFloat(obj.tsRttConnRes.toFixed(6));
            obj.tsRttFirstAckReq = parseFloat(obj.tsRttFirstAckReq.toFixed(6));
            obj.tsRttFirstAckRes = parseFloat(obj.tsRttFirstAckRes.toFixed(6));
            obj.tsConnDelayReq = parseFloat(obj.tsConnDelayReq.toFixed(6));
            obj.tsConnDelayRes = parseFloat(obj.tsConnDelayRes.toFixed(6));
            obj.tsFirstAckDelayReq = parseFloat(obj.tsFirstAckDelayReq.toFixed(6));
            obj.tsFirstAckDelayRes = parseFloat(obj.tsFirstAckDelayRes.toFixed(6));
            obj.tsReqMaking = parseFloat(obj.tsReqMaking.toFixed(6));
            obj.ackRttSumReq = parseFloat(obj.ackRttSumReq.toFixed(6));
            obj.ackRttSumRes = parseFloat(obj.ackRttSumRes.toFixed(6));
            obj.ackRtoSumReq = parseFloat(obj.ackRtoSumReq.toFixed(6));
            obj.ackRtoSumRes = parseFloat(obj.ackRtoSumRes.toFixed(6));
            obj.ackDelayReq = parseFloat(obj.ackDelayReq.toFixed(6));
            obj.ackDelayRes = parseFloat(obj.ackDelayRes.toFixed(6));
            obj.ackRttMinReq = parseFloat(obj.ackRttMinReq.toFixed(6));
            obj.ackRttMinRes = parseFloat(obj.ackRttMinRes.toFixed(6));
            obj.ackRttMaxReq = parseFloat(obj.ackRttMaxReq.toFixed(6));
            obj.ackRttMaxRes = parseFloat(obj.ackRttMaxRes.toFixed(6));
        });
    }

    const addPolicyList = (page) => {
        const offset = page * scrollGridCnt;
        let requestData = formatSchReqData(schItem);

        // Grid Size
        requestData.limit = scrollGridCnt;
        requestData.offset = offset;

        if (viewType === "Latency") {
            requestData.sort = "asc";
        }

        axiosConf.post('/api/protocol/uri/find', requestData).then(res => {
            if (viewType === "Grid") {
                formatUriCurrData(res.data.results);
                setPolicyData([...policyData, ...res.data.results]);
            } else {
                let latencySeries = _.cloneDeep(latencyChartSeries);

                formatUriLatencyData(res.data.results, latencySeries);
                setLatencyData([...latencyData, ...res.data.results]);
                setLatencyChartSeries(latencySeries);
            }

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

    const onScrollChartEvent = (e) => {
        if ((e.target.scrollTop + e.target.clientHeight) === e.target.scrollHeight) {
            if (maxPage > currentPage) {
                setShowLoader(true);
                setCurrentPage(prev => prev + 1);
                addPolicyList(currentPage + 1);
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
            getGridFindColumn("realtimeUri", gridApiObj[target], setGridState);
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
        onSaveGridSetting("realtimeUri", gridState, handleGridSetClose);
    }

    const onDragStopped = () => {
        if (gridApiObj.policyNoticeGridApi) {
            onSaveIdxGridSetting("realtimeUri", gridApiObj.policyNoticeGridApi);
        }

        if (gridApiObj.policySnapGridApi) {
            onSaveIdxGridSetting("realtimeUri", gridApiObj.policySnapGridApi);
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
                pageIdx: dataset.realtimeUriKey.pageIdx.toString(),
                uriIdx: dataset.realtimeUriKey.uriIdx.toString(),
                tsFrameArrival: dataset.realtimeUriKey.tsFrameArrival.toString(),
                tsFrameLandOff: dataset.realtimeUriKey.tsFrameLandOff.toString(),
                srcIp: dataset.srcIp,
                dstIp: dataset.dstIp,
                srcPort: dataset.srcPort,
                dstPort: dataset.dstPort
            },
            schItem: schItem
        };

        navigate("/protocol/http/uri/detail", { state: stateParam });
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

            axiosConf.post('/api/protocol/uri/find', requestData).then(res => {
                formatUriCurrData(res.data);

                const getField = gridApiObj.policyNoticeGridApi.columnApi.getDisplayedCenterColumns();
                let excelData = {
                    category: 'uri',
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
    }, [schItem, viewType, selectResource, currentPage]);

    // import content
    const SearchContent = useMemo(() => (
        <SearchCommonDetail schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={getPolicyList} resetEvt={resetEvt} handleOpen={handleSearchOpen} />
    ), [schItem, selectResource, viewType, currentPage]);

    const ModalResourceContent = useMemo(() => (
        <ModalSearchFormResources open={resourceOpen} resourceItem={staticChartResources} selectResource={selectResource} single={true} onClose={handleResourceClose} onSuccess={bridgeModalResource} />
    ), [resourceOpen]);

    const ModalSearchContent = useMemo(() => (
        <ModalSearchFormUri open={searchOpen} schItem={schItem} onClose={handleSearchClose} setSchItem={setSchItem} searchEvt={getPolicyList} />
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

                    <Button color="inherit" size="small" startIcon={viewType === "Grid" ? <TableOutlined /> : viewType === "Chart" ? <DotChartOutlined /> : <FontAwesomeIcon icon={faChartGantt} />} onClick={handleViewTypeOpen}>
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
                        <MenuItem onClick={() => handleViewType("Latency")} selected={viewType === "Latency"}><FontAwesomeIcon icon={faChartGantt} style={{ marginRight: 5 }} />Latency</MenuItem>
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

                    <Box className="border" sx={{ bgcolor: "background.paper", overflowY: "auto", height: "104%" }} id="latencyChartEl" onScrollCapture={onScrollChartEvent} hidden={viewType !== "Latency"}>
                        {latencyChartSeries.length > 0 && <Chart options={latencyChartOption} series={latencyChartSeries} type="rangeBar" width="99%" height={latencyData.length > 50 ? 20 * latencyData.length : "98%"} />}
                    </Box>
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

export default HttpUriIndex;