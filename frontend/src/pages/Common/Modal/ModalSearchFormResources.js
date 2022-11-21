import React, { useEffect, useState } from 'react';
import _ from 'lodash';
import PropTypes from 'prop-types';
import { Card, CardHeader, Grid, Box, Button, ListItem, CardContent, Collapse, FormGroup, FormControlLabel, Checkbox, Modal, Radio } from '@mui/material';

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleDown, faAngleUp } from "@fortawesome/free-solid-svg-icons";
import { ReloadOutlined } from '@ant-design/icons';

import { modalStyles } from '../../../lib/common';

function TabPanel(props) {
    const { listItem, selectItem, evtCollapseListGroup, handleCheckboxChange, handleItemCheckboxChange, handleRadioChange, single } = props;
    const selectItemKey = _.map(selectItem, "key");

    return (
        _.map(listItem, (obj, i) => (
            obj.group ? <React.Fragment key={i}>
                <ListItem component="div" className="border cursorp" onClick={() => evtCollapseListGroup(i)}>
                    <FormControlLabel control={single ? <></> : <Checkbox checked={obj.checked} onChange={(e) => handleItemCheckboxChange(e, i)} />} onClick={(e) => e.stopPropagation()} label={obj.name} />
                    <Box sx={{ position: "absolute", top: ".3rem", right: ".5rem", lineHeight: "0" }}>
                        {obj.open ? <FontAwesomeIcon icon={faAngleUp} /> : <FontAwesomeIcon icon={faAngleDown} />}
                    </Box>
                </ListItem>
                <Collapse in={obj.open} timeout="auto" unmountOnExit>
                    <CardContent className="bg-gray">
                        <Grid container sx={{ pl: 1, pr: 1 }}>
                            {
                                _.map(obj.children, (subObj, subI) => (
                                    <Grid item sm={3} key={i + "_" + subI}>
                                        <FormGroup>
                                            <FormControlLabel control={single ? <Radio id={subObj.key} checked={selectItemKey.indexOf(subObj.key) !== -1} onChange={(e) => handleRadioChange(e, i)} />
                                                : <Checkbox id={subObj.key} checked={selectItemKey.indexOf(subObj.key) !== -1} onChange={(e) => handleCheckboxChange(e, i)} />} label={subObj.name} />
                                        </FormGroup>
                                    </Grid>
                                ))
                            }
                        </Grid>
                    </CardContent>
                </Collapse>
            </React.Fragment> : <CardContent className="bg-gray" key={i}>
                <Grid container sx={{ pl: 1, pr: 1 }}>
                    {
                        _.map(obj.children, (subObj, subI) => (
                            <Grid item sm={3} key={i + "_" + subI}>
                                <FormGroup>
                                    <FormControlLabel control={single ? <Radio id={subObj.key} checked={selectItemKey.indexOf(subObj.key) !== -1} onChange={(e) => handleRadioChange(e, i)} />
                                        : <Checkbox id={subObj.key} checked={selectItemKey.indexOf(subObj.key) !== -1} onChange={(e) => handleCheckboxChange(e, i)} />} label={subObj.name} />
                                </FormGroup>
                            </Grid>
                        ))
                    }
                </Grid>
            </CardContent>
        ))
    );
}

TabPanel.propTypes = {
    listItem: PropTypes.array,
    selectItem: PropTypes.array
};

const ModalSearchFormResources = React.forwardRef((props, ref) => {
    const [selectResourceObj, setSelectResourceObj] = useState([]);
    const [resourceItem, setResourceItem] = useState([]);

    useEffect(() => {
        const selectResourceKey = _.map(props.selectResource, "key");
        let cloneResourceItem = _.cloneDeep(resourceItem);

        _.forEach(cloneResourceItem, (obj) => {
            const filterKey = _.filter(obj.children, (childObj) => {
                return selectResourceKey.indexOf(childObj.key) !== -1;
            });

            obj.checked = filterKey.length === obj.children.length;
        });

        setResourceItem(cloneResourceItem);
        setSelectResourceObj(props.selectResource);
    }, [props.selectResource]);

    useEffect(() => {
        setResourceItem(props.resourceItem);
    }, [props.resourceItem]);

    const handleItemCheckboxChange = (e, target) => {
        let cloneResourceItem = _.cloneDeep(resourceItem);
        cloneResourceItem[target].checked = e.target.checked;
        setResourceItem(cloneResourceItem);

        if (e.target.checked) {
            const selectResourceKey = _.map(selectResourceObj, "key");
            let cloneSelectResourceObj = _.cloneDeep(selectResourceObj);

            _.forEach(resourceItem[target].children, (obj) => {
                if (selectResourceKey.indexOf(obj.key) === -1) {
                    cloneSelectResourceObj.push(obj);
                }
            });

            setSelectResourceObj(cloneSelectResourceObj);
        } else {
            const selectResourceKey = _.map(resourceItem[target].children, "key");

            const filterObj = _.filter(selectResourceObj, (obj) => {
                return selectResourceKey.indexOf(obj.key) === -1;
            });

            setSelectResourceObj(filterObj);
        }
    }

    const handleCheckboxChange = (e, target) => {
        if (e.target.checked) {
            const filterObj = _.filter(resourceItem[target].children, (obj) => {
                return obj.key === e.target.id;
            });

            setSelectResourceObj([...selectResourceObj, filterObj[0]]);
        } else {
            const filterObj = _.filter(selectResourceObj, (obj) => {
                return obj.key !== e.target.id;
            });

            setSelectResourceObj(filterObj);
        }
    }

    const handleRadioChange = (e, target) => {
        const filterObj = _.filter(resourceItem[target].children, (obj) => {
            return obj.key === e.target.id;
        });

        setSelectResourceObj([filterObj[0]]);
    }

    const evtCollapseListGroup = (target) => {
        let cloneResourceItem = _.cloneDeep(resourceItem);
        cloneResourceItem[target].open = !cloneResourceItem[target].open;
        setResourceItem(cloneResourceItem);
    }

    const resetEvt = () => {
        let cloneResourceItem = _.cloneDeep(resourceItem);

        _.forEach(cloneResourceItem, (obj) => {
            obj.checked = false;
        });

        setResourceItem(cloneResourceItem);
        setSelectResourceObj([]);
    }

    const onSavePolicyValidation = () => {
        props.onSuccess(selectResourceObj);
        props.onClose();
    }

    return (
        <Modal open={props.open} onClose={props.onClose}>
            <Card sx={{ ...modalStyles, maxWidth: "1200px" }}>
                <CardHeader title="자원"></CardHeader>
                <CardContent sx={{ overflowY: "auto", height: "40rem" }}>
                    <TabPanel listItem={resourceItem} selectItem={selectResourceObj} single={props.single}
                        handleRadioChange={handleRadioChange} handleItemCheckboxChange={handleItemCheckboxChange} handleCheckboxChange={handleCheckboxChange} evtCollapseListGroup={evtCollapseListGroup} />
                </CardContent>

                <Box component="div" textAlign="center" sx={{ mt: 1, pb: 1 }}>
                    {!props.single && <Button variant="outlined" color="secondary" size="small" startIcon={<ReloadOutlined />} sx={{ mr: 1 }} onClick={resetEvt}>초기화</Button>}
                    <Button variant="contained" color="primary" size="small" sx={{ mr: 1 }} onClick={onSavePolicyValidation}>저장</Button>
                    <Button variant="contained" color="secondary" size="small" onClick={props.onClose}>닫기</Button>
                </Box>
            </Card>
        </Modal>
    );
});

export default ModalSearchFormResources;