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
@Table (name = "tbl_info_alarm_l3_ip")
public class InfoAlarmL3Ip {

	@Id
	@SequenceGenerator(name="tbl_info_alarm_l3_ip_seq", sequenceName="tbl_info_alarm_l3_ip_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_alarm_l3_ip_seq")
   	@Column(name = "l3_ip_id")
	private Integer l3IpId;

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

   	@Column(name = "frag_pkts_tot_req_use")
	private Boolean fragPktsTotReqUse;

   	@Column(name = "frag_pkts_tot_req_caution")
	private Integer fragPktsTotReqCaution;

   	@Column(name = "frag_pkts_tot_req_warning")
	private Integer fragPktsTotReqWarning;

   	@Column(name = "frag_pkts_tot_req_danger")
	private Integer fragPktsTotReqDanger;

   	@Column(name = "frag_pkts_tot_req_obstacle")
	private Integer fragPktsTotReqObstacle;

   	@Column(name = "frag_pkts_tot_res_use")
	private Boolean fragPktsTotResUse;

   	@Column(name = "frag_pkts_tot_res_caution")
	private Integer fragPktsTotResCaution;

   	@Column(name = "frag_pkts_tot_res_warning")
	private Integer fragPktsTotResWarning;

   	@Column(name = "frag_pkts_tot_res_danger")
	private Integer fragPktsTotResDanger;

   	@Column(name = "frag_pkts_tot_res_obstacle")
	private Integer fragPktsTotResObstacle;

   	@Column(name = "frag_pkts_per_sec_req_use")
	private Boolean fragPktsPerSecReqUse;

   	@Column(name = "frag_pkts_per_sec_req_caution")
	private Integer fragPktsPerSecReqCaution;

   	@Column(name = "frag_pkts_per_sec_req_warning")
	private Integer fragPktsPerSecReqWarning;

   	@Column(name = "frag_pkts_per_sec_req_danger")
	private Integer fragPktsPerSecReqDanger;

   	@Column(name = "frag_pkts_per_sec_req_obstacle")
	private Integer fragPktsPerSecReqObstacle;

   	@Column(name = "frag_pkts_per_sec_res_use")
	private Boolean fragPktsPerSecResUse;

   	@Column(name = "frag_pkts_per_sec_res_caution")
	private Integer fragPktsPerSecResCaution;

   	@Column(name = "frag_pkts_per_sec_res_warning")
	private Integer fragPktsPerSecResWarning;

   	@Column(name = "frag_pkts_per_sec_res_danger")
	private Integer fragPktsPerSecResDanger;

   	@Column(name = "frag_pkts_per_sec_res_obstacle")
	private Integer fragPktsPerSecResObstacle;

   	@Column(name = "frag_pkts_delta_req_use")
	private Boolean fragPktsDeltaReqUse;

   	@Column(name = "frag_pkts_delta_req_caution")
	private Integer fragPktsDeltaReqCaution;

   	@Column(name = "frag_pkts_delta_req_warning")
	private Integer fragPktsDeltaReqWarning;

   	@Column(name = "frag_pkts_delta_req_danger")
	private Integer fragPktsDeltaReqDanger;

   	@Column(name = "frag_pkts_delta_req_obstacle")
	private Integer fragPktsDeltaReqObstacle;

   	@Column(name = "frag_pkts_delta_res_use")
	private Boolean fragPktsDeltaResUse;

   	@Column(name = "frag_pkts_delta_res_caution")
	private Integer fragPktsDeltaResCaution;

   	@Column(name = "frag_pkts_delta_res_warning")
	private Integer fragPktsDeltaResWarning;

   	@Column(name = "frag_pkts_delta_res_danger")
	private Integer fragPktsDeltaResDanger;

   	@Column(name = "frag_pkts_delta_res_obstacle")
	private Integer fragPktsDeltaResObstacle;

   	@Column(name = "ttl_min_req_use")
	private Boolean ttlMinReqUse;

   	@Column(name = "ttl_min_req_caution")
	private Integer ttlMinReqCaution;

   	@Column(name = "ttl_min_req_warning")
	private Integer ttlMinReqWarning;

   	@Column(name = "ttl_min_req_danger")
	private Integer ttlMinReqDanger;

   	@Column(name = "ttl_min_req_obstacle")
	private Integer ttlMinReqObstacle;

   	@Column(name = "ttl_min_res_use")
	private Boolean ttlMinResUse;

   	@Column(name = "ttl_min_res_caution")
	private Integer ttlMinResCaution;

   	@Column(name = "ttl_min_res_warning")
	private Integer ttlMinResWarning;

   	@Column(name = "ttl_min_res_danger")
	private Integer ttlMinResDanger;

   	@Column(name = "ttl_min_res_obstacle")
	private Integer ttlMinResObstacle;

   	@Column(name = "ttl_max_req_use")
	private Boolean ttlMaxReqUse;

   	@Column(name = "ttl_max_req_caution")
	private Integer ttlMaxReqCaution;

   	@Column(name = "ttl_max_req_warning")
	private Integer ttlMaxReqWarning;

   	@Column(name = "ttl_max_req_danger")
	private Integer ttlMaxReqDanger;

   	@Column(name = "ttl_max_req_obstacle")
	private Integer ttlMaxReqObstacle;

   	@Column(name = "ttl_max_res_use")
	private Boolean ttlMaxResUse;

   	@Column(name = "ttl_max_res_caution")
	private Integer ttlMaxResCaution;

   	@Column(name = "ttl_max_res_warning")
	private Integer ttlMaxResWarning;

   	@Column(name = "ttl_max_res_danger")
	private Integer ttlMaxResDanger;

   	@Column(name = "ttl_max_res_obstacle")
	private Integer ttlMaxResObstacle;

   	@Column(name = "ttl_stat_1_req_tot_use")
	private Boolean ttlStat1ReqTotUse;

