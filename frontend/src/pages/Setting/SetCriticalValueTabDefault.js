import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import moment from 'moment';
import { styled } from '@mui/material/styles';
import { Box, Grid, Paper, Chip, IconButton, Button, Typography, FormLabel } from '@mui/material';

import { SearchOutlined } from '@ant-design/icons';

import axiosConf from '../../axios';
import Loader from '../../components/Loader';
import ModalSearchFormResources from '../Common/Modal/ModalSearchFormResources';
import SetCriticalValueTable from './SetCriticalValueTable';

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const SetCriticalValueTabDefault = (props) => {
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
        const reqUrl = dir ? "/api/setting/critical/findByTypeAndSub2Id/" + props.target + "/" + dir : "/api/setting/critical/findByLastDefault/" + props.target;

        axiosConf.get(reqUrl).then(res => {
            if (res.data.resourceAll) {
                const formatDate = res.data.infoDefault.regDate ? moment(res.data.infoDefault.regDate).format("YYYY-MM-DD HH:mm:ss") : "";
                let usedResourceArr = [];
                let levelDataArr = [];

                _.forEach(res.data.resourceAll, (obj) => {
                    _.forEach(props.resourceItem, (resourceObj) => {
                        const filterResourceItem = _.filter(resourceObj.children, (resourceChildObj) => {
                            return resourceChildObj.key === obj.resource;
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
                                level1Field: obj.resource_info || "",
                                level2Field: obj.resource_warning || "",
                                level3Field: obj.resource_danger || "",
                                level4Field: obj.resource_fatal || ""
                            });
                        }
                    });
                });

                setSelectResource(usedResourceArr);
                setChangeKey(changeKey + 1);
                setCriticalResource(levelDataArr);

                if (dir) {
                    setPolicyInfo({
                        policyId: res.data.infoDefault.resourceId,
                        regDate: formatDate,
                        showPrev: res.data.infoDefault.resourceId > 1,
                        showNext: res.data.infoDefault.resourceId !== res.data.lastId
                    });
                } else {
                    setPolicyInfo({
                        policyId: res.data.infoDefault.resourceId,
                        regDate: formatDate,
                        showPrev: res.data.infoDefault.resourceId > 1,
                        showNext: false
                    });
                }

                setShowLoader(false);
            } else {
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

        axiosConf.get("/api/setting/critical/findByLastDefault/" + props.target).then(res => {
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

    const onSavePolicyValidation = () => {
        let requestData = {
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
        axiosConf.post("/api/setting/critical/insertDefaultResource/" + props.target, requestData).then(res => {
            alert("저장 되었습니다.");
            getPolicyList();
        });
    }

    // import content
    const ModalResourceContent = useMemo(() => (
        <ModalSearchFormResources open={open} resourceItem={props.resourceItem} selectResource={selectResource} onClose={handleClose} onSuccess={bridgeModalResource} />
    ), [open]);

    const ThresholdTbContent = useMemo(() => (
        <SetCriticalValueTable children={criticalResource} height={tbHeight} changeKey={changeKey}
            handleThresholdValue={handleThresholdValue} defaultThresholdValue={defaultThresholdValue} clearThresholdValue={clearThresholdValue} />
    ), [criticalResource, changeKey]);

    return (
        <Grid container>
            <Grid item sm={5.5} mb={1} textAlign="right">
                {policyInfo.showPrev && <Typography className="btn-left-icon" onClick={() => getPolicyList(policyInfo.policyId - 1)} />}
            </Grid>
            <Grid item sm={1} mb={1} textAlign="center">
                <Typography variant="h5" mt={.3} ml={1} mr={1}>{policyInfo.regDate}</Typography>
            </Grid>
            <Grid item sm={5.5} mb={1}>
                {policyInfo.showNext && <Typography className="btn-right-icon" onClick={() => getPolicyList(policyInfo.policyId + 1)} />}
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

export default SetCriticalValueTabDefault;