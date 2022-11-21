import React, { useState } from 'react';
import {
    Accordion, AccordionSummary, AccordionDetails, Box, FormControl, OutlinedInput, Button
} from '@mui/material';
import { DownOutlined, SearchOutlined, ReloadOutlined } from '@ant-design/icons';

import { setInputValue } from '../../../lib/common';

const SearchIpBandRegion = (props) => {
    const schItem = props.schItem;
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
                        '& .MuiFormControl-root, .MuiBox-root': { mb: .5 }
                    }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "10%" }}>IP</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <OutlinedInput
                            size="small"
                            placeholder="IP"
                            name="ipAddr"
                            value={schItem.ipAddr}
                            onChange={(e) => setInputValue(e, schItem, props.setSchItem)}
                        />
                    </FormControl>
                </Box>

                <Box component="div" sx={{ textAlign: "center" }}>
                    <Button variant="contained" color="primary" size="small" startIcon={<SearchOutlined />} onClick={() => props.searchEvt()}>조회하기</Button>
                    <Button variant="contained" color="secondary" size="small" startIcon={<ReloadOutlined />} sx={{ position: "absolute", right: ".5rem" }} onClick={props.resetEvt}>초기화</Button>
                </Box>

            </AccordionDetails>
        </Accordion>
    )
}

export default SearchIpBandRegion;