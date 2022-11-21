package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoIdc;
import com.sysone.eumaiwacs.entity.setting.MappingIdcServer;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GeoIdcService {

    List<InfoIdc> findAllIdc();
    List<MappingIdcServer> findIpById(Integer groupId);
    void createIdc(Map<String, Object> map, LoginUser loginUser, HttpServletRequest req);
    void updateIdc(Integer id, Map<String, Object> map, LoginUser loginUser, HttpServletRequest req);
    void delete(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req);

    InfoIdc findIdcPrimary(Integer id);
    List<MappingIdcServer> findIdcIpRangeByIp(String ip);

    void createIdcMappingIp(Map<String, Object> map, LoginUser loginUser, HttpServletRequest req);
    void updateIdcMappingIp(Map<String, Object> map, Integer id, LoginUser loginUser, HttpServletRequest req);
    void deleteIpMapping(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req);
}
