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
@Table(name="tbl_info_grid_realtime_uri_index")
public class GridRealtimeUriIndex {

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

    @Column(name="ts_rsq_delay_response")
    private Integer tsRsqDelayResponse;

    @Column(name="mbps")
    private Integer mbps;

    @Column(name="tcp_error")
    private Integer tcpError;

    @Column(name="is_stopped_transaction")
    private Integer isStoppedTransaction;

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

    @Column(name="http_content_length")
    private Integer httpContentLength;

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

    @Column(name="application_name")
    private Integer applicationName;

    @Column(name="ndpi_protocol_app")
    private Integer ndpiProtocolApp;

    @Column(name="ndpi_protocol_master")
    private Integer ndpiProtocolMaster;

    @Column(name="user_agent_software_name")
    private Integer userAgentSoftwareName;

    @Column(name="user_agent_software_version")
    private Integer userAgentSoftwareVersion;

    @Column(name="user_agent_software_version_full")
    private Integer userAgentSoftwareVersionFull;

    @Column(name="user_agent_operating_system_name")
    private Integer userAgentOperatingSystemName;

    @Column(name="user_agent_operating_system_version")
    private Integer userAgentOperatingSystemVersion;

    @Column(name="user_agent_operating_system_version_full")
    private Integer userAgentOperatingSystemVersionFull;

    @Column(name="user_agent_operating_system_flavour")
    private Integer userAgentOperatingSystemFlavour;

    @Column(name="user_agent_operating_platform")
    private Integer userAgentOperatingPlatform;

    @Column(name="user_agent_operating_platform_code")
    private Integer userAgentOperatingPlatformCode;

    @Column(name="user_agent_operating_platform_code_name")
    private Integer userAgentOperatingPlatformCodeName;

    @Column(name="user_agent_operating_platform_vendor_name")
    private Integer userAgentOperatingPlatformVendorName;

    @Column(name="user_agent_software_type")
    private Integer userAgentSoftwareType;

    @Column(name="user_agent_software_sub_type")
    private Integer userAgentSoftwareSubType;

    @Column(name="user_agent_hardware_type")
    private Integer userAgentHardwareType;

    @Column(name="user_agent_hardware_sub_type")
    private Integer userAgentHardwareSubType;

    @Column(name="user_agent_layout_engine_name")
    private Integer userAgentLayoutEngineName;

    @Column(name="ts_frame_landoff")
    private Integer tsFrameLandoff;

    @Column(name="ts_frame_arrival_page")
    private Integer tsFrameArrivalPage;

    @Column(name="ts_frame_landoff_page")
    private Integer tsFrameLandoffPage;

    @Column(name="req_seq_first")
    private Integer reqSeqFirst;

    @Column(name="req_ack_first")
    private Integer reqAckFirst;

    @Column(name="req_seq_last")
    private Integer reqSeqLast;

    @Column(name="req_ack_last")
    private Integer reqAckLast;

    @Column(name="res_seq_first")
    private Integer resSeqFirst;

    @Column(name="res_ack_first")
    private Integer resAckFirst;

    @Column(name="ts_req_pkt_first")
    private Integer tsReqPktFirst;

    @Column(name="ts_req_pkt_push")
    private Integer tsReqPktPush;

    @Column(name="ts_req_pkt_last")
    private Integer tsReqPktLast;

    @Column(name="ts_res_pkt_first")
    private Integer tsResPktFirst;

    @Column(name="ts_res_pkt_push")
    private Integer tsResPktPush;

    @Column(name="ts_res_pkt_last")
    private Integer tsResPktLast;

    @Column(name="ts_req_delay_transfer")
    private Integer tsReqDelayTransfer;

    @Column(name="ts_res_delay_transfer")
    private Integer tsResDelayTransfer;

    @Column(name="ts_res_process_first")
    private Integer tsResProcessFirst;

    @Column(name="ts_res_process_push")
    private Integer tsResProcessPush;

    @Column(name="ts_res_process_last")
    private Integer tsResProcessLast;

    @Column(name="ts_rtt_syn")
    private Integer tsRttSyn;

    @Column(name="ts_rtt_syn_ack")
    private Integer tsRttSynAck;

    @Column(name="ts_rtt_first_ack")
    private Integer tsRttFirstAck;

    @Column(name="ts_rtt_req_ack")
    private Integer tsRttReqAck;

    @Column(name="ts_rtt_ack_req_ack")
    private Integer tsRttAckReqAck;

    @Column(name="ts_rtt_res_ack")
    private Integer tsRttResAck;

    @Column(name="ts_rtt_ack_res_ack")
    private Integer tsRttAckResAck;

    @Column(name="ts_rtt_conn_req")
    private Integer tsRttConnReq;

    @Column(name="ts_rtt_conn_res")
    private Integer tsRttConnRes;

    @Column(name="ts_rtt_first_ack_req")
    private Integer tsRttFirstAckReq;

    @Column(name="ts_rtt_first_ack_res")
    private Integer tsRttFirstAckRes;

    @Column(name="ts_conn_delay_req")
    private Integer tsConnDelayReq;

    @Column(name="ts_conn_delay_res")
    private Integer tsConnDelayRes;

    @Column(name="ts_first_ack_delay_req")
    private Integer tsFirstAckDelayReq;

    @Column(name="ts_first_ack_delay_res")
    private Integer tsFirstAckDelayRes;

    @Column(name="ts_req_making")
    private Integer tsReqMaking;

    @Column(name="pkt_len_req")
    private Integer pktLenReq;

    @Column(name="pkt_len_res")
    private Integer pktLenRes;

    @Column(name="pkt_cnt_req")
    private Integer pktCntReq;

    @Column(name="pkt_cnt_res")
    private Integer pktCntRes;

    @Column(name="http_len_req")
    private Integer httpLenReq;

    @Column(name="http_len_res")
    private Integer httpLenRes;

    @Column(name="http_cnt_req")
    private Integer httpCntReq;

    @Column(name="http_cnt_res")
    private Integer httpCntRes;

    @Column(name="tcp_len_req")
    private Integer tcpLenReq;

    @Column(name="tcp_len_res")
    private Integer tcpLenRes;

    @Column(name="tcp_cnt_req")
    private Integer tcpCntReq;

    @Column(name="tcp_cnt_res")
    private Integer tcpCntRes;

    @Column(name="ack_rtt_sum_req")
    private Integer ackRttSumReq;

    @Column(name="ack_rtt_sum_res")
    private Integer ackRttSumRes;

    @Column(name="ack_rto_sum_req")
    private Integer ackRtoSumReq;

    @Column(name="ack_rto_sum_res")
    private Integer ackRtoSumRes;

    @Column(name="ack_rtt_cnt_req")
    private Integer ackRttCntReq;

    @Column(name="ack_rtt_cnt_res")
    private Integer ackRttCntRes;

    @Column(name="ack_rto_cnt_req")
    private Integer ackRtoCntReq;

    @Column(name="ack_rto_cnt_res")
    private Integer ackRtoCntRes;

    @Column(name="ack_delay_req")
    private Integer ackDelayReq;

    @Column(name="ack_delay_res")
    private Integer ackDelayRes;

    @Column(name="ack_rtt_min_req")
    private Integer ackRttMinReq;

    @Column(name="ack_rtt_min_res")
    private Integer ackRttMinRes;

    @Column(name="ack_rtt_max_req")
    private Integer ackRttMaxReq;

    @Column(name="ack_rtt_max_res")
    private Integer ackRttMaxRes;

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
}
