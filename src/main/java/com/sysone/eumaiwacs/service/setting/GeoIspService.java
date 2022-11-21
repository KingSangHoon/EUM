package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.InfoIspIpv4;
import com.sysone.eumaiwacs.entity.setting.MappingIspIpv4Server;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GeoIspService {

    List<InfoIspIpv4> findAllIsp();
    List<MappingIspIpv4Server> findIpById(Integer groupId);

    InfoIspIpv4 findIspPrimary(Integer id);

    void createIsp(Map<String, Object> map, LoginUser loginUser, HttpServletRequest req);
    void updateIsp(Integer id, Map<String, Object> map, LoginUser loginUser, HttpServletRequest req);
    void deleteIspGroup(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req);

    Map<String, Object> findIspIpRangeByIp(String ip);

    Boolean createMappingIsp(Map<String, Object> domesticMap, LoginUser loginUser, HttpServletRequest req);
    Boolean updateMappingIsp(Integer id, Map<String, Object> ipMap, LoginUser loginUser, HttpServletRequest req);
    void deleteIpMapping(Set<Integer> idSet, LoginUser loginUser, HttpServletRequest req);
}
