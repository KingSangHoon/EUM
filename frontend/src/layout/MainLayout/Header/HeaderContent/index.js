// project import
import Notification from './Notification';
import Logout from './Logout';
import Clock from 'react-live-clock';
import { Typography } from '@mui/material';

// ==============================|| HEADER - CONTENT ||============================== //

const HeaderContent = () => {
    const loginedUser = localStorage.getItem('authenticatedUser');

    return (
        <>
            <Typography component="span" mr={1}>
                <Typography component="span" className="font-blue">
                    {loginedUser}
                </Typography> 님, 환영합니다.
            </Typography>
            <Clock format={'YYYY-MM-DD HH:mm:ss'} ticking={true} timezone={'Asia/Seoul'}></Clock>
            <Notification />
            <Logout />
        </>
    );
};

export default HeaderContent;