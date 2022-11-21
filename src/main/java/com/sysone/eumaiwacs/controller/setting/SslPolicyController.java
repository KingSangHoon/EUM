package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.auth.ResponseResult;
import com.sysone.eumaiwacs.service.setting.SslPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/ssl")
public class SslPolicyController {

    @Autowired
    private SslPolicyService sslPolicyService;

    @GetMapping("/findAll")
    public List<Object> findAll() {
        return sslPolicyService.findAll();
    }

    @GetMapping("/find/{id}")
    public Object find(@PathVariable Integer id) {
        return sslPolicyService.find(id);
    }

    @Transactional
    @ResponseBody
    @RequestMapping(value="/save", method=RequestMethod.POST, produces="application/json")
    public ResponseResult save(MultipartHttpServletRequest req, @AuthenticationPrincipal LoginUser loginUser) throws IllegalStateException, IOException {
        return sslPolicyService.save(req, loginUser);
    }

    @GetMapping("/delete/{idStr}")
    public void delete(@PathVariable String idStr, @AuthenticationPrincipal LoginUser loginUser) {
        sslPolicyService.delete(idStr, loginUser);
    }

}
