package com.sysone.eumaiwacs.serviceImpl.setting;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.grid.*;
import com.sysone.eumaiwacs.repository.grid.*;
import com.sysone.eumaiwacs.service.setting.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class GridServiceImpl implements GridService {

    @Autowired private AuditUtil auditUtil;

    @Autowired private GridGroupRepository gridGroupRepository;

    @Autowired private GridUserRepository gridUserRepository;
    @Autowired private GridUserIndexRepository gridUserIndexRepository;
    @Autowired private GridGroupUserRepository gridGroupUserRepository;

    @Autowired private GridRealtimePageRepository gridRealtimePageRepository;
    @Autowired private GridRealtimePageIndexRepository gridRealtimePageIndexRepository;
    @Autowired private GridGroupRealtimePageRepository gridGroupRealtimePageRepository;

    @Autowired private GridRealtimeUriRepository gridRealtimeUriRepository;
    @Autowired private GridRealtimeUriIndexRepository gridRealtimeUriIndexRepository;
    @Autowired private GridGroupRealtimeUriRepository gridGroupRealtimeUriRepository;


    @Override
    public Map<String, Object> findSettingGrid(String gridName, Integer userId) {
        Map<String, Object> result = new HashMap<>();

        if(gridName.equals(Constants.SETTING_GRID_USER)) {
            result.put("grid", gridUserRepository.findGridUserByUserId(userId));
            result.put("index", gridUserIndexRepository.findGridUserIndexByUserId(userId));
        }

        if(gridName.equals(Constants.SETTING_GRID_PROTOCOL_HTTP_PAGE)) {
            result.put("grid", gridRealtimePageRepository.findGridRealtimePageByUserId(userId));
            result.put("index", gridRealtimePageIndexRepository.findGridRealtimePageIndexByUserId(userId));
        }

        if(gridName.equals(Constants.SETTING_GRID_PROTOCOL_HTTP_URI)) {
            result.put("grid", gridRealtimeUriRepository.findGridRealtimeUriByUserId(userId));
            result.put("index", gridRealtimeUriIndexRepository.findGridRealtimeUriIndexByUserId(userId));
        }
        return result;
    }

    @Override
    public Map<String, Object> updateSettingGrid(String gridName, Integer userId, Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(gridName.equals(Constants.SETTING_GRID_USER)) {
            GridUser gridUser = mapper.convertValue(param, GridUser.class);
            gridUser.setUserId(userId);
            gridUserRepository.saveAndFlush(gridUser);

            result.put("grid", gridUserRepository.findGridUserByUserId(userId));
            result.put("index", gridUserIndexRepository.findGridUserIndexByUserId(userId));
        }

        if(gridName.equals(Constants.SETTING_GRID_PROTOCOL_HTTP_PAGE)) {
            GridRealtimePage gridRealtimePage = mapper.convertValue(param, GridRealtimePage.class);
            gridRealtimePage.setUserId(userId);
            gridRealtimePageRepository.saveAndFlush(gridRealtimePage);

            result.put("grid", gridRealtimePageRepository.findGridRealtimePageByUserId(userId));
            result.put("index", gridRealtimePageIndexRepository.findGridRealtimePageIndexByUserId(userId));
        }

        if(gridName.equals(Constants.SETTING_GRID_PROTOCOL_HTTP_URI)) {
            GridRealtimeUri gridRealtimeUri = mapper.convertValue(param, GridRealtimeUri.class);
            gridRealtimeUri.setUserId(userId);
            gridRealtimeUriRepository.saveAndFlush(gridRealtimeUri);

            result.put("grid", gridRealtimeUriRepository.findGridRealtimeUriByUserId(userId));
            result.put("index", gridRealtimeUriIndexRepository.findGridRealtimeUriIndexByUserId(userId));
        }

        return result;
    }

    @Override
    public Map<String, Object> updateSettingGridIndex(String gridName, Integer userId, Map<String, Object> param) {
        Map<String, Object> result = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(gridName.equals(Constants.SETTING_GRID_USER)) {
            GridUserIndex gridUserIndex = mapper.convertValue(param, GridUserIndex.class);
            gridUserIndex.setUserId(userId);
            gridUserIndexRepository.saveAndFlush(gridUserIndex);

            result.put("grid", gridUserRepository.findGridUserByUserId(userId));
            result.put("index", gridUserIndexRepository.findGridUserIndexByUserId(userId));
        }

        if(gridName.equals(Constants.SETTING_GRID_PROTOCOL_HTTP_PAGE)) {
            GridRealtimePageIndex gridRealtimePageIndex = mapper.convertValue(param, GridRealtimePageIndex.class);
            gridRealtimePageIndex.setUserId(userId);
            gridRealtimePageIndexRepository.saveAndFlush(gridRealtimePageIndex);

            result.put("grid", gridRealtimePageRepository.findGridRealtimePageByUserId(userId));
            result.put("index", gridRealtimePageIndexRepository.findGridRealtimePageIndexByUserId(userId));
        }

        if(gridName.equals(Constants.SETTING_GRID_PROTOCOL_HTTP_URI)) {
            GridRealtimeUriIndex gridRealtimeUriIndex = mapper.convertValue(param, GridRealtimeUriIndex.class);
            gridRealtimeUriIndex.setUserId(userId);
            gridRealtimeUriIndexRepository.saveAndFlush(gridRealtimeUriIndex);

            result.put("grid", gridRealtimeUriRepository.findGridRealtimeUriByUserId(userId));
            result.put("index", gridRealtimeUriIndexRepository.findGridRealtimeUriIndexByUserId(userId));
        }

        return result;
    }

    @Override
    @Transactional
    public void insertUserGridSetting(Integer userId) {
        // Grid User
        GridUser gridUser = new GridUser();
        gridUser.setUserId(userId);
        gridUserRepository.save(gridUser);

        GridUserIndex gridUserIndex = new GridUserIndex();
        gridUserIndex.setUserId(userId);
        gridUserIndexRepository.save(gridUserIndex);

        //Grid Realtime Page
        GridRealtimePage gridRealtimePage = new GridRealtimePage();
        gridRealtimePage.setUserId(userId);
        gridRealtimePageRepository.save(gridRealtimePage);

        GridRealtimePageIndex gridRealtimePageIndex = new GridRealtimePageIndex();
        gridRealtimePageIndex.setUserId(userId);
        gridRealtimePageIndexRepository.save(gridRealtimePageIndex);

        //Grid Realtime Uri
        GridRealtimeUri gridRealtimeUri = new GridRealtimeUri();
        gridRealtimeUri.setUserId(userId);
        gridRealtimeUriRepository.save(gridRealtimeUri);

        GridRealtimeUriIndex gridRealtimeUriindex = new GridRealtimeUriIndex();
        gridRealtimeUriindex.setUserId(userId);
        gridRealtimeUriIndexRepository.save(gridRealtimeUriindex);

    }

    @Override
    @Transactional
    public void deleteUsersGridSetting(Set<Integer> userIdSet) {
        // Grid User
        gridUserRepository.deleteGridUsersByUserId(userIdSet);
        gridUserIndexRepository.deleteGridUsersByUserId(userIdSet);

        //Grid Realtime Page
        gridRealtimePageRepository.deleteGridRealtimePageByUserId(userIdSet);
        gridRealtimePageIndexRepository.deleteGridRealtimePageIndexByUserId(userIdSet);

        //Grid Realtime Uri
        gridRealtimeUriRepository.deleteGridRealtimeUriByUserId(userIdSet);
        gridRealtimeUriIndexRepository.deleteGridRealtimeUriIndexByUserId(userIdSet);
    }

    @Override
    public List<GridGroup> findAllGridGroup() {
        List<GridGroup> result = gridGroupRepository.findAll(Sort.by(Sort.Direction.DESC, "gridGroupId"));

        for(GridGroup gridGroup : result) {
            gridGroup.setGridGroupName(Util.latin1ToUtf8(gridGroup.getGridGroupName()));
            gridGroup.setDescription(Util.latin1ToUtf8(gridGroup.getDescription()));
        }
        return result;
    }

    @Override
    public Map<String, Object> findGridGroup(Integer gridGroupId) {
        Map<String, Object> result = new HashMap<>();

        GridGroup gridGroup = gridGroupRepository.findGridGroupByGridGroupId(gridGroupId);
        gridGroup.setGridGroupName(Util.latin1ToUtf8(gridGroup.getGridGroupName()));
        gridGroup.setDescription(Util.latin1ToUtf8(gridGroup.getDescription()));

        result.put("group", gridGroup);
        result.put(Constants.SETTING_GRID_GROUP_USER, gridGroupUserRepository.findGridGroupUsersByGridGroupId(gridGroupId));
        result.put(Constants.SETTING_GRID_GROUP_PROTOCOL_HTTP_PAGE, gridGroupRealtimePageRepository.findGridGroupRealtimePageByGridGroupId(gridGroupId));
        result.put(Constants.SETTING_GRID_GROUP_PROTOCOL_HTTP_URI, gridGroupRealtimeUriRepository.findGridGroupRealtimeUriByGridGroupId(gridGroupId));

        return result;
    }

    @Override
    @Transactional
    public void insertGridGroup(Map<String, Object> param, LoginUser user, HttpServletRequest req) {

        String gridGroupName = param.get("gridGroupName").toString();
        String desc = param.get("desc").toString();

        GridGroup gridGroup = new GridGroup();
        gridGroup.setGridGroupName(Util.utf8ToLatin1(gridGroupName));
        gridGroup.setDescription(Util.utf8ToLatin1(desc));
        gridGroup.setRegDate(LocalDateTime.now());
        gridGroup.setFirstWriter(user.getLoginId());
        GridGroup insertGridGroup = gridGroupRepository.save(gridGroup);

        Integer gridGroupId = insertGridGroup.getGridGroupId();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Map<String, Object> gridGroupUserParam = (Map<String, Object>) param.get(Constants.SETTING_GRID_GROUP_USER);
        GridGroupUser gridGroupUser = mapper.convertValue(gridGroupUserParam, GridGroupUser.class);
        gridGroupUser.setGridGroupId(gridGroupId);
        gridGroupUserRepository.save(gridGroupUser);

        Map<String, Object> gridGroupRealtimePageParam = (Map<String, Object>) param.get(Constants.SETTING_GRID_GROUP_PROTOCOL_HTTP_PAGE);
        GridGroupRealtimePage gridGroupRealtimePage = mapper.convertValue(gridGroupRealtimePageParam, GridGroupRealtimePage.class);
        gridGroupRealtimePage.setGridGroupId(gridGroupId);
        gridGroupRealtimePageRepository.save(gridGroupRealtimePage);

        Map<String, Object> gridGroupRealtimeUriParam = (Map<String, Object>) param.get(Constants.SETTING_GRID_GROUP_PROTOCOL_HTTP_URI);
        GridGroupRealtimeUri gridGroupRealtimeUri = mapper.convertValue(gridGroupRealtimeUriParam, GridGroupRealtimeUri.class);
        gridGroupRealtimeUri.setGridGroupId(gridGroupId);
        gridGroupRealtimeUriRepository.save(gridGroupRealtimeUri);

        auditUtil.insertAudit(user, Constants.ACTION_CREATE, Constants.MENU_SETTING, Constants.MENU_SETTING_GRID, null, null, gridGroupName, req);
    }

    @Override
    public void updateGridGroup(Map<String, Object> param, LoginUser user, HttpServletRequest req) {

        Integer gridGroupId = (Integer) param.get("gridGroupId");
        String gridGroupName = param.get("gridGroupName").toString();
        String desc = param.get("desc").toString();

        GridGroup originGridGroup = gridGroupRepository.findGridGroupByGridGroupId(gridGroupId);

        GridGroup gridGroup = new GridGroup();
        gridGroup.setGridGroupId(gridGroupId);
        gridGroup.setGridGroupName(Util.utf8ToLatin1(gridGroupName));
        gridGroup.setDescription(Util.utf8ToLatin1(desc));
        gridGroup.setRegDate(originGridGroup.getRegDate());
        gridGroup.setFirstWriter(originGridGroup.getFirstWriter());
        gridGroup.setModifyDate(LocalDateTime.now());
        gridGroup.setLastWriter(user.getLoginId());
        gridGroupRepository.saveAndFlush(gridGroup);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Map<String, Object> gridGroupUserParam = (Map<String, Object>) param.get(Constants.SETTING_GRID_GROUP_USER);
        GridGroupUser gridGroupUser = mapper.convertValue(gridGroupUserParam, GridGroupUser.class);
        gridGroupUser.setGridGroupId(gridGroupId);
        gridGroupUserRepository.save(gridGroupUser);

        Map<String, Object> gridGroupRealtimePageParam = (Map<String, Object>) param.get(Constants.SETTING_GRID_GROUP_PROTOCOL_HTTP_PAGE);
        GridGroupRealtimePage gridGroupRealtimePage = mapper.convertValue(gridGroupRealtimePageParam, GridGroupRealtimePage.class);
        gridGroupRealtimePage.setGridGroupId(gridGroupId);
        gridGroupRealtimePageRepository.save(gridGroupRealtimePage);

        Map<String, Object> gridGroupRealtimeUriParam = (Map<String, Object>) param.get(Constants.SETTING_GRID_GROUP_PROTOCOL_HTTP_URI);
        GridGroupRealtimeUri gridGroupRealtimeUri = mapper.convertValue(gridGroupRealtimeUriParam, GridGroupRealtimeUri.class);
        gridGroupRealtimeUri.setGridGroupId(gridGroupId);
        gridGroupRealtimeUriRepository.save(gridGroupRealtimeUri);

        auditUtil.insertAudit(user, Constants.ACTION_UPDATE, Constants.MENU_SETTING, Constants.MENU_SETTING_GRID, null, null, gridGroupName, req);
    }

    @Override
    @Transactional
    public void deleteGridGroup(String idStr, LoginUser loginUser, HttpServletRequest req) {
        Set<Integer> idSet = Util.getStringToIntegerSet(idStr);

        for(Integer id : idSet) {
            GridGroup gridGroup = gridGroupRepository.findGridGroupByGridGroupId(id);
            String gridGroupName = Util.latin1ToUtf8(gridGroup.getGridGroupName());
            auditUtil.insertAudit(loginUser, Constants.ACTION_DELETE, Constants.MENU_SETTING, Constants.MENU_SETTING_GRID, null, null, gridGroupName, req);
        }

        gridGroupUserRepository.deleteGridGroupUserByGridGroupIdSet(idSet);
        gridGroupRealtimePageRepository.deleteGridGroupRealtimePageByGridGroupIdSet(idSet);
        gridGroupRealtimeUriRepository.deleteGridGroupRealtimeUriByGridGroupIdSet(idSet);

        gridGroupRepository.deleteGridGroupByGridGroupIds(idSet);
    }

    @Override
    @Transactional
    public void applyGridGroup(Integer gridGroupId, LoginUser loginUser, HttpServletRequest req) {

        ObjectMapper mapper = new ObjectMapper();
        Integer userId = loginUser.getUserId();

        //Grid User
        GridGroupUser gridGroupUser = gridGroupUserRepository.findGridGroupUsersByGridGroupId(gridGroupId);
        Map<String, Object> userMap = mapper.convertValue(gridGroupUser, Map.class);
        userMap.remove("gridGroupId");

        GridUser gridUser = mapper.convertValue(userMap, GridUser.class);
        gridUser.setUserId(userId);
        gridUserRepository.save(gridUser);

        //Grid Realtime Page
        GridGroupRealtimePage gridGroupRealtimePage = gridGroupRealtimePageRepository.findGridGroupRealtimePageByGridGroupId(gridGroupId);
        Map<String, Object> pageMap = mapper.convertValue(gridGroupRealtimePage, Map.class);
        pageMap.remove("gridGroupId");

        GridRealtimePage gridRealtimePage = mapper.convertValue(pageMap, GridRealtimePage.class);
        gridRealtimePage.setUserId(userId);
        gridRealtimePageRepository.save(gridRealtimePage);

        //Grid Realtime URI
        GridGroupRealtimeUri gridGroupRealtimeUri = gridGroupRealtimeUriRepository.findGridGroupRealtimeUriByGridGroupId(gridGroupId);
        Map<String, Object> uriMap = mapper.convertValue(gridGroupRealtimeUri, Map.class);
        uriMap.remove("gridGroupId");

        GridRealtimeUri gridRealtimeUri = mapper.convertValue(uriMap, GridRealtimeUri.class);
        gridRealtimeUri.setUserId(userId);
        gridRealtimeUriRepository.save(gridRealtimeUri);

        GridGroup gridGroup = gridGroupRepository.findGridGroupByGridGroupId(gridGroupId);
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_GRID, null, null, gridGroup.getGridGroupName(), req);
    }
}
