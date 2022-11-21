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
@Table(name="tbl_info_threshold_uri")
public class InfoThresholdUri {

    @Id
    @SequenceGenerator(name="tbl_info_threshold_uri_seq", sequenceName="tbl_info_threshold_uri_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_threshold_uri_seq")
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

    @Column(name="ts_res_delay_transfer_use")
    private Boolean tsResDelayTransferUse;
    @Column(name="ts_res_delay_transfer_level1")
    private Integer tsResDelayTransferLevel1;
    @Column(name="ts_res_delay_transfer_level2")
    private Integer tsResDelayTransferLevel2;
    @Column(name="ts_res_delay_transfer_level3")
    private Integer tsResDelayTransferLevel3;
    @Column(name="ts_res_delay_transfer_level4")
    private Integer tsResDelayTransferLevel4;
    @Column(name="ts_res_delay_transfer_level5")
    private Integer tsResDelayTransferLevel5;

    @Column(name="ts_rsq_delay_response_use")
    private Boolean tsRsqDelayResponseUse;
    @Column(name="ts_rsq_delay_response_level1")
    private Integer tsRsqDelayResponseLevel1;
    @Column(name="ts_rsq_delay_response_level2")
    private Integer tsRsqDelayResponseLevel2;
    @Column(name="ts_rsq_delay_response_level3")
    private Integer tsRsqDelayResponseLevel3;
    @Column(name="ts_rsq_delay_response_level4")
    private Integer tsRsqDelayResponseLevel4;
    @Column(name="ts_rsq_delay_response_level5")
    private Integer tsRsqDelayResponseLevel5;

    @Column(name="ts_res_process_first_use")
    private Boolean tsResProcessFirstUse;
    @Column(name="ts_res_process_first_level1")
    private Integer tsResProcessFirstLevel1;
    @Column(name="ts_res_process_first_level2")
    private Integer tsResProcessFirstLevel2;
    @Column(name="ts_res_process_first_level3")
    private Integer tsResProcessFirstLevel3;
    @Column(name="ts_res_process_first_level4")
    private Integer tsResProcessFirstLevel4;
    @Column(name="ts_res_process_first_level5")
    private Integer tsResProcessFirstLevel5;

    @Column(name="ts_res_process_push_use")
    private Boolean tsResProcessPushUse;
    @Column(name="ts_res_process_push_level1")
    private Integer tsResProcessPushLevel1;
    @Column(name="ts_res_process_push_level2")
    private Integer tsResProcessPushLevel2;
    @Column(name="ts_res_process_push_level3")
    private Integer tsResProcessPushLevel3;
    @Column(name="ts_res_process_push_level4")
    private Integer tsResProcessPushLevel4;
    @Column(name="ts_res_process_push_level5")
    private Integer tsResProcessPushLevel5;

    @Column(name="ts_res_process_last_use")
    private Boolean tsResProcessLastUse;
    @Column(name="ts_res_process_last_level1")
    private Integer tsResProcessLastLevel1;
    @Column(name="ts_res_process_last_level2")
    private Integer tsResProcessLastLevel2;
    @Column(name="ts_res_process_last_level3")
    private Integer tsResProcessLastLevel3;
    @Column(name="ts_res_process_last_level4")
    private Integer tsResProcessLastLevel4;
    @Column(name="ts_res_process_last_level5")
    private Integer tsResProcessLastLevel5;

    @Column(name="ts_req_delay_transfer_use")
    private Boolean tsReqDelayTransferUse;
    @Column(name="ts_req_delay_transfer_level1")
    private Integer tsReqDelayTransferLevel1;
    @Column(name="ts_req_delay_transfer_level2")
    private Integer tsReqDelayTransferLevel2;
    @Column(name="ts_req_delay_transfer_level3")
    private Integer tsReqDelayTransferLevel3;
    @Column(name="ts_req_delay_transfer_level4")
    private Integer tsReqDelayTransferLevel4;
    @Column(name="ts_req_delay_transfer_level5")
    private Integer tsReqDelayTransferLevel5;


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

    @Column(name="ts_rtt_first_ack_use")
    private Boolean tsRttFirstAckUse;
    @Column(name="ts_rtt_first_ack_level1")
    private Integer tsRttFirstAckLevel1;
    @Column(name="ts_rtt_first_ack_level2")
    private Integer tsRttFirstAckLevel2;
    @Column(name="ts_rtt_first_ack_level3")
    private Integer tsRttFirstAckLevel3;
    @Column(name="ts_rtt_first_ack_level4")
    private Integer tsRttFirstAckLevel4;
    @Column(name="ts_rtt_first_ack_level5")
    private Integer tsRttFirstAckLevel5;

    @Column(name="ts_rtt_req_ack_use")
    private Boolean tsRttReqAckUse;
    @Column(name="ts_rtt_req_ack_level1")
    private Integer tsRttReqAckLevel1;
    @Column(name="ts_rtt_req_ack_level2")
    private Integer tsRttReqAckLevel2;
    @Column(name="ts_rtt_req_ack_level3")
    private Integer tsRttReqAckLevel3;
    @Column(name="ts_rtt_req_ack_level4")
    private Integer tsRttReqAckLevel4;
    @Column(name="ts_rtt_req_ack_level5")
    private Integer tsRttReqAckLevel5;

    @Column(name="ts_rtt_res_ack_use")
    private Boolean tsRttResAckUse;
    @Column(name="ts_rtt_res_ack_level1")
    private Integer tsRttResAckLevel1;
    @Column(name="ts_rtt_res_ack_level2")
    private Integer tsRttResAckLevel2;
    @Column(name="ts_rtt_res_ack_level3")
    private Integer tsRttResAckLevel3;
    @Column(name="ts_rtt_res_ack_level4")
    private Integer tsRttResAckLevel4;
    @Column(name="ts_rtt_res_ack_level5")
    private Integer tsRttResAckLevel5;

    @Column(name="ts_rtt_ack_req_ack_use")
    private Boolean tsRttAckReqAckUse;
    @Column(name="ts_rtt_ack_req_ack_level1")
    private Integer tsRttAckReqAckLevel1;
    @Column(name="ts_rtt_ack_req_ack_level2")
    private Integer tsRttAckReqAckLevel2;
    @Column(name="ts_rtt_ack_req_ack_level3")
    private Integer tsRttAckReqAckLevel3;
    @Column(name="ts_rtt_ack_req_ack_level4")
    private Integer tsRttAckReqAckLevel4;
    @Column(name="ts_rtt_ack_req_ack_level5")
    private Integer tsRttAckReqAckLevel5;

    @Column(name="ts_rtt_ack_res_ack_use")
    private Boolean tsRttAckResAckUse;
    @Column(name="ts_rtt_ack_res_ack_level1")
    private Integer tsRttAckResAckLevel1;
    @Column(name="ts_rtt_ack_res_ack_level2")
    private Integer tsRttAckResAckLevel2;
    @Column(name="ts_rtt_ack_res_ack_level3")
    private Integer tsRttAckResAckLevel3;
    @Column(name="ts_rtt_ack_res_ack_level4")
    private Integer tsRttAckResAckLevel4;
    @Column(name="ts_rtt_ack_res_ack_level5")
    private Integer tsRttAckResAckLevel5;

    @Column(name="ts_rtt_conn_req_use")
    private Boolean tsRttConnReqUse;
    @Column(name="ts_rtt_conn_req_level1")
    private Integer tsRttConnReqLevel1;
    @Column(name="ts_rtt_conn_req_level2")
    private Integer tsRttConnReqLevel2;
    @Column(name="ts_rtt_conn_req_level3")
    private Integer tsRttConnReqLevel3;
    @Column(name="ts_rtt_conn_req_level4")
    private Integer tsRttConnReqLevel4;
    @Column(name="ts_rtt_conn_req_level5")
    private Integer tsRttConnReqLevel5;

    @Column(name="ts_rtt_conn_res_use")
    private Boolean tsRttConnResUse;
    @Column(name="ts_rtt_conn_res_level1")
    private Integer tsRttConnResLevel1;
    @Column(name="ts_rtt_conn_res_level2")
    private Integer tsRttConnResLevel2;
    @Column(name="ts_rtt_conn_res_level3")
    private Integer tsRttConnResLevel3;
    @Column(name="ts_rtt_conn_res_level4")
    private Integer tsRttConnResLevel4;
    @Column(name="ts_rtt_conn_res_level5")
    private Integer tsRttConnResLevel5;

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

    @Column(name="ts_conn_delay_req_use")
    private Boolean tsConnDelayReqUse;
    @Column(name="ts_conn_delay_req_level1")
    private Integer tsConnDelayReqLevel1;
    @Column(name="ts_conn_delay_req_level2")
    private Integer tsConnDelayReqLevel2;
    @Column(name="ts_conn_delay_req_level3")
    private Integer tsConnDelayReqLevel3;
    @Column(name="ts_conn_delay_req_level4")
    private Integer tsConnDelayReqLevel4;
    @Column(name="ts_conn_delay_req_level5")
    private Integer tsConnDelayReqLevel5;

    @Column(name="ts_conn_delay_res_use")
    private Boolean tsConnDelayResUse;
    @Column(name="ts_conn_delay_res_level1")
    private Integer tsConnDelayResLevel1;
    @Column(name="ts_conn_delay_res_level2")
    private Integer tsConnDelayResLevel2;
    @Column(name="ts_conn_delay_res_level3")
    private Integer tsConnDelayResLevel3;
    @Column(name="ts_conn_delay_res_level4")
    private Integer tsConnDelayResLevel4;
    @Column(name="ts_conn_delay_res_level5")
    private Integer tsConnDelayResLevel5;

    @Column(name="ts_first_ack_delay_req_use")
    private Boolean tsFirstAckDelayReqUse;
    @Column(name="ts_first_ack_delay_req_level1")
    private Integer tsFirstAckDelayReqLevel1;
    @Column(name="ts_first_ack_delay_req_level2")
    private Integer tsFirstAckDelayReqLevel2;
    @Column(name="ts_first_ack_delay_req_level3")
    private Integer tsFirstAckDelayReqLevel3;
    @Column(name="ts_first_ack_delay_req_level4")
    private Integer tsFirstAckDelayReqLevel4;
    @Column(name="ts_first_ack_delay_req_level5")
    private Integer tsFirstAckDelayReqLevel5;

    @Column(name="ts_first_ack_delay_res_use")
    private Boolean tsFirstAckDelayResUse;
    @Column(name="ts_first_ack_delay_res_level1")
    private Integer tsFirstAckDelayResLevel1;
    @Column(name="ts_first_ack_delay_res_level2")
    private Integer tsFirstAckDelayResLevel2;
    @Column(name="ts_first_ack_delay_res_level3")
    private Integer tsFirstAckDelayResLevel3;
    @Column(name="ts_first_ack_delay_res_level4")
    private Integer tsFirstAckDelayResLevel4;
    @Column(name="ts_first_ack_delay_res_level5")
    private Integer tsFirstAckDelayResLevel5;

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

    @Column(name="pkt_len_req_use")
    private Boolean pktLenReqUse;
    @Column(name="pkt_len_req_level1")
    private Integer pktLenReqLevel1;
    @Column(name="pkt_len_req_level2")
    private Integer pktLenReqLevel2;
    @Column(name="pkt_len_req_level3")
    private Integer pktLenReqLevel3;
    @Column(name="pkt_len_req_level4")
    private Integer pktLenReqLevel4;
    @Column(name="pkt_len_req_level5")
    private Integer pktLenReqLevel5;

    @Column(name="pkt_len_res_use")
    private Boolean pktLenResUse;
    @Column(name="pkt_len_res_level1")
    private Integer pktLenResLevel1;
    @Column(name="pkt_len_res_level2")
    private Integer pktLenResLevel2;
    @Column(name="pkt_len_res_level3")
    private Integer pktLenResLevel3;
    @Column(name="pkt_len_res_level4")
    private Integer pktLenResLevel4;
    @Column(name="pkt_len_res_level5")
    private Integer pktLenResLevel5;

    @Column(name="pkt_cnt_req_use")
    private Boolean pktCntReqUse;
    @Column(name="pkt_cnt_req_level1")
    private Integer pktCntReqLevel1;
    @Column(name="pkt_cnt_req_level2")
    private Integer pktCntReqLevel2;
    @Column(name="pkt_cnt_req_level3")
    private Integer pktCntReqLevel3;
    @Column(name="pkt_cnt_req_level4")
    private Integer pktCntReqLevel4;
    @Column(name="pkt_cnt_req_level5")
    private Integer pktCntReqLevel5;

    @Column(name="pkt_cnt_res_use")
    private Boolean pktCntResUse;
    @Column(name="pkt_cnt_res_level1")
    private Integer pktCntResLevel1;
    @Column(name="pkt_cnt_res_level2")
    private Integer pktCntResLevel2;
    @Column(name="pkt_cnt_res_level3")
    private Integer pktCntResLevel3;
    @Column(name="pkt_cnt_res_level4")
    private Integer pktCntResLevel4;
    @Column(name="pkt_cnt_res_level5")
    private Integer pktCntResLevel5;

    @Column(name="http_len_req_use")
    private Boolean httpLenReqUse;
    @Column(name="http_len_req_level1")
    private Integer httpLenReqLevel1;
    @Column(name="http_len_req_level2")
    private Integer httpLenReqLevel2;
    @Column(name="http_len_req_level3")
    private Integer httpLenReqLevel3;
    @Column(name="http_len_req_level4")
    private Integer httpLenReqLevel4;
    @Column(name="http_len_req_level5")
    private Integer httpLenReqLevel5;

    @Column(name="http_len_res_use")
    private Boolean httpLenResUse;
    @Column(name="http_len_res_level1")
    private Integer httpLenResLevel1;
    @Column(name="http_len_res_level2")
    private Integer httpLenResLevel2;
    @Column(name="http_len_res_level3")
    private Integer httpLenResLevel3;
    @Column(name="http_len_res_level4")
    private Integer httpLenResLevel4;
    @Column(name="http_len_res_level5")
    private Integer httpLenResLevel5;

    @Column(name="http_cnt_req_use")
    private Boolean httpCntReqUse;
    @Column(name="http_cnt_req_level1")
    private Integer httpCntReqLevel1;
    @Column(name="http_cnt_req_level2")
    private Integer httpCntReqLevel2;
    @Column(name="http_cnt_req_level3")
    private Integer httpCntReqLevel3;
    @Column(name="http_cnt_req_level4")
    private Integer httpCntReqLevel4;
    @Column(name="http_cnt_req_level5")
    private Integer httpCntReqLevel5;

    @Column(name="http_cnt_res_use")
    private Boolean httpCntResUse;
    @Column(name="http_cnt_res_level1")
    private Integer httpCntResLevel1;
    @Column(name="http_cnt_res_level2")
    private Integer httpCntResLevel2;
    @Column(name="http_cnt_res_level3")
    private Integer httpCntResLevel3;
    @Column(name="http_cnt_res_level4")
    private Integer httpCntResLevel4;
    @Column(name="http_cnt_res_level5")
    private Integer httpCntResLevel5;


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

    @Column(name="ack_delay_req_use")
    private Boolean ackDelayReqUse;
    @Column(name="ack_delay_req_level1")
    private Integer ackDelayReqLevel1;
    @Column(name="ack_delay_req_level2")
    private Integer ackDelayReqLevel2;
    @Column(name="ack_delay_req_level3")
    private Integer ackDelayReqLevel3;
    @Column(name="ack_delay_req_level4")
    private Integer ackDelayReqLevel4;
    @Column(name="ack_delay_req_level5")
    private Integer ackDelayReqLevel5;

    @Column(name="ack_delay_res_use")
    private Boolean ackDelayResUse;
    @Column(name="ack_delay_res_level1")
    private Integer ackDelayResLevel1;
    @Column(name="ack_delay_res_level2")
    private Integer ackDelayResLevel2;
    @Column(name="ack_delay_res_level3")
    private Integer ackDelayResLevel3;
    @Column(name="ack_delay_res_level4")
    private Integer ackDelayResLevel4;
    @Column(name="ack_delay_res_level5")
    private Integer ackDelayResLevel5;

    @Column(name="retransmission_cnt_req_use")
    private Boolean retransmissionCntReqUse;
    @Column(name="retransmission_cnt_req_level1")
    private Integer retransmissionCntReqLevel1;
    @Column(name="retransmission_cnt_req_level2")
    private Integer retransmissionCntReqLevel2;
    @Column(name="retransmission_cnt_req_level3")
    private Integer retransmissionCntReqLevel3;
    @Column(name="retransmission_cnt_req_level4")
    private Integer retransmissionCntReqLevel4;
    @Column(name="retransmission_cnt_req_level5")
    private Integer retransmissionCntReqLevel5;

    @Column(name="retransmission_cnt_res_use")
    private Boolean retransmissionCntResUse;
    @Column(name="retransmission_cnt_res_level1")
    private Integer retransmissionCntResLevel1;
    @Column(name="retransmission_cnt_res_level2")
    private Integer retransmissionCntResLevel2;
    @Column(name="retransmission_cnt_res_level3")
    private Integer retransmissionCntResLevel3;
    @Column(name="retransmission_cnt_res_level4")
    private Integer retransmissionCntResLevel4;
    @Column(name="retransmission_cnt_res_level5")
    private Integer retransmissionCntResLevel5;

    @Column(name="retransmission_len_req_use")
    private Boolean retransmissionLenReqUse;
    @Column(name="retransmission_len_req_level1")
    private Integer retransmissionLenReqLevel1;
    @Column(name="retransmission_len_req_level2")
    private Integer retransmissionLenReqLevel2;
    @Column(name="retransmission_len_req_level3")
    private Integer retransmissionLenReqLevel3;
    @Column(name="retransmission_len_req_level4")
    private Integer retransmissionLenReqLevel4;
    @Column(name="retransmission_len_req_level5")
    private Integer retransmissionLenReqLevel5;

    @Column(name="retransmission_len_res_use")
    private Boolean retransmissionLenResUse;
    @Column(name="retransmission_len_res_level1")
    private Integer retransmissionLenResLevel1;
    @Column(name="retransmission_len_res_level2")
    private Integer retransmissionLenResLevel2;
    @Column(name="retransmission_len_res_level3")
    private Integer retransmissionLenResLevel3;
    @Column(name="retransmission_len_res_level4")
    private Integer retransmissionLenResLevel4;
    @Column(name="retransmission_len_res_level5")
    private Integer retransmissionLenResLevel5;

    @Column(name="fast_retransmission_cnt_req_use")
    private Boolean fastRetransmissionCntReqUse;
    @Column(name="fast_retransmission_cnt_req_level1")
    private Integer fastRetransmissionCntReqLevel1;
    @Column(name="fast_retransmission_cnt_req_level2")
    private Integer fastRetransmissionCntReqLevel2;
    @Column(name="fast_retransmission_cnt_req_level3")
    private Integer fastRetransmissionCntReqLevel3;
    @Column(name="fast_retransmission_cnt_req_level4")
    private Integer fastRetransmissionCntReqLevel4;
    @Column(name="fast_retransmission_cnt_req_level5")
    private Integer fastRetransmissionCntReqLevel5;

    @Column(name="fast_retransmission_cnt_Res_use")
    private Boolean fastRetransmissionCntResUse;
    @Column(name="fast_retransmission_cnt_Res_level1")
    private Integer fastRetransmissionCntResLevel1;
    @Column(name="fast_retransmission_cnt_Res_level2")
    private Integer fastRetransmissionCntResLevel2;
    @Column(name="fast_retransmission_cnt_Res_level3")
    private Integer fastRetransmissionCntResLevel3;
    @Column(name="fast_retransmission_cnt_Res_level4")
    private Integer fastRetransmissionCntResLevel4;
    @Column(name="fast_retransmission_cnt_Res_level5")
    private Integer fastRetransmissionCntResLevel5;

    @Column(name="fast_retransmission_len_req_use")
    private Boolean fastRetransmissionLenReqUse;
    @Column(name="fast_retransmission_len_req_level1")
    private Integer fastRetransmissionLenReqLevel1;
    @Column(name="fast_retransmission_len_req_level2")
    private Integer fastRetransmissionLenReqLevel2;
    @Column(name="fast_retransmission_len_req_level3")
    private Integer fastRetransmissionLenReqLevel3;
    @Column(name="fast_retransmission_len_req_level4")
    private Integer fastRetransmissionLenReqLevel4;
    @Column(name="fast_retransmission_len_req_level5")
    private Integer fastRetransmissionLenReqLevel5;

    @Column(name="fast_retransmission_len_res_use")
    private Boolean fastRetransmissionLenResUse;
    @Column(name="fast_retransmission_len_res_level1")
    private Integer fastRetransmissionLenResLevel1;
    @Column(name="fast_retransmission_len_res_level2")
    private Integer fastRetransmissionLenResLevel2;
    @Column(name="fast_retransmission_len_res_level3")
    private Integer fastRetransmissionLenResLevel3;
    @Column(name="fast_retransmission_len_res_level4")
    private Integer fastRetransmissionLenResLevel4;
    @Column(name="fast_retransmission_len_res_level5")
    private Integer fastRetransmissionLenResLevel5;

    @Column(name="out_of_order_cnt_req_use")
    private Boolean outOfOrderCntReqUse;
    @Column(name="out_of_order_cnt_req_level1")
    private Integer outOfOrderCntReqLevel1;
    @Column(name="out_of_order_cnt_req_level2")
    private Integer outOfOrderCntReqLevel2;
    @Column(name="out_of_order_cnt_req_level3")
    private Integer outOfOrderCntReqLevel3;
    @Column(name="out_of_order_cnt_req_level4")
    private Integer outOfOrderCntReqLevel4;
    @Column(name="out_of_order_cnt_req_level5")
    private Integer outOfOrderCntReqLevel5;

    @Column(name="out_of_order_cnt_Res_use")
    private Boolean outOfOrderCntResUse;
    @Column(name="out_of_order_cnt_Res_level1")
    private Integer outOfOrderCntResLevel1;
    @Column(name="out_of_order_cnt_Res_level2")
    private Integer outOfOrderCntResLevel2;
    @Column(name="out_of_order_cnt_Res_level3")
    private Integer outOfOrderCntResLevel3;
    @Column(name="out_of_order_cnt_Res_level4")
    private Integer outOfOrderCntResLevel4;
    @Column(name="out_of_order_cnt_Res_level5")
    private Integer outOfOrderCntResLevel5;

    @Column(name="out_of_order_len_req_use")
    private Boolean outOfOrderLenReqUse;
    @Column(name="out_of_order_len_req_level1")
    private Integer outOfOrderLenReqLevel1;
    @Column(name="out_of_order_len_req_level2")
    private Integer outOfOrderLenReqLevel2;
    @Column(name="out_of_order_len_req_level3")
    private Integer outOfOrderLenReqLevel3;
    @Column(name="out_of_order_len_req_level4")
    private Integer outOfOrderLenReqLevel4;
    @Column(name="out_of_order_len_req_level5")
    private Integer outOfOrderLenReqLevel5;

    @Column(name="out_of_order_len_res_use")
    private Boolean outOfOrderLenResUse;
    @Column(name="out_of_order_len_res_level1")
    private Integer outOfOrderLenResLevel1;
    @Column(name="out_of_order_len_res_level2")
    private Integer outOfOrderLenResLevel2;
    @Column(name="out_of_order_len_res_level3")
    private Integer outOfOrderLenResLevel3;
    @Column(name="out_of_order_len_res_level4")
    private Integer outOfOrderLenResLevel4;
    @Column(name="out_of_order_len_res_level5")
    private Integer outOfOrderLenResLevel5;

    @Column(name="lost_seg_cnt_req_use")
    private Boolean lostSegCntReqUse;
    @Column(name="lost_seg_cnt_req_level1")
    private Integer lostSegCntReqLevel1;
    @Column(name="lost_seg_cnt_req_level2")
    private Integer lostSegCntReqLevel2;
    @Column(name="lost_seg_cnt_req_level3")
    private Integer lostSegCntReqLevel3;
    @Column(name="lost_seg_cnt_req_level4")
    private Integer lostSegCntReqLevel4;
    @Column(name="lost_seg_cnt_req_level5")
    private Integer lostSegCntReqLevel5;

    @Column(name="lost_seg_cnt_Res_use")
    private Boolean lostSegCntResUse;
    @Column(name="lost_seg_cnt_Res_level1")
    private Integer lostSegCntResLevel1;
    @Column(name="lost_seg_cnt_Res_level2")
    private Integer lostSegCntResLevel2;
    @Column(name="lost_seg_cnt_Res_level3")
    private Integer lostSegCntResLevel3;
    @Column(name="lost_seg_cnt_Res_level4")
    private Integer lostSegCntResLevel4;
    @Column(name="lost_seg_cnt_Res_level5")
    private Integer lostSegCntResLevel5;

    @Column(name="lost_seg_len_req_use")
    private Boolean lostSegLenReqUse;
    @Column(name="lost_seg_len_req_level1")
    private Integer lostSegLenReqLevel1;
    @Column(name="lost_seg_len_req_level2")
    private Integer lostSegLenReqLevel2;
    @Column(name="lost_seg_len_req_level3")
    private Integer lostSegLenReqLevel3;
    @Column(name="lost_seg_len_req_level4")
    private Integer lostSegLenReqLevel4;
    @Column(name="lost_seg_len_req_level5")
    private Integer lostSegLenReqLevel5;

    @Column(name="lost_seg_len_res_use")
    private Boolean lostSegLenResUse;
    @Column(name="lost_seg_len_res_level1")
    private Integer lostSegLenResLevel1;
    @Column(name="lost_seg_len_res_level2")
    private Integer lostSegLenResLevel2;
    @Column(name="lost_seg_len_res_level3")
    private Integer lostSegLenResLevel3;
    @Column(name="lost_seg_len_res_level4")
    private Integer lostSegLenResLevel4;
    @Column(name="lost_seg_len_res_level5")
    private Integer lostSegLenResLevel5;

    @Column(name="ack_lost_cnt_req_use")
    private Boolean ackLostCntReqUse;
    @Column(name="ack_lost_cnt_req_level1")
    private Integer ackLostCntReqLevel1;
    @Column(name="ack_lost_cnt_req_level2")
    private Integer ackLostCntReqLevel2;
    @Column(name="ack_lost_cnt_req_level3")
    private Integer ackLostCntReqLevel3;
    @Column(name="ack_lost_cnt_req_level4")
    private Integer ackLostCntReqLevel4;
    @Column(name="ack_lost_cnt_req_level5")
    private Integer ackLostCntReqLevel5;

    @Column(name="ack_lost_cnt_res_use")
    private Boolean ackLostCntResUse;
    @Column(name="ack_lost_cnt_res_level1")
    private Integer ackLostCntResLevel1;
    @Column(name="ack_lost_cnt_res_level2")
    private Integer ackLostCntResLevel2;
    @Column(name="ack_lost_cnt_res_level3")
    private Integer ackLostCntResLevel3;
    @Column(name="ack_lost_cnt_res_level4")
    private Integer ackLostCntResLevel4;
    @Column(name="ack_lost_cnt_res_level5")
    private Integer ackLostCntResLevel5;

    @Column(name="ack_lost_len_req_use")
    private Boolean ackLostLenReqUse;
    @Column(name="ack_lost_len_req_level1")
    private Integer ackLostLenReqLevel1;
    @Column(name="ack_lost_len_req_level2")
    private Integer ackLostLenReqLevel2;
    @Column(name="ack_lost_len_req_level3")
    private Integer ackLostLenReqLevel3;
    @Column(name="ack_lost_len_req_level4")
    private Integer ackLostLenReqLevel4;
    @Column(name="ack_lost_len_req_level5")
    private Integer ackLostLenReqLevel5;

    @Column(name="ack_lost_len_res_use")
    private Boolean ackLostLenResUse;
    @Column(name="ack_lost_len_res_level1")
    private Integer ackLostLenResLevel1;
    @Column(name="ack_lost_len_res_level2")
    private Integer ackLostLenResLevel2;
    @Column(name="ack_lost_len_res_level3")
    private Integer ackLostLenResLevel3;
    @Column(name="ack_lost_len_res_level4")
    private Integer ackLostLenResLevel4;
    @Column(name="ack_lost_len_res_level5")
    private Integer ackLostLenResLevel5;

    @Column(name="win_update_cnt_req_use")
    private Boolean winUpdateCntReqUse;
    @Column(name="win_update_cnt_req_level1")
    private Integer winUpdateCntReqLevel1;
    @Column(name="win_update_cnt_req_level2")
    private Integer winUpdateCntReqLevel2;
    @Column(name="win_update_cnt_req_level3")
    private Integer winUpdateCntReqLevel3;
    @Column(name="win_update_cnt_req_level4")
    private Integer winUpdateCntReqLevel4;
    @Column(name="win_update_cnt_req_level5")
    private Integer winUpdateCntReqLevel5;

    @Column(name="win_update_cnt_Res_use")
    private Boolean winUpdateCntResUse;
    @Column(name="win_update_cnt_Res_level1")
    private Integer winUpdateCntResLevel1;
    @Column(name="win_update_cnt_Res_level2")
    private Integer winUpdateCntResLevel2;
    @Column(name="win_update_cnt_Res_level3")
    private Integer winUpdateCntResLevel3;
    @Column(name="win_update_cnt_Res_level4")
    private Integer winUpdateCntResLevel4;
    @Column(name="win_update_cnt_Res_level5")
    private Integer winUpdateCntResLevel5;

    @Column(name="win_update_len_req_use")
    private Boolean winUpdateLenReqUse;
    @Column(name="win_update_len_req_level1")
    private Integer winUpdateLenReqLevel1;
    @Column(name="win_update_len_req_level2")
    private Integer winUpdateLenReqLevel2;
    @Column(name="win_update_len_req_level3")
    private Integer winUpdateLenReqLevel3;
    @Column(name="win_update_len_req_level4")
    private Integer winUpdateLenReqLevel4;
    @Column(name="win_update_len_req_level5")
    private Integer winUpdateLenReqLevel5;

    @Column(name="win_update_len_res_use")
    private Boolean winUpdateLenResUse;
    @Column(name="win_update_len_res_level1")
    private Integer winUpdateLenResLevel1;
    @Column(name="win_update_len_res_level2")
    private Integer winUpdateLenResLevel2;
    @Column(name="win_update_len_res_level3")
    private Integer winUpdateLenResLevel3;
    @Column(name="win_update_len_res_level4")
    private Integer winUpdateLenResLevel4;
    @Column(name="win_update_len_res_level5")
    private Integer winUpdateLenResLevel5;

    @Column(name="dup_ack_cnt_req_use")
    private Boolean dupAckCntReqUse;
    @Column(name="dup_ack_cnt_req_level1")
    private Integer dupAckCntReqLevel1;
    @Column(name="dup_ack_cnt_req_level2")
    private Integer dupAckCntReqLevel2;
    @Column(name="dup_ack_cnt_req_level3")
    private Integer dupAckCntReqLevel3;
    @Column(name="dup_ack_cnt_req_level4")
    private Integer dupAckCntReqLevel4;
    @Column(name="dup_ack_cnt_req_level5")
    private Integer dupAckCntReqLevel5;

    @Column(name="dup_ack_cnt_Res_use")
    private Boolean dupAckCntResUse;
    @Column(name="dup_ack_cnt_Res_level1")
    private Integer dupAckCntResLevel1;
    @Column(name="dup_ack_cnt_Res_level2")
    private Integer dupAckCntResLevel2;
    @Column(name="dup_ack_cnt_Res_level3")
    private Integer dupAckCntResLevel3;
    @Column(name="dup_ack_cnt_Res_level4")
    private Integer dupAckCntResLevel4;
    @Column(name="dup_ack_cnt_Res_level5")
    private Integer dupAckCntResLevel5;

    @Column(name="dup_ack_len_req_use")
    private Boolean dupAckLenReqUse;
    @Column(name="dup_ack_len_req_level1")
    private Integer dupAckLenReqLevel1;
    @Column(name="dup_ack_len_req_level2")
    private Integer dupAckLenReqLevel2;
    @Column(name="dup_ack_len_req_level3")
    private Integer dupAckLenReqLevel3;
    @Column(name="dup_ack_len_req_level4")
    private Integer dupAckLenReqLevel4;
    @Column(name="dup_ack_len_req_level5")
    private Integer dupAckLenReqLevel5;

    @Column(name="dup_ack_len_res_use")
    private Boolean dupAckLenResUse;
    @Column(name="dup_ack_len_res_level1")
    private Integer dupAckLenResLevel1;
    @Column(name="dup_ack_len_res_level2")
    private Integer dupAckLenResLevel2;
    @Column(name="dup_ack_len_res_level3")
    private Integer dupAckLenResLevel3;
    @Column(name="dup_ack_len_res_level4")
    private Integer dupAckLenResLevel4;
    @Column(name="dup_ack_len_res_level5")
    private Integer dupAckLenResLevel5;

    @Column(name="zero_win_cnt_req_use")
    private Boolean zeroWinCntReqUse;
    @Column(name="zero_win_cnt_req_level1")
    private Integer zeroWinCntReqLevel1;
    @Column(name="zero_win_cnt_req_level2")
    private Integer zeroWinCntReqLevel2;
    @Column(name="zero_win_cnt_req_level3")
    private Integer zeroWinCntReqLevel3;
    @Column(name="zero_win_cnt_req_level4")
    private Integer zeroWinCntReqLevel4;
    @Column(name="zero_win_cnt_req_level5")
    private Integer zeroWinCntReqLevel5;

    @Column(name="zero_win_cnt_Res_use")
    private Boolean zeroWinCntResUse;
    @Column(name="zero_win_cnt_Res_level1")
    private Integer zeroWinCntResLevel1;
    @Column(name="zero_win_cnt_Res_level2")
    private Integer zeroWinCntResLevel2;
    @Column(name="zero_win_cnt_Res_level3")
    private Integer zeroWinCntResLevel3;
    @Column(name="zero_win_cnt_Res_level4")
    private Integer zeroWinCntResLevel4;
    @Column(name="zero_win_cnt_Res_level5")
    private Integer zeroWinCntResLevel5;

    @Column(name="zero_win_len_req_use")
    private Boolean zeroWinLenReqUse;
    @Column(name="zero_win_len_req_level1")
    private Integer zeroWinLenReqLevel1;
    @Column(name="zero_win_len_req_level2")
    private Integer zeroWinLenReqLevel2;
    @Column(name="zero_win_len_req_level3")
    private Integer zeroWinLenReqLevel3;
    @Column(name="zero_win_len_req_level4")
    private Integer zeroWinLenReqLevel4;
    @Column(name="zero_win_len_req_level5")
    private Integer zeroWinLenReqLevel5;

    @Column(name="zero_win_len_res_use")
    private Boolean zeroWinLenResUse;
    @Column(name="zero_win_len_res_level1")
    private Integer zeroWinLenResLevel1;
    @Column(name="zero_win_len_res_level2")
    private Integer zeroWinLenResLevel2;
    @Column(name="zero_win_len_res_level3")
    private Integer zeroWinLenResLevel3;
    @Column(name="zero_win_len_res_level4")
    private Integer zeroWinLenResLevel4;
    @Column(name="zero_win_len_res_level5")
    private Integer zeroWinLenResLevel5;

    @Column(name="spurious_retransmission_cnt_req_use")
    private Boolean spuriousRetransmissionCntReqUse;
    @Column(name="spurious_retransmission_cnt_req_level1")
    private Integer spuriousRetransmissionCntReqLevel1;
    @Column(name="spurious_retransmission_cnt_req_level2")
    private Integer spuriousRetransmissionCntReqLevel2;
    @Column(name="spurious_retransmission_cnt_req_level3")
    private Integer spuriousRetransmissionCntReqLevel3;
    @Column(name="spurious_retransmission_cnt_req_level4")
    private Integer spuriousRetransmissionCntReqLevel4;
    @Column(name="spurious_retransmission_cnt_req_level5")
    private Integer spuriousRetransmissionCntReqLevel5;

    @Column(name="spurious_retransmission_cnt_Res_use")
    private Boolean spuriousRetransmissionCntResUse;
    @Column(name="spurious_retransmission_cnt_Res_level1")
    private Integer spuriousRetransmissionCntResLevel1;
    @Column(name="spurious_retransmission_cnt_Res_level2")
    private Integer spuriousRetransmissionCntResLevel2;
    @Column(name="spurious_retransmission_cnt_Res_level3")
    private Integer spuriousRetransmissionCntResLevel3;
    @Column(name="spurious_retransmission_cnt_Res_level4")
    private Integer spuriousRetransmissionCntResLevel4;
    @Column(name="spurious_retransmission_cnt_Res_level5")
    private Integer spuriousRetransmissionCntResLevel5;

    @Column(name="spurious_retransmission_len_req_use")
    private Boolean spuriousRetransmissionLenReqUse;
    @Column(name="spurious_retransmission_len_req_level1")
    private Integer spuriousRetransmissionLenReqLevel1;
    @Column(name="spurious_retransmission_len_req_level2")
    private Integer spuriousRetransmissionLenReqLevel2;
    @Column(name="spurious_retransmission_len_req_level3")
    private Integer spuriousRetransmissionLenReqLevel3;
    @Column(name="spurious_retransmission_len_req_level4")
    private Integer spuriousRetransmissionLenReqLevel4;
    @Column(name="spurious_retransmission_len_req_level5")
    private Integer spuriousRetransmissionLenReqLevel5;

    @Column(name="spurious_retransmission_len_res_use")
    private Boolean spuriousRetransmissionLenResUse;
    @Column(name="spurious_retransmission_len_res_level1")
    private Integer spuriousRetransmissionLenResLevel1;
    @Column(name="spurious_retransmission_len_res_level2")
    private Integer spuriousRetransmissionLenResLevel2;
    @Column(name="spurious_retransmission_len_res_level3")
    private Integer spuriousRetransmissionLenResLevel3;
    @Column(name="spurious_retransmission_len_res_level4")
    private Integer spuriousRetransmissionLenResLevel4;
    @Column(name="spurious_retransmission_len_res_level5")
    private Integer spuriousRetransmissionLenResLevel5;

    @Column(name="overlap_cnt_req_use")
    private Boolean overlapCntReqUse;
    @Column(name="overlap_cnt_req_level1")
    private Integer overlapCntReqLevel1;
    @Column(name="overlap_cnt_req_level2")
    private Integer overlapCntReqLevel2;
    @Column(name="overlap_cnt_req_level3")
    private Integer overlapCntReqLevel3;
    @Column(name="overlap_cnt_req_level4")
    private Integer overlapCntReqLevel4;
    @Column(name="overlap_cnt_req_level5")
    private Integer overlapCntReqLevel5;

    @Column(name="overlap_cnt_Res_use")
    private Boolean overlapCntResUse;
    @Column(name="overlap_cnt_Res_level1")
    private Integer overlapCntResLevel1;
    @Column(name="overlap_cnt_Res_level2")
    private Integer overlapCntResLevel2;
    @Column(name="overlap_cnt_Res_level3")
    private Integer overlapCntResLevel3;
    @Column(name="overlap_cnt_Res_level4")
    private Integer overlapCntResLevel4;
    @Column(name="overlap_cnt_Res_level5")
    private Integer overlapCntResLevel5;

    @Column(name="overlap_len_req_use")
    private Boolean overlapLenReqUse;
    @Column(name="overlap_len_req_level1")
    private Integer overlapLenReqLevel1;
    @Column(name="overlap_len_req_level2")
    private Integer overlapLenReqLevel2;
    @Column(name="overlap_len_req_level3")
    private Integer overlapLenReqLevel3;
    @Column(name="overlap_len_req_level4")
    private Integer overlapLenReqLevel4;
    @Column(name="overlap_len_req_level5")
    private Integer overlapLenReqLevel5;

    @Column(name="overlap_len_res_use")
    private Boolean overlapLenResUse;
    @Column(name="overlap_len_res_level1")
    private Integer overlapLenResLevel1;
    @Column(name="overlap_len_res_level2")
    private Integer overlapLenResLevel2;
    @Column(name="overlap_len_res_level3")
    private Integer overlapLenResLevel3;
    @Column(name="overlap_len_res_level4")
    private Integer overlapLenResLevel4;
    @Column(name="overlap_len_res_level5")
    private Integer overlapLenResLevel5;

    @Column(name="overlap_attack_cnt_req_use")
    private Boolean overlapAttackCntReqUse;
    @Column(name="overlap_attack_cnt_req_level1")
    private Integer overlapAttackCntReqLevel1;
    @Column(name="overlap_attack_cnt_req_level2")
    private Integer overlapAttackCntReqLevel2;
    @Column(name="overlap_attack_cnt_req_level3")
    private Integer overlapAttackCntReqLevel3;
    @Column(name="overlap_attack_cnt_req_level4")
    private Integer overlapAttackCntReqLevel4;
    @Column(name="overlap_attack_cnt_req_level5")
    private Integer overlapAttackCntReqLevel5;

    @Column(name="overlap_attack_cnt_Res_use")
    private Boolean overlapAttackCntResUse;
    @Column(name="overlap_attack_cnt_Res_level1")
    private Integer overlapAttackCntResLevel1;
    @Column(name="overlap_attack_cnt_Res_level2")
    private Integer overlapAttackCntResLevel2;
    @Column(name="overlap_attack_cnt_Res_level3")
    private Integer overlapAttackCntResLevel3;
    @Column(name="overlap_attack_cnt_Res_level4")
    private Integer overlapAttackCntResLevel4;
    @Column(name="overlap_attack_cnt_Res_level5")
    private Integer overlapAttackCntResLevel5;

    @Column(name="overlap_attack_len_req_use")
    private Boolean overlapAttackLenReqUse;
    @Column(name="overlap_attack_len_req_level1")
    private Integer overlapAttackLenReqLevel1;
    @Column(name="overlap_attack_len_req_level2")
    private Integer overlapAttackLenReqLevel2;
    @Column(name="overlap_attack_len_req_level3")
    private Integer overlapAttackLenReqLevel3;
    @Column(name="overlap_attack_len_req_level4")
    private Integer overlapAttackLenReqLevel4;
    @Column(name="overlap_attack_len_req_level5")
    private Integer overlapAttackLenReqLevel5;

    @Column(name="overlap_attack_len_res_use")
    private Boolean overlapAttackLenResUse;
    @Column(name="overlap_attack_len_res_level1")
    private Integer overlapAttackLenResLevel1;
    @Column(name="overlap_attack_len_res_level2")
    private Integer overlapAttackLenResLevel2;
    @Column(name="overlap_attack_len_res_level3")
    private Integer overlapAttackLenResLevel3;
    @Column(name="overlap_attack_len_res_level4")
    private Integer overlapAttackLenResLevel4;
    @Column(name="overlap_attack_len_res_level5")
    private Integer overlapAttackLenResLevel5;

    @Column(name="zero_win_probe_cnt_req_use")
    private Boolean zeroWinProbeCntReqUse;
    @Column(name="zero_win_probe_cnt_req_level1")
    private Integer zeroWinProbeCntReqLevel1;
    @Column(name="zero_win_probe_cnt_req_level2")
    private Integer zeroWinProbeCntReqLevel2;
    @Column(name="zero_win_probe_cnt_req_level3")
    private Integer zeroWinProbeCntReqLevel3;
    @Column(name="zero_win_probe_cnt_req_level4")
    private Integer zeroWinProbeCntReqLevel4;
    @Column(name="zero_win_probe_cnt_req_level5")
    private Integer zeroWinProbeCntReqLevel5;

    @Column(name="zero_win_probe_cnt_Res_use")
    private Boolean zeroWinProbeCntResUse;
    @Column(name="zero_win_probe_cnt_Res_level1")
    private Integer zeroWinProbeCntResLevel1;
    @Column(name="zero_win_probe_cnt_Res_level2")
    private Integer zeroWinProbeCntResLevel2;
    @Column(name="zero_win_probe_cnt_Res_level3")
    private Integer zeroWinProbeCntResLevel3;
    @Column(name="zero_win_probe_cnt_Res_level4")
    private Integer zeroWinProbeCntResLevel4;
    @Column(name="zero_win_probe_cnt_Res_level5")
    private Integer zeroWinProbeCntResLevel5;

    @Column(name="zero_win_probe_len_req_use")
    private Boolean zeroWinProbeLenReqUse;
    @Column(name="zero_win_probe_len_req_level1")
    private Integer zeroWinProbeLenReqLevel1;
    @Column(name="zero_win_probe_len_req_level2")
    private Integer zeroWinProbeLenReqLevel2;
    @Column(name="zero_win_probe_len_req_level3")
    private Integer zeroWinProbeLenReqLevel3;
    @Column(name="zero_win_probe_len_req_level4")
    private Integer zeroWinProbeLenReqLevel4;
    @Column(name="zero_win_probe_len_req_level5")
    private Integer zeroWinProbeLenReqLevel5;

    @Column(name="zero_win_probe_len_res_use")
    private Boolean zeroWinProbeLenResUse;
    @Column(name="zero_win_probe_len_res_level1")
    private Integer zeroWinProbeLenResLevel1;
    @Column(name="zero_win_probe_len_res_level2")
    private Integer zeroWinProbeLenResLevel2;
    @Column(name="zero_win_probe_len_res_level3")
    private Integer zeroWinProbeLenResLevel3;
    @Column(name="zero_win_probe_len_res_level4")
    private Integer zeroWinProbeLenResLevel4;
    @Column(name="zero_win_probe_len_res_level5")
    private Integer zeroWinProbeLenResLevel5;

    @Column(name="zero_win_probe_ack_cnt_req_use")
    private Boolean zeroWinProbeAckCntReqUse;
    @Column(name="zero_win_probe_ack_cnt_req_level1")
    private Integer zeroWinProbeAckCntReqLevel1;
    @Column(name="zero_win_probe_ack_cnt_req_level2")
    private Integer zeroWinProbeAckCntReqLevel2;
    @Column(name="zero_win_probe_ack_cnt_req_level3")
    private Integer zeroWinProbeAckCntReqLevel3;
    @Column(name="zero_win_probe_ack_cnt_req_level4")
    private Integer zeroWinProbeAckCntReqLevel4;
    @Column(name="zero_win_probe_ack_cnt_req_level5")
    private Integer zeroWinProbeAckCntReqLevel5;

    @Column(name="zero_win_probe_ack_cnt_Res_use")
    private Boolean zeroWinProbeAckCntResUse;
    @Column(name="zero_win_probe_ack_cnt_Res_level1")
    private Integer zeroWinProbeAckCntResLevel1;
    @Column(name="zero_win_probe_ack_cnt_Res_level2")
    private Integer zeroWinProbeAckCntResLevel2;
    @Column(name="zero_win_probe_ack_cnt_Res_level3")
    private Integer zeroWinProbeAckCntResLevel3;
    @Column(name="zero_win_probe_ack_cnt_Res_level4")
    private Integer zeroWinProbeAckCntResLevel4;
    @Column(name="zero_win_probe_ack_cnt_Res_level5")
    private Integer zeroWinProbeAckCntResLevel5;

    @Column(name="zero_win_probe_ack_len_req_use")
    private Boolean zeroWinProbeAckLenReqUse;
    @Column(name="zero_win_probe_ack_len_req_level1")
    private Integer zeroWinProbeAckLenReqLevel1;
    @Column(name="zero_win_probe_ack_len_req_level2")
    private Integer zeroWinProbeAckLenReqLevel2;
    @Column(name="zero_win_probe_ack_len_req_level3")
    private Integer zeroWinProbeAckLenReqLevel3;
    @Column(name="zero_win_probe_ack_len_req_level4")
    private Integer zeroWinProbeAckLenReqLevel4;
    @Column(name="zero_win_probe_ack_len_req_level5")
    private Integer zeroWinProbeAckLenReqLevel5;

    @Column(name="zero_win_probe_ack_len_res_use")
    private Boolean zeroWinProbeAckLenResUse;
    @Column(name="zero_win_probe_ack_len_res_level1")
    private Integer zeroWinProbeAckLenResLevel1;
    @Column(name="zero_win_probe_ack_len_res_level2")
    private Integer zeroWinProbeAckLenResLevel2;
    @Column(name="zero_win_probe_ack_len_res_level3")
    private Integer zeroWinProbeAckLenResLevel3;
    @Column(name="zero_win_probe_ack_len_res_level4")
    private Integer zeroWinProbeAckLenResLevel4;
    @Column(name="zero_win_probe_ack_len_res_level5")
    private Integer zeroWinProbeAckLenResLevel5;

    @Column(name="keep_alive_cnt_req_use")
    private Boolean keepAliveCntReqUse;
    @Column(name="keep_alive_cnt_req_level1")
    private Integer keepAliveCntReqLevel1;
    @Column(name="keep_alive_cnt_req_level2")
    private Integer keepAliveCntReqLevel2;
    @Column(name="keep_alive_cnt_req_level3")
    private Integer keepAliveCntReqLevel3;
    @Column(name="keep_alive_cnt_req_level4")
    private Integer keepAliveCntReqLevel4;
    @Column(name="keep_alive_cnt_req_level5")
    private Integer keepAliveCntReqLevel5;

    @Column(name="keep_alive_cnt_Res_use")
    private Boolean keepAliveCntResUse;
    @Column(name="keep_alive_cnt_Res_level1")
    private Integer keepAliveCntResLevel1;
    @Column(name="keep_alive_cnt_Res_level2")
    private Integer keepAliveCntResLevel2;
    @Column(name="keep_alive_cnt_Res_level3")
    private Integer keepAliveCntResLevel3;
    @Column(name="keep_alive_cnt_Res_level4")
    private Integer keepAliveCntResLevel4;
    @Column(name="keep_alive_cnt_Res_level5")
    private Integer keepAliveCntResLevel5;

    @Column(name="keep_alive_len_req_use")
    private Boolean keepAliveLenReqUse;
    @Column(name="keep_alive_len_req_level1")
    private Integer keepAliveLenReqLevel1;
    @Column(name="keep_alive_len_req_level2")
    private Integer keepAliveLenReqLevel2;
    @Column(name="keep_alive_len_req_level3")
    private Integer keepAliveLenReqLevel3;
    @Column(name="keep_alive_len_req_level4")
    private Integer keepAliveLenReqLevel4;
    @Column(name="keep_alive_len_req_level5")
    private Integer keepAliveLenReqLevel5;

    @Column(name="keep_alive_len_res_use")
    private Boolean keepAliveLenResUse;
    @Column(name="keep_alive_len_res_level1")
    private Integer keepAliveLenResLevel1;
    @Column(name="keep_alive_len_res_level2")
    private Integer keepAliveLenResLevel2;
    @Column(name="keep_alive_len_res_level3")
    private Integer keepAliveLenResLevel3;
    @Column(name="keep_alive_len_res_level4")
    private Integer keepAliveLenResLevel4;
    @Column(name="keep_alive_len_res_level5")
    private Integer keepAliveLenResLevel5;

    @Column(name="keep_alive_ack_cnt_req_use")
    private Boolean keepAliveAckCntReqUse;
    @Column(name="keep_alive_ack_cnt_req_level1")
    private Integer keepAliveAckCntReqLevel1;
    @Column(name="keep_alive_ack_cnt_req_level2")
    private Integer keepAliveAckCntReqLevel2;
    @Column(name="keep_alive_ack_cnt_req_level3")
    private Integer keepAliveAckCntReqLevel3;
    @Column(name="keep_alive_ack_cnt_req_level4")
    private Integer keepAliveAckCntReqLevel4;
    @Column(name="keep_alive_ack_cnt_req_level5")
    private Integer keepAliveAckCntReqLevel5;

    @Column(name="keep_alive_ack_cnt_Res_use")
    private Boolean keepAliveAckCntResUse;
    @Column(name="keep_alive_ack_cnt_Res_level1")
    private Integer keepAliveAckCntResLevel1;
    @Column(name="keep_alive_ack_cnt_Res_level2")
    private Integer keepAliveAckCntResLevel2;
    @Column(name="keep_alive_ack_cnt_Res_level3")
    private Integer keepAliveAckCntResLevel3;
    @Column(name="keep_alive_ack_cnt_Res_level4")
    private Integer keepAliveAckCntResLevel4;
    @Column(name="keep_alive_ack_cnt_Res_level5")
    private Integer keepAliveAckCntResLevel5;

    @Column(name="keep_alive_ack_len_req_use")
    private Boolean keepAliveAckLenReqUse;
    @Column(name="keep_alive_ack_len_req_level1")
    private Integer keepAliveAckLenReqLevel1;
    @Column(name="keep_alive_ack_len_req_level2")
    private Integer keepAliveAckLenReqLevel2;
    @Column(name="keep_alive_ack_len_req_level3")
    private Integer keepAliveAckLenReqLevel3;
    @Column(name="keep_alive_ack_len_req_level4")
    private Integer keepAliveAckLenReqLevel4;
    @Column(name="keep_alive_ack_len_req_level5")
    private Integer keepAliveAckLenReqLevel5;

    @Column(name="keep_alive_ack_len_res_use")
    private Boolean keepAliveAckLenResUse;
    @Column(name="keep_alive_ack_len_res_level1")
    private Integer keepAliveAckLenResLevel1;
    @Column(name="keep_alive_ack_len_res_level2")
    private Integer keepAliveAckLenResLevel2;
    @Column(name="keep_alive_ack_len_res_level3")
    private Integer keepAliveAckLenResLevel3;
    @Column(name="keep_alive_ack_len_res_level4")
    private Integer keepAliveAckLenResLevel4;
    @Column(name="keep_alive_ack_len_res_level5")
    private Integer keepAliveAckLenResLevel5;

    @Column(name="ack_rtt_min_req_use")
    private Boolean ackRttMinReqUse;
    @Column(name="ack_rtt_min_req_level1")
    private Integer ackRttMinReqLevel1;
    @Column(name="ack_rtt_min_req_level2")
    private Integer ackRttMinReqLevel2;
    @Column(name="ack_rtt_min_req_level3")
    private Integer ackRttMinReqLevel3;
    @Column(name="ack_rtt_min_req_level4")
    private Integer ackRttMinReqLevel4;
    @Column(name="ack_rtt_min_req_level5")
    private Integer ackRttMinReqLevel5;

    @Column(name="ack_rtt_min_res_use")
    private Boolean ackRttMinResUse;
    @Column(name="ack_rtt_min_res_level1")
    private Integer ackRttMinResLevel1;
    @Column(name="ack_rtt_min_res_level2")
    private Integer ackRttMinResLevel2;
    @Column(name="ack_rtt_min_res_level3")
    private Integer ackRttMinResLevel3;
    @Column(name="ack_rtt_min_res_level4")
    private Integer ackRttMinResLevel4;
    @Column(name="ack_rtt_min_res_level5")
    private Integer ackRttMinResLevel5;

    @Column(name="ack_rtt_max_req_use")
    private Boolean ackRttMaxReqUse;
    @Column(name="ack_rtt_max_req_level1")
    private Integer ackRttMaxReqLevel1;
    @Column(name="ack_rtt_max_req_level2")
    private Integer ackRttMaxReqLevel2;
    @Column(name="ack_rtt_max_req_level3")
    private Integer ackRttMaxReqLevel3;
    @Column(name="ack_rtt_max_req_level4")
    private Integer ackRttMaxReqLevel4;
    @Column(name="ack_rtt_max_req_level5")
    private Integer ackRttMaxReqLevel5;

    @Column(name="ack_rtt_max_res_use")
    private Boolean ackRttMaxResUse;
    @Column(name="ack_rtt_max_res_level1")
    private Integer ackRttMaxResLevel1;
    @Column(name="ack_rtt_max_res_level2")
    private Integer ackRttMaxResLevel2;
    @Column(name="ack_rtt_max_res_level3")
    private Integer ackRttMaxResLevel3;
    @Column(name="ack_rtt_max_res_level4")
    private Integer ackRttMaxResLevel4;
    @Column(name="ack_rtt_max_res_level5")
    private Integer ackRttMaxResLevel5;

    @Column(name="tcp_len_req_use")
    private Boolean tcpLenReqUse;
    @Column(name="tcp_len_req_level1")
    private Integer tcpLenReqLevel1;
    @Column(name="tcp_len_req_level2")
    private Integer tcpLenReqLevel2;
    @Column(name="tcp_len_req_level3")
    private Integer tcpLenReqLevel3;
    @Column(name="tcp_len_req_level4")
    private Integer tcpLenReqLevel4;
    @Column(name="tcp_len_req_level5")
    private Integer tcpLenReqLevel5;

    @Column(name="tcp_len_res_use")
    private Boolean tcpLenResUse;
    @Column(name="tcp_len_res_level1")
    private Integer tcpLenResLevel1;
    @Column(name="tcp_len_res_level2")
    private Integer tcpLenResLevel2;
    @Column(name="tcp_len_res_level3")
    private Integer tcpLenResLevel3;
    @Column(name="tcp_len_res_level4")
    private Integer tcpLenResLevel4;
    @Column(name="tcp_len_res_level5")
    private Integer tcpLenResLevel5;

    @Column(name="tcp_cnt_req_use")
    private Boolean tcpCntReqUse;
    @Column(name="tcp_cnt_req_level1")
    private Integer tcpCntReqLevel1;
    @Column(name="tcp_cnt_req_level2")
    private Integer tcpCntReqLevel2;
    @Column(name="tcp_cnt_req_level3")
    private Integer tcpCntReqLevel3;
    @Column(name="tcp_cnt_req_level4")
    private Integer tcpCntReqLevel4;
    @Column(name="tcp_cnt_req_level5")
    private Integer tcpCntReqLevel5;

    @Column(name="tcp_cnt_res_use")
    private Boolean tcpCntResUse;
    @Column(name="tcp_cnt_res_level1")
    private Integer tcpCntResLevel1;
    @Column(name="tcp_cnt_res_level2")
    private Integer tcpCntResLevel2;
    @Column(name="tcp_cnt_res_level3")
    private Integer tcpCntResLevel3;
    @Column(name="tcp_cnt_res_level4")
    private Integer tcpCntResLevel4;
    @Column(name="tcp_cnt_res_level5")
    private Integer tcpCntResLevel5;
}
