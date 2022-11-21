import PropTypes from 'prop-types';
import { useSelector } from 'react-redux';

// assets
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAngleDown, faAngleUp } from "@fortawesome/free-solid-svg-icons";

// material-ui
import { Box, List, Typography, ListItemIcon, ListSubheader, Collapse } from '@mui/material';

// project import
import NavItem from './NavItem';

// ==============================|| NAVIGATION - LIST GROUP ||============================== //

const NavGroup = ({ item, handleCollpaseNav, handleCollpaseSubNav }) => {
    const menu = useSelector((state) => state.menu);
    const { drawerOpen } = menu;
    const Icon = item.icon;
    const itemIcon = item.icon ? <Icon style={{ fontSize: drawerOpen ? '1rem' : '1.25rem' }} /> : false;

    const navCollapse = item.children?.map((menuItem) => {
        switch (menuItem.type) {
            case 'collapse':
                return (
                    <Typography key={menuItem.id} variant="caption" color="error" sx={{ p: 2.5 }}>
                        collapse - only available in paid version
                    </Typography>
                );
            case 'item':
                return <NavItem key={menuItem.id} item={menuItem} level={1} />;
            /* case 'subGroup':
                return <ListSubheader key={menuItem.id} component="div" color="primary" sx={{ background: "rgba(240, 240, 240, 0.8)" }}>
                    {menuItem.title}
                </ListSubheader>; */
            case 'subGroup':
                return <List key={menuItem.id} subheader={(<Box className="cursorp" onClick={() => handleCollpaseSubNav(item.id, menuItem.id)}>
                    <ListSubheader component="div" color="primary" sx={{ background: "rgba(240, 240, 240, 0.8)" }}>
                        {menuItem.title}
                        <Box sx={{ float: "right" }} mr={.4}>
                            {menuItem.collpase ? <FontAwesomeIcon icon={faAngleUp} /> : <FontAwesomeIcon icon={faAngleDown} />}
                        </Box>
                    </ListSubheader>
                </Box>)}>
                    <Collapse in={!menuItem.collpase} timeout="auto" unmountOnExit sx={{ background: "rgba(240, 240, 240, 0.2)" }}>
                        {
                            menuItem.children?.map((subMenuItem) => (
                                <NavItem key={subMenuItem.id} item={subMenuItem} level={1} />
                            ))
                        }
                    </Collapse>
                </List>;
            default:
                return (
                    <Typography key={menuItem.id} variant="h6" color="error" align="center">
                        Fix - Group Collapse or Items
                    </Typography>
                );
        }
    });

    return (
        <List
            subheader={
                item.title &&
                drawerOpen && (
                    <Box sx={{ pl: 2, mb: 1 }} className="cursorp" onClick={() => handleCollpaseNav(item.id)}>
                        {itemIcon && (
                            <ListItemIcon
                                sx={{
                                    display: 'inline-block',
                                    minWidth: 28,
                                    color: 'primary.main',
                                    ...(!drawerOpen && {
                                        borderRadius: 1.5,
                                        width: 36,
                                        height: 36,
                                        alignItems: 'center',
                                        justifyContent: 'center',
                                        '&:hover': {
                                            bgcolor: 'secondary.lighter'
                                        }
                                    })
                                }}
                            >
                                {itemIcon}
                            </ListItemIcon>
                        )}
                        <Typography variant="subtitle2" color="textSecondary" sx={{ display: 'inline-block' }}>
                            {item.title}

                            <Box sx={{ position: "absolute", top: "0rem", right: ".8rem" }}>
                                {item.collpase ? <FontAwesomeIcon icon={faAngleUp} /> : <FontAwesomeIcon icon={faAngleDown} />}
                            </Box>
                        </Typography>
                    </Box>
                )
            }
            sx={{ mb: drawerOpen ? 1.5 : 0, py: 0, zIndex: 0 }}
        >
            <Collapse in={!item.collpase} timeout="auto" unmountOnExit sx={{ background: "rgba(240, 240, 240, 0.2)" }}>
                {navCollapse}
            </Collapse>
        </List>
    );
};

NavGroup.propTypes = {
    item: PropTypes.object
};

export default NavGroup;