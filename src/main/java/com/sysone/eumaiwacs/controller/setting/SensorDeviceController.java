package com.sysone.eumaiwacs.controller.setting;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.entity.setting.SensorDevice;
import com.sysone.eumaiwacs.service.setting.SensorDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/setting/sensorDevice")
public class SensorDeviceController {

    @Autowired
    private SensorDeviceService sensorDeviceService;

    @GetMapping("/findAll")
    public List<SensorDevice> findAll() {
        return sensorDeviceService.findAll();
    }

    @GetMapping("/find/{sensorId}")
    public SensorDevice find(@PathVariable Integer sensorId) {
        return sensorDeviceService.find(sensorId);
    }

    @PostMapping("/update")
    public SensorDevice update(@RequestBody Map<String, Object> param, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        return sensorDeviceService.update(param, loginUser, req);
    }

    @GetMapping("/findAllActive")
    public List<SensorDevice> findAllActive() {
        return sensorDeviceService.findAllActive();
    }

}
