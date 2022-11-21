import PropTypes from 'prop-types';
import _ from 'lodash';
import { Link } from 'react-router-dom';

// material-ui
import { useTheme } from '@mui/material/styles';
import { AppBar, IconButton, Toolbar, useMediaQuery, Grid, Breadcrumbs, Typography } from '@mui/material';

// project import
import AppBarStyled from './AppBarStyled';
import HeaderContent from './HeaderContent';

// assets
import { MenuFoldOutlined, MenuUnfoldOutlined } from '@ant-design/icons';

// Menu Name
const staticMenuName = {
    0: {
        "dashboard": "Dashboard",
        "protocol": "Protocol",
        "map": "Map",
        "setting": "환경설정",
    },
    1: {
        "overview": { name: "Overview", group: true },
        "http": { name: "HTTP", group: true },
        "world": { name: "World", group: false },
        "domestic": { name: "국내", group: false },
        "gridSetting": { name: "그리드 설정", group: false },
        "companySetting": { name: "고객사 설정", group: false },
        "userSetting": { name: "사용자 설정", group: false },
        "code": { name: "코드 관리", group: true },
        "ipBand": { name: "IP 대역대 관리", group: true },
        "sensorDevice": { name: "Sensor Device 설정", group: false },
        "sslSetting": { name: "SSL 설정", group: false },
        "transaction": { name: "트랜잭션 설정", group: false },
        "threshold": { name: "레벨 설정", group: false },
        "alarm": { name: "알림 설정", group: false },
        "application": { name: "어플리케이션 설정", group: false },
        "band": { name: "대역 설정", group: false },
        "auditHistory": { name: "감사 이력", group: false },
        "criticalValue": { name: "임계치 설정", group: false },
    },
    2: {
        "pages": { name: "Pages", group: false },
        "uri": { name: "URI", group: false },
        "domestic": { name: "국내-지역별", group: false },
        "country": { name: "대륙-국가별", group: false },
        "idc": { name: "IDC", group: false },
        "isp": { name: "ISP", group: false },
        "protocol": { name: "Protocol", group: false },
        "userAgent": { name: "User Agent", group: false },
        "mime": { name: "MIME", group: false },
        "http": { name: "HTTP", group: false },
        "setting": { name: "환경설정", group: true },
    },
    3: {
        "detail": { name: "상세", group: false }
    }
};

// ==============================|| MAIN LAYOUT - HEADER ||============================== //

const Header = ({ open, openNav, handleDrawerToggle }) => {
    const theme = useTheme();
    const matchDownMD = useMediaQuery(theme.breakpoints.down('lg'));

    const iconBackColor = 'grey.100';
    const iconBackColorOpen = 'grey.200';
    const openNavStr = [];

    // common header
    const mainHeader = (
        <Toolbar sx={{ minHeight: "10px", paddingTop: "3px", paddingBottom: "3px" }}>
            <IconButton
                disableRipple
                aria-label="open drawer"
                onClick={handleDrawerToggle}
                edge="start"
                color="secondary"
                size="small"
                sx={{ color: 'text.primary', bgcolor: open ? iconBackColorOpen : iconBackColor, ml: { xs: 0, lg: -2 } }}
            >
                {!open ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            </IconButton>

            <Grid container>
                <Grid item sm={6}>
                    <Breadcrumbs sx={{ pl: 1, pt: 1 }}>
                        <Typography component={Link} to="/" variant="h6" sx={{ textDecoration: 'none' }} color="inherit">Home</Typography>
                        {
                            _.map(openNav, (str, i) => {
                                openNavStr.push(str);
                                return (
                                    i === 0 ? <Typography key={i} color="text.primary">{staticMenuName[i][str]}</Typography>
                                        : !staticMenuName[i][str] ? ""
                                            : staticMenuName[i][str].group ? <Typography key={i} color="text.primary">{staticMenuName[i][str].name}</Typography>
                                                : <Typography key={i} component={Link} to={"/" + openNavStr.join("/")} variant="h6" sx={{ textDecoration: 'none' }} color="inherit">{staticMenuName[i][str].name}</Typography>
                                );
                            })
                        }
                    </Breadcrumbs>
                </Grid>
                <Grid item sm={6} textAlign="right">
                    <HeaderContent />
                </Grid>
            </Grid>
        </Toolbar>
    );

    // app-bar params
    const appBar = {
        position: 'fixed',
        color: 'inherit',
        elevation: 0,
        sx: {
            borderBottom: `1px solid ${theme.palette.divider}`
            // boxShadow: theme.customShadows.z1
        }
    };

    return (
        <>
            {!matchDownMD ? (
                <AppBarStyled open={open} {...appBar}>
                    {mainHeader}
                </AppBarStyled>
            ) : (
                <AppBar {...appBar}>{mainHeader}</AppBar>
            )}
        </>
    );
};

Header.propTypes = {
    open: PropTypes.bool,
    handleDrawerToggle: PropTypes.func
};

export default Header;