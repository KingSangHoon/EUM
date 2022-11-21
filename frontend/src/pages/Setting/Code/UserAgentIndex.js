import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent } from '@mui/material';
import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { onCopyGridCell, gridApiObj, numberWithCommas, scrollGridCnt } from '../../../lib/common';
import Loader from '../../../components/Loader';
import SearchUserAgent from "../../Common/Search/SearchUserAgent";
import { useLocation } from "react-router-dom";

const UserAgentIndex = () => {
    const resetSchItem = {
        userAgent: "",
        userAgentType: "전체",
    };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });
    const [showLoader, setShowLoader] = useState(false);
    const [schItem, setSchItem] = useState(formatSchItem);
    const [resultSchItem, setResultSchItem] = useState({
        userAgent: "",
        userAgentType: "전체",
    });
    const [maxPageUserAgentList, setMaxPageUserAgentList] = useState(0);
    const [currentPageUserAgentList, setCurrentPageUserAgentList] = useState(0);
    const [agentListCnt, setAgentListCnt] = useState(0);
    const [viewType, setViewType] = useState(true);
    const [userAgentData, setUserAgentData] = useState([]);
    const [agentData, setAgentData] = useState([]);
    const [selectUserAgentType, setSelectUserAgentType] = useState({
        userAgentType: "software",
    });

    const [userDetailData, setUserDetailData] = useState([]);
    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);
    const [infoCategoryGrid, setInfoCategoryGrid] = useState({
        name: "",
        size: 0
    })

    const [category, setCategory] = useState([{
        key: "software",
        label: "Software",
        enabled: true,
        data: [],
        api: "softwareGridApi",
        columnDefs: [{
            headerName: "그룹 명",
            field: 'name',
            cellRenderer: 'agGroupCellRenderer',
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Name',
            field: 'software',
            cellClass: ['cursorp'],
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Version',
            field: 'softwareVersion',
            cellClass: ['text-right', 'cursorp'],
            filter: 'agTextColumnFilter'
        }]
    }, {
        key: "operatingSystems",
        label: "Operating Systems",
        enabled: false,
        data: [],
        api: "operatingSystemGridApi",
        columnDefs: [{
            headerName: "그룹 명",
            field: 'name',
            cellRenderer: 'agGroupCellRenderer',
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Name',
            field: 'operatingSystem',
            cellClass: ['cursorp'],
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Version',
            field: 'operatingSystemVersion',
            cellClass: ['text-right', 'cursorp'],
            filter: 'agTextColumnFilter'
        }]
    }, {
        key: "operatingPlatforms",
        label: "Vendor Operating Platforms",
        enabled: false,
        data: [],
        api: "operatingPlatformGridApi",
        columnDefs: [{
            headerName: "그룹 명",
            field: 'name',
            cellRenderer: 'agGroupCellRenderer',
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Name',
            field: 'operatingPlatform',
            cellClass: ['cursorp'],
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Code',
            field: 'operatingPlatformCode',
            cellClass: ['cursorp'],
            filter: "agTextColumnFilter"
        },
        {
            headerName: 'Code Name',
            field: 'operatingPlatformCodeName',
            cellClass: ['cursorp'],
            filter: "agTextColumnFilter"
        }]
    }, {
        key: "softwareTypes",
        label: "Software Types",
        enabled: false,
        data: [],
        api: "softwareTypeGridApi",
        columnDefs: [{
            headerName: "그룹 명",
            field: 'name',
            cellRenderer: 'agGroupCellRenderer',
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Sub Type',
            field: 'softwareSubType',
            cellClass: ['cursorp'],
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Type Specific',
            field: 'softwareSubTypeSpecific',
            cellClass: ['cursorp'],
            filter: 'agTextColumnFilter'
        }]
    }, {
        key: "hardwareTypes",
        label: "Hardware Types",
        enabled: false,
        data: [],
        api: "hardwareTypeGridApi",
        columnDefs: [{
            headerName: "그룹 명",
            field: 'name',
            cellRenderer: 'agGroupCellRenderer',
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Sub Type',
            field: 'hardwareSubType',
            cellClass: ['cursorp'],
            filter: 'agTextColumnFilter'
        },
        {
            headerName: 'Sub-Sub Type',
            field: 'hardwareSubSubType',
            cellClass: ['cursorp'],
            filter: 'agTextColumnFilter'
        }]
    }, {
        key: "layoutEngines",
        label: "Layout Engines",
        enabled: false,
        data: [],
        api: "layoutEngineGridApi",
        columnDefs: [{
            headerName: 'Name',
            field: 'name',
            cellClass: ['cursorp'],
            filter: "agTextColumnFilter"
        }]
    }]);

    const ipListColumnDefs = [
        {
            headerName: 'User Agent',
            field: 'userAgent',
            filter: "agTextColumnFilter",
        }
    ];

    useEffect(() => {
        autoComponentSize();
        totalSearchResult();

        getCategoryList("software");
        window.addEventListener("resize", autoComponentSize);
        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyUserAgentGridApi) {
                gridApiObj.policyUserAgentGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [userDetailData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.agentAllGridApi) {
                gridApiObj.agentAllGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [agentData]);

    const totalSearchResult = () => {
        setShowLoader(true);

        if (gridApiObj.agentAllGridApi) {
            gridApiObj.agentAllGridApi.api.ensureIndexVisible(0, 'top');
        }

        axiosConf.post("/api/setting/code/userAgent/findCntAllAgent", resultSchItem).then(res => {
            const maxPage = _.floor(res.data / scrollGridCnt);

            setMaxPageUserAgentList(maxPage);
            setCurrentPageUserAgentList(0);
            setAgentListCnt(res.data);

            getAgentListFist(0);
        });
    }

    const searchResult = (item) => {
        setShowLoader(true);

        if (gridApiObj.agentAllGridApi) {
            gridApiObj.agentAllGridApi.api.ensureIndexVisible(0, 'top');
        }

        axiosConf.post("/api/setting/code/userAgent/findCntAllAgent", item).then(res => {
            const maxPage = _.floor(res.data / scrollGridCnt);

            setMaxPageUserAgentList(maxPage);
            setCurrentPageUserAgentList(0);
            setAgentListCnt(res.data);

            getSearchAgentListFist(0, item);
        });
    }

    const autoComponentSize = useCallback(() => {
        const mainHeight = document.body.clientHeight - document.getElementById("searchEl").clientHeight;
        if (document.getElementById('categoryGroupEl')) {
            document.getElementById('categoryGroupEl').style.height = mainHeight - 30 + 'px';
        }
        if (document.getElementById('agentAllGrid')) {
            document.getElementById('agentAllGrid').style.height = mainHeight - 30 + 'px';
        }
        if (document.getElementById('categoryListEl')) {
            document.getElementById('categoryListEl').style.height = mainHeight - 30 + 'px';
        }
        handleResize();
    }, []);

    const getCategoryList = (selectKey) => {
        let cloneCategory = _.cloneDeep(category);

        axiosConf.get("/api/setting/code/userAgent/findUserAgentType/" + selectKey).then(res => {
            if (selectKey === "operatingPlatforms") {
                const groupByVendor = _.groupBy(res.data, "operatingPlatformVendorName");

                res.data = _.map(groupByVendor, (obj, key) => {
                    return {
                        name: key,
                        values: obj
                    };
                });
            }

            _.forEach(cloneCategory, (obj) => {
                obj.enabled = false;

                if (obj.key === selectKey) {
                    obj.enabled = true;
                    obj.data = res.data;
                }
            });

            setCategory(cloneCategory);
        });
    }

    const switchSelectCategoryData = (selectKey) => {
        switch (selectKey) {
            case "software":
                return "software"
            case "operatingSystems":
                return "operatingSystem"
            case "operatingPlatforms":
                return "operatingPlatform"
            case "softwareTypes":
                return "softwareSubType"
            case "hardwareTypes":
                return "hardwareSubType"
            case "layoutEngines":
                return "name"
            default:
                return "software"
        }
    }

    const onCellRegionClick = (params) => {
        const targetKey = switchSelectCategoryData(selectUserAgentType.userAgentType);

        if (params.data[targetKey]) {
            const requestData = { value: params.data[targetKey] };

            axiosConf.post("/api/setting/code/userAgent/findUserAgent/" + selectUserAgentType.userAgentType, requestData).then(res => {
                setUserAgentData(res.data);
            });
        }
    }

    const getAgentListFist = (page) => {
        axiosConf.post("/api/setting/code/userAgent/findAgent/" + scrollGridCnt + "/" + page, resultSchItem).then(res => {
            setAgentData(res.data);
            setShowLoader(false);
            autoComponentSize();
        });
    }

    const getSearchAgentListFist = (page, item) => {
        axiosConf.post("/api/setting/code/userAgent/findAgent/" + scrollGridCnt + "/" + page, item).then(res => {
            setAgentData(res.data);
            setShowLoader(false);
            autoComponentSize();
        });
    }

    const addAgentListFist = (page) => {
        setShowLoader(true);
        const offset = page * scrollGridCnt;

        axiosConf.post("/api/setting/code/userAgent/findAgent/" + scrollGridCnt + "/" + offset, resultSchItem).then(res => {
            setAgentData([...agentData, ...res.data]);
            setShowLoader(false);
        });
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

    const TransitionUp = (props) => {
        return <Slide {...props} direction="up" />;
    }

    const onGridReady = (params, target) => {
        gridApiObj[target] = params;
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const formatGroupChild = data => {
        if (data.values) {
            return {
                group: true,
                children: data.values
            };
        } else {
            return null;
        }
    }

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);
        setResultSchItem(resetSchItem);
        setViewType(true);
        setUserAgentData([]);

        totalSearchResult();
    }, [resultSchItem]);

    const getPolicyList = useCallback((e, data) => {
        setShowLoader(true);
        let cloneCategory = _.cloneDeep(category);

        const targetData = data ? data : schItem;
        let requestData = {};

        requestData.userAgent = targetData.userAgent;
        requestData.userAgentType = targetData.userAgentType;
        setUserAgentData([]);

        if (targetData.userAgentType != '전체') {
            axiosConf.post("/api/setting/code/userAgent/findUserAgentByUser", requestData).then(res => {
                setSelectUserAgentType({ ...selectUserAgentType, ['userAgentType']: requestData.userAgentType });

                if (requestData.userAgentType === "operatingPlatforms") {
                    const groupByVendor = _.groupBy(res.data, "operatingPlatformVendorName");

                    res.data = _.map(groupByVendor, (obj, key) => {
                        return {
                            name: key,
                            values: obj
                        };
                    });
                }

                _.forEach(cloneCategory, (obj) => {
                    obj.enabled = false;
                    if (obj.key === requestData.userAgentType) {
                        obj.enabled = true;
                        obj.data = res.data;

                        const bufItem = { size: res.data.length, name: obj.label }
                        setInfoCategoryGrid(bufItem);
                    }
                });

                setCategory(cloneCategory);
                setViewType(false);
                setTimeout(() => {
                    if (gridApiObj.policyUserAgentGridApi) {
                        gridApiObj.policyUserAgentGridApi.api.sizeColumnsToFit();
                    }
                }, 200);

                autoComponentSize();
                setShowLoader(false);
            });
        } else {
            setResultSchItem(requestData);
            setViewType(true);
            searchResult(requestData);
            autoComponentSize();
            setShowLoader(false);
        }
    }, [schItem, selectUserAgentType]);

    const onScrollLoadEvent = (e) => {
        if (e.target.classList.value.includes("ag-body-viewport") && agentData.length > 0) {
            if ((e.target.scrollTop + e.target.clientHeight) === e.target.scrollHeight) {
                if (maxPageUserAgentList > currentPageUserAgentList) {
                    setShowLoader(true);
                    setCurrentPageUserAgentList(prev => prev + 1);
                    addAgentListFist(currentPageUserAgentList + 1);
                }
            }
        }
    }

    // import content
    const SearchContent = useMemo(() => (
        <SearchUserAgent schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={getPolicyList} resetEvt={resetEvt} />
    ), [schItem]);

    return (
        <>
            {SearchContent}

            <Grid container mt={2.5} spacing={.5}>
                {
                    viewType ? <Grid item md={12}>
                        <Card>
                            <CardHeader title={(
                                <Grid container>
                                    <Grid item sm={10}>
                                        User Agent ( Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(agentListCnt)}</Typography> )
                                    </Grid>
                                    <Grid item sm={2} textAlign="right"></Grid>
                                </Grid>
                            )} />
                            <CardContent className="policyGridEl" id="agentAllGrid">
                                <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()} onScrollCapture={onScrollLoadEvent}>
                                    <AgGridModule
                                        gridName="agentAllGridApi"
                                        columnDefs={ipListColumnDefs}
                                        rowData={agentData}
                                        onCellMouseDown={onCellMouseDown}
                                        onGridReady={onGridReady}
                                        handleResize={handleResize} />
                                </Box>
                            </CardContent>
                        </Card>
                    </Grid> : <>
                        <Grid item md={6}>
                            <Card>
                                <CardHeader title={(
                                    <Grid container>
                                        <Grid item sm={10}>
                                            {infoCategoryGrid.name} ( Total: <Typography component="span" className="font-blue font-bold">{numberWithCommas(infoCategoryGrid.size)}</Typography> )
                                        </Grid>
                                        <Grid item sm={2} textAlign="right"> </Grid>
                                    </Grid>
                                )} />

                                <CardContent className="policyGridEl" id="categoryGroupEl">
                                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                        onContextMenu={(e) => e.preventDefault()}>
                                        {
                                            category.map((obj, i) => (
                                                <div className={obj.enabled ? "" : "none"} id={obj.api + "Tb"} style={{ height: "100%" }} key={i}>
                                                    <AgGridModule
                                                        gridName={obj.api}
                                                        columnDefs={obj.columnDefs}
                                                        rowData={obj.data}
                                                        formatGroupChild={formatGroupChild}
                                                        onCellMouseDown={onCellMouseDown}
                                                        onGridReady={onGridReady}
                                                        onCellClicked={onCellRegionClick}
                                                        handleResize={handleResize} />
                                                </div>
                                            ))
                                        }
                                    </Box>
                                </CardContent>
                            </Card>
                        </Grid>
                        <Grid item md={6}>
                            <Card>
                                <CardHeader title={(
                                    <Grid container>
                                        <Grid item sm={10}>
                                            User Agent ( Total: <Typography component="span"
                                                className="font-blue font-bold">{numberWithCommas(userAgentData.length)}</Typography> )
                                        </Grid>
                                        <Grid item sm={2} textAlign="right"> </Grid>
                                    </Grid>
                                )} />
                                <CardContent className="policyGridEl" id="categoryListEl">
                                    <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }} onContextMenu={(e) => e.preventDefault()}>
                                        <AgGridModule
                                            gridName="policyUserAgentGridApi"
                                            columnDefs={ipListColumnDefs}
                                            rowData={userAgentData}
                                            onCellMouseDown={onCellMouseDown}
                                            onGridReady={onGridReady}
                                            handleResize={handleResize} />
                                    </Box>
                                </CardContent>
                            </Card>
                        </Grid>
                    </>
                }

                <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                    <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
                </Snackbar>
                {showLoader && (<Loader />)}
            </Grid>
        </>
    );
};

export default UserAgentIndex;