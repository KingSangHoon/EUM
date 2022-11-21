import React, { useState, useMemo } from 'react';
import _ from "lodash";
import { TableContainer, Paper, Table, TableHead, TableRow, TableCell, Checkbox, TableBody, Grid, TextField, MenuItem, Button } from '@mui/material';

const ipFormat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
const numberFormat = /^[0-9]+$/;

const PopupSetBandRegMappingTb = (props) => {
    const [mappingAllCheck, setMappingAllCheck] = useState(false);

    const [state, setState] = useState({
        rangeType: "single",
        startField: "",
        endField: ""
    });

    const handleKeyPress = (e) => {
        setState({ ...state, [e.target.name]: e.target.value });
    }

    const handleMappingAllCheckbox = (e) => {
        let cloneData = _.cloneDeep(props.mappingData);

        _.forEach(cloneData, (obj) => {
            obj.isCheck = e.target.checked;
        });

        props.mappingBridge({
            mappingData: cloneData
        });

        setMappingAllCheck(e.target.checked);
    }

    const handleMappingCheckbox = (e, targetIdx) => {
        let cloneData = _.cloneDeep(props.mappingData);

        cloneData[targetIdx].isCheck = e.target.checked;

        props.mappingBridge({
            mappingData: cloneData
        });
    }

    const singleValidationChk = () => {
        if (state.startField === "") {
            alert("추가할 항목을 입력해주세요.");
            return false;
        }

        if (props.text === "IP") {
            if (!ipFormat.test(state.startField)) {
                alert("올바른 IP 주소를 입력해주세요.");
                return false;
            }
        } else if (props.text === "Port") {
            if (!numberFormat.test(state.startField)) {
                alert("올바른 Port 를 입력해주세요.");
                return false;
            }
        }

        const existField = _.filter(props.mappingData, (obj) => {
            return obj.txt === state.startField;
        });

        if (existField.length > 0) {
            alert("중복된 항목이 존재합니다.");
            return false;
        }

        return true;
    }

    const rangeValidationChk = () => {
        if (state.startField === "" || state.endField === "") {
            alert("추가할 항목을 입력해주세요.");
            return false;
        }

        if (props.text === "IP") {
            if (!ipFormat.test(state.startField) || !ipFormat.test(state.endField)) {
                alert("올바른 IP 주소를 입력해주세요.");
                return false;
            }
        } else if (props.text === "Port") {
            if (!numberFormat.test(state.startField) || !numberFormat.test(state.endField)) {
                alert("올바른 Port 를 입력해주세요.");
                return false;
            }
        }

        const existField = _.filter(props.mappingData, (obj) => {
            return obj.txt === state.startField + " ~ " + state.endField;
        });

        if (existField.length > 0) {
            alert("중복된 항목이 존재합니다.");
            return false;
        }

        return true;
    }

    const addMappingItem = () => {
        if (state.rangeType === "single") {
            if (!singleValidationChk()) {
                return;
            }

            const addData = {
                isCheck: false,
                range: state.rangeType,
                startField: state.startField,
                endField: state.startField,
                txt: state.startField
            };

            props.mappingBridge({
                mappingData: [...props.mappingData, addData]
            });
        } else {
            if (!rangeValidationChk()) {
                return;
            }

            const addData = {
                isCheck: false,
                range: state.rangeType,
                startField: state.startField,
                endField: state.endField,
                txt: state.startField + " ~ " + state.endField
            };

            props.mappingBridge({
                mappingData: [...props.mappingData, addData]
            });
        }
    }

    const deleteMappingItem = () => {
        const filterData = _.filter(props.mappingData, (obj) => {
            return !obj.isCheck;
        });

        if (filterData.length === props.mappingData.length) {
            alert("삭제할 항목을 선택해주세요.");
            return;
        }

        props.mappingBridge({
            mappingData: filterData
        });

        setMappingAllCheck(false);
    }

    // import content
    const MappingTbContent = useMemo(() => (
        <TableContainer component={Paper} className="border" sx={{ maxHeight: 200, mt: .5 }}>
            <Table stickyHeader size="small" className="table-bordered">
                <TableHead className="thead-light">
                    <TableRow>
                        <TableCell align="center" sx={{ width: "3rem" }}>
                            <Checkbox name="mappingAllCheck" checked={mappingAllCheck} onChange={handleMappingAllCheckbox} />
                        </TableCell>
                        <TableCell align="center">{props.text}</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {
                        _.map(props.mappingData, (obj, i) => (
                            <TableRow key={i}>
                                <TableCell align="center">
                                    <Checkbox name="isCheck" checked={obj.isCheck} onChange={(e) => handleMappingCheckbox(e, i)} />
                                </TableCell>
                                <TableCell>{obj.txt}</TableCell>
                            </TableRow>
                        ))
                    }
                </TableBody>
            </Table>
        </TableContainer>
    ), [props.mappingData]);

    return (
        <Grid container spacing={.5}>
            {
                !props.single && <Grid item md={1}>
                    <TextField
                        fullWidth
                        select
                        name="rangeType"
                        size="small"
                        value={state.rangeType}
                        onChange={handleKeyPress}
                    >
                        <MenuItem value="single">Single</MenuItem>
                        <MenuItem value="range">Range</MenuItem>
                    </TextField>
                </Grid>
            }

            {
                state.rangeType === "single" ? <Grid item md={4}>
                    <TextField
                        fullWidth
                        name="startField"
                        placeholder={props.text}
                        size="small"
                        value={state.startField}
                        onChange={handleKeyPress}
                    />
                </Grid> : <>
                    <Grid item md={2}>
                        <TextField
                            fullWidth
                            name="startField"
                            placeholder={"Start " + props.text}
                            size="small"
                            value={state.startField}
                            onChange={handleKeyPress}
                        />
                    </Grid>
                    <Grid item md={2}>
                        <TextField
                            fullWidth
                            name="endField"
                            placeholder={"End " + props.text}
                            size="small"
                            value={state.endField}
                            onChange={handleKeyPress}
                        />
                    </Grid>
                </>
            }

            <Grid item md={7}>
                {
                    props.target && props.mappingData.length > 0 ? "" : <Button variant="outlined" color="primary" size="small" sx={{ mr: .5 }} onClick={addMappingItem}>추가</Button>
                }
                {
                    props.mappingData.length === 0 ? "" : <Button variant="outlined" color="secondary" size="small" onClick={deleteMappingItem}>삭제</Button>
                }
            </Grid>

            <Grid item md={12}>
                {MappingTbContent}
            </Grid>
        </Grid>
    );
};

export default PopupSetBandRegMappingTb;