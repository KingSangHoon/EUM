package com.sysone.eumaiwacs.entity.realtime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="tbl_eum_rad_http_uri")
public class RealtimeUri {

    @Id
    private RealtimeUriKey realtimeUriKey;

    @Column(name="req_seq_first")
    private BigInteger reqSeqFirst;
    @Column(name="req_ack_first")
    private BigInteger reqAckFirst;
    @Column(name="req_seq_last")
    private BigInteger reqSeqLast;
    @Column(name="req_ack_last")
    private BigInteger reqAckLast;
    @Column(name="res_seq_first")
    private BigInteger resSeqFirst;
    @Column(name="res_ack_first")
    private BigInteger resAckFirst;

    @Column(name="ts_req_pkt_first")
    private BigDecimal tsReqPktFirst;
    @Column(name="ts_req_pkt_push")
    private BigDecimal tsReqPktPush;
    @Column(name="ts_req_pkt_last")
    private BigDecimal tsReqPktLast;
    @Column(name="ts_res_pkt_first")
    private BigDecimal tsResPktFirst;
    @Column(name="ts_res_pkt_push")
    private BigDecimal tsResPktPush;
    @Column(name="ts_res_pkt_last")
    private BigDecimal tsResPktLast;
    @Column(name="ts_res_delay_transfer")
    private BigDecimal tsResDelayTransfer;
    @Column(name="ts_rsq_delay_response")
    private BigDecimal tsRsqDelayResponse;
    @Column(name="ts_res_process_first")
    private BigDecimal tsResProcessFirst;
    @Column(name="ts_res_process_push")
    private BigDecimal tsResProcessPush;
    @Column(name="ts_res_process_last")
    private BigDecimal tsResProcessLast;
    @Column(name="ts_req_delay_transfer")
    private BigDecimal tsReqDelayTransfer;
    @Column(name="ts_rtt_syn")
    private BigDecimal tsRttSyn;
    @Column(name="ts_rtt_syn_ack")
    private BigDecimal tsRttSynAck;
    @Column(name="ts_rtt_first_ack")
    private BigDecimal tsRttFirstAck;
    @Column(name="ts_rtt_req_ack")
    private BigDecimal tsRttReqAck;
    @Column(name="ts_rtt_ack_req_ack")
    private BigDecimal tsRttAckReqAck;
    @Column(name="ts_rtt_res_ack")
    private BigDecimal tsRttResAck;
    @Column(name="ts_rtt_ack_res_ack")
    private BigDecimal tsRttAckResAck;
    @Column(name="ts_rtt_conn_req")
    private BigDecimal tsRttConnReq;
    @Column(name="ts_rtt_conn_res")
    private BigDecimal tsRttConnRes;
    @Column(name="ts_rtt_first_ack_req")
    private BigDecimal tsRttFirstAckReq;
    @Column(name="ts_rtt_first_ack_res")
    private BigDecimal tsRttFirstAckRes;
    @Column(name="ts_conn_delay_req")
    private BigDecimal tsConnDelayReq;
    @Column(name="ts_conn_delay_res")
    private BigDecimal tsConnDelayRes;
    @Column(name="ts_first_ack_delay_req")
    private BigDecimal tsFirstAckDelayReq;
    @Column(name="ts_first_ack_delay_res")
    private BigDecimal tsFirstAckDelayRes;
    @Column(name="ts_req_making")
    private BigDecimal tsReqMaking;

    @Column(name="pkt_len_req")
    private BigInteger pktLenReq;
    @Column(name="pkt_len_res")
    private BigInteger pktLenRes;
    @Column(name="pkt_cnt_req")
    private BigInteger pktCntReq;
    @Column(name="pkt_cnt_res")
    private BigInteger pktCntRes;
    @Column(name="http_len_req")
    private BigInteger httpLenReq;
    @Column(name="http_len_res")
    private BigInteger httpLenRes;
    @Column(name="http_cnt_req")
    private BigInteger httpCntReq;
    @Column(name="http_cnt_res")
    private BigInteger httpCntRes;

    @Column(name="ack_rtt_sum_req")
    private BigDecimal ackRttSumReq;
    @Column(name="ack_rtt_sum_res")
    private BigDecimal ackRttSumRes;
    @Column(name="ack_rto_sum_req")
    private BigDecimal ackRtoSumReq;
    @Column(name="ack_rto_sum_res")
    private BigDecimal ackRtoSumRes;

    @Column(name="ack_rtt_cnt_req")
    private BigInteger ackRttCntReq;
    @Column(name="ack_rtt_cnt_res")
    private BigInteger ackRttCntRes;
    @Column(name="ack_rto_cnt_req")
    private BigInteger ackRtoCntReq;
    @Column(name="ack_rto_cnt_res")
    private BigInteger ackRtoCntRes;

    @Column(name="ack_delay_req")
    private BigDecimal ackDelayReq;
    @Column(name="ack_delay_res")
    private BigDecimal ackDelayRes;

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

    @Column(name="transaction_url_id")
    private Integer transactionUrlId;

    @Column(name="is_stopped_transaction")
    private Integer isStoppedTransaction;

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

    @Column(name="ack_rtt_min_req")
    private BigDecimal ackRttMinReq;
    @Column(name="ack_rtt_min_res")
    private BigDecimal ackRttMinRes;
    @Column(name="ack_rtt_max_req")
    private BigDecimal ackRttMaxReq;
    @Column(name="ack_rtt_max_res")
    private BigDecimal ackRttMaxRes;

    @Column(name="tcp_len_req")
    private BigInteger tcpLenReq;
    @Column(name="tcp_len_res")
    private BigInteger tcpLenRes;
    @Column(name="tcp_cnt_req")
    private BigInteger tcpCntReq;
    @Column(name="tcp_cnt_res")
    private BigInteger tcpCntRes;

    @Column(name="http_method")
    private String httpMethod;
    @Column(name="http_version")
    private String httpVersion;
    @Column(name="http_version_req")
    private String httpVersionReq;
    @Column(name="http_version_res")
    private String httpVersionRes;
    @Column(name="http_res_code")
    private Integer httpResCode;
    @Column(name="http_content_length")
    private BigInteger httpContentLength;
    @Column(name="http_res_phrase")
    private String httpResPhrase;
    @Column(name="http_content_type")
    private String httpContentType;
    @Column(name="http_user_agent")
    private String httpUserAgent;
    @Column(name="http_cookie")
    private String httpCookie;
    @Column(name="http_location")
    private String httpLocation;
    @Column(name="http_host")
    private String httpHost;
    @Column(name="http_uri")
    private String httpUri;
    @Column(name="http_uri_split")
    private String httpUriSplit;
    @Column(name="http_referer")
    private String httpReferer;

    @Column(name="flow_identifier")
    private String flowIdentifier;

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

    @Column(name="ts_frame_arrival_page")
    private BigDecimal tsFrameArrivalPage;
    @Column(name="ts_frame_landoff_page")
    private BigDecimal tsFrameLandoffPage;

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
