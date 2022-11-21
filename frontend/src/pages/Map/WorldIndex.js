import React, { useEffect, useMemo, useRef, useState, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { useLocation } from 'react-router-dom';
import { styled, useTheme } from '@mui/material/styles';
import {
    Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent, Chip, Paper, Button, FormControlLabel, Checkbox,
    TableContainer, Table, TableHead, TableBody, TableRow, TableCell, RadioGroup, Radio,
    List, ListItemButton, ListItemAvatar, ListItemText, Avatar, IconButton, Divider
} from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMap, faLineChart } from "@fortawesome/free-solid-svg-icons";
import { CloseOutlined, LineChartOutlined, SearchOutlined } from '@ant-design/icons';

/* GeoJson */
import World from './MapGeojson/World/World.json';

import axiosConf from '../../axios';
import { AgGridModule } from '../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj } from '../../lib/common';
import Loader from '../../components/Loader';
import EchartsComponent from '../../lib/EchartsComponent';
import SearchMap from '../Common/Search/SearchMap';
import MainCard from '../../components/MainCard';

/* Echarts */
import * as echarts from 'echarts';
import EchartsReact from 'echarts-for-react/lib/core';

echarts.registerMap("World", World);

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const WorldIndex = () => {
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

    const theme = useTheme();
    const tooltipRef = useRef(null);

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);
    const [showLoader, setShowLoader] = useState(false);

    const [activeChipData, setActiveChipData] = useState("World");
    const [chipData, setChipData] = useState([{ key: "World", label: 'World' }]);

    const [viewType, setViewType] = useState("client");

    const [showMap, setShowMap] = useState(true);
    const [showGrid, setShowGrid] = useState(true);

    const [tooltipOption, setTooltipOption] = useState({ open: false, opts: {} });

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

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
                    show: false,
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
            type: 'effectScatter',
            coordinateSystem: 'geo',
            symbolSize: (params) => {
                return (params[2] / 100) * 7 + 3;
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

    useEffect(() => {
        document.getElementsByTagName("body")[0].addEventListener('mousedown', handleClickOutside);

        return () => {
            document.getElementsByTagName("body")[0].removeEventListener('mousedown', handleClickOutside);
        }
    }, [tooltipOption]);

    const handleClickOutside = (event) => {
        if (tooltipOption.open) {
            if (tooltipRef.current && !tooltipRef.current.contains(event.target)) {
                setTooltipOption({ open: false, opts: {} });
            }
        }
    }

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

        // 임시 테스트
        cloneMapOption.series[0].data = [];

        const staticCode = ["AS", "EU", "AF", "NA", "SA", "AT", "OC"];

        _.forEach(staticCode, (key, i) => {
            axiosConf.get('/api/setting/geoCountry/findAllCountryByCode/' + key).then(res => {
                _.forEach(res.data, (obj) => {
                    cloneMapOption.series[0].data.push({
                        name: obj.countryName,
                        value: [parseFloat(obj.geoX), parseFloat(obj.geoY), _.random(1, 100)]
                    });
                });

                if (i + 1 === staticCode.length) {
                    setMapOption(cloneMapOption);
                }
            });
        });
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
        } else {
            createChartTrend();
        }

        setActiveChipData(key);
    }

    const onMapSeriesEvents = {
        click: (params) => {
            if (params.seriesType === "effectScatter" && activeChipData === "World") {
                setTooltipOption({
                    open: true,
                    opts: {
                        top: params.event.offsetY + 250,
                        left: params.event.offsetX + 100,
                        name: params.data.name
                    }
                });
            }
        }
    }

    const handleTooltipToggle = () => {
        setTooltipOption({ open: false, opts: {} });
    }

    const addCountryData = (params) => {
        const filterLabel = _.filter(chipData, (obj) => {
            return obj.label === params.countryName;
        });

        if (filterLabel.length > 0) {
            alert("이미 추가된 지역입니다.");
            return;
        }

        try {
            /* const requireJson = require("./MapGeojson/Domestic/kr-" + staticGeoCode[paramsName] + ".json");
            echarts.registerMap(staticGeoCode[paramsName], requireJson);

            createMapGeoJson(staticGeoCode[paramsName]);

            setChipData([...chipData, { key: staticGeoCode[paramsName], label: paramsName }]);
            setActiveChipData(staticGeoCode[paramsName]); */

            setChipData([...chipData, { key: params.countryCode, label: params.countryName }]);
            setActiveChipData(params.countryCode);

            createChartTrend();
        } catch {
            alert("지역 정보가 없습니다.");
        }
    }

    const handleKeyPress = (e) => {
        setViewType(e.target.value);
    }

    const onCellClickEvt = (event) => {
        if (activeChipData === "World") {
            if (event.colDef.field === "countryName") {
                addCountryData(event.data);
            }
        }
    }

    const getHttpGridColumnDefs = () => {
        return [{
            headerName: 'Country',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                try {
                    const requireGeoJson = require("../../images/country_icon/" + _.lowerCase(params.data.countryCode) + ".png");
                    return <><i className="fa fa-plus font-blue"></i> <img src={requireGeoJson.default} /> {params.value}</>;
                } catch {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
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
            headerName: 'Country',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                try {
                    const requireGeoJson = require("../../images/country_icon/" + _.lowerCase(params.data.countryCode) + ".png");
                    return <><i className="fa fa-plus font-blue"></i> <img src={requireGeoJson.default} /> {params.value}</>;
                } catch {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
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
            headerName: 'Country',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                try {
                    const requireGeoJson = require("../../images/country_icon/" + _.lowerCase(params.data.countryCode) + ".png");
                    return <><i className="fa fa-plus font-blue"></i> <img src={requireGeoJson.default} /> {params.value}</>;
                } catch {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
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
            headerName: 'Country',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                try {
                    const requireGeoJson = require("../../images/country_icon/" + _.lowerCase(params.data.countryCode) + ".png");
                    return <><i className="fa fa-plus font-blue"></i> <img src={requireGeoJson.default} /> {params.value}</>;
                } catch {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
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
            headerName: 'Country',
            field: 'countryName',
            suppressToolPanel: true,
            suppressSizeToFit: true,
            cellClass: ['cursorp'],
            cellRendererFramework: (params) => {
                try {
                    const requireGeoJson = require("../../images/country_icon/" + _.lowerCase(params.data.countryCode) + ".png");
                    return <><i className="fa fa-plus font-blue"></i> <img src={requireGeoJson.default} /> {params.value}</>;
                } catch {
                    return <><i className="fa fa-plus font-blue"></i> {params.value}</>;
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

            <Paper sx={{ position: "absolute", boxShadow: theme.customShadows.z1 + " !important", width: 200, top: tooltipOption.opts.top, left: tooltipOption.opts.left }}
                ref={tooltipRef} hidden={!tooltipOption.open}>
                <MainCard
                    title={tooltipOption.opts.name}
                    elevation={0}
                    border={false}
                    content={false}
                    secondary={
                        <IconButton size="small" onClick={handleTooltipToggle}>
                            <CloseOutlined />
                        </IconButton>
                    }
                >
                    <List component="nav" sx={{ p: 0, '& .MuiListItemButton-root': { py: 0.5 } }}>
                        <ListItemButton>
                            <ListItemAvatar>
                                <Avatar sx={{ color: 'primary.main', bgcolor: 'primary.lighter' }}>
                                    <SearchOutlined />
                                </Avatar>
                            </ListItemAvatar>
                            <ListItemText
                                primary={
                                    <Typography variant="h6" className="btn-page">Detail View</Typography>
                                }
                            />
                        </ListItemButton>
                        <Divider />
                        <ListItemButton>
                            <ListItemAvatar>
                                <Avatar sx={{ color: 'primary.main', bgcolor: 'primary.lighter' }}>
                                    <LineChartOutlined />
                                </Avatar>
                            </ListItemAvatar>
                            <ListItemText
                                primary={
                                    <Typography variant="h6" className="btn-page">Analysis</Typography>
                                }
                            />
                        </ListItemButton>
                    </List>
                </MainCard>
            </Paper>

            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
            {showLoader && (<Loader />)}
        </>
    );
};

export default WorldIndex;