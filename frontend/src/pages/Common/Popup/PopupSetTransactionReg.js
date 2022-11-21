import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import DatePicker from "react-datepicker";
import PropTypes from 'prop-types';
import moment from 'moment';
import { styled } from '@mui/material/styles';
import {
    Card, CardHeader, CardContent, Button, Box, TextField, FormControl, MenuItem, FormControlLabel, Checkbox, Tabs, Tab, Grid, Typography,
    TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper, Chip, IconButton, ButtonGroup, Slide, Snackbar, Alert
} from '@mui/material';

import { SearchOutlined, PlayCircleOutlined } from '@ant-design/icons';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { hiddenComponentPopup, gridApiObj, numberWithCommas, pageResources, uriResources, onCopyGridCell } from '../../../lib/common';
import Loader from '../../../components/Loader';
import DateInputField from "../Search/SearchDateInputField";
import ModalSearchFormResources from '../Modal/ModalSearchFormResources';
import ModalSearchFormThresholdPolicy from '../Modal/ModalSearchFormThresholdPolicy';
import SetThresholdTable from '../../Setting/SetThresholdTable';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

function TableSelectHeaderItem(props) {
    const { children, target, handleHeaderKeyPress, handleHeaderCheckbox, handleHeaderAllCheckbox } = props;

    return (
        <TableContainer component={Paper} className="border" sx={{ maxHeight: 200 }}>
            <Table stickyHeader size="small" className="table-bordered">
                <TableHead className="thead-light">
                    <TableRow>
                        <TableCell align="center" sx={{ width: "3rem" }}>
                            <Checkbox onChange={(e) => handleHeaderAllCheckbox(e, target)} />
                        </TableCell>
                        <TableCell align="center" sx={{ width: "15rem" }}>이름</TableCell>
                        <TableCell align="center" sx={{ width: "9rem" }}>타입</TableCell>
                        <TableCell align="center">HTTP User Custom Value</TableCell>
                        {target === "request" && <TableCell align="center" sx={{ width: "5rem" }}>저장 여부</TableCell>}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {
                        _.map(children, (obj, i) => (
                            <TableRow sx={{ '&:last-child td, &:last-child th': { border: 0 } }} key={target + "-" + i}>
                                <TableCell align="center">
                                    <Checkbox name="isHeaderChk" checked={obj.isHeaderChk} onChange={(e) => handleHeaderCheckbox(e, i, target)} />
                                </TableCell>
                                <TableCell>
                                    <TextField
                                        fullWidth
                                        name="headerName"
                                        placeholder="이름"
                                        size="small"
                                        value={obj.headerName}
                                        onChange={(e) => handleHeaderKeyPress(e, i, target)}
                                    />
                                </TableCell>
                                <TableCell>
                                    <TextField
                                        fullWidth
                                        select
                                        name="headerType"
                                        size="small"
                                        value={obj.headerType}
                                        onChange={(e) => handleHeaderKeyPress(e, i, target)}
                                    >
                                        <MenuItem value="0">Value</MenuItem>
                                        <MenuItem value="1">Value Pattern</MenuItem>
                                        <MenuItem value="2">Key/Value</MenuItem>
                                        <MenuItem value="3">Key/Value Pattern</MenuItem>
                                    </TextField>
                                </TableCell>
                                {
                                    obj.headerType === "0" || obj.headerType === "1" ?
                                        <TableCell align="center">
                                            <TextField
                                                fullWidth
                                                name="httpHeaderValue"
                                                placeholder="HTTP User Custom Value"
                                                size="small"
                                                value={obj.httpHeaderValue}
                                                onChange={(e) => handleHeaderKeyPress(e, i, target)}
                                            />
                                        </TableCell> :
                                        <TableCell align="center">
                                            <Grid container spacing={1}>
                                                <Grid item sm={5}>
                                                    <TextField
                                                        fullWidth
                                                        name="httpHeaderKey"
                                                        placeholder="Key"
                                                        size="small"
                                                        value={obj.httpHeaderKey}
                                                        onChange={(e) => handleHeaderKeyPress(e, i, target)}
                                                    />
                                                </Grid>
                                                <Grid item sm={1}>
                                                    <TextField
                                                        fullWidth
                                                        name="httpHeaderSep"
                                                        placeholder="="
                                                        size="small"
                                                        value={obj.httpHeaderSep}
                                                        onChange={(e) => handleHeaderKeyPress(e, i, target)}
                                                    />
                                                </Grid>
                                                <Grid item sm={6}>
                                                    <TextField
                                                        fullWidth
                                                        name="httpHeaderValue"
                                                        placeholder="Value"
                                                        size="small"
                                                        value={obj.httpHeaderValue}
                                                        onChange={(e) => handleHeaderKeyPress(e, i, target)}
                                                    />
                                                </Grid>
                                            </Grid>
                                        </TableCell>
                                }
                                {
                                    target === "request" && <TableCell align="center">
                                        <Checkbox name="isHeaderWrite" checked={obj.isHeaderWrite} onChange={(e) => handleHeaderCheckbox(e, i, target)} />
                                    </TableCell>
                                }
                            </TableRow>
                        ))
                    }
                </TableBody>
            </Table>
        </TableContainer>
    )
}

TableSelectHeaderItem.propTypes = {
    children: PropTypes.array,
    target: PropTypes.string,
    handleHeaderKeyPress: PropTypes.func,
    handleHeaderCheckbox: PropTypes.func,
    handleHeaderAllCheckbox: PropTypes.func
};

const PopupSetTransactionReg = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [state, setState] = useState({
        urlAlias: "",
        startDate: moment().subtract(59, "seconds").toDate(),
        startHours: moment().subtract(59, 'seconds').format("HH"),
        startMinutes: moment().subtract(59, 'seconds').format("mm"),
        startSeconds: moment().subtract(59, 'seconds').format("ss"),
        endDate: moment().toDate(),
        endHours: moment().format("HH"),
        endMinutes: moment().format("mm"),
        endSeconds: moment().format("ss"),
        isUseUrlSchText: false,
        urlSchTextType: "host",
        urlSchText: "",
        autoDate: "1minutes",
        urlSchDeviceType: "url",
        staticUrlField: "",
        urlField: "",
        urlPatternEnabled: false,
        uriMappingEnabled: false,
        argUseEnabled: false,
        staticArgField: "",
        argField: "",
        argWriteHttp: "",
        isUseMethod: false,
        httpMethod: "GET",
        httpBodyType: "0",
        argPatternEnabled: false,
        reqHeaderEnabled: false,
        reqHeaderAlias: "",
        resHeaderEnabled: false,
        resHeaderAlias: "",
        resBodyEnabled: false,
        resBodyAlias: "",
        cookieEnabled: false,
        cookieAlias: "",
        transCriticalType: "2",
        transactionCriticalPolicyId: "",
        uriCriticalType: "2",
        uriCriticalPolicyId: ""
    });

    const [urlFieldExp, setUrlFieldExp] = useState([]);
    const [urlFieldExpErr, setUrlFieldExpErr] = useState(false);

    const [argFieldExp, setArgFieldExp] = useState([]);
    const [argFieldExpErr, setArgFieldExpErr] = useState(false);

    const [menuKey, setMenuKey] = useState(0);

    const [selectTransResource, setSelectTransResource] = useState([]);
    const [criticalTransResource, setCriticalTransResource] = useState([]);
    const [selectUriResource, setSelectUriResource] = useState([]);
    const [criticalUriResource, setCriticalUriResource] = useState([]);

    const [resourceOpen, setResourceOpen] = useState(false);
    const [thresholdPolicyOpen, setThresholdPolicyOpen] = useState(false);

    const [refererUrlData, setRefererUrlData] = useState([]);
    const [refererUrlColumnDefs, setRefererUrlColumnDefs] = useState([]);

    const [reqHeaderData, setReqHeaderData] = useState([]);
    const [selectReqHeaderData, setSelectReqHeaderData] = useState([]);
    const [resHeaderData, setResHeaderData] = useState([]);
    const [selectResHeaderData, setSelectResHeaderData] = useState([]);

    const [cookieData, setCookieData] = useState([]);

    const [resBodyData, setResBodyData] = useState([]);

    const [uriMappingRefererData, setUriMappingRefererData] = useState([]);
    const [selectUriMappingData, setSelectUriMappingData] = useState([]);

    const [changeTransKey, setChangeTransKey] = useState(0);
    const [changeUriKey, setChangeUriKey] = useState(0);

    const uriMappingRefererColumnDefs = [{
        headerName: 'Host',
        field: 'httpHost',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'URI',
        field: 'httpUri',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'Argument',
        field: 'httpUriSplit',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'DST IP',
        field: 'dstIp',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'DST Port',
        field: 'dstPort',
        cellClass: ['cursorp', 'text-right'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'SRC IP',
        field: 'srcIp',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'SRC Port',
        field: 'srcPort',
        cellClass: ['cursorp', 'text-right'],
        filter: "agTextColumnFilter"
    }];

    const httpHeaderColumnDefs = [{
        headerName: 'Name',
        field: 'name',
        cellClass: ['cursorp'],
        filter: 'agTextColumnFilter'
    },
    {
        headerName: 'Description',
        field: 'description',
        cellClass: ['cursorp'],
        filter: 'agTextColumnFilter'
    }];

    useEffect(() => {
        if (params.id !== "0") {
            getFindoneInfo();
        }

        getRefererUrlData();
        getRefererHeaderData();

        hiddenComponentPopup();
        window.addEventListener("resize", () => {
            hiddenComponentPopup();
            handleResize();
        });

        return () => {
            window.removeEventListener('resize', () => {
                hiddenComponentPopup();
                handleResize();
            });
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            handleResize();
        }, 200);

        if (menuKey === 7) {
            // URI 연계 참조 그리드
            getRefererUriMappingData();
        }
    }, [menuKey]);

    const getFindoneInfo = () => {
        axiosConf.get("/api/setting/transaction/find/" + params.id).then(res => {
            const transaction = res.data.transaction;

            setState({
                ...state,
                urlAlias: transaction.urlAlias || "",
                staticUrlField: transaction.urlName || "",
                urlField: transaction.urlName || "",
                urlPatternEnabled: transaction.isPattern,
                uriMappingEnabled: transaction.isUriChk,
                argUseEnabled: transaction.isArgument,
                staticArgField: transaction.argument || "",
                argField: transaction.argument || "",
                argWriteHttp: transaction.writeHttpArgument || "",
                isUseMethod: transaction.httpMethod !== "",
                httpMethod: transaction.httpMethod === "" ? "GET" : transaction.httpMethod,
                httpBodyType: transaction.httpBodyType.toString(),
                argPatternEnabled: transaction.isArgumentPattern,
                reqHeaderEnabled: transaction.isRequestHeader,
                reqHeaderAlias: transaction.requestHeaderAlias,
                resHeaderEnabled: transaction.isResponseHeader,
                resHeaderAlias: transaction.responseHeaderAlias,
                resBodyEnabled: transaction.isResponseBody,
                resBodyAlias: transaction.responseBodyAlias,
                cookieEnabled: transaction.isCookie,
                cookieAlias: transaction.cookieAlias,
                transCriticalType: transaction.transactionThresholdType.toString(),
                transactionCriticalPolicyId: transaction.transactionThresholdId || "",
                uriCriticalType: transaction.uriThresholdType.toString(),
                uriCriticalPolicyId: transaction.uriThresholdId || ""
            });

            // Header
            findoneSetHeaderTab(res.data.mappingHeader);

            // Cookie
            findoneSetCookieTab(res.data.mappingCookie);

            // Response Body
            findoneResBodyTab(res.data.mappingResponseBody);

            // URI 연계
            findoneUriMappingTab(res.data.mappingUri);

            // Transaction 레벨
            if (transaction.transactionThresholdType === 0) {
                defaultThresholdList("transaction");
            } else if (transaction.transactionThresholdType === 1) {
                const reqUrl = "/api/setting/threshold/find/policy/transaction/" + transaction.transactionThresholdId;
                getThresholdPolicyData(reqUrl, "transaction");
            } else {
                const thresholdData = thresholdSetFindoneData(pageResources, res.data, "Transaction");
                setSelectTransResource(thresholdData.usedResource);
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource(thresholdData.levelData);
            }

            // URI 레벨
            if (transaction.uriThresholdType === 0) {
                defaultThresholdList("uri");
            } else if (transaction.uriThresholdType === 1) {
                const reqUrl = "/api/setting/threshold/find/policy/uri/" + transaction.uriThresholdId;
                getThresholdPolicyData(reqUrl, "uri");
            } else {
                const thresholdData = thresholdSetFindoneData(uriResources, res.data, "Uri");
                setSelectUriResource(thresholdData.usedResource);
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource(thresholdData.levelData);
            }
        });
    }

    const thresholdSetFindoneData = (targetResource, resData, flagKey) => {
        let usedResourceArr = [];
        let levelDataArr = [];
        const usedResource = flagKey ? resData["usedResource" + flagKey] : resData.usedResource;
        const threshold = flagKey ? resData["threshold" + flagKey] : resData.threshold;

        _.forEach(targetResource, (obj) => {
            _.forEach(obj.children, (childObj) => {
                if (usedResource.indexOf(childObj.key) !== -1) {
                    usedResourceArr.push({
                        name: childObj.name,
                        key: childObj.key,
                    });

                    levelDataArr.push({
                        name: childObj.name,
                        key: childObj.key,
                        defaultCheck: false,
                        level1Field: threshold[childObj.key + "Level1"] || "",
                        level2Field: threshold[childObj.key + "Level2"] || "",
                        level3Field: threshold[childObj.key + "Level3"] || "",
                        level4Field: threshold[childObj.key + "Level4"] || "",
                        level5Field: threshold[childObj.key + "Level5"] || ""
                    });
                }
            });
        });

        return {
            usedResource: usedResourceArr,
            levelData: levelDataArr
        };
    }

    const defaultThresholdList = (target) => {
        setShowLoader(true);

        axiosConf.get("/api/setting/threshold/find/" + target).then(res => {
            let thresholdData = { usedResource: [], levelData: [] };

            if (res.data.threshold) {
                const targetResource = target === "transaction" ? pageResources : uriResources;
                thresholdData = thresholdSetFindoneData(targetResource, res.data);
            }

            if (target === "transaction") {
                setSelectTransResource(thresholdData.usedResource);
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource(thresholdData.levelData);
            } else {
                setSelectUriResource(thresholdData.usedResource);
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource(thresholdData.levelData);
            }

            setShowLoader(false);
        });
    }

    const findoneUriMappingTab = (mappingUri) => {
        const formatUriMapping = _.map(mappingUri, (obj) => {
            return {
                isUriMappingChk: false,
                uriMappingName: obj.uri
            };
        });

        setSelectUriMappingData(formatUriMapping);
    }

    const findoneResBodyTab = (mappingResponseBody) => {
        const formatResBody = _.map(mappingResponseBody, (obj) => {
            return {
                isResBodyChk: false,
                isResBodyPattern: obj.isValuePattern,
                resBodyKey: obj.httpResponseBodyKey || "",
                resBodyValue: obj.httpResponseBodyValue || "",
                isResBodyWrite: obj.writeHttpCookie
            };
        });

        setResBodyData(formatResBody);
    }

    const findoneSetCookieTab = (mappingCookie) => {
        const formatCookie = _.map(mappingCookie, (obj) => {
            return {
                isCookieChk: false,
                isCookiePattern: obj.isValuePattern,
                cookieKey: obj.httpCookieKey || "",
                cookieValue: obj.httpCookieValue || "",
                isCookieWrite: obj.writeHttpCookie
            };
        });

        setCookieData(formatCookie);
    }

    const findoneSetHeaderTab = (mappingHeader) => {
        const groupByHeader = _.groupBy(mappingHeader, "headerType");

        const formatReqHeader = _.map(groupByHeader.request, (obj) => {
            return {
                isHeaderChk: false,
                headerName: obj.httpHeaderName || "",
                headerType: obj.type.toString(),
                httpHeaderKey: obj.httpHeaderKey || "",
                httpHeaderSep: obj.httpHeaderSeparate || "",
                httpHeaderValue: obj.httpHeaderValue || "",
                isHeaderWrite: obj.writeHttpHeader
            };
        });

        const formatResHeader = _.map(groupByHeader.response, (obj) => {
            return {
                isHeaderChk: false,
                headerName: obj.httpHeaderName || "",
                headerType: obj.type.toString(),
                httpHeaderKey: obj.httpHeaderKey || "",
                httpHeaderSep: obj.httpHeaderSeparate || "",
                httpHeaderValue: obj.httpHeaderValue || "",
                isHeaderWrite: obj.writeHttpHeader
            };
        });

        setSelectReqHeaderData(formatReqHeader);
        setSelectResHeaderData(formatResHeader);
    }

    const getRefererUriMappingData = () => {
        setShowLoader(true);

        const formatStartDate = moment(state.startDate).format("YYYYMMDD") + state.startHours + state.startMinutes + state.startSeconds;
        const formatEndDate = moment(state.endDate).format("YYYYMMDD") + state.endHours + state.endMinutes + state.endSeconds;
        const requestData = {
            begin: formatStartDate,
            end: formatEndDate,
            url: state.staticUrlField
            /* begin: "20220907090000",
            end: "20220907090059",
            url: '/msdownload/update/v3/static/trustedr/en/authrootstl.cab' */
        };

        axiosConf.post("/api/setting/transaction/find/chk/uri", requestData).then(res => {
            setUriMappingRefererData(res.data);
            setShowLoader(false);
        });
    }

    const onCellUriMappingClick = (params) => {
        if (params.data) {
            addUriMappingEvt(params.data.httpUri + params.data.httpUriSplit);
        }
    }

    const getRefererHeaderData = () => {
        axiosConf.get("/api/setting/code/http/findHttpName/HTTP%20Header").then(res => {
            const groupByData = _.groupBy(res.data, "subType");

            setReqHeaderData(groupByData["Request Header"]);
            setResHeaderData(groupByData["Response Header"]);
        });
    }

    const onCellReqHeaderClick = (params) => {
        if (params.data) {
            addHeaderEvt("request", params.data.name);
        }
    }

    const onCellResHeaderClick = (params) => {
        if (params.data) {
            addHeaderEvt("response", params.data.name);
        }
    }

    const getRefererUrlData = (category) => {
        setShowLoader(true);

        const urlSchDeviceType = category ? category : state.urlSchDeviceType;
        const formatStartDate = moment(state.startDate).format("YYYYMMDD") + state.startHours + state.startMinutes + state.startSeconds;
        const formatEndDate = moment(state.endDate).format("YYYYMMDD") + state.endHours + state.endMinutes + state.endSeconds;

        let requestData = {
            type: "url",
            category: urlSchDeviceType,
            begin: formatStartDate,
            end: formatEndDate,
            isSearchTxt: state.isUseUrlSchText
        };

        if (state.isUseUrlSchText) {
            requestData.searchType = state.urlSchTextType;
            requestData.searchTxt = state.urlSchText;
        }

        axiosConf.post("/api/setting/transaction/search", requestData).then(res => {
            const formatData = formatRefererUrlData(urlSchDeviceType, res.data);

            setRefererUrlData(formatData.data);
            setRefererUrlColumnDefs(formatData.columnDefs);
            setShowLoader(false);
        });
    }

    const formatRefererUrlData = (category, resData) => {
        if (category === "url") {
            return {
                data: resData,
                columnDefs: [{
                    headerName: 'Host',
                    field: 'httpHost',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'URL',
                    field: 'httpUri',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'Argument',
                    field: 'httpUriSplit',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                }]
            };
        } else if (category === "host") {
            return {
                data: resData,
                columnDefs: [{
                    headerName: 'Group',
                    field: 'host',
                    cellRenderer: 'agGroupCellRenderer',
                    filter: "agTextColumnFilter"
                },
                {
                    headerName: 'Host',
                    field: 'httpHost',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'URL',
                    field: 'httpUri',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'Argument',
                    field: 'httpUriSplit',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                }]
            };
        } else if (category === "dst") {
            _.forEach(resData, (obj) => {
                obj.group = obj.dstIp + ":" + obj.dstPort;
            });

            return {
                data: resData,
                columnDefs: [{
                    headerName: 'Group',
                    field: 'group',
                    cellRenderer: 'agGroupCellRenderer',
                    filter: "agTextColumnFilter"
                },
                {
                    headerName: 'Host',
                    field: 'httpHost',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'URL',
                    field: 'httpUri',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'Argument',
                    field: 'httpUriSplit',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                }]
            };
        } else {
            _.forEach(resData, (obj) => {
                obj.group = obj.srcIp + ":" + obj.srcPort;
            });

            return {
                data: resData,
                columnDefs: [{
                    headerName: 'Group',
                    field: 'group',
                    cellRenderer: 'agGroupCellRenderer',
                    filter: "agTextColumnFilter"
                },
                {
                    headerName: 'Host',
                    field: 'httpHost',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'URL',
                    field: 'httpUri',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                },
                {
                    headerName: 'Argument',
                    field: 'httpUriSplit',
                    cellClass: ['cursorp'],
                    filter: 'agTextColumnFilter'
                }]
            };
        }
    }

    const onCellRefererUrlClick = (params) => {
        if (params.data) {
            setState({ ...state, staticUrlField: params.data.httpUri, staticArgField: params.data.httpUriSplit });
            setUrlFieldExp([]);
            setUrlFieldExpErr(false);
            setArgFieldExp([]);
            setArgFieldExpErr(false);
        }
    }

    const defaultThresholdValue = useCallback((checkData) => {
        setShowLoader(true);

        const reqUrl = menuKey === 5 ? "transaction" : "uri";

        axiosConf.get("/api/setting/threshold/find/" + reqUrl).then(res => {
            let cloneCriticalResource = _.cloneDeep(menuKey === 5 ? criticalTransResource : criticalUriResource);

            if (res.data.threshold) {
                const checkDataKey = _.map(checkData, "key");

                _.forEach(cloneCriticalResource, (obj) => {
                    if (checkDataKey.indexOf(obj.key) !== -1) {
                        obj.level1Field = res.data.threshold[obj.key + "Level1"] || "";
                        obj.level2Field = res.data.threshold[obj.key + "Level2"] || "";
                        obj.level3Field = res.data.threshold[obj.key + "Level3"] || "";
                        obj.level4Field = res.data.threshold[obj.key + "Level4"] || "";
                        obj.level5Field = res.data.threshold[obj.key + "Level5"] || "";
                    }
                });
            } else {
                _.forEach(cloneCriticalResource, (obj) => {
                    obj.level1Field = "";
                    obj.level2Field = "";
                    obj.level3Field = "";
                    obj.level4Field = "";
                    obj.level5Field = "";
                });
            }

            if (menuKey === 5) {
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource(cloneCriticalResource);
            } else {
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource(cloneCriticalResource);
            }

            setShowLoader(false);
        });
    }, [menuKey, criticalTransResource, criticalUriResource, changeTransKey, changeUriKey]);

    const handleResourceOpen = () => setResourceOpen(true);

    const handleResourceClose = useCallback(() => {
        setResourceOpen(false);
    }, []);

    const handleThresholdPolicyOpen = () => setThresholdPolicyOpen(true);

    const handleThresholdPolicyClose = useCallback(() => {
        setThresholdPolicyOpen(false);
    }, []);

    const bridgeModalThresholdPolicy = useCallback((data) => {
        const reqUrl = "/api/setting/threshold/find/policy/" + data.target + "/" + data.id;

        getThresholdPolicyData(reqUrl, data.target);
        setState({ ...state, [data.target + "CriticalPolicyId"]: data.id });
    }, [state]);

    const getThresholdPolicyData = (reqUrl, target) => {
        setShowLoader(true);

        axiosConf.get(reqUrl).then(res => {
            let thresholdData = { usedResource: [], levelData: [] };

            if (res.data.threshold) {
                const targetResource = target === "transaction" ? pageResources : uriResources;
                thresholdData = thresholdSetFindoneData(targetResource, res.data);
            }

            if (target === "transaction") {
                setSelectTransResource(thresholdData.usedResource);
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource(thresholdData.levelData);
            } else {
                setSelectUriResource(thresholdData.usedResource);
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource(thresholdData.levelData);
            }

            setShowLoader(false);
        });
    }

    const bridgeModalResource = useCallback((data) => {
        setShowLoader(true);

        setTimeout(() => {
            const targetResource = menuKey === 5 ? criticalTransResource : criticalUriResource;
            let levelDataArr = [];

            _.forEach(data, (obj) => {
                const filterExistData = _.filter(targetResource, (existObj) => {
                    return existObj.key === obj.key;
                });

                if (filterExistData.length > 0) {
                    levelDataArr.push(filterExistData[0]);
                } else {
                    levelDataArr.push({
                        name: obj.name,
                        key: obj.key,
                        defaultCheck: false,
                        level1Field: "",
                        level2Field: "",
                        level3Field: "",
                        level4Field: "",
                        level5Field: ""
                    });
                }
            });

            if (menuKey === 5) {
                setSelectTransResource(data);
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource(levelDataArr);
            } else {
                setSelectUriResource(data);
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource(levelDataArr);
            }

            setShowLoader(false);
        }, 0);
    }, [menuKey, criticalTransResource, criticalUriResource, changeTransKey, changeUriKey]);

    const handleResourceDelete = (targetId) => {
        setShowLoader(true);

        setTimeout(() => {
            if (menuKey === 5) {
                const filterSelectData = _.filter(selectTransResource, (obj) => {
                    return obj.key !== targetId;
                });

                const filterCriticalData = _.filter(criticalTransResource, (obj) => {
                    return obj.key !== targetId;
                });

                setSelectTransResource(filterSelectData);
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource(filterCriticalData);
            } else {
                const filterSelectData = _.filter(selectUriResource, (obj) => {
                    return obj.key !== targetId;
                });

                const filterCriticalData = _.filter(criticalUriResource, (obj) => {
                    return obj.key !== targetId;
                });

                setSelectUriResource(filterSelectData);
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource(filterCriticalData);
            }

            setShowLoader(false);
        }, 0);
    }

    const handleThresholdValue = useCallback((changeData) => {
        setShowLoader(true);

        setTimeout(() => {
            if (menuKey === 5) {
                setCriticalTransResource(changeData);
            } else {
                setCriticalUriResource(changeData);
            }

            setShowLoader(false);
        }, 0);
    }, [menuKey]);

    const clearThresholdValue = useCallback((changeData) => {
        setShowLoader(true);

        setTimeout(() => {
            if (menuKey === 5) {
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource(changeData);
            } else {
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource(changeData);
            }

            setShowLoader(false);
        }, 0);
    }, [changeTransKey, changeUriKey]);

    const handleChange = (event, newValue) => {
        setMenuKey(newValue);
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.type === "checkbox" ? e.target.checked : e.target.value });

        if (e.target.type === "checkbox") {
            setTimeout(() => {
                handleResize();
            }, 200);
        } else if (e.target.name === "urlSchDeviceType") {
            // url/argument > 참고 url category
            getRefererUrlData(e.target.value);
        } else if (e.target.name === "transCriticalType") {
            // Transaction 레벨 > 기본/정책/사용자정의 타입
            if (e.target.value === "0") {
                const reqUrl = "/api/setting/threshold/find/transaction";
                getThresholdPolicyData(reqUrl, "transaction");
            } else {
                setSelectTransResource([]);
                setChangeTransKey(changeTransKey + 1);
                setCriticalTransResource([]);
            }
        } else if (e.target.name === "uriCriticalType") {
            // URI 레벨 > 기본/정책/사용자정의 타입
            if (e.target.value === "0") {
                const reqUrl = "/api/setting/threshold/find/uri";
                getThresholdPolicyData(reqUrl, "uri");
            } else {
                setSelectUriResource([]);
                setChangeUriKey(changeUriKey + 1);
                setCriticalUriResource([]);
            }
        }
    }

    const autoDatePicker = (time, unit) => {
        const autoDate = time + unit;
        const startDate = moment().subtract(time, unit).toDate();
        const formatStartDate = moment(startDate).add(1, "seconds").toDate();
        const endDate = moment().toDate();

        setState({
            ...state,
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

    const handleHeaderKeyPress = (e, idx, target) => {
        if (target === "request") {
            const formatData = _.map(selectReqHeaderData, (obj, dataIdx) => {
                return dataIdx === idx ? { ...obj, [e.target.name]: e.target.value } : obj;
            });

            setSelectReqHeaderData(formatData);
        } else {
            const formatData = _.map(selectResHeaderData, (obj, dataIdx) => {
                return dataIdx === idx ? { ...obj, [e.target.name]: e.target.value } : obj;
            });

            setSelectResHeaderData(formatData);
        }
    }

    const handleHeaderAllCheckbox = (e, target) => {
        if (target === "request") {
            const formatData = _.map(selectReqHeaderData, (obj) => {
                return { ...obj, isHeaderChk: e.target.checked };
            });

            setSelectReqHeaderData(formatData);
        } else {
            const formatData = _.map(selectResHeaderData, (obj) => {
                return { ...obj, isHeaderChk: e.target.checked };
            });

            setSelectResHeaderData(formatData);
        }
    }

    const handleHeaderCheckbox = (e, idx, target) => {
        if (target === "request") {
            const formatData = _.map(selectReqHeaderData, (obj, dataIdx) => {
                return dataIdx === idx ? { ...obj, [e.target.name]: e.target.checked } : obj;
            });

            setSelectReqHeaderData(formatData);
        } else {
            const formatData = _.map(selectResHeaderData, (obj, dataIdx) => {
                return dataIdx === idx ? { ...obj, [e.target.name]: e.target.checked } : obj;
            });

            setSelectResHeaderData(formatData);
        }
    }

    const addHeaderEvt = (target, selectName) => {
        const addData = {
            isHeaderChk: false,
            headerName: selectName ? selectName : "",
            headerType: "0",
            httpHeaderKey: "",
            httpHeaderSep: "",
            httpHeaderValue: "",
            isHeaderWrite: false
        };

        if (target === "request") {
            if (selectReqHeaderData.length === 4) {
                alert("최대 4개까지 추가할 수 있습니다.");
                return;
            }

            setSelectReqHeaderData([...selectReqHeaderData, addData]);
        } else {
            setSelectResHeaderData([...selectResHeaderData, addData]);
        }
    }

    const removeHeaderEvt = (target) => {
        if (target === "request") {
            const chkData = _.filter(selectReqHeaderData, { isHeaderChk: true });
            const filterData = _.filter(selectReqHeaderData, { isHeaderChk: false });

            if (chkData.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            setSelectReqHeaderData(filterData);
        } else {
            const chkData = _.filter(selectResHeaderData, { isHeaderChk: true });
            const filterData = _.filter(selectResHeaderData, { isHeaderChk: false });

            if (chkData.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            setSelectResHeaderData(filterData);
        }
    }

    const handleCookieKeyPress = (e, idx) => {
        const formatData = _.map(cookieData, (obj, dataIdx) => {
            return dataIdx === idx ? { ...obj, [e.target.name]: e.target.value } : obj;
        });

        setCookieData(formatData);
    }

    const handleCookieAllCheckbox = (e) => {
        const formatData = _.map(cookieData, (obj) => {
            return { ...obj, isCookieChk: e.target.checked };
        });

        setCookieData(formatData);
    }

    const handleCookieCheckbox = (e, idx) => {
        const formatData = _.map(cookieData, (obj, dataIdx) => {
            return dataIdx === idx ? { ...obj, [e.target.name]: e.target.checked } : obj;
        });

        setCookieData(formatData);
    }

    const addCookieEvt = () => {
        if (cookieData.length === 4) {
            alert("최대 4개까지 추가할 수 있습니다.");
            return;
        }

        const addData = {
            isCookieChk: false,
            isCookiePattern: true,
            cookieKey: "",
            cookieValue: "",
            isCookieWrite: false
        };

        setCookieData([...cookieData, addData]);
    }

    const removeCookieEvt = () => {
        const chkData = _.filter(cookieData, { isCookieChk: true });
        const filterData = _.filter(cookieData, { isCookieChk: false });

        if (chkData.length === 0) {
            alert("삭제할 항목을 선택해주세요.");
            return;
        }

        setCookieData(filterData);
    }

    const handleResBodyKeyPress = (e, idx) => {
        const formatData = _.map(resBodyData, (obj, dataIdx) => {
            return dataIdx === idx ? { ...obj, [e.target.name]: e.target.value } : obj;
        });

        setResBodyData(formatData);
    }

    const handleResBodyAllCheckbox = (e) => {
        const formatData = _.map(resBodyData, (obj) => {
            return { ...obj, isResBodyChk: e.target.checked };
        });

        setResBodyData(formatData);
    }

    const handleResBodyCheckbox = (e, idx) => {
        const formatData = _.map(resBodyData, (obj, dataIdx) => {
            return dataIdx === idx ? { ...obj, [e.target.name]: e.target.checked } : obj;
        });

        setResBodyData(formatData);
    }

    const addResBodyEvt = () => {
        if (resBodyData.length === 6) {
            alert("최대 6개까지 추가할 수 있습니다.");
            return;
        }

        const addData = {
            isResBodyChk: false,
            isResBodyPattern: true,
            resBodyKey: "",
            resBodyValue: "",
            isResBodyWrite: false
        };

        setResBodyData([...resBodyData, addData]);
    }

    const removeResBodyEvt = () => {
        const chkData = _.filter(resBodyData, { isResBodyChk: true });
        const filterData = _.filter(resBodyData, { isResBodyChk: false });

        if (chkData.length === 0) {
            alert("삭제할 항목을 선택해주세요.");
            return;
        }

        setResBodyData(filterData);
    }

    const handleUriMappingKeyPress = (e, idx) => {
        const formatData = _.map(selectUriMappingData, (obj, dataIdx) => {
            return dataIdx === idx ? { ...obj, [e.target.name]: e.target.value } : obj;
        });

        setSelectUriMappingData(formatData);
    }

    const handleUriMappingAllCheckbox = (e) => {
        const formatData = _.map(selectUriMappingData, (obj) => {
            return { ...obj, isUriMappingChk: e.target.checked };
        });

        setSelectUriMappingData(formatData);
    }

    const handleUriMappingCheckbox = (e, idx) => {
        const formatData = _.map(selectUriMappingData, (obj, dataIdx) => {
            return dataIdx === idx ? { ...obj, [e.target.name]: e.target.checked } : obj;
        });

        setSelectUriMappingData(formatData);
    }

    const addUriMappingEvt = (selectName) => {
        const addData = {
            isUriMappingChk: false,
            uriMappingName: selectName ? selectName : ""
        };

        setSelectUriMappingData([...selectUriMappingData, addData]);
    }

    const removeUriMappingEvt = () => {
        const chkData = _.filter(selectUriMappingData, { isUriMappingChk: true });
        const filterData = _.filter(selectUriMappingData, { isUriMappingChk: false });

        if (chkData.length === 0) {
            alert("삭제할 항목을 선택해주세요.");
            return;
        }

        setSelectUriMappingData(filterData);
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const formatGroupChild = (data) => {
        if (data.result) {
            return {
                group: true,
                children: data.result
            };
        } else {
            return null;
        }
    }

    const thresholdResourceField = (targetKey, selectResource) => {
        return (
            <Grid container spacing={1} mb={1}>
                <Grid item sm={1.5}>
                    <TextField
                        fullWidth
                        select
                        name={targetKey + "CriticalType"}
                        size="small"
                        value={state[targetKey + "CriticalType"]}
                        onChange={handleKeyPress}
                    >
                        <MenuItem value="0">기본</MenuItem>
                        <MenuItem value="1">정책</MenuItem>
                        <MenuItem value="2">사용자 정의</MenuItem>
                    </TextField>
                </Grid>
                {
                    state[targetKey + "CriticalType"] === "1" && <Grid item sm={1}>
                        <Button variant="outlined" color="primary" size="small" startIcon={<SearchOutlined />} onClick={handleThresholdPolicyOpen}>Search</Button>
                    </Grid>
                }

                {
                    state[targetKey + "CriticalType"] === "2" && <Grid item sm={10.5} className="popupEl">
                        <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }}>
                            <IconButton edge="start" size="small" className="search-btn" onClick={handleResourceOpen}>
                                <SearchOutlined />
                            </IconButton>
                            <Box component="div" flex="1 1">
                                <Paper
                                    sx={{
                                        display: 'flex',
                                        flexWrap: 'wrap',
                                        listStyle: 'none',
                                        pl: .5,
                                        m: 0,
                                        '& .MuiListItem-root': { p: 0 }
                                    }}
                                    className="border"
                                    component="ul"
                                >
                                    {
                                        selectResource.length === 0 && <ListItem sx={{ mt: .3 }} className="font-gray">Choose Resources</ListItem>
                                    }

                                    {
                                        _.map(selectResource, (obj, i) => (
                                            <ListItem key={i}>
                                                <Chip size="small" label={obj.name} onDelete={() => handleResourceDelete(obj.key)} />
                                            </ListItem>
                                        ))
                                    }
                                </Paper>
                            </Box>
                        </Box>
                    </Grid>
                }
            </Grid>
        );
    }

    const formatHeaderData = (headerData) => {
        return _.map(headerData, (obj) => {
            let formatData = {
                type: parseInt(obj.headerType),
                writeHttpHeader: obj.isHeaderWrite,
                isValuePattern: obj.headerType === "1" || obj.headerType === "3",
                httpHeaderName: obj.headerName,
                httpHeaderValue: obj.httpHeaderValue
            };

            if (obj.headerType === "2" || obj.headerType === "3") {
                formatData.httpHeaderKey = obj.httpHeaderKey;
                formatData.httpHeaderSeparate = obj.httpHeaderSep;
            }

            return formatData;
        });
    }

    const formatThresholdData = (criticalType, criticalResource) => {
        let requestData = {
            type: parseInt(criticalType)
        };

        _.forEach(criticalResource, (obj) => {
            requestData[obj.key + "Use"] = true;

            if (obj.level1Field !== "") requestData[obj.key + "Level1"] = parseFloat(obj.level1Field);
            if (obj.level2Field !== "") requestData[obj.key + "Level2"] = parseFloat(obj.level2Field);
            if (obj.level3Field !== "") requestData[obj.key + "Level3"] = parseFloat(obj.level3Field);
            if (obj.level4Field !== "") requestData[obj.key + "Level4"] = parseFloat(obj.level4Field);
            if (obj.level5Field !== "") requestData[obj.key + "Level5"] = parseFloat(obj.level5Field);
        });

        return requestData;
    }

    const copyToClipboard = (copyKey) => {
        onCopyGridCell(state[copyKey]);

        setTransition(() => TransitionUp);
        setCopySuccess(true);

        setTimeout(() => {
            setCopySuccess(false);
        }, 1000);
    }

    const TransitionUp = (props) => {
        return <Slide {...props} direction="up" />;
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

    const getAllMatches = (regex, text) => {
        let res = [];
        let match = null;
        let matchObj = {};
        let originCompareResult = [];
        let num = 0;

        if (regex.global) {
            while ((match = regex.exec(text)) !== null) {
                if (num === text.length) {
                    res = [];
                    break;
                }

                res.push(match);
                num += 1;
            }
        } else {
            if (match = regex.exec(text)) {
                res.push(match);
            }
        }

        if (res.length > 0) {
            _.forEach(res, (obj) => {
                matchObj[obj.index] = obj[0];
            });

            _.forEach(text, (obj, i) => {
                originCompareResult.push({
                    char: obj,
                    match: matchObj[i] === obj
                });
            });
        }

        return {
            match: res,
            result: originCompareResult
        };
    }

    const patternChkUrl = () => {
        if (state.urlField === "") {
            alert("URL 패턴을 입력해주세요.");
            return;
        }

        try {
            const originField = state.staticUrlField;
            const expField = new RegExp(state.urlField, "g");
            const matchResult = getAllMatches(expField, originField);

            setUrlFieldExp(matchResult.result);
            setUrlFieldExpErr(matchResult.match.length === 0);
        } catch {
            setUrlFieldExp([]);
            setUrlFieldExpErr(true);
        }
    }

    const patternChkArg = () => {
        if (state.argField === "") {
            alert("Argument 패턴을 입력해주세요.");
            return;
        }

        try {
            const originField = state.staticArgField;
            const expField = new RegExp(state.argField, "g");
            const matchResult = getAllMatches(expField, originField);

            setArgFieldExp(matchResult.result);
            setArgFieldExpErr(matchResult.match.length === 0);
        } catch {
            setArgFieldExp([]);
            setArgFieldExpErr(true);
        }
    }

    const TabFragment = () => {
        switch (menuKey) {
            case 0:
                {/* URL/Argument */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    <Grid container spacing={1} mb={.5}>
                        <Grid item sm={1.5}>
                            <DatePicker customInput={<DateInputField />} dateFormat="yyyy/MM/dd" selected={state.startDate}
                                onChange={(date) => handleKeyPress({ target: { name: "startDate", value: date } })} />
                        </Grid>
                        <Grid item sm={.5}>
                            <TextField
                                fullWidth
                                select
                                name="startHours"
                                size="small"
                                value={state.startHours}
                                onChange={handleKeyPress}
                            >
                                {
                                    _.map(_.range(24), (i) => {
                                        return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                    })
                                }
                            </TextField>
                        </Grid>
                        <Grid item sm={.5}>
                            <TextField
                                fullWidth
                                select
                                name="startMinutes"
                                size="small"
                                value={state.startMinutes}
                                onChange={handleKeyPress}
                            >
                                {
                                    _.map(_.range(60), (i) => {
                                        return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                    })
                                }
                            </TextField>
                        </Grid>
                        <Grid item sm={.5}>
                            <TextField
                                fullWidth
                                select
                                name="startSeconds"
                                size="small"
                                value={state.startSeconds}
                                onChange={handleKeyPress}
                            >
                                {
                                    _.map(_.range(60), (i) => {
                                        return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                    })
                                }
                            </TextField>
                        </Grid>

                        <Grid item sm={.2} textAlign="center">~</Grid>

                        <Grid item sm={1.5}>
                            <DatePicker customInput={<DateInputField />} dateFormat="yyyy/MM/dd" selected={state.endDate}
                                onChange={(date) => handleKeyPress({ target: { name: "endDate", value: date } })} />
                        </Grid>
                        <Grid item sm={.5}>
                            <TextField
                                fullWidth
                                select
                                name="endHours"
                                size="small"
                                value={state.endHours}
                                onChange={handleKeyPress}
                            >
                                {
                                    _.map(_.range(24), (i) => {
                                        return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                    })
                                }
                            </TextField>
                        </Grid>
                        <Grid item sm={.5}>
                            <TextField
                                fullWidth
                                select
                                name="endMinutes"
                                size="small"
                                value={state.endMinutes}
                                onChange={handleKeyPress}
                            >
                                {
                                    _.map(_.range(60), (i) => {
                                        return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                    })
                                }
                            </TextField>
                        </Grid>
                        <Grid item sm={.5}>
                            <TextField
                                fullWidth
                                select
                                name="endSeconds"
                                size="small"
                                value={state.endSeconds}
                                onChange={handleKeyPress}
                            >
                                {
                                    _.map(_.range(60), (i) => {
                                        return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                    })
                                }
                            </TextField>
                        </Grid>

                        <Grid item sm={2}>
                            <ButtonGroup size="small" color="inherit" fullWidth>
                                <Button className={state.autoDate === "1minutes" ? "active" : ""} onClick={() => autoDatePicker("1", "minutes")}>1min</Button>
                                <Button className={state.autoDate === "3minutes" ? "active" : ""} onClick={() => autoDatePicker("3", "minutes")}>3min</Button>
                                <Button className={state.autoDate === "5minutes" ? "active" : ""} onClick={() => autoDatePicker("5", "minutes")}>5min</Button>
                            </ButtonGroup>
                        </Grid>
                    </Grid>

                    <Grid container spacing={1} mb={1}>
                        <Grid item sm={.5}>
                            <FormControlLabel control={<Checkbox name="isUseUrlSchText" checked={state.isUseUrlSchText} onChange={handleKeyPress} />} label="Text" />
                        </Grid>

                        <Grid item sm={1}>
                            <TextField
                                fullWidth
                                select
                                name="urlSchTextType"
                                size="small"
                                disabled={!state.isUseUrlSchText}
                                value={state.urlSchTextType}
                                onChange={handleKeyPress}
                            >
                                <MenuItem value="host">Host</MenuItem>
                                <MenuItem value="url">URL</MenuItem>
                                <MenuItem value="argument">Argument</MenuItem>
                                <MenuItem value="srcIp">Src IP</MenuItem>
                                <MenuItem value="srcPort">Src Port</MenuItem>
                                <MenuItem value="dstIp">Dst IP</MenuItem>
                                <MenuItem value="dstPort">Dst Port</MenuItem>
                            </TextField>
                        </Grid>

                        <Grid item sm={6.7}>
                            <TextField
                                fullWidth
                                name="urlSchText"
                                placeholder="Text"
                                size="small"
                                disabled={!state.isUseUrlSchText}
                                value={state.urlSchText}
                                onChange={handleKeyPress}
                            />
                        </Grid>

                        <Grid item sm={1}>
                            <Button variant="outlined" color="primary" size="small" startIcon={<SearchOutlined />} onClick={() => getRefererUrlData()}>Search</Button>
                        </Grid>
                    </Grid>

                    <Grid container pt={1} mb={1} className="top_underline">
                        <Grid item sm={11} mt={1}>
                            Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(refererUrlData.length)}</Typography>
                        </Grid>
                        <Grid item sm={1}>
                            <TextField
                                fullWidth
                                select
                                name="urlSchDeviceType"
                                size="small"
                                value={state.urlSchDeviceType}
                                onChange={handleKeyPress}
                            >
                                <MenuItem value="url">URL</MenuItem>
                                <MenuItem value="host">Host</MenuItem>
                                <MenuItem value="dst">Dst</MenuItem>
                                <MenuItem value="src">Src</MenuItem>
                            </TextField>
                        </Grid>
                        <Grid item sm={12} mt={.5}>
                            <Box component="div" className="ag-theme-alpine" sx={{ height: 300 }} onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="refererUriGridApi"
                                    columnDefs={refererUrlColumnDefs}
                                    rowData={refererUrlData}
                                    rowMultiSelectWithClick={false}
                                    formatGroupChild={formatGroupChild}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellRefererUrlClick}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </Grid>
                    </Grid>

                    <Box
                        component="form"
                        sx={{
                            '& .MuiTextField-root, .ag-theme-alpine': { mt: .5, mb: 1 }
                        }}
                        noValidate
                        autoComplete="off"
                    >
                        <FormControl sx={{ width: "15%", mt: .5 }}>
                            <Grid container>
                                <Grid item sm={2}>URL</Grid>
                                <Grid item sm={10}>
                                    <Button variant="contained" color="inherit" size="small" onClick={() => copyToClipboard("staticUrlField")}>Copy</Button>
                                </Grid>
                            </Grid>
                        </FormControl>
                        <FormControl sx={{ width: "85%" }}>
                            <TextField
                                multiline
                                rows={2}
                                placeholder="참조 URL"
                                size="small"
                                disabled={true}
                                value={state.staticUrlField}
                            />
                        </FormControl>
                        <FormControl sx={{ width: "15%", mt: .5 }}></FormControl>
                        {
                            state.urlPatternEnabled ? <>
                                <FormControl sx={{ width: "2%" }}>
                                    <Box className="MuiTextField-root" sx={{ p: ".3rem", height: "63px", textAlign: "center" }}>
                                        <Typography color="gray">/</Typography>
                                    </Box>
                                </FormControl>
                                <FormControl sx={{ width: "81%" }}>
                                    <TextField
                                        multiline
                                        rows={2}
                                        name="urlField"
                                        placeholder="URL"
                                        size="small"
                                        value={state.urlField}
                                        onChange={handleKeyPress}
                                    />
                                </FormControl>
                                <FormControl sx={{ width: "2%" }}>
                                    <Box className="MuiTextField-root" sx={{ p: ".3rem", height: "63px", textAlign: "center" }}>
                                        <Typography color="gray">/g</Typography>
                                    </Box>
                                </FormControl>
                            </> : <FormControl sx={{ width: "85%" }}>
                                <TextField
                                    multiline
                                    rows={2}
                                    name="urlField"
                                    placeholder="URL"
                                    size="small"
                                    value={state.urlField}
                                    onChange={handleKeyPress}
                                />
                            </FormControl>
                        }
                        <FormControl sx={{ width: "15%", mt: .5 }}></FormControl>
                        <FormControl sx={{ width: "85%" }}>
                            <Grid container>
                                <Grid item sm={2}>
                                    <FormControlLabel control={<Checkbox name="urlPatternEnabled" checked={state.urlPatternEnabled} onChange={handleKeyPress} />} label="URL 패턴 사용 여부" />
                                </Grid>
                                {
                                    state.urlPatternEnabled && <Grid item sm={10}>
                                        <Button variant="outlined" color="primary" size="small" startIcon={<PlayCircleOutlined />} onClick={patternChkUrl}>Match</Button>
                                    </Grid>
                                }
                            </Grid>
                        </FormControl>
                        {
                            state.urlPatternEnabled && <>
                                <FormControl sx={{ width: "15%", mt: .5 }}></FormControl>
                                <FormControl sx={{ width: "85%" }}>
                                    {
                                        urlFieldExpErr ? <Box className="matchField" sx={{ border: "1px solid #ff4d4f !important" }}>
                                            <Typography color="#ff4d4f">No Matches.</Typography>
                                        </Box> :
                                            <Box className="matchField">
                                                {
                                                    _.map(urlFieldExp, (obj, i) => (
                                                        <Typography key={i} component="span" className={obj.match ? "match" : ""} sx={{ padding: "2px" }}>{obj.char}</Typography>
                                                    ))
                                                }
                                            </Box>
                                    }
                                </FormControl>
                            </>
                        }

                        <FormControl sx={{ width: "15%", mt: .5 }}>
                            <Grid container>
                                <Grid item sm={5}>
                                    <FormControlLabel control={<Checkbox name="argUseEnabled" checked={state.argUseEnabled} onChange={handleKeyPress} />} label="Argument" />
                                </Grid>
                                <Grid item sm={7} className={state.argUseEnabled ? "" : "none"}>
                                    <Button variant="contained" color="inherit" size="small" onClick={() => copyToClipboard("staticArgField")}>Copy</Button>
                                </Grid>
                            </Grid>
                        </FormControl>
                        <FormControl sx={{ width: "85%" }}>
                            <TextField
                                multiline
                                rows={2}
                                placeholder="참조 Argument"
                                size="small"
                                disabled={true}
                                className={state.argUseEnabled ? "" : "none"}
                                value={state.staticArgField}
                            />
                        </FormControl>
                        {
                            state.argUseEnabled && <>
                                <FormControl sx={{ width: "15%", mt: .5 }}></FormControl>
                                {
                                    state.argPatternEnabled ? <>
                                        <FormControl sx={{ width: "2%" }}>
                                            <Box className="MuiTextField-root" sx={{ p: ".3rem", height: "63px", textAlign: "center" }}>
                                                <Typography color="gray">/</Typography>
                                            </Box>
                                        </FormControl>
                                        <FormControl sx={{ width: "81%" }}>
                                            <TextField
                                                multiline
                                                rows={2}
                                                name="argField"
                                                placeholder="Argument"
                                                size="small"
                                                value={state.argField}
                                                onChange={handleKeyPress}
                                            />
                                        </FormControl>
                                        <FormControl sx={{ width: "2%" }}>
                                            <Box className="MuiTextField-root" sx={{ p: ".3rem", height: "63px", textAlign: "center" }}>
                                                <Typography color="gray">/g</Typography>
                                            </Box>
                                        </FormControl>
                                    </> : <FormControl sx={{ width: "85%" }}>
                                        <TextField
                                            multiline
                                            rows={2}
                                            name="argField"
                                            placeholder="Argument"
                                            size="small"
                                            value={state.argField}
                                            onChange={handleKeyPress}
                                        />
                                    </FormControl>
                                }
                                <FormControl sx={{ width: "15%", mt: .5 }}></FormControl>
                                <FormControl sx={{ width: "85%" }}>
                                    <Grid container>
                                        <Grid item sm={2}>
                                            <FormControlLabel control={<Checkbox name="argPatternEnabled" checked={state.argPatternEnabled} onChange={handleKeyPress} />} label="Argument 패턴 사용 여부" />
                                        </Grid>
                                        {
                                            state.argPatternEnabled && <Grid item sm={10}>
                                                <Button variant="outlined" color="primary" size="small" startIcon={<PlayCircleOutlined />} onClick={patternChkArg}>Match</Button>
                                            </Grid>
                                        }
                                    </Grid>
                                </FormControl>
                                {
                                    state.argPatternEnabled && <>
                                        <FormControl sx={{ width: "15%", mt: .5 }}></FormControl>
                                        <FormControl sx={{ width: "85%" }}>
                                            {
                                                argFieldExpErr ? <Box className="matchField" sx={{ border: "1px solid #ff4d4f !important" }}>
                                                    <Typography color="#ff4d4f">No Matches.</Typography>
                                                </Box> :
                                                    <Box className="matchField">
                                                        {
                                                            _.map(argFieldExp, (obj, i) => (
                                                                <Typography key={i} component="span" className={obj.match ? "match" : ""} sx={{ padding: "2px" }}>{obj.char}</Typography>
                                                            ))
                                                        }
                                                    </Box>
                                            }
                                        </FormControl>
                                    </>
                                }
                                <FormControl sx={{ width: "15%", mt: .5 }}>Argument 컬럼 입력 순서</FormControl>
                                <FormControl sx={{ width: "85%" }}>
                                    <TextField
                                        name="argWriteHttp"
                                        placeholder="Argument 컬럼에 입력된 순서에 맞춰 저장 여부 지정"
                                        size="small"
                                        value={state.argWriteHttp}
                                        onChange={handleKeyPress}
                                    />
                                </FormControl>
                            </>
                        }
                    </Box>
                </Box>;
            case 1:
                {/* HTTP */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    <FormControl sx={{ width: "15%", mt: .5 }}>
                        <FormControlLabel control={<Checkbox name="isUseMethod" checked={state.isUseMethod} onChange={handleKeyPress} />} label="HTTP Method" />
                    </FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            select
                            name="httpMethod"
                            size="small"
                            sx={{ width: "15rem" }}
                            className={state.isUseMethod ? "" : "none"}
                            value={state.httpMethod}
                            onChange={handleKeyPress}
                        >
                            <MenuItem value="GET">GET</MenuItem>
                            <MenuItem value="POST">POST</MenuItem>
                            <MenuItem value="PUT">PUT</MenuItem>
                            <MenuItem value="DELETE">DELETE</MenuItem>
                            <MenuItem value="HEAD">HEAD</MenuItem>
                            <MenuItem value="TRACE">TRACE</MenuItem>
                            <MenuItem value="OPTIONS">OPTIONS</MenuItem>
                            <MenuItem value="CONNECT">CONNECT</MenuItem>
                        </TextField>
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: .5 }}>HTTP Body Type</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            select
                            name="httpBodyType"
                            size="small"
                            sx={{ width: "15rem" }}
                            value={state.httpBodyType}
                            onChange={handleKeyPress}
                        >
                            <MenuItem value="0">None</MenuItem>
                            <MenuItem value="1">application/x-www-form-urlencoded</MenuItem>
                            <MenuItem value="2">application/json</MenuItem>
                            <MenuItem value="3">application/xml</MenuItem>
                        </TextField>
                    </FormControl>
                </Box>;
            case 2:
                {/* Header */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    <FormControl sx={{ width: "15%" }}>
                        <FormControlLabel control={<Checkbox name="reqHeaderEnabled" checked={state.reqHeaderEnabled} onChange={handleKeyPress} />} label="Request HTTP Header" />
                    </FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="reqHeaderAlias"
                            placeholder="Alias"
                            size="small"
                            className={state.reqHeaderEnabled ? "" : "none"}
                            value={state.reqHeaderAlias}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <Box mt={1} mb={2}>
                        <Box component="div" className={state.reqHeaderEnabled ? "ag-theme-alpine" : "none"} mb={1} sx={{ height: 250 }} onContextMenu={(e) => e.preventDefault()}>
                            <AgGridModule
                                gridName="reqHeaderGridApi"
                                columnDefs={httpHeaderColumnDefs}
                                rowData={reqHeaderData}
                                rowMultiSelectWithClick={false}
                                onCellMouseDown={onCellMouseDown}
                                onCellClicked={onCellReqHeaderClick}
                                onGridReady={onGridReady}
                                handleResize={handleResize} />
                        </Box>

                        <Grid container className={state.reqHeaderEnabled ? "" : "none"}>
                            <Grid item sm={6}>
                                <Button variant="outlined" color="primary" size="small" sx={{ mr: .5 }} onClick={() => addHeaderEvt("request")}>추가</Button>
                                <Button variant="outlined" color="inherit" size="small" onClick={() => removeHeaderEvt("request")}>삭제</Button>
                            </Grid>
                            <Grid item sm={6} textAlign="right" pt={.7}>
                                Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(selectReqHeaderData.length)}</Typography>
                            </Grid>
                            <Grid item sm={12} mt={.5}>
                                <TableSelectHeaderItem children={selectReqHeaderData} target="request" handleHeaderKeyPress={handleHeaderKeyPress}
                                    handleHeaderCheckbox={handleHeaderCheckbox} handleHeaderAllCheckbox={handleHeaderAllCheckbox} />
                            </Grid>
                        </Grid>
                    </Box>

                    <FormControl sx={{ width: "15%" }}>
                        <FormControlLabel control={<Checkbox name="resHeaderEnabled" checked={state.resHeaderEnabled} onChange={handleKeyPress} />} label="Response HTTP Header" />
                    </FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="resHeaderAlias"
                            placeholder="Alias"
                            size="small"
                            className={state.resHeaderEnabled ? "" : "none"}
                            value={state.resHeaderAlias}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <Box mt={1} mb={1}>
                        <Box component="div" className={state.resHeaderEnabled ? "ag-theme-alpine" : "none"} mb={1} sx={{ height: 250 }} onContextMenu={(e) => e.preventDefault()}>
                            <AgGridModule
                                gridName="resHeaderGridApi"
                                columnDefs={httpHeaderColumnDefs}
                                rowData={resHeaderData}
                                rowMultiSelectWithClick={false}
                                onCellMouseDown={onCellMouseDown}
                                onCellClicked={onCellResHeaderClick}
                                onGridReady={onGridReady}
                                handleResize={handleResize} />
                        </Box>

                        <Grid container className={state.resHeaderEnabled ? "" : "none"}>
                            <Grid item sm={6}>
                                <Button variant="outlined" color="primary" size="small" sx={{ mr: .5 }} onClick={() => addHeaderEvt("response")}>추가</Button>
                                <Button variant="outlined" color="inherit" size="small" onClick={() => removeHeaderEvt("response")}>삭제</Button>
                            </Grid>
                            <Grid item sm={6} textAlign="right" pt={.7}>
                                Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(selectResHeaderData.length)}</Typography>
                            </Grid>
                            <Grid item sm={12} mt={.5}>
                                <TableSelectHeaderItem children={selectResHeaderData} target="response" handleHeaderKeyPress={handleHeaderKeyPress}
                                    handleHeaderCheckbox={handleHeaderCheckbox} handleHeaderAllCheckbox={handleHeaderAllCheckbox} />
                            </Grid>
                        </Grid>
                    </Box>
                </Box>;
            case 3:
                {/* Cookie */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    <FormControl sx={{ width: "15%" }}>
                        <FormControlLabel control={<Checkbox name="cookieEnabled" checked={state.cookieEnabled} onChange={handleKeyPress} />} label="Cookie" />
                    </FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="cookieAlias"
                            placeholder="Alias"
                            size="small"
                            className={state.cookieEnabled ? "" : "none"}
                            value={state.cookieAlias}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <Grid container mt={1} className={state.cookieEnabled ? "" : "none"}>
                        <Grid item sm={6}>
                            <Button variant="outlined" color="primary" size="small" sx={{ mr: .5 }} onClick={addCookieEvt}>추가</Button>
                            <Button variant="outlined" color="inherit" size="small" onClick={removeCookieEvt}>삭제</Button>
                        </Grid>
                        <Grid item sm={6} textAlign="right" pt={.7}>
                            Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(cookieData.length)}</Typography>
                        </Grid>
                        <Grid item sm={12} mt={.5}>
                            <TableContainer component={Paper} className="border">
                                <Table size="small" className="table-bordered">
                                    <TableHead className="thead-light">
                                        <TableRow>
                                            <TableCell align="center" sx={{ width: "3rem" }}>
                                                <Checkbox onChange={(e) => handleCookieAllCheckbox(e)} />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "3rem" }}>패턴</TableCell>
                                            <TableCell align="center" sx={{ width: "15rem" }}>키</TableCell>
                                            <TableCell align="center">값</TableCell>
                                            <TableCell align="center" sx={{ width: "5rem" }}>저장 여부</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {
                                            _.map(cookieData, (obj, i) => (
                                                <TableRow sx={{ '&:last-child td, &:last-child th': { border: 0 } }} key={i}>
                                                    <TableCell align="center">
                                                        <Checkbox name="isCookieChk" checked={obj.isCookieChk} onChange={(e) => handleCookieCheckbox(e, i)} />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <Checkbox name="isCookiePattern" checked={obj.isCookiePattern} onChange={(e) => handleCookieCheckbox(e, i)} />
                                                    </TableCell>
                                                    <TableCell>
                                                        <TextField
                                                            fullWidth
                                                            name="cookieKey"
                                                            placeholder="Key"
                                                            size="small"
                                                            value={obj.cookieKey}
                                                            onChange={(e) => handleCookieKeyPress(e, i)}
                                                        />
                                                    </TableCell>
                                                    <TableCell>
                                                        <TextField
                                                            fullWidth
                                                            name="cookieValue"
                                                            placeholder="Value"
                                                            size="small"
                                                            value={obj.cookieValue}
                                                            onChange={(e) => handleCookieKeyPress(e, i)}
                                                        />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <Checkbox name="isCookieWrite" checked={obj.isCookieWrite} onChange={(e) => handleCookieCheckbox(e, i)} />
                                                    </TableCell>
                                                </TableRow>
                                            ))
                                        }
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </Grid>
                    </Grid>
                </Box>;
            case 4:
                {/* Response Body */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    <FormControl sx={{ width: "15%" }}>
                        <FormControlLabel control={<Checkbox name="resBodyEnabled" checked={state.resBodyEnabled} onChange={handleKeyPress} />} label="Response Body" />
                    </FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="resBodyAlias"
                            placeholder="Alias"
                            size="small"
                            className={state.resBodyEnabled ? "" : "none"}
                            value={state.resBodyAlias}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <Grid container mt={1} className={state.resBodyEnabled ? "" : "none"}>
                        <Grid item sm={6}>
                            <Button variant="outlined" color="primary" size="small" sx={{ mr: .5 }} onClick={addResBodyEvt}>추가</Button>
                            <Button variant="outlined" color="inherit" size="small" onClick={removeResBodyEvt}>삭제</Button>
                        </Grid>
                        <Grid item sm={6} textAlign="right" pt={.7}>
                            Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(resBodyData.length)}</Typography>
                        </Grid>
                        <Grid item sm={12} mt={.5}>
                            <TableContainer component={Paper} className="border">
                                <Table size="small" className="table-bordered">
                                    <TableHead className="thead-light">
                                        <TableRow>
                                            <TableCell align="center" sx={{ width: "3rem" }}>
                                                <Checkbox onChange={(e) => handleResBodyAllCheckbox(e)} />
                                            </TableCell>
                                            <TableCell align="center" sx={{ width: "3rem" }}>패턴</TableCell>
                                            <TableCell align="center" sx={{ width: "15rem" }}>키</TableCell>
                                            <TableCell align="center">값</TableCell>
                                            <TableCell align="center" sx={{ width: "5rem" }}>저장 여부</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {
                                            _.map(resBodyData, (obj, i) => (
                                                <TableRow sx={{ '&:last-child td, &:last-child th': { border: 0 } }} key={i}>
                                                    <TableCell align="center">
                                                        <Checkbox name="isResBodyChk" checked={obj.isResBodyChk} onChange={(e) => handleResBodyCheckbox(e, i)} />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <Checkbox name="isResBodyPattern" checked={obj.isResBodyPattern} onChange={(e) => handleResBodyCheckbox(e, i)} />
                                                    </TableCell>
                                                    <TableCell>
                                                        <TextField
                                                            fullWidth
                                                            name="resBodyKey"
                                                            placeholder="Key"
                                                            size="small"
                                                            value={obj.resBodyKey}
                                                            onChange={(e) => handleResBodyKeyPress(e, i)}
                                                        />
                                                    </TableCell>
                                                    <TableCell>
                                                        <TextField
                                                            fullWidth
                                                            name="resBodyValue"
                                                            placeholder="Value"
                                                            size="small"
                                                            value={obj.resBodyValue}
                                                            onChange={(e) => handleResBodyKeyPress(e, i)}
                                                        />
                                                    </TableCell>
                                                    <TableCell align="center">
                                                        <Checkbox name="isResBodyWrite" checked={obj.isResBodyWrite} onChange={(e) => handleResBodyCheckbox(e, i)} />
                                                    </TableCell>
                                                </TableRow>
                                            ))
                                        }
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </Grid>
                    </Grid>
                </Box>;
            case 5:
                {/* Transaction 레벨 */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    {thresholdResourceField("trans", selectTransResource)}
                    {ThresholdTransTbContent}
                </Box>;
            case 6:
                {/* URI 레벨 */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    {thresholdResourceField("uri", selectUriResource)}
                    {ThresholdUriTbContent}
                </Box>;
            case 7:
                {/* URI 연계 */ }
                return <Box m={1} pb={1} className="bottom_underline">
                    <FormControl sx={{ width: "100%" }}>
                        <FormControlLabel control={<Checkbox name="uriMappingEnabled" checked={state.uriMappingEnabled} onChange={handleKeyPress} />} label="URI 필수 체크" />
                    </FormControl>

                    <Box className={state.uriMappingEnabled ? "" : "none"}>
                        <Box component="div" className="ag-theme-alpine" mb={1} sx={{ height: 250 }} onContextMenu={(e) => e.preventDefault()}>
                            <AgGridModule
                                gridName="uriMappingGridApi"
                                columnDefs={uriMappingRefererColumnDefs}
                                rowData={uriMappingRefererData}
                                rowMultiSelectWithClick={false}
                                onCellMouseDown={onCellMouseDown}
                                onCellClicked={onCellUriMappingClick}
                                onGridReady={onGridReady}
                                handleResize={handleResize} />
                        </Box>
                    </Box>

                    <Grid container className={state.uriMappingEnabled ? "" : "none"}>
                        <Grid item sm={6}>
                            <Button variant="outlined" color="primary" size="small" sx={{ mr: .5 }} onClick={() => addUriMappingEvt()}>추가</Button>
                            <Button variant="outlined" color="inherit" size="small" onClick={() => removeUriMappingEvt()}>삭제</Button>
                        </Grid>
                        <Grid item sm={6} textAlign="right" pt={.7}>
                            Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(selectUriMappingData.length)}</Typography>
                        </Grid>
                        <Grid item sm={12} mt={.5}>
                            <TableContainer component={Paper} className="border" sx={{ maxHeight: 200 }}>
                                <Table stickyHeader size="small" className="table-bordered">
                                    <TableHead className="thead-light">
                                        <TableRow>
                                            <TableCell align="center" sx={{ width: "3rem" }}>
                                                <Checkbox onChange={(e) => handleUriMappingAllCheckbox(e)} />
                                            </TableCell>
                                            <TableCell align="center">URI</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {
                                            _.map(selectUriMappingData, (obj, i) => (
                                                <TableRow sx={{ '&:last-child td, &:last-child th': { border: 0 } }} key={i}>
                                                    <TableCell align="center">
                                                        <Checkbox name="isUriMappingChk" checked={obj.isUriMappingChk} onChange={(e) => handleUriMappingCheckbox(e, i)} />
                                                    </TableCell>
                                                    <TableCell>
                                                        <TextField
                                                            fullWidth
                                                            name="uriMappingName"
                                                            placeholder="URI"
                                                            size="small"
                                                            value={obj.uriMappingName}
                                                            onChange={(e) => handleUriMappingKeyPress(e, i)}
                                                        />
                                                    </TableCell>
                                                </TableRow>
                                            ))
                                        }
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </Grid>
                    </Grid>
                </Box>;
        }
    }

    const onSavePolicyValidation = () => {
        if (state.urlAlias === "") {
            alert("URL Alias를 입력해주세요.");
            return;
        }

        if (state.urlField === "") {
            alert("URL 명을 입력해주세요.");
            return;
        }

        let requestData = {
            transaction: {
                urlName: state.urlField,
                urlAlias: state.urlAlias,
                isPattern: state.urlPatternEnabled,
                isArgument: state.argUseEnabled,
                isArgumentPattern: state.argPatternEnabled,
                argument: state.argField,
                writeHttpArgument: state.argWriteHttp,
                httpMethod: state.isUseMethod ? state.httpMethod : "",
                httpBodyType: parseInt(state.httpBodyType),
                isUriChk: state.uriMappingEnabled,
                isRequestHeader: state.reqHeaderEnabled,
                requestHeaderAlias: state.reqHeaderEnabled ? state.reqHeaderAlias : "",
                isResponseHeader: state.resHeaderEnabled,
                responseHeaderAlias: state.resHeaderEnabled ? state.resHeaderAlias : "",
                isResponseBody: state.resBodyEnabled,
                responseBodyAlias: state.resBodyEnabled ? state.resBodyAlias : "",
                isCookie: state.cookieEnabled,
                cookieAlias: state.cookieEnabled ? state.cookieAlias : "",
                transactionThresholdType: parseInt(state.transCriticalType),
                uriThresholdType: parseInt(state.uriCriticalType)
            }
        };

        if (state.uriMappingEnabled) {
            requestData.mappingUri = _.map(selectUriMappingData, (obj) => {
                return { uri: obj.uriMappingName };
            });
        }

        if (state.cookieEnabled) {
            requestData.mappingCookie = _.map(cookieData, (obj) => {
                return {
                    isValuePattern: obj.isCookiePattern,
                    httpCookieKey: obj.cookieKey,
                    httpCookieValue: obj.cookieValue,
                    writeHttpCookie: obj.isCookieWrite
                };
            });
        }

        if (state.resBodyEnabled) {
            requestData.mappingResponseBody = _.map(resBodyData, (obj) => {
                return {
                    isValuePattern: obj.isResBodyPattern,
                    httpResponseBodyKey: obj.resBodyKey,
                    httpResponseBodyValue: obj.resBodyValue,
                    writeHttpCookie: obj.isResBodyWrite
                };
            });
        }

        if (state.reqHeaderEnabled) {
            requestData.mappingRequestHeader = formatHeaderData(selectReqHeaderData);
        }

        if (state.resHeaderEnabled) {
            requestData.mappingResponseHeader = formatHeaderData(selectResHeaderData);
        }

        if (state.transCriticalType === "1") {
            requestData.transaction.transactionThresholdId = parseFloat(state.transactionCriticalPolicyId);
        } else if (state.transCriticalType === "2") {
            requestData.transactionThreshold = formatThresholdData(state.transCriticalType, criticalTransResource);
            requestData.transactionActiveField = _.map(selectTransResource, "key");
        }

        if (state.uriCriticalType === "1") {
            requestData.transaction.uriThresholdId = parseFloat(state.uriCriticalPolicyId);
        } else if (state.uriCriticalType === "2") {
            requestData.uriThreshold = formatThresholdData(state.uriCriticalType, criticalUriResource);
            requestData.uriActiveField = _.map(selectUriResource, "key");
        }

        applyPolicySave(requestData);
    }

    const applyPolicySave = (requestData) => {
        let reqUrl = "insert";

        if (params.id !== "0") {
            reqUrl = "update";
            requestData.transaction.urlId = parseFloat(params.id);
        }

        axiosConf.post("/api/setting/transaction/" + reqUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    // import content
    const ModalResourceContent = useMemo(() => (
        <ModalSearchFormResources open={resourceOpen} resourceItem={menuKey === 5 ? pageResources : uriResources} selectResource={menuKey === 5 ? selectTransResource : selectUriResource}
            onClose={handleResourceClose} onSuccess={bridgeModalResource} />
    ), [resourceOpen]);

    const ModalPolicyContent = useMemo(() => (
        <ModalSearchFormThresholdPolicy open={thresholdPolicyOpen} target={menuKey === 5 ? "transaction" : "uri"} onClose={handleThresholdPolicyClose} onSuccess={bridgeModalThresholdPolicy} />
    ), [thresholdPolicyOpen]);

    const ThresholdTransTbContent = useMemo(() => (
        <SetThresholdTable children={criticalTransResource} flagReadOnly={state.transCriticalType === "0" || state.transCriticalType === "1"} changeKey={changeTransKey}
            handleThresholdValue={handleThresholdValue} defaultThresholdValue={defaultThresholdValue} clearThresholdValue={clearThresholdValue} />
    ), [criticalTransResource, changeTransKey]);

    const ThresholdUriTbContent = useMemo(() => (
        <SetThresholdTable children={criticalUriResource} flagReadOnly={state.uriCriticalType === "0" || state.uriCriticalType === "1"} changeKey={changeUriKey}
            handleThresholdValue={handleThresholdValue} defaultThresholdValue={defaultThresholdValue} clearThresholdValue={clearThresholdValue} />
    ), [criticalUriResource, changeUriKey]);

    return (
        <Card>
            <CardHeader title="트랜잭션 설정"></CardHeader>
            <CardContent>
                <FormControl sx={{ width: "15%", mt: .5 }}>URL Alias</FormControl>
                <FormControl sx={{ width: "85%" }}>
                    <TextField
                        name="urlAlias"
                        placeholder="URL Alias"
                        size="small"
                        value={state.urlAlias}
                        onChange={handleKeyPress}
                    />
                </FormControl>

                <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper', mt: 1 }}>
                    <Tabs value={menuKey} onChange={handleChange} className="small">
                        <Tab label="URL/Argument" />
                        <Tab label="HTTP" />
                        <Tab label="Header" />
                        <Tab label="Cookie" />
                        <Tab label="Response Body" />
                        <Tab label="Transaction 레벨" />
                        <Tab label="URI 레벨" />
                        <Tab label="URI 연계" />

                        <Box component="div" className="text-ellipsis" sx={{ position: "absolute", right: 0, mt: .5, maxWidth: "30rem" }}>
                            URL: <Typography component="span" className="font-blue" sx={{ mr: 1 }}>{state.urlField}</Typography>
                        </Box>
                    </Tabs>
                </Box>

                {TabFragment()}

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>

            {ModalResourceContent}
            {ModalPolicyContent}
        </Card >
    );
};

export default PopupSetTransactionReg;