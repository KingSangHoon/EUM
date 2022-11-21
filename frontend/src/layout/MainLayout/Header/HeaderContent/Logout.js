import {
    Box,
    IconButton,
} from '@mui/material';

// assets
import { LogoutOutlined } from '@ant-design/icons';
import axiosConf from "../../../../axios";

// ==============================|| HEADER CONTENT - Logout ||============================== //

const Logout = () => {
    const handleLocalLogout = () => {
        // axios({
        //     method: 'post',
        //     url: '/api/auth/logout'
        // }).then(res => {
        //     localStorage.removeItem("authenticatedUser");
        //     localStorage.removeItem("level");
        //     alert("로그아웃 되었습니다.");
        //     window.location.href = "/login";
        // })

        axiosConf.get("/api/auth/signout").then(res => {
            localStorage.removeItem("authenticatedUser");
            localStorage.removeItem("level");
            localStorage.removeItem("token");
            localStorage.removeItem("sideHide");

            alert("로그아웃 되었습니다.");
            window.location.href = "/login";
        });


    };

    return (
        <Box sx={{ flexShrink: 0, ml: 0.75, mr: "-16px" }} className="inline-block">
            <IconButton
                color="primary"
                size="small"
                sx={{ fontSize: "1rem", bgcolor: "white" }}
                onClick={handleLocalLogout}
            >
                <LogoutOutlined />
            </IconButton>
        </Box>
    );
};

export default Logout;