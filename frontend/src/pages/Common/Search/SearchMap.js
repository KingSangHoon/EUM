import React, { useState } from 'react';
import _ from 'lodash';
import {
    Accordion, AccordionSummary, AccordionDetails, Box, FormControl, IconButton, Paper, Chip, Grid, ButtonGroup, Button, TextField, MenuItem
} from '@mui/material';
import { DownOutlined, SearchOutlined, ReloadOutlined } from '@ant-design/icons';
import DatePicker from "react-datepicker";
import { styled } from '@mui/material/styles';
import WindowOpener from 'react-window-opener';

import { setInputValue, autoDatePicker } from '../../../lib/common';
import DateInputField from "./SearchDateInputField";

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const SearchMap = (props) => {
    const { schItem, setSchItem, searchEvt, resetEvt } = props;

    const [expanded, setExpanded] = useState(true);

    const handleChange = () => {
        setExpanded(!expanded);

        setTimeout(() => {
            props.autoComponentSize();
        }, 500);
    }

    return (
        <Accordion expanded={expanded} id="searchEl" className="searchEl">
            <AccordionSummary expandIcon={<DownOutlined />} onClick={handleChange} sx={{ transform: !expanded ? "rotate(180deg)" : "" }} />
            <AccordionDetails>
                <Box
                    component="form"
                    sx={{
                        '& .MuiFormControl-root, .MuiPaper-root': { mb: .5 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "10%" }}>기간</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <Grid container spacing={.5}>
                            <Grid item sm={1}>
                                <DatePicker customInput={<DateInputField />} dateFormat="yyyy/MM/dd" selected={schItem.startDate}
                                    onChange={(date) => { setInputValue({ target: { name: "startDate", value: date } }, schItem, setSchItem); }} />
                            </Grid>
                            <Grid item sm={.5}>
                                <TextField
                                    fullWidth
                                    select
                                    name="startHours"
                                    size="small"
                                    value={schItem.startHours}
                                    onChange={(e) => setInputValue(e, schItem, setSchItem)}
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
                                    value={schItem.startMinutes}
                                    onChange={(e) => setInputValue(e, schItem, setSchItem)}
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
                                    value={schItem.startSeconds}
                                    onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                >
                                    {
                                        _.map(_.range(60), (i) => {
                                            return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                        })
                                    }
                                </TextField>
                            </Grid>

                            <Grid item sm={.2} sx={{ textAlign: "center" }}>~</Grid>

                            <Grid item sm={1}>
                                <DatePicker customInput={<DateInputField />} dateFormat="yyyy/MM/dd" selected={schItem.endDate}
                                    onChange={(date) => { setInputValue({ target: { name: "endDate", value: date } }, schItem, setSchItem); }} />
                            </Grid>
                            <Grid item sm={.5}>
                                <TextField
                                    fullWidth
                                    select
                                    name="endHours"
                                    size="small"
                                    value={schItem.endHours}
                                    onChange={(e) => setInputValue(e, schItem, setSchItem)}
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
                                    value={schItem.endMinutes}
                                    onChange={(e) => setInputValue(e, schItem, setSchItem)}
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
                                    value={schItem.endSeconds}
                                    onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                >
                                    {
                                        _.map(_.range(60), (i) => {
                                            return <MenuItem key={i} value={i < 10 ? "0" + i : i}>{i < 10 ? "0" + i : i}</MenuItem>
                                        })
                                    }
                                </TextField>
                            </Grid>

                            <Grid item sm={2} ml={1}>
                                <ButtonGroup size="small" color="inherit" fullWidth>
                                    <Button className={schItem.autoDate === "30minutes" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "30", "minutes")}>30min</Button>
                                    <Button className={schItem.autoDate === "1hours" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "1", "hours")}>1hour</Button>
                                    <Button className={schItem.autoDate === "24hours" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "24", "hours")}>24hour</Button>
                                    <Button className={schItem.autoDate === "1day" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "1", "day")}>1day</Button>
                                </ButtonGroup>
                            </Grid>
                        </Grid>
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>Interval</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            fullWidth
                            select
                            name="interval"
                            size="small"
                            value={schItem.interval}
                            onChange={(e) => setInputValue(e, schItem, setSchItem)}
                        >
                            <MenuItem value="1min">1min</MenuItem>
                            <MenuItem value="10min">10min</MenuItem>
                            <MenuItem value="1hour">1hour</MenuItem>
                            <MenuItem value="1day">1day</MenuItem>
                        </TextField>
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>자원</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }}>
                            <WindowOpener className="inline-block" url={"/popup/search/resources/single?key=resource"} width="900" height="800" bridge={searchEvt} state={schItem}>
                                <IconButton edge="start" size="small" className="search-btn">
                                    <SearchOutlined />
                                </IconButton>
                            </WindowOpener>
                            {/* <IconButton edge="start" size="small" className="search-btn" onClick={() => {
                                const win = window.open("/popup/search/resources/single?key=resource", "_blank", "width=900 height=800");
                                const browser = window.self;

                                win.onload = function () {
                                    var doc = win.document;

                                    var newDiv = doc.createElement("input");
                                    newDiv.type = "hidden";
                                    newDiv.id = "stateInput";
                                    newDiv.value = JSON.stringify(schItem);

                                    doc.body.appendChild(newDiv);
                                };

                                browser.onSuccess = function (res) {
                                    console.log(res)
                                    searchEvt(null, res);
                                };
                            }}>
                                <SearchOutlined />
                            </IconButton> */}
                            <Box component="div" flex="1 1">
                                <Paper
                                    sx={{
                                        display: 'flex',
                                        flexWrap: 'wrap',
                                        listStyle: 'none',
                                        pl: .5,
                                        m: 0,
                                    }}
                                    component="ul"
                                >
                                    {
                                        _.map(schItem.resource, (obj, i) => (
                                            <ListItem key={i}>
                                                <Chip size="small" label={obj.name} />
                                            </ListItem>
                                        ))
                                    }
                                </Paper>
                            </Box>
                        </Box>
                    </FormControl>
                </Box>

                <Box component="div" sx={{ textAlign: "center" }}>
                    <Button variant="contained" color="primary" size="small" startIcon={<SearchOutlined />} onClick={searchEvt}>조회하기</Button>
                    <Button variant="contained" color="secondary" size="small" startIcon={<ReloadOutlined />} sx={{ position: "absolute", right: ".5rem" }} onClick={resetEvt}>초기화</Button>
                </Box>

            </AccordionDetails>
        </Accordion>
    )
}

export default SearchMap;