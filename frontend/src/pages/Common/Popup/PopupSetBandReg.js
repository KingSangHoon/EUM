import React, { useEffect, useState, useMemo, useCallback } from 'react';
import { useParams } from 'react-router-dom';
import _ from "lodash";
import { Card, CardHeader, CardContent, Button, Box, TextField, FormControl, RadioGroup, Radio, Paper, IconButton, Chip, FormControlLabel, Divider } from '@mui/material';
import { styled } from "@mui/material/styles";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowLeft, faArrowRight, faArrowsLeftRight } from "@fortawesome/free-solid-svg-icons";
import { SearchOutlined } from "@ant-design/icons";

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';
import Loader from '../../../components/Loader';
import PopupSetBandRegMappingTb from './PopupSetBandRegMappingTb';
import ModalSearchFormBandAppPolicy from '../Modal/ModalSearchFormBandAppPolicy';
import ModalSearchFormBandIp from '../Modal/ModalSearchFormBandIp';
import ModalSearchFormBandCollect from '../Modal/ModalSearchFormBandCollect';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const PopupSetBandReg = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);

    const [state, setState] = useState({
        ruleType: "0",
        ruleName: "",
        ipFlow: "2",
        macFlow: "2",
        protocolType: "0",
        portFlow: "2"
    });

    const [appPolicyOpen, setAppPolicyOpen] = useState(false);

    const [collectSearchOpen, setCollectSearchOpen] = useState(false);

    const [ipModalGeoType, setIpModalGeoType] = useState(null);
    const [ipSelectData, setIpSelectData] = useState([]);
    const [ipSearchOpen, setIpSearchOpen] = useState({
        open: false,
        selectedData: []
    });

    const [sourceIpData, setSourceIpData] = useState([]);
    const [targetIpData, setTargetIpData] = useState([]);
    const [sourceMacData, setSourceMacData] = useState([]);
    const [targetMacData, setTargetMacData] = useState([]);
    const [sourcePortData, setSourcePortData] = useState([]);
    const [targetPortData, setTargetPortData] = useState([]);

    const [appSelectData, setAppSelectData] = useState([]);

    useEffect(() => {
        if (params.id !== "0") {
            getFindoneInfo();
        }

        hiddenComponentPopup();
        window.addEventListener("resize", () => {
            hiddenComponentPopup();
        });

        return () => {
            window.removeEventListener('resize', () => {
                hiddenComponentPopup();
            });
        }
    }, []);

    const getFindoneInfo = () => {
        setShowLoader(true);

        axiosConf.get("/api/setting/band/find/" + params.group + "/" + params.id).then(res => {
            const bandRule = res.data.bandRule;

            setState({
                ruleType: (bandRule.isType).toString(),
                ruleName: bandRule.title,
                ipFlow: (bandRule.ipFlow).toString(),
                macFlow: (bandRule.macFlow).toString(),
                protocolType: (bandRule.portocolType).toString(),
                portFlow: (bandRule.portFlow).toString(),
            });

            // Destination IP
            if (bandRule.destinationipStart !== null) {
                setTargetIpData([{
                    isCheck: false,
                    range: bandRule.destinationipType === 0 ? "single" : "range",
                    startField: bandRule.destinationipStart,
                    endField: bandRule.destinationipEnd,
                    txt: bandRule.destinationipType === 1 ? bandRule.destinationipStart + " ~ " + bandRule.destinationipEnd : bandRule.destinationipStart
                }]);
            }

            // Destination MAC
            if (bandRule.destinationmac !== null) {
                setTargetMacData([{
                    isCheck: false,
                    range: "single",
                    startField: bandRule.destinationmac,
                    endField: bandRule.destinationmac,
                    txt: bandRule.destinationmac
                }]);
            }

            // Destination Port
            if (bandRule.destinationportStart !== null) {
                setTargetPortData([{
                    isCheck: false,
                    range: bandRule.destinationportType === 0 ? "single" : "range",
                    startField: bandRule.destinationportStart,
                    endField: bandRule.destinationportEnd,
                    txt: bandRule.destinationportType === 1 ? bandRule.destinationportStart + " ~ " + bandRule.destinationportEnd : bandRule.destinationportStart
                }]);
            }

            // Application
            if (bandRule.application) {
                setAppSelectData([{ name: bandRule.application.application.title }]);
            }

            // Source IP
            let sourceIpData = [];
            let sourceIpUniqId = [];

            _.forEach(bandRule.sourceIpItem, (obj) => {
                sourceIpData.push({
                    isCheck: false,
                    range: obj.type === 0 ? "single" : "range",
                    startField: obj.sourceipStart,
                    endField: obj.sourceipEnd,
                    txt: obj.type === 1 ? obj.sourceipStart + " ~ " + obj.sourceipEnd : obj.sourceipStart
                });
            });

            // Source IP > Geo > ISP
            _.forEach(bandRule.ispItem, (ispObj) => {
                ispObj.uniqId = "isp&" + ispObj.id;
                sourceIpUniqId.push(ispObj.uniqId);

                sourceIpData.push({
                    type: "isp",
                    searchInfo: ispObj,
                    isCheck: false,
                    range: "single",
                    startField: ispObj.name,
                    endField: ispObj.name,
                    txt: "ISP - " + ispObj.name
                });
            });

            // Source IP > Geo > IDC
            _.forEach(bandRule.idcItem, (idcObj) => {
                idcObj.uniqId = "idc&" + idcObj.id;
                sourceIpUniqId.push(idcObj.uniqId);

                sourceIpData.push({
                    type: "idc",
                    searchInfo: idcObj,
                    isCheck: false,
                    range: "single",
                    startField: idcObj.name,
                    endField: idcObj.name,
                    txt: "IDC - " + idcObj.name
                });
            });

            if (bandRule.isDomestic) {
                // Source IP > Geo > Domestic
                if (bandRule.domesticItem.primary && bandRule.domesticItem.sub1 && bandRule.domesticItem.sub2) {
                    _.forEach(bandRule.domesticItem.sub2, (sub2Obj) => {
                        sub2Obj.uniqId = "sub2&" + sub2Obj.primaryId + "_" + sub2Obj.sub1Id + "_" + sub2Obj.id;
                        sourceIpUniqId.push(sub2Obj.uniqId);

                        sourceIpData.push({
                            type: "sub2",
                            searchInfo: sub2Obj,
                            isCheck: false,
                            range: "single",
                            startField: sub2Obj.name,
                            endField: sub2Obj.name,
                            txt: "읍/면/동 - " + bandRule.domesticItem.primary[0].name + ">" + bandRule.domesticItem.sub1[0].name + ">" + sub2Obj.name
                        });
                    });

                    setIpModalGeoType("4");
                } else if (bandRule.domesticItem.primary && bandRule.domesticItem.sub1) {
                    _.forEach(bandRule.domesticItem.sub1, (sub1Obj) => {
                        sub1Obj.uniqId = "sub1&" + sub1Obj.primaryId + "_" + sub1Obj.id;
                        sourceIpUniqId.push(sub1Obj.uniqId);

                        sourceIpData.push({
                            type: "sub1",
                            searchInfo: sub1Obj,
                            isCheck: false,
                            range: "single",
                            startField: sub1Obj.name,
                            endField: sub1Obj.name,
                            txt: "시/군/구 - " + bandRule.domesticItem.primary[0].name + ">" + sub1Obj.name
                        });
                    });

                    setIpModalGeoType("3");
                } else if (bandRule.domesticItem.primary) {
                    _.forEach(bandRule.domesticItem.primary, (primaryObj) => {
                        primaryObj.uniqId = "primary&" + primaryObj.id;
                        sourceIpUniqId.push(primaryObj.uniqId);

                        sourceIpData.push({
                            type: "primary",
                            searchInfo: primaryObj,
                            isCheck: false,
                            range: "single",
                            startField: primaryObj.name,
                            endField: primaryObj.name,
                            txt: "시/도 - " + primaryObj.name
                        });
                    });

                    setIpModalGeoType("2");
                }
            } else if (bandRule.isCountry) {
                // Source IP > Geo > Country
                _.forEach(bandRule.countryItem, (countryObj) => {
                    countryObj.uniqId = "country&" + countryObj.countryId;
                    sourceIpUniqId.push(countryObj.uniqId);

                    sourceIpData.push({
                        type: "country",
                        searchInfo: countryObj,
                        isCheck: false,
                        range: "single",
                        startField: countryObj.countryName,
                        endField: countryObj.countryName,
                        txt: "국가 - " + countryObj.countryName
                    });
                });

                setIpModalGeoType("1");
            } else if (bandRule.isContinent) {
                // Source IP > Geo > Continent
                _.forEach(bandRule.continentItem, (continentObj) => {
                    continentObj.uniqId = "continent&" + continentObj.continentId;
                    sourceIpUniqId.push(continentObj.uniqId);

                    sourceIpData.push({
                        type: "continent",
                        searchInfo: continentObj,
                        isCheck: false,
                        range: "single",
                        startField: continentObj.continentName,
                        endField: continentObj.continentName,
                        txt: "대륙 - " + continentObj.continentName
                    });
                });
            }

            // Source MAC
            let sourceMacData = [];

            _.forEach(bandRule.sourceMacItem, (obj) => {
                sourceMacData.push({
                    isCheck: false,
                    range: "single",
                    startField: obj.sourceMac,
                    endField: obj.sourceMac,
                    txt: obj.sourceMac
                });
            });

            // Source Port
            let sourcePortData = [];

            _.forEach(bandRule.sourcePortItem, (obj) => {
                sourcePortData.push({
                    isCheck: false,
                    range: obj.type === 0 ? "single" : "range",
                    startField: obj.sourceportStart,
                    endField: obj.sourceportEnd,
                    txt: obj.type === 1 ? obj.sourceportStart + " ~ " + obj.sourceportEnd : obj.sourceportStart
                });
            });

            setSourceIpData(sourceIpData);
            setIpSelectData(sourceIpUniqId);
            setSourceMacData(sourceMacData);
            setSourcePortData(sourcePortData);
            setShowLoader(false);
        });
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleAppPolicyOpen = () => setAppPolicyOpen(true);

    const handleAppPolicyClose = useCallback(() => {
        setAppPolicyOpen(false);
    }, []);

    const bridgeModalAppPolicy = useCallback((data) => {
        if (data) {
            const addIpData = {
                isCheck: false,
                range: "single",
                startField: data.ipAddr,
                endField: data.ipAddr,
                txt: data.ipAddr
            };

            const addMacData = {
                isCheck: false,
                range: "single",
                startField: data.ipMac,
                endField: data.ipMac,
                txt: data.ipMac
            };

            const addPortData = {
                isCheck: false,
                range: data.portTypeIsRange,
                startField: data.startPort,
                endField: data.endPort,
                txt: data.portTypeIsRange === "range" ? data.startPort + " ~ " + data.endPort : data.startPort
            };

            setTargetIpData([addIpData]);
            setTargetMacData([addMacData]);
            setTargetPortData([addPortData]);
            setAppSelectData([data]);
        }
    }, []);

    const handleCollectOpen = () => setCollectSearchOpen(true);

    const handleCollectClose = useCallback(() => {
        setCollectSearchOpen(false);
    }, []);

    const bridgeModalCollect = useCallback((data) => {
        if (data) {
            const existIp = _.map(sourceIpData, "txt");
            const existMac = _.map(sourceMacData, "txt");
            const existPort = _.map(sourcePortData, "txt");

            const selectUniqIp = _.uniqBy(data, "dstIp");
            const selectUniqMac = _.uniqBy(data, "dstMac");
            const selectUniqPort = _.uniqBy(data, "dstPort");

            let filterNewIp = [];
            let filterNewMac = [];
            let filterNewPort = [];

            _.forEach(selectUniqIp, (obj) => {
                if (existIp.indexOf(obj.dstIp) === -1) {
                    filterNewIp.push({
                        isCheck: false,
                        range: "single",
                        startField: obj.dstIp,
                        endField: obj.dstIp,
                        txt: obj.dstIp
                    });
                }
            });

            _.forEach(selectUniqMac, (obj) => {
                if (existMac.indexOf(obj.dstMac) === -1) {
                    filterNewMac.push({
                        isCheck: false,
                        range: "single",
                        startField: obj.dstMac,
                        endField: obj.dstMac,
                        txt: obj.dstMac
                    });
                }
            });

            _.forEach(selectUniqPort, (obj) => {
                if (existPort.indexOf(obj.dstPort) === -1) {
                    filterNewPort.push({
                        isCheck: false,
                        range: "single",
                        startField: obj.dstPort,
                        endField: obj.dstPort,
                        txt: obj.dstPort
                    });
                }
            });

            setSourceIpData([...sourceIpData, ...filterNewIp]);
            setSourceMacData([...sourceMacData, ...filterNewMac]);
            setSourcePortData([...sourcePortData, ...filterNewPort]);
        }
    }, [sourceIpData, sourceMacData, sourcePortData]);

    const handleIpOpen = () => {
        const filterData = _.filter(sourceIpData, (obj) => {
            return obj.searchInfo;
        });

        setIpSearchOpen({
            open: true,
            selectedData: _.map(filterData, "searchInfo")
        });
    }

    const handleIpClose = useCallback(() => {
        setIpSearchOpen({ ...ipSearchOpen, open: false });
    }, [ipSearchOpen]);

    const bridgeModalIp = useCallback((data) => {
        if (data) {
            let cloneMappingData = _.cloneDeep(sourceIpData);
            const selectId = _.map(data, "uniqId");

            cloneMappingData = _.filter(cloneMappingData, (obj) => {
                return !obj.searchInfo || selectId.indexOf(obj.searchInfo.uniqId) !== -1;
            });

            _.forEach(data, (obj) => {
                if (ipSelectData.indexOf(obj.uniqId) === -1) {
                    cloneMappingData.push({
                        type: obj.uniqId.split("&")[0],
                        searchInfo: obj,
                        isCheck: false,
                        range: "single",
                        startField: obj.name,
                        endField: obj.name,
                        txt: obj.txt
                    });
                }
            });

            setSourceIpData(cloneMappingData);
            setIpSelectData(selectId);
        }
    }, [sourceIpData, ipSelectData]);

    const onSavePolicyValidation = () => {
        if (state.ruleName === "") {
            alert("규칙 명을 입력해주세요.");
            return;
        }

        if (state.type === "1" && appSelectData.length === 0) {
            alert("어플리케이션 정책을 선택해주세요.");
            return;
        }

        const customSourceIp = _.filter(sourceIpData, (obj) => {
            return !obj.searchInfo;
        });

        const sourceIsp = _.filter(sourceIpData, (obj) => {
            return obj.type === "isp";
        });

        const sourceIdc = _.filter(sourceIpData, (obj) => {
            return obj.type === "idc";
        });

        const sourceContinent = _.filter(sourceIpData, (obj) => {
            return obj.type === "continent";
        });

        const sourceCountry = _.filter(sourceIpData, (obj) => {
            return obj.type === "country";
        });

        const sourcePrimary = _.filter(sourceIpData, (obj) => {
            return obj.type === "primary";
        });

        const sourceSub1 = _.filter(sourceIpData, (obj) => {
            return obj.type === "sub1";
        });

        const sourceSub2 = _.filter(sourceIpData, (obj) => {
            return obj.type === "sub2";
        });

        const sourceIspInfo = _.map(sourceIsp, "searchInfo");
        const sourceIdcInfo = _.map(sourceIdc, "searchInfo");
        const sourceContinentInfo = _.map(sourceContinent, "searchInfo");
        const sourceCountryInfo = _.map(sourceCountry, "searchInfo");

        let requestData = {
            bandId: Number(params.group),
            type: state.ruleType,
            title: state.ruleName,
            sourceIp: [],
            ipFlow: parseInt(state.ipFlow),
            destinationIp: {
                destinationIpType: targetIpData.length === 0 ? null : targetIpData[0].range === "single" ? 0 : 1,
                destinationIpStart: targetIpData.length === 0 ? null : targetIpData[0].startField,
                destinationIpEnd: targetIpData.length === 0 ? null : targetIpData[0].endField
            },
            sourctMac: [],
            macFlow: parseInt(state.macFlow),
            destinationMac: targetMacData.length === 0 ? null : targetMacData[0].startField,
            protocolType: parseInt(state.protocolType),
            sourcePort: [],
            portFlow: parseInt(state.portFlow),
            destinationPort: {
                destinationPortType: targetPortData.length === 0 ? null : targetPortData[0].range === "single" ? 0 : 1,
                destinationPortStart: targetPortData.length === 0 ? null : Number(targetPortData[0].startField),
                destinationPortEnd: targetPortData.length === 0 ? null : Number(targetPortData[0].endField)
            },
            isIsp: sourceIsp.length > 0,
            ispId: _.map(sourceIspInfo, "id"),
            isIdc: sourceIdc.length > 0,
            idcId: _.map(sourceIdcInfo, "id"),
            isContinent: sourceContinent.length > 0,
            continentId: _.map(sourceContinentInfo, "continentId"),
            isCountry: sourceCountry.length > 0,
            isDomestic: false,
            countryId: _.map(sourceCountryInfo, "countryId")
        };

        _.forEach(customSourceIp, (obj) => {
            requestData.sourceIp.push({
                sourceIpType: obj.range === "single" ? 0 : 1,
                sourceIpStart: obj.startField,
                sourceIpEnd: obj.endField
            });
        });

        _.forEach(sourceMacData, (obj) => {
            requestData.sourctMac.push(obj.startField);
        });

        _.forEach(sourcePortData, (obj) => {
            requestData.sourcePort.push({
                sourcePortType: obj.range === "single" ? 0 : 1,
                sourcePortStart: Number(obj.startField),
                sourcePortEnd: Number(obj.endField)
            });
        });

        // Source IP > Domestic
        if (sourcePrimary.length > 0) {
            const sourceDomesticInfo = _.map(sourcePrimary, "searchInfo");

            requestData.isDomestic = true;
            requestData.domesticId = {
                primaryId: _.map(sourceDomesticInfo, "id"),
                sub1Id: [],
                sub2Id: []
            };
        } else if (sourceSub1.length > 0) {
            const sourceDomesticInfo = _.map(sourceSub1, "searchInfo");

            requestData.isDomestic = true;
            requestData.domesticId = {
                primaryId: _.map(sourceDomesticInfo, "primaryId"),
                sub1Id: _.map(sourceDomesticInfo, "id"),
                sub2Id: []
            };
        } else if (sourceSub2.length > 0) {
            const sourceDomesticInfo = _.map(sourceSub2, "searchInfo");

            requestData.isDomestic = true;
            requestData.domesticId = {
                primaryId: _.map(sourceDomesticInfo, "primaryId"),
                sub1Id: _.map(sourceDomesticInfo, "sub1Id"),
                sub2Id: _.map(sourceDomesticInfo, "id")
            };
        }

        // 어플리케이션 정책
        if (state.ruleType === "1") {
            requestData.applicationId = appSelectData[0].applicationId;
            requestData.applicationIdSub = appSelectData[0].id;
        }

        applyPolicySave(requestData);
    }

    const applyPolicySave = (requestData) => {
        const reqUrl = params.id === "0" ? "insertRule" : "updateRule/" + params.group + "/" + params.id;

        axiosConf.post("/api/setting/band/" + reqUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    const mappingSourceIpBridge = useCallback((data) => {
        if (data.mappingData) {
            setSourceIpData(data.mappingData);
        }
    }, []);

    const mappingTargetIpBridge = useCallback((data) => {
        if (data.mappingData) {
            setTargetIpData(data.mappingData);
        }
    }, []);

    const mappingSourceMacBridge = useCallback((data) => {
        if (data.mappingData) {
            setSourceMacData(data.mappingData);
        }
    }, []);

    const mappingTargetMacBridge = useCallback((data) => {
        if (data.mappingData) {
            setTargetMacData(data.mappingData);
        }
    }, []);

    const mappingSourcePortBridge = useCallback((data) => {
        if (data.mappingData) {
            setSourcePortData(data.mappingData);
        }
    }, []);

    const mappingTargetPortBridge = useCallback((data) => {
        if (data.mappingData) {
            setTargetPortData(data.mappingData);
        }
    }, []);

    // import content
    const SourceIpTbContent = useMemo(() => (
        <PopupSetBandRegMappingTb mappingData={sourceIpData} mappingBridge={mappingSourceIpBridge} text="IP" />
    ), [sourceIpData]);

    const TargetIpTbContent = useMemo(() => (
        <PopupSetBandRegMappingTb mappingData={targetIpData} mappingBridge={mappingTargetIpBridge} text="IP" target={true} />
    ), [targetIpData]);

    const SourceMacTbContent = useMemo(() => (
        <PopupSetBandRegMappingTb mappingData={sourceMacData} mappingBridge={mappingSourceMacBridge} text="MAC" single={true} />
    ), [sourceMacData]);

    const TargetMacTbContent = useMemo(() => (
        <PopupSetBandRegMappingTb mappingData={targetMacData} mappingBridge={mappingTargetMacBridge} text="MAC" single={true} target={true} />
    ), [targetMacData]);

    const SourcePortTbContent = useMemo(() => (
        <PopupSetBandRegMappingTb mappingData={sourcePortData} mappingBridge={mappingSourcePortBridge} text="Port" />
    ), [sourcePortData]);

    const TargetPortTbContent = useMemo(() => (
        <PopupSetBandRegMappingTb mappingData={targetPortData} mappingBridge={mappingTargetPortBridge} text="Port" target={true} />
    ), [targetPortData]);

    const ModalAppPolicyContent = useMemo(() => (
        <ModalSearchFormBandAppPolicy open={appPolicyOpen} onClose={handleAppPolicyClose} onSuccess={bridgeModalAppPolicy} />
    ), [appPolicyOpen]);

    const ModalBandIpContent = useMemo(() => (
        <ModalSearchFormBandIp open={ipSearchOpen.open} onClose={handleIpClose} onSuccess={bridgeModalIp} selectedData={ipSearchOpen.selectedData} geoType={ipModalGeoType} />
    ), [ipSearchOpen, ipModalGeoType]);

    const ModalBandCollectContent = useMemo(() => (
        <ModalSearchFormBandCollect open={collectSearchOpen} onClose={handleCollectClose} onSuccess={bridgeModalCollect} />
    ), [collectSearchOpen]);

    return (
        <Card>
            <CardHeader title="규칙 추가"></CardHeader>
            <CardContent>
                <Box sx={{ backgroundColor: "rgba(240, 240, 240, 0.3)" }}>
                    <FormControl sx={{ width: "10%", mt: .5 }}>규칙 종류</FormControl>
                    <FormControl sx={{ width: "90%", mt: .5 }}>
                        <RadioGroup row name="ruleType" onChange={handleKeyPress} value={state.ruleType}>
                            <FormControlLabel value="0" control={<Radio />} label="사용자 정의" />
                            <FormControlLabel value="1" control={<Radio />} label="어플리케이션 정책" />
                        </RadioGroup>
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: 1 }}>규칙 명</FormControl>
                    <FormControl sx={{ width: "90%", mt: 1 }}>
                        <TextField
                            name="ruleName"
                            placeholder="규칙 명"
                            size="small"
                            value={state.ruleName}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    {
                        state.ruleType === "1" && <>
                            <FormControl sx={{ width: "10%", mt: 1 }}>어플리케이션 정책</FormControl>
                            <FormControl sx={{ width: "90%", mt: 1 }}>
                                <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }} className="popupEl">
                                    <IconButton edge="start" size="small" className="search-btn" onClick={handleAppPolicyOpen}>
                                        <SearchOutlined />
                                    </IconButton>
                                    <Box component="div" flex="1 1">
                                        <Paper
                                            sx={{
                                                display: 'flex',
                                                flexWrap: 'wrap',
                                                listStyle: 'none',
                                                pl: .5,
                                                m: 0,
                                                '& .MuiListItem-root': { p: 0 }
                                            }}
                                            className="border"
                                            component="ul"
                                        >
                                            {
                                                _.map(appSelectData, (obj, i) => (
                                                    <ListItem key={i}>
                                                        <Chip size="small" label={obj.name} />
                                                    </ListItem>
                                                ))
                                            }
                                        </Paper>
                                    </Box>
                                </Box>
                            </FormControl>
                        </>
                    }

                    <FormControl sx={{ width: "10%", mt: 1 }}>수집</FormControl>
                    <FormControl sx={{ width: "90%", mt: 1 }} className="popupEl">
                        <IconButton edge="start" size="small" className="search-btn" onClick={handleCollectOpen}>
                            <SearchOutlined />
                        </IconButton>
                    </FormControl>

                    <Divider sx={{ marginTop: 1 }} />
                </Box>

                {/* IP */}
                <FormControl sx={{ width: "10%", mt: 1 }}></FormControl>
                <FormControl sx={{ width: "7%", mt: 1 }}>Source IP</FormControl>
                <FormControl sx={{ width: "2.5%", mt: 1 }} className="popupEl">
                    <IconButton edge="start" size="small" className="search-btn" onClick={handleIpOpen}>
                        <SearchOutlined />
                    </IconButton>
                </FormControl>
                <FormControl sx={{ width: "80.5%", mt: 1 }}>
                    {SourceIpTbContent}
                </FormControl>

                <FormControl sx={{ width: "10%", textAlign: "center" }}>IP</FormControl>
                <FormControl sx={{ width: "90%", mt: 1 }}><Divider /></FormControl>

                <FormControl sx={{ width: "10%" }}></FormControl>
                <FormControl sx={{ width: "7%" }}>Flow</FormControl>
                <FormControl sx={{ width: "83%" }}>
                    <RadioGroup row name="ipFlow" onChange={handleKeyPress} value={state.ipFlow}>
                        <FormControlLabel value="0" control={<Radio />} label={<FontAwesomeIcon icon={faArrowRight} style={{ paddingLeft: ".5rem" }} />} />
                        <FormControlLabel value="1" control={<Radio />} label={<FontAwesomeIcon icon={faArrowLeft} style={{ paddingLeft: ".5rem" }} />} />
                        <FormControlLabel value="2" control={<Radio />} label={<FontAwesomeIcon icon={faArrowsLeftRight} style={{ paddingLeft: ".5rem" }} />} />
                    </RadioGroup>
                </FormControl>

                <FormControl sx={{ width: "10%", mt: 1 }}></FormControl>
                <FormControl sx={{ width: "90%", mt: 1 }}><Divider /></FormControl>

                <FormControl sx={{ width: "10%" }}></FormControl>
                <FormControl sx={{ width: "7%" }}>Destination IP</FormControl>
                <FormControl sx={{ width: "2.5%" }}></FormControl>
                <FormControl sx={{ width: "80.5%" }}>
                    {TargetIpTbContent}
                </FormControl>

                <Divider sx={{ marginTop: 1 }} />

                {/* MAC */}
                <Box sx={{ backgroundColor: "rgba(240, 240, 240, 0.3)" }}>
                    <FormControl sx={{ width: "10%", mt: 1 }}></FormControl>
                    <FormControl sx={{ width: "7%", mt: 1 }}>Source MAC</FormControl>
                    <FormControl sx={{ width: "2.5%", mt: 1 }}></FormControl>
                    <FormControl sx={{ width: "80.5%", mt: 1 }}>
                        {SourceMacTbContent}
                    </FormControl>

                    <FormControl sx={{ width: "10%", textAlign: "center" }}>MAC</FormControl>
                    <FormControl sx={{ width: "90%", mt: 1 }}><Divider /></FormControl>

                    <FormControl sx={{ width: "10%" }}></FormControl>
                    <FormControl sx={{ width: "7%" }}>Flow</FormControl>
                    <FormControl sx={{ width: "83%" }}>
                        <RadioGroup row name="macFlow" onChange={handleKeyPress} value={state.macFlow}>
                            <FormControlLabel value="0" control={<Radio />} label={<FontAwesomeIcon icon={faArrowRight} style={{ paddingLeft: ".5rem" }} />} />
                            <FormControlLabel value="1" control={<Radio />} label={<FontAwesomeIcon icon={faArrowLeft} style={{ paddingLeft: ".5rem" }} />} />
                            <FormControlLabel value="2" control={<Radio />} label={<FontAwesomeIcon icon={faArrowsLeftRight} style={{ paddingLeft: ".5rem" }} />} />
                        </RadioGroup>
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: 1 }}></FormControl>
                    <FormControl sx={{ width: "90%", mt: 1 }}><Divider /></FormControl>

                    <FormControl sx={{ width: "10%" }}></FormControl>
                    <FormControl sx={{ width: "7%" }}>Destination MAC</FormControl>
                    <FormControl sx={{ width: "2.5%" }}></FormControl>
                    <FormControl sx={{ width: "80.5%" }}>
                        {TargetMacTbContent}
                    </FormControl>

                    <Divider sx={{ marginTop: 1 }} />
                </Box>

                {/* Protocol */}
                <FormControl sx={{ width: "10%", mt: 1, textAlign: "center" }}>Protocol</FormControl>
                <FormControl sx={{ width: "90%", mt: 1 }}>
                    <RadioGroup row name="protocolType" onChange={handleKeyPress} value={state.protocolType}>
                        <FormControlLabel value="0" control={<Radio />} label="TCP" />
                        <FormControlLabel value="1" control={<Radio />} label="UDP" />
                    </RadioGroup>
                </FormControl>
                <Divider sx={{ marginTop: 1 }} />

                {/* Port */}
                <Box sx={{ backgroundColor: "rgba(240, 240, 240, 0.3)" }}>
                    <FormControl sx={{ width: "10%", mt: 1 }}></FormControl>
                    <FormControl sx={{ width: "7%", mt: 1 }}>Source Port</FormControl>
                    <FormControl sx={{ width: "2.5%", mt: 1 }}></FormControl>
                    <FormControl sx={{ width: "80.5%", mt: 1 }}>
                        {SourcePortTbContent}
                    </FormControl>

                    <FormControl sx={{ width: "10%", textAlign: "center" }}>Port</FormControl>
                    <FormControl sx={{ width: "90%", mt: 1 }}><Divider /></FormControl>

                    <FormControl sx={{ width: "10%" }}></FormControl>
                    <FormControl sx={{ width: "7%" }}>Flow</FormControl>
                    <FormControl sx={{ width: "83%" }}>
                        <RadioGroup row name="portFlow" onChange={handleKeyPress} value={state.portFlow}>
                            <FormControlLabel value="0" control={<Radio />} label={<FontAwesomeIcon icon={faArrowRight} style={{ paddingLeft: ".5rem" }} />} />
                            <FormControlLabel value="1" control={<Radio />} label={<FontAwesomeIcon icon={faArrowLeft} style={{ paddingLeft: ".5rem" }} />} />
                            <FormControlLabel value="2" control={<Radio />} label={<FontAwesomeIcon icon={faArrowsLeftRight} style={{ paddingLeft: ".5rem" }} />} />
                        </RadioGroup>
                    </FormControl>

                    <FormControl sx={{ width: "10%", mt: 1 }}></FormControl>
                    <FormControl sx={{ width: "90%", mt: 1 }}><Divider /></FormControl>

                    <FormControl sx={{ width: "10%" }}></FormControl>
                    <FormControl sx={{ width: "7%" }}>Destination Port</FormControl>
                    <FormControl sx={{ width: "2.5%" }}></FormControl>
                    <FormControl sx={{ width: "80.5%" }}>
                        {TargetPortTbContent}
                    </FormControl>

                    <Divider sx={{ marginTop: 1 }} />
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1, pt: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
            {ModalAppPolicyContent}
            {ModalBandIpContent}
            {ModalBandCollectContent}
        </Card >
    );
};

export default PopupSetBandReg;