package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="tbl_info_threshold_l4_tcp_error")
public class InfoThresholdL4TcpError {

	@Id
	@Column(name="id", unique=true, nullable=false)
	private Integer id;

	@Column(name="deleted")
	private Boolean deleted;

	@Column(name="retransmission_cnt_req_tot_use")
	private Boolean retransmissionCntReqTotUse;
	@Column(name="retransmission_cnt_req_tot_level1")
	private Integer retransmissionCntReqTotLevel1;
	@Column(name="retransmission_cnt_req_tot_level2")
	private Integer retransmissionCntReqTotLevel2;
	@Column(name="retransmission_cnt_req_tot_level3")
	private Integer retransmissionCntReqTotLevel3;
	@Column(name="retransmission_cnt_req_tot_level4")
	private Integer retransmissionCntReqTotLevel4;
	@Column(name="retransmission_cnt_req_tot_level5")
	private Integer retransmissionCntReqTotLevel5;

	@Column(name="retransmission_cnt_res_tot_use")
	private Boolean retransmissionCntResTotUse;
	@Column(name="retransmission_cnt_res_tot_level1")
	private Integer retransmissionCntResTotLevel1;
	@Column(name="retransmission_cnt_res_tot_level2")
	private Integer retransmissionCntResTotLevel2;
	@Column(name="retransmission_cnt_res_tot_level3")
	private Integer retransmissionCntResTotLevel3;
	@Column(name="retransmission_cnt_res_tot_level4")
	private Integer retransmissionCntResTotLevel4;
	@Column(name="retransmission_cnt_res_tot_level5")
	private Integer retransmissionCntResTotLevel5;

	@Column(name="retransmission_len_req_tot_use")
	private Boolean retransmissionLenReqTotUse;
	@Column(name="retransmission_len_req_tot_level1")
	private Integer retransmissionLenReqTotLevel1;
	@Column(name="retransmission_len_req_tot_level2")
	private Integer retransmissionLenReqTotLevel2;
	@Column(name="retransmission_len_req_tot_level3")
	private Integer retransmissionLenReqTotLevel3;
	@Column(name="retransmission_len_req_tot_level4")
	private Integer retransmissionLenReqTotLevel4;
	@Column(name="retransmission_len_req_tot_level5")
	private Integer retransmissionLenReqTotLevel5;

	@Column(name="retransmission_len_res_tot_use")
	private Boolean retransmissionLenResTotUse;
	@Column(name="retransmission_len_res_tot_level1")
	private Integer retransmissionLenResTotLevel1;
	@Column(name="retransmission_len_res_tot_level2")
	private Integer retransmissionLenResTotLevel2;
	@Column(name="retransmission_len_res_tot_level3")
	private Integer retransmissionLenResTotLevel3;
	@Column(name="retransmission_len_res_tot_level4")
	private Integer retransmissionLenResTotLevel4;
	@Column(name="retransmission_len_res_tot_level5")
	private Integer retransmissionLenResTotLevel5;

	@Column(name="fastretransmission_cnt_req_tot_use")
	private Boolean fastretransmissionCntReqTotUse;
	@Column(name="fastretransmission_cnt_req_tot_level1")
	private Integer fastretransmissionCntReqTotLevel1;
	@Column(name="fastretransmission_cnt_req_tot_level2")
	private Integer fastretransmissionCntReqTotLevel2;
	@Column(name="fastretransmission_cnt_req_tot_level3")
	private Integer fastretransmissionCntReqTotLevel3;
	@Column(name="fastretransmission_cnt_req_tot_level4")
	private Integer fastretransmissionCntReqTotLevel4;
	@Column(name="fastretransmission_cnt_req_tot_level5")
	private Integer fastretransmissionCntReqTotLevel5;

	@Column(name="fastretransmission_cnt_res_tot_use")
	private Boolean fastretransmissionCntResTotUse;
	@Column(name="fastretransmission_cnt_res_tot_level1")
	private Integer fastretransmissionCntResTotLevel1;
	@Column(name="fastretransmission_cnt_res_tot_level2")
	private Integer fastretransmissionCntResTotLevel2;
	@Column(name="fastretransmission_cnt_res_tot_level3")
	private Integer fastretransmissionCntResTotLevel3;
	@Column(name="fastretransmission_cnt_res_tot_level4")
	private Integer fastretransmissionCntResTotLevel4;
	@Column(name="fastretransmission_cnt_res_tot_level5")
	private Integer fastretransmissionCntResTotLevel5;

	@Column(name="fastretransmission_len_req_tot_use")
	private Boolean fastretransmissionLenReqTotUse;
	@Column(name="fastretransmission_len_req_tot_level1")
	private Integer fastretransmissionLenReqTotLevel1;
	@Column(name="fastretransmission_len_req_tot_level2")
	private Integer fastretransmissionLenReqTotLevel2;
	@Column(name="fastretransmission_len_req_tot_level3")
	private Integer fastretransmissionLenReqTotLevel3;
	@Column(name="fastretransmission_len_req_tot_level4")
	private Integer fastretransmissionLenReqTotLevel4;
	@Column(name="fastretransmission_len_req_tot_level5")
	private Integer fastretransmissionLenReqTotLevel5;

	@Column(name="fastretransmission_len_res_tot_use")
	private Boolean fastretransmissionLenResTotUse;
	@Column(name="fastretransmission_len_res_tot_level1")
	private Integer fastretransmissionLenResTotLevel1;
	@Column(name="fastretransmission_len_res_tot_level2")
	private Integer fastretransmissionLenResTotLevel2;
	@Column(name="fastretransmission_len_res_tot_level3")
	private Integer fastretransmissionLenResTotLevel3;
	@Column(name="fastretransmission_len_res_tot_level4")
	private Integer fastretransmissionLenResTotLevel4;
	@Column(name="fastretransmission_len_res_tot_level5")
	private Integer fastretransmissionLenResTotLevel5;

	@Column(name="outoforder_cnt_req_tot_use")
	private Boolean outoforderCntReqTotUse;
	@Column(name="outoforder_cnt_req_tot_level1")
	private Integer outoforderCntReqTotLevel1;
	@Column(name="outoforder_cnt_req_tot_level2")
	private Integer outoforderCntReqTotLevel2;
	@Column(name="outoforder_cnt_req_tot_level3")
	private Integer outoforderCntReqTotLevel3;
	@Column(name="outoforder_cnt_req_tot_level4")
	private Integer outoforderCntReqTotLevel4;
	@Column(name="outoforder_cnt_req_tot_level5")
	private Integer outoforderCntReqTotLevel5;

	@Column(name="outoforder_cnt_res_tot_use")
	private Boolean outoforderCntResTotUse;
	@Column(name="outoforder_cnt_res_tot_level1")
	private Integer outoforderCntResTotLevel1;
	@Column(name="outoforder_cnt_res_tot_level2")
	private Integer outoforderCntResTotLevel2;
	@Column(name="outoforder_cnt_res_tot_level3")
	private Integer outoforderCntResTotLevel3;
	@Column(name="outoforder_cnt_res_tot_level4")
	private Integer outoforderCntResTotLevel4;
	@Column(name="outoforder_cnt_res_tot_level5")
	private Integer outoforderCntResTotLevel5;

	@Column(name="outoforder_len_req_tot_use")
	private Boolean outoforderLenReqTotUse;
	@Column(name="outoforder_len_req_tot_level1")
	private Integer outoforderLenReqTotLevel1;
	@Column(name="outoforder_len_req_tot_level2")
	private Integer outoforderLenReqTotLevel2;
	@Column(name="outoforder_len_req_tot_level3")
	private Integer outoforderLenReqTotLevel3;
	@Column(name="outoforder_len_req_tot_level4")
	private Integer outoforderLenReqTotLevel4;
	@Column(name="outoforder_len_req_tot_level5")
	private Integer outoforderLenReqTotLevel5;

	@Column(name="outoforder_len_res_tot_use")
	private Boolean outoforderLenResTotUse;
	@Column(name="outoforder_len_res_tot_level1")
	private Integer outoforderLenResTotLevel1;
	@Column(name="outoforder_len_res_tot_level2")
	private Integer outoforderLenResTotLevel2;
	@Column(name="outoforder_len_res_tot_level3")
	private Integer outoforderLenResTotLevel3;
	@Column(name="outoforder_len_res_tot_level4")
	private Integer outoforderLenResTotLevel4;
	@Column(name="outoforder_len_res_tot_level5")
	private Integer outoforderLenResTotLevel5;

	@Column(name="lostseg_cnt_req_tot_use")
	private Boolean lostsegCntReqTotUse;
	@Column(name="lostseg_cnt_req_tot_level1")
	private Integer lostsegCntReqTotLevel1;
	@Column(name="lostseg_cnt_req_tot_level2")
	private Integer lostsegCntReqTotLevel2;
	@Column(name="lostseg_cnt_req_tot_level3")
	private Integer lostsegCntReqTotLevel3;
	@Column(name="lostseg_cnt_req_tot_level4")
	private Integer lostsegCntReqTotLevel4;
	@Column(name="lostseg_cnt_req_tot_level5")
	private Integer lostsegCntReqTotLevel5;

	@Column(name="lostseg_cnt_res_tot_use")
	private Boolean lostsegCntResTotUse;
	@Column(name="lostseg_cnt_res_tot_level1")
	private Integer lostsegCntResTotLevel1;
	@Column(name="lostseg_cnt_res_tot_level2")
	private Integer lostsegCntResTotLevel2;
	@Column(name="lostseg_cnt_res_tot_level3")
	private Integer lostsegCntResTotLevel3;
	@Column(name="lostseg_cnt_res_tot_level4")
	private Integer lostsegCntResTotLevel4;
	@Column(name="lostseg_cnt_res_tot_level5")
	private Integer lostsegCntResTotLevel5;

	@Column(name="lostseg_len_req_tot_use")
	private Boolean lostsegLenReqTotUse;
	@Column(name="lostseg_len_req_tot_level1")
	private Integer lostsegLenReqTotLevel1;
	@Column(name="lostseg_len_req_tot_level2")
	private Integer lostsegLenReqTotLevel2;
	@Column(name="lostseg_len_req_tot_level3")
	private Integer lostsegLenReqTotLevel3;
	@Column(name="lostseg_len_req_tot_level4")
	private Integer lostsegLenReqTotLevel4;
	@Column(name="lostseg_len_req_tot_level5")
	private Integer lostsegLenReqTotLevel5;

	@Column(name="lostseg_len_res_tot_use")
	private Boolean lostsegLenResTotUse;
	@Column(name="lostseg_len_res_tot_level1")
	private Integer lostsegLenResTotLevel1;
	@Column(name="lostseg_len_res_tot_level2")
	private Integer lostsegLenResTotLevel2;
	@Column(name="lostseg_len_res_tot_level3")
	private Integer lostsegLenResTotLevel3;
	@Column(name="lostseg_len_res_tot_level4")
	private Integer lostsegLenResTotLevel4;
	@Column(name="lostseg_len_res_tot_level5")
	private Integer lostsegLenResTotLevel5;

	@Column(name="acklost_cnt_req_tot_use")
	private Boolean acklostCntReqTotUse;
	@Column(name="acklost_cnt_req_tot_level1")
	private Integer acklostCntReqTotLevel1;
	@Column(name="acklost_cnt_req_tot_level2")
	private Integer acklostCntReqTotLevel2;
	@Column(name="acklost_cnt_req_tot_level3")
	private Integer acklostCntReqTotLevel3;
	@Column(name="acklost_cnt_req_tot_level4")
	private Integer acklostCntReqTotLevel4;
	@Column(name="acklost_cnt_req_tot_level5")
	private Integer acklostCntReqTotLevel5;

	@Column(name="acklost_cnt_res_tot_use")
	private Boolean acklostCntResTotUse;
	@Column(name="acklost_cnt_res_tot_level1")
	private Integer acklostCntResTotLevel1;
	@Column(name="acklost_cnt_res_tot_level2")
	private Integer acklostCntResTotLevel2;
	@Column(name="acklost_cnt_res_tot_level3")
	private Integer acklostCntResTotLevel3;
	@Column(name="acklost_cnt_res_tot_level4")
	private Integer acklostCntResTotLevel4;
	@Column(name="acklost_cnt_res_tot_level5")
	private Integer acklostCntResTotLevel5;

	@Column(name="acklost_len_req_tot_use")
	private Boolean acklostLenReqTotUse;
	@Column(name="acklost_len_req_tot_level1")
	private Integer acklostLenReqTotLevel1;
	@Column(name="acklost_len_req_tot_level2")
	private Integer acklostLenReqTotLevel2;
	@Column(name="acklost_len_req_tot_level3")
	private Integer acklostLenReqTotLevel3;
	@Column(name="acklost_len_req_tot_level4")
	private Integer acklostLenReqTotLevel4;
	@Column(name="acklost_len_req_tot_level5")
	private Integer acklostLenReqTotLevel5;

	@Column(name="acklost_len_res_tot_use")
	private Boolean acklostLenResTotUse;
	@Column(name="acklost_len_res_tot_level1")
	private Integer acklostLenResTotLevel1;
	@Column(name="acklost_len_res_tot_level2")
	private Integer acklostLenResTotLevel2;
	@Column(name="acklost_len_res_tot_level3")
	private Integer acklostLenResTotLevel3;
	@Column(name="acklost_len_res_tot_level4")
	private Integer acklostLenResTotLevel4;
	@Column(name="acklost_len_res_tot_level5")
	private Integer acklostLenResTotLevel5;

	@Column(name="winupdate_cnt_req_tot_use")
	private Boolean winupdateCntReqTotUse;
	@Column(name="winupdate_cnt_req_tot_level1")
	private Integer winupdateCntReqTotLevel1;
	@Column(name="winupdate_cnt_req_tot_level2")
	private Integer winupdateCntReqTotLevel2;
	@Column(name="winupdate_cnt_req_tot_level3")
	private Integer winupdateCntReqTotLevel3;
	@Column(name="winupdate_cnt_req_tot_level4")
	private Integer winupdateCntReqTotLevel4;
	@Column(name="winupdate_cnt_req_tot_level5")
	private Integer winupdateCntReqTotLevel5;

	@Column(name="winupdate_cnt_res_tot_use")
	private Boolean winupdateCntResTotUse;
	@Column(name="winupdate_cnt_res_tot_level1")
	private Integer winupdateCntResTotLevel1;
	@Column(name="winupdate_cnt_res_tot_level2")
	private Integer winupdateCntResTotLevel2;
	@Column(name="winupdate_cnt_res_tot_level3")
	private Integer winupdateCntResTotLevel3;
	@Column(name="winupdate_cnt_res_tot_level4")
	private Integer winupdateCntResTotLevel4;
	@Column(name="winupdate_cnt_res_tot_level5")
	private Integer winupdateCntResTotLevel5;

	@Column(name="winupdate_len_req_tot_use")
	private Boolean winupdateLenReqTotUse;
	@Column(name="winupdate_len_req_tot_level1")
	private Integer winupdateLenReqTotLevel1;
	@Column(name="winupdate_len_req_tot_level2")
	private Integer winupdateLenReqTotLevel2;
	@Column(name="winupdate_len_req_tot_level3")
	private Integer winupdateLenReqTotLevel3;
	@Column(name="winupdate_len_req_tot_level4")
	private Integer winupdateLenReqTotLevel4;
	@Column(name="winupdate_len_req_tot_level5")
	private Integer winupdateLenReqTotLevel5;

	@Column(name="winupdate_len_res_tot_use")
	private Boolean winupdateLenResTotUse;
	@Column(name="winupdate_len_res_tot_level1")
	private Integer winupdateLenResTotLevel1;
	@Column(name="winupdate_len_res_tot_level2")
	private Integer winupdateLenResTotLevel2;
	@Column(name="winupdate_len_res_tot_level3")
	private Integer winupdateLenResTotLevel3;
	@Column(name="winupdate_len_res_tot_level4")
	private Integer winupdateLenResTotLevel4;
	@Column(name="winupdate_len_res_tot_level5")
	private Integer winupdateLenResTotLevel5;

	@Column(name="dupack_cnt_req_tot_use")
	private Boolean dupackCntReqTotUse;
	@Column(name="dupack_cnt_req_tot_level1")
	private Integer dupackCntReqTotLevel1;
	@Column(name="dupack_cnt_req_tot_level2")
	private Integer dupackCntReqTotLevel2;
	@Column(name="dupack_cnt_req_tot_level3")
	private Integer dupackCntReqTotLevel3;
	@Column(name="dupack_cnt_req_tot_level4")
	private Integer dupackCntReqTotLevel4;
	@Column(name="dupack_cnt_req_tot_level5")
	private Integer dupackCntReqTotLevel5;

	@Column(name="dupack_cnt_res_tot_use")
	private Boolean dupackCntResTotUse;
	@Column(name="dupack_cnt_res_tot_level1")
	private Integer dupackCntResTotLevel1;
	@Column(name="dupack_cnt_res_tot_level2")
	private Integer dupackCntResTotLevel2;
	@Column(name="dupack_cnt_res_tot_level3")
	private Integer dupackCntResTotLevel3;
	@Column(name="dupack_cnt_res_tot_level4")
	private Integer dupackCntResTotLevel4;
	@Column(name="dupack_cnt_res_tot_level5")
	private Integer dupackCntResTotLevel5;

	@Column(name="dupack_len_req_tot_use")
	private Boolean dupackLenReqTotUse;
	@Column(name="dupack_len_req_tot_level1")
	private Integer dupackLenReqTotLevel1;
	@Column(name="dupack_len_req_tot_level2")
	private Integer dupackLenReqTotLevel2;
	@Column(name="dupack_len_req_tot_level3")
	private Integer dupackLenReqTotLevel3;
	@Column(name="dupack_len_req_tot_level4")
	private Integer dupackLenReqTotLevel4;
	@Column(name="dupack_len_req_tot_level5")
	private Integer dupackLenReqTotLevel5;

	@Column(name="dupack_len_res_tot_use")
	private Boolean dupackLenResTotUse;
	@Column(name="dupack_len_res_tot_level1")
	private Integer dupackLenResTotLevel1;
	@Column(name="dupack_len_res_tot_level2")
	private Integer dupackLenResTotLevel2;
	@Column(name="dupack_len_res_tot_level3")
	private Integer dupackLenResTotLevel3;
	@Column(name="dupack_len_res_tot_level4")
	private Integer dupackLenResTotLevel4;
	@Column(name="dupack_len_res_tot_level5")
	private Integer dupackLenResTotLevel5;

	@Column(name="zerowin_cnt_req_tot_use")
	private Boolean zerowinCntReqTotUse;
	@Column(name="zerowin_cnt_req_tot_level1")
	private Integer zerowinCntReqTotLevel1;
	@Column(name="zerowin_cnt_req_tot_level2")
	private Integer zerowinCntReqTotLevel2;
	@Column(name="zerowin_cnt_req_tot_level3")
	private Integer zerowinCntReqTotLevel3;
	@Column(name="zerowin_cnt_req_tot_level4")
	private Integer zerowinCntReqTotLevel4;
	@Column(name="zerowin_cnt_req_tot_level5")
	private Integer zerowinCntReqTotLevel5;

	@Column(name="zerowin_cnt_res_tot_use")
	private Boolean zerowinCntResTotUse;
	@Column(name="zerowin_cnt_res_tot_level1")
	private Integer zerowinCntResTotLevel1;
	@Column(name="zerowin_cnt_res_tot_level2")
	private Integer zerowinCntResTotLevel2;
	@Column(name="zerowin_cnt_res_tot_level3")
	private Integer zerowinCntResTotLevel3;
	@Column(name="zerowin_cnt_res_tot_level4")
	private Integer zerowinCntResTotLevel4;
	@Column(name="zerowin_cnt_res_tot_level5")
	private Integer zerowinCntResTotLevel5;

	@Column(name="zerowin_len_req_tot_use")
	private Boolean zerowinLenReqTotUse;
	@Column(name="zerowin_len_req_tot_level1")
	private Integer zerowinLenReqTotLevel1;
	@Column(name="zerowin_len_req_tot_level2")
	private Integer zerowinLenReqTotLevel2;
	@Column(name="zerowin_len_req_tot_level3")
	private Integer zerowinLenReqTotLevel3;
	@Column(name="zerowin_len_req_tot_level4")
	private Integer zerowinLenReqTotLevel4;
	@Column(name="zerowin_len_req_tot_level5")
	private Integer zerowinLenReqTotLevel5;

	@Column(name="zerowin_len_res_tot_use")
	private Boolean zerowinLenResTotUse;
	@Column(name="zerowin_len_res_tot_level1")
	private Integer zerowinLenResTotLevel1;
	@Column(name="zerowin_len_res_tot_level2")
	private Integer zerowinLenResTotLevel2;
	@Column(name="zerowin_len_res_tot_level3")
	private Integer zerowinLenResTotLevel3;
	@Column(name="zerowin_len_res_tot_level4")
	private Integer zerowinLenResTotLevel4;
	@Column(name="zerowin_len_res_tot_level5")
	private Integer zerowinLenResTotLevel5;

	@Column(name="retransmission_cnt_req_per_sec_use")
	private Boolean retransmissionCntReqPerSecUse;
	@Column(name="retransmission_cnt_req_per_sec_level1")
	private Integer retransmissionCntReqPerSecLevel1;
	@Column(name="retransmission_cnt_req_per_sec_level2")
	private Integer retransmissionCntReqPerSecLevel2;
	@Column(name="retransmission_cnt_req_per_sec_level3")
	private Integer retransmissionCntReqPerSecLevel3;
	@Column(name="retransmission_cnt_req_per_sec_level4")
	private Integer retransmissionCntReqPerSecLevel4;
	@Column(name="retransmission_cnt_req_per_sec_level5")
	private Integer retransmissionCntReqPerSecLevel5;

	@Column(name="retransmission_cnt_res_per_sec_use")
	private Boolean retransmissionCntResPerSecUse;
	@Column(name="retransmission_cnt_res_per_sec_level1")
	private Integer retransmissionCntResPerSecLevel1;
	@Column(name="retransmission_cnt_res_per_sec_level2")
	private Integer retransmissionCntResPerSecLevel2;
	@Column(name="retransmission_cnt_res_per_sec_level3")
	private Integer retransmissionCntResPerSecLevel3;
	@Column(name="retransmission_cnt_res_per_sec_level4")
	private Integer retransmissionCntResPerSecLevel4;
	@Column(name="retransmission_cnt_res_per_sec_level5")
	private Integer retransmissionCntResPerSecLevel5;

	@Column(name="retransmission_len_req_per_sec_use")
	private Boolean retransmissionLenReqPerSecUse;
	@Column(name="retransmission_len_req_per_sec_level1")
	private Integer retransmissionLenReqPerSecLevel1;
	@Column(name="retransmission_len_req_per_sec_level2")
	private Integer retransmissionLenReqPerSecLevel2;
	@Column(name="retransmission_len_req_per_sec_level3")
	private Integer retransmissionLenReqPerSecLevel3;
	@Column(name="retransmission_len_req_per_sec_level4")
	private Integer retransmissionLenReqPerSecLevel4;
	@Column(name="retransmission_len_req_per_sec_level5")
	private Integer retransmissionLenReqPerSecLevel5;

	@Column(name="retransmission_len_res_per_sec_use")
	private Boolean retransmissionLenResPerSecUse;
	@Column(name="retransmission_len_res_per_sec_level1")
	private Integer retransmissionLenResPerSecLevel1;
	@Column(name="retransmission_len_res_per_sec_level2")
	private Integer retransmissionLenResPerSecLevel2;
	@Column(name="retransmission_len_res_per_sec_level3")
	private Integer retransmissionLenResPerSecLevel3;
	@Column(name="retransmission_len_res_per_sec_level4")
	private Integer retransmissionLenResPerSecLevel4;
	@Column(name="retransmission_len_res_per_sec_level5")
	private Integer retransmissionLenResPerSecLevel5;

	@Column(name="fastretransmission_cnt_req_per_sec_use")
	private Boolean fastretransmissionCntReqPerSecUse;
	@Column(name="fastretransmission_cnt_req_per_sec_level1")
	private Integer fastretransmissionCntReqPerSecLevel1;
	@Column(name="fastretransmission_cnt_req_per_sec_level2")
	private Integer fastretransmissionCntReqPerSecLevel2;
	@Column(name="fastretransmission_cnt_req_per_sec_level3")
	private Integer fastretransmissionCntReqPerSecLevel3;
	@Column(name="fastretransmission_cnt_req_per_sec_level4")
	private Integer fastretransmissionCntReqPerSecLevel4;
	@Column(name="fastretransmission_cnt_req_per_sec_level5")
	private Integer fastretransmissionCntReqPerSecLevel5;

	@Column(name="fastretransmission_cnt_res_per_sec_use")
	private Boolean fastretransmissionCntResPerSecUse;
	@Column(name="fastretransmission_cnt_res_per_sec_level1")
	private Integer fastretransmissionCntResPerSecLevel1;
	@Column(name="fastretransmission_cnt_res_per_sec_level2")
	private Integer fastretransmissionCntResPerSecLevel2;
	@Column(name="fastretransmission_cnt_res_per_sec_level3")
	private Integer fastretransmissionCntResPerSecLevel3;
	@Column(name="fastretransmission_cnt_res_per_sec_level4")
	private Integer fastretransmissionCntResPerSecLevel4;
	@Column(name="fastretransmission_cnt_res_per_sec_level5")
	private Integer fastretransmissionCntResPerSecLevel5;

	@Column(name="fastretransmission_len_req_per_sec_use")
	private Boolean fastretransmissionLenReqPerSecUse;
	@Column(name="fastretransmission_len_req_per_sec_level1")
	private Integer fastretransmissionLenReqPerSecLevel1;
	@Column(name="fastretransmission_len_req_per_sec_level2")
	private Integer fastretransmissionLenReqPerSecLevel2;
	@Column(name="fastretransmission_len_req_per_sec_level3")
	private Integer fastretransmissionLenReqPerSecLevel3;
	@Column(name="fastretransmission_len_req_per_sec_level4")
	private Integer fastretransmissionLenReqPerSecLevel4;
	@Column(name="fastretransmission_len_req_per_sec_level5")
	private Integer fastretransmissionLenReqPerSecLevel5;

	@Column(name="fastretransmission_len_res_per_sec_use")
	private Boolean fastretransmissionLenResPerSecUse;
	@Column(name="fastretransmission_len_res_per_sec_level1")
	private Integer fastretransmissionLenResPerSecLevel1;
	@Column(name="fastretransmission_len_res_per_sec_level2")
	private Integer fastretransmissionLenResPerSecLevel2;
	@Column(name="fastretransmission_len_res_per_sec_level3")
	private Integer fastretransmissionLenResPerSecLevel3;
	@Column(name="fastretransmission_len_res_per_sec_level4")
	private Integer fastretransmissionLenResPerSecLevel4;
	@Column(name="fastretransmission_len_res_per_sec_level5")
	private Integer fastretransmissionLenResPerSecLevel5;

	@Column(name="outoforder_cnt_req_per_sec_use")
	private Boolean outoforderCntReqPerSecUse;
	@Column(name="outoforder_cnt_req_per_sec_level1")
	private Integer outoforderCntReqPerSecLevel1;
	@Column(name="outoforder_cnt_req_per_sec_level2")
	private Integer outoforderCntReqPerSecLevel2;
	@Column(name="outoforder_cnt_req_per_sec_level3")
	private Integer outoforderCntReqPerSecLevel3;
	@Column(name="outoforder_cnt_req_per_sec_level4")
	private Integer outoforderCntReqPerSecLevel4;
	@Column(name="outoforder_cnt_req_per_sec_level5")
	private Integer outoforderCntReqPerSecLevel5;

	@Column(name="outoforder_cnt_res_per_sec_use")
	private Boolean outoforderCntResPerSecUse;
	@Column(name="outoforder_cnt_res_per_sec_level1")
	private Integer outoforderCntResPerSecLevel1;
	@Column(name="outoforder_cnt_res_per_sec_level2")
	private Integer outoforderCntResPerSecLevel2;
	@Column(name="outoforder_cnt_res_per_sec_level3")
	private Integer outoforderCntResPerSecLevel3;
	@Column(name="outoforder_cnt_res_per_sec_level4")
	private Integer outoforderCntResPerSecLevel4;
	@Column(name="outoforder_cnt_res_per_sec_level5")
	private Integer outoforderCntResPerSecLevel5;

	@Column(name="outoforder_len_req_per_sec_use")
	private Boolean outoforderLenReqPerSecUse;
	@Column(name="outoforder_len_req_per_sec_level1")
	private Integer outoforderLenReqPerSecLevel1;
	@Column(name="outoforder_len_req_per_sec_level2")
	private Integer outoforderLenReqPerSecLevel2;
	@Column(name="outoforder_len_req_per_sec_level3")
	private Integer outoforderLenReqPerSecLevel3;
	@Column(name="outoforder_len_req_per_sec_level4")
	private Integer outoforderLenReqPerSecLevel4;
	@Column(name="outoforder_len_req_per_sec_level5")
	private Integer outoforderLenReqPerSecLevel5;

	@Column(name="outoforder_len_res_per_sec_use")
	private Boolean OutoforderLenResPerSecUse;
	@Column(name="outoforder_len_res_per_sec_level1")
	private Integer OutoforderLenResPerSecLevel1;
	@Column(name="outoforder_len_res_per_sec_level2")
	private Integer OutoforderLenResPerSecLevel2;
	@Column(name="outoforder_len_res_per_sec_level3")
	private Integer OutoforderLenResPerSecLevel3;
	@Column(name="outoforder_len_res_per_sec_level4")
	private Integer OutoforderLenResPerSecLevel4;
	@Column(name="outoforder_len_res_per_sec_level5")
	private Integer OutoforderLenResPerSecLevel5;

	@Column(name="lostseg_cnt_req_per_sec_use")
	private Boolean LostsegCntReqPerSecUse;
	@Column(name="lostseg_cnt_req_per_sec_level1")
	private Integer LostsegCntReqPerSecLevel1;
	@Column(name="lostseg_cnt_req_per_sec_level2")
	private Integer LostsegCntReqPerSecLevel2;
	@Column(name="lostseg_cnt_req_per_sec_level3")
	private Integer LostsegCntReqPerSecLevel3;
	@Column(name="lostseg_cnt_req_per_sec_level4")
	private Integer LostsegCntReqPerSecLevel4;
	@Column(name="lostseg_cnt_req_per_sec_level5")
	private Integer LostsegCntReqPerSecLevel5;

	@Column(name="lostseg_cnt_res_per_sec_use")
	private Boolean LostsegCntResPerSecUse;
	@Column(name="lostseg_cnt_res_per_sec_level1")
	private Integer LostsegCntResPerSecLevel1;
	@Column(name="lostseg_cnt_res_per_sec_level2")
	private Integer LostsegCntResPerSecLevel2;
	@Column(name="lostseg_cnt_res_per_sec_level3")
	private Integer LostsegCntResPerSecLevel3;
	@Column(name="lostseg_cnt_res_per_sec_level4")
	private Integer LostsegCntResPerSecLevel4;
	@Column(name="lostseg_cnt_res_per_sec_level5")
	private Integer LostsegCntResPerSecLevel5;

	@Column(name="lostseg_len_req_per_sec_use")
	private Boolean LostsegLenReqPerSecUse;
	@Column(name="lostseg_len_req_per_sec_level1")
	private Integer LostsegLenReqPerSecLevel1;
	@Column(name="lostseg_len_req_per_sec_level2")
	private Integer LostsegLenReqPerSecLevel2;
	@Column(name="lostseg_len_req_per_sec_level3")
	private Integer LostsegLenReqPerSecLevel3;
	@Column(name="lostseg_len_req_per_sec_level4")
	private Integer LostsegLenReqPerSecLevel4;
	@Column(name="lostseg_len_req_per_sec_level5")
	private Integer LostsegLenReqPerSecLevel5;

