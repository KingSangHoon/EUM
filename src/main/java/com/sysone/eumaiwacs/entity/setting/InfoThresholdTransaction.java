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
@Table(name="tbl_info_threshold_transaction")
public class InfoThresholdTransaction {

    @Id
    @SequenceGenerator(name="tbl_info_threshold_transaction_seq", sequenceName="tbl_info_threshold_transaction_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_threshold_transaction_seq")
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

    @Column(name="ts_page_use")
    private Boolean tsPageUse;
    @Column(name="ts_page_level1")
    private Integer tsPageLevel1;
    @Column(name="ts_page_level2")
    private Integer tsPageLevel2;
    @Column(name="ts_page_level3")
    private Integer tsPageLevel3;
    @Column(name="ts_page_level4")
    private Integer tsPageLevel4;
    @Column(name="ts_page_level5")
    private Integer tsPageLevel5;

    @Column(name="ts_page_gap_use")
    private Boolean tsPageGapUse;
    @Column(name="ts_page_gap_level1")
    private Integer tsPageGapLevel1;
    @Column(name="ts_page_gap_level2")
    private Integer tsPageGapLevel2;
    @Column(name="ts_page_gap_level3")
    private Integer tsPageGapLevel3;
    @Column(name="ts_page_gap_level4")
    private Integer tsPageGapLevel4;
    @Column(name="ts_page_gap_level5")
    private Integer tsPageGapLevel5;

    @Column(name="ts_page_res_init_use")
    private Boolean tsPageResInitUse;
    @Column(name="ts_page_res_init_level1")
    private Integer tsPageResInitLevel1;
    @Column(name="ts_page_res_init_level2")
    private Integer tsPageResInitLevel2;
    @Column(name="ts_page_res_init_level3")
    private Integer tsPageResInitLevel3;
    @Column(name="ts_page_res_init_level4")
    private Integer tsPageResInitLevel4;
    @Column(name="ts_page_res_init_level5")
    private Integer tsPageResInitLevel5;

    @Column(name="ts_page_res_init_gap_use")
    private Boolean tsPageResInitGapUse;
    @Column(name="ts_page_res_init_gap_level1")
    private Integer tsPageResInitGapLevel1;
    @Column(name="ts_page_res_init_gap_level2")
    private Integer tsPageResInitGapLevel2;
    @Column(name="ts_page_res_init_gap_level3")
    private Integer tsPageResInitGapLevel3;
    @Column(name="ts_page_res_init_gap_level4")
    private Integer tsPageResInitGapLevel4;
    @Column(name="ts_page_res_init_gap_level5")
    private Integer tsPageResInitGapLevel5;

    @Column(name="ts_page_res_app_use")
    private Boolean tsPageResAppUse;
    @Column(name="ts_page_res_app_level1")
    private Integer tsPageResAppLevel1;
    @Column(name="ts_page_res_app_level2")
    private Integer tsPageResAppLevel2;
    @Column(name="ts_page_res_app_level3")
    private Integer tsPageResAppLevel3;
    @Column(name="ts_page_res_app_level4")
    private Integer tsPageResAppLevel4;
    @Column(name="ts_page_res_app_level5")
    private Integer tsPageResAppLevel5;

    @Column(name="ts_page_res_app_gap_use")
    private Boolean tsPageResAppGapUse;
    @Column(name="ts_page_res_app_gap_level1")
    private Integer tsPageResAppGapLevel1;
    @Column(name="ts_page_res_app_gap_level2")
    private Integer tsPageResAppGapLevel2;
    @Column(name="ts_page_res_app_gap_level3")
    private Integer tsPageResAppGapLevel3;
    @Column(name="ts_page_res_app_gap_level4")
    private Integer tsPageResAppGapLevel4;
    @Column(name="ts_page_res_app_gap_level5")
    private Integer tsPageResAppGapLevel5;

    @Column(name="ts_page_res_use")
    private Boolean tsPageResUse;
    @Column(name="ts_page_res_level1")
    private Integer tsPageResLevel1;
    @Column(name="ts_page_res_level2")
    private Integer tsPageResLevel2;
    @Column(name="ts_page_res_level3")
    private Integer tsPageResLevel3;
    @Column(name="ts_page_res_level4")
    private Integer tsPageResLevel4;
    @Column(name="ts_page_res_level5")
    private Integer tsPageResLevel5;

