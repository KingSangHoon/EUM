import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel } from '@mui/material';

const HttpUriDetailAvailabilityTb = React.forwardRef((props, ref) => {
    const uriData = props.uriData;

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody>
                    <TableRow>
                        <TableCell><FormLabel>TCP Overall Error</FormLabel></TableCell>
                        <TableCell colSpan={3}>{uriData.tcpError} %</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>Request TCP Count</FormLabel></TableCell>
                        <TableCell>{uriData.tcpCntReq}</TableCell>
                        <TableCell width="18%"><FormLabel>Response TCP Count</FormLabel></TableCell>
                        <TableCell>{uriData.tcpCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request TCP Length</FormLabel></TableCell>
                        <TableCell>{uriData.tcpLenReq}</TableCell>
                        <TableCell><FormLabel>Response TCP Length</FormLabel></TableCell>
                        <TableCell>{uriData.tcpLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request ACK Lost Count</FormLabel></TableCell>
                        <TableCell>{uriData.ackLostCntReq}</TableCell>
                        <TableCell><FormLabel>Response ACK Lost Count</FormLabel></TableCell>
                        <TableCell>{uriData.ackLostCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request ACK Lost Length</FormLabel></TableCell>
                        <TableCell>{uriData.ackLostLenReq}</TableCell>
                        <TableCell><FormLabel>Response Ack Lost Length</FormLabel></TableCell>
                        <TableCell>{uriData.ackLostLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Dup ACK Count</FormLabel></TableCell>
                        <TableCell>{uriData.dupAckCntReq}</TableCell>
                        <TableCell><FormLabel>Response Dup ACK Count</FormLabel></TableCell>
                        <TableCell>{uriData.dupAckCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Dup ACK Length</FormLabel></TableCell>
                        <TableCell>{uriData.dupAckLenReq}</TableCell>
                        <TableCell><FormLabel>Response Dup ACK Length</FormLabel></TableCell>
                        <TableCell>{uriData.dupAckLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Retransmission Count</FormLabel></TableCell>
                        <TableCell>{uriData.retransmissionCntReq}</TableCell>
                        <TableCell><FormLabel>Response Retransmission Count</FormLabel></TableCell>
                        <TableCell>{uriData.retransmissionCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Retransmission Length</FormLabel></TableCell>
                        <TableCell>{uriData.retransmissionLenReq}</TableCell>
                        <TableCell><FormLabel>Response Retransmission Length</FormLabel></TableCell>
                        <TableCell>{uriData.retransmissionLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Fast Retransmission Count</FormLabel></TableCell>
                        <TableCell>{uriData.fastRetransmissionCntReq}</TableCell>
                        <TableCell><FormLabel>Response Fast Retransmission Count</FormLabel></TableCell>
                        <TableCell>{uriData.fastRetransmissionCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Fast Retransmission Length</FormLabel></TableCell>
                        <TableCell>{uriData.fastRetransmissionLenReq}</TableCell>
                        <TableCell><FormLabel>Response Fast Retransmission Length</FormLabel></TableCell>
                        <TableCell>{uriData.fastRetransmissionLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Spurious Retransmission Count</FormLabel></TableCell>
                        <TableCell>{uriData.spuriousRetransmissionCntReq}</TableCell>
                        <TableCell><FormLabel>Response Spurious Retransmission Count</FormLabel></TableCell>
                        <TableCell>{uriData.spuriousRetransmissionCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Spurious Retransmission Length</FormLabel></TableCell>
                        <TableCell>{uriData.spuriousRetransmissionLenReq}</TableCell>
                        <TableCell><FormLabel>Response Spurious Retransmission Length</FormLabel></TableCell>
                        <TableCell>{uriData.spuriousRetransmissionLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive Count</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveCntReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive Count</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive Length</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveLenReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive Length</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive ACK Count</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveAckCntReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive ACK Count</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveAckCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive ACK Length</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveAckLenReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive ACK Length</FormLabel></TableCell>
                        <TableCell>{uriData.keepAliveAckLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Lost Segment Count</FormLabel></TableCell>
                        <TableCell>{uriData.lostSegCntReq}</TableCell>
                        <TableCell><FormLabel>Response Lost Segment Count</FormLabel></TableCell>
                        <TableCell>{uriData.lostSegCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Lost Segment Length</FormLabel></TableCell>
                        <TableCell>{uriData.lostSegLenReq}</TableCell>
                        <TableCell><FormLabel>Response Lost Segment Length</FormLabel></TableCell>
                        <TableCell>{uriData.lostSegLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Out Of Order Count</FormLabel></TableCell>
                        <TableCell>{uriData.outOfOrderCntReq}</TableCell>
                        <TableCell><FormLabel>Response Out Of Order Count</FormLabel></TableCell>
                        <TableCell>{uriData.outOfOrderCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Out Of Order Length</FormLabel></TableCell>
                        <TableCell>{uriData.outOfOrderLenReq}</TableCell>
                        <TableCell><FormLabel>Response Out Of Order Length</FormLabel></TableCell>
                        <TableCell>{uriData.outOfOrderLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Count</FormLabel></TableCell>
                        <TableCell>{uriData.overlapCntReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Count</FormLabel></TableCell>
                        <TableCell>{uriData.overlapCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Length</FormLabel></TableCell>
                        <TableCell>{uriData.overlapLenReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Length</FormLabel></TableCell>
                        <TableCell>{uriData.overlapLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Attack Count</FormLabel></TableCell>
                        <TableCell>{uriData.overlapAttackCntReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Attack Count</FormLabel></TableCell>
                        <TableCell>{uriData.overlapAttackCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Attack Length</FormLabel></TableCell>
                        <TableCell>{uriData.overlapAttackLenReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Attack Length</FormLabel></TableCell>
                        <TableCell>{uriData.overlapAttackLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Window Update Count</FormLabel></TableCell>
                        <TableCell>{uriData.winUpdateCntReq}</TableCell>
                        <TableCell><FormLabel>Response Window Update Count</FormLabel></TableCell>
                        <TableCell>{uriData.winUpdateCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Window Update Length</FormLabel></TableCell>
                        <TableCell>{uriData.winUpdateLenReq}</TableCell>
                        <TableCell><FormLabel>Response Window Update Length</FormLabel></TableCell>
                        <TableCell>{uriData.winUpdateLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Count</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinCntReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Count</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Length</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinLenReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Length</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe Count</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeCntReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe Count</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe Length</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeLenReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe Length</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe ACK Count</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeAckCntReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe ACK Count</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeAckCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe ACK Length</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeAckLenReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe ACK Length</FormLabel></TableCell>
                        <TableCell>{uriData.zeroWinProbeAckLenRes}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpUriDetailAvailabilityTb;