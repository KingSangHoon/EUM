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
@Table(name="tbl_info_grid_realtime_page_index")
public class GridRealtimePageIndex {

    @Id
    @Column(name="user_id", unique=true, nullable=false)
    private Integer userId;

    @Column(name="level")
    private Integer level;

    @Column(name="ts_frame_arrival")
    private Integer tsFrameArrival;

    @Column(name="src_geo")
    private Integer srcGeo;

    @Column(name="src_domestic")
    private Integer srcDomestic;

    @Column(name="isp_name_req")
    private Integer ispNameReq;

    @Column(name="idc_name_req")
    private Integer idcNameReq;

    @Column(name="src_ip")
    private Integer srcIp;

    @Column(name="src_port")
    private Integer srcPort;

    @Column(name="dst_geo")
    private Integer dstGeo;

    @Column(name="dst_domestic")
    private Integer dstDomestic;

    @Column(name="isp_name_res")
    private Integer ispNameRes;

    @Column(name="idc_name_res")
    private Integer idcNameRes;

    @Column(name="dst_ip")
    private Integer dstIp;

    @Column(name="dst_port")
    private Integer dstPort;

    @Column(name="http_res_code")
    private Integer httpResCode;

    @Column(name="http_host")
    private Integer httpHost;

    @Column(name="page")
    private Integer page;

    @Column(name="http_user_agent")
    private Integer httpUserAgent;

    @Column(name="ts_page")
    private Integer tsPage;

    @Column(name="mbps")
    private Integer mbps;

    @Column(name="uri_cnt")
    private Integer uriCnt;

    @Column(name="session_cnt")
    private Integer sessionCnt;

    @Column(name="conn_err_session_cnt")
    private Integer connErrSessionCnt;

    @Column(name="stopped_transaction_cnt")
    private Integer stoppedTransactionCnt;

    @Column(name="conn_err_pkt_cnt")
    private Integer connErrPktCnt;

    @Column(name="tcp_error")
    private Integer tcpError;

    @Column(name="http_error")
    private Integer httpError;

    @Column(name="src_mac")
    private Integer srcMac;

    @Column(name="dst_mac")
    private Integer dstMac;

    @Column(name="http_method")
    private Integer httpMethod;

    @Column(name="http_version")
    private Integer httpVersion;

    @Column(name="http_version_req")
    private Integer httpVersionReq;

    @Column(name="http_version_res")
    private Integer httpVersionRes;

    @Column(name="http_res_phrase")
    private Integer httpResPhrase;

    @Column(name="http_content_type")
    private Integer httpContentType;

    @Column(name="http_cookie")
    private Integer httpCookie;

    @Column(name="http_location")
    private Integer httpLocation;

    @Column(name="http_uri")
    private Integer httpUri;

    @Column(name="http_uri_split")
    private Integer httpUriSplit;

    @Column(name="http_referer")
    private Integer httpReferer;

    @Column(name="http_content_length")
    private Integer httpContentLength;

    @Column(name="res_code_2xx_cnt")
    private Integer resCode2xxCnt;

    @Column(name="res_code_3xx_cnt")
    private Integer resCode3xxCnt;

    @Column(name="res_code_401_cnt")
    private Integer resCode401Cnt;

    @Column(name="res_code_404_cnt")
    private Integer resCode404Cnt;

    @Column(name="res_code_4xx_cnt")
    private Integer resCode4xxCnt;

    @Column(name="res_code_5xx_cnt")
    private Integer resCode5xxCnt;

    @Column(name="res_code_oth_cnt")
    private Integer resCodeOthCnt;

    @Column(name="user_agent_software_name")
    private Integer userAgentSoftwareName;

    @Column(name="user_agent_operating_system_name")
    private Integer userAgentOperatingSystemName;

    @Column(name="user_agent_operating_platform")
    private Integer userAgentOperatingPlatform;

    @Column(name="user_agent_hardware_type")
    private Integer userAgentHardwareType;

    @Column(name="ts_frame_landoff")
    private Integer tsFrameLandoff;

    @Column(name="ts_page_begin")
    private Integer tsPageBegin;

    @Column(name="ts_page_end")
    private Integer tsPageEnd;

    @Column(name="ts_page_req_syn")
    private Integer tsPageReqSyn;

    @Column(name="ts_page_gap")
    private Integer tsPageGap;

    @Column(name="ts_page_res_init")
    private Integer tsPageResInit;

    @Column(name="ts_page_res_init_gap")
    private Integer tsPageResInitGap;

    @Column(name="ts_page_res_app")
    private Integer tsPageResApp;

    @Column(name="ts_page_res_app_gap")
    private Integer tsPageResAppGap;

    @Column(name="ts_page_res")
    private Integer tsPageRes;

    @Column(name="ts_page_res_gap")
    private Integer tsPageResGap;

    @Column(name="ts_page_transfer_req")
    private Integer tsPageTransferReq;

    @Column(name="ts_page_transfer_req_gap")
    private Integer tsPageTransferReqGap;

    @Column(name="ts_page_transfer_res")
    private Integer tsPageTransferRes;

    @Column(name="ts_page_transfer_res_gap")
    private Integer tsPageTransferResGap;

    @Column(name="ts_page_rtt_conn_sum_req")
    private Integer tsPageRttConnSumReq;

    @Column(name="ts_page_rtt_conn_sum_res")
    private Integer tsPageRttConnSumRes;

    @Column(name="ts_page_rtt_ack_sum_req")
    private Integer tsPageRttAckSumReq;

    @Column(name="ts_page_rtt_ack_sum_res")
    private Integer tsPageRttAckSumRes;

    @Column(name="ts_page_req_making_sum")
    private Integer tsPageReqMakingSum;

    @Column(name="page_rtt_conn_cnt_req")
    private Integer pageRttConnCntReq;

    @Column(name="page_rtt_conn_cnt_res")
    private Integer pageRttConnCntRes;

    @Column(name="page_rtt_ack_cnt_req")
    private Integer pageRttAckCntReq;

    @Column(name="page_rtt_ack_cnt_res")
    private Integer pageRttAckCntRes;

    @Column(name="page_req_making_cnt")
    private Integer pageReqMakingCnt;

    @Column(name="page_http_len_req")
    private Integer pageHttpLenReq;

    @Column(name="page_http_len_res")
    private Integer pageHttpLenRes;

    @Column(name="page_http_cnt_req")
    private Integer pageHttpCntReq;

    @Column(name="page_http_cnt_res")
    private Integer pageHttpCntRes;

    @Column(name="page_pkt_len_req")
    private Integer pagePktLenReq;

    @Column(name="page_pkt_len_res")
    private Integer pagePktLenRes;

    @Column(name="page_pkt_cnt_req")
    private Integer pagePktCntReq;

    @Column(name="page_pkt_cnt_res")
    private Integer pagePktCntRes;

    @Column(name="page_tcp_len_req")
    private Integer pageTcpLenReq;

    @Column(name="page_tcp_len_res")
    private Integer pageTcpLenRes;

    @Column(name="page_tcp_cnt_req")
    private Integer pageTcpCntReq;

    @Column(name="page_tcp_cnt_res")
    private Integer pageTcpCntRes;

    @Column(name="req_conn_err_session_len")
    private Integer reqConnErrSessionLen;

    @Column(name="res_conn_err_session_len")
    private Integer resConnErrSessionLen;

    @Column(name="ts_page_rtt_conn_req_min")
    private Integer tsPageRttConnReqMin;

    @Column(name="ts_page_rtt_conn_res_min")
    private Integer tsPageRttConnResMin;

    @Column(name="ts_page_rtt_conn_req_max")
    private Integer tsPageRttConnReqMax;

    @Column(name="ts_page_rtt_conn_res_max")
    private Integer tsPageRttConnResMax;

    @Column(name="ts_page_rtt_ack_req_min")
    private Integer tsPageRttAckReqMin;

    @Column(name="ts_page_rtt_ack_res_min")
    private Integer tsPageRttAckResMin;

    @Column(name="ts_page_rtt_ack_req_max")
    private Integer tsPageRttAckReqMax;

    @Column(name="ts_page_rtt_ack_res_max")
    private Integer tsPageRttAckResMax;

    @Column(name="ts_page_req_making_min")
    private Integer tsPageReqMakingMin;

    @Column(name="ts_page_req_making_max")
    private Integer tsPageReqMakingMax;

    @Column(name="ts_page_rto_sum_req")
    private Integer tsPageRtoSumReq;

    @Column(name="ts_page_rto_sum_res")
    private Integer tsPageRtoSumRes;

    @Column(name="ts_page_rto_cnt_req")
    private Integer tsPageRtoCntReq;

    @Column(name="ts_page_rto_cnt_res")
    private Integer tsPageRtoCntRes;

    @Column(name="retransmission_cnt_req")
    private Integer retransmissionCntReq;

    @Column(name="retransmission_cnt_res")
    private Integer retransmissionCntRes;

    @Column(name="retransmission_len_req")
    private Integer retransmissionLenReq;

    @Column(name="retransmission_len_res")
    private Integer retransmissionLenRes;

    @Column(name="fast_retransmission_cnt_req")
    private Integer fastRetransmissionCntReq;

    @Column(name="fast_retransmission_cnt_res")
    private Integer fastRetransmissionCntRes;

    @Column(name="fast_retransmission_len_req")
    private Integer fastRetransmissionLenReq;

    @Column(name="fast_retransmission_len_res")
    private Integer fastRetransmissionLenRes;

    @Column(name="out_of_order_cnt_req")
    private Integer outOfOrderCntReq;

    @Column(name="out_of_order_cnt_res")
    private Integer outOfOrderCntRes;

    @Column(name="out_of_order_len_req")
    private Integer outOfOrderLenReq;

