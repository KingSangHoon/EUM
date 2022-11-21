import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import { useSearchParams } from 'react-router-dom';
import moment from 'moment';
import { Card, CardHeader, CardContent, Button, Box, FormControl, Divider, FormControlLabel, RadioGroup, Radio, Checkbox } from '@mui/material';

import { FileExcelOutlined } from '@ant-design/icons';

import axiosConf from '../../../axios';
import { hiddenComponentPopup } from '../../../lib/common';
import Loader from '../../../components/Loader';

const PopupCommonExport = () => {
    const [searchParams] = useSearchParams();
    const paramCategory = searchParams.get("category");

    const [showLoader, setShowLoader] = useState(false);

    const [filterType, setFilterType] = useState("true");

    const [allCheckbox, setAllCheckbox] = useState(true);
    const [fieldState, setFieldState] = useState([]);

    const [filterItem, setFilterItem] = useState({});
    const [filterKey, setFilterKey] = useState([]);

    useEffect(() => {
        getFindoneStateData();

        hiddenComponentPopup();
        window.addEventListener("resize", hiddenComponentPopup);

        return () => {
            window.removeEventListener('resize', hiddenComponentPopup);
        }
    }, []);

    const getFindoneStateData = () => {
        setTimeout(() => {
            const param = document.getElementById("stateInput");

            if (param) {
                const jsonParam = JSON.parse(param.value);

                const columnDefs = _.map(jsonParam.columnDefs, (obj) => {
                    let sortType = "left";

                    if (obj.cellClass) {
                        if (obj.cellClass.indexOf("text-center") !== -1) {
                            sortType = "center";
                        } else if (obj.cellClass.indexOf("text-right") !== -1) {
                            sortType = "right";
                        }
                    }

                    return {
                        name: obj.headerName || obj.name,
                        key: obj.field || obj.key,
                        active: obj.show || true,
                        sort: sortType
                    };
                });

                setFieldState(columnDefs);
                setFilterItem(jsonParam.schItem);
                setFilterKey(jsonParam.filter);
            }
        }, 100);
    }

    const handleKeyPress = (e) => {
        setFilterType(e.target.value);
    }

    const handleAllCheckbox = (e) => {
        let cloneFieldItem = _.cloneDeep(fieldState);

        _.forEach(cloneFieldItem, (obj) => {
            obj.active = e.target.checked;
        });

        setAllCheckbox(e.target.checked);
        setFieldState(cloneFieldItem);
    }

    const handleCheckboxChange = (e) => {
        const splitId = e.target.id.split("_");
        let cloneFieldItem = _.cloneDeep(fieldState);

        const filterData = _.filter(cloneFieldItem, (obj) => {
            return obj.key === splitId[1];
        });

        if (filterData.length > 0) {
            filterData[0].active = e.target.checked;
            setFieldState(cloneFieldItem);
        }
    }

    const staticMappingFiterId = () => {
        return {
            "userId": "userId"
        };
    }

    const onSavePolicyValidation = () => {
        if (fieldState.length === 0) {
            return;
        }

        setShowLoader(true);

        const mappingFilterId = staticMappingFiterId();

        const activeField = _.filter(fieldState, (obj) => {
            return obj.active;
        });

        let requestData = {
            category: paramCategory,
            filter: JSON.parse(filterType),
            begin: moment(filterItem.startDate).format("YYYYMMDD") + filterItem.startHours + filterItem.startMinutes + filterItem.startSeconds,
            end: moment(filterItem.endDate).format("YYYYMMDD") + filterItem.endHours + filterItem.endMinutes + filterItem.endSeconds,
            field: _.map(activeField, "key"),
            viewField: _.map(activeField, "name"),
            sort: _.map(activeField, "sort"),
        };

        if (requestData.filter) {
            _.forEach(filterKey, (key) => {
                if (mappingFilterId[key]) {
                    requestData[key] = _.map(filterItem[key], mappingFilterId[key]);
                } else {
                    requestData[key] = filterItem[key];
                }
            });
        }

        exportExlList(requestData);
    }

    const exportExlList = (requestData) => {
        axiosConf.post("/api/excel/field", requestData, { responseType: 'blob' }).then(res => {
            const name = res.headers['content-disposition'].split('Filename=')[1];
            const url = window.URL.createObjectURL(new Blob([res.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', name);
            link.style.cssText = 'display:none';
            document.body.appendChild(link);
            link.click();
            link.remove();

            setShowLoader(false);
        });
    }

    return (
        <Card>
            <CardHeader title="내보내기" />
            <CardContent>
                <FormControl sx={{ width: "10%", mt: .5 }}>필드</FormControl>
                <FormControl sx={{ width: "90%" }}>
                    <FormControlLabel control={<Checkbox id={"field_all"} checked={allCheckbox} onChange={handleAllCheckbox} />} label="전체" />
                    <Divider />
                </FormControl>

                <FormControl sx={{ width: "10%", mt: .5 }}></FormControl>
                <FormControl sx={{ width: "90%", maxHeight: 400, overflowY: "auto" }}>
                    {
                        _.map(fieldState, (obj, i) => (
                            <FormControl fullWidth key={i}>
                                <FormControlLabel control={<Checkbox id={"field_" + obj.key} checked={obj.active} onChange={handleCheckboxChange} />} label={obj.name} />
                            </FormControl>
                        ))
                    }
                </FormControl>
                <Divider />

                <FormControl sx={{ width: "10%", mt: .5 }}>구분</FormControl>
                <FormControl sx={{ width: "90%", mt: .5 }}>
                    <RadioGroup row name="filterType" onChange={handleKeyPress} value={filterType}>
                        <FormControlLabel value="true" control={<Radio />} label="필터링" />
                        <FormControlLabel value="false" control={<Radio />} label="전체" />
                    </RadioGroup>
                </FormControl>

                <Box component="div" textAlign="center" sx={{ pb: 1 }}>
                    <Button variant="contained" color="info" size="small" sx={{ mr: 1 }} startIcon={<FileExcelOutlined />} onClick={onSavePolicyValidation}>엑셀</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={() => { window.close(); }}>닫기</Button>
                </Box>
            </CardContent>

            {showLoader && (<Loader />)}
        </Card>
    );
};

export default PopupCommonExport;