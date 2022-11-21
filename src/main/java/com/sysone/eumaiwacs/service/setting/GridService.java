package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.grid.GridGroup;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GridService {

    Map<String, Object> findSettingGrid(String gridName, Integer userId);
    Map<String, Object> updateSettingGrid(String gridName, Integer userId, Map<String, Object> param);
    Map<String, Object> updateSettingGridIndex(String gridName, Integer userId, Map<String, Object> param);

    void insertUserGridSetting(Integer userId);
    void deleteUsersGridSetting(Set<Integer> userId);

    List<GridGroup> findAllGridGroup();
    Map<String, Object> findGridGroup(Integer gridGroupId);
    void insertGridGroup(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    void updateGridGroup(Map<String, Object> param, LoginUser loginUser, HttpServletRequest req);
    void deleteGridGroup(String idStr, LoginUser loginUser, HttpServletRequest req);
    void applyGridGroup(Integer gridGroupId, LoginUser loginUser, HttpServletRequest req);

}