    @Column(name="ts_page_res_gap_use")
    private Boolean tsPageResGapUse;
    @Column(name="ts_page_res_gap_level1")
    private Integer tsPageResGapLevel1;
    @Column(name="ts_page_res_gap_level2")
    private Integer tsPageResGapLevel2;
    @Column(name="ts_page_res_gap_level3")
    private Integer tsPageResGapLevel3;
    @Column(name="ts_page_res_gap_level4")
    private Integer tsPageResGapLevel4;
    @Column(name="ts_page_res_gap_level5")
    private Integer tsPageResGapLevel5;

    @Column(name="ts_page_transfer_req_use")
    private Boolean tsPageTransferReqUse;
    @Column(name="ts_page_transfer_req_level1")
    private Integer tsPageTransferReqLevel1;
    @Column(name="ts_page_transfer_req_level2")
    private Integer tsPageTransferReqLevel2;
    @Column(name="ts_page_transfer_req_level3")
    private Integer tsPageTransferReqLevel3;
    @Column(name="ts_page_transfer_req_level4")
    private Integer tsPageTransferReqLevel4;
    @Column(name="ts_page_transfer_req_level5")
    private Integer tsPageTransferReqLevel5;

    @Column(name="ts_page_transfer_req_gap_use")
    private Boolean tsPageTransferReqGapUse;
    @Column(name="ts_page_transfer_req_gap_level1")
    private Integer tsPageTransferReqGapLevel1;
    @Column(name="ts_page_transfer_req_gap_level2")
    private Integer tsPageTransferReqGapLevel2;
    @Column(name="ts_page_transfer_req_gap_level3")
    private Integer tsPageTransferReqGapLevel3;
    @Column(name="ts_page_transfer_req_gap_level4")
    private Integer tsPageTransferReqGapLevel4;
    @Column(name="ts_page_transfer_req_gap_level5")
    private Integer tsPageTransferReqGapLevel5;

    @Column(name="ts_page_transfer_res_use")
    private Boolean tsPageTransferResUse;
    @Column(name="ts_page_transfer_res_level1")
    private Integer tsPageTransferResLevel1;
    @Column(name="ts_page_transfer_res_level2")
    private Integer tsPageTransferResLevel2;
    @Column(name="ts_page_transfer_res_level3")
    private Integer tsPageTransferResLevel3;
    @Column(name="ts_page_transfer_res_level4")
    private Integer tsPageTransferResLevel4;
    @Column(name="ts_page_transfer_res_level5")
    private Integer tsPageTransferResLevel5;

    @Column(name="ts_page_transfer_res_gap_use")
    private Boolean tsPageTransferResGapUse;
    @Column(name="ts_page_transfer_res_gap_level1")
    private Integer tsPageTransferResGapLevel1;
    @Column(name="ts_page_transfer_res_gap_level2")
    private Integer tsPageTransferResGapLevel2;
    @Column(name="ts_page_transfer_res_gap_level3")
    private Integer tsPageTransferResGapLevel3;
    @Column(name="ts_page_transfer_res_gap_level4")
    private Integer tsPageTransferResGapLevel4;
    @Column(name="ts_page_transfer_res_gap_level5")
    private Integer tsPageTransferResGapLevel5;

    @Column(name="ts_page_rtt_conn_sum_req_use")
    private Boolean tsPageRttConnSumReqUse;
    @Column(name="ts_page_rtt_conn_sum_req_level1")
    private Integer tsPageRttConnSumReqLevel1;
    @Column(name="ts_page_rtt_conn_sum_req_level2")
    private Integer tsPageRttConnSumReqLevel2;
    @Column(name="ts_page_rtt_conn_sum_req_level3")
    private Integer tsPageRttConnSumReqLevel3;
    @Column(name="ts_page_rtt_conn_sum_req_level4")
    private Integer tsPageRttConnSumReqLevel4;
    @Column(name="ts_page_rtt_conn_sum_req_level5")
    private Integer tsPageRttConnSumReqLevel5;

    @Column(name="ts_page_rtt_conn_sum_res_use")
    private Boolean tsPageRttConnSumResUse;
    @Column(name="ts_page_rtt_conn_sum_res_level1")
    private Integer tsPageRttConnSumResLevel1;
    @Column(name="ts_page_rtt_conn_sum_res_level2")
    private Integer tsPageRttConnSumResLevel2;
    @Column(name="ts_page_rtt_conn_sum_res_level3")
    private Integer tsPageRttConnSumResLevel3;
    @Column(name="ts_page_rtt_conn_sum_res_level4")
    private Integer tsPageRttConnSumResLevel4;
    @Column(name="ts_page_rtt_conn_sum_res_level5")
    private Integer tsPageRttConnSumResLevel5;

