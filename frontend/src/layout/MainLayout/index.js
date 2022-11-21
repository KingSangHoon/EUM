import { useEffect, useState } from 'react';
import { Outlet, useLocation } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import _ from 'lodash';
import axiosConf from '../../axios';

// material-ui
import { useTheme } from '@mui/material/styles';
import { Box, Toolbar, useMediaQuery } from '@mui/material';

// project import
import Drawer from './Drawer';
import Header from './Header';
import navigation from '../../menu-items';
import Breadcrumbs from '../../components/@extended/Breadcrumbs';

// types
import { openDrawer } from '../../store/reducers/menu';
import { setResponseCode } from '../../store/reducers/staticVar';

// common
import { gridApiObj } from '../../lib/common';

// common Echarts
import * as echarts from 'echarts/core';
import { ScatterChart, PieChart } from 'echarts/charts';
import { TooltipComponent, GridComponent, LegendComponent, ToolboxComponent } from 'echarts/components';
import { CanvasRenderer } from 'echarts/renderers';

// ==============================|| MAIN LAYOUT ||============================== //

const MainLayout = () => {
    const location = useLocation();
    const theme = useTheme();
    const matchDownLG = useMediaQuery(theme.breakpoints.down('xl'));
    const dispatch = useDispatch();

    const { drawerOpen, openNav } = useSelector((state) => state.menu);

    useEffect(() => {
        let loop = null;

        // use Echarts module import
        echarts.use([CanvasRenderer, TooltipComponent, GridComponent, LegendComponent, ToolboxComponent, ScatterChart, PieChart]);

        if (!location.pathname.includes("popup/")) {
            // HTTP Response Code
            axiosConf.get("/api/setting/code/http/findHttpName/HTTP%20Response%20Code").then(res => {
                dispatch(setResponseCode(_.groupBy(res.data, "name")));
            });

            // Interval
            /* loop = setInterval(() => {
                const loginedUser = localStorage.getItem('authenticatedUser');

                if (loginedUser) {
                    //console.log(loginedUser);
                } else {
                    //console.log("clear")
                    clearInterval(loop);
                }
            }, 60000); */
        }

        return () => {
            if (loop) {
                clearInterval(loop);
            }
        }
    }, []);

    // drawer toggler
    const [open, setOpen] = useState(drawerOpen);
    const handleDrawerToggle = () => {
        setOpen(!open);
        dispatch(openDrawer({ drawerOpen: !open }));

        setTimeout(() => {
            _.map(gridApiObj, (obj) => {
                if (obj) {
                    if (obj.columnApi.getAllColumns().length > 10) {
                        obj.columnApi.autoSizeColumns();
                    } else {
                        obj.api.sizeColumnsToFit();
                    }
                }
            });
        }, 500);
    };

    // set media wise responsive drawer
    useEffect(() => {
        setOpen(!matchDownLG);
        dispatch(openDrawer({ drawerOpen: !matchDownLG }));
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [matchDownLG]);

    useEffect(() => {
        if (open !== drawerOpen) setOpen(drawerOpen);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [drawerOpen]);

    return (
        <Box sx={{ display: 'flex', width: '100%' }}>
            <Header open={open} openNav={openNav} handleDrawerToggle={handleDrawerToggle} />
            <Drawer open={open} handleDrawerToggle={handleDrawerToggle} />
            <Box component="main" sx={{ width: '100%', flexGrow: 1, p: { xs: 2, sm: 3 }, padding: ".1rem !important" }}>
                <Toolbar sx={{ minHeight: "35px" }} />
                <Breadcrumbs navigation={navigation} title titleBottom card={false} divider={false} />
                <Outlet />
            </Box>
        </Box>
    );
};

export default MainLayout;