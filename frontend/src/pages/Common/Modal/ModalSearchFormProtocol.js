import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { Card, CardHeader, Box, Button, CardContent, Modal } from '@mui/material';

import { gridApiObj, modalStyles } from '../../../lib/common';
import { AgGridModule } from "../../../lib/AgGridModule";

const ModalSearchFormProtocol = React.forwardRef((props, ref) => {
    const [selectResourceObj, setSelectResourceObj] = useState([]);

    useEffect(() => {
        if (props.open) {
            setTimeout(() => {
                handleResize();
            }, 200);
        }
    }, [props.open]);

    useEffect(() => {
        setSelectResourceObj(props.selectResource);
    }, [props.selectResource]);

    const httpDetailColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererParams: {
            checkbox: true
        },
        checkboxSelection: true,
        lockPosition: true
    },
    {
        headerName: 'Protocol',
        field: 'ndpiProtocolName',
        filter: "agTextColumnFilter"
    }];

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const onCellProtocolTypeClick = (params) => {
        if (params.data) {
            setSelectResourceObj(params.data);
        }
    }

    const onSavePolicyValidation = () => {
        props.onSuccess(selectResourceObj);
        props.onClose();
    }

    return (
        <Modal open={props.open} onClose={props.onClose}>
            <Card sx={{ ...modalStyles, maxWidth: "800px" }}>
                <CardHeader title="Protocol"></CardHeader>
                <CardContent>
                    <Box component="div" className="ag-theme-alpine" sx={{ height: 430 }} onContextMenu={(e) => e.preventDefault()}>
                        <AgGridModule
                            gridName="protocolGridApi"
                            columnDefs={httpDetailColumnDefs}
                            rowData={props.resourceItem}
                            rowSelection={'single'}
                            onCellClicked={onCellProtocolTypeClick}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>
                </CardContent>

                <Box component="div" textAlign="center" sx={{ mt: 1, pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={props.onClose}>닫기</Button>
                </Box>
            </Card>
        </Modal>
    );
});

export default ModalSearchFormProtocol;