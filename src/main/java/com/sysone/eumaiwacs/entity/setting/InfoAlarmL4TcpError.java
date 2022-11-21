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
@Table (name = "tbl_info_alarm_l4_tcp_error")
public class InfoAlarmL4TcpError {

	@Id
	@SequenceGenerator(name="tbl_info_alarm_l4_tcp_error_seq", sequenceName="tbl_info_alarm_l4_tcp_error_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_alarm_l4_tcp_error_seq")
   	@Column(name = "l4_tcp_error_id")
	private Integer l4TcpErrorId;

   	@Column(name = "deleted")
	private Boolean deleted;

   	@Column(name = "retransmission_cnt_req_tot_use")
	private Boolean retransmissionCntReqTotUse;

   	@Column(name = "retransmission_cnt_req_tot_caution")
	private Integer retransmissionCntReqTotCaution;

   	@Column(name = "retransmission_cnt_req_tot_warning")
	private Integer retransmissionCntReqTotWarning;

   	@Column(name = "retransmission_cnt_req_tot_danger")
	private Integer retransmissionCntReqTotDanger;

   	@Column(name = "retransmission_cnt_req_tot_obstacle")
	private Integer retransmissionCntReqTotObstacle;

   	@Column(name = "retransmission_cnt_res_tot_use")
	private Boolean retransmissionCntResTotUse;

   	@Column(name = "retransmission_cnt_res_tot_caution")
	private Integer retransmissionCntResTotCaution;

   	@Column(name = "retransmission_cnt_res_tot_warning")
	private Integer retransmissionCntResTotWarning;

   	@Column(name = "retransmission_cnt_res_tot_danger")
	private Integer retransmissionCntResTotDanger;

   	@Column(name = "retransmission_cnt_res_tot_obstacle")
	private Integer retransmissionCntResTotObstacle;

   	@Column(name = "retransmission_len_req_tot_use")
	private Boolean retransmissionLenReqTotUse;

   	@Column(name = "retransmission_len_req_tot_caution")
	private Integer retransmissionLenReqTotCaution;

   	@Column(name = "retransmission_len_req_tot_warning")
	private Integer retransmissionLenReqTotWarning;

   	@Column(name = "retransmission_len_req_tot_danger")
	private Integer retransmissionLenReqTotDanger;

   	@Column(name = "retransmission_len_req_tot_obstacle")
	private Integer retransmissionLenReqTotObstacle;

   	@Column(name = "retransmission_len_res_tot_use")
	private Boolean retransmissionLenResTotUse;

   	@Column(name = "retransmission_len_res_tot_caution")
	private Integer retransmissionLenResTotCaution;

   	@Column(name = "retransmission_len_res_tot_warning")
	private Integer retransmissionLenResTotWarning;

   	@Column(name = "retransmission_len_res_tot_danger")
	private Integer retransmissionLenResTotDanger;

   	@Column(name = "retransmission_len_res_tot_obstacle")
	private Integer retransmissionLenResTotObstacle;

   	@Column(name = "fastretransmission_cnt_req_tot_use")
	private Boolean fastretransmissionCntReqTotUse;

   	@Column(name = "fastretransmission_cnt_req_tot_caution")
	private Integer fastretransmissionCntReqTotCaution;

   	@Column(name = "fastretransmission_cnt_req_tot_warning")
	private Integer fastretransmissionCntReqTotWarning;

   	@Column(name = "fastretransmission_cnt_req_tot_danger")
	private Integer fastretransmissionCntReqTotDanger;

   	@Column(name = "fastretransmission_cnt_req_tot_obstacle")
	private Integer fastretransmissionCntReqTotObstacle;

   	@Column(name = "fastretransmission_cnt_res_tot_use")
	private Boolean fastretransmissionCntResTotUse;

   	@Column(name = "fastretransmission_cnt_res_tot_caution")
	private Integer fastretransmissionCntResTotCaution;

   	@Column(name = "fastretransmission_cnt_res_tot_warning")
	private Integer fastretransmissionCntResTotWarning;

   	@Column(name = "fastretransmission_cnt_res_tot_danger")
	private Integer fastretransmissionCntResTotDanger;

   	@Column(name = "fastretransmission_cnt_res_tot_obstacle")
	private Integer fastretransmissionCntResTotObstacle;

   	@Column(name = "fastretransmission_len_req_tot_use")
	private Boolean fastretransmissionLenReqTotUse;

   	@Column(name = "fastretransmission_len_req_tot_caution")
	private Integer fastretransmissionLenReqTotCaution;

   	@Column(name = "fastretransmission_len_req_tot_warning")
	private Integer fastretransmissionLenReqTotWarning;

   	@Column(name = "fastretransmission_len_req_tot_danger")
	private Integer fastretransmissionLenReqTotDanger;

   	@Column(name = "fastretransmission_len_req_tot_obstacle")
	private Integer fastretransmissionLenReqTotObstacle;

   	@Column(name = "fastretransmission_len_res_tot_use")
	private Boolean fastretransmissionLenResTotUse;

   	@Column(name = "fastretransmission_len_res_tot_caution")
	private Integer fastretransmissionLenResTotCaution;

   	@Column(name = "fastretransmission_len_res_tot_warning")
	private Integer fastretransmissionLenResTotWarning;

   	@Column(name = "fastretransmission_len_res_tot_danger")
	private Integer fastretransmissionLenResTotDanger;

   	@Column(name = "fastretransmission_len_res_tot_obstacle")
	private Integer fastretransmissionLenResTotObstacle;

   	@Column(name = "outoforder_cnt_req_tot_use")
	private Boolean outoforderCntReqTotUse;

   	@Column(name = "outoforder_cnt_req_tot_caution")
	private Integer outoforderCntReqTotCaution;

   	@Column(name = "outoforder_cnt_req_tot_warning")
	private Integer outoforderCntReqTotWarning;

   	@Column(name = "outoforder_cnt_req_tot_danger")
	private Integer outoforderCntReqTotDanger;

   	@Column(name = "outoforder_cnt_req_tot_obstacle")
	private Integer outoforderCntReqTotObstacle;

   	@Column(name = "outoforder_cnt_res_tot_use")
	private Boolean outoforderCntResTotUse;

   	@Column(name = "outoforder_cnt_res_tot_caution")
	private Integer outoforderCntResTotCaution;

   	@Column(name = "outoforder_cnt_res_tot_warning")
	private Integer outoforderCntResTotWarning;

   	@Column(name = "outoforder_cnt_res_tot_danger")
	private Integer outoforderCntResTotDanger;

   	@Column(name = "outoforder_cnt_res_tot_obstacle")
	private Integer outoforderCntResTotObstacle;

   	@Column(name = "outoforder_len_req_tot_use")
	private Boolean outoforderLenReqTotUse;

   	@Column(name = "outoforder_len_req_tot_caution")
	private Integer outoforderLenReqTotCaution;

   	@Column(name = "outoforder_len_req_tot_warning")
	private Integer outoforderLenReqTotWarning;

   	@Column(name = "outoforder_len_req_tot_danger")
	private Integer outoforderLenReqTotDanger;

   	@Column(name = "outoforder_len_req_tot_obstacle")
	private Integer outoforderLenReqTotObstacle;

   	@Column(name = "outoforder_len_res_tot_use")
	private Boolean outoforderLenResTotUse;

   	@Column(name = "outoforder_len_res_tot_caution")
	private Integer outoforderLenResTotCaution;

   	@Column(name = "outoforder_len_res_tot_warning")
	private Integer outoforderLenResTotWarning;

   	@Column(name = "outoforder_len_res_tot_danger")
	private Integer outoforderLenResTotDanger;

   	@Column(name = "outoforder_len_res_tot_obstacle")
	private Integer outoforderLenResTotObstacle;

   	@Column(name = "lostseg_cnt_req_tot_use")
	private Boolean lostsegCntReqTotUse;

   	@Column(name = "lostseg_cnt_req_tot_caution")
	private Integer lostsegCntReqTotCaution;

   	@Column(name = "lostseg_cnt_req_tot_warning")
	private Integer lostsegCntReqTotWarning;

   	@Column(name = "lostseg_cnt_req_tot_danger")
	private Integer lostsegCntReqTotDanger;

   	@Column(name = "lostseg_cnt_req_tot_obstacle")
	private Integer lostsegCntReqTotObstacle;

   	@Column(name = "lostseg_cnt_res_tot_use")
	private Boolean lostsegCntResTotUse;

   	@Column(name = "lostseg_cnt_res_tot_caution")
	private Integer lostsegCntResTotCaution;

   	@Column(name = "lostseg_cnt_res_tot_warning")
	private Integer lostsegCntResTotWarning;

   	@Column(name = "lostseg_cnt_res_tot_danger")
	private Integer lostsegCntResTotDanger;

   	@Column(name = "lostseg_cnt_res_tot_obstacle")
	private Integer lostsegCntResTotObstacle;

   	@Column(name = "lostseg_len_req_tot_use")
	private Boolean lostsegLenReqTotUse;

   	@Column(name = "lostseg_len_req_tot_caution")
	private Integer lostsegLenReqTotCaution;

   	@Column(name = "lostseg_len_req_tot_warning")
	private Integer lostsegLenReqTotWarning;

   	@Column(name = "lostseg_len_req_tot_danger")
	private Integer lostsegLenReqTotDanger;

   	@Column(name = "lostseg_len_req_tot_obstacle")
	private Integer lostsegLenReqTotObstacle;

   	@Column(name = "lostseg_len_res_tot_use")
	private Boolean lostsegLenResTotUse;

   	@Column(name = "lostseg_len_res_tot_caution")
	private Integer lostsegLenResTotCaution;

   	@Column(name = "lostseg_len_res_tot_warning")
	private Integer lostsegLenResTotWarning;

   	@Column(name = "lostseg_len_res_tot_danger")
	private Integer lostsegLenResTotDanger;

   	@Column(name = "lostseg_len_res_tot_obstacle")
	private Integer lostsegLenResTotObstacle;

   	@Column(name = "acklost_cnt_req_tot_use")
	private Boolean acklostCntReqTotUse;

   	@Column(name = "acklost_cnt_req_tot_caution")
	private Integer acklostCntReqTotCaution;

   	@Column(name = "acklost_cnt_req_tot_warning")
	private Integer acklostCntReqTotWarning;

   	@Column(name = "acklost_cnt_req_tot_danger")
	private Integer acklostCntReqTotDanger;

   	@Column(name = "acklost_cnt_req_tot_obstacle")
	private Integer acklostCntReqTotObstacle;

   	@Column(name = "acklost_cnt_res_tot_use")
	private Boolean acklostCntResTotUse;

   	@Column(name = "acklost_cnt_res_tot_caution")
	private Integer acklostCntResTotCaution;

   	@Column(name = "acklost_cnt_res_tot_warning")
	private Integer acklostCntResTotWarning;

   	@Column(name = "acklost_cnt_res_tot_danger")
	private Integer acklostCntResTotDanger;

   	@Column(name = "acklost_cnt_res_tot_obstacle")
	private Integer acklostCntResTotObstacle;

   	@Column(name = "acklost_len_req_tot_use")
	private Boolean acklostLenReqTotUse;

   	@Column(name = "acklost_len_req_tot_caution")
	private Integer acklostLenReqTotCaution;

   	@Column(name = "acklost_len_req_tot_warning")
	private Integer acklostLenReqTotWarning;

   	@Column(name = "acklost_len_req_tot_danger")
	private Integer acklostLenReqTotDanger;

   	@Column(name = "acklost_len_req_tot_obstacle")
	private Integer acklostLenReqTotObstacle;

   	@Column(name = "acklost_len_res_tot_use")
	private Boolean acklostLenResTotUse;

   	@Column(name = "acklost_len_res_tot_caution")
	private Integer acklostLenResTotCaution;

   	@Column(name = "acklost_len_res_tot_warning")
	private Integer acklostLenResTotWarning;

   	@Column(name = "acklost_len_res_tot_danger")
	private Integer acklostLenResTotDanger;

   	@Column(name = "acklost_len_res_tot_obstacle")
	private Integer acklostLenResTotObstacle;

   	@Column(name = "winupdate_cnt_req_tot_use")
	private Boolean winupdateCntReqTotUse;

   	@Column(name = "winupdate_cnt_req_tot_caution")
	private Integer winupdateCntReqTotCaution;

   	@Column(name = "winupdate_cnt_req_tot_warning")
	private Integer winupdateCntReqTotWarning;

   	@Column(name = "winupdate_cnt_req_tot_danger")
	private Integer winupdateCntReqTotDanger;

   	@Column(name = "winupdate_cnt_req_tot_obstacle")
	private Integer winupdateCntReqTotObstacle;

   	@Column(name = "winupdate_cnt_res_tot_use")
	private Boolean winupdateCntResTotUse;

   	@Column(name = "winupdate_cnt_res_tot_caution")
	private Integer winupdateCntResTotCaution;

   	@Column(name = "winupdate_cnt_res_tot_warning")
	private Integer winupdateCntResTotWarning;

   	@Column(name = "winupdate_cnt_res_tot_danger")
	private Integer winupdateCntResTotDanger;

   	@Column(name = "winupdate_cnt_res_tot_obstacle")
	private Integer winupdateCntResTotObstacle;

   	@Column(name = "winupdate_len_req_tot_use")
	private Boolean winupdateLenReqTotUse;

   	@Column(name = "winupdate_len_req_tot_caution")
	private Integer winupdateLenReqTotCaution;

   	@Column(name = "winupdate_len_req_tot_warning")
	private Integer winupdateLenReqTotWarning;

   	@Column(name = "winupdate_len_req_tot_danger")
	private Integer winupdateLenReqTotDanger;

   	@Column(name = "winupdate_len_req_tot_obstacle")
	private Integer winupdateLenReqTotObstacle;

   	@Column(name = "winupdate_len_res_tot_use")
	private Boolean winupdateLenResTotUse;

   	@Column(name = "winupdate_len_res_tot_caution")
	private Integer winupdateLenResTotCaution;

   	@Column(name = "winupdate_len_res_tot_warning")
	private Integer winupdateLenResTotWarning;

   	@Column(name = "winupdate_len_res_tot_danger")
	private Integer winupdateLenResTotDanger;

   	@Column(name = "winupdate_len_res_tot_obstacle")
	private Integer winupdateLenResTotObstacle;

   	@Column(name = "dupack_cnt_req_tot_use")
	private Boolean dupackCntReqTotUse;

   	@Column(name = "dupack_cnt_req_tot_caution")
	private Integer dupackCntReqTotCaution;

   	@Column(name = "dupack_cnt_req_tot_warning")
	private Integer dupackCntReqTotWarning;

   	@Column(name = "dupack_cnt_req_tot_danger")
	private Integer dupackCntReqTotDanger;

   	@Column(name = "dupack_cnt_req_tot_obstacle")
	private Integer dupackCntReqTotObstacle;

   	@Column(name = "dupack_cnt_res_tot_use")
	private Boolean dupackCntResTotUse;

   	@Column(name = "dupack_cnt_res_tot_caution")
	private Integer dupackCntResTotCaution;

   	@Column(name = "dupack_cnt_res_tot_warning")
	private Integer dupackCntResTotWarning;

   	@Column(name = "dupack_cnt_res_tot_danger")
	private Integer dupackCntResTotDanger;

   	@Column(name = "dupack_cnt_res_tot_obstacle")
	private Integer dupackCntResTotObstacle;

   	@Column(name = "dupack_len_req_tot_use")
	private Boolean dupackLenReqTotUse;

   	@Column(name = "dupack_len_req_tot_caution")
	private Integer dupackLenReqTotCaution;

   	@Column(name = "dupack_len_req_tot_warning")
	private Integer dupackLenReqTotWarning;

   	@Column(name = "dupack_len_req_tot_danger")
	private Integer dupackLenReqTotDanger;

   	@Column(name = "dupack_len_req_tot_obstacle")
	private Integer dupackLenReqTotObstacle;

   	@Column(name = "dupack_len_res_tot_use")
	private Boolean dupackLenResTotUse;

   	@Column(name = "dupack_len_res_tot_caution")
	private Integer dupackLenResTotCaution;

   	@Column(name = "dupack_len_res_tot_warning")
	private Integer dupackLenResTotWarning;

   	@Column(name = "dupack_len_res_tot_danger")
	private Integer dupackLenResTotDanger;

   	@Column(name = "dupack_len_res_tot_obstacle")
	private Integer dupackLenResTotObstacle;

   	@Column(name = "zerowin_cnt_req_tot_use")
	private Boolean zerowinCntReqTotUse;

   	@Column(name = "zerowin_cnt_req_tot_caution")
	private Integer zerowinCntReqTotCaution;

   	@Column(name = "zerowin_cnt_req_tot_warning")
	private Integer zerowinCntReqTotWarning;

   	@Column(name = "zerowin_cnt_req_tot_danger")
	private Integer zerowinCntReqTotDanger;

   	@Column(name = "zerowin_cnt_req_tot_obstacle")
	private Integer zerowinCntReqTotObstacle;

   	@Column(name = "zerowin_cnt_res_tot_use")
	private Boolean zerowinCntResTotUse;

   	@Column(name = "zerowin_cnt_res_tot_caution")
	private Integer zerowinCntResTotCaution;

   	@Column(name = "zerowin_cnt_res_tot_warning")
	private Integer zerowinCntResTotWarning;

   	@Column(name = "zerowin_cnt_res_tot_danger")
	private Integer zerowinCntResTotDanger;

   	@Column(name = "zerowin_cnt_res_tot_obstacle")
	private Integer zerowinCntResTotObstacle;

   	@Column(name = "zerowin_len_req_tot_use")
	private Boolean zerowinLenReqTotUse;

   	@Column(name = "zerowin_len_req_tot_caution")
	private Integer zerowinLenReqTotCaution;

   	@Column(name = "zerowin_len_req_tot_warning")
	private Integer zerowinLenReqTotWarning;

   	@Column(name = "zerowin_len_req_tot_danger")
	private Integer zerowinLenReqTotDanger;

   	@Column(name = "zerowin_len_req_tot_obstacle")
	private Integer zerowinLenReqTotObstacle;

   	@Column(name = "zerowin_len_res_tot_use")
	private Boolean zerowinLenResTotUse;

   	@Column(name = "zerowin_len_res_tot_caution")
	private Integer zerowinLenResTotCaution;

   	@Column(name = "zerowin_len_res_tot_warning")
	private Integer zerowinLenResTotWarning;

   	@Column(name = "zerowin_len_res_tot_danger")
	private Integer zerowinLenResTotDanger;

   	@Column(name = "zerowin_len_res_tot_obstacle")
	private Integer zerowinLenResTotObstacle;

   	@Column(name = "retransmission_cnt_req_per_sec_use")
	private Boolean retransmissionCntReqPerSecUse;

   	@Column(name = "retransmission_cnt_req_per_sec_caution")
	private Integer retransmissionCntReqPerSecCaution;

   	@Column(name = "retransmission_cnt_req_per_sec_warning")
	private Integer retransmissionCntReqPerSecWarning;

   	@Column(name = "retransmission_cnt_req_per_sec_danger")
	private Integer retransmissionCntReqPerSecDanger;

   	@Column(name = "retransmission_cnt_req_per_sec_obstacle")
	private Integer retransmissionCntReqPerSecObstacle;

   	@Column(name = "retransmission_cnt_res_per_sec_use")
	private Boolean retransmissionCntResPerSecUse;

   	@Column(name = "retransmission_cnt_res_per_sec_caution")
	private Integer retransmissionCntResPerSecCaution;

   	@Column(name = "retransmission_cnt_res_per_sec_warning")
	private Integer retransmissionCntResPerSecWarning;

   	@Column(name = "retransmission_cnt_res_per_sec_danger")
	private Integer retransmissionCntResPerSecDanger;

   	@Column(name = "retransmission_cnt_res_per_sec_obstacle")
	private Integer retransmissionCntResPerSecObstacle;

   	@Column(name = "retransmission_len_req_per_sec_use")
	private Boolean retransmissionLenReqPerSecUse;

   	@Column(name = "retransmission_len_req_per_sec_caution")
	private Integer retransmissionLenReqPerSecCaution;

   	@Column(name = "retransmission_len_req_per_sec_warning")
	private Integer retransmissionLenReqPerSecWarning;

   	@Column(name = "retransmission_len_req_per_sec_danger")
	private Integer retransmissionLenReqPerSecDanger;

   	@Column(name = "retransmission_len_req_per_sec_obstacle")
	private Integer retransmissionLenReqPerSecObstacle;

   	@Column(name = "retransmission_len_res_per_sec_use")
	private Boolean retransmissionLenResPerSecUse;

   	@Column(name = "retransmission_len_res_per_sec_caution")
	private Integer retransmissionLenResPerSecCaution;

   	@Column(name = "retransmission_len_res_per_sec_warning")
	private Integer retransmissionLenResPerSecWarning;

   	@Column(name = "retransmission_len_res_per_sec_danger")
	private Integer retransmissionLenResPerSecDanger;

   	@Column(name = "retransmission_len_res_per_sec_obstacle")
	private Integer retransmissionLenResPerSecObstacle;

   	@Column(name = "fastretransmission_cnt_req_per_sec_use")
	private Boolean fastretransmissionCntReqPerSecUse;

   	@Column(name = "fastretransmission_cnt_req_per_sec_caution")
	private Integer fastretransmissionCntReqPerSecCaution;

   	@Column(name = "fastretransmission_cnt_req_per_sec_warning")
	private Integer fastretransmissionCntReqPerSecWarning;

   	@Column(name = "fastretransmission_cnt_req_per_sec_danger")
	private Integer fastretransmissionCntReqPerSecDanger;

   	@Column(name = "fastretransmission_cnt_req_per_sec_obstacle")
	private Integer fastretransmissionCntReqPerSecObstacle;

   	@Column(name = "fastretransmission_cnt_res_per_sec_use")
	private Boolean fastretransmissionCntResPerSecUse;

   	@Column(name = "fastretransmission_cnt_res_per_sec_caution")
	private Integer fastretransmissionCntResPerSecCaution;

   	@Column(name = "fastretransmission_cnt_res_per_sec_warning")
	private Integer fastretransmissionCntResPerSecWarning;

   	@Column(name = "fastretransmission_cnt_res_per_sec_danger")
	private Integer fastretransmissionCntResPerSecDanger;

   	@Column(name = "fastretransmission_cnt_res_per_sec_obstacle")
	private Integer fastretransmissionCntResPerSecObstacle;

   	@Column(name = "fastretransmission_len_req_per_sec_use")
	private Boolean fastretransmissionLenReqPerSecUse;

   	@Column(name = "fastretransmission_len_req_per_sec_caution")
	private Integer fastretransmissionLenReqPerSecCaution;

   	@Column(name = "fastretransmission_len_req_per_sec_warning")
	private Integer fastretransmissionLenReqPerSecWarning;

   	@Column(name = "fastretransmission_len_req_per_sec_danger")
	private Integer fastretransmissionLenReqPerSecDanger;

   	@Column(name = "fastretransmission_len_req_per_sec_obstacle")
	private Integer fastretransmissionLenReqPerSecObstacle;

   	@Column(name = "fastretransmission_len_res_per_sec_use")
	private Boolean fastretransmissionLenResPerSecUse;

   	@Column(name = "fastretransmission_len_res_per_sec_caution")
	private Integer fastretransmissionLenResPerSecCaution;

   	@Column(name = "fastretransmission_len_res_per_sec_warning")
	private Integer fastretransmissionLenResPerSecWarning;

   	@Column(name = "fastretransmission_len_res_per_sec_danger")
	private Integer fastretransmissionLenResPerSecDanger;

   	@Column(name = "fastretransmission_len_res_per_sec_obstacle")
	private Integer fastretransmissionLenResPerSecObstacle;

   	@Column(name = "outoforder_cnt_req_per_sec_use")
	private Boolean outoforderCntReqPerSecUse;

   	@Column(name = "outoforder_cnt_req_per_sec_caution")
	private Integer outoforderCntReqPerSecCaution;

   	@Column(name = "outoforder_cnt_req_per_sec_warning")
	private Integer outoforderCntReqPerSecWarning;

   	@Column(name = "outoforder_cnt_req_per_sec_danger")
	private Integer outoforderCntReqPerSecDanger;

   	@Column(name = "outoforder_cnt_req_per_sec_obstacle")
	private Integer outoforderCntReqPerSecObstacle;

   	@Column(name = "outoforder_cnt_res_per_sec_use")
	private Boolean outoforderCntResPerSecUse;

   	@Column(name = "outoforder_cnt_res_per_sec_caution")
	private Integer outoforderCntResPerSecCaution;

   	@Column(name = "outoforder_cnt_res_per_sec_warning")
	private Integer outoforderCntResPerSecWarning;

   	@Column(name = "outoforder_cnt_res_per_sec_danger")
	private Integer outoforderCntResPerSecDanger;

   	@Column(name = "outoforder_cnt_res_per_sec_obstacle")
	private Integer outoforderCntResPerSecObstacle;

   	@Column(name = "outoforder_len_req_per_sec_use")
	private Boolean outoforderLenReqPerSecUse;

   	@Column(name = "outoforder_len_req_per_sec_caution")
	private Integer outoforderLenReqPerSecCaution;

   	@Column(name = "outoforder_len_req_per_sec_warning")
	private Integer outoforderLenReqPerSecWarning;

   	@Column(name = "outoforder_len_req_per_sec_danger")
	private Integer outoforderLenReqPerSecDanger;

   	@Column(name = "outoforder_len_req_per_sec_obstacle")
	private Integer outoforderLenReqPerSecObstacle;

   	@Column(name = "outoforder_len_res_per_sec_use")
	private Boolean outoforderLenResPerSecUse;

   	@Column(name = "outoforder_len_res_per_sec_caution")
	private Integer outoforderLenResPerSecCaution;

   	@Column(name = "outoforder_len_res_per_sec_warning")
	private Integer outoforderLenResPerSecWarning;

   	@Column(name = "outoforder_len_res_per_sec_danger")
	private Integer outoforderLenResPerSecDanger;

   	@Column(name = "outoforder_len_res_per_sec_obstacle")
	private Integer outoforderLenResPerSecObstacle;

   	@Column(name = "lostseg_cnt_req_per_sec_use")
	private Boolean lostsegCntReqPerSecUse;

   	@Column(name = "lostseg_cnt_req_per_sec_caution")
	private Integer lostsegCntReqPerSecCaution;

   	@Column(name = "lostseg_cnt_req_per_sec_warning")
	private Integer lostsegCntReqPerSecWarning;

   	@Column(name = "lostseg_cnt_req_per_sec_danger")
	private Integer lostsegCntReqPerSecDanger;

   	@Column(name = "lostseg_cnt_req_per_sec_obstacle")
	private Integer lostsegCntReqPerSecObstacle;

   	@Column(name = "lostseg_cnt_res_per_sec_use")
	private Boolean lostsegCntResPerSecUse;

   	@Column(name = "lostseg_cnt_res_per_sec_caution")
	private Integer lostsegCntResPerSecCaution;

   	@Column(name = "lostseg_cnt_res_per_sec_warning")
	private Integer lostsegCntResPerSecWarning;

   	@Column(name = "lostseg_cnt_res_per_sec_danger")
	private Integer lostsegCntResPerSecDanger;

   	@Column(name = "lostseg_cnt_res_per_sec_obstacle")
	private Integer lostsegCntResPerSecObstacle;

   	@Column(name = "lostseg_len_req_per_sec_use")
	private Boolean lostsegLenReqPerSecUse;

   	@Column(name = "lostseg_len_req_per_sec_caution")
	private Integer lostsegLenReqPerSecCaution;

   	@Column(name = "lostseg_len_req_per_sec_warning")
	private Integer lostsegLenReqPerSecWarning;

   	@Column(name = "lostseg_len_req_per_sec_danger")
	private Integer lostsegLenReqPerSecDanger;

   	@Column(name = "lostseg_len_req_per_sec_obstacle")
	private Integer lostsegLenReqPerSecObstacle;

   	@Column(name = "lostseg_len_res_per_sec_use")
	private Boolean lostsegLenResPerSecUse;

   	@Column(name = "lostseg_len_res_per_sec_caution")
	private Integer lostsegLenResPerSecCaution;

   	@Column(name = "lostseg_len_res_per_sec_warning")
	private Integer lostsegLenResPerSecWarning;

   	@Column(name = "lostseg_len_res_per_sec_danger")
	private Integer lostsegLenResPerSecDanger;

   	@Column(name = "lostseg_len_res_per_sec_obstacle")
	private Integer lostsegLenResPerSecObstacle;

   	@Column(name = "acklost_cnt_req_per_sec_use")
	private Boolean acklostCntReqPerSecUse;

   	@Column(name = "acklost_cnt_req_per_sec_caution")
	private Integer acklostCntReqPerSecCaution;

   	@Column(name = "acklost_cnt_req_per_sec_warning")
	private Integer acklostCntReqPerSecWarning;

   	@Column(name = "acklost_cnt_req_per_sec_danger")
	private Integer acklostCntReqPerSecDanger;

   	@Column(name = "acklost_cnt_req_per_sec_obstacle")
	private Integer acklostCntReqPerSecObstacle;

   	@Column(name = "acklost_cnt_res_per_sec_use")
	private Boolean acklostCntResPerSecUse;

   	@Column(name = "acklost_cnt_res_per_sec_caution")
	private Integer acklostCntResPerSecCaution;

   	@Column(name = "acklost_cnt_res_per_sec_warning")
	private Integer acklostCntResPerSecWarning;

   	@Column(name = "acklost_cnt_res_per_sec_danger")
	private Integer acklostCntResPerSecDanger;

   	@Column(name = "acklost_cnt_res_per_sec_obstacle")
	private Integer acklostCntResPerSecObstacle;

   	@Column(name = "acklost_len_req_per_sec_use")
	private Boolean acklostLenReqPerSecUse;

   	@Column(name = "acklost_len_req_per_sec_caution")
	private Integer acklostLenReqPerSecCaution;

   	@Column(name = "acklost_len_req_per_sec_warning")
	private Integer acklostLenReqPerSecWarning;

   	@Column(name = "acklost_len_req_per_sec_danger")
	private Integer acklostLenReqPerSecDanger;

   	@Column(name = "acklost_len_req_per_sec_obstacle")
	private Integer acklostLenReqPerSecObstacle;

   	@Column(name = "acklost_len_res_per_sec_use")
	private Boolean acklostLenResPerSecUse;

   	@Column(name = "acklost_len_res_per_sec_caution")
	private Integer acklostLenResPerSecCaution;

   	@Column(name = "acklost_len_res_per_sec_warning")
	private Integer acklostLenResPerSecWarning;

   	@Column(name = "acklost_len_res_per_sec_danger")
	private Integer acklostLenResPerSecDanger;

   	@Column(name = "acklost_len_res_per_sec_obstacle")
	private Integer acklostLenResPerSecObstacle;

   	@Column(name = "winupdate_cnt_req_per_sec_use")
	private Boolean winupdateCntReqPerSecUse;

   	@Column(name = "winupdate_cnt_req_per_sec_caution")
	private Integer winupdateCntReqPerSecCaution;

   	@Column(name = "winupdate_cnt_req_per_sec_warning")
	private Integer winupdateCntReqPerSecWarning;

   	@Column(name = "winupdate_cnt_req_per_sec_danger")
	private Integer winupdateCntReqPerSecDanger;

   	@Column(name = "winupdate_cnt_req_per_sec_obstacle")
	private Integer winupdateCntReqPerSecObstacle;

   	@Column(name = "winupdate_cnt_res_per_sec_use")
	private Boolean winupdateCntResPerSecUse;

   	@Column(name = "winupdate_cnt_res_per_sec_caution")
	private Integer winupdateCntResPerSecCaution;

   	@Column(name = "winupdate_cnt_res_per_sec_warning")
	private Integer winupdateCntResPerSecWarning;

   	@Column(name = "winupdate_cnt_res_per_sec_danger")
	private Integer winupdateCntResPerSecDanger;

   	@Column(name = "winupdate_cnt_res_per_sec_obstacle")
	private Integer winupdateCntResPerSecObstacle;

   	@Column(name = "winupdate_len_req_per_sec_use")
	private Boolean winupdateLenReqPerSecUse;

   	@Column(name = "winupdate_len_req_per_sec_caution")
	private Integer winupdateLenReqPerSecCaution;

   	@Column(name = "winupdate_len_req_per_sec_warning")
	private Integer winupdateLenReqPerSecWarning;

   	@Column(name = "winupdate_len_req_per_sec_danger")
	private Integer winupdateLenReqPerSecDanger;

   	@Column(name = "winupdate_len_req_per_sec_obstacle")
	private Integer winupdateLenReqPerSecObstacle;

   	@Column(name = "winupdate_len_res_per_sec_use")
	private Boolean winupdateLenResPerSecUse;

   	@Column(name = "winupdate_len_res_per_sec_caution")
	private Integer winupdateLenResPerSecCaution;

   	@Column(name = "winupdate_len_res_per_sec_warning")
	private Integer winupdateLenResPerSecWarning;

   	@Column(name = "winupdate_len_res_per_sec_danger")
	private Integer winupdateLenResPerSecDanger;

   	@Column(name = "winupdate_len_res_per_sec_obstacle")
	private Integer winupdateLenResPerSecObstacle;

   	@Column(name = "dupack_cnt_req_per_sec_use")
	private Boolean dupackCntReqPerSecUse;

   	@Column(name = "dupack_cnt_req_per_sec_caution")
	private Integer dupackCntReqPerSecCaution;

   	@Column(name = "dupack_cnt_req_per_sec_warning")
	private Integer dupackCntReqPerSecWarning;

   	@Column(name = "dupack_cnt_req_per_sec_danger")
	private Integer dupackCntReqPerSecDanger;

   	@Column(name = "dupack_cnt_req_per_sec_obstacle")
	private Integer dupackCntReqPerSecObstacle;

   	@Column(name = "dupack_cnt_res_per_sec_use")
	private Boolean dupackCntResPerSecUse;

   	@Column(name = "dupack_cnt_res_per_sec_caution")
	private Integer dupackCntResPerSecCaution;

   	@Column(name = "dupack_cnt_res_per_sec_warning")
	private Integer dupackCntResPerSecWarning;

   	@Column(name = "dupack_cnt_res_per_sec_danger")
	private Integer dupackCntResPerSecDanger;

   	@Column(name = "dupack_cnt_res_per_sec_obstacle")
	private Integer dupackCntResPerSecObstacle;

   	@Column(name = "dupack_len_req_per_sec_use")
	private Boolean dupackLenReqPerSecUse;

   	@Column(name = "dupack_len_req_per_sec_caution")
	private Integer dupackLenReqPerSecCaution;

   	@Column(name = "dupack_len_req_per_sec_warning")
	private Integer dupackLenReqPerSecWarning;

   	@Column(name = "dupack_len_req_per_sec_danger")
	private Integer dupackLenReqPerSecDanger;

   	@Column(name = "dupack_len_req_per_sec_obstacle")
	private Integer dupackLenReqPerSecObstacle;

   	@Column(name = "dupack_len_res_per_sec_use")
	private Boolean dupackLenResPerSecUse;

   	@Column(name = "dupack_len_res_per_sec_caution")
	private Integer dupackLenResPerSecCaution;

   	@Column(name = "dupack_len_res_per_sec_warning")
	private Integer dupackLenResPerSecWarning;

   	@Column(name = "dupack_len_res_per_sec_danger")
	private Integer dupackLenResPerSecDanger;

   	@Column(name = "dupack_len_res_per_sec_obstacle")
	private Integer dupackLenResPerSecObstacle;

   	@Column(name = "zerowin_cnt_req_per_sec_use")
	private Boolean zerowinCntReqPerSecUse;

   	@Column(name = "zerowin_cnt_req_per_sec_caution")
	private Integer zerowinCntReqPerSecCaution;

   	@Column(name = "zerowin_cnt_req_per_sec_warning")
	private Integer zerowinCntReqPerSecWarning;

   	@Column(name = "zerowin_cnt_req_per_sec_danger")
	private Integer zerowinCntReqPerSecDanger;

   	@Column(name = "zerowin_cnt_req_per_sec_obstacle")
	private Integer zerowinCntReqPerSecObstacle;

   	@Column(name = "zerowin_cnt_res_per_sec_use")
	private Boolean zerowinCntResPerSecUse;

   	@Column(name = "zerowin_cnt_res_per_sec_caution")
	private Integer zerowinCntResPerSecCaution;

   	@Column(name = "zerowin_cnt_res_per_sec_warning")
	private Integer zerowinCntResPerSecWarning;

   	@Column(name = "zerowin_cnt_res_per_sec_danger")
	private Integer zerowinCntResPerSecDanger;

   	@Column(name = "zerowin_cnt_res_per_sec_obstacle")
	private Integer zerowinCntResPerSecObstacle;

   	@Column(name = "zerowin_len_req_per_sec_use")
	private Boolean zerowinLenReqPerSecUse;

   	@Column(name = "zerowin_len_req_per_sec_caution")
	private Integer zerowinLenReqPerSecCaution;

   	@Column(name = "zerowin_len_req_per_sec_warning")
	private Integer zerowinLenReqPerSecWarning;

   	@Column(name = "zerowin_len_req_per_sec_danger")
	private Integer zerowinLenReqPerSecDanger;

   	@Column(name = "zerowin_len_req_per_sec_obstacle")
	private Integer zerowinLenReqPerSecObstacle;

   	@Column(name = "zerowin_len_res_per_sec_use")
	private Boolean zerowinLenResPerSecUse;

   	@Column(name = "zerowin_len_res_per_sec_caution")
	private Integer zerowinLenResPerSecCaution;

   	@Column(name = "zerowin_len_res_per_sec_warning")
	private Integer zerowinLenResPerSecWarning;

   	@Column(name = "zerowin_len_res_per_sec_danger")
	private Integer zerowinLenResPerSecDanger;

   	@Column(name = "zerowin_len_res_per_sec_obstacle")
	private Integer zerowinLenResPerSecObstacle;

   	@Column(name = "retransmission_cnt_req_delta_use")
	private Boolean retransmissionCntReqDeltaUse;

   	@Column(name = "retransmission_cnt_req_delta_caution")
	private Integer retransmissionCntReqDeltaCaution;

   	@Column(name = "retransmission_cnt_req_delta_warning")
	private Integer retransmissionCntReqDeltaWarning;

   	@Column(name = "retransmission_cnt_req_delta_danger")
	private Integer retransmissionCntReqDeltaDanger;

   	@Column(name = "retransmission_cnt_req_delta_obstacle")
	private Integer retransmissionCntReqDeltaObstacle;

   	@Column(name = "retransmission_cnt_res_delta_use")
	private Boolean retransmissionCntResDeltaUse;

   	@Column(name = "retransmission_cnt_res_delta_caution")
	private Integer retransmissionCntResDeltaCaution;

   	@Column(name = "retransmission_cnt_res_delta_warning")
	private Integer retransmissionCntResDeltaWarning;

   	@Column(name = "retransmission_cnt_res_delta_danger")
	private Integer retransmissionCntResDeltaDanger;

   	@Column(name = "retransmission_cnt_res_delta_obstacle")
	private Integer retransmissionCntResDeltaObstacle;

   	@Column(name = "retransmission_len_req_delta_use")
	private Boolean retransmissionLenReqDeltaUse;

   	@Column(name = "retransmission_len_req_delta_caution")
	private Integer retransmissionLenReqDeltaCaution;

   	@Column(name = "retransmission_len_req_delta_warning")
	private Integer retransmissionLenReqDeltaWarning;

   	@Column(name = "retransmission_len_req_delta_danger")
	private Integer retransmissionLenReqDeltaDanger;

   	@Column(name = "retransmission_len_req_delta_obstacle")
	private Integer retransmissionLenReqDeltaObstacle;

   	@Column(name = "retransmission_len_res_delta_use")
	private Boolean retransmissionLenResDeltaUse;

   	@Column(name = "retransmission_len_res_delta_caution")
	private Integer retransmissionLenResDeltaCaution;

   	@Column(name = "retransmission_len_res_delta_warning")
	private Integer retransmissionLenResDeltaWarning;

   	@Column(name = "retransmission_len_res_delta_danger")
	private Integer retransmissionLenResDeltaDanger;

   	@Column(name = "retransmission_len_res_delta_obstacle")
	private Integer retransmissionLenResDeltaObstacle;

   	@Column(name = "fastretransmission_cnt_req_delta_use")
	private Boolean fastretransmissionCntReqDeltaUse;

   	@Column(name = "fastretransmission_cnt_req_delta_caution")
	private Integer fastretransmissionCntReqDeltaCaution;

   	@Column(name = "fastretransmission_cnt_req_delta_warning")
	private Integer fastretransmissionCntReqDeltaWarning;

   	@Column(name = "fastretransmission_cnt_req_delta_danger")
	private Integer fastretransmissionCntReqDeltaDanger;

   	@Column(name = "fastretransmission_cnt_req_delta_obstacle")
	private Integer fastretransmissionCntReqDeltaObstacle;

   	@Column(name = "fastretransmission_cnt_res_delta_use")
	private Boolean fastretransmissionCntResDeltaUse;

   	@Column(name = "fastretransmission_cnt_res_delta_caution")
	private Integer fastretransmissionCntResDeltaCaution;

   	@Column(name = "fastretransmission_cnt_res_delta_warning")
	private Integer fastretransmissionCntResDeltaWarning;

   	@Column(name = "fastretransmission_cnt_res_delta_danger")
	private Integer fastretransmissionCntResDeltaDanger;

   	@Column(name = "fastretransmission_cnt_res_delta_obstacle")
	private Integer fastretransmissionCntResDeltaObstacle;

   	@Column(name = "fastretransmission_len_req_delta_use")
	private Boolean fastretransmissionLenReqDeltaUse;

   	@Column(name = "fastretransmission_len_req_delta_caution")
	private Integer fastretransmissionLenReqDeltaCaution;

   	@Column(name = "fastretransmission_len_req_delta_warning")
	private Integer fastretransmissionLenReqDeltaWarning;

   	@Column(name = "fastretransmission_len_req_delta_danger")
	private Integer fastretransmissionLenReqDeltaDanger;

   	@Column(name = "fastretransmission_len_req_delta_obstacle")
	private Integer fastretransmissionLenReqDeltaObstacle;

   	@Column(name = "fastretransmission_len_res_delta_use")
	private Boolean fastretransmissionLenResDeltaUse;

   	@Column(name = "fastretransmission_len_res_delta_caution")
	private Integer fastretransmissionLenResDeltaCaution;

   	@Column(name = "fastretransmission_len_res_delta_warning")
	private Integer fastretransmissionLenResDeltaWarning;

   	@Column(name = "fastretransmission_len_res_delta_danger")
	private Integer fastretransmissionLenResDeltaDanger;

   	@Column(name = "fastretransmission_len_res_delta_obstacle")
	private Integer fastretransmissionLenResDeltaObstacle;

   	@Column(name = "outoforder_cnt_req_delta_use")
	private Boolean outoforderCntReqDeltaUse;

   	@Column(name = "outoforder_cnt_req_delta_caution")
	private Integer outoforderCntReqDeltaCaution;

   	@Column(name = "outoforder_cnt_req_delta_warning")
	private Integer outoforderCntReqDeltaWarning;

   	@Column(name = "outoforder_cnt_req_delta_danger")
	private Integer outoforderCntReqDeltaDanger;

   	@Column(name = "outoforder_cnt_req_delta_obstacle")
	private Integer outoforderCntReqDeltaObstacle;

   	@Column(name = "outoforder_cnt_res_delta_use")
	private Boolean outoforderCntResDeltaUse;

   	@Column(name = "outoforder_cnt_res_delta_caution")
	private Integer outoforderCntResDeltaCaution;

   	@Column(name = "outoforder_cnt_res_delta_warning")
	private Integer outoforderCntResDeltaWarning;

   	@Column(name = "outoforder_cnt_res_delta_danger")
	private Integer outoforderCntResDeltaDanger;

   	@Column(name = "outoforder_cnt_res_delta_obstacle")
	private Integer outoforderCntResDeltaObstacle;

   	@Column(name = "outoforder_len_req_delta_use")
	private Boolean outoforderLenReqDeltaUse;

   	@Column(name = "outoforder_len_req_delta_caution")
	private Integer outoforderLenReqDeltaCaution;

   	@Column(name = "outoforder_len_req_delta_warning")
	private Integer outoforderLenReqDeltaWarning;

   	@Column(name = "outoforder_len_req_delta_danger")
	private Integer outoforderLenReqDeltaDanger;

   	@Column(name = "outoforder_len_req_delta_obstacle")
	private Integer outoforderLenReqDeltaObstacle;

   	@Column(name = "outoforder_len_res_delta_use")
	private Boolean outoforderLenResDeltaUse;

   	@Column(name = "outoforder_len_res_delta_caution")
	private Integer outoforderLenResDeltaCaution;

   	@Column(name = "outoforder_len_res_delta_warning")
	private Integer outoforderLenResDeltaWarning;

   	@Column(name = "outoforder_len_res_delta_danger")
	private Integer outoforderLenResDeltaDanger;

   	@Column(name = "outoforder_len_res_delta_obstacle")
	private Integer outoforderLenResDeltaObstacle;

   	@Column(name = "lostseg_cnt_req_delta_use")
	private Boolean lostsegCntReqDeltaUse;

   	@Column(name = "lostseg_cnt_req_delta_caution")
	private Integer lostsegCntReqDeltaCaution;

   	@Column(name = "lostseg_cnt_req_delta_warning")
	private Integer lostsegCntReqDeltaWarning;

   	@Column(name = "lostseg_cnt_req_delta_danger")
	private Integer lostsegCntReqDeltaDanger;

   	@Column(name = "lostseg_cnt_req_delta_obstacle")
	private Integer lostsegCntReqDeltaObstacle;

   	@Column(name = "lostseg_cnt_res_delta_use")
	private Boolean lostsegCntResDeltaUse;

   	@Column(name = "lostseg_cnt_res_delta_caution")
	private Integer lostsegCntResDeltaCaution;

   	@Column(name = "lostseg_cnt_res_delta_warning")
	private Integer lostsegCntResDeltaWarning;

   	@Column(name = "lostseg_cnt_res_delta_danger")
	private Integer lostsegCntResDeltaDanger;

   	@Column(name = "lostseg_cnt_res_delta_obstacle")
	private Integer lostsegCntResDeltaObstacle;

   	@Column(name = "lostseg_len_req_delta_use")
	private Boolean lostsegLenReqDeltaUse;

   	@Column(name = "lostseg_len_req_delta_caution")
	private Integer lostsegLenReqDeltaCaution;

   	@Column(name = "lostseg_len_req_delta_warning")
	private Integer lostsegLenReqDeltaWarning;

   	@Column(name = "lostseg_len_req_delta_danger")
	private Integer lostsegLenReqDeltaDanger;

   	@Column(name = "lostseg_len_req_delta_obstacle")
	private Integer lostsegLenReqDeltaObstacle;

   	@Column(name = "lostseg_len_res_delta_use")
	private Boolean lostsegLenResDeltaUse;

   	@Column(name = "lostseg_len_res_delta_caution")
	private Integer lostsegLenResDeltaCaution;

   	@Column(name = "lostseg_len_res_delta_warning")
	private Integer lostsegLenResDeltaWarning;

   	@Column(name = "lostseg_len_res_delta_danger")
	private Integer lostsegLenResDeltaDanger;

   	@Column(name = "lostseg_len_res_delta_obstacle")
	private Integer lostsegLenResDeltaObstacle;

   	@Column(name = "acklost_cnt_req_delta_use")
	private Boolean acklostCntReqDeltaUse;

   	@Column(name = "acklost_cnt_req_delta_caution")
	private Integer acklostCntReqDeltaCaution;

   	@Column(name = "acklost_cnt_req_delta_warning")
	private Integer acklostCntReqDeltaWarning;

   	@Column(name = "acklost_cnt_req_delta_danger")
	private Integer acklostCntReqDeltaDanger;

   	@Column(name = "acklost_cnt_req_delta_obstacle")
	private Integer acklostCntReqDeltaObstacle;

   	@Column(name = "acklost_cnt_res_delta_use")
	private Boolean acklostCntResDeltaUse;

   	@Column(name = "acklost_cnt_res_delta_caution")
	private Integer acklostCntResDeltaCaution;

   	@Column(name = "acklost_cnt_res_delta_warning")
	private Integer acklostCntResDeltaWarning;

   	@Column(name = "acklost_cnt_res_delta_danger")
	private Integer acklostCntResDeltaDanger;

   	@Column(name = "acklost_cnt_res_delta_obstacle")
	private Integer acklostCntResDeltaObstacle;

   	@Column(name = "acklost_len_req_delta_use")
	private Boolean acklostLenReqDeltaUse;

   	@Column(name = "acklost_len_req_delta_caution")
	private Integer acklostLenReqDeltaCaution;

   	@Column(name = "acklost_len_req_delta_warning")
	private Integer acklostLenReqDeltaWarning;

   	@Column(name = "acklost_len_req_delta_danger")
	private Integer acklostLenReqDeltaDanger;

   	@Column(name = "acklost_len_req_delta_obstacle")
	private Integer acklostLenReqDeltaObstacle;

   	@Column(name = "acklost_len_res_delta_use")
	private Boolean acklostLenResDeltaUse;

   	@Column(name = "acklost_len_res_delta_caution")
	private Integer acklostLenResDeltaCaution;

   	@Column(name = "acklost_len_res_delta_warning")
	private Integer acklostLenResDeltaWarning;

   	@Column(name = "acklost_len_res_delta_danger")
	private Integer acklostLenResDeltaDanger;

   	@Column(name = "acklost_len_res_delta_obstacle")
	private Integer acklostLenResDeltaObstacle;

   	@Column(name = "winupdate_cnt_req_delta_use")
	private Boolean winupdateCntReqDeltaUse;

   	@Column(name = "winupdate_cnt_req_delta_caution")
	private Integer winupdateCntReqDeltaCaution;

   	@Column(name = "winupdate_cnt_req_delta_warning")
	private Integer winupdateCntReqDeltaWarning;

   	@Column(name = "winupdate_cnt_req_delta_danger")
	private Integer winupdateCntReqDeltaDanger;

   	@Column(name = "winupdate_cnt_req_delta_obstacle")
	private Integer winupdateCntReqDeltaObstacle;

   	@Column(name = "winupdate_cnt_res_delta_use")
	private Boolean winupdateCntResDeltaUse;

   	@Column(name = "winupdate_cnt_res_delta_caution")
	private Integer winupdateCntResDeltaCaution;

   	@Column(name = "winupdate_cnt_res_delta_warning")
	private Integer winupdateCntResDeltaWarning;

   	@Column(name = "winupdate_cnt_res_delta_danger")
	private Integer winupdateCntResDeltaDanger;

   	@Column(name = "winupdate_cnt_res_delta_obstacle")
	private Integer winupdateCntResDeltaObstacle;

   	@Column(name = "winupdate_len_req_delta_use")
	private Boolean winupdateLenReqDeltaUse;

   	@Column(name = "winupdate_len_req_delta_caution")
	private Integer winupdateLenReqDeltaCaution;

   	@Column(name = "winupdate_len_req_delta_warning")
	private Integer winupdateLenReqDeltaWarning;

   	@Column(name = "winupdate_len_req_delta_danger")
	private Integer winupdateLenReqDeltaDanger;

   	@Column(name = "winupdate_len_req_delta_obstacle")
	private Integer winupdateLenReqDeltaObstacle;

   	@Column(name = "winupdate_len_res_delta_use")
	private Boolean winupdateLenResDeltaUse;

   	@Column(name = "winupdate_len_res_delta_caution")
	private Integer winupdateLenResDeltaCaution;

   	@Column(name = "winupdate_len_res_delta_warning")
	private Integer winupdateLenResDeltaWarning;

   	@Column(name = "winupdate_len_res_delta_danger")
	private Integer winupdateLenResDeltaDanger;

   	@Column(name = "winupdate_len_res_delta_obstacle")
	private Integer winupdateLenResDeltaObstacle;

   	@Column(name = "dupack_cnt_req_delta_use")
	private Boolean dupackCntReqDeltaUse;

   	@Column(name = "dupack_cnt_req_delta_caution")
	private Integer dupackCntReqDeltaCaution;

   	@Column(name = "dupack_cnt_req_delta_warning")
	private Integer dupackCntReqDeltaWarning;

   	@Column(name = "dupack_cnt_req_delta_danger")
	private Integer dupackCntReqDeltaDanger;

   	@Column(name = "dupack_cnt_req_delta_obstacle")
	private Integer dupackCntReqDeltaObstacle;

   	@Column(name = "dupack_cnt_res_delta_use")
	private Boolean dupackCntResDeltaUse;

   	@Column(name = "dupack_cnt_res_delta_caution")
	private Integer dupackCntResDeltaCaution;

   	@Column(name = "dupack_cnt_res_delta_warning")
	private Integer dupackCntResDeltaWarning;

   	@Column(name = "dupack_cnt_res_delta_danger")
	private Integer dupackCntResDeltaDanger;

   	@Column(name = "dupack_cnt_res_delta_obstacle")
	private Integer dupackCntResDeltaObstacle;

   	@Column(name = "dupack_len_req_delta_use")
	private Boolean dupackLenReqDeltaUse;

   	@Column(name = "dupack_len_req_delta_caution")
	private Integer dupackLenReqDeltaCaution;

   	@Column(name = "dupack_len_req_delta_warning")
	private Integer dupackLenReqDeltaWarning;

   	@Column(name = "dupack_len_req_delta_danger")
	private Integer dupackLenReqDeltaDanger;

   	@Column(name = "dupack_len_req_delta_obstacle")
	private Integer dupackLenReqDeltaObstacle;

   	@Column(name = "dupack_len_res_delta_use")
	private Boolean dupackLenResDeltaUse;

   	@Column(name = "dupack_len_res_delta_caution")
	private Integer dupackLenResDeltaCaution;

   	@Column(name = "dupack_len_res_delta_warning")
	private Integer dupackLenResDeltaWarning;

   	@Column(name = "dupack_len_res_delta_danger")
	private Integer dupackLenResDeltaDanger;

   	@Column(name = "dupack_len_res_delta_obstacle")
	private Integer dupackLenResDeltaObstacle;

   	@Column(name = "zerowin_cnt_req_delta_use")
	private Boolean zerowinCntReqDeltaUse;

   	@Column(name = "zerowin_cnt_req_delta_caution")
	private Integer zerowinCntReqDeltaCaution;

   	@Column(name = "zerowin_cnt_req_delta_warning")
	private Integer zerowinCntReqDeltaWarning;

   	@Column(name = "zerowin_cnt_req_delta_danger")
	private Integer zerowinCntReqDeltaDanger;

   	@Column(name = "zerowin_cnt_req_delta_obstacle")
	private Integer zerowinCntReqDeltaObstacle;

   	@Column(name = "zerowin_cnt_res_delta_use")
	private Boolean zerowinCntResDeltaUse;

   	@Column(name = "zerowin_cnt_res_delta_caution")
	private Integer zerowinCntResDeltaCaution;

   	@Column(name = "zerowin_cnt_res_delta_warning")
	private Integer zerowinCntResDeltaWarning;

   	@Column(name = "zerowin_cnt_res_delta_danger")
	private Integer zerowinCntResDeltaDanger;

   	@Column(name = "zerowin_cnt_res_delta_obstacle")
	private Integer zerowinCntResDeltaObstacle;

   	@Column(name = "zerowin_len_req_delta_use")
	private Boolean zerowinLenReqDeltaUse;

   	@Column(name = "zerowin_len_req_delta_caution")
	private Integer zerowinLenReqDeltaCaution;

   	@Column(name = "zerowin_len_req_delta_warning")
	private Integer zerowinLenReqDeltaWarning;

   	@Column(name = "zerowin_len_req_delta_danger")
	private Integer zerowinLenReqDeltaDanger;

   	@Column(name = "zerowin_len_req_delta_obstacle")
	private Integer zerowinLenReqDeltaObstacle;

   	@Column(name = "zerowin_len_res_delta_use")
	private Boolean zerowinLenResDeltaUse;

   	@Column(name = "zerowin_len_res_delta_caution")
	private Integer zerowinLenResDeltaCaution;

   	@Column(name = "zerowin_len_res_delta_warning")
	private Integer zerowinLenResDeltaWarning;

   	@Column(name = "zerowin_len_res_delta_danger")
	private Integer zerowinLenResDeltaDanger;

   	@Column(name = "zerowin_len_res_delta_obstacle")
	private Integer zerowinLenResDeltaObstacle;

   	@Column(name = "spurious_retransmission_cnt_req_tot_use")
	private Boolean spuriousRetransmissionCntReqTotUse;

   	@Column(name = "spurious_retransmission_cnt_req_tot_caution")
	private Integer spuriousRetransmissionCntReqTotCaution;

   	@Column(name = "spurious_retransmission_cnt_req_tot_warning")
	private Integer spuriousRetransmissionCntReqTotWarning;

   	@Column(name = "spurious_retransmission_cnt_req_tot_danger")
	private Integer spuriousRetransmissionCntReqTotDanger;

   	@Column(name = "spurious_retransmission_cnt_req_tot_obstacle")
	private Integer spuriousRetransmissionCntReqTotObstacle;

   	@Column(name = "spurious_retransmission_cnt_res_tot_use")
	private Boolean spuriousRetransmissionCntResTotUse;

   	@Column(name = "spurious_retransmission_cnt_res_tot_caution")
	private Integer spuriousRetransmissionCntResTotCaution;

   	@Column(name = "spurious_retransmission_cnt_res_tot_warning")
	private Integer spuriousRetransmissionCntResTotWarning;

   	@Column(name = "spurious_retransmission_cnt_res_tot_danger")
	private Integer spuriousRetransmissionCntResTotDanger;

   	@Column(name = "spurious_retransmission_cnt_res_tot_obstacle")
	private Integer spuriousRetransmissionCntResTotObstacle;

   	@Column(name = "spurious_retransmission_len_req_tot_use")
	private Boolean spuriousRetransmissionLenReqTotUse;

   	@Column(name = "spurious_retransmission_len_req_tot_caution")
	private Integer spuriousRetransmissionLenReqTotCaution;

   	@Column(name = "spurious_retransmission_len_req_tot_warning")
	private Integer spuriousRetransmissionLenReqTotWarning;

   	@Column(name = "spurious_retransmission_len_req_tot_danger")
	private Integer spuriousRetransmissionLenReqTotDanger;

   	@Column(name = "spurious_retransmission_len_req_tot_obstacle")
	private Integer spuriousRetransmissionLenReqTotObstacle;

   	@Column(name = "spurious_retransmission_len_res_tot_use")
	private Boolean spuriousRetransmissionLenResTotUse;

   	@Column(name = "spurious_retransmission_len_res_tot_caution")
	private Integer spuriousRetransmissionLenResTotCaution;

   	@Column(name = "spurious_retransmission_len_res_tot_warning")
	private Integer spuriousRetransmissionLenResTotWarning;

   	@Column(name = "spurious_retransmission_len_res_tot_danger")
	private Integer spuriousRetransmissionLenResTotDanger;

   	@Column(name = "spurious_retransmission_len_res_tot_obstacle")
	private Integer spuriousRetransmissionLenResTotObstacle;

   	@Column(name = "spurious_retransmission_cnt_req_per_sec_use")
	private Boolean spuriousRetransmissionCntReqPerSecUse;

   	@Column(name = "spurious_retransmission_cnt_req_per_sec_caution")
	private Integer spuriousRetransmissionCntReqPerSecCaution;

   	@Column(name = "spurious_retransmission_cnt_req_per_sec_warning")
	private Integer spuriousRetransmissionCntReqPerSecWarning;

   	@Column(name = "spurious_retransmission_cnt_req_per_sec_danger")
	private Integer spuriousRetransmissionCntReqPerSecDanger;

   	@Column(name = "spurious_retransmission_cnt_req_per_sec_obstacle")
	private Integer spuriousRetransmissionCntReqPerSecObstacle;

   	@Column(name = "spurious_retransmission_cnt_res_per_sec_use")
	private Boolean spuriousRetransmissionCntResPerSecUse;

   	@Column(name = "spurious_retransmission_cnt_res_per_sec_caution")
	private Integer spuriousRetransmissionCntResPerSecCaution;

   	@Column(name = "spurious_retransmission_cnt_res_per_sec_warning")
	private Integer spuriousRetransmissionCntResPerSecWarning;

   	@Column(name = "spurious_retransmission_cnt_res_per_sec_danger")
	private Integer spuriousRetransmissionCntResPerSecDanger;

   	@Column(name = "spurious_retransmission_cnt_res_per_sec_obstacle")
	private Integer spuriousRetransmissionCntResPerSecObstacle;

   	@Column(name = "spurious_retransmission_len_req_per_sec_use")
	private Boolean spuriousRetransmissionLenReqPerSecUse;

   	@Column(name = "spurious_retransmission_len_req_per_sec_caution")
	private Integer spuriousRetransmissionLenReqPerSecCaution;

   	@Column(name = "spurious_retransmission_len_req_per_sec_warning")
	private Integer spuriousRetransmissionLenReqPerSecWarning;

   	@Column(name = "spurious_retransmission_len_req_per_sec_danger")
	private Integer spuriousRetransmissionLenReqPerSecDanger;

   	@Column(name = "spurious_retransmission_len_req_per_sec_obstacle")
	private Integer spuriousRetransmissionLenReqPerSecObstacle;

   	@Column(name = "spurious_retransmission_len_res_per_sec_use")
	private Boolean spuriousRetransmissionLenResPerSecUse;

   	@Column(name = "spurious_retransmission_len_res_per_sec_caution")
	private Integer spuriousRetransmissionLenResPerSecCaution;

   	@Column(name = "spurious_retransmission_len_res_per_sec_warning")
	private Integer spuriousRetransmissionLenResPerSecWarning;

   	@Column(name = "spurious_retransmission_len_res_per_sec_danger")
	private Integer spuriousRetransmissionLenResPerSecDanger;

   	@Column(name = "spurious_retransmission_len_res_per_sec_obstacle")
	private Integer spuriousRetransmissionLenResPerSecObstacle;

   	@Column(name = "spurious_retransmission_cnt_req_delta_use")
	private Boolean spuriousRetransmissionCntReqDeltaUse;

   	@Column(name = "spurious_retransmission_cnt_req_delta_caution")
	private Integer spuriousRetransmissionCntReqDeltaCaution;

   	@Column(name = "spurious_retransmission_cnt_req_delta_warning")
	private Integer spuriousRetransmissionCntReqDeltaWarning;

   	@Column(name = "spurious_retransmission_cnt_req_delta_danger")
	private Integer spuriousRetransmissionCntReqDeltaDanger;

   	@Column(name = "spurious_retransmission_cnt_req_delta_obstacle")
	private Integer spuriousRetransmissionCntReqDeltaObstacle;

   	@Column(name = "spurious_retransmission_cnt_res_delta_use")
	private Boolean spuriousRetransmissionCntResDeltaUse;

   	@Column(name = "spurious_retransmission_cnt_res_delta_caution")
	private Integer spuriousRetransmissionCntResDeltaCaution;

   	@Column(name = "spurious_retransmission_cnt_res_delta_warning")
	private Integer spuriousRetransmissionCntResDeltaWarning;

   	@Column(name = "spurious_retransmission_cnt_res_delta_danger")
	private Integer spuriousRetransmissionCntResDeltaDanger;

   	@Column(name = "spurious_retransmission_cnt_res_delta_obstacle")
	private Integer spuriousRetransmissionCntResDeltaObstacle;

   	@Column(name = "spurious_retransmission_len_req_delta_use")
	private Boolean spuriousRetransmissionLenReqDeltaUse;

   	@Column(name = "spurious_retransmission_len_req_delta_caution")
	private Integer spuriousRetransmissionLenReqDeltaCaution;

   	@Column(name = "spurious_retransmission_len_req_delta_warning")
	private Integer spuriousRetransmissionLenReqDeltaWarning;

   	@Column(name = "spurious_retransmission_len_req_delta_danger")
	private Integer spuriousRetransmissionLenReqDeltaDanger;

   	@Column(name = "spurious_retransmission_len_req_delta_obstacle")
	private Integer spuriousRetransmissionLenReqDeltaObstacle;

   	@Column(name = "spurious_retransmission_len_res_delta_use")
	private Boolean spuriousRetransmissionLenResDeltaUse;

   	@Column(name = "spurious_retransmission_len_res_delta_caution")
	private Integer spuriousRetransmissionLenResDeltaCaution;

   	@Column(name = "spurious_retransmission_len_res_delta_warning")
	private Integer spuriousRetransmissionLenResDeltaWarning;

   	@Column(name = "spurious_retransmission_len_res_delta_danger")
	private Integer spuriousRetransmissionLenResDeltaDanger;

   	@Column(name = "spurious_retransmission_len_res_delta_obstacle")
	private Integer spuriousRetransmissionLenResDeltaObstacle;

   	@Column(name = "overlap_cnt_req_tot_use")
	private Boolean overlapCntReqTotUse;

   	@Column(name = "overlap_cnt_req_tot_caution")
	private Integer overlapCntReqTotCaution;

   	@Column(name = "overlap_cnt_req_tot_warning")
	private Integer overlapCntReqTotWarning;

   	@Column(name = "overlap_cnt_req_tot_danger")
	private Integer overlapCntReqTotDanger;

   	@Column(name = "overlap_cnt_req_tot_obstacle")
	private Integer overlapCntReqTotObstacle;

   	@Column(name = "overlap_cnt_res_tot_use")
	private Boolean overlapCntResTotUse;

   	@Column(name = "overlap_cnt_res_tot_caution")
	private Integer overlapCntResTotCaution;

   	@Column(name = "overlap_cnt_res_tot_warning")
	private Integer overlapCntResTotWarning;

   	@Column(name = "overlap_cnt_res_tot_danger")
	private Integer overlapCntResTotDanger;

   	@Column(name = "overlap_cnt_res_tot_obstacle")
	private Integer overlapCntResTotObstacle;

   	@Column(name = "overlap_len_req_tot_use")
	private Boolean overlapLenReqTotUse;

   	@Column(name = "overlap_len_req_tot_caution")
	private Integer overlapLenReqTotCaution;

   	@Column(name = "overlap_len_req_tot_warning")
	private Integer overlapLenReqTotWarning;

   	@Column(name = "overlap_len_req_tot_danger")
	private Integer overlapLenReqTotDanger;

   	@Column(name = "overlap_len_req_tot_obstacle")
	private Integer overlapLenReqTotObstacle;

   	@Column(name = "overlap_len_res_tot_use")
	private Boolean overlapLenResTotUse;

   	@Column(name = "overlap_len_res_tot_caution")
	private Integer overlapLenResTotCaution;

   	@Column(name = "overlap_len_res_tot_warning")
	private Integer overlapLenResTotWarning;

   	@Column(name = "overlap_len_res_tot_danger")
	private Integer overlapLenResTotDanger;

   	@Column(name = "overlap_len_res_tot_obstacle")
	private Integer overlapLenResTotObstacle;

   	@Column(name = "overlap_cnt_req_per_sec_use")
	private Boolean overlapCntReqPerSecUse;

   	@Column(name = "overlap_cnt_req_per_sec_caution")
	private Integer overlapCntReqPerSecCaution;

   	@Column(name = "overlap_cnt_req_per_sec_warning")
	private Integer overlapCntReqPerSecWarning;

   	@Column(name = "overlap_cnt_req_per_sec_danger")
	private Integer overlapCntReqPerSecDanger;

   	@Column(name = "overlap_cnt_req_per_sec_obstacle")
	private Integer overlapCntReqPerSecObstacle;

   	@Column(name = "overlap_cnt_res_per_sec_use")
	private Boolean overlapCntResPerSecUse;

   	@Column(name = "overlap_cnt_res_per_sec_caution")
	private Integer overlapCntResPerSecCaution;

   	@Column(name = "overlap_cnt_res_per_sec_warning")
	private Integer overlapCntResPerSecWarning;

   	@Column(name = "overlap_cnt_res_per_sec_danger")
	private Integer overlapCntResPerSecDanger;

   	@Column(name = "overlap_cnt_res_per_sec_obstacle")
	private Integer overlapCntResPerSecObstacle;

   	@Column(name = "overlap_len_req_per_sec_use")
	private Boolean overlapLenReqPerSecUse;

   	@Column(name = "overlap_len_req_per_sec_caution")
	private Integer overlapLenReqPerSecCaution;

   	@Column(name = "overlap_len_req_per_sec_warning")
	private Integer overlapLenReqPerSecWarning;

   	@Column(name = "overlap_len_req_per_sec_danger")
	private Integer overlapLenReqPerSecDanger;

   	@Column(name = "overlap_len_req_per_sec_obstacle")
	private Integer overlapLenReqPerSecObstacle;

   	@Column(name = "overlap_len_res_per_sec_use")
	private Boolean overlapLenResPerSecUse;

   	@Column(name = "overlap_len_res_per_sec_caution")
	private Integer overlapLenResPerSecCaution;

   	@Column(name = "overlap_len_res_per_sec_warning")
	private Integer overlapLenResPerSecWarning;

   	@Column(name = "overlap_len_res_per_sec_danger")
	private Integer overlapLenResPerSecDanger;

   	@Column(name = "overlap_len_res_per_sec_obstacle")
	private Integer overlapLenResPerSecObstacle;

   	@Column(name = "overlap_cnt_req_delta_use")
	private Boolean overlapCntReqDeltaUse;

   	@Column(name = "overlap_cnt_req_delta_caution")
	private Integer overlapCntReqDeltaCaution;

   	@Column(name = "overlap_cnt_req_delta_warning")
	private Integer overlapCntReqDeltaWarning;

   	@Column(name = "overlap_cnt_req_delta_danger")
	private Integer overlapCntReqDeltaDanger;

   	@Column(name = "overlap_cnt_req_delta_obstacle")
	private Integer overlapCntReqDeltaObstacle;

   	@Column(name = "overlap_cnt_res_delta_use")
	private Boolean overlapCntResDeltaUse;

   	@Column(name = "overlap_cnt_res_delta_caution")
	private Integer overlapCntResDeltaCaution;

   	@Column(name = "overlap_cnt_res_delta_warning")
	private Integer overlapCntResDeltaWarning;

   	@Column(name = "overlap_cnt_res_delta_danger")
	private Integer overlapCntResDeltaDanger;

   	@Column(name = "overlap_cnt_res_delta_obstacle")
	private Integer overlapCntResDeltaObstacle;

   	@Column(name = "overlap_len_req_delta_use")
	private Boolean overlapLenReqDeltaUse;

   	@Column(name = "overlap_len_req_delta_caution")
	private Integer overlapLenReqDeltaCaution;

   	@Column(name = "overlap_len_req_delta_warning")
	private Integer overlapLenReqDeltaWarning;

   	@Column(name = "overlap_len_req_delta_danger")
	private Integer overlapLenReqDeltaDanger;

   	@Column(name = "overlap_len_req_delta_obstacle")
	private Integer overlapLenReqDeltaObstacle;

   	@Column(name = "overlap_len_res_delta_use")
	private Boolean overlapLenResDeltaUse;

   	@Column(name = "overlap_len_res_delta_caution")
	private Integer overlapLenResDeltaCaution;

   	@Column(name = "overlap_len_res_delta_warning")
	private Integer overlapLenResDeltaWarning;

   	@Column(name = "overlap_len_res_delta_danger")
	private Integer overlapLenResDeltaDanger;

   	@Column(name = "overlap_len_res_delta_obstacle")
	private Integer overlapLenResDeltaObstacle;

   	@Column(name = "overlap_attack_cnt_req_tot_use")
	private Boolean overlapAttackCntReqTotUse;

   	@Column(name = "overlap_attack_cnt_req_tot_caution")
	private Integer overlapAttackCntReqTotCaution;

   	@Column(name = "overlap_attack_cnt_req_tot_warning")
	private Integer overlapAttackCntReqTotWarning;

   	@Column(name = "overlap_attack_cnt_req_tot_danger")
	private Integer overlapAttackCntReqTotDanger;

   	@Column(name = "overlap_attack_cnt_req_tot_obstacle")
	private Integer overlapAttackCntReqTotObstacle;

   	@Column(name = "overlap_attack_cnt_res_tot_use")
	private Boolean overlapAttackCntResTotUse;

   	@Column(name = "overlap_attack_cnt_res_tot_caution")
	private Integer overlapAttackCntResTotCaution;

   	@Column(name = "overlap_attack_cnt_res_tot_warning")
	private Integer overlapAttackCntResTotWarning;

   	@Column(name = "overlap_attack_cnt_res_tot_danger")
	private Integer overlapAttackCntResTotDanger;

   	@Column(name = "overlap_attack_cnt_res_tot_obstacle")
	private Integer overlapAttackCntResTotObstacle;

   	@Column(name = "overlap_attack_len_req_tot_use")
	private Boolean overlapAttackLenReqTotUse;

   	@Column(name = "overlap_attack_len_req_tot_caution")
	private Integer overlapAttackLenReqTotCaution;

   	@Column(name = "overlap_attack_len_req_tot_warning")
	private Integer overlapAttackLenReqTotWarning;

   	@Column(name = "overlap_attack_len_req_tot_danger")
	private Integer overlapAttackLenReqTotDanger;

   	@Column(name = "overlap_attack_len_req_tot_obstacle")
	private Integer overlapAttackLenReqTotObstacle;

   	@Column(name = "overlap_attack_len_res_tot_use")
	private Boolean overlapAttackLenResTotUse;

   	@Column(name = "overlap_attack_len_res_tot_caution")
	private Integer overlapAttackLenResTotCaution;

   	@Column(name = "overlap_attack_len_res_tot_warning")
	private Integer overlapAttackLenResTotWarning;

   	@Column(name = "overlap_attack_len_res_tot_danger")
	private Integer overlapAttackLenResTotDanger;

   	@Column(name = "overlap_attack_len_res_tot_obstacle")
	private Integer overlapAttackLenResTotObstacle;

   	@Column(name = "overlap_attack_cnt_req_per_sec_use")
	private Boolean overlapAttackCntReqPerSecUse;

   	@Column(name = "overlap_attack_cnt_req_per_sec_caution")
	private Integer overlapAttackCntReqPerSecCaution;

   	@Column(name = "overlap_attack_cnt_req_per_sec_warning")
	private Integer overlapAttackCntReqPerSecWarning;

   	@Column(name = "overlap_attack_cnt_req_per_sec_danger")
	private Integer overlapAttackCntReqPerSecDanger;

   	@Column(name = "overlap_attack_cnt_req_per_sec_obstacle")
	private Integer overlapAttackCntReqPerSecObstacle;

   	@Column(name = "overlap_attack_cnt_res_per_sec_use")
	private Boolean overlapAttackCntResPerSecUse;

   	@Column(name = "overlap_attack_cnt_res_per_sec_caution")
	private Integer overlapAttackCntResPerSecCaution;

   	@Column(name = "overlap_attack_cnt_res_per_sec_warning")
	private Integer overlapAttackCntResPerSecWarning;

   	@Column(name = "overlap_attack_cnt_res_per_sec_danger")
	private Integer overlapAttackCntResPerSecDanger;

   	@Column(name = "overlap_attack_cnt_res_per_sec_obstacle")
	private Integer overlapAttackCntResPerSecObstacle;

   	@Column(name = "overlap_attack_len_req_per_sec_use")
	private Boolean overlapAttackLenReqPerSecUse;

   	@Column(name = "overlap_attack_len_req_per_sec_caution")
	private Integer overlapAttackLenReqPerSecCaution;

   	@Column(name = "overlap_attack_len_req_per_sec_warning")
	private Integer overlapAttackLenReqPerSecWarning;

   	@Column(name = "overlap_attack_len_req_per_sec_danger")
	private Integer overlapAttackLenReqPerSecDanger;

   	@Column(name = "overlap_attack_len_req_per_sec_obstacle")
	private Integer overlapAttackLenReqPerSecObstacle;

   	@Column(name = "overlap_attack_len_res_per_sec_use")
	private Boolean overlapAttackLenResPerSecUse;

   	@Column(name = "overlap_attack_len_res_per_sec_caution")
	private Integer overlapAttackLenResPerSecCaution;

   	@Column(name = "overlap_attack_len_res_per_sec_warning")
	private Integer overlapAttackLenResPerSecWarning;

   	@Column(name = "overlap_attack_len_res_per_sec_danger")
	private Integer overlapAttackLenResPerSecDanger;

   	@Column(name = "overlap_attack_len_res_per_sec_obstacle")
	private Integer overlapAttackLenResPerSecObstacle;

   	@Column(name = "overlap_attack_cnt_req_delta_use")
	private Boolean overlapAttackCntReqDeltaUse;

   	@Column(name = "overlap_attack_cnt_req_delta_caution")
	private Integer overlapAttackCntReqDeltaCaution;

   	@Column(name = "overlap_attack_cnt_req_delta_warning")
	private Integer overlapAttackCntReqDeltaWarning;

   	@Column(name = "overlap_attack_cnt_req_delta_danger")
	private Integer overlapAttackCntReqDeltaDanger;

   	@Column(name = "overlap_attack_cnt_req_delta_obstacle")
	private Integer overlapAttackCntReqDeltaObstacle;

   	@Column(name = "overlap_attack_cnt_res_delta_use")
	private Boolean overlapAttackCntResDeltaUse;

   	@Column(name = "overlap_attack_cnt_res_delta_caution")
	private Integer overlapAttackCntResDeltaCaution;

   	@Column(name = "overlap_attack_cnt_res_delta_warning")
	private Integer overlapAttackCntResDeltaWarning;

   	@Column(name = "overlap_attack_cnt_res_delta_danger")
	private Integer overlapAttackCntResDeltaDanger;

   	@Column(name = "overlap_attack_cnt_res_delta_obstacle")
	private Integer overlapAttackCntResDeltaObstacle;

   	@Column(name = "overlap_attack_len_req_delta_use")
	private Boolean overlapAttackLenReqDeltaUse;

   	@Column(name = "overlap_attack_len_req_delta_caution")
	private Integer overlapAttackLenReqDeltaCaution;

   	@Column(name = "overlap_attack_len_req_delta_warning")
	private Integer overlapAttackLenReqDeltaWarning;

   	@Column(name = "overlap_attack_len_req_delta_danger")
	private Integer overlapAttackLenReqDeltaDanger;

   	@Column(name = "overlap_attack_len_req_delta_obstacle")
	private Integer overlapAttackLenReqDeltaObstacle;

   	@Column(name = "overlap_attack_len_res_delta_use")
	private Boolean overlapAttackLenResDeltaUse;

   	@Column(name = "overlap_attack_len_res_delta_caution")
	private Integer overlapAttackLenResDeltaCaution;

   	@Column(name = "overlap_attack_len_res_delta_warning")
	private Integer overlapAttackLenResDeltaWarning;

   	@Column(name = "overlap_attack_len_res_delta_danger")
	private Integer overlapAttackLenResDeltaDanger;

   	@Column(name = "overlap_attack_len_res_delta_obstacle")
	private Integer overlapAttackLenResDeltaObstacle;

   	@Column(name = "zero_win_probe_cnt_req_tot_use")
	private Boolean zeroWinProbeCntReqTotUse;

   	@Column(name = "zero_win_probe_cnt_req_tot_caution")
	private Integer zeroWinProbeCntReqTotCaution;

   	@Column(name = "zero_win_probe_cnt_req_tot_warning")
	private Integer zeroWinProbeCntReqTotWarning;

   	@Column(name = "zero_win_probe_cnt_req_tot_danger")
	private Integer zeroWinProbeCntReqTotDanger;

   	@Column(name = "zero_win_probe_cnt_req_tot_obstacle")
	private Integer zeroWinProbeCntReqTotObstacle;

   	@Column(name = "zero_win_probe_cnt_res_tot_use")
	private Boolean zeroWinProbeCntResTotUse;

   	@Column(name = "zero_win_probe_cnt_res_tot_caution")
	private Integer zeroWinProbeCntResTotCaution;

   	@Column(name = "zero_win_probe_cnt_res_tot_warning")
	private Integer zeroWinProbeCntResTotWarning;

   	@Column(name = "zero_win_probe_cnt_res_tot_danger")
	private Integer zeroWinProbeCntResTotDanger;

   	@Column(name = "zero_win_probe_cnt_res_tot_obstacle")
	private Integer zeroWinProbeCntResTotObstacle;

   	@Column(name = "zero_win_probe_len_req_tot_use")
	private Boolean zeroWinProbeLenReqTotUse;

   	@Column(name = "zero_win_probe_len_req_tot_caution")
	private Integer zeroWinProbeLenReqTotCaution;

   	@Column(name = "zero_win_probe_len_req_tot_warning")
	private Integer zeroWinProbeLenReqTotWarning;

   	@Column(name = "zero_win_probe_len_req_tot_danger")
	private Integer zeroWinProbeLenReqTotDanger;

   	@Column(name = "zero_win_probe_len_req_tot_obstacle")
	private Integer zeroWinProbeLenReqTotObstacle;

   	@Column(name = "zero_win_probe_len_res_tot_use")
	private Boolean zeroWinProbeLenResTotUse;

   	@Column(name = "zero_win_probe_len_res_tot_caution")
	private Integer zeroWinProbeLenResTotCaution;

   	@Column(name = "zero_win_probe_len_res_tot_warning")
	private Integer zeroWinProbeLenResTotWarning;

   	@Column(name = "zero_win_probe_len_res_tot_danger")
	private Integer zeroWinProbeLenResTotDanger;

   	@Column(name = "zero_win_probe_len_res_tot_obstacle")
	private Integer zeroWinProbeLenResTotObstacle;

   	@Column(name = "zero_win_probe_cnt_req_per_sec_use")
	private Boolean zeroWinProbeCntReqPerSecUse;

   	@Column(name = "zero_win_probe_cnt_req_per_sec_caution")
	private Integer zeroWinProbeCntReqPerSecCaution;

   	@Column(name = "zero_win_probe_cnt_req_per_sec_warning")
	private Integer zeroWinProbeCntReqPerSecWarning;

   	@Column(name = "zero_win_probe_cnt_req_per_sec_danger")
	private Integer zeroWinProbeCntReqPerSecDanger;

   	@Column(name = "zero_win_probe_cnt_req_per_sec_obstacle")
	private Integer zeroWinProbeCntReqPerSecObstacle;

   	@Column(name = "zero_win_probe_cnt_res_per_sec_use")
	private Boolean zeroWinProbeCntResPerSecUse;

   	@Column(name = "zero_win_probe_cnt_res_per_sec_caution")
	private Integer zeroWinProbeCntResPerSecCaution;

   	@Column(name = "zero_win_probe_cnt_res_per_sec_warning")
	private Integer zeroWinProbeCntResPerSecWarning;

   	@Column(name = "zero_win_probe_cnt_res_per_sec_danger")
	private Integer zeroWinProbeCntResPerSecDanger;

   	@Column(name = "zero_win_probe_cnt_res_per_sec_obstacle")
	private Integer zeroWinProbeCntResPerSecObstacle;

   	@Column(name = "zero_win_probe_len_req_per_sec_use")
	private Boolean zeroWinProbeLenReqPerSecUse;

   	@Column(name = "zero_win_probe_len_req_per_sec_caution")
	private Integer zeroWinProbeLenReqPerSecCaution;

   	@Column(name = "zero_win_probe_len_req_per_sec_warning")
	private Integer zeroWinProbeLenReqPerSecWarning;

   	@Column(name = "zero_win_probe_len_req_per_sec_danger")
	private Integer zeroWinProbeLenReqPerSecDanger;

   	@Column(name = "zero_win_probe_len_req_per_sec_obstacle")
	private Integer zeroWinProbeLenReqPerSecObstacle;

   	@Column(name = "zero_win_probe_len_res_per_sec_use")
	private Boolean zeroWinProbeLenResPerSecUse;

   	@Column(name = "zero_win_probe_len_res_per_sec_caution")
	private Integer zeroWinProbeLenResPerSecCaution;

   	@Column(name = "zero_win_probe_len_res_per_sec_warning")
	private Integer zeroWinProbeLenResPerSecWarning;

   	@Column(name = "zero_win_probe_len_res_per_sec_danger")
	private Integer zeroWinProbeLenResPerSecDanger;

   	@Column(name = "zero_win_probe_len_res_per_sec_obstacle")
	private Integer zeroWinProbeLenResPerSecObstacle;

   	@Column(name = "zero_win_probe_cnt_req_delta_use")
	private Boolean zeroWinProbeCntReqDeltaUse;

   	@Column(name = "zero_win_probe_cnt_req_delta_caution")
	private Integer zeroWinProbeCntReqDeltaCaution;

   	@Column(name = "zero_win_probe_cnt_req_delta_warning")
	private Integer zeroWinProbeCntReqDeltaWarning;

   	@Column(name = "zero_win_probe_cnt_req_delta_danger")
	private Integer zeroWinProbeCntReqDeltaDanger;

   	@Column(name = "zero_win_probe_cnt_req_delta_obstacle")
	private Integer zeroWinProbeCntReqDeltaObstacle;

   	@Column(name = "zero_win_probe_cnt_res_delta_use")
	private Boolean zeroWinProbeCntResDeltaUse;

   	@Column(name = "zero_win_probe_cnt_res_delta_caution")
	private Integer zeroWinProbeCntResDeltaCaution;

   	@Column(name = "zero_win_probe_cnt_res_delta_warning")
	private Integer zeroWinProbeCntResDeltaWarning;

   	@Column(name = "zero_win_probe_cnt_res_delta_danger")
	private Integer zeroWinProbeCntResDeltaDanger;

   	@Column(name = "zero_win_probe_cnt_res_delta_obstacle")
	private Integer zeroWinProbeCntResDeltaObstacle;

   	@Column(name = "zero_win_probe_len_req_delta_use")
	private Boolean zeroWinProbeLenReqDeltaUse;

   	@Column(name = "zero_win_probe_len_req_delta_caution")
	private Integer zeroWinProbeLenReqDeltaCaution;

   	@Column(name = "zero_win_probe_len_req_delta_warning")
	private Integer zeroWinProbeLenReqDeltaWarning;

   	@Column(name = "zero_win_probe_len_req_delta_danger")
	private Integer zeroWinProbeLenReqDeltaDanger;

   	@Column(name = "zero_win_probe_len_req_delta_obstacle")
	private Integer zeroWinProbeLenReqDeltaObstacle;

   	@Column(name = "zero_win_probe_len_res_delta_use")
	private Boolean zeroWinProbeLenResDeltaUse;

   	@Column(name = "zero_win_probe_len_res_delta_caution")
	private Integer zeroWinProbeLenResDeltaCaution;

   	@Column(name = "zero_win_probe_len_res_delta_warning")
	private Integer zeroWinProbeLenResDeltaWarning;

   	@Column(name = "zero_win_probe_len_res_delta_danger")
	private Integer zeroWinProbeLenResDeltaDanger;

   	@Column(name = "zero_win_probe_len_res_delta_obstacle")
	private Integer zeroWinProbeLenResDeltaObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_req_tot_use")
	private Boolean zeroWinProbeAckCntReqTotUse;

   	@Column(name = "zero_win_probe_ack_cnt_req_tot_caution")
	private Integer zeroWinProbeAckCntReqTotCaution;

   	@Column(name = "zero_win_probe_ack_cnt_req_tot_warning")
	private Integer zeroWinProbeAckCntReqTotWarning;

   	@Column(name = "zero_win_probe_ack_cnt_req_tot_danger")
	private Integer zeroWinProbeAckCntReqTotDanger;

   	@Column(name = "zero_win_probe_ack_cnt_req_tot_obstacle")
	private Integer zeroWinProbeAckCntReqTotObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_res_tot_use")
	private Boolean zeroWinProbeAckCntResTotUse;

   	@Column(name = "zero_win_probe_ack_cnt_res_tot_caution")
	private Integer zeroWinProbeAckCntResTotCaution;

   	@Column(name = "zero_win_probe_ack_cnt_res_tot_warning")
	private Integer zeroWinProbeAckCntResTotWarning;

   	@Column(name = "zero_win_probe_ack_cnt_res_tot_danger")
	private Integer zeroWinProbeAckCntResTotDanger;

   	@Column(name = "zero_win_probe_ack_cnt_res_tot_obstacle")
	private Integer zeroWinProbeAckCntResTotObstacle;

   	@Column(name = "zero_win_probe_ack_len_req_tot_use")
	private Boolean zeroWinProbeAckLenReqTotUse;

   	@Column(name = "zero_win_probe_ack_len_req_tot_caution")
	private Integer zeroWinProbeAckLenReqTotCaution;

   	@Column(name = "zero_win_probe_ack_len_req_tot_warning")
	private Integer zeroWinProbeAckLenReqTotWarning;

   	@Column(name = "zero_win_probe_ack_len_req_tot_danger")
	private Integer zeroWinProbeAckLenReqTotDanger;

   	@Column(name = "zero_win_probe_ack_len_req_tot_obstacle")
	private Integer zeroWinProbeAckLenReqTotObstacle;

   	@Column(name = "zero_win_probe_ack_len_res_tot_use")
	private Boolean zeroWinProbeAckLenResTotUse;

   	@Column(name = "zero_win_probe_ack_len_res_tot_caution")
	private Integer zeroWinProbeAckLenResTotCaution;

   	@Column(name = "zero_win_probe_ack_len_res_tot_warning")
	private Integer zeroWinProbeAckLenResTotWarning;

   	@Column(name = "zero_win_probe_ack_len_res_tot_danger")
	private Integer zeroWinProbeAckLenResTotDanger;

   	@Column(name = "zero_win_probe_ack_len_res_tot_obstacle")
	private Integer zeroWinProbeAckLenResTotObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_req_per_sec_use")
	private Boolean zeroWinProbeAckCntReqPerSecUse;

   	@Column(name = "zero_win_probe_ack_cnt_req_per_sec_caution")
	private Integer zeroWinProbeAckCntReqPerSecCaution;

   	@Column(name = "zero_win_probe_ack_cnt_req_per_sec_warning")
	private Integer zeroWinProbeAckCntReqPerSecWarning;

   	@Column(name = "zero_win_probe_ack_cnt_req_per_sec_danger")
	private Integer zeroWinProbeAckCntReqPerSecDanger;

   	@Column(name = "zero_win_probe_ack_cnt_req_per_sec_obstacle")
	private Integer zeroWinProbeAckCntReqPerSecObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_res_per_sec_use")
	private Boolean zeroWinProbeAckCntResPerSecUse;

   	@Column(name = "zero_win_probe_ack_cnt_res_per_sec_caution")
	private Integer zeroWinProbeAckCntResPerSecCaution;

   	@Column(name = "zero_win_probe_ack_cnt_res_per_sec_warning")
	private Integer zeroWinProbeAckCntResPerSecWarning;

   	@Column(name = "zero_win_probe_ack_cnt_res_per_sec_danger")
	private Integer zeroWinProbeAckCntResPerSecDanger;

   	@Column(name = "zero_win_probe_ack_cnt_res_per_sec_obstacle")
	private Integer zeroWinProbeAckCntResPerSecObstacle;

   	@Column(name = "zero_win_probe_ack_len_req_per_sec_use")
	private Boolean zeroWinProbeAckLenReqPerSecUse;

   	@Column(name = "zero_win_probe_ack_len_req_per_sec_caution")
	private Integer zeroWinProbeAckLenReqPerSecCaution;

   	@Column(name = "zero_win_probe_ack_len_req_per_sec_warning")
	private Integer zeroWinProbeAckLenReqPerSecWarning;

   	@Column(name = "zero_win_probe_ack_len_req_per_sec_danger")
	private Integer zeroWinProbeAckLenReqPerSecDanger;

   	@Column(name = "zero_win_probe_ack_len_req_per_sec_obstacle")
	private Integer zeroWinProbeAckLenReqPerSecObstacle;

   	@Column(name = "zero_win_probe_ack_len_res_per_sec_use")
	private Boolean zeroWinProbeAckLenResPerSecUse;

   	@Column(name = "zero_win_probe_ack_len_res_per_sec_caution")
	private Integer zeroWinProbeAckLenResPerSecCaution;

   	@Column(name = "zero_win_probe_ack_len_res_per_sec_warning")
	private Integer zeroWinProbeAckLenResPerSecWarning;

   	@Column(name = "zero_win_probe_ack_len_res_per_sec_danger")
	private Integer zeroWinProbeAckLenResPerSecDanger;

   	@Column(name = "zero_win_probe_ack_len_res_per_sec_obstacle")
	private Integer zeroWinProbeAckLenResPerSecObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_req_delta_use")
	private Boolean zeroWinProbeAckCntReqDeltaUse;

   	@Column(name = "zero_win_probe_ack_cnt_req_delta_caution")
	private Integer zeroWinProbeAckCntReqDeltaCaution;

   	@Column(name = "zero_win_probe_ack_cnt_req_delta_warning")
	private Integer zeroWinProbeAckCntReqDeltaWarning;

   	@Column(name = "zero_win_probe_ack_cnt_req_delta_danger")
	private Integer zeroWinProbeAckCntReqDeltaDanger;

   	@Column(name = "zero_win_probe_ack_cnt_req_delta_obstacle")
	private Integer zeroWinProbeAckCntReqDeltaObstacle;

   	@Column(name = "zero_win_probe_ack_cnt_res_delta_use")
	private Boolean zeroWinProbeAckCntResDeltaUse;

   	@Column(name = "zero_win_probe_ack_cnt_res_delta_caution")
	private Integer zeroWinProbeAckCntResDeltaCaution;

   	@Column(name = "zero_win_probe_ack_cnt_res_delta_warning")
	private Integer zeroWinProbeAckCntResDeltaWarning;

   	@Column(name = "zero_win_probe_ack_cnt_res_delta_danger")
	private Integer zeroWinProbeAckCntResDeltaDanger;

   	@Column(name = "zero_win_probe_ack_cnt_res_delta_obstacle")
	private Integer zeroWinProbeAckCntResDeltaObstacle;

   	@Column(name = "zero_win_probe_ack_len_req_delta_use")
	private Boolean zeroWinProbeAckLenReqDeltaUse;

   	@Column(name = "zero_win_probe_ack_len_req_delta_caution")
	private Integer zeroWinProbeAckLenReqDeltaCaution;

   	@Column(name = "zero_win_probe_ack_len_req_delta_warning")
	private Integer zeroWinProbeAckLenReqDeltaWarning;

   	@Column(name = "zero_win_probe_ack_len_req_delta_danger")
	private Integer zeroWinProbeAckLenReqDeltaDanger;

   	@Column(name = "zero_win_probe_ack_len_req_delta_obstacle")
	private Integer zeroWinProbeAckLenReqDeltaObstacle;

   	@Column(name = "zero_win_probe_ack_len_res_delta_use")
	private Boolean zeroWinProbeAckLenResDeltaUse;

   	@Column(name = "zero_win_probe_ack_len_res_delta_caution")
	private Integer zeroWinProbeAckLenResDeltaCaution;

   	@Column(name = "zero_win_probe_ack_len_res_delta_warning")
	private Integer zeroWinProbeAckLenResDeltaWarning;

   	@Column(name = "zero_win_probe_ack_len_res_delta_danger")
	private Integer zeroWinProbeAckLenResDeltaDanger;

   	@Column(name = "zero_win_probe_ack_len_res_delta_obstacle")
	private Integer zeroWinProbeAckLenResDeltaObstacle;

   	@Column(name = "keep_alive_cnt_req_tot_use")
	private Boolean keepAliveCntReqTotUse;

   	@Column(name = "keep_alive_cnt_req_tot_caution")
	private Integer keepAliveCntReqTotCaution;

   	@Column(name = "keep_alive_cnt_req_tot_warning")
	private Integer keepAliveCntReqTotWarning;

   	@Column(name = "keep_alive_cnt_req_tot_danger")
	private Integer keepAliveCntReqTotDanger;

   	@Column(name = "keep_alive_cnt_req_tot_obstacle")
	private Integer keepAliveCntReqTotObstacle;

   	@Column(name = "keep_alive_cnt_res_tot_use")
	private Boolean keepAliveCntResTotUse;

   	@Column(name = "keep_alive_cnt_res_tot_caution")
	private Integer keepAliveCntResTotCaution;

   	@Column(name = "keep_alive_cnt_res_tot_warning")
	private Integer keepAliveCntResTotWarning;

   	@Column(name = "keep_alive_cnt_res_tot_danger")
	private Integer keepAliveCntResTotDanger;

   	@Column(name = "keep_alive_cnt_res_tot_obstacle")
	private Integer keepAliveCntResTotObstacle;

   	@Column(name = "keep_alive_len_req_tot_use")
	private Boolean keepAliveLenReqTotUse;

   	@Column(name = "keep_alive_len_req_tot_caution")
	private Integer keepAliveLenReqTotCaution;

   	@Column(name = "keep_alive_len_req_tot_warning")
	private Integer keepAliveLenReqTotWarning;

   	@Column(name = "keep_alive_len_req_tot_danger")
	private Integer keepAliveLenReqTotDanger;

   	@Column(name = "keep_alive_len_req_tot_obstacle")
	private Integer keepAliveLenReqTotObstacle;

   	@Column(name = "keep_alive_len_res_tot_use")
	private Boolean keepAliveLenResTotUse;

   	@Column(name = "keep_alive_len_res_tot_caution")
	private Integer keepAliveLenResTotCaution;

   	@Column(name = "keep_alive_len_res_tot_warning")
	private Integer keepAliveLenResTotWarning;

   	@Column(name = "keep_alive_len_res_tot_danger")
	private Integer keepAliveLenResTotDanger;

   	@Column(name = "keep_alive_len_res_tot_obstacle")
	private Integer keepAliveLenResTotObstacle;

   	@Column(name = "keep_alive_cnt_req_per_sec_use")
	private Boolean keepAliveCntReqPerSecUse;

   	@Column(name = "keep_alive_cnt_req_per_sec_caution")
	private Integer keepAliveCntReqPerSecCaution;

   	@Column(name = "keep_alive_cnt_req_per_sec_warning")
	private Integer keepAliveCntReqPerSecWarning;

   	@Column(name = "keep_alive_cnt_req_per_sec_danger")
	private Integer keepAliveCntReqPerSecDanger;

   	@Column(name = "keep_alive_cnt_req_per_sec_obstacle")
	private Integer keepAliveCntReqPerSecObstacle;

   	@Column(name = "keep_alive_cnt_res_per_sec_use")
	private Boolean keepAliveCntResPerSecUse;

   	@Column(name = "keep_alive_cnt_res_per_sec_caution")
	private Integer keepAliveCntResPerSecCaution;

   	@Column(name = "keep_alive_cnt_res_per_sec_warning")
	private Integer keepAliveCntResPerSecWarning;

   	@Column(name = "keep_alive_cnt_res_per_sec_danger")
	private Integer keepAliveCntResPerSecDanger;

   	@Column(name = "keep_alive_cnt_res_per_sec_obstacle")
	private Integer keepAliveCntResPerSecObstacle;

   	@Column(name = "keep_alive_len_req_per_sec_use")
	private Boolean keepAliveLenReqPerSecUse;

   	@Column(name = "keep_alive_len_req_per_sec_caution")
	private Integer keepAliveLenReqPerSecCaution;

   	@Column(name = "keep_alive_len_req_per_sec_warning")
	private Integer keepAliveLenReqPerSecWarning;

   	@Column(name = "keep_alive_len_req_per_sec_danger")
	private Integer keepAliveLenReqPerSecDanger;

   	@Column(name = "keep_alive_len_req_per_sec_obstacle")
	private Integer keepAliveLenReqPerSecObstacle;

   	@Column(name = "keep_alive_len_res_per_sec_use")
	private Boolean keepAliveLenResPerSecUse;

   	@Column(name = "keep_alive_len_res_per_sec_caution")
	private Integer keepAliveLenResPerSecCaution;

   	@Column(name = "keep_alive_len_res_per_sec_warning")
	private Integer keepAliveLenResPerSecWarning;

   	@Column(name = "keep_alive_len_res_per_sec_danger")
	private Integer keepAliveLenResPerSecDanger;

   	@Column(name = "keep_alive_len_res_per_sec_obstacle")
	private Integer keepAliveLenResPerSecObstacle;

   	@Column(name = "keep_alive_cnt_req_delta_use")
	private Boolean keepAliveCntReqDeltaUse;

   	@Column(name = "keep_alive_cnt_req_delta_caution")
	private Integer keepAliveCntReqDeltaCaution;

   	@Column(name = "keep_alive_cnt_req_delta_warning")
	private Integer keepAliveCntReqDeltaWarning;

   	@Column(name = "keep_alive_cnt_req_delta_danger")
	private Integer keepAliveCntReqDeltaDanger;

   	@Column(name = "keep_alive_cnt_req_delta_obstacle")
	private Integer keepAliveCntReqDeltaObstacle;

   	@Column(name = "keep_alive_cnt_res_delta_use")
	private Boolean keepAliveCntResDeltaUse;

   	@Column(name = "keep_alive_cnt_res_delta_caution")
	private Integer keepAliveCntResDeltaCaution;

   	@Column(name = "keep_alive_cnt_res_delta_warning")
	private Integer keepAliveCntResDeltaWarning;

   	@Column(name = "keep_alive_cnt_res_delta_danger")
	private Integer keepAliveCntResDeltaDanger;

   	@Column(name = "keep_alive_cnt_res_delta_obstacle")
	private Integer keepAliveCntResDeltaObstacle;

   	@Column(name = "keep_alive_len_req_delta_use")
	private Boolean keepAliveLenReqDeltaUse;

   	@Column(name = "keep_alive_len_req_delta_caution")
	private Integer keepAliveLenReqDeltaCaution;

   	@Column(name = "keep_alive_len_req_delta_warning")
	private Integer keepAliveLenReqDeltaWarning;

   	@Column(name = "keep_alive_len_req_delta_danger")
	private Integer keepAliveLenReqDeltaDanger;

   	@Column(name = "keep_alive_len_req_delta_obstacle")
	private Integer keepAliveLenReqDeltaObstacle;

   	@Column(name = "keep_alive_len_res_delta_use")
	private Boolean keepAliveLenResDeltaUse;

   	@Column(name = "keep_alive_len_res_delta_caution")
	private Integer keepAliveLenResDeltaCaution;

   	@Column(name = "keep_alive_len_res_delta_warning")
	private Integer keepAliveLenResDeltaWarning;

   	@Column(name = "keep_alive_len_res_delta_danger")
	private Integer keepAliveLenResDeltaDanger;

   	@Column(name = "keep_alive_len_res_delta_obstacle")
	private Integer keepAliveLenResDeltaObstacle;

   	@Column(name = "keep_alive_ack_cnt_req_tot_use")
	private Boolean keepAliveAckCntReqTotUse;

   	@Column(name = "keep_alive_ack_cnt_req_tot_caution")
	private Integer keepAliveAckCntReqTotCaution;

   	@Column(name = "keep_alive_ack_cnt_req_tot_warning")
	private Integer keepAliveAckCntReqTotWarning;

   	@Column(name = "keep_alive_ack_cnt_req_tot_danger")
	private Integer keepAliveAckCntReqTotDanger;

   	@Column(name = "keep_alive_ack_cnt_req_tot_obstacle")
	private Integer keepAliveAckCntReqTotObstacle;

   	@Column(name = "keep_alive_ack_cnt_res_tot_use")
	private Boolean keepAliveAckCntResTotUse;

   	@Column(name = "keep_alive_ack_cnt_res_tot_caution")
	private Integer keepAliveAckCntResTotCaution;

   	@Column(name = "keep_alive_ack_cnt_res_tot_warning")
	private Integer keepAliveAckCntResTotWarning;

   	@Column(name = "keep_alive_ack_cnt_res_tot_danger")
	private Integer keepAliveAckCntResTotDanger;

   	@Column(name = "keep_alive_ack_cnt_res_tot_obstacle")
	private Integer keepAliveAckCntResTotObstacle;

   	@Column(name = "keep_alive_ack_len_req_tot_use")
	private Boolean keepAliveAckLenReqTotUse;

   	@Column(name = "keep_alive_ack_len_req_tot_caution")
	private Integer keepAliveAckLenReqTotCaution;

   	@Column(name = "keep_alive_ack_len_req_tot_warning")
	private Integer keepAliveAckLenReqTotWarning;

   	@Column(name = "keep_alive_ack_len_req_tot_danger")
	private Integer keepAliveAckLenReqTotDanger;

   	@Column(name = "keep_alive_ack_len_req_tot_obstacle")
	private Integer keepAliveAckLenReqTotObstacle;

   	@Column(name = "keep_alive_ack_len_res_tot_use")
	private Boolean keepAliveAckLenResTotUse;

   	@Column(name = "keep_alive_ack_len_res_tot_caution")
	private Integer keepAliveAckLenResTotCaution;

   	@Column(name = "keep_alive_ack_len_res_tot_warning")
	private Integer keepAliveAckLenResTotWarning;

   	@Column(name = "keep_alive_ack_len_res_tot_danger")
	private Integer keepAliveAckLenResTotDanger;

   	@Column(name = "keep_alive_ack_len_res_tot_obstacle")
	private Integer keepAliveAckLenResTotObstacle;

   	@Column(name = "keep_alive_ack_cnt_req_per_sec_use")
	private Boolean keepAliveAckCntReqPerSecUse;

   	@Column(name = "keep_alive_ack_cnt_req_per_sec_caution")
	private Integer keepAliveAckCntReqPerSecCaution;

   	@Column(name = "keep_alive_ack_cnt_req_per_sec_warning")
	private Integer keepAliveAckCntReqPerSecWarning;

   	@Column(name = "keep_alive_ack_cnt_req_per_sec_danger")
	private Integer keepAliveAckCntReqPerSecDanger;

   	@Column(name = "keep_alive_ack_cnt_req_per_sec_obstacle")
	private Integer keepAliveAckCntReqPerSecObstacle;

   	@Column(name = "keep_alive_ack_cnt_res_per_sec_use")
	private Boolean keepAliveAckCntResPerSecUse;

   	@Column(name = "keep_alive_ack_cnt_res_per_sec_caution")
	private Integer keepAliveAckCntResPerSecCaution;

   	@Column(name = "keep_alive_ack_cnt_res_per_sec_warning")
	private Integer keepAliveAckCntResPerSecWarning;

   	@Column(name = "keep_alive_ack_cnt_res_per_sec_danger")
	private Integer keepAliveAckCntResPerSecDanger;

   	@Column(name = "keep_alive_ack_cnt_res_per_sec_obstacle")
	private Integer keepAliveAckCntResPerSecObstacle;

   	@Column(name = "keep_alive_ack_len_req_per_sec_use")
	private Boolean keepAliveAckLenReqPerSecUse;

   	@Column(name = "keep_alive_ack_len_req_per_sec_caution")
	private Integer keepAliveAckLenReqPerSecCaution;

   	@Column(name = "keep_alive_ack_len_req_per_sec_warning")
	private Integer keepAliveAckLenReqPerSecWarning;

   	@Column(name = "keep_alive_ack_len_req_per_sec_danger")
	private Integer keepAliveAckLenReqPerSecDanger;

   	@Column(name = "keep_alive_ack_len_req_per_sec_obstacle")
	private Integer keepAliveAckLenReqPerSecObstacle;

   	@Column(name = "keep_alive_ack_len_res_per_sec_use")
	private Boolean keepAliveAckLenResPerSecUse;

   	@Column(name = "keep_alive_ack_len_res_per_sec_caution")
	private Integer keepAliveAckLenResPerSecCaution;

   	@Column(name = "keep_alive_ack_len_res_per_sec_warning")
	private Integer keepAliveAckLenResPerSecWarning;

   	@Column(name = "keep_alive_ack_len_res_per_sec_danger")
	private Integer keepAliveAckLenResPerSecDanger;

   	@Column(name = "keep_alive_ack_len_res_per_sec_obstacle")
	private Integer keepAliveAckLenResPerSecObstacle;

   	@Column(name = "keep_alive_ack_cnt_req_delta_use")
	private Boolean keepAliveAckCntReqDeltaUse;

   	@Column(name = "keep_alive_ack_cnt_req_delta_caution")
	private Integer keepAliveAckCntReqDeltaCaution;

   	@Column(name = "keep_alive_ack_cnt_req_delta_warning")
	private Integer keepAliveAckCntReqDeltaWarning;

   	@Column(name = "keep_alive_ack_cnt_req_delta_danger")
	private Integer keepAliveAckCntReqDeltaDanger;

   	@Column(name = "keep_alive_ack_cnt_req_delta_obstacle")
	private Integer keepAliveAckCntReqDeltaObstacle;

   	@Column(name = "keep_alive_ack_cnt_res_delta_use")
	private Boolean keepAliveAckCntResDeltaUse;

   	@Column(name = "keep_alive_ack_cnt_res_delta_caution")
	private Integer keepAliveAckCntResDeltaCaution;

   	@Column(name = "keep_alive_ack_cnt_res_delta_warning")
	private Integer keepAliveAckCntResDeltaWarning;

   	@Column(name = "keep_alive_ack_cnt_res_delta_danger")
	private Integer keepAliveAckCntResDeltaDanger;

   	@Column(name = "keep_alive_ack_cnt_res_delta_obstacle")
	private Integer keepAliveAckCntResDeltaObstacle;

   	@Column(name = "keep_alive_ack_len_req_delta_use")
	private Boolean keepAliveAckLenReqDeltaUse;

   	@Column(name = "keep_alive_ack_len_req_delta_caution")
	private Integer keepAliveAckLenReqDeltaCaution;

   	@Column(name = "keep_alive_ack_len_req_delta_warning")
	private Integer keepAliveAckLenReqDeltaWarning;

   	@Column(name = "keep_alive_ack_len_req_delta_danger")
	private Integer keepAliveAckLenReqDeltaDanger;

   	@Column(name = "keep_alive_ack_len_req_delta_obstacle")
	private Integer keepAliveAckLenReqDeltaObstacle;

   	@Column(name = "keep_alive_ack_len_res_delta_use")
	private Boolean keepAliveAckLenResDeltaUse;

   	@Column(name = "keep_alive_ack_len_res_delta_caution")
	private Integer keepAliveAckLenResDeltaCaution;

   	@Column(name = "keep_alive_ack_len_res_delta_warning")
	private Integer keepAliveAckLenResDeltaWarning;

   	@Column(name = "keep_alive_ack_len_res_delta_danger")
	private Integer keepAliveAckLenResDeltaDanger;

   	@Column(name = "keep_alive_ack_len_res_delta_obstacle")
	private Integer keepAliveAckLenResDeltaObstacle;


}
