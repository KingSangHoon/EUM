package com.sysone.eumaiwacs.controller.common;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.service.common.RealtimeSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/set")
public class RealtimeSettingController {

    @Autowired
    private AuditUtil auditUtil;

    @Autowired
    private RealtimeSettingService realtimeSettingService;

    @PostMapping("/applyIsp")
    public void applyIsp(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // 지역 관련 정보 재설정시 분석장치 재기동 필요
        realtimeSettingService.restartAnalyticsDevice();
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_ISP, null, null, null, req);
    }

    @PostMapping("/applyIdc")
    public void applyIdc(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // 지역 관련 정보 재설정시 분석장치 재기동 필요
        realtimeSettingService.restartAnalyticsDevice();
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_IDC, null, null, null, req);
    }

    @PostMapping("/applyContinent")
    public void applyContinent(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // 지역 관련 정보 재설정시 분석장치 재기동 필요
        realtimeSettingService.restartAnalyticsDevice();
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_GEO, null, null, null, req);
    }

    @PostMapping("/applyDomestic")
    public void applyDomestic(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // 지역 관련 정보 재설정시 분석장치 재기동 필요
        realtimeSettingService.restartAnalyticsDevice();
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_DOMESTIC, null, null, null, req);
    }

    @PostMapping("/applyUserAgent")
    public void applyUserAgent(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // User Agent 적용
        realtimeSettingService.applyUserAgent();
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_USERAGENT, null, null, null, req);
    }

    @PostMapping("/applySslKey/{sensorDeviceId}")
    public void applySslKey(@PathVariable Integer sensorDeviceId, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // SSL KEY 적용(해당 센서장치 재시작됨)
        realtimeSettingService.applySslKey(sensorDeviceId);
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_SSL, Constants.MENU_SETTING_SSL_KEY, null, null, req);
    }

    @PostMapping("/applyPCapBpf/{sensorDeviceId}")
    public void applyPCapBpf(@PathVariable Integer sensorDeviceId, @AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // PCAP BPF 적용(해당 센서장치 재시작됨)
        realtimeSettingService.applyPCapBpf(sensorDeviceId);
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_SENSOR_DEVICE, Constants.MENU_SETTING_PCAP_BPF, null, null, req);
    }

    @PostMapping("/applyTransactionSettings")
    public void applyTransactionSettings(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // 트랜잭션 설정 적옹(전체 센서장치 재시작됨)
        realtimeSettingService.applyTransactionSettings();
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_TRANSACTION, null, null, null, req);
    }

    @PostMapping("/applyApplicationSettings")
    public void applyApplicationSettings(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // 어플리케이션 설정 적용
        realtimeSettingService.applyApplicationSettings();
        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING_APPLICATION, null, null, null, req);
    }

    @PostMapping("/applyAlarmSettings")
    public void applyAlarmSettings(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req) {
        // 알람설정 적용
        realtimeSettingService.applyAlarmSettings();
//        auditUtil.insertAudit(loginUser, Constants.ACTION_APPLY, Constants.MENU_SETTING, Constants.MENU_SETTING, null, null, username, req);
    }

    // 여기서부터는 추후 필요시 사용(화면 미구현 부분)
    @PostMapping("/restartSensorDevice/{sensorDeviceId}")
    public void restartSensorDevice(@PathVariable Integer sensorDeviceId) {
        // 선택한 센서장치 재시작
        realtimeSettingService.restartSensorDevice(sensorDeviceId);
    }

    @PostMapping("/restartAnalyticsDevice")
    public void restartAnalyticsDevice() {
        // 분석장치 재시작
        realtimeSettingService.restartAnalyticsDevice();
    }

    @PostMapping("/restartStatDevice")
    public void restartStatDevice() {
        // 통계장치 재시작
        realtimeSettingService.restartStatDevice();
    }

    @PostMapping("/loadSensorIniFileRequest/{sensorDeviceId}")
    public void loadSensorIniFileRequest(@PathVariable Integer sensorDeviceId) {
        realtimeSettingService.loadSensorIniFileRequest(sensorDeviceId);
    }

    @GetMapping("/loadSensorIniFileResponse/{sensorDeviceId}")
    public String loadSensorIniFileResponse(@PathVariable Integer sensorDeviceId) {
        return realtimeSettingService.loadSensorIniFileResponse(sensorDeviceId).get(sensorDeviceId);
    }

    @PostMapping("/applySensorIniFile/{sensorDeviceId}")
    public void applySensorIniFile(@PathVariable Integer sensorDeviceId, @RequestBody String iniFileContents) {
        realtimeSettingService.applySensorIniFile(sensorDeviceId, iniFileContents);
    }
}
