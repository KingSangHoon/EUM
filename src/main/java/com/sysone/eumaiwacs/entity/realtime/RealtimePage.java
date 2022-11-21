package com.sysone.eumaiwacs.entity.realtime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="tbl_eum_rad_http_page")
public class RealtimePage {

    @EmbeddedId
    private RealtimePageKey realtimePageKey;

    @Column(name="ts_page_begin")
    private BigDecimal tsPageBegin;
    @Column(name="ts_page_end")
    private BigDecimal tsPageEnd;
    @Column(name="ts_page_req_syn")
    private BigDecimal tsPageReqSyn;
    @Column(name="ts_page")
    private BigDecimal tsPage;
    @Column(name="ts_page_gap")
    private BigDecimal tsPageGap;
    @Column(name="ts_page_res_init")
    private BigDecimal tsPageResInit;
    @Column(name="ts_page_res_init_gap")
    private BigDecimal tsPageResInitGap;
    @Column(name="ts_page_res_app")
    private BigDecimal tsPageResApp;
    @Column(name="ts_page_res_app_gap")
    private BigDecimal tsPageResAppGap;
    @Column(name="ts_page_res")
    private BigDecimal tsPageRes;
    @Column(name="ts_page_res_gap")
    private BigDecimal tsPageResGap;
    @Column(name="ts_page_transfer_req")
    private BigDecimal tsPageTransferReq;
    @Column(name="ts_page_transfer_req_gap")
    private BigDecimal tsPageTransferReqGap;
    @Column(name="ts_page_transfer_res")
    private BigDecimal tsPageTransferRes;
    @Column(name="ts_page_transfer_res_gap")
    private BigDecimal tsPageTransferResGap;
    @Column(name="ts_page_rtt_conn_sum_req")
    private BigDecimal tsPageRttConnSumReq;
    @Column(name="ts_page_rtt_conn_sum_res")
    private BigDecimal tsPageRttConnSumRes;
    @Column(name="ts_page_rtt_ack_sum_req")
    private BigDecimal tsPageRttAckSumReq;
    @Column(name="ts_page_rtt_ack_sum_res")
    private BigDecimal tsPageRttAckSumRes;
    @Column(name="ts_page_req_making_sum")
    private BigDecimal tsPageReqMakingSum;
    @Column(name="page_rtt_conn_cnt_req")
    private BigDecimal pageRttConnCntReq;
    @Column(name="page_rtt_conn_cnt_res")
    private BigDecimal pageRttConnCntRes;
    @Column(name="page_rtt_ack_cnt_req")
    private BigDecimal pageRttAckCntReq;
    @Column(name="page_rtt_ack_cnt_res")
    private BigDecimal pageRttAckCntRes;
    @Column(name="page_req_making_cnt")
    private BigInteger pageReqMakingCnt;
    @Column(name="page_http_len_req")
    private BigInteger pageHttpLenReq;
    @Column(name="page_http_len_res")
    private BigInteger pageHttpLenRes;
    @Column(name="page_http_cnt_req")
    private BigInteger pageHttpCntReq;
    @Column(name="page_http_cnt_res")
    private BigInteger pageHttpCntRes;
    @Column(name="page_pkt_len_req")
    private BigInteger pagePktLenReq;
    @Column(name="page_pkt_len_res")
    private BigInteger pagePktLenRes;
    @Column(name="page_pkt_cnt_req")
    private BigInteger pagePktCntReq;
    @Column(name="page_pkt_cnt_res")
    private BigInteger pagePktCntRes;
    @Column(name="page_session_cnt")
    private BigInteger pageSessionCnt;
    @Column(name="conn_err_pkt_cnt")
    private BigInteger connErrPktCnt;
    @Column(name="conn_err_session_cnt")
    private BigInteger connErrSessionCnt;
    @Column(name="req_conn_err_session_len")
    private BigInteger reqConnErrSessionLen;
    @Column(name="res_conn_err_session_len")
    private BigInteger resConnErrSessionLen;
    @Column(name="retransmission_cnt_req")
    private BigInteger retransmissionCntReq;
    @Column(name="retransmission_cnt_res")
    private BigInteger retransmissionCntRes;
    @Column(name="retransmission_len_req")
    private BigInteger retransmissionLenReq;
    @Column(name="retransmission_len_res")
    private BigInteger retransmissionLenRes;
    @Column(name="fast_retransmission_cnt_req")
    private BigInteger fastRetransmissionCntReq;
    @Column(name="fast_retransmission_cnt_res")
    private BigInteger fastRetransmissionCntRes;
    @Column(name="fast_retransmission_len_req")
    private BigInteger fastRetransmissionLenReq;
    @Column(name="fast_retransmission_len_res")
    private BigInteger fastRetransmissionLenRes;
    @Column(name="out_of_order_cnt_req")
    private BigInteger outOfOrderCntReq;
    @Column(name="out_of_order_cnt_res")
    private BigInteger outOfOrderCntRes;
    @Column(name="out_of_order_len_req")
    private BigInteger outOfOrderLenReq;
    @Column(name="out_of_order_len_res")
    private BigInteger outOfOrderLenRes;
    @Column(name="lost_seg_cnt_req")
    private BigInteger lostSegCntReq;
    @Column(name="lost_seg_cnt_res")
    private BigInteger lostSegCntRes;
    @Column(name="lost_seg_len_req")
    private BigInteger lostSegLenReq;
    @Column(name="lost_seg_len_res")
    private BigInteger lostSegLenRes;
    @Column(name="ack_lost_cnt_req")
    private BigInteger ackLostCntReq;
    @Column(name="ack_lost_cnt_res")
    private BigInteger ackLostCntRes;
    @Column(name="ack_lost_len_req")
    private BigInteger ackLostLenReq;
    @Column(name="ack_lost_len_res")
    private BigInteger ackLostLenRes;
    @Column(name="win_update_cnt_req")
    private BigInteger winUpdateCntReq;
    @Column(name="win_update_cnt_res")
    private BigInteger winUpdateCntRes;
    @Column(name="win_update_len_req")
    private BigInteger winUpdateLenReq;
    @Column(name="win_update_len_res")
    private BigInteger winUpdateLenRes;
    @Column(name="dup_ack_cnt_req")
    private BigInteger dupAckCntReq;
    @Column(name="dup_ack_cnt_res")
    private BigInteger dupAckCntRes;
    @Column(name="dup_ack_len_req")
    private BigInteger dupAckLenReq;
    @Column(name="dup_ack_len_res")
    private BigInteger dupAckLenRes;
    @Column(name="zero_win_cnt_req")
    private BigInteger zeroWinCntReq;
    @Column(name="zero_win_cnt_res")
    private BigInteger zeroWinCntRes;
    @Column(name="zero_win_len_req")
    private BigInteger zeroWinLenReq;
    @Column(name="zero_win_len_res")
    private BigInteger zeroWinLenRes;
    @Column(name="spurious_retransmission_cnt_req")
    private BigInteger spuriousRetransmissionCntReq;
    @Column(name="spurious_retransmission_cnt_res")
    private BigInteger spuriousRetransmissionCntRes;
    @Column(name="spurious_retransmission_len_req")
    private BigInteger spuriousRetransmissionLenReq;
    @Column(name="spurious_retransmission_len_res")
    private BigInteger spuriousRetransmissionLenRes;
    @Column(name="overlap_cnt_req")
    private BigInteger overlapCntReq;
    @Column(name="overlap_cnt_res")
    private BigInteger overlapCntRes;
    @Column(name="overlap_len_req")
    private BigInteger overlapLenReq;
    @Column(name="overlap_len_res")
    private BigInteger overlapLenRes;
    @Column(name="overlap_attack_cnt_req")
    private BigInteger overlapAttackCntReq;
    @Column(name="overlap_attack_cnt_res")
    private BigInteger overlapAttackCntRes;
    @Column(name="overlap_attack_len_req")
    private BigInteger overlapAttackLenReq;
    @Column(name="overlap_attack_len_res")
    private BigInteger overlapAttackLenRes;
    @Column(name="zero_win_probe_cnt_req")
    private BigInteger zeroWinProbeCntReq;
    @Column(name="zero_win_probe_cnt_res")
    private BigInteger zeroWinProbeCntRes;
    @Column(name="zero_win_probe_len_req")
    private BigInteger zeroWinProbeLenReq;
    @Column(name="zero_win_probe_len_res")
    private BigInteger zeroWinProbeLenRes;
    @Column(name="zero_win_probe_ack_cnt_req")
    private BigInteger zeroWinProbeAckCntReq;
    @Column(name="zero_win_probe_ack_cnt_res")
    private BigInteger zeroWinProbeAckCntRes;
    @Column(name="zero_win_probe_ack_len_req")
    private BigInteger zeroWinProbeAckLenReq;
    @Column(name="zero_win_probe_ack_len_res")
    private BigInteger zeroWinProbeAckLenRes;
    @Column(name="keep_alive_cnt_req")
    private BigInteger keepAliveCntReq;
    @Column(name="keep_alive_cnt_res")
    private BigInteger keepAliveCntRes;
    @Column(name="keep_alive_len_req")
    private BigInteger keepAliveLenReq;
    @Column(name="keep_alive_len_res")
    private BigInteger keepAliveLenRes;
    @Column(name="keep_alive_ack_cnt_req")
    private BigInteger keepAliveAckCntReq;
    @Column(name="keep_alive_ack_cnt_res")
    private BigInteger keepAliveAckCntRes;
    @Column(name="keep_alive_ack_len_req")
    private BigInteger keepAliveAckLenReq;
    @Column(name="keep_alive_ack_len_res")
    private BigInteger keepAliveAckLenRes;
    @Column(name="res_code_2xx_cnt")
    private BigInteger resCode2xxCnt;
    @Column(name="res_code_3xx_cnt")
    private BigInteger resCode3xxCnt;
    @Column(name="res_code_401_cnt")
    private BigInteger resCode401Cnt;
    @Column(name="res_code_404_cnt")
    private BigInteger resCode404Cnt;
    @Column(name="res_code_4xx_cnt")
    private BigInteger resCode4xxCnt;
    @Column(name="res_code_5xx_cnt")
    private BigInteger resCode5xxCnt;
    @Column(name="res_code_oth_cnt")
    private BigInteger resCodeOthCnt;
    @Column(name="page_tcp_len_req")
    private BigInteger pageTcpLenReq;
    @Column(name="page_tcp_len_res")
    private BigInteger pageTcpLenRes;
    @Column(name="page_tcp_cnt_req")
    private BigInteger pageTcpCntReq;
    @Column(name="page_tcp_cnt_res")
    private BigInteger pageTcpCntRes;
    @Column(name="geoip_id_req")
    private Integer geoIpIdReq;
    @Column(name="geoip_id_res")
    private Integer geoIpIdRes;
    @Column(name="country_id_req")
    private Integer countryIdReq;
    @Column(name="country_id_res")
    private Integer countryIdRes;
    @Column(name="continent_id_req")
    private Integer continentIdReq;
    @Column(name="continent_id_res")
    private Integer continentIdRes;
    @Column(name="city_id_req")
    private Integer cityIdReq;
    @Column(name="city_id_res")
    private Integer cityIdRes;
    @Column(name="city_country_id_req")
    private Integer cityCountryIdReq;
    @Column(name="city_country_id_res")
    private Integer cityCountryIdRes;
    @Column(name="domestic_primary_id_req")
    private Integer domesticPrimaryIdReq;
    @Column(name="domestic_primary_id_res")
    private Integer domesticPrimaryIdRes;
    @Column(name="domestic_sub1_id_req")
    private Integer domesticSub1IdReq;
    @Column(name="domestic_sub1_id_res")
    private Integer domesticSub1IdRes;
    @Column(name="domestic_sub2_id_req")
    private Integer domesticSub2IdReq;
    @Column(name="domestic_sub2_id_res")
    private Integer domesticSub2IdRes;
    @Column(name="isp_id_req")
    private Integer ispIdReq;
    @Column(name="isp_id_res")
    private Integer ispIdRes;
    @Column(name="idc_id_req")
    private Integer idcIdReq;
    @Column(name="idc_id_res")
    private Integer idcIdRes;
    @Column(name="as_id_req")
    private Integer asIdReq;
    @Column(name="as_id_res")
    private Integer asIdRes;
    @Column(name="country_name_req")
    private String countryNameReq;
    @Column(name="country_name_res")
    private String countryNameRes;
    @Column(name="continent_name_req")
    private String continentNameReq;
    @Column(name="continent_name_res")
    private String continentNameRes;
    @Column(name="city_name_req")
    private String cityNameReq;
    @Column(name="city_name_res")
    private String cityNameRes;
    @Column(name="city_country_name_req")
    private String cityCountryNameReq;
    @Column(name="city_country_name_res")
    private String cityCountryNameRes;
    @Column(name="domestic_latitude_req")
    private String domesticLatitudeReq;
    @Column(name="domestic_latitude_res")
    private String domesticLatitudeRes;
    @Column(name="domestic_longitude_req")
    private String domesticLongitudeReq;
    @Column(name="domestic_longitude_res")
    private String domesticLongitudeRes;
    @Column(name="domestic_primary_name_req")
    private String domesticPrimaryNameReq;
    @Column(name="domestic_primary_name_res")
    private String domesticPrimaryNameRes;
    @Column(name="domestic_sub1_name_req")
    private String domesticSub1NameReq;
    @Column(name="domestic_sub1_name_res")
    private String domesticSub1NameRes;
    @Column(name="domestic_sub2_name_req")
    private String domesticSub2NameReq;
    @Column(name="domestic_sub2_name_res")
    private String domesticSub2NameRes;
    @Column(name="isp_name_req")
    private String ispNameReq;
    @Column(name="isp_name_res")
    private String ispNameRes;
    @Column(name="isp_name_eng_req")
    private String ispNameEngReq;
    @Column(name="isp_name_eng_res")
    private String ispNameEngRes;
    @Column(name="idc_name_req")
    private String idcNameReq;
    @Column(name="idc_name_res")
    private String idcNameRes;
    @Column(name="idc_name_eng_req")
    private String idcNameEngReq;
    @Column(name="idc_name_eng_res")
    private String idcNameEngRes;
    @Column(name="as_number_req")
    private BigInteger asNumberReq;
    @Column(name="as_number_res")
    private BigInteger asNumberRes;
    @Column(name="as_name_req")
    private String asNameReq;
    @Column(name="as_name_res")
    private String asNameRes;
    @Column(name="as_name_eng_req")
    private String asNameEngReq;
    @Column(name="as_name_eng_res")
    private String asNameEngRes;

    @Column(name="application_id")
    private Integer applicationId;
    @Column(name="application_rip_id")
    private Integer applicationRipId;
    @Column(name="application_vip_id")
    private Integer applicationVipId;
    @Column(name="application_name")
    private String applicationName;
    @Column(name="application_rip_begin")
    private String applicationRipBegin;
    @Column(name="application_rip_end")
    private String applicationRipEnd;
    @Column(name="application_rport_begin")
    private Integer applicationRportBegin;
    @Column(name="application_rport_end")
    private Integer applicationRportEnd;
    @Column(name="application_vip")
    private String applicationVip;
    @Column(name="application_vport")
    private Integer applicationVport;
    @Column(name="application_vhost_id")
    private Integer applicationVhostId;
    @Column(name="application_vhost")
    private String applicationVhost;

    @Column(name="level_overall")
    private Integer levelOverall;
    @Column(name="level_performance")
    private Integer levelPerformance;
    @Column(name="level_throughput")
    private Integer levelThroughput;
    @Column(name="level_availability")
    private Integer levelAvailability;

    @Column(name="transaction_url_id")
    private Integer transactionUrlId;
    @Column(name="stopped_transaction_cnt")
    private BigInteger stoppedTransactionCnt;
    @Column(name="ndpi_protocol_app")
    private String ndpiProtocolApp;
    @Column(name="ndpi_protocol_master")
    private String ndpiProtocolMaster;
    @Column(name="ndpi_is_guessed")
    private Integer ndpiIsGuessed;
    @Column(name="ndpi_protocol_app_id")
    private Integer ndpiProtocolAppId;
    @Column(name="ndpi_protocol_master_id")
    private Integer ndpiProtocolMasterId;
    @Column(name="ndpi_protocol_is_white_list")
    private Boolean ndpiProtocolIsWhiteList;

