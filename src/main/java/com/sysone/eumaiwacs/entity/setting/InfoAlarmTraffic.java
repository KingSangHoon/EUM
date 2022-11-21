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
@Table (name = "tbl_info_alarm_traffic")
public class InfoAlarmTraffic {

	@Id
	@SequenceGenerator(name="tbl_info_alarm_traffic_seq", sequenceName="tbl_info_alarm_traffic_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_alarm_traffic_seq")
   	@Column(name = "traffic_id")
	private Integer trafficId;

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

   	@Column(name = "len_req_tot_use")
	private Boolean lenReqTotUse;

   	@Column(name = "len_req_tot_caution")
	private Integer lenReqTotCaution;

   	@Column(name = "len_req_tot_warning")
	private Integer lenReqTotWarning;

   	@Column(name = "len_req_tot_danger")
	private Integer lenReqTotDanger;

   	@Column(name = "len_req_tot_obstacle")
	private Integer lenReqTotObstacle;

   	@Column(name = "len_res_tot_use")
	private Boolean lenResTotUse;

   	@Column(name = "len_res_tot_caution")
	private Integer lenResTotCaution;

   	@Column(name = "len_res_tot_warning")
	private Integer lenResTotWarning;

   	@Column(name = "len_res_tot_danger")
	private Integer lenResTotDanger;

   	@Column(name = "len_res_tot_obstacle")
	private Integer lenResTotObstacle;

   	@Column(name = "pkts_req_tot_use")
	private Boolean pktsReqTotUse;

   	@Column(name = "pkts_req_tot_caution")
	private Integer pktsReqTotCaution;

   	@Column(name = "pkts_req_tot_warning")
	private Integer pktsReqTotWarning;

   	@Column(name = "pkts_req_tot_danger")
	private Integer pktsReqTotDanger;

   	@Column(name = "pkts_req_tot_obstacle")
	private Integer pktsReqTotObstacle;

   	@Column(name = "pkts_res_tot_use")
	private Boolean pktsResTotUse;

   	@Column(name = "pkts_res_tot_caution")
	private Integer pktsResTotCaution;

   	@Column(name = "pkts_res_tot_warning")
	private Integer pktsResTotWarning;

   	@Column(name = "pkts_res_tot_danger")
	private Integer pktsResTotDanger;

   	@Column(name = "pkts_res_tot_obstacle")
	private Integer pktsResTotObstacle;

   	@Column(name = "len_req_per_sec_use")
	private Boolean lenReqPerSecUse;

   	@Column(name = "len_req_per_sec_caution")
	private Integer lenReqPerSecCaution;

   	@Column(name = "len_req_per_sec_warning")
	private Integer lenReqPerSecWarning;

   	@Column(name = "len_req_per_sec_danger")
	private Integer lenReqPerSecDanger;

   	@Column(name = "len_req_per_sec_obstacle")
	private Integer lenReqPerSecObstacle;

   	@Column(name = "len_res_per_sec_use")
	private Boolean lenResPerSecUse;

   	@Column(name = "len_res_per_sec_caution")
	private Integer lenResPerSecCaution;

   	@Column(name = "len_res_per_sec_warning")
	private Integer lenResPerSecWarning;

   	@Column(name = "len_res_per_sec_danger")
	private Integer lenResPerSecDanger;

   	@Column(name = "len_res_per_sec_obstacle")
	private Integer lenResPerSecObstacle;

   	@Column(name = "pkts_req_per_sec_use")
	private Boolean pktsReqPerSecUse;

   	@Column(name = "pkts_req_per_sec_caution")
	private Integer pktsReqPerSecCaution;

   	@Column(name = "pkts_req_per_sec_warning")
	private Integer pktsReqPerSecWarning;

   	@Column(name = "pkts_req_per_sec_danger")
	private Integer pktsReqPerSecDanger;

   	@Column(name = "pkts_req_per_sec_obstacle")
	private Integer pktsReqPerSecObstacle;

   	@Column(name = "pkts_res_per_sec_use")
	private Boolean pktsResPerSecUse;

   	@Column(name = "pkts_res_per_sec_caution")
	private Integer pktsResPerSecCaution;

   	@Column(name = "pkts_res_per_sec_warning")
	private Integer pktsResPerSecWarning;

   	@Column(name = "pkts_res_per_sec_danger")
	private Integer pktsResPerSecDanger;

   	@Column(name = "pkts_res_per_sec_obstacle")
	private Integer pktsResPerSecObstacle;

   	@Column(name = "len_req_delta_use")
	private Boolean lenReqDeltaUse;

   	@Column(name = "len_req_delta_caution")
	private Integer lenReqDeltaCaution;

   	@Column(name = "len_req_delta_warning")
	private Integer lenReqDeltaWarning;

   	@Column(name = "len_req_delta_danger")
	private Integer lenReqDeltaDanger;

   	@Column(name = "len_req_delta_obstacle")
	private Integer lenReqDeltaObstacle;

   	@Column(name = "len_res_delta_use")
	private Boolean lenResDeltaUse;

   	@Column(name = "len_res_delta_caution")
	private Integer lenResDeltaCaution;

   	@Column(name = "len_res_delta_warning")
	private Integer lenResDeltaWarning;

   	@Column(name = "len_res_delta_danger")
	private Integer lenResDeltaDanger;

   	@Column(name = "len_res_delta_obstacle")
	private Integer lenResDeltaObstacle;

   	@Column(name = "pkts_req_delta_use")
	private Boolean pktsReqDeltaUse;

   	@Column(name = "pkts_req_delta_caution")
	private Integer pktsReqDeltaCaution;

   	@Column(name = "pkts_req_delta_warning")
	private Integer pktsReqDeltaWarning;

   	@Column(name = "pkts_req_delta_danger")
	private Integer pktsReqDeltaDanger;

   	@Column(name = "pkts_req_delta_obstacle")
	private Integer pktsReqDeltaObstacle;

   	@Column(name = "pkts_res_delta_use")
	private Boolean pktsResDeltaUse;

   	@Column(name = "pkts_res_delta_caution")
	private Integer pktsResDeltaCaution;

   	@Column(name = "pkts_res_delta_warning")
	private Integer pktsResDeltaWarning;

   	@Column(name = "pkts_res_delta_danger")
	private Integer pktsResDeltaDanger;

   	@Column(name = "pkts_res_delta_obstacle")
	private Integer pktsResDeltaObstacle;

   	@Column(name = "pkt_len_min_req_use")
	private Boolean pktLenMinReqUse;

   	@Column(name = "pkt_len_min_req_caution")
	private Integer pktLenMinReqCaution;

   	@Column(name = "pkt_len_min_req_warning")
	private Integer pktLenMinReqWarning;

   	@Column(name = "pkt_len_min_req_danger")
	private Integer pktLenMinReqDanger;

   	@Column(name = "pkt_len_min_req_obstacle")
	private Integer pktLenMinReqObstacle;

   	@Column(name = "pkt_len_min_res_use")
	private Boolean pktLenMinResUse;

   	@Column(name = "pkt_len_min_res_caution")
	private Integer pktLenMinResCaution;

   	@Column(name = "pkt_len_min_res_warning")
	private Integer pktLenMinResWarning;

   	@Column(name = "pkt_len_min_res_danger")
	private Integer pktLenMinResDanger;

   	@Column(name = "pkt_len_min_res_obstacle")
	private Integer pktLenMinResObstacle;

   	@Column(name = "pkt_len_max_req_use")
	private Boolean pktLenMaxReqUse;

   	@Column(name = "pkt_len_max_req_caution")
	private Integer pktLenMaxReqCaution;

   	@Column(name = "pkt_len_max_req_warning")
	private Integer pktLenMaxReqWarning;

   	@Column(name = "pkt_len_max_req_danger")
	private Integer pktLenMaxReqDanger;

   	@Column(name = "pkt_len_max_req_obstacle")
	private Integer pktLenMaxReqObstacle;

   	@Column(name = "pkt_len_max_res_use")
	private Boolean pktLenMaxResUse;

   	@Column(name = "pkt_len_max_res_caution")
	private Integer pktLenMaxResCaution;

   	@Column(name = "pkt_len_max_res_warning")
	private Integer pktLenMaxResWarning;

   	@Column(name = "pkt_len_max_res_danger")
	private Integer pktLenMaxResDanger;

