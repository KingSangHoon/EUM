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
import SetCriticalValueTable from '../../Setting/SetCriticalValueTable';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const PopupSetCriticalValueTabPolicyReg = () => {
    const params = useParams();
    const search = useLocation().search;
    const target = new URLSearchParams(search).get("target");
    let resourceItem = [];

    if (target === "httpTransaction") {
        resourceItem = pageResources;
    } else if (target === "HttpURI") {
        resourceItem = uriResources;
    } else if (target === "l4TCP") {
        resourceItem = tcpResources;
    } else if (target === "l4UDP") {
        resourceItem = udpResources;
    } else if (target === "l3IP") {
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

        axiosConf.get("/api/setting/critical/findPolicyByPolicyId/" + target + "/" + params.id).then(res => {
            const policyName = res.data.policyInfo ? res.data.policyInfo.title : "";
            let usedResourceArr = [];
            let levelDataArr = [];

            _.forEach(res.data.resourceItem, (obj) => {
                _.forEach(resourceItem, (resourceObj) => {
                    const filterResourceItem = _.filter(resourceObj.children, (resourceChildObj) => {
                        return resourceChildObj.key === obj.resource_code;
                    });

                    if (filterResourceItem.length > 0) {
                        usedResourceArr.push({
                            name: filterResourceItem[0].name,
                            key: filterResourceItem[0].key
                        });

                        levelDataArr.push({
                            name: filterResourceItem[0].name,
                            key: filterResourceItem[0].key,
                            defaultCheck: false,
                            level1Field: obj.resourceInfo || "",
                            level2Field: obj.resourceWarning || "",
                            level3Field: obj.resourceDanger || "",
                            level4Field: obj.resourceFatal || ""
                        });
                    }
                });
            });

            setPolicyName(policyName);
            setSelectResource(usedResourceArr);
            setChangeKey(changeKey + 1);
            setCriticalResource(levelDataArr);
            setShowLoader(false);
        });
    }

    const defaultThresholdValue = useCallback((checkData) => {
        setShowLoader(true);

        axiosConf.get("/api/setting/critical/findByLastDefault/" + target).then(res => {
            let cloneCriticalResource = _.cloneDeep(criticalResource);

            if (res.data.resourceAll) {
                const checkDataKey = _.map(checkData, "key");

                _.forEach(cloneCriticalResource, (obj) => {
                    if (checkDataKey.indexOf(obj.key) !== -1) {
                        const filterData = _.filter(res.data.resourceAll, (defaultObj) => {
                            return defaultObj.resource === obj.key;
                        });

                        if (filterData.length > 0) {
                            obj.level1Field = filterData[0].resource_info || "";
                            obj.level2Field = filterData[0].resource_warning || "";
                            obj.level3Field = filterData[0].resource_danger || "";
                            obj.level4Field = filterData[0].resource_fatal || "";
                        } else {
                            obj.level1Field = "";
                            obj.level2Field = "";
                            obj.level3Field = "";
                            obj.level4Field = "";
                        }
                    }
                });
            } else {
                _.forEach(cloneCriticalResource, (obj) => {
                    obj.level1Field = "";
                    obj.level2Field = "";
                    obj.level3Field = "";
                    obj.level4Field = "";
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
                        level4Field: ""
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

        let requestData = {
            title: policyName,
            resourceAll: []
        };

        _.forEach(criticalResource, (obj) => {
            requestData.resourceAll.push({
                resource_code: obj.key,
                resource_info: obj.level1Field ? parseFloat(obj.level1Field) : null,
                resource_warning: obj.level2Field ? parseFloat(obj.level2Field) : null,
                resource_danger: obj.level3Field ? parseFloat(obj.level3Field) : null,
                resource_fatal: obj.level4Field ? parseFloat(obj.level4Field) : null
            });
        });

        applyPolicySave(requestData);
    }

    const applyPolicySave = (requestData) => {
        const flag = params.id !== "0" ? "updatePolicy/" + target + "/" + params.id : "insertPolicy/" + target;

        axiosConf.post("/api/setting/critical/" + flag, requestData).then(res => {
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
        <SetCriticalValueTable children={criticalResource} changeKey={changeKey}
            handleThresholdValue={handleThresholdValue} defaultThresholdValue={defaultThresholdValue} clearThresholdValue={clearThresholdValue} />
    ), [criticalResource, changeKey]);

    return (
        <Card>
            <CardHeader title="임계치 정책 설정"></CardHeader>
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

export default PopupSetCriticalValueTabPolicyReg;