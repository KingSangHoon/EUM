package com.sysone.eumaiwacs.common;

public class Constants {

    /* TOKEN STATUS STRING */
    public static final String ERROR_INVALIDJWTSIGNATURE_STR = "ERROR_INVALIDJWTSIGNATURE";
    public static final String ERROR_INVALIDJWTTOKEN_STR = "ERROR_INVALIDJWTTOKEN";
    public static final String ERROR_EXPIREDJWTTOKEN_STR = "ERROR_EXPIREDJWTTOKEN";
    public static final String ERROR_UNSUPPORTEDJWTTOKEN_STR = "ERROR_UNSUPPORTEDJWTTOKEN";
    public static final String ERROR_EMPTYJWTCLAIMSSTRING_STR = "ERROR_EMPTYJWTCLAIMSSTRING";
    public static final String ERROR_UNAUTHRIZED_STR = "ERROR_UNAUTHRIZED";

    /* Protocol */
    private static final String PROTO_L2_ETH = "4294967297";
    private static final String PROTO_L3_IP = "8589936640";
    private static final String PROTO_L3_IPV6 = "8589969117";
    private static final String PROTO_L3_NONIP = "18446744069414500000";
    private static final String PROTO_L4_TCP = "12884901894";
    private static final String PROTO_L4_UDP = "12884901905";
    private static final String PROTO_L7_HTTP = "1";

    /*
    UserAgent Type
     */
    public static final String USERAGENT_NOT_INFO = "Unknown";
    public static final String USERAGENT_DIRECT_PARSING = "Direct parsing";

    /*
    HTTP Response Code (Category)
     */
    public static final String HTTP_RESPONSE_CODE_1xx = "1xx";
    public static final String HTTP_RESPONSE_CODE_2xx = "2xx";
    public static final String HTTP_RESPONSE_CODE_3xx = "3xx";
    public static final String HTTP_RESPONSE_CODE_4xx = "4xx";
    public static final String HTTP_RESPONSE_CODE_5xx = "5xx";
    public static final String HTTP_RESPONSE_CODE_1xx_DESC = "Information responses (조건부 응답), 요청을 받았으며 작업을 계속함. HTTP 1.0 - 지원 X";
    public static final String HTTP_RESPONSE_CODE_2xx_DESC = "Successful responses (성공), 클라이언트가 요청한 동작을 수신하여 성공적으로 처리함.";
    public static final String HTTP_RESPONSE_CODE_3xx_DESC = "Redirection messages (리다이렉션 완료), 클라이언트는 요청을 마치기 위해 추가 동작을 취해야함.";
    public static final String HTTP_RESPONSE_CODE_4xx_DESC = "Client error responses (요청 오류), 클라이언트에 오류가 있음.";
    public static final String HTTP_RESPONSE_CODE_5xx_DESC = "Server error responses (서버 오류), 서버가 유효한 요청을 명백하게 수행하지 못했음.";


    /*
    그리드 설정 - 메뉴 Name
     */
    public static final String SETTING_GRID_USER = "user";
    public static final String SETTING_GRID_PROTOCOL_HTTP_PAGE = "realtimePage";
    public static final String SETTING_GRID_PROTOCOL_HTTP_URI = "realtimeUri";

    public static final String SETTING_GRID_GROUP_USER = "gridGroupUser";
    public static final String SETTING_GRID_GROUP_PROTOCOL_HTTP_PAGE = "gridGroupRealtimePage";
    public static final String SETTING_GRID_GROUP_PROTOCOL_HTTP_URI = "gridGroupRealtimeUri";

    /*
    감사이력/메뉴
     */

    public static final String ACTION_LOGIN = "LOGIN";
    public static final String ACTION_LOGOUT = "LOGOUT";
    public static final String ACTION_CREATE = "CREATE";
    public static final String ACTION_UPDATE = "UPDATE";
    public static final String ACTION_DELETE = "DELETE";
    public static final String ACTION_ACTIVE = "ACTIVE";
    public static final String ACTION_INACTIVE = "INACTIVE";
    public static final String ACTION_DOWNLOAD = "DOWNLOAD";
    public static final String ACTION_APPLY = "APPLY";

    public static final String  MENU_SETTING = "설정";

    public static final String MENU_SETTING_GRID = "그리드 설정";
    public static final String MENU_SETTING_COMPANY = "고객사 설정";
    public static final String MENU_SETTING_USER = "사용자 설정";
    public static final String MENU_SETTING_SENSOR_DEVICE = "Sensor Device 설정";
    public static final String MENU_SETTING_SSL = "SSL 설정";
    public static final String MENU_SETTING_TRANSACTION = "트랜잭션 설정";
    public static final String MENU_SETTING_LEVEL = "레벨 설정";
    public static final String MENU_SETTING_APPLICATION = "어플리케이션 설정";
    public static final String MENU_SETTING_AUDIT_HISTORY = "감사이력";

    public static final String MENU_SETTING_PROTOCOL = "Protocol";
    public static final String MENU_SETTING_USERAGENT = "User Agent";
    public static final String MENU_SETTING_MIME = "MIME";
    public static final String MENU_SETTING_HTTP = "HTTP";
    public static final String MENU_SETTING_ISP = "ISP";
    public static final String MENU_SETTING_IDC = "IDC";
    public static final String MENU_SETTING_DOMESTIC = "국내-지역 별";
    public static final String MENU_SETTING_GEO = "대륙-국가 별";

    public static final String MENU_SETTING_SSL_KEY = "SSL KEY";
    public static final String MENU_SETTING_PCAP_BPF = "PCAP BPF";


}