	@Column(name="lostseg_len_res_per_sec_use")
	private Boolean LostsegLenResPerSecUse;
	@Column(name="lostseg_len_res_per_sec_level1")
	private Integer LostsegLenResPerSecLevel1;
	@Column(name="lostseg_len_res_per_sec_level2")
	private Integer LostsegLenResPerSecLevel2;
	@Column(name="lostseg_len_res_per_sec_level3")
	private Integer LostsegLenResPerSecLevel3;
	@Column(name="lostseg_len_res_per_sec_level4")
	private Integer LostsegLenResPerSecLevel4;
	@Column(name="lostseg_len_res_per_sec_level5")
	private Integer LostsegLenResPerSecLevel5;

	@Column(name="acklost_cnt_req_per_sec_use")
	private Boolean acklostCntReqPerSecUse;
	@Column(name="acklost_cnt_req_per_sec_level1")
	private Integer acklostCntReqPerSecLevel1;
	@Column(name="acklost_cnt_req_per_sec_level2")
	private Integer acklostCntReqPerSecLevel2;
	@Column(name="acklost_cnt_req_per_sec_level3")
	private Integer acklostCntReqPerSecLevel3;
	@Column(name="acklost_cnt_req_per_sec_level4")
	private Integer acklostCntReqPerSecLevel4;
	@Column(name="acklost_cnt_req_per_sec_level5")
	private Integer acklostCntReqPerSecLevel5;

	@Column(name="acklost_cnt_res_per_sec_use")
	private Boolean acklostCntResPerSecUse;
	@Column(name="acklost_cnt_res_per_sec_level1")
	private Integer acklostCntResPerSecLevel1;
	@Column(name="acklost_cnt_res_per_sec_level2")
	private Integer acklostCntResPerSecLevel2;
	@Column(name="acklost_cnt_res_per_sec_level3")
	private Integer acklostCntResPerSecLevel3;
	@Column(name="acklost_cnt_res_per_sec_level4")
	private Integer acklostCntResPerSecLevel4;
	@Column(name="acklost_cnt_res_per_sec_level5")
	private Integer acklostCntResPerSecLevel5;

	@Column(name="acklost_len_req_per_sec_use")
	private Boolean acklostLenReqPerSecUse;
	@Column(name="acklost_len_req_per_sec_level1")
	private Integer acklostLenReqPerSecLevel1;
	@Column(name="acklost_len_req_per_sec_level2")
	private Integer acklostLenReqPerSecLevel2;
	@Column(name="acklost_len_req_per_sec_level3")
	private Integer acklostLenReqPerSecLevel3;
	@Column(name="acklost_len_req_per_sec_level4")
	private Integer acklostLenReqPerSecLevel4;
	@Column(name="acklost_len_req_per_sec_level5")
	private Integer acklostLenReqPerSecLevel5;

	@Column(name="acklost_len_res_per_sec_use")
	private Boolean acklostLenResPerSecUse;
	@Column(name="acklost_len_res_per_sec_level1")
	private Integer acklostLenResPerSecLevel1;
	@Column(name="acklost_len_res_per_sec_level2")
	private Integer acklostLenResPerSecLevel2;
	@Column(name="acklost_len_res_per_sec_level3")
	private Integer acklostLenResPerSecLevel3;
	@Column(name="acklost_len_res_per_sec_level4")
	private Integer acklostLenResPerSecLevel4;
	@Column(name="acklost_len_res_per_sec_level5")
	private Integer acklostLenResPerSecLevel5;

	@Column(name="winupdate_cnt_req_per_sec_use")
	private Boolean winupdateCntReqPerSecUse;
	@Column(name="winupdate_cnt_req_per_sec_level1")
	private Integer winupdateCntReqPerSecLevel1;
	@Column(name="winupdate_cnt_req_per_sec_level2")
	private Integer winupdateCntReqPerSecLevel2;
	@Column(name="winupdate_cnt_req_per_sec_level3")
	private Integer winupdateCntReqPerSecLevel3;
	@Column(name="winupdate_cnt_req_per_sec_level4")
	private Integer winupdateCntReqPerSecLevel4;
	@Column(name="winupdate_cnt_req_per_sec_level5")
	private Integer winupdateCntReqPerSecLevel5;

	@Column(name="winupdate_cnt_res_per_sec_use")
	private Boolean winupdateCntResPerSecUse;
	@Column(name="winupdate_cnt_res_per_sec_level1")
	private Integer winupdateCntResPerSecLevel1;
	@Column(name="winupdate_cnt_res_per_sec_level2")
	private Integer winupdateCntResPerSecLevel2;
	@Column(name="winupdate_cnt_res_per_sec_level3")
	private Integer winupdateCntResPerSecLevel3;
	@Column(name="winupdate_cnt_res_per_sec_level4")
	private Integer winupdateCntResPerSecLevel4;
	@Column(name="winupdate_cnt_res_per_sec_level5")
	private Integer winupdateCntResPerSecLevel5;

	@Column(name="winupdate_len_req_per_sec_use")
	private Boolean winupdateLenReqPerSecUse;
	@Column(name="winupdate_len_req_per_sec_level1")
	private Integer winupdateLenReqPerSecLevel1;
	@Column(name="winupdate_len_req_per_sec_level2")
	private Integer winupdateLenReqPerSecLevel2;
	@Column(name="winupdate_len_req_per_sec_level3")
	private Integer winupdateLenReqPerSecLevel3;
	@Column(name="winupdate_len_req_per_sec_level4")
	private Integer winupdateLenReqPerSecLevel4;
	@Column(name="winupdate_len_req_per_sec_level5")
	private Integer winupdateLenReqPerSecLevel5;

	@Column(name="winupdate_len_res_per_sec_use")
	private Boolean winupdateLenResPerSecUse;
	@Column(name="winupdate_len_res_per_sec_level1")
	private Integer winupdateLenResPerSecLevel1;
	@Column(name="winupdate_len_res_per_sec_level2")
	private Integer winupdateLenResPerSecLevel2;
	@Column(name="winupdate_len_res_per_sec_level3")
	private Integer winupdateLenResPerSecLevel3;
	@Column(name="winupdate_len_res_per_sec_level4")
	private Integer winupdateLenResPerSecLevel4;
	@Column(name="winupdate_len_res_per_sec_level5")
	private Integer winupdateLenResPerSecLevel5;

	@Column(name="dupack_cnt_req_per_sec_use")
	private Boolean dupackCntReqPerSecUse;
	@Column(name="dupack_cnt_req_per_sec_level1")
	private Integer dupackCntReqPerSecLevel1;
	@Column(name="dupack_cnt_req_per_sec_level2")
	private Integer dupackCntReqPerSecLevel2;
	@Column(name="dupack_cnt_req_per_sec_level3")
	private Integer dupackCntReqPerSecLevel3;
	@Column(name="dupack_cnt_req_per_sec_level4")
	private Integer dupackCntReqPerSecLevel4;
	@Column(name="dupack_cnt_req_per_sec_level5")
	private Integer dupackCntReqPerSecLevel5;

	@Column(name="dupack_cnt_res_per_sec_use")
	private Boolean dupackCntResPerSecUse;
	@Column(name="dupack_cnt_res_per_sec_level1")
	private Integer dupackCntResPerSecLevel1;
	@Column(name="dupack_cnt_res_per_sec_level2")
	private Integer dupackCntResPerSecLevel2;
	@Column(name="dupack_cnt_res_per_sec_level3")
	private Integer dupackCntResPerSecLevel3;
	@Column(name="dupack_cnt_res_per_sec_level4")
	private Integer dupackCntResPerSecLevel4;
	@Column(name="dupack_cnt_res_per_sec_level5")
	private Integer dupackCntResPerSecLevel5;

	@Column(name="dupack_len_req_per_sec_use")
	private Boolean dupackLenReqPerSecUse;
	@Column(name="dupack_len_req_per_sec_level1")
	private Integer dupackLenReqPerSecLevel1;
	@Column(name="dupack_len_req_per_sec_level2")
	private Integer dupackLenReqPerSecLevel2;
	@Column(name="dupack_len_req_per_sec_level3")
	private Integer dupackLenReqPerSecLevel3;
	@Column(name="dupack_len_req_per_sec_level4")
	private Integer dupackLenReqPerSecLevel4;
	@Column(name="dupack_len_req_per_sec_level5")
	private Integer dupackLenReqPerSecLevel5;

	@Column(name="dupack_len_res_per_sec_use")
	private Boolean dupackLenResPerSecUse;
	@Column(name="dupack_len_res_per_sec_level1")
	private Integer dupackLenResPerSecLevel1;
	@Column(name="dupack_len_res_per_sec_level2")
	private Integer dupackLenResPerSecLevel2;
	@Column(name="dupack_len_res_per_sec_level3")
	private Integer dupackLenResPerSecLevel3;
	@Column(name="dupack_len_res_per_sec_level4")
	private Integer dupackLenResPerSecLevel4;
	@Column(name="dupack_len_res_per_sec_level5")
	private Integer dupackLenResPerSecLevel5;

	@Column(name="zerowin_cnt_req_per_sec_use")
	private Boolean zerowinCntReqPerSecUse;
	@Column(name="zerowin_cnt_req_per_sec_level1")
	private Integer zerowinCntReqPerSecLevel1;
	@Column(name="zerowin_cnt_req_per_sec_level2")
	private Integer zerowinCntReqPerSecLevel2;
	@Column(name="zerowin_cnt_req_per_sec_level3")
	private Integer zerowinCntReqPerSecLevel3;
	@Column(name="zerowin_cnt_req_per_sec_level4")
	private Integer zerowinCntReqPerSecLevel4;
	@Column(name="zerowin_cnt_req_per_sec_level5")
	private Integer zerowinCntReqPerSecLevel5;

	@Column(name="zerowin_cnt_res_per_sec_use")
	private Boolean zerowinCntResPerSecUse;
	@Column(name="zerowin_cnt_res_per_sec_level1")
	private Integer zerowinCntResPerSecLevel1;
	@Column(name="zerowin_cnt_res_per_sec_level2")
	private Integer zerowinCntResPerSecLevel2;
	@Column(name="zerowin_cnt_res_per_sec_level3")
	private Integer zerowinCntResPerSecLevel3;
	@Column(name="zerowin_cnt_res_per_sec_level4")
	private Integer zerowinCntResPerSecLevel4;
	@Column(name="zerowin_cnt_res_per_sec_level5")
	private Integer zerowinCntResPerSecLevel5;

	@Column(name="zerowin_len_req_per_sec_use")
	private Boolean zerowinLenReqPerSecUse;
	@Column(name="zerowin_len_req_per_sec_level1")
	private Integer zerowinLenReqPerSecLevel1;
	@Column(name="zerowin_len_req_per_sec_level2")
	private Integer zerowinLenReqPerSecLevel2;
	@Column(name="zerowin_len_req_per_sec_level3")
	private Integer zerowinLenReqPerSecLevel3;
	@Column(name="zerowin_len_req_per_sec_level4")
	private Integer zerowinLenReqPerSecLevel4;
	@Column(name="zerowin_len_req_per_sec_level5")
	private Integer zerowinLenReqPerSecLevel5;

	@Column(name="zerowin_len_res_per_sec_use")
	private Boolean zerowinLenResPerSecUse;
	@Column(name="zerowin_len_res_per_sec_level1")
	private Integer zerowinLenResPerSecLevel1;
	@Column(name="zerowin_len_res_per_sec_level2")
	private Integer zerowinLenResPerSecLevel2;
	@Column(name="zerowin_len_res_per_sec_level3")
	private Integer zerowinLenResPerSecLevel3;
	@Column(name="zerowin_len_res_per_sec_level4")
	private Integer zerowinLenResPerSecLevel4;
	@Column(name="zerowin_len_res_per_sec_level5")
	private Integer zerowinLenResPerSecLevel5;

	@Column(name="retransmission_cnt_req_delta_use")
	private Boolean retransmissionCntReqDeltaUse;
	@Column(name="retransmission_cnt_req_delta_level1")
	private Integer retransmissionCntReqDeltaLevel1;
	@Column(name="retransmission_cnt_req_delta_level2")
	private Integer retransmissionCntReqDeltaLevel2;
	@Column(name="retransmission_cnt_req_delta_level3")
	private Integer retransmissionCntReqDeltaLevel3;
	@Column(name="retransmission_cnt_req_delta_level4")
	private Integer retransmissionCntReqDeltaLevel4;
	@Column(name="retransmission_cnt_req_delta_level5")
	private Integer retransmissionCntReqDeltaLevel5;

	@Column(name="retransmission_cnt_res_delta_use")
	private Boolean retransmissionCntResDeltaUse;
	@Column(name="retransmission_cnt_res_delta_level1")
	private Integer retransmissionCntResDeltaLevel1;
	@Column(name="retransmission_cnt_res_delta_level2")
	private Integer retransmissionCntResDeltaLevel2;
	@Column(name="retransmission_cnt_res_delta_level3")
	private Integer retransmissionCntResDeltaLevel3;
	@Column(name="retransmission_cnt_res_delta_level4")
	private Integer retransmissionCntResDeltaLevel4;
	@Column(name="retransmission_cnt_res_delta_level5")
	private Integer retransmissionCntResDeltaLevel5;

	@Column(name="retransmission_len_req_delta_use")
	private Boolean retransmissionLenReqDeltaUse;
	@Column(name="retransmission_len_req_delta_level1")
	private Integer retransmissionLenReqDeltaLevel1;
	@Column(name="retransmission_len_req_delta_level2")
	private Integer retransmissionLenReqDeltaLevel2;
	@Column(name="retransmission_len_req_delta_level3")
	private Integer retransmissionLenReqDeltaLevel3;
	@Column(name="retransmission_len_req_delta_level4")
	private Integer retransmissionLenReqDeltaLevel4;
	@Column(name="retransmission_len_req_delta_level5")
	private Integer retransmissionLenReqDeltaLevel5;

	@Column(name="retransmission_len_res_delta_use")
	private Boolean retransmissionLenResDeltaUse;
	@Column(name="retransmission_len_res_delta_level1")
	private Integer retransmissionLenResDeltaLevel1;
	@Column(name="retransmission_len_res_delta_level2")
	private Integer retransmissionLenResDeltaLevel2;
	@Column(name="retransmission_len_res_delta_level3")
	private Integer retransmissionLenResDeltaLevel3;
	@Column(name="retransmission_len_res_delta_level4")
	private Integer retransmissionLenResDeltaLevel4;
	@Column(name="retransmission_len_res_delta_level5")
	private Integer retransmissionLenResDeltaLevel5;

	@Column(name="fastretransmission_cnt_req_delta_use")
	private Boolean fastretransmissionCntReqDeltaUse;
	@Column(name="fastretransmission_cnt_req_delta_level1")
	private Integer fastretransmissionCntReqDeltaLevel1;
	@Column(name="fastretransmission_cnt_req_delta_level2")
	private Integer fastretransmissionCntReqDeltaLevel2;
	@Column(name="fastretransmission_cnt_req_delta_level3")
	private Integer fastretransmissionCntReqDeltaLevel3;
	@Column(name="fastretransmission_cnt_req_delta_level4")
	private Integer fastretransmissionCntReqDeltaLevel4;
	@Column(name="fastretransmission_cnt_req_delta_level5")
	private Integer fastretransmissionCntReqDeltaLevel5;

	@Column(name="fastretransmission_cnt_res_delta_use")
	private Boolean fastretransmissionCntResDeltaUse;
	@Column(name="fastretransmission_cnt_res_delta_level1")
	private Integer fastretransmissionCntResDeltaLevel1;
	@Column(name="fastretransmission_cnt_res_delta_level2")
	private Integer fastretransmissionCntResDeltaLevel2;
	@Column(name="fastretransmission_cnt_res_delta_level3")
	private Integer fastretransmissionCntResDeltaLevel3;
	@Column(name="fastretransmission_cnt_res_delta_level4")
	private Integer fastretransmissionCntResDeltaLevel4;
	@Column(name="fastretransmission_cnt_res_delta_level5")
	private Integer fastretransmissionCntResDeltaLevel5;

	@Column(name="fastretransmission_len_req_delta_use")
	private Boolean fastretransmissionLenReqDeltaUse;
	@Column(name="fastretransmission_len_req_delta_level1")
	private Integer fastretransmissionLenReqDeltaLevel1;
	@Column(name="fastretransmission_len_req_delta_level2")
	private Integer fastretransmissionLenReqDeltaLevel2;
	@Column(name="fastretransmission_len_req_delta_level3")
	private Integer fastretransmissionLenReqDeltaLevel3;
	@Column(name="fastretransmission_len_req_delta_level4")
	private Integer fastretransmissionLenReqDeltaLevel4;
	@Column(name="fastretransmission_len_req_delta_level5")
	private Integer fastretransmissionLenReqDeltaLevel5;

