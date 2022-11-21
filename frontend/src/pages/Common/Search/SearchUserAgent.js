import React, { useState } from 'react';
import {
    Accordion,
    AccordionSummary,
    AccordionDetails,
    Box,
    FormControl,
    OutlinedInput,
    Button,
    TextField, MenuItem
} from '@mui/material';
import { DownOutlined, SearchOutlined, ReloadOutlined } from '@ant-design/icons';

import { setInputValue } from '../../../lib/common';
import _ from "lodash";

const SearchUserAgent = (props) => {
    const schItem = props.schItem;
    const [expanded, setExpanded] = useState(true);

    const [selectUserAgentType, setSelectUserAgentType] = useState({
        userAgentType: "전체",
    });

    const [category, setCategory] = useState([{
        key: "전체",
        label: "전체",
    }, {
        key: "software",
        label: "Software",
    }, {
        key: "operatingSystems",
        label: "Operating Systems",
    }, {
        key: "operatingPlatforms",
        label: "Vendor Operating Platforms",
    }, {
        key: "softwareTypes",
        label: "Software Types",
    }, {
        key: "hardwareTypes",
        label: "Hardware Types",
    }, {
        key: "layoutEngines",
        label: "Layout Engines",
    }]);

    const handleChange = () => {
        setExpanded(!expanded);

        setTimeout(() => {
            props.autoComponentSize();
        }, 500);
    }

    const handleKeyPress = (e) => {
        setSelectUserAgentType({ ...selectUserAgentType, [e.target.name]: e.target.value });
        //setInputValue(e, schItem, props.setSchItem)
        props.setSchItem({ ...schItem, [e.target.name]: e.target.value })
    }

    return (
        <Accordion expanded={expanded} id="searchEl" className="searchEl">
            <AccordionSummary expandIcon={<DownOutlined />} onClick={handleChange} sx={{ transform: !expanded ? "rotate(180deg)" : "" }} />
            <AccordionDetails>
                <Box
                    component="form"
                    sx={{
                        '& .MuiFormControl-root, .MuiBox-root': { mb: .5 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "10%" }}>User Agent</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <OutlinedInput
                            size="small"
                            placeholder="User Agent"
                            name="userAgent"
                            value={schItem.userAgent}
                            onChange={(e) => setInputValue(e, schItem, props.setSchItem)}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>타입</FormControl>

                    <FormControl sx={{ width: "90%" }}>
                        <TextField
                            select
                            name="userAgentType"
                            size="small"
                            value={selectUserAgentType.userAgentType}
                            onChange={handleKeyPress}
                            defaultValue="전체"
                        >
                            {_.map(category, (obj, i) => { return <MenuItem key={i} value={obj.key}>{obj.label}</MenuItem> })}
                        </TextField>
                    </FormControl>

                    <br></br>

                </Box>

                <Box component="div" sx={{ textAlign: "center" }}>
                    <Button variant="contained" color="primary" size="small" startIcon={<SearchOutlined />} onClick={props.searchEvt}>조회하기</Button>
                    <Button variant="contained" color="secondary" size="small" startIcon={<ReloadOutlined />} sx={{ position: "absolute", right: ".5rem" }} onClick={() => {
                        props.resetEvt()
                        setSelectUserAgentType({ userAgentType: "전체" })
                    }}>초기화</Button>
                </Box>

            </AccordionDetails>
        </Accordion>
    )
}

export default SearchUserAgent;