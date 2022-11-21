import React, { useState } from 'react';
import _ from 'lodash';
import moment from "moment";
import { Card, CardHeader, Box, Button, CardContent, Modal, Grid, TextField, MenuItem, ButtonGroup, FormControlLabel, Checkbox, Typography } from '@mui/material';
import DatePicker from "react-datepicker";

import { SearchOutlined } from '@ant-design/icons';

import { gridApiObj, modalStyles, numberWithCommas } from '../../../lib/common';
import { AgGridModule } from "../../../lib/AgGridModule";
import axiosConf from "../../../axios";
import Loader from "../../../components/Loader";
import DateInputField from "../Search/SearchDateInputField";

const ModalSearchFormApplicationSetting = React.forwardRef((props, ref) => {
    const [state, setState] = useState({
        autoDate: "1minutes",
        startDate: moment().subtract(59, "seconds").toDate(),
        startHours: moment().subtract(59, 'seconds').format("HH"),
        startMinutes: moment().subtract(59, 'seconds').format("mm"),
        startSeconds: moment().subtract(59, 'seconds').format("ss"),
        endDate: moment().toDate(),
        endHours: moment().format("HH"),
        endMinutes: moment().format("mm"),
        endSeconds: moment().format("ss"),
    });

    const [showLoader, setShowLoader] = useState(false);

    const [searchIpData, setSearchIpData] = useState([]);

    const ipSearchColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererParams: {
            checkbox: true
        },
        checkboxSelection: true,
        lockPosition: true
    },
    {
        headerName: 'IP',
        field: 'dstIp',
        filter: "agTextColumnFilter",
    },
    {
        headerName: 'MAC',
        field: 'dstMac',
        filter: "agTextColumnFilter",
    },
    {
        headerName: 'Port',
        field: 'dstPort',
        filter: "agTextColumnFilter",
    },
    {
        headerName: '등록여부',
        field: 'flag',
        filter: "agTextColumnFilter",
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            return params.value === undefined ? "X" : params.value;
        },
    }];

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.type === "checkbox" ? e.target.checked : e.target.value });
    }

    const getSearchIpData = () => {
        setShowLoader(true);

        const formatStartDate = moment(state.startDate).format("YYYYMMDD") + state.startHours + state.startMinutes + state.startSeconds;
        const formatEndDate = moment(state.endDate).format("YYYYMMDD") + state.endHours + state.endMinutes + state.endSeconds;

        let requestData = {
            begin: formatStartDate,
            end: formatEndDate,
            isSearchTxt: state.isIpSchText == undefined ? false : state.isIpSchText
        };

        if (state.isIpSchText) {
            requestData.searchTxt = state.urlSchText;
        }

        axiosConf.post("/api/setting/application/searchIpAndMacAndPortByDateOrIp", requestData).then(res => {
            const applicationInfo = res.data.infoApplicationIp;

            _.map(res.data.searchIpList, (obj) => {
                _.map(applicationInfo, (ip) => {
                    if (obj.dstIp == ip.ip) {
                        if (obj.dstMac == ip.mac) {
                            if ((ip.startPort <= Number(obj.dstPort)) && (Number(obj.dstPort) <= ip.endPort)) {
                                obj.flag = ip.title;
                            }
                        }
                    }
                })
            });

            setSearchIpData(res.data.searchIpList);
            setShowLoader(false);
        });
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const onSelectIpValidation = () => {
        const selectedNodes = gridApiObj.searchIpGridApi.api.getSelectedRows();

        if (selectedNodes.length === 0) {
            alert("선택된 값이 없습니다.");
            return;
        }

        props.onSuccess(selectedNodes);
        props.onClose();
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

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;

        if (gridApiObj.searchIpGridApi) {
            gridApiObj.searchIpGridApi.api.sizeColumnsToFit();
        }
    }

    return (
        <Modal open={props.open} onClose={props.onClose}>
            <Card sx={modalStyles}>
                <CardHeader title="IP"></CardHeader>
                <CardContent>
                    <Box m={1} pb={1} className="bottom_underline">
                        <Grid container spacing={1} mb={.5}>
                            <Grid item sm={1.5}>
                                <DatePicker customInput={<DateInputField />} dateFormat="yyyy/MM/dd"
                                    selected={state.startDate}
                                    onChange={(date) => handleKeyPress({
                                        target: {
                                            name: "startDate",
                                            value: date
                                        }
                                    })} />
                            </Grid>
                            <Grid item sm={1}>
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
                            <Grid item sm={1}>
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
                            <Grid item sm={1}>
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
                                <DatePicker customInput={<DateInputField />} dateFormat="yyyy/MM/dd"
                                    selected={state.endDate}
                                    onChange={(date) => handleKeyPress({
                                        target: {
                                            name: "endDate",
                                            value: date
                                        }
                                    })} />
                            </Grid>
                            <Grid item sm={1}>
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
                            <Grid item sm={1}>
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
                            <Grid item sm={1}>
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
                            <Grid item sm={0.7}>
                                <FormControlLabel control={<Checkbox name="isIpSchText" checked={state.isIpSchText} onChange={handleKeyPress} />} label="Text" />
                            </Grid>
                            <Grid item sm={6.7}>
                                <TextField
                                    fullWidth
                                    name="urlSchText"
                                    placeholder="Text"
                                    size="small"
                                    disabled={!state.isIpSchText}
                                    value={state.urlSchText}
                                    onChange={handleKeyPress}
                                />
                            </Grid>

                            <Grid item sm={1}>
                                <Button variant="outlined" color="primary" size="small" startIcon={<SearchOutlined />} onClick={getSearchIpData}>Search</Button>
                            </Grid>
                        </Grid>

                        <Grid container pt={1} className="top_underline">
                            <Grid item sm={11}>
                                Total: <Typography component="span" className="font-bold font-blue">{numberWithCommas(searchIpData.length)}</Typography>
                            </Grid>
                            <Grid item sm={12} mt={.5}>
                                <Box component="div" className="ag-theme-alpine" sx={{ height: 430 }} onContextMenu={(e) => e.preventDefault()}>
                                    <AgGridModule
                                        gridName="searchIpGridApi"
                                        columnDefs={ipSearchColumnDefs}
                                        rowData={searchIpData}
                                        rowSelection="single"
                                        formatGroupChild={formatGroupChild}
                                        onGridReady={onGridReady}
                                        handleResize={handleResize} />
                                </Box>
                            </Grid>
                        </Grid>
                    </Box>
                </CardContent>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSelectIpValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={props.onClose}>닫기</Button>
                </Box>

                {showLoader && (<Loader />)}
            </Card>
        </Modal>
    );
});

export default ModalSearchFormApplicationSetting;