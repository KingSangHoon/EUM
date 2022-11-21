package com.sysone.eumaiwacs.service.auth;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.User;
import com.sysone.eumaiwacs.repository.setting.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findLoginInfo(username);
        if (user == null) throw new UsernameNotFoundException("ID " + username + " not found.");

        LoginUser loginUser = new LoginUser(user.getUserId(), username, user.getUsername(), user.getPassword(), user.getCompanyId(),
                user.getEmail(), user.getPhoneNumber(), user.getLevel(), true, true, true, true, authorities(user));


        return loginUser;
    }

    //권한다시
    private static Collection<SimpleGrantedAuthority> authorities(User user){
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        String rolename = user.getRole();

        if(rolename.equals("ROLE_ADMIN")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }if(rolename.equals("ROLE_PM")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }if(rolename.equals("ROLE_USER")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }
}
