import React, { useState, useMemo } from 'react';
import _ from 'lodash';
import { Button, TextField, FormControlLabel, Checkbox, TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Paper, Typography } from '@mui/material';

const handleAllCheckCritical = (e, props, setChkAllResource) => {
    let cloneResource = _.cloneDeep(props.children);

    _.forEach(cloneResource, (obj) => {
        obj.defaultCheck = e.target.checked;
    });

    props.handleThresholdValue(cloneResource);
    setChkAllResource(e.target.checked);
}

const evtResetButton = (props, setChkAllResource) => {
    const checkData = _.filter(props.children, (obj) => {
        return obj.defaultCheck;
    });

    if (checkData.length === 0) {
        alert("항목을 선택해주세요.");
        return;
    }

    let cloneResource = _.cloneDeep(props.children);

    _.forEach(cloneResource, (obj) => {
        if (obj.defaultCheck) {
            obj.defaultCheck = false;
            obj.level1Field = "";
            obj.level2Field = "";
            obj.level3Field = "";
            obj.level4Field = "";
        }
    });

    props.clearThresholdValue(cloneResource);
    setChkAllResource(false);
}

const evtDefaultDataButton = (props, setChkAllResource) => {
    const checkData = _.filter(props.children, (obj) => {
        return obj.defaultCheck;
    });

    if (checkData.length === 0) {
        alert("항목을 선택해주세요.");
        return;
    }

    _.forEach(checkData, (obj) => {
        if (obj.defaultCheck) {
            obj.defaultCheck = false;
        }
    });

    props.defaultThresholdValue(checkData);
    setChkAllResource(false);
}

const handleKeyPressCritical = (e, targetIdx, props) => {
    let cloneCriticalResource = [...props.children];

    if (e.target.type === "checkbox") {
        cloneCriticalResource[targetIdx][e.target.name] = e.target.checked;
        props.handleThresholdValue(cloneCriticalResource);
    } else {
        const re = /^[0-9\b]+$/;
        const isNumberValue = re.test(e.target.value);

        if (isNumberValue || e.target.value === "") {
            if (cloneCriticalResource[targetIdx][e.target.name] === e.target.value) {
                return;
            }

            cloneCriticalResource[targetIdx][e.target.name] = e.target.value;
            props.handleThresholdValue(cloneCriticalResource);
        }
    }
}

const preventPressWhich = (e) => {
    const availCode = [8, 9, 17, 65, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105];

    if (availCode.indexOf(e.keyCode) === -1) {
        e.preventDefault();
    }
}

const SetCriticalValueTable = React.forwardRef((props, ref) => {
    const [chkAllResource, setChkAllResource] = useState(false);

    // import content
    const ChildrenResourceTb = useMemo(() => (
        <TableBody>
            {
                _.map(props.children, (obj, i) => (
                    <TableRow
                        sx={{
                            '&:last-child td, &:last-child th': { border: 0 },
                            '& input::-webkit-outer-spin-button, input::-webkit-inner-spin-button': { WebkitAppearance: "none", margin: 0 }
                        }}
                        key={props.changeKey + "_" + i}
                    >
                        <TableCell>
                            {
                                props.flagReadOnly ? <Typography>{obj.name}</Typography>
                                    : <FormControlLabel control={<Checkbox name="defaultCheck" checked={obj.defaultCheck} onChange={(e) => handleKeyPressCritical(e, i, props)} />} label={obj.name} />
                            }
                        </TableCell>
                        <TableCell align="center">
                            <TextField
                                name="level1Field"
                                size="small"
                                sx={{ input: { textAlign: "center" } }}
                                InputProps={{
                                    readOnly: props.flagReadOnly
                                }}
                                type="number"
                                defaultValue={obj.level1Field}
                                onKeyDown={preventPressWhich}
                                onBlur={(e) => handleKeyPressCritical(e, i, props)}
                            />
                        </TableCell>
                        <TableCell align="center">
                            <TextField
                                name="level2Field"
                                size="small"
                                sx={{ input: { textAlign: "center" } }}
                                InputProps={{
                                    readOnly: props.flagReadOnly
                                }}
                                type="number"
                                defaultValue={obj.level2Field}
                                onKeyDown={preventPressWhich}
                                onBlur={(e) => handleKeyPressCritical(e, i, props)}
                            />
                        </TableCell>
                        <TableCell align="center">
                            <TextField
                                name="level3Field"
                                size="small"
                                sx={{ input: { textAlign: "center" } }}
                                InputProps={{
                                    readOnly: props.flagReadOnly
                                }}
                                type="number"
                                defaultValue={obj.level3Field}
                                onKeyDown={preventPressWhich}
                                onBlur={(e) => handleKeyPressCritical(e, i, props)}
                            />
                        </TableCell>
                        <TableCell align="center">
                            <TextField
                                name="level4Field"
                                size="small"
                                sx={{ input: { textAlign: "center" } }}
                                InputProps={{
                                    readOnly: props.flagReadOnly
                                }}
                                type="number"
                                defaultValue={obj.level4Field}
                                onKeyDown={preventPressWhich}
                                onBlur={(e) => handleKeyPressCritical(e, i, props)}
                            />
                        </TableCell>
                    </TableRow>
                ))
            }
        </TableBody>
    ), [props.children]);

    return (
        <TableContainer component={Paper} className="border" sx={{ maxHeight: props.height || 600 }}>
            <Table stickyHeader className="table-bordered" size="small">
                <TableHead className="thead-light">
                    <TableRow
                        sx={{
                            '& .MuiBadge-badge': { width: "12rem", height: "1.2rem" }
                        }}
                    >
                        <TableCell>
                            {
                                !props.flagReadOnly && <>
                                    <FormControlLabel control={<Checkbox name="chkAllResource" checked={chkAllResource} onChange={(e) => handleAllCheckCritical(e, props, setChkAllResource)} />} />
                                    <Button variant="outlined" color="primary" size="small" onClick={() => evtDefaultDataButton(props, setChkAllResource)}>기본 값 불러오기</Button>
                                    <Button variant="outlined" color="inherit" size="small" sx={{ ml: .5 }} onClick={() => evtResetButton(props, setChkAllResource)}>초기화</Button>
                                </>
                            }
                        </TableCell>
                        <TableCell align="center" className="bg-yellow font-white" sx={{ width: "14rem" }}>주의</TableCell>
                        <TableCell align="center" className="bg-orange font-white" sx={{ width: "14rem" }}>경고</TableCell>
                        <TableCell align="center" className="bg-redorange font-white" sx={{ width: "14rem" }}>위험</TableCell>
                        <TableCell align="center" className="bg-danger font-white" sx={{ width: "14rem" }}>장애</TableCell>
                    </TableRow>
                </TableHead>
                {ChildrenResourceTb}
            </Table>
        </TableContainer>
    )
})

export default SetCriticalValueTable;