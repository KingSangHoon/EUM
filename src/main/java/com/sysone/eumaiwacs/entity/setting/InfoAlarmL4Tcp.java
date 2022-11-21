package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table (name = "tbl_info_alarm_l4_tcp")
public class InfoAlarmL4Tcp {

	@Id
	@SequenceGenerator(name="tbl_info_alarm_l4_tcp_seq", sequenceName="tbl_info_alarm_l4_tcp_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_alarm_l4_tcp_seq")
   	@Column(name = "l4_tcp_id")
	private Integer l4TcpId;

   	@Column(name = "type")
	private Integer type;

   	@Column(name = "deleted")
	private Boolean deleted;

   	@Column(name = "reg_date")
	private LocalDateTime regDate;

   	@Column(name = "modify_date")
	private LocalDateTime modifyDate;

   	@Column(name = "first_writer")
	private String firstWriter;

   	@Column(name = "last_writer")
	private String lastWriter;

   	@Column(name = "policy_name")
	private String policyName;

   	@Column(name = "is_tcp_error")
	private Boolean isTcpError;

   	@Column(name = "len_pdu_req_tot_use")
	private Boolean lenPduReqTotUse;

   	@Column(name = "len_pdu_req_tot_caution")
	private Integer lenPduReqTotCaution;

   	@Column(name = "len_pdu_req_tot_warning")
	private Integer lenPduReqTotWarning;

   	@Column(name = "len_pdu_req_tot_danger")
	private Integer lenPduReqTotDanger;

   	@Column(name = "len_pdu_req_tot_obstacle")
	private Integer lenPduReqTotObstacle;

   	@Column(name = "len_pdu_res_tot_use")
	private Boolean lenPduResTotUse;

   	@Column(name = "len_pdu_res_tot_caution")
	private Integer lenPduResTotCaution;

   	@Column(name = "len_pdu_res_tot_warning")
	private Integer lenPduResTotWarning;

   	@Column(name = "len_pdu_res_tot_danger")
	private Integer lenPduResTotDanger;

   	@Column(name = "len_pdu_res_tot_obstacle")
	private Integer lenPduResTotObstacle;

   	@Column(name = "pkts_pdu_req_tot_use")
	private Boolean pktsPduReqTotUse;

   	@Column(name = "pkts_pdu_req_tot_caution")
	private Integer pktsPduReqTotCaution;

   	@Column(name = "pkts_pdu_req_tot_warning")
	private Integer pktsPduReqTotWarning;

   	@Column(name = "pkts_pdu_req_tot_danger")
	private Integer pktsPduReqTotDanger;

   	@Column(name = "pkts_pdu_req_tot_obstacle")
	private Integer pktsPduReqTotObstacle;

   	@Column(name = "pkts_pdu_res_tot_use")
	private Boolean pktsPduResTotUse;

   	@Column(name = "pkts_pdu_res_tot_caution")
	private Integer pktsPduResTotCaution;

   	@Column(name = "pkts_pdu_res_tot_warning")
	private Integer pktsPduResTotWarning;

   	@Column(name = "pkts_pdu_res_tot_danger")
	private Integer pktsPduResTotDanger;

   	@Column(name = "pkts_pdu_res_tot_obstacle")
	private Integer pktsPduResTotObstacle;

   	@Column(name = "len_pdu_req_per_sec_use")
	private Boolean lenPduReqPerSecUse;

   	@Column(name = "len_pdu_req_per_sec_caution")
	private Integer lenPduReqPerSecCaution;

   	@Column(name = "len_pdu_req_per_sec_warning")
	private Integer lenPduReqPerSecWarning;

   	@Column(name = "len_pdu_req_per_sec_danger")
	private Integer lenPduReqPerSecDanger;

   	@Column(name = "len_pdu_req_per_sec_obstacle")
	private Integer lenPduReqPerSecObstacle;

   	@Column(name = "len_pdu_res_per_sec_use")
	private Boolean lenPduResPerSecUse;

   	@Column(name = "len_pdu_res_per_sec_caution")
	private Integer lenPduResPerSecCaution;

   	@Column(name = "len_pdu_res_per_sec_warning")
	private Integer lenPduResPerSecWarning;

   	@Column(name = "len_pdu_res_per_sec_danger")
	private Integer lenPduResPerSecDanger;

   	@Column(name = "len_pdu_res_per_sec_obstacle")
	private Integer lenPduResPerSecObstacle;

   	@Column(name = "pkts_pdu_req_per_sec_use")
	private Boolean pktsPduReqPerSecUse;

   	@Column(name = "pkts_pdu_req_per_sec_caution")
	private Integer pktsPduReqPerSecCaution;

   	@Column(name = "pkts_pdu_req_per_sec_warning")
	private Integer pktsPduReqPerSecWarning;

   	@Column(name = "pkts_pdu_req_per_sec_danger")
	private Integer pktsPduReqPerSecDanger;

   	@Column(name = "pkts_pdu_req_per_sec_obstacle")
	private Integer pktsPduReqPerSecObstacle;

   	@Column(name = "pkts_pdu_res_per_sec_use")
	private Boolean pktsPduResPerSecUse;

   	@Column(name = "pkts_pdu_res_per_sec_caution")
	private Integer pktsPduResPerSecCaution;

   	@Column(name = "pkts_pdu_res_per_sec_warning")
	private Integer pktsPduResPerSecWarning;

   	@Column(name = "pkts_pdu_res_per_sec_danger")
	private Integer pktsPduResPerSecDanger;

   	@Column(name = "pkts_pdu_res_per_sec_obstacle")
	private Integer pktsPduResPerSecObstacle;

   	@Column(name = "len_pdu_req_delta_use")
	private Boolean lenPduReqDeltaUse;

   	@Column(name = "len_pdu_req_delta_caution")
	private Integer lenPduReqDeltaCaution;

   	@Column(name = "len_pdu_req_delta_warning")
	private Integer lenPduReqDeltaWarning;

   	@Column(name = "len_pdu_req_delta_danger")
	private Integer lenPduReqDeltaDanger;

   	@Column(name = "len_pdu_req_delta_obstacle")
	private Integer lenPduReqDeltaObstacle;

   	@Column(name = "len_pdu_res_delta_use")
	private Boolean lenPduResDeltaUse;

   	@Column(name = "len_pdu_res_delta_caution")
	private Integer lenPduResDeltaCaution;

   	@Column(name = "len_pdu_res_delta_warning")
	private Integer lenPduResDeltaWarning;

   	@Column(name = "len_pdu_res_delta_danger")
	private Integer lenPduResDeltaDanger;

   	@Column(name = "len_pdu_res_delta_obstacle")
	private Integer lenPduResDeltaObstacle;

   	@Column(name = "pkts_pdu_req_delta_use")
	private Boolean pktsPduReqDeltaUse;

   	@Column(name = "pkts_pdu_req_delta_caution")
	private Integer pktsPduReqDeltaCaution;

   	@Column(name = "pkts_pdu_req_delta_warning")
	private Integer pktsPduReqDeltaWarning;

   	@Column(name = "pkts_pdu_req_delta_danger")
	private Integer pktsPduReqDeltaDanger;

   	@Column(name = "pkts_pdu_req_delta_obstacle")
	private Integer pktsPduReqDeltaObstacle;

   	@Column(name = "pkts_pdu_res_delta_use")
	private Boolean pktsPduResDeltaUse;

   	@Column(name = "pkts_pdu_res_delta_caution")
	private Integer pktsPduResDeltaCaution;

   	@Column(name = "pkts_pdu_res_delta_warning")
	private Integer pktsPduResDeltaWarning;

   	@Column(name = "pkts_pdu_res_delta_danger")
	private Integer pktsPduResDeltaDanger;

   	@Column(name = "pkts_pdu_res_delta_obstacle")
	private Integer pktsPduResDeltaObstacle;

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

   	@Column(name = "ts_rtt_last_ack_req_use")
	private Boolean tsRttLastAckReqUse;

   	@Column(name = "ts_rtt_last_ack_req_caution")
	private Integer tsRttLastAckReqCaution;

   	@Column(name = "ts_rtt_last_ack_req_warning")
	private Integer tsRttLastAckReqWarning;

   	@Column(name = "ts_rtt_last_ack_req_danger")
	private Integer tsRttLastAckReqDanger;

   	@Column(name = "ts_rtt_last_ack_req_obstacle")
	private Integer tsRttLastAckReqObstacle;

   	@Column(name = "ts_rtt_last_ack_res_use")
	private Boolean tsRttLastAckResUse;

   	@Column(name = "ts_rtt_last_ack_res_caution")
	private Integer tsRttLastAckResCaution;

   	@Column(name = "ts_rtt_last_ack_res_warning")
	private Integer tsRttLastAckResWarning;

   	@Column(name = "ts_rtt_last_ack_res_danger")
	private Integer tsRttLastAckResDanger;

   	@Column(name = "ts_rtt_last_ack_res_obstacle")
	private Integer tsRttLastAckResObstacle;

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

   	@Column(name = "ts_syn_delay_use")
	private Boolean tsSynDelayUse;

   	@Column(name = "ts_syn_delay_caution")
	private Integer tsSynDelayCaution;

   	@Column(name = "ts_syn_delay_warning")
	private Integer tsSynDelayWarning;

   	@Column(name = "ts_syn_delay_danger")
	private Integer tsSynDelayDanger;

   	@Column(name = "ts_syn_delay_obstacle")
	private Integer tsSynDelayObstacle;

   	@Column(name = "ts_synack_delay_use")
	private Boolean tsSynackDelayUse;

   	@Column(name = "ts_synack_delay_caution")
	private Integer tsSynackDelayCaution;

   	@Column(name = "ts_synack_delay_warning")
	private Integer tsSynackDelayWarning;

   	@Column(name = "ts_synack_delay_danger")
	private Integer tsSynackDelayDanger;

   	@Column(name = "ts_synack_delay_obstacle")
	private Integer tsSynackDelayObstacle;

   	@Column(name = "ts_ack_delay_first_req_use")
	private Boolean tsAckDelayFirstReqUse;

   	@Column(name = "ts_ack_delay_first_req_caution")
	private Integer tsAckDelayFirstReqCaution;

   	@Column(name = "ts_ack_delay_first_req_warning")
	private Integer tsAckDelayFirstReqWarning;

   	@Column(name = "ts_ack_delay_first_req_danger")
	private Integer tsAckDelayFirstReqDanger;

   	@Column(name = "ts_ack_delay_first_req_obstacle")
	private Integer tsAckDelayFirstReqObstacle;

   	@Column(name = "ts_ack_delay_first_res_use")
	private Boolean tsAckDelayFirstResUse;

   	@Column(name = "ts_ack_delay_first_res_caution")
	private Integer tsAckDelayFirstResCaution;

   	@Column(name = "ts_ack_delay_first_res_warning")
	private Integer tsAckDelayFirstResWarning;

   	@Column(name = "ts_ack_delay_first_res_danger")
	private Integer tsAckDelayFirstResDanger;

   	@Column(name = "ts_ack_delay_first_res_obstacle")
	private Integer tsAckDelayFirstResObstacle;

   	@Column(name = "ts_ack_delay_last_req_use")
	private Boolean tsAckDelayLastReqUse;

   	@Column(name = "ts_ack_delay_last_req_caution")
	private Integer tsAckDelayLastReqCaution;

   	@Column(name = "ts_ack_delay_last_req_warning")
	private Integer tsAckDelayLastReqWarning;

   	@Column(name = "ts_ack_delay_last_req_danger")
	private Integer tsAckDelayLastReqDanger;

   	@Column(name = "ts_ack_delay_last_req_obstacle")
	private Integer tsAckDelayLastReqObstacle;

   	@Column(name = "ts_ack_delay_last_res_use")
	private Boolean tsAckDelayLastResUse;

   	@Column(name = "ts_ack_delay_last_res_caution")
	private Integer tsAckDelayLastResCaution;

   	@Column(name = "ts_ack_delay_last_res_warning")
	private Integer tsAckDelayLastResWarning;

   	@Column(name = "ts_ack_delay_last_res_danger")
	private Integer tsAckDelayLastResDanger;

   	@Column(name = "ts_ack_delay_last_res_obstacle")
	private Integer tsAckDelayLastResObstacle;

   	@Column(name = "ts_syn_use")
	private Boolean tsSynUse;

   	@Column(name = "ts_syn_caution")
	private Integer tsSynCaution;

   	@Column(name = "ts_syn_warning")
	private Integer tsSynWarning;

   	@Column(name = "ts_syn_danger")
	private Integer tsSynDanger;

   	@Column(name = "ts_syn_obstacle")
	private Integer tsSynObstacle;

   	@Column(name = "ts_syn_ack_use")
	private Boolean tsSynAckUse;

   	@Column(name = "ts_syn_ack_caution")
	private Integer tsSynAckCaution;

   	@Column(name = "ts_syn_ack_warning")
	private Integer tsSynAckWarning;

   	@Column(name = "ts_syn_ack_danger")
	private Integer tsSynAckDanger;

   	@Column(name = "ts_syn_ack_obstacle")
	private Integer tsSynAckObstacle;

   	@Column(name = "ts_first_ack_use")
	private Boolean tsFirstAckUse;

   	@Column(name = "ts_first_ack_caution")
	private Integer tsFirstAckCaution;

   	@Column(name = "ts_first_ack_warning")
	private Integer tsFirstAckWarning;

   	@Column(name = "ts_first_ack_danger")
	private Integer tsFirstAckDanger;

   	@Column(name = "ts_first_ack_obstacle")
	private Integer tsFirstAckObstacle;

   	@Column(name = "ts_first_ack_req_use")
	private Boolean tsFirstAckReqUse;

   	@Column(name = "ts_first_ack_req_caution")
	private Integer tsFirstAckReqCaution;

   	@Column(name = "ts_first_ack_req_warning")
	private Integer tsFirstAckReqWarning;

   	@Column(name = "ts_first_ack_req_danger")
	private Integer tsFirstAckReqDanger;

   	@Column(name = "ts_first_ack_req_obstacle")
	private Integer tsFirstAckReqObstacle;

   	@Column(name = "ts_first_ack_req_ack_use")
	private Boolean tsFirstAckReqAckUse;

   	@Column(name = "ts_first_ack_req_ack_caution")
	private Integer tsFirstAckReqAckCaution;

   	@Column(name = "ts_first_ack_req_ack_warning")
	private Integer tsFirstAckReqAckWarning;

   	@Column(name = "ts_first_ack_req_ack_danger")
	private Integer tsFirstAckReqAckDanger;

   	@Column(name = "ts_first_ack_req_ack_obstacle")
	private Integer tsFirstAckReqAckObstacle;

   	@Column(name = "ts_first_ack_res_use")
	private Boolean tsFirstAckResUse;

   	@Column(name = "ts_first_ack_res_caution")
	private Integer tsFirstAckResCaution;

   	@Column(name = "ts_first_ack_res_warning")
	private Integer tsFirstAckResWarning;

   	@Column(name = "ts_first_ack_res_danger")
	private Integer tsFirstAckResDanger;

   	@Column(name = "ts_first_ack_res_obstacle")
	private Integer tsFirstAckResObstacle;

   	@Column(name = "ts_first_ack_res_ack_use")
	private Boolean tsFirstAckResAckUse;

   	@Column(name = "ts_first_ack_res_ack_caution")
	private Integer tsFirstAckResAckCaution;

   	@Column(name = "ts_first_ack_res_ack_warning")
	private Integer tsFirstAckResAckWarning;

   	@Column(name = "ts_first_ack_res_ack_danger")
	private Integer tsFirstAckResAckDanger;

   	@Column(name = "ts_first_ack_res_ack_obstacle")
	private Integer tsFirstAckResAckObstacle;

   	@Column(name = "ts_first_push_req_use")
	private Boolean tsFirstPushReqUse;

   	@Column(name = "ts_first_push_req_caution")
	private Integer tsFirstPushReqCaution;

   	@Column(name = "ts_first_push_req_warning")
	private Integer tsFirstPushReqWarning;

   	@Column(name = "ts_first_push_req_danger")
	private Integer tsFirstPushReqDanger;

   	@Column(name = "ts_first_push_req_obstacle")
	private Integer tsFirstPushReqObstacle;

   	@Column(name = "ts_first_push_res_use")
	private Boolean tsFirstPushResUse;

   	@Column(name = "ts_first_push_res_caution")
	private Integer tsFirstPushResCaution;

   	@Column(name = "ts_first_push_res_warning")
	private Integer tsFirstPushResWarning;

   	@Column(name = "ts_first_push_res_danger")
	private Integer tsFirstPushResDanger;

   	@Column(name = "ts_first_push_res_obstacle")
	private Integer tsFirstPushResObstacle;

   	@Column(name = "ack_rtt_min_req_tot_use")
	private Boolean ackRttMinReqTotUse;

   	@Column(name = "ack_rtt_min_req_tot_caution")
	private Integer ackRttMinReqTotCaution;

   	@Column(name = "ack_rtt_min_req_tot_warning")
	private Integer ackRttMinReqTotWarning;

   	@Column(name = "ack_rtt_min_req_tot_danger")
	private Integer ackRttMinReqTotDanger;

   	@Column(name = "ack_rtt_min_req_tot_obstacle")
	private Integer ackRttMinReqTotObstacle;

   	@Column(name = "ack_rtt_min_res_tot_use")
	private Boolean ackRttMinResTotUse;

   	@Column(name = "ack_rtt_min_res_tot_caution")
	private Integer ackRttMinResTotCaution;

   	@Column(name = "ack_rtt_min_res_tot_warning")
	private Integer ackRttMinResTotWarning;

   	@Column(name = "ack_rtt_min_res_tot_danger")
	private Integer ackRttMinResTotDanger;

   	@Column(name = "ack_rtt_min_res_tot_obstacle")
	private Integer ackRttMinResTotObstacle;

   	@Column(name = "ack_rtt_max_req_tot_use")
	private Boolean ackRttMaxReqTotUse;

   	@Column(name = "ack_rtt_max_req_tot_caution")
	private Integer ackRttMaxReqTotCaution;

   	@Column(name = "ack_rtt_max_req_tot_warning")
	private Integer ackRttMaxReqTotWarning;

   	@Column(name = "ack_rtt_max_req_tot_danger")
	private Integer ackRttMaxReqTotDanger;

   	@Column(name = "ack_rtt_max_req_tot_obstacle")
	private Integer ackRttMaxReqTotObstacle;

   	@Column(name = "ack_rtt_max_res_tot_use")
	private Boolean ackRttMaxResTotUse;

   	@Column(name = "ack_rtt_max_res_tot_caution")
	private Integer ackRttMaxResTotCaution;

   	@Column(name = "ack_rtt_max_res_tot_warning")
	private Integer ackRttMaxResTotWarning;

   	@Column(name = "ack_rtt_max_res_tot_danger")
	private Integer ackRttMaxResTotDanger;

   	@Column(name = "ack_rtt_max_res_tot_obstacle")
	private Integer ackRttMaxResTotObstacle;

   	@Column(name = "ack_rtt_min_req_curr_use")
	private Boolean ackRttMinReqCurrUse;

   	@Column(name = "ack_rtt_min_req_curr_caution")
	private Integer ackRttMinReqCurrCaution;

   	@Column(name = "ack_rtt_min_req_curr_warning")
	private Integer ackRttMinReqCurrWarning;

   	@Column(name = "ack_rtt_min_req_curr_danger")
	private Integer ackRttMinReqCurrDanger;

   	@Column(name = "ack_rtt_min_req_curr_obstacle")
	private Integer ackRttMinReqCurrObstacle;

   	@Column(name = "ack_rtt_min_res_curr_use")
	private Boolean ackRttMinResCurrUse;

   	@Column(name = "ack_rtt_min_res_curr_caution")
	private Integer ackRttMinResCurrCaution;

   	@Column(name = "ack_rtt_min_res_curr_warning")
	private Integer ackRttMinResCurrWarning;

   	@Column(name = "ack_rtt_min_res_curr_danger")
	private Integer ackRttMinResCurrDanger;

   	@Column(name = "ack_rtt_min_res_curr_obstacle")
	private Integer ackRttMinResCurrObstacle;

   	@Column(name = "ack_rtt_max_req_curr_use")
	private Boolean ackRttMaxReqCurrUse;

   	@Column(name = "ack_rtt_max_req_curr_caution")
	private Integer ackRttMaxReqCurrCaution;

   	@Column(name = "ack_rtt_max_req_curr_warning")
	private Integer ackRttMaxReqCurrWarning;

   	@Column(name = "ack_rtt_max_req_curr_danger")
	private Integer ackRttMaxReqCurrDanger;

   	@Column(name = "ack_rtt_max_req_curr_obstacle")
	private Integer ackRttMaxReqCurrObstacle;

   	@Column(name = "ack_rtt_max_res_curr_use")
	private Boolean ackRttMaxResCurrUse;

   	@Column(name = "ack_rtt_max_res_curr_caution")
	private Integer ackRttMaxResCurrCaution;

   	@Column(name = "ack_rtt_max_res_curr_warning")
	private Integer ackRttMaxResCurrWarning;

   	@Column(name = "ack_rtt_max_res_curr_danger")
	private Integer ackRttMaxResCurrDanger;

   	@Column(name = "ack_rtt_max_res_curr_obstacle")
	private Integer ackRttMaxResCurrObstacle;

   	@Column(name = "tcp_flag_stat_fin_req_tot_use")
	private Boolean tcpFlagStatFinReqTotUse;

   	@Column(name = "tcp_flag_stat_fin_req_tot_caution")
	private Integer tcpFlagStatFinReqTotCaution;

   	@Column(name = "tcp_flag_stat_fin_req_tot_warning")
	private Integer tcpFlagStatFinReqTotWarning;

   	@Column(name = "tcp_flag_stat_fin_req_tot_danger")
	private Integer tcpFlagStatFinReqTotDanger;

   	@Column(name = "tcp_flag_stat_fin_req_tot_obstacle")
	private Integer tcpFlagStatFinReqTotObstacle;

   	@Column(name = "tcp_flag_stat_fin_res_tot_use")
	private Boolean tcpFlagStatFinResTotUse;

   	@Column(name = "tcp_flag_stat_fin_res_tot_caution")
	private Integer tcpFlagStatFinResTotCaution;

   	@Column(name = "tcp_flag_stat_fin_res_tot_warning")
	private Integer tcpFlagStatFinResTotWarning;

   	@Column(name = "tcp_flag_stat_fin_res_tot_danger")
	private Integer tcpFlagStatFinResTotDanger;

   	@Column(name = "tcp_flag_stat_fin_res_tot_obstacle")
	private Integer tcpFlagStatFinResTotObstacle;

   	@Column(name = "tcp_flag_stat_syn_req_tot_use")
	private Boolean tcpFlagStatSynReqTotUse;

   	@Column(name = "tcp_flag_stat_syn_req_tot_caution")
	private Integer tcpFlagStatSynReqTotCaution;

   	@Column(name = "tcp_flag_stat_syn_req_tot_warning")
	private Integer tcpFlagStatSynReqTotWarning;

   	@Column(name = "tcp_flag_stat_syn_req_tot_danger")
	private Integer tcpFlagStatSynReqTotDanger;

   	@Column(name = "tcp_flag_stat_syn_req_tot_obstacle")
	private Integer tcpFlagStatSynReqTotObstacle;

   	@Column(name = "tcp_flag_stat_syn_res_tot_use")
	private Boolean tcpFlagStatSynResTotUse;

   	@Column(name = "tcp_flag_stat_syn_res_tot_caution")
	private Integer tcpFlagStatSynResTotCaution;

   	@Column(name = "tcp_flag_stat_syn_res_tot_warning")
	private Integer tcpFlagStatSynResTotWarning;

   	@Column(name = "tcp_flag_stat_syn_res_tot_danger")
	private Integer tcpFlagStatSynResTotDanger;

   	@Column(name = "tcp_flag_stat_syn_res_tot_obstacle")
	private Integer tcpFlagStatSynResTotObstacle;

   	@Column(name = "tcp_flag_stat_rst_req_tot_use")
	private Boolean tcpFlagStatRstReqTotUse;

   	@Column(name = "tcp_flag_stat_rst_req_tot_caution")
	private Integer tcpFlagStatRstReqTotCaution;

   	@Column(name = "tcp_flag_stat_rst_req_tot_warning")
	private Integer tcpFlagStatRstReqTotWarning;

   	@Column(name = "tcp_flag_stat_rst_req_tot_danger")
	private Integer tcpFlagStatRstReqTotDanger;

   	@Column(name = "tcp_flag_stat_rst_req_tot_obstacle")
	private Integer tcpFlagStatRstReqTotObstacle;

   	@Column(name = "tcp_flag_stat_rst_res_tot_use")
	private Boolean tcpFlagStatRstResTotUse;

   	@Column(name = "tcp_flag_stat_rst_res_tot_caution")
	private Integer tcpFlagStatRstResTotCaution;

   	@Column(name = "tcp_flag_stat_rst_res_tot_warning")
	private Integer tcpFlagStatRstResTotWarning;

   	@Column(name = "tcp_flag_stat_rst_res_tot_danger")
	private Integer tcpFlagStatRstResTotDanger;

   	@Column(name = "tcp_flag_stat_rst_res_tot_obstacle")
	private Integer tcpFlagStatRstResTotObstacle;

   	@Column(name = "tcp_flag_stat_psh_req_tot_use")
	private Boolean tcpFlagStatPshReqTotUse;

   	@Column(name = "tcp_flag_stat_psh_req_tot_caution")
	private Integer tcpFlagStatPshReqTotCaution;

   	@Column(name = "tcp_flag_stat_psh_req_tot_warning")
	private Integer tcpFlagStatPshReqTotWarning;

   	@Column(name = "tcp_flag_stat_psh_req_tot_danger")
	private Integer tcpFlagStatPshReqTotDanger;

   	@Column(name = "tcp_flag_stat_psh_req_tot_obstacle")
	private Integer tcpFlagStatPshReqTotObstacle;

   	@Column(name = "tcp_flag_stat_psh_res_tot_use")
	private Boolean tcpFlagStatPshResTotUse;

   	@Column(name = "tcp_flag_stat_psh_res_tot_caution")
	private Integer tcpFlagStatPshResTotCaution;

   	@Column(name = "tcp_flag_stat_psh_res_tot_warning")
	private Integer tcpFlagStatPshResTotWarning;

   	@Column(name = "tcp_flag_stat_psh_res_tot_danger")
	private Integer tcpFlagStatPshResTotDanger;

   	@Column(name = "tcp_flag_stat_psh_res_tot_obstacle")
	private Integer tcpFlagStatPshResTotObstacle;

   	@Column(name = "tcp_flag_stat_ack_req_tot_use")
	private Boolean tcpFlagStatAckReqTotUse;

   	@Column(name = "tcp_flag_stat_ack_req_tot_caution")
	private Integer tcpFlagStatAckReqTotCaution;

   	@Column(name = "tcp_flag_stat_ack_req_tot_warning")
	private Integer tcpFlagStatAckReqTotWarning;

   	@Column(name = "tcp_flag_stat_ack_req_tot_danger")
	private Integer tcpFlagStatAckReqTotDanger;

   	@Column(name = "tcp_flag_stat_ack_req_tot_obstacle")
	private Integer tcpFlagStatAckReqTotObstacle;

   	@Column(name = "tcp_flag_stat_ack_res_tot_use")
	private Boolean tcpFlagStatAckResTotUse;

   	@Column(name = "tcp_flag_stat_ack_res_tot_caution")
	private Integer tcpFlagStatAckResTotCaution;

   	@Column(name = "tcp_flag_stat_ack_res_tot_warning")
	private Integer tcpFlagStatAckResTotWarning;

   	@Column(name = "tcp_flag_stat_ack_res_tot_danger")
	private Integer tcpFlagStatAckResTotDanger;

   	@Column(name = "tcp_flag_stat_ack_res_tot_obstacle")
	private Integer tcpFlagStatAckResTotObstacle;

   	@Column(name = "tcp_flag_stat_urg_req_tot_use")
	private Boolean tcpFlagStatUrgReqTotUse;

   	@Column(name = "tcp_flag_stat_urg_req_tot_caution")
	private Integer tcpFlagStatUrgReqTotCaution;

   	@Column(name = "tcp_flag_stat_urg_req_tot_warning")
	private Integer tcpFlagStatUrgReqTotWarning;

   	@Column(name = "tcp_flag_stat_urg_req_tot_danger")
	private Integer tcpFlagStatUrgReqTotDanger;

   	@Column(name = "tcp_flag_stat_urg_req_tot_obstacle")
	private Integer tcpFlagStatUrgReqTotObstacle;

   	@Column(name = "tcp_flag_stat_urg_res_tot_use")
	private Boolean tcpFlagStatUrgResTotUse;

   	@Column(name = "tcp_flag_stat_urg_res_tot_caution")
	private Integer tcpFlagStatUrgResTotCaution;

   	@Column(name = "tcp_flag_stat_urg_res_tot_warning")
	private Integer tcpFlagStatUrgResTotWarning;

   	@Column(name = "tcp_flag_stat_urg_res_tot_danger")
	private Integer tcpFlagStatUrgResTotDanger;

   	@Column(name = "tcp_flag_stat_urg_res_tot_obstacle")
	private Integer tcpFlagStatUrgResTotObstacle;

   	@Column(name = "tcp_flag_stat_fin_req_per_sec_use")
	private Boolean tcpFlagStatFinReqPerSecUse;

   	@Column(name = "tcp_flag_stat_fin_req_per_sec_caution")
	private Integer tcpFlagStatFinReqPerSecCaution;

   	@Column(name = "tcp_flag_stat_fin_req_per_sec_warning")
	private Integer tcpFlagStatFinReqPerSecWarning;

   	@Column(name = "tcp_flag_stat_fin_req_per_sec_danger")
	private Integer tcpFlagStatFinReqPerSecDanger;

   	@Column(name = "tcp_flag_stat_fin_req_per_sec_obstacle")
	private Integer tcpFlagStatFinReqPerSecObstacle;

   	@Column(name = "tcp_flag_stat_fin_res_per_sec_use")
	private Boolean tcpFlagStatFinResPerSecUse;

   	@Column(name = "tcp_flag_stat_fin_res_per_sec_caution")
	private Integer tcpFlagStatFinResPerSecCaution;

   	@Column(name = "tcp_flag_stat_fin_res_per_sec_warning")
	private Integer tcpFlagStatFinResPerSecWarning;

   	@Column(name = "tcp_flag_stat_fin_res_per_sec_danger")
	private Integer tcpFlagStatFinResPerSecDanger;

   	@Column(name = "tcp_flag_stat_fin_res_per_sec_obstacle")
	private Integer tcpFlagStatFinResPerSecObstacle;

   	@Column(name = "tcp_flag_stat_syn_req_per_sec_use")
	private Boolean tcpFlagStatSynReqPerSecUse;

   	@Column(name = "tcp_flag_stat_syn_req_per_sec_caution")
	private Integer tcpFlagStatSynReqPerSecCaution;

   	@Column(name = "tcp_flag_stat_syn_req_per_sec_warning")
	private Integer tcpFlagStatSynReqPerSecWarning;

   	@Column(name = "tcp_flag_stat_syn_req_per_sec_danger")
	private Integer tcpFlagStatSynReqPerSecDanger;

   	@Column(name = "tcp_flag_stat_syn_req_per_sec_obstacle")
	private Integer tcpFlagStatSynReqPerSecObstacle;

   	@Column(name = "tcp_flag_stat_syn_res_per_sec_use")
	private Boolean tcpFlagStatSynResPerSecUse;

   	@Column(name = "tcp_flag_stat_syn_res_per_sec_caution")
	private Integer tcpFlagStatSynResPerSecCaution;

   	@Column(name = "tcp_flag_stat_syn_res_per_sec_warning")
	private Integer tcpFlagStatSynResPerSecWarning;

   	@Column(name = "tcp_flag_stat_syn_res_per_sec_danger")
	private Integer tcpFlagStatSynResPerSecDanger;

   	@Column(name = "tcp_flag_stat_syn_res_per_sec_obstacle")
	private Integer tcpFlagStatSynResPerSecObstacle;

   	@Column(name = "tcp_flag_stat_rst_req_per_sec_use")
	private Boolean tcpFlagStatRstReqPerSecUse;

   	@Column(name = "tcp_flag_stat_rst_req_per_sec_caution")
	private Integer tcpFlagStatRstReqPerSecCaution;

   	@Column(name = "tcp_flag_stat_rst_req_per_sec_warning")
	private Integer tcpFlagStatRstReqPerSecWarning;

   	@Column(name = "tcp_flag_stat_rst_req_per_sec_danger")
	private Integer tcpFlagStatRstReqPerSecDanger;

   	@Column(name = "tcp_flag_stat_rst_req_per_sec_obstacle")
	private Integer tcpFlagStatRstReqPerSecObstacle;

   	@Column(name = "tcp_flag_stat_rst_res_per_sec_use")
	private Boolean tcpFlagStatRstResPerSecUse;

   	@Column(name = "tcp_flag_stat_rst_res_per_sec_caution")
	private Integer tcpFlagStatRstResPerSecCaution;

   	@Column(name = "tcp_flag_stat_rst_res_per_sec_warning")
	private Integer tcpFlagStatRstResPerSecWarning;

   	@Column(name = "tcp_flag_stat_rst_res_per_sec_danger")
	private Integer tcpFlagStatRstResPerSecDanger;

   	@Column(name = "tcp_flag_stat_rst_res_per_sec_obstacle")
	private Integer tcpFlagStatRstResPerSecObstacle;

   	@Column(name = "tcp_flag_stat_psh_req_per_sec_use")
	private Boolean tcpFlagStatPshReqPerSecUse;

   	@Column(name = "tcp_flag_stat_psh_req_per_sec_caution")
	private Integer tcpFlagStatPshReqPerSecCaution;

   	@Column(name = "tcp_flag_stat_psh_req_per_sec_warning")
	private Integer tcpFlagStatPshReqPerSecWarning;

   	@Column(name = "tcp_flag_stat_psh_req_per_sec_danger")
	private Integer tcpFlagStatPshReqPerSecDanger;

   	@Column(name = "tcp_flag_stat_psh_req_per_sec_obstacle")
	private Integer tcpFlagStatPshReqPerSecObstacle;

   	@Column(name = "tcp_flag_stat_psh_res_per_sec_use")
	private Boolean tcpFlagStatPshResPerSecUse;

   	@Column(name = "tcp_flag_stat_psh_res_per_sec_caution")
	private Integer tcpFlagStatPshResPerSecCaution;

   	@Column(name = "tcp_flag_stat_psh_res_per_sec_warning")
	private Integer tcpFlagStatPshResPerSecWarning;

   	@Column(name = "tcp_flag_stat_psh_res_per_sec_danger")
	private Integer tcpFlagStatPshResPerSecDanger;

   	@Column(name = "tcp_flag_stat_psh_res_per_sec_obstacle")
	private Integer tcpFlagStatPshResPerSecObstacle;

   	@Column(name = "tcp_flag_stat_ack_req_per_sec_use")
	private Boolean tcpFlagStatAckReqPerSecUse;

   	@Column(name = "tcp_flag_stat_ack_req_per_sec_caution")
	private Integer tcpFlagStatAckReqPerSecCaution;

   	@Column(name = "tcp_flag_stat_ack_req_per_sec_warning")
	private Integer tcpFlagStatAckReqPerSecWarning;

   	@Column(name = "tcp_flag_stat_ack_req_per_sec_danger")
	private Integer tcpFlagStatAckReqPerSecDanger;

   	@Column(name = "tcp_flag_stat_ack_req_per_sec_obstacle")
	private Integer tcpFlagStatAckReqPerSecObstacle;

   	@Column(name = "tcp_flag_stat_ack_res_per_sec_use")
	private Boolean tcpFlagStatAckResPerSecUse;

   	@Column(name = "tcp_flag_stat_ack_res_per_sec_caution")
	private Integer tcpFlagStatAckResPerSecCaution;

   	@Column(name = "tcp_flag_stat_ack_res_per_sec_warning")
	private Integer tcpFlagStatAckResPerSecWarning;

   	@Column(name = "tcp_flag_stat_ack_res_per_sec_danger")
	private Integer tcpFlagStatAckResPerSecDanger;

   	@Column(name = "tcp_flag_stat_ack_res_per_sec_obstacle")
	private Integer tcpFlagStatAckResPerSecObstacle;

   	@Column(name = "tcp_flag_stat_urg_req_per_sec_use")
	private Boolean tcpFlagStatUrgReqPerSecUse;

   	@Column(name = "tcp_flag_stat_urg_req_per_sec_caution")
	private Integer tcpFlagStatUrgReqPerSecCaution;

   	@Column(name = "tcp_flag_stat_urg_req_per_sec_warning")
	private Integer tcpFlagStatUrgReqPerSecWarning;

   	@Column(name = "tcp_flag_stat_urg_req_per_sec_danger")
	private Integer tcpFlagStatUrgReqPerSecDanger;

   	@Column(name = "tcp_flag_stat_urg_req_per_sec_obstacle")
	private Integer tcpFlagStatUrgReqPerSecObstacle;

   	@Column(name = "tcp_flag_stat_urg_res_per_sec_use")
	private Boolean tcpFlagStatUrgResPerSecUse;

   	@Column(name = "tcp_flag_stat_urg_res_per_sec_caution")
	private Integer tcpFlagStatUrgResPerSecCaution;

   	@Column(name = "tcp_flag_stat_urg_res_per_sec_warning")
	private Integer tcpFlagStatUrgResPerSecWarning;

   	@Column(name = "tcp_flag_stat_urg_res_per_sec_danger")
	private Integer tcpFlagStatUrgResPerSecDanger;

   	@Column(name = "tcp_flag_stat_urg_res_per_sec_obstacle")
	private Integer tcpFlagStatUrgResPerSecObstacle;

   	@Column(name = "tcp_flag_stat_fin_req_delta_use")
	private Boolean tcpFlagStatFinReqDeltaUse;

   	@Column(name = "tcp_flag_stat_fin_req_delta_caution")
	private Integer tcpFlagStatFinReqDeltaCaution;

   	@Column(name = "tcp_flag_stat_fin_req_delta_warning")
	private Integer tcpFlagStatFinReqDeltaWarning;

   	@Column(name = "tcp_flag_stat_fin_req_delta_danger")
	private Integer tcpFlagStatFinReqDeltaDanger;

   	@Column(name = "tcp_flag_stat_fin_req_delta_obstacle")
	private Integer tcpFlagStatFinReqDeltaObstacle;

   	@Column(name = "tcp_flag_stat_fin_res_delta_use")
	private Boolean tcpFlagStatFinResDeltaUse;

   	@Column(name = "tcp_flag_stat_fin_res_delta_caution")
	private Integer tcpFlagStatFinResDeltaCaution;

   	@Column(name = "tcp_flag_stat_fin_res_delta_warning")
	private Integer tcpFlagStatFinResDeltaWarning;

   	@Column(name = "tcp_flag_stat_fin_res_delta_danger")
	private Integer tcpFlagStatFinResDeltaDanger;

   	@Column(name = "tcp_flag_stat_fin_res_delta_obstacle")
	private Integer tcpFlagStatFinResDeltaObstacle;

   	@Column(name = "tcp_flag_stat_syn_req_delta_use")
	private Boolean tcpFlagStatSynReqDeltaUse;

   	@Column(name = "tcp_flag_stat_syn_req_delta_caution")
	private Integer tcpFlagStatSynReqDeltaCaution;

   	@Column(name = "tcp_flag_stat_syn_req_delta_warning")
	private Integer tcpFlagStatSynReqDeltaWarning;

   	@Column(name = "tcp_flag_stat_syn_req_delta_danger")
	private Integer tcpFlagStatSynReqDeltaDanger;

   	@Column(name = "tcp_flag_stat_syn_req_delta_obstacle")
	private Integer tcpFlagStatSynReqDeltaObstacle;

   	@Column(name = "tcp_flag_stat_syn_res_delta_use")
	private Boolean tcpFlagStatSynResDeltaUse;

   	@Column(name = "tcp_flag_stat_syn_res_delta_caution")
	private Integer tcpFlagStatSynResDeltaCaution;

   	@Column(name = "tcp_flag_stat_syn_res_delta_warning")
	private Integer tcpFlagStatSynResDeltaWarning;

   	@Column(name = "tcp_flag_stat_syn_res_delta_danger")
	private Integer tcpFlagStatSynResDeltaDanger;

   	@Column(name = "tcp_flag_stat_syn_res_delta_obstacle")
	private Integer tcpFlagStatSynResDeltaObstacle;

   	@Column(name = "tcp_flag_stat_rst_req_delta_use")
	private Boolean tcpFlagStatRstReqDeltaUse;

   	@Column(name = "tcp_flag_stat_rst_req_delta_caution")
	private Integer tcpFlagStatRstReqDeltaCaution;

   	@Column(name = "tcp_flag_stat_rst_req_delta_warning")
	private Integer tcpFlagStatRstReqDeltaWarning;

   	@Column(name = "tcp_flag_stat_rst_req_delta_danger")
	private Integer tcpFlagStatRstReqDeltaDanger;

   	@Column(name = "tcp_flag_stat_rst_req_delta_obstacle")
	private Integer tcpFlagStatRstReqDeltaObstacle;

   	@Column(name = "tcp_flag_stat_rst_res_delta_use")
	private Boolean tcpFlagStatRstResDeltaUse;

   	@Column(name = "tcp_flag_stat_rst_res_delta_caution")
	private Integer tcpFlagStatRstResDeltaCaution;

   	@Column(name = "tcp_flag_stat_rst_res_delta_warning")
	private Integer tcpFlagStatRstResDeltaWarning;

   	@Column(name = "tcp_flag_stat_rst_res_delta_danger")
	private Integer tcpFlagStatRstResDeltaDanger;

   	@Column(name = "tcp_flag_stat_rst_res_delta_obstacle")
	private Integer tcpFlagStatRstResDeltaObstacle;

   	@Column(name = "tcp_flag_stat_psh_req_delta_use")
	private Boolean tcpFlagStatPshReqDeltaUse;

   	@Column(name = "tcp_flag_stat_psh_req_delta_caution")
	private Integer tcpFlagStatPshReqDeltaCaution;

   	@Column(name = "tcp_flag_stat_psh_req_delta_warning")
	private Integer tcpFlagStatPshReqDeltaWarning;

   	@Column(name = "tcp_flag_stat_psh_req_delta_danger")
	private Integer tcpFlagStatPshReqDeltaDanger;

   	@Column(name = "tcp_flag_stat_psh_req_delta_obstacle")
	private Integer tcpFlagStatPshReqDeltaObstacle;

   	@Column(name = "tcp_flag_stat_psh_res_delta_use")
	private Boolean tcpFlagStatPshResDeltaUse;

   	@Column(name = "tcp_flag_stat_psh_res_delta_caution")
	private Integer tcpFlagStatPshResDeltaCaution;

   	@Column(name = "tcp_flag_stat_psh_res_delta_warning")
	private Integer tcpFlagStatPshResDeltaWarning;

   	@Column(name = "tcp_flag_stat_psh_res_delta_danger")
	private Integer tcpFlagStatPshResDeltaDanger;

   	@Column(name = "tcp_flag_stat_psh_res_delta_obstacle")
	private Integer tcpFlagStatPshResDeltaObstacle;

   	@Column(name = "tcp_flag_stat_ack_req_delta_use")
	private Boolean tcpFlagStatAckReqDeltaUse;

   	@Column(name = "tcp_flag_stat_ack_req_delta_caution")
	private Integer tcpFlagStatAckReqDeltaCaution;

   	@Column(name = "tcp_flag_stat_ack_req_delta_warning")
	private Integer tcpFlagStatAckReqDeltaWarning;

   	@Column(name = "tcp_flag_stat_ack_req_delta_danger")
	private Integer tcpFlagStatAckReqDeltaDanger;

   	@Column(name = "tcp_flag_stat_ack_req_delta_obstacle")
	private Integer tcpFlagStatAckReqDeltaObstacle;

   	@Column(name = "tcp_flag_stat_ack_res_delta_use")
	private Boolean tcpFlagStatAckResDeltaUse;

   	@Column(name = "tcp_flag_stat_ack_res_delta_caution")
	private Integer tcpFlagStatAckResDeltaCaution;

   	@Column(name = "tcp_flag_stat_ack_res_delta_warning")
	private Integer tcpFlagStatAckResDeltaWarning;

   	@Column(name = "tcp_flag_stat_ack_res_delta_danger")
	private Integer tcpFlagStatAckResDeltaDanger;

   	@Column(name = "tcp_flag_stat_ack_res_delta_obstacle")
	private Integer tcpFlagStatAckResDeltaObstacle;

   	@Column(name = "tcp_flag_stat_urg_req_delta_use")
	private Boolean tcpFlagStatUrgReqDeltaUse;

   	@Column(name = "tcp_flag_stat_urg_req_delta_caution")
	private Integer tcpFlagStatUrgReqDeltaCaution;

   	@Column(name = "tcp_flag_stat_urg_req_delta_warning")
	private Integer tcpFlagStatUrgReqDeltaWarning;

   	@Column(name = "tcp_flag_stat_urg_req_delta_danger")
	private Integer tcpFlagStatUrgReqDeltaDanger;

   	@Column(name = "tcp_flag_stat_urg_req_delta_obstacle")
	private Integer tcpFlagStatUrgReqDeltaObstacle;

   	@Column(name = "tcp_flag_stat_urg_res_delta_use")
	private Boolean tcpFlagStatUrgResDeltaUse;

   	@Column(name = "tcp_flag_stat_urg_res_delta_caution")
	private Integer tcpFlagStatUrgResDeltaCaution;

   	@Column(name = "tcp_flag_stat_urg_res_delta_warning")
	private Integer tcpFlagStatUrgResDeltaWarning;

   	@Column(name = "tcp_flag_stat_urg_res_delta_danger")
	private Integer tcpFlagStatUrgResDeltaDanger;

   	@Column(name = "tcp_flag_stat_urg_res_delta_obstacle")
	private Integer tcpFlagStatUrgResDeltaObstacle;

   	@Column(name = "req_conn_refused_use")
	private Boolean reqConnRefusedUse;

   	@Column(name = "req_conn_refused_caution")
	private Integer reqConnRefusedCaution;

   	@Column(name = "req_conn_refused_warning")
	private Integer reqConnRefusedWarning;

   	@Column(name = "req_conn_refused_danger")
	private Integer reqConnRefusedDanger;

   	@Column(name = "req_conn_refused_obstacle")
	private Integer reqConnRefusedObstacle;

   	@Column(name = "res_conn_refused_use")
	private Boolean resConnRefusedUse;

   	@Column(name = "res_conn_refused_caution")
	private Integer resConnRefusedCaution;

   	@Column(name = "res_conn_refused_warning")
	private Integer resConnRefusedWarning;

   	@Column(name = "res_conn_refused_danger")
	private Integer resConnRefusedDanger;

   	@Column(name = "res_conn_refused_obstacle")
	private Integer resConnRefusedObstacle;

   	@Column(name = "syn_timeout_use")
	private Boolean synTimeoutUse;

   	@Column(name = "syn_timeout_caution")
	private Integer synTimeoutCaution;

   	@Column(name = "syn_timeout_warning")
	private Integer synTimeoutWarning;

   	@Column(name = "syn_timeout_danger")
	private Integer synTimeoutDanger;

   	@Column(name = "syn_timeout_obstacle")
	private Integer synTimeoutObstacle;

   	@Column(name = "synack_timeout_use")
	private Boolean synackTimeoutUse;

   	@Column(name = "synack_timeout_caution")
	private Integer synackTimeoutCaution;

   	@Column(name = "synack_timeout_warning")
	private Integer synackTimeoutWarning;

   	@Column(name = "synack_timeout_danger")
	private Integer synackTimeoutDanger;

   	@Column(name = "synack_timeout_obstacle")
	private Integer synackTimeoutObstacle;

   	@Column(name = "req_ack_timeout_cnt_use")
	private Boolean reqAckTimeoutCntUse;

   	@Column(name = "req_ack_timeout_cnt_caution")
	private Integer reqAckTimeoutCntCaution;

   	@Column(name = "req_ack_timeout_cnt_warning")
	private Integer reqAckTimeoutCntWarning;

   	@Column(name = "req_ack_timeout_cnt_danger")
	private Integer reqAckTimeoutCntDanger;

   	@Column(name = "req_ack_timeout_cnt_obstacle")
	private Integer reqAckTimeoutCntObstacle;

   	@Column(name = "res_ack_timeout_cnt_use")
	private Boolean resAckTimeoutCntUse;

   	@Column(name = "res_ack_timeout_cnt_caution")
	private Integer resAckTimeoutCntCaution;

   	@Column(name = "res_ack_timeout_cnt_warning")
	private Integer resAckTimeoutCntWarning;

   	@Column(name = "res_ack_timeout_cnt_danger")
	private Integer resAckTimeoutCntDanger;

   	@Column(name = "res_ack_timeout_cnt_obstacle")
	private Integer resAckTimeoutCntObstacle;

   	@Column(name = "stopped_transaction_use")
	private Boolean stoppedTransactionUse;

   	@Column(name = "stopped_transaction_caution")
	private Integer stoppedTransactionCaution;

   	@Column(name = "stopped_transaction_warning")
	private Integer stoppedTransactionWarning;

   	@Column(name = "stopped_transaction_danger")
	private Integer stoppedTransactionDanger;

   	@Column(name = "stopped_transaction_obstacle")
	private Integer stoppedTransactionObstacle;

   	@Column(name = "session_timeout_use")
	private Boolean sessionTimeoutUse;

   	@Column(name = "session_timeout_caution")
	private Integer sessionTimeoutCaution;

   	@Column(name = "session_timeout_warning")
	private Integer sessionTimeoutWarning;

   	@Column(name = "session_timeout_danger")
	private Integer sessionTimeoutDanger;

   	@Column(name = "session_timeout_obstacle")
	private Integer sessionTimeoutObstacle;

   	@Column(name = "fin_wait_timeout_use")
	private Boolean finWaitTimeoutUse;

   	@Column(name = "fin_wait_timeout_caution")
	private Integer finWaitTimeoutCaution;

   	@Column(name = "fin_wait_timeout_warning")
	private Integer finWaitTimeoutWarning;

   	@Column(name = "fin_wait_timeout_danger")
	private Integer finWaitTimeoutDanger;

   	@Column(name = "fin_wait_timeout_obstacle")
	private Integer finWaitTimeoutObstacle;

   	@Column(name = "close_wait_timeout_use")
	private Boolean closeWaitTimeoutUse;

   	@Column(name = "close_wait_timeout_caution")
	private Integer closeWaitTimeoutCaution;

   	@Column(name = "close_wait_timeout_warning")
	private Integer closeWaitTimeoutWarning;

   	@Column(name = "close_wait_timeout_danger")
	private Integer closeWaitTimeoutDanger;

   	@Column(name = "close_wait_timeout_obstacle")
	private Integer closeWaitTimeoutObstacle;

   	@Column(name = "last_ack_timeout_use")
	private Boolean lastAckTimeoutUse;

   	@Column(name = "last_ack_timeout_caution")
	private Integer lastAckTimeoutCaution;

   	@Column(name = "last_ack_timeout_warning")
	private Integer lastAckTimeoutWarning;

   	@Column(name = "last_ack_timeout_danger")
	private Integer lastAckTimeoutDanger;

   	@Column(name = "last_ack_timeout_obstacle")
	private Integer lastAckTimeoutObstacle;


}
