import React, { Component } from 'react';
import * as AuthenticationService from './AuthenticationService';
import axios from 'axios';

// material-ui
import {
    Button,
    Grid,
    IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput,
    Stack,
} from '@mui/material';

// assets
import { EyeOutlined, EyeInvisibleOutlined } from '@ant-design/icons';

// ============================|| FIREBASE - LOGIN ||============================ //

class AuthLogin extends Component {
    constructor(props) {
        super(props)

        this.state = {
            username: "",
            password: "",
            showPassword: false
        }
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    handleKeyPress = (e) => {
        if (e.key === "Enter") {
            this.loginClicked();
        }
    }

    handleClickShowPassword = () => {
        const { showPassword } = this.state;
        this.setState({ showPassword: !showPassword });
    }

    handleMouseDownPassword = (event) => {
        event.preventDefault();
    }

    loginClicked = () => {
        try {
            AuthenticationService
                .executeJwtAuthenticationService(this.state.username, this.state.password)
                .then((response) => {
                    localStorage.setItem('authenticatedUser', response.data.username);
                    localStorage.setItem('level', response.data.authorities[0].authority);
                    localStorage.setItem('token', response.data.accessToken);

                    axios.defaults.headers.common['Authorization'] = this.createJWTToken(response.data.accessToken);
                    this.setupAxiosInterceptors(this.createJWTToken(response.data.accessToken))

                    alert("로그인되었습니다.");
                    window.location.href = "/";
                }).catch(error => {
                    if (error.response.status === 401) {
                        if (error.response.data.message === 'ERROR_UNAUTHRIZED') alert("ID/PASSWORD를 다시 확인해 주세요.");
                    } else {
                        alert("로그인에 실패하였습니다.");
                    }
                });
        } catch {
            alert("로그인에 실패하였습니다.");
        }
    }

    createJWTToken(token) {
        return 'Bearer ' + token
    }

    setupAxiosInterceptors(token) {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.Authorization = token
                }
                return config
            }
        )
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem("logged")
        if (user === null) return false
        return true
    }

    render() {
        const { username, password, showPassword } = this.state;

        return (
            <Grid container spacing={3}>
                <Grid item xs={12}>
                    <Stack spacing={1}>
                        <InputLabel htmlFor="username-login">ID</InputLabel>
                        <OutlinedInput
                            fullWidth
                            id="username-login"
                            type="text"
                            value={username}
                            name="username"
                            onChange={this.handleChange}
                            onKeyPress={this.handleKeyPress}
                            placeholder="Enter ID"
                        />
                    </Stack>
                </Grid>
                <Grid item xs={12}>
                    <Stack spacing={1}>
                        <InputLabel htmlFor="password-login">Password</InputLabel>
                        <OutlinedInput
                            fullWidth
                            id="password-login"
                            type={showPassword ? 'text' : 'password'}
                            value={password}
                            name="password"
                            onChange={this.handleChange}
                            onKeyPress={this.handleKeyPress}
                            endAdornment={
                                <InputAdornment position="end">
                                    <IconButton
                                        aria-label="toggle password visibility"
                                        onClick={this.handleClickShowPassword}
                                        onMouseDown={this.handleMouseDownPassword}
                                        edge="end"
                                        size="large"
                                    >
                                        {showPassword ? <EyeOutlined /> : <EyeInvisibleOutlined />}
                                    </IconButton>
                                </InputAdornment>
                            }
                            placeholder="Enter password"
                        />
                    </Stack>
                </Grid>

                <Grid item xs={12}>
                    <Button
                        disableElevation
                        fullWidth
                        size="large"
                        type="submit"
                        variant="contained"
                        color="primary"
                        onClick={this.loginClicked}
                    >
                        Login
                    </Button>
                </Grid>
            </Grid>
        );
    }
}

export default AuthLogin;