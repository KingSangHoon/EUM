import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useSearchParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, TextField, MenuItem, Tabs, Tab, Slide, Snackbar, Alert } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { hiddenComponentPopup, gridApiObj, onCopyGridCell } from '../../../lib/common';
import Loader from '../../../components/Loader';

const PopupSearchFormZone = () => {
    const [searchParams] = useSearchParams();
    const paramKey = searchParams.get("key");

    const [showLoader, setShowLoader] = useState(false);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const [menuKey, setMenuKey] = useState(0);

    const [ispData, setIspData] = useState([]);
    const [idcData, setIdcData] = useState([]);
    const [geoData, setGeoData] = useState([]);

    const [geoType, setGeoType] = useState("0");

    const zoneColumnDefs = [{
        headerName: "Name",
        field: 'name',
        cellRendererParams: {
            checkbox: (params) => {
                if (params.data.country) return params.data.country.length > 0 ? true : false;
                else if (params.data.sub1) return params.data.sub1.length > 0 ? true : false;
                else if (params.data.sub2) return params.data.sub2.length > 0 ? true : false;
                else return true;
            }
        },
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        cellRenderer: 'agGroupCellRenderer',
        filter: 'agTextColumnFilter'
    }];

    const zoneIspColumnDefs = [{
        headerName: "Name",
        field: 'name',
        cellRendererParams: {
            checkbox: true
        },
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        cellRenderer: 'agGroupCellRenderer',
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "Eng Name",
        field: 'nameEng',
        filter: 'agTextColumnFilter'
    }];

    useEffect(() => {
        getIspData();

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
    }, [menuKey]);

    const getIspData = () => {
        setShowLoader(true);

        axiosConf.get("/api/search/isp").then(res => {
            let filterModifyFalse = _.filter(res.data, (obj) => {
                return !obj.modifyFlag;
            });

            let filterModifyTrue = _.filter(res.data, (obj) => {
                return obj.modifyFlag;
            });

            filterModifyFalse = _.sortBy(filterModifyFalse, "name");
            filterModifyTrue = _.sortBy(filterModifyTrue, "name");

            const unionData = _.unionBy(filterModifyFalse, filterModifyTrue);

            setIspData(unionData);
            getIdcData();
        });
    }

    const getIdcData = () => {
        axiosConf.get("/api/search/idc").then(res => {
            let filterModifyFalse = _.filter(res.data, (obj) => {
                return !obj.modifyFlag;
            });

            let filterModifyTrue = _.filter(res.data, (obj) => {
                return obj.modifyFlag;
            });

            filterModifyFalse = _.sortBy(filterModifyFalse, "name");
            filterModifyTrue = _.sortBy(filterModifyTrue, "name");

            const unionData = _.unionBy(filterModifyFalse, filterModifyTrue);

            setIdcData(unionData);
            getFindoneStateData();
        });
    }

    const getFindoneStateData = () => {
        const param = document.getElementById("stateInput");

        if (param) {
            const jsonParam = JSON.parse(param.value);
            let selectGeoType = "0";
            let selectedData = null;

            if (jsonParam[paramKey].length > 0) {
                selectedData = {
                    isp: [],
                    idc: [],
                    continent: [],
                    country: [],
                    primary: [],
                    sub1: [],
                    sub2: []
                };

                _.forEach(jsonParam[paramKey], (obj) => {
                    if (obj.ispId) {
                        selectedData.isp.push(obj.ispId);
                    } else if (obj.idcId) {
                        selectedData.idc.push(obj.idcId);
                    } else if (obj.continentId) {
                        selectedData.continent.push(obj.continentId);
                    } else if (obj.countryId) {
                        selectedData.country.push(obj.countryId);
                    } else if (obj.primaryId && obj.sub1Id && obj.id) {
                        selectedData.sub2.push(obj.primaryId + "_" + obj.sub1Id + "_" + obj.id);
                    } else if (obj.primaryId && obj.id) {
                        selectedData.sub1.push(obj.primaryId + "_" + obj.id);
                    } else {
                        selectedData.primary.push(obj.id);
                    }
                });

                if (selectedData.country.length > 0) {
                    selectGeoType = "1";
                } else if (selectedData.primary.length > 0) {
                    selectGeoType = "2";
                } else if (selectedData.sub1.length > 0) {
                    selectGeoType = "3";
                } else if (selectedData.sub2.length > 0) {
                    selectGeoType = "4";
                }
            }

            getGeoData(selectGeoType, selectedData);
        } else {
            getGeoData(geoType);
        }
    }

    const getGeoData = (selectGeoType, selectedData) => {
        let reqUrl = "/api/search/geo";

        if (selectGeoType === "0" || selectGeoType === "1") {
            reqUrl += "/country";
        } else {
            reqUrl += "/domestic";
        }

        setShowLoader(true);

        axiosConf.get(reqUrl).then(res => {
            if (selectGeoType === "0") {
                _.forEach(res.data, (obj) => {
                    obj.name = obj.continentName;
                    delete obj.country;
                });
            } else if (selectGeoType === "1") {
                _.forEach(res.data, (obj) => {
                    obj.name = obj.continentName;

                    _.forEach(obj.country, (subObj) => {
                        subObj.name = subObj.countryName;
                    });
                });
            } else if (selectGeoType === "2") {
                _.forEach(res.data, (obj) => {
                    delete obj.sub1;
                });
            } else if (selectGeoType === "3") {
                _.forEach(res.data, (obj) => {
                    _.forEach(obj.sub1, (subObj) => {
                        subObj.name = subObj.nameVar;
                        delete subObj.sub2;
                    });

                    obj.sub1 = _.sortBy(obj.sub1, "name");
                });
            } else {
                _.forEach(res.data, (obj) => {
                    _.forEach(obj.sub1, (subObj) => {
                        subObj.name = subObj.nameVar;
                        subObj.sub2 = _.sortBy(subObj.sub2, "name");
                    });

                    obj.sub1 = _.sortBy(obj.sub1, "name");
                });
            }

            setGeoType(selectGeoType);
            setGeoData(res.data);
            setShowLoader(false);

            setTimeout(() => {
                if (selectedData) {
                    chkGridSelectedData(selectedData);
                }
            }, 100);
        });
    }

    const chkGridSelectedData = (selectedData) => {
        gridApiObj.ispGridApi.api.forEachNode((node) => {
            if (selectedData.isp.indexOf(node.data.id) !== -1) {
                node.setSelected(true);
            }
        });

        gridApiObj.idcGridApi.api.forEachNode((node) => {
            if (selectedData.idc.indexOf(node.data.id) !== -1) {
                node.setSelected(true);
            }
        });

        if (selectedData.continent.length > 0) {
            gridApiObj.geoGridApi.api.forEachNode((node) => {
                if (selectedData.continent.indexOf(node.data.continentId) !== -1) {
                    node.setSelected(true);
                }
            });
        } else if (selectedData.country.length > 0) {
            gridApiObj.geoGridApi.api.forEachNode((node) => {
                if (node.data.countryId) {
                    if (selectedData.country.indexOf(node.data.countryId) !== -1) {
                        node.setSelected(true);
                    }
                }
            });
        } else if (selectedData.sub2.length > 0) {
            gridApiObj.geoGridApi.api.forEachNode((node) => {
                if (node.data.primaryId && node.data.sub1Id && node.data.id) {
                    if (selectedData.sub2.indexOf(node.data.primaryId + "_" + node.data.sub1Id + "_" + node.data.id) !== -1) {
                        node.setSelected(true);
                    }
                }
            });
        } else if (selectedData.sub1.length > 0) {
            gridApiObj.geoGridApi.api.forEachNode((node) => {
                if (node.data.primaryId && node.data.id) {
                    if (selectedData.sub1.indexOf(node.data.primaryId + "_" + node.data.id) !== -1) {
                        node.setSelected(true);
                    }
                }
            });
        } else if (selectedData.primary.length > 0) {
            gridApiObj.geoGridApi.api.forEachNode((node) => {
                if (selectedData.primary.indexOf(node.data.id) !== -1) {
                    node.setSelected(true);
                }
            });
        }
    }

    const handleChange = (event, newValue) => {
        setMenuKey(newValue);
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
        if (data.country || data.sub1 || data.sub2) {
            return {
                group: true,
                children: data.country || data.sub1 || data.sub2
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
        let selectNodes = [];
        const selectIspNodes = gridApiObj.ispGridApi.api.getSelectedRows();
        const selectIdcNodes = gridApiObj.idcGridApi.api.getSelectedRows();
        const selectGeoNodes = gridApiObj.geoGridApi.api.getSelectedRows();

        _.forEach(selectIspNodes, (obj) => {
            obj.ispId = obj.id;
            selectNodes.push(obj);
        });

        _.forEach(selectIdcNodes, (obj) => {
            obj.idcId = obj.id;
            selectNodes.push(obj);
        });

        _.forEach(selectGeoNodes, (obj) => {
            selectNodes.push(obj);
        });

        window.opener.onSuccess({ target: { id: paramKey, value: selectNodes } });
        window.close();
    }

    return (
        <Card>
            <CardHeader title="IP 대역대 검색"></CardHeader>
            <CardContent>
                <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper' }}>
                    <Tabs value={menuKey} onChange={handleChange} variant="fullWidth" className="small">
                        <Tab label="ISP" />
                        <Tab label="IDC" />
                        <Tab label="Geo" />
                    </Tabs>
                </Box>

                {
                    menuKey === 2 && <TextField
                        select
                        sx={{ mt: 1, width: "8rem" }}
                        name="geoType"
                        size="small"
                        value={geoType}
                        onChange={(e) => getGeoData(e.target.value)}
                    >
                        <MenuItem value="0">대륙</MenuItem>
                        <MenuItem value="1">국가</MenuItem>
                        <MenuItem value="2">시/도</MenuItem>
                        <MenuItem value="3">시/군/구</MenuItem>
                        <MenuItem value="4">읍/면/동</MenuItem>
                    </TextField>
                }

                <Box component="div" className="ag-theme-alpine" mt={1} pb={1} sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()} hidden={menuKey !== 0}>
                    <AgGridModule
                        gridName="ispGridApi"
                        columnDefs={zoneIspColumnDefs}
                        rowData={ispData}
                        onCellMouseDown={onCellMouseDown}
                        onGridReady={onGridReady}
                        handleResize={handleResize} />
                </Box>

                <Box component="div" className="ag-theme-alpine" mt={1} pb={1} sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()} hidden={menuKey !== 1}>
                    <AgGridModule
                        gridName="idcGridApi"
                        columnDefs={zoneColumnDefs}
                        rowData={idcData}
                        onCellMouseDown={onCellMouseDown}
                        onGridReady={onGridReady}
                        handleResize={handleResize} />
                </Box>

                <Box component="div" className="ag-theme-alpine" mt={1} pb={1} sx={{ height: 400 }} onContextMenu={(e) => e.preventDefault()} hidden={menuKey !== 2}>
                    <AgGridModule
                        gridName="geoGridApi"
                        columnDefs={zoneColumnDefs}
                        rowData={geoData}
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

export default PopupSearchFormZone;