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
@Table (name = "tbl_info_alarm_transaction")
public class InfoAlarmTransaction {

	@Id
	@SequenceGenerator(name="tbl_info_alarm_transaction_seq", sequenceName="tbl_info_alarm_transaction_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_alarm_transaction_seq")
   	@Column(name = "transaction_id")
	private Integer transactionId;

   	@Column(name = "ts_page_use")
	private Boolean tsPageUse;

   	@Column(name = "ts_page_caution")
	private Integer tsPageCaution;

   	@Column(name = "ts_page_warning")
	private Integer tsPageWarning;

   	@Column(name = "ts_page_danger")
	private Integer tsPageDanger;

   	@Column(name = "ts_page_obstacle")
	private Integer tsPageObstacle;

   	@Column(name = "ts_page_gap_use")
	private Boolean tsPageGapUse;

   	@Column(name = "ts_page_gap_caution")
	private Integer tsPageGapCaution;

   	@Column(name = "ts_page_gap_warning")
	private Integer tsPageGapWarning;

   	@Column(name = "ts_page_gap_danger")
	private Integer tsPageGapDanger;

   	@Column(name = "ts_page_gap_obstacle")
	private Integer tsPageGapObstacle;

   	@Column(name = "ts_page_res_init_use")
	private Boolean tsPageResInitUse;

   	@Column(name = "ts_page_res_init_caution")
	private Integer tsPageResInitCaution;

   	@Column(name = "ts_page_res_init_warning")
	private Integer tsPageResInitWarning;

   	@Column(name = "ts_page_res_init_danger")
	private Integer tsPageResInitDanger;

   	@Column(name = "ts_page_res_init_obstacle")
	private Integer tsPageResInitObstacle;

   	@Column(name = "ts_page_res_init_gap_use")
	private Boolean tsPageResInitGapUse;

   	@Column(name = "ts_page_res_init_gap_caution")
	private Integer tsPageResInitGapCaution;

   	@Column(name = "ts_page_res_init_gap_warning")
	private Integer tsPageResInitGapWarning;

   	@Column(name = "ts_page_res_init_gap_danger")
	private Integer tsPageResInitGapDanger;

   	@Column(name = "ts_page_res_init_gap_obstacle")
	private Integer tsPageResInitGapObstacle;

   	@Column(name = "ts_page_res_app_use")
	private Boolean tsPageResAppUse;

   	@Column(name = "ts_page_res_app_caution")
	private Integer tsPageResAppCaution;

   	@Column(name = "ts_page_res_app_warning")
	private Integer tsPageResAppWarning;

   	@Column(name = "ts_page_res_app_danger")
	private Integer tsPageResAppDanger;

   	@Column(name = "ts_page_res_app_obstacle")
	private Integer tsPageResAppObstacle;

   	@Column(name = "ts_page_res_app_gap_use")
	private Boolean tsPageResAppGapUse;

   	@Column(name = "ts_page_res_app_gap_caution")
	private Integer tsPageResAppGapCaution;

   	@Column(name = "ts_page_res_app_gap_warning")
	private Integer tsPageResAppGapWarning;

   	@Column(name = "ts_page_res_app_gap_danger")
	private Integer tsPageResAppGapDanger;

   	@Column(name = "ts_page_res_app_gap_obstacle")
	private Integer tsPageResAppGapObstacle;

   	@Column(name = "ts_page_res_use")
	private Boolean tsPageResUse;

   	@Column(name = "ts_page_res_caution")
	private Integer tsPageResCaution;

   	@Column(name = "ts_page_res_warning")
	private Integer tsPageResWarning;

   	@Column(name = "ts_page_res_danger")
	private Integer tsPageResDanger;

   	@Column(name = "ts_page_res_obstacle")
	private Integer tsPageResObstacle;

   	@Column(name = "ts_page_res_gap_use")
	private Boolean tsPageResGapUse;