	@Column(name="fastretransmission_len_res_delta_use")
	private Boolean fastretransmissionLenResDeltaUse;
	@Column(name="fastretransmission_len_res_delta_level1")
	private Integer fastretransmissionLenResDeltaLevel1;
	@Column(name="fastretransmission_len_res_delta_level2")
	private Integer fastretransmissionLenResDeltaLevel2;
	@Column(name="fastretransmission_len_res_delta_level3")
	private Integer fastretransmissionLenResDeltaLevel3;
	@Column(name="fastretransmission_len_res_delta_level4")
	private Integer fastretransmissionLenResDeltaLevel4;
	@Column(name="fastretransmission_len_res_delta_level5")
	private Integer fastretransmissionLenResDeltaLevel5;

	@Column(name="outoforder_cnt_req_delta_use")
	private Boolean outoforderCntReqDeltaUse;
	@Column(name="outoforder_cnt_req_delta_level1")
	private Integer outoforderCntReqDeltaLevel1;
	@Column(name="outoforder_cnt_req_delta_level2")
	private Integer outoforderCntReqDeltaLevel2;
	@Column(name="outoforder_cnt_req_delta_level3")
	private Integer outoforderCntReqDeltaLevel3;
	@Column(name="outoforder_cnt_req_delta_level4")
	private Integer outoforderCntReqDeltaLevel4;
	@Column(name="outoforder_cnt_req_delta_level5")
	private Integer outoforderCntReqDeltaLevel5;

	@Column(name="outoforder_cnt_res_delta_use")
	private Boolean outoforderCntResDeltaUse;
	@Column(name="outoforder_cnt_res_delta_level1")
	private Integer outoforderCntResDeltaLevel1;
	@Column(name="outoforder_cnt_res_delta_level2")
	private Integer outoforderCntResDeltaLevel2;
	@Column(name="outoforder_cnt_res_delta_level3")
	private Integer outoforderCntResDeltaLevel3;
	@Column(name="outoforder_cnt_res_delta_level4")
	private Integer outoforderCntResDeltaLevel4;
	@Column(name="outoforder_cnt_res_delta_level5")
	private Integer outoforderCntResDeltaLevel5;

	@Column(name="outoforder_len_req_delta_use")
	private Boolean outoforderLenReqDeltaUse;
	@Column(name="outoforder_len_req_delta_level1")
	private Integer outoforderLenReqDeltaLevel1;
	@Column(name="outoforder_len_req_delta_level2")
	private Integer outoforderLenReqDeltaLevel2;
	@Column(name="outoforder_len_req_delta_level3")
	private Integer outoforderLenReqDeltaLevel3;
	@Column(name="outoforder_len_req_delta_level4")
	private Integer outoforderLenReqDeltaLevel4;
	@Column(name="outoforder_len_req_delta_level5")
	private Integer outoforderLenReqDeltaLevel5;

	@Column(name="outoforder_len_res_delta_use")
	private Boolean outoforderLenResDeltaUse;
	@Column(name="outoforder_len_res_delta_level1")
	private Integer outoforderLenResDeltaLevel1;
	@Column(name="outoforder_len_res_delta_level2")
	private Integer outoforderLenResDeltaLevel2;
	@Column(name="outoforder_len_res_delta_level3")
	private Integer outoforderLenResDeltaLevel3;
	@Column(name="outoforder_len_res_delta_level4")
	private Integer outoforderLenResDeltaLevel4;
	@Column(name="outoforder_len_res_delta_level5")
	private Integer outoforderLenResDeltaLevel5;

	@Column(name="lostseg_cnt_req_delta_use")
	private Boolean lostsegCntReqDeltaUse;
	@Column(name="lostseg_cnt_req_delta_level1")
	private Integer lostsegCntReqDeltaLevel1;
	@Column(name="lostseg_cnt_req_delta_level2")
	private Integer lostsegCntReqDeltaLevel2;
	@Column(name="lostseg_cnt_req_delta_level3")
	private Integer lostsegCntReqDeltaLevel3;
	@Column(name="lostseg_cnt_req_delta_level4")
	private Integer lostsegCntReqDeltaLevel4;
	@Column(name="lostseg_cnt_req_delta_level5")
	private Integer lostsegCntReqDeltaLevel5;

	@Column(name="lostseg_cnt_res_delta_use")
	private Boolean lostsegCntResDeltaUse;
	@Column(name="lostseg_cnt_res_delta_level1")
	private Integer lostsegCntResDeltaLevel1;
	@Column(name="lostseg_cnt_res_delta_level2")
	private Integer lostsegCntResDeltaLevel2;
	@Column(name="lostseg_cnt_res_delta_level3")
	private Integer lostsegCntResDeltaLevel3;
	@Column(name="lostseg_cnt_res_delta_level4")
	private Integer lostsegCntResDeltaLevel4;
	@Column(name="lostseg_cnt_res_delta_level5")
	private Integer lostsegCntResDeltaLevel5;

	@Column(name="lostseg_len_req_delta_use")
	private Boolean lostsegLenReqDeltaUse;
	@Column(name="lostseg_len_req_delta_level1")
	private Integer lostsegLenReqDeltaLevel1;
	@Column(name="lostseg_len_req_delta_level2")
	private Integer lostsegLenReqDeltaLevel2;
	@Column(name="lostseg_len_req_delta_level3")
	private Integer lostsegLenReqDeltaLevel3;
	@Column(name="lostseg_len_req_delta_level4")
	private Integer lostsegLenReqDeltaLevel4;
	@Column(name="lostseg_len_req_delta_level5")
	private Integer lostsegLenReqDeltaLevel5;

	@Column(name="lostseg_len_res_delta_use")
	private Boolean lostsegLenResDeltaUse;
	@Column(name="lostseg_len_res_delta_level1")
	private Integer lostsegLenResDeltaLevel1;
	@Column(name="lostseg_len_res_delta_level2")
	private Integer lostsegLenResDeltaLevel2;
	@Column(name="lostseg_len_res_delta_level3")
	private Integer lostsegLenResDeltaLevel3;
	@Column(name="lostseg_len_res_delta_level4")
	private Integer lostsegLenResDeltaLevel4;
	@Column(name="lostseg_len_res_delta_level5")
	private Integer lostsegLenResDeltaLevel5;

	@Column(name="acklost_cnt_req_delta_use")
	private Boolean acklostCntReqDeltaUse;
	@Column(name="acklost_cnt_req_delta_level1")
	private Integer acklostCntReqDeltaLevel1;
	@Column(name="acklost_cnt_req_delta_level2")
	private Integer acklostCntReqDeltaLevel2;
	@Column(name="acklost_cnt_req_delta_level3")
	private Integer acklostCntReqDeltaLevel3;
	@Column(name="acklost_cnt_req_delta_level4")
	private Integer acklostCntReqDeltaLevel4;
	@Column(name="acklost_cnt_req_delta_level5")
	private Integer acklostCntReqDeltaLevel5;

	@Column(name="acklost_cnt_res_delta_use")
	private Boolean acklostCntResDeltaUse;
	@Column(name="acklost_cnt_res_delta_level1")
	private Integer acklostCntResDeltaLevel1;
	@Column(name="acklost_cnt_res_delta_level2")
	private Integer acklostCntResDeltaLevel2;
	@Column(name="acklost_cnt_res_delta_level3")
	private Integer acklostCntResDeltaLevel3;
	@Column(name="acklost_cnt_res_delta_level4")
	private Integer acklostCntResDeltaLevel4;
	@Column(name="acklost_cnt_res_delta_level5")
	private Integer acklostCntResDeltaLevel5;

	@Column(name="acklost_len_req_delta_use")
	private Boolean acklostLenReqDeltaUse;
	@Column(name="acklost_len_req_delta_level1")
	private Integer acklostLenReqDeltaLevel1;
	@Column(name="acklost_len_req_delta_level2")
	private Integer acklostLenReqDeltaLevel2;
	@Column(name="acklost_len_req_delta_level3")
	private Integer acklostLenReqDeltaLevel3;
	@Column(name="acklost_len_req_delta_level4")
	private Integer acklostLenReqDeltaLevel4;
	@Column(name="acklost_len_req_delta_level5")
	private Integer acklostLenReqDeltaLevel5;

	@Column(name="acklost_len_res_delta_use")
	private Boolean acklostLenResDeltaUse;
	@Column(name="acklost_len_res_delta_level1")
	private Integer acklostLenResDeltaLevel1;
	@Column(name="acklost_len_res_delta_level2")
	private Integer acklostLenResDeltaLevel2;
	@Column(name="acklost_len_res_delta_level3")
	private Integer acklostLenResDeltaLevel3;
	@Column(name="acklost_len_res_delta_level4")
	private Integer acklostLenResDeltaLevel4;
	@Column(name="acklost_len_res_delta_level5")
	private Integer acklostLenResDeltaLevel5;

	@Column(name="winupdate_cnt_req_delta_use")
	private Boolean winupdateCntReqDeltaUse;
	@Column(name="winupdate_cnt_req_delta_level1")
	private Integer winupdateCntReqDeltaLevel1;
	@Column(name="winupdate_cnt_req_delta_level2")
	private Integer winupdateCntReqDeltaLevel2;
	@Column(name="winupdate_cnt_req_delta_level3")
	private Integer winupdateCntReqDeltaLevel3;
	@Column(name="winupdate_cnt_req_delta_level4")
	private Integer winupdateCntReqDeltaLevel4;
	@Column(name="winupdate_cnt_req_delta_level5")
	private Integer winupdateCntReqDeltaLevel5;

	@Column(name="winupdate_cnt_res_delta_use")
	private Boolean winupdateCntResDeltaUse;
	@Column(name="winupdate_cnt_res_delta_level1")
	private Integer winupdateCntResDeltaLevel1;
	@Column(name="winupdate_cnt_res_delta_level2")
	private Integer winupdateCntResDeltaLevel2;
	@Column(name="winupdate_cnt_res_delta_level3")
	private Integer winupdateCntResDeltaLevel3;
	@Column(name="winupdate_cnt_res_delta_level4")
	private Integer winupdateCntResDeltaLevel4;
	@Column(name="winupdate_cnt_res_delta_level5")
	private Integer winupdateCntResDeltaLevel5;

	@Column(name="winupdate_len_req_delta_use")
	private Boolean winupdateLenReqDeltaUse;
	@Column(name="winupdate_len_req_delta_level1")
	private Integer winupdateLenReqDeltaLevel1;
	@Column(name="winupdate_len_req_delta_level2")
	private Integer winupdateLenReqDeltaLevel2;
	@Column(name="winupdate_len_req_delta_level3")
	private Integer winupdateLenReqDeltaLevel3;
	@Column(name="winupdate_len_req_delta_level4")
	private Integer winupdateLenReqDeltaLevel4;
	@Column(name="winupdate_len_req_delta_level5")
	private Integer winupdateLenReqDeltaLevel5;

	@Column(name="winupdate_len_res_delta_use")
	private Boolean winupdateLenResDeltaUse;
	@Column(name="winupdate_len_res_delta_level1")
	private Integer winupdateLenResDeltaLevel1;
	@Column(name="winupdate_len_res_delta_level2")
	private Integer winupdateLenResDeltaLevel2;
	@Column(name="winupdate_len_res_delta_level3")
	private Integer winupdateLenResDeltaLevel3;
	@Column(name="winupdate_len_res_delta_level4")
	private Integer winupdateLenResDeltaLevel4;
	@Column(name="winupdate_len_res_delta_level5")
	private Integer winupdateLenResDeltaLevel5;

	@Column(name="dupack_cnt_req_delta_use")
	private Boolean dupackCntReqDeltaUse;
	@Column(name="dupack_cnt_req_delta_level1")
	private Integer dupackCntReqDeltaLevel1;
	@Column(name="dupack_cnt_req_delta_level2")
	private Integer dupackCntReqDeltaLevel2;
	@Column(name="dupack_cnt_req_delta_level3")
	private Integer dupackCntReqDeltaLevel3;
	@Column(name="dupack_cnt_req_delta_level4")
	private Integer dupackCntReqDeltaLevel4;
	@Column(name="dupack_cnt_req_delta_level5")
	private Integer dupackCntReqDeltaLevel5;

	@Column(name="dupack_cnt_res_delta_use")
	private Boolean dupackCntResDeltaUse;
	@Column(name="dupack_cnt_res_delta_level1")
	private Integer dupackCntResDeltaLevel1;
	@Column(name="dupack_cnt_res_delta_level2")
	private Integer dupackCntResDeltaLevel2;
	@Column(name="dupack_cnt_res_delta_level3")
	private Integer dupackCntResDeltaLevel3;
	@Column(name="dupack_cnt_res_delta_level4")
	private Integer dupackCntResDeltaLevel4;
	@Column(name="dupack_cnt_res_delta_level5")
	private Integer dupackCntResDeltaLevel5;

	@Column(name="dupack_len_req_delta_use")
	private Boolean dupackLenReqDeltaUse;
	@Column(name="dupack_len_req_delta_level1")
	private Integer dupackLenReqDeltaLevel1;
	@Column(name="dupack_len_req_delta_level2")
	private Integer dupackLenReqDeltaLevel2;
	@Column(name="dupack_len_req_delta_level3")
	private Integer dupackLenReqDeltaLevel3;
	@Column(name="dupack_len_req_delta_level4")
	private Integer dupackLenReqDeltaLevel4;
	@Column(name="dupack_len_req_delta_level5")
	private Integer dupackLenReqDeltaLevel5;

	@Column(name="dupack_len_res_delta_use")
	private Boolean dupackLenResDeltaUse;
	@Column(name="dupack_len_res_delta_level1")
	private Integer dupackLenResDeltaLevel1;
	@Column(name="dupack_len_res_delta_level2")
	private Integer dupackLenResDeltaLevel2;
	@Column(name="dupack_len_res_delta_level3")
	private Integer dupackLenResDeltaLevel3;
	@Column(name="dupack_len_res_delta_level4")
	private Integer dupackLenResDeltaLevel4;
	@Column(name="dupack_len_res_delta_level5")
	private Integer dupackLenResDeltaLevel5;

	@Column(name="zerowin_cnt_req_delta_use")
	private Boolean zerowinCntReqDeltaUse;
	@Column(name="zerowin_cnt_req_delta_level1")
	private Integer zerowinCntReqDeltaLevel1;
	@Column(name="zerowin_cnt_req_delta_level2")
	private Integer zerowinCntReqDeltaLevel2;
	@Column(name="zerowin_cnt_req_delta_level3")
	private Integer zerowinCntReqDeltaLevel3;
	@Column(name="zerowin_cnt_req_delta_level4")
	private Integer zerowinCntReqDeltaLevel4;
	@Column(name="zerowin_cnt_req_delta_level5")
	private Integer zerowinCntReqDeltaLevel5;

	@Column(name="zerowin_cnt_res_delta_use")
	private Boolean zerowinCntResDeltaUse;
	@Column(name="zerowin_cnt_res_delta_level1")
	private Integer zerowinCntResDeltaLevel1;
	@Column(name="zerowin_cnt_res_delta_level2")
	private Integer zerowinCntResDeltaLevel2;
	@Column(name="zerowin_cnt_res_delta_level3")
	private Integer zerowinCntResDeltaLevel3;
	@Column(name="zerowin_cnt_res_delta_level4")
	private Integer zerowinCntResDeltaLevel4;
	@Column(name="zerowin_cnt_res_delta_level5")
	private Integer zerowinCntResDeltaLevel5;

	@Column(name="zerowin_len_req_delta_use")
	private Boolean zerowinLenReqDeltaUse;
	@Column(name="zerowin_len_req_delta_level1")
	private Integer zerowinLenReqDeltaLevel1;
	@Column(name="zerowin_len_req_delta_level2")
	private Integer zerowinLenReqDeltaLevel2;
	@Column(name="zerowin_len_req_delta_level3")
	private Integer zerowinLenReqDeltaLevel3;
	@Column(name="zerowin_len_req_delta_level4")
	private Integer zerowinLenReqDeltaLevel4;
	@Column(name="zerowin_len_req_delta_level5")
	private Integer zerowinLenReqDeltaLevel5;

