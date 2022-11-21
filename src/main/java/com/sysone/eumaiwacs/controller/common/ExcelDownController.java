package com.sysone.eumaiwacs.controller.common;

import com.sysone.eumaiwacs.auth.LoginUser;
import com.sysone.eumaiwacs.common.AuditUtil;
import com.sysone.eumaiwacs.common.Constants;
import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.User;
import com.sysone.eumaiwacs.repository.setting.UserRepository;
import com.sysone.eumaiwacs.service.setting.AuditService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600, exposedHeaders = {"Content-Disposition"})
@RestController
@RequestMapping("/api/excel")
public class ExcelDownController {

    @Autowired private AuditUtil auditUtil;
    @Autowired private UserRepository userRepository;
    @Autowired private AuditService auditService;

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formatedNow = now.format(formatter);
        return formatedNow;
    }

    private CellStyle createCellStyle(SXSSFWorkbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        return cellStyle;
    };

    private Font createFont(SXSSFWorkbook wb) {
        Font font = wb.createFont();
        font.setFontHeight((short) 180);
        font.setFontName("맑은 고딕");
        return font;
    }

    private CellStyle getHeaderCellStyle(CellStyle style) {
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        return style;
    }
    private CellStyle getBodyCenterCellStyle(CellStyle style) {
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    private CellStyle getBodyLeftCellStyle(CellStyle style) {
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }
    private CellStyle getBodyRightCellStyle(CellStyle style) {
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    // 공통 - View에서 출력할 필드만 선택해서 보내는 Excel 다운로드
    @PostMapping("/field")
    public void downloadExcelGetDb(@AuthenticationPrincipal LoginUser loginUser, @RequestBody Map<String, Object> param, HttpServletRequest req, HttpServletResponse res) throws IOException {

        String category = (String) param.get("category");
        List<String> viewField = (List<String>) param.get("viewField");
        List<String> sortField = (List<String>) param.get("sort");

        List<Object[]> queryResult = new ArrayList<>();

        if(category.equals("audit")) queryResult = auditService.searchAuditByExcel(param);

        SXSSFWorkbook wb = new SXSSFWorkbook(-1);
        SXSSFSheet sh = wb.createSheet(category);

        Font headerFont = createFont(wb);
        Font bodyFont= createFont(wb);
        headerFont.setBold(true);

        CellStyle headerCellstyle = createCellStyle(wb);
        CellStyle bodyCenterCellstyle = createCellStyle(wb);
        CellStyle bodyLeftCellstyle = createCellStyle(wb);
        CellStyle bodyRightCellstyle = createCellStyle(wb);

        headerCellstyle.setFont(headerFont);
        bodyCenterCellstyle.setFont(bodyFont);
        bodyLeftCellstyle.setFont(bodyFont);
        bodyRightCellstyle.setFont(bodyFont);

        CellStyle headerStyle = getHeaderCellStyle(headerCellstyle);
        CellStyle bodyCenterStyle = getBodyCenterCellStyle(bodyCenterCellstyle);
        CellStyle bodyLeftStyle = getBodyLeftCellStyle(bodyLeftCellstyle);
        CellStyle bodyRightStyle = getBodyRightCellStyle(bodyRightCellstyle);

        Row row = sh.createRow(0);

        int cellNum = 0;
        for (String label : viewField) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(label);
            cell.setCellStyle(headerStyle);
            cellNum++;
        }

        int rowNum = 1;

        for(Object[] obj : queryResult) {
            row = sh.createRow(rowNum);

            for(int i=0;i<viewField.size();i++) {
                Cell cell = row.createCell(i);

                String value = "";
                if(obj[i] != null) value = Util.latin1ToUtf8(obj[i].toString());
                cell.setCellValue(value);

                if(sortField.get(i).equals("left")) cell.setCellStyle(bodyLeftStyle);
                else if(sortField.get(i).equals("right")) cell.setCellStyle(bodyRightStyle);
                else if(sortField.get(i).equals("center")) cell.setCellStyle(bodyCenterStyle);
            }

            rowNum++;
        }

        sh.trackAllColumnsForAutoSizing();
        short lastColumn = sh.getRow(sh.getLastRowNum()).getLastCellNum();
        for (int i = 0; i < lastColumn; i++) {
            sh.autoSizeColumn(i);
        }

        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; Filename=" + URLEncoder.encode(category+"_"+getCurrentTime(), "UTF-8") + ".xlsx");

        OutputStream fileOut = res.getOutputStream();
        wb.write(fileOut);
        fileOut.close();

        res.getOutputStream().flush();
        res.getOutputStream().close();

        auditUtil.insertAudit(loginUser, Constants.ACTION_DOWNLOAD, category, null, null, null, null, req);
    }

    // 공통 - View에서 데이터 정제해서 보내는 Excel 다운로드
    @PostMapping("/data")
    public void downloadExcelHttpPage(@AuthenticationPrincipal LoginUser loginUser, @RequestBody Map<String, Object> param, HttpServletRequest req, HttpServletResponse res) throws IOException {

        String category = (String) param.get("category");
        List<String> activeColumnName = (List<String>) param.get("activeColumnName");
        List<List<Map<String, Object>>> excelData = (List<List<Map<String, Object>>>) param.get("data");

        SXSSFWorkbook wb = new SXSSFWorkbook(-1);
        SXSSFSheet sh = wb.createSheet(category);

        Font headerFont = createFont(wb);
        Font bodyFont= createFont(wb);
        headerFont.setBold(true);

        CellStyle headerCellstyle = createCellStyle(wb);
        CellStyle bodyCenterCellstyle = createCellStyle(wb);
        CellStyle bodyLeftCellstyle = createCellStyle(wb);
        CellStyle bodyRightCellstyle = createCellStyle(wb);

        headerCellstyle.setFont(headerFont);
        bodyCenterCellstyle.setFont(bodyFont);
        bodyLeftCellstyle.setFont(bodyFont);
        bodyRightCellstyle.setFont(bodyFont);

        CellStyle headerStyle = getHeaderCellStyle(headerCellstyle);
        CellStyle bodyCenterStyle = getBodyCenterCellStyle(bodyCenterCellstyle);
        CellStyle bodyLeftStyle = getBodyLeftCellStyle(bodyLeftCellstyle);
        CellStyle bodyRightStyle = getBodyRightCellStyle(bodyRightCellstyle);

        Row row = sh.createRow(0);

        int cellNum = 0;
        for (String label : activeColumnName) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(label);
            cell.setCellStyle(headerStyle);
            cellNum++;
        }

        int rowNum = 1;

        for(List<Map<String, Object>> rowData : excelData) {
            row = sh.createRow(rowNum);

            cellNum = 0;
            for(Map<String, Object> data : rowData) {

                Cell cell = row.createCell(cellNum);

                String value = "";
                if(data.get("value") != null) value = data.get("value").toString();
                cell.setCellValue(value);

                if(data.get("sort").equals("left")) cell.setCellStyle(bodyLeftStyle);
                else if(data.get("sort").equals("right")) cell.setCellStyle(bodyRightStyle);
                else if(data.get("sort").equals("center")) cell.setCellStyle(bodyCenterStyle);

                cellNum++;
            }
            rowNum++;
        }

        sh.trackAllColumnsForAutoSizing();
        short lastColumn = sh.getRow(sh.getLastRowNum()).getLastCellNum();
        for (int i = 0; i < lastColumn; i++) {
            sh.autoSizeColumn(i);
        }

        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; Filename=" + URLEncoder.encode(category+"_"+getCurrentTime(), "UTF-8") + ".xlsx");

        OutputStream fileOut = res.getOutputStream();
        wb.write(fileOut);
        fileOut.close();

        res.getOutputStream().flush();
        res.getOutputStream().close();

        auditUtil.insertAudit(loginUser, Constants.ACTION_DOWNLOAD, category, null, null, null, null, req);
    }

    @PostMapping("/user")
    public void downloadExcelUser(@AuthenticationPrincipal LoginUser loginUser, HttpServletRequest req, HttpServletResponse res) throws IOException {

        SXSSFWorkbook wb = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
        SXSSFSheet sh = wb.createSheet("User");

        List<User> userList = userRepository.findAllUser();

        Row row = sh.createRow(0);

        String[] headerLabel = {"ID", "이름", "EMAIL", "PHONE", "권한", "등록일", "활성 여부"};

        Font headerFont = createFont(wb);
        Font bodyFont = createFont(wb);
        Font bodyRedFont = createFont(wb);
        Font bodyGreenFont = createFont(wb);

        headerFont.setBold(true);
        bodyRedFont.setColor(IndexedColors.RED.getIndex());
        bodyGreenFont.setColor(IndexedColors.GREEN.getIndex());

        CellStyle headerCellstyle = createCellStyle(wb);
        CellStyle bodyCenterCellstyle = createCellStyle(wb);
        CellStyle bodyLeftCellstyle = createCellStyle(wb);
        CellStyle bodyCenterFontRedCellStyle = createCellStyle(wb);
        CellStyle bodyCenterFontGreenCellStyle = createCellStyle(wb);

        headerCellstyle.setFont(headerFont);
        bodyCenterCellstyle.setFont(bodyFont);
        bodyLeftCellstyle.setFont(bodyFont);
        bodyCenterFontRedCellStyle.setFont(bodyRedFont);
        bodyCenterFontGreenCellStyle.setFont(bodyGreenFont);

        CellStyle headerStyle = getHeaderCellStyle(headerCellstyle);
        CellStyle bodyCenterStyle = getBodyCenterCellStyle(bodyCenterCellstyle);
        CellStyle bodyLeftStyle = getBodyLeftCellStyle(bodyLeftCellstyle);
        CellStyle bodyCenterFontRedStyle = getBodyCenterCellStyle(bodyCenterFontRedCellStyle);
        CellStyle bodyCenterFontGreenStyle = getBodyCenterCellStyle(bodyCenterFontGreenCellStyle);

        int cellNum = 0;
        for (String label : headerLabel) {
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(label);
            cell.setCellStyle(headerStyle);
            cellNum++;
        }

        int rowNum = 1;

        for (User user : userList) {
            row = sh.createRow(rowNum);
            cellNum = 0;
            Cell cell = row.createCell(cellNum);
            cell.setCellValue(user.getLoginId());
            cell.setCellStyle(bodyLeftStyle);
            cellNum++;

            cell = row.createCell(cellNum);
            cell.setCellValue(Util.latin1ToUtf8(user.getUsername()));
            cell.setCellStyle(bodyLeftStyle);
            cellNum++;

            cell = row.createCell(cellNum);
            cell.setCellValue(user.getEmail());
            cell.setCellStyle(bodyLeftStyle);
            cellNum++;

            cell = row.createCell(cellNum);
            cell.setCellValue(user.getPhoneNumber());
            cell.setCellStyle(bodyLeftStyle);
            cellNum++;

            cell = row.createCell(cellNum);
            String role = "";
            if (user.getRole() != null && user.getRole().equals("ROLE_ADMIN")) role = "관리자";
            if (user.getRole() != null && user.getRole().equals("ROLE_USER")) role = "사용자";
            cell.setCellValue(role);
            cell.setCellStyle(bodyCenterStyle);
            cellNum++;

            cell = row.createCell(cellNum);
            cell.setCellValue(Util.formatLocalDateToStringDateFormat(user.getRegDate()));
            cell.setCellStyle(bodyCenterStyle);
            cellNum++;

            cell = row.createCell(cellNum);
            String active = "";
            if (user.getActive()) {
                active = "활성";
                cell.setCellValue(active);
                cell.setCellStyle(bodyCenterFontGreenStyle);
            } else {
                active = "비활성";
                cell.setCellValue(active);
                cell.setCellStyle(bodyCenterFontRedStyle);
            }
            rowNum++;
        }

        sh.trackAllColumnsForAutoSizing();
        short lastColumn = sh.getRow(sh.getLastRowNum()).getLastCellNum();
        for (int i = 0; i < lastColumn; i++) {
            sh.autoSizeColumn(i);
        }

        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; Filename=" + URLEncoder.encode("User_"+getCurrentTime(), "UTF-8") + ".xlsx");

        OutputStream fileOut = res.getOutputStream();
        wb.write(fileOut);
        fileOut.close();

        res.getOutputStream().flush();
        res.getOutputStream().close();

        auditUtil.insertAudit(loginUser, Constants.ACTION_DOWNLOAD, "User", null, null, null, null, req);
    }
}
