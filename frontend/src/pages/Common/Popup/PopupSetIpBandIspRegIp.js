import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, TextField, RadioGroup, Radio, Checkbox, FormHelperText, Grid, Typography } from '@mui/material';

import axiosConf from '../../../axios';
import { AgGridModule } from '../../../lib/AgGridModule';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj, ipTypeCheck, ipToLong, scrollGridCnt, numberWithCommas } from '../../../lib/common';

const PopupSetIpBandIspRegIp = () => {
    const params = useParams();

    const [paramValue, setParamValue] = useState({});
    const [state, setState] = useState({
        startIp: "",
        endIp: "",
        mappingId: null,
    });

    const [listData, setListData] = useState([]);

    const [currentPageIpList, setCurrentPageIpList] = useState(0);
    const [maxPageIpList, setMaxPageIpList] = useState(0);
    const [totalIpList, setTotalIpList] = useState(0);

    const [selectRegionData, setSelectRegionData] = useState({
        primaryId: null,
        sub1Id: null,
        sub2Id: null,
        selectName: "-"
    });
    const [selectIpIdArr, setSelectIpIdArr] = useState([]);

    useEffect(() => {
        getFindoneStateData();

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
            if (gridApiObj.policyListGridApi) {
                gridApiObj.policyListGridApi.api.sizeColumnsToFit();
                chkListSelectIpData();
            }
        }, 200);
    }, [listData]);

    useEffect(() => {
        if (selectRegionData.primaryId) {
            getPolicyIpListMaxCount();
        }
    }, [selectRegionData]);

    const getFindoneStateData = () => {

        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);
                setParamValue(jsonParam);

                if (params.id === "0") {
                    setState({
                        ...state,
                        mappingId: jsonParam.id
                    });
                } else {
                    setState({
                        ...state,
                        id: jsonParam.id,
                        startIp: jsonParam.startIp,
                        endIp: jsonParam.endIp,
                        mappingId: jsonParam.mappingId
                    });

                }
            } else {
            }
        }, 100);
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleCheckboxChange = (e) => {
        setState({ ...state, [e.target.name]: e.target.checked });
        handleResize();
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
        if (data.sub1 || data.sub2) {
            return {
                group: true,
                children: data.sub1 || data.sub2
            };
        } else {
            return null;
        }
    }


    const getPolicyIpListMaxCount = () => {
        let regionIdParam = selectRegionData.primaryId;

        if (selectRegionData.primaryId && selectRegionData.sub1Id && selectRegionData.sub2Id) {
            regionIdParam += "/" + selectRegionData.sub1Id + "/" + selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            regionIdParam += "/" + selectRegionData.sub1Id;
        }

        axiosConf.get('/api/setting/geoDomestic/countPartDomesticIpRange/' + regionIdParam).then(res => {
            const maxPage = _.floor(res.data / scrollGridCnt);

            setMaxPageIpList(maxPage);
            setCurrentPageIpList(0);
            setTotalIpList(res.data);

            getPolicyIpListData(selectRegionData);
        });
    }

    const getPolicyIpListData = () => {
        let regionIdParam = selectRegionData.primaryId;

        if (selectRegionData.sub1Id && selectRegionData.sub2Id) {
            regionIdParam += "/" + selectRegionData.sub1Id + "/" + selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            regionIdParam += "/" + selectRegionData.sub1Id;
        }

        axiosConf.get('/api/setting/geoDomestic/findPartDomesticIpRange/' + regionIdParam + "/" + scrollGridCnt + "/0").then(res => {
            setListData(res.data);

            if (gridApiObj.policyListGridApi) {
                gridApiObj.policyListGridApi.api.ensureIndexVisible(0, 'top');
            }
        });
    }

    const addPolicyIpListData = (page) => {
        const offset = page * scrollGridCnt;
        let regionIdParam = selectRegionData.primaryId;

        if (selectRegionData.sub1Id && selectRegionData.sub2Id) {
            regionIdParam += "/" + selectRegionData.sub1Id + "/" + selectRegionData.sub2Id;
        } else if (selectRegionData.sub1Id) {
            regionIdParam += "/" + selectRegionData.sub1Id;
        }

        axiosConf.get('/api/setting/geoDomestic/findPartDomesticIpRange/' + regionIdParam + "/" + scrollGridCnt + "/" + offset).then(res => {
            setListData([...listData, ...res.data]);
        });
    }

    const chkListSelectIpData = () => {
        gridApiObj.policyListGridApi.api.forEachNode((node) => {
            if (selectIpIdArr.indexOf(node.data.id) !== -1) {
                node.setSelected(true);
            }
        });
    }

    const onSavePolicyValidation = () => {
        if (!_.isEmpty(paramValue)) {

            let requestData = [];
            let requestUrl = "";

            const ipFormat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;

            if (state.startIp === "") {
                alert("Start IP를 입력해주세요.");
                return;
            }

            if (state.endIp === "") {
                alert("End IP를 입력해주세요.");
                return;
            }

            if (!ipFormat.test(state.startIp)) {
                alert("올바른 Start IP 주소를 입력해주세요.");
                return;
            }

            if (!ipFormat.test(state.endIp)) {
                alert("올바른 End IP 주소를 입력해주세요.");
                return;
            }

            if (ipToLong(state.startIp) >= ipToLong(state.endIp)) {
                alert("Start IP가 End IP보다 크거나 같습니다.");
                return;
            }

            requestUrl = params.id === "0" ? "createMappingIsp" : "updateMappingIsp/" + params.id;
            requestData = {
                id: params.id,
                startIp: state.startIp,
                endIp: state.endIp,
                mappingId: state.mappingId,
            };

            applyPolicySave(requestUrl, requestData);
        }
    }

    const applyPolicySave = (requestUrl, requestData) => {
        axiosConf.post("/api/setting/geoIsp/" + requestUrl, requestData).then(res => {
            if (res.data) {
                alert("저장 되었습니다.");
                window.opener.onSuccess(requestData);
                window.close();
            } else {
                alert("IP 대역이 중복 되었습니다.");
            }
        });
    }

    return (
        <Card>
            <CardHeader title="IP 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{
                        '& .MuiFormControl-root': { mb: .5 }
                    }}
                    noValidate
                    autoComplete="off"
                >

                    <FormControl sx={{ width: "15%", mt: .5 }}>Start IP</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="startIp"
                            placeholder="Start IP"
                            size="small"
                            value={state.startIp}
                            onChange={handleKeyPress}
                        />
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: .5 }}>End IP</FormControl>
                    <FormControl sx={{ width: "85%" }}>
                        <TextField
                            name="endIp"
                            placeholder="End IP"
                            size="small"
                            value={state.endIp}
                            onChange={handleKeyPress}
                        />
                    </FormControl>
                </Box>

                <Box component="div" textAlign="center" sx={{ pt: 1, pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

        </Card>
    );
};

export default PopupSetIpBandIspRegIp;