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
@Table(name="tbl_eum_rad_flowstat_l4_tcp")
public class FlowstatL4Tcp {

    @Id
    private FlowstatL4TcpKey flowstatL4TcpKey;

    @Column(name="src_port")
    private Integer srcPort;
    @Column(name="dst_port")
    private Integer dstPort;

    @Column(name="len_pdu_req_tot")
    private BigInteger lenPduReqTot;
    @Column(name="len_pdu_res_tot")
    private BigInteger lenPduResTot;
    @Column(name="pkts_pdu_req_tot")
    private BigInteger pktsPduReqTot;
    @Column(name="pkts_pdu_res_tot")
    private BigInteger pktsPduResTot;

    @Column(name="len_pdu_req_per_sec")
    private BigDecimal lenPduReqPerSec;
    @Column(name="len_pdu_res_per_sec")
    private BigDecimal lenPduResPerSec;
    @Column(name="pkts_pdu_req_per_sec")
    private BigDecimal pktsPduReqPerSec;
    @Column(name="pkts_pdu_res_per_sec")
    private BigDecimal pktsPduResPerSec;

    @Column(name="len_pdu_req_delta")
    private BigInteger lenPduReqDelta;
    @Column(name="len_pdu_res_delta")
    private BigInteger lenPduResDelta;
    @Column(name="pkts_pdu_req_delta")
    private BigInteger pktsPduReqDelta;
    @Column(name="pkts_pdu_res_delta")
    private BigInteger pktsPduResDelta;

    @Column(name="l7proto")
    private Integer l7proto;

    @Column(name="ts_rtt_syn")
    private BigDecimal tsRttSyn;
    @Column(name="ts_rtt_syn_ack")
    private BigDecimal tsRttSynAck;
    @Column(name="ts_rtt_first_ack_req")
    private BigDecimal tsRttFirstAckReq;
    @Column(name="ts_rtt_first_ack_res")
    private BigDecimal tsRttFirstAckRes;
    @Column(name="ts_rtt_last_ack_req")
    private BigDecimal tsRttLastAckReq;
    @Column(name="ts_rtt_last_ack_res")
    private BigDecimal tsRttLastAckRes;
    @Column(name="ts_req_making")
    private BigDecimal tsReqMaking;

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

    @Column(name="ts_syn_delay")
    private BigDecimal tsSynDelay;
    @Column(name="ts_synack_delay")
    private BigDecimal tsSynackDelay;
    @Column(name="ts_ack_delay_first_req")
    private BigDecimal tsAckDelayFirstReq;
    @Column(name="ts_ack_delay_first_res")
    private BigDecimal tsAckDelayFirstRes;
    @Column(name="ts_ack_delay_last_req")
    private BigDecimal tsAckDelayLastReq;
    @Column(name="ts_ack_delay_last_res")
    private BigDecimal tsAckDelayLastRes;

    @Column(name="retransmission_cnt_req_tot")
    private BigInteger retransmissionCntReqTot;
    @Column(name="retransmission_cnt_res_tot")
    private BigInteger retransmissionCntResTot;
    @Column(name="retransmission_len_req_tot")
    private BigInteger retransmissionLenReqTot;
    @Column(name="retransmission_len_res_tot")
    private BigInteger retransmissionLenResTot;

    @Column(name="fastretransmission_cnt_req_tot")
    private BigInteger fastretransmissionCntReqTot;
    @Column(name="fastretransmission_cnt_res_tot")
    private BigInteger fastretransmissionCntResTot;
    @Column(name="fastretransmission_len_req_tot")
    private BigInteger fastretransmissionLenReqTot;
    @Column(name="fastretransmission_len_res_tot")
    private BigInteger fastretransmissionLenResTot;

    @Column(name="outoforder_cnt_req_tot")
    private BigInteger outoforderCntReqTot;
    @Column(name="outoforder_cnt_res_tot")
    private BigInteger outoforderCntResTot;
    @Column(name="outoforder_len_req_tot")
    private BigInteger outoforderLenReqTot;
    @Column(name="outoforder_len_res_tot")
    private BigInteger outoforderLenResTot;

    @Column(name="lostseg_cnt_req_tot")
    private BigInteger lostsegCntReqTot;
    @Column(name="lostseg_cnt_res_tot")
    private BigInteger lostsegCntResTot;
    @Column(name="lostseg_len_req_tot")
    private BigInteger lostsegLenReqTot;
    @Column(name="lostseg_len_res_tot")
    private BigInteger lostsegLenResTot;

    @Column(name="acklost_cnt_req_tot")
    private BigInteger acklostCntReqTot;
    @Column(name="acklost_cnt_res_tot")
    private BigInteger acklostCntResTot;
    @Column(name="acklost_len_req_tot")
    private BigInteger acklostLenReqTot;
    @Column(name="acklost_len_res_tot")
    private BigInteger acklostLenResTot;

    @Column(name="winupdate_cnt_req_tot")
    private BigInteger winupdateCntReqTot;
    @Column(name="winupdate_cnt_res_tot")
    private BigInteger winupdateCntResTot;
    @Column(name="winupdate_len_req_tot")
    private BigInteger winupdateLenReqTot;
    @Column(name="winupdate_len_res_tot")
    private BigInteger winupdateLenResTot;

    @Column(name="dupack_cnt_req_tot")
    private BigInteger dupackCntReqTot;
    @Column(name="dupack_cnt_res_tot")
    private BigInteger dupackCntResTot;
    @Column(name="dupack_len_req_tot")
    private BigInteger dupackLenReqTot;
    @Column(name="dupack_len_res_tot")
    private BigInteger dupackLenResTot;

    @Column(name="zerowin_cnt_req_tot")
    private BigInteger zerowinCntReqTot;
    @Column(name="zerowin_cnt_res_tot")
    private BigInteger zerowinCntResTot;
    @Column(name="zerowin_len_req_tot")
    private BigInteger zerowinLenReqTot;
    @Column(name="zerowin_len_res_tot")
    private BigInteger zerowinLenResTot;

    @Column(name="retransmission_cnt_req_per_sec")
    private BigDecimal retransmissionCntReqPerSec;
    @Column(name="retransmission_cnt_res_per_sec")
    private BigDecimal retransmissionCntResPerSec;
    @Column(name="retransmission_len_req_per_sec")
    private BigDecimal retransmissionLenReqPerSec;
    @Column(name="retransmission_len_res_per_sec")
    private BigDecimal retransmissionLenResPerSec;

    @Column(name="fastretransmission_cnt_req_per_sec")
    private BigDecimal fastretransmissionCntReqPerSec;
    @Column(name="fastretransmission_cnt_res_per_sec")
    private BigDecimal fastretransmissionCntResPerSec;
    @Column(name="fastretransmission_len_req_per_sec")
    private BigDecimal fastretransmissionLenReqPerSec;
    @Column(name="fastretransmission_len_res_per_sec")
    private BigDecimal fastretransmissionLenResPerSec;

    @Column(name="outoforder_cnt_req_per_sec")
    private BigDecimal outoforderCntReqPerSec;
    @Column(name="outoforder_cnt_res_per_sec")
    private BigDecimal outoforderCntResPerSec;
    @Column(name="outoforder_len_req_per_sec")
    private BigDecimal outoforderLenReqPerSec;
    @Column(name="outoforder_len_res_per_sec")
    private BigDecimal outoforderLenResPerSec;

    @Column(name="lostseg_cnt_req_per_sec")
    private BigDecimal lostsegCntReqPerSec;
    @Column(name="lostseg_cnt_res_per_sec")
    private BigDecimal lostsegCntResPerSec;
    @Column(name="lostseg_len_req_per_sec")
    private BigDecimal lostsegLenReqPerSec;
    @Column(name="lostseg_len_res_per_sec")
    private BigDecimal lostsegLenResPerSec;

    @Column(name="acklost_cnt_req_per_sec")
    private BigDecimal acklostCntReqPerSec;
    @Column(name="acklost_cnt_res_per_sec")
    private BigDecimal acklostCntResPerSec;
    @Column(name="acklost_len_req_per_sec")
    private BigDecimal acklostLenReqPerSec;
    @Column(name="acklost_len_res_per_sec")
    private BigDecimal acklostLenResPerSec;

    @Column(name="winupdate_cnt_req_per_sec")
    private BigDecimal winupdateCntReqPerSec;
    @Column(name="winupdate_cnt_res_per_sec")
    private BigDecimal winupdateCntResPerSec;
    @Column(name="winupdate_len_req_per_sec")
    private BigDecimal winupdateLenReqPerSec;
    @Column(name="winupdate_len_res_per_sec")
    private BigDecimal winupdateLenResPerSec;

    @Column(name="dupack_cnt_req_per_sec")
    private BigDecimal dupackCntReqPerSec;
    @Column(name="dupack_cnt_res_per_sec")
    private BigDecimal dupackCntResPerSec;
    @Column(name="dupack_len_req_per_sec")
    private BigDecimal dupackLenReqPerSec;
    @Column(name="dupack_len_res_per_sec")
    private BigDecimal dupackLenResPerSec;

    @Column(name="zerowin_cnt_req_per_sec")
    private BigDecimal zerowinCntReqPerSec;
    @Column(name="zerowin_cnt_res_per_sec")
    private BigDecimal zerowinCntResPerSec;
    @Column(name="zerowin_len_req_per_sec")
    private BigDecimal zerowinLenReqPerSec;
    @Column(name="zerowin_len_res_per_sec")
    private BigDecimal zerowinLenResPerSec;

    @Column(name="retransmission_cnt_req_delta")
    private BigInteger retransmissionCntReqDelta;
    @Column(name="retransmission_cnt_res_delta")
    private BigInteger retransmissionCntResDelta;
    @Column(name="retransmission_len_req_delta")
    private BigInteger retransmissionLenReqDelta;
    @Column(name="retransmission_len_res_delta")
    private BigInteger retransmissionLenResDelta;

    @Column(name="fastretransmission_cnt_req_delta")
    private BigInteger fastretransmissionCntReqDelta;
    @Column(name="fastretransmission_cnt_res_delta")
    private BigInteger fastretransmissionCntResDelta;
    @Column(name="fastretransmission_len_req_delta")
    private BigInteger fastretransmissionLenReqDelta;
    @Column(name="fastretransmission_len_res_delta")
    private BigInteger fastretransmissionLenResDelta;

    @Column(name="outoforder_cnt_req_delta")
    private BigInteger outoforderCntReqDelta;
    @Column(name="outoforder_cnt_res_delta")
    private BigInteger outoforderCntResDelta;
    @Column(name="outoforder_len_req_delta")
    private BigInteger outoforderLenReqDelta;
    @Column(name="outoforder_len_res_delta")
    private BigInteger outoforderLenResDelta;

    @Column(name="lostseg_cnt_req_delta")
    private BigInteger lostsegCntReqDelta;
    @Column(name="lostseg_cnt_res_delta")
    private BigInteger lostsegCntResDelta;
    @Column(name="lostseg_len_req_delta")
    private BigInteger lostsegLenReqDelta;
    @Column(name="lostseg_len_res_delta")
    private BigInteger lostsegLenResDelta;

    @Column(name="acklost_cnt_req_delta")
    private BigInteger acklostCntReqDelta;
    @Column(name="acklost_cnt_res_delta")
    private BigInteger acklostCntResDelta;
    @Column(name="acklost_len_req_delta")
    private BigInteger acklostLenReqDelta;
    @Column(name="acklost_len_res_delta")
    private BigInteger acklostLenResDelta;

    @Column(name="winupdate_cnt_req_delta")
    private BigInteger winupdateCntReqDelta;
    @Column(name="winupdate_cnt_res_delta")
    private BigInteger winupdateCntResDelta;
    @Column(name="winupdate_len_req_delta")
    private BigInteger winupdateLenReqDelta;
    @Column(name="winupdate_len_res_delta")
    private BigInteger winupdateLenResDelta;

    @Column(name="dupack_cnt_req_delta")
    private BigInteger dupackCntReqDelta;
    @Column(name="dupack_cnt_res_delta")
    private BigInteger dupackCntResDelta;
    @Column(name="dupack_len_req_delta")
    private BigInteger dupackLenReqDelta;
    @Column(name="dupack_len_res_delta")
    private BigInteger dupackLenResDelta;

    @Column(name="zerowin_cnt_req_delta")
    private BigInteger zerowinCntReqDelta;
    @Column(name="zerowin_cnt_res_delta")
    private BigInteger zerowinCntResDelta;
    @Column(name="zerowin_len_req_delta")
    private BigInteger zerowinLenReqDelta;
    @Column(name="zerowin_len_res_delta")
    private BigInteger zerowinLenResDelta;

    @Column(name="spurious_retransmission_cnt_req_tot")
    private BigInteger spuriousRetransmissionCntReqTot;
    @Column(name="spurious_retransmission_cnt_res_tot")
    private BigInteger spuriousRetransmissionCntResTot;
    @Column(name="spurious_retransmission_len_req_tot")
    private BigInteger spuriousRetransmissionLenReqTot;
    @Column(name="spurious_retransmission_len_res_tot")
    private BigInteger spuriousRetransmissionLenResTot;

    @Column(name="spurious_retransmission_cnt_req_per_sec")
    private BigDecimal spuriousRetransmissionCntReqPerSec;
    @Column(name="spurious_retransmission_cnt_res_per_sec")
    private BigDecimal spuriousRetransmissionCntResPerSec;
    @Column(name="spurious_retransmission_len_req_per_sec")
    private BigDecimal spuriousRetransmissionLenReqPerSec;
    @Column(name="spurious_retransmission_len_res_per_sec")
    private BigDecimal spuriousRetransmissionLenResPerSec;

    @Column(name="spurious_retransmission_cnt_req_delta")
    private BigInteger spuriousRetransmissionCntReqDelta;
    @Column(name="spurious_retransmission_cnt_res_delta")
    private BigInteger spuriousRetransmissionCntResDelta;
    @Column(name="spurious_retransmission_len_req_delta")
    private BigInteger spuriousRetransmissionLenReqDelta;
    @Column(name="spurious_retransmission_len_res_delta")
    private BigInteger spuriousRetransmissionLenResDelta;

    @Column(name="overlap_cnt_req_tot")
    private BigInteger overlapCntReqTot;
    @Column(name="overlap_cnt_res_tot")
    private BigInteger overlapCntResTot;
    @Column(name="overlap_len_req_tot")
    private BigInteger overlapLenReqTot;
    @Column(name="overlap_len_res_tot")
    private BigInteger overlapLenResTot;

    @Column(name="overlap_cnt_req_per_sec")
    private BigDecimal overlapCntReqPerSec;
    @Column(name="overlap_cnt_res_per_sec")
    private BigDecimal overlapCntResPerSec;
    @Column(name="overlap_len_req_per_sec")
    private BigDecimal overlapLenReqPerSec;
    @Column(name="overlap_len_res_per_sec")
    private BigDecimal overlapLenResPerSec;

    @Column(name="overlap_cnt_req_delta")
    private BigInteger overlapCntReqDelta;
    @Column(name="overlap_cnt_res_delta")
    private BigInteger overlapCntResDelta;
    @Column(name="overlap_len_req_delta")
    private BigInteger overlapLenReqDelta;
    @Column(name="overlap_len_res_delta")
    private BigInteger overlapLenResDelta;

    @Column(name="overlap_attack_cnt_req_tot")
    private BigInteger overlapAttackCntReqTot;
    @Column(name="overlap_attack_cnt_res_tot")
    private BigInteger overlapAttackCntResTot;
    @Column(name="overlap_attack_len_req_tot")
    private BigInteger overlapAttackLenReqTot;
    @Column(name="overlap_attack_len_res_tot")
    private BigInteger overlapAttackLenResTot;

    @Column(name="overlap_attack_cnt_req_per_sec")
    private BigDecimal overlapAttackCntReqPerSec;
    @Column(name="overlap_attack_cnt_res_per_sec")
    private BigDecimal overlapAttackCntResPerSec;
    @Column(name="overlap_attack_len_req_per_sec")
    private BigDecimal overlapAttackLenReqPerSec;
    @Column(name="overlap_attack_len_res_per_sec")
    private BigDecimal overlapAttackLenResPerSec;

    @Column(name="overlap_attack_cnt_req_delta")
    private BigInteger overlapAttackCntReqDelta;
    @Column(name="overlap_attack_cnt_res_delta")
    private BigInteger overlapAttackCntResDelta;
    @Column(name="overlap_attack_len_req_delta")
    private BigInteger overlapAttackLenReqDelta;
    @Column(name="overlap_attack_len_res_delta")
    private BigInteger overlapAttackLenResDelta;

    @Column(name="zero_win_probe_cnt_req_tot")
    private BigInteger zeroWinProbeCntReqTot;
    @Column(name="zero_win_probe_cnt_res_tot")
    private BigInteger zeroWinProbeCntResTot;
    @Column(name="zero_win_probe_len_req_tot")
    private BigInteger zeroWinProbeLenReqTot;
    @Column(name="zero_win_probe_len_res_tot")
    private BigInteger zeroWinProbeLenResTot;

    @Column(name="zero_win_probe_cnt_req_per_sec")
    private BigDecimal zeroWinProbeCntReqPerSec;
    @Column(name="zero_win_probe_cnt_res_per_sec")
    private BigDecimal zeroWinProbeCntResPerSec;
    @Column(name="zero_win_probe_len_req_per_sec")
    private BigDecimal zeroWinProbeLenReqPerSec;
    @Column(name="zero_win_probe_len_res_per_sec")
    private BigDecimal zeroWinProbeLenResPerSec;

    @Column(name="zero_win_probe_cnt_req_delta")
    private BigInteger zeroWinProbeCntReqDelta;
    @Column(name="zero_win_probe_cnt_res_delta")
    private BigInteger zeroWinProbeCntResDelta;
    @Column(name="zero_win_probe_len_req_delta")
    private BigInteger zeroWinProbeLenReqDelta;
    @Column(name="zero_win_probe_len_res_delta")
    private BigInteger zeroWinProbeLenResDelta;

    @Column(name="zero_win_probe_ack_cnt_req_tot")
    private BigInteger zeroWinProbeAckCntReqTot;
    @Column(name="zero_win_probe_ack_cnt_res_tot")
    private BigInteger zeroWinProbeAckCntResTot;
    @Column(name="zero_win_probe_ack_len_req_tot")
    private BigInteger zeroWinProbeAckLenReqTot;
    @Column(name="zero_win_probe_ack_len_res_tot")
    private BigInteger zeroWinProbeAckLenResTot;

    @Column(name="zero_win_probe_ack_cnt_req_per_sec")
    private BigDecimal zeroWinProbeAckCntReqPerSec;
    @Column(name="zero_win_probe_ack_cnt_res_per_sec")
    private BigDecimal zeroWinProbeAckCntResPerSec;
    @Column(name="zero_win_probe_ack_len_req_per_sec")
    private BigDecimal zeroWinProbeAckLenReqPerSec;
    @Column(name="zero_win_probe_ack_len_res_per_sec")
    private BigDecimal zeroWinProbeAckLenResPerSec;

    @Column(name="zero_win_probe_ack_cnt_req_delta")
    private BigInteger zeroWinProbeAckCntReqDelta;
    @Column(name="zero_win_probe_ack_cnt_res_delta")
    private BigInteger zeroWinProbeAckCntResDelta;
    @Column(name="zero_win_probe_ack_len_req_delta")
    private BigInteger zeroWinProbeAckLenReqDelta;
    @Column(name="zero_win_probe_ack_len_res_delta")
    private BigInteger zeroWinProbeAckLenResDelta;

    @Column(name="keep_alive_cnt_req_tot")
    private BigInteger keepAliveCntReqTot;
    @Column(name="keep_alive_cnt_res_tot")
    private BigInteger keepAliveCntResTot;
    @Column(name="keep_alive_len_req_tot")
    private BigInteger keepAliveLenReqTot;
    @Column(name="keep_alive_len_res_tot")
    private BigInteger keepAliveLenResTot;

    @Column(name="keep_alive_cnt_req_per_sec")
    private BigDecimal keepAliveCntReqPerSec;
    @Column(name="keep_alive_cnt_res_per_sec")
    private BigDecimal keepAliveCntResPerSec;
    @Column(name="keep_alive_len_req_per_sec")
    private BigDecimal keepAliveLenReqPerSec;
    @Column(name="keep_alive_len_res_per_sec")
    private BigDecimal keepAliveLenResPerSec;

    @Column(name="keep_alive_cnt_req_delta")
    private BigInteger keepAliveCntReqDelta;
    @Column(name="keep_alive_cnt_res_delta")
    private BigInteger keepAliveCntResDelta;
    @Column(name="keep_alive_len_req_delta")
    private BigInteger keepAliveLenReqDelta;
    @Column(name="keep_alive_len_res_delta")
    private BigInteger keepAliveLenResDelta;

    @Column(name="keep_alive_ack_cnt_req_tot")
    private BigInteger keepAliveAckCntReqTot;
    @Column(name="keep_alive_ack_cnt_res_tot")
    private BigInteger keepAliveAckCntResTot;
    @Column(name="keep_alive_ack_len_req_tot")
    private BigInteger keepAliveAckLenReqTot;
    @Column(name="keep_alive_ack_len_res_tot")
    private BigInteger keepAliveAckLenResTot;

    @Column(name="keep_alive_ack_cnt_req_per_sec")
    private BigDecimal keepAliveAckCntReqPerSec;
    @Column(name="keep_alive_ack_cnt_res_per_sec")
    private BigDecimal keepAliveAckCntResPerSec;
    @Column(name="keep_alive_ack_len_req_per_sec")
    private BigDecimal keepAliveAckLenReqPerSec;
    @Column(name="keep_alive_ack_len_res_per_sec")
    private BigDecimal keepAliveAckLenResPerSec;

    @Column(name="keep_alive_ack_cnt_req_delta")
    private BigInteger keepAliveAckCntReqDelta;
    @Column(name="keep_alive_ack_cnt_res_delta")
    private BigInteger keepAliveAckCntResDelta;
    @Column(name="keep_alive_ack_len_req_delta")
    private BigInteger keepAliveAckLenReqDelta;
    @Column(name="keep_alive_ack_len_res_delta")
    private BigInteger keepAliveAckLenResDelta;

    @Column(name="ts_syn")
    private BigDecimal tsSyn;
    @Column(name="ts_syn_ack")
    private BigDecimal tsSynAck;
    @Column(name="ts_first_ack")
    private BigDecimal tsFirstAck;
    @Column(name="ts_first_ack_req")
    private BigDecimal tsFirstAckReq;
    @Column(name="ts_first_ack_req_ack")
    private BigDecimal tsFirstAckReqAck;
    @Column(name="ts_first_ack_res")
    private BigDecimal tsFirstAckRes;
    @Column(name="ts_first_ack_res_ack")
    private BigDecimal tsFirstAckResAck;
    @Column(name="ts_first_push_req")
    private BigDecimal tsFirstPushReq;
    @Column(name="ts_first_push_res")
    private BigDecimal tsFirstPushRes;

    @Column(name="ack_rtt_min_req_tot")
    private BigDecimal ackRttMinReqTot;
    @Column(name="ack_rtt_min_res_tot")
    private BigDecimal ackRttMinResTot;
    @Column(name="ack_rtt_max_req_tot")
    private BigDecimal ackRttMaxReqTot;
    @Column(name="ack_rtt_max_res_tot")
    private BigDecimal ackRttMaxResTot;
    @Column(name="ack_rtt_min_req_curr")
    private BigDecimal ackRttMinReqCurr;
    @Column(name="ack_rtt_min_res_curr")
    private BigDecimal ackRttMinResCurr;
    @Column(name="ack_rtt_max_req_curr")
    private BigDecimal ackRttMaxReqCurr;
    @Column(name="ack_rtt_max_res_curr")
    private BigDecimal ackRttMaxResCurr;

    @Column(name="tcp_flag_stat_fin_req_tot")
    private BigInteger tcpFlagStatFinReqTot;
    @Column(name="tcp_flag_stat_fin_res_tot")
    private BigInteger tcpFlagStatFinResTot;

    @Column(name="tcp_flag_stat_syn_req_tot")
    private BigInteger tcpFlagStatSynReqTot;
    @Column(name="tcp_flag_stat_syn_res_tot")
    private BigInteger tcpFlagStatSynResTot;

    @Column(name="tcp_flag_stat_rst_req_tot")
    private BigInteger tcpFlagStatRstReqTot;
    @Column(name="tcp_flag_stat_rst_res_tot")
    private BigInteger tcpFlagStatRstResTot;

    @Column(name="tcp_flag_stat_psh_req_tot")
    private BigInteger tcpFlagStatPshReqTot;
    @Column(name="tcp_flag_stat_psh_res_tot")
    private BigInteger tcpFlagStatPshResTot;

    @Column(name="tcp_flag_stat_ack_req_tot")
    private BigInteger tcpFlagStatAckReqTot;
    @Column(name="tcp_flag_stat_ack_res_tot")
    private BigInteger tcpFlagStatAckResTot;

    @Column(name="tcp_flag_stat_urg_req_tot")
    private BigInteger tcpFlagStatUrgReqTot;
    @Column(name="tcp_flag_stat_urg_res_tot")
    private BigInteger tcpFlagStatUrgResTot;

    @Column(name="tcp_flag_stat_fin_req_per_sec")
    private BigDecimal tcpFlagStatFinReqPerSec;
    @Column(name="tcp_flag_stat_fin_res_per_sec")
    private BigDecimal tcpFlagStatFinResPerSec;

    @Column(name="tcp_flag_stat_syn_req_per_sec")
    private BigDecimal tcpFlagStatSynReqPerSec;
    @Column(name="tcp_flag_stat_syn_res_per_sec")
    private BigDecimal tcpFlagStatSynResPerSec;

    @Column(name="tcp_flag_stat_rst_req_per_sec")
    private BigDecimal tcpflagstatrstreqPerSec;
    @Column(name="tcp_flag_stat_rst_res_per_sec")
    private BigDecimal tcpFlagStatRstResPerSec;

    @Column(name="tcp_flag_stat_psh_req_per_sec")
    private BigDecimal tcpFlagStatPshReqPerSec;
    @Column(name="tcp_flag_stat_psh_res_per_sec")
    private BigDecimal tcpFlagStatPshResPerSec;

    @Column(name="tcp_flag_stat_ack_req_per_sec")
    private BigDecimal tcpFlagStatAckReqPerSec;
    @Column(name="tcp_flag_stat_ack_res_per_sec")
    private BigDecimal tcpFlagStatAckResPerSec;

    @Column(name="tcp_flag_stat_urg_req_per_sec")
    private BigDecimal tcpFlagStatUrgReqPerSec;
    @Column(name="tcp_flag_stat_urg_res_per_sec")
    private BigDecimal tcpFlagStatUrgResPerSec;

    @Column(name="tcp_flag_stat_fin_req_delta")
    private BigInteger tcpFlagStatFinReqDelta;
    @Column(name="tcp_flag_stat_fin_res_delta")
    private BigInteger tcpFlagStatFinResDelta;

    @Column(name="tcp_flag_stat_syn_req_delta")
    private BigInteger tcpFlagStatSynReqDelta;
    @Column(name="tcp_flag_stat_syn_res_delta")
    private BigInteger tcpFlagStatSynResDelta;

    @Column(name="tcp_flag_stat_rst_req_delta")
    private BigInteger tcpFlagStatRstReqDelta;
    @Column(name="tcp_flag_stat_rst_res_delta")
    private BigInteger tcpFlagStatRstResDelta;

    @Column(name="tcp_flag_stat_psh_req_delta")
    private BigInteger tcpFlagStatPshReqDelta;
    @Column(name="tcp_flag_stat_psh_res_delta")
    private BigInteger tcpFlagStatPshResDelta;

    @Column(name="tcp_flag_stat_ack_req_delta")
    private BigInteger tcpFlagStatAckReqDelta;
    @Column(name="tcp_flag_stat_ack_res_delta")
    private BigInteger tcpFlagStatAckResDelta;

    @Column(name="tcp_flag_stat_urg_req_delta")
    private BigInteger tcpFlagStatUrgReqDelta;
    @Column(name="tcp_flag_stat_urg_res_delta")
    private BigInteger tcpFlagStatUrgResDelta;

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

    @Column(name="application_direction")
    private Integer applicationDirection;

    @Column(name="req_conn_refused")
    private Integer reqConnRefused;
    @Column(name="res_conn_refused")
    private Integer resConnRefused;

    @Column(name="syn_timeout")
    private Integer synTimeout;
    @Column(name="synack_timeout")
    private Integer synackTimeout;

    @Column(name="req_ack_timeout_cnt")
    private BigInteger reqAckTimeoutCnt;
    @Column(name="res_ack_timeout_cnt")
    private BigInteger resAckTimeoutCnt;

    @Column(name="stopped_transaction")
    private Integer stoppedTransaction;

    @Column(name="session_timeout")
    private Integer sessionTimeout;
    @Column(name="fin_wait_timeout")
    private Integer finWaitTimeout;
    @Column(name="close_wait_timeout")
    private Integer closeWaitTimeout;
    @Column(name="last_ack_timeout")
    private Integer lastAckTimeout;

}