   	@Column(name = "ttl_stat_1_req_tot_caution")
	private Integer ttlStat1ReqTotCaution;

   	@Column(name = "ttl_stat_1_req_tot_warning")
	private Integer ttlStat1ReqTotWarning;

   	@Column(name = "ttl_stat_1_req_tot_danger")
	private Integer ttlStat1ReqTotDanger;

   	@Column(name = "ttl_stat_1_req_tot_obstacle")
	private Integer ttlStat1ReqTotObstacle;

   	@Column(name = "ttl_stat_2_to_5_req_tot_use")
	private Boolean ttlStat2To5ReqTotUse;

   	@Column(name = "ttl_stat_2_to_5_req_tot_caution")
	private Integer ttlStat2To5ReqTotCaution;

   	@Column(name = "ttl_stat_2_to_5_req_tot_warning")
	private Integer ttlStat2To5ReqTotWarning;

   	@Column(name = "ttl_stat_2_to_5_req_tot_danger")
	private Integer ttlStat2To5ReqTotDanger;

   	@Column(name = "ttl_stat_2_to_5_req_tot_obstacle")
	private Integer ttlStat2To5ReqTotObstacle;

   	@Column(name = "ttl_stat_6_to_32_req_tot_use")
	private Boolean ttlStat6To32ReqTotUse;

   	@Column(name = "ttl_stat_6_to_32_req_tot_caution")
	private Integer ttlStat6To32ReqTotCaution;

   	@Column(name = "ttl_stat_6_to_32_req_tot_warning")
	private Integer ttlStat6To32ReqTotWarning;

   	@Column(name = "ttl_stat_6_to_32_req_tot_danger")
	private Integer ttlStat6To32ReqTotDanger;

   	@Column(name = "ttl_stat_6_to_32_req_tot_obstacle")
	private Integer ttlStat6To32ReqTotObstacle;

   	@Column(name = "ttl_stat_33_to_64_req_tot_use")
	private Boolean ttlStat33To64ReqTotUse;

   	@Column(name = "ttl_stat_33_to_64_req_tot_caution")
	private Integer ttlStat33To64ReqTotCaution;

   	@Column(name = "ttl_stat_33_to_64_req_tot_warning")
	private Integer ttlStat33To64ReqTotWarning;

   	@Column(name = "ttl_stat_33_to_64_req_tot_danger")
	private Integer ttlStat33To64ReqTotDanger;

   	@Column(name = "ttl_stat_33_to_64_req_tot_obstacle")
	private Integer ttlStat33To64ReqTotObstacle;

   	@Column(name = "ttl_stat_65_to_96_req_tot_use")
	private Boolean ttlStat65To96ReqTotUse;

   	@Column(name = "ttl_stat_65_to_96_req_tot_caution")
	private Integer ttlStat65To96ReqTotCaution;

   	@Column(name = "ttl_stat_65_to_96_req_tot_warning")
	private Integer ttlStat65To96ReqTotWarning;

   	@Column(name = "ttl_stat_65_to_96_req_tot_danger")
	private Integer ttlStat65To96ReqTotDanger;

   	@Column(name = "ttl_stat_65_to_96_req_tot_obstacle")
	private Integer ttlStat65To96ReqTotObstacle;

   	@Column(name = "ttl_stat_97_to_128_req_tot_use")
	private Boolean ttlStat97To128ReqTotUse;

   	@Column(name = "ttl_stat_97_to_128_req_tot_caution")
	private Integer ttlStat97To128ReqTotCaution;

   	@Column(name = "ttl_stat_97_to_128_req_tot_warning")
	private Integer ttlStat97To128ReqTotWarning;

   	@Column(name = "ttl_stat_97_to_128_req_tot_danger")
	private Integer ttlStat97To128ReqTotDanger;

   	@Column(name = "ttl_stat_97_to_128_req_tot_obstacle")
	private Integer ttlStat97To128ReqTotObstacle;

   	@Column(name = "ttl_stat_129_to_160_req_tot_use")
	private Boolean ttlStat129To160ReqTotUse;

   	@Column(name = "ttl_stat_129_to_160_req_tot_caution")
	private Integer ttlStat129To160ReqTotCaution;

   	@Column(name = "ttl_stat_129_to_160_req_tot_warning")
	private Integer ttlStat129To160ReqTotWarning;

   	@Column(name = "ttl_stat_129_to_160_req_tot_danger")
	private Integer ttlStat129To160ReqTotDanger;

   	@Column(name = "ttl_stat_129_to_160_req_tot_obstacle")
	private Integer ttlStat129To160ReqTotObstacle;

   	@Column(name = "ttl_stat_161_to_192_req_tot_use")
	private Boolean ttlStat161To192ReqTotUse;

   	@Column(name = "ttl_stat_161_to_192_req_tot_caution")
	private Integer ttlStat161To192ReqTotCaution;

   	@Column(name = "ttl_stat_161_to_192_req_tot_warning")
	private Integer ttlStat161To192ReqTotWarning;

   	@Column(name = "ttl_stat_161_to_192_req_tot_danger")
	private Integer ttlStat161To192ReqTotDanger;

   	@Column(name = "ttl_stat_161_to_192_req_tot_obstacle")
	private Integer ttlStat161To192ReqTotObstacle;

   	@Column(name = "ttl_stat_193_to_224_req_tot_use")
	private Boolean ttlStat193To224ReqTotUse;

   	@Column(name = "ttl_stat_193_to_224_req_tot_caution")
	private Integer ttlStat193To224ReqTotCaution;

   	@Column(name = "ttl_stat_193_to_224_req_tot_warning")
	private Integer ttlStat193To224ReqTotWarning;

   	@Column(name = "ttl_stat_193_to_224_req_tot_danger")
	private Integer ttlStat193To224ReqTotDanger;

   	@Column(name = "ttl_stat_193_to_224_req_tot_obstacle")
	private Integer ttlStat193To224ReqTotObstacle;