    @Column(name="ts_page_rtt_conn_req_min")
    private BigDecimal tsPageRttConnReqMin;
    @Column(name="ts_page_rtt_conn_res_min")
    private BigDecimal tsPageRttConnResMin;
    @Column(name="ts_page_rtt_conn_req_max")
    private BigDecimal tsPageRttConnReqMax;
    @Column(name="ts_page_rtt_conn_res_max")
    private BigDecimal tsPageRttConnResMax;
    @Column(name="ts_page_rtt_ack_req_min")
    private BigDecimal tsPageRttAckReqMin;
    @Column(name="ts_page_rtt_ack_res_min")
    private BigDecimal tsPageRttAckResMin;
    @Column(name="ts_page_rtt_ack_req_max")
    private BigDecimal tsPageRttAckReqMax;
    @Column(name="ts_page_rtt_ack_res_max")
    private BigDecimal tsPageRttAckResMax;
    @Column(name="ts_page_req_making_min")
    private BigDecimal tsPageReqMakingMin;
    @Column(name="ts_page_req_making_max")
    private BigDecimal tsPageReqMakingMax;
    @Column(name="ts_page_rto_sum_req")
    private BigDecimal tsPageRtoSumReq;
    @Column(name="ts_page_rto_sum_res")
    private BigDecimal tsPageRtoSumRes;
    @Column(name="ts_page_rto_cnt_req")
    private BigInteger tsPageRtoCntReq;
    @Column(name="ts_page_rto_cnt_res")
    private BigInteger tsPageRtoCntRes;

    @Column(name="http_res_code")
    private Integer httpResCode;
    @Column(name="http_content_length")
    private BigInteger httpContentLength;
    @Column(name="http_method")
    private String httpMethod;
    @Column(name="http_version")
    private String httpVersion;
    @Column(name="http_version_req")
    private String httpVersionReq;
    @Column(name="http_version_res")
    private String httpVersionRes;
    @Column(name="http_res_phrase")
    private String httpResPhrase;
    @Column(name="http_content_type")
    private String httpContentType;
    @Column(name="http_cookie")
    private String httpCookie;
    @Column(name="http_location")
    private String httpLocation;
    @Column(name="http_referer")
    private String httpReferer;

    @Column(name="http_user_agent")
    private String httpUserAgent;
    @Column(name="http_host")
    private String httpHost;
    @Column(name="http_uri")
    private String httpUri;
    @Column(name="http_uri_split")
    private String httpUriSplit;

    @Column(name="user_agent_id")
    private Integer userAgentId;
    @Column(name="user_agent_software_name")
    private String userAgentSoftwareName;
    @Column(name="user_agent_software_version")
    private String userAgentSoftwareVersion;
    @Column(name="user_agent_software_version_full")
    private String userAgentSoftwareVersionFull;
    @Column(name="user_agent_operating_system_name")
    private String userAgentOperatingSystemName;
    @Column(name="user_agent_operating_system_version")
    private String userAgentOperatingSystemVersion;
    @Column(name="user_agent_operating_system_version_full")
    private String userAgentOperatingSystemVersionFull;
    @Column(name="user_agent_operating_system_flavour")
    private String userAgentOperatingSystemFlavour;
    @Column(name="user_agent_operating_platform")
    private String userAgentOperatingPlatform;
    @Column(name="user_agent_operating_platform_code")
    private String userAgentOperatingPlatformCode;
    @Column(name="user_agent_operating_platform_code_name")
    private String userAgentOperatingPlatformCodeName;
    @Column(name="user_agent_operating_platform_vendor_name")
    private String userAgentOperatingPlatformVendorName;
    @Column(name="user_agent_software_type")
    private String userAgentSoftwareType;
    @Column(name="user_agent_software_sub_type")
    private String userAgentSoftwareSubType;
    @Column(name="user_agent_hardware_type")
    private String userAgentHardwareType;
    @Column(name="user_agent_hardware_sub_type")
    private String userAgentHardwareSubType;
    @Column(name="user_agent_layout_engine_name")
    private String userAgentLayoutEngineName;
    @Column(name="src_mac")
    private String srcMac;
    @Column(name="dst_mac")
    private String dstMac;

    @Column(name="argument_1_key")
    private String argument1Key;
    @Column(name="argument_1_value")
    private String argument1Value;
    @Column(name="argument_2_key")
    private String argument2Key;
    @Column(name="argument_2_value")
    private String argument2Value;
    @Column(name="argument_3_key")
    private String argument3Key;
    @Column(name="argument_3_value")
    private String argument3Value;
    @Column(name="argument_4_key")
    private String argument4Key;
    @Column(name="argument_4_value")
    private String argument4Value;

    @Column(name="request_header_1_key")
    private String requestHeader1Key;
    @Column(name="request_header_1_value")
    private String requestHeader1Value;
    @Column(name="request_header_2_key")
    private String requestHeader2Key;
    @Column(name="request_header_2_value")
    private String requestHeader2Value;
    @Column(name="request_header_3_key")
    private String requestHeader3Key;
    @Column(name="request_header_3_value")
    private String requestHeader3Value;
    @Column(name="request_header_4_key")
    private String requestHeader4Key;
    @Column(name="request_header_4_value")
    private String requestHeader4Value;

    @Column(name="request_body_1_key")
    private String requestBody1Key;
    @Column(name="request_body_1_value")
    private String requestBody1Value;
    @Column(name="request_body_2_key")
    private String requestBody2Key;
    @Column(name="request_body_2_value")
    private String requestBody2Value;
    @Column(name="request_body_3_key")
    private String requestBody3Key;
    @Column(name="request_body_3_value")
    private String requestBody3Value;
    @Column(name="request_body_4_key")
    private String requestBody4Key;
    @Column(name="request_body_4_value")
    private String requestBody4Value;
    @Column(name="request_body_5_key")
    private String requestBody5Key;
    @Column(name="request_body_5_value")
    private String requestBody5Value;
    @Column(name="request_body_6_key")
    private String requestBody6Key;
    @Column(name="request_body_6_value")
    private String requestBody6Value;

    @Column(name="cookie_1_key")
    private String cookie1Key;
    @Column(name="cookie_1_value")
    private String cookie1Value;
    @Column(name="cookie_2_key")
    private String cookie2Key;
    @Column(name="cookie_2_value")
    private String cookie2Value;
    @Column(name="cookie_3_key")
    private String cookie3Key;
    @Column(name="cookie_3_value")
    private String cookie3Value;
    @Column(name="cookie_4_key")
    private String cookie4Key;
    @Column(name="cookie_4_value")
    private String cookie4Value;

}
