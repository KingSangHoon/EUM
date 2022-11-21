import axios from 'axios';

export function executeJwtAuthenticationService(username, password) {
    return axios({
        method: 'post',
        url: '/api/auth/signin',
        data: {
            username,
            password
        }
    })
}

export function createBasicAuthToken(username, password) {
    return 'Bearer ' + window.btoa(username + ":" + password)
}

export function executeBasicAuthenticationService(username, password) {
    return axios.get(`/api/auth/basicauth`,
        { headers: { Authorization: this.createBasicAuthToken(username, password) } })
}

export function logout() {
    const { history } = this.props;
    localStorage.removeItem("authenticatedUser");
    alert("로그아웃 되었습니다.");
    history.push("/");
}