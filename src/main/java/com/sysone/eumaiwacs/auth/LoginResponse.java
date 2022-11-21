package com.sysone.eumaiwacs.auth;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginResponse {

    private String token;
    private String type = "Bearer";
    private Integer userId;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

    public LoginResponse(String accessToken, Integer userId, String username, Collection<? extends GrantedAuthority> authorities) {
        this.token = accessToken;
        this.userId = userId;
        this.username = username;
        this.authorities = authorities;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