   	@Column(name = "pkt_len_max_res_obstacle")
	private Integer pktLenMaxResObstacle;

   	@Column(name = "pkt_len_stat_1_to_128_req_tot_use")
	private Boolean pktLenStat1To128ReqTotUse;

   	@Column(name = "pkt_len_stat_1_to_128_req_tot_caution")
	private Integer pktLenStat1To128ReqTotCaution;

   	@Column(name = "pkt_len_stat_1_to_128_req_tot_warning")
	private Integer pktLenStat1To128ReqTotWarning;

   	@Column(name = "pkt_len_stat_1_to_128_req_tot_danger")
	private Integer pktLenStat1To128ReqTotDanger;

   	@Column(name = "pkt_len_stat_1_to_128_req_tot_obstacle")
	private Integer pktLenStat1To128ReqTotObstacle;

   	@Column(name = "pkt_len_stat_129_to_256_req_tot_use")
	private Boolean pktLenStat129To256ReqTotUse;

   	@Column(name = "pkt_len_stat_129_to_256_req_tot_caution")
	private Integer pktLenStat129To256ReqTotCaution;

   	@Column(name = "pkt_len_stat_129_to_256_req_tot_warning")
	private Integer pktLenStat129To256ReqTotWarning;

   	@Column(name = "pkt_len_stat_129_to_256_req_tot_danger")
	private Integer pktLenStat129To256ReqTotDanger;

   	@Column(name = "pkt_len_stat_129_to_256_req_tot_obstacle")
	private Integer pktLenStat129To256ReqTotObstacle;

   	@Column(name = "pkt_len_stat_257_to_512_req_tot_use")
	private Boolean pktLenStat257To512ReqTotUse;

   	@Column(name = "pkt_len_stat_257_to_512_req_tot_caution")
	private Integer pktLenStat257To512ReqTotCaution;

   	@Column(name = "pkt_len_stat_257_to_512_req_tot_warning")
	private Integer pktLenStat257To512ReqTotWarning;

   	@Column(name = "pkt_len_stat_257_to_512_req_tot_danger")
	private Integer pktLenStat257To512ReqTotDanger;

   	@Column(name = "pkt_len_stat_257_to_512_req_tot_obstacle")
	private Integer pktLenStat257To512ReqTotObstacle;

   	@Column(name = "pkt_len_stat_513_to_1024_req_tot_use")
	private Boolean pktLenStat513To1024ReqTotUse;

   	@Column(name = "pkt_len_stat_513_to_1024_req_tot_caution")
	private Integer pktLenStat513To1024ReqTotCaution;

   	@Column(name = "pkt_len_stat_513_to_1024_req_tot_warning")
	private Integer pktLenStat513To1024ReqTotWarning;

   	@Column(name = "pkt_len_stat_513_to_1024_req_tot_danger")
	private Integer pktLenStat513To1024ReqTotDanger;

   	@Column(name = "pkt_len_stat_513_to_1024_req_tot_obstacle")
	private Integer pktLenStat513To1024ReqTotObstacle;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_tot_use")
	private Boolean pktLenStat1025To1514ReqTotUse;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_tot_caution")
	private Integer pktLenStat1025To1514ReqTotCaution;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_tot_warning")
	private Integer pktLenStat1025To1514ReqTotWarning;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_tot_danger")
	private Integer pktLenStat1025To1514ReqTotDanger;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_tot_obstacle")
	private Integer pktLenStat1025To1514ReqTotObstacle;

   	@Column(name = "pkt_len_stat_jumbo_req_tot_use")
	private Boolean pktLenStatJumboReqTotUse;

   	@Column(name = "pkt_len_stat_jumbo_req_tot_caution")
	private Integer pktLenStatJumboReqTotCaution;

   	@Column(name = "pkt_len_stat_jumbo_req_tot_warning")
	private Integer pktLenStatJumboReqTotWarning;

   	@Column(name = "pkt_len_stat_jumbo_req_tot_danger")
	private Integer pktLenStatJumboReqTotDanger;

   	@Column(name = "pkt_len_stat_jumbo_req_tot_obstacle")
	private Integer pktLenStatJumboReqTotObstacle;

   	@Column(name = "pkt_len_stat_1_to_128_res_tot_use")
	private Boolean pktLenStat1To128ResTotUse;

   	@Column(name = "pkt_len_stat_1_to_128_res_tot_caution")
	private Integer pktLenStat1To128ResTotCaution;

   	@Column(name = "pkt_len_stat_1_to_128_res_tot_warning")
	private Integer pktLenStat1To128ResTotWarning;

   	@Column(name = "pkt_len_stat_1_to_128_res_tot_danger")
	private Integer pktLenStat1To128ResTotDanger;

   	@Column(name = "pkt_len_stat_1_to_128_res_tot_obstacle")
	private Integer pktLenStat1To128ResTotObstacle;

   	@Column(name = "pkt_len_stat_129_to_256_res_tot_use")
	private Boolean pktLenStat129To256ResTotUse;

   	@Column(name = "pkt_len_stat_129_to_256_res_tot_caution")
	private Integer pktLenStat129To256ResTotCaution;

   	@Column(name = "pkt_len_stat_129_to_256_res_tot_warning")
	private Integer pktLenStat129To256ResTotWarning;

   	@Column(name = "pkt_len_stat_129_to_256_res_tot_danger")
	private Integer pktLenStat129To256ResTotDanger;

   	@Column(name = "pkt_len_stat_129_to_256_res_tot_obstacle")
	private Integer pktLenStat129To256ResTotObstacle;

   	@Column(name = "pkt_len_stat_257_to_512_res_tot_use")
	private Boolean pktLenStat257To512ResTotUse;

   	@Column(name = "pkt_len_stat_257_to_512_res_tot_caution")
	private Integer pktLenStat257To512ResTotCaution;

   	@Column(name = "pkt_len_stat_257_to_512_res_tot_warning")
	private Integer pktLenStat257To512ResTotWarning;

   	@Column(name = "pkt_len_stat_257_to_512_res_tot_danger")
	private Integer pktLenStat257To512ResTotDanger;

   	@Column(name = "pkt_len_stat_257_to_512_res_tot_obstacle")
	private Integer pktLenStat257To512ResTotObstacle;

   	@Column(name = "pkt_len_stat_513_to_1024_res_tot_use")
	private Boolean pktLenStat513To1024ResTotUse;

   	@Column(name = "pkt_len_stat_513_to_1024_res_tot_caution")
	private Integer pktLenStat513To1024ResTotCaution;

   	@Column(name = "pkt_len_stat_513_to_1024_res_tot_warning")
	private Integer pktLenStat513To1024ResTotWarning;

   	@Column(name = "pkt_len_stat_513_to_1024_res_tot_danger")
	private Integer pktLenStat513To1024ResTotDanger;

   	@Column(name = "pkt_len_stat_513_to_1024_res_tot_obstacle")
	private Integer pktLenStat513To1024ResTotObstacle;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_tot_use")
	private Boolean pktLenStat1025To1514ResTotUse;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_tot_caution")
	private Integer pktLenStat1025To1514ResTotCaution;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_tot_warning")
	private Integer pktLenStat1025To1514ResTotWarning;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_tot_danger")
	private Integer pktLenStat1025To1514ResTotDanger;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_tot_obstacle")
	private Integer pktLenStat1025To1514ResTotObstacle;

   	@Column(name = "pkt_len_stat_jumbo_res_tot_use")
	private Boolean pktLenStatJumboResTotUse;

   	@Column(name = "pkt_len_stat_jumbo_res_tot_caution")
	private Integer pktLenStatJumboResTotCaution;

   	@Column(name = "pkt_len_stat_jumbo_res_tot_warning")
	private Integer pktLenStatJumboResTotWarning;

   	@Column(name = "pkt_len_stat_jumbo_res_tot_danger")
	private Integer pktLenStatJumboResTotDanger;

   	@Column(name = "pkt_len_stat_jumbo_res_tot_obstacle")
	private Integer pktLenStatJumboResTotObstacle;

   	@Column(name = "pkt_len_stat_1_to_128_req_per_sec_use")
	private Boolean pktLenStat1To128ReqPerSecUse;

   	@Column(name = "pkt_len_stat_1_to_128_req_per_sec_caution")
	private Integer pktLenStat1To128ReqPerSecCaution;

   	@Column(name = "pkt_len_stat_1_to_128_req_per_sec_warning")
	private Integer pktLenStat1To128ReqPerSecWarning;

   	@Column(name = "pkt_len_stat_1_to_128_req_per_sec_danger")
	private Integer pktLenStat1To128ReqPerSecDanger;

   	@Column(name = "pkt_len_stat_1_to_128_req_per_sec_obstacle")
	private Integer pktLenStat1To128ReqPerSecObstacle;

   	@Column(name = "pkt_len_stat_129_to_256_req_per_sec_use")
	private Boolean pktLenStat129To256ReqPerSecUse;

   	@Column(name = "pkt_len_stat_129_to_256_req_per_sec_caution")
	private Integer pktLenStat129To256ReqPerSecCaution;

   	@Column(name = "pkt_len_stat_129_to_256_req_per_sec_warning")
	private Integer pktLenStat129To256ReqPerSecWarning;

   	@Column(name = "pkt_len_stat_129_to_256_req_per_sec_danger")
	private Integer pktLenStat129To256ReqPerSecDanger;

   	@Column(name = "pkt_len_stat_129_to_256_req_per_sec_obstacle")
	private Integer pktLenStat129To256ReqPerSecObstacle;

   	@Column(name = "pkt_len_stat_257_to_512_req_per_sec_use")
	private Boolean pktLenStat257To512ReqPerSecUse;

   	@Column(name = "pkt_len_stat_257_to_512_req_per_sec_caution")
	private Integer pktLenStat257To512ReqPerSecCaution;

   	@Column(name = "pkt_len_stat_257_to_512_req_per_sec_warning")
	private Integer pktLenStat257To512ReqPerSecWarning;

   	@Column(name = "pkt_len_stat_257_to_512_req_per_sec_danger")
	private Integer pktLenStat257To512ReqPerSecDanger;

   	@Column(name = "pkt_len_stat_257_to_512_req_per_sec_obstacle")
	private Integer pktLenStat257To512ReqPerSecObstacle;

   	@Column(name = "pkt_len_stat_513_to_1024_req_per_sec_use")
	private Boolean pktLenStat513To1024ReqPerSecUse;

   	@Column(name = "pkt_len_stat_513_to_1024_req_per_sec_caution")
	private Integer pktLenStat513To1024ReqPerSecCaution;

   	@Column(name = "pkt_len_stat_513_to_1024_req_per_sec_warning")
	private Integer pktLenStat513To1024ReqPerSecWarning;

   	@Column(name = "pkt_len_stat_513_to_1024_req_per_sec_danger")
	private Integer pktLenStat513To1024ReqPerSecDanger;

   	@Column(name = "pkt_len_stat_513_to_1024_req_per_sec_obstacle")
	private Integer pktLenStat513To1024ReqPerSecObstacle;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_per_sec_use")
	private Boolean pktLenStat1025To1514ReqPerSecUse;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_per_sec_caution")
	private Integer pktLenStat1025To1514ReqPerSecCaution;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_per_sec_warning")
	private Integer pktLenStat1025To1514ReqPerSecWarning;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_per_sec_danger")
	private Integer pktLenStat1025To1514ReqPerSecDanger;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_per_sec_obstacle")
	private Integer pktLenStat1025To1514ReqPerSecObstacle;

   	@Column(name = "pkt_len_stat_jumbo_req_per_sec_use")
	private Boolean pktLenStatJumboReqPerSecUse;

   	@Column(name = "pkt_len_stat_jumbo_req_per_sec_caution")
	private Integer pktLenStatJumboReqPerSecCaution;

   	@Column(name = "pkt_len_stat_jumbo_req_per_sec_warning")
	private Integer pktLenStatJumboReqPerSecWarning;

   	@Column(name = "pkt_len_stat_jumbo_req_per_sec_danger")
	private Integer pktLenStatJumboReqPerSecDanger;

   	@Column(name = "pkt_len_stat_jumbo_req_per_sec_obstacle")
	private Integer pktLenStatJumboReqPerSecObstacle;

   	@Column(name = "pkt_len_stat_1_to_128_res_per_sec_use")
	private Boolean pktLenStat1To128ResPerSecUse;

