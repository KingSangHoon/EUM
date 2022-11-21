import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel } from '@mui/material';

const HttpUriDetailThroughputTb = React.forwardRef((props, ref) => {
    const uriData = props.uriData;

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>Request Packet Length</FormLabel></TableCell>
                        <TableCell>{uriData.pktLenReq}</TableCell>
                        <TableCell width="18%"><FormLabel>Response Packet Length</FormLabel></TableCell>
                        <TableCell>{uriData.pktLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Packet Count</FormLabel></TableCell>
                        <TableCell>{uriData.pktCntReq}</TableCell>
                        <TableCell><FormLabel>Response Packet Count</FormLabel></TableCell>
                        <TableCell>{uriData.pktCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Length</FormLabel></TableCell>
                        <TableCell>{uriData.httpLenReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Length</FormLabel></TableCell>
                        <TableCell>{uriData.httpLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Count</FormLabel></TableCell>
                        <TableCell>{uriData.httpCntReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Count</FormLabel></TableCell>
                        <TableCell>{uriData.httpCntRes}</TableCell>
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

export default HttpUriDetailThroughputTb;