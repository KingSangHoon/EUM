import axios from 'axios';

axios.interceptors.request.use(function (config) {

    const accessToken = localStorage.getItem('token');
    const token = 'Bearer ' + accessToken;

    config.headers.Authorization = token;
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    // Do something with response data
    return response;
}, function (error) {

    if (error.response.status === 401) {

        const resultMsg = error.response.data.message;

        if (resultMsg === 'ERROR_INVALIDJWTSIGNATURE' || resultMsg === 'ERROR_UNSUPPORTEDJWTTOKEN' || resultMsg === 'ERROR_EMPTYJWTCLAIMSSTRING') {
            alert("유효하지 않은 TOKEN 값 입니다. 다시 로그인 해 주세요.");
        } else if (resultMsg === 'ERROR_EXPIREDJWTTOKEN') {
            alert("TOKEN이 만료되었습니다. 로그인 화면으로 돌아갑니다.");
        } else if (resultMsg === 'ERROR_UNAUTHRIZED' || resultMsg === 'ERROR_INVALIDJWTTOKEN') {
            alert("인증되지 않은 사용자입니다.");
        } else {
            alert("인증되지 않은 사용자입니다.");
        }

        if (window.location.pathname !== '/login') {
            localStorage.removeItem("authenticatedUser");
            localStorage.removeItem("level");
            localStorage.removeItem("token");
            localStorage.removeItem("sideHide");
            window.location.href = "/login";
        }

    } else if (error.response.status === 404) {
        alert("잘못된 페이지 접근입니다.");

    } else if (error.response.status === 500) {
        alert("Server Error. 관리자에게 문의하세요.");
    }
    return Promise.reject(error);
});

export default axios;