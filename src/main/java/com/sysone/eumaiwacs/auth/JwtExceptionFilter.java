package com.sysone.eumaiwacs.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.service.auth.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtExceptionFilter extends OncePerRequestFilter {

    @Value("${app.sysone.signinKey}")
    private String signinKey;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    Logger logger = LoggerFactory.getLogger(JwtExceptionFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {

            String jwt = parseJwt(request);

            if(!jwt.equals("null")) {
                try {
                    Jwts.parser().setSigningKey(signinKey).parseClaimsJws(jwt);
                } catch (SignatureException e) {
                   logger.error("Invalid JWT signature: {}", e.getMessage());
                    throw new JwtException(Constants.ERROR_INVALIDJWTSIGNATURE_STR);
                } catch (MalformedJwtException e) {
                   logger.error("Invalid JWT token: {}", e.getMessage());
                    throw new JwtException(Constants.ERROR_INVALIDJWTTOKEN_STR);
                } catch (ExpiredJwtException e) {
                   logger.error("JWT token is expired: {}", e.getMessage());
                    throw new JwtException(Constants.ERROR_EXPIREDJWTTOKEN_STR);
                } catch (UnsupportedJwtException e) {
                    logger.error("JWT token is unsupported: {}", e.getMessage());
                    throw new JwtException(Constants.ERROR_UNSUPPORTEDJWTTOKEN_STR);
                } catch (IllegalArgumentException e) {
                    logger.error("JWT claims string is empty: {}", e.getMessage());
                    throw new JwtException(Constants.ERROR_EMPTYJWTCLAIMSSTRING_STR);
                }
            }

        } catch (JwtException ex) {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, ex);
        }

        chain.doFilter(request, response);
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, Throwable ex) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json; charset=UTF-8");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", ex.getMessage());
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }
}
