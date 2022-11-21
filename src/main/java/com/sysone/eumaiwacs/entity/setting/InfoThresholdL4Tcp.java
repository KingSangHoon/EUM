package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="tbl_info_threshold_l4_tcp")
public class InfoThresholdL4Tcp {

    @Id
    @SequenceGenerator(name="tbl_info_threshold_l4_tcp_seq", sequenceName="tbl_info_threshold_l4_tcp_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_threshold_l4_tcp_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="type")
    private Integer type;

    @Column(name="deleted")
    private Boolean deleted;

    @Column(name="reg_date")
    private LocalDateTime regDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;

    @Column(name="first_writer")
    private String firstWriter;

    @Column(name="last_writer")
    private String lastWriter;

    @Column(name="policy_name")
    private String policyName;

    @Column(name="is_tcp_error")
    private Boolean isTcpError;

    @Column(name="len_pdu_req_tot_use")
    private Boolean lenPduReqTotUse;
    @Column(name="len_pdu_req_tot_level1")
    private Integer lenPduReqTotLevel1;
    @Column(name="len_pdu_req_tot_level2")
    private Integer lenPduReqTotLevel2;
    @Column(name="len_pdu_req_tot_level3")
    private Integer lenPduReqTotLevel3;
    @Column(name="len_pdu_req_tot_level4")
    private Integer lenPduReqTotLevel4;
    @Column(name="len_pdu_req_tot_level5")
    private Integer lenPduReqTotLevel5;

    @Column(name="len_pdu_res_tot_use")
    private Boolean lenPduResTotUse;
    @Column(name="len_pdu_res_tot_level1")
    private Integer lenPduResTotLevel1;
    @Column(name="len_pdu_res_tot_level2")
    private Integer lenPduResTotLevel2;
    @Column(name="len_pdu_res_tot_level3")
    private Integer lenPduResTotLevel3;
    @Column(name="len_pdu_res_tot_level4")
    private Integer lenPduResTotLevel4;
    @Column(name="len_pdu_res_tot_level5")
    private Integer lenPduResTotLevel5;

    @Column(name="pkts_pdu_req_tot_use")
    private Boolean pktsPduReqTotUse;
    @Column(name="pkts_pdu_req_tot_level1")
    private Integer pktsPduReqTotLevel1;
    @Column(name="pkts_pdu_req_tot_level2")
    private Integer pktsPduReqTotLevel2;
    @Column(name="pkts_pdu_req_tot_level3")
    private Integer pktsPduReqTotLevel3;
    @Column(name="pkts_pdu_req_tot_level4")
    private Integer pktsPduReqTotLevel4;
    @Column(name="pkts_pdu_req_tot_level5")
    private Integer pktsPduReqTotLevel5;

    @Column(name="pkts_pdu_res_tot_use")
    private Boolean pktsPduResTotUse;
    @Column(name="pkts_pdu_res_tot_level1")
    private Integer pktsPduResTotLevel1;
    @Column(name="pkts_pdu_res_tot_level2")
    private Integer pktsPduResTotLevel2;
    @Column(name="pkts_pdu_res_tot_level3")
    private Integer pktsPduResTotLevel3;
    @Column(name="pkts_pdu_res_tot_level4")
    private Integer pktsPduResTotLevel4;
    @Column(name="pkts_pdu_res_tot_level5")
    private Integer pktsPduResTotLevel5;

    @Column(name="len_pdu_req_per_sec_use")
    private Boolean lenPduReqPerSecUse;
    @Column(name="len_pdu_req_per_sec_level1")
    private Integer lenPduReqPerSecLevel1;
    @Column(name="len_pdu_req_per_sec_level2")
    private Integer lenPduReqPerSecLevel2;
    @Column(name="len_pdu_req_per_sec_level3")
    private Integer lenPduReqPerSecLevel3;
    @Column(name="len_pdu_req_per_sec_level4")
    private Integer lenPduReqPerSecLevel4;
    @Column(name="len_pdu_req_per_sec_level5")
    private Integer lenPduReqPerSecLevel5;

    @Column(name="len_pdu_res_per_sec_use")
    private Boolean lenPduResPerSecUse;
    @Column(name="len_pdu_res_per_sec_level1")
    private Integer lenPduResPerSecLevel1;
    @Column(name="len_pdu_res_per_sec_level2")
    private Integer lenPduResPerSecLevel2;
    @Column(name="len_pdu_res_per_sec_level3")
    private Integer lenPduResPerSecLevel3;
    @Column(name="len_pdu_res_per_sec_level4")
    private Integer lenPduResPerSecLevel4;
    @Column(name="len_pdu_res_per_sec_level5")
    private Integer lenPduResPerSecLevel5;

    @Column(name="pkts_pdu_req_per_sec_use")
    private Boolean pktsPduReqPerSecUse;
    @Column(name="pkts_pdu_req_per_sec_level1")
    private Integer pktsPduReqPerSecLevel1;
    @Column(name="pkts_pdu_req_per_sec_level2")
    private Integer pktsPduReqPerSecLevel2;
    @Column(name="pkts_pdu_req_per_sec_level3")
    private Integer pktsPduReqPerSecLevel3;
    @Column(name="pkts_pdu_req_per_sec_level4")
    private Integer pktsPduReqPerSecLevel4;
    @Column(name="pkts_pdu_req_per_sec_level5")
    private Integer pktsPduReqPerSecLevel5;

    @Column(name="pkts_pdu_res_per_sec_use")
    private Boolean pktsPduResPerSecUse;
    @Column(name="pkts_pdu_res_per_sec_level1")
    private Integer pktsPduResPerSecLevel1;
    @Column(name="pkts_pdu_res_per_sec_level2")
    private Integer pktsPduResPerSecLevel2;
    @Column(name="pkts_pdu_res_per_sec_level3")
    private Integer pktsPduResPerSecLevel3;
    @Column(name="pkts_pdu_res_per_sec_level4")
    private Integer pktsPduResPerSecLevel4;
    @Column(name="pkts_pdu_res_per_sec_level5")
    private Integer pktsPduResPerSecLevel5;

    @Column(name="len_pdu_req_delta_use")
    private Boolean lenPduReqDeltaUse;
    @Column(name="len_pdu_req_delta_level1")
    private Integer lenPduReqDeltaLevel1;
    @Column(name="len_pdu_req_delta_level2")
    private Integer lenPduReqDeltaLevel2;
    @Column(name="len_pdu_req_delta_level3")
    private Integer lenPduReqDeltaLevel3;
    @Column(name="len_pdu_req_delta_level4")
    private Integer lenPduReqDeltaLevel4;
    @Column(name="len_pdu_req_delta_level5")
    private Integer lenPduReqDeltaLevel5;

    @Column(name="len_pdu_res_delta_use")
    private Boolean lenPduResDeltaUse;
    @Column(name="len_pdu_res_delta_level1")
    private Integer lenPduResDeltaLevel1;
    @Column(name="len_pdu_res_delta_level2")
    private Integer lenPduResDeltaLevel2;
    @Column(name="len_pdu_res_delta_level3")
    private Integer lenPduResDeltaLevel3;
    @Column(name="len_pdu_res_delta_level4")
    private Integer lenPduResDeltaLevel4;
    @Column(name="len_pdu_res_delta_level5")
    private Integer lenPduResDeltaLevel5;

    @Column(name="pkts_pdu_req_delta_use")
    private Boolean pktsPduReqDeltaUse;
    @Column(name="pkts_pdu_req_delta_level1")
    private Integer pktsPduReqDeltaLevel1;
    @Column(name="pkts_pdu_req_delta_level2")
    private Integer pktsPduReqDeltaLevel2;
    @Column(name="pkts_pdu_req_delta_level3")
    private Integer pktsPduReqDeltaLevel3;
    @Column(name="pkts_pdu_req_delta_level4")
    private Integer pktsPduReqDeltaLevel4;
    @Column(name="pkts_pdu_req_delta_level5")
    private Integer pktsPduReqDeltaLevel5;

    @Column(name="pkts_pdu_res_delta_use")
    private Boolean pktsPduResDeltaUse;
    @Column(name="pkts_pdu_res_delta_level1")
    private Integer pktsPduResDeltaLevel1;
    @Column(name="pkts_pdu_res_delta_level2")
    private Integer pktsPduResDeltaLevel2;
    @Column(name="pkts_pdu_res_delta_level3")
    private Integer pktsPduResDeltaLevel3;
    @Column(name="pkts_pdu_res_delta_level4")
    private Integer pktsPduResDeltaLevel4;
    @Column(name="pkts_pdu_res_delta_level5")
    private Integer pktsPduResDeltaLevel5;

    @Column(name="ts_rtt_syn_use")
    private Boolean tsRttSynUse;
    @Column(name="ts_rtt_syn_level1")
    private Integer tsRttSynLevel1;
    @Column(name="ts_rtt_syn_level2")
    private Integer tsRttSynLevel2;
    @Column(name="ts_rtt_syn_level3")
    private Integer tsRttSynLevel3;
    @Column(name="ts_rtt_syn_level4")
    private Integer tsRttSynLevel4;
    @Column(name="ts_rtt_syn_level5")
    private Integer tsRttSynLevel5;

    @Column(name="ts_rtt_syn_ack_use")
    private Boolean tsRttSynAckUse;
    @Column(name="ts_rtt_syn_ack_level1")
    private Integer tsRttSynAckLevel1;
    @Column(name="ts_rtt_syn_ack_level2")
    private Integer tsRttSynAckLevel2;
    @Column(name="ts_rtt_syn_ack_level3")
    private Integer tsRttSynAckLevel3;
    @Column(name="ts_rtt_syn_ack_level4")
    private Integer tsRttSynAckLevel4;
    @Column(name="ts_rtt_syn_ack_level5")
    private Integer tsRttSynAckLevel5;

    @Column(name="ts_rtt_first_ack_req_use")
    private Boolean tsRttFirstAckReqUse;
    @Column(name="ts_rtt_first_ack_req_level1")
    private Integer tsRttFirstAckReqLevel1;
    @Column(name="ts_rtt_first_ack_req_level2")
    private Integer tsRttFirstAckReqLevel2;
    @Column(name="ts_rtt_first_ack_req_level3")
    private Integer tsRttFirstAckReqLevel3;
    @Column(name="ts_rtt_first_ack_req_level4")
    private Integer tsRttFirstAckReqLevel4;
    @Column(name="ts_rtt_first_ack_req_level5")
    private Integer tsRttFirstAckReqLevel5;

    @Column(name="ts_rtt_first_ack_res_use")
    private Boolean tsRttFirstAckResUse;
    @Column(name="ts_rtt_first_ack_res_level1")
    private Integer tsRttFirstAckResLevel1;
    @Column(name="ts_rtt_first_ack_res_level2")
    private Integer tsRttFirstAckResLevel2;
    @Column(name="ts_rtt_first_ack_res_level3")
    private Integer tsRttFirstAckResLevel3;
    @Column(name="ts_rtt_first_ack_res_level4")
    private Integer tsRttFirstAckResLevel4;
    @Column(name="ts_rtt_first_ack_res_level5")
    private Integer tsRttFirstAckResLevel5;

    @Column(name="ts_rtt_last_ack_req_use")
    private Boolean tsRttLastAckReqUse;
    @Column(name="ts_rtt_last_ack_req_level1")
    private Integer tsRttLastAckReqLevel1;
    @Column(name="ts_rtt_last_ack_req_level2")
    private Integer tsRttLastAckReqLevel2;
    @Column(name="ts_rtt_last_ack_req_level3")
    private Integer tsRttLastAckReqLevel3;
    @Column(name="ts_rtt_last_ack_req_level4")
    private Integer tsRttLastAckReqLevel4;
    @Column(name="ts_rtt_last_ack_req_level5")
    private Integer tsRttLastAckReqLevel5;

    @Column(name="ts_rtt_last_ack_res_use")
    private Boolean tsRttLastAckResUse;
    @Column(name="ts_rtt_last_ack_res_level1")
    private Integer tsRttLastAckResLevel1;
    @Column(name="ts_rtt_last_ack_res_level2")
    private Integer tsRttLastAckResLevel2;
    @Column(name="ts_rtt_last_ack_res_level3")
    private Integer tsRttLastAckResLevel3;
    @Column(name="ts_rtt_last_ack_res_level4")
    private Integer tsRttLastAckResLevel4;
    @Column(name="ts_rtt_last_ack_res_level5")
    private Integer tsRttLastAckResLevel5;

    @Column(name="ts_req_making_use")
    private Boolean tsReqMakingUse;
    @Column(name="ts_req_making_level1")
    private Integer tsReqMakingLevel1;
    @Column(name="ts_req_making_level2")
    private Integer tsReqMakingLevel2;
    @Column(name="ts_req_making_level3")
    private Integer tsReqMakingLevel3;
    @Column(name="ts_req_making_level4")
    private Integer tsReqMakingLevel4;
    @Column(name="ts_req_making_level5")
    private Integer tsReqMakingLevel5;

    @Column(name="ack_rtt_sum_req_use")
    private Boolean ackRttSumReqUse;
    @Column(name="ack_rtt_sum_req_level1")
    private Integer ackRttSumReqLevel1;
    @Column(name="ack_rtt_sum_req_level2")
    private Integer ackRttSumReqLevel2;
    @Column(name="ack_rtt_sum_req_level3")
    private Integer ackRttSumReqLevel3;
    @Column(name="ack_rtt_sum_req_level4")
    private Integer ackRttSumReqLevel4;
    @Column(name="ack_rtt_sum_req_level5")
    private Integer ackRttSumReqLevel5;

    @Column(name="ack_rtt_sum_res_use")
    private Boolean ackRttSumResUse;
    @Column(name="ack_rtt_sum_res_level1")
    private Integer ackRttSumResLevel1;
    @Column(name="ack_rtt_sum_res_level2")
    private Integer ackRttSumResLevel2;
    @Column(name="ack_rtt_sum_res_level3")
    private Integer ackRttSumResLevel3;
    @Column(name="ack_rtt_sum_res_level4")
    private Integer ackRttSumResLevel4;
    @Column(name="ack_rtt_sum_res_level5")
    private Integer ackRttSumResLevel5;

    @Column(name="ack_rto_sum_req_use")
    private Boolean ackRtoSumReqUse;
    @Column(name="ack_rto_sum_req_level1")
    private Integer ackRtoSumReqLevel1;
    @Column(name="ack_rto_sum_req_level2")
    private Integer ackRtoSumReqLevel2;
    @Column(name="ack_rto_sum_req_level3")
    private Integer ackRtoSumReqLevel3;
    @Column(name="ack_rto_sum_req_level4")
    private Integer ackRtoSumReqLevel4;
    @Column(name="ack_rto_sum_req_level5")
    private Integer ackRtoSumReqLevel5;

    @Column(name="ack_rto_sum_res_use")
    private Boolean ackRtoSumResUse;
    @Column(name="ack_rto_sum_res_level1")
    private Integer ackRtoSumResLevel1;
    @Column(name="ack_rto_sum_res_level2")
    private Integer ackRtoSumResLevel2;
    @Column(name="ack_rto_sum_res_level3")
    private Integer ackRtoSumResLevel3;
    @Column(name="ack_rto_sum_res_level4")
    private Integer ackRtoSumResLevel4;
    @Column(name="ack_rto_sum_res_level5")
    private Integer ackRtoSumResLevel5;

    @Column(name="ack_rtt_cnt_req_use")
    private Boolean ackRttCntReqUse;
    @Column(name="ack_rtt_cnt_req_level1")
    private Integer ackRttCntReqLevel1;
    @Column(name="ack_rtt_cnt_req_level2")
    private Integer ackRttCntReqLevel2;
    @Column(name="ack_rtt_cnt_req_level3")
    private Integer ackRttCntReqLevel3;
    @Column(name="ack_rtt_cnt_req_level4")
    private Integer ackRttCntReqLevel4;
    @Column(name="ack_rtt_cnt_req_level5")
    private Integer ackRttCntReqLevel5;

    @Column(name="ack_rtt_cnt_res_use")
    private Boolean ackRttCntResUse;
    @Column(name="ack_rtt_cnt_res_level1")
    private Integer ackRttCntResLevel1;
    @Column(name="ack_rtt_cnt_res_level2")
    private Integer ackRttCntResLevel2;
    @Column(name="ack_rtt_cnt_res_level3")
    private Integer ackRttCntResLevel3;
    @Column(name="ack_rtt_cnt_res_level4")
    private Integer ackRttCntResLevel4;
    @Column(name="ack_rtt_cnt_res_level5")
    private Integer ackRttCntResLevel5;

    @Column(name="ack_rto_cnt_req_use")
    private Boolean ackRtoCntReqUse;
    @Column(name="ack_rto_cnt_req_level1")
    private Integer ackRtoCntReqLevel1;
    @Column(name="ack_rto_cnt_req_level2")
    private Integer ackRtoCntReqLevel2;
    @Column(name="ack_rto_cnt_req_level3")
    private Integer ackRtoCntReqLevel3;
    @Column(name="ack_rto_cnt_req_level4")
    private Integer ackRtoCntReqLevel4;
    @Column(name="ack_rto_cnt_req_level5")
    private Integer ackRtoCntReqLevel5;

    @Column(name="ack_rto_cnt_res_use")
    private Boolean ackRtoCntResUse;
    @Column(name="ack_rto_cnt_res_level1")
    private Integer ackRtoCntResLevel1;
    @Column(name="ack_rto_cnt_res_level2")
    private Integer ackRtoCntResLevel2;
    @Column(name="ack_rto_cnt_res_level3")
    private Integer ackRtoCntResLevel3;
    @Column(name="ack_rto_cnt_res_level4")
    private Integer ackRtoCntResLevel4;
    @Column(name="ack_rto_cnt_res_level5")
    private Integer ackRtoCntResLevel5;

    @Column(name="ts_syn_delay_use")
    private Boolean tsSynDelayUse;
    @Column(name="ts_syn_delay_level1")
    private Integer tsSynDelayLevel1;
    @Column(name="ts_syn_delay_level2")
    private Integer tsSynDelayLevel2;
    @Column(name="ts_syn_delay_level3")
    private Integer tsSynDelayLevel3;
    @Column(name="ts_syn_delay_level4")
    private Integer tsSynDelayLevel4;
    @Column(name="ts_syn_delay_level5")
    private Integer tsSynDelayLevel5;

    @Column(name="ts_synack_delay_use")
    private Boolean tsSynackDelayUse;
    @Column(name="ts_synack_delay_level1")
    private Integer tsSynackDelayLevel1;
    @Column(name="ts_synack_delay_level2")
    private Integer tsSynackDelayLevel2;
    @Column(name="ts_synack_delay_level3")
    private Integer tsSynackDelayLevel3;
    @Column(name="ts_synack_delay_level4")
    private Integer tsSynackDelayLevel4;
    @Column(name="ts_synack_delay_level5")
    private Integer tsSynackDelayLevel5;

    @Column(name="ts_ack_delay_first_req_use")
    private Boolean tsAckDelayFirstReqUse;
    @Column(name="ts_ack_delay_first_req_level1")
    private Integer tsAckDelayFirstReqLevel1;
    @Column(name="ts_ack_delay_first_req_level2")
    private Integer tsAckDelayFirstReqLevel2;
    @Column(name="ts_ack_delay_first_req_level3")
    private Integer tsAckDelayFirstReqLevel3;
    @Column(name="ts_ack_delay_first_req_level4")
    private Integer tsAckDelayFirstReqLevel4;
    @Column(name="ts_ack_delay_first_req_level5")
    private Integer tsAckDelayFirstReqLevel5;

    @Column(name="ts_ack_delay_first_res_use")
    private Boolean tsAckDelayFirstResUse;
    @Column(name="ts_ack_delay_first_res_level1")
    private Integer tsAckDelayFirstResLevel1;
    @Column(name="ts_ack_delay_first_res_level2")
    private Integer tsAckDelayFirstResLevel2;
    @Column(name="ts_ack_delay_first_res_level3")
    private Integer tsAckDelayFirstResLevel3;
    @Column(name="ts_ack_delay_first_res_level4")
    private Integer tsAckDelayFirstResLevel4;
    @Column(name="ts_ack_delay_first_res_level5")
    private Integer tsAckDelayFirstResLevel5;

    @Column(name="ts_ack_delay_last_req_use")
    private Boolean tsAckDelayLastReqUse;
    @Column(name="ts_ack_delay_last_req_level1")
    private Integer tsAckDelayLastReqLevel1;
    @Column(name="ts_ack_delay_last_req_level2")
    private Integer tsAckDelayLastReqLevel2;
    @Column(name="ts_ack_delay_last_req_level3")
    private Integer tsAckDelayLastReqLevel3;
    @Column(name="ts_ack_delay_last_req_level4")
    private Integer tsAckDelayLastReqLevel4;
    @Column(name="ts_ack_delay_last_req_level5")
    private Integer tsAckDelayLastReqLevel5;

    @Column(name="ts_ack_delay_last_res_use")
    private Boolean tsAckDelayLastResUse;
    @Column(name="ts_ack_delay_last_res_level1")
    private Integer tsAckDelayLastResLevel1;
    @Column(name="ts_ack_delay_last_res_level2")
    private Integer tsAckDelayLastResLevel2;
    @Column(name="ts_ack_delay_last_res_level3")
    private Integer tsAckDelayLastResLevel3;
    @Column(name="ts_ack_delay_last_res_level4")
    private Integer tsAckDelayLastResLevel4;
    @Column(name="ts_ack_delay_last_res_level5")
    private Integer tsAckDelayLastResLevel5;

    @Column(name="ts_syn_use")
    private Boolean tsSynUse;
    @Column(name="ts_syn_level1")
    private Integer tsSynLevel1;
    @Column(name="ts_syn_level2")
    private Integer tsSynLevel2;
    @Column(name="ts_syn_level3")
    private Integer tsSynLevel3;
    @Column(name="ts_syn_level4")
    private Integer tsSynLevel4;
    @Column(name="ts_syn_level5")
    private Integer tsSynLevel5;

    @Column(name="ts_syn_ack_use")
    private Boolean tsSynAckUse;
    @Column(name="ts_syn_ack_level1")
    private Integer tsSynAckLevel1;
    @Column(name="ts_syn_ack_level2")
    private Integer tsSynAckLevel2;
    @Column(name="ts_syn_ack_level3")
    private Integer tsSynAckLevel3;
    @Column(name="ts_syn_ack_level4")
    private Integer tsSynAckLevel4;
    @Column(name="ts_syn_ack_level5")
    private Integer tsSynAckLevel5;

    @Column(name="ts_first_ack_use")
    private Boolean tsFirstAckUse;
    @Column(name="ts_first_ack_level1")
    private Integer tsFirstAckLevel1;
    @Column(name="ts_first_ack_level2")
    private Integer tsFirstAckLevel2;
    @Column(name="ts_first_ack_level3")
    private Integer tsFirstAckLevel3;
    @Column(name="ts_first_ack_level4")
    private Integer tsFirstAckLevel4;
    @Column(name="ts_first_ack_level5")
    private Integer tsFirstAckLevel5;

    @Column(name="ts_first_ack_req_use")
    private Boolean tsFirstAckReqUse;
    @Column(name="ts_first_ack_req_level1")
    private Integer tsFirstAckReqLevel1;
    @Column(name="ts_first_ack_req_level2")
    private Integer tsFirstAckReqLevel2;
    @Column(name="ts_first_ack_req_level3")
    private Integer tsFirstAckReqLevel3;
    @Column(name="ts_first_ack_req_level4")
    private Integer tsFirstAckReqLevel4;
    @Column(name="ts_first_ack_req_level5")
    private Integer tsFirstAckReqLevel5;

    @Column(name="ts_first_ack_req_ack_use")
    private Boolean tsFirstAckReqAckUse;
    @Column(name="ts_first_ack_req_ack_level1")
    private Integer tsFirstAckReqAckLevel1;
    @Column(name="ts_first_ack_req_ack_level2")
    private Integer tsFirstAckReqAckLevel2;
    @Column(name="ts_first_ack_req_ack_level3")
    private Integer tsFirstAckReqAckLevel3;
    @Column(name="ts_first_ack_req_ack_level4")
    private Integer tsFirstAckReqAckLevel4;
    @Column(name="ts_first_ack_req_ack_level5")
    private Integer tsFirstAckReqAckLevel5;

    @Column(name="ts_first_ack_res_use")
    private Boolean tsFirstAckResUse;
    @Column(name="ts_first_ack_res_level1")
    private Integer tsFirstAckResLevel1;
    @Column(name="ts_first_ack_res_level2")
    private Integer tsFirstAckResLevel2;
    @Column(name="ts_first_ack_res_level3")
    private Integer tsFirstAckResLevel3;
    @Column(name="ts_first_ack_res_level4")
    private Integer tsFirstAckResLevel4;
    @Column(name="ts_first_ack_res_level5")
    private Integer tsFirstAckResLevel5;

    @Column(name="ts_first_ack_res_ack_use")
    private Boolean tsFirstAckResAckUse;
    @Column(name="ts_first_ack_res_ack_level1")
    private Integer tsFirstAckResAckLevel1;
    @Column(name="ts_first_ack_res_ack_level2")
    private Integer tsFirstAckResAckLevel2;
    @Column(name="ts_first_ack_res_ack_level3")
    private Integer tsFirstAckResAckLevel3;
    @Column(name="ts_first_ack_res_ack_level4")
    private Integer tsFirstAckResAckLevel4;
    @Column(name="ts_first_ack_res_ack_level5")
    private Integer tsFirstAckResAckLevel5;

    @Column(name="ts_first_push_req_use")
    private Boolean tsFirstPushReqUse;
    @Column(name="ts_first_push_req_level1")
    private Integer tsFirstPushReqLevel1;
    @Column(name="ts_first_push_req_level2")
    private Integer tsFirstPushReqLevel2;
    @Column(name="ts_first_push_req_level3")
    private Integer tsFirstPushReqLevel3;
    @Column(name="ts_first_push_req_level4")
    private Integer tsFirstPushReqLevel4;
    @Column(name="ts_first_push_req_level5")
    private Integer tsFirstPushReqLevel5;

    @Column(name="ts_first_push_res_use")
    private Boolean tsFirstPushResUse;
    @Column(name="ts_first_push_res_level1")
    private Integer tsFirstPushResLevel1;
    @Column(name="ts_first_push_res_level2")
    private Integer tsFirstPushResLevel2;
    @Column(name="ts_first_push_res_level3")
    private Integer tsFirstPushResLevel3;
    @Column(name="ts_first_push_res_level4")
    private Integer tsFirstPushResLevel4;
    @Column(name="ts_first_push_res_level5")
    private Integer tsFirstPushResLevel5;

    @Column(name="ack_rtt_min_req_tot_use")
    private Boolean ackRttMinReqTotUse;
    @Column(name="ack_rtt_min_req_tot_level1")
    private Integer ackRttMinReqTotLevel1;
    @Column(name="ack_rtt_min_req_tot_level2")
    private Integer ackRttMinReqTotLevel2;
    @Column(name="ack_rtt_min_req_tot_level3")
    private Integer ackRttMinReqTotLevel3;
    @Column(name="ack_rtt_min_req_tot_level4")
    private Integer ackRttMinReqTotLevel4;
    @Column(name="ack_rtt_min_req_tot_level5")
    private Integer ackRttMinReqTotLevel5;

    @Column(name="ack_rtt_min_res_tot_use")
    private Boolean ackRttMinResTotUse;
    @Column(name="ack_rtt_min_res_tot_level1")
    private Integer ackRttMinResTotLevel1;
    @Column(name="ack_rtt_min_res_tot_level2")
    private Integer ackRttMinResTotLevel2;
    @Column(name="ack_rtt_min_res_tot_level3")
    private Integer ackRttMinResTotLevel3;
    @Column(name="ack_rtt_min_res_tot_level4")
    private Integer ackRttMinResTotLevel4;
    @Column(name="ack_rtt_min_res_tot_level5")
    private Integer ackRttMinResTotLevel5;

    @Column(name="ack_rtt_max_req_tot_use")
    private Boolean ackRttMaxReqTotUse;
    @Column(name="ack_rtt_max_req_tot_level1")
    private Integer ackRttMaxReqTotLevel1;
    @Column(name="ack_rtt_max_req_tot_level2")
    private Integer ackRttMaxReqTotLevel2;
    @Column(name="ack_rtt_max_req_tot_level3")
    private Integer ackRttMaxReqTotLevel3;
    @Column(name="ack_rtt_max_req_tot_level4")
    private Integer ackRttMaxReqTotLevel4;
    @Column(name="ack_rtt_max_req_tot_level5")
    private Integer ackRttMaxReqTotLevel5;

    @Column(name="ack_rtt_max_res_tot_use")
    private Boolean ackRttMaxResTotUse;
    @Column(name="ack_rtt_max_res_tot_level1")
    private Integer ackRttMaxResTotLevel1;
    @Column(name="ack_rtt_max_res_tot_level2")
    private Integer ackRttMaxResTotLevel2;
    @Column(name="ack_rtt_max_res_tot_level3")
    private Integer ackRttMaxResTotLevel3;
    @Column(name="ack_rtt_max_res_tot_level4")
    private Integer ackRttMaxResTotLevel4;
    @Column(name="ack_rtt_max_res_tot_level5")
    private Integer ackRttMaxResTotLevel5;

    @Column(name="ack_rtt_min_req_curr_use")
    private Boolean ackRttMinReqCurrUse;
    @Column(name="ack_rtt_min_req_curr_level1")
    private Integer ackRttMinReqCurrLevel1;
    @Column(name="ack_rtt_min_req_curr_level2")
    private Integer ackRttMinReqCurrLevel2;
    @Column(name="ack_rtt_min_req_curr_level3")
    private Integer ackRttMinReqCurrLevel3;
    @Column(name="ack_rtt_min_req_curr_level4")
    private Integer ackRttMinReqCurrLevel4;
    @Column(name="ack_rtt_min_req_curr_level5")
    private Integer ackRttMinReqCurrLevel5;

    @Column(name="ack_rtt_min_res_curr_use")
    private Boolean ackRttMinResCurrUse;
    @Column(name="ack_rtt_min_res_curr_level1")
    private Integer ackRttMinResCurrLevel1;
    @Column(name="ack_rtt_min_res_curr_level2")
    private Integer ackRttMinResCurrLevel2;
    @Column(name="ack_rtt_min_res_curr_level3")
    private Integer ackRttMinResCurrLevel3;
    @Column(name="ack_rtt_min_res_curr_level4")
    private Integer ackRttMinResCurrLevel4;
    @Column(name="ack_rtt_min_res_curr_level5")
    private Integer ackRttMinResCurrLevel5;

    @Column(name="ack_rtt_max_req_curr_use")
    private Boolean ackRttMaxReqCurrUse;
    @Column(name="ack_rtt_max_req_curr_level1")
    private Integer ackRttMaxReqCurrLevel1;
    @Column(name="ack_rtt_max_req_curr_level2")
    private Integer ackRttMaxReqCurrLevel2;
    @Column(name="ack_rtt_max_req_curr_level3")
    private Integer ackRttMaxReqCurrLevel3;
    @Column(name="ack_rtt_max_req_curr_level4")
    private Integer ackRttMaxReqCurrLevel4;
    @Column(name="ack_rtt_max_req_curr_level5")
    private Integer ackRttMaxReqCurrLevel5;

    @Column(name="ack_rtt_max_res_curr_use")
    private Boolean ackRttMaxResCurrUse;
    @Column(name="ack_rtt_max_res_curr_level1")
    private Integer ackRttMaxResCurrLevel1;
    @Column(name="ack_rtt_max_res_curr_level2")
    private Integer ackRttMaxResCurrLevel2;
    @Column(name="ack_rtt_max_res_curr_level3")
    private Integer ackRttMaxResCurrLevel3;
    @Column(name="ack_rtt_max_res_curr_level4")
    private Integer ackRttMaxResCurrLevel4;
    @Column(name="ack_rtt_max_res_curr_level5")
    private Integer ackRttMaxResCurrLevel5;

    @Column(name="tcp_flag_stat_fin_req_tot_use")
    private Boolean tcpFlagStatFinReqTotUse;
    @Column(name="tcp_flag_stat_fin_req_tot_level1")
    private Integer tcpFlagStatFinReqTotLevel1;
    @Column(name="tcp_flag_stat_fin_req_tot_level2")
    private Integer tcpFlagStatFinReqTotLevel2;
    @Column(name="tcp_flag_stat_fin_req_tot_level3")
    private Integer tcpFlagStatFinReqTotLevel3;
    @Column(name="tcp_flag_stat_fin_req_tot_level4")
    private Integer tcpFlagStatFinReqTotLevel4;
    @Column(name="tcp_flag_stat_fin_req_tot_level5")
    private Integer tcpFlagStatFinReqTotLevel5;

    @Column(name="tcp_flag_stat_fin_res_tot_use")
    private Boolean tcpFlagStatFinResTotUse;
    @Column(name="tcp_flag_stat_fin_res_tot_level1")
    private Integer tcpFlagStatFinResTotLevel1;
    @Column(name="tcp_flag_stat_fin_res_tot_level2")
    private Integer tcpFlagStatFinResTotLevel2;
    @Column(name="tcp_flag_stat_fin_res_tot_level3")
    private Integer tcpFlagStatFinResTotLevel3;
    @Column(name="tcp_flag_stat_fin_res_tot_level4")
    private Integer tcpFlagStatFinResTotLevel4;
    @Column(name="tcp_flag_stat_fin_res_tot_level5")
    private Integer tcpFlagStatFinResTotLevel5;

    @Column(name="tcp_flag_stat_syn_req_tot_use")
    private Boolean tcpFlagStatSynReqTotUse;
    @Column(name="tcp_flag_stat_syn_req_tot_level1")
    private Integer tcpFlagStatSynReqTotLevel1;
    @Column(name="tcp_flag_stat_syn_req_tot_level2")
    private Integer tcpFlagStatSynReqTotLevel2;
    @Column(name="tcp_flag_stat_syn_req_tot_level3")
    private Integer tcpFlagStatSynReqTotLevel3;
    @Column(name="tcp_flag_stat_syn_req_tot_level4")
    private Integer tcpFlagStatSynReqTotLevel4;
    @Column(name="tcp_flag_stat_syn_req_tot_level5")
    private Integer tcpFlagStatSynReqTotLevel5;

    @Column(name="tcp_flag_stat_syn_res_tot_use")
    private Boolean tcpFlagStatSynResTotUse;
    @Column(name="tcp_flag_stat_syn_res_tot_level1")
    private Integer tcpFlagStatSynResTotLevel1;
    @Column(name="tcp_flag_stat_syn_res_tot_level2")
    private Integer tcpFlagStatSynResTotLevel2;
    @Column(name="tcp_flag_stat_syn_res_tot_level3")
    private Integer tcpFlagStatSynResTotLevel3;
    @Column(name="tcp_flag_stat_syn_res_tot_level4")
    private Integer tcpFlagStatSynResTotLevel4;
    @Column(name="tcp_flag_stat_syn_res_tot_level5")
    private Integer tcpFlagStatSynResTotLevel5;

    @Column(name="tcp_flag_stat_rst_req_tot_use")
    private Boolean tcpFlagStatRstReqTotUse;
    @Column(name="tcp_flag_stat_rst_req_tot_level1")
    private Integer tcpFlagStatRstReqTotLevel1;
    @Column(name="tcp_flag_stat_rst_req_tot_level2")
    private Integer tcpFlagStatRstReqTotLevel2;
    @Column(name="tcp_flag_stat_rst_req_tot_level3")
    private Integer tcpFlagStatRstReqTotLevel3;
    @Column(name="tcp_flag_stat_rst_req_tot_level4")
    private Integer tcpFlagStatRstReqTotLevel4;
    @Column(name="tcp_flag_stat_rst_req_tot_level5")
    private Integer tcpFlagStatRstReqTotLevel5;

    @Column(name="tcp_flag_stat_rst_res_tot_use")
    private Boolean tcpFlagStatRstResTotUse;
    @Column(name="tcp_flag_stat_rst_res_tot_level1")
    private Integer tcpFlagStatRstResTotLevel1;
    @Column(name="tcp_flag_stat_rst_res_tot_level2")
    private Integer tcpFlagStatRstResTotLevel2;
    @Column(name="tcp_flag_stat_rst_res_tot_level3")
    private Integer tcpFlagStatRstResTotLevel3;
    @Column(name="tcp_flag_stat_rst_res_tot_level4")
    private Integer tcpFlagStatRstResTotLevel4;
    @Column(name="tcp_flag_stat_rst_res_tot_level5")
    private Integer tcpFlagStatRstResTotLevel5;

    @Column(name="tcp_flag_stat_psh_req_tot_use")
    private Boolean tcpFlagStatPshReqTotUse;
    @Column(name="tcp_flag_stat_psh_req_tot_level1")
    private Integer tcpFlagStatPshReqTotLevel1;
    @Column(name="tcp_flag_stat_psh_req_tot_level2")
    private Integer tcpFlagStatPshReqTotLevel2;
    @Column(name="tcp_flag_stat_psh_req_tot_level3")
    private Integer tcpFlagStatPshReqTotLevel3;
    @Column(name="tcp_flag_stat_psh_req_tot_level4")
    private Integer tcpFlagStatPshReqTotLevel4;
    @Column(name="tcp_flag_stat_psh_req_tot_level5")
    private Integer tcpFlagStatPshReqTotLevel5;

    @Column(name="tcp_flag_stat_psh_res_tot_use")
    private Boolean tcpFlagStatPshResTotUse;
    @Column(name="tcp_flag_stat_psh_res_tot_level1")
    private Integer tcpFlagStatPshResTotLevel1;
    @Column(name="tcp_flag_stat_psh_res_tot_level2")
    private Integer tcpFlagStatPshResTotLevel2;
    @Column(name="tcp_flag_stat_psh_res_tot_level3")
    private Integer tcpFlagStatPshResTotLevel3;
    @Column(name="tcp_flag_stat_psh_res_tot_level4")
    private Integer tcpFlagStatPshResTotLevel4;
    @Column(name="tcp_flag_stat_psh_res_tot_level5")
    private Integer tcpFlagStatPshResTotLevel5;

    @Column(name="tcp_flag_stat_ack_req_tot_use")
    private Boolean tcpFlagStatAckReqTotUse;
    @Column(name="tcp_flag_stat_ack_req_tot_level1")
    private Integer tcpFlagStatAckReqTotLevel1;
    @Column(name="tcp_flag_stat_ack_req_tot_level2")
    private Integer tcpFlagStatAckReqTotLevel2;
    @Column(name="tcp_flag_stat_ack_req_tot_level3")
    private Integer tcpFlagStatAckReqTotLevel3;
    @Column(name="tcp_flag_stat_ack_req_tot_level4")
    private Integer tcpFlagStatAckReqTotLevel4;
    @Column(name="tcp_flag_stat_ack_req_tot_level5")
    private Integer tcpFlagStatAckReqTotLevel5;

    @Column(name="tcp_flag_stat_ack_res_tot_use")
    private Boolean tcpFlagStatAckResTotUse;
    @Column(name="tcp_flag_stat_ack_res_tot_level1")
    private Integer tcpFlagStatAckResTotLevel1;
    @Column(name="tcp_flag_stat_ack_res_tot_level2")
    private Integer tcpFlagStatAckResTotLevel2;
    @Column(name="tcp_flag_stat_ack_res_tot_level3")
    private Integer tcpFlagStatAckResTotLevel3;
    @Column(name="tcp_flag_stat_ack_res_tot_level4")
    private Integer tcpFlagStatAckResTotLevel4;
    @Column(name="tcp_flag_stat_ack_res_tot_level5")
    private Integer tcpFlagStatAckResTotLevel5;

    @Column(name="tcp_flag_stat_urg_req_tot_use")
    private Boolean tcpFlagStatUrgReqTotUse;
    @Column(name="tcp_flag_stat_urg_req_tot_level1")
    private Integer tcpFlagStatUrgReqTotLevel1;
    @Column(name="tcp_flag_stat_urg_req_tot_level2")
    private Integer tcpFlagStatUrgReqTotLevel2;
    @Column(name="tcp_flag_stat_urg_req_tot_level3")
    private Integer tcpFlagStatUrgReqTotLevel3;
    @Column(name="tcp_flag_stat_urg_req_tot_level4")
    private Integer tcpFlagStatUrgReqTotLevel4;
    @Column(name="tcp_flag_stat_urg_req_tot_level5")
    private Integer tcpFlagStatUrgReqTotLevel5;

    @Column(name="tcp_flag_stat_urg_res_tot_use")
    private Boolean tcpFlagStatUrgResTotUse;
    @Column(name="tcp_flag_stat_urg_res_tot_level1")
    private Integer tcpFlagStatUrgResTotLevel1;
    @Column(name="tcp_flag_stat_urg_res_tot_level2")
    private Integer tcpFlagStatUrgResTotLevel2;
    @Column(name="tcp_flag_stat_urg_res_tot_level3")
    private Integer tcpFlagStatUrgResTotLevel3;
    @Column(name="tcp_flag_stat_urg_res_tot_level4")
    private Integer tcpFlagStatUrgResTotLevel4;
    @Column(name="tcp_flag_stat_urg_res_tot_level5")
    private Integer tcpFlagStatUrgResTotLevel5;

    @Column(name="tcp_flag_stat_fin_req_per_sec_use")
    private Boolean tcpFlagStatFinReqPerSecUse;
    @Column(name="tcp_flag_stat_fin_req_per_sec_level1")
    private Integer tcpFlagStatFinReqPerSecLevel1;
    @Column(name="tcp_flag_stat_fin_req_per_sec_level2")
    private Integer tcpFlagStatFinReqPerSecLevel2;
    @Column(name="tcp_flag_stat_fin_req_per_sec_level3")
    private Integer tcpFlagStatFinReqPerSecLevel3;
    @Column(name="tcp_flag_stat_fin_req_per_sec_level4")
    private Integer tcpFlagStatFinReqPerSecLevel4;
    @Column(name="tcp_flag_stat_fin_req_per_sec_level5")
    private Integer tcpFlagStatFinReqPerSecLevel5;

    @Column(name="tcp_flag_stat_fin_res_per_sec_use")
    private Boolean tcpFlagStatFinResPerSecUse;
    @Column(name="tcp_flag_stat_fin_res_per_sec_level1")
    private Integer tcpFlagStatFinResPerSecLevel1;
    @Column(name="tcp_flag_stat_fin_res_per_sec_level2")
    private Integer tcpFlagStatFinResPerSecLevel2;
    @Column(name="tcp_flag_stat_fin_res_per_sec_level3")
    private Integer tcpFlagStatFinResPerSecLevel3;
    @Column(name="tcp_flag_stat_fin_res_per_sec_level4")
    private Integer tcpFlagStatFinResPerSecLevel4;
    @Column(name="tcp_flag_stat_fin_res_per_sec_level5")
    private Integer tcpFlagStatFinResPerSecLevel5;

    @Column(name="tcp_flag_stat_syn_req_per_sec_use")
    private Boolean tcpFlagStatSynReqPerSecUse;
    @Column(name="tcp_flag_stat_syn_req_per_sec_level1")
    private Integer tcpFlagStatSynReqPerSecLevel1;
    @Column(name="tcp_flag_stat_syn_req_per_sec_level2")
    private Integer tcpFlagStatSynReqPerSecLevel2;
    @Column(name="tcp_flag_stat_syn_req_per_sec_level3")
    private Integer tcpFlagStatSynReqPerSecLevel3;
    @Column(name="tcp_flag_stat_syn_req_per_sec_level4")
    private Integer tcpFlagStatSynReqPerSecLevel4;
    @Column(name="tcp_flag_stat_syn_req_per_sec_level5")
    private Integer tcpFlagStatSynReqPerSecLevel5;

    @Column(name="tcp_flag_stat_syn_res_per_sec_use")
    private Boolean tcpFlagStatSynResPerSecUse;
    @Column(name="tcp_flag_stat_syn_res_per_sec_level1")
    private Integer tcpFlagStatSynResPerSecLevel1;
    @Column(name="tcp_flag_stat_syn_res_per_sec_level2")
    private Integer tcpFlagStatSynResPerSecLevel2;
    @Column(name="tcp_flag_stat_syn_res_per_sec_level3")
    private Integer tcpFlagStatSynResPerSecLevel3;
    @Column(name="tcp_flag_stat_syn_res_per_sec_level4")
    private Integer tcpFlagStatSynResPerSecLevel4;
    @Column(name="tcp_flag_stat_syn_res_per_sec_level5")
    private Integer tcpFlagStatSynResPerSecLevel5;

    @Column(name="tcp_flag_stat_rst_req_per_sec_use")
    private Boolean tcpFlagStatRstReqPerSecUse;
    @Column(name="tcp_flag_stat_rst_req_per_sec_level1")
    private Integer tcpFlagStatRstReqPerSecLevel1;
    @Column(name="tcp_flag_stat_rst_req_per_sec_level2")
    private Integer tcpFlagStatRstReqPerSecLevel2;
    @Column(name="tcp_flag_stat_rst_req_per_sec_level3")
    private Integer tcpFlagStatRstReqPerSecLevel3;
    @Column(name="tcp_flag_stat_rst_req_per_sec_level4")
    private Integer tcpFlagStatRstReqPerSecLevel4;
    @Column(name="tcp_flag_stat_rst_req_per_sec_level5")
    private Integer tcpFlagStatRstReqPerSecLevel5;

    @Column(name="tcp_flag_stat_rst_res_per_sec_use")
    private Boolean tcpFlagStatRstResPerSecUse;
    @Column(name="tcp_flag_stat_rst_res_per_sec_level1")
    private Integer tcpFlagStatRstResPerSecLevel1;
    @Column(name="tcp_flag_stat_rst_res_per_sec_level2")
    private Integer tcpFlagStatRstResPerSecLevel2;
    @Column(name="tcp_flag_stat_rst_res_per_sec_level3")
    private Integer tcpFlagStatRstResPerSecLevel3;
    @Column(name="tcp_flag_stat_rst_res_per_sec_level4")
    private Integer tcpFlagStatRstResPerSecLevel4;
    @Column(name="tcp_flag_stat_rst_res_per_sec_level5")
    private Integer tcpFlagStatRstResPerSecLevel5;

    @Column(name="tcp_flag_stat_psh_req_per_sec_use")
    private Boolean tcpFlagStatPshReqPerSecUse;
    @Column(name="tcp_flag_stat_psh_req_per_sec_level1")
    private Integer tcpFlagStatPshReqPerSecLevel1;
    @Column(name="tcp_flag_stat_psh_req_per_sec_level2")
    private Integer tcpFlagStatPshReqPerSecLevel2;
    @Column(name="tcp_flag_stat_psh_req_per_sec_level3")
    private Integer tcpFlagStatPshReqPerSecLevel3;
    @Column(name="tcp_flag_stat_psh_req_per_sec_level4")
    private Integer tcpFlagStatPshReqPerSecLevel4;
    @Column(name="tcp_flag_stat_psh_req_per_sec_level5")
    private Integer tcpFlagStatPshReqPerSecLevel5;

    @Column(name="tcp_flag_stat_psh_res_per_sec_use")
    private Boolean tcpFlagStatPshResPerSecUse;
    @Column(name="tcp_flag_stat_psh_res_per_sec_level1")
    private Integer tcpFlagStatPshResPerSecLevel1;
    @Column(name="tcp_flag_stat_psh_res_per_sec_level2")
    private Integer tcpFlagStatPshResPerSecLevel2;
    @Column(name="tcp_flag_stat_psh_res_per_sec_level3")
    private Integer tcpFlagStatPshResPerSecLevel3;
    @Column(name="tcp_flag_stat_psh_res_per_sec_level4")
    private Integer tcpFlagStatPshResPerSecLevel4;
    @Column(name="tcp_flag_stat_psh_res_per_sec_level5")
    private Integer tcpFlagStatPshResPerSecLevel5;

    @Column(name="tcp_flag_stat_ack_req_per_sec_use")
    private Boolean tcpFlagStatAckReqPerSecUse;
    @Column(name="tcp_flag_stat_ack_req_per_sec_level1")
    private Integer tcpFlagStatAckReqPerSecLevel1;
    @Column(name="tcp_flag_stat_ack_req_per_sec_level2")
    private Integer tcpFlagStatAckReqPerSecLevel2;
    @Column(name="tcp_flag_stat_ack_req_per_sec_level3")
    private Integer tcpFlagStatAckReqPerSecLevel3;
    @Column(name="tcp_flag_stat_ack_req_per_sec_level4")
    private Integer tcpFlagStatAckReqPerSecLevel4;
    @Column(name="tcp_flag_stat_ack_req_per_sec_level5")
    private Integer tcpFlagStatAckReqPerSecLevel5;

    @Column(name="tcp_flag_stat_ack_res_per_sec_use")
    private Boolean tcpFlagStatAckResPerSecUse;
    @Column(name="tcp_flag_stat_ack_res_per_sec_level1")
    private Integer tcpFlagStatAckResPerSecLevel1;
    @Column(name="tcp_flag_stat_ack_res_per_sec_level2")
    private Integer tcpFlagStatAckResPerSecLevel2;
    @Column(name="tcp_flag_stat_ack_res_per_sec_level3")
    private Integer tcpFlagStatAckResPerSecLevel3;
    @Column(name="tcp_flag_stat_ack_res_per_sec_level4")
    private Integer tcpFlagStatAckResPerSecLevel4;
    @Column(name="tcp_flag_stat_ack_res_per_sec_level5")
    private Integer tcpFlagStatAckResPerSecLevel5;

    @Column(name="tcp_flag_stat_urg_req_per_sec_use")
    private Boolean tcpFlagStatUrgReqPerSecUse;
    @Column(name="tcp_flag_stat_urg_req_per_sec_level1")
    private Integer tcpFlagStatUrgReqPerSecLevel1;
    @Column(name="tcp_flag_stat_urg_req_per_sec_level2")
    private Integer tcpFlagStatUrgReqPerSecLevel2;
    @Column(name="tcp_flag_stat_urg_req_per_sec_level3")
    private Integer tcpFlagStatUrgReqPerSecLevel3;
    @Column(name="tcp_flag_stat_urg_req_per_sec_level4")
    private Integer tcpFlagStatUrgReqPerSecLevel4;
    @Column(name="tcp_flag_stat_urg_req_per_sec_level5")
    private Integer tcpFlagStatUrgReqPerSecLevel5;

    @Column(name="tcp_flag_stat_urg_res_per_sec_use")
    private Boolean tcpFlagStatUrgResPerSecUse;
    @Column(name="tcp_flag_stat_urg_res_per_sec_level1")
    private Integer tcpFlagStatUrgResPerSecLevel1;
    @Column(name="tcp_flag_stat_urg_res_per_sec_level2")
    private Integer tcpFlagStatUrgResPerSecLevel2;
    @Column(name="tcp_flag_stat_urg_res_per_sec_level3")
    private Integer tcpFlagStatUrgResPerSecLevel3;
    @Column(name="tcp_flag_stat_urg_res_per_sec_level4")
    private Integer tcpFlagStatUrgResPerSecLevel4;
    @Column(name="tcp_flag_stat_urg_res_per_sec_level5")
    private Integer tcpFlagStatUrgResPerSecLevel5;

    @Column(name="tcp_flag_stat_fin_req_delta_use")
    private Boolean tcpFlagStatFinReqDeltaUse;
    @Column(name="tcp_flag_stat_fin_req_delta_level1")
    private Integer tcpFlagStatFinReqDeltaLevel1;
    @Column(name="tcp_flag_stat_fin_req_delta_level2")
    private Integer tcpFlagStatFinReqDeltaLevel2;
    @Column(name="tcp_flag_stat_fin_req_delta_level3")
    private Integer tcpFlagStatFinReqDeltaLevel3;
    @Column(name="tcp_flag_stat_fin_req_delta_level4")
    private Integer tcpFlagStatFinReqDeltaLevel4;
    @Column(name="tcp_flag_stat_fin_req_delta_level5")
    private Integer tcpFlagStatFinReqDeltaLevel5;

    @Column(name="tcp_flag_stat_fin_res_delta_use")
    private Boolean tcpFlagStatFinResDeltaUse;
    @Column(name="tcp_flag_stat_fin_res_delta_level1")
    private Integer tcpFlagStatFinResDeltaLevel1;
    @Column(name="tcp_flag_stat_fin_res_delta_level2")
    private Integer tcpFlagStatFinResDeltaLevel2;
    @Column(name="tcp_flag_stat_fin_res_delta_level3")
    private Integer tcpFlagStatFinResDeltaLevel3;
    @Column(name="tcp_flag_stat_fin_res_delta_level4")
    private Integer tcpFlagStatFinResDeltaLevel4;
    @Column(name="tcp_flag_stat_fin_res_delta_level5")
    private Integer tcpFlagStatFinResDeltaLevel5;

    @Column(name="tcp_flag_stat_syn_req_delta_use")
    private Boolean tcpFlagStatSynReqDeltaUse;
    @Column(name="tcp_flag_stat_syn_req_delta_level1")
    private Integer tcpFlagStatSynReqDeltaLevel1;
    @Column(name="tcp_flag_stat_syn_req_delta_level2")
    private Integer tcpFlagStatSynReqDeltaLevel2;
    @Column(name="tcp_flag_stat_syn_req_delta_level3")
    private Integer tcpFlagStatSynReqDeltaLevel3;
    @Column(name="tcp_flag_stat_syn_req_delta_level4")
    private Integer tcpFlagStatSynReqDeltaLevel4;
    @Column(name="tcp_flag_stat_syn_req_delta_level5")
    private Integer tcpFlagStatSynReqDeltaLevel5;

    @Column(name="tcp_flag_stat_syn_res_delta_use")
    private Boolean tcpFlagStatSynResDeltaUse;
    @Column(name="tcp_flag_stat_syn_res_delta_level1")
    private Integer tcpFlagStatSynResDeltaLevel1;
    @Column(name="tcp_flag_stat_syn_res_delta_level2")
    private Integer tcpFlagStatSynResDeltaLevel2;
    @Column(name="tcp_flag_stat_syn_res_delta_level3")
    private Integer tcpFlagStatSynResDeltaLevel3;
    @Column(name="tcp_flag_stat_syn_res_delta_level4")
    private Integer tcpFlagStatSynResDeltaLevel4;
    @Column(name="tcp_flag_stat_syn_res_delta_level5")
    private Integer tcpFlagStatSynResDeltaLevel5;

    @Column(name="tcp_flag_stat_rst_req_delta_use")
    private Boolean tcpFlagStatRstReqDeltaUse;
    @Column(name="tcp_flag_stat_rst_req_delta_level1")
    private Integer tcpFlagStatRstReqDeltaLevel1;
    @Column(name="tcp_flag_stat_rst_req_delta_level2")
    private Integer tcpFlagStatRstReqDeltaLevel2;
    @Column(name="tcp_flag_stat_rst_req_delta_level3")
    private Integer tcpFlagStatRstReqDeltaLevel3;
    @Column(name="tcp_flag_stat_rst_req_delta_level4")
    private Integer tcpFlagStatRstReqDeltaLevel4;
    @Column(name="tcp_flag_stat_rst_req_delta_level5")
    private Integer tcpFlagStatRstReqDeltaLevel5;

    @Column(name="tcp_flag_stat_rst_res_delta_use")
    private Boolean tcpFlagStatRstResDeltaUse;
    @Column(name="tcp_flag_stat_rst_res_delta_level1")
    private Integer tcpFlagStatRstResDeltaLevel1;
    @Column(name="tcp_flag_stat_rst_res_delta_level2")
    private Integer tcpFlagStatRstResDeltaLevel2;
    @Column(name="tcp_flag_stat_rst_res_delta_level3")
    private Integer tcpFlagStatRstResDeltaLevel3;
    @Column(name="tcp_flag_stat_rst_res_delta_level4")
    private Integer tcpFlagStatRstResDeltaLevel4;
    @Column(name="tcp_flag_stat_rst_res_delta_level5")
    private Integer tcpFlagStatRstResDeltaLevel5;

    @Column(name="tcp_flag_stat_psh_req_delta_use")
    private Boolean tcpFlagStatPshReqDeltaUse;
    @Column(name="tcp_flag_stat_psh_req_delta_level1")
    private Integer tcpFlagStatPshReqDeltaLevel1;
    @Column(name="tcp_flag_stat_psh_req_delta_level2")
    private Integer tcpFlagStatPshReqDeltaLevel2;
    @Column(name="tcp_flag_stat_psh_req_delta_level3")
    private Integer tcpFlagStatPshReqDeltaLevel3;
    @Column(name="tcp_flag_stat_psh_req_delta_level4")
    private Integer tcpFlagStatPshReqDeltaLevel4;
    @Column(name="tcp_flag_stat_psh_req_delta_level5")
    private Integer tcpFlagStatPshReqDeltaLevel5;

    @Column(name="tcp_flag_stat_psh_res_delta_use")
    private Boolean tcpFlagStatPshResDeltaUse;
    @Column(name="tcp_flag_stat_psh_res_delta_level1")
    private Integer tcpFlagStatPshResDeltaLevel1;
    @Column(name="tcp_flag_stat_psh_res_delta_level2")
    private Integer tcpFlagStatPshResDeltaLevel2;
    @Column(name="tcp_flag_stat_psh_res_delta_level3")
    private Integer tcpFlagStatPshResDeltaLevel3;
    @Column(name="tcp_flag_stat_psh_res_delta_level4")
    private Integer tcpFlagStatPshResDeltaLevel4;
    @Column(name="tcp_flag_stat_psh_res_delta_level5")
    private Integer tcpFlagStatPshResDeltaLevel5;

    @Column(name="tcp_flag_stat_ack_req_delta_use")
    private Boolean tcpFlagStatAckReqDeltaUse;
    @Column(name="tcp_flag_stat_ack_req_delta_level1")
    private Integer tcpFlagStatAckReqDeltaLevel1;
    @Column(name="tcp_flag_stat_ack_req_delta_level2")
    private Integer tcpFlagStatAckReqDeltaLevel2;
    @Column(name="tcp_flag_stat_ack_req_delta_level3")
    private Integer tcpFlagStatAckReqDeltaLevel3;
    @Column(name="tcp_flag_stat_ack_req_delta_level4")
    private Integer tcpFlagStatAckReqDeltaLevel4;
    @Column(name="tcp_flag_stat_ack_req_delta_level5")
    private Integer tcpFlagStatAckReqDeltaLevel5;

    @Column(name="tcp_flag_stat_ack_res_delta_use")
    private Boolean tcpFlagStatAckResDeltaUse;
    @Column(name="tcp_flag_stat_ack_res_delta_level1")
    private Integer tcpFlagStatAckResDeltaLevel1;
    @Column(name="tcp_flag_stat_ack_res_delta_level2")
    private Integer tcpFlagStatAckResDeltaLevel2;
    @Column(name="tcp_flag_stat_ack_res_delta_level3")
    private Integer tcpFlagStatAckResDeltaLevel3;
    @Column(name="tcp_flag_stat_ack_res_delta_level4")
    private Integer tcpFlagStatAckResDeltaLevel4;
    @Column(name="tcp_flag_stat_ack_res_delta_level5")
    private Integer tcpFlagStatAckResDeltaLevel5;

    @Column(name="tcp_flag_stat_urg_req_delta_use")
    private Boolean tcpFlagStatUrgReqDeltaUse;
    @Column(name="tcp_flag_stat_urg_req_delta_level1")
    private Integer tcpFlagStatUrgReqDeltaLevel1;
    @Column(name="tcp_flag_stat_urg_req_delta_level2")
    private Integer tcpFlagStatUrgReqDeltaLevel2;
    @Column(name="tcp_flag_stat_urg_req_delta_level3")
    private Integer tcpFlagStatUrgReqDeltaLevel3;
    @Column(name="tcp_flag_stat_urg_req_delta_level4")
    private Integer tcpFlagStatUrgReqDeltaLevel4;
    @Column(name="tcp_flag_stat_urg_req_delta_level5")
    private Integer tcpFlagStatUrgReqDeltaLevel5;

    @Column(name="tcp_flag_stat_urg_res_delta_use")
    private Boolean tcpFlagStatUrgResDeltaUse;
    @Column(name="tcp_flag_stat_urg_res_delta_level1")
    private Integer tcpFlagStatUrgResDeltaLevel1;
    @Column(name="tcp_flag_stat_urg_res_delta_level2")
    private Integer tcpFlagStatUrgResDeltaLevel2;
    @Column(name="tcp_flag_stat_urg_res_delta_level3")
    private Integer tcpFlagStatUrgResDeltaLevel3;
    @Column(name="tcp_flag_stat_urg_res_delta_level4")
    private Integer tcpFlagStatUrgResDeltaLevel4;
    @Column(name="tcp_flag_stat_urg_res_delta_level5")
    private Integer tcpFlagStatUrgResDeltaLevel5;

    @Column(name="req_conn_refused_use")
    private Boolean reqConnRefusedUse;
    @Column(name="req_conn_refused_level1")
    private Integer reqConnRefusedLevel1;
    @Column(name="req_conn_refused_level2")
    private Integer reqConnRefusedLevel2;
    @Column(name="req_conn_refused_level3")
    private Integer reqConnRefusedLevel3;
    @Column(name="req_conn_refused_level4")
    private Integer reqConnRefusedLevel4;
    @Column(name="req_conn_refused_level5")
    private Integer reqConnRefusedLevel5;

    @Column(name="res_conn_refused_use")
    private Boolean resConnRefusedUse;
    @Column(name="res_conn_refused_level1")
    private Integer resConnRefusedLevel1;
    @Column(name="res_conn_refused_level2")
    private Integer resConnRefusedLevel2;
    @Column(name="res_conn_refused_level3")
    private Integer resConnRefusedLevel3;
    @Column(name="res_conn_refused_level4")
    private Integer resConnRefusedLevel4;
    @Column(name="res_conn_refused_level5")
    private Integer resConnRefusedLevel5;

    @Column(name="syn_timeout_use")
    private Boolean synTimeoutUse;
    @Column(name="syn_timeout_level1")
    private Integer synTimeoutLevel1;
    @Column(name="syn_timeout_level2")
    private Integer synTimeoutLevel2;
    @Column(name="syn_timeout_level3")
    private Integer synTimeoutLevel3;
    @Column(name="syn_timeout_level4")
    private Integer synTimeoutLevel4;
    @Column(name="syn_timeout_level5")
    private Integer synTimeoutLevel5;

    @Column(name="synack_timeout_use")
    private Boolean synackTimeoutUse;
    @Column(name="synack_timeout_level1")
    private Integer synackTimeoutLevel1;
    @Column(name="synack_timeout_level2")
    private Integer synackTimeoutLevel2;
    @Column(name="synack_timeout_level3")
    private Integer synackTimeoutLevel3;
    @Column(name="synack_timeout_level4")
    private Integer synackTimeoutLevel4;
    @Column(name="synack_timeout_level5")
    private Integer synackTimeoutLevel5;

    @Column(name="req_ack_timeout_cnt_use")
    private Boolean reqAckTimeoutCntUse;
    @Column(name="req_ack_timeout_cnt_level1")
    private Integer reqAckTimeoutCntLevel1;
    @Column(name="req_ack_timeout_cnt_level2")
    private Integer reqAckTimeoutCntLevel2;
    @Column(name="req_ack_timeout_cnt_level3")
    private Integer reqAckTimeoutCntLevel3;
    @Column(name="req_ack_timeout_cnt_level4")
    private Integer reqAckTimeoutCntLevel4;
    @Column(name="req_ack_timeout_cnt_level5")
    private Integer reqAckTimeoutCntLevel5;

    @Column(name="res_ack_timeout_cnt_use")
    private Boolean resAckTimeoutCntUse;
    @Column(name="res_ack_timeout_cnt_level1")
    private Integer resAckTimeoutCntLevel1;
    @Column(name="res_ack_timeout_cnt_level2")
    private Integer resAckTimeoutCntLevel2;
    @Column(name="res_ack_timeout_cnt_level3")
    private Integer resAckTimeoutCntLevel3;
    @Column(name="res_ack_timeout_cnt_level4")
    private Integer resAckTimeoutCntLevel4;
    @Column(name="res_ack_timeout_cnt_level5")
    private Integer resAckTimeoutCntLevel5;

    @Column(name="stopped_transaction_use")
    private Boolean stoppedTransactionUse;
    @Column(name="stopped_transaction_level1")
    private Integer stoppedTransactionLevel1;
    @Column(name="stopped_transaction_level2")
    private Integer stoppedTransactionLevel2;
    @Column(name="stopped_transaction_level3")
    private Integer stoppedTransactionLevel3;
    @Column(name="stopped_transaction_level4")
    private Integer stoppedTransactionLevel4;
    @Column(name="stopped_transaction_level5")
    private Integer stoppedTransactionLevel5;

    @Column(name="session_timeout_use")
    private Boolean sessionTimeoutUse;
    @Column(name="session_timeout_level1")
    private Integer sessionTimeoutLevel1;
    @Column(name="session_timeout_level2")
    private Integer sessionTimeoutLevel2;
    @Column(name="session_timeout_level3")
    private Integer sessionTimeoutLevel3;
    @Column(name="session_timeout_level4")
    private Integer sessionTimeoutLevel4;
    @Column(name="session_timeout_level5")
    private Integer sessionTimeoutLevel5;

    @Column(name="fin_wait_timeout_use")
    private Boolean finWaitTimeoutUse;
    @Column(name="fin_wait_timeout_level1")
    private Integer finWaitTimeoutLevel1;
    @Column(name="fin_wait_timeout_level2")
    private Integer finWaitTimeoutLevel2;
    @Column(name="fin_wait_timeout_level3")
    private Integer finWaitTimeoutLevel3;
    @Column(name="fin_wait_timeout_level4")
    private Integer finWaitTimeoutLevel4;
    @Column(name="fin_wait_timeout_level5")
    private Integer finWaitTimeoutLevel5;

    @Column(name="close_wait_timeout_use")
    private Boolean closeWaitTimeoutUse;
    @Column(name="close_wait_timeout_level1")
    private Integer closeWaitTimeoutLevel1;
    @Column(name="close_wait_timeout_level2")
    private Integer closeWaitTimeoutLevel2;
    @Column(name="close_wait_timeout_level3")
    private Integer closeWaitTimeoutLevel3;
    @Column(name="close_wait_timeout_level4")
    private Integer closeWaitTimeoutLevel4;
    @Column(name="close_wait_timeout_level5")
    private Integer closeWaitTimeoutLevel5;

    @Column(name="last_ack_timeout_use")
    private Boolean lastAckTimeoutUse;
    @Column(name="last_ack_timeout_level1")
    private Integer lastAckTimeoutLevel1;
    @Column(name="last_ack_timeout_level2")
    private Integer lastAckTimeoutLevel2;
    @Column(name="last_ack_timeout_level3")
    private Integer lastAckTimeoutLevel3;
    @Column(name="last_ack_timeout_level4")
    private Integer lastAckTimeoutLevel4;
    @Column(name="last_ack_timeout_level5")
    private Integer lastAckTimeoutLevel5;

}
