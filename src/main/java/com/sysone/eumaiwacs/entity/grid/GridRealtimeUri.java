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
@Table(name="tbl_info_grid_realtime_uri")
public class GridRealtimeUri {

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

    @Column(name="ts_rsq_delay_response")
    private Boolean tsRsqDelayResponse;

    @Column(name="mbps")
    private Boolean mbps;

    @Column(name="tcp_error")
    private Boolean tcpError;

    @Column(name="is_stopped_transaction")
    private Boolean isStoppedTransaction;

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

    @Column(name="http_content_length")
    private Boolean httpContentLength;

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

    @Column(name="application_name")
    private Boolean applicationName;

    @Column(name="ndpi_protocol_app")
    private Boolean ndpiProtocolApp;

    @Column(name="ndpi_protocol_master")
    private Boolean ndpiProtocolMaster;

    @Column(name="user_agent_software_name")
    private Boolean userAgentSoftwareName;

    @Column(name="user_agent_software_version")
    private Boolean userAgentSoftwareVersion;

    @Column(name="user_agent_software_version_full")
    private Boolean userAgentSoftwareVersionFull;

    @Column(name="user_agent_operating_system_name")
    private Boolean userAgentOperatingSystemName;

    @Column(name="user_agent_operating_system_version")
    private Boolean userAgentOperatingSystemVersion;

    @Column(name="user_agent_operating_system_version_full")
    private Boolean userAgentOperatingSystemVersionFull;

    @Column(name="user_agent_operating_system_flavour")
    private Boolean userAgentOperatingSystemFlavour;

    @Column(name="user_agent_operating_platform")
    private Boolean userAgentOperatingPlatform;

    @Column(name="user_agent_operating_platform_code")
    private Boolean userAgentOperatingPlatformCode;

    @Column(name="user_agent_operating_platform_code_name")
    private Boolean userAgentOperatingPlatformCodeName;

    @Column(name="user_agent_operating_platform_vendor_name")
    private Boolean userAgentOperatingPlatformVendorName;

    @Column(name="user_agent_software_type")
    private Boolean userAgentSoftwareType;

    @Column(name="user_agent_software_sub_type")
    private Boolean userAgentSoftwareSubType;

    @Column(name="user_agent_hardware_type")
    private Boolean userAgentHardwareType;

    @Column(name="user_agent_hardware_sub_type")
    private Boolean userAgentHardwareSubType;

    @Column(name="user_agent_layout_engine_name")
    private Boolean userAgentLayoutEngineName;

    @Column(name="ts_frame_landoff")
    private Boolean tsFrameLandoff;

    @Column(name="ts_frame_arrival_page")
    private Boolean tsFrameArrivalPage;

    @Column(name="ts_frame_landoff_page")
    private Boolean tsFrameLandoffPage;

    @Column(name="req_seq_first")
    private Boolean reqSeqFirst;

    @Column(name="req_ack_first")
    private Boolean reqAckFirst;

    @Column(name="req_seq_last")
    private Boolean reqSeqLast;

    @Column(name="req_ack_last")
    private Boolean reqAckLast;

    @Column(name="res_seq_first")
    private Boolean resSeqFirst;

    @Column(name="res_ack_first")
    private Boolean resAckFirst;

    @Column(name="ts_req_pkt_first")
    private Boolean tsReqPktFirst;

    @Column(name="ts_req_pkt_push")
    private Boolean tsReqPktPush;

    @Column(name="ts_req_pkt_last")
    private Boolean tsReqPktLast;

    @Column(name="ts_res_pkt_first")
    private Boolean tsResPktFirst;

    @Column(name="ts_res_pkt_push")
    private Boolean tsResPktPush;

    @Column(name="ts_res_pkt_last")
    private Boolean tsResPktLast;

    @Column(name="ts_req_delay_transfer")
    private Boolean tsReqDelayTransfer;

    @Column(name="ts_res_delay_transfer")
    private Boolean tsResDelayTransfer;

    @Column(name="ts_res_process_first")
    private Boolean tsResProcessFirst;

    @Column(name="ts_res_process_push")
    private Boolean tsResProcessPush;

    @Column(name="ts_res_process_last")
    private Boolean tsResProcessLast;

    @Column(name="ts_rtt_syn")
    private Boolean tsRttSyn;

    @Column(name="ts_rtt_syn_ack")
    private Boolean tsRttSynAck;

    @Column(name="ts_rtt_first_ack")
    private Boolean tsRttFirstAck;

    @Column(name="ts_rtt_req_ack")
    private Boolean tsRttReqAck;

    @Column(name="ts_rtt_ack_req_ack")
    private Boolean tsRttAckReqAck;

    @Column(name="ts_rtt_res_ack")
    private Boolean tsRttResAck;

    @Column(name="ts_rtt_ack_res_ack")
    private Boolean tsRttAckResAck;

    @Column(name="ts_rtt_conn_req")
    private Boolean tsRttConnReq;

    @Column(name="ts_rtt_conn_res")
    private Boolean tsRttConnRes;

    @Column(name="ts_rtt_first_ack_req")
    private Boolean tsRttFirstAckReq;

    @Column(name="ts_rtt_first_ack_res")
    private Boolean tsRttFirstAckRes;

    @Column(name="ts_conn_delay_req")
    private Boolean tsConnDelayReq;

    @Column(name="ts_conn_delay_res")
    private Boolean tsConnDelayRes;

    @Column(name="ts_first_ack_delay_req")
    private Boolean tsFirstAckDelayReq;

    @Column(name="ts_first_ack_delay_res")
    private Boolean tsFirstAckDelayRes;

    @Column(name="ts_req_making")
    private Boolean tsReqMaking;

    @Column(name="pkt_len_req")
    private Boolean pktLenReq;

    @Column(name="pkt_len_res")
    private Boolean pktLenRes;

    @Column(name="pkt_cnt_req")
    private Boolean pktCntReq;

    @Column(name="pkt_cnt_res")
    private Boolean pktCntRes;

    @Column(name="http_len_req")
    private Boolean httpLenReq;

    @Column(name="http_len_res")
    private Boolean httpLenRes;

    @Column(name="http_cnt_req")
    private Boolean httpCntReq;

    @Column(name="http_cnt_res")
    private Boolean httpCntRes;

    @Column(name="tcp_len_req")
    private Boolean tcpLenReq;

    @Column(name="tcp_len_res")
    private Boolean tcpLenRes;

    @Column(name="tcp_cnt_req")
    private Boolean tcpCntReq;

    @Column(name="tcp_cnt_res")
    private Boolean tcpCntRes;

    @Column(name="ack_rtt_sum_req")
    private Boolean ackRttSumReq;

    @Column(name="ack_rtt_sum_res")
    private Boolean ackRttSumRes;

    @Column(name="ack_rto_sum_req")
    private Boolean ackRtoSumReq;

    @Column(name="ack_rto_sum_res")
    private Boolean ackRtoSumRes;

    @Column(name="ack_rtt_cnt_req")
    private Boolean ackRttCntReq;

    @Column(name="ack_rtt_cnt_res")
    private Boolean ackRttCntRes;

    @Column(name="ack_rto_cnt_req")
    private Boolean ackRtoCntReq;

    @Column(name="ack_rto_cnt_res")
    private Boolean ackRtoCntRes;

    @Column(name="ack_delay_req")
    private Boolean ackDelayReq;

    @Column(name="ack_delay_res")
    private Boolean ackDelayRes;

    @Column(name="ack_rtt_min_req")
    private Boolean ackRttMinReq;

    @Column(name="ack_rtt_min_res")
    private Boolean ackRttMinRes;

    @Column(name="ack_rtt_max_req")
    private Boolean ackRttMaxReq;

    @Column(name="ack_rtt_max_res")
    private Boolean ackRttMaxRes;

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

}