   	@Column(name = "pkt_len_stat_1_to_128_res_per_sec_caution")
	private Integer pktLenStat1To128ResPerSecCaution;

   	@Column(name = "pkt_len_stat_1_to_128_res_per_sec_warning")
	private Integer pktLenStat1To128ResPerSecWarning;

   	@Column(name = "pkt_len_stat_1_to_128_res_per_sec_danger")
	private Integer pktLenStat1To128ResPerSecDanger;

   	@Column(name = "pkt_len_stat_1_to_128_res_per_sec_obstacle")
	private Integer pktLenStat1To128ResPerSecObstacle;

   	@Column(name = "pkt_len_stat_129_to_256_res_per_sec_use")
	private Boolean pktLenStat129To256ResPerSecUse;

   	@Column(name = "pkt_len_stat_129_to_256_res_per_sec_caution")
	private Integer pktLenStat129To256ResPerSecCaution;

   	@Column(name = "pkt_len_stat_129_to_256_res_per_sec_warning")
	private Integer pktLenStat129To256ResPerSecWarning;

   	@Column(name = "pkt_len_stat_129_to_256_res_per_sec_danger")
	private Integer pktLenStat129To256ResPerSecDanger;

   	@Column(name = "pkt_len_stat_129_to_256_res_per_sec_obstacle")
	private Integer pktLenStat129To256ResPerSecObstacle;

   	@Column(name = "pkt_len_stat_257_to_512_res_per_sec_use")
	private Boolean pktLenStat257To512ResPerSecUse;

   	@Column(name = "pkt_len_stat_257_to_512_res_per_sec_caution")
	private Integer pktLenStat257To512ResPerSecCaution;

   	@Column(name = "pkt_len_stat_257_to_512_res_per_sec_warning")
	private Integer pktLenStat257To512ResPerSecWarning;

   	@Column(name = "pkt_len_stat_257_to_512_res_per_sec_danger")
	private Integer pktLenStat257To512ResPerSecDanger;

   	@Column(name = "pkt_len_stat_257_to_512_res_per_sec_obstacle")
	private Integer pktLenStat257To512ResPerSecObstacle;

   	@Column(name = "pkt_len_stat_513_to_1024_res_per_sec_use")
	private Boolean pktLenStat513To1024ResPerSecUse;

   	@Column(name = "pkt_len_stat_513_to_1024_res_per_sec_caution")
	private Integer pktLenStat513To1024ResPerSecCaution;

   	@Column(name = "pkt_len_stat_513_to_1024_res_per_sec_warning")
	private Integer pktLenStat513To1024ResPerSecWarning;

   	@Column(name = "pkt_len_stat_513_to_1024_res_per_sec_danger")
	private Integer pktLenStat513To1024ResPerSecDanger;

   	@Column(name = "pkt_len_stat_513_to_1024_res_per_sec_obstacle")
	private Integer pktLenStat513To1024ResPerSecObstacle;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_per_sec_use")
	private Boolean pktLenStat1025To1514ResPerSecUse;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_per_sec_caution")
	private Integer pktLenStat1025To1514ResPerSecCaution;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_per_sec_warning")
	private Integer pktLenStat1025To1514ResPerSecWarning;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_per_sec_danger")
	private Integer pktLenStat1025To1514ResPerSecDanger;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_per_sec_obstacle")
	private Integer pktLenStat1025To1514ResPerSecObstacle;

   	@Column(name = "pkt_len_stat_jumbo_res_per_sec_use")
	private Boolean pktLenStatJumboResPerSecUse;

   	@Column(name = "pkt_len_stat_jumbo_res_per_sec_caution")
	private Integer pktLenStatJumboResPerSecCaution;

   	@Column(name = "pkt_len_stat_jumbo_res_per_sec_warning")
	private Integer pktLenStatJumboResPerSecWarning;

   	@Column(name = "pkt_len_stat_jumbo_res_per_sec_danger")
	private Integer pktLenStatJumboResPerSecDanger;

   	@Column(name = "pkt_len_stat_jumbo_res_per_sec_obstacle")
	private Integer pktLenStatJumboResPerSecObstacle;

   	@Column(name = "pkt_len_stat_1_to_128_req_delta_use")
	private Boolean pktLenStat1To128ReqDeltaUse;

   	@Column(name = "pkt_len_stat_1_to_128_req_delta_caution")
	private Integer pktLenStat1To128ReqDeltaCaution;

   	@Column(name = "pkt_len_stat_1_to_128_req_delta_warning")
	private Integer pktLenStat1To128ReqDeltaWarning;

   	@Column(name = "pkt_len_stat_1_to_128_req_delta_danger")
	private Integer pktLenStat1To128ReqDeltaDanger;

   	@Column(name = "pkt_len_stat_1_to_128_req_delta_obstacle")
	private Integer pktLenStat1To128ReqDeltaObstacle;

   	@Column(name = "pkt_len_stat_129_to_256_req_delta_use")
	private Boolean pktLenStat129To256ReqDeltaUse;

   	@Column(name = "pkt_len_stat_129_to_256_req_delta_caution")
	private Integer pktLenStat129To256ReqDeltaCaution;

   	@Column(name = "pkt_len_stat_129_to_256_req_delta_warning")
	private Integer pktLenStat129To256ReqDeltaWarning;

   	@Column(name = "pkt_len_stat_129_to_256_req_delta_danger")
	private Integer pktLenStat129To256ReqDeltaDanger;

   	@Column(name = "pkt_len_stat_129_to_256_req_delta_obstacle")
	private Integer pktLenStat129To256ReqDeltaObstacle;

   	@Column(name = "pkt_len_stat_257_to_512_req_delta_use")
	private Boolean pktLenStat257To512ReqDeltaUse;

   	@Column(name = "pkt_len_stat_257_to_512_req_delta_caution")
	private Integer pktLenStat257To512ReqDeltaCaution;

   	@Column(name = "pkt_len_stat_257_to_512_req_delta_warning")
	private Integer pktLenStat257To512ReqDeltaWarning;

   	@Column(name = "pkt_len_stat_257_to_512_req_delta_danger")
	private Integer pktLenStat257To512ReqDeltaDanger;

   	@Column(name = "pkt_len_stat_257_to_512_req_delta_obstacle")
	private Integer pktLenStat257To512ReqDeltaObstacle;

   	@Column(name = "pkt_len_stat_513_to_1024_req_delta_use")
	private Boolean pktLenStat513To1024ReqDeltaUse;

   	@Column(name = "pkt_len_stat_513_to_1024_req_delta_caution")
	private Integer pktLenStat513To1024ReqDeltaCaution;

   	@Column(name = "pkt_len_stat_513_to_1024_req_delta_warning")
	private Integer pktLenStat513To1024ReqDeltaWarning;

   	@Column(name = "pkt_len_stat_513_to_1024_req_delta_danger")
	private Integer pktLenStat513To1024ReqDeltaDanger;

   	@Column(name = "pkt_len_stat_513_to_1024_req_delta_obstacle")
	private Integer pktLenStat513To1024ReqDeltaObstacle;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_delta_use")
	private Boolean pktLenStat1025To1514ReqDeltaUse;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_delta_caution")
	private Integer pktLenStat1025To1514ReqDeltaCaution;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_delta_warning")
	private Integer pktLenStat1025To1514ReqDeltaWarning;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_delta_danger")
	private Integer pktLenStat1025To1514ReqDeltaDanger;

   	@Column(name = "pkt_len_stat_1025_to_1514_req_delta_obstacle")
	private Integer pktLenStat1025To1514ReqDeltaObstacle;

   	@Column(name = "pkt_len_stat_jumbo_req_delta_use")
	private Boolean pktLenStatJumboReqDeltaUse;

   	@Column(name = "pkt_len_stat_jumbo_req_delta_caution")
	private Integer pktLenStatJumboReqDeltaCaution;

   	@Column(name = "pkt_len_stat_jumbo_req_delta_warning")
	private Integer pktLenStatJumboReqDeltaWarning;

   	@Column(name = "pkt_len_stat_jumbo_req_delta_danger")
	private Integer pktLenStatJumboReqDeltaDanger;

   	@Column(name = "pkt_len_stat_jumbo_req_delta_obstacle")
	private Integer pktLenStatJumboReqDeltaObstacle;

   	@Column(name = "pkt_len_stat_1_to_128_res_delta_use")
	private Boolean pktLenStat1To128ResDeltaUse;

   	@Column(name = "pkt_len_stat_1_to_128_res_delta_caution")
	private Integer pktLenStat1To128ResDeltaCaution;

   	@Column(name = "pkt_len_stat_1_to_128_res_delta_warning")
	private Integer pktLenStat1To128ResDeltaWarning;

   	@Column(name = "pkt_len_stat_1_to_128_res_delta_danger")
	private Integer pktLenStat1To128ResDeltaDanger;

   	@Column(name = "pkt_len_stat_1_to_128_res_delta_obstacle")
	private Integer pktLenStat1To128ResDeltaObstacle;

   	@Column(name = "pkt_len_stat_129_to_256_res_delta_use")
	private Boolean pktLenStat129To256ResDeltaUse;

   	@Column(name = "pkt_len_stat_129_to_256_res_delta_caution")
	private Integer pktLenStat129To256ResDeltaCaution;

   	@Column(name = "pkt_len_stat_129_to_256_res_delta_warning")
	private Integer pktLenStat129To256ResDeltaWarning;

   	@Column(name = "pkt_len_stat_129_to_256_res_delta_danger")
	private Integer pktLenStat129To256ResDeltaDanger;

   	@Column(name = "pkt_len_stat_129_to_256_res_delta_obstacle")
	private Integer pktLenStat129To256ResDeltaObstacle;

   	@Column(name = "pkt_len_stat_257_to_512_res_delta_use")
	private Boolean pktLenStat257To512ResDeltaUse;

   	@Column(name = "pkt_len_stat_257_to_512_res_delta_caution")
	private Integer pktLenStat257To512ResDeltaCaution;

   	@Column(name = "pkt_len_stat_257_to_512_res_delta_warning")
	private Integer pktLenStat257To512ResDeltaWarning;

   	@Column(name = "pkt_len_stat_257_to_512_res_delta_danger")
	private Integer pktLenStat257To512ResDeltaDanger;

   	@Column(name = "pkt_len_stat_257_to_512_res_delta_obstacle")
	private Integer pktLenStat257To512ResDeltaObstacle;

   	@Column(name = "pkt_len_stat_513_to_1024_res_delta_use")
	private Boolean pktLenStat513To1024ResDeltaUse;

   	@Column(name = "pkt_len_stat_513_to_1024_res_delta_caution")
	private Integer pktLenStat513To1024ResDeltaCaution;

   	@Column(name = "pkt_len_stat_513_to_1024_res_delta_warning")
	private Integer pktLenStat513To1024ResDeltaWarning;

   	@Column(name = "pkt_len_stat_513_to_1024_res_delta_danger")
	private Integer pktLenStat513To1024ResDeltaDanger;

   	@Column(name = "pkt_len_stat_513_to_1024_res_delta_obstacle")
	private Integer pktLenStat513To1024ResDeltaObstacle;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_delta_use")
	private Boolean pktLenStat1025To1514ResDeltaUse;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_delta_caution")
	private Integer pktLenStat1025To1514ResDeltaCaution;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_delta_warning")
	private Integer pktLenStat1025To1514ResDeltaWarning;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_delta_danger")
	private Integer pktLenStat1025To1514ResDeltaDanger;

   	@Column(name = "pkt_len_stat_1025_to_1514_res_delta_obstacle")
	private Integer pktLenStat1025To1514ResDeltaObstacle;

   	@Column(name = "pkt_len_stat_jumbo_res_delta_use")
	private Boolean pktLenStatJumboResDeltaUse;

   	@Column(name = "pkt_len_stat_jumbo_res_delta_caution")
	private Integer pktLenStatJumboResDeltaCaution;

   	@Column(name = "pkt_len_stat_jumbo_res_delta_warning")
	private Integer pktLenStatJumboResDeltaWarning;

   	@Column(name = "pkt_len_stat_jumbo_res_delta_danger")
	private Integer pktLenStatJumboResDeltaDanger;

   	@Column(name = "pkt_len_stat_jumbo_res_delta_obstacle")
	private Integer pktLenStatJumboResDeltaObstacle;


}
