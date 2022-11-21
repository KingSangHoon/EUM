import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel, Badge, Typography } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowUpRightFromSquare } from "@fortawesome/free-solid-svg-icons";

const staticLevelCircle = { 1: "green", 2: "yellow", 3: "orange", 4: "redorange", 5: "red" };

const HttpPagesDetailSummaryTb = React.forwardRef((props, ref) => {
    const pageData = props.pageData;
    const getUseragentIcon = props.getUseragentIcon;
    const getCountryIcon = props.getCountryIcon;

    const moveToPage = () => {
        const httpPage = 'http://' + pageData.httpHost + pageData.page;
        window.open(httpPage, '_blank');
    }

    return (
        <TableContainer component={Paper} sx={{ height: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody
                    sx={{
                        '& .MuiBadge-badge': { width: "3rem" }
                    }}>
                    <TableRow>
                        <TableCell><FormLabel>Page</FormLabel></TableCell>
                        <TableCell colSpan={3}>
                            {staticLevelCircle[pageData.level] ? <Typography component="i" mr={1} className={"fa fa-circle font-" + staticLevelCircle[pageData.level]} /> : ""}
                            {
                                pageData.httpResCode < 300 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-success" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                    pageData.httpResCode >= 300 && pageData.httpResCode < 400 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-info" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                        pageData.httpResCode >= 400 && pageData.httpResCode < 500 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-danger" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                            pageData.httpResCode >= 500 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-warning" }} max={999} sx={{ ml: 3, mr: 4 }} /> : ""
                            }
                            {getUseragentIcon("software", pageData.userAgentSoftwareName)}
                            {getUseragentIcon("os", pageData.userAgentOperatingSystemName)}
                            {pageData.httpHost}{pageData.page}
                            <FontAwesomeIcon icon={faArrowUpRightFromSquare} className="font-blue cursorp" style={{ marginLeft: ".5rem" }} onClick={moveToPage} />
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell width="20%"><FormLabel>SRC IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(pageData.countryIdReq)} {pageData.formatSrcIpGeo}</TableCell>
                        <TableCell width="20%"><FormLabel>DST IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(pageData.countryIdRes)} {pageData.formatDstIpGeo}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Response Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.tsPage}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Frame Arrival Time</FormLabel></TableCell>
                        <TableCell>{pageData.totFrameArrival}</TableCell>
                        <TableCell><FormLabel>Frame Landoff Time</FormLabel></TableCell>
                        <TableCell>{pageData.totFrameLandoff}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>SRC ISP</FormLabel></TableCell>
                        <TableCell>{pageData.ispNameReq}</TableCell>
                        <TableCell><FormLabel>DST ISP</FormLabel></TableCell>
                        <TableCell>{pageData.ispNameRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>SRC IDC</FormLabel></TableCell>
                        <TableCell>{pageData.idcNameReq}</TableCell>
                        <TableCell><FormLabel>DST IDC</FormLabel></TableCell>
                        <TableCell>{pageData.idcNameRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>URI Count</FormLabel></TableCell>
                        <TableCell>{pageData.uriCnt}</TableCell>
                        <TableCell><FormLabel>Session Count</FormLabel></TableCell>
                        <TableCell>{pageData.sessionCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Success/Error URI Count</FormLabel></TableCell>
                        <TableCell>{pageData.successUriCnt}/{pageData.errUriCnt}</TableCell>
                        <TableCell><FormLabel>Stopped Transaction Count</FormLabel></TableCell>
                        <TableCell>{pageData.stoppedTransactionCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Connection Error Pkt Count</FormLabel></TableCell>
                        <TableCell>{pageData.connErrPktCnt}</TableCell>
                        <TableCell><FormLabel>Connection Error Session Count</FormLabel></TableCell>
                        <TableCell>{pageData.connErrSessionCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Method</FormLabel></TableCell>
                        <TableCell>{pageData.httpMethod}</TableCell>
                        <TableCell><FormLabel>HTTP Response Phrase</FormLabel></TableCell>
                        <TableCell>{pageData.httpResPhrase}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Referer</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpReferer}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP User Agent</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpUserAgent}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>DPI Protocol APP</FormLabel></TableCell>
                        <TableCell>{pageData.ndpiProtocolApp}</TableCell>
                        <TableCell><FormLabel>DPI Protocol Master</FormLabel></TableCell>
                        <TableCell>{pageData.ndpiProtocolMaster}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Mbps</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.mbps}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpPagesDetailSummaryTb;