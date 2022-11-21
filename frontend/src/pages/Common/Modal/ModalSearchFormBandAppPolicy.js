import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { Card, CardHeader, Box, CardContent, Modal } from '@mui/material';

import axiosConf from '../../../axios';
import { modalStyles, gridApiObj } from '../../../lib/common';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';

const ModalSearchFormBandAppPolicy = React.forwardRef((props, ref) => {
    const [showLoader, setShowLoader] = useState(false);

    const [appPolicyData, setAppPolicyData] = useState([]);

    const policyColumnDefs = [{
        headerName: "어플리케이션 정책",
        field: 'title',
        cellRenderer: 'agGroupCellRenderer',
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "구분",
        field: 'type',
        cellClass: ['text-center', 'cursorp'],
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "IP Addr",
        field: 'ipAddr',
        cellClass: ['cursorp'],
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "MAC Addr",
        field: 'ipMac',
        cellClass: ['cursorp'],
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "TCP/UDP",
        field: 'protocol',
        cellClass: ['text-center', 'cursorp'],
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "Port Addr",
        field: 'portAddr',
        cellClass: ['cursorp'],
        filter: 'agTextColumnFilter'
    }];

    useEffect(() => {
        if (props.open) {
            if (appPolicyData.length === 0) {
                getAppPolicyList();
            }

            setTimeout(() => {
                handleResize();
            }, 200);
        }
    }, [props.open]);

    const getAppPolicyList = () => {
        setShowLoader(true);

        axiosConf.get('/api/setting/application/detailFindAll').then(res => {
            res.data = _.map(res.data, (obj) => {
                let groupData = [];

                _.forEach(obj.ripInfo, (infoObj) => {
                    _.forEach(infoObj.vipInfo, (vipObj) => {
                        if (vipObj.vipInfo) {
                            vipObj.vipInfo.name = obj.applicationInfo.title;
                            vipObj.vipInfo.applicationId = obj.applicationInfo.id;
                            vipObj.vipInfo.protocol = _.upperCase(vipObj.vipInfo.protocol);
                            vipObj.vipInfo.portAddr = vipObj.vipInfo.portTypeIsRange === "range" ? vipObj.vipInfo.startPort + "~" + vipObj.vipInfo.endPort : vipObj.vipInfo.startPort;
                            groupData.push(vipObj.vipInfo);
                        }
                    });
                });

                return {
                    title: obj.applicationInfo.title,
                    groups: groupData
                };
            });

            setAppPolicyData(res.data);
            setShowLoader(false);
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

    const formatGroupChild = (data) => {
        if (data.groups) {
            return {
                group: true,
                children: data.groups,
                expanded: true
            };
        } else {
            return null;
        }
    }

    const onSavePolicyValidation = (params) => {
        if (params.data) {
            props.onSuccess(params.data);
            props.onClose();
        }
    }

    return (
        <Modal open={props.open} onClose={props.onClose}>
            <Card sx={modalStyles}>
                <CardHeader title="어플리케이션 정책"></CardHeader>
                <CardContent sx={{ overflowY: "auto", height: "40rem" }}>
                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                        <AgGridModule
                            gridName="applicationGridApi"
                            columnDefs={policyColumnDefs}
                            rowData={appPolicyData}
                            formatGroupChild={formatGroupChild}
                            onCellClicked={onSavePolicyValidation}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>
                </CardContent>

                {showLoader && (<Loader />)}
            </Card>
        </Modal>
    );
});

export default ModalSearchFormBandAppPolicy;