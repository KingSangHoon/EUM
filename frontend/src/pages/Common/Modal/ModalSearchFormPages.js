import React from 'react';
import _ from 'lodash';
import WindowOpener from 'react-window-opener';
import { styled } from '@mui/material/styles';
import { Card, CardHeader, Grid, Box, Button, CardContent, Modal, FormLabel, FormControlLabel, IconButton, Paper, Chip, Checkbox, TextField, MenuItem } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowsLeftRight } from "@fortawesome/free-solid-svg-icons";
import { SearchOutlined } from '@ant-design/icons';

import { modalStyles, setInputValue, setInputChecked } from '../../../lib/common';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const ModalSearchFormPages = React.forwardRef((props, ref) => {
    const { schItem, setSchItem, searchEvt } = props;

    const handleDelete = (idx, key) => {
        const filterData = _.filter(schItem[key], (obj, schIdx) => {
            return schIdx !== idx;
        });

        setInputValue({ target: { name: key, value: filterData } }, schItem, setSchItem);
    }

    const drawTypeAheadElement = (children) => {
        return (
            <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }}>
                <WindowOpener className="inline-block" url={children.url} width={children.width || 900} height={children.height || 500} bridge={searchEvt} state={schItem}>
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
                            _.map(schItem[children.key], (obj, i) => (
                                <ListItem key={i}>
                                    <Chip size="small" label={obj.name} onDelete={() => handleDelete(i, children.key)} />
                                </ListItem>
                            ))
                        }
                    </Paper>
                </Box>
            </Box>
        );
    }

    return (
        <Modal open={props.open} onClose={props.onClose}>
            <Card sx={{ ...modalStyles, maxWidth: "1200px" }}>
                <CardHeader title="검색"></CardHeader>
                <CardContent>
                    <Grid container spacing={1} className="searchEl" sx={{ '& .MuiPaper-root, .MuiTextField-root': { border: "1px solid #babfc7" }, p: .5 }}>

                        {/* Zone */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Zone</Grid>
                        <Grid item sm={3}>
                            <FormLabel>Src</FormLabel>
                            {drawTypeAheadElement({ key: "srcZone", url: "/popup/search/zone?urlType=server&key=srcZone", height: 550 })}
                        </Grid>
                        <Grid item sm={.5} textAlign="center" sx={{ marginTop: "auto" }}>
                            <FontAwesomeIcon icon={faArrowsLeftRight} style={{ fontSize: "1rem", color: "#8c8c8c" }} />
                        </Grid>
                        <Grid item sm={3}>
                            <FormLabel>Dst</FormLabel>
                            {drawTypeAheadElement({ key: "dstZone", url: "/popup/search/zone?urlType=client&key=dstZone", height: 550 })}
                        </Grid>
                        <Grid item sm={.25} sx={{ height: "2.5rem", marginTop: "auto" }} className="right_underline"></Grid>
                        <Grid item sm={.25} sx={{ height: "2.5rem", marginTop: "auto" }}></Grid>
                        <Grid item sm={3.5}>
                            <FormLabel>Src or Dst</FormLabel>
                            {drawTypeAheadElement({ key: "bothZone", url: "/popup/search/zone?urlType=client&key=bothZone", height: 550 })}
                        </Grid>

                        {/* IP */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">IP</Grid>
                        <Grid item sm={3}>
                            <FormLabel sx={{ mr: 1.5 }}>Src</FormLabel>
                            <FormControlLabel control={<Checkbox name="isTextSrcIp" checked={schItem.isTextSrcIp} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                            {
                                schItem.isTextSrcIp ?
                                    <TextField
                                        fullWidth
                                        name="srcIpTxt"
                                        placeholder="Src IP"
                                        size="small"
                                        value={schItem.srcIpTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "srcIp", url: "/popup/search/http?uri=page&key=srcIp&str=IP" })
                            }
                        </Grid>
                        <Grid item sm={.5} textAlign="center" sx={{ marginTop: "auto" }}>
                            <FontAwesomeIcon icon={faArrowsLeftRight} style={{ fontSize: "1rem", color: "#8c8c8c" }} />
                        </Grid>
                        <Grid item sm={3}>
                            <FormLabel sx={{ mr: 1.5 }}>Dst</FormLabel>
                            <FormControlLabel control={<Checkbox name="isTextDstIp" checked={schItem.isTextDstIp} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                            {
                                schItem.isTextDstIp ?
                                    <TextField
                                        fullWidth
                                        name="dstIpTxt"
                                        placeholder="Dst IP"
                                        size="small"
                                        value={schItem.dstIpTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "dstIp", url: "/popup/search/http?uri=page&key=dstIp&str=IP" })
                            }
                        </Grid>
                        <Grid item sm={.25} sx={{ height: "2.5rem", marginTop: "auto" }} className="right_underline"></Grid>
                        <Grid item sm={.25} sx={{ height: "2.5rem", marginTop: "auto" }}></Grid>
                        <Grid item sm={3.5}>
                            <FormLabel sx={{ mr: 1.5 }}>Src or Dst</FormLabel>
                            <FormControlLabel control={<Checkbox name="isTextBothIp" checked={schItem.isTextBothIp} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                            {
                                schItem.isTextBothIp ?
                                    <TextField
                                        fullWidth
                                        name="bothIpTxt"
                                        placeholder="Src or Dst IP"
                                        size="small"
                                        value={schItem.bothIpTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "bothIp", url: "/popup/search/http?uri=page&key=bothIp&str=IP" })
                            }
                        </Grid>

                        {/* Port */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Port</Grid>
                        <Grid item sm={3}>
                            <FormLabel sx={{ mr: 1.5 }}>Src</FormLabel>
                            <FormControlLabel control={<Checkbox name="isTextSrcPort" checked={schItem.isTextSrcPort} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                            {
                                schItem.isTextSrcPort ?
                                    <TextField
                                        fullWidth
                                        type="number"
                                        InputProps={{ inputProps: { min: 0 } }}
                                        name="srcPortTxt"
                                        placeholder="Src Port (type: Number)"
                                        size="small"
                                        value={schItem.srcPortTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "srcPort", url: "/popup/search/http?uri=page&key=srcPort&str=Port" })
                            }
                        </Grid>
                        <Grid item sm={.5} textAlign="center" sx={{ marginTop: "auto" }}>
                            <FontAwesomeIcon icon={faArrowsLeftRight} style={{ fontSize: "1rem", color: "#8c8c8c" }} />
                        </Grid>
                        <Grid item sm={3}>
                            <FormLabel sx={{ mr: 1.5 }}>Dst</FormLabel>
                            <FormControlLabel control={<Checkbox name="isTextDstPort" checked={schItem.isTextDstPort} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                            {
                                schItem.isTextDstPort ?
                                    <TextField
                                        fullWidth
                                        type="number"
                                        InputProps={{ inputProps: { min: 0 } }}
                                        name="dstPortTxt"
                                        placeholder="Dst Port (type: Number)"
                                        size="small"
                                        value={schItem.dstPortTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "dstPort", url: "/popup/search/http?uri=page&key=dstPort&str=Port" })
                            }
                        </Grid>
                        <Grid item sm={.25} sx={{ height: "2.5rem", marginTop: "auto" }} className="right_underline"></Grid>
                        <Grid item sm={.25} sx={{ height: "2.5rem", marginTop: "auto" }}></Grid>
                        <Grid item sm={3.5}>
                            <FormLabel sx={{ mr: 1.5 }}>Src or Dst</FormLabel>
                            <FormControlLabel control={<Checkbox name="isTextBothPort" checked={schItem.isTextBothPort} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                            {
                                schItem.isTextBothPort ?
                                    <TextField
                                        fullWidth
                                        type="number"
                                        InputProps={{ inputProps: { min: 0 } }}
                                        name="bothPortTxt"
                                        placeholder="Src or Dst Port (type: Number)"
                                        size="small"
                                        value={schItem.bothPortTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "bothPort", url: "/popup/search/http?uri=page&key=bothPort&str=Port" })
                            }
                        </Grid>

                        {/* Application */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Application</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "application", url: "/popup/search/http?uri=page&key=application&str=Application" })}
                        </Grid>

                        {/* Transaction */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Transaction</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "transaction", url: "/popup/search/http?uri=page&key=transaction&str=Transaction" })}
                        </Grid>

                        {/* Host */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Host</Grid>
                        <Grid item sm={.7}>
                            <FormControlLabel control={<Checkbox name="isTextHost" checked={schItem.isTextHost} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                        </Grid>
                        <Grid item sm={9.8}>
                            {
                                schItem.isTextHost ?
                                    <TextField
                                        fullWidth
                                        name="hostTxt"
                                        placeholder="Host"
                                        size="small"
                                        value={schItem.hostTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "host", url: "/popup/search/http?uri=page&key=host&str=Host" })
                            }
                        </Grid>

                        {/* Page */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Page</Grid>
                        <Grid item sm={.7}>
                            <FormControlLabel control={<Checkbox name="isTextPage" checked={schItem.isTextPage} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                        </Grid>
                        <Grid item sm={9.8}>
                            {
                                schItem.isTextPage ?
                                    <TextField
                                        fullWidth
                                        name="pageTxt"
                                        placeholder="Page"
                                        size="small"
                                        value={schItem.pageTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "page", url: "/popup/search/http?uri=page&key=page&str=Page" })
                            }
                        </Grid>

                        {/* User Agent */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">User Agent</Grid>
                        <Grid item sm={.7}>
                            <FormControlLabel control={<Checkbox name="isTextUseragent" checked={schItem.isTextUseragent} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                        </Grid>
                        <Grid item sm={9.8}>
                            {
                                schItem.isTextUseragent ?
                                    <TextField
                                        fullWidth
                                        name="useragentTxt"
                                        placeholder="User Agent"
                                        size="small"
                                        value={schItem.useragentTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "useragent", url: "/popup/search/http?uri=page&key=useragent&category=userAgent&str=User Agent" })
                            }
                        </Grid>

                        {/* Referer */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Referer</Grid>
                        <Grid item sm={.7}>
                            <FormControlLabel control={<Checkbox name="isTextReferer" checked={schItem.isTextReferer} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                        </Grid>
                        <Grid item sm={9.8}>
                            {
                                schItem.isTextReferer ?
                                    <TextField
                                        fullWidth
                                        name="refererTxt"
                                        placeholder="Referer"
                                        size="small"
                                        value={schItem.refererTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "referer", url: "/popup/search/http?uri=page&key=referer&str=Referer" })
                            }
                        </Grid>

                        {/* HTTP Response Code */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">HTTP Response Code</Grid>
                        <Grid item sm={.7}>
                            <FormControlLabel control={<Checkbox name="isTextResponseCode" checked={schItem.isTextResponseCode} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Text" />
                        </Grid>
                        <Grid item sm={9.8}>
                            {
                                schItem.isTextResponseCode ?
                                    <TextField
                                        fullWidth
                                        type="number"
                                        InputProps={{ inputProps: { min: 0 } }}
                                        name="responseCodeTxt"
                                        placeholder="HTTP Response Code (type: Number)"
                                        size="small"
                                        value={schItem.responseCodeTxt}
                                        onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                    /> :
                                    drawTypeAheadElement({ key: "responseCode", url: "/popup/search/http?uri=page&key=responseCode&category=httpResponseCode&str=HTTP Response Code" })
                            }
                        </Grid>

                        {/* HTTP Method  */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">HTTP Method</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "httpMethod", url: "/popup/search/http?uri=page&key=httpMethod&str=HTTP Method" })}
                        </Grid>

                        {/* Browser */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Browser</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "browser", url: "/popup/search/http?uri=page&key=browser&str=Browser" })}
                        </Grid>

                        {/* Hardware */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Hardware</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "hardware", url: "/popup/search/http?uri=page&key=hardware&str=Hardware" })}
                        </Grid>

                        {/* OS */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">OS</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "os", url: "/popup/search/http?uri=page&key=os&str=OS" })}
                        </Grid>

                        {/* Platform */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Platform</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "platform", url: "/popup/search/http?uri=page&key=platform&str=Platform" })}
                        </Grid>

                        {/* Protocol */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Protocol</Grid>
                        <Grid item sm={10.5}>
                            {drawTypeAheadElement({ key: "protocol", url: "/popup/search/http?uri=page&key=protocol&str=Protocol" })}
                        </Grid>

                        {/* Response Time */}
                        <Grid item sm={1.5} sx={{ marginTop: "auto" }} textAlign="right">Response Time</Grid>
                        <Grid item sm={.7}>
                            <FormControlLabel control={<Checkbox name="resTimeRangeChk" checked={schItem.resTimeRangeChk} onChange={(e) => setInputChecked(e, schItem, setSchItem)} />} label="Range" />
                        </Grid>
                        <Grid item sm={9.3}>
                            {
                                schItem.resTimeRangeChk ?
                                    <Grid container spacing={1}>
                                        <Grid item sm={2}>
                                            <TextField
                                                fullWidth
                                                type="number"
                                                InputProps={{ inputProps: { min: 0 } }}
                                                name="resTimeRangeFirstVal"
                                                placeholder="Time (type: Number)"
                                                size="small"
                                                value={schItem.resTimeRangeFirstVal}
                                                onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                            />
                                        </Grid>
                                        <Grid item sm={1} sx={{ marginTop: "auto" }}>
                                            <TextField
                                                fullWidth
                                                select
                                                name="resTimeRangeFirstUnit"
                                                size="small"
                                                value={schItem.resTimeRangeFirstUnit}
                                                onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                            >
                                                <MenuItem value="goe">이상</MenuItem>
                                                <MenuItem value="gt">초과</MenuItem>
                                            </TextField>
                                        </Grid>
                                        <Grid item sm={.5} textAlign="center" sx={{ marginTop: "auto" }}>~</Grid>
                                        <Grid item sm={2} sx={{ marginTop: "auto" }}>
                                            <TextField
                                                fullWidth
                                                type="number"
                                                InputProps={{ inputProps: { min: 0 } }}
                                                name="resTimeRangeLastVal"
                                                placeholder="Time (type: Number)"
                                                size="small"
                                                value={schItem.resTimeRangeLastVal}
                                                onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                            />
                                        </Grid>
                                        <Grid item sm={1} sx={{ marginTop: "auto" }}>
                                            <TextField
                                                fullWidth
                                                select
                                                name="resTimeRangeLastUnit"
                                                size="small"
                                                value={schItem.resTimeRangeLastUnit}
                                                onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                            >
                                                <MenuItem value="loe">이하</MenuItem>
                                                <MenuItem value="lt">미만</MenuItem>
                                            </TextField>
                                        </Grid>
                                    </Grid> :
                                    <Grid container spacing={1}>
                                        <Grid item sm={2}>
                                            <TextField
                                                fullWidth
                                                type="number"
                                                InputProps={{ inputProps: { min: 0 } }}
                                                name="resTimeSingleVal"
                                                placeholder="Time (type: Number)"
                                                size="small"
                                                value={schItem.resTimeSingleVal}
                                                onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                            />
                                        </Grid>
                                        <Grid item sm={1}>
                                            <TextField
                                                fullWidth
                                                select
                                                name="resTimeSingleUnit"
                                                size="small"
                                                sx={{ marginTop: "auto" }}
                                                value={schItem.resTimeSingleUnit}
                                                onChange={(e) => setInputValue(e, schItem, setSchItem)}
                                            >
                                                <MenuItem value="goe">이상</MenuItem>
                                                <MenuItem value="gt">초과</MenuItem>
                                                <MenuItem value="loe">이하</MenuItem>
                                                <MenuItem value="lt">미만</MenuItem>
                                            </TextField>
                                        </Grid>
                                    </Grid>
                            }
                        </Grid>
                    </Grid>
                </CardContent>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="secondary" size="small" onClick={props.onClose}>닫기</Button>
                </Box>
            </Card>
        </Modal>
    );
});

export default ModalSearchFormPages;