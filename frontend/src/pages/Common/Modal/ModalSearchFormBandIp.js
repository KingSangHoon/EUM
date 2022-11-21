import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { Card, CardHeader, Box, CardContent, Modal, Tabs, Tab, TextField, MenuItem, Button, Stack, Paper, Typography } from '@mui/material';

import axiosConf from '../../../axios';
import { modalStyles, gridApiObj } from '../../../lib/common';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';

const ModalSearchFormBandIp = React.forwardRef((props, ref) => {
    const [showLoader, setShowLoader] = useState(false);

    const [menuKey, setMenuKey] = useState(0);

    const [ispData, setIspData] = useState([]);
    const [idcData, setIdcData] = useState([]);
    const [geoData, setGeoData] = useState([]);
    const [geoSubData, setGeoSubData] = useState([]);

    const [countryData, setCountryData] = useState([]);
    const [domesticData, setDomesticData] = useState([]);

    const [geoType, setGeoType] = useState("0");
    const [geoGroupName, setGeoGroupName] = useState("-");

    const [zoneIspColumnDefs] = useState([{
        headerName: "Name",
        field: 'name',
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        filter: 'agTextColumnFilter'
    },
    {
        headerName: "Eng Name",
        field: 'nameEng',
        filter: 'agTextColumnFilter'
    }]);

    const [zoneColumnDefs] = useState([{
        headerName: "Name",
        field: 'name',
        checkboxSelection: true,
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        filter: 'agTextColumnFilter'
    }]);

    const [zoneSingleColumnDefs] = useState([{
        headerName: "Name",
        field: 'name',
        checkboxSelection: true,
        filter: 'agTextColumnFilter'
    }]);

    const [zoneGroupColumnDefs] = useState([{
        headerName: "Name",
        field: 'name',
        cellClass: ['cursorp'],
        cellRenderer: 'agGroupCellRenderer',
        filter: 'agTextColumnFilter'
    }]);

    useEffect(() => {
        getIspData();
    }, []);

    useEffect(() => {
        if (props.open) {
            // 수정 시 set geo Group, Sub
            const staticGeoGroupKey = { "0": "continent", "1": "country", "2": "primary", "3": "sub1", "4": "sub2" };

            const filterData = _.filter(props.selectedData, (obj) => {
                return _.includes(obj.uniqId, staticGeoGroupKey[geoType]);
            });

            if (filterData.length > 0) {
                if (geoType === "4") {
                    const filterPrimary = _.filter(geoData, (obj) => {
                        return obj.id === filterData[0].primaryId;
                    });

                    if (filterPrimary.length > 0) {
                        const filterSub1 = _.filter(filterPrimary[0].sub1, (obj) => {
                            return obj.id === filterData[0].sub1Id;
                        });

                        if (filterSub1.length > 0) {
                            setGeoSubData(filterSub1[0].groups);
                            setGeoGroupName(filterSub1[0].groupName + ">" + filterSub1[0].name);
                        }
                    }
                } else if (geoType === "3") {
                    const filterPrimary = _.filter(geoData, (obj) => {
                        return obj.id === filterData[0].primaryId;
                    });

                    if (filterPrimary.length > 0) {
                        setGeoSubData(filterPrimary[0].groups);
                        setGeoGroupName(filterPrimary[0].name);
                    }
                } else if (geoType === "1") {
                    const filterContinent = _.filter(geoData, (obj) => {
                        return obj.continentCode === filterData[0].continentCode;
                    });

                    if (filterContinent.length > 0) {
                        setGeoSubData(filterContinent[0].groups);
                        setGeoGroupName(filterContinent[0].name);
                    }
                }
            }

            setTimeout(() => {
                handleResize();

                chkGridSelectedData(gridApiObj.ispGridApi, "isp");
                chkGridSelectedData(gridApiObj.idcGridApi, "idc");
                chkGridSelectedData(gridApiObj.geoGridApi, staticGeoGroupKey[geoType]);
            }, 200);
        }
    }, [props.open]);

    useEffect(() => {
        if (props.open) {
            setTimeout(() => {
                handleResize();
            }, 200);
        }
    }, [menuKey]);

    useEffect(() => {
        if (props.geoType) {
            setGeoType(props.geoType);
        }
    }, [props.geoType]);

    useEffect(() => {
        getGeoData(geoType);

        setTimeout(() => {
            handleResize();
        }, 200);
    }, [geoType]);

    const getIspData = () => {
        axiosConf.get("/api/search/isp").then(res => {
            _.forEach(res.data, (obj) => {
                obj.uniqId = "isp&" + obj.id;
                obj.txt = "ISP - " + obj.name;
            });

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
            _.forEach(res.data, (obj) => {
                obj.uniqId = "idc&" + obj.id;
                obj.txt = "IDC - " + obj.name;
            });

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
        });
    }

    const getGeoData = (selectGeoType) => {
        setShowLoader(true);

        if (selectGeoType === "0" || selectGeoType === "1") {
            if (countryData.length === 0) {
                axiosConf.get("/api/search/geo/country").then(res => {
                    setCountryData(res.data);
                    formatGeoData(selectGeoType, res.data);
                });
            } else {
                formatGeoData(selectGeoType, countryData);
            }
        } else {
            if (domesticData.length === 0) {
                axiosConf.get("/api/search/geo/domestic").then(res => {
                    setDomesticData(res.data);
                    formatGeoData(selectGeoType, res.data);
                });
            } else {
                formatGeoData(selectGeoType, domesticData);
            }
        }
    }

    const formatGeoData = (selectGeoType, resData) => {
        let cloneData = _.cloneDeep(resData);

        if (selectGeoType === "0") {
            _.forEach(cloneData, (obj) => {
                obj.uniqId = "continent&" + obj.continentId;
                obj.name = obj.continentName;
                obj.txt = "대륙 - " + obj.name;

                delete obj.country;
            });
        } else if (selectGeoType === "1") {
            _.forEach(cloneData, (obj) => {
                obj.name = obj.continentName;

                obj.groups = _.map(obj.country, (subObj) => {
                    subObj.uniqId = "country&" + subObj.countryId;
                    subObj.name = subObj.countryName;
                    subObj.txt = "국가 - " + obj.name + ">" + subObj.name;

                    return subObj;
                });

                delete obj.country;
            });
        } else if (selectGeoType === "2") {
            _.forEach(cloneData, (obj) => {
                obj.uniqId = "primary&" + obj.id;
                obj.txt = "시/도 - " + obj.name;

                delete obj.sub1;
            });
        } else if (selectGeoType === "3") {
            _.forEach(cloneData, (obj) => {
                obj.groups = _.map(obj.sub1, (subObj) => {
                    subObj.uniqId = "sub1&" + subObj.primaryId + "_" + subObj.id;
                    subObj.name = subObj.nameVar;
                    subObj.txt = "시/군/구 - " + obj.name + ">" + subObj.name;

                    delete subObj.sub2;
                    return subObj;
                });

                obj.groups = _.sortBy(obj.groups, "name");
                delete obj.sub1;
            });
        } else {
            _.forEach(cloneData, (obj) => {
                _.forEach(obj.sub1, (subObj) => {
                    subObj.name = subObj.nameVar;
                    subObj.groupName = obj.name;

                    _.forEach(subObj.sub2, (sub2Obj) => {
                        sub2Obj.uniqId = "sub2&" + sub2Obj.primaryId + "_" + sub2Obj.sub1Id + "_" + sub2Obj.id;
                        sub2Obj.txt = "읍/면/동 - " + obj.name + ">" + subObj.name + ">" + sub2Obj.name;
                    });

                    subObj.groups = _.sortBy(subObj.sub2, "name");
                    delete subObj.sub2;
                });

                obj.sub1 = _.sortBy(obj.sub1, "name");
            });
        }

        setGeoData(cloneData);
        setGeoSubData([]);
        setShowLoader(false);
    }

    const handleGeoType = (e) => {
        setGeoType(e.target.value);
        setGeoGroupName("-");
    }

    const handleChange = (event, newValue) => {
        setMenuKey(newValue);
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const chkGridSelectedData = (targetGridApi, targetType) => {
        const filterData = _.filter(props.selectedData, (obj) => {
            return _.includes(obj.uniqId, targetType);
        });

        if (filterData.length > 0) {
            const selectData = _.map(filterData, "uniqId");

            targetGridApi.api.forEachNode((node) => {
                if (selectData.indexOf(node.data.uniqId) !== -1) {
                    node.setSelected(true);
                }
            });
        }
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const formatGroupChild = (data) => {
        if (data.sub1) {
            return {
                group: true,
                children: data.sub1
            };
        } else {
            return null;
        }
    }

    const onCellClicked = (params) => {
        if (params.data && params.data.groups) {
            if (geoType === "4") {
                setGeoGroupName(params.data.groupName + ">" + params.data.name);
            } else {
                setGeoGroupName(params.data.name);
            }

            setGeoSubData(params.data.groups);
        }
    }

    const onSavePolicyValidation = () => {
        let selectNodes = [];
        const selectIspNodes = gridApiObj.ispGridApi.api.getSelectedRows();
        const selectIdcNodes = gridApiObj.idcGridApi.api.getSelectedRows();
        const selectGeoNodes = gridApiObj.geoGridApi.api.getSelectedRows();

        _.forEach(selectIspNodes, (obj) => {
            selectNodes.push(obj);
        });

        _.forEach(selectIdcNodes, (obj) => {
            selectNodes.push(obj);
        });

        _.forEach(selectGeoNodes, (obj) => {
            selectNodes.push(obj);
        });

        props.onSuccess(selectNodes);
        props.onClose();
    }

    return (
        <Modal open={props.open} onClose={props.onClose}>
            <Card sx={modalStyles}>
                <CardHeader title="IP 대역대 검색"></CardHeader>
                <CardContent sx={{ overflowY: "auto", height: "40rem" }}>
                    <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper' }}>
                        <Tabs value={menuKey} onChange={handleChange} variant="fullWidth" className="small">
                            <Tab label="ISP" />
                            <Tab label="IDC" />
                            <Tab label="Geo" />
                        </Tabs>
                    </Box>

                    {
                        menuKey === 2 && <Stack direction="row">
                            <Paper sx={{ width: "50%" }}>
                                <TextField
                                    select
                                    sx={{ mt: 1, width: "8rem" }}
                                    name="geoType"
                                    size="small"
                                    value={geoType}
                                    onChange={handleGeoType}
                                >
                                    <MenuItem value="0">대륙</MenuItem>
                                    <MenuItem value="1">국가</MenuItem>
                                    <MenuItem value="2">시/도</MenuItem>
                                    <MenuItem value="3">시/군/구</MenuItem>
                                    <MenuItem value="4">읍/면/동</MenuItem>
                                </TextField>
                            </Paper>
                            {
                                (geoType === "1" || geoType === "3" || geoType === "4") && <Paper sx={{ width: "50%", textAlign: "right", pt: "1rem" }}>
                                    선택한 지역: <Typography component="span" color="primary">{geoGroupName}</Typography>
                                </Paper>
                            }
                        </Stack>
                    }

                    <Box component="div" className="ag-theme-alpine" mt={1} pb={1} sx={{ height: "93%" }} onContextMenu={(e) => e.preventDefault()} hidden={menuKey !== 0}>
                        <AgGridModule
                            gridName="ispGridApi"
                            columnDefs={zoneIspColumnDefs}
                            rowData={ispData}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>

                    <Box component="div" className="ag-theme-alpine" mt={1} pb={1} sx={{ height: "93%" }} onContextMenu={(e) => e.preventDefault()} hidden={menuKey !== 1}>
                        <AgGridModule
                            gridName="idcGridApi"
                            columnDefs={zoneColumnDefs}
                            rowData={idcData}
                            onGridReady={onGridReady}
                            handleResize={handleResize} />
                    </Box>

                    <Box component="div" mt={1} pb={1} sx={{ height: "88%" }} hidden={menuKey !== 2}>
                        {
                            geoType === "0" || geoType === "2" ?
                                <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                    <AgGridModule
                                        gridName="geoGridApi"
                                        columnDefs={zoneSingleColumnDefs}
                                        rowData={geoData}
                                        rowSelection="single"
                                        onGridReady={onGridReady}
                                        handleResize={handleResize} />
                                </Box>
                                : <Stack sx={{ height: "100%" }} direction="row" spacing={.5}>
                                    <Paper className="ag-theme-alpine" sx={{ width: "50%", height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                        <AgGridModule
                                            gridName="geoGroupGridApi"
                                            columnDefs={zoneGroupColumnDefs}
                                            rowData={geoData}
                                            rowSelection="single"
                                            formatGroupChild={formatGroupChild}
                                            onCellClicked={onCellClicked}
                                            onGridReady={onGridReady}
                                            handleResize={handleResize} />
                                    </Paper>
                                    <Paper className="ag-theme-alpine" sx={{ width: "50%", height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                        <AgGridModule
                                            gridName="geoGridApi"
                                            columnDefs={zoneColumnDefs}
                                            rowData={geoSubData}
                                            onGridReady={onGridReady}
                                            handleResize={handleResize} />
                                    </Paper>
                                </Stack>
                        }
                    </Box>
                </CardContent>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={props.onClose}>닫기</Button>
                </Box>

                {showLoader && (<Loader />)}
            </Card>
        </Modal>
    );
});

export default ModalSearchFormBandIp;