import React, { useState } from 'react';
import _ from 'lodash';
import {
    Accordion, AccordionSummary, AccordionDetails, Box, FormControl, Grid, ButtonGroup, Button, TextField, MenuItem, FormControlLabel, Checkbox,
    IconButton, Paper, Chip
} from '@mui/material';
import { DownOutlined, SearchOutlined, ReloadOutlined } from '@ant-design/icons';
import DatePicker from "react-datepicker";
import { styled } from '@mui/material/styles';
import WindowOpener from 'react-window-opener';

import { setInputValue, autoDatePicker, setInputAllChecked, setInputMultiChecked } from '../../../lib/common';
import DateInputField from "./SearchDateInputField";

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const actionStr = ["LOGIN", "LOGOUT", "CREATE", "UPDATE", "DELETE", "ACTIVE", "INACTIVE", "APPLY", "DOWNLOAD"];

const SearchAuditHistory = (props) => {
    const { schItem, setSchItem } = props;
    const [expanded, setExpanded] = useState(true);

    const handleChange = () => {
        setExpanded(!expanded);

        setTimeout(() => {
            props.autoComponentSize();
        }, 500);
    }

    const handleDelete = (idx, key) => {
        const filterData = _.filter(schItem[key], (obj, schIdx) => {
            return schIdx !== idx;
        });

        setInputValue({ target: { name: key, value: filterData } }, schItem, setSchItem);
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
                    <FormControl sx={{ width: "10%" }}>사용자</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }}>
                            <WindowOpener className="inline-block" url={"/popup/search/user?key=userId"} width="900" height="500" bridge={props.searchEvt} state={schItem}>
                                <IconButton edge="start" size="small" className="search-btn">
                                    <SearchOutlined />
                                </IconButton>
                            </WindowOpener>
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
                                        _.map(schItem.userId, (obj, i) => (
                                            <ListItem key={i}>
                                                <Chip size="small" label={obj.name} onDelete={() => handleDelete(i, "userId")} />
                                            </ListItem>
                                        ))
                                    }
                                </Paper>
                            </Box>
                        </Box>
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>권한</FormControl>
                    <Box component="div" display="inline-flex" sx={{ mb: 1 }}>
                        <FormControlLabel sx={{ pr: 1.5 }} className="right_underline"
                            control={<Checkbox name="action" id="action-all" onChange={(e) => setInputAllChecked(e, schItem, props.setSchItem, actionStr)} checked={schItem.action.length === 9} />} label="전체" />
                        {
                            _.map(actionStr, (el, i) => (
                                <FormControlLabel key={i} control={<Checkbox name="action" id={"action-" + el} checked={schItem.action.indexOf(el) !== -1} onChange={(e) => setInputMultiChecked(e, schItem, props.setSchItem)} />} label={el} />
                            ))
                        }
                    </Box><br />

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
                                    <Button className={schItem.autoDate === "12hours" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "12", "hours")}>12hour</Button>
                                    <Button className={schItem.autoDate === "24hours" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "24", "hours")}>24hour</Button>
                                    <Button className={schItem.autoDate === "7days" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "7", "days")}>7day</Button>
                                    <Button className={schItem.autoDate === "30days" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "30", "days")}>30day</Button>
                                </ButtonGroup>
                            </Grid>
                        </Grid>
                    </FormControl>
                </Box>

                <Box component="div" sx={{ textAlign: "center" }}>
                    <Button variant="contained" color="primary" size="small" startIcon={<SearchOutlined />} onClick={props.searchEvt}>조회하기</Button>
                    <Button variant="contained" color="secondary" size="small" startIcon={<ReloadOutlined />} sx={{ position: "absolute", right: ".5rem" }} onClick={props.resetEvt}>초기화</Button>
                </Box>
            </AccordionDetails>
        </Accordion>
    )
}

export default SearchAuditHistory;