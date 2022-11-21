// material-ui
import { styled } from '@mui/material/styles';
import LinearProgress from '@mui/material/LinearProgress';

// loader style
const LoaderWrapper = styled('div')(({ theme }) => ({
    /* position: 'fixed',
    top: 0,
    left: 0, */
    position: 'absolute',
    textAlign: 'center',
    inset: 0,
    alignItems: 'center',
    justifyContent: 'center',
    zIndex: 2001,
    width: '100%',
    '& > * + *': {
        marginTop: theme.spacing(2)
    }
}));

// ==============================|| Loader ||============================== //

const Loader = () => (
    <LoaderWrapper>
        <LinearProgress color="primary" />
    </LoaderWrapper>
);

export default Loader;