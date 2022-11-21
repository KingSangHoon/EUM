package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.User;
import com.sysone.eumaiwacs.service.setting.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/find/{userId}")
    public User findUser(@PathVariable Integer userId) {
        return userService.findUser(userId);
    }

    @PostMapping("/insert")
    public User insertUser(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return userService.insertUser(param, loginUser, req);
    }

    @PostMapping("/update")
    public User updatetUser(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return userService.updateUser(param, loginUser, req);
    }

    @GetMapping("/delete/{idStr}")
    public void deleteUser(@PathVariable String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        userService.deleteUser(idStr, loginUser, req);
    }

    @GetMapping("/active/{flag}/{idStr}")
    public void activeUser(@PathVariable Boolean flag, @PathVariable String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req){
        userService.activeUser(flag, idStr, loginUser, req);
    }

    @PostMapping("/search")
    public List<User> searchUser(@RequestBody Map<String, Object> param) {
        return userService.searchUser(param);
    }

    @GetMapping("/find/mapping/company")
    public List<Object> findMappingCompanyUser() {
        return userService.findMappingCompanyUser();
    }

}