    @Column(name="ts_page_rtt_ack_sum_req_use")
    private Boolean tsPageRttAckSumReqUse;
    @Column(name="ts_page_rtt_ack_sum_req_level1")
    private Integer tsPageRttAckSumReqLevel1;
    @Column(name="ts_page_rtt_ack_sum_req_level2")
    private Integer tsPageRttAckSumReqLevel2;
    @Column(name="ts_page_rtt_ack_sum_req_level3")
    private Integer tsPageRttAckSumReqLevel3;
    @Column(name="ts_page_rtt_ack_sum_req_level4")
    private Integer tsPageRttAckSumReqLevel4;
    @Column(name="ts_page_rtt_ack_sum_req_level5")
    private Integer tsPageRttAckSumReqLevel5;

    @Column(name="ts_page_rtt_ack_sum_res_use")
    private Boolean tsPageRttAckSumResUse;
    @Column(name="ts_page_rtt_ack_sum_res_level1")
    private Integer tsPageRttAckSumResLevel1;
    @Column(name="ts_page_rtt_ack_sum_res_level2")
    private Integer tsPageRttAckSumResLevel2;
    @Column(name="ts_page_rtt_ack_sum_res_level3")
    private Integer tsPageRttAckSumResLevel3;
    @Column(name="ts_page_rtt_ack_sum_res_level4")
    private Integer tsPageRttAckSumResLevel4;
    @Column(name="ts_page_rtt_ack_sum_res_level5")
    private Integer tsPageRttAckSumResLevel5;

    @Column(name="ts_page_req_making_sum_use")
    private Boolean tsPageReqMakingSumUse;
    @Column(name="ts_page_req_making_sum_level1")
    private Integer tsPageReqMakingSumLevel1;
    @Column(name="ts_page_req_making_sum_level2")
    private Integer tsPageReqMakingSumLevel2;
    @Column(name="ts_page_req_making_sum_level3")
    private Integer tsPageReqMakingSumLevel3;
    @Column(name="ts_page_req_making_sum_level4")
    private Integer tsPageReqMakingSumLevel4;
    @Column(name="ts_page_req_making_sum_level5")
    private Integer tsPageReqMakingSumLevel5;

    @Column(name="page_rtt_conn_cnt_req_use")
    private Boolean pageRttConnCntReqUse;
    @Column(name="page_rtt_conn_cnt_req_level1")
    private Integer pageRttConnCntReqLevel1;
    @Column(name="page_rtt_conn_cnt_req_level2")
    private Integer pageRttConnCntReqLevel2;
    @Column(name="page_rtt_conn_cnt_req_level3")
    private Integer pageRttConnCntReqLevel3;
    @Column(name="page_rtt_conn_cnt_req_level4")
    private Integer pageRttConnCntReqLevel4;
    @Column(name="page_rtt_conn_cnt_req_level5")
    private Integer pageRttConnCntReqLevel5;

    @Column(name="page_rtt_conn_cnt_res_use")
    private Boolean pageRttConnCntResUse;
    @Column(name="page_rtt_conn_cnt_res_level1")
    private Integer pageRttConnCntResLevel1;
    @Column(name="page_rtt_conn_cnt_res_level2")
    private Integer pageRttConnCntResLevel2;
    @Column(name="page_rtt_conn_cnt_res_level3")
    private Integer pageRttConnCntResLevel3;
    @Column(name="page_rtt_conn_cnt_res_level4")
    private Integer pageRttConnCntResLevel4;
    @Column(name="page_rtt_conn_cnt_res_level5")
    private Integer pageRttConnCntResLevel5;

    @Column(name="page_rtt_ack_cnt_req_use")
    private Boolean pageRttAckCntReqUse;
    @Column(name="page_rtt_ack_cnt_req_level1")
    private Integer pageRttAckCntReqLevel1;
    @Column(name="page_rtt_ack_cnt_req_level2")
    private Integer pageRttAckCntReqLevel2;
    @Column(name="page_rtt_ack_cnt_req_level3")
    private Integer pageRttAckCntReqLevel3;
    @Column(name="page_rtt_ack_cnt_req_level4")
    private Integer pageRttAckCntReqLevel4;
    @Column(name="page_rtt_ack_cnt_req_level5")
    private Integer pageRttAckCntReqLevel5;

    @Column(name="page_rtt_ack_cnt_res_use")
    private Boolean pageRttAckCntResUse;
    @Column(name="page_rtt_ack_cnt_res_level1")
    private Integer pageRttAckCntResLevel1;
    @Column(name="page_rtt_ack_cnt_res_level2")
    private Integer pageRttAckCntResLevel2;
    @Column(name="page_rtt_ack_cnt_res_level3")
    private Integer pageRttAckCntResLevel3;
    @Column(name="page_rtt_ack_cnt_res_level4")
    private Integer pageRttAckCntResLevel4;
    @Column(name="page_rtt_ack_cnt_res_level5")
    private Integer pageRttAckCntResLevel5;

    @Column(name="page_req_making_cnt_use")
    private Boolean pageReqMakingCntUse;
    @Column(name="page_req_making_cnt_level1")
    private Integer pageReqMakingCntLevel1;
    @Column(name="page_req_making_cnt_level2")
    private Integer pageReqMakingCntLevel2;
    @Column(name="page_req_making_cnt_level3")
    private Integer pageReqMakingCntLevel3;
    @Column(name="page_req_making_cnt_level4")
    private Integer pageReqMakingCntLevel4;
    @Column(name="page_req_making_cnt_level5")
    private Integer pageReqMakingCntLevel5;

    @Column(name="page_http_len_req_use")
    private Boolean pageHttpLenReqUse;
    @Column(name="page_http_len_req_level1")
    private Integer pageHttpLenReqLevel1;
    @Column(name="page_http_len_req_level2")
    private Integer pageHttpLenReqLevel2;
    @Column(name="page_http_len_req_level3")
    private Integer pageHttpLenReqLevel3;
    @Column(name="page_http_len_req_level4")
    private Integer pageHttpLenReqLevel4;
    @Column(name="page_http_len_req_level5")
    private Integer pageHttpLenReqLevel5;

    @Column(name="page_http_len_res_use")
    private Boolean pageHttpLenResUse;
    @Column(name="page_http_len_res_level1")
    private Integer pageHttpLenResLevel1;
    @Column(name="page_http_len_res_level2")
    private Integer pageHttpLenResLevel2;
    @Column(name="page_http_len_res_level3")
    private Integer pageHttpLenResLevel3;
    @Column(name="page_http_len_res_level4")
    private Integer pageHttpLenResLevel4;
    @Column(name="page_http_len_res_level5")
    private Integer pageHttpLenResLevel5;

    @Column(name="page_http_cnt_req_use")
    private Boolean pageHttpCntReqUse;
    @Column(name="page_http_cnt_req_level1")
    private Integer pageHttpCntReqLevel1;
    @Column(name="page_http_cnt_req_level2")
    private Integer pageHttpCntReqLevel2;
    @Column(name="page_http_cnt_req_level3")
    private Integer pageHttpCntReqLevel3;
    @Column(name="page_http_cnt_req_level4")
    private Integer pageHttpCntReqLevel4;
    @Column(name="page_http_cnt_req_level5")
    private Integer pageHttpCntReqLevel5;

    @Column(name="page_http_cnt_res_use")
    private Boolean pageHttpCntResUse;
    @Column(name="page_http_cnt_res_level1")
    private Integer pageHttpCntResLevel1;
    @Column(name="page_http_cnt_res_level2")
    private Integer pageHttpCntResLevel2;
    @Column(name="page_http_cnt_res_level3")
    private Integer pageHttpCntResLevel3;
    @Column(name="page_http_cnt_res_level4")
    private Integer pageHttpCntResLevel4;
    @Column(name="page_http_cnt_res_level5")
    private Integer pageHttpCntResLevel5;

    @Column(name="page_pkt_len_req_use")
    private Boolean pagePktLenReqUse;
    @Column(name="page_pkt_len_req_level1")
    private Integer pagePktLenReqLevel1;
    @Column(name="page_pkt_len_req_level2")
    private Integer pagePktLenReqLevel2;
    @Column(name="page_pkt_len_req_level3")
    private Integer pagePktLenReqLevel3;
    @Column(name="page_pkt_len_req_level4")
    private Integer pagePktLenReqLevel4;
    @Column(name="page_pkt_len_req_level5")
    private Integer pagePktLenReqLevel5;

    @Column(name="page_pkt_len_res_use")
    private Boolean pagePktLenResUse;
    @Column(name="page_pkt_len_res_level1")
    private Integer pagePktLenResLevel1;
    @Column(name="page_pkt_len_res_level2")
    private Integer pagePktLenResLevel2;
    @Column(name="page_pkt_len_res_level3")
    private Integer pagePktLenResLevel3;
    @Column(name="page_pkt_len_res_level4")
    private Integer pagePktLenResLevel4;
    @Column(name="page_pkt_len_res_level5")
    private Integer pagePktLenResLevel5;

    @Column(name="page_pkt_cnt_req_use")
    private Boolean pagePktCntReqUse;
    @Column(name="page_pkt_cnt_req_level1")
    private Integer pagePktCntReqLevel1;
    @Column(name="page_pkt_cnt_req_level2")
    private Integer pagePktCntReqLevel2;
    @Column(name="page_pkt_cnt_req_level3")
    private Integer pagePktCntReqLevel3;
    @Column(name="page_pkt_cnt_req_level4")
    private Integer pagePktCntReqLevel4;
    @Column(name="page_pkt_cnt_req_level5")
    private Integer pagePktCntReqLevel5;

    @Column(name="page_pkt_cnt_res_use")
    private Boolean pagePktCntResUse;
    @Column(name="page_pkt_cnt_res_level1")
    private Integer pagePktCntResLevel1;
    @Column(name="page_pkt_cnt_res_level2")
    private Integer pagePktCntResLevel2;
    @Column(name="page_pkt_cnt_res_level3")
    private Integer pagePktCntResLevel3;
    @Column(name="page_pkt_cnt_res_level4")
    private Integer pagePktCntResLevel4;
    @Column(name="page_pkt_cnt_res_level5")
    private Integer pagePktCntResLevel5;

    @Column(name="page_session_cnt_use")
    private Boolean pageSessionCntUse;
    @Column(name="page_session_cnt_level1")
    private Integer pageSessionCntLevel1;
    @Column(name="page_session_cnt_level2")
    private Integer pageSessionCntLevel2;
    @Column(name="page_session_cnt_level3")
    private Integer pageSessionCntLevel3;
    @Column(name="page_session_cnt_level4")
    private Integer pageSessionCntLevel4;
    @Column(name="page_session_cnt_level5")
    private Integer pageSessionCntLevel5;

    @Column(name="conn_err_pkt_cnt_use")
    private Boolean connErrPktCntUse;
    @Column(name="conn_err_pkt_cnt_level1")
    private Integer connErrPktCntLevel1;
    @Column(name="conn_err_pkt_cnt_level2")
    private Integer connErrPktCntLevel2;
    @Column(name="conn_err_pkt_cnt_level3")
    private Integer connErrPktCntLevel3;
    @Column(name="conn_err_pkt_cnt_level4")
    private Integer connErrPktCntLevel4;
    @Column(name="conn_err_pkt_cnt_level5")
    private Integer connErrPktCntLevel5;

    @Column(name="conn_err_session_cnt_use")
    private Boolean connErrSessionCntUse;
    @Column(name="conn_err_session_cnt_level1")
    private Integer connErrSessionCntLevel1;
    @Column(name="conn_err_session_cnt_level2")
    private Integer connErrSessionCntLevel2;
    @Column(name="conn_err_session_cnt_level3")
    private Integer connErrSessionCntLevel3;
    @Column(name="conn_err_session_cnt_level4")
    private Integer connErrSessionCntLevel4;
    @Column(name="conn_err_session_cnt_level5")
    private Integer connErrSessionCntLevel5;

    @Column(name="req_conn_err_session_len_use")
    private Boolean reqConnErrSessionLenUse;
    @Column(name="req_conn_err_session_len_level1")
    private Integer reqConnErrSessionLenLevel1;
    @Column(name="req_conn_err_session_len_level2")
    private Integer reqConnErrSessionLenLevel2;
    @Column(name="req_conn_err_session_len_level3")
    private Integer reqConnErrSessionLenLevel3;
    @Column(name="req_conn_err_session_len_level4")
    private Integer reqConnErrSessionLenLevel4;
    @Column(name="req_conn_err_session_len_level5")
    private Integer reqConnErrSessionLenLevel5;

    @Column(name="res_conn_err_session_len_use")
    private Boolean resConnErrSessionLenUse;
    @Column(name="res_conn_err_session_len_level1")
    private Integer resConnErrSessionLenLevel1;
    @Column(name="res_conn_err_session_len_level2")
    private Integer resConnErrSessionLenLevel2;
    @Column(name="res_conn_err_session_len_level3")
    private Integer resConnErrSessionLenLevel3;
    @Column(name="res_conn_err_session_len_level4")
    private Integer resConnErrSessionLenLevel4;
    @Column(name="res_conn_err_session_len_level5")
    private Integer resConnErrSessionLenLevel5;

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

    @Column(name="res_code_2xx_cnt_use")
    private Boolean resCode2xxCntUse;
    @Column(name="res_code_2xx_cnt_level1")
    private Integer resCode2xxCntLevel1;
    @Column(name="res_code_2xx_cnt_level2")
    private Integer resCode2xxCntLevel2;
    @Column(name="res_code_2xx_cnt_level3")
    private Integer resCode2xxCntLevel3;
    @Column(name="res_code_2xx_cnt_level4")
    private Integer resCode2xxCntLevel4;
    @Column(name="res_code_2xx_cnt_level5")
    private Integer resCode2xxCntLevel5;

    @Column(name="res_code_3xx_cnt_use")
    private Boolean resCode3xxCntUse;
    @Column(name="res_code_3xx_cnt_level1")
    private Integer resCode3xxCntLevel1;
    @Column(name="res_code_3xx_cnt_level2")
    private Integer resCode3xxCntLevel2;
    @Column(name="res_code_3xx_cnt_level3")
    private Integer resCode3xxCntLevel3;
    @Column(name="res_code_3xx_cnt_level4")
    private Integer resCode3xxCntLevel4;
    @Column(name="res_code_3xx_cnt_level5")
    private Integer resCode3xxCntLevel5;

    @Column(name="res_code_401_cnt_use")
    private Boolean resCode401CntUse;
    @Column(name="res_code_401_cnt_level1")
    private Integer resCode401CntLevel1;
    @Column(name="res_code_401_cnt_level2")
    private Integer resCode401CntLevel2;
    @Column(name="res_code_401_cnt_level3")
    private Integer resCode401CntLevel3;
    @Column(name="res_code_401_cnt_level4")
    private Integer resCode401CntLevel4;
    @Column(name="res_code_401_cnt_level5")
    private Integer resCode401CntLevel5;

    @Column(name="res_code_404_cnt_use")
    private Boolean resCode404CntUse;
    @Column(name="res_code_404_cnt_level1")
    private Integer resCode404CntLevel1;
    @Column(name="res_code_404_cnt_level2")
    private Integer resCode404CntLevel2;
    @Column(name="res_code_404_cnt_level3")
    private Integer resCode404CntLevel3;
    @Column(name="res_code_404_cnt_level4")
    private Integer resCode404CntLevel4;
    @Column(name="res_code_404_cnt_level5")
    private Integer resCode404CntLevel5;

    @Column(name="res_code_4xx_cnt_use")
    private Boolean resCode4xxCntUse;
    @Column(name="res_code_4xx_cnt_level1")
    private Integer resCode4xxCntLevel1;
    @Column(name="res_code_4xx_cnt_level2")
    private Integer resCode4xxCntLevel2;
    @Column(name="res_code_4xx_cnt_level3")
    private Integer resCode4xxCntLevel3;
    @Column(name="res_code_4xx_cnt_level4")
    private Integer resCode4xxCntLevel4;
    @Column(name="res_code_4xx_cnt_level5")
    private Integer resCode4xxCntLevel5;

    @Column(name="res_code_5xx_cnt_use")
    private Boolean resCode5xxCntUse;
    @Column(name="res_code_5xx_cnt_level1")
    private Integer resCode5xxCntLevel1;
    @Column(name="res_code_5xx_cnt_level2")
    private Integer resCode5xxCntLevel2;
    @Column(name="res_code_5xx_cnt_level3")
    private Integer resCode5xxCntLevel3;
    @Column(name="res_code_5xx_cnt_level4")
    private Integer resCode5xxCntLevel4;
    @Column(name="res_code_5xx_cnt_level5")
    private Integer resCode5xxCntLevel5;

    @Column(name="res_code_oth_cnt_use")
    private Boolean resCodeothCntUse;
    @Column(name="res_code_oth_cnt_level1")
    private Integer resCodeOthCntLevel1;
    @Column(name="res_code_oth_cnt_level2")
    private Integer resCodeOthCntLevel2;
    @Column(name="res_code_oth_cnt_level3")
    private Integer resCodeOthCntLevel3;
    @Column(name="res_code_oth_cnt_level4")
    private Integer resCodeOthCntLevel4;
    @Column(name="res_code_oth_cnt_level5")
    private Integer resCodeOthCntLevel5;

    @Column(name="page_tcp_len_req_use")
    private Boolean pageTcpLenReqUse;
    @Column(name="page_tcp_len_req_level1")
    private Integer pageTcpLenReqLevel1;
    @Column(name="page_tcp_len_req_level2")
    private Integer pageTcpLenReqLevel2;
    @Column(name="page_tcp_len_req_level3")
    private Integer pageTcpLenReqLevel3;
    @Column(name="page_tcp_len_req_level4")
    private Integer pageTcpLenReqLevel4;
    @Column(name="page_tcp_len_req_level5")
    private Integer pageTcpLenReqLevel5;

    @Column(name="page_tcp_len_res_use")
    private Boolean pageTcpLenResUse;
    @Column(name="page_tcp_len_res_level1")
    private Integer pageTcpLenResLevel1;
    @Column(name="page_tcp_len_res_level2")
    private Integer pageTcpLenResLevel2;
    @Column(name="page_tcp_len_res_level3")
    private Integer pageTcpLenResLevel3;
    @Column(name="page_tcp_len_res_level4")
    private Integer pageTcpLenResLevel4;
    @Column(name="page_tcp_len_res_level5")
    private Integer pageTcpLenResLevel5;

    @Column(name="page_tcp_cnt_req_use")
    private Boolean pageTcpCntReqUse;
    @Column(name="page_tcp_cnt_req_level1")
    private Integer pageTcpCntReqLevel1;
    @Column(name="page_tcp_cnt_req_level2")
    private Integer pageTcpCntReqLevel2;
    @Column(name="page_tcp_cnt_req_level3")
    private Integer pageTcpCntReqLevel3;
    @Column(name="page_tcp_cnt_req_level4")
    private Integer pageTcpCntReqLevel4;
    @Column(name="page_tcp_cnt_req_level5")
    private Integer pageTcpCntReqLevel5;

    @Column(name="page_tcp_cnt_res_use")
    private Boolean pageTcpCntResUse;
    @Column(name="page_tcp_cnt_res_level1")
    private Integer pageTcpCntResLevel1;
    @Column(name="page_tcp_cnt_res_level2")
    private Integer pageTcpCntResLevel2;
    @Column(name="page_tcp_cnt_res_level3")
    private Integer pageTcpCntResLevel3;
    @Column(name="page_tcp_cnt_res_level4")
    private Integer pageTcpCntResLevel4;
    @Column(name="page_tcp_cnt_res_level5")
    private Integer pageTcpCntResLevel5;

    @Column(name="stopped_transaction_cnt_use")
    private Boolean stoppedTransactionCntUse;
    @Column(name="stopped_transaction_cnt_level1")
    private Integer stoppedTransactionCntLevel1;
    @Column(name="stopped_transaction_cnt_level2")
    private Integer stoppedTransactionCntLevel2;
    @Column(name="stopped_transaction_cnt_level3")
    private Integer stoppedTransactionCntLevel3;
    @Column(name="stopped_transaction_cnt_level4")
    private Integer stoppedTransactionCntLevel4;
    @Column(name="stopped_transaction_cnt_level5")
    private Integer stoppedTransactionCntLevel5;

    @Column(name="ts_page_rtt_ack_req_min_use")
    private Boolean tsPageRttAckReqMinUse;
    @Column(name="ts_page_rtt_ack_req_min_level1")
    private Integer tsPageRttAckReqMinLevel1;
    @Column(name="ts_page_rtt_ack_req_min_level2")
    private Integer tsPageRttAckReqMinLevel2;
    @Column(name="ts_page_rtt_ack_req_min_level3")
    private Integer tsPageRttAckReqMinLevel3;
    @Column(name="ts_page_rtt_ack_req_min_level4")
    private Integer tsPageRttAckReqMinLevel4;
    @Column(name="ts_page_rtt_ack_req_min_level5")
    private Integer tsPageRttAckReqMinLevel5;

    @Column(name="ts_page_rtt_ack_res_min_use")
    private Boolean tsPageRttAckResMinUse;
    @Column(name="ts_page_rtt_ack_res_min_level1")
    private Integer tsPageRttAckResMinLevel1;
    @Column(name="ts_page_rtt_ack_res_min_level2")
    private Integer tsPageRttAckResMinLevel2;
    @Column(name="ts_page_rtt_ack_res_min_level3")
    private Integer tsPageRttAckResMinLevel3;
    @Column(name="ts_page_rtt_ack_res_min_level4")
    private Integer tsPageRttAckResMinLevel4;
    @Column(name="ts_page_rtt_ack_res_min_level5")
    private Integer tsPageRttAckResMinLevel5;

    @Column(name="ts_page_rtt_ack_req_max_use")
    private Boolean tsPageRttAckReqMaxUse;
    @Column(name="ts_page_rtt_ack_req_max_level1")
    private Integer tsPageRttAckReqMaxLevel1;
    @Column(name="ts_page_rtt_ack_req_max_level2")
    private Integer tsPageRttAckReqMaxLevel2;
    @Column(name="ts_page_rtt_ack_req_max_level3")
    private Integer tsPageRttAckReqMaxLevel3;
    @Column(name="ts_page_rtt_ack_req_max_level4")
    private Integer tsPageRttAckReqMaxLevel4;
    @Column(name="ts_page_rtt_ack_req_max_level5")
    private Integer tsPageRttAckReqMaxLevel5;

    @Column(name="ts_page_rtt_ack_res_max_use")
    private Boolean tsPageRttAckResMaxUse;
    @Column(name="ts_page_rtt_ack_res_max_level1")
    private Integer tsPageRttAckResMaxLevel1;
    @Column(name="ts_page_rtt_ack_res_max_level2")
    private Integer tsPageRttAckResMaxLevel2;
    @Column(name="ts_page_rtt_ack_res_max_level3")
    private Integer tsPageRttAckResMaxLevel3;
    @Column(name="ts_page_rtt_ack_res_max_level4")
    private Integer tsPageRttAckResMaxLevel4;
    @Column(name="ts_page_rtt_ack_res_max_level5")
    private Integer tsPageRttAckResMaxLevel5;

    @Column(name="ts_page_rto_sum_req_use")
    private Boolean tsPageRtoSumReqUse;
    @Column(name="ts_page_rto_sum_req_level1")
    private Integer tsPageRtoSumReqLevel1;
    @Column(name="ts_page_rto_sum_req_level2")
    private Integer tsPageRtoSumReqLevel2;
    @Column(name="ts_page_rto_sum_req_level3")
    private Integer tsPageRtoSumReqLevel3;
    @Column(name="ts_page_rto_sum_req_level4")
    private Integer tsPageRtoSumReqLevel4;
    @Column(name="ts_page_rto_sum_req_level5")
    private Integer tsPageRtoSumReqLevel5;

    @Column(name="ts_page_rto_sum_res_use")
    private Boolean tsPageRtoSumResUse;
    @Column(name="ts_page_rto_sum_res_level1")
    private Integer tsPageRtoSumResLevel1;
    @Column(name="ts_page_rto_sum_res_level2")
    private Integer tsPageRtoSumResLevel2;
    @Column(name="ts_page_rto_sum_res_level3")
    private Integer tsPageRtoSumResLevel3;
    @Column(name="ts_page_rto_sum_res_level4")
    private Integer tsPageRtoSumResLevel4;
    @Column(name="ts_page_rto_sum_res_level5")
    private Integer tsPageRtoSumResLevel5;

    @Column(name="ts_page_rto_cnt_req_use")
    private Boolean tsPageRtoCntReqUse;
    @Column(name="ts_page_rto_cnt_req_level1")
    private Integer tsPageRtoCntReqLevel1;
    @Column(name="ts_page_rto_cnt_req_level2")
    private Integer tsPageRtoCntReqLevel2;
    @Column(name="ts_page_rto_cnt_req_level3")
    private Integer tsPageRtoCntReqLevel3;
    @Column(name="ts_page_rto_cnt_req_level4")
    private Integer tsPageRtoCntReqLevel4;
    @Column(name="ts_page_rto_cnt_req_level5")
    private Integer tsPageRtoCntReqLevel5;

    @Column(name="ts_page_rto_cnt_res_use")
    private Boolean tsPageRtoCntResUse;
    @Column(name="ts_page_rto_cnt_res_level1")
    private Integer tsPageRtoCntResLevel1;
    @Column(name="ts_page_rto_cnt_res_level2")
    private Integer tsPageRtoCntResLevel2;
    @Column(name="ts_page_rto_cnt_res_level3")
    private Integer tsPageRtoCntResLevel3;
    @Column(name="ts_page_rto_cnt_res_level4")
    private Integer tsPageRtoCntResLevel4;
    @Column(name="ts_page_rto_cnt_res_level5")
    private Integer tsPageRtoCntResLevel5;
}
