import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel, Badge } from '@mui/material';

const HttpPagesDetailHttpTb = React.forwardRef((props, ref) => {
    const pageData = props.pageData;
    const getUseragentIcon = props.getUseragentIcon;
    const getCountryIcon = props.getCountryIcon;

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody
                    sx={{
                        '& .MuiBadge-badge': { width: "3rem" }
                    }}>
                    <TableRow>
                        <TableCell><FormLabel>Page</FormLabel></TableCell>
                        <TableCell colSpan={3}>
                            {
                                pageData.httpResCode < 300 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-success" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                    pageData.httpResCode >= 300 && pageData.httpResCode < 400 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-info" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                        pageData.httpResCode >= 400 && pageData.httpResCode < 500 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-danger" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                            pageData.httpResCode >= 500 ? <Badge badgeContent={pageData.httpResCode} classes={{ badge: "badge-warning" }} max={999} sx={{ ml: 3, mr: 4 }} /> : ""
                            }
                            {pageData.page}
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>HTTP Method</FormLabel></TableCell>
                        <TableCell>{pageData.httpMethod}</TableCell>
                        <TableCell width="18%"><FormLabel>HTTP Response Phrase</FormLabel></TableCell>
                        <TableCell>{pageData.httpResPhrase}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Frame Arrival Time</FormLabel></TableCell>
                        <TableCell>{pageData.totFrameArrival}</TableCell>
                        <TableCell><FormLabel>Frame Landoff Time</FormLabel></TableCell>
                        <TableCell>{pageData.totFrameLandoff}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>SRC IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(pageData.countryIdReq)} {pageData.formatSrcIpGeo}</TableCell>
                        <TableCell><FormLabel>DST IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(pageData.countryIdRes)} {pageData.formatDstIpGeo}</TableCell>
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
                        <TableCell><FormLabel>SRC MAC</FormLabel></TableCell>
                        <TableCell>{pageData.srcMac}</TableCell>
                        <TableCell><FormLabel>DST MAC</FormLabel></TableCell>
                        <TableCell>{pageData.dstMac}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>DPI Protocol APP</FormLabel></TableCell>
                        <TableCell>{pageData.ndpiProtocolApp}</TableCell>
                        <TableCell><FormLabel>DPI Protocol Master</FormLabel></TableCell>
                        <TableCell>{pageData.ndpiProtocolMaster}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Count</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpCntReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Count</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Length</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpLenReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Length</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Content Length</FormLabel></TableCell>
                        <TableCell>{pageData.httpContentLength}</TableCell>
                        <TableCell><FormLabel>HTTP Content Type</FormLabel></TableCell>
                        <TableCell>{pageData.httpContentType}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Host</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpHost}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Cookie</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpCookie}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Referer</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpReferer}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Location</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpLocation}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Version</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpVersion}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Version</FormLabel></TableCell>
                        <TableCell>{pageData.httpVersionReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Version</FormLabel></TableCell>
                        <TableCell>{pageData.httpVersionRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP User Agent</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpUserAgent}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Software Name</FormLabel></TableCell>
                        <TableCell colSpan={3}>{getUseragentIcon("software", pageData.userAgentSoftwareName)} {pageData.userAgentSoftwareName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Software Version</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentSoftwareVersion}</TableCell>
                        <TableCell><FormLabel>User Agent Software Version Full</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentSoftwareVersionFull}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Software Type</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentSoftwareType}</TableCell>
                        <TableCell><FormLabel>User Agent Software Sub Type</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentSoftwareSubType}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Hardware Type</FormLabel></TableCell>
                        <TableCell>{getUseragentIcon("hardware", pageData.userAgentHardwareType)} {pageData.userAgentHardwareType}</TableCell>
                        <TableCell><FormLabel>User Agent Hardware Sub Type</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentHardwareSubType}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent OS Name</FormLabel></TableCell>
                        <TableCell colSpan={3}>{getUseragentIcon("os", pageData.userAgentOperatingSystemName)} {pageData.userAgentOperatingSystemName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent OS Version</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentOperatingSystemVersion}</TableCell>
                        <TableCell><FormLabel>User Agent OS Version Full</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentOperatingSystemVersionFull}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent OS Flavour</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.userAgentOperatingSystemFlavour}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Operating Platform</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentOperatingPlatform}</TableCell>
                        <TableCell><FormLabel>User Agent Operating Platform Vendor Name</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentOperatingPlatformVendorName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Operating Platform Code</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentOperatingPlatformCode}</TableCell>
                        <TableCell><FormLabel>User Agent Operating Platform Code Name</FormLabel></TableCell>
                        <TableCell>{pageData.userAgentOperatingPlatformCodeName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Layout Engine Name</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.userAgentLayoutEngineName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 1</FormLabel></TableCell>
                        <TableCell>{pageData.argument1Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 1</FormLabel></TableCell>
                        <TableCell>{pageData.argument1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 2</FormLabel></TableCell>
                        <TableCell>{pageData.argument2Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 2</FormLabel></TableCell>
                        <TableCell>{pageData.argument2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 3</FormLabel></TableCell>
                        <TableCell>{pageData.argument3Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 3</FormLabel></TableCell>
                        <TableCell>{pageData.argument3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 4</FormLabel></TableCell>
                        <TableCell>{pageData.argument4Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 4</FormLabel></TableCell>
                        <TableCell>{pageData.argument4Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 1</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader1Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 1</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 2</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader2Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 2</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 3</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader3Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 3</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 4</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader4Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 4</FormLabel></TableCell>
                        <TableCell>{pageData.requestHeader4Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 1</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody1Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 1</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 2</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody2Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 2</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 3</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody3Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 3</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 4</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody4Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 4</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody4Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 5</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody5Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 5</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody5Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 6</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody6Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 6</FormLabel></TableCell>
                        <TableCell>{pageData.requestBody6Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 1</FormLabel></TableCell>
                        <TableCell>{pageData.cookie1Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 1</FormLabel></TableCell>
                        <TableCell>{pageData.cookie1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 2</FormLabel></TableCell>
                        <TableCell>{pageData.cookie2Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 2</FormLabel></TableCell>
                        <TableCell>{pageData.cookie2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 3</FormLabel></TableCell>
                        <TableCell>{pageData.cookie3Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 3</FormLabel></TableCell>
                        <TableCell>{pageData.cookie3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 4</FormLabel></TableCell>
                        <TableCell>{pageData.cookie4Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 4</FormLabel></TableCell>
                        <TableCell>{pageData.cookie4Value}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpPagesDetailHttpTb;