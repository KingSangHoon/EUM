import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import WindowOpener from 'react-window-opener';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWindowMaximize, faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj, scrollGridCnt } from '../../../lib/common';
import Loader from '../../../components/Loader';
import SearchIpBandRegion from '../../Common/Search/SearchIpBandRegion';

const CountryIndex = () => {
    const resetSchItem = { ipAddr: "" };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);

    const [showLoader, setShowLoader] = useState(false);

    const [continentData, setContinentData] = useState([]);
    const [countryData, setCountryData] = useState([]);

    const [selectRegionData, setSelectRegionData] = useState({
        continentCode: "",
        continentId: null,
        continentName: "",
        modifyFlag: false,
        countryCode: "",
        countryName: "",
        countryId: null,
    });

    const [currentPageIpList, setCurrentPageIpList] = useState(0);
    const [maxPageIpList, setMaxPageIpList] = useState(0);
    const [totalIpList, setTotalIpList] = useState(0);
    const [ipListData, setIpListData] = useState([]);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const continentColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        checkboxSelection: (params) => {
            return params.data.modifyFlag;
        },
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        lockPosition: true
    },
    {
        headerName: '대륙명',
        field: 'continentName',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            return params.data.modifyFlag ? <>
                <WindowOpener className="inline-block"
                    url={"/popup/setting/ipBand/country/reg/region/" + params.data.continentId}
                    width="800" height="600" bridge={getPolicyRegionData}
                    state={{
                        continentId: params.data.continentId,
                        countryId: null,
                        continentName: params.data.continentName,
                        countryName: "",
                        modifyFlag: true
                    }}>
                    <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
                </WindowOpener>
                <Typography component="span" ml={1}>{params.value}</Typography>
            </> : <Typography component="span">{params.value}</Typography>;
        },
        filter: "agTextColumnFilter"
    },
    {
        headerName: "대륙코드",
        field: "continentCode",
        cellClass: ['text-center', 'font-gray'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: "기본 그룹",
        field: "modifyFlag",
        cellClass: ['text-center', 'font-gray'],
        cellRenderer: (params) => {
            return params.value ? "X" : "O";
        }
    }];

    const countryColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererParams: {
            checkbox: true
        },
        checkboxSelection: (params) => {
            return params.data.modifyFlag;
        },
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        lockPosition: true
    },
    {
        headerName: '국가명',
        field: 'countryName',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            return params.data.modifyFlag ? <>
                <WindowOpener className="inline-block"
                    url={"/popup/setting/ipBand/country/reg/region/" + params.data.countryId} width="800"
                    height="600" bridge={getPolicyRegionData}
                    state={{
                        continentId: params.data.continentId,
                        countryId: params.data.countryId,
                        continentName: params.data.continentName,
                        countryName: params.data.countryName,
                        modifyFlag: true
                    }}>
                    <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
                </WindowOpener>
                <Typography component="span" ml={1}>{params.value}</Typography>
            </> : <Typography component="span">{params.value}</Typography>;
        },
        filter: "agTextColumnFilter"
    },
    {
        headerName: '국가코드',
        field: 'countryCode',
        cellClass: ['text-center', 'font-gray'],
        filter: "agTextColumnFilter"
    },
    {
        headerName: "기본 그룹",
        field: "modifyFlag",
        cellClass: ['text-center', 'font-gray'],
        cellRenderer: (params) => {
            return params.value ? "X" : "O";
        }
    }];

    const ipListColumnDefs = [{
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererParams: {
            checkbox: true
        },
        checkboxSelection: (params) => {
            return params.data.modify_flag;
        },
        headerCheckboxSelection: true,
        headerCheckboxSelectionFilteredOnly: true,
        lockPosition: true
    },
    {
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            return params.data.modify_flag ? <WindowOpener className="inline-block"
                url={"/popup/setting/ipBand/domestic/reg/ip/" + params.data.id}
                width="1000" height="530" bridge={getPolicyRegionData}
                state={{
                    primaryId: params.data.primaryId,
                    sub1Id: params.data.sub1Id,
                    sub2Id: params.data.sub2Id,
                    primaryName: params.data.primaryName,
                    sub1Name: params.data.sub1Name,
                    sub2Name: params.data.sub2Name,
                    modifyFlag: true,
                    startIp: params.data.startIp,
                    endIp: params.data.endIp
                }}>
                <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
            </WindowOpener> : "";
        },
        lockPosition: true
    },
    {
        headerName: 'Start IP',
        field: 'start_ip',
        filter: "agTextColumnFilter",
    },
    {
        headerName: 'End IP',
        field: 'end_ip',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '국가명',
        field: 'country_name',
        filter: "agTextColumnFilter"
    }
    ];

    useEffect(() => {
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyContinentGridApi) {
                gridApiObj.policyContinentGridApi.api.sizeColumnsToFit();
            }
        }, 200);

        setCountryData([]);
    }, [continentData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyCountryGridApi) {
                gridApiObj.policyCountryGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [countryData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyIpGridApi) {
                gridApiObj.policyIpGridApi.api.sizeColumnsToFit();
            }
        }, 200);
    }, [ipListData]);

    useEffect(() => {
        getPolicyRegionData();
    }, [selectRegionData]);

    const autoComponentSize = useCallback(() => {
        const mainHeight = document.body.clientHeight - document.getElementById("searchEl").clientHeight;

        _.each(document.getElementsByClassName("policyGridEl"), (obj) => {
            obj.style.height = mainHeight - 30 + 'px';
        });

        handleResize();
    }, []);

    const getPolicyRegionData = (e, data) => {
        if (data) {
            if (data.continentId) {
                getCountryData();
                getPolicyIpListMaxCount();
            } else {
                getContinentData();
            }
        } else {
            if (selectRegionData.continentId) {
                getCountryData();
                getPolicyIpListMaxCount();
            } else {
                getContinentData();
            }
        }
    }

    const getContinentData = () => {
        setShowLoader(true);

        axiosConf.get('/api/setting/geoCountry/findAllCountry').then(res => {
            setContinentData(res.data);
            setShowLoader(false);
        });
    }

    const getCountryData = () => {
        axiosConf.get('/api/setting/geoCountry/findAllCountryByCode/' + selectRegionData.continentCode).then(res => {
            _.forEach(res.data, (obj) => {
                obj.continentId = selectRegionData.continentId;
            });

            setCountryData(res.data);
            setShowLoader(false);
        });
    }

    const getPolicyIpListMaxCount = () => {
        let regionIdParam = selectRegionData.continentCode;

        if (selectRegionData.continentCode && selectRegionData.countryCode) {
            regionIdParam += "/" + selectRegionData.countryCode;
        } else if (selectRegionData.continentCode) {
            regionIdParam += "/null";
        }

        axiosConf.get('/api/setting/geoCountry/findCntMappingGeoip/' + regionIdParam).then(res => {
            const maxPage = _.floor(res.data / scrollGridCnt);

            setMaxPageIpList(maxPage);
            setCurrentPageIpList(0);
            setTotalIpList(res.data);

            getPolicyIpListData();
        });
    }

    const getPolicyIpListData = () => {
        let regionIdParam = selectRegionData.continentCode;

        if (selectRegionData.countryCode) regionIdParam += "/" + selectRegionData.countryCode;
        else regionIdParam += "/null";

        axiosConf.get('/api/setting/geoCountry/findMappingGeoip/' + regionIdParam + "/" + scrollGridCnt + "/0").then(res => {
            setIpListData(res.data.geoip);
            setShowLoader(false);

            if (gridApiObj.policyIpGridApi) {
                gridApiObj.policyIpGridApi.api.ensureIndexVisible(0, 'top');
            }
        });
    }

    const addPolicyIpListData = (page) => {
        const offset = page * scrollGridCnt;
        let regionIdParam = selectRegionData.continentCode;

        if (selectRegionData.countryCode) regionIdParam += "/" + selectRegionData.countryCode;
        else regionIdParam += "/null";

        axiosConf.get('/api/setting/geoCountry/findMappingGeoip/' + regionIdParam + "/" + scrollGridCnt + "/" + offset).then(res => {

            setIpListData([...ipListData, ...res.data.geoip]);
            setShowLoader(false);
        });
    }

    const onScrollLoadEvent = (e) => {
        if (e.target.classList.value.includes("ag-body-viewport") && ipListData.length > 0) {
            if ((e.target.scrollTop + e.target.clientHeight) === e.target.scrollHeight) {
                if (maxPageIpList > currentPageIpList) {
                    setShowLoader(true);
                    setCurrentPageIpList(prev => prev + 1);
                    addPolicyIpListData(currentPageIpList + 1);
                }
            }
        }
    }

    const onCellRegionClick = (params) => {
        if (params.data) {
            setShowLoader(true);
            if (params.data.continentId && params.data.countryId) {
                setSelectRegionData({
                    continentCode: params.data.continentCode,
                    continentId: params.data.continentId,
                    continentName: params.data.continentName,
                    modifyFlag: params.data.modifyFlag,
                    countryCode: params.data.countryCode,
                    countryName: params.data.countryName,
                    countryId: params.data.countryId,
                });
            } else if (params.data.continentId) {
                setSelectRegionData({
                    continentCode: params.data.continentCode,
                    continentId: params.data.continentId,
                    continentName: params.data.continentName,
                    modifyFlag: params.data.modifyFlag,
                    countryName: ""
                });
            }
        }
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

    const onDeleteCountryPolicy = () => {
        if (gridApiObj.policyCountryGridApi) {
            const selectedNodes = gridApiObj.policyCountryGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "countryId").join("|");

                axiosConf.post("/api/setting/geoCountry/deleteCountry", { id: selectedValueData }).then(res => {
                    alert("삭제되었습니다.");

                    setSelectRegionData({
                        ...selectRegionData,
                        countryCode: "",
                        countryName: "",
                        countryId: null,
                    });
                });
            }
        }
    }

    const onDeleteIpListPolicy = () => {
        if (gridApiObj.policyIpGridApi) {
            const selectedNodes = gridApiObj.policyIpGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.post("/api/setting/geoDomestic/deleteMappingDomestic", { id: selectedValueData }).then(res => {
                    alert("삭제되었습니다.");
                    getPolicyIpListMaxCount();
                });
            }
        }
    }

    const searchIpBandData = useCallback(() => {
        if (schItem.ipAddr === "") {
            alert("IP 대역대를 입력해주세요.");
            return;
        }

        axiosConf.get('/api/setting/geoCountry/findCountryIpRangeByIp?ip=' + schItem.ipAddr).then(res => {
            setMaxPageIpList(0);
            setCurrentPageIpList(0);
            setTotalIpList(res.data.length);
            setIpListData(res.data);

            setSelectRegionData({
                continentCode: "",
                continentId: null,
                continentName: "",
                modifyFlag: false,
                countryCode: "",
                countryName: "",
                countryId: null,
            });
        });
    }, [schItem]);

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);

        setMaxPageIpList(0);
        setCurrentPageIpList(0);
        setTotalIpList(0);
        setIpListData([]);

        setSelectRegionData({
            continentCode: "",
            continentId: null,
            continentName: "",
            modifyFlag: false,
            countryCode: "",
            countryName: "",
            countryId: null,
        });
    }, []);

    const applyContinentServer = () => {
        if (window.confirm("적용하시겠습니까?")) {
            axiosConf.post("/api/set/applyContinent", {}).then(res => {
                alert("적용되었습니다.");
            });
        }
    }

    // import content
    const SearchContent = useMemo(() => (
        <SearchIpBandRegion schItem={schItem} setSchItem={setSchItem} autoComponentSize={autoComponentSize} searchEvt={searchIpBandData} resetEvt={resetEvt} />
    ), [schItem]);

    return (
        <>
            {SearchContent}

            <Grid container mt={2.5} spacing={.5}>
                <Grid item md={3}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    대륙 ( Total: <Typography component="span"
                                        className="font-blue font-bold">{numberWithCommas(continentData.length)}</Typography> )
                                </Grid>
                            </Grid>
                        )} />
                        <CardContent className="policyGridEl">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="policyContinentGridApi"
                                    columnDefs={continentColumnDefs}
                                    rowData={continentData}
                                    rowMultiSelectWithClick={false}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellRegionClick}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={3}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    국가 ( Total: <Typography component="span"
                                        className="font-blue font-bold">{numberWithCommas(countryData.length)}</Typography> )
                                </Grid>
                                {
                                    selectRegionData.modifyFlag && <Grid item sm={2} textAlign="right">
                                        <Typography component="span" color="textPrimary" variant="h6"
                                            sx={{ mr: 1.5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faMinus} onClick={onDeleteCountryPolicy} />
                                        </Typography>
                                        <WindowOpener className="inline-block"
                                            url="/popup/setting/ipBand/country/reg/region/0" width="800"
                                            height="600" bridge={getPolicyRegionData}
                                            state={{
                                                continentId: selectRegionData.continentId,
                                                countryId: 0,
                                                primaryName: selectRegionData.continentName,
                                                countryName: "",
                                                modifyFlag: true
                                            }}>
                                            <Typography component="span" color="textPrimary" variant="h6"
                                                sx={{ mr: .5, cursor: "pointer" }}>
                                                <FontAwesomeIcon icon={faPlus} />
                                            </Typography>
                                        </WindowOpener>
                                    </Grid>
                                }
                            </Grid>
                        )} />
                        <CardContent className="policyGridEl">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()}>
                                <AgGridModule
                                    gridName="policyCountryGridApi"
                                    columnDefs={countryColumnDefs}
                                    rowData={countryData}
                                    rowMultiSelectWithClick={false}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellRegionClick}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={6}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    IP ( 선택한 지역: <Typography component="span"
                                        className="font-blue">{selectRegionData.continentName}{selectRegionData.countryName ? ">" + selectRegionData.countryName : ""}</Typography>,
                                    Total: <Typography component="span"
                                        className="font-blue font-bold">{numberWithCommas(totalIpList)}</Typography> )
                                </Grid>
                                {/*<Grid item sm={2} textAlign="right">
                                    {
                                        selectRegionData.continentId &&
                                        <Typography component="span" color="textPrimary" variant="h6"
                                            sx={{ mr: 1.5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faMinus} onClick={onDeleteIpListPolicy} />
                                        </Typography>
                                    }
                                    {
                                        selectRegionData.continentId &&
                                        <WindowOpener className="inline-block"
                                            url="/popup/setting/ipBand/country/reg/ip/0" width="1000"
                                            height="530" bridge={getPolicyRegionData}
                                            state={selectRegionData}>
                                            <Typography component="span" color="textPrimary" variant="h6"
                                                sx={{ mr: .5, cursor: "pointer" }}>
                                                <FontAwesomeIcon icon={faPlus} />
                                            </Typography>
                                        </WindowOpener>
                                    }
                                    <Button variant="outlined" size="small" color="inherit"
                                        sx={{ height: "0 !important", padding: ".53rem !important" }}
                                        onClick={applyContinentServer}>서버 적용</Button>
                                </Grid>*/}
                            </Grid>
                        )} />
                        <CardContent className="policyGridEl">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()} onScrollCapture={onScrollLoadEvent}>
                                <AgGridModule
                                    gridName="policyIpGridApi"
                                    columnDefs={ipListColumnDefs}
                                    rowData={ipListData}
                                    onCellMouseDown={onCellMouseDown}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Snackbar open={copySuccess} autoHideDuration={6000} TransitionComponent={transition}>
                    <Alert severity="success" sx={{ width: '100%' }}>Copied to clipboard!</Alert>
                </Snackbar>
                {showLoader && (<Loader />)}
            </Grid>
        </>
    );
};

export default CountryIndex;