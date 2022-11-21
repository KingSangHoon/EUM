package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.grid.GridGroup;
import com.sysone.eumaiwacs.service.setting.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/grid")
    public class GridController {

        @Autowired
        private GridService gridService;

        @GetMapping("/find/{gridName}")
        public Map<String, Object> findSettingGrid(@PathVariable String gridName, @AuthenticationPrincipal LoginUser loginUser) {
        return gridService.findSettingGrid(gridName, loginUser.getUserId());
    }

    @PostMapping("/update/{gridName}")
    public Map<String, Object> updateSettingGrid(@PathVariable String gridName, @AuthenticationPrincipal LoginUser loginUser, @RequestBody Map<String, Object> param) {
        return gridService.updateSettingGrid(gridName, loginUser.getUserId(), param);
    }

    @PostMapping("/update/index/{gridName}")
    public Map<String, Object> updateSettingGridIndex(@PathVariable String gridName, @AuthenticationPrincipal LoginUser loginUser, @RequestBody Map<String, Object> param) {
        return gridService.updateSettingGridIndex(gridName, loginUser.getUserId(), param);
    }

    @GetMapping("/findAll")
    public List<GridGroup> findAllGridGroup() {
        return gridService.findAllGridGroup();
    }

    @GetMapping("/findGroup/{gridGroupId}")
    public Map<String, Object> findGridGroup(@PathVariable Integer gridGroupId) {
        return gridService.findGridGroup(gridGroupId);
    }

    @PostMapping("/insertGroup")
    public void insertGridGroup(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        gridService.insertGridGroup(param, loginUser, req);
    }

    @PostMapping("/updateGroup")
    public void updateGridGroup(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        gridService.updateGridGroup(param, loginUser, req);
    }

    @GetMapping("/deleteGroup/{idStr}")
    public void deleteGridGroup(@PathVariable String idStr, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        gridService.deleteGridGroup(idStr, loginUser, req);
    }

    @GetMapping("/apply/{id}")
    public void applyGridGroup(@PathVariable Integer id, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
            gridService.applyGridGroup(id, loginUser, req);
    }

}
