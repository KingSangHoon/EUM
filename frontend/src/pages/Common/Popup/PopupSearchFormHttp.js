import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useSearchParams } from 'react-router-dom';
import moment from 'moment';
import {
    Card, CardHeader, CardContent, Button, Box, Slide, Snackbar, Alert, Tooltip
} from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faQuestionCircle } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { hiddenComponentPopup, gridApiObj, onCopyGridCell } from '../../../lib/common';
import Loader from '../../../components/Loader';

const PopupSearchFormHttp = () => {
    const [searchParams] = useSearchParams();
    const paramUri = searchParams.get("uri");
    const paramKey = searchParams.get("key");
    const paramCategory = searchParams.get("category");
    const paramStr = searchParams.get("str");

    const [showLoader, setShowLoader] = useState(false);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [categoryData, setCategoryData] = useState([]);

    const categoryColumnDefs = [{
        headerName: "항목",
        field: 'name',
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        filter: 'agTextColumnFilter'
    }];

    const codeColumnDefs = [{
        headerName: "Code",
        field: 'name',
        minWidth: 300,
        maxWidth: 300,
        cellRendererParams: {
            checkbox: true
        },
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        cellRenderer: 'agGroupCellRenderer',
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "Desc",
        field: 'text',
        cellRendererFramework: (params) => {
            return <>{params.value}
                <Tooltip title={params.data.description2}>
                    <FontAwesomeIcon icon={faQuestionCircle} className="font-primary cursorp" style={params.value ? { marginLeft: 5 } : {}} />
                </Tooltip>
            </>;
        },
        filter: 'agTextColumnFilter'
    }];

    const protocolColumnDefs = [{
        headerName: "App Name",
        field: 'appName',
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "Master Name",
        field: 'masterName',
        filter: 'agTextColumnFilter'
    }];

    useEffect(() => {
        getFindoneStateData();

        hiddenComponentPopup();
        window.addEventListener("resize", () => {
            hiddenComponentPopup();
            handleResize();
        });

        return () => {
            window.removeEventListener('resize', () => {
                hiddenComponentPopup();
                handleResize();
            });
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            handleResize();
        }, 200);
    }, [categoryData]);

    const getFindoneStateData = () => {
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);
                getCategoryData(jsonParam);
            }
        }, 100);
    }

    const getCategoryData = (stateData) => {
        setShowLoader(true);

        const requestData = {
            category: paramCategory || paramKey,
            begin: moment(stateData.startDate).format("YYYYMMDDHHmmss"),
            end: moment(stateData.endDate).format("YYYYMMDDHHmmss")
        };

        axiosConf.post("/api/search/http/" + paramUri, requestData).then(res => {
            if (paramKey === "responseCode") {
                _.forEach(res.data, (obj) => {
                    obj.name = obj.code;
                    obj.description2 = obj.codeDesc;

                    _.forEach(obj.responseCode, (subObj) => {
                        subObj.text = subObj.description + " (" + subObj.description1 + ")";
                    });
                });
            } else if (paramKey === "application") {
                _.forEach(res.data, (obj) => {
                    obj.name = obj.applicationName;
                });
            } else if (paramKey === "protocol") {
                _.forEach(res.data, (obj) => {
                    obj.name = obj.appName + " (" + obj.masterName + ")";
                });
            } else if (paramKey === "transaction") {
                _.forEach(res.data, (obj) => {
                    obj.name = obj.transactionName;
                });
            } else if (paramKey !== "httpMethod") {
                if (paramKey === "useragent") res.data = res.data[0];

                res.data = _.map(res.data, (obj) => {
                    return { name: obj };
                });
            }

            res.data = _.filter(res.data, (obj) => {
                return obj.name !== "";
            });

            setCategoryData(res.data);
            setShowLoader(false);

            setTimeout(() => {
                if (stateData[paramKey] && stateData[paramKey].length > 0) {
                    const selectedData = _.map(stateData[paramKey], "name");
                    chkGridSelectedData(selectedData);
                }
            }, 100);
        });
    }

    const chkGridSelectedData = (selectedData) => {
        gridApiObj.categoryGridApi.api.forEachNode((node) => {
            if (selectedData.indexOf(node.data.name) !== -1) {
                node.setSelected(true);
            }
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
        if (data.responseCode) {
            return {
                group: true,
                children: data.responseCode
            };
        } else {
            return null;
        }
    }

    const TransitionUp = (props) => {
        return <Slide {...props} direction="up" />;
    }

    const onCellMouseDown = (params) => {
        const { which } = params.event;

        if (which === 3) {
            onCopyGridCell(params.value);

            setTransition(() => TransitionUp);
            setCopySuccess(true);

            setTimeout(() => {
                setCopySuccess(false);
            }, 1000);
        }
    }

    const onSavePolicyValidation = () => {
        const selectNodes = gridApiObj.categoryGridApi.api.getSelectedRows();

        window.opener.onSuccess({ target: { id: paramKey, value: selectNodes } });
        window.close();
    }

    return (
        <Card>
            <CardHeader title={paramStr + " 검색"}></CardHeader>
            <CardContent>
                <Box component="div" className="ag-theme-alpine" mt={1} pb={1} sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()}>
                    <AgGridModule
                        gridName="categoryGridApi"
                        columnDefs={paramKey === "responseCode" ? codeColumnDefs : paramKey === "protocol" ? protocolColumnDefs : categoryColumnDefs}
                        rowData={categoryData}
                        formatGroupChild={formatGroupChild}
                        onCellMouseDown={onCellMouseDown}
                        onGridReady={onGridReady}
                        handleResize={handleResize} />
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
            <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
            </Snackbar>
        </Card>
    );
};

export default PopupSearchFormHttp;