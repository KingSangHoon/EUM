import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { Card, CardHeader, Box, CardContent, Modal } from '@mui/material';

import axiosConf from '../../../axios';
import { modalStyles, gridApiObj } from '../../../lib/common';
import { AgGridModule } from '../../../lib/AgGridModule';

const ModalSearchFormThresholdPolicy = React.forwardRef((props, ref) => {
    const [policyData, setPolicyData] = useState([]);

    const policyColumnDefs = [{
        headerName: '정책 명',
        field: 'policyName',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '작성자',
        field: 'firstWriter',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '최근 수정자',
        field: 'lastWriter',
        cellClass: ['cursorp'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: '최근 수정 일자',
        field: 'lastUpdateDate',
        cellClass: ['cursorp', 'text-center'],
        filter: "agTextColumnFilter"
    }];

    useEffect(() => {
        getPolicyList();
    }, [props.target]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.thresholdPolicyGridApi) {
                gridApiObj.thresholdPolicyGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [props.open]);

    const getPolicyList = () => {
        axiosConf.get("/api/setting/threshold/findAll/policy/" + props.target).then(res => {
            _.forEach(res.data, (obj) => {
                obj.regDate = obj.regDate ? moment(obj.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                obj.modifyDate = obj.modifyDate ? moment(obj.modifyDate).format("YYYY-MM-DD HH:mm:ss") : "";
            });

            setPolicyData(res.data);
        });
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const onSavePolicyValidation = (params) => {
        if (params.data) {
            props.onSuccess({ target: props.target, id: params.data.id });
            props.onClose();
        }
    }

    return (
        <Modal open={props.open} onClose={props.onClose}>
            <Card sx={modalStyles}>
                <CardHeader title="레벨 정책"></CardHeader>
                <CardContent sx={{ overflowY: "auto", height: "40rem" }}>
                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                        <AgGridModule
                            gridName="thresholdPolicyGridApi"
                            columnDefs={policyColumnDefs}
                            rowData={policyData}
                            onCellClicked={onSavePolicyValidation}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>
                </CardContent>
            </Card>
        </Modal>
    );
});

export default ModalSearchFormThresholdPolicy;