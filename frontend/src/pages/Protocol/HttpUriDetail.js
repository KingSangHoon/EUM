import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { useLocation, useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { Grid, Button, Box, Tabs, Tab, Card, CardHeader, CardContent } from '@mui/material';
import Chart from 'react-apexcharts';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowUpRightFromSquare, faAngleLeft } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../axios';
import { gridApiObj, timeDeltaSec, timeAverageNs, getGeoKeyByCountryId, getFloatSec, getNs } from '../../lib/common';
import Loader from '../../components/Loader';
import EchartsComponent from '../../lib/EchartsComponent';
import HttpUriDetailSummaryTb from './HttpUriDetailSummaryTb';
import HttpUriDetailHttpTb from './HttpUriDetailHttpTb';
import HttpUriDetailPerformanceTb from './HttpUriDetailPerformanceTb';
import HttpUriDetailThroughputTb from './HttpUriDetailThroughputTb';
import HttpUriDetailAvailabilityTb from './HttpUriDetailAvailabilityTb';

const HttpUriDetail = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { responseCode } = useSelector((state) => state.staticVar);

    const [showLoader, setShowLoader] = useState(false);

    const [menuKey, setMenuKey] = useState(0);

    const [uriData, setUriData] = useState({});

    const [summaryErrChart, setSummaryErrChart] = useState({});
    const [summaryChartHeight, setSummaryChartHeight] = useState(0);

    const [latencySeries, setLatencySeries] = useState([]);
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
                show: false
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

                return `<div class="apexcharts-tooltip-rangebar"><span style="color: ${target.color}">● </span><b>${target.name}:</b><div>
                        <span class="category">${data.x}: </span>
                        <span class="value">${resultData}</span></div></div>`;
            }
        }
    });

    useEffect(() => {
        if (location.state && location.state.request) {
            getDetailInfo();
        }

        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

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

    const autoComponentSize = () => {
        if (document.getElementById('mainEl')) {
            const mainHeight = document.body.clientHeight;

            document.getElementById('mainEl').style.height = mainHeight - 15 + 'px';
            setSummaryChartHeight(mainHeight - 385);
        }
    }

    const getDetailInfo = () => {
        setShowLoader(true);

        axiosConf.post('/api/protocol/uri/detail', location.state.request).then(res => {
            if (!_.isEmpty(res.data)) {
                formatUriCurrData(res.data);
                createLatencyChart(res.data);
                createSummaryErrChart(res.data);
            }

            setUriData(res.data);
            setShowLoader(false);
        });
    }

    const createLatencyChart = (uriData) => {
        let latencySeries = formatUriLatencyData(uriData);

        _.forEach(latencySeries, (obj) => {
            _.forEach(_.range(24), (i) => {
                obj.data.push({ x: "Empty Name" + i, y: [0, 0] });
            });
        });

        setLatencySeries(latencySeries);
    }

    const createSummaryErrChart = (formatUriData) => {
        let cloneChartOption = _.cloneDeep(summaryPieOption());

        cloneChartOption.series[0].data.push({
            name: "Retransmission",
            value: formatUriData.retransmissionCntReq + formatUriData.retransmissionCntRes
        }, {
            name: "Fast Retransmission",
            value: formatUriData.fastRetransmissionCntReq + formatUriData.fastRetransmissionCntRes
        }, {
            name: "Out Of Order",
            value: formatUriData.outOfOrderCntReq + formatUriData.outOfOrderCntRes
        }, {
            name: "Lost Segment",
            value: formatUriData.lostSegCntReq + formatUriData.lostSegCntRes
        }, {
            name: "Ack Lost",
            value: formatUriData.ackLostCntReq + formatUriData.ackLostCntRes
        }, {
            name: "Duplication Ack",
            value: formatUriData.dupAckCntReq + formatUriData.dupAckCntRes
        }, {
            name: "Zero Window",
            value: formatUriData.zeroWinCntReq + formatUriData.zeroWinCntRes
        }, {
            name: "Spurious Retransmission",
            value: formatUriData.spuriousRetransmissionCntReq + formatUriData.spuriousRetransmissionCntRes
        }, {
            name: "Overlap",
            value: formatUriData.overlapCntReq + formatUriData.overlapCntRes
        }, {
            name: "Overlap Attack",
            value: formatUriData.overlapAttackCntReq + formatUriData.overlapAttackCntRes
        }, {
            name: "Zero Window Probe",
            value: formatUriData.zeroWinProbeCntReq + formatUriData.zeroWinProbeCntRes
        }, {
            name: "Zero Window Probe Ack",
            value: formatUriData.zeroWinProbeAckCntReq + formatUriData.zeroWinProbeAckCntRes
        }, {
            name: "Keep Alive",
            value: formatUriData.keepAliveCntReq + formatUriData.keepAliveCntRes
        }, {
            name: "Keep Alive Ack",
            value: formatUriData.keepAliveAckCntReq + formatUriData.keepAliveAckCntRes
        }, {
            name: "Window Update",
            value: formatUriData.winUpdateCntReq + formatUriData.winUpdateCntRes
        });

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

    const formatUriLatencyData = (obj) => {
        const tsFirstFrameArrivalNs = getNs(obj.realtimeUriKey.tsFrameArrival.toFixed(9));

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

        obj.uri = obj.httpUri + obj.httpUriSplit;

        return [{
            name: "Response Time",
            color: "#90ED7D",
            data: [{ x: obj.uri, y: [tsResBeginSec, tsResEndSec] }]
        },
        {
            name: "Wait Time",
            color: "#8085E9",
            data: [{ x: obj.uri, y: [tsReqEndSec, tsResBeginSec] }]
        },
        {
            name: "Request Time",
            color: "#F15C80",
            data: [{ x: obj.uri, y: [tsReqBeginSec, tsReqEndSec] }]
        },
        {
            name: "Connection Time",
            color: "#1EB7FF",
            data: [{ x: obj.uri, y: [tsConnBeginSec, tsReqBeginSec] }]
        }];
    }

    const formatUriCurrData = (obj) => {
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

        obj.strFrameLandoff = obj.realtimeUriKey.tsFrameLandOff ? moment(obj.realtimeUriKey.tsFrameLandOff * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strFrameArrival = obj.realtimeUriKey.tsFrameArrival ? moment(obj.realtimeUriKey.tsFrameArrival * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strFrameArrivalPage = obj.tsFrameArrivalPage ? moment(obj.tsFrameArrivalPage * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strFrameLandoffPage = obj.tsFrameLandoffPage ? moment(obj.tsFrameLandoffPage * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strReqPktFirst = obj.tsReqPktFirst ? moment(obj.tsReqPktFirst * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strReqPktPush = obj.tsReqPktPush ? moment(obj.tsReqPktPush * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strReqPktLast = obj.tsReqPktLast ? moment(obj.tsReqPktLast * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strResPktFirst = obj.tsResPktFirst ? moment(obj.tsResPktFirst * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strResPktPush = obj.tsResPktPush ? moment(obj.tsResPktPush * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strResPktLast = obj.tsResPktLast ? moment(obj.tsResPktLast * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strRttSyn = obj.tsRttSyn ? moment(obj.tsRttSyn * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strRttSynAck = obj.tsRttSynAck ? moment(obj.tsRttSynAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strRttFirstAck = obj.tsRttFirstAck ? moment(obj.tsRttFirstAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strRttReqAck = obj.tsRttReqAck ? moment(obj.tsRttReqAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strRttAckReqAck = obj.tsRttAckReqAck ? moment(obj.tsRttAckReqAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strRttResAck = obj.tsRttResAck ? moment(obj.tsRttResAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";
        obj.strRttAckResAck = obj.tsRttAckResAck ? moment(obj.tsRttAckResAck * 1000).format("YYYY-MM-DD HH:mm:ss") : "";

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

        obj.totFrameArrival = obj.strFrameArrival + " (" + obj.realtimeUriKey.tsFrameArrival + ")";
        obj.totFrameLandoff = obj.strFrameLandoff + " (" + obj.realtimeUriKey.tsFrameLandOff + ")";
        obj.totFrameArrivalPage = obj.strFrameArrivalPage + " (" + obj.tsFrameArrivalPage + ")";
        obj.totFrameLandoffPage = obj.strFrameLandoffPage + " (" + obj.tsFrameLandoffPage + ")";
        obj.totReqPktFirst = obj.strReqPktFirst + " (" + obj.tsReqPktFirst + ")";
        obj.totResPktFirst = obj.strResPktFirst + " (" + obj.tsResPktFirst + ")";
        obj.totReqPktLast = obj.strReqPktLast + " (" + obj.tsReqPktLast + ")";
        obj.totResPktLast = obj.strResPktLast + " (" + obj.tsResPktLast + ")";
        obj.totReqPktPush = obj.strReqPktPush + " (" + obj.tsReqPktPush + ")";
        obj.totResPktPush = obj.strResPktPush + " (" + obj.tsResPktPush + ")";
        obj.totRttSyn = obj.strRttSyn + " (" + obj.tsRttSyn + ")";
        obj.totRttSynAck = obj.strRttSynAck + " (" + obj.tsRttSynAck + ")";
        obj.totRttFirstAck = obj.strRttFirstAck + " (" + obj.tsRttFirstAck + ")";
        obj.totRttReqAck = obj.strRttReqAck + " (" + obj.tsRttReqAck + ")";
        obj.totRttAckReqAck = obj.strRttAckReqAck + " (" + obj.tsRttAckReqAck + ")";
        obj.totRttResAck = obj.strRttResAck + " (" + obj.tsRttResAck + ")";
        obj.totRttAckResAck = obj.strRttAckResAck + " (" + obj.tsRttAckResAck + ")";
        obj.formatSrcIpGeo = obj.countryIdReq === 0 ? obj.srcIp + ":" + obj.srcPort : obj.srcIp + ":" + obj.srcPort + " (" + obj.srcGeo + ") ";
        obj.formatDstIpGeo = obj.countryIdRes === 0 ? obj.dstIp + ":" + obj.dstPort : obj.dstIp + ":" + obj.dstPort + " (" + obj.dstGeo + ") ";

        obj.reqAvgAckRtt = timeAverageNs(obj.ackRttSumReq, obj.ackRttCntReq);
        obj.resAvgAckRtt = timeAverageNs(obj.ackRttSumRes, obj.ackRttCntRes);
        obj.reqAvgAckRto = timeAverageNs(obj.ackRtoSumReq, obj.ackRtoCntReq);
        obj.resAvgAckRto = timeAverageNs(obj.ackRtoSumReq, obj.ackRtoCntRes);

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
    }


    const handleTabChange = (event, newValue) => {
        setMenuKey(newValue);
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
            navigate("/protocol/http/uri", { state: { schItem: location.state.schItem } });
        }
    }

    const moveToPage = () => {
        const reqParam = location.state.request;
        const stateParam = {
            request: {
                pageIdx: reqParam.pageIdx,
                tsFrameArrival: uriData.tsFrameArrivalPage.toString(),
                tsFrameLandOff: uriData.tsFrameLandoffPage.toString(),
                srcIp: reqParam.srcIp,
                dstIp: reqParam.dstIp,
                srcPort: reqParam.srcPort,
                dstPort: reqParam.dstPort
            },
            schItem: {}
        };

        navigate("/protocol/http/pages/detail", { state: stateParam });
    }

    // import content
    const SummaryErrChartsContent = useMemo(() => (
        <>
            {!_.isEmpty(summaryErrChart) && <EchartsComponent option={summaryErrChart} style={{ width: "100%", height: summaryChartHeight + "px" }} />}
        </>
    ), [summaryErrChart, summaryChartHeight]);

    const SummaryTbContent = useMemo(() => (
        <HttpUriDetailSummaryTb uriData={uriData} getUseragentIcon={getUseragentIcon} getCountryIcon={getCountryIcon} />
    ), [uriData]);

    const HttpTbContent = useMemo(() => (
        <HttpUriDetailHttpTb uriData={uriData} getUseragentIcon={getUseragentIcon} getCountryIcon={getCountryIcon} />
    ), [uriData]);

    const PerformanceTbContent = useMemo(() => (
        <HttpUriDetailPerformanceTb uriData={uriData} />
    ), [uriData]);

    const ThroughputTbContent = useMemo(() => (
        <HttpUriDetailThroughputTb uriData={uriData} />
    ), [uriData]);

    const AvailabilityTbContent = useMemo(() => (
        <HttpUriDetailAvailabilityTb uriData={uriData} />
    ), [uriData]);

    return (
        <>
            <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper' }}>
                <Grid container>
                    <Grid item sm={10.5}>
                        <Tabs value={menuKey} onChange={handleTabChange} className="small">
                            <Tab label="Summary" />
                            <Tab label="HTTP" />
                            <Tab label="Performance" />
                            <Tab label="Throughput" />
                            <Tab label="Availability" />
                        </Tabs>
                    </Grid>
                    <Grid item sm={1} textAlign="right">
                        <Button variant="text" color="primary" size="small" startIcon={<FontAwesomeIcon icon={faArrowUpRightFromSquare} />} sx={{ mt: .5 }} onClick={moveToPage}>Page 이동</Button>
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
                                    <Grid item sm={12} sx={{ height: "344px" }}>
                                        {SummaryTbContent}
                                    </Grid>
                                    <Grid item sm={5} className="bottom_underline">
                                        <Card>
                                            <CardHeader title="TCP Error(%)" />
                                            <CardContent sx={{ height: summaryChartHeight + "px" }}>
                                                {SummaryErrChartsContent}
                                            </CardContent>
                                        </Card>
                                    </Grid>
                                    <Grid item sm={7} className="border" sx={{ bgcolor: "background.paper" }}>
                                        <Card>
                                            <CardHeader title="Latency" />
                                            <CardContent sx={{ overflowY: "auto", height: summaryChartHeight + "px" }}>
                                                {latencySeries.length > 0 && <Chart options={latencyChartOption} series={latencySeries} type="rangeBar" width="98%" height="97%" />}
                                            </CardContent>
                                        </Card>
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
                        }
                    })()
                }
            </Box>

            {showLoader && (<Loader />)}
        </>
    );
};

export default HttpUriDetail;