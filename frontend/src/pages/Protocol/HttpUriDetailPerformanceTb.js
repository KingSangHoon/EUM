import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel } from '@mui/material';

const HttpUriDetailPerformanceTb = React.forwardRef((props, ref) => {
    const uriData = props.uriData;

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody>
                    <TableRow>
                        <TableCell><FormLabel>Response Time</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.tsRsqDelayResponse}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>Frame Arrival Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameArrival}</TableCell>
                        <TableCell width="18%"><FormLabel>Frame Landoff Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameLandoff}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Frame Arrival Page Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameArrivalPage}</TableCell>
                        <TableCell><FormLabel>Frame Landoff Page Time</FormLabel></TableCell>
                        <TableCell>{uriData.totFrameLandoffPage}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Sequence First</FormLabel></TableCell>
                        <TableCell>{uriData.reqSeqFirst}</TableCell>
                        <TableCell><FormLabel>Response Sequence First</FormLabel></TableCell>
                        <TableCell>{uriData.resSeqFirst}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request ACK First</FormLabel></TableCell>
                        <TableCell>{uriData.reqAckFirst}</TableCell>
                        <TableCell><FormLabel>Response ACK First</FormLabel></TableCell>
                        <TableCell>{uriData.resAckFirst}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Sequence Last</FormLabel></TableCell>
                        <TableCell>{uriData.reqSeqLast}</TableCell>
                        <TableCell><FormLabel>Response Sequence Last</FormLabel></TableCell>
                        <TableCell>{uriData.resSeqLast}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request ACK Last</FormLabel></TableCell>
                        <TableCell>{uriData.reqAckLast}</TableCell>
                        <TableCell><FormLabel>Response ACK Last</FormLabel></TableCell>
                        <TableCell>{uriData.resAckLast}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Pkt First Time</FormLabel></TableCell>
                        <TableCell>{uriData.totReqPktFirst}</TableCell>
                        <TableCell><FormLabel>Response Pkt First Time</FormLabel></TableCell>
                        <TableCell>{uriData.totResPktFirst}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Pkt Last Time</FormLabel></TableCell>
                        <TableCell>{uriData.totReqPktLast}</TableCell>
                        <TableCell><FormLabel>Response Pkt Last Time</FormLabel></TableCell>
                        <TableCell>{uriData.totResPktLast}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Pkt Push Time</FormLabel></TableCell>
                        <TableCell>{uriData.totReqPktPush}</TableCell>
                        <TableCell><FormLabel>Response Pkt Push Time</FormLabel></TableCell>
                        <TableCell>{uriData.totResPktPush}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Delay Transfer</FormLabel></TableCell>
                        <TableCell>{uriData.tsReqDelayTransfer}</TableCell>
                        <TableCell><FormLabel>Response Delay Transfer</FormLabel></TableCell>
                        <TableCell>{uriData.tsResDelayTransfer}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response Process First</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.tsResProcessFirst}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response Process Push</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.tsResProcessPush}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response Process Last</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.tsResProcessLast}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>RTT SYN Time</FormLabel></TableCell>
                        <TableCell>{uriData.totRttSyn}</TableCell>
                        <TableCell><FormLabel>RTT SYN ACK Time</FormLabel></TableCell>
                        <TableCell>{uriData.totRttSynAck}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>RTT Request ACK Time</FormLabel></TableCell>
                        <TableCell>{uriData.totRttReqAck}</TableCell>
                        <TableCell><FormLabel>RTT Response ACK Time</FormLabel></TableCell>
                        <TableCell>{uriData.totRttResAck}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request -&gt; Response Avg ACK RTT</FormLabel></TableCell>
                        <TableCell>{uriData.reqAvgAckRtt}</TableCell>
                        <TableCell><FormLabel>Response -&gt; Request Avg ACK RTT</FormLabel></TableCell>
                        <TableCell>{uriData.resAvgAckRtt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request -&gt; Response Avg ACK RTO</FormLabel></TableCell>
                        <TableCell>{uriData.reqAvgAckRto}</TableCell>
                        <TableCell><FormLabel>Response -&gt; Request Avg ACK RTO</FormLabel></TableCell>
                        <TableCell>{uriData.resAvgAckRto}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request -&gt; Response ACK RTT Min</FormLabel></TableCell>
                        <TableCell>{uriData.ackRttMinReq}</TableCell>
                        <TableCell><FormLabel>Response -&gt; Request ACK RTT Min</FormLabel></TableCell>
                        <TableCell>{uriData.ackRttMinRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request -&gt; Response ACK RTT Max</FormLabel></TableCell>
                        <TableCell>{uriData.ackRttMaxReq}</TableCell>
                        <TableCell><FormLabel>Response -&gt; Request ACK RTT Max</FormLabel></TableCell>
                        <TableCell>{uriData.ackRttMaxRes}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpUriDetailPerformanceTb;