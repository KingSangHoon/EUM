package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table (name = "tbl_info_alarm_uri")
public class InfoAlarmUri {

	@Id
	@SequenceGenerator(name="tbl_info_alarm_uri_seq", sequenceName="tbl_info_alarm_uri_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_alarm_uri_seq")
   	@Column(name = "uri_id")
	private Integer uriId;

   	@Column(name = "ts_res_delay_transfer_use")
	private Boolean tsResDelayTransferUse;

   	@Column(name = "ts_res_delay_transfer_caution")
	private Integer tsResDelayTransferCaution;

   	@Column(name = "ts_res_delay_transfer_warning")
	private Integer tsResDelayTransferWarning;

   	@Column(name = "ts_res_delay_transfer_danger")
	private Integer tsResDelayTransferDanger;

   	@Column(name = "ts_res_delay_transfer_obstacle")
	private Integer tsResDelayTransferObstacle;

   	@Column(name = "ts_rsq_delay_response_use")
	private Boolean tsRsqDelayResponseUse;

   	@Column(name = "ts_rsq_delay_response_caution")
	private Integer tsRsqDelayResponseCaution;

   	@Column(name = "ts_rsq_delay_response_warning")
	private Integer tsRsqDelayResponseWarning;

   	@Column(name = "ts_rsq_delay_response_danger")
	private Integer tsRsqDelayResponseDanger;

   	@Column(name = "ts_rsq_delay_response_obstacle")
	private Integer tsRsqDelayResponseObstacle;

   	@Column(name = "ts_res_process_first_use")
	private Boolean tsResProcessFirstUse;

   	@Column(name = "ts_res_process_first_caution")
	private Integer tsResProcessFirstCaution;

   	@Column(name = "ts_res_process_first_warning")
	private Integer tsResProcessFirstWarning;

   	@Column(name = "ts_res_process_first_danger")
	private Integer tsResProcessFirstDanger;

   	@Column(name = "ts_res_process_first_obstacle")
	private Integer tsResProcessFirstObstacle;

   	@Column(name = "ts_res_process_push_use")
	private Boolean tsResProcessPushUse;

   	@Column(name = "ts_res_process_push_caution")
	private Integer tsResProcessPushCaution;

   	@Column(name = "ts_res_process_push_warning")
	private Integer tsResProcessPushWarning;

   	@Column(name = "ts_res_process_push_danger")
	private Integer tsResProcessPushDanger;

   	@Column(name = "ts_res_process_push_obstacle")
	private Integer tsResProcessPushObstacle;

   	@Column(name = "ts_res_process_last_use")
	private Boolean tsResProcessLastUse;

   	@Column(name = "ts_res_process_last_caution")
	private Integer tsResProcessLastCaution;

   	@Column(name = "ts_res_process_last_warning")
	private Integer tsResProcessLastWarning;

   	@Column(name = "ts_res_process_last_danger")
	private Integer tsResProcessLastDanger;

   	@Column(name = "ts_res_process_last_obstacle")
	private Integer tsResProcessLastObstacle;

   	@Column(name = "ts_req_delay_transfer_use")
	private Boolean tsReqDelayTransferUse;

   	@Column(name = "ts_req_delay_transfer_caution")
	private Integer tsReqDelayTransferCaution;

   	@Column(name = "ts_req_delay_transfer_warning")
	private Integer tsReqDelayTransferWarning;

   	@Column(name = "ts_req_delay_transfer_danger")
	private Integer tsReqDelayTransferDanger;

   	@Column(name = "ts_req_delay_transfer_obstacle")
	private Integer tsReqDelayTransferObstacle;

   	@Column(name = "ts_rtt_syn_use")
	private Boolean tsRttSynUse;

   	@Column(name = "ts_rtt_syn_caution")
	private Integer tsRttSynCaution;

   	@Column(name = "ts_rtt_syn_warning")
	private Integer tsRttSynWarning;

   	@Column(name = "ts_rtt_syn_danger")
	private Integer tsRttSynDanger;

   	@Column(name = "ts_rtt_syn_obstacle")
	private Integer tsRttSynObstacle;

   	@Column(name = "ts_rtt_syn_ack_use")
	private Boolean tsRttSynAckUse;

   	@Column(name = "ts_rtt_syn_ack_caution")
	private Integer tsRttSynAckCaution;

   	@Column(name = "ts_rtt_syn_ack_warning")
	private Integer tsRttSynAckWarning;

   	@Column(name = "ts_rtt_syn_ack_danger")
	private Integer tsRttSynAckDanger;

   	@Column(name = "ts_rtt_syn_ack_obstacle")
	private Integer tsRttSynAckObstacle;

   	@Column(name = "ts_rtt_first_ack_use")
	private Boolean tsRttFirstAckUse;

   	@Column(name = "ts_rtt_first_ack_caution")
	private Integer tsRttFirstAckCaution;

   	@Column(name = "ts_rtt_first_ack_warning")
	private Integer tsRttFirstAckWarning;

   	@Column(name = "ts_rtt_first_ack_danger")
	private Integer tsRttFirstAckDanger;

   	@Column(name = "ts_rtt_first_ack_obstacle")
	private Integer tsRttFirstAckObstacle;

   	@Column(name = "ts_rtt_req_ack_use")
	private Boolean tsRttReqAckUse;

   	@Column(name = "ts_rtt_req_ack_caution")
	private Integer tsRttReqAckCaution;

   	@Column(name = "ts_rtt_req_ack_warning")
	private Integer tsRttReqAckWarning;

   	@Column(name = "ts_rtt_req_ack_danger")
	private Integer tsRttReqAckDanger;

   	@Column(name = "ts_rtt_req_ack_obstacle")
	private Integer tsRttReqAckObstacle;

   	@Column(name = "ts_rtt_ack_req_ack_use")
	private Boolean tsRttAckReqAckUse;

   	@Column(name = "ts_rtt_ack_req_ack_caution")
	private Integer tsRttAckReqAckCaution;

   	@Column(name = "ts_rtt_ack_req_ack_warning")
	private Integer tsRttAckReqAckWarning;

   	@Column(name = "ts_rtt_ack_req_ack_danger")
	private Integer tsRttAckReqAckDanger;

   	@Column(name = "ts_rtt_ack_req_ack_obstacle")
	private Integer tsRttAckReqAckObstacle;

   	@Column(name = "ts_rtt_res_ack_use")
	private Boolean tsRttResAckUse;

   	@Column(name = "ts_rtt_res_ack_caution")
	private Integer tsRttResAckCaution;

   	@Column(name = "ts_rtt_res_ack_warning")
	private Integer tsRttResAckWarning;

   	@Column(name = "ts_rtt_res_ack_danger")
	private Integer tsRttResAckDanger;

   	@Column(name = "ts_rtt_res_ack_obstacle")
	private Integer tsRttResAckObstacle;

   	@Column(name = "ts_rtt_ack_res_ack_use")
	private Boolean tsRttAckResAckUse;

   	@Column(name = "ts_rtt_ack_res_ack_caution")
	private Integer tsRttAckResAckCaution;

   	@Column(name = "ts_rtt_ack_res_ack_warning")
	private Integer tsRttAckResAckWarning;

   	@Column(name = "ts_rtt_ack_res_ack_danger")
	private Integer tsRttAckResAckDanger;

   	@Column(name = "ts_rtt_ack_res_ack_obstacle")
	private Integer tsRttAckResAckObstacle;

   	@Column(name = "ts_rtt_conn_req_use")
	private Boolean tsRttConnReqUse;

   	@Column(name = "ts_rtt_conn_req_caution")
	private Integer tsRttConnReqCaution;

   	@Column(name = "ts_rtt_conn_req_warning")
	private Integer tsRttConnReqWarning;

   	@Column(name = "ts_rtt_conn_req_danger")
	private Integer tsRttConnReqDanger;

   	@Column(name = "ts_rtt_conn_req_obstacle")
	private Integer tsRttConnReqObstacle;

   	@Column(name = "ts_rtt_conn_res_use")
	private Boolean tsRttConnResUse;

   	@Column(name = "ts_rtt_conn_res_caution")
	private Integer tsRttConnResCaution;

   	@Column(name = "ts_rtt_conn_res_warning")
	private Integer tsRttConnResWarning;

   	@Column(name = "ts_rtt_conn_res_danger")
	private Integer tsRttConnResDanger;

   	@Column(name = "ts_rtt_conn_res_obstacle")
	private Integer tsRttConnResObstacle;

   	@Column(name = "ts_rtt_first_ack_req_use")
	private Boolean tsRttFirstAckReqUse;

   	@Column(name = "ts_rtt_first_ack_req_caution")
	private Integer tsRttFirstAckReqCaution;

   	@Column(name = "ts_rtt_first_ack_req_warning")
	private Integer tsRttFirstAckReqWarning;

   	@Column(name = "ts_rtt_first_ack_req_danger")
	private Integer tsRttFirstAckReqDanger;

   	@Column(name = "ts_rtt_first_ack_req_obstacle")
	private Integer tsRttFirstAckReqObstacle;

   	@Column(name = "ts_rtt_first_ack_res_use")
	private Boolean tsRttFirstAckResUse;

   	@Column(name = "ts_rtt_first_ack_res_caution")
	private Integer tsRttFirstAckResCaution;

   	@Column(name = "ts_rtt_first_ack_res_warning")
	private Integer tsRttFirstAckResWarning;

   	@Column(name = "ts_rtt_first_ack_res_danger")
	private Integer tsRttFirstAckResDanger;

   	@Column(name = "ts_rtt_first_ack_res_obstacle")
	private Integer tsRttFirstAckResObstacle;

   	@Column(name = "ts_conn_delay_req_use")
	private Boolean tsConnDelayReqUse;

   	@Column(name = "ts_conn_delay_req_caution")
	private Integer tsConnDelayReqCaution;

   	@Column(name = "ts_conn_delay_req_warning")
	private Integer tsConnDelayReqWarning;

   	@Column(name = "ts_conn_delay_req_danger")
	private Integer tsConnDelayReqDanger;

   	@Column(name = "ts_conn_delay_req_obstacle")
	private Integer tsConnDelayReqObstacle;

   	@Column(name = "ts_conn_delay_res_use")
	private Boolean tsConnDelayResUse;

   	@Column(name = "ts_conn_delay_res_caution")
	private Integer tsConnDelayResCaution;

   	@Column(name = "ts_conn_delay_res_warning")
	private Integer tsConnDelayResWarning;

   	@Column(name = "ts_conn_delay_res_danger")
	private Integer tsConnDelayResDanger;

   	@Column(name = "ts_conn_delay_res_obstacle")
	private Integer tsConnDelayResObstacle;

   	@Column(name = "ts_first_ack_delay_req_use")
	private Boolean tsFirstAckDelayReqUse;

   	@Column(name = "ts_first_ack_delay_req_caution")
	private Integer tsFirstAckDelayReqCaution;

   	@Column(name = "ts_first_ack_delay_req_warning")
	private Integer tsFirstAckDelayReqWarning;

   	@Column(name = "ts_first_ack_delay_req_danger")
	private Integer tsFirstAckDelayReqDanger;

   	@Column(name = "ts_first_ack_delay_req_obstacle")
	private Integer tsFirstAckDelayReqObstacle;

   	@Column(name = "ts_first_ack_delay_res_use")
	private Boolean tsFirstAckDelayResUse;

   	@Column(name = "ts_first_ack_delay_res_caution")
	private Integer tsFirstAckDelayResCaution;

   	@Column(name = "ts_first_ack_delay_res_warning")
	private Integer tsFirstAckDelayResWarning;

   	@Column(name = "ts_first_ack_delay_res_danger")
	private Integer tsFirstAckDelayResDanger;

   	@Column(name = "ts_first_ack_delay_res_obstacle")
	private Integer tsFirstAckDelayResObstacle;

   	@Column(name = "ts_req_making_use")
	private Boolean tsReqMakingUse;

   	@Column(name = "ts_req_making_caution")
	private Integer tsReqMakingCaution;

   	@Column(name = "ts_req_making_warning")
	private Integer tsReqMakingWarning;

   	@Column(name = "ts_req_making_danger")
	private Integer tsReqMakingDanger;

   	@Column(name = "ts_req_making_obstacle")
	private Integer tsReqMakingObstacle;

   	@Column(name = "pkt_len_req_use")
	private Boolean pktLenReqUse;

   	@Column(name = "pkt_len_req_caution")
	private Integer pktLenReqCaution;

   	@Column(name = "pkt_len_req_warning")
	private Integer pktLenReqWarning;

   	@Column(name = "pkt_len_req_danger")
	private Integer pktLenReqDanger;

   	@Column(name = "pkt_len_req_obstacle")
	private Integer pktLenReqObstacle;

   	@Column(name = "pkt_len_res_use")
	private Boolean pktLenResUse;

   	@Column(name = "pkt_len_res_caution")
	private Integer pktLenResCaution;

   	@Column(name = "pkt_len_res_warning")
	private Integer pktLenResWarning;

   	@Column(name = "pkt_len_res_danger")
	private Integer pktLenResDanger;

   	@Column(name = "pkt_len_res_obstacle")
	private Integer pktLenResObstacle;

   	@Column(name = "pkt_cnt_req_use")
	private Boolean pktCntReqUse;

   	@Column(name = "pkt_cnt_req_caution")
	private Integer pktCntReqCaution;

   	@Column(name = "pkt_cnt_req_warning")
	private Integer pktCntReqWarning;

   	@Column(name = "pkt_cnt_req_danger")
	private Integer pktCntReqDanger;

   	@Column(name = "pkt_cnt_req_obstacle")
	private Integer pktCntReqObstacle;

   	@Column(name = "pkt_cnt_res_use")
	private Boolean pktCntResUse;

   	@Column(name = "pkt_cnt_res_caution")
	private Integer pktCntResCaution;

   	@Column(name = "pkt_cnt_res_warning")
	private Integer pktCntResWarning;

   	@Column(name = "pkt_cnt_res_danger")
	private Integer pktCntResDanger;

   	@Column(name = "pkt_cnt_res_obstacle")
	private Integer pktCntResObstacle;

   	@Column(name = "http_len_req_use")
	private Boolean httpLenReqUse;

   	@Column(name = "http_len_req_caution")
	private Integer httpLenReqCaution;

   	@Column(name = "http_len_req_warning")
	private Integer httpLenReqWarning;

   	@Column(name = "http_len_req_danger")
	private Integer httpLenReqDanger;

   	@Column(name = "http_len_req_obstacle")
	private Integer httpLenReqObstacle;

   	@Column(name = "http_len_res_use")
	private Boolean httpLenResUse;

   	@Column(name = "http_len_res_caution")
	private Integer httpLenResCaution;

   	@Column(name = "http_len_res_warning")
	private Integer httpLenResWarning;

   	@Column(name = "http_len_res_danger")
	private Integer httpLenResDanger;

   	@Column(name = "http_len_res_obstacle")
	private Integer httpLenResObstacle;

   	@Column(name = "http_cnt_req_use")
	private Boolean httpCntReqUse;

   	@Column(name = "http_cnt_req_caution")
	private Integer httpCntReqCaution;

   	@Column(name = "http_cnt_req_warning")
	private Integer httpCntReqWarning;

   	@Column(name = "http_cnt_req_danger")
	private Integer httpCntReqDanger;

   	@Column(name = "http_cnt_req_obstacle")
	private Integer httpCntReqObstacle;

   	@Column(name = "http_cnt_res_use")
	private Boolean httpCntResUse;

   	@Column(name = "http_cnt_res_caution")
	private Integer httpCntResCaution;

   	@Column(name = "http_cnt_res_warning")
	private Integer httpCntResWarning;

   	@Column(name = "http_cnt_res_danger")
	private Integer httpCntResDanger;

   	@Column(name = "http_cnt_res_obstacle")
	private Integer httpCntResObstacle;

   	@Column(name = "ack_rtt_sum_req_use")
	private Boolean ackRttSumReqUse;

   	@Column(name = "ack_rtt_sum_req_caution")
	private Integer ackRttSumReqCaution;

   	@Column(name = "ack_rtt_sum_req_warning")
	private Integer ackRttSumReqWarning;

   	@Column(name = "ack_rtt_sum_req_danger")
	private Integer ackRttSumReqDanger;

   	@Column(name = "ack_rtt_sum_req_obstacle")
	private Integer ackRttSumReqObstacle;

   	@Column(name = "ack_rtt_sum_res_use")
	private Boolean ackRttSumResUse;

   	@Column(name = "ack_rtt_sum_res_caution")
	private Integer ackRttSumResCaution;

   	@Column(name = "ack_rtt_sum_res_warning")
	private Integer ackRttSumResWarning;

   	@Column(name = "ack_rtt_sum_res_danger")
	private Integer ackRttSumResDanger;

   	@Column(name = "ack_rtt_sum_res_obstacle")
	private Integer ackRttSumResObstacle;

   	@Column(name = "ack_rto_sum_req_use")
	private Boolean ackRtoSumReqUse;

   	@Column(name = "ack_rto_sum_req_caution")
	private Integer ackRtoSumReqCaution;

   	@Column(name = "ack_rto_sum_req_warning")
	private Integer ackRtoSumReqWarning;

   	@Column(name = "ack_rto_sum_req_danger")
	private Integer ackRtoSumReqDanger;

   	@Column(name = "ack_rto_sum_req_obstacle")
	private Integer ackRtoSumReqObstacle;

   	@Column(name = "ack_rto_sum_res_use")
	private Boolean ackRtoSumResUse;

   	@Column(name = "ack_rto_sum_res_caution")
	private Integer ackRtoSumResCaution;

   	@Column(name = "ack_rto_sum_res_warning")
	private Integer ackRtoSumResWarning;

   	@Column(name = "ack_rto_sum_res_danger")
	private Integer ackRtoSumResDanger;

   	@Column(name = "ack_rto_sum_res_obstacle")
	private Integer ackRtoSumResObstacle;

   	@Column(name = "ack_rtt_cnt_req_use")
	private Boolean ackRttCntReqUse;

   	@Column(name = "ack_rtt_cnt_req_caution")
	private Integer ackRttCntReqCaution;

   	@Column(name = "ack_rtt_cnt_req_warning")
	private Integer ackRttCntReqWarning;

   	@Column(name = "ack_rtt_cnt_req_danger")
	private Integer ackRttCntReqDanger;

   	@Column(name = "ack_rtt_cnt_req_obstacle")
	private Integer ackRttCntReqObstacle;

   	@Column(name = "ack_rtt_cnt_res_use")
	private Boolean ackRttCntResUse;

   	@Column(name = "ack_rtt_cnt_res_caution")
	private Integer ackRttCntResCaution;

   	@Column(name = "ack_rtt_cnt_res_warning")
	private Integer ackRttCntResWarning;

   	@Column(name = "ack_rtt_cnt_res_danger")
	private Integer ackRttCntResDanger;

   	@Column(name = "ack_rtt_cnt_res_obstacle")
	private Integer ackRttCntResObstacle;

   	@Column(name = "ack_rto_cnt_req_use")
	private Boolean ackRtoCntReqUse;

   	@Column(name = "ack_rto_cnt_req_caution")
	private Integer ackRtoCntReqCaution;

   	@Column(name = "ack_rto_cnt_req_warning")
	private Integer ackRtoCntReqWarning;

   	@Column(name = "ack_rto_cnt_req_danger")
	private Integer ackRtoCntReqDanger;

   	@Column(name = "ack_rto_cnt_req_obstacle")
	private Integer ackRtoCntReqObstacle;

   	@Column(name = "ack_rto_cnt_res_use")
	private Boolean ackRtoCntResUse;

   	@Column(name = "ack_rto_cnt_res_caution")
	private Integer ackRtoCntResCaution;

   	@Column(name = "ack_rto_cnt_res_warning")
	private Integer ackRtoCntResWarning;

   	@Column(name = "ack_rto_cnt_res_danger")
	private Integer ackRtoCntResDanger;

   	@Column(name = "ack_rto_cnt_res_obstacle")
	private Integer ackRtoCntResObstacle;

   	@Column(name = "ack_delay_req_use")
	private Boolean ackDelayReqUse;

   	@Column(name = "ack_delay_req_caution")
	private Integer ackDelayReqCaution;

   	@Column(name = "ack_delay_req_warning")
	private Integer ackDelayReqWarning;

   	@Column(name = "ack_delay_req_danger")
	private Integer ackDelayReqDanger;

   	@Column(name = "ack_delay_req_obstacle")
	private Integer ackDelayReqObstacle;

   	@Column(name = "ack_delay_res_use")
	private Boolean ackDelayResUse;

   	@Column(name = "ack_delay_res_caution")
	private Integer ackDelayResCaution;

   	@Column(name = "ack_delay_res_warning")
	private Integer ackDelayResWarning;

   	@Column(name = "ack_delay_res_danger")
	private Integer ackDelayResDanger;

   	@Column(name = "ack_delay_res_obstacle")
	private Integer ackDelayResObstacle;

   	@Column(name = "retransmission_cnt_req_use")
	private Boolean retransmissionCntReqUse;

   	@Column(name = "retransmission_cnt_req_caution")
	private Integer retransmissionCntReqCaution;

   	@Column(name = "retransmission_cnt_req_warning")
	private Integer retransmissionCntReqWarning;

   	@Column(name = "retransmission_cnt_req_danger")
	private Integer retransmissionCntReqDanger;

   	@Column(name = "retransmission_cnt_req_obstacle")
	private Integer retransmissionCntReqObstacle;

   	@Column(name = "retransmission_cnt_res_use")
	private Boolean retransmissionCntResUse;

   	@Column(name = "retransmission_cnt_res_caution")
	private Integer retransmissionCntResCaution;

   	@Column(name = "retransmission_cnt_res_warning")
	private Integer retransmissionCntResWarning;

   	@Column(name = "retransmission_cnt_res_danger")
	private Integer retransmissionCntResDanger;

   	@Column(name = "retransmission_cnt_res_obstacle")
	private Integer retransmissionCntResObstacle;

   	@Column(name = "retransmission_len_req_use")
	private Boolean retransmissionLenReqUse;

   	@Column(name = "retransmission_len_req_caution")
	private Integer retransmissionLenReqCaution;

   	@Column(name = "retransmission_len_req_warning")
	private Integer retransmissionLenReqWarning;

   	@Column(name = "retransmission_len_req_danger")
	private Integer retransmissionLenReqDanger;

   	@Column(name = "retransmission_len_req_obstacle")
	private Integer retransmissionLenReqObstacle;

   	@Column(name = "retransmission_len_res_use")
	private Boolean retransmissionLenResUse;

   	@Column(name = "retransmission_len_res_caution")
	private Integer retransmissionLenResCaution;

   	@Column(name = "retransmission_len_res_warning")
	private Integer retransmissionLenResWarning;

   	@Column(name = "retransmission_len_res_danger")
	private Integer retransmissionLenResDanger;

   	@Column(name = "retransmission_len_res_obstacle")
	private Integer retransmissionLenResObstacle;

   	@Column(name = "fast_retransmission_cnt_req_use")
	private Boolean fastRetransmissionCntReqUse;

   	@Column(name = "fast_retransmission_cnt_req_caution")
	private Integer fastRetransmissionCntReqCaution;

   	@Column(name = "fast_retransmission_cnt_req_warning")
	private Integer fastRetransmissionCntReqWarning;

   	@Column(name = "fast_retransmission_cnt_req_danger")
	private Integer fastRetransmissionCntReqDanger;

   	@Column(name = "fast_retransmission_cnt_req_obstacle")
	private Integer fastRetransmissionCntReqObstacle;

   	@Column(name = "fast_retransmission_cnt_res_use")
	private Boolean fastRetransmissionCntResUse;

   	@Column(name = "fast_retransmission_cnt_res_caution")
	private Integer fastRetransmissionCntResCaution;

   	@Column(name = "fast_retransmission_cnt_res_warning")
	private Integer fastRetransmissionCntResWarning;

   	@Column(name = "fast_retransmission_cnt_res_danger")
	private Integer fastRetransmissionCntResDanger;

   	@Column(name = "fast_retransmission_cnt_res_obstacle")
	private Integer fastRetransmissionCntResObstacle;

   	@Column(name = "fast_retransmission_len_req_use")
	private Boolean fastRetransmissionLenReqUse;

   	@Column(name = "fast_retransmission_len_req_caution")
	private Integer fastRetransmissionLenReqCaution;

   	@Column(name = "fast_retransmission_len_req_warning")
	private Integer fastRetransmissionLenReqWarning;

   	@Column(name = "fast_retransmission_len_req_danger")
	private Integer fastRetransmissionLenReqDanger;

   	@Column(name = "fast_retransmission_len_req_obstacle")
	private Integer fastRetransmissionLenReqObstacle;

   	@Column(name = "fast_retransmission_len_res_use")
	private Boolean fastRetransmissionLenResUse;

   	@Column(name = "fast_retransmission_len_res_caution")
	private Integer fastRetransmissionLenResCaution;

   	@Column(name = "fast_retransmission_len_res_warning")
	private Integer fastRetransmissionLenResWarning;

   	@Column(name = "fast_retransmission_len_res_danger")
	private Integer fastRetransmissionLenResDanger;

   	@Column(name = "fast_retransmission_len_res_obstacle")
	private Integer fastRetransmissionLenResObstacle;

   	@Column(name = "out_of_order_cnt_req_use")
	private Boolean outOfOrderCntReqUse;

   	@Column(name = "out_of_order_cnt_req_caution")
	private Integer outOfOrderCntReqCaution;

   	@Column(name = "out_of_order_cnt_req_warning")
	private Integer outOfOrderCntReqWarning;

   	@Column(name = "out_of_order_cnt_req_danger")
	private Integer outOfOrderCntReqDanger;

   	@Column(name = "out_of_order_cnt_req_obstacle")
	private Integer outOfOrderCntReqObstacle;

   	@Column(name = "out_of_order_cnt_res_use")
	private Boolean outOfOrderCntResUse;

   	@Column(name = "out_of_order_cnt_res_caution")
	private Integer outOfOrderCntResCaution;

   	@Column(name = "out_of_order_cnt_res_warning")
	private Integer outOfOrderCntResWarning;

   	@Column(name = "out_of_order_cnt_res_danger")
	private Integer outOfOrderCntResDanger;

   	@Column(name = "out_of_order_cnt_res_obstacle")
	private Integer outOfOrderCntResObstacle;

   	@Column(name = "out_of_order_len_req_use")
	private Boolean outOfOrderLenReqUse;

   	@Column(name = "out_of_order_len_req_caution")
	private Integer outOfOrderLenReqCaution;

   	@Column(name = "out_of_order_len_req_warning")
	private Integer outOfOrderLenReqWarning;

   	@Column(name = "out_of_order_len_req_danger")
	private Integer outOfOrderLenReqDanger;

   	@Column(name = "out_of_order_len_req_obstacle")
	private Integer outOfOrderLenReqObstacle;

   	@Column(name = "out_of_order_len_res_use")
	private Boolean outOfOrderLenResUse;

   	@Column(name = "out_of_order_len_res_caution")
	private Integer outOfOrderLenResCaution;

   	@Column(name = "out_of_order_len_res_warning")
	private Integer outOfOrderLenResWarning;

   	@Column(name = "out_of_order_len_res_danger")
	private Integer outOfOrderLenResDanger;

   	@Column(name = "out_of_order_len_res_obstacle")
	private Integer outOfOrderLenResObstacle;

   	@Column(name = "lost_seg_cnt_req_use")
	private Boolean lostSegCntReqUse;

   	@Column(name = "lost_seg_cnt_req_caution")
	private Integer lostSegCntReqCaution;

   	@Column(name = "lost_seg_cnt_req_warning")
	private Integer lostSegCntReqWarning;

   	@Column(name = "lost_seg_cnt_req_danger")
	private Integer lostSegCntReqDanger;

   	@Column(name = "lost_seg_cnt_req_obstacle")
	private Integer lostSegCntReqObstacle;

   	@Column(name = "lost_seg_cnt_res_use")
	private Boolean lostSegCntResUse;

   	@Column(name = "lost_seg_cnt_res_caution")
	private Integer lostSegCntResCaution;

   	@Column(name = "lost_seg_cnt_res_warning")
	private Integer lostSegCntResWarning;

   	@Column(name = "lost_seg_cnt_res_danger")
	private Integer lostSegCntResDanger;

   	@Column(name = "lost_seg_cnt_res_obstacle")
	private Integer lostSegCntResObstacle;

   	@Column(name = "lost_seg_len_req_use")
	private Boolean lostSegLenReqUse;

   	@Column(name = "lost_seg_len_req_caution")
	private Integer lostSegLenReqCaution;

   	@Column(name = "lost_seg_len_req_warning")
	private Integer lostSegLenReqWarning;

   	@Column(name = "lost_seg_len_req_danger")
	private Integer lostSegLenReqDanger;

   	@Column(name = "lost_seg_len_req_obstacle")
	private Integer lostSegLenReqObstacle;

   	@Column(name = "lost_seg_len_res_use")
	private Boolean lostSegLenResUse;

   	@Column(name = "lost_seg_len_res_caution")
	private Integer lostSegLenResCaution;

   	@Column(name = "lost_seg_len_res_warning")
	private Integer lostSegLenResWarning;

   	@Column(name = "lost_seg_len_res_danger")
	private Integer lostSegLenResDanger;

   	@Column(name = "lost_seg_len_res_obstacle")
	private Integer lostSegLenResObstacle;

   	@Column(name = "ack_lost_cnt_req_use")
	private Boolean ackLostCntReqUse;

   	@Column(name = "ack_lost_cnt_req_caution")
	private Integer ackLostCntReqCaution;

   	@Column(name = "ack_lost_cnt_req_warning")
	private Integer ackLostCntReqWarning;

   	@Column(name = "ack_lost_cnt_req_danger")
	private Integer ackLostCntReqDanger;

   	@Column(name = "ack_lost_cnt_req_obstacle")
	private Integer ackLostCntReqObstacle;

   	@Column(name = "ack_lost_cnt_res_use")
	private Boolean ackLostCntResUse;

   	@Column(name = "ack_lost_cnt_res_caution")
	private Integer ackLostCntResCaution;

   	@Column(name = "ack_lost_cnt_res_warning")
	private Integer ackLostCntResWarning;

   	@Column(name = "ack_lost_cnt_res_danger")
	private Integer ackLostCntResDanger;

   	@Column(name = "ack_lost_cnt_res_obstacle")
	private Integer ackLostCntResObstacle;

   	@Column(name = "ack_lost_len_req_use")
	private Boolean ackLostLenReqUse;

   	@Column(name = "ack_lost_len_req_caution")
	private Integer ackLostLenReqCaution;

   	@Column(name = "ack_lost_len_req_warning")
	private Integer ackLostLenReqWarning;

   	@Column(name = "ack_lost_len_req_danger")
	private Integer ackLostLenReqDanger;

   	@Column(name = "ack_lost_len_req_obstacle")
	private Integer ackLostLenReqObstacle;

   	@Column(name = "ack_lost_len_res_use")
	private Boolean ackLostLenResUse;

   	@Column(name = "ack_lost_len_res_caution")
	private Integer ackLostLenResCaution;

   	@Column(name = "ack_lost_len_res_warning")
	private Integer ackLostLenResWarning;

   	@Column(name = "ack_lost_len_res_danger")
	private Integer ackLostLenResDanger;

   	@Column(name = "ack_lost_len_res_obstacle")
	private Integer ackLostLenResObstacle;

   	@Column(name = "win_update_cnt_req_use")
	private Boolean winUpdateCntReqUse;

   	@Column(name = "win_update_cnt_req_caution")
	private Integer winUpdateCntReqCaution;

   	@Column(name = "win_update_cnt_req_warning")
	private Integer winUpdateCntReqWarning;

   	@Column(name = "win_update_cnt_req_danger")
	private Integer winUpdateCntReqDanger;

   	@Column(name = "win_update_cnt_req_obstacle")
	private Integer winUpdateCntReqObstacle;

   	@Column(name = "win_update_cnt_res_use")
	private Boolean winUpdateCntResUse;

   	@Column(name = "win_update_cnt_res_caution")
	private Integer winUpdateCntResCaution;

   	@Column(name = "win_update_cnt_res_warning")
	private Integer winUpdateCntResWarning;

   	@Column(name = "win_update_cnt_res_danger")
	private Integer winUpdateCntResDanger;

   	@Column(name = "win_update_cnt_res_obstacle")
	private Integer winUpdateCntResObstacle;

   	@Column(name = "win_update_len_req_use")
	private Boolean winUpdateLenReqUse;

   	@Column(name = "win_update_len_req_caution")
	private Integer winUpdateLenReqCaution;

   	@Column(name = "win_update_len_req_warning")
	private Integer winUpdateLenReqWarning;

   	@Column(name = "win_update_len_req_danger")
	private Integer winUpdateLenReqDanger;

   	@Column(name = "win_update_len_req_obstacle")
	private Integer winUpdateLenReqObstacle;

   	@Column(name = "win_update_len_res_use")
	private Boolean winUpdateLenResUse;

   	@Column(name = "win_update_len_res_caution")
	private Integer winUpdateLenResCaution;

   	@Column(name = "win_update_len_res_warning")
	private Integer winUpdateLenResWarning;

   	@Column(name = "win_update_len_res_danger")
	private Integer winUpdateLenResDanger;

   	@Column(name = "win_update_len_res_obstacle")
	private Integer winUpdateLenResObstacle;

   	@Column(name = "dup_ack_cnt_req_use")
	private Boolean dupAckCntReqUse;

   	@Column(name = "dup_ack_cnt_req_caution")
	private Integer dupAckCntReqCaution;

   	@Column(name = "dup_ack_cnt_req_warning")
	private Integer dupAckCntReqWarning;

   	@Column(name = "dup_ack_cnt_req_danger")
	private Integer dupAckCntReqDanger;

   	@Column(name = "dup_ack_cnt_req_obstacle")
	private Integer dupAckCntReqObstacle;

   	@Column(name = "dup_ack_cnt_res_use")
	private Boolean dupAckCntResUse;

   	@Column(name = "dup_ack_cnt_res_caution")
	private Integer dupAckCntResCaution;

   	@Column(name = "dup_ack_cnt_res_warning")
	private Integer dupAckCntResWarning;

   	@Column(name = "dup_ack_cnt_res_danger")
	private Integer dupAckCntResDanger;

   	@Column(name = "dup_ack_cnt_res_obstacle")
	private Integer dupAckCntResObstacle;

   	@Column(name = "dup_ack_len_req_use")
	private Boolean dupAckLenReqUse;

   	@Column(name = "dup_ack_len_req_caution")
	private Integer dupAckLenReqCaution;

   	@Column(name = "dup_ack_len_req_warning")
	private Integer dupAckLenReqWarning;

   	@Column(name = "dup_ack_len_req_danger")
	private Integer dupAckLenReqDanger;

   	@Column(name = "dup_ack_len_req_obstacle")
	private Integer dupAckLenReqObstacle;

   	@Column(name = "dup_ack_len_res_use")
	private Boolean dupAckLenResUse;

   	@Column(name = "dup_ack_len_res_caution")
	private Integer dupAckLenResCaution;

   	@Column(name = "dup_ack_len_res_warning")
	private Integer dupAckLenResWarning;

   	@Column(name = "dup_ack_len_res_danger")
	private Integer dupAckLenResDanger;

   	@Column(name = "dup_ack_len_res_obstacle")
	private Integer dupAckLenResObstacle;

   	@Column(name = "zero_win_cnt_req_use")
	private Boolean zeroWinCntReqUse;

   	@Column(name = "zero_win_cnt_req_caution")
	private Integer zeroWinCntReqCaution;

   	@Column(name = "zero_win_cnt_req_warning")
	private Integer zeroWinCntReqWarning;

   	@Column(name = "zero_win_cnt_req_danger")
	private Integer zeroWinCntReqDanger;

   	@Column(name = "zero_win_cnt_req_obstacle")
	private Integer zeroWinCntReqObstacle;

   	@Column(name = "zero_win_cnt_res_use")
	private Boolean zeroWinCntResUse;

   	@Column(name = "zero_win_cnt_res_caution")
	private Integer zeroWinCntResCaution;

   	@Column(name = "zero_win_cnt_res_warning")
	private Integer zeroWinCntResWarning;

   	@Column(name = "zero_win_cnt_res_danger")
	private Integer zeroWinCntResDanger;

   	@Column(name = "zero_win_cnt_res_obstacle")
	private Integer zeroWinCntResObstacle;

   	@Column(name = "zero_win_len_req_use")
	private Boolean zeroWinLenReqUse;

   	@Column(name = "zero_win_len_req_caution")
	private Integer zeroWinLenReqCaution;

   	@Column(name = "zero_win_len_req_warning")
	private Integer zeroWinLenReqWarning;

   	@Column(name = "zero_win_len_req_danger")
	private Integer zeroWinLenReqDanger;

   	@Column(name = "zero_win_len_req_obstacle")
	private Integer zeroWinLenReqObstacle;

   	@Column(name = "zero_win_len_res_use")
	private Boolean zeroWinLenResUse;

   	@Column(name = "zero_win_len_res_caution")
	private Integer zeroWinLenResCaution;

   	@Column(name = "zero_win_len_res_warning")
	private Integer zeroWinLenResWarning;

   	@Column(name = "zero_win_len_res_danger")
	private Integer zeroWinLenResDanger;

   	@Column(name = "zero_win_len_res_obstacle")
	private Integer zeroWinLenResObstacle;

   	@Column(name = "spurious_retransmission_cnt_req_use")
	private Boolean spuriousRetransmissionCntReqUse;

   	@Column(name = "spurious_retransmission_cnt_req_caution")
	private Integer spuriousRetransmissionCntReqCaution;

   	@Column(name = "spurious_retransmission_cnt_req_warning")
	private Integer spuriousRetransmissionCntReqWarning;

   	@Column(name = "spurious_retransmission_cnt_req_danger")
	private Integer spuriousRetransmissionCntReqDanger;

   	@Column(name = "spurious_retransmission_cnt_req_obstacle")
	private Integer spuriousRetransmissionCntReqObstacle;

   	@Column(name = "spurious_retransmission_cnt_res_use")
	private Boolean spuriousRetransmissionCntResUse;

   	@Column(name = "spurious_retransmission_cnt_res_caution")
	private Integer spuriousRetransmissionCntResCaution;

   	@Column(name = "spurious_retransmission_cnt_res_warning")
	private Integer spuriousRetransmissionCntResWarning;

   	@Column(name = "spurious_retransmission_cnt_res_danger")
	private Integer spuriousRetransmissionCntResDanger;

   	@Column(name = "spurious_retransmission_cnt_res_obstacle")
	private Integer spuriousRetransmissionCntResObstacle;

   	@Column(name = "spurious_retransmission_len_req_use")
	private Boolean spuriousRetransmissionLenReqUse;

   	@Column(name = "spurious_retransmission_len_req_caution")
	private Integer spuriousRetransmissionLenReqCaution;

   	@Column(name = "spurious_retransmission_len_req_warning")
	private Integer spuriousRetransmissionLenReqWarning;

   	@Column(name = "spurious_retransmission_len_req_danger")
	private Integer spuriousRetransmissionLenReqDanger;

   	@Column(name = "spurious_retransmission_len_req_obstacle")
	private Integer spuriousRetransmissionLenReqObstacle;

   	@Column(name = "spurious_retransmission_len_res_use")
	private Boolean spuriousRetransmissionLenResUse;

   	@Column(name = "spurious_retransmission_len_res_caution")
	private Integer spuriousRetransmissionLenResCaution;

   	@Column(name = "spurious_retransmission_len_res_warning")
	private Integer spuriousRetransmissionLenResWarning;

   	@Column(name = "spurious_retransmission_len_res_danger")
	private Integer spuriousRetransmissionLenResDanger;

   	@Column(name = "spurious_retransmission_len_res_obstacle")
	private Integer spuriousRetransmissionLenResObstacle;

   	@Column(name = "overlap_cnt_req_use")
	private Boolean overlapCntReqUse;

   	@Column(name = "overlap_cnt_req_caution")
	private Integer overlapCntReqCaution;

   	@Column(name = "overlap_cnt_req_warning")
	private Integer overlapCntReqWarning;

   	@Column(name = "overlap_cnt_req_danger")
	private Integer overlapCntReqDanger;

   	@Column(name = "overlap_cnt_req_obstacle")
	private Integer overlapCntReqObstacle;

   	@Column(name = "overlap_cnt_res_use")
	private Boolean overlapCntResUse;

   	@Column(name = "overlap_cnt_res_caution")
	private Integer overlapCntResCaution;

   	@Column(name = "overlap_cnt_res_warning")
	private Integer overlapCntResWarning;

   	@Column(name = "overlap_cnt_res_danger")
	private Integer overlapCntResDanger;

   	@Column(name = "overlap_cnt_res_obstacle")
	private Integer overlapCntResObstacle;

   	@Column(name = "overlap_len_req_use")
	private Boolean overlapLenReqUse;

   	@Column(name = "overlap_len_req_caution")
	private Integer overlapLenReqCaution;

   	@Column(name = "overlap_len_req_warning")
	private Integer overlapLenReqWarning;

   	@Column(name = "overlap_len_req_danger")
	private Integer overlapLenReqDanger;

   	@Column(name = "overlap_len_req_obstacle")
	private Integer overlapLenReqObstacle;

   	@Column(name = "overlap_len_res_use")
	private Boolean overlapLenResUse;

   	@Column(name = "overlap_len_res_caution")
	private Integer overlapLenResCaution;

   	@Column(name = "overlap_len_res_warning")
	private Integer overlapLenResWarning;

   	@Column(name = "overlap_len_res_danger")
	private Integer overlapLenResDanger;

   	@Column(name = "overlap_len_res_obstacle")
	private Integer overlapLenResObstacle;

   	@Column(name = "overlap_attack_cnt_req_use")
	private Boolean overlapAttackCntReqUse;

   	@Column(name = "overlap_attack_cnt_req_caution")
	private Integer overlapAttackCntReqCaution;

   	@Column(name = "overlap_attack_cnt_req_warning")
	private Integer overlapAttackCntReqWarning;

   	@Column(name = "overlap_attack_cnt_req_danger")
	private Integer overlapAttackCntReqDanger;

   	@Column(name = "overlap_attack_cnt_req_obstacle")
	private Integer overlapAttackCntReqObstacle;

   	@Column(name = "overlap_attack_cnt_res_use")
	private Boolean overlapAttackCntResUse;

   	@Column(name = "overlap_attack_cnt_res_caution")
	private Integer overlapAttackCntResCaution;

   	@Column(name = "overlap_attack_cnt_res_warning")
	private Integer overlapAttackCntResWarning;

   	@Column(name = "overlap_attack_cnt_res_danger")
	private Integer overlapAttackCntResDanger;

   	@Column(name = "overlap_attack_cnt_res_obstacle")
	private Integer overlapAttackCntResObstacle;

   	@Column(name = "overlap_attack_len_req_use")
	private Boolean overlapAttackLenReqUse;

   	@Column(name = "overlap_attack_len_req_caution")
	private Integer overlapAttackLenReqCaution;

   	@Column(name = "overlap_attack_len_req_warning")
	private Integer overlapAttackLenReqWarning;

   	@Column(name = "overlap_attack_len_req_danger")
	private Integer overlapAttackLenReqDanger;

   	@Column(name = "overlap_attack_len_req_obstacle")
	private Integer overlapAttackLenReqObstacle;

   	@Column(name = "overlap_attack_len_res_use")
	private Boolean overlapAttackLenResUse;

   	@Column(name = "overlap_attack_len_res_caution")
	private Integer overlapAttackLenResCaution;

   	@Column(name = "overlap_attack_len_res_warning")
	private Integer overlapAttackLenResWarning;

   	@Column(name = "overlap_attack_len_res_danger")
	private Integer overlapAttackLenResDanger;

   	@Column(name = "overlap_attack_len_res_obstacle")
	private Integer overlapAttackLenResObstacle;

   	@Column(name = "zero_win_probe_cnt_req_use")
	private Boolean zeroWinProbeCntReqUse;

   	@Column(name = "zero_win_probe_cnt_req_caution")
	private Integer zeroWinProbeCntReqCaution;

   	@Column(name = "zero_win_probe_cnt_req_warning")
	private Integer zeroWinProbeCntReqWarning;

   	@Column(name = "zero_win_probe_cnt_req_danger")
	private Integer zeroWinProbeCntReqDanger;

   	@Column(name = "zero_win_probe_cnt_req_obstacle")
	private Integer zeroWinProbeCntReqObstacle;

   	@Column(name = "zero_win_probe_cnt_res_use")
	private Boolean zeroWinProbeCntResUse;

   	@Column(name = "zero_win_probe_cnt_res_caution")
	private Integer zeroWinProbeCntResCaution;

   	@Column(name = "zero_win_probe_cnt_res_warning")
	private Integer zeroWinProbeCntResWarning;

   	@Column(name = "zero_win_probe_cnt_res_danger")
	private Integer zeroWinProbeCntResDanger;

   	@Column(name = "zero_win_probe_cnt_res_obstacle")
	private Integer zeroWinProbeCntResObstacle;

   	@Column(name = "zero_win_probe_len_req_use")
	private Boolean zeroWinProbeLenReqUse;

   	@Column(name = "zero_win_probe_len_req_caution")
	private Integer zeroWinProbeLenReqCaution;

   	@Column(name = "zero_win_probe_len_req_warning")
	private Integer zeroWinProbeLenReqWarning;

   	@Column(name = "zero_win_probe_len_req_danger")
	private Integer zeroWinProbeLenReqDanger;

   	@Column(name = "zero_win_probe_len_req_obstacle")
	private Integer zeroWinProbeLenReqObstacle;

   	@Column(name = "zero_win_probe_len_res_use")
	private Boolean zeroWinProbeLenResUse;

   	@Column(name = "zero_win_probe_len_res_caution")
	private Integer zeroWinProbeLenResCaution;

   	@Column(name = "zero_win_probe_len_res_warning")
	private Integer zeroWinProbeLenResWarning;

   	@Column(name = "zero_win_probe_len_res_danger")
	private Integer zeroWinProbeLenResDanger;

   	@Column(name = "zero_win_probe_len_res_obstacle")
	private Integer zeroWinProbeLenResObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_req_use")
	private Boolean zeroWinProbeAckCntReqUse;

   	@Column(name = "zero_win_probe_ack_cnt_req_caution")
	private Integer zeroWinProbeAckCntReqCaution;

   	@Column(name = "zero_win_probe_ack_cnt_req_warning")
	private Integer zeroWinProbeAckCntReqWarning;

   	@Column(name = "zero_win_probe_ack_cnt_req_danger")
	private Integer zeroWinProbeAckCntReqDanger;

   	@Column(name = "zero_win_probe_ack_cnt_req_obstacle")
	private Integer zeroWinProbeAckCntReqObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_res_use")
	private Boolean zeroWinProbeAckCntResUse;

   	@Column(name = "zero_win_probe_ack_cnt_res_caution")
	private Integer zeroWinProbeAckCntResCaution;

   	@Column(name = "zero_win_probe_ack_cnt_res_warning")
	private Integer zeroWinProbeAckCntResWarning;

   	@Column(name = "zero_win_probe_ack_cnt_res_danger")
	private Integer zeroWinProbeAckCntResDanger;

   	@Column(name = "zero_win_probe_ack_cnt_res_obstacle")
	private Integer zeroWinProbeAckCntResObstacle;

   	@Column(name = "zero_win_probe_ack_len_req_use")
	private Boolean zeroWinProbeAckLenReqUse;

   	@Column(name = "zero_win_probe_ack_len_req_caution")
	private Integer zeroWinProbeAckLenReqCaution;

   	@Column(name = "zero_win_probe_ack_len_req_warning")
	private Integer zeroWinProbeAckLenReqWarning;

   	@Column(name = "zero_win_probe_ack_len_req_danger")
	private Integer zeroWinProbeAckLenReqDanger;

   	@Column(name = "zero_win_probe_ack_len_req_obstacle")
	private Integer zeroWinProbeAckLenReqObstacle;

   	@Column(name = "zero_win_probe_ack_len_res_use")
	private Boolean zeroWinProbeAckLenResUse;

   	@Column(name = "zero_win_probe_ack_len_res_caution")
	private Integer zeroWinProbeAckLenResCaution;

   	@Column(name = "zero_win_probe_ack_len_res_warning")
	private Integer zeroWinProbeAckLenResWarning;

   	@Column(name = "zero_win_probe_ack_len_res_danger")
	private Integer zeroWinProbeAckLenResDanger;

   	@Column(name = "zero_win_probe_ack_len_res_obstacle")
	private Integer zeroWinProbeAckLenResObstacle;

   	@Column(name = "keep_alive_cnt_req_use")
	private Boolean keepAliveCntReqUse;

   	@Column(name = "keep_alive_cnt_req_caution")
	private Integer keepAliveCntReqCaution;

   	@Column(name = "keep_alive_cnt_req_warning")
	private Integer keepAliveCntReqWarning;

   	@Column(name = "keep_alive_cnt_req_danger")
	private Integer keepAliveCntReqDanger;

   	@Column(name = "keep_alive_cnt_req_obstacle")
	private Integer keepAliveCntReqObstacle;

   	@Column(name = "keep_alive_cnt_res_use")
	private Boolean keepAliveCntResUse;

   	@Column(name = "keep_alive_cnt_res_caution")
	private Integer keepAliveCntResCaution;

   	@Column(name = "keep_alive_cnt_res_warning")
	private Integer keepAliveCntResWarning;

   	@Column(name = "keep_alive_cnt_res_danger")
	private Integer keepAliveCntResDanger;

   	@Column(name = "keep_alive_cnt_res_obstacle")
	private Integer keepAliveCntResObstacle;

   	@Column(name = "keep_alive_len_req_use")
	private Boolean keepAliveLenReqUse;

   	@Column(name = "keep_alive_len_req_caution")
	private Integer keepAliveLenReqCaution;

   	@Column(name = "keep_alive_len_req_warning")
	private Integer keepAliveLenReqWarning;

   	@Column(name = "keep_alive_len_req_danger")
	private Integer keepAliveLenReqDanger;

   	@Column(name = "keep_alive_len_req_obstacle")
	private Integer keepAliveLenReqObstacle;

   	@Column(name = "keep_alive_len_res_use")
	private Boolean keepAliveLenResUse;

   	@Column(name = "keep_alive_len_res_caution")
	private Integer keepAliveLenResCaution;

   	@Column(name = "keep_alive_len_res_warning")
	private Integer keepAliveLenResWarning;

   	@Column(name = "keep_alive_len_res_danger")
	private Integer keepAliveLenResDanger;

   	@Column(name = "keep_alive_len_res_obstacle")
	private Integer keepAliveLenResObstacle;

   	@Column(name = "keep_alive_ack_cnt_req_use")
	private Boolean keepAliveAckCntReqUse;

   	@Column(name = "keep_alive_ack_cnt_req_caution")
	private Integer keepAliveAckCntReqCaution;

   	@Column(name = "keep_alive_ack_cnt_req_warning")
	private Integer keepAliveAckCntReqWarning;

   	@Column(name = "keep_alive_ack_cnt_req_danger")
	private Integer keepAliveAckCntReqDanger;

   	@Column(name = "keep_alive_ack_cnt_req_obstacle")
	private Integer keepAliveAckCntReqObstacle;

   	@Column(name = "keep_alive_ack_cnt_res_use")
	private Boolean keepAliveAckCntResUse;

   	@Column(name = "keep_alive_ack_cnt_res_caution")
	private Integer keepAliveAckCntResCaution;

   	@Column(name = "keep_alive_ack_cnt_res_warning")
	private Integer keepAliveAckCntResWarning;

   	@Column(name = "keep_alive_ack_cnt_res_danger")
	private Integer keepAliveAckCntResDanger;

   	@Column(name = "keep_alive_ack_cnt_res_obstacle")
	private Integer keepAliveAckCntResObstacle;

   	@Column(name = "keep_alive_ack_len_req_use")
	private Boolean keepAliveAckLenReqUse;

   	@Column(name = "keep_alive_ack_len_req_caution")
	private Integer keepAliveAckLenReqCaution;

   	@Column(name = "keep_alive_ack_len_req_warning")
	private Integer keepAliveAckLenReqWarning;

   	@Column(name = "keep_alive_ack_len_req_danger")
	private Integer keepAliveAckLenReqDanger;

   	@Column(name = "keep_alive_ack_len_req_obstacle")
	private Integer keepAliveAckLenReqObstacle;

   	@Column(name = "keep_alive_ack_len_res_use")
	private Boolean keepAliveAckLenResUse;

   	@Column(name = "keep_alive_ack_len_res_caution")
	private Integer keepAliveAckLenResCaution;

   	@Column(name = "keep_alive_ack_len_res_warning")
	private Integer keepAliveAckLenResWarning;

   	@Column(name = "keep_alive_ack_len_res_danger")
	private Integer keepAliveAckLenResDanger;

   	@Column(name = "keep_alive_ack_len_res_obstacle")
	private Integer keepAliveAckLenResObstacle;

   	@Column(name = "ack_rtt_min_req_use")
	private Boolean ackRttMinReqUse;

   	@Column(name = "ack_rtt_min_req_caution")
	private Integer ackRttMinReqCaution;

   	@Column(name = "ack_rtt_min_req_warning")
	private Integer ackRttMinReqWarning;

   	@Column(name = "ack_rtt_min_req_danger")
	private Integer ackRttMinReqDanger;

   	@Column(name = "ack_rtt_min_req_obstacle")
	private Integer ackRttMinReqObstacle;

   	@Column(name = "ack_rtt_min_res_use")
	private Boolean ackRttMinResUse;

   	@Column(name = "ack_rtt_min_res_caution")
	private Integer ackRttMinResCaution;

   	@Column(name = "ack_rtt_min_res_warning")
	private Integer ackRttMinResWarning;

   	@Column(name = "ack_rtt_min_res_danger")
	private Integer ackRttMinResDanger;

   	@Column(name = "ack_rtt_min_res_obstacle")
	private Integer ackRttMinResObstacle;

   	@Column(name = "ack_rtt_max_req_use")
	private Boolean ackRttMaxReqUse;

   	@Column(name = "ack_rtt_max_req_caution")
	private Integer ackRttMaxReqCaution;

   	@Column(name = "ack_rtt_max_req_warning")
	private Integer ackRttMaxReqWarning;

   	@Column(name = "ack_rtt_max_req_danger")
	private Integer ackRttMaxReqDanger;

   	@Column(name = "ack_rtt_max_req_obstacle")
	private Integer ackRttMaxReqObstacle;

   	@Column(name = "ack_rtt_max_res_use")
	private Boolean ackRttMaxResUse;

   	@Column(name = "ack_rtt_max_res_caution")
	private Integer ackRttMaxResCaution;

   	@Column(name = "ack_rtt_max_res_warning")
	private Integer ackRttMaxResWarning;

   	@Column(name = "ack_rtt_max_res_danger")
	private Integer ackRttMaxResDanger;

   	@Column(name = "ack_rtt_max_res_obstacle")
	private Integer ackRttMaxResObstacle;

   	@Column(name = "tcp_len_req_use")
	private Boolean tcpLenReqUse;

   	@Column(name = "tcp_len_req_caution")
	private Integer tcpLenReqCaution;

   	@Column(name = "tcp_len_req_warning")
	private Integer tcpLenReqWarning;

   	@Column(name = "tcp_len_req_danger")
	private Integer tcpLenReqDanger;

   	@Column(name = "tcp_len_req_obstacle")
	private Integer tcpLenReqObstacle;

   	@Column(name = "tcp_len_res_use")
	private Boolean tcpLenResUse;

   	@Column(name = "tcp_len_res_caution")
	private Integer tcpLenResCaution;

   	@Column(name = "tcp_len_res_warning")
	private Integer tcpLenResWarning;

   	@Column(name = "tcp_len_res_danger")
	private Integer tcpLenResDanger;

   	@Column(name = "tcp_len_res_obstacle")
	private Integer tcpLenResObstacle;

   	@Column(name = "tcp_cnt_req_use")
	private Boolean tcpCntReqUse;

   	@Column(name = "tcp_cnt_req_caution")
	private Integer tcpCntReqCaution;

   	@Column(name = "tcp_cnt_req_warning")
	private Integer tcpCntReqWarning;

   	@Column(name = "tcp_cnt_req_danger")
	private Integer tcpCntReqDanger;

   	@Column(name = "tcp_cnt_req_obstacle")
	private Integer tcpCntReqObstacle;

   	@Column(name = "tcp_cnt_res_use")
	private Boolean tcpCntResUse;

   	@Column(name = "tcp_cnt_res_caution")
	private Integer tcpCntResCaution;

   	@Column(name = "tcp_cnt_res_warning")
	private Integer tcpCntResWarning;

   	@Column(name = "tcp_cnt_res_danger")
	private Integer tcpCntResDanger;

   	@Column(name = "tcp_cnt_res_obstacle")
	private Integer tcpCntResObstacle;


}
