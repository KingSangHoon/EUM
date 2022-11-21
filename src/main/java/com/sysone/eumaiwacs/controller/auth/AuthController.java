package com.sysone.eumaiwacs.controller.auth;

import com.sysone.eumaiwacs.auth.JwtUtils;
import com.sysone.eumaiwacs.auth.LoginRequest;
import com.sysone.eumaiwacs.auth.LoginResponse;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.entity.setting.AuditHistory;
import com.sysone.eumaiwacs.repository.setting.UserRepository;
import com.sysone.eumaiwacs.service.auth.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailServiceImpl;

    @Autowired
    private AuditUtil auditUtil;

    @PostMapping("/signin")
    public ResponseEntity authenticationUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest req) {

        String username = loginRequest.getUsername();

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());

        LoginUser user = (LoginUser) userDetailServiceImpl.loadUserByUsername(username);
        LoginResponse loginResponse = new LoginResponse(jwt, user.getUserId(), user.getLoginId(), user.getAuthorities());

        auditUtil.insertAudit(user, Constants.ACTION_LOGIN, Constants.ACTION_LOGIN, null, null, null, username, req);

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/signout")
    public void singout(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        auditUtil.insertAudit(loginUser, Constants.ACTION_LOGOUT, Constants.ACTION_LOGOUT, null, null, null, loginUser.getLoginId(), req);
    }
}
