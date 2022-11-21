import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel, Badge, Typography } from '@mui/material';

const staticLevelCircle = { 1: "green", 2: "yellow", 3: "orange", 4: "redorange", 5: "red" };

const HttpUriDetailSummaryTb = React.forwardRef((props, ref) => {
    const uriData = props.uriData;
    const getUseragentIcon = props.getUseragentIcon;
    const getCountryIcon = props.getCountryIcon;

    return (
        <TableContainer component={Paper} sx={{ height: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody
                    sx={{
                        '& .MuiBadge-badge': { width: "3rem" }
                    }}>
                    <TableRow>
                        <TableCell><FormLabel>URI</FormLabel></TableCell>
                        <TableCell colSpan={3}>
                            {staticLevelCircle[uriData.level] ? <Typography component="i" mr={1} className={"fa fa-circle font-" + staticLevelCircle[uriData.level]} /> : ""}
                            {
                                uriData.httpResCode < 300 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-success" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                    uriData.httpResCode >= 300 && uriData.httpResCode < 400 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-info" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                        uriData.httpResCode >= 400 && uriData.httpResCode < 500 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-danger" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                            uriData.httpResCode >= 500 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-warning" }} max={999} sx={{ ml: 3, mr: 4 }} /> : ""
                            }
                            {getUseragentIcon("software", uriData.userAgentSoftwareName)}
                            {getUseragentIcon("os", uriData.userAgentOperatingSystemName)}
                            {uriData.page}
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell width="20%"><FormLabel>SRC IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(uriData.countryIdReq)} {uriData.formatSrcIpGeo}</TableCell>
                        <TableCell width="20%"><FormLabel>DST IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(uriData.countryIdRes)} {uriData.formatDstIpGeo}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.tsRsqDelayResponse}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Frame Arrival Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameArrival}</TableCell>
                        <TableCell><FormLabel>Frame Landoff Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameLandoff}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Frame Arrival Page Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameArrivalPage}</TableCell>
                        <TableCell><FormLabel>Frame Landoff Page Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameLandoffPage}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>SRC ISP</FormLabel></TableCell>
                        <TableCell>{uriData.ispNameReq}</TableCell>
                        <TableCell><FormLabel>DST ISP</FormLabel></TableCell>
                        <TableCell>{uriData.ispNameRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>SRC IDC</FormLabel></TableCell>
                        <TableCell>{uriData.idcNameReq}</TableCell>
                        <TableCell><FormLabel>DST IDC</FormLabel></TableCell>
                        <TableCell>{uriData.idcNameRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Method</FormLabel></TableCell>
                        <TableCell>{uriData.httpMethod}</TableCell>
                        <TableCell><FormLabel>HTTP Response Phrase</FormLabel></TableCell>
                        <TableCell>{uriData.httpResPhrase}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Host</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpHost}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Referer</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpReferer}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP User Agent</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpUserAgent}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>DPI Protocol APP</FormLabel></TableCell>
                        <TableCell>{uriData.ndpiProtocolApp}</TableCell>
                        <TableCell><FormLabel>DPI Protocol Master</FormLabel></TableCell>
                        <TableCell>{uriData.ndpiProtocolMaster}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Mbps</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.mbps}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpUriDetailSummaryTb;