import React, { useState } from 'react';
import _ from 'lodash';
import {
    Accordion, AccordionSummary, AccordionDetails, Box, FormControl, Grid, ButtonGroup, Button, TextField, MenuItem
} from '@mui/material';
import { DownOutlined, SearchOutlined, ReloadOutlined } from '@ant-design/icons';
import DatePicker from "react-datepicker";

import { setInputValue, autoDatePicker } from '../../../lib/common';
import DateInputField from "./SearchDateInputField";

const SearchCommonDetail = (props) => {
    const { schItem, setSchItem } = props;
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

                            <Grid item sm={1.5} ml={1}>
                                <ButtonGroup size="small" color="inherit" fullWidth>
                                    <Button className={schItem.autoDate === "1minutes" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "1", "minutes")}>1min</Button>
                                    <Button className={schItem.autoDate === "3minutes" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "3", "minutes")}>3min</Button>
                                    <Button className={schItem.autoDate === "5minutes" ? "active" : ""} onClick={() => autoDatePicker(schItem, setSchItem, "5", "minutes")}>5min</Button>
                                </ButtonGroup>
                            </Grid>
                        </Grid>
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>상세</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <Button variant="contained" color="primary" size="small" startIcon={<SearchOutlined />} sx={{ width: "3rem", '& .MuiButton-startIcon': { m: 0 } }} onClick={props.handleOpen}></Button>
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

export default SearchCommonDetail;