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
@Table (name = "tbl_info_alarm_l4_udp")
public class InfoAlarmL4Udp {

	@Id
	@SequenceGenerator(name="tbl_info_alarm_l4_udp_seq", sequenceName="tbl_info_alarm_l4_udp_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_alarm_l4_udp_seq")
   	@Column(name = "l4_udp_id")
	private Integer l4UdpId;

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


}
