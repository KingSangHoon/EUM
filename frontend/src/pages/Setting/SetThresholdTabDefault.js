import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { styled } from '@mui/material/styles';
import { Box, Grid, Paper, Chip, IconButton, Button, Typography, FormLabel } from '@mui/material';

import { SearchOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import Loader from '../../components/Loader';
import ModalSearchFormResources from '../Common/Modal/ModalSearchFormResources';
import SetThresholdTable from './SetThresholdTable';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const SetThresholdTabDefault = (props) => {
    const [showLoader, setShowLoader] = useState(false);

    const [selectResource, setSelectResource] = useState([]);
    const [criticalResource, setCriticalResource] = useState([]);
    const [open, setOpen] = useState(false);

    const [policyInfo, setPolicyInfo] = useState({
        policyId: "",
        regDate: "",
        showPrev: false,
        showNext: false
    });

    const [tbHeight, setTbHeight] = useState(0);

    const [changeKey, setChangeKey] = useState(0);

    useEffect(() => {
        getPolicyList();
        autoComponentSize();
        window.addEventListener("resize", autoComponentSize);

        return () => {
            window.removeEventListener('resize', autoComponentSize);
        }
    }, [props.menuKey]);

    const autoComponentSize = () => {
        const mainHeight = document.body.clientHeight - 170;
        setTbHeight(mainHeight);
    }

    const getPolicyList = (dir) => {
        setShowLoader(true);
        const reqUrl = dir ? "/api/setting/threshold/find/" + props.target + "/" + dir + "/" + policyInfo.policyId : "/api/setting/threshold/find/" + props.target;

        axiosConf.get(reqUrl).then(res => {
            if (res.data.threshold) {
                const formatDate = res.data.threshold.regDate ? moment(res.data.threshold.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                let usedResourceArr = [];
                let levelDataArr = [];

                _.forEach(props.resourceItem, (obj) => {
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

                setSelectResource(usedResourceArr);
                setChangeKey(changeKey + 1);
                setCriticalResource(levelDataArr);

                if (dir) {
                    setPolicyInfo({ policyId: res.data.threshold.id, regDate: formatDate, showPrev: !res.data.isFirst, showNext: !res.data.isLatest });
                } else {
                    setPolicyInfo({ policyId: res.data.threshold.id, regDate: formatDate, showPrev: res.data.threshold.id !== 1, showNext: false });
                }

                setShowLoader(false);
            } else {
                if (res.data.isFirst === false && res.data.isLatest === false) {
                    alert("이전 내역이 없습니다.");
                    setShowLoader(false);
                    return;
                }

                setSelectResource([]);
                setChangeKey(changeKey + 1);
                setCriticalResource([]);

                setPolicyInfo({
                    policyId: "",
                    regDate: "",
                    showPrev: false,
                    showNext: false
                });

                setShowLoader(false);
            }
        });
    }

    const defaultThresholdValue = useCallback((checkData) => {
        setShowLoader(true);

        axiosConf.get("/api/setting/threshold/find/" + props.target).then(res => {
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

    const onSavePolicyValidation = () => {
        const filterNotTcpError = _.filter(selectResource, (obj) => {
            return !obj.isTcpError;
        });

        const filterTcpError = _.filter(selectResource, (obj) => {
            return obj.isTcpError;
        });

        let requestData = {
            data: { type: 0 },
            activeField: _.map(filterNotTcpError, "key"),
        };

        if (filterTcpError.length > 0) {
            requestData.data.isTcpError = true;
            requestData.errorField = _.map(filterTcpError, "key");
            requestData.errorData = {};
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
        axiosConf.post("/api/setting/threshold/insert/" + props.target, requestData).then(res => {
            alert("저장 되었습니다.");
            getPolicyList();
        });
    }

    // import content
    const ModalResourceContent = useMemo(() => (
        <ModalSearchFormResources open={open} resourceItem={props.resourceItem} selectResource={selectResource} onClose={handleClose} onSuccess={bridgeModalResource} />
    ), [open]);

    const ThresholdTbContent = useMemo(() => (
        <SetThresholdTable children={criticalResource} height={tbHeight} changeKey={changeKey}
            handleThresholdValue={handleThresholdValue} defaultThresholdValue={defaultThresholdValue} clearThresholdValue={clearThresholdValue} />
    ), [criticalResource, changeKey]);

    return (
        <Grid container>
            <Grid item sm={5.5} mb={1} textAlign="right">
                {policyInfo.showPrev && <Typography className="btn-left-icon" onClick={() => getPolicyList("prev")} />}
            </Grid>
            <Grid item sm={1} mb={1} textAlign="center">
                <Typography variant="h5" mt={.3} ml={1} mr={1}>{policyInfo.regDate}</Typography>
            </Grid>
            <Grid item sm={5.5} mb={1}>
                {policyInfo.showNext && <Typography className="btn-right-icon" onClick={() => getPolicyList("next")} />}
            </Grid>

            <Grid item sm={.5}>
                <FormLabel>자원</FormLabel>
            </Grid>
            <Grid item sm={11}>
                <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: "100%" }} className="popupEl">
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
            </Grid>
            <Grid item sm={.5} textAlign="right">
                <Button variant="contained" color="primary" size="small" onClick={onSavePolicyValidation}>저장</Button>
            </Grid>

            <Grid item sm={12} mt={1}>
                {ThresholdTbContent}
            </Grid>

            {showLoader && (<Loader />)}
            {ModalResourceContent}
        </Grid>
    );
};

export default SetThresholdTabDefault;