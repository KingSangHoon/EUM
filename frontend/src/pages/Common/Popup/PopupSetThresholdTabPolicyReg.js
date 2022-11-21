import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import { useParams, useLocation } from 'react-router-dom';
import { styled } from '@mui/material/styles';
import { Card, CardHeader, CardContent, Button, Box, TextField, FormControl, Paper, Chip, IconButton } from '@mui/material';

import { SearchOutlined } from '@ant-design/icons';

import axiosConf from '../../../axios';
import { hiddenComponentPopup, pageResources, uriResources, tcpResources, udpResources, ipResources, trafficResources } from '../../../lib/common';
import Loader from '../../../components/Loader';
import ModalSearchFormResources from '../Modal/ModalSearchFormResources';
import SetThresholdTable from '../../Setting/SetThresholdTable';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const PopupSetThresholdTabPolicyReg = () => {
    const params = useParams();
    const search = useLocation().search;
    const target = new URLSearchParams(search).get("target");
    let resourceItem = [];

    if (target === "transaction") {
        resourceItem = pageResources;
    } else if (target === "uri") {
        resourceItem = uriResources;
    } else if (target === "tcp") {
        resourceItem = tcpResources;
    } else if (target === "udp") {
        resourceItem = udpResources;
    } else if (target === "ip") {
        resourceItem = ipResources;
    } else if (target === "traffic") {
        resourceItem = trafficResources;
    }

    const [showLoader, setShowLoader] = useState(false);

    const [policyName, setPolicyName] = useState("");

    const [selectResource, setSelectResource] = useState([]);
    const [criticalResource, setCriticalResource] = useState([]);
    const [open, setOpen] = useState(false);

    const [changeKey, setChangeKey] = useState(0);

    useEffect(() => {
        if (params.id !== "0") {
            getFindoneInfo();
        }

        hiddenComponentPopup();
        window.addEventListener("resize", hiddenComponentPopup);

        return () => {
            window.removeEventListener('resize', hiddenComponentPopup);
        }
    }, []);

    const getFindoneInfo = () => {
        setShowLoader(true);

        axiosConf.get("/api/setting/threshold/find/policy/" + target + "/" + params.id).then(res => {
            let usedResourceArr = [];
            let levelDataArr = [];

            _.forEach(resourceItem, (obj) => {
                _.forEach(obj.children, (childObj) => {
                    if (res.data.usedResource.indexOf(childObj.key) !== -1) {
                        usedResourceArr.push({
                            name: childObj.name,
                            key: childObj.key,
                            isTcpError: childObj.isTcpError
                        });

                        levelDataArr.push({
                            name: childObj.name,
                            key: childObj.key,
                            defaultCheck: false,
                            isTcpError: childObj.isTcpError,
                            level1Field: res.data.threshold[childObj.key + "Level1"] || "",
                            level2Field: res.data.threshold[childObj.key + "Level2"] || "",
                            level3Field: res.data.threshold[childObj.key + "Level3"] || "",
                            level4Field: res.data.threshold[childObj.key + "Level4"] || "",
                            level5Field: res.data.threshold[childObj.key + "Level5"] || ""
                        });
                    } else {
                        // TCP Error
                        if (res.data.usedResourceError) {
                            if (res.data.usedResourceError.indexOf(childObj.key) !== -1) {
                                usedResourceArr.push({
                                    name: childObj.name,
                                    key: childObj.key,
                                    isTcpError: childObj.isTcpError
                                });

                                levelDataArr.push({
                                    name: childObj.name,
                                    key: childObj.key,
                                    defaultCheck: false,
                                    isTcpError: childObj.isTcpError,
                                    level1Field: res.data.thresholdError[childObj.key + "Level1"] || "",
                                    level2Field: res.data.thresholdError[childObj.key + "Level2"] || "",
                                    level3Field: res.data.thresholdError[childObj.key + "Level3"] || "",
                                    level4Field: res.data.thresholdError[childObj.key + "Level4"] || "",
                                    level5Field: res.data.thresholdError[childObj.key + "Level5"] || ""
                                });
                            }
                        }
                    }
                });
            });

            setPolicyName(res.data.threshold.policyName);
            setSelectResource(usedResourceArr);
            setChangeKey(changeKey + 1);
            setCriticalResource(levelDataArr);
            setShowLoader(false);
        });
    }

    const defaultThresholdValue = useCallback((checkData) => {
        setShowLoader(true);

        axiosConf.get("/api/setting/threshold/find/" + target).then(res => {
            let cloneCriticalResource = _.cloneDeep(criticalResource);

            if (res.data.threshold) {
                const checkDataKey = _.map(checkData, "key");

                _.forEach(cloneCriticalResource, (obj) => {
                    if (checkDataKey.indexOf(obj.key) !== -1) {
                        obj.level1Field = res.data.threshold[obj.key + "Level1"] || "";
                        obj.level2Field = res.data.threshold[obj.key + "Level2"] || "";
                        obj.level3Field = res.data.threshold[obj.key + "Level3"] || "";
                        obj.level4Field = res.data.threshold[obj.key + "Level4"] || "";
                        obj.level5Field = res.data.threshold[obj.key + "Level5"] || "";
                    }
                });
            } else {
                _.forEach(cloneCriticalResource, (obj) => {
                    obj.level1Field = "";
                    obj.level2Field = "";
                    obj.level3Field = "";
                    obj.level4Field = "";
                    obj.level5Field = "";
                });
            }

            setChangeKey(changeKey + 1);
            setCriticalResource(cloneCriticalResource);
            setShowLoader(false);
        });
    }, [criticalResource, changeKey]);

    const handleOpen = () => setOpen(true);

    const handleClose = useCallback(() => {
        setOpen(false);
    }, []);

    const bridgeModalResource = useCallback((data) => {
        setShowLoader(true);

        setTimeout(() => {
            let levelDataArr = [];

            _.forEach(data, (obj) => {
                const filterExistData = _.filter(criticalResource, (existObj) => {
                    return existObj.key === obj.key;
                });

                if (filterExistData.length > 0) {
                    levelDataArr.push(filterExistData[0]);
                } else {
                    levelDataArr.push({
                        name: obj.name,
                        key: obj.key,
                        defaultCheck: false,
                        isTcpError: obj.isTcpError,
                        level1Field: "",
                        level2Field: "",
                        level3Field: "",
                        level4Field: "",
                        level5Field: ""
                    });
                }
            });

            setSelectResource(data);
            setChangeKey(changeKey + 1);
            setCriticalResource(levelDataArr);
            setShowLoader(false);
        }, 0);
    }, [criticalResource, changeKey]);

    const handleResourceDelete = (targetId) => {
        setShowLoader(true);

        setTimeout(() => {
            const filterSelectData = _.filter(selectResource, (obj) => {
                return obj.key !== targetId;
            });

            const filterCriticalData = _.filter(criticalResource, (obj) => {
                return obj.key !== targetId;
            });

            setSelectResource(filterSelectData);
            setChangeKey(changeKey + 1);
            setCriticalResource(filterCriticalData);
            setShowLoader(false);
        }, 0);
    }

    const handleThresholdValue = useCallback((data) => {
        setShowLoader(true);

        setTimeout(() => {
            setCriticalResource(data);
            setShowLoader(false);
        }, 0);
    }, []);

    const clearThresholdValue = useCallback((data) => {
        setShowLoader(true);

        setTimeout(() => {
            setChangeKey(changeKey + 1);
            setCriticalResource(data);
            setShowLoader(false);
        }, 0);
    }, [changeKey]);

    const handleKeyPress = (e) => {
        setPolicyName(e.target.value);
    }

    const onSavePolicyValidation = () => {
        if (policyName === "") {
            alert("정책 명을 입력해주세요.");
            return;
        }

        const filterNotTcpError = _.filter(selectResource, (obj) => {
            return !obj.isTcpError;
        });

        const filterTcpError = _.filter(selectResource, (obj) => {
            return obj.isTcpError;
        });

        let requestData = {
            data: {
                type: 1,
                policyName: policyName
            },
            activeField: _.map(filterNotTcpError, "key"),
        };

        if (filterTcpError.length > 0) {
            requestData.data.isTcpError = true;
            requestData.errorField = _.map(filterTcpError, "key");
            requestData.errorData = {};
        }

        if (params.id !== "0") {
            requestData.data.id = parseFloat(params.id);
        }

        _.forEach(criticalResource, (obj) => {
            const targetPushObj = obj.isTcpError ? requestData.errorData : requestData.data;

            targetPushObj[obj.key + "Use"] = true;

            if (obj.level1Field !== "") targetPushObj[obj.key + "Level1"] = parseFloat(obj.level1Field);
            if (obj.level2Field !== "") targetPushObj[obj.key + "Level2"] = parseFloat(obj.level2Field);
            if (obj.level3Field !== "") targetPushObj[obj.key + "Level3"] = parseFloat(obj.level3Field);
            if (obj.level4Field !== "") targetPushObj[obj.key + "Level4"] = parseFloat(obj.level4Field);
            if (obj.level5Field !== "") targetPushObj[obj.key + "Level5"] = parseFloat(obj.level5Field);
        });

        applyPolicySave(requestData);
    }

    const applyPolicySave = (requestData) => {
        const flag = params.id !== "0" ? "update" : "insert";

        axiosConf.post("/api/setting/threshold/" + flag + "/policy/" + target, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess();
            window.close();
        });
    }

    // import content
    const ModalResourceContent = useMemo(() => (
        <ModalSearchFormResources open={open} resourceItem={resourceItem} selectResource={selectResource} onClose={handleClose} onSuccess={bridgeModalResource} />
    ), [open]);

    const ThresholdTbContent = useMemo(() => (
        <SetThresholdTable children={criticalResource} changeKey={changeKey}
            handleThresholdValue={handleThresholdValue} defaultThresholdValue={defaultThresholdValue} clearThresholdValue={clearThresholdValue} />
    ), [criticalResource, changeKey]);

    return (
        <Card>
            <CardHeader title="레벨 정책 설정"></CardHeader>
            <CardContent>
                <FormControl sx={{ width: "15%", mt: .5 }}>정책 명</FormControl>
                <FormControl sx={{ width: "85%" }}>
                    <TextField
                        name="policyName"
                        placeholder="정책 명"
                        size="small"
                        value={policyName}
                        onChange={handleKeyPress}
                    />
                </FormControl>

                <FormControl sx={{ width: "15%", mt: .5 }}>자원</FormControl>
                <FormControl sx={{ width: "85%" }}>
                    <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }} mt={1} mb={1} className="popupEl">
                        <IconButton edge="start" size="small" className="search-btn" onClick={handleOpen}>
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
                                    selectResource.length === 0 && <ListItem sx={{ mt: .3 }} className="font-gray">Choose Resources</ListItem>
                                }

                                {
                                    _.map(selectResource, (obj, i) => (
                                        <ListItem key={i}>
                                            <Chip size="small" label={obj.name} onDelete={() => handleResourceDelete(obj.key)} />
                                        </ListItem>
                                    ))
                                }
                            </Paper>
                        </Box>
                    </Box>
                </FormControl>

                <Box mb={1} pb={1} className="bottom_underline">
                    {ThresholdTbContent}
                </Box>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
            {ModalResourceContent}
        </Card>
    );
};

export default PopupSetThresholdTabPolicyReg;