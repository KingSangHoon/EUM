import React, { useEffect, useState, useMemo, useCallback } from 'react';
import _ from 'lodash';
import { useParams } from 'react-router-dom';
import { styled } from "@mui/material/styles";
import { Card, CardHeader, CardContent, Button, Box, FormControl, FormControlLabel, IconButton, Paper, Chip, RadioGroup, Radio } from '@mui/material';

import { SearchOutlined } from "@ant-design/icons";

import axiosConf from '../../../axios';
import Loader from '../../../components/Loader';
import { hiddenComponentPopup, gridApiObj } from '../../../lib/common';
import ModalSearchFormProtocol from "../Modal/ModalSearchFormProtocol";

const ListItem = styled('li')(({ theme }) => ({
    marginRight: theme.spacing(0.5)
}));

const PopupSetCodeProtocolDetailReg = () => {
    const params = useParams();

    const [showLoader, setShowLoader] = useState(false);
    const [protocolList, setProtocolList] = useState([]);

    const [state, setState] = useState({
        protocolType: 0,
        id: 0,
    });

    const [open, setOpen] = useState(false);
    const [masterModalOpen, setMasterModalOpen] = useState(false);

    const [selectResource, setSelectResource] = useState([]);
    const [selectMasterResource, setSelectMasterResource] = useState([]);

    useEffect(() => {
        getFindoneStateData();

        if (params.id != 0) {
            getDetailProtocol();
        }

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

    const getDetailProtocol = () => {
        setShowLoader(true);

        setTimeout(() => {
            axiosConf.get('/api/setting/code/protocol/findOneProtocol/' + params.id).then(res => {
                setSelectResource(res.data.appProtocol);
                setSelectMasterResource(res.data.masterProtocol);
                setState({ ...state, protocolType: res.data.mappingInfo.isWhiteList == true ? 0 : 1 });
            })
        })
    }

    const getFindoneStateData = () => {
        setShowLoader(true);

        setTimeout(() => {
            axiosConf.get('/api/setting/code/protocol/findAllProtocol').then(res => {
                setShowLoader(false);
                setProtocolList(res.data)
            });
        }, 100);
    }

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleResize = () => {
        _.map(gridApiObj, (obj) => {
            if (obj) obj.api.sizeColumnsToFit();
        });
    }

    const onSavePolicyValidation = () => {
        if (selectResource.length == 0 || selectMasterResource.length == 0) {
            alert("Protocol를 선택해주세요.");
            return;
        }

        let requestUrl = "";

        let requestData = {
            appId: selectResource.ndpiProtocolNumber,
            masterId: selectMasterResource.ndpiProtocolNumber,
            type: state.protocolType == 0 ? true : false,
        };

        requestUrl = params.id === "0" ? "createProtocolMapping" : "updateProtocolMapping/" + params.id;

        applyPolicySave(requestUrl, requestData);
    }

    const applyPolicySave = (requestUrl, requestData) => {
        axiosConf.post("/api/setting/code/protocol/" + requestUrl, requestData).then(res => {
            alert("저장 되었습니다.");
            window.opener.onSuccess(requestData);
            window.close();
        });
    }

    const bridgeModalResource = useCallback((data) => {
        setSelectResource(data)
    }, []);

    const bridgeMasterModalResource = useCallback((data) => {
        setSelectMasterResource(data)
    }, []);

    const handleOpen = () => setOpen(true);

    const handleClose = useCallback(() => {
        setOpen(false);
    }, []);

    const handleMasterModalOpen = () => setMasterModalOpen(true);

    const handleMasterModalClose = useCallback(() => {
        setMasterModalOpen(false);
    }, []);

    // import content
    const ModalAppContent = useMemo(() => (
        <ModalSearchFormProtocol open={open} resourceItem={protocolList} selectResource={selectResource} onClose={handleClose} onSuccess={bridgeModalResource} />
    ), [open]);

    const ModalMasterContent = useMemo(() => (
        <ModalSearchFormProtocol open={masterModalOpen} resourceItem={protocolList} selectResource={selectMasterResource} onClose={handleMasterModalClose} onSuccess={bridgeMasterModalResource} />
    ), [masterModalOpen]);

    return (
        <Card>
            <CardHeader title="Protocol 설정"></CardHeader>
            <CardContent>
                <Box
                    component="form"
                    sx={{ '& .MuiTextField-root, .MuiFormControlLabel-root': { mb: 1 } }}
                    noValidate
                    autoComplete="off"
                >
                    <FormControl sx={{ width: "15%" }}>Protocol App</FormControl>
                    <FormControl sx={{ width: "85%" }} className="popupEl">
                        <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: " 100%" }}>
                            <IconButton edge="start" size="small" className="search-btn" onClick={handleOpen}>
                                <SearchOutlined />
                            </IconButton>
                            <Box component="div" flex="1 1">
                                <Paper sx={{ display: 'flex', flexWrap: 'wrap', listStyle: 'none', pl: .5, m: 0, '& .MuiListItem-root': { p: 0 } }} className="border" component="ul" >
                                    {
                                        selectResource.length === 0 && <ListItem sx={{ mt: .3 }} className="font-gray">Choose Resources</ListItem>
                                    }
                                    {
                                        selectResource.length != 0 &&
                                        <ListItem key={selectResource.id}>
                                            <Chip size="small" label={selectResource.ndpiProtocolName} />
                                        </ListItem>
                                    }
                                </Paper>
                            </Box>
                        </Box>
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: 2 }}>Protocol Master</FormControl>
                    <FormControl sx={{ width: "85%", mt: 2 }} className="popupEl">
                        <Box component="div" sx={{ display: "flex", flexWrap: "wrap", alignItems: "stretch", width: " 100%" }}>
                            <IconButton edge="start" size="small" className="search-btn" onClick={handleMasterModalOpen}>
                                <SearchOutlined />
                            </IconButton>
                            <Box component="div" flex="1 1">
                                <Paper sx={{ display: 'flex', flexWrap: 'wrap', listStyle: 'none', pl: .5, m: 0, '& .MuiListItem-root': { p: 0 } }} className="border" component="ul" >
                                    {
                                        selectMasterResource.length === 0 && <ListItem sx={{ mt: .3 }} className="font-gray">Choose Resources</ListItem>
                                    }
                                    {
                                        selectMasterResource.length != 0 &&
                                        <ListItem key={selectMasterResource.id}>
                                            <Chip size="small" label={selectMasterResource.ndpiProtocolName} />
                                        </ListItem>
                                    }
                                </Paper>
                            </Box>
                        </Box>
                    </FormControl>

                    <FormControl sx={{ width: "15%", mt: 2 }}>Type</FormControl>
                    <FormControl sx={{ width: "85%", mt: 2 }}>
                        <RadioGroup row name="protocolType" onChange={handleKeyPress} value={state.protocolType}>
                            <FormControlLabel value="0" control={<Radio />} label="White List" />
                            <FormControlLabel value="1" control={<Radio />} label="Black List" />
                        </RadioGroup>
                    </FormControl>
                </Box>

                <Box component="div" textAlign="center" sx={{ pt: 1, pb: 1 }}>
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
            {ModalAppContent}
            {ModalMasterContent}
        </Card>
    );
};

export default PopupSetCodeProtocolDetailReg;