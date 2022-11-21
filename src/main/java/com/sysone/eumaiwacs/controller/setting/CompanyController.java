package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.Company;
import com.sysone.eumaiwacs.service.setting.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/findAll")
    public List<Object> findAllCompany() {
        return companyService.findAll();
    }

    @PostMapping("/insert")
    public Company insertCompany(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return companyService.insertCompany(param, loginUser, req);
    }

    @PostMapping("/update")
    public Company updateCompany(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return companyService.updateCompany(param, loginUser, req);
    }

    @GetMapping("/delete/{idStr}")
    public void deleteCompany(@PathVariable String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        companyService.deleteCompany(idStr, loginUser, req);
    }

    @GetMapping("/mapping/user/{companyId}/{userIdStr}")
    public List<Object> mappingUserCompany(@PathVariable Integer companyId, @PathVariable String userIdStr) {
        return companyService.mappingUserCompany(companyId, userIdStr);
    }


}
