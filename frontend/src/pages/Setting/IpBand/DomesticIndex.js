import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import WindowOpener from 'react-window-opener';
import { useLocation } from 'react-router-dom';
import { Grid, Box, Typography, Alert, Snackbar, Slide, Card, CardHeader, CardContent, Button } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faWindowMaximize, faMinus, faPlus } from "@fortawesome/free-solid-svg-icons";

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import { numberWithCommas, onCopyGridCell, gridApiObj, scrollGridCnt } from '../../../lib/common';
import Loader from '../../../components/Loader';
import SearchIpBandRegion from '../../Common/Search/SearchIpBandRegion';

const DomesticIndex = () => {
    const resetSchItem = { ipAddr: "" };

    const location = useLocation();
    let formatSchItem = {};

    _.map(resetSchItem, (obj, key) => {
        formatSchItem[key] = location.state && location.state.schItem[key] ? location.state.schItem[key] : obj;
    });

    const [schItem, setSchItem] = useState(formatSchItem);
    const [showLoader, setShowLoader] = useState(false);

    const [primaryData, setPrimaryData] = useState([]);
    const [sub1Data, setSub1Data] = useState([]);
    const [sub2Data, setSub2Data] = useState([]);

    const [selectRegionData, setSelectRegionData] = useState({
        primaryId: null,
        sub1Id: null,
        sub2Id: null,
        primaryName: "-",
        sub1Name: "",
        sub2Name: "",
        modifyFlag: false
    });

    const [currentPageIpList, setCurrentPageIpList] = useState(0);
    const [maxPageIpList, setMaxPageIpList] = useState(0);
    const [totalIpList, setTotalIpList] = useState(0);
    const [ipListData, setIpListData] = useState([]);

    const [searchIpResult, setSearchIpResult] = useState(null);

    const [copySuccess, setCopySuccess] = useState(false);
    const [transition, setTransition] = useState(undefined);

    const primaryColumnDefs = [{
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
        headerName: '시/도',
        field: 'name',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            return params.data.modifyFlag ? <>
                <WindowOpener className="inline-block"
                    url={"/popup/setting/ipBand/domestic/reg/region/" + params.data.id} width="800"
                    height="600" bridge={getPolicyRegionData}
                    state={{
                        primaryId: null,
                        sub1Id: null,
                        sub2Id: null,
                        primaryName: "-",
                        sub1Name: "",
                        sub2Name: "",
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
        headerName: "기본 그룹",
        field: "modifyFlag",
        maxWidth: 100,
        cellClass: ['text-center', 'font-gray', 'cursorp'],
        cellRenderer: (params) => {
            return params.value ? "X" : "O";
        }
    }];

    const sub1ColumnDefs = [{
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
        headerName: '시/군/구',
        field: 'nameVar',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            return params.data.modifyFlag ? <>
                <WindowOpener className="inline-block"
                    url={"/popup/setting/ipBand/domestic/reg/region/" + params.data.id} width="800"
                    height="600" bridge={getPolicyRegionData}
                    state={{
                        primaryId: params.data.primaryId,
                        sub1Id: null,
                        sub2Id: null,
                        primaryName: params.data.primaryName,
                        sub1Name: "",
                        sub2Name: "",
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
        headerName: "기본 그룹",
        field: "modifyFlag",
        maxWidth: 100,
        cellClass: ['text-center', 'font-gray', 'cursorp'],
        cellRenderer: (params) => {
            return params.value ? "X" : "O";
        }
    }];

    const sub2ColumnDefs = [{
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
        headerName: '읍/면/동',
        field: 'name',
        cellClass: ['cursorp'],
        cellRendererFramework: (params) => {
            return params.data.modifyFlag ? <>
                <WindowOpener className="inline-block"
                    url={"/popup/setting/ipBand/domestic/reg/region/" + params.data.id} width="800"
                    height="600" bridge={getPolicyRegionData}
                    state={{
                        primaryId: params.data.primaryId,
                        sub1Id: params.data.sub1Id,
                        sub2Id: null,
                        primaryName: params.data.primaryName,
                        sub1Name: params.data.sub1Name,
                        sub2Name: "",
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
        headerName: "기본 그룹",
        field: "modifyFlag",
        maxWidth: 100,
        cellClass: ['text-center', 'font-gray', 'cursorp'],
        cellRenderer: (params) => {
            return params.value ? "X" : "O";
        }
    }];

    const ipListColumnDefs = [{
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
        headerName: '',
        minWidth: 50,
        maxWidth: 50,
        cellClass: ['text-center'],
        cellRendererFramework: (params) => {
            return params.data.modifyFlag && params.data.type === "private" ? <WindowOpener className="inline-block"
                url={"/popup/setting/ipBand/domestic/reg/ip/" + params.data.id}
                width="1000"
                height="530"
                bridge={getPolicyRegionData}
                state={{
                    primaryId: params.data.primaryId,
                    sub1Id: params.data.sub1Id,
                    sub2Id: params.data.sub2Id,
                    primaryName: params.data.primaryName,
                    sub1Name: params.data.sub1Name,
                    sub2Name: params.data.sub2Name,
                    modifyFlag: true,
                    startIp: params.data.startIp,
                    endIp: params.data.endIp,
                    searchIp: params.data.searchIp
                }}>
                <FontAwesomeIcon icon={faWindowMaximize} className="font-blue cursorp" />
            </WindowOpener> : "";
        },
        lockPosition: true
    },
    {
        headerName: 'Start IP',
        field: 'startIp',
        filter: "agTextColumnFilter"
    },
    {
        headerName: 'End IP',
        field: 'endIp',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '시/도',
        field: 'primaryName',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '시/군/구',
        field: 'r1Name',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '읍/면/동',
        field: 'r2Name',
        filter: "agTextColumnFilter"
    },
    {
        headerName: '유형',
        field: 'type',
        filter: "agTextColumnFilter",
        cellRendererFramework: (params) => {
            let result = "-";
            if (params.data.type == "public") result = "공인 IP"
            else if (params.data.type == "private") result = "사설 IP"
            return result;
        }
    }];

    useEffect(() => {
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, []);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyPrimaryGridApi) {
                gridApiObj.policyPrimaryGridApi.api.sizeColumnsToFit();
            }
        }, 200);

        setSub1Data([]);
        setSub2Data([]);
    }, [primaryData]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policySub1GridApi) {
                gridApiObj.policySub1GridApi.api.sizeColumnsToFit();
            }
        }, 200);

        setSub2Data([]);
    }, [sub1Data]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policySub2GridApi) {
                gridApiObj.policySub2GridApi.api.sizeColumnsToFit();
            }
        }, 200);

    }, [sub2Data]);

    useEffect(() => {
        setTimeout(() => {
            if (gridApiObj.policyIpGridApi) {
                gridApiObj.policyIpGridApi.api.sizeColumnsToFit();
            }
        }, 200);

    }, [ipListData]);

    useEffect(() => {
        if (searchIpResult === null) {
            getPolicyRegionData();
        }
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
            if (data.searchIp) {
                setSchItem({ ipAddr: data.searchIp });
                searchIpBandData(data.searchIp);
            } else {
                setSelectRegionData(data);
            }
        } else {
            setShowLoader(true);

            if (selectRegionData.primaryId && selectRegionData.sub1Id && selectRegionData.sub2Id) {
                getPolicyIpListData();
            } else if (selectRegionData.primaryId && selectRegionData.sub1Id) {
                getPolicySub2Data();
                getPolicyIpListData();
            } else if (selectRegionData.primaryId) {
                getPolicySub1Data();
                getPolicyIpListData();
            } else {
                getPolicyPrimaryData();
            }
        }
    }

    const searchIpBandData = useCallback((searchIp) => {
        const ipAddr = searchIp ? searchIp : schItem.ipAddr;

        if (ipAddr === "") {
            alert("IP 대역대를 입력해주세요.");
            return;
        }

        setShowLoader(true);

        const requestData = {
            ip: ipAddr,
            offset: 0,
            limit: scrollGridCnt
        };

        axiosConf.post('/api/setting/geoDomestic/search', requestData).then(res => {
            const maxPage = _.floor(res.data.result.total / scrollGridCnt);

            res.data.info = _.filter(res.data.info, (obj) => {
                obj.sub1 = _.filter(obj.sub1, (sub1Obj) => {
                    sub1Obj.nameVar = sub1Obj.name;

                    sub1Obj.sub2 = _.filter(sub1Obj.sub2, (sub2Obj) => {
                        return sub2Obj.name !== "";
                    });

                    return sub1Obj.name !== "";
                });

                return obj.name !== "";
            });

            _.forEach(res.data.result.results, (obj) => {
                obj.searchIp = ipAddr;
            });

            setSearchIpResult(res.data.result.results);
            setPrimaryData([]);
            setPrimaryData(res.data.info);

            setMaxPageIpList(maxPage);
            setCurrentPageIpList(0);
            setTotalIpList(res.data.result.total);
            setIpListData([]);
            setIpListData(res.data.result.results);

            setSelectRegionData({
                primaryId: null,
                sub1Id: null,
                sub2Id: null,
                primaryName: "-",
                sub1Name: "",
                sub2Name: "",
                modifyFlag: false
            });

            setShowLoader(false);

            if (gridApiObj.policyIpGridApi) {
                gridApiObj.policyIpGridApi.api.ensureIndexVisible(0, 'top');
            }
        });
    }, [schItem]);

    const addSearchIpListData = (page) => {
        const requestData = {
            ip: schItem.ipAddr,
            offset: page * scrollGridCnt,
            limit: scrollGridCnt
        };

        axiosConf.post('/api/setting/geoDomestic/search', requestData).then(res => {
            _.forEach(res.data.result.results, (obj) => {
                obj.searchIp = schItem.ipAddr;
            });

            setSearchIpResult([...ipListData, ...res.data.result.results]);
            setIpListData([...ipListData, ...res.data.result.results]);
            setShowLoader(false);
        });
    }

    const getPolicyPrimaryData = () => {
        const requestData = {
            category: "primary",
            primaryId: null
        };

        axiosConf.post('/api/setting/geoDomestic/find/domestic', requestData).then(res => {
            setPrimaryData([]);
            setPrimaryData(res.data);
            setShowLoader(false);
        });
    }

    const getPolicySub1Data = () => {
        const requestData = {
            category: "primary",
            primaryId: selectRegionData.primaryId
        };

        axiosConf.post('/api/setting/geoDomestic/find/domestic', requestData).then(res => {
            _.forEach(res.data, (obj) => {
                obj.primaryName = selectRegionData.primaryName;
            });

            let filterModifyFalse = _.filter(res.data, (obj) => {
                return !obj.modifyFlag;
            });

            let filterModifyTrue = _.filter(res.data, (obj) => {
                return obj.modifyFlag;
            });

            filterModifyFalse = _.sortBy(filterModifyFalse, "nameVar");

            const unionData = _.unionBy(filterModifyFalse, filterModifyTrue);
            setSub1Data([]);
            setSub1Data(unionData);
        });
    }

    const getPolicySub2Data = () => {
        const requestData = {
            category: "sub1",
            primaryId: selectRegionData.primaryId,
            sub1Id: selectRegionData.sub1Id
        };

        axiosConf.post('/api/setting/geoDomestic/find/domestic', requestData).then(res => {
            _.forEach(res.data, (obj, i) => {
                obj.primaryName = selectRegionData.primaryName;
                obj.sub1Name = selectRegionData.sub1Name;
            });

            let filterModifyFalse = _.filter(res.data, (obj) => {
                return !obj.modifyFlag;
            });

            let filterModifyTrue = _.filter(res.data, (obj) => {
                return obj.modifyFlag;
            });

            filterModifyFalse = _.sortBy(filterModifyFalse, "name");

            const unionData = _.unionBy(filterModifyFalse, filterModifyTrue);
            setSub2Data([]);
            setSub2Data(unionData);
        });
    }

    const getPolicyIpListData = () => {
        let requestData = {
            category: "primary",
            primaryId: selectRegionData.primaryId,
            offset: 0,
            limit: scrollGridCnt
        };

        if (selectRegionData.sub1Id && selectRegionData.sub2Id) {
            requestData.category = "sub2";
            requestData.sub1Id = selectRegionData.sub1Id;
            requestData.sub2Id = selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            requestData.category = "sub1";
            requestData.sub1Id = selectRegionData.sub1Id;
        }

        axiosConf.post('/api/setting/geoDomestic/find/ip', requestData).then(res => {
            const maxPage = _.floor(res.data.total / scrollGridCnt);

            _.forEach(res.data.results, (obj) => {
                obj.sub1Name = selectRegionData.sub1Name;
                obj.sub2Name = selectRegionData.sub2Name;
            });

            setMaxPageIpList(maxPage);
            setCurrentPageIpList(0);
            setTotalIpList(res.data.total);

            setIpListData([]);
            setIpListData(res.data.results);
            setShowLoader(false);

            if (gridApiObj.policyIpGridApi) {
                gridApiObj.policyIpGridApi.api.ensureIndexVisible(0, 'top');
            }
        });
    }

    const addPolicyIpListData = (page) => {
        let requestData = {
            category: "primary",
            primaryId: selectRegionData.primaryId,
            offset: page * scrollGridCnt,
            limit: scrollGridCnt
        };

        if (selectRegionData.sub1Id && selectRegionData.sub2Id) {
            requestData.category = "sub2";
            requestData.sub1Id = selectRegionData.sub1Id;
            requestData.sub2Id = selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            requestData.category = "sub1";
            requestData.sub1Id = selectRegionData.sub1Id;
        }

        axiosConf.post('/api/setting/geoDomestic/find/ip', requestData).then(res => {
            _.forEach(res.data.results, (obj) => {
                obj.sub1Name = selectRegionData.sub1Name;
                obj.sub2Name = selectRegionData.sub2Name;
            });

            setIpListData([...ipListData, ...res.data.results]);
            setShowLoader(false);
        });
    }

    const onScrollLoadEvent = (e) => {
        if (e.target.classList.value.includes("ag-body-viewport") && ipListData.length > 0) {
            if ((e.target.scrollTop + e.target.clientHeight) === e.target.scrollHeight) {
                if (maxPageIpList > currentPageIpList) {
                    setShowLoader(true);
                    setCurrentPageIpList(prev => prev + 1);

                    if (searchIpResult === null) {
                        addPolicyIpListData(currentPageIpList + 1);
                    } else {
                        addSearchIpListData(currentPageIpList + 1);
                    }
                }
            }
        }
    }

    const onCellRegionClick = (params) => {
        if (params.data) {
            if (searchIpResult === null) {
                selectParamRegionData(params.data);
            } else {
                filterParamRegionData(params.data);

                if (gridApiObj.policyIpGridApi) {
                    gridApiObj.policyIpGridApi.api.ensureIndexVisible(0, 'top');
                }
            }
        }
    }

    const filterParamRegionData = (params) => {
        if (params.sub1) {
            const filterIp = _.filter(searchIpResult, (obj) => {
                return obj.primaryId === params.id;
            });

            setSub1Data(params.sub1);
            setMaxPageIpList(0);
            setCurrentPageIpList(0);
            setTotalIpList(filterIp.length);
            setIpListData(filterIp);

            setSelectRegionData({
                primaryId: params.id,
                sub1Id: null,
                sub2Id: null,
                primaryName: params.name,
                sub1Name: "",
                sub2Name: "",
                modifyFlag: false
            });
        } else if (params.sub2) {
            const filterIp = _.filter(searchIpResult, (obj) => {
                return obj.primaryId === selectRegionData.primaryId && obj.sub1Id === params.id;
            });

            setSub2Data(params.sub2);
            setTotalIpList(filterIp.length);
            setIpListData(filterIp);

            setSelectRegionData({
                primaryId: selectRegionData.primaryId,
                sub1Id: params.id,
                sub2Id: null,
                primaryName: selectRegionData.primaryName,
                sub1Name: " > " + params.name,
                sub2Name: "",
                modifyFlag: false
            });
        } else {
            const filterIp = _.filter(searchIpResult, (obj) => {
                return obj.primaryId === selectRegionData.primaryId && obj.sub1Id === selectRegionData.sub1Id && obj.sub2Id === params.id;
            });

            setTotalIpList(filterIp.length);
            setIpListData(filterIp);

            setSelectRegionData({
                primaryId: selectRegionData.primaryId,
                sub1Id: selectRegionData.sub1Id,
                sub2Id: params.id,
                primaryName: selectRegionData.primaryName,
                sub1Name: selectRegionData.sub1Name,
                sub2Name: " > " + params.name,
                modifyFlag: false
            });
        }
    }

    const selectParamRegionData = (params) => {
        if (params.primaryId && params.sub1Id && params.id) {
            setSelectRegionData({
                primaryId: params.primaryId,
                sub1Id: params.sub1Id,
                sub2Id: params.id,
                primaryName: selectRegionData.primaryName,
                sub1Name: selectRegionData.sub1Name,
                sub2Name: " > " + params.name,
                modifyFlag: params.modifyFlag
            });
        } else if (params.primaryId && params.id) {
            setSelectRegionData({
                primaryId: params.primaryId,
                sub1Id: params.id,
                sub2Id: null,
                primaryName: selectRegionData.primaryName,
                sub1Name: " > " + params.name,
                sub2Name: "",
                modifyFlag: params.modifyFlag
            });
        } else if (params.id) {
            setSelectRegionData({
                primaryId: params.id,
                sub1Id: null,
                sub2Id: null,
                primaryName: params.name,
                sub1Name: "",
                sub2Name: "",
                modifyFlag: params.modifyFlag
            });
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

    const onDeletePrimaryPolicy = () => {
        if (gridApiObj.policyPrimaryGridApi) {
            const selectedNodes = gridApiObj.policyPrimaryGridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.post("/api/setting/geoDomestic/deleteInfoDomesticPrimary", { id: selectedValueData }).then(res => {
                    alert("삭제되었습니다.");

                    setMaxPageIpList(0);
                    setCurrentPageIpList(0);
                    setTotalIpList(0);
                    setIpListData([]);

                    setSelectRegionData({
                        primaryId: null,
                        sub1Id: null,
                        sub2Id: null,
                        primaryName: "-",
                        sub1Name: "",
                        sub2Name: "",
                        modifyFlag: false
                    });
                });
            }
        }
    }

    const onDeleteSub1Policy = () => {
        if (gridApiObj.policySub1GridApi) {
            const selectedNodes = gridApiObj.policySub1GridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.post("/api/setting/geoDomestic/deleteInfoDomesticSub1", { id: selectedValueData }).then(res => {
                    alert("삭제되었습니다.");

                    setSelectRegionData({
                        ...selectRegionData,
                        sub1Id: null,
                        sub2Id: null,
                        sub1Name: "",
                        sub2Name: ""
                    });
                });
            }
        }
    }

    const onDeleteSub2Policy = () => {
        if (gridApiObj.policySub2GridApi) {
            const selectedNodes = gridApiObj.policySub2GridApi.api.getSelectedRows();

            if (selectedNodes.length === 0) {
                alert("삭제할 항목을 선택해주세요.");
                return;
            }

            if (window.confirm("삭제하시겠습니까?")) {
                const selectedValueData = _.map(selectedNodes, "id").join("|");

                axiosConf.post("/api/setting/geoDomestic/deleteInfoDomesticSub2", { id: selectedValueData }).then(res => {
                    alert("삭제되었습니다.");

                    setSelectRegionData({
                        ...selectRegionData,
                        sub2Id: null,
                        sub2Name: ""
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

                    if (searchIpResult === null) {
                        getPolicyIpListData();
                    } else {
                        searchIpBandData();
                    }
                });
            }
        }
    }

    const resetEvt = useCallback(() => {
        setSchItem(resetSchItem);
        setSearchIpResult(null);

        setMaxPageIpList(0);
        setCurrentPageIpList(0);
        setTotalIpList(0);
        setIpListData([]);

        setSelectRegionData({
            primaryId: null,
            sub1Id: null,
            sub2Id: null,
            primaryName: "-",
            sub1Name: "",
            sub2Name: "",
            modifyFlag: false
        });
    }, []);

    const applyIpServer = () => {
        if (window.confirm("적용하시겠습니까?")) {
            axiosConf.post("/api/set/applyDomestic", {}).then(res => {
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
                <Grid item md={2}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    시/도 ( Total: <Typography component="span"
                                        className="font-blue font-bold">{numberWithCommas(primaryData.length)}</Typography> )
                                </Grid>
                                {
                                    searchIpResult === null && <Grid item sm={2} textAlign="right">
                                        <Typography component="span" color="textPrimary" variant="h6"
                                            sx={{ mr: 1.5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faMinus} onClick={onDeletePrimaryPolicy} />
                                        </Typography>
                                        <WindowOpener className="inline-block"
                                            url="/popup/setting/ipBand/domestic/reg/region/0" width="800"
                                            height="600" bridge={getPolicyRegionData}
                                            state={{
                                                primaryId: null,
                                                sub1Id: null,
                                                sub2Id: null,
                                                primaryName: "-",
                                                sub1Name: "",
                                                sub2Name: "",
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
                                    gridName="policyPrimaryGridApi"
                                    columnDefs={primaryColumnDefs}
                                    rowData={primaryData}
                                    rowMultiSelectWithClick={false}
                                    isRowSelectable={(rowNode) => rowNode.data.modifyFlag}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellRegionClick}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={2}>
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    시/군/구 ( Total: <Typography component="span"
                                        className="font-blue font-bold">{numberWithCommas(sub1Data.length)}</Typography> )
                                </Grid>
                                {
                                    searchIpResult === null && selectRegionData.primaryId && <Grid item sm={2} textAlign="right">
                                        <Typography component="span" color="textPrimary" variant="h6"
                                            sx={{ mr: 1.5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faMinus} onClick={onDeleteSub1Policy} />
                                        </Typography>
                                        <WindowOpener className="inline-block"
                                            url="/popup/setting/ipBand/domestic/reg/region/0" width="800"
                                            height="600" bridge={getPolicyRegionData}
                                            state={{
                                                primaryId: selectRegionData.primaryId,
                                                sub1Id: null,
                                                sub2Id: null,
                                                primaryName: selectRegionData.primaryName,
                                                sub1Name: "",
                                                sub2Name: "",
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
                                    gridName="policySub1GridApi"
                                    columnDefs={sub1ColumnDefs}
                                    rowData={sub1Data}
                                    rowMultiSelectWithClick={false}
                                    isRowSelectable={(rowNode) => rowNode.data.modifyFlag}
                                    onCellMouseDown={onCellMouseDown}
                                    onCellClicked={onCellRegionClick}
                                    onGridReady={onGridReady}
                                    handleResize={handleResize} />
                            </Box>
                        </CardContent>
                    </Card>
                </Grid>

                <Grid item md={2} pr={1} className="right_underline">
                    <Card>
                        <CardHeader title={(
                            <Grid container>
                                <Grid item sm={10}>
                                    읍/면/동 ( Total: <Typography component="span"
                                        className="font-blue font-bold">{numberWithCommas(sub2Data.length)}</Typography> )
                                </Grid>
                                {
                                    searchIpResult === null && selectRegionData.primaryId && selectRegionData.sub1Id && <Grid item sm={2} textAlign="right">
                                        <Typography component="span" color="textPrimary" variant="h6"
                                            sx={{ mr: 1.5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faMinus} onClick={onDeleteSub2Policy} />
                                        </Typography>
                                        <WindowOpener className="inline-block"
                                            url="/popup/setting/ipBand/domestic/reg/region/0" width="800"
                                            height="600" bridge={getPolicyRegionData}
                                            state={{
                                                primaryId: selectRegionData.primaryId,
                                                sub1Id: selectRegionData.sub1Id,
                                                sub2Id: null,
                                                primaryName: selectRegionData.primaryName,
                                                sub1Name: selectRegionData.sub1Name,
                                                sub2Name: "",
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
                                    gridName="policySub2GridApi"
                                    columnDefs={sub2ColumnDefs}
                                    rowData={sub2Data}
                                    rowMultiSelectWithClick={false}
                                    isRowSelectable={(rowNode) => rowNode.data.modifyFlag}
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
                                        className="font-blue">{selectRegionData.primaryName + selectRegionData.sub1Name + selectRegionData.sub2Name}</Typography>,
                                    Total: <Typography component="span"
                                        className="font-blue font-bold">{numberWithCommas(totalIpList)}</Typography> )
                                </Grid>
                                <Grid item sm={2} textAlign="right">
                                    {
                                        (searchIpResult !== null || selectRegionData.primaryId) &&
                                        <Typography component="span" color="textPrimary" variant="h6"
                                            sx={{ mr: 1.5, cursor: "pointer" }}>
                                            <FontAwesomeIcon icon={faMinus} onClick={onDeleteIpListPolicy} />
                                        </Typography>
                                    }
                                    {
                                        searchIpResult === null && selectRegionData.primaryId &&
                                        <WindowOpener className="inline-block"
                                            url="/popup/setting/ipBand/domestic/reg/ip/0" width="1000"
                                            height="530" bridge={getPolicyRegionData}
                                            state={selectRegionData}>
                                            <Typography component="span" color="textPrimary" variant="h6"
                                                sx={{ mr: 1.5, cursor: "pointer" }}>
                                                <FontAwesomeIcon icon={faPlus} />
                                            </Typography>
                                        </WindowOpener>
                                    }
                                    <Button variant="outlined" size="small" color="inherit"
                                        sx={{ height: "0 !important", padding: ".53rem !important" }}
                                        onClick={applyIpServer}>서버 적용</Button>
                                </Grid>
                            </Grid>
                        )} />
                        <CardContent className="policyGridEl">
                            <Box component="div" className="ag-theme-alpine" sx={{ height: "100%" }}
                                onContextMenu={(e) => e.preventDefault()} onScrollCapture={onScrollLoadEvent}>
                                <AgGridModule
                                    gridName="policyIpGridApi"
                                    columnDefs={ipListColumnDefs}
                                    rowData={ipListData}
                                    isRowSelectable={(rowNode) => rowNode.data.modifyFlag}
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

export default DomesticIndex;