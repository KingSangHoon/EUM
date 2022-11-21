// material-ui
import { Grid } from '@mui/material';

// project import
import AuthLogin from './AuthLogin';
import AuthWrapper from './AuthWrapper';
import Logo from '../../components/Logo';

// ================================|| LOGIN ||================================ //

const Login = () => (
    <AuthWrapper>
        <Grid container spacing={3}>
            <Grid item xs={12} textAlign={"center"}>
                <Logo />
            </Grid>
            <Grid item xs={12}>
                <AuthLogin />
            </Grid>
        </Grid>
    </AuthWrapper>
);

export default Login;