package com.sysone.eumaiwacs.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@ToString
public class LoginUser extends User {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private Integer userId;
    private String loginId;
    private Integer companyId;
    private String roles;
    private String userName;
    private String email;
    private String phoneNumber;

    public LoginUser(Integer userId, String username, String userName, String password, Integer companyId, String email, String phoneNumber,
                       String roles, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities){
        super(companyId + " " + roles + " " + username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.loginId = username;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.companyId = companyId;
    }

}