	@Column(name="zerowin_len_res_delta_use")
	private Boolean zerowinLenResDeltaUse;
	@Column(name="zerowin_len_res_delta_level1")
	private Integer zerowinLenResDeltaLevel1;
	@Column(name="zerowin_len_res_delta_level2")
	private Integer zerowinLenResDeltaLevel2;
	@Column(name="zerowin_len_res_delta_level3")
	private Integer zerowinLenResDeltaLevel3;
	@Column(name="zerowin_len_res_delta_level4")
	private Integer zerowinLenResDeltaLevel4;
	@Column(name="zerowin_len_res_delta_level5")
	private Integer zerowinLenResDeltaLevel5;

	@Column(name="spurious_retransmission_cnt_req_tot_use")
	private Boolean spuriousRetransmissionCntReqTotUse;
	@Column(name="spurious_retransmission_cnt_req_tot_level1")
	private Integer spuriousRetransmissionCntReqTotLevel1;
	@Column(name="spurious_retransmission_cnt_req_tot_level2")
	private Integer spuriousRetransmissionCntReqTotLevel2;
	@Column(name="spurious_retransmission_cnt_req_tot_level3")
	private Integer spuriousRetransmissionCntReqTotLevel3;
	@Column(name="spurious_retransmission_cnt_req_tot_level4")
	private Integer spuriousRetransmissionCntReqTotLevel4;
	@Column(name="spurious_retransmission_cnt_req_tot_level5")
	private Integer spuriousRetransmissionCntReqTotLevel5;

	@Column(name="spurious_retransmission_cnt_res_tot_use")
	private Boolean spuriousRetransmissionCntResTotUse;
	@Column(name="spurious_retransmission_cnt_res_tot_level1")
	private Integer spuriousRetransmissionCntResTotLevel1;
	@Column(name="spurious_retransmission_cnt_res_tot_level2")
	private Integer spuriousRetransmissionCntResTotLevel2;
	@Column(name="spurious_retransmission_cnt_res_tot_level3")
	private Integer spuriousRetransmissionCntResTotLevel3;
	@Column(name="spurious_retransmission_cnt_res_tot_level4")
	private Integer spuriousRetransmissionCntResTotLevel4;
	@Column(name="spurious_retransmission_cnt_res_tot_level5")
	private Integer spuriousRetransmissionCntResTotLevel5;

	@Column(name="spurious_retransmission_len_req_tot_use")
	private Boolean spuriousRetransmissionLenReqTotUse;
	@Column(name="spurious_retransmission_len_req_tot_level1")
	private Integer spuriousRetransmissionLenReqTotLevel1;
	@Column(name="spurious_retransmission_len_req_tot_level2")
	private Integer spuriousRetransmissionLenReqTotLevel2;
	@Column(name="spurious_retransmission_len_req_tot_level3")
	private Integer spuriousRetransmissionLenReqTotLevel3;
	@Column(name="spurious_retransmission_len_req_tot_level4")
	private Integer spuriousRetransmissionLenReqTotLevel4;
	@Column(name="spurious_retransmission_len_req_tot_level5")
	private Integer spuriousRetransmissionLenReqTotLevel5;

	@Column(name="spurious_retransmission_len_res_tot_use")
	private Boolean spuriousRetransmissionLenResTotUse;
	@Column(name="spurious_retransmission_len_res_tot_level1")
	private Integer spuriousRetransmissionLenResTotLevel1;
	@Column(name="spurious_retransmission_len_res_tot_level2")
	private Integer spuriousRetransmissionLenResTotLevel2;
	@Column(name="spurious_retransmission_len_res_tot_level3")
	private Integer spuriousRetransmissionLenResTotLevel3;
	@Column(name="spurious_retransmission_len_res_tot_level4")
	private Integer spuriousRetransmissionLenResTotLevel4;
	@Column(name="spurious_retransmission_len_res_tot_level5")
	private Integer spuriousRetransmissionLenResTotLevel5;

	@Column(name="spurious_retransmission_cnt_req_per_sec_use")
	private Boolean spuriousRetransmissionCntReqPerSecUse;
	@Column(name="spurious_retransmission_cnt_req_per_sec_level1")
	private Integer spuriousRetransmissionCntReqPerSecLevel1;
	@Column(name="spurious_retransmission_cnt_req_per_sec_level2")
	private Integer spuriousRetransmissionCntReqPerSecLevel2;
	@Column(name="spurious_retransmission_cnt_req_per_sec_level3")
	private Integer spuriousRetransmissionCntReqPerSecLevel3;
	@Column(name="spurious_retransmission_cnt_req_per_sec_level4")
	private Integer spuriousRetransmissionCntReqPerSecLevel4;
	@Column(name="spurious_retransmission_cnt_req_per_sec_level5")
	private Integer spuriousRetransmissionCntReqPerSecLevel5;

	@Column(name="spurious_retransmission_cnt_res_per_sec_use")
	private Boolean spuriousRetransmissionCntResPerSecUse;
	@Column(name="spurious_retransmission_cnt_res_per_sec_level1")
	private Integer spuriousRetransmissionCntResPerSecLevel1;
	@Column(name="spurious_retransmission_cnt_res_per_sec_level2")
	private Integer spuriousRetransmissionCntResPerSecLevel2;
	@Column(name="spurious_retransmission_cnt_res_per_sec_level3")
	private Integer spuriousRetransmissionCntResPerSecLevel3;
	@Column(name="spurious_retransmission_cnt_res_per_sec_level4")
	private Integer spuriousRetransmissionCntResPerSecLevel4;
	@Column(name="spurious_retransmission_cnt_res_per_sec_level5")
	private Integer spuriousRetransmissionCntResPerSecLevel5;

	@Column(name="spurious_retransmission_len_req_per_sec_use")
	private Boolean spuriousRetransmissionLenReqPerSecUse;
	@Column(name="spurious_retransmission_len_req_per_sec_level1")
	private Integer spuriousRetransmissionLenReqPerSecLevel1;
	@Column(name="spurious_retransmission_len_req_per_sec_level2")
	private Integer spuriousRetransmissionLenReqPerSecLevel2;
	@Column(name="spurious_retransmission_len_req_per_sec_level3")
	private Integer spuriousRetransmissionLenReqPerSecLevel3;
	@Column(name="spurious_retransmission_len_req_per_sec_level4")
	private Integer spuriousRetransmissionLenReqPerSecLevel4;
	@Column(name="spurious_retransmission_len_req_per_sec_level5")
	private Integer spuriousRetransmissionLenReqPerSecLevel5;

	@Column(name="spurious_retransmission_len_res_per_sec_use")
	private Boolean spuriousRetransmissionLenResPerSecUse;
	@Column(name="spurious_retransmission_len_res_per_sec_level1")
	private Integer spuriousRetransmissionLenResPerSecLevel1;
	@Column(name="spurious_retransmission_len_res_per_sec_level2")
	private Integer spuriousRetransmissionLenResPerSecLevel2;
	@Column(name="spurious_retransmission_len_res_per_sec_level3")
	private Integer spuriousRetransmissionLenResPerSecLevel3;
	@Column(name="spurious_retransmission_len_res_per_sec_level4")
	private Integer spuriousRetransmissionLenResPerSecLevel4;
	@Column(name="spurious_retransmission_len_res_per_sec_level5")
	private Integer spuriousRetransmissionLenResPerSecLevel5;

	@Column(name="spurious_retransmission_cnt_req_delta_use")
	private Boolean spuriousRetransmissionCntReqDeltaUse;
	@Column(name="spurious_retransmission_cnt_req_delta_level1")
	private Integer spuriousRetransmissionCntReqDeltaLevel1;
	@Column(name="spurious_retransmission_cnt_req_delta_level2")
	private Integer spuriousRetransmissionCntReqDeltaLevel2;
	@Column(name="spurious_retransmission_cnt_req_delta_level3")
	private Integer spuriousRetransmissionCntReqDeltaLevel3;
	@Column(name="spurious_retransmission_cnt_req_delta_level4")
	private Integer spuriousRetransmissionCntReqDeltaLevel4;
	@Column(name="spurious_retransmission_cnt_req_delta_level5")
	private Integer spuriousRetransmissionCntReqDeltaLevel5;

	@Column(name="spurious_retransmission_cnt_res_delta_use")
	private Boolean spuriousRetransmissionCntResDeltaUse;
	@Column(name="spurious_retransmission_cnt_res_delta_level1")
	private Integer spuriousRetransmissionCntResDeltaLevel1;
	@Column(name="spurious_retransmission_cnt_res_delta_level2")
	private Integer spuriousRetransmissionCntResDeltaLevel2;
	@Column(name="spurious_retransmission_cnt_res_delta_level3")
	private Integer spuriousRetransmissionCntResDeltaLevel3;
	@Column(name="spurious_retransmission_cnt_res_delta_level4")
	private Integer spuriousRetransmissionCntResDeltaLevel4;
	@Column(name="spurious_retransmission_cnt_res_delta_level5")
	private Integer spuriousRetransmissionCntResDeltaLevel5;

	@Column(name="spurious_retransmission_len_req_delta_use")
	private Boolean spuriousRetransmissionLenReqDeltaUse;
	@Column(name="spurious_retransmission_len_req_delta_level1")
	private Integer spuriousRetransmissionLenReqDeltaLevel1;
	@Column(name="spurious_retransmission_len_req_delta_level2")
	private Integer spuriousRetransmissionLenReqDeltaLevel2;
	@Column(name="spurious_retransmission_len_req_delta_level3")
	private Integer spuriousRetransmissionLenReqDeltaLevel3;
	@Column(name="spurious_retransmission_len_req_delta_level4")
	private Integer spuriousRetransmissionLenReqDeltaLevel4;
	@Column(name="spurious_retransmission_len_req_delta_level5")
	private Integer spuriousRetransmissionLenReqDeltaLevel5;

	@Column(name="spurious_retransmission_len_res_delta_use")
	private Boolean spuriousRetransmissionLenResDeltaUse;
	@Column(name="spurious_retransmission_len_res_delta_level1")
	private Integer spuriousRetransmissionLenResDeltaLevel1;
	@Column(name="spurious_retransmission_len_res_delta_level2")
	private Integer spuriousRetransmissionLenResDeltaLevel2;
	@Column(name="spurious_retransmission_len_res_delta_level3")
	private Integer spuriousRetransmissionLenResDeltaLevel3;
	@Column(name="spurious_retransmission_len_res_delta_level4")
	private Integer spuriousRetransmissionLenResDeltaLevel4;
	@Column(name="spurious_retransmission_len_res_delta_level5")
	private Integer spuriousRetransmissionLenResDeltaLevel5;

	@Column(name="overlap_cnt_req_tot_use")
	private Boolean overlapCntReqTotUse;
	@Column(name="overlap_cnt_req_tot_level1")
	private Integer overlapCntReqTotLevel1;
	@Column(name="overlap_cnt_req_tot_level2")
	private Integer overlapCntReqTotLevel2;
	@Column(name="overlap_cnt_req_tot_level3")
	private Integer overlapCntReqTotLevel3;
	@Column(name="overlap_cnt_req_tot_level4")
	private Integer overlapCntReqTotLevel4;
	@Column(name="overlap_cnt_req_tot_level5")
	private Integer overlapCntReqTotLevel5;

	@Column(name="overlap_cnt_res_tot_use")
	private Boolean overlapCntResTotUse;
	@Column(name="overlap_cnt_res_tot_level1")
	private Integer overlapCntResTotLevel1;
	@Column(name="overlap_cnt_res_tot_level2")
	private Integer overlapCntResTotLevel2;
	@Column(name="overlap_cnt_res_tot_level3")
	private Integer overlapCntResTotLevel3;
	@Column(name="overlap_cnt_res_tot_level4")
	private Integer overlapCntResTotLevel4;
	@Column(name="overlap_cnt_res_tot_level5")
	private Integer overlapCntResTotLevel5;

	@Column(name="overlap_len_req_tot_use")
	private Boolean overlapLenReqTotUse;
	@Column(name="overlap_len_req_tot_level1")
	private Integer overlapLenReqTotLevel1;
	@Column(name="overlap_len_req_tot_level2")
	private Integer overlapLenReqTotLevel2;
	@Column(name="overlap_len_req_tot_level3")
	private Integer overlapLenReqTotLevel3;
	@Column(name="overlap_len_req_tot_level4")
	private Integer overlapLenReqTotLevel4;
	@Column(name="overlap_len_req_tot_level5")
	private Integer overlapLenReqTotLevel5;

	@Column(name="overlap_len_res_tot_use")
	private Boolean overlapLenResTotUse;
	@Column(name="overlap_len_res_tot_level1")
	private Integer overlapLenResTotLevel1;
	@Column(name="overlap_len_res_tot_level2")
	private Integer overlapLenResTotLevel2;
	@Column(name="overlap_len_res_tot_level3")
	private Integer overlapLenResTotLevel3;
	@Column(name="overlap_len_res_tot_level4")
	private Integer overlapLenResTotLevel4;
	@Column(name="overlap_len_res_tot_level5")
	private Integer overlapLenResTotLevel5;

	@Column(name="overlap_cnt_req_per_sec_use")
	private Boolean overlapCntReqPerSecUse;
	@Column(name="overlap_cnt_req_per_sec_level1")
	private Integer overlapCntReqPerSecLevel1;
	@Column(name="overlap_cnt_req_per_sec_level2")
	private Integer overlapCntReqPerSecLevel2;
	@Column(name="overlap_cnt_req_per_sec_level3")
	private Integer overlapCntReqPerSecLevel3;
	@Column(name="overlap_cnt_req_per_sec_level4")
	private Integer overlapCntReqPerSecLevel4;
	@Column(name="overlap_cnt_req_per_sec_level5")
	private Integer overlapCntReqPerSecLevel5;

	@Column(name="overlap_cnt_res_per_sec_use")
	private Boolean overlapCntResPerSecUse;
	@Column(name="overlap_cnt_res_per_sec_level1")
	private Integer overlapCntResPerSecLevel1;
	@Column(name="overlap_cnt_res_per_sec_level2")
	private Integer overlapCntResPerSecLevel2;
	@Column(name="overlap_cnt_res_per_sec_level3")
	private Integer overlapCntResPerSecLevel3;
	@Column(name="overlap_cnt_res_per_sec_level4")
	private Integer overlapCntResPerSecLevel4;
	@Column(name="overlap_cnt_res_per_sec_level5")
	private Integer overlapCntResPerSecLevel5;

	@Column(name="overlap_len_req_per_sec_use")
	private Boolean overlapLenReqPerSecUse;
	@Column(name="overlap_len_req_per_sec_level1")
	private Integer overlapLenReqPerSecLevel1;
	@Column(name="overlap_len_req_per_sec_level2")
	private Integer overlapLenReqPerSecLevel2;
	@Column(name="overlap_len_req_per_sec_level3")
	private Integer overlapLenReqPerSecLevel3;
	@Column(name="overlap_len_req_per_sec_level4")
	private Integer overlapLenReqPerSecLevel4;
	@Column(name="overlap_len_req_per_sec_level5")
	private Integer overlapLenReqPerSecLevel5;

	@Column(name="overlap_len_res_per_sec_use")
	private Boolean overlapLenResPerSecUse;
	@Column(name="overlap_len_res_per_sec_level1")
	private Integer overlapLenResPerSecLevel1;
	@Column(name="overlap_len_res_per_sec_level2")
	private Integer overlapLenResPerSecLevel2;
	@Column(name="overlap_len_res_per_sec_level3")
	private Integer overlapLenResPerSecLevel3;
	@Column(name="overlap_len_res_per_sec_level4")
	private Integer overlapLenResPerSecLevel4;
	@Column(name="overlap_len_res_per_sec_level5")
	private Integer overlapLenResPerSecLevel5;

	@Column(name="overlap_cnt_req_delta_use")
	private Boolean overlapCntReqDeltaUse;
	@Column(name="overlap_cnt_req_delta_level1")
	private Integer overlapCntReqDeltaLevel1;
	@Column(name="overlap_cnt_req_delta_level2")
	private Integer overlapCntReqDeltaLevel2;
	@Column(name="overlap_cnt_req_delta_level3")
	private Integer overlapCntReqDeltaLevel3;
	@Column(name="overlap_cnt_req_delta_level4")
	private Integer overlapCntReqDeltaLevel4;
	@Column(name="overlap_cnt_req_delta_level5")
	private Integer overlapCntReqDeltaLevel5;

	@Column(name="overlap_cnt_res_delta_use")
	private Boolean overlapCntResDeltaUse;
	@Column(name="overlap_cnt_res_delta_level1")
	private Integer overlapCntResDeltaLevel1;
	@Column(name="overlap_cnt_res_delta_level2")
	private Integer overlapCntResDeltaLevel2;
	@Column(name="overlap_cnt_res_delta_level3")
	private Integer overlapCntResDeltaLevel3;
	@Column(name="overlap_cnt_res_delta_level4")
	private Integer overlapCntResDeltaLevel4;
	@Column(name="overlap_cnt_res_delta_level5")
	private Integer overlapCntResDeltaLevel5;

	@Column(name="overlap_len_req_delta_use")
	private Boolean overlapLenReqDeltaUse;
	@Column(name="overlap_len_req_delta_level1")
	private Integer overlapLenReqDeltaLevel1;
	@Column(name="overlap_len_req_delta_level2")
	private Integer overlapLenReqDeltaLevel2;
	@Column(name="overlap_len_req_delta_level3")
	private Integer overlapLenReqDeltaLevel3;
	@Column(name="overlap_len_req_delta_level4")
	private Integer overlapLenReqDeltaLevel4;
	@Column(name="overlap_len_req_delta_level5")
	private Integer overlapLenReqDeltaLevel5;

	@Column(name="overlap_len_res_delta_use")
	private Boolean overlapLenResDeltaUse;
	@Column(name="overlap_len_res_delta_level1")
	private Integer overlapLenResDeltaLevel1;
	@Column(name="overlap_len_res_delta_level2")
	private Integer overlapLenResDeltaLevel2;
	@Column(name="overlap_len_res_delta_level3")
	private Integer overlapLenResDeltaLevel3;
	@Column(name="overlap_len_res_delta_level4")
	private Integer overlapLenResDeltaLevel4;
	@Column(name="overlap_len_res_delta_level5")
	private Integer overlapLenResDeltaLevel5;

	@Column(name="overlap_attack_cnt_req_tot_use")
	private Boolean overlapAttackCntReqTotUse;
	@Column(name="overlap_attack_cnt_req_tot_level1")
	private Integer overlapAttackCntReqTotLevel1;
	@Column(name="overlap_attack_cnt_req_tot_level2")
	private Integer overlapAttackCntReqTotLevel2;
	@Column(name="overlap_attack_cnt_req_tot_level3")
	private Integer overlapAttackCntReqTotLevel3;
	@Column(name="overlap_attack_cnt_req_tot_level4")
	private Integer overlapAttackCntReqTotLevel4;
	@Column(name="overlap_attack_cnt_req_tot_level5")
	private Integer overlapAttackCntReqTotLevel5;

	@Column(name="overlap_attack_cnt_res_tot_use")
	private Boolean overlapAttackCntResTotUse;
	@Column(name="overlap_attack_cnt_res_tot_level1")
	private Integer overlapAttackCntResTotLevel1;
	@Column(name="overlap_attack_cnt_res_tot_level2")
	private Integer overlapAttackCntResTotLevel2;
	@Column(name="overlap_attack_cnt_res_tot_level3")
	private Integer overlapAttackCntResTotLevel3;
	@Column(name="overlap_attack_cnt_res_tot_level4")
	private Integer overlapAttackCntResTotLevel4;
	@Column(name="overlap_attack_cnt_res_tot_level5")
	private Integer overlapAttackCntResTotLevel5;

	@Column(name="overlap_attack_len_req_tot_use")
	private Boolean overlapAttackLenReqTotUse;
	@Column(name="overlap_attack_len_req_tot_level1")
	private Integer overlapAttackLenReqTotLevel1;
	@Column(name="overlap_attack_len_req_tot_level2")
	private Integer overlapAttackLenReqTotLevel2;
	@Column(name="overlap_attack_len_req_tot_level3")
	private Integer overlapAttackLenReqTotLevel3;
	@Column(name="overlap_attack_len_req_tot_level4")
	private Integer overlapAttackLenReqTotLevel4;
	@Column(name="overlap_attack_len_req_tot_level5")
	private Integer overlapAttackLenReqTotLevel5;

	@Column(name="overlap_attack_len_res_tot_use")
	private Boolean overlapAttackLenResTotUse;
	@Column(name="overlap_attack_len_res_tot_level1")
	private Integer overlapAttackLenResTotLevel1;
	@Column(name="overlap_attack_len_res_tot_level2")
	private Integer overlapAttackLenResTotLevel2;
	@Column(name="overlap_attack_len_res_tot_level3")
	private Integer overlapAttackLenResTotLevel3;
	@Column(name="overlap_attack_len_res_tot_level4")
	private Integer overlapAttackLenResTotLevel4;
	@Column(name="overlap_attack_len_res_tot_level5")
	private Integer overlapAttackLenResTotLevel5;

	@Column(name="overlap_attack_cnt_req_per_sec_use")
	private Boolean overlapAttackCntReqPerSecUse;
	@Column(name="overlap_attack_cnt_req_per_sec_level1")
	private Integer overlapAttackCntReqPerSecLevel1;
	@Column(name="overlap_attack_cnt_req_per_sec_level2")
	private Integer overlapAttackCntReqPerSecLevel2;
	@Column(name="overlap_attack_cnt_req_per_sec_level3")
	private Integer overlapAttackCntReqPerSecLevel3;
	@Column(name="overlap_attack_cnt_req_per_sec_level4")
	private Integer overlapAttackCntReqPerSecLevel4;
	@Column(name="overlap_attack_cnt_req_per_sec_level5")
	private Integer overlapAttackCntReqPerSecLevel5;

	@Column(name="overlap_attack_cnt_res_per_sec_use")
	private Boolean overlapAttackCntResPerSecUse;
	@Column(name="overlap_attack_cnt_res_per_sec_level1")
	private Integer overlapAttackCntResPerSecLevel1;
	@Column(name="overlap_attack_cnt_res_per_sec_level2")
	private Integer overlapAttackCntResPerSecLevel2;
	@Column(name="overlap_attack_cnt_res_per_sec_level3")
	private Integer overlapAttackCntResPerSecLevel3;
	@Column(name="overlap_attack_cnt_res_per_sec_level4")
	private Integer overlapAttackCntResPerSecLevel4;
	@Column(name="overlap_attack_cnt_res_per_sec_level5")
	private Integer overlapAttackCntResPerSecLevel5;

	@Column(name="overlap_attack_len_req_per_sec_use")
	private Boolean overlapAttackLenReqPerSecUse;
	@Column(name="overlap_attack_len_req_per_sec_level1")
	private Integer overlapAttackLenReqPerSecLevel1;
	@Column(name="overlap_attack_len_req_per_sec_level2")
	private Integer overlapAttackLenReqPerSecLevel2;
	@Column(name="overlap_attack_len_req_per_sec_level3")
	private Integer overlapAttackLenReqPerSecLevel3;
	@Column(name="overlap_attack_len_req_per_sec_level4")
	private Integer overlapAttackLenReqPerSecLevel4;
	@Column(name="overlap_attack_len_req_per_sec_level5")
	private Integer overlapAttackLenReqPerSecLevel5;

	@Column(name="overlap_attack_len_res_per_sec_use")
	private Boolean overlapAttackLenResPerSecUse;
	@Column(name="overlap_attack_len_res_per_sec_level1")
	private Integer overlapAttackLenResPerSecLevel1;
	@Column(name="overlap_attack_len_res_per_sec_level2")
	private Integer overlapAttackLenResPerSecLevel2;
	@Column(name="overlap_attack_len_res_per_sec_level3")
	private Integer overlapAttackLenResPerSecLevel3;
	@Column(name="overlap_attack_len_res_per_sec_level4")
	private Integer overlapAttackLenResPerSecLevel4;
	@Column(name="overlap_attack_len_res_per_sec_level5")
	private Integer overlapAttackLenResPerSecLevel5;

	@Column(name="overlap_attack_cnt_req_delta_use")
	private Boolean overlapAttackCntReqDeltaUse;
	@Column(name="overlap_attack_cnt_req_delta_level1")
	private Integer overlapAttackCntReqDeltaLevel1;
	@Column(name="overlap_attack_cnt_req_delta_level2")
	private Integer overlapAttackCntReqDeltaLevel2;
	@Column(name="overlap_attack_cnt_req_delta_level3")
	private Integer overlapAttackCntReqDeltaLevel3;
	@Column(name="overlap_attack_cnt_req_delta_level4")
	private Integer overlapAttackCntReqDeltaLevel4;
	@Column(name="overlap_attack_cnt_req_delta_level5")
	private Integer overlapAttackCntReqDeltaLevel5;

	@Column(name="overlap_attack_cnt_res_delta_use")
	private Boolean overlapAttackCntResDeltaUse;
	@Column(name="overlap_attack_cnt_res_delta_level1")
	private Integer overlapAttackCntResDeltaLevel1;
	@Column(name="overlap_attack_cnt_res_delta_level2")
	private Integer overlapAttackCntResDeltaLevel2;
	@Column(name="overlap_attack_cnt_res_delta_level3")
	private Integer overlapAttackCntResDeltaLevel3;
	@Column(name="overlap_attack_cnt_res_delta_level4")
	private Integer overlapAttackCntResDeltaLevel4;
	@Column(name="overlap_attack_cnt_res_delta_level5")
	private Integer overlapAttackCntResDeltaLevel5;

	@Column(name="overlap_attack_len_req_delta_use")
	private Boolean overlapAttackLenReqDeltaUse;
	@Column(name="overlap_attack_len_req_delta_level1")
	private Integer overlapAttackLenReqDeltaLevel1;
	@Column(name="overlap_attack_len_req_delta_level2")
	private Integer overlapAttackLenReqDeltaLevel2;
	@Column(name="overlap_attack_len_req_delta_level3")
	private Integer overlapAttackLenReqDeltaLevel3;
	@Column(name="overlap_attack_len_req_delta_level4")
	private Integer overlapAttackLenReqDeltaLevel4;
	@Column(name="overlap_attack_len_req_delta_level5")
	private Integer overlapAttackLenReqDeltaLevel5;

	@Column(name="overlap_attack_len_res_delta_use")
	private Boolean overlapAttackLenResDeltaUse;
	@Column(name="overlap_attack_len_res_delta_level1")
	private Integer overlapAttackLenResDeltaLevel1;
	@Column(name="overlap_attack_len_res_delta_level2")
	private Integer overlapAttackLenResDeltaLevel2;
	@Column(name="overlap_attack_len_res_delta_level3")
	private Integer overlapAttackLenResDeltaLevel3;
	@Column(name="overlap_attack_len_res_delta_level4")
	private Integer overlapAttackLenResDeltaLevel4;
	@Column(name="overlap_attack_len_res_delta_level5")
	private Integer overlapAttackLenResDeltaLevel5;

	@Column(name="zero_win_probe_cnt_req_tot_use")
	private Boolean zeroWinProbeCntReqTotUse;
	@Column(name="zero_win_probe_cnt_req_tot_level1")
	private Integer zeroWinProbeCntReqTotLevel1;
	@Column(name="zero_win_probe_cnt_req_tot_level2")
	private Integer zeroWinProbeCntReqTotLevel2;
	@Column(name="zero_win_probe_cnt_req_tot_level3")
	private Integer zeroWinProbeCntReqTotLevel3;
	@Column(name="zero_win_probe_cnt_req_tot_level4")
	private Integer zeroWinProbeCntReqTotLevel4;
	@Column(name="zero_win_probe_cnt_req_tot_level5")
	private Integer zeroWinProbeCntReqTotLevel5;

	@Column(name="zero_win_probe_cnt_res_tot_use")
	private Boolean zeroWinProbeCntResTotUse;
	@Column(name="zero_win_probe_cnt_res_tot_level1")
	private Integer zeroWinProbeCntResTotLevel1;
	@Column(name="zero_win_probe_cnt_res_tot_level2")
	private Integer zeroWinProbeCntResTotLevel2;
	@Column(name="zero_win_probe_cnt_res_tot_level3")
	private Integer zeroWinProbeCntResTotLevel3;
	@Column(name="zero_win_probe_cnt_res_tot_level4")
	private Integer zeroWinProbeCntResTotLevel4;
	@Column(name="zero_win_probe_cnt_res_tot_level5")
	private Integer zeroWinProbeCntResTotLevel5;

	@Column(name="zero_win_probe_len_req_tot_use")
	private Boolean zeroWinProbeLenReqTotUse;
	@Column(name="zero_win_probe_len_req_tot_level1")
	private Integer zeroWinProbeLenReqTotLevel1;
	@Column(name="zero_win_probe_len_req_tot_level2")
	private Integer zeroWinProbeLenReqTotLevel2;
	@Column(name="zero_win_probe_len_req_tot_level3")
	private Integer zeroWinProbeLenReqTotLevel3;
	@Column(name="zero_win_probe_len_req_tot_level4")
	private Integer zeroWinProbeLenReqTotLevel4;
	@Column(name="zero_win_probe_len_req_tot_level5")
	private Integer zeroWinProbeLenReqTotLevel5;

	@Column(name="zero_win_probe_len_res_tot_use")
	private Boolean zeroWinProbeLenResTotUse;
	@Column(name="zero_win_probe_len_res_tot_level1")
	private Integer zeroWinProbeLenResTotLevel1;
	@Column(name="zero_win_probe_len_res_tot_level2")
	private Integer zeroWinProbeLenResTotLevel2;
	@Column(name="zero_win_probe_len_res_tot_level3")
	private Integer zeroWinProbeLenResTotLevel3;
	@Column(name="zero_win_probe_len_res_tot_level4")
	private Integer zeroWinProbeLenResTotLevel4;
	@Column(name="zero_win_probe_len_res_tot_level5")
	private Integer zeroWinProbeLenResTotLevel5;

	@Column(name="zero_win_probe_cnt_req_per_sec_use")
	private Boolean zeroWinProbeCntReqPerSecUse;
	@Column(name="zero_win_probe_cnt_req_per_sec_level1")
	private Integer zeroWinProbeCntReqPerSecLevel1;
	@Column(name="zero_win_probe_cnt_req_per_sec_level2")
	private Integer zeroWinProbeCntReqPerSecLevel2;
	@Column(name="zero_win_probe_cnt_req_per_sec_level3")
	private Integer zeroWinProbeCntReqPerSecLevel3;
	@Column(name="zero_win_probe_cnt_req_per_sec_level4")
	private Integer zeroWinProbeCntReqPerSecLevel4;
	@Column(name="zero_win_probe_cnt_req_per_sec_level5")
	private Integer zeroWinProbeCntReqPerSecLevel5;

	@Column(name="zero_win_probe_cnt_res_per_sec_use")
	private Boolean zeroWinProbeCntResPerSecUse;
	@Column(name="zero_win_probe_cnt_res_per_sec_level1")
	private Integer zeroWinProbeCntResPerSecLevel1;
	@Column(name="zero_win_probe_cnt_res_per_sec_level2")
	private Integer zeroWinProbeCntResPerSecLevel2;
	@Column(name="zero_win_probe_cnt_res_per_sec_level3")
	private Integer zeroWinProbeCntResPerSecLevel3;
	@Column(name="zero_win_probe_cnt_res_per_sec_level4")
	private Integer zeroWinProbeCntResPerSecLevel4;
	@Column(name="zero_win_probe_cnt_res_per_sec_level5")
	private Integer zeroWinProbeCntResPerSecLevel5;

	@Column(name="zero_win_probe_len_req_per_sec_use")
	private Boolean zeroWinProbeLenReqPerSecUse;
	@Column(name="zero_win_probe_len_req_per_sec_level1")
	private Integer zeroWinProbeLenReqPerSecLevel1;
	@Column(name="zero_win_probe_len_req_per_sec_level2")
	private Integer zeroWinProbeLenReqPerSecLevel2;
	@Column(name="zero_win_probe_len_req_per_sec_level3")
	private Integer zeroWinProbeLenReqPerSecLevel3;
	@Column(name="zero_win_probe_len_req_per_sec_level4")
	private Integer zeroWinProbeLenReqPerSecLevel4;
	@Column(name="zero_win_probe_len_req_per_sec_level5")
	private Integer zeroWinProbeLenReqPerSecLevel5;

	@Column(name="zero_win_probe_len_res_per_sec_use")
	private Boolean zeroWinProbeLenResPerSecUse;
	@Column(name="zero_win_probe_len_res_per_sec_level1")
	private Integer zeroWinProbeLenResPerSecLevel1;
	@Column(name="zero_win_probe_len_res_per_sec_level2")
	private Integer zeroWinProbeLenResPerSecLevel2;
	@Column(name="zero_win_probe_len_res_per_sec_level3")
	private Integer zeroWinProbeLenResPerSecLevel3;
	@Column(name="zero_win_probe_len_res_per_sec_level4")
	private Integer zeroWinProbeLenResPerSecLevel4;
	@Column(name="zero_win_probe_len_res_per_sec_level5")
	private Integer zeroWinProbeLenResPerSecLevel5;

	@Column(name="zero_win_probe_cnt_req_delta_use")
	private Boolean zeroWinProbeCntReqDeltaUse;
	@Column(name="zero_win_probe_cnt_req_delta_level1")
	private Integer zeroWinProbeCntReqDeltaLevel1;
	@Column(name="zero_win_probe_cnt_req_delta_level2")
	private Integer zeroWinProbeCntReqDeltaLevel2;
	@Column(name="zero_win_probe_cnt_req_delta_level3")
	private Integer zeroWinProbeCntReqDeltaLevel3;
	@Column(name="zero_win_probe_cnt_req_delta_level4")
	private Integer zeroWinProbeCntReqDeltaLevel4;
	@Column(name="zero_win_probe_cnt_req_delta_level5")
	private Integer zeroWinProbeCntReqDeltaLevel5;

	@Column(name="zero_win_probe_cnt_res_delta_use")
	private Boolean zeroWinProbeCntResDeltaUse;
	@Column(name="zero_win_probe_cnt_res_delta_level1")
	private Integer zeroWinProbeCntResDeltaLevel1;
	@Column(name="zero_win_probe_cnt_res_delta_level2")
	private Integer zeroWinProbeCntResDeltaLevel2;
	@Column(name="zero_win_probe_cnt_res_delta_level3")
	private Integer zeroWinProbeCntResDeltaLevel3;
	@Column(name="zero_win_probe_cnt_res_delta_level4")
	private Integer zeroWinProbeCntResDeltaLevel4;
	@Column(name="zero_win_probe_cnt_res_delta_level5")
	private Integer zeroWinProbeCntResDeltaLevel5;

	@Column(name="zero_win_probe_len_req_delta_use")
	private Boolean zeroWinProbeLenReqDeltaUse;
	@Column(name="zero_win_probe_len_req_delta_level1")
	private Integer zeroWinProbeLenReqDeltaLevel1;
	@Column(name="zero_win_probe_len_req_delta_level2")
	private Integer zeroWinProbeLenReqDeltaLevel2;
	@Column(name="zero_win_probe_len_req_delta_level3")
	private Integer zeroWinProbeLenReqDeltaLevel3;
	@Column(name="zero_win_probe_len_req_delta_level4")
	private Integer zeroWinProbeLenReqDeltaLevel4;
	@Column(name="zero_win_probe_len_req_delta_level5")
	private Integer zeroWinProbeLenReqDeltaLevel5;

	@Column(name="zero_win_probe_len_res_delta_use")
	private Boolean zeroWinProbeLenResDeltaUse;
	@Column(name="zero_win_probe_len_res_delta_level1")
	private Integer zeroWinProbeLenResDeltaLevel1;
	@Column(name="zero_win_probe_len_res_delta_level2")
	private Integer zeroWinProbeLenResDeltaLevel2;
	@Column(name="zero_win_probe_len_res_delta_level3")
	private Integer zeroWinProbeLenResDeltaLevel3;
	@Column(name="zero_win_probe_len_res_delta_level4")
	private Integer zeroWinProbeLenResDeltaLevel4;
	@Column(name="zero_win_probe_len_res_delta_level5")
	private Integer zeroWinProbeLenResDeltaLevel5;

	@Column(name="zero_win_probe_ack_cnt_req_tot_use")
	private Boolean zeroWinProbeAckCntReqTotUse;
	@Column(name="zero_win_probe_ack_cnt_req_tot_level1")
	private Integer zeroWinProbeAckCntReqTotLevel1;
	@Column(name="zero_win_probe_ack_cnt_req_tot_level2")
	private Integer zeroWinProbeAckCntReqTotLevel2;
	@Column(name="zero_win_probe_ack_cnt_req_tot_level3")
	private Integer zeroWinProbeAckCntReqTotLevel3;
	@Column(name="zero_win_probe_ack_cnt_req_tot_level4")
	private Integer zeroWinProbeAckCntReqTotLevel4;
	@Column(name="zero_win_probe_ack_cnt_req_tot_level5")
	private Integer zeroWinProbeAckCntReqTotLevel5;

	@Column(name="zero_win_probe_ack_cnt_res_tot_use")
	private Boolean zeroWinProbeAckCntResTotUse;
	@Column(name="zero_win_probe_ack_cnt_res_tot_level1")
	private Integer zeroWinProbeAckCntResTotLevel1;
	@Column(name="zero_win_probe_ack_cnt_res_tot_level2")
	private Integer zeroWinProbeAckCntResTotLevel2;
	@Column(name="zero_win_probe_ack_cnt_res_tot_level3")
	private Integer zeroWinProbeAckCntResTotLevel3;
	@Column(name="zero_win_probe_ack_cnt_res_tot_level4")
	private Integer zeroWinProbeAckCntResTotLevel4;
	@Column(name="zero_win_probe_ack_cnt_res_tot_level5")
	private Integer zeroWinProbeAckCntResTotLevel5;

	@Column(name="zero_win_probe_ack_len_req_tot_use")
	private Boolean zeroWinProbeAckLenReqTotUse;
	@Column(name="zero_win_probe_ack_len_req_tot_level1")
	private Integer zeroWinProbeAckLenReqTotLevel1;
	@Column(name="zero_win_probe_ack_len_req_tot_level2")
	private Integer zeroWinProbeAckLenReqTotLevel2;
	@Column(name="zero_win_probe_ack_len_req_tot_level3")
	private Integer zeroWinProbeAckLenReqTotLevel3;
	@Column(name="zero_win_probe_ack_len_req_tot_level4")
	private Integer zeroWinProbeAckLenReqTotLevel4;
	@Column(name="zero_win_probe_ack_len_req_tot_level5")
	private Integer zeroWinProbeAckLenReqTotLevel5;

	@Column(name="zero_win_probe_ack_len_res_tot_use")
	private Boolean zeroWinProbeAckLenResTotUse;
	@Column(name="zero_win_probe_ack_len_res_tot_level1")
	private Integer zeroWinProbeAckLenResTotLevel1;
	@Column(name="zero_win_probe_ack_len_res_tot_level2")
	private Integer zeroWinProbeAckLenResTotLevel2;
	@Column(name="zero_win_probe_ack_len_res_tot_level3")
	private Integer zeroWinProbeAckLenResTotLevel3;
	@Column(name="zero_win_probe_ack_len_res_tot_level4")
	private Integer zeroWinProbeAckLenResTotLevel4;
	@Column(name="zero_win_probe_ack_len_res_tot_level5")
	private Integer zeroWinProbeAckLenResTotLevel5;

	@Column(name="zero_win_probe_ack_cnt_req_per_sec_use")
	private Boolean zeroWinProbeAckCntReqPerSecUse;
	@Column(name="zero_win_probe_ack_cnt_req_per_sec_level1")
	private Integer zeroWinProbeAckCntReqPerSecLevel1;
	@Column(name="zero_win_probe_ack_cnt_req_per_sec_level2")
	private Integer zeroWinProbeAckCntReqPerSecLevel2;
	@Column(name="zero_win_probe_ack_cnt_req_per_sec_level3")
	private Integer zeroWinProbeAckCntReqPerSecLevel3;
	@Column(name="zero_win_probe_ack_cnt_req_per_sec_level4")
	private Integer zeroWinProbeAckCntReqPerSecLevel4;
	@Column(name="zero_win_probe_ack_cnt_req_per_sec_level5")
	private Integer zeroWinProbeAckCntReqPerSecLevel5;

	@Column(name="zero_win_probe_ack_cnt_res_per_sec_use")
	private Boolean zeroWinProbeAckCntResPerSecUse;
	@Column(name="zero_win_probe_ack_cnt_res_per_sec_level1")
	private Integer zeroWinProbeAckCntResPerSecLevel1;
	@Column(name="zero_win_probe_ack_cnt_res_per_sec_level2")
	private Integer zeroWinProbeAckCntResPerSecLevel2;
	@Column(name="zero_win_probe_ack_cnt_res_per_sec_level3")
	private Integer zeroWinProbeAckCntResPerSecLevel3;
	@Column(name="zero_win_probe_ack_cnt_res_per_sec_level4")
	private Integer zeroWinProbeAckCntResPerSecLevel4;
	@Column(name="zero_win_probe_ack_cnt_res_per_sec_level5")
	private Integer zeroWinProbeAckCntResPerSecLevel5;

	@Column(name="zero_win_probe_ack_len_req_per_sec_use")
	private Boolean zeroWinProbeAckLenReqPerSecUse;
	@Column(name="zero_win_probe_ack_len_req_per_sec_level1")
	private Integer zeroWinProbeAckLenReqPerSecLevel1;
	@Column(name="zero_win_probe_ack_len_req_per_sec_level2")
	private Integer zeroWinProbeAckLenReqPerSecLevel2;
	@Column(name="zero_win_probe_ack_len_req_per_sec_level3")
	private Integer zeroWinProbeAckLenReqPerSecLevel3;
	@Column(name="zero_win_probe_ack_len_req_per_sec_level4")
	private Integer zeroWinProbeAckLenReqPerSecLevel4;
	@Column(name="zero_win_probe_ack_len_req_per_sec_level5")
	private Integer zeroWinProbeAckLenReqPerSecLevel5;

	@Column(name="zero_win_probe_ack_len_res_per_sec_use")
	private Boolean zeroWinProbeAckLenResPerSecUse;
	@Column(name="zero_win_probe_ack_len_res_per_sec_level1")
	private Integer zeroWinProbeAckLenResPerSecLevel1;
	@Column(name="zero_win_probe_ack_len_res_per_sec_level2")
	private Integer zeroWinProbeAckLenResPerSecLevel2;
	@Column(name="zero_win_probe_ack_len_res_per_sec_level3")
	private Integer zeroWinProbeAckLenResPerSecLevel3;
	@Column(name="zero_win_probe_ack_len_res_per_sec_level4")
	private Integer zeroWinProbeAckLenResPerSecLevel4;
	@Column(name="zero_win_probe_ack_len_res_per_sec_level5")
	private Integer zeroWinProbeAckLenResPerSecLevel5;

	@Column(name="zero_win_probe_ack_cnt_req_delta_use")
	private Boolean zeroWinProbeAckCntReqDeltaUse;
	@Column(name="zero_win_probe_ack_cnt_req_delta_level1")
	private Integer zeroWinProbeAckCntReqDeltaLevel1;
	@Column(name="zero_win_probe_ack_cnt_req_delta_level2")
	private Integer zeroWinProbeAckCntReqDeltaLevel2;
	@Column(name="zero_win_probe_ack_cnt_req_delta_level3")
	private Integer zeroWinProbeAckCntReqDeltaLevel3;
	@Column(name="zero_win_probe_ack_cnt_req_delta_level4")
	private Integer zeroWinProbeAckCntReqDeltaLevel4;
	@Column(name="zero_win_probe_ack_cnt_req_delta_level5")
	private Integer zeroWinProbeAckCntReqDeltaLevel5;

	@Column(name="zero_win_probe_ack_cnt_res_delta_use")
	private Boolean zeroWinProbeAckCntResDeltaUse;
	@Column(name="zero_win_probe_ack_cnt_res_delta_level1")
	private Integer zeroWinProbeAckCntResDeltaLevel1;
	@Column(name="zero_win_probe_ack_cnt_res_delta_level2")
	private Integer zeroWinProbeAckCntResDeltaLevel2;
	@Column(name="zero_win_probe_ack_cnt_res_delta_level3")
	private Integer zeroWinProbeAckCntResDeltaLevel3;
	@Column(name="zero_win_probe_ack_cnt_res_delta_level4")
	private Integer zeroWinProbeAckCntResDeltaLevel4;
	@Column(name="zero_win_probe_ack_cnt_res_delta_level5")
	private Integer zeroWinProbeAckCntResDeltaLevel5;

	@Column(name="zero_win_probe_ack_len_req_delta_use")
	private Boolean zeroWinProbeAckLenReqDeltaUse;
	@Column(name="zero_win_probe_ack_len_req_delta_level1")
	private Integer zeroWinProbeAckLenReqDeltaLevel1;
	@Column(name="zero_win_probe_ack_len_req_delta_level2")
	private Integer zeroWinProbeAckLenReqDeltaLevel2;
	@Column(name="zero_win_probe_ack_len_req_delta_level3")
	private Integer zeroWinProbeAckLenReqDeltaLevel3;
	@Column(name="zero_win_probe_ack_len_req_delta_level4")
	private Integer zeroWinProbeAckLenReqDeltaLevel4;
	@Column(name="zero_win_probe_ack_len_req_delta_level5")
	private Integer zeroWinProbeAckLenReqDeltaLevel5;

	@Column(name="zero_win_probe_ack_len_res_delta_use")
	private Boolean zeroWinProbeAckLenResDeltaUse;
	@Column(name="zero_win_probe_ack_len_res_delta_level1")
	private Integer zeroWinProbeAckLenResDeltaLevel1;
	@Column(name="zero_win_probe_ack_len_res_delta_level2")
	private Integer zeroWinProbeAckLenResDeltaLevel2;
	@Column(name="zero_win_probe_ack_len_res_delta_level3")
	private Integer zeroWinProbeAckLenResDeltaLevel3;
	@Column(name="zero_win_probe_ack_len_res_delta_level4")
	private Integer zeroWinProbeAckLenResDeltaLevel4;
	@Column(name="zero_win_probe_ack_len_res_delta_level5")
	private Integer zeroWinProbeAckLenResDeltaLevel5;

	@Column(name="keep_alive_cnt_req_tot_use")
	private Boolean keepAliveCntReqTotUse;
	@Column(name="keep_alive_cnt_req_tot_level1")
	private Integer keepAliveCntReqTotLevel1;
	@Column(name="keep_alive_cnt_req_tot_level2")
	private Integer keepAliveCntReqTotLevel2;
	@Column(name="keep_alive_cnt_req_tot_level3")
	private Integer keepAliveCntReqTotLevel3;
	@Column(name="keep_alive_cnt_req_tot_level4")
	private Integer keepAliveCntReqTotLevel4;
	@Column(name="keep_alive_cnt_req_tot_level5")
	private Integer keepAliveCntReqTotLevel5;

	@Column(name="keep_alive_cnt_res_tot_use")
	private Boolean keepAliveCntResTotUse;
	@Column(name="keep_alive_cnt_res_tot_level1")
	private Integer keepAliveCntResTotLevel1;
	@Column(name="keep_alive_cnt_res_tot_level2")
	private Integer keepAliveCntResTotLevel2;
	@Column(name="keep_alive_cnt_res_tot_level3")
	private Integer keepAliveCntResTotLevel3;
	@Column(name="keep_alive_cnt_res_tot_level4")
	private Integer keepAliveCntResTotLevel4;
	@Column(name="keep_alive_cnt_res_tot_level5")
	private Integer keepAliveCntResTotLevel5;

	@Column(name="keep_alive_len_req_tot_use")
	private Boolean keepAliveLenReqTotUse;
	@Column(name="keep_alive_len_req_tot_level1")
	private Integer keepAliveLenReqTotLevel1;
	@Column(name="keep_alive_len_req_tot_level2")
	private Integer keepAliveLenReqTotLevel2;
	@Column(name="keep_alive_len_req_tot_level3")
	private Integer keepAliveLenReqTotLevel3;
	@Column(name="keep_alive_len_req_tot_level4")
	private Integer keepAliveLenReqTotLevel4;
	@Column(name="keep_alive_len_req_tot_level5")
	private Integer keepAliveLenReqTotLevel5;

	@Column(name="keep_alive_len_res_tot_use")
	private Boolean keepAliveLenResTotUse;
	@Column(name="keep_alive_len_res_tot_level1")
	private Integer keepAliveLenResTotLevel1;
	@Column(name="keep_alive_len_res_tot_level2")
	private Integer keepAliveLenResTotLevel2;
	@Column(name="keep_alive_len_res_tot_level3")
	private Integer keepAliveLenResTotLevel3;
	@Column(name="keep_alive_len_res_tot_level4")
	private Integer keepAliveLenResTotLevel4;
	@Column(name="keep_alive_len_res_tot_level5")
	private Integer keepAliveLenResTotLevel5;

	@Column(name="keep_alive_cnt_req_per_sec_use")
	private Boolean keepAliveCntReqPerSecUse;
	@Column(name="keep_alive_cnt_req_per_sec_level1")
	private Integer keepAliveCntReqPerSecLevel1;
	@Column(name="keep_alive_cnt_req_per_sec_level2")
	private Integer keepAliveCntReqPerSecLevel2;
	@Column(name="keep_alive_cnt_req_per_sec_level3")
	private Integer keepAliveCntReqPerSecLevel3;
	@Column(name="keep_alive_cnt_req_per_sec_level4")
	private Integer keepAliveCntReqPerSecLevel4;
	@Column(name="keep_alive_cnt_req_per_sec_level5")
	private Integer keepAliveCntReqPerSecLevel5;

	@Column(name="keep_alive_cnt_res_per_sec_use")
	private Boolean keepAliveCntResPerSecUse;
	@Column(name="keep_alive_cnt_res_per_sec_level1")
	private Integer keepAliveCntResPerSecLevel1;
	@Column(name="keep_alive_cnt_res_per_sec_level2")
	private Integer keepAliveCntResPerSecLevel2;
	@Column(name="keep_alive_cnt_res_per_sec_level3")
	private Integer keepAliveCntResPerSecLevel3;
	@Column(name="keep_alive_cnt_res_per_sec_level4")
	private Integer keepAliveCntResPerSecLevel4;
	@Column(name="keep_alive_cnt_res_per_sec_level5")
	private Integer keepAliveCntResPerSecLevel5;

	@Column(name="keep_alive_len_req_per_sec_use")
	private Boolean keepAliveLenReqPerSecUse;
	@Column(name="keep_alive_len_req_per_sec_level1")
	private Integer keepAliveLenReqPerSecLevel1;
	@Column(name="keep_alive_len_req_per_sec_level2")
	private Integer keepAliveLenReqPerSecLevel2;
	@Column(name="keep_alive_len_req_per_sec_level3")
	private Integer keepAliveLenReqPerSecLevel3;
	@Column(name="keep_alive_len_req_per_sec_level4")
	private Integer keepAliveLenReqPerSecLevel4;
	@Column(name="keep_alive_len_req_per_sec_level5")
	private Integer keepAliveLenReqPerSecLevel5;

	@Column(name="keep_alive_len_res_per_sec_use")
	private Boolean keepAliveLenResPerSecUse;
	@Column(name="keep_alive_len_res_per_sec_level1")
	private Integer keepAliveLenResPerSecLevel1;
	@Column(name="keep_alive_len_res_per_sec_level2")
	private Integer keepAliveLenResPerSecLevel2;
	@Column(name="keep_alive_len_res_per_sec_level3")
	private Integer keepAliveLenResPerSecLevel3;
	@Column(name="keep_alive_len_res_per_sec_level4")
	private Integer keepAliveLenResPerSecLevel4;
	@Column(name="keep_alive_len_res_per_sec_level5")
	private Integer keepAliveLenResPerSecLevel5;

	@Column(name="keep_alive_cnt_req_delta_use")
	private Boolean keepAliveCntReqDeltaUse;
	@Column(name="keep_alive_cnt_req_delta_level1")
	private Integer keepAliveCntReqDeltaLevel1;
	@Column(name="keep_alive_cnt_req_delta_level2")
	private Integer keepAliveCntReqDeltaLevel2;
	@Column(name="keep_alive_cnt_req_delta_level3")
	private Integer keepAliveCntReqDeltaLevel3;
	@Column(name="keep_alive_cnt_req_delta_level4")
	private Integer keepAliveCntReqDeltaLevel4;
	@Column(name="keep_alive_cnt_req_delta_level5")
	private Integer keepAliveCntReqDeltaLevel5;

	@Column(name="keep_alive_cnt_res_delta_use")
	private Boolean keepAliveCntResDeltaUse;
	@Column(name="keep_alive_cnt_res_delta_level1")
	private Integer keepAliveCntResDeltaLevel1;
	@Column(name="keep_alive_cnt_res_delta_level2")
	private Integer keepAliveCntResDeltaLevel2;
	@Column(name="keep_alive_cnt_res_delta_level3")
	private Integer keepAliveCntResDeltaLevel3;
	@Column(name="keep_alive_cnt_res_delta_level4")
	private Integer keepAliveCntResDeltaLevel4;
	@Column(name="keep_alive_cnt_res_delta_level5")
	private Integer keepAliveCntResDeltaLevel5;

	@Column(name="keep_alive_len_req_delta_use")
	private Boolean keepAliveLenReqDeltaUse;
	@Column(name="keep_alive_len_req_delta_level1")
	private Integer keepAliveLenReqDeltaLevel1;
	@Column(name="keep_alive_len_req_delta_level2")
	private Integer keepAliveLenReqDeltaLevel2;
	@Column(name="keep_alive_len_req_delta_level3")
	private Integer keepAliveLenReqDeltaLevel3;
	@Column(name="keep_alive_len_req_delta_level4")
	private Integer keepAliveLenReqDeltaLevel4;
	@Column(name="keep_alive_len_req_delta_level5")
	private Integer keepAliveLenReqDeltaLevel5;

	@Column(name="keep_alive_len_res_delta_use")
	private Boolean keepAliveLenResDeltaUse;
	@Column(name="keep_alive_len_res_delta_level1")
	private Integer keepAliveLenResDeltaLevel1;
	@Column(name="keep_alive_len_res_delta_level2")
	private Integer keepAliveLenResDeltaLevel2;
	@Column(name="keep_alive_len_res_delta_level3")
	private Integer keepAliveLenResDeltaLevel3;
	@Column(name="keep_alive_len_res_delta_level4")
	private Integer keepAliveLenResDeltaLevel4;
	@Column(name="keep_alive_len_res_delta_level5")
	private Integer keepAliveLenResDeltaLevel5;

	@Column(name="keep_alive_ack_cnt_req_tot_use")
	private Boolean keepAliveAckCntReqTotUse;
	@Column(name="keep_alive_ack_cnt_req_tot_level1")
	private Integer keepAliveAckCntReqTotLevel1;
	@Column(name="keep_alive_ack_cnt_req_tot_level2")
	private Integer keepAliveAckCntReqTotLevel2;
	@Column(name="keep_alive_ack_cnt_req_tot_level3")
	private Integer keepAliveAckCntReqTotLevel3;
	@Column(name="keep_alive_ack_cnt_req_tot_level4")
	private Integer keepAliveAckCntReqTotLevel4;
	@Column(name="keep_alive_ack_cnt_req_tot_level5")
	private Integer keepAliveAckCntReqTotLevel5;

	@Column(name="keep_alive_ack_cnt_res_tot_use")
	private Boolean keepAliveAckCntResTotUse;
	@Column(name="keep_alive_ack_cnt_res_tot_level1")
	private Integer keepAliveAckCntResTotLevel1;
	@Column(name="keep_alive_ack_cnt_res_tot_level2")
	private Integer keepAliveAckCntResTotLevel2;
	@Column(name="keep_alive_ack_cnt_res_tot_level3")
	private Integer keepAliveAckCntResTotLevel3;
	@Column(name="keep_alive_ack_cnt_res_tot_level4")
	private Integer keepAliveAckCntResTotLevel4;
	@Column(name="keep_alive_ack_cnt_res_tot_level5")
	private Integer keepAliveAckCntResTotLevel5;

	@Column(name="keep_alive_ack_len_req_tot_use")
	private Boolean keepAliveAckLenReqTotUse;
	@Column(name="keep_alive_ack_len_req_tot_level1")
	private Integer keepAliveAckLenReqTotLevel1;
	@Column(name="keep_alive_ack_len_req_tot_level2")
	private Integer keepAliveAckLenReqTotLevel2;
	@Column(name="keep_alive_ack_len_req_tot_level3")
	private Integer keepAliveAckLenReqTotLevel3;
	@Column(name="keep_alive_ack_len_req_tot_level4")
	private Integer keepAliveAckLenReqTotLevel4;
	@Column(name="keep_alive_ack_len_req_tot_level5")
	private Integer keepAliveAckLenReqTotLevel5;

	@Column(name="keep_alive_ack_len_res_tot_use")
	private Boolean keepAliveAckLenResTotUse;
	@Column(name="keep_alive_ack_len_res_tot_level1")
	private Integer keepAliveAckLenResTotLevel1;
	@Column(name="keep_alive_ack_len_res_tot_level2")
	private Integer keepAliveAckLenResTotLevel2;
	@Column(name="keep_alive_ack_len_res_tot_level3")
	private Integer keepAliveAckLenResTotLevel3;
	@Column(name="keep_alive_ack_len_res_tot_level4")
	private Integer keepAliveAckLenResTotLevel4;
	@Column(name="keep_alive_ack_len_res_tot_level5")
	private Integer keepAliveAckLenResTotLevel5;

	@Column(name="keep_alive_ack_cnt_req_per_sec_use")
	private Boolean keepAliveAckCntReqPerSecUse;
	@Column(name="keep_alive_ack_cnt_req_per_sec_level1")
	private Integer keepAliveAckCntReqPerSecLevel1;
	@Column(name="keep_alive_ack_cnt_req_per_sec_level2")
	private Integer keepAliveAckCntReqPerSecLevel2;
	@Column(name="keep_alive_ack_cnt_req_per_sec_level3")
	private Integer keepAliveAckCntReqPerSecLevel3;
	@Column(name="keep_alive_ack_cnt_req_per_sec_level4")
	private Integer keepAliveAckCntReqPerSecLevel4;
	@Column(name="keep_alive_ack_cnt_req_per_sec_level5")
	private Integer keepAliveAckCntReqPerSecLevel5;

	@Column(name="keep_alive_ack_cnt_res_per_sec_use")
	private Boolean keepAliveAckCntResPerSecUse;
	@Column(name="keep_alive_ack_cnt_res_per_sec_level1")
	private Integer keepAliveAckCntResPerSecLevel1;
	@Column(name="keep_alive_ack_cnt_res_per_sec_level2")
	private Integer keepAliveAckCntResPerSecLevel2;
	@Column(name="keep_alive_ack_cnt_res_per_sec_level3")
	private Integer keepAliveAckCntResPerSecLevel3;
	@Column(name="keep_alive_ack_cnt_res_per_sec_level4")
	private Integer keepAliveAckCntResPerSecLevel4;
	@Column(name="keep_alive_ack_cnt_res_per_sec_level5")
	private Integer keepAliveAckCntResPerSecLevel5;

	@Column(name="keep_alive_ack_len_req_per_sec_use")
	private Boolean keepAliveAckLenReqPerSecUse;
	@Column(name="keep_alive_ack_len_req_per_sec_level1")
	private Integer keepAliveAckLenReqPerSecLevel1;
	@Column(name="keep_alive_ack_len_req_per_sec_level2")
	private Integer keepAliveAckLenReqPerSecLevel2;
	@Column(name="keep_alive_ack_len_req_per_sec_level3")
	private Integer keepAliveAckLenReqPerSecLevel3;
	@Column(name="keep_alive_ack_len_req_per_sec_level4")
	private Integer keepAliveAckLenReqPerSecLevel4;
	@Column(name="keep_alive_ack_len_req_per_sec_level5")
	private Integer keepAliveAckLenReqPerSecLevel5;

	@Column(name="keep_alive_ack_len_res_per_sec_use")
	private Boolean keepAliveAckLenResPerSecUse;
	@Column(name="keep_alive_ack_len_res_per_sec_level1")
	private Integer keepAliveAckLenResPerSecLevel1;
	@Column(name="keep_alive_ack_len_res_per_sec_level2")
	private Integer keepAliveAckLenResPerSecLevel2;
	@Column(name="keep_alive_ack_len_res_per_sec_level3")
	private Integer keepAliveAckLenResPerSecLevel3;
	@Column(name="keep_alive_ack_len_res_per_sec_level4")
	private Integer keepAliveAckLenResPerSecLevel4;
	@Column(name="keep_alive_ack_len_res_per_sec_level5")
	private Integer keepAliveAckLenResPerSecLevel5;

	@Column(name="keep_alive_ack_cnt_req_delta_use")
	private Boolean keepAliveAckCntReqDeltaUse;
	@Column(name="keep_alive_ack_cnt_req_delta_level1")
	private Integer keepAliveAckCntReqDeltaLevel1;
	@Column(name="keep_alive_ack_cnt_req_delta_level2")
	private Integer keepAliveAckCntReqDeltaLevel2;
	@Column(name="keep_alive_ack_cnt_req_delta_level3")
	private Integer keepAliveAckCntReqDeltaLevel3;
	@Column(name="keep_alive_ack_cnt_req_delta_level4")
	private Integer keepAliveAckCntReqDeltaLevel4;
	@Column(name="keep_alive_ack_cnt_req_delta_level5")
	private Integer keepAliveAckCntReqDeltaLevel5;

	@Column(name="keep_alive_ack_cnt_res_delta_use")
	private Boolean keepAliveAckCntResDeltaUse;
	@Column(name="keep_alive_ack_cnt_res_delta_level1")
	private Integer keepAliveAckCntResDeltaLevel1;
	@Column(name="keep_alive_ack_cnt_res_delta_level2")
	private Integer keepAliveAckCntResDeltaLevel2;
	@Column(name="keep_alive_ack_cnt_res_delta_level3")
	private Integer keepAliveAckCntResDeltaLevel3;
	@Column(name="keep_alive_ack_cnt_res_delta_level4")
	private Integer keepAliveAckCntResDeltaLevel4;
	@Column(name="keep_alive_ack_cnt_res_delta_level5")
	private Integer keepAliveAckCntResDeltaLevel5;

	@Column(name="keep_alive_ack_len_req_delta_use")
	private Boolean keepAliveAckLenReqDeltaUse;
	@Column(name="keep_alive_ack_len_req_delta_level1")
	private Integer keepAliveAckLenReqDeltaLevel1;
	@Column(name="keep_alive_ack_len_req_delta_level2")
	private Integer keepAliveAckLenReqDeltaLevel2;
	@Column(name="keep_alive_ack_len_req_delta_level3")
	private Integer keepAliveAckLenReqDeltaLevel3;
	@Column(name="keep_alive_ack_len_req_delta_level4")
	private Integer keepAliveAckLenReqDeltaLevel4;
	@Column(name="keep_alive_ack_len_req_delta_level5")
	private Integer keepAliveAckLenReqDeltaLevel5;

	@Column(name="keep_alive_ack_len_res_delta_use")
	private Boolean keepAliveAckLenResDeltaUse;
	@Column(name="keep_alive_ack_len_res_delta_level1")
	private Integer keepAliveAckLenResDeltaLevel1;
	@Column(name="keep_alive_ack_len_res_delta_level2")
	private Integer keepAliveAckLenResDeltaLevel2;
	@Column(name="keep_alive_ack_len_res_delta_level3")
	private Integer keepAliveAckLenResDeltaLevel3;
	@Column(name="keep_alive_ack_len_res_delta_level4")
	private Integer keepAliveAckLenResDeltaLevel4;
	@Column(name="keep_alive_ack_len_res_delta_level5")
	private Integer keepAliveAckLenResDeltaLevel5;

}
