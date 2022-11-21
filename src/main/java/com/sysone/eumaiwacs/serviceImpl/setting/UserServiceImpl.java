package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.User;
import com.sysone.eumaiwacs.repository.setting.CompanyRepository;
import com.sysone.eumaiwacs.repository.setting.UserRepositoryCustom;
import com.sysone.eumaiwacs.repository.setting.UserRepository;
import com.sysone.eumaiwacs.service.setting.GridService;
import com.sysone.eumaiwacs.service.setting.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private GridService gridService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRepositoryCustom userRepositoryCustom;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AuditUtil auditUtil;

    @Override
    public List<User> findAll() {
        List<User> result = userRepository.findAllUser();

        for(User user : result) {
            user.setUsername(Util.latin1ToUtf8(user.getUsername()));
        }
        return result;
    }

    @Override
    public User findUser(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        user.setUsername(Util.latin1ToUtf8(user.getUsername()));
        return user;
    }

    @Override
    @Transactional
    public User insertUser(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        User user = new User();

        String loginId = param.get("loginId").toString();
        String username = param.get("username").toString();
        String password = param.get("password").toString();
        String email = param.get("email").toString();
        String phoneNumber = param.get("phoneNumber").toString();
        String role = param.get("role").toString();
        Boolean active= (Boolean) param.get("active");

        user.setCompanyId(1);
        user.setLoginId(loginId);
        user.setUsername(Util.utf8ToLatin1(username));
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setRegDate(LocalDateTime.now());
        user.setActive(active);
        user.setDeleted(false);

        User insertUser = userRepository.save(user);
        gridService.insertUserGridSetting(insertUser.getUserId());

        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_USER, null, null, loginId, req);

        return insertUser;
    }

    @Override
    public User updateUser(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {

        User user = new User();

        Integer userId = (Integer) param.get("userId");
        String loginId = param.get("loginId").toString();
        String username = param.get("username").toString();
        String password = param.get("password").toString();
        String email = param.get("email").toString();
        String phoneNumber = param.get("phoneNumber").toString();
        String role = param.get("role").toString();
        Boolean active= (Boolean) param.get("active");

        user.setUserId(userId);
        user.setCompanyId(1);
        user.setLoginId(loginId);
        user.setUsername(Util.utf8ToLatin1(username));
        user.setPassword(encoder.encode(password));
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);
        user.setActive(active);
        user.setRegDate(userRepository.findUserByUserId(user.getUserId()).getRegDate());
        user.setModifyDate(LocalDateTime.now());
        user.setDeleted(false);

        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_USER, null, null, loginId, req);

        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUser(String userIdStr, LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = Util.getStringToIntegerSet(userIdStr);

        for(Integer id : idSet) {
            User user = userRepository.findUserByUserId(id);
            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_USER, null, null, user.getLoginId(), req);
        }
        userRepository.deleteUserByIdSet(idSet);
        gridService.deleteUsersGridSetting(idSet);
    }

    @Override
    public void activeUser(Boolean flag, String userIdStr, LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = Util.getStringToIntegerSet(userIdStr);
        userRepository.activeUserByIdSet(flag, idSet);

        for(Integer id : idSet) {
            User user = userRepository.findUserByUserId(id);
            String flagStr = Constants.ACTION_ACTIVE;
            if (!flag) flagStr = Constants.ACTION_INACTIVE;
            auditUtil.insertAudit(loginUser, flagStr, Constants.MENU_SETTING, Constants.MENU_SETTING_USER, null, null, user.getLoginId(), req);
        }
    }

    @Override
    public List<User> searchUser(Map<String, Object> param) {
        String loginId = param.get("loginId") == null ? null : param.get("loginId").toString();
        String username = param.get("username") == null ? null : param.get("username").toString();
        String email = param.get("email") == null ? null : param.get("email").toString();
        String phoneNumber = param.get("phoneNumber") == null ? null : param.get("phoneNumber").toString();
        List<String> roles = param.get("roles") == null ? null : (List<String>) param.get("roles");
        Boolean active = param.get("active") == null ? null : (Boolean) param.get("active");

        return userRepositoryCustom.searchUser(loginId, username, email, phoneNumber, roles, active);
    }

    @Override
    public List<Object> findMappingCompanyUser() {
        List<Object> result = new ArrayList<>();

        List<User> userList = userRepository.findAllUser();
        for(User user : userList) {
            user.setUsername(Util.latin1ToUtf8(user.getUsername()));

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            Map<String, Object> userMap = mapper.convertValue(user, Map.class);

            Integer companyId = user.getCompanyId();

            if(companyId == 1) userMap.put("companyName", null);
            else {
                String companyName = companyRepository.findCompanyByCompanyId(user.getCompanyId()).getCompanyName();
                userMap.put("companyName", Util.latin1ToUtf8(companyName));
            }
            result.add(userMap);
        }
        return result;
    }

}
