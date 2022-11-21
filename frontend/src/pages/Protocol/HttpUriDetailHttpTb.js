import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel, Badge } from '@mui/material';

const HttpUriDetailHttpTb = React.forwardRef((props, ref) => {
    const uriData = props.uriData;
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
                        <TableCell><FormLabel>URI</FormLabel></TableCell>
                        <TableCell colSpan={3}>
                            {
                                uriData.httpResCode < 300 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-success" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                    uriData.httpResCode >= 300 && uriData.httpResCode < 400 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-info" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                        uriData.httpResCode >= 400 && uriData.httpResCode < 500 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-danger" }} max={999} sx={{ ml: 3, mr: 4 }} /> :
                                            uriData.httpResCode >= 500 ? <Badge badgeContent={uriData.httpResCode} classes={{ badge: "badge-warning" }} max={999} sx={{ ml: 3, mr: 4 }} /> : ""
                            }
                            {uriData.page}
                        </TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>HTTP Method</FormLabel></TableCell>
                        <TableCell>{uriData.httpMethod}</TableCell>
                        <TableCell width="18%"><FormLabel>HTTP Response Phrase</FormLabel></TableCell>
                        <TableCell>{uriData.httpResPhrase}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Frame Arrival Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameArrival}</TableCell>
                        <TableCell><FormLabel>Frame Landoff Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameLandoff}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>SRC IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(uriData.countryIdReq)} {uriData.formatSrcIpGeo}</TableCell>
                        <TableCell><FormLabel>DST IP</FormLabel></TableCell>
                        <TableCell>{getCountryIcon(uriData.countryIdRes)} {uriData.formatDstIpGeo}</TableCell>
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
                        <TableCell><FormLabel>SRC MAC</FormLabel></TableCell>
                        <TableCell>{uriData.srcMac}</TableCell>
                        <TableCell><FormLabel>DST MAC</FormLabel></TableCell>
                        <TableCell>{uriData.dstMac}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>DPI Protocol APP</FormLabel></TableCell>
                        <TableCell>{uriData.ndpiProtocolApp}</TableCell>
                        <TableCell><FormLabel>DPI Protocol Master</FormLabel></TableCell>
                        <TableCell>{uriData.ndpiProtocolMaster}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Count</FormLabel></TableCell>
                        <TableCell>{uriData.httpCntReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Count</FormLabel></TableCell>
                        <TableCell>{uriData.httpCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Length</FormLabel></TableCell>
                        <TableCell>{uriData.httpLenReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Length</FormLabel></TableCell>
                        <TableCell>{uriData.httpLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Content Length</FormLabel></TableCell>
                        <TableCell>{uriData.httpContentLength}</TableCell>
                        <TableCell><FormLabel>HTTP Content Type</FormLabel></TableCell>
                        <TableCell>{uriData.httpContentType}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Host</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpHost}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Cookie</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpCookie}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Referer</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpReferer}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Location</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpLocation}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Version</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpVersion}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Version</FormLabel></TableCell>
                        <TableCell>{uriData.httpVersionReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Version</FormLabel></TableCell>
                        <TableCell>{uriData.httpVersionRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>HTTP User Agent</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.httpUserAgent}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Software Name</FormLabel></TableCell>
                        <TableCell colSpan={3}>{getUseragentIcon("software", uriData.userAgentSoftwareName)} {uriData.userAgentSoftwareName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Software Version</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentSoftwareVersion}</TableCell>
                        <TableCell><FormLabel>User Agent Software Version Full</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentSoftwareVersionFull}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Software Type</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentSoftwareType}</TableCell>
                        <TableCell><FormLabel>User Agent Software Sub Type</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentSoftwareSubType}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Hardware Type</FormLabel></TableCell>
                        <TableCell>{getUseragentIcon("hardware", uriData.userAgentHardwareType)} {uriData.userAgentHardwareType}</TableCell>
                        <TableCell><FormLabel>User Agent Hardware Sub Type</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentHardwareSubType}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent OS Name</FormLabel></TableCell>
                        <TableCell colSpan={3}>{getUseragentIcon("os", uriData.userAgentOperatingSystemName)} {uriData.userAgentOperatingSystemName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent OS Version</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentOperatingSystemVersion}</TableCell>
                        <TableCell><FormLabel>User Agent OS Version Full</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentOperatingSystemVersionFull}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent OS Flavour</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.userAgentOperatingSystemFlavour}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Operating Platform</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentOperatingPlatform}</TableCell>
                        <TableCell><FormLabel>User Agent Operating Platform Vendor Name</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentOperatingPlatformVendorName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Operating Platform Code</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentOperatingPlatformCode}</TableCell>
                        <TableCell><FormLabel>User Agent Operating Platform Code Name</FormLabel></TableCell>
                        <TableCell>{uriData.userAgentOperatingPlatformCodeName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>User Agent Layout Engine Name</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.userAgentLayoutEngineName}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 1</FormLabel></TableCell>
                        <TableCell>{uriData.argument1Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 1</FormLabel></TableCell>
                        <TableCell>{uriData.argument1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 2</FormLabel></TableCell>
                        <TableCell>{uriData.argument2Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 2</FormLabel></TableCell>
                        <TableCell>{uriData.argument2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 3</FormLabel></TableCell>
                        <TableCell>{uriData.argument3Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 3</FormLabel></TableCell>
                        <TableCell>{uriData.argument3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Argument Key 4</FormLabel></TableCell>
                        <TableCell>{uriData.argument4Key}</TableCell>
                        <TableCell><FormLabel>Argument Value 4</FormLabel></TableCell>
                        <TableCell>{uriData.argument4Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 1</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader1Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 1</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 2</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader2Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 2</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 3</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader3Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 3</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Header Key 4</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader4Key}</TableCell>
                        <TableCell><FormLabel>Request Header Value 4</FormLabel></TableCell>
                        <TableCell>{uriData.requestHeader4Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 1</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody1Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 1</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 2</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody2Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 2</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 3</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody3Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 3</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 4</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody4Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 4</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody4Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 5</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody5Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 5</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody5Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Body Key 6</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody6Key}</TableCell>
                        <TableCell><FormLabel>Request Body Value 6</FormLabel></TableCell>
                        <TableCell>{uriData.requestBody6Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 1</FormLabel></TableCell>
                        <TableCell>{uriData.cookie1Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 1</FormLabel></TableCell>
                        <TableCell>{uriData.cookie1Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 2</FormLabel></TableCell>
                        <TableCell>{uriData.cookie2Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 2</FormLabel></TableCell>
                        <TableCell>{uriData.cookie2Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 3</FormLabel></TableCell>
                        <TableCell>{uriData.cookie3Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 3</FormLabel></TableCell>
                        <TableCell>{uriData.cookie3Value}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Cookie Key 4</FormLabel></TableCell>
                        <TableCell>{uriData.cookie4Key}</TableCell>
                        <TableCell><FormLabel>Cookie Value 4</FormLabel></TableCell>
                        <TableCell>{uriData.cookie4Value}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpUriDetailHttpTb;