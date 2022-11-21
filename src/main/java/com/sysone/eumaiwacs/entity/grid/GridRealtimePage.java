package com.sysone.eumaiwacs.entity.grid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="tbl_info_grid_realtime_page")
public class GridRealtimePage {

    @Id
    @Column(name="user_id", unique=true, nullable=false)
    private Integer userId;

    @Column(name="level")
    private Boolean level;

    @Column(name="ts_frame_arrival")
    private Boolean tsFrameArrival;

    @Column(name="src_geo")
    private Boolean srcGeo;

    @Column(name="src_domestic")
    private Boolean srcDomestic;

    @Column(name="isp_name_req")
    private Boolean ispNameReq;

    @Column(name="idc_name_req")
    private Boolean idcNameReq;

    @Column(name="src_ip")
    private Boolean srcIp;

    @Column(name="src_port")
    private Boolean srcPort;

    @Column(name="dst_geo")
    private Boolean dstGeo;

    @Column(name="dst_domestic")
    private Boolean dstDomestic;

    @Column(name="isp_name_res")
    private Boolean ispNameRes;

    @Column(name="idc_name_res")
    private Boolean idcNameRes;

    @Column(name="dst_ip")
    private Boolean dstIp;

    @Column(name="dst_port")
    private Boolean dstPort;

    @Column(name="http_res_code")
    private Boolean httpResCode;

    @Column(name="http_host")
    private Boolean httpHost;

    @Column(name="page")
    private Boolean page;

    @Column(name="http_user_agent")
    private Boolean httpUserAgent;

    @Column(name="ts_page")
    private Boolean tsPage;

    @Column(name="mbps")
    private Boolean mbps;

    @Column(name="uri_cnt")
    private Boolean uriCnt;

    @Column(name="session_cnt")
    private Boolean sessionCnt;

    @Column(name="conn_err_session_cnt")
    private Boolean connErrSessionCnt;

    @Column(name="stopped_transaction_cnt")
    private Boolean stoppedTransactionCnt;

    @Column(name="conn_err_pkt_cnt")
    private Boolean connErrPktCnt;

    @Column(name="tcp_error")
    private Boolean tcpError;

    @Column(name="http_error")
    private Boolean httpError;

    @Column(name="src_mac")
    private Boolean srcMac;

    @Column(name="dst_mac")
    private Boolean dstMac;

    @Column(name="http_method")
    private Boolean httpMethod;

    @Column(name="http_version")
    private Boolean httpVersion;

    @Column(name="http_version_req")
    private Boolean httpVersionReq;

    @Column(name="http_version_res")
    private Boolean httpVersionRes;

    @Column(name="http_res_phrase")
    private Boolean httpResPhrase;

    @Column(name="http_content_type")
    private Boolean httpContentType;

    @Column(name="http_cookie")
    private Boolean httpCookie;

    @Column(name="http_location")
    private Boolean httpLocation;

    @Column(name="http_uri")
    private Boolean httpUri;

    @Column(name="http_uri_split")
    private Boolean httpUriSplit;

    @Column(name="http_referer")
    private Boolean httpReferer;

    @Column(name="http_content_length")
    private Boolean httpContentLength;

    @Column(name="res_code_2xx_cnt")
    private Boolean resCode2xxCnt;

    @Column(name="res_code_3xx_cnt")
    private Boolean resCode3xxCnt;

    @Column(name="res_code_401_cnt")
    private Boolean resCode401Cnt;

    @Column(name="res_code_404_cnt")
    private Boolean resCode404Cnt;

    @Column(name="res_code_4xx_cnt")
    private Boolean resCode4xxCnt;

    @Column(name="res_code_5xx_cnt")
    private Boolean resCode5xxCnt;

    @Column(name="res_code_oth_cnt")
    private Boolean resCodeOthCnt;

    @Column(name="user_agent_software_name")
    private Boolean userAgentSoftwareName;

    @Column(name="user_agent_operating_system_name")
    private Boolean userAgentOperatingSystemName;

    @Column(name="user_agent_operating_platform")
    private Boolean userAgentOperatingPlatform;

    @Column(name="user_agent_hardware_type")
    private Boolean userAgentHardwareType;

    @Column(name="ts_frame_landoff")
    private Boolean tsFrameLandoff;

    @Column(name="ts_page_begin")
    private Boolean tsPageBegin;

    @Column(name="ts_page_end")
    private Boolean tsPageEnd;

    @Column(name="ts_page_req_syn")
    private Boolean tsPageReqSyn;

    @Column(name="ts_page_gap")
    private Boolean tsPageGap;

    @Column(name="ts_page_res_init")
    private Boolean tsPageResInit;

    @Column(name="ts_page_res_init_gap")
    private Boolean tsPageResInitGap;

    @Column(name="ts_page_res_app")
    private Boolean tsPageResApp;

    @Column(name="ts_page_res_app_gap")
    private Boolean tsPageResAppGap;

    @Column(name="ts_page_res")
    private Boolean tsPageRes;

    @Column(name="ts_page_res_gap")
    private Boolean tsPageResGap;

    @Column(name="ts_page_transfer_req")
    private Boolean tsPageTransferReq;

    @Column(name="ts_page_transfer_req_gap")
    private Boolean tsPageTransferReqGap;

    @Column(name="ts_page_transfer_res")
    private Boolean tsPageTransferRes;

    @Column(name="ts_page_transfer_res_gap")
    private Boolean tsPageTransferResGap;

    @Column(name="ts_page_rtt_conn_sum_req")
    private Boolean tsPageRttConnSumReq;

    @Column(name="ts_page_rtt_conn_sum_res")
    private Boolean tsPageRttConnSumRes;

    @Column(name="ts_page_rtt_ack_sum_req")
    private Boolean tsPageRttAckSumReq;

    @Column(name="ts_page_rtt_ack_sum_res")
    private Boolean tsPageRttAckSumRes;

    @Column(name="ts_page_req_making_sum")
    private Boolean tsPageReqMakingSum;

    @Column(name="page_rtt_conn_cnt_req")
    private Boolean pageRttConnCntReq;

    @Column(name="page_rtt_conn_cnt_res")
    private Boolean pageRttConnCntRes;

    @Column(name="page_rtt_ack_cnt_req")
    private Boolean pageRttAckCntReq;

    @Column(name="page_rtt_ack_cnt_res")
    private Boolean pageRttAckCntRes;

    @Column(name="page_req_making_cnt")
    private Boolean pageReqMakingCnt;

    @Column(name="page_http_len_req")
    private Boolean pageHttpLenReq;

    @Column(name="page_http_len_res")
    private Boolean pageHttpLenRes;

    @Column(name="page_http_cnt_req")
    private Boolean pageHttpCntReq;

    @Column(name="page_http_cnt_res")
    private Boolean pageHttpCntRes;

    @Column(name="page_pkt_len_req")
    private Boolean pagePktLenReq;

    @Column(name="page_pkt_len_res")
    private Boolean pagePktLenRes;

    @Column(name="page_pkt_cnt_req")
    private Boolean pagePktCntReq;

    @Column(name="page_pkt_cnt_res")
    private Boolean pagePktCntRes;

    @Column(name="page_tcp_len_req")
    private Boolean pageTcpLenReq;

    @Column(name="page_tcp_len_res")
    private Boolean pageTcpLenRes;

    @Column(name="page_tcp_cnt_req")
    private Boolean pageTcpCntReq;

    @Column(name="page_tcp_cnt_res")
    private Boolean pageTcpCntRes;

    @Column(name="req_conn_err_session_len")
    private Boolean reqConnErrSessionLen;

    @Column(name="res_conn_err_session_len")
    private Boolean resConnErrSessionLen;

    @Column(name="ts_page_rtt_conn_req_min")
    private Boolean tsPageRttConnReqMin;

    @Column(name="ts_page_rtt_conn_res_min")
    private Boolean tsPageRttConnResMin;

    @Column(name="ts_page_rtt_conn_req_max")
    private Boolean tsPageRttConnReqMax;

    @Column(name="ts_page_rtt_conn_res_max")
    private Boolean tsPageRttConnResMax;

    @Column(name="ts_page_rtt_ack_req_min")
    private Boolean tsPageRttAckReqMin;

    @Column(name="ts_page_rtt_ack_res_min")
    private Boolean tsPageRttAckResMin;

    @Column(name="ts_page_rtt_ack_req_max")
    private Boolean tsPageRttAckReqMax;

    @Column(name="ts_page_rtt_ack_res_max")
    private Boolean tsPageRttAckResMax;

    @Column(name="ts_page_req_making_min")
    private Boolean tsPageReqMakingMin;

    @Column(name="ts_page_req_making_max")
    private Boolean tsPageReqMakingMax;

    @Column(name="ts_page_rto_sum_req")
    private Boolean tsPageRtoSumReq;

    @Column(name="ts_page_rto_sum_res")
    private Boolean tsPageRtoSumRes;

    @Column(name="ts_page_rto_cnt_req")
    private Boolean tsPageRtoCntReq;

    @Column(name="ts_page_rto_cnt_res")
    private Boolean tsPageRtoCntRes;

    @Column(name="retransmission_cnt_req")
    private Boolean retransmissionCntReq;

    @Column(name="retransmission_cnt_res")
    private Boolean retransmissionCntRes;

    @Column(name="retransmission_len_req")
    private Boolean retransmissionLenReq;

    @Column(name="retransmission_len_res")
    private Boolean retransmissionLenRes;

    @Column(name="fast_retransmission_cnt_req")
    private Boolean fastRetransmissionCntReq;

    @Column(name="fast_retransmission_cnt_res")
    private Boolean fastRetransmissionCntRes;

    @Column(name="fast_retransmission_len_req")
    private Boolean fastRetransmissionLenReq;

    @Column(name="fast_retransmission_len_res")
    private Boolean fastRetransmissionLenRes;

    @Column(name="out_of_order_cnt_req")
    private Boolean outOfOrderCntReq;

    @Column(name="out_of_order_cnt_res")
    private Boolean outOfOrderCntRes;

    @Column(name="out_of_order_len_req")
    private Boolean outOfOrderLenReq;

    @Column(name="out_of_order_len_res")
    private Boolean outOfOrderLenRes;

    @Column(name="lost_seg_cnt_req")
    private Boolean lostSegCntReq;

    @Column(name="lost_seg_cnt_res")
    private Boolean lostSegCntRes;

    @Column(name="lost_seg_len_req")
    private Boolean lostSegLenReq;

    @Column(name="lost_seg_len_res")
    private Boolean lostSegLenRes;

    @Column(name="ack_lost_cnt_req")
    private Boolean ackLostCntReq;

    @Column(name="ack_lost_cnt_res")
    private Boolean ackLostCntRes;

    @Column(name="ack_lost_len_req")
    private Boolean ackLostLenReq;

    @Column(name="ack_lost_len_res")
    private Boolean ackLostLenRes;

    @Column(name="win_update_cnt_req")
    private Boolean winUpdateCntReq;

    @Column(name="win_update_cnt_res")
    private Boolean winUpdateCntRes;

    @Column(name="win_update_len_req")
    private Boolean winUpdateLenReq;

    @Column(name="win_update_len_res")
    private Boolean winUpdateLenRes;

    @Column(name="dup_ack_cnt_req")
    private Boolean dupAckCntReq;

    @Column(name="dup_ack_cnt_res")
    private Boolean dupAckCntRes;

    @Column(name="dup_ack_len_req")
    private Boolean dupAckLenReq;

    @Column(name="dup_ack_len_res")
    private Boolean dupAckLenRes;

    @Column(name="zero_win_cnt_req")
    private Boolean zeroWinCntReq;

    @Column(name="zero_win_cnt_res")
    private Boolean zeroWinCntRes;

    @Column(name="zero_win_len_req")
    private Boolean zeroWinLenReq;

    @Column(name="zero_win_len_res")
    private Boolean zeroWinLenRes;

    @Column(name="spurious_retransmission_cnt_req")
    private Boolean spuriousRetransmissionCntReq;

    @Column(name="spurious_retransmission_cnt_res")
    private Boolean spuriousRetransmissionCntRes;

    @Column(name="spurious_retransmission_len_req")
    private Boolean spuriousRetransmissionLenReq;

    @Column(name="spurious_retransmission_len_res")
    private Boolean spuriousRetransmissionLenRes;

    @Column(name="overlap_cnt_req")
    private Boolean overlapCntReq;

    @Column(name="overlap_cnt_res")
    private Boolean overlapCntRes;

    @Column(name="overlap_len_req")
    private Boolean overlapLenReq;

    @Column(name="overlap_len_res")
    private Boolean overlapLenRes;

    @Column(name="overlap_attack_cnt_req")
    private Boolean overlapAttackCntReq;

    @Column(name="overlap_attack_cnt_res")
    private Boolean overlapAttackCntRes;

    @Column(name="overlap_attack_len_req")
    private Boolean overlapAttackLenReq;

    @Column(name="overlap_attack_len_res")
    private Boolean overlapAttackLenRes;

    @Column(name="zero_win_probe_cnt_req")
    private Boolean zeroWinProbeCntReq;

    @Column(name="zero_win_probe_cnt_res")
    private Boolean zeroWinProbeCntRes;

    @Column(name="zero_win_probe_len_req")
    private Boolean zeroWinProbeLenReq;

    @Column(name="zero_win_probe_len_res")
    private Boolean zeroWinProbeLenRes;

    @Column(name="zero_win_probe_ack_cnt_req")
    private Boolean zeroWinProbeAckCntReq;

    @Column(name="zero_win_probe_ack_cnt_res")
    private Boolean zeroWinProbeAckCntRes;

    @Column(name="zero_win_probe_ack_len_req")
    private Boolean zeroWinProbeAckLenReq;

    @Column(name="zero_win_probe_ack_len_res")
    private Boolean zeroWinProbeAckLenRes;

    @Column(name="keep_alive_cnt_req")
    private Boolean keepAliveCntReq;

    @Column(name="keep_alive_cnt_res")
    private Boolean keepAliveCntRes;

    @Column(name="keep_alive_len_req")
    private Boolean keepAliveLenReq;

    @Column(name="keep_alive_len_res")
    private Boolean keepAliveLenRes;

    @Column(name="keep_alive_ack_cnt_req")
    private Boolean keepAliveAckCntReq;

    @Column(name="keep_alive_ack_cnt_res")
    private Boolean keepAliveAckCntRes;

    @Column(name="keep_alive_ack_len_req")
    private Boolean keepAliveAckLenReq;

    @Column(name="keep_alive_ack_len_res")
    private Boolean keepAliveAckLenRes;

    @Column(name="as_name_req")
    private Boolean asNameReq;

    @Column(name="as_name_res")
    private Boolean asNameRes;

    @Column(name="application_name")
    private Boolean applicationName;

    @Column(name="ndpi_protocol_app")
    private Boolean ndpiProtocolApp;

    @Column(name="ndpi_protocol_master")
    private Boolean ndpiProtocolMaster;

}
