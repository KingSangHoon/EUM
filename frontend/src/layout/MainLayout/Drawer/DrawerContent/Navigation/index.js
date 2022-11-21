import { useState } from 'react';
import _ from 'lodash';

// material-ui
import { Box, Typography } from '@mui/material';

// project import
import NavGroup from './NavGroup';
import menuItem from '../../../../../menu-items';

// ==============================|| DRAWER CONTENT - NAVIGATION ||============================== //

const Navigation = () => {
    const [menuItems, setMenuItems] = useState(menuItem.items);

    const handleCollpaseNav = (index) => {
        setMenuItems(
            menuItems.map(item =>
                item.id === index ? { ...item, collpase: !item.collpase } : item
            )
        );
    }

    const handleCollpaseSubNav = (index, subIndex) => {
        let cloneMenu = _.cloneDeep(menuItems);

        const filterMenu = _.filter(cloneMenu, (obj) => {
            return obj.id === index;
        });

        if (filterMenu.length > 0) {
            filterMenu[0].children = _.map(filterMenu[0].children, (subItem) => {
                return subItem.id === subIndex ? { ...subItem, collpase: !subItem.collpase } : subItem;
            });

            setMenuItems(cloneMenu);
        }
    }

    const navGroups = menuItems.map((item) => {
        switch (item.type) {
            case 'group':
                return <NavGroup key={item.id} item={item} handleCollpaseNav={handleCollpaseNav} handleCollpaseSubNav={handleCollpaseSubNav} />;
            default:
                return (
                    <Typography key={item.id} variant="h6" color="error" align="center">
                        Fix - Navigation Group
                    </Typography>
                );
        }
    });

    return <Box sx={{ pt: 2 }}>{navGroups}</Box>;
};

export default Navigation;