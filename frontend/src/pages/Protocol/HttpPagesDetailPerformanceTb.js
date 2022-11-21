import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel } from '@mui/material';

const HttpPagesDetailPerformanceTb = React.forwardRef((props, ref) => {
    const pageData = props.pageData;

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>Frame Arrival Time</FormLabel></TableCell>
                        <TableCell>{pageData.totFrameArrival}</TableCell>
                        <TableCell width="18%"><FormLabel>Frame Landoff Time</FormLabel></TableCell>
                        <TableCell>{pageData.totFrameLandoff}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Begin Time</FormLabel></TableCell>
                        <TableCell>{pageData.totPageBegin}</TableCell>
                        <TableCell><FormLabel>Page End Time</FormLabel></TableCell>
                        <TableCell>{pageData.totPageEnd}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Response Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.tsPage}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Response Init Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.tsPageResInit}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Response Init Gap Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.tsPageResInitGap}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Response Gap Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.tsPageResGap}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request Transfer Time</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageTransferReq}</TableCell>
                        <TableCell><FormLabel>Page Response Transfer Time</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageTransferRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request Transfer Gap Time</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageTransferReqGap}</TableCell>
                        <TableCell><FormLabel>Page Response Transfer Gap Time</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageTransferResGap}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request SYN Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.totPageReqSyn}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request -&gt; Response Connection RTT</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.reqAvgConnRtt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response -&gt; Request Connection RTT</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resAvgConnRtt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request -&gt; Response ACK RTT</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.reqAvgAckRtt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response -&gt; Request ACK RTT</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resAvgAckRtt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Making Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.tsPageReqMakingSum}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request RTO Count</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRtoCntReq}</TableCell>
                        <TableCell><FormLabel>Page Response RTO Count</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRtoCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request RTO Sum</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRtoSumReq}</TableCell>
                        <TableCell><FormLabel>Page Response RTO Sum</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRtoSumRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request ACK RTT Min</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttAckReqMin}</TableCell>
                        <TableCell><FormLabel>Page Response ACK RTT Min</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttAckResMin}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request ACK RTT Max</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttAckReqMax}</TableCell>
                        <TableCell><FormLabel>Page Response ACK RTT Max</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttAckResMax}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request ACK RTT Sum</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttAckSumReq}</TableCell>
                        <TableCell><FormLabel>Page Response ACK RTT Sum</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttAckSumRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Page Request Connection RTT Sum</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttConnSumReq}</TableCell>
                        <TableCell><FormLabel>Page Response Connection RTT Sum</FormLabel></TableCell>
                        <TableCell>{pageData.tsPageRttConnSumRes}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpPagesDetailPerformanceTb;