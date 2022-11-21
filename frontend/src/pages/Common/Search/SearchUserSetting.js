import React, { useState } from 'react';
import {
    Accordion, AccordionSummary, AccordionDetails, Box, FormControl, OutlinedInput, FormControlLabel, Checkbox, RadioGroup, Radio, Button
} from '@mui/material';
import { DownOutlined, SearchOutlined, ReloadOutlined } from '@ant-design/icons';

import { setInputValue, setInputChecked, setInputMultiChecked, setInputAllChecked } from '../../../lib/common';

const SearchUserSetting = (props) => {
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
                    <FormControl sx={{ width: "10%" }}>아이디</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <OutlinedInput
                            size="small"
                            placeholder="아이디"
                            name="loginId"
                            value={schItem.loginId}
                            onChange={(e) => setInputValue(e, schItem, props.setSchItem)}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>이름</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <OutlinedInput
                            size="small"
                            placeholder="이름"
                            name="username"
                            value={schItem.username}
                            onChange={(e) => setInputValue(e, schItem, props.setSchItem)}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>이메일</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <OutlinedInput
                            size="small"
                            placeholder="이메일"
                            name="email"
                            value={schItem.email}
                            onChange={(e) => setInputValue(e, schItem, props.setSchItem)}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>전화번호</FormControl>
                    <FormControl sx={{ width: "90%" }}>
                        <OutlinedInput
                            size="small"
                            placeholder="전화번호"
                            name="phoneNumber"
                            value={schItem.phoneNumber}
                            onChange={(e) => setInputValue(e, schItem, props.setSchItem)}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "10%" }}>권한</FormControl>
                    <Box component="div" display="inline-flex">
                        <FormControlLabel sx={{ pr: 1.5 }} className="right_underline"
                            control={<Checkbox name="roles" id="roles-all" onChange={(e) => setInputAllChecked(e, schItem, props.setSchItem, ["ROLE_ADMIN", "ROLE_USER"])} checked={schItem.roles.length === 2} />} label="전체" />
                        <FormControlLabel control={<Checkbox name="roles" id="roles-ROLE_USER" checked={schItem.roles.indexOf("ROLE_USER") !== -1} onChange={(e) => setInputMultiChecked(e, schItem, props.setSchItem)} />} label="사용자" />
                        <FormControlLabel control={<Checkbox name="roles" id="roles-ROLE_ADMIN" checked={schItem.roles.indexOf("ROLE_ADMIN") !== -1} onChange={(e) => setInputMultiChecked(e, schItem, props.setSchItem)} />} label="관리자" />
                    </Box><br />

                    <FormControl sx={{ width: "10%" }}>계정</FormControl>
                    <Box component="div" display="inline-flex">
                        <FormControlLabel sx={{ pr: 1.5 }} className="right_underline"
                            control={<Checkbox name="allActive" onChange={(e) => setInputChecked(e, schItem, props.setSchItem)} checked={schItem.allActive} />} label="전체" />
                        <RadioGroup row name="active" value={schItem.active} onChange={(e) => setInputValue(e, schItem, props.setSchItem)}>
                            <FormControlLabel value="0" control={<Radio disabled={schItem.allActive} />} label="활성" />
                            <FormControlLabel value="1" control={<Radio disabled={schItem.allActive} />} label="비활성" />
                        </RadioGroup>
                    </Box>
                </Box>

                <Box component="div" sx={{ textAlign: "center" }}>
                    <Button variant="contained" color="primary" size="small" startIcon={<SearchOutlined />} onClick={props.searchEvt}>조회하기</Button>
                    <Button variant="contained" color="secondary" size="small" startIcon={<ReloadOutlined />} sx={{ position: "absolute", right: ".5rem" }} onClick={props.resetEvt}>초기화</Button>
                </Box>

            </AccordionDetails>
        </Accordion>
    )
}

export default SearchUserSetting;