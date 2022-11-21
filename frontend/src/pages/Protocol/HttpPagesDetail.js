import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import WindowOpener from 'react-window-opener';
import { useLocation, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { Grid, Button, Box, Alert, Snackbar, Slide, Tabs, Tab, Badge, Tooltip, Typography } from '@mui/material';
import Chart from 'react-apexcharts';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowUpRightFromSquare, faQuestionCircle, faAngleLeft, faWindowMaximize } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import { onCopyGridCell, gridApiObj, timeDeltaSec, timeAverageNs, getGeoKeyByCountryId, getFloatSec, getNs } from '../../lib/common';
import Loader from '../../components/Loader';
import EchartsComponent from '../../lib/EchartsComponent';
import HttpPagesDetailSummaryTb from './HttpPagesDetailSummaryTb';
import HttpPagesDetailHttpTb from './HttpPagesDetailHttpTb';
import HttpPagesDetailPerformanceTb from './HttpPagesDetailPerformanceTb';
import HttpPagesDetailThroughputTb from './HttpPagesDetailThroughputTb';
import HttpPagesDetailAvailabilityTb from './HttpPagesDetailAvailabilityTb';

const HttpPagesDetail = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { responseCode } = useSelector((state) => state.staticVar);

    const [showLoader, setShowLoader] = useState(false);

    const [menuKey, setMenuKey] = useState(0);

    const [pageData, setPageData] = useState({});

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [summaryChartKey, setSummaryChartKey] = useState(0);
    const [summaryErrChart, setSummaryErrChart] = useState({});

    const [summaryLatencyHeight, setSummaryLatencyHeight] = useState(0);
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

    const [summaryLatencySeries, setSummaryLatencySeries] = useState([]);
    const [latencyChartSeries, setLatencyChartSeries] = useState([]);

    const [httpUriData, setHttpUriData] = useState([]);
    const [httpUriColumnDefs] = useState([{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            return <FontAwesomeIcon icon={faArrowUpRightFromSquare} className="font-blue cursorp" onClick={() => onClickUri(params.data)} />;
        }
    },
    {
        headerName: 'Begin Time',
        field: 'strFrameArrival',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'URI',
        field: 'uri',
        suppressToolPanel: true,
        suppressSizeToFit: true,
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Response Time',
        field: 'tsRsqDelayResponse',
        cellClass: ['text-right'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Mbps',
        field: 'mbps',
        cellClass: ['text-right'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Method',
        field: 'httpMethod',
        cellClass: ['text-center'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Result',
        field: 'httpResCode',
        cellClass: ['text-center'],
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
    }]);

    useEffect(() => {
        if (location.state && location.state.request) {
            getDetailInfo();
        }

        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);
        document.getElementById("mainEl").addEventListener('mousedown', handleClickOutside);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (menuKey === 5) {
                if (gridApiObj.uriGridApi) {
                    gridApiObj.uriGridApi.columnApi.autoSizeColumns(["uri"], false);
                    gridApiObj.uriGridApi.api.sizeColumnsToFit();
                }
            }
        }, 200);
    }, [menuKey]);

    useEffect(() => {
        createSummaryErrChart(pageData);
    }, [summaryChartKey]);

    const handleClickOutside = (event) => {
        if (_.includes(event.target.innerHTML, " ")) {
            if (event.target.nextElementSibling) {
                const titleParam = event.target.nextElementSibling.innerHTML;
                latencyLabelClick(titleParam);
            }
        }
    }

    const autoComponentSize = () => {
        if (document.getElementById('mainEl')) {
            const mainHeight = document.body.clientHeight;

            document.getElementById('mainEl').style.height = mainHeight - 15 + 'px';
            setSummaryLatencyHeight(mainHeight - 390);
        }

        handleResize();
    }

    const getDetailInfo = () => {
        setShowLoader(true);

        axiosConf.post('/api/protocol/http/detail', location.state.request).then(res => {
            if (!_.isEmpty(res.data.page)) {
                formatPageCurrData(res.data.page);
                createSummaryErrChart(res.data.page);
                setPageData(res.data.page);
            }

            const latencySeries = formatUriCurrData(res.data.uri);
            createLatencyChart(latencySeries, res.data.uri);
            setHttpUriData(res.data.uri);

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
            schItem: {}
        };

        navigate("/protocol/http/uri/detail", { state: stateParam });
    }

    const createLatencyChart = (latencySeries, uriData) => {
        let cloneLatencySeries = _.cloneDeep(latencySeries);

        if (uriData.length < 25) {
            _.forEach(latencySeries, (obj) => {
                _.forEach(_.range(25 - uriData.length), (i) => {
                    obj.data.push({ x: "Empty Name" + i, y: [0, 0] });
                });
            });
        }

        if (uriData.length < 50) {
            _.forEach(cloneLatencySeries, (obj) => {
                _.forEach(_.range(50 - uriData.length), (i) => {
                    obj.data.push({ x: "Empty Name" + i, y: [0, 0] });
                });
            });
        }

        setSummaryLatencySeries(latencySeries);
        setLatencyChartSeries(cloneLatencySeries);
    }

    const formatUriCurrData = (dataset) => {
        const tsFirstFrameArrivalNs = dataset.length > 0 ? getNs(dataset[0].realtimeUriKey.tsFrameArrival.toFixed(9)) : 0;
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

        _.forEach(dataset, (obj, i) => {
            const mbps = (((obj.pktLenReq + obj.pktLenRes) * 8) / timeDeltaSec(obj.realtimeUriKey.tsFrameArrival.toFixed(9), obj.realtimeUriKey.tsFrameLandOff.toFixed(9))) / 1000000;
            const httpResCodeDesc = responseCode[obj.httpResCode] || [];

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

            const param = "|srcIp=" + obj.realtimeUriKey.srcIp + "|dstIp=" + obj.realtimeUriKey.dstIp + "|srcPort=" + obj.realtimeUriKey.srcPort + "|dstPort=" + obj.realtimeUriKey.dstPort
                + "|tsFrameArrival=" + obj.realtimeUriKey.tsFrameArrival + "|tsFrameLandOff=" + obj.realtimeUriKey.tsFrameLandOff
                + "|uriIdx=" + obj.realtimeUriKey.uriIdx + "|pageIdx=" + obj.realtimeUriKey.pageIdx;

            obj.strFrameLandoff = obj.realtimeUriKey.tsFrameLandOff ? moment(obj.realtimeUriKey.tsFrameLandOff * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
            obj.strFrameArrival = obj.realtimeUriKey.tsFrameArrival ? moment(obj.realtimeUriKey.tsFrameArrival * 1000).format("YYYY-MM-DD HH:mm:ss") : "";

            obj.srcIp = obj.realtimeUriKey.srcIp;
            obj.srcPort = obj.realtimeUriKey.srcPort;
            obj.dstIp = obj.realtimeUriKey.dstIp;
            obj.dstPort = obj.realtimeUriKey.dstPort;
            obj.uri = obj.httpUri + obj.httpUriSplit;
            obj.httpResCodeDesc = httpResCodeDesc.length > 0 ? httpResCodeDesc[0].description + " (" + httpResCodeDesc[0].description1 + "), " + httpResCodeDesc[0].description2 : null;

            obj.tsRsqDelayResponse = parseFloat(obj.tsRsqDelayResponse.toFixed(6));
            obj.mbps = parseFloat(mbps.toFixed(6));

            latencySeries[0].data.push({
                x: " " + obj.uri + "|idx=" + i + param,
                y: [tsResBeginSec, tsResEndSec]
            });

            latencySeries[1].data.push({
                x: " " + obj.uri + "|idx=" + i + param,
                y: [tsReqEndSec, tsResBeginSec]
            });

            latencySeries[2].data.push({
                x: " " + obj.uri + "|idx=" + i + param,
                y: [tsReqBeginSec, tsReqEndSec]
            });

            latencySeries[3].data.push({
                x: " " + obj.uri + "|idx=" + i + param,
                y: [tsConnBeginSec, tsReqBeginSec]
            });
        });

        return latencySeries;
    }

    const createSummaryErrChart = (formatPageData) => {
        let cloneChartOption = _.cloneDeep(summaryPieOption());

        if (summaryChartKey === 1) {
            cloneChartOption.series[0].data.push({
                name: "Retransmission",
                value: formatPageData.retransmissionCntReq + formatPageData.retransmissionCntRes
            }, {
                name: "Fast Retransmission",
                value: formatPageData.fastRetransmissionCntReq + formatPageData.fastRetransmissionCntRes
            }, {
                name: "Out Of Order",
                value: formatPageData.outOfOrderCntReq + formatPageData.outOfOrderCntRes
            }, {
                name: "Lost Segment",
                value: formatPageData.lostSegCntReq + formatPageData.lostSegCntRes
            }, {
                name: "Ack Lost",
                value: formatPageData.ackLostCntReq + formatPageData.ackLostCntRes
            }, {
                name: "Duplication Ack",
                value: formatPageData.dupAckCntReq + formatPageData.dupAckCntRes
            }, {
                name: "Zero Window",
                value: formatPageData.zeroWinCntReq + formatPageData.zeroWinCntRes
            }, {
                name: "Spurious Retransmission",
                value: formatPageData.spuriousRetransmissionCntReq + formatPageData.spuriousRetransmissionCntRes
            }, {
                name: "Overlap",
                value: formatPageData.overlapCntReq + formatPageData.overlapCntRes
            }, {
                name: "Overlap Attack",
                value: formatPageData.overlapAttackCntReq + formatPageData.overlapAttackCntRes
            }, {
                name: "Zero Window Probe",
                value: formatPageData.zeroWinProbeCntReq + formatPageData.zeroWinProbeCntRes
            }, {
                name: "Zero Window Probe Ack",
                value: formatPageData.zeroWinProbeAckCntReq + formatPageData.zeroWinProbeAckCntRes
            }, {
                name: "Keep Alive",
                value: formatPageData.keepAliveCntReq + formatPageData.keepAliveCntRes
            }, {
                name: "Keep Alive Ack",
                value: formatPageData.keepAliveAckCntReq + formatPageData.keepAliveAckCntRes
            }, {
                name: "Window Update",
                value: formatPageData.winUpdateCntReq + formatPageData.winUpdateCntRes
            });
        } else {
            cloneChartOption.series[0].data.push({
                name: "2xx",
                itemStyle: { color: '#8bbc21' },
                value: formatPageData.resCode2xxCnt
            }, {
                name: "3xx",
                itemStyle: { color: '#7CB5EC' },
                value: formatPageData.resCode3xxCnt
            }, {
                name: "4xx",
                itemStyle: { color: '#F7A35C' },
                value: formatPageData.resCode4xxCnt
            }, {
                name: "404",
                itemStyle: { color: '#F45B5B' },
                value: formatPageData.resCode404Cnt
            }, {
                name: "5xx",
                itemStyle: { color: '#F15C80' },
                value: formatPageData.resCode5xxCnt
            }, {
                name: "Other",
                itemStyle: { color: '#E4D354' },
                value: formatPageData.resCodeOthCnt
            });
        }

        setSummaryErrChart(cloneChartOption);
    }

    const summaryPieOption = () => {
        return {
            toolbox: {
                feature: {
                    dataZoom: { show: false },
                    restore: { show: false },
                    saveAsImage: {}
                }
            },
            tooltip: {
                trigger: 'item'
            },
            legend: {
                show: false,
            },
            series: [{
                type: 'pie',
                radius: ['25%', '65%'],
                label: {
                    formatter: '{name|{b}}\n{value|{c}} {per|({d}%)}',
                    alignTo: 'edge',
                    minMargin: 5,
                    edgeDistance: 70,
                    lineHeight: 15,
                    rich: {
                        value: {
                            color: 'gray'
                        },
                        per: {
                            color: 'gray'
                        }
                    }
                },
                stillShowZeroSum: false,
                data: []
            }]
        };
    }

    const formatPageCurrData = (obj) => {
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

        obj.strFrameLandoff = obj.realtimePageKey.tsFrameLandOff ? moment(obj.realtimePageKey.tsFrameLandOff * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strFrameArrival = obj.realtimePageKey.tsFrameArrival ? moment(obj.realtimePageKey.tsFrameArrival * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strPageBegin = obj.tsPageBegin ? moment(obj.tsPageBegin * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strPageEnd = obj.tsPageEnd ? moment(obj.tsPageEnd * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strPageReqSyn = obj.tsPageReqSyn ? moment(obj.tsPageReqSyn * 1000).format("YYYY-MM-DD HH:mm:ss") : "";

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

        obj.totPageBegin = obj.strPageBegin + " (" + obj.tsPageBegin + ")";
        obj.totPageReqSyn = obj.tsPageReqSyn === 0 ? 0 : obj.strPageReqSyn + " (" + obj.tsPageReqSyn + ")";
        obj.totFrameArrival = obj.strFrameArrival + " (" + obj.realtimePageKey.tsFrameArrival + ")";
        obj.totFrameLandoff = obj.strFrameLandoff + " (" + obj.realtimePageKey.tsFrameLandOff + ")";
        obj.formatSrcIpGeo = obj.countryIdReq === 0 ? obj.srcIp + ":" + obj.srcPort : obj.srcIp + ":" + obj.srcPort + " (" + obj.srcGeo + ") ";
        obj.formatDstIpGeo = obj.countryIdRes === 0 ? obj.dstIp + ":" + obj.dstPort : obj.dstIp + ":" + obj.dstPort + " (" + obj.dstGeo + ") ";

        obj.reqAvgConnRtt = timeAverageNs(obj.tsPageRttConnSumReq, obj.pageRttConnCntReq);
        obj.resAvgConnRtt = timeAverageNs(obj.tsPageRttConnSumRes, obj.pageRttConnCntRes);
        obj.reqAvgAckRtt = timeAverageNs(obj.tsPageRttAckSumReq, obj.pageRttAckCntReq);
        obj.resAvgAckRtt = timeAverageNs(obj.tsPageRttAckSumRes, obj.pageRttAckCntRes);
        obj.reqMakingTime = timeAverageNs(obj.tsPageReqMakingSum, obj.pageReqMakingCnt);

        obj.successUriCnt = successUriCnt;
        obj.errUriCnt = errUriCnt;
        obj.tsPage = parseFloat(obj.tsPage.toFixed(6));
        obj.mbps = parseFloat(mbps.toFixed(6));
        obj.uriCnt = successUriCnt + errUriCnt;
        obj.sessionCnt = parseFloat(obj.pageSessionCnt);
        obj.tcpError = parseFloat(tcpError.toFixed(2));
        obj.httpError = parseFloat(httpError.toFixed(2));
        obj.tsPageResInit = parseFloat(obj.tsPageResInit.toFixed(6));
        obj.tsPageResInitGap = parseFloat(obj.tsPageResInitGap.toFixed(6));
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
        obj.tsPageRttAckReqMin = parseFloat(obj.tsPageRttAckReqMin.toFixed(6));
        obj.tsPageRttAckResMin = parseFloat(obj.tsPageRttAckResMin.toFixed(6));
        obj.tsPageRttAckReqMax = parseFloat(obj.tsPageRttAckReqMax.toFixed(6));
        obj.tsPageRttAckResMax = parseFloat(obj.tsPageRttAckResMax.toFixed(6));
        obj.tsPageRtoSumReq = parseFloat(obj.tsPageRtoSumReq.toFixed(6));
        obj.tsPageRtoSumRes = parseFloat(obj.tsPageRtoSumRes.toFixed(6));
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
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const handleTabChange = (event, newValue) => {
        setMenuKey(newValue);
    }

    const handleSummaryTabChange = (event, newValue) => {
        setSummaryChartKey(newValue);
    }

    const onClickUri = (dataset) => {
        if (dataset) {
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
                schItem: {}
            };

            navigate("/protocol/http/uri/detail", { state: stateParam });
        }
    }

    const getUseragentIcon = useCallback((category, val) => {
        try {
            const useragentNm = _.lowerCase(val).replace(/(\s*)/g, "");
            const requireUseragent = require("../../images/useragent_icon/" + category + "/" + useragentNm + ".png");
            return (
                <img src={requireUseragent.default} style={{ width: "1.2rem", marginRight: ".3rem", verticalAlign: "bottom" }} />
            );
        } catch {
            return "";
        }
    }, []);

    const getCountryIcon = useCallback((countryId) => {
        try {
            const resGeoCode = _.lowerCase(getGeoKeyByCountryId(countryId));
            const requireGeoJson = require("../../images/country_icon/" + resGeoCode + ".png");
            return (
                <img src={requireGeoJson.default} />
            );
        } catch {
            return "";
        }
    }, []);

    const prevEvt = () => {
        if (location.state) {
            navigate("/protocol/http/pages", { state: { schItem: location.state.schItem } });
        }
    }

    // import content
    const SummaryErrChartsContent = useMemo(() => (
        <>
            {!_.isEmpty(summaryErrChart) && <EchartsComponent option={summaryErrChart} style={{ width: "100%", height: "340px" }} />}
        </>
    ), [summaryErrChart]);

    const SummaryTbContent = useMemo(() => (
        <HttpPagesDetailSummaryTb pageData={pageData} getUseragentIcon={getUseragentIcon} getCountryIcon={getCountryIcon} />
    ), [pageData]);

    const HttpTbContent = useMemo(() => (
        <HttpPagesDetailHttpTb pageData={pageData} getUseragentIcon={getUseragentIcon} getCountryIcon={getCountryIcon} />
    ), [pageData]);

    const PerformanceTbContent = useMemo(() => (
        <HttpPagesDetailPerformanceTb pageData={pageData} />
    ), [pageData]);

    const ThroughputTbContent = useMemo(() => (
        <HttpPagesDetailThroughputTb pageData={pageData} />
    ), [pageData]);

    const AvailabilityTbContent = useMemo(() => (
        <HttpPagesDetailAvailabilityTb pageData={pageData} />
    ), [pageData]);

    return (
        <>
            <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper' }}>
                <Grid container>
                    <Grid item sm={11.5}>
                        <Tabs value={menuKey} onChange={handleTabChange} className="small">
                            <Tab label="Summary" />
                            <Tab label="HTTP" />
                            <Tab label="Performance" />
                            <Tab label="Throughput" />
                            <Tab label="Availability" />
                            <Tab label="URI" />
                            <Tab label={<Typography component="span">Latency&nbsp;
                                <WindowOpener className="inline-block" url="/popup/common/xrange" width="1500" height="850" state={latencyChartSeries}>
                                    <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
                                </WindowOpener>
                            </Typography>} />
                        </Tabs>
                    </Grid>
                    <Grid item sm={.5}>
                        <Button variant="contained" color="primary" size="small" startIcon={<FontAwesomeIcon icon={faAngleLeft} />} sx={{ mt: .5 }} onClick={prevEvt}>목록</Button>
                    </Grid>
                </Grid>
            </Box>

            <Box id="mainEl">
                {
                    (() => {
                        switch (menuKey) {
                            case 0:
                                return <Grid container>
                                    <Grid item sm={8} sx={{ height: "370px" }}>
                                        {SummaryTbContent}
                                    </Grid>
                                    <Grid item sm={4} sx={{ height: "370px", bgcolor: "background.paper" }}>
                                        <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'rgba(240, 240, 240, 0.8)' }}>
                                            <Tabs value={summaryChartKey} onChange={handleSummaryTabChange} variant="fullWidth" className="small">
                                                <Tab label="HTTP Error(%)" />
                                                <Tab label="TCP Error(%)" />
                                            </Tabs>
                                        </Box>
                                        {SummaryErrChartsContent}
                                    </Grid>
                                    <Grid item sm={12} className="border" sx={{ bgcolor: "background.paper" }}>
                                        <Box sx={{ overflowY: "auto", height: summaryLatencyHeight + "px" }}>
                                            {summaryLatencySeries.length > 0 && <Chart options={latencyChartOption} series={summaryLatencySeries} type="rangeBar" width="99%" height={httpUriData.length > 25 ? 20 * httpUriData.length : "95%"} />}
                                        </Box>
                                    </Grid>
                                </Grid>
                            case 1:
                                return <Box sx={{ height: "100%" }}>
                                    {HttpTbContent}
                                </Box>
                            case 2:
                                return <Box sx={{ height: "100%" }}>
                                    {PerformanceTbContent}
                                </Box>
                            case 3:
                                return <Box sx={{ height: "100%" }}>
                                    {ThroughputTbContent}
                                </Box>
                            case 4:
                                return <Box sx={{ height: "100%" }}>
                                    {AvailabilityTbContent}
                                </Box>
                            case 5:
                                return <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                    <AgGridModule
                                        gridName="uriGridApi"
                                        columnDefs={httpUriColumnDefs}
                                        rowData={httpUriData}
                                        onCellMouseDown={onCellMouseDown}
                                        onGridReady={onGridReady}
                                        handleResize={handleResize} />
                                </Box>
                            case 6:
                                return <Box className="border" sx={{ bgcolor: "background.paper", overflowY: "auto", height: "100%" }}>
                                    {latencyChartSeries.length > 0 && <Chart options={latencyChartOption} series={latencyChartSeries} type="rangeBar" width="99%" height={httpUriData.length > 50 ? 20 * httpUriData.length : "98%"} />}
                                </Box>
                        }
                    })()
                }
            </Box>

            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
            {showLoader && (<Loader />)}
        </>
    );
};

export default HttpPagesDetail;