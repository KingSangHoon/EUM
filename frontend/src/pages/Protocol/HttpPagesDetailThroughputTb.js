import React from 'react';
import { TableContainer, Table, TableRow, TableCell, TableBody, Paper, FormLabel } from '@mui/material';

const HttpPagesDetailThroughputTb = React.forwardRef((props, ref) => {
    const pageData = props.pageData;

    return (
        <TableContainer component={Paper} sx={{ maxHeight: "100%" }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableBody>
                    <TableRow>
                        <TableCell width="18%"><FormLabel>Request Packet Length</FormLabel></TableCell>
                        <TableCell>{pageData.pagePktLenReq}</TableCell>
                        <TableCell width="18%"><FormLabel>Response Packet Length</FormLabel></TableCell>
                        <TableCell>{pageData.pagePktLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request Packet Count</FormLabel></TableCell>
                        <TableCell>{pageData.pagePktCntReq}</TableCell>
                        <TableCell><FormLabel>Response Packet Count</FormLabel></TableCell>
                        <TableCell>{pageData.pagePktCntRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Length</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpLenReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Length</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpLenRes}</TableCell>
                    </TableRow>
                    <TableRow>
                        <TableCell><FormLabel>Request HTTP Count</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpCntReq}</TableCell>
                        <TableCell><FormLabel>Response HTTP Count</FormLabel></TableCell>
                        <TableCell>{pageData.pageHttpCntRes}</TableCell>
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

export default HttpPagesDetailThroughputTb;