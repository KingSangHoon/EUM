import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel } from '@mui/material';

const HttpPagesDetailAvailabilityTb = React.forwardRef((props, ref) => {
    const pageData = props.pageData;

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody>
                    <TableRow>
                        <TableCell><FormLabel>HTTP Overall Error</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.httpError} %</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>URI Count</FormLabel></TableCell>
                        <TableCell>{pageData.uriCnt}</TableCell>
                        <TableCell width="18%"><FormLabel>Session Count</FormLabel></TableCell>
                        <TableCell>{pageData.sessionCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Success URI Count</FormLabel></TableCell>
                        <TableCell>{pageData.successUriCnt}</TableCell>
                        <TableCell><FormLabel>Error URI Count</FormLabel></TableCell>
                        <TableCell>{pageData.errUriCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Stopped Transaction Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.stoppedTransactionCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response 2xx Code Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resCode2xxCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response 3xx Code Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resCode3xxCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response 4xx Code Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resCode4xxCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response 401 Code Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resCode401Cnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response 404 Code Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resCode404Cnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response 5xx Code Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resCode5xxCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Response Other Code Count</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.resCodeOthCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Connection Error Packet Count</FormLabel></TableCell>
                        <TableCell>{pageData.connErrPktCnt}</TableCell>
                        <TableCell><FormLabel>Connection Error Session Count</FormLabel></TableCell>
                        <TableCell>{pageData.connErrSessionCnt}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>TCP Overall Error</FormLabel></TableCell>
                        <TableCell colSpan={3}>{pageData.tcpError} %</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request TCP Count</FormLabel></TableCell>
                        <TableCell>{pageData.pageTcpCntReq}</TableCell>
                        <TableCell><FormLabel>Response TCP Count</FormLabel></TableCell>
                        <TableCell>{pageData.pageTcpCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request TCP Length</FormLabel></TableCell>
                        <TableCell>{pageData.pageTcpLenReq}</TableCell>
                        <TableCell><FormLabel>Response TCP Length</FormLabel></TableCell>
                        <TableCell>{pageData.pageTcpLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request ACK Lost Count</FormLabel></TableCell>
                        <TableCell>{pageData.ackLostCntReq}</TableCell>
                        <TableCell><FormLabel>Response ACK Lost Count</FormLabel></TableCell>
                        <TableCell>{pageData.ackLostCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request ACK Lost Length</FormLabel></TableCell>
                        <TableCell>{pageData.ackLostLenReq}</TableCell>
                        <TableCell><FormLabel>Response Ack Lost Length</FormLabel></TableCell>
                        <TableCell>{pageData.ackLostLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Dup ACK Count</FormLabel></TableCell>
                        <TableCell>{pageData.dupAckCntReq}</TableCell>
                        <TableCell><FormLabel>Response Dup ACK Count</FormLabel></TableCell>
                        <TableCell>{pageData.dupAckCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Dup ACK Length</FormLabel></TableCell>
                        <TableCell>{pageData.dupAckLenReq}</TableCell>
                        <TableCell><FormLabel>Response Dup ACK Length</FormLabel></TableCell>
                        <TableCell>{pageData.dupAckLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Retransmission Count</FormLabel></TableCell>
                        <TableCell>{pageData.retransmissionCntReq}</TableCell>
                        <TableCell><FormLabel>Response Retransmission Count</FormLabel></TableCell>
                        <TableCell>{pageData.retransmissionCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Retransmission Length</FormLabel></TableCell>
                        <TableCell>{pageData.retransmissionLenReq}</TableCell>
                        <TableCell><FormLabel>Response Retransmission Length</FormLabel></TableCell>
                        <TableCell>{pageData.retransmissionLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Fast Retransmission Count</FormLabel></TableCell>
                        <TableCell>{pageData.fastRetransmissionCntReq}</TableCell>
                        <TableCell><FormLabel>Response Fast Retransmission Count</FormLabel></TableCell>
                        <TableCell>{pageData.fastRetransmissionCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Fast Retransmission Length</FormLabel></TableCell>
                        <TableCell>{pageData.fastRetransmissionLenReq}</TableCell>
                        <TableCell><FormLabel>Response Fast Retransmission Length</FormLabel></TableCell>
                        <TableCell>{pageData.fastRetransmissionLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Spurious Retransmission Count</FormLabel></TableCell>
                        <TableCell>{pageData.spuriousRetransmissionCntReq}</TableCell>
                        <TableCell><FormLabel>Response Spurious Retransmission Count</FormLabel></TableCell>
                        <TableCell>{pageData.spuriousRetransmissionCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Spurious Retransmission Length</FormLabel></TableCell>
                        <TableCell>{pageData.spuriousRetransmissionLenReq}</TableCell>
                        <TableCell><FormLabel>Response Spurious Retransmission Length</FormLabel></TableCell>
                        <TableCell>{pageData.spuriousRetransmissionLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive Count</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveCntReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive Count</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive Length</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveLenReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive Length</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive ACK Count</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveAckCntReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive ACK Count</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveAckCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Keep Alive ACK Length</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveAckLenReq}</TableCell>
                        <TableCell><FormLabel>Response Keep Alive ACK Length</FormLabel></TableCell>
                        <TableCell>{pageData.keepAliveAckLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Lost Segment Count</FormLabel></TableCell>
                        <TableCell>{pageData.lostSegCntReq}</TableCell>
                        <TableCell><FormLabel>Response Lost Segment Count</FormLabel></TableCell>
                        <TableCell>{pageData.lostSegCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Lost Segment Length</FormLabel></TableCell>
                        <TableCell>{pageData.lostSegLenReq}</TableCell>
                        <TableCell><FormLabel>Response Lost Segment Length</FormLabel></TableCell>
                        <TableCell>{pageData.lostSegLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Out Of Order Count</FormLabel></TableCell>
                        <TableCell>{pageData.outOfOrderCntReq}</TableCell>
                        <TableCell><FormLabel>Response Out Of Order Count</FormLabel></TableCell>
                        <TableCell>{pageData.outOfOrderCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Out Of Order Length</FormLabel></TableCell>
                        <TableCell>{pageData.outOfOrderLenReq}</TableCell>
                        <TableCell><FormLabel>Response Out Of Order Length</FormLabel></TableCell>
                        <TableCell>{pageData.outOfOrderLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Count</FormLabel></TableCell>
                        <TableCell>{pageData.overlapCntReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Count</FormLabel></TableCell>
                        <TableCell>{pageData.overlapCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Length</FormLabel></TableCell>
                        <TableCell>{pageData.overlapLenReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Length</FormLabel></TableCell>
                        <TableCell>{pageData.overlapLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Attack Count</FormLabel></TableCell>
                        <TableCell>{pageData.overlapAttackCntReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Attack Count</FormLabel></TableCell>
                        <TableCell>{pageData.overlapAttackCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Overlap Attack Length</FormLabel></TableCell>
                        <TableCell>{pageData.overlapAttackLenReq}</TableCell>
                        <TableCell><FormLabel>Response Overlap Attack Length</FormLabel></TableCell>
                        <TableCell>{pageData.overlapAttackLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Window Update Count</FormLabel></TableCell>
                        <TableCell>{pageData.winUpdateCntReq}</TableCell>
                        <TableCell><FormLabel>Response Window Update Count</FormLabel></TableCell>
                        <TableCell>{pageData.winUpdateCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Window Update Length</FormLabel></TableCell>
                        <TableCell>{pageData.winUpdateLenReq}</TableCell>
                        <TableCell><FormLabel>Response Window Update Length</FormLabel></TableCell>
                        <TableCell>{pageData.winUpdateLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Count</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinCntReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Count</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Length</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinLenReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Length</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe Count</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeCntReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe Count</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe Length</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeLenReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe Length</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe ACK Count</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeAckCntReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe ACK Count</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeAckCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Zero Window Probe ACK Length</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeAckLenReq}</TableCell>
                        <TableCell><FormLabel>Response Zero Window Probe ACK Length</FormLabel></TableCell>
                        <TableCell>{pageData.zeroWinProbeAckLenRes}</TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    )
})

export default HttpPagesDetailAvailabilityTb;