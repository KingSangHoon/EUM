import React, { useEffect, useMemo, useState, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { useLocation } from 'react-router-dom';
import { styled } from '@mui/material/styles';
import {
    Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent, Chip, Paper, Button, FormControlLabel, Checkbox,
    TableContainer, Table, TableHead, TableBody, TableRow, TableCell, RadioGroup, Radio
} from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMap, faLineChart } from "@fortawesome/free-solid-svg-icons";

/* GeoJson */
import Korea from './MapGeojson/World/kr.json';

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj } from '../../lib/common';
import Loader from '../../components/Loader';
import EchartsComponent from '../../lib/EchartsComponent';
import SearchMap from '../Common/Search/SearchMap';

/* Echarts */
import * as echarts from 'echarts';
import EchartsReact from 'echarts-for-react/lib/core';

echarts.registerMap("Korea", Korea);

const staticGeoCode = {
    "부산광역시": "bs",
    "충청북도": "cb",
    "충청남도": "cn",
    "대구광역시": "dg",
    "대전광역시": "dj",
    "경상북도": "gb",
    "광주광역시": "gj",
    "경상남도": "gn",
    "강원도": "gw",
    "인천광역시": "ic",
    "전라북도": "jb",
    "제주도": "jj",
    "전라남도": "jn",
    "경기도": "kg",
    "세종특별자치시": "sj",
    "서울특별시": "so",
    "울산광역시": "us"
};

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const DomesticIndex = () => {
    const resetSchItem = {
        startDate: moment().subtract('30', 'minutes').toDate(),
        startHours: moment().subtract('30', 'minutes').format("HH"),
        startMinutes: moment().subtract('30', 'minutes').format("mm"),
        startSeconds: moment().subtract('30', 'minutes').format("ss"),
        endDate: moment().toDate(),
        endHours: moment().format("HH"),
        endMinutes: moment().format("mm"),
        endSeconds: moment().format("ss"),
        autoDate: "30minutes",
        interval: "1min",
        resource: [{ name: "Page Response Time", key: "tsPageAvg", tab: "http" }]
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);
    const [showLoader, setShowLoader] = useState(false);

    const [activeChipData, setActiveChipData] = useState("Korea");
    const [chipData, setChipData] = useState([{ key: "Korea", label: '국내' }]);

    const [viewType, setViewType] = useState("client");

    const [showMap, setShowMap] = useState(true);
    const [showGrid, setShowGrid] = useState(true);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [allMapKey, setAllMapKey] = useState(null);
    const [allMapView, setAllMapView] = useState(false);

    const [chartOption, setChartOption] = useState({});

    const [geoColumnDefs, setGeoColumnDefs] = useState([]);
    const [geoData, setGeoData] = useState({ client: [], server: [] });

    const [mapOption, setMapOption] = useState({
        tooltip: {},
        toolbox: {
            show: true,
            left: 'left',
            top: 'top',
            feature: {
                restore: {},
                saveAsImage: {}
            }
        },
        geo: {
            map: "",
            roam: true,
            silent: false,
            itemStyle: {
                borderColor: "#DFE0E1",
                borderWidth: 1,
                areaColor: "#F7F7F7"
            },
            label: {
                normal: {
                    show: true,
                    textStyle: {
                        fontSize: 10
                    }
                }
            },
            zoom: 1.2,
            zlevel: 1
        },
        visualMap: {
            type: 'continuous',
            left: 'right',
            min: 0,
            text: ['High', 'Low'],
            calculable: true,
            seriesIndex: [0],
            zlevel: 2
        },
        series: [{
            name: '',
            type: 'map',
            geoIndex: 0,
            zlevel: 3,
            data: []
        },
        {
            name: '',
            type: 'effectScatter',
            coordinateSystem: 'geo',
            symbolSize: (params) => {
                return (params[2] / 100) * 7 + 5;
            },
            itemStyle: {
                color: '#7CB5EC'
            },
            encode: {
                tooltip: 2
            },
            zlevel: 1,
            data: []
        }]
    });

    useEffect(() => {
        getMapData();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, [showGrid]);

    const autoComponentSize = useCallback(() => {
        if (document.getElementById('geoGridEl')) {
            const mainHeight = document.body.clientHeight - document.getElementById("searchEl").clientHeight;

            if (showGrid) {
                _.forEach(document.getElementsByClassName("geoMapEl"), (el) => {
                    el.style.height = "400px";
                });

                document.getElementById('geoGridEl').style.height = mainHeight - 460 + 'px';
            } else {
                _.forEach(document.getElementsByClassName("geoMapEl"), (el) => {
                    el.style.height = mainHeight - 50 + "px";
                });
            }
        }

        handleResize();
    }, [showGrid]);

    const getMapData = useCallback((e, bridgeData, resetItem) => {
        if (bridgeData) {
            setSchItem({ ...schItem, [bridgeData.target.id]: bridgeData.target.value });
        } else {
            const targetData = resetItem ? resetItem : schItem;
            let formatColumnDefs = [];

            if (targetData.resource[0].group === "Traffic") formatColumnDefs = getTrafficGridColumnDefs();
            else if (targetData.resource[0].group === "Ip") formatColumnDefs = getIpGridColumnDefs();
            else if (targetData.resource[0].group === "Tcp") formatColumnDefs = getTcpGridColumnDefs();
            else if (targetData.resource[0].group === "Udp") formatColumnDefs = getUdpGridColumnDefs();
            else formatColumnDefs = getHttpGridColumnDefs();

            setGeoColumnDefs(formatColumnDefs);

            if (showMap) {
                createMapGeoJson(activeChipData);
            } else {
                createChartTrend();
            }
        }
    }, [schItem, showMap, activeChipData]);

    const renderBtnViewChange = (mapFlag) => {
        if (showMap === mapFlag) {
            return;
        }

        setShowMap(mapFlag);

        if (mapFlag) {
            createMapGeoJson(activeChipData);
        } else {
            createChartTrend();
        }
    }

    const createChartTrend = () => {
        let cloneChartOption = _.cloneDeep(scatterOption());

        cloneChartOption.series[0].data = [
            [1665626427998, _.random(0, 100)], [1665626427996, _.random(0, 100)], [1665626427995, _.random(0, 100)], [1665626427995, _.random(0, 100)], [1665626427995, _.random(0, 100)],
            [1665626427985, _.random(0, 100)], [1665626427975, _.random(0, 100)], [1665626427939, _.random(0, 100)], [1665626427863, _.random(0, 100)], [1665626427845, _.random(0, 100)]
        ];

        cloneChartOption.series[1].data = [
            [1665626427998, _.random(0, 100)], [1665626427996, _.random(0, 100)], [1665626427995, _.random(0, 100)], [1665626427995, _.random(0, 100)], [1665626427995, _.random(0, 100)],
            [1665626427985, _.random(0, 100)], [1665626427975, _.random(0, 100)], [1665626427939, _.random(0, 100)], [1665626427863, _.random(0, 100)], [1665626427845, _.random(0, 100)]
        ];

        setChartOption(cloneChartOption);
    }

    const scatterOption = () => {
        return {
            xAxis: {
                type: "time",
                boundaryGap: false,
                axisLabel: {
                    formatter: (value) => {
                        return moment(value).format('MM-DD') + "\n" + moment(value).format("HH:mm:ss");
                    }
                }
            },
            yAxis: {},
            tooltip: {},
            series: [{
                type: "line",
                name: "test 1",
                areaStyle: {},
                showSymbol: false,
                data: []
            },
            {
                type: "line",
                name: "test 2",
                areaStyle: {},
                showSymbol: false,
                data: []
            }]
        };
    }

    const createMapGeoJson = (geoKey) => {
        let cloneMapOption = _.cloneDeep(mapOption);

        cloneMapOption.geo.map = geoKey;

        cloneMapOption.series[0].name = schItem.resource[0].name;
        cloneMapOption.series[0].data = [
            { name: '강원도', value: _.random(1, 100) },
            { name: '경기도', value: _.random(1, 100) },
            { name: '인천광역시', value: _.random(1, 100) },
            { name: '경상북도', value: _.random(1, 100) },
            { name: '충청남도', value: _.random(1, 100) }
        ];

        cloneMapOption.series[1].name = schItem.resource[0].name;

        // 임시 테스트
        const geoCode = {
            "so": 1,
            "bs": 2,
            "dg": 3,
            "ic": 4,
            "gj": 5,
            "dj": 6,
            "us": 7,
            "sj": 8,
            "kg": 9,
            "kg-all": 9,
            "gw": 10,
            "cb": 11,
            "cb-all": 11,
            "cn": 12,
            "cn-all": 12,
            "jb": 13,
            "jb-all": 13,
            "jn": 14,
            "gb": 15,
            "gb-all": 15,
            "gn": 16,
            "gn-all": 16,
            "jj": 17
        };

        if (geoKey === "Korea") {
            cloneMapOption.geo.silent = false;
            cloneMapOption.series[1].data = [];

            let reqData = {};
            reqData.category = 'primary';
            reqData.primaryId = null;

            /* axiosConf.post('/api/setting/geoDomestic/find/domestic', reqData).then(res => {
                _.forEach(res.data, (obj) => {
                    cloneMapOption.series[1].data.push({
                        name: obj.name,
                        value: [parseFloat(obj.geoX), parseFloat(obj.geoY), _.random(1, 100)]
                    });
                });

                setMapOption(cloneMapOption);
            }); */

            setMapOption(cloneMapOption);
        } else {
            cloneMapOption.geo.silent = true;
            cloneMapOption.series[1].data = [];

            /* axiosConf.get('/api/setting/geoDomestic/findAllDomesticSub1Info/' + geoCode[geoKey]).then(res => {
                _.forEach(res.data, (obj) => {
                    cloneMapOption.series[1].data.push({
                        name: obj.name,
                        value: [parseFloat(obj.geoX), parseFloat(obj.geoY), _.random(1, 100)]
                    });
                });

                setMapOption(cloneMapOption);
            }); */

            setMapOption(cloneMapOption);
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
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const handleDelete = (chipToDelete) => () => {
        if (activeChipData === chipToDelete.key) {
            const index = _.findIndex(chipData, { key: chipToDelete.key });
            menuClickEvt(chipData[index - 1].key);
        }

        setChipData((chips) => chips.filter((chip) => chip.key !== chipToDelete.key));
    }

    const menuClickEvt = (key) => {
        if (showMap) {
            createMapGeoJson(key);
            regAllMapRegion(key);
        } else {
            createChartTrend();
        }

        setActiveChipData(key);
    }

    const allMapClickEvt = (e) => {
        if (showMap) {
            const geoKey = e.target.checked ? allMapKey + "-all" : allMapKey;
            createMapGeoJson(geoKey);
        } else {
            createChartTrend();
        }

        setAllMapView(e.target.checked);
    }

    const onMapSeriesEvents = {
        click: (params) => {
            if (params.seriesType === "map" && activeChipData === "Korea") {
                addCountryData(params.name);
            }
        }
    }

    const addCountryData = (paramsName) => {
        const filterLabel = _.filter(chipData, (obj) => {
            return obj.label === paramsName;
        });

        if (filterLabel.length > 0) {
            alert("이미 추가된 지역입니다.");
            return;
        }

        try {
            const requireJson = require("./MapGeojson/Domestic/kr-" + staticGeoCode[paramsName] + ".json");
            echarts.registerMap(staticGeoCode[paramsName], requireJson);

            createMapGeoJson(staticGeoCode[paramsName]);
            regAllMapRegion(staticGeoCode[paramsName]);

            setChipData([...chipData, { key: staticGeoCode[paramsName], label: paramsName }]);
            setActiveChipData(staticGeoCode[paramsName]);
        } catch {
            alert("지역 정보가 없습니다.");
        }
    }

    const regAllMapRegion = (geoCode) => {
        try {
            const requireAllJson = require("./MapGeojson/Domestic/kr-" + geoCode + "-all.json");
            echarts.registerMap(geoCode + "-all", requireAllJson);

            setAllMapKey(geoCode);
            setAllMapView(false);
        } catch {
            setAllMapKey(null);
            setAllMapView(false);
        }
    }

    const handleKeyPress = (e) => {
        setViewType(e.target.value);
    }

    const onCellClickEvt = (event) => {
        if (activeChipData === "Korea") {
            if (event.colDef.field === "countryName") {
                console.log(event.data)
                //addCountryData(event.data);
            }
        }
    }

    const getHttpGridColumnDefs = () => {
        return [{
            headerName: 'City',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                if (activeChipData === "Korea") {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
                } else {
                    return params.value;
                }
            },
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Page Response Time',
            field: 'tsPageAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Session Count',
            field: 'pageSessionCntSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Err Session Count',
            field: 'connErrSessionCntSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Stopped Transaction Count',
            field: 'stoppedTransactionCntSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkt Count(req)',
            field: 'pagePktCntReqSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkt Count(res)',
            field: 'pagePktCntResSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'RTT Connection Count(req)',
            field: 'pageRttConnCntReqSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'RTT Connection Count(res)',
            field: 'pageRttConnCntResSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'RTT ACK Count(req)',
            field: 'pageRttAckCntReqSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'RTT ACK Count(res)',
            field: 'pageRttAckCntResSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'TCP Error(%)',
            field: 'tcpError',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'HTTP Error(%)',
            field: 'httpError',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        }];
    }

    const getTrafficGridColumnDefs = () => {
        return [{
            headerName: 'City',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                if (activeChipData === "Korea") {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
                } else {
                    return params.value;
                }
            },
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Len (req)',
            field: 'lenReqPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Len (res)',
            field: 'lenResPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkts (req)',
            field: 'pktsReqPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkts (res)',
            field: 'pktsResPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Len Delta (req)',
            field: 'lenReqDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Len Delta (res)',
            field: 'lenResDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkts Delta (req)',
            field: 'pktsReqDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkts Delta (res)',
            field: 'pktsResDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkt Len Min (req)',
            field: 'pktLenMinReqMin',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkt Len Min (res)',
            field: 'pktLenMinResMin',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkt Len Max (req)',
            field: 'pktLenMaxReqMax',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Traffic Pkt Len Max (res)',
            field: 'pktLenMaxResMax',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        }];
    }

    const getIpGridColumnDefs = () => {
        return [{
            headerName: 'City',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                if (activeChipData === "Korea") {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
                } else {
                    return params.value;
                }
            },
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Packet Fragment (req)',
            field: 'fragPktsPerSecReqAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Packet Fragment (res)',
            field: 'fragPktsPerSecResAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Packet Fragment Delta (req)',
            field: 'fragPktsDeltaReqSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Packet Fragment Delta (res)',
            field: 'fragPktsDeltaResSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'TTL Min (req)',
            field: 'ttlMinReqMin',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'TTL Min (res)',
            field: 'ttlMinResMin',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'TTL Max (req)',
            field: 'ttlMaxReqMax',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'TTL Max (res)',
            field: 'ttlMaxResMax',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Overlap Pkts (req)',
            field: 'overlapPktsPerSecReqAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Overlap Pkts (res)',
            field: 'overlapPktsPerSecResAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Overlap Bytes (req)',
            field: 'overlapBytesPerSecReqAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Overlap Bytes (res)',
            field: 'overlapBytesPerSecResAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        }];
    }

    const getTcpGridColumnDefs = () => {
        return [{
            headerName: 'City',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                if (activeChipData === "Korea") {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
                } else {
                    return params.value;
                }
            },
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu (req)',
            field: 'lenPduReqPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu (res)',
            field: 'lenPduResPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu (req)',
            field: 'pktsPduReqPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu (res)',
            field: 'pktsPduResPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu Delta (req)',
            field: 'lenPduReqDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu Delta (res)',
            field: 'lenPduResDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu Delta (req)',
            field: 'pktsPduReqDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu Delta (res)',
            field: 'pktsPduResDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Rtt Syn',
            field: 'tsRttSynAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Rtt Syn Ack',
            field: 'tsRttSynAckAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Rtt First Ack (req)',
            field: 'tsRttFirstAckReqAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Rtt First Ack (res)',
            field: 'tsRttFirstAckResAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        }];
    }

    const getUdpGridColumnDefs = () => {
        return [{
            headerName: 'City',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                if (activeChipData === "Korea") {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
                } else {
                    return params.value;
                }
            },
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu (req)',
            field: 'lenPduReqPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu (res)',
            field: 'lenPduResPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu (req)',
            field: 'pktsPduReqPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu (res)',
            field: 'pktsPduResPerSecAvg',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu Delta (req)',
            field: 'lenPduReqDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Len Pdu Delta (res)',
            field: 'lenPduResDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu Delta (req)',
            field: 'pktsPduReqDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Pkts Pdu Delta (res)',
            field: 'pktsPduResDeltaSum',
            cellClass: ['text-right'],
            filter: "agTextColumnFilter"
        }];
    }

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);
        getMapData(null, null, resetSchItem);
    }, [schItem, showMap, activeChipData]);

    // import content
    const SearchContent = useMemo(() => (
        <SearchMap schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={getMapData} resetEvt={resetEvt} />
    ), [schItem, showGrid, activeChipData]);

    const ChartsContent = useMemo(() => (
        <>
            {!_.isEmpty(chartOption) && <EchartsComponent option={chartOption} style={{ width: "100%", height: "380px", marginTop: ".5rem" }} />}
        </>
    ), [chartOption]);

    return (
        <>
            {SearchContent}

            <Grid container spacing={.5} mt={2}>
                <Grid item md={9}>
                    <Card>
                        <CardHeader sx={{ p: "0 !important" }} title={(
                            <Paper sx={{ display: 'flex', flexWrap: 'wrap', listStyle: 'none', p: 0, m: 0, background: "none", height: "1.5rem", overflowY: "auto" }} component="ul">
                                {chipData.map((obj) => {
                                    return (
                                        <ListItem key={obj.key} onClick={() => menuClickEvt(obj.key)}>
                                            <Chip className="cursorp" label={obj.label} sx={{ backgroundColor: activeChipData === obj.key ? "rgba(0, 0, 0, 0.08)" : "transparent" }}
                                                onDelete={obj.label === '국내' ? undefined : handleDelete(obj)} />
                                        </ListItem>
                                    );
                                })}
                            </Paper>
                        )} />
                        <CardContent className="geoMapEl">
                            <Box sx={{ float: "right" }}>
                                {allMapKey && <FormControlLabel sx={{ "& .MuiFormControlLabel-label": { zIndex: 1 } }} control={<Checkbox name="allMapView" sx={{ zIndex: 1 }} checked={allMapView} onChange={allMapClickEvt} />} label="전체" />}

                                <Button variant="text" color="inherit" size="small" sx={{ zIndex: 1, bgcolor: showMap ? "#e6f7ff" : "#fff", color: "#1890ff" }} onClick={() => renderBtnViewChange(true)}>
                                    <FontAwesomeIcon icon={faMap} style={{ fontSize: "1rem" }} />
                                </Button>
                                <Button variant="text" color="inherit" size="small" sx={{ zIndex: 1, bgcolor: showMap ? "#fff" : "#e6f7ff", color: "#1890ff" }} onClick={() => renderBtnViewChange(false)}>
                                    <FontAwesomeIcon icon={faLineChart} style={{ fontSize: "1rem" }} />
                                </Button>
                            </Box>

                            <Box className={showMap ? "" : "none"} sx={{ height: "100%" }}>
                                {mapOption.geo.map !== "" && <EchartsReact echarts={echarts} option={mapOption} style={{ height: '100%', width: '99%' }} onEvents={onMapSeriesEvents} />}
                            </Box>
                            <Box className={showMap ? "none" : ""} sx={{ height: "100%" }}>
                                {ChartsContent}
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item md={3}>
                    <Card>
                        <CardHeader title={schItem.resource[0].name} />
                        <CardContent className="geoMapEl" sx={{ p: "0 !important" }}>
                            <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
                                <Table stickyHeader size="small">
                                    <TableHead className="thead-dark">
                                        <TableRow>
                                            <TableCell sx={{ width: "2rem" }}></TableCell>
                                            <TableCell>Country</TableCell>
                                            <TableCell align="right">Data</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody >
                                        <TableRow sx={{ "&:nth-of-type(odd)": { backgroundColor: "rgba(173,181,189,.2)" } }}>
                                            <TableCell align="right">1.</TableCell>
                                            <TableCell sx={{ maxWidth: "10rem" }} className="font-red text-ellipsis">
                                                <img src={require("../../images/country_icon/kr.png").default} /> United States
                                            </TableCell>
                                            <TableCell align="right">{numberWithCommas(0.234093)}</TableCell>
                                        </TableRow>
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={6}>
                    <Box component="span" className={showGrid ? "btn-bottom-icon" : "btn-top-icon"} onClick={() => setShowGrid(!showGrid)}></Box>
                    Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(0)}</Typography>
                </Grid>
                <Grid item md={6}>
                    <RadioGroup row sx={{ float: "right" }} name="viewType" onChange={handleKeyPress} value={viewType}>
                        <FormControlLabel value="client" control={<Radio />} label="Client" />
                        <FormControlLabel value="server" control={<Radio />} label="Server" />
                    </RadioGroup>
                </Grid>
                <Grid item md={12} className={showGrid ? "" : "none"} id="geoGridEl">
                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                        <AgGridModule
                            gridName="geoGridApi"
                            columnDefs={geoColumnDefs}
                            rowData={geoData[viewType]}
                            onCellClicked={onCellClickEvt}
                            onCellMouseDown={onCellMouseDown}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>
                </Grid>
            </Grid>

            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
            {showLoader && (<Loader />)}
        </>
    );
};

export default DomesticIndex;