   	@Column(name = "ts_page_res_gap_caution")
	private Integer tsPageResGapCaution;

   	@Column(name = "ts_page_res_gap_warning")
	private Integer tsPageResGapWarning;

   	@Column(name = "ts_page_res_gap_danger")
	private Integer tsPageResGapDanger;

   	@Column(name = "ts_page_res_gap_obstacle")
	private Integer tsPageResGapObstacle;

   	@Column(name = "ts_page_transfer_req_use")
	private Boolean tsPageTransferReqUse;

   	@Column(name = "ts_page_transfer_req_caution")
	private Integer tsPageTransferReqCaution;

   	@Column(name = "ts_page_transfer_req_warning")
	private Integer tsPageTransferReqWarning;

   	@Column(name = "ts_page_transfer_req_danger")
	private Integer tsPageTransferReqDanger;

   	@Column(name = "ts_page_transfer_req_obstacle")
	private Integer tsPageTransferReqObstacle;

   	@Column(name = "ts_page_transfer_req_gap_use")
	private Boolean tsPageTransferReqGapUse;

   	@Column(name = "ts_page_transfer_req_gap_caution")
	private Integer tsPageTransferReqGapCaution;

   	@Column(name = "ts_page_transfer_req_gap_warning")
	private Integer tsPageTransferReqGapWarning;

   	@Column(name = "ts_page_transfer_req_gap_danger")
	private Integer tsPageTransferReqGapDanger;

   	@Column(name = "ts_page_transfer_req_gap_obstacle")
	private Integer tsPageTransferReqGapObstacle;

   	@Column(name = "ts_page_transfer_res_use")
	private Boolean tsPageTransferResUse;

   	@Column(name = "ts_page_transfer_res_caution")
	private Integer tsPageTransferResCaution;

   	@Column(name = "ts_page_transfer_res_warning")
	private Integer tsPageTransferResWarning;

   	@Column(name = "ts_page_transfer_res_danger")
	private Integer tsPageTransferResDanger;

   	@Column(name = "ts_page_transfer_res_obstacle")
	private Integer tsPageTransferResObstacle;

   	@Column(name = "ts_page_transfer_res_gap_use")
	private Boolean tsPageTransferResGapUse;

   	@Column(name = "ts_page_transfer_res_gap_caution")
	private Integer tsPageTransferResGapCaution;

   	@Column(name = "ts_page_transfer_res_gap_warning")
	private Integer tsPageTransferResGapWarning;

   	@Column(name = "ts_page_transfer_res_gap_danger")
	private Integer tsPageTransferResGapDanger;

   	@Column(name = "ts_page_transfer_res_gap_obstacle")
	private Integer tsPageTransferResGapObstacle;

   	@Column(name = "ts_page_rtt_conn_sum_req_use")
	private Boolean tsPageRttConnSumReqUse;

   	@Column(name = "ts_page_rtt_conn_sum_req_caution")
	private Integer tsPageRttConnSumReqCaution;

   	@Column(name = "ts_page_rtt_conn_sum_req_warning")
	private Integer tsPageRttConnSumReqWarning;

   	@Column(name = "ts_page_rtt_conn_sum_req_danger")
	private Integer tsPageRttConnSumReqDanger;

   	@Column(name = "ts_page_rtt_conn_sum_req_obstacle")
	private Integer tsPageRttConnSumReqObstacle;

   	@Column(name = "ts_page_rtt_conn_sum_res_use")
	private Boolean tsPageRttConnSumResUse;

   	@Column(name = "ts_page_rtt_conn_sum_res_caution")
	private Integer tsPageRttConnSumResCaution;

   	@Column(name = "ts_page_rtt_conn_sum_res_warning")
	private Integer tsPageRttConnSumResWarning;

   	@Column(name = "ts_page_rtt_conn_sum_res_danger")
	private Integer tsPageRttConnSumResDanger;

   	@Column(name = "ts_page_rtt_conn_sum_res_obstacle")
	private Integer tsPageRttConnSumResObstacle;

   	@Column(name = "ts_page_rtt_ack_sum_req_use")
	private Boolean tsPageRttAckSumReqUse;

   	@Column(name = "ts_page_rtt_ack_sum_req_caution")
	private Integer tsPageRttAckSumReqCaution;

   	@Column(name = "ts_page_rtt_ack_sum_req_warning")
	private Integer tsPageRttAckSumReqWarning;

   	@Column(name = "ts_page_rtt_ack_sum_req_danger")
	private Integer tsPageRttAckSumReqDanger;

   	@Column(name = "ts_page_rtt_ack_sum_req_obstacle")
	private Integer tsPageRttAckSumReqObstacle;

   	@Column(name = "ts_page_rtt_ack_sum_res_use")
	private Boolean tsPageRttAckSumResUse;

   	@Column(name = "ts_page_rtt_ack_sum_res_caution")
	private Integer tsPageRttAckSumResCaution;

   	@Column(name = "ts_page_rtt_ack_sum_res_warning")
	private Integer tsPageRttAckSumResWarning;

   	@Column(name = "ts_page_rtt_ack_sum_res_danger")
	private Integer tsPageRttAckSumResDanger;

   	@Column(name = "ts_page_rtt_ack_sum_res_obstacle")
	private Integer tsPageRttAckSumResObstacle;

   	@Column(name = "ts_page_req_making_sum_use")
	private Boolean tsPageReqMakingSumUse;

   	@Column(name = "ts_page_req_making_sum_caution")
	private Integer tsPageReqMakingSumCaution;

   	@Column(name = "ts_page_req_making_sum_warning")
	private Integer tsPageReqMakingSumWarning;

   	@Column(name = "ts_page_req_making_sum_danger")
	private Integer tsPageReqMakingSumDanger;

   	@Column(name = "ts_page_req_making_sum_obstacle")
	private Integer tsPageReqMakingSumObstacle;

   	@Column(name = "page_rtt_conn_cnt_req_use")
	private Boolean pageRttConnCntReqUse;

   	@Column(name = "page_rtt_conn_cnt_req_caution")
	private Integer pageRttConnCntReqCaution;

   	@Column(name = "page_rtt_conn_cnt_req_warning")
	private Integer pageRttConnCntReqWarning;

   	@Column(name = "page_rtt_conn_cnt_req_danger")
	private Integer pageRttConnCntReqDanger;

   	@Column(name = "page_rtt_conn_cnt_req_obstacle")
	private Integer pageRttConnCntReqObstacle;

   	@Column(name = "page_rtt_conn_cnt_res_use")
	private Boolean pageRttConnCntResUse;

   	@Column(name = "page_rtt_conn_cnt_res_caution")
	private Integer pageRttConnCntResCaution;

   	@Column(name = "page_rtt_conn_cnt_res_warning")
	private Integer pageRttConnCntResWarning;

   	@Column(name = "page_rtt_conn_cnt_res_danger")
	private Integer pageRttConnCntResDanger;

   	@Column(name = "page_rtt_conn_cnt_res_obstacle")
	private Integer pageRttConnCntResObstacle;

   	@Column(name = "page_rtt_ack_cnt_req_use")
	private Boolean pageRttAckCntReqUse;

   	@Column(name = "page_rtt_ack_cnt_req_caution")
	private Integer pageRttAckCntReqCaution;

   	@Column(name = "page_rtt_ack_cnt_req_warning")
	private Integer pageRttAckCntReqWarning;

   	@Column(name = "page_rtt_ack_cnt_req_danger")
	private Integer pageRttAckCntReqDanger;

   	@Column(name = "page_rtt_ack_cnt_req_obstacle")
	private Integer pageRttAckCntReqObstacle;

   	@Column(name = "page_rtt_ack_cnt_res_use")
	private Boolean pageRttAckCntResUse;

   	@Column(name = "page_rtt_ack_cnt_res_caution")
	private Integer pageRttAckCntResCaution;

   	@Column(name = "page_rtt_ack_cnt_res_warning")
	private Integer pageRttAckCntResWarning;

   	@Column(name = "page_rtt_ack_cnt_res_danger")
	private Integer pageRttAckCntResDanger;

   	@Column(name = "page_rtt_ack_cnt_res_obstacle")
	private Integer pageRttAckCntResObstacle;

   	@Column(name = "page_req_making_cnt_use")
	private Boolean pageReqMakingCntUse;

   	@Column(name = "page_req_making_cnt_caution")
	private Integer pageReqMakingCntCaution;

   	@Column(name = "page_req_making_cnt_warning")
	private Integer pageReqMakingCntWarning;

   	@Column(name = "page_req_making_cnt_danger")
	private Integer pageReqMakingCntDanger;

   	@Column(name = "page_req_making_cnt_obstacle")
	private Integer pageReqMakingCntObstacle;

   	@Column(name = "page_http_len_req_use")
	private Boolean pageHttpLenReqUse;

   	@Column(name = "page_http_len_req_caution")
	private Integer pageHttpLenReqCaution;

   	@Column(name = "page_http_len_req_warning")
	private Integer pageHttpLenReqWarning;

   	@Column(name = "page_http_len_req_danger")
	private Integer pageHttpLenReqDanger;

   	@Column(name = "page_http_len_req_obstacle")
	private Integer pageHttpLenReqObstacle;

   	@Column(name = "page_http_len_res_use")
	private Boolean pageHttpLenResUse;

   	@Column(name = "page_http_len_res_caution")
	private Integer pageHttpLenResCaution;

   	@Column(name = "page_http_len_res_warning")
	private Integer pageHttpLenResWarning;

   	@Column(name = "page_http_len_res_danger")
	private Integer pageHttpLenResDanger;

   	@Column(name = "page_http_len_res_obstacle")
	private Integer pageHttpLenResObstacle;

   	@Column(name = "page_http_cnt_req_use")
	private Boolean pageHttpCntReqUse;

   	@Column(name = "page_http_cnt_req_caution")
	private Integer pageHttpCntReqCaution;

   	@Column(name = "page_http_cnt_req_warning")
	private Integer pageHttpCntReqWarning;

   	@Column(name = "page_http_cnt_req_danger")
	private Integer pageHttpCntReqDanger;

   	@Column(name = "page_http_cnt_req_obstacle")
	private Integer pageHttpCntReqObstacle;

   	@Column(name = "page_http_cnt_res_use")
	private Boolean pageHttpCntResUse;

   	@Column(name = "page_http_cnt_res_caution")
	private Integer pageHttpCntResCaution;

   	@Column(name = "page_http_cnt_res_warning")
	private Integer pageHttpCntResWarning;

   	@Column(name = "page_http_cnt_res_danger")
	private Integer pageHttpCntResDanger;

   	@Column(name = "page_http_cnt_res_obstacle")
	private Integer pageHttpCntResObstacle;

   	@Column(name = "page_pkt_len_req_use")
	private Boolean pagePktLenReqUse;

   	@Column(name = "page_pkt_len_req_caution")
	private Integer pagePktLenReqCaution;

   	@Column(name = "page_pkt_len_req_warning")
	private Integer pagePktLenReqWarning;

   	@Column(name = "page_pkt_len_req_danger")
	private Integer pagePktLenReqDanger;

   	@Column(name = "page_pkt_len_req_obstacle")
	private Integer pagePktLenReqObstacle;

   	@Column(name = "page_pkt_len_res_use")
	private Boolean pagePktLenResUse;

   	@Column(name = "page_pkt_len_res_caution")
	private Integer pagePktLenResCaution;

   	@Column(name = "page_pkt_len_res_warning")
	private Integer pagePktLenResWarning;

   	@Column(name = "page_pkt_len_res_danger")
	private Integer pagePktLenResDanger;

   	@Column(name = "page_pkt_len_res_obstacle")
	private Integer pagePktLenResObstacle;

   	@Column(name = "page_pkt_cnt_req_use")
	private Boolean pagePktCntReqUse;

   	@Column(name = "page_pkt_cnt_req_caution")
	private Integer pagePktCntReqCaution;

   	@Column(name = "page_pkt_cnt_req_warning")
	private Integer pagePktCntReqWarning;

   	@Column(name = "page_pkt_cnt_req_danger")
	private Integer pagePktCntReqDanger;

   	@Column(name = "page_pkt_cnt_req_obstacle")
	private Integer pagePktCntReqObstacle;

   	@Column(name = "page_pkt_cnt_res_use")
	private Boolean pagePktCntResUse;

   	@Column(name = "page_pkt_cnt_res_caution")
	private Integer pagePktCntResCaution;

   	@Column(name = "page_pkt_cnt_res_warning")
	private Integer pagePktCntResWarning;

   	@Column(name = "page_pkt_cnt_res_danger")
	private Integer pagePktCntResDanger;

   	@Column(name = "page_pkt_cnt_res_obstacle")
	private Integer pagePktCntResObstacle;

   	@Column(name = "page_session_cnt_use")
	private Boolean pageSessionCntUse;

   	@Column(name = "page_session_cnt_caution")
	private Integer pageSessionCntCaution;

   	@Column(name = "page_session_cnt_warning")
	private Integer pageSessionCntWarning;

   	@Column(name = "page_session_cnt_danger")
	private Integer pageSessionCntDanger;

   	@Column(name = "page_session_cnt_obstacle")
	private Integer pageSessionCntObstacle;

   	@Column(name = "conn_err_pkt_cnt_use")
	private Boolean connErrPktCntUse;

   	@Column(name = "conn_err_pkt_cnt_caution")
	private Integer connErrPktCntCaution;

   	@Column(name = "conn_err_pkt_cnt_warning")
	private Integer connErrPktCntWarning;

   	@Column(name = "conn_err_pkt_cnt_danger")
	private Integer connErrPktCntDanger;

   	@Column(name = "conn_err_pkt_cnt_obstacle")
	private Integer connErrPktCntObstacle;

   	@Column(name = "conn_err_session_cnt_use")
	private Boolean connErrSessionCntUse;

   	@Column(name = "conn_err_session_cnt_caution")
	private Integer connErrSessionCntCaution;

   	@Column(name = "conn_err_session_cnt_warning")
	private Integer connErrSessionCntWarning;

   	@Column(name = "conn_err_session_cnt_danger")
	private Integer connErrSessionCntDanger;

   	@Column(name = "conn_err_session_cnt_obstacle")
	private Integer connErrSessionCntObstacle;

   	@Column(name = "req_conn_err_session_len_use")
	private Boolean reqConnErrSessionLenUse;

   	@Column(name = "req_conn_err_session_len_caution")
	private Integer reqConnErrSessionLenCaution;

   	@Column(name = "req_conn_err_session_len_warning")
	private Integer reqConnErrSessionLenWarning;

   	@Column(name = "req_conn_err_session_len_danger")
	private Integer reqConnErrSessionLenDanger;

   	@Column(name = "req_conn_err_session_len_obstacle")
	private Integer reqConnErrSessionLenObstacle;

   	@Column(name = "res_conn_err_session_len_use")
	private Boolean resConnErrSessionLenUse;

   	@Column(name = "res_conn_err_session_len_caution")
	private Integer resConnErrSessionLenCaution;

   	@Column(name = "res_conn_err_session_len_warning")
	private Integer resConnErrSessionLenWarning;

   	@Column(name = "res_conn_err_session_len_danger")
	private Integer resConnErrSessionLenDanger;

   	@Column(name = "res_conn_err_session_len_obstacle")
	private Integer resConnErrSessionLenObstacle;

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

   	@Column(name = "res_code_2xx_cnt_use")
	private Boolean resCode2XxCntUse;

   	@Column(name = "res_code_2xx_cnt_caution")
	private Integer resCode2XxCntCaution;

   	@Column(name = "res_code_2xx_cnt_warning")
	private Integer resCode2XxCntWarning;

   	@Column(name = "res_code_2xx_cnt_danger")
	private Integer resCode2XxCntDanger;

   	@Column(name = "res_code_2xx_cnt_obstacle")
	private Integer resCode2XxCntObstacle;

   	@Column(name = "res_code_3xx_cnt_use")
	private Boolean resCode3XxCntUse;

   	@Column(name = "res_code_3xx_cnt_caution")
	private Integer resCode3XxCntCaution;

   	@Column(name = "res_code_3xx_cnt_warning")
	private Integer resCode3XxCntWarning;

   	@Column(name = "res_code_3xx_cnt_danger")
	private Integer resCode3XxCntDanger;

   	@Column(name = "res_code_3xx_cnt_obstacle")
	private Integer resCode3XxCntObstacle;

   	@Column(name = "res_code_401_cnt_use")
	private Boolean resCode401CntUse;

   	@Column(name = "res_code_401_cnt_caution")
	private Integer resCode401CntCaution;

   	@Column(name = "res_code_401_cnt_warning")
	private Integer resCode401CntWarning;

   	@Column(name = "res_code_401_cnt_danger")
	private Integer resCode401CntDanger;

   	@Column(name = "res_code_401_cnt_obstacle")
	private Integer resCode401CntObstacle;

   	@Column(name = "res_code_404_cnt_use")
	private Boolean resCode404CntUse;

   	@Column(name = "res_code_404_cnt_caution")
	private Integer resCode404CntCaution;

   	@Column(name = "res_code_404_cnt_warning")
	private Integer resCode404CntWarning;

   	@Column(name = "res_code_404_cnt_danger")
	private Integer resCode404CntDanger;

   	@Column(name = "res_code_404_cnt_obstacle")
	private Integer resCode404CntObstacle;

   	@Column(name = "res_code_4xx_cnt_use")
	private Boolean resCode4XxCntUse;

   	@Column(name = "res_code_4xx_cnt_caution")
	private Integer resCode4XxCntCaution;

   	@Column(name = "res_code_4xx_cnt_warning")
	private Integer resCode4XxCntWarning;

   	@Column(name = "res_code_4xx_cnt_danger")
	private Integer resCode4XxCntDanger;

   	@Column(name = "res_code_4xx_cnt_obstacle")
	private Integer resCode4XxCntObstacle;

   	@Column(name = "res_code_5xx_cnt_use")
	private Boolean resCode5XxCntUse;

   	@Column(name = "res_code_5xx_cnt_caution")
	private Integer resCode5XxCntCaution;

   	@Column(name = "res_code_5xx_cnt_warning")
	private Integer resCode5XxCntWarning;

   	@Column(name = "res_code_5xx_cnt_danger")
	private Integer resCode5XxCntDanger;

   	@Column(name = "res_code_5xx_cnt_obstacle")
	private Integer resCode5XxCntObstacle;

   	@Column(name = "res_code_oth_cnt_use")
	private Boolean resCodeOthCntUse;

   	@Column(name = "res_code_oth_cnt_caution")
	private Integer resCodeOthCntCaution;

   	@Column(name = "res_code_oth_cnt_warning")
	private Integer resCodeOthCntWarning;

   	@Column(name = "res_code_oth_cnt_danger")
	private Integer resCodeOthCntDanger;

   	@Column(name = "res_code_oth_cnt_obstacle")
	private Integer resCodeOthCntObstacle;

   	@Column(name = "page_tcp_len_req_use")
	private Boolean pageTcpLenReqUse;

   	@Column(name = "page_tcp_len_req_caution")
	private Integer pageTcpLenReqCaution;

   	@Column(name = "page_tcp_len_req_warning")
	private Integer pageTcpLenReqWarning;

   	@Column(name = "page_tcp_len_req_danger")
	private Integer pageTcpLenReqDanger;

   	@Column(name = "page_tcp_len_req_obstacle")
	private Integer pageTcpLenReqObstacle;

   	@Column(name = "page_tcp_len_res_use")
	private Boolean pageTcpLenResUse;

   	@Column(name = "page_tcp_len_res_caution")
	private Integer pageTcpLenResCaution;

   	@Column(name = "page_tcp_len_res_warning")
	private Integer pageTcpLenResWarning;

   	@Column(name = "page_tcp_len_res_danger")
	private Integer pageTcpLenResDanger;

   	@Column(name = "page_tcp_len_res_obstacle")
	private Integer pageTcpLenResObstacle;

   	@Column(name = "page_tcp_cnt_req_use")
	private Boolean pageTcpCntReqUse;

   	@Column(name = "page_tcp_cnt_req_caution")
	private Integer pageTcpCntReqCaution;

   	@Column(name = "page_tcp_cnt_req_warning")
	private Integer pageTcpCntReqWarning;

   	@Column(name = "page_tcp_cnt_req_danger")
	private Integer pageTcpCntReqDanger;

   	@Column(name = "page_tcp_cnt_req_obstacle")
	private Integer pageTcpCntReqObstacle;

   	@Column(name = "page_tcp_cnt_res_use")
	private Boolean pageTcpCntResUse;

   	@Column(name = "page_tcp_cnt_res_caution")
	private Integer pageTcpCntResCaution;

   	@Column(name = "page_tcp_cnt_res_warning")
	private Integer pageTcpCntResWarning;

   	@Column(name = "page_tcp_cnt_res_danger")
	private Integer pageTcpCntResDanger;

   	@Column(name = "page_tcp_cnt_res_obstacle")
	private Integer pageTcpCntResObstacle;

   	@Column(name = "stopped_transaction_cnt_use")
	private Boolean stoppedTransactionCntUse;

   	@Column(name = "stopped_transaction_cnt_caution")
	private Integer stoppedTransactionCntCaution;

   	@Column(name = "stopped_transaction_cnt_warning")
	private Integer stoppedTransactionCntWarning;

   	@Column(name = "stopped_transaction_cnt_danger")
	private Integer stoppedTransactionCntDanger;

   	@Column(name = "stopped_transaction_cnt_obstacle")
	private Integer stoppedTransactionCntObstacle;

   	@Column(name = "ts_page_rtt_ack_req_min_use")
	private Boolean tsPageRttAckReqMinUse;

   	@Column(name = "ts_page_rtt_ack_req_min_caution")
	private Integer tsPageRttAckReqMinCaution;

   	@Column(name = "ts_page_rtt_ack_req_min_warning")
	private Integer tsPageRttAckReqMinWarning;

   	@Column(name = "ts_page_rtt_ack_req_min_danger")
	private Integer tsPageRttAckReqMinDanger;

   	@Column(name = "ts_page_rtt_ack_req_min_obstacle")
	private Integer tsPageRttAckReqMinObstacle;

   	@Column(name = "ts_page_rtt_ack_res_min_use")
	private Boolean tsPageRttAckResMinUse;

   	@Column(name = "ts_page_rtt_ack_res_min_caution")
	private Integer tsPageRttAckResMinCaution;

   	@Column(name = "ts_page_rtt_ack_res_min_warning")
	private Integer tsPageRttAckResMinWarning;

   	@Column(name = "ts_page_rtt_ack_res_min_danger")
	private Integer tsPageRttAckResMinDanger;

   	@Column(name = "ts_page_rtt_ack_res_min_obstacle")
	private Integer tsPageRttAckResMinObstacle;

   	@Column(name = "ts_page_rtt_ack_req_max_use")
	private Boolean tsPageRttAckReqMaxUse;

   	@Column(name = "ts_page_rtt_ack_req_max_caution")
	private Integer tsPageRttAckReqMaxCaution;

   	@Column(name = "ts_page_rtt_ack_req_max_warning")
	private Integer tsPageRttAckReqMaxWarning;

   	@Column(name = "ts_page_rtt_ack_req_max_danger")
	private Integer tsPageRttAckReqMaxDanger;

   	@Column(name = "ts_page_rtt_ack_req_max_obstacle")
	private Integer tsPageRttAckReqMaxObstacle;

   	@Column(name = "ts_page_rtt_ack_res_max_use")
	private Boolean tsPageRttAckResMaxUse;

   	@Column(name = "ts_page_rtt_ack_res_max_caution")
	private Integer tsPageRttAckResMaxCaution;

   	@Column(name = "ts_page_rtt_ack_res_max_warning")
	private Integer tsPageRttAckResMaxWarning;

   	@Column(name = "ts_page_rtt_ack_res_max_danger")
	private Integer tsPageRttAckResMaxDanger;

   	@Column(name = "ts_page_rtt_ack_res_max_obstacle")
	private Integer tsPageRttAckResMaxObstacle;

   	@Column(name = "ts_page_rto_sum_req_use")
	private Boolean tsPageRtoSumReqUse;

   	@Column(name = "ts_page_rto_sum_req_caution")
	private Integer tsPageRtoSumReqCaution;

   	@Column(name = "ts_page_rto_sum_req_warning")
	private Integer tsPageRtoSumReqWarning;

   	@Column(name = "ts_page_rto_sum_req_danger")
	private Integer tsPageRtoSumReqDanger;

   	@Column(name = "ts_page_rto_sum_req_obstacle")
	private Integer tsPageRtoSumReqObstacle;

   	@Column(name = "ts_page_rto_sum_res_use")
	private Boolean tsPageRtoSumResUse;

   	@Column(name = "ts_page_rto_sum_res_caution")
	private Integer tsPageRtoSumResCaution;

   	@Column(name = "ts_page_rto_sum_res_warning")
	private Integer tsPageRtoSumResWarning;

   	@Column(name = "ts_page_rto_sum_res_danger")
	private Integer tsPageRtoSumResDanger;

   	@Column(name = "ts_page_rto_sum_res_obstacle")
	private Integer tsPageRtoSumResObstacle;

   	@Column(name = "ts_page_rto_cnt_req_use")
	private Boolean tsPageRtoCntReqUse;

   	@Column(name = "ts_page_rto_cnt_req_caution")
	private Integer tsPageRtoCntReqCaution;

   	@Column(name = "ts_page_rto_cnt_req_warning")
	private Integer tsPageRtoCntReqWarning;

   	@Column(name = "ts_page_rto_cnt_req_danger")
	private Integer tsPageRtoCntReqDanger;

   	@Column(name = "ts_page_rto_cnt_req_obstacle")
	private Integer tsPageRtoCntReqObstacle;

   	@Column(name = "ts_page_rto_cnt_res_use")
	private Boolean tsPageRtoCntResUse;

   	@Column(name = "ts_page_rto_cnt_res_caution")
	private Integer tsPageRtoCntResCaution;

   	@Column(name = "ts_page_rto_cnt_res_warning")
	private Integer tsPageRtoCntResWarning;

   	@Column(name = "ts_page_rto_cnt_res_danger")
	private Integer tsPageRtoCntResDanger;

   	@Column(name = "ts_page_rto_cnt_res_obstacle")
	private Integer tsPageRtoCntResObstacle;


}
