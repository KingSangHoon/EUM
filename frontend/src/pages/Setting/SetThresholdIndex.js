import React, { useState } from 'react';
import _ from 'lodash';
import { Box, Tabs, Tab } from '@mui/material';

import { pageResources, uriResources, tcpResources, udpResources, ipResources, trafficResources } from '../../lib/common';
import SetThresholdTabDefault from './SetThresholdTabDefault';
import SetThresholdTabPolicy from './SetThresholdTabPolicy';

function TabFragment(props) {
    switch (props.menuKey) {
        case "0_0":
            return <SetThresholdTabDefault menuKey={props.menuKey} target="transaction" resourceItem={pageResources} />;
        case "0_1":
            return <SetThresholdTabDefault menuKey={props.menuKey} target="uri" resourceItem={uriResources} />;
        case "0_2":
            return <SetThresholdTabDefault menuKey={props.menuKey} target="tcp" resourceItem={tcpResources} />;
        case "0_3":
            return <SetThresholdTabDefault menuKey={props.menuKey} target="udp" resourceItem={udpResources} />;
        case "0_4":
            return <SetThresholdTabDefault menuKey={props.menuKey} target="ip" resourceItem={ipResources} />;
        case "0_5":
            return <SetThresholdTabDefault menuKey={props.menuKey} target="traffic" resourceItem={trafficResources} />;
        case "1_0":
            return <SetThresholdTabPolicy menuKey={props.menuKey} target="transaction" resourceItem={pageResources} />;
        case "1_1":
            return <SetThresholdTabPolicy menuKey={props.menuKey} target="uri" resourceItem={uriResources} />;
        case "1_2":
            return <SetThresholdTabPolicy menuKey={props.menuKey} target="tcp" resourceItem={tcpResources} />;
        case "1_3":
            return <SetThresholdTabPolicy menuKey={props.menuKey} target="udp" resourceItem={udpResources} />;
        case "1_4":
            return <SetThresholdTabPolicy menuKey={props.menuKey} target="ip" resourceItem={ipResources} />;
        case "1_5":
            return <SetThresholdTabPolicy menuKey={props.menuKey} target="traffic" resourceItem={trafficResources} />;
        default:
            return <></>;
    }
}

const SetThresholdIndex = () => {
    const [menuKey, setMenuKey] = useState(0);
    const [subMenuKey, setSubMenuKey] = useState(0);

    const handleMainChange = (event, newValue) => {
        setMenuKey(newValue);
        setSubMenuKey(0);
    }

    const handleSubChange = (event, newValue) => {
        setSubMenuKey(newValue);
    }

    return (
        <Box>
            <Box sx={{ borderBottom: 1, borderColor: 'divider', bgcolor: 'background.paper' }}>
                <Tabs value={menuKey} onChange={handleMainChange} className="small">
                    <Tab label="기본값" />
                    <Tab label="레벨 정책" />
                </Tabs>
                <Tabs value={subMenuKey} onChange={handleSubChange} className="small" sx={{ bgcolor: "#f6f6f6" }}>
                    <Tab label="HTTP-Transaction" />
                    <Tab label="HTTP-URI" />
                    <Tab label="L4-TCP" />
                    <Tab label="L4-UDP" />
                    <Tab label="L3-IP" />
                    <Tab label="Traffic" />
                </Tabs>
            </Box>

            <Box m={.5}>
                <TabFragment menuKey={menuKey + "_" + subMenuKey} />
            </Box>
        </Box>
    );
};

export default SetThresholdIndex;