   	@Column(name = "ttl_stat_225_to_255_req_tot_use")
	private Boolean ttlStat225To255ReqTotUse;

   	@Column(name = "ttl_stat_225_to_255_req_tot_caution")
	private Integer ttlStat225To255ReqTotCaution;

   	@Column(name = "ttl_stat_225_to_255_req_tot_warning")
	private Integer ttlStat225To255ReqTotWarning;

   	@Column(name = "ttl_stat_225_to_255_req_tot_danger")
	private Integer ttlStat225To255ReqTotDanger;

   	@Column(name = "ttl_stat_225_to_255_req_tot_obstacle")
	private Integer ttlStat225To255ReqTotObstacle;

   	@Column(name = "ttl_stat_1_res_tot_use")
	private Boolean ttlStat1ResTotUse;

   	@Column(name = "ttl_stat_1_res_tot_caution")
	private Integer ttlStat1ResTotCaution;

   	@Column(name = "ttl_stat_1_res_tot_warning")
	private Integer ttlStat1ResTotWarning;

   	@Column(name = "ttl_stat_1_res_tot_danger")
	private Integer ttlStat1ResTotDanger;

   	@Column(name = "ttl_stat_1_res_tot_obstacle")
	private Integer ttlStat1ResTotObstacle;

   	@Column(name = "ttl_stat_2_to_5_res_tot_use")
	private Boolean ttlStat2To5ResTotUse;

   	@Column(name = "ttl_stat_2_to_5_res_tot_caution")
	private Integer ttlStat2To5ResTotCaution;

   	@Column(name = "ttl_stat_2_to_5_res_tot_warning")
	private Integer ttlStat2To5ResTotWarning;

   	@Column(name = "ttl_stat_2_to_5_res_tot_danger")
	private Integer ttlStat2To5ResTotDanger;

   	@Column(name = "ttl_stat_2_to_5_res_tot_obstacle")
	private Integer ttlStat2To5ResTotObstacle;

   	@Column(name = "ttl_stat_6_to_32_res_tot_use")
	private Boolean ttlStat6To32ResTotUse;

   	@Column(name = "ttl_stat_6_to_32_res_tot_caution")
	private Integer ttlStat6To32ResTotCaution;

   	@Column(name = "ttl_stat_6_to_32_res_tot_warning")
	private Integer ttlStat6To32ResTotWarning;

   	@Column(name = "ttl_stat_6_to_32_res_tot_danger")
	private Integer ttlStat6To32ResTotDanger;

   	@Column(name = "ttl_stat_6_to_32_res_tot_obstacle")
	private Integer ttlStat6To32ResTotObstacle;

   	@Column(name = "ttl_stat_33_to_64_res_tot_use")
	private Boolean ttlStat33To64ResTotUse;

   	@Column(name = "ttl_stat_33_to_64_res_tot_caution")
	private Integer ttlStat33To64ResTotCaution;

   	@Column(name = "ttl_stat_33_to_64_res_tot_warning")
	private Integer ttlStat33To64ResTotWarning;

   	@Column(name = "ttl_stat_33_to_64_res_tot_danger")
	private Integer ttlStat33To64ResTotDanger;

   	@Column(name = "ttl_stat_33_to_64_res_tot_obstacle")
	private Integer ttlStat33To64ResTotObstacle;

   	@Column(name = "ttl_stat_65_to_96_res_tot_use")
	private Boolean ttlStat65To96ResTotUse;

   	@Column(name = "ttl_stat_65_to_96_res_tot_caution")
	private Integer ttlStat65To96ResTotCaution;

   	@Column(name = "ttl_stat_65_to_96_res_tot_warning")
	private Integer ttlStat65To96ResTotWarning;

   	@Column(name = "ttl_stat_65_to_96_res_tot_danger")
	private Integer ttlStat65To96ResTotDanger;

   	@Column(name = "ttl_stat_65_to_96_res_tot_obstacle")
	private Integer ttlStat65To96ResTotObstacle;

   	@Column(name = "ttl_stat_97_to_128_res_tot_use")
	private Boolean ttlStat97To128ResTotUse;

   	@Column(name = "ttl_stat_97_to_128_res_tot_caution")
	private Integer ttlStat97To128ResTotCaution;

   	@Column(name = "ttl_stat_97_to_128_res_tot_warning")
	private Integer ttlStat97To128ResTotWarning;

   	@Column(name = "ttl_stat_97_to_128_res_tot_danger")
	private Integer ttlStat97To128ResTotDanger;

   	@Column(name = "ttl_stat_97_to_128_res_tot_obstacle")
	private Integer ttlStat97To128ResTotObstacle;

   	@Column(name = "ttl_stat_129_to_160_res_tot_use")
	private Boolean ttlStat129To160ResTotUse;

   	@Column(name = "ttl_stat_129_to_160_res_tot_caution")
	private Integer ttlStat129To160ResTotCaution;

   	@Column(name = "ttl_stat_129_to_160_res_tot_warning")
	private Integer ttlStat129To160ResTotWarning;

   	@Column(name = "ttl_stat_129_to_160_res_tot_danger")
	private Integer ttlStat129To160ResTotDanger;

   	@Column(name = "ttl_stat_129_to_160_res_tot_obstacle")
	private Integer ttlStat129To160ResTotObstacle;

   	@Column(name = "ttl_stat_161_to_192_res_tot_use")
	private Boolean ttlStat161To192ResTotUse;

   	@Column(name = "ttl_stat_161_to_192_res_tot_caution")
	private Integer ttlStat161To192ResTotCaution;

   	@Column(name = "ttl_stat_161_to_192_res_tot_warning")
	private Integer ttlStat161To192ResTotWarning;

   	@Column(name = "ttl_stat_161_to_192_res_tot_danger")
	private Integer ttlStat161To192ResTotDanger;

   	@Column(name = "ttl_stat_161_to_192_res_tot_obstacle")
	private Integer ttlStat161To192ResTotObstacle;

   	@Column(name = "ttl_stat_193_to_224_res_tot_use")
	private Boolean ttlStat193To224ResTotUse;

   	@Column(name = "ttl_stat_193_to_224_res_tot_caution")
	private Integer ttlStat193To224ResTotCaution;

   	@Column(name = "ttl_stat_193_to_224_res_tot_warning")
	private Integer ttlStat193To224ResTotWarning;

   	@Column(name = "ttl_stat_193_to_224_res_tot_danger")
	private Integer ttlStat193To224ResTotDanger;

   	@Column(name = "ttl_stat_193_to_224_res_tot_obstacle")
	private Integer ttlStat193To224ResTotObstacle;

   	@Column(name = "ttl_stat_225_to_255_res_tot_use")
	private Boolean ttlStat225To255ResTotUse;

   	@Column(name = "ttl_stat_225_to_255_res_tot_caution")
	private Integer ttlStat225To255ResTotCaution;

   	@Column(name = "ttl_stat_225_to_255_res_tot_warning")
	private Integer ttlStat225To255ResTotWarning;

   	@Column(name = "ttl_stat_225_to_255_res_tot_danger")
	private Integer ttlStat225To255ResTotDanger;

   	@Column(name = "ttl_stat_225_to_255_res_tot_obstacle")
	private Integer ttlStat225To255ResTotObstacle;

   	@Column(name = "ttl_stat_1_req_per_sec_use")
	private Boolean ttlStat1ReqPerSecUse;

   	@Column(name = "ttl_stat_1_req_per_sec_caution")
	private Integer ttlStat1ReqPerSecCaution;

   	@Column(name = "ttl_stat_1_req_per_sec_warning")
	private Integer ttlStat1ReqPerSecWarning;

   	@Column(name = "ttl_stat_1_req_per_sec_danger")
	private Integer ttlStat1ReqPerSecDanger;

   	@Column(name = "ttl_stat_1_req_per_sec_obstacle")
	private Integer ttlStat1ReqPerSecObstacle;

   	@Column(name = "ttl_stat_2_to_5_req_per_sec_use")
	private Boolean ttlStat2To5ReqPerSecUse;

   	@Column(name = "ttl_stat_2_to_5_req_per_sec_caution")
	private Integer ttlStat2To5ReqPerSecCaution;

   	@Column(name = "ttl_stat_2_to_5_req_per_sec_warning")
	private Integer ttlStat2To5ReqPerSecWarning;

   	@Column(name = "ttl_stat_2_to_5_req_per_sec_danger")
	private Integer ttlStat2To5ReqPerSecDanger;

   	@Column(name = "ttl_stat_2_to_5_req_per_sec_obstacle")
	private Integer ttlStat2To5ReqPerSecObstacle;

   	@Column(name = "ttl_stat_6_to_32_req_per_sec_use")
	private Boolean ttlStat6To32ReqPerSecUse;

   	@Column(name = "ttl_stat_6_to_32_req_per_sec_caution")
	private Integer ttlStat6To32ReqPerSecCaution;

   	@Column(name = "ttl_stat_6_to_32_req_per_sec_warning")
	private Integer ttlStat6To32ReqPerSecWarning;

   	@Column(name = "ttl_stat_6_to_32_req_per_sec_danger")
	private Integer ttlStat6To32ReqPerSecDanger;

   	@Column(name = "ttl_stat_6_to_32_req_per_sec_obstacle")
	private Integer ttlStat6To32ReqPerSecObstacle;

   	@Column(name = "ttl_stat_33_to_64_req_per_sec_use")
	private Boolean ttlStat33To64ReqPerSecUse;

   	@Column(name = "ttl_stat_33_to_64_req_per_sec_caution")
	private Integer ttlStat33To64ReqPerSecCaution;

   	@Column(name = "ttl_stat_33_to_64_req_per_sec_warning")
	private Integer ttlStat33To64ReqPerSecWarning;

   	@Column(name = "ttl_stat_33_to_64_req_per_sec_danger")
	private Integer ttlStat33To64ReqPerSecDanger;

   	@Column(name = "ttl_stat_33_to_64_req_per_sec_obstacle")
	private Integer ttlStat33To64ReqPerSecObstacle;

   	@Column(name = "ttl_stat_65_to_96_req_per_sec_use")
	private Boolean ttlStat65To96ReqPerSecUse;

   	@Column(name = "ttl_stat_65_to_96_req_per_sec_caution")
	private Integer ttlStat65To96ReqPerSecCaution;

   	@Column(name = "ttl_stat_65_to_96_req_per_sec_warning")
	private Integer ttlStat65To96ReqPerSecWarning;

   	@Column(name = "ttl_stat_65_to_96_req_per_sec_danger")
	private Integer ttlStat65To96ReqPerSecDanger;

   	@Column(name = "ttl_stat_65_to_96_req_per_sec_obstacle")
	private Integer ttlStat65To96ReqPerSecObstacle;

   	@Column(name = "ttl_stat_97_to_128_req_per_sec_use")
	private Boolean ttlStat97To128ReqPerSecUse;

   	@Column(name = "ttl_stat_97_to_128_req_per_sec_caution")
	private Integer ttlStat97To128ReqPerSecCaution;

   	@Column(name = "ttl_stat_97_to_128_req_per_sec_warning")
	private Integer ttlStat97To128ReqPerSecWarning;

   	@Column(name = "ttl_stat_97_to_128_req_per_sec_danger")
	private Integer ttlStat97To128ReqPerSecDanger;

   	@Column(name = "ttl_stat_97_to_128_req_per_sec_obstacle")
	private Integer ttlStat97To128ReqPerSecObstacle;

   	@Column(name = "ttl_stat_129_to_160_req_per_sec_use")
	private Boolean ttlStat129To160ReqPerSecUse;

   	@Column(name = "ttl_stat_129_to_160_req_per_sec_caution")
	private Integer ttlStat129To160ReqPerSecCaution;

   	@Column(name = "ttl_stat_129_to_160_req_per_sec_warning")
	private Integer ttlStat129To160ReqPerSecWarning;

   	@Column(name = "ttl_stat_129_to_160_req_per_sec_danger")
	private Integer ttlStat129To160ReqPerSecDanger;

   	@Column(name = "ttl_stat_129_to_160_req_per_sec_obstacle")
	private Integer ttlStat129To160ReqPerSecObstacle;

   	@Column(name = "ttl_stat_161_to_192_req_per_sec_use")
	private Boolean ttlStat161To192ReqPerSecUse;

   	@Column(name = "ttl_stat_161_to_192_req_per_sec_caution")
	private Integer ttlStat161To192ReqPerSecCaution;

   	@Column(name = "ttl_stat_161_to_192_req_per_sec_warning")
	private Integer ttlStat161To192ReqPerSecWarning;

   	@Column(name = "ttl_stat_161_to_192_req_per_sec_danger")
	private Integer ttlStat161To192ReqPerSecDanger;

   	@Column(name = "ttl_stat_161_to_192_req_per_sec_obstacle")
	private Integer ttlStat161To192ReqPerSecObstacle;

   	@Column(name = "ttl_stat_193_to_224_req_per_sec_use")
	private Boolean ttlStat193To224ReqPerSecUse;

   	@Column(name = "ttl_stat_193_to_224_req_per_sec_caution")
	private Integer ttlStat193To224ReqPerSecCaution;

   	@Column(name = "ttl_stat_193_to_224_req_per_sec_warning")
	private Integer ttlStat193To224ReqPerSecWarning;

   	@Column(name = "ttl_stat_193_to_224_req_per_sec_danger")
	private Integer ttlStat193To224ReqPerSecDanger;

   	@Column(name = "ttl_stat_193_to_224_req_per_sec_obstacle")
	private Integer ttlStat193To224ReqPerSecObstacle;

   	@Column(name = "ttl_stat_225_to_255_req_per_sec_use")
	private Boolean ttlStat225To255ReqPerSecUse;

   	@Column(name = "ttl_stat_225_to_255_req_per_sec_caution")
	private Integer ttlStat225To255ReqPerSecCaution;

   	@Column(name = "ttl_stat_225_to_255_req_per_sec_warning")
	private Integer ttlStat225To255ReqPerSecWarning;

   	@Column(name = "ttl_stat_225_to_255_req_per_sec_danger")
	private Integer ttlStat225To255ReqPerSecDanger;

   	@Column(name = "ttl_stat_225_to_255_req_per_sec_obstacle")
	private Integer ttlStat225To255ReqPerSecObstacle;

   	@Column(name = "ttl_stat_1_res_per_sec_use")
	private Boolean ttlStat1ResPerSecUse;

   	@Column(name = "ttl_stat_1_res_per_sec_caution")
	private Integer ttlStat1ResPerSecCaution;

   	@Column(name = "ttl_stat_1_res_per_sec_warning")
	private Integer ttlStat1ResPerSecWarning;

   	@Column(name = "ttl_stat_1_res_per_sec_danger")
	private Integer ttlStat1ResPerSecDanger;

   	@Column(name = "ttl_stat_1_res_per_sec_obstacle")
	private Integer ttlStat1ResPerSecObstacle;

   	@Column(name = "ttl_stat_2_to_5_res_per_sec_use")
	private Boolean ttlStat2To5ResPerSecUse;

   	@Column(name = "ttl_stat_2_to_5_res_per_sec_caution")
	private Integer ttlStat2To5ResPerSecCaution;

   	@Column(name = "ttl_stat_2_to_5_res_per_sec_warning")
	private Integer ttlStat2To5ResPerSecWarning;

   	@Column(name = "ttl_stat_2_to_5_res_per_sec_danger")
	private Integer ttlStat2To5ResPerSecDanger;

   	@Column(name = "ttl_stat_2_to_5_res_per_sec_obstacle")
	private Integer ttlStat2To5ResPerSecObstacle;

   	@Column(name = "ttl_stat_6_to_32_res_per_sec_use")
	private Boolean ttlStat6To32ResPerSecUse;

   	@Column(name = "ttl_stat_6_to_32_res_per_sec_caution")
	private Integer ttlStat6To32ResPerSecCaution;

   	@Column(name = "ttl_stat_6_to_32_res_per_sec_warning")
	private Integer ttlStat6To32ResPerSecWarning;

   	@Column(name = "ttl_stat_6_to_32_res_per_sec_danger")
	private Integer ttlStat6To32ResPerSecDanger;

   	@Column(name = "ttl_stat_6_to_32_res_per_sec_obstacle")
	private Integer ttlStat6To32ResPerSecObstacle;

   	@Column(name = "ttl_stat_33_to_64_res_per_sec_use")
	private Boolean ttlStat33To64ResPerSecUse;

   	@Column(name = "ttl_stat_33_to_64_res_per_sec_caution")
	private Integer ttlStat33To64ResPerSecCaution;

   	@Column(name = "ttl_stat_33_to_64_res_per_sec_warning")
	private Integer ttlStat33To64ResPerSecWarning;

   	@Column(name = "ttl_stat_33_to_64_res_per_sec_danger")
	private Integer ttlStat33To64ResPerSecDanger;

   	@Column(name = "ttl_stat_33_to_64_res_per_sec_obstacle")
	private Integer ttlStat33To64ResPerSecObstacle;

   	@Column(name = "ttl_stat_65_to_96_res_per_sec_use")
	private Boolean ttlStat65To96ResPerSecUse;

   	@Column(name = "ttl_stat_65_to_96_res_per_sec_caution")
	private Integer ttlStat65To96ResPerSecCaution;

   	@Column(name = "ttl_stat_65_to_96_res_per_sec_warning")
	private Integer ttlStat65To96ResPerSecWarning;

   	@Column(name = "ttl_stat_65_to_96_res_per_sec_danger")
	private Integer ttlStat65To96ResPerSecDanger;

   	@Column(name = "ttl_stat_65_to_96_res_per_sec_obstacle")
	private Integer ttlStat65To96ResPerSecObstacle;

   	@Column(name = "ttl_stat_97_to_128_res_per_sec_use")
	private Boolean ttlStat97To128ResPerSecUse;

   	@Column(name = "ttl_stat_97_to_128_res_per_sec_caution")
	private Integer ttlStat97To128ResPerSecCaution;

   	@Column(name = "ttl_stat_97_to_128_res_per_sec_warning")
	private Integer ttlStat97To128ResPerSecWarning;

   	@Column(name = "ttl_stat_97_to_128_res_per_sec_danger")
	private Integer ttlStat97To128ResPerSecDanger;

   	@Column(name = "ttl_stat_97_to_128_res_per_sec_obstacle")
	private Integer ttlStat97To128ResPerSecObstacle;

   	@Column(name = "ttl_stat_129_to_160_res_per_sec_use")
	private Boolean ttlStat129To160ResPerSecUse;

   	@Column(name = "ttl_stat_129_to_160_res_per_sec_caution")
	private Integer ttlStat129To160ResPerSecCaution;

   	@Column(name = "ttl_stat_129_to_160_res_per_sec_warning")
	private Integer ttlStat129To160ResPerSecWarning;

   	@Column(name = "ttl_stat_129_to_160_res_per_sec_danger")
	private Integer ttlStat129To160ResPerSecDanger;

   	@Column(name = "ttl_stat_129_to_160_res_per_sec_obstacle")
	private Integer ttlStat129To160ResPerSecObstacle;

   	@Column(name = "ttl_stat_161_to_192_res_per_sec_use")
	private Boolean ttlStat161To192ResPerSecUse;

   	@Column(name = "ttl_stat_161_to_192_res_per_sec_caution")
	private Integer ttlStat161To192ResPerSecCaution;

   	@Column(name = "ttl_stat_161_to_192_res_per_sec_warning")
	private Integer ttlStat161To192ResPerSecWarning;

   	@Column(name = "ttl_stat_161_to_192_res_per_sec_danger")
	private Integer ttlStat161To192ResPerSecDanger;

   	@Column(name = "ttl_stat_161_to_192_res_per_sec_obstacle")
	private Integer ttlStat161To192ResPerSecObstacle;

   	@Column(name = "ttl_stat_193_to_224_res_per_sec_use")
	private Boolean ttlStat193To224ResPerSecUse;

   	@Column(name = "ttl_stat_193_to_224_res_per_sec_caution")
	private Integer ttlStat193To224ResPerSecCaution;

   	@Column(name = "ttl_stat_193_to_224_res_per_sec_warning")
	private Integer ttlStat193To224ResPerSecWarning;

   	@Column(name = "ttl_stat_193_to_224_res_per_sec_danger")
	private Integer ttlStat193To224ResPerSecDanger;

   	@Column(name = "ttl_stat_193_to_224_res_per_sec_obstacle")
	private Integer ttlStat193To224ResPerSecObstacle;

   	@Column(name = "ttl_stat_225_to_255_res_per_sec_use")
	private Boolean ttlStat225To255ResPerSecUse;

   	@Column(name = "ttl_stat_225_to_255_res_per_sec_caution")
	private Integer ttlStat225To255ResPerSecCaution;

   	@Column(name = "ttl_stat_225_to_255_res_per_sec_warning")
	private Integer ttlStat225To255ResPerSecWarning;

   	@Column(name = "ttl_stat_225_to_255_res_per_sec_danger")
	private Integer ttlStat225To255ResPerSecDanger;

   	@Column(name = "ttl_stat_225_to_255_res_per_sec_obstacle")
	private Integer ttlStat225To255ResPerSecObstacle;

   	@Column(name = "ttl_stat_1_req_delta_use")
	private Boolean ttlStat1ReqDeltaUse;

   	@Column(name = "ttl_stat_1_req_delta_caution")
	private Integer ttlStat1ReqDeltaCaution;

   	@Column(name = "ttl_stat_1_req_delta_warning")
	private Integer ttlStat1ReqDeltaWarning;

   	@Column(name = "ttl_stat_1_req_delta_danger")
	private Integer ttlStat1ReqDeltaDanger;

   	@Column(name = "ttl_stat_1_req_delta_obstacle")
	private Integer ttlStat1ReqDeltaObstacle;

   	@Column(name = "ttl_stat_2_to_5_req_delta_use")
	private Boolean ttlStat2To5ReqDeltaUse;

   	@Column(name = "ttl_stat_2_to_5_req_delta_caution")
	private Integer ttlStat2To5ReqDeltaCaution;

   	@Column(name = "ttl_stat_2_to_5_req_delta_warning")
	private Integer ttlStat2To5ReqDeltaWarning;

   	@Column(name = "ttl_stat_2_to_5_req_delta_danger")
	private Integer ttlStat2To5ReqDeltaDanger;

   	@Column(name = "ttl_stat_2_to_5_req_delta_obstacle")
	private Integer ttlStat2To5ReqDeltaObstacle;

   	@Column(name = "ttl_stat_6_to_32_req_delta_use")
	private Boolean ttlStat6To32ReqDeltaUse;

   	@Column(name = "ttl_stat_6_to_32_req_delta_caution")
	private Integer ttlStat6To32ReqDeltaCaution;

   	@Column(name = "ttl_stat_6_to_32_req_delta_warning")
	private Integer ttlStat6To32ReqDeltaWarning;

   	@Column(name = "ttl_stat_6_to_32_req_delta_danger")
	private Integer ttlStat6To32ReqDeltaDanger;

   	@Column(name = "ttl_stat_6_to_32_req_delta_obstacle")
	private Integer ttlStat6To32ReqDeltaObstacle;

   	@Column(name = "ttl_stat_33_to_64_req_delta_use")
	private Boolean ttlStat33To64ReqDeltaUse;

   	@Column(name = "ttl_stat_33_to_64_req_delta_caution")
	private Integer ttlStat33To64ReqDeltaCaution;

   	@Column(name = "ttl_stat_33_to_64_req_delta_warning")
	private Integer ttlStat33To64ReqDeltaWarning;

   	@Column(name = "ttl_stat_33_to_64_req_delta_danger")
	private Integer ttlStat33To64ReqDeltaDanger;

   	@Column(name = "ttl_stat_33_to_64_req_delta_obstacle")
	private Integer ttlStat33To64ReqDeltaObstacle;

   	@Column(name = "ttl_stat_65_to_96_req_delta_use")
	private Boolean ttlStat65To96ReqDeltaUse;

   	@Column(name = "ttl_stat_65_to_96_req_delta_caution")
	private Integer ttlStat65To96ReqDeltaCaution;

   	@Column(name = "ttl_stat_65_to_96_req_delta_warning")
	private Integer ttlStat65To96ReqDeltaWarning;

   	@Column(name = "ttl_stat_65_to_96_req_delta_danger")
	private Integer ttlStat65To96ReqDeltaDanger;

   	@Column(name = "ttl_stat_65_to_96_req_delta_obstacle")
	private Integer ttlStat65To96ReqDeltaObstacle;

   	@Column(name = "ttl_stat_97_to_128_req_delta_use")
	private Boolean ttlStat97To128ReqDeltaUse;

   	@Column(name = "ttl_stat_97_to_128_req_delta_caution")
	private Integer ttlStat97To128ReqDeltaCaution;

   	@Column(name = "ttl_stat_97_to_128_req_delta_warning")
	private Integer ttlStat97To128ReqDeltaWarning;

   	@Column(name = "ttl_stat_97_to_128_req_delta_danger")
	private Integer ttlStat97To128ReqDeltaDanger;

   	@Column(name = "ttl_stat_97_to_128_req_delta_obstacle")
	private Integer ttlStat97To128ReqDeltaObstacle;

   	@Column(name = "ttl_stat_129_to_160_req_delta_use")
	private Boolean ttlStat129To160ReqDeltaUse;

   	@Column(name = "ttl_stat_129_to_160_req_delta_caution")
	private Integer ttlStat129To160ReqDeltaCaution;

   	@Column(name = "ttl_stat_129_to_160_req_delta_warning")
	private Integer ttlStat129To160ReqDeltaWarning;

   	@Column(name = "ttl_stat_129_to_160_req_delta_danger")
	private Integer ttlStat129To160ReqDeltaDanger;

   	@Column(name = "ttl_stat_129_to_160_req_delta_obstacle")
	private Integer ttlStat129To160ReqDeltaObstacle;

   	@Column(name = "ttl_stat_161_to_192_req_delta_use")
	private Boolean ttlStat161To192ReqDeltaUse;

   	@Column(name = "ttl_stat_161_to_192_req_delta_caution")
	private Integer ttlStat161To192ReqDeltaCaution;

   	@Column(name = "ttl_stat_161_to_192_req_delta_warning")
	private Integer ttlStat161To192ReqDeltaWarning;

   	@Column(name = "ttl_stat_161_to_192_req_delta_danger")
	private Integer ttlStat161To192ReqDeltaDanger;

   	@Column(name = "ttl_stat_161_to_192_req_delta_obstacle")
	private Integer ttlStat161To192ReqDeltaObstacle;

   	@Column(name = "ttl_stat_193_to_224_req_delta_use")
	private Boolean ttlStat193To224ReqDeltaUse;

   	@Column(name = "ttl_stat_193_to_224_req_delta_caution")
	private Integer ttlStat193To224ReqDeltaCaution;

   	@Column(name = "ttl_stat_193_to_224_req_delta_warning")
	private Integer ttlStat193To224ReqDeltaWarning;

   	@Column(name = "ttl_stat_193_to_224_req_delta_danger")
	private Integer ttlStat193To224ReqDeltaDanger;

   	@Column(name = "ttl_stat_193_to_224_req_delta_obstacle")
	private Integer ttlStat193To224ReqDeltaObstacle;

   	@Column(name = "ttl_stat_225_to_255_req_delta_use")
	private Boolean ttlStat225To255ReqDeltaUse;

   	@Column(name = "ttl_stat_225_to_255_req_delta_caution")
	private Integer ttlStat225To255ReqDeltaCaution;

   	@Column(name = "ttl_stat_225_to_255_req_delta_warning")
	private Integer ttlStat225To255ReqDeltaWarning;

   	@Column(name = "ttl_stat_225_to_255_req_delta_danger")
	private Integer ttlStat225To255ReqDeltaDanger;

   	@Column(name = "ttl_stat_225_to_255_req_delta_obstacle")
	private Integer ttlStat225To255ReqDeltaObstacle;

   	@Column(name = "ttl_stat_1_res_delta_use")
	private Boolean ttlStat1ResDeltaUse;

   	@Column(name = "ttl_stat_1_res_delta_caution")
	private Integer ttlStat1ResDeltaCaution;

   	@Column(name = "ttl_stat_1_res_delta_warning")
	private Integer ttlStat1ResDeltaWarning;

   	@Column(name = "ttl_stat_1_res_delta_danger")
	private Integer ttlStat1ResDeltaDanger;

   	@Column(name = "ttl_stat_1_res_delta_obstacle")
	private Integer ttlStat1ResDeltaObstacle;

   	@Column(name = "ttl_stat_2_to_5_res_delta_use")
	private Boolean ttlStat2To5ResDeltaUse;

   	@Column(name = "ttl_stat_2_to_5_res_delta_caution")
	private Integer ttlStat2To5ResDeltaCaution;

   	@Column(name = "ttl_stat_2_to_5_res_delta_warning")
	private Integer ttlStat2To5ResDeltaWarning;

   	@Column(name = "ttl_stat_2_to_5_res_delta_danger")
	private Integer ttlStat2To5ResDeltaDanger;

   	@Column(name = "ttl_stat_2_to_5_res_delta_obstacle")
	private Integer ttlStat2To5ResDeltaObstacle;

   	@Column(name = "ttl_stat_6_to_32_res_delta_use")
	private Boolean ttlStat6To32ResDeltaUse;

   	@Column(name = "ttl_stat_6_to_32_res_delta_caution")
	private Integer ttlStat6To32ResDeltaCaution;

   	@Column(name = "ttl_stat_6_to_32_res_delta_warning")
	private Integer ttlStat6To32ResDeltaWarning;

   	@Column(name = "ttl_stat_6_to_32_res_delta_danger")
	private Integer ttlStat6To32ResDeltaDanger;

   	@Column(name = "ttl_stat_6_to_32_res_delta_obstacle")
	private Integer ttlStat6To32ResDeltaObstacle;

   	@Column(name = "ttl_stat_33_to_64_res_delta_use")
	private Boolean ttlStat33To64ResDeltaUse;

   	@Column(name = "ttl_stat_33_to_64_res_delta_caution")
	private Integer ttlStat33To64ResDeltaCaution;

   	@Column(name = "ttl_stat_33_to_64_res_delta_warning")
	private Integer ttlStat33To64ResDeltaWarning;

   	@Column(name = "ttl_stat_33_to_64_res_delta_danger")
	private Integer ttlStat33To64ResDeltaDanger;

   	@Column(name = "ttl_stat_33_to_64_res_delta_obstacle")
	private Integer ttlStat33To64ResDeltaObstacle;

   	@Column(name = "ttl_stat_65_to_96_res_delta_use")
	private Boolean ttlStat65To96ResDeltaUse;

   	@Column(name = "ttl_stat_65_to_96_res_delta_caution")
	private Integer ttlStat65To96ResDeltaCaution;

   	@Column(name = "ttl_stat_65_to_96_res_delta_warning")
	private Integer ttlStat65To96ResDeltaWarning;

   	@Column(name = "ttl_stat_65_to_96_res_delta_danger")
	private Integer ttlStat65To96ResDeltaDanger;

   	@Column(name = "ttl_stat_65_to_96_res_delta_obstacle")
	private Integer ttlStat65To96ResDeltaObstacle;

   	@Column(name = "ttl_stat_97_to_128_res_delta_use")
	private Boolean ttlStat97To128ResDeltaUse;

   	@Column(name = "ttl_stat_97_to_128_res_delta_caution")
	private Integer ttlStat97To128ResDeltaCaution;

   	@Column(name = "ttl_stat_97_to_128_res_delta_warning")
	private Integer ttlStat97To128ResDeltaWarning;

   	@Column(name = "ttl_stat_97_to_128_res_delta_danger")
	private Integer ttlStat97To128ResDeltaDanger;

   	@Column(name = "ttl_stat_97_to_128_res_delta_obstacle")
	private Integer ttlStat97To128ResDeltaObstacle;

   	@Column(name = "ttl_stat_129_to_160_res_delta_use")
	private Boolean ttlStat129To160ResDeltaUse;

   	@Column(name = "ttl_stat_129_to_160_res_delta_caution")
	private Integer ttlStat129To160ResDeltaCaution;

   	@Column(name = "ttl_stat_129_to_160_res_delta_warning")
	private Integer ttlStat129To160ResDeltaWarning;

   	@Column(name = "ttl_stat_129_to_160_res_delta_danger")
	private Integer ttlStat129To160ResDeltaDanger;

   	@Column(name = "ttl_stat_129_to_160_res_delta_obstacle")
	private Integer ttlStat129To160ResDeltaObstacle;

   	@Column(name = "ttl_stat_161_to_192_res_delta_use")
	private Boolean ttlStat161To192ResDeltaUse;

   	@Column(name = "ttl_stat_161_to_192_res_delta_caution")
	private Integer ttlStat161To192ResDeltaCaution;

   	@Column(name = "ttl_stat_161_to_192_res_delta_warning")
	private Integer ttlStat161To192ResDeltaWarning;

   	@Column(name = "ttl_stat_161_to_192_res_delta_danger")
	private Integer ttlStat161To192ResDeltaDanger;

   	@Column(name = "ttl_stat_161_to_192_res_delta_obstacle")
	private Integer ttlStat161To192ResDeltaObstacle;

   	@Column(name = "ttl_stat_193_to_224_res_delta_use")
	private Boolean ttlStat193To224ResDeltaUse;

   	@Column(name = "ttl_stat_193_to_224_res_delta_caution")
	private Integer ttlStat193To224ResDeltaCaution;

   	@Column(name = "ttl_stat_193_to_224_res_delta_warning")
	private Integer ttlStat193To224ResDeltaWarning;

   	@Column(name = "ttl_stat_193_to_224_res_delta_danger")
	private Integer ttlStat193To224ResDeltaDanger;

   	@Column(name = "ttl_stat_193_to_224_res_delta_obstacle")
	private Integer ttlStat193To224ResDeltaObstacle;

   	@Column(name = "ttl_stat_225_to_255_res_delta_use")
	private Boolean ttlStat225To255ResDeltaUse;

   	@Column(name = "ttl_stat_225_to_255_res_delta_caution")
	private Integer ttlStat225To255ResDeltaCaution;

   	@Column(name = "ttl_stat_225_to_255_res_delta_warning")
	private Integer ttlStat225To255ResDeltaWarning;

   	@Column(name = "ttl_stat_225_to_255_res_delta_danger")
	private Integer ttlStat225To255ResDeltaDanger;

   	@Column(name = "ttl_stat_225_to_255_res_delta_obstacle")
	private Integer ttlStat225To255ResDeltaObstacle;

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


}
