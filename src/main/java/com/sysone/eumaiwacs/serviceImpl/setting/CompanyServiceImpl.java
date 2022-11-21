package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.Company;
import com.sysone.eumaiwacs.entity.setting.User;
import com.sysone.eumaiwacs.repository.setting.CompanyRepository;
import com.sysone.eumaiwacs.repository.setting.UserRepository;
import com.sysone.eumaiwacs.service.setting.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuditUtil auditUtil;

    @Override
    public List<Object> findAll() {
        List<Object> result = new ArrayList<>();

        List<Company> companyList = companyRepository.findAll();

        for(Company company : companyList) {
            company.setCompanyName(Util.latin1ToUtf8(company.getCompanyName()));
            if(company.getDescription() != null )company.setDescription(Util.latin1ToUtf8(company.getDescription()));

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            Map<String, Object> companyMap = mapper.convertValue(company, Map.class);

            List<User> userList = userRepository.findUserByCompanyId(company.getCompanyId());

            for(User user : userList) {
                user.setUsername(Util.latin1ToUtf8(user.getUsername()));
            }

            companyMap.put("userList", userList);
            result.add(companyMap);
        }
        return result;
    }

    @Override
    public Company insertCompany(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Company company = mapper.convertValue(param, Company.class);

        auditUtil.insertAudit(loginUser, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_COMPANY, null, null, company.getCompanyName(), req);

        company.setCompanyName(Util.utf8ToLatin1(company.getCompanyName()));
        company.setDescription(Util.utf8ToLatin1(company.getDescription()));
        company.setRegDate(LocalDateTime.now());
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Company company = mapper.convertValue(param, Company.class);
        Company originCompany = companyRepository.findCompanyByCompanyId(company.getCompanyId());
        auditUtil.insertAudit(loginUser, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_COMPANY, null, null, company.getCompanyName(), req);

        company.setCompanyName(Util.utf8ToLatin1(company.getCompanyName()));
        company.setDescription(Util.utf8ToLatin1(company.getDescription()));
        company.setRegDate(originCompany.getRegDate());
        company.setModifyDate(LocalDateTime.now());
        return companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(String idStr, LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> companyIdSet = Util.getStringToIntegerSet(idStr);

        for(Integer id : companyIdSet) {
            Company company = companyRepository.findCompanyByCompanyId(id);
            String companyName = Util.latin1ToUtf8(company.getCompanyName());
            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_COMPANY, null, null, companyName, req);
        }

        userRepository.deleteMappingUserByCompanyIdSet(companyIdSet);
        companyRepository.deleteCompanyByCompantIdSet(companyIdSet);
    }

    @Override
    public List<Object> mappingUserCompany(Integer companyId, String userIdStr) {
        List<Object> result = new ArrayList<>();
        Set<Integer> userIdSet = Util.getStringToIntegerSet(userIdStr);

        userRepository.deleteMappingUserByCompanyId(companyId);
        userRepository.updateMappingUserByUserIdSet(companyId, userIdSet);

        List<User> userList = userRepository.findUserByCompanyId(companyId);
        if(userList.size() > 0) result.addAll(userList);

        return result;
    }

}