    @Column(name="out_of_order_len_res")
    private Integer outOfOrderLenRes;

    @Column(name="lost_seg_cnt_req")
    private Integer lostSegCntReq;

    @Column(name="lost_seg_cnt_res")
    private Integer lostSegCntRes;

    @Column(name="lost_seg_len_req")
    private Integer lostSegLenReq;

    @Column(name="lost_seg_len_res")
    private Integer lostSegLenRes;

    @Column(name="ack_lost_cnt_req")
    private Integer ackLostCntReq;

    @Column(name="ack_lost_cnt_res")
    private Integer ackLostCntRes;

    @Column(name="ack_lost_len_req")
    private Integer ackLostLenReq;

    @Column(name="ack_lost_len_res")
    private Integer ackLostLenRes;

    @Column(name="win_update_cnt_req")
    private Integer winUpdateCntReq;

    @Column(name="win_update_cnt_res")
    private Integer winUpdateCntRes;

    @Column(name="win_update_len_req")
    private Integer winUpdateLenReq;

    @Column(name="win_update_len_res")
    private Integer winUpdateLenRes;

    @Column(name="dup_ack_cnt_req")
    private Integer dupAckCntReq;

    @Column(name="dup_ack_cnt_res")
    private Integer dupAckCntRes;

    @Column(name="dup_ack_len_req")
    private Integer dupAckLenReq;

    @Column(name="dup_ack_len_res")
    private Integer dupAckLenRes;

    @Column(name="zero_win_cnt_req")
    private Integer zeroWinCntReq;

    @Column(name="zero_win_cnt_res")
    private Integer zeroWinCntRes;

    @Column(name="zero_win_len_req")
    private Integer zeroWinLenReq;

    @Column(name="zero_win_len_res")
    private Integer zeroWinLenRes;

    @Column(name="spurious_retransmission_cnt_req")
    private Integer spuriousRetransmissionCntReq;

    @Column(name="spurious_retransmission_cnt_res")
    private Integer spuriousRetransmissionCntRes;

    @Column(name="spurious_retransmission_len_req")
    private Integer spuriousRetransmissionLenReq;

    @Column(name="spurious_retransmission_len_res")
    private Integer spuriousRetransmissionLenRes;

    @Column(name="overlap_cnt_req")
    private Integer overlapCntReq;

    @Column(name="overlap_cnt_res")
    private Integer overlapCntRes;

    @Column(name="overlap_len_req")
    private Integer overlapLenReq;

    @Column(name="overlap_len_res")
    private Integer overlapLenRes;

    @Column(name="overlap_attack_cnt_req")
    private Integer overlapAttackCntReq;

    @Column(name="overlap_attack_cnt_res")
    private Integer overlapAttackCntRes;

    @Column(name="overlap_attack_len_req")
    private Integer overlapAttackLenReq;

    @Column(name="overlap_attack_len_res")
    private Integer overlapAttackLenRes;

    @Column(name="zero_win_probe_cnt_req")
    private Integer zeroWinProbeCntReq;

    @Column(name="zero_win_probe_cnt_res")
    private Integer zeroWinProbeCntRes;

    @Column(name="zero_win_probe_len_req")
    private Integer zeroWinProbeLenReq;

    @Column(name="zero_win_probe_len_res")
    private Integer zeroWinProbeLenRes;

    @Column(name="zero_win_probe_ack_cnt_req")
    private Integer zeroWinProbeAckCntReq;

    @Column(name="zero_win_probe_ack_cnt_res")
    private Integer zeroWinProbeAckCntRes;

    @Column(name="zero_win_probe_ack_len_req")
    private Integer zeroWinProbeAckLenReq;

    @Column(name="zero_win_probe_ack_len_res")
    private Integer zeroWinProbeAckLenRes;

    @Column(name="keep_alive_cnt_req")
    private Integer keepAliveCntReq;

    @Column(name="keep_alive_cnt_res")
    private Integer keepAliveCntRes;

    @Column(name="keep_alive_len_req")
    private Integer keepAliveLenReq;

    @Column(name="keep_alive_len_res")
    private Integer keepAliveLenRes;

    @Column(name="keep_alive_ack_cnt_req")
    private Integer keepAliveAckCntReq;

    @Column(name="keep_alive_ack_cnt_res")
    private Integer keepAliveAckCntRes;

    @Column(name="keep_alive_ack_len_req")
    private Integer keepAliveAckLenReq;

    @Column(name="keep_alive_ack_len_res")
    private Integer keepAliveAckLenRes;

    @Column(name="as_name_req")
    private Integer asNameReq;

    @Column(name="as_name_res")
    private Integer asNameRes;

    @Column(name="application_name")
    private Integer applicationName;

    @Column(name="ndpi_protocol_app")
    private Integer ndpiProtocolApp;

    @Column(name="ndpi_protocol_master")
    private Integer ndpiProtocolMaster;
}
