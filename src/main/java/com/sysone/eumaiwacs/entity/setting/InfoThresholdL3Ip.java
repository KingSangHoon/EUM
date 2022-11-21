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
@Table(name="tbl_info_threshold_l3_ip")
public class InfoThresholdL3Ip {

    @Id
    @SequenceGenerator(name="tbl_info_threshold_l3_ip_seq", sequenceName="tbl_info_threshold_l3_ip_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_threshold_l3_ip_seq")
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

    @Column(name="frag_pkts_tot_req_use")
    private Boolean fragPktsTotReqUse;
    @Column(name="frag_pkts_tot_req_level1")
    private Integer fragPktsTotReqLevel1;
    @Column(name="frag_pkts_tot_req_level2")
    private Integer fragPktsTotReqLevel2;
    @Column(name="frag_pkts_tot_req_level3")
    private Integer fragPktsTotReqLevel3;
    @Column(name="frag_pkts_tot_req_level4")
    private Integer fragPktsTotReqLevel4;
    @Column(name="frag_pkts_tot_req_level5")
    private Integer fragPktsTotReqLevel5;

    @Column(name="frag_pkts_tot_res_use")
    private Boolean fragPktsTotResUse;
    @Column(name="frag_pkts_tot_res_level1")
    private Integer fragPktsTotResLevel1;
    @Column(name="frag_pkts_tot_res_level2")
    private Integer fragPktsTotResLevel2;
    @Column(name="frag_pkts_tot_res_level3")
    private Integer fragPktsTotResLevel3;
    @Column(name="frag_pkts_tot_res_level4")
    private Integer fragPktsTotResLevel4;
    @Column(name="frag_pkts_tot_res_level5")
    private Integer fragPktsTotResLevel5;

    @Column(name="frag_pkts_per_sec_req_use")
    private Boolean fragPktsPerSecReqUse;
    @Column(name="frag_pkts_per_sec_req_level1")
    private Integer fragPktsPerSecReqLevel1;
    @Column(name="frag_pkts_per_sec_req_level2")
    private Integer fragPktsPerSecReqLevel2;
    @Column(name="frag_pkts_per_sec_req_level3")
    private Integer fragPktsPerSecReqLevel3;
    @Column(name="frag_pkts_per_sec_req_level4")
    private Integer fragPktsPerSecReqLevel4;
    @Column(name="frag_pkts_per_sec_req_level5")
    private Integer fragPktsPerSecReqLevel5;

    @Column(name="frag_pkts_per_sec_res_use")
    private Boolean fragPktsPerSecResUse;
    @Column(name="frag_pkts_per_sec_res_level1")
    private Integer fragPktsPerSecResLevel1;
    @Column(name="frag_pkts_per_sec_res_level2")
    private Integer fragPktsPerSecResLevel2;
    @Column(name="frag_pkts_per_sec_res_level3")
    private Integer fragPktsPerSecResLevel3;
    @Column(name="frag_pkts_per_sec_res_level4")
    private Integer fragPktsPerSecResLevel4;
    @Column(name="frag_pkts_per_sec_res_level5")
    private Integer fragPktsPerSecResLevel5;

    @Column(name="frag_pkts_delta_req_use")
    private Boolean fragPktsDeltaReqUse;
    @Column(name="frag_pkts_delta_req_level1")
    private Integer fragPktsDeltaReqLevel1;
    @Column(name="frag_pkts_delta_req_level2")
    private Integer fragPktsDeltaReqLevel2;
    @Column(name="frag_pkts_delta_req_level3")
    private Integer fragPktsDeltaReqLevel3;
    @Column(name="frag_pkts_delta_req_level4")
    private Integer fragPktsDeltaReqLevel4;
    @Column(name="frag_pkts_delta_req_level5")
    private Integer fragPktsDeltaReqLevel5;

    @Column(name="frag_pkts_delta_res_use")
    private Boolean fragPktsDeltaResUse;
    @Column(name="frag_pkts_delta_res_level1")
    private Integer fragPktsDeltaResLevel1;
    @Column(name="frag_pkts_delta_res_level2")
    private Integer fragPktsDeltaResLevel2;
    @Column(name="frag_pkts_delta_res_level3")
    private Integer fragPktsDeltaResLevel3;
    @Column(name="frag_pkts_delta_res_level4")
    private Integer fragPktsDeltaResLevel4;
    @Column(name="frag_pkts_delta_res_level5")
    private Integer fragPktsDeltaResLevel5;

    @Column(name="ttl_min_req_use")
    private Boolean ttlMinReqUse;
    @Column(name="ttl_min_req_level1")
    private Integer ttlMinReqLevel1;
    @Column(name="ttl_min_req_level2")
    private Integer ttlMinReqLevel2;
    @Column(name="ttl_min_req_level3")
    private Integer ttlMinReqLevel3;
    @Column(name="ttl_min_req_level4")
    private Integer ttlMinReqLevel4;
    @Column(name="ttl_min_req_level5")
    private Integer ttlMinReqLevel5;

    @Column(name="ttl_min_res_use")
    private Boolean ttlMinResUse;
    @Column(name="ttl_min_res_level1")
    private Integer ttlMinResLevel1;
    @Column(name="ttl_min_res_level2")
    private Integer ttlMinResLevel2;
    @Column(name="ttl_min_res_level3")
    private Integer ttlMinResLevel3;
    @Column(name="ttl_min_res_level4")
    private Integer ttlMinResLevel4;
    @Column(name="ttl_min_res_level5")
    private Integer ttlMinResLevel5;

    @Column(name="ttl_max_req_use")
    private Boolean ttlMaxReqUse;
    @Column(name="ttl_max_req_level1")
    private Integer ttlMaxReqLevel1;
    @Column(name="ttl_max_req_level2")
    private Integer ttlMaxReqLevel2;
    @Column(name="ttl_max_req_level3")
    private Integer ttlMaxReqLevel3;
    @Column(name="ttl_max_req_level4")
    private Integer ttlMaxReqLevel4;
    @Column(name="ttl_max_req_level5")
    private Integer ttlMaxReqLevel5;

    @Column(name="ttl_max_res_use")
    private Boolean ttlMaxResUse;
    @Column(name="ttl_max_res_level1")
    private Integer ttlMaxResLevel1;
    @Column(name="ttl_max_res_level2")
    private Integer ttlMaxResLevel2;
    @Column(name="ttl_max_res_level3")
    private Integer ttlMaxResLevel3;
    @Column(name="ttl_max_res_level4")
    private Integer ttlMaxResLevel4;
    @Column(name="ttl_max_res_level5")
    private Integer ttlMaxResLevel5;

    @Column(name="ttl_stat_1_req_tot_use")
    private Boolean ttlStat1ReqTotUse;
    @Column(name="ttl_stat_1_req_tot_level1")
    private Integer ttlStat1ReqTotLevel1;
    @Column(name="ttl_stat_1_req_tot_level2")
    private Integer ttlStat1ReqTotLevel2;
    @Column(name="ttl_stat_1_req_tot_level3")
    private Integer ttlStat1ReqTotLevel3;
    @Column(name="ttl_stat_1_req_tot_level4")
    private Integer ttlStat1ReqTotLevel4;
    @Column(name="ttl_stat_1_req_tot_level5")
    private Integer ttlStat1ReqTotLevel5;

    @Column(name="ttl_stat_2_to_5_req_tot_use")
    private Boolean ttlStat2To5ReqTotUse;
    @Column(name="ttl_stat_2_to_5_req_tot_level1")
    private Integer ttlStat2To5ReqTotLevel1;
    @Column(name="ttl_stat_2_to_5_req_tot_level2")
    private Integer ttlStat2To5ReqTotLevel2;
    @Column(name="ttl_stat_2_to_5_req_tot_level3")
    private Integer ttlStat2To5ReqTotLevel3;
    @Column(name="ttl_stat_2_to_5_req_tot_level4")
    private Integer ttlStat2To5ReqTotLevel4;
    @Column(name="ttl_stat_2_to_5_req_tot_level5")
    private Integer ttlStat2To5ReqTotLevel5;

    @Column(name="ttl_stat_6_to_32_req_tot_use")
    private Boolean ttlStat6To32ReqTotUse;
    @Column(name="ttl_stat_6_to_32_req_tot_level1")
    private Integer ttlStat6To32ReqTotLevel1;
    @Column(name="ttl_stat_6_to_32_req_tot_level2")
    private Integer ttlStat6To32ReqTotLevel2;
    @Column(name="ttl_stat_6_to_32_req_tot_level3")
    private Integer ttlStat6To32ReqTotLevel3;
    @Column(name="ttl_stat_6_to_32_req_tot_level4")
    private Integer ttlStat6To32ReqTotLevel4;
    @Column(name="ttl_stat_6_to_32_req_tot_level5")
    private Integer ttlStat6To32ReqTotLevel5;

    @Column(name="ttl_stat_33_to_64_req_tot_use")
    private Boolean ttlStat33To64ReqTotUse;
    @Column(name="ttl_stat_33_to_64_req_tot_level1")
    private Integer ttlStat33To64ReqTotLevel1;
    @Column(name="ttl_stat_33_to_64_req_tot_level2")
    private Integer ttlStat33To64ReqTotLevel2;
    @Column(name="ttl_stat_33_to_64_req_tot_level3")
    private Integer ttlStat33To64ReqTotLevel3;
    @Column(name="ttl_stat_33_to_64_req_tot_level4")
    private Integer ttlStat33To64ReqTotLevel4;
    @Column(name="ttl_stat_33_to_64_req_tot_level5")
    private Integer ttlStat33To64ReqTotLevel5;

    @Column(name="ttl_stat_65_to_96_req_tot_use")
    private Boolean ttlStat65To96ReqTotUse;
    @Column(name="ttl_stat_65_to_96_req_tot_level1")
    private Integer ttlStat65To96ReqTotLevel1;
    @Column(name="ttl_stat_65_to_96_req_tot_level2")
    private Integer ttlStat65To96ReqTotLevel2;
    @Column(name="ttl_stat_65_to_96_req_tot_level3")
    private Integer ttlStat65To96ReqTotLevel3;
    @Column(name="ttl_stat_65_to_96_req_tot_level4")
    private Integer ttlStat65To96ReqTotLevel4;
    @Column(name="ttl_stat_65_to_96_req_tot_level5")
    private Integer ttlStat65To96ReqTotLevel5;

    @Column(name="ttl_stat_97_to_128_req_tot_use")
    private Boolean ttlStat97To128ReqTotUse;
    @Column(name="ttl_stat_97_to_128_req_tot_level1")
    private Integer ttlStat97To128ReqTotLevel1;
    @Column(name="ttl_stat_97_to_128_req_tot_level2")
    private Integer ttlStat97To128ReqTotLevel2;
    @Column(name="ttl_stat_97_to_128_req_tot_level3")
    private Integer ttlStat97To128ReqTotLevel3;
    @Column(name="ttl_stat_97_to_128_req_tot_level4")
    private Integer ttlStat97To128ReqTotLevel4;
    @Column(name="ttl_stat_97_to_128_req_tot_level5")
    private Integer ttlStat97To128ReqTotLevel5;

    @Column(name="ttl_stat_129_to_160_req_tot_use")
    private Boolean ttlStat129To160ReqTotUse;
    @Column(name="ttl_stat_129_to_160_req_tot_level1")
    private Integer ttlStat129To160ReqTotLevel1;
    @Column(name="ttl_stat_129_to_160_req_tot_level2")
    private Integer ttlStat129To160ReqTotLevel2;
    @Column(name="ttl_stat_129_to_160_req_tot_level3")
    private Integer ttlStat129To160ReqTotLevel3;
    @Column(name="ttl_stat_129_to_160_req_tot_level4")
    private Integer ttlStat129To160ReqTotLevel4;
    @Column(name="ttl_stat_129_to_160_req_tot_level5")
    private Integer ttlStat129To160ReqTotLevel5;

    @Column(name="ttl_stat_161_to_192_req_tot_use")
    private Boolean ttlStat161To192ReqTotUse;
    @Column(name="ttl_stat_161_to_192_req_tot_level1")
    private Integer ttlStat161To192ReqTotLevel1;
    @Column(name="ttl_stat_161_to_192_req_tot_level2")
    private Integer ttlStat161To192ReqTotLevel2;
    @Column(name="ttl_stat_161_to_192_req_tot_level3")
    private Integer ttlStat161To192ReqTotLevel3;
    @Column(name="ttl_stat_161_to_192_req_tot_level4")
    private Integer ttlStat161To192ReqTotLevel4;
    @Column(name="ttl_stat_161_to_192_req_tot_level5")
    private Integer ttlStat161To192ReqTotLevel5;

    @Column(name="ttl_stat_193_to_224_req_tot_use")
    private Boolean ttlStat193To224ReqTotUse;
    @Column(name="ttl_stat_193_to_224_req_tot_level1")
    private Integer ttlStat193To224ReqTotLevel1;
    @Column(name="ttl_stat_193_to_224_req_tot_level2")
    private Integer ttlStat193To224ReqTotLevel2;
    @Column(name="ttl_stat_193_to_224_req_tot_level3")
    private Integer ttlStat193To224ReqTotLevel3;
    @Column(name="ttl_stat_193_to_224_req_tot_level4")
    private Integer ttlStat193To224ReqTotLevel4;
    @Column(name="ttl_stat_193_to_224_req_tot_level5")
    private Integer ttlStat193To224ReqTotLevel5;

    @Column(name="ttl_stat_225_to_255_req_tot_use")
    private Boolean ttlStat225To255ReqTotUse;
    @Column(name="ttl_stat_225_to_255_req_tot_level1")
    private Integer ttlStat225To255ReqTotLevel1;
    @Column(name="ttl_stat_225_to_255_req_tot_level2")
    private Integer ttlStat225To255ReqTotLevel2;
    @Column(name="ttl_stat_225_to_255_req_tot_level3")
    private Integer ttlStat225To255ReqTotLevel3;
    @Column(name="ttl_stat_225_to_255_req_tot_level4")
    private Integer ttlStat225To255ReqTotLevel4;
    @Column(name="ttl_stat_225_to_255_req_tot_level5")
    private Integer ttlStat225To255ReqTotLevel5;

    @Column(name="ttl_stat_1_res_tot_use")
    private Boolean ttlStat1ResTotUse;
    @Column(name="ttl_stat_1_res_tot_level1")
    private Integer ttlStat1ResTotLevel1;
    @Column(name="ttl_stat_1_res_tot_level2")
    private Integer ttlStat1ResTotLevel2;
    @Column(name="ttl_stat_1_res_tot_level3")
    private Integer ttlStat1ResTotLevel3;
    @Column(name="ttl_stat_1_res_tot_level4")
    private Integer ttlStat1ResTotLevel4;
    @Column(name="ttl_stat_1_res_tot_level5")
    private Integer ttlStat1ResTotLevel5;

    @Column(name="ttl_stat_2_to_5_res_tot_use")
    private Boolean ttlStat2To5ResTotUse;
    @Column(name="ttl_stat_2_to_5_res_tot_level1")
    private Integer ttlStat2To5ResTotLevel1;
    @Column(name="ttl_stat_2_to_5_res_tot_level2")
    private Integer ttlStat2To5ResTotLevel2;
    @Column(name="ttl_stat_2_to_5_res_tot_level3")
    private Integer ttlStat2To5ResTotLevel3;
    @Column(name="ttl_stat_2_to_5_res_tot_level4")
    private Integer ttlStat2To5ResTotLevel4;
    @Column(name="ttl_stat_2_to_5_res_tot_level5")
    private Integer ttlStat2To5ResTotLevel5;

    @Column(name="ttl_stat_6_to_32_res_tot_use")
    private Boolean ttlStat6To32ResTotUse;
    @Column(name="ttl_stat_6_to_32_res_tot_level1")
    private Integer ttlStat6To32ResTotLevel1;
    @Column(name="ttl_stat_6_to_32_res_tot_level2")
    private Integer ttlStat6To32ResTotLevel2;
    @Column(name="ttl_stat_6_to_32_res_tot_level3")
    private Integer ttlStat6To32ResTotLevel3;
    @Column(name="ttl_stat_6_to_32_res_tot_level4")
    private Integer ttlStat6To32ResTotLevel4;
    @Column(name="ttl_stat_6_to_32_res_tot_level5")
    private Integer ttlStat6To32ResTotLevel5;

    @Column(name="ttl_stat_33_to_64_res_tot_use")
    private Boolean ttlStat33To64ResTotUse;
    @Column(name="ttl_stat_33_to_64_res_tot_level1")
    private Integer ttlStat33To64ResTotLevel1;
    @Column(name="ttl_stat_33_to_64_res_tot_level2")
    private Integer ttlStat33To64ResTotLevel2;
    @Column(name="ttl_stat_33_to_64_res_tot_level3")
    private Integer ttlStat33To64ResTotLevel3;
    @Column(name="ttl_stat_33_to_64_res_tot_level4")
    private Integer ttlStat33To64ResTotLevel4;
    @Column(name="ttl_stat_33_to_64_res_tot_level5")
    private Integer ttlStat33To64ResTotLevel5;

    @Column(name="ttl_stat_65_to_96_res_tot_use")
    private Boolean ttlStat65To96ResTotUse;
    @Column(name="ttl_stat_65_to_96_res_tot_level1")
    private Integer ttlStat65To96ResTotLevel1;
    @Column(name="ttl_stat_65_to_96_res_tot_level2")
    private Integer ttlStat65To96ResTotLevel2;
    @Column(name="ttl_stat_65_to_96_res_tot_level3")
    private Integer ttlStat65To96ResTotLevel3;
    @Column(name="ttl_stat_65_to_96_res_tot_level4")
    private Integer ttlStat65To96ResTotLevel4;
    @Column(name="ttl_stat_65_to_96_res_tot_level5")
    private Integer ttlStat65To96ResTotLevel5;

    @Column(name="ttl_stat_97_to_128_res_tot_use")
    private Boolean ttlStat97To128ResTotUse;
    @Column(name="ttl_stat_97_to_128_res_tot_level1")
    private Integer ttlStat97To128ResTotLevel1;
    @Column(name="ttl_stat_97_to_128_res_tot_level2")
    private Integer ttlStat97To128ResTotLevel2;
    @Column(name="ttl_stat_97_to_128_res_tot_level3")
    private Integer ttlStat97To128ResTotLevel3;
    @Column(name="ttl_stat_97_to_128_res_tot_level4")
    private Integer ttlStat97To128ResTotLevel4;
    @Column(name="ttl_stat_97_to_128_res_tot_level5")
    private Integer ttlStat97To128ResTotLevel5;

    @Column(name="ttl_stat_129_to_160_res_tot_use")
    private Boolean ttlStat129To160ResTotUse;
    @Column(name="ttl_stat_129_to_160_res_tot_level1")
    private Integer ttlStat129To160ResTotLevel1;
    @Column(name="ttl_stat_129_to_160_res_tot_level2")
    private Integer ttlStat129To160ResTotLevel2;
    @Column(name="ttl_stat_129_to_160_res_tot_level3")
    private Integer ttlStat129To160ResTotLevel3;
    @Column(name="ttl_stat_129_to_160_res_tot_level4")
    private Integer ttlStat129To160ResTotLevel4;
    @Column(name="ttl_stat_129_to_160_res_tot_level5")
    private Integer ttlStat129To160ResTotLevel5;

    @Column(name="ttl_stat_161_to_192_res_tot_use")
    private Boolean ttlStat161To192ResTotUse;
    @Column(name="ttl_stat_161_to_192_res_tot_level1")
    private Integer ttlStat161To192ResTotLevel1;
    @Column(name="ttl_stat_161_to_192_res_tot_level2")
    private Integer ttlStat161To192ResTotLevel2;
    @Column(name="ttl_stat_161_to_192_res_tot_level3")
    private Integer ttlStat161To192ResTotLevel3;
    @Column(name="ttl_stat_161_to_192_res_tot_level4")
    private Integer ttlStat161To192ResTotLevel4;
    @Column(name="ttl_stat_161_to_192_res_tot_level5")
    private Integer ttlStat161To192ResTotLevel5;

    @Column(name="ttl_stat_193_to_224_res_tot_use")
    private Boolean ttlStat193To224ResTotUse;
    @Column(name="ttl_stat_193_to_224_res_tot_level1")
    private Integer ttlStat193To224ResTotLevel1;
    @Column(name="ttl_stat_193_to_224_res_tot_level2")
    private Integer ttlStat193To224ResTotLevel2;
    @Column(name="ttl_stat_193_to_224_res_tot_level3")
    private Integer ttlStat193To224ResTotLevel3;
    @Column(name="ttl_stat_193_to_224_res_tot_level4")
    private Integer ttlStat193To224ResTotLevel4;
    @Column(name="ttl_stat_193_to_224_res_tot_level5")
    private Integer ttlStat193To224ResTotLevel5;

    @Column(name="ttl_stat_225_to_255_res_tot_use")
    private Boolean ttlStat225To255ResTotUse;
    @Column(name="ttl_stat_225_to_255_res_tot_level1")
    private Integer ttlStat225To255ResTotLevel1;
    @Column(name="ttl_stat_225_to_255_res_tot_level2")
    private Integer ttlStat225To255ResTotLevel2;
    @Column(name="ttl_stat_225_to_255_res_tot_level3")
    private Integer ttlStat225To255ResTotLevel3;
    @Column(name="ttl_stat_225_to_255_res_tot_level4")
    private Integer ttlStat225To255ResTotLevel4;
    @Column(name="ttl_stat_225_to_255_res_tot_level5")
    private Integer ttlStat225To255ResTotLevel5;

    @Column(name="ttl_stat_1_req_per_sec_use")
    private Boolean ttlStat1ReqPerSecUse;
    @Column(name="ttl_stat_1_req_per_sec_level1")
    private Integer ttlStat1ReqPerSecLevel1;
    @Column(name="ttl_stat_1_req_per_sec_level2")
    private Integer ttlStat1ReqPerSecLevel2;
    @Column(name="ttl_stat_1_req_per_sec_level3")
    private Integer ttlStat1ReqPerSecLevel3;
    @Column(name="ttl_stat_1_req_per_sec_level4")
    private Integer ttlStat1ReqPerSecLevel4;
    @Column(name="ttl_stat_1_req_per_sec_level5")
    private Integer ttlStat1ReqPerSecLevel5;

    @Column(name="ttl_stat_2_to_5_req_per_sec_use")
    private Boolean ttlStat2To5ReqPerSecUse;
    @Column(name="ttl_stat_2_to_5_req_per_sec_level1")
    private Integer ttlStat2To5ReqPerSecLevel1;
    @Column(name="ttl_stat_2_to_5_req_per_sec_level2")
    private Integer ttlStat2To5ReqPerSecLevel2;
    @Column(name="ttl_stat_2_to_5_req_per_sec_level3")
    private Integer ttlStat2To5ReqPerSecLevel3;
    @Column(name="ttl_stat_2_to_5_req_per_sec_level4")
    private Integer ttlStat2To5ReqPerSecLevel4;
    @Column(name="ttl_stat_2_to_5_req_per_sec_level5")
    private Integer ttlStat2To5ReqPerSecLevel5;

    @Column(name="ttl_stat_6_to_32_req_per_sec_use")
    private Boolean ttlStat6To32ReqPerSecUse;
    @Column(name="ttl_stat_6_to_32_req_per_sec_level1")
    private Integer ttlStat6To32ReqPerSecLevel1;
    @Column(name="ttl_stat_6_to_32_req_per_sec_level2")
    private Integer ttlStat6To32ReqPerSecLevel2;
    @Column(name="ttl_stat_6_to_32_req_per_sec_level3")
    private Integer ttlStat6To32ReqPerSecLevel3;
    @Column(name="ttl_stat_6_to_32_req_per_sec_level4")
    private Integer ttlStat6To32ReqPerSecLevel4;
    @Column(name="ttl_stat_6_to_32_req_per_sec_level5")
    private Integer ttlStat6To32ReqPerSecLevel5;

    @Column(name="ttl_stat_33_to_64_req_per_sec_use")
    private Boolean ttlStat33To64ReqPerSecUse;
    @Column(name="ttl_stat_33_to_64_req_per_sec_level1")
    private Integer ttlStat33To64ReqPerSecLevel1;
    @Column(name="ttl_stat_33_to_64_req_per_sec_level2")
    private Integer ttlStat33To64ReqPerSecLevel2;
    @Column(name="ttl_stat_33_to_64_req_per_sec_level3")
    private Integer ttlStat33To64ReqPerSecLevel3;
    @Column(name="ttl_stat_33_to_64_req_per_sec_level4")
    private Integer ttlStat33To64ReqPerSecLevel4;
    @Column(name="ttl_stat_33_to_64_req_per_sec_level5")
    private Integer ttlStat33To64ReqPerSecLevel5;

    @Column(name="ttl_stat_65_to_96_req_per_sec_use")
    private Boolean ttlStat65To96ReqPerSecUse;
    @Column(name="ttl_stat_65_to_96_req_per_sec_level1")
    private Integer ttlStat65To96ReqPerSecLevel1;
    @Column(name="ttl_stat_65_to_96_req_per_sec_level2")
    private Integer ttlStat65To96ReqPerSecLevel2;
    @Column(name="ttl_stat_65_to_96_req_per_sec_level3")
    private Integer ttlStat65To96ReqPerSecLevel3;
    @Column(name="ttl_stat_65_to_96_req_per_sec_level4")
    private Integer ttlStat65To96ReqPerSecLevel4;
    @Column(name="ttl_stat_65_to_96_req_per_sec_level5")
    private Integer ttlStat65To96ReqPerSecLevel5;

    @Column(name="ttl_stat_97_to_128_req_per_sec_use")
    private Boolean ttlStat97To128ReqPerSecUse;
    @Column(name="ttl_stat_97_to_128_req_per_sec_level1")
    private Integer ttlStat97To128ReqPerSecLevel1;
    @Column(name="ttl_stat_97_to_128_req_per_sec_level2")
    private Integer ttlStat97To128ReqPerSecLevel2;
    @Column(name="ttl_stat_97_to_128_req_per_sec_level3")
    private Integer ttlStat97To128ReqPerSecLevel3;
    @Column(name="ttl_stat_97_to_128_req_per_sec_level4")
    private Integer ttlStat97To128ReqPerSecLevel4;
    @Column(name="ttl_stat_97_to_128_req_per_sec_level5")
    private Integer ttlStat97To128ReqPerSecLevel5;

    @Column(name="ttl_stat_129_to_160_req_per_sec_use")
    private Boolean ttlStat129To160ReqPerSecUse;
    @Column(name="ttl_stat_129_to_160_req_per_sec_level1")
    private Integer ttlStat129To160ReqPerSecLevel1;
    @Column(name="ttl_stat_129_to_160_req_per_sec_level2")
    private Integer ttlStat129To160ReqPerSecLevel2;
    @Column(name="ttl_stat_129_to_160_req_per_sec_level3")
    private Integer ttlStat129To160ReqPerSecLevel3;
    @Column(name="ttl_stat_129_to_160_req_per_sec_level4")
    private Integer ttlStat129To160ReqPerSecLevel4;
    @Column(name="ttl_stat_129_to_160_req_per_sec_level5")
    private Integer ttlStat129To160ReqPerSecLevel5;

    @Column(name="ttl_stat_161_to_192_req_per_sec_use")
    private Boolean ttlStat161To192ReqPerSecUse;
    @Column(name="ttl_stat_161_to_192_req_per_sec_level1")
    private Integer ttlStat161To192ReqPerSecLevel1;
    @Column(name="ttl_stat_161_to_192_req_per_sec_level2")
    private Integer ttlStat161To192ReqPerSecLevel2;
    @Column(name="ttl_stat_161_to_192_req_per_sec_level3")
    private Integer ttlStat161To192ReqPerSecLevel3;
    @Column(name="ttl_stat_161_to_192_req_per_sec_level4")
    private Integer ttlStat161To192ReqPerSecLevel4;
    @Column(name="ttl_stat_161_to_192_req_per_sec_level5")
    private Integer ttlStat161To192ReqPerSecLevel5;

    @Column(name="ttl_stat_193_to_224_req_per_sec_use")
    private Boolean ttlStat193To224ReqPerSecUse;
    @Column(name="ttl_stat_193_to_224_req_per_sec_level1")
    private Integer ttlStat193To224ReqPerSecLevel1;
    @Column(name="ttl_stat_193_to_224_req_per_sec_level2")
    private Integer ttlStat193To224ReqPerSecLevel2;
    @Column(name="ttl_stat_193_to_224_req_per_sec_level3")
    private Integer ttlStat193To224ReqPerSecLevel3;
    @Column(name="ttl_stat_193_to_224_req_per_sec_level4")
    private Integer ttlStat193To224ReqPerSecLevel4;
    @Column(name="ttl_stat_193_to_224_req_per_sec_level5")
    private Integer ttlStat193To224ReqPerSecLevel5;

    @Column(name="ttl_stat_225_to_255_req_per_sec_use")
    private Boolean ttlStat225To255ReqPerSecUse;
    @Column(name="ttl_stat_225_to_255_req_per_sec_level1")
    private Integer ttlStat225To255ReqPerSecLevel1;
    @Column(name="ttl_stat_225_to_255_req_per_sec_level2")
    private Integer ttlStat225To255ReqPerSecLevel2;
    @Column(name="ttl_stat_225_to_255_req_per_sec_level3")
    private Integer ttlStat225To255ReqPerSecLevel3;
    @Column(name="ttl_stat_225_to_255_req_per_sec_level4")
    private Integer ttlStat225To255ReqPerSecLevel4;
    @Column(name="ttl_stat_225_to_255_req_per_sec_level5")
    private Integer ttlStat225To255ReqPerSecLevel5;

    @Column(name="ttl_stat_1_res_per_sec_use")
    private Boolean ttlStat1ResPerSecUse;
    @Column(name="ttl_stat_1_res_per_sec_level1")
    private Integer ttlStat1ResPerSecLevel1;
    @Column(name="ttl_stat_1_res_per_sec_level2")
    private Integer ttlStat1ResPerSecLevel2;
    @Column(name="ttl_stat_1_res_per_sec_level3")
    private Integer ttlStat1ResPerSecLevel3;
    @Column(name="ttl_stat_1_res_per_sec_level4")
    private Integer ttlStat1ResPerSecLevel4;
    @Column(name="ttl_stat_1_res_per_sec_level5")
    private Integer ttlStat1ResPerSecLevel5;

    @Column(name="ttl_stat_2_to_5_res_per_sec_use")
    private Boolean ttlStat2To5ResPerSecUse;
    @Column(name="ttl_stat_2_to_5_res_per_sec_level1")
    private Integer ttlStat2To5ResPerSecLevel1;
    @Column(name="ttl_stat_2_to_5_res_per_sec_level2")
    private Integer ttlStat2To5ResPerSecLevel2;
    @Column(name="ttl_stat_2_to_5_res_per_sec_level3")
    private Integer ttlStat2To5ResPerSecLevel3;
    @Column(name="ttl_stat_2_to_5_res_per_sec_level4")
    private Integer ttlStat2To5ResPerSecLevel4;
    @Column(name="ttl_stat_2_to_5_res_per_sec_level5")
    private Integer ttlStat2To5ResPerSecLevel5;

    @Column(name="ttl_stat_6_to_32_res_per_sec_use")
    private Boolean ttlStat6To32ResPerSecUse;
    @Column(name="ttl_stat_6_to_32_res_per_sec_level1")
    private Integer ttlStat6To32ResPerSecLevel1;
    @Column(name="ttl_stat_6_to_32_res_per_sec_level2")
    private Integer ttlStat6To32ResPerSecLevel2;
    @Column(name="ttl_stat_6_to_32_res_per_sec_level3")
    private Integer ttlStat6To32ResPerSecLevel3;
    @Column(name="ttl_stat_6_to_32_res_per_sec_level4")
    private Integer ttlStat6To32ResPerSecLevel4;
    @Column(name="ttl_stat_6_to_32_res_per_sec_level5")
    private Integer ttlStat6To32ResPerSecLevel5;

    @Column(name="ttl_stat_33_to_64_res_per_sec_use")
    private Boolean ttlStat33To64ResPerSecUse;
    @Column(name="ttl_stat_33_to_64_res_per_sec_level1")
    private Integer ttlStat33To64ResPerSecLevel1;
    @Column(name="ttl_stat_33_to_64_res_per_sec_level2")
    private Integer ttlStat33To64ResPerSecLevel2;
    @Column(name="ttl_stat_33_to_64_res_per_sec_level3")
    private Integer ttlStat33To64ResPerSecLevel3;
    @Column(name="ttl_stat_33_to_64_res_per_sec_level4")
    private Integer ttlStat33To64ResPerSecLevel4;
    @Column(name="ttl_stat_33_to_64_res_per_sec_level5")
    private Integer ttlStat33To64ResPerSecLevel5;

    @Column(name="ttl_stat_65_to_96_res_per_sec_use")
    private Boolean ttlStat65To96ResPerSecUse;
    @Column(name="ttl_stat_65_to_96_res_per_sec_level1")
    private Integer ttlStat65To96ResPerSecLevel1;
    @Column(name="ttl_stat_65_to_96_res_per_sec_level2")
    private Integer ttlStat65To96ResPerSecLevel2;
    @Column(name="ttl_stat_65_to_96_res_per_sec_level3")
    private Integer ttlStat65To96ResPerSecLevel3;
    @Column(name="ttl_stat_65_to_96_res_per_sec_level4")
    private Integer ttlStat65To96ResPerSecLevel4;
    @Column(name="ttl_stat_65_to_96_res_per_sec_level5")
    private Integer ttlStat65To96ResPerSecLevel5;

    @Column(name="ttl_stat_97_to_128_res_per_sec_use")
    private Boolean ttlStat97To128ResPerSecUse;
    @Column(name="ttl_stat_97_to_128_res_per_sec_level1")
    private Integer ttlStat97To128ResPerSecLevel1;
    @Column(name="ttl_stat_97_to_128_res_per_sec_level2")
    private Integer ttlStat97To128ResPerSecLevel2;
    @Column(name="ttl_stat_97_to_128_res_per_sec_level3")
    private Integer ttlStat97To128ResPerSecLevel3;
    @Column(name="ttl_stat_97_to_128_res_per_sec_level4")
    private Integer ttlStat97To128ResPerSecLevel4;
    @Column(name="ttl_stat_97_to_128_res_per_sec_level5")
    private Integer ttlStat97To128ResPerSecLevel5;

    @Column(name="ttl_stat_129_to_160_res_per_sec_use")
    private Boolean ttlStat129To160ResPerSecUse;
    @Column(name="ttl_stat_129_to_160_res_per_sec_level1")
    private Integer ttlStat129To160ResPerSecLevel1;
    @Column(name="ttl_stat_129_to_160_res_per_sec_level2")
    private Integer ttlStat129To160ResPerSecLevel2;
    @Column(name="ttl_stat_129_to_160_res_per_sec_level3")
    private Integer ttlStat129To160ResPerSecLevel3;
    @Column(name="ttl_stat_129_to_160_res_per_sec_level4")
    private Integer ttlStat129To160ResPerSecLevel4;
    @Column(name="ttl_stat_129_to_160_res_per_sec_level5")
    private Integer ttlStat129To160ResPerSecLevel5;

    @Column(name="ttl_stat_161_to_192_res_per_sec_use")
    private Boolean ttlStat161To192ResPerSecUse;
    @Column(name="ttl_stat_161_to_192_res_per_sec_level1")
    private Integer ttlStat161To192ResPerSecLevel1;
    @Column(name="ttl_stat_161_to_192_res_per_sec_level2")
    private Integer ttlStat161To192ResPerSecLevel2;
    @Column(name="ttl_stat_161_to_192_res_per_sec_level3")
    private Integer ttlStat161To192ResPerSecLevel3;
    @Column(name="ttl_stat_161_to_192_res_per_sec_level4")
    private Integer ttlStat161To192ResPerSecLevel4;
    @Column(name="ttl_stat_161_to_192_res_per_sec_level5")
    private Integer ttlStat161To192ResPerSecLevel5;

    @Column(name="ttl_stat_193_to_224_res_per_sec_use")
    private Boolean ttlStat193To224ResPerSecUse;
    @Column(name="ttl_stat_193_to_224_res_per_sec_level1")
    private Integer ttlStat193To224ResPerSecLevel1;
    @Column(name="ttl_stat_193_to_224_res_per_sec_level2")
    private Integer ttlStat193To224ResPerSecLevel2;
    @Column(name="ttl_stat_193_to_224_res_per_sec_level3")
    private Integer ttlStat193To224ResPerSecLevel3;
    @Column(name="ttl_stat_193_to_224_res_per_sec_level4")
    private Integer ttlStat193To224ResPerSecLevel4;
    @Column(name="ttl_stat_193_to_224_res_per_sec_level5")
    private Integer ttlStat193To224ResPerSecLevel5;

    @Column(name="ttl_stat_225_to_255_res_per_sec_use")
    private Boolean ttlStat225To255ResPerSecUse;
    @Column(name="ttl_stat_225_to_255_res_per_sec_level1")
    private Integer ttlStat225To255ResPerSecLevel1;
    @Column(name="ttl_stat_225_to_255_res_per_sec_level2")
    private Integer ttlStat225To255ResPerSecLevel2;
    @Column(name="ttl_stat_225_to_255_res_per_sec_level3")
    private Integer ttlStat225To255ResPerSecLevel3;
    @Column(name="ttl_stat_225_to_255_res_per_sec_level4")
    private Integer ttlStat225To255ResPerSecLevel4;
    @Column(name="ttl_stat_225_to_255_res_per_sec_level5")
    private Integer ttlStat225To255ResPerSecLevel5;

    @Column(name="ttl_stat_1_req_delta_use")
    private Boolean ttlStat1ReqDeltaUse;
    @Column(name="ttl_stat_1_req_delta_level1")
    private Integer ttlStat1ReqDeltaLevel1;
    @Column(name="ttl_stat_1_req_delta_level2")
    private Integer ttlStat1ReqDeltaLevel2;
    @Column(name="ttl_stat_1_req_delta_level3")
    private Integer ttlStat1ReqDeltaLevel3;
    @Column(name="ttl_stat_1_req_delta_level4")
    private Integer ttlStat1ReqDeltaLevel4;
    @Column(name="ttl_stat_1_req_delta_level5")
    private Integer ttlStat1ReqDeltaLevel5;

    @Column(name="ttl_stat_2_to_5_req_delta_use")
    private Boolean ttlStat2To5ReqDeltaUse;
    @Column(name="ttl_stat_2_to_5_req_delta_level1")
    private Integer ttlStat2To5ReqDeltaLevel1;
    @Column(name="ttl_stat_2_to_5_req_delta_level2")
    private Integer ttlStat2To5ReqDeltaLevel2;
    @Column(name="ttl_stat_2_to_5_req_delta_level3")
    private Integer ttlStat2To5ReqDeltaLevel3;
    @Column(name="ttl_stat_2_to_5_req_delta_level4")
    private Integer ttlStat2To5ReqDeltaLevel4;
    @Column(name="ttl_stat_2_to_5_req_delta_level5")
    private Integer ttlStat2To5ReqDeltaLevel5;

    @Column(name="ttl_stat_6_to_32_req_delta_use")
    private Boolean ttlStat6To32ReqDeltaUse;
    @Column(name="ttl_stat_6_to_32_req_delta_level1")
    private Integer ttlStat6To32ReqDeltaLevel1;
    @Column(name="ttl_stat_6_to_32_req_delta_level2")
    private Integer ttlStat6To32ReqDeltaLevel2;
    @Column(name="ttl_stat_6_to_32_req_delta_level3")
    private Integer ttlStat6To32ReqDeltaLevel3;
    @Column(name="ttl_stat_6_to_32_req_delta_level4")
    private Integer ttlStat6To32ReqDeltaLevel4;
    @Column(name="ttl_stat_6_to_32_req_delta_level5")
    private Integer ttlStat6To32ReqDeltaLevel5;

    @Column(name="ttl_stat_33_to_64_req_delta_use")
    private Boolean ttlStat33To64ReqDeltaUse;
    @Column(name="ttl_stat_33_to_64_req_delta_level1")
    private Integer ttlStat33To64ReqDeltaLevel1;
    @Column(name="ttl_stat_33_to_64_req_delta_level2")
    private Integer ttlStat33To64ReqDeltaLevel2;
    @Column(name="ttl_stat_33_to_64_req_delta_level3")
    private Integer ttlStat33To64ReqDeltaLevel3;
    @Column(name="ttl_stat_33_to_64_req_delta_level4")
    private Integer ttlStat33To64ReqDeltaLevel4;
    @Column(name="ttl_stat_33_to_64_req_delta_level5")
    private Integer ttlStat33To64ReqDeltaLevel5;

    @Column(name="ttl_stat_65_to_96_req_delta_use")
    private Boolean ttlStat65To96ReqDeltaUse;
    @Column(name="ttl_stat_65_to_96_req_delta_level1")
    private Integer ttlStat65To96ReqDeltaLevel1;
    @Column(name="ttl_stat_65_to_96_req_delta_level2")
    private Integer ttlStat65To96ReqDeltaLevel2;
    @Column(name="ttl_stat_65_to_96_req_delta_level3")
    private Integer ttlStat65To96ReqDeltaLevel3;
    @Column(name="ttl_stat_65_to_96_req_delta_level4")
    private Integer ttlStat65To96ReqDeltaLevel4;
    @Column(name="ttl_stat_65_to_96_req_delta_level5")
    private Integer ttlStat65To96ReqDeltaLevel5;

    @Column(name="ttl_stat_97_to_128_req_delta_use")
    private Boolean ttlStat97To128ReqDeltaUse;
    @Column(name="ttl_stat_97_to_128_req_delta_level1")
    private Integer ttlStat97To128ReqDeltaLevel1;
    @Column(name="ttl_stat_97_to_128_req_delta_level2")
    private Integer ttlStat97To128ReqDeltaLevel2;
    @Column(name="ttl_stat_97_to_128_req_delta_level3")
    private Integer ttlStat97To128ReqDeltaLevel3;
    @Column(name="ttl_stat_97_to_128_req_delta_level4")
    private Integer ttlStat97To128ReqDeltaLevel4;
    @Column(name="ttl_stat_97_to_128_req_delta_level5")
    private Integer ttlStat97To128ReqDeltaLevel5;

    @Column(name="ttl_stat_129_to_160_req_delta_use")
    private Boolean ttlStat129To160ReqDeltaUse;
    @Column(name="ttl_stat_129_to_160_req_delta_level1")
    private Integer ttlStat129To160ReqDeltaLevel1;
    @Column(name="ttl_stat_129_to_160_req_delta_level2")
    private Integer ttlStat129To160ReqDeltaLevel2;
    @Column(name="ttl_stat_129_to_160_req_delta_level3")
    private Integer ttlStat129To160ReqDeltaLevel3;
    @Column(name="ttl_stat_129_to_160_req_delta_level4")
    private Integer ttlStat129To160ReqDeltaLevel4;
    @Column(name="ttl_stat_129_to_160_req_delta_level5")
    private Integer ttlStat129To160ReqDeltaLevel5;

    @Column(name="ttl_stat_161_to_192_req_delta_use")
    private Boolean ttlStat161To192ReqDeltaUse;
    @Column(name="ttl_stat_161_to_192_req_delta_level1")
    private Integer ttlStat161To192ReqDeltaLevel1;
    @Column(name="ttl_stat_161_to_192_req_delta_level2")
    private Integer ttlStat161To192ReqDeltaLevel2;
    @Column(name="ttl_stat_161_to_192_req_delta_level3")
    private Integer ttlStat161To192ReqDeltaLevel3;
    @Column(name="ttl_stat_161_to_192_req_delta_level4")
    private Integer ttlStat161To192ReqDeltaLevel4;
    @Column(name="ttl_stat_161_to_192_req_delta_level5")
    private Integer ttlStat161To192ReqDeltaLevel5;

    @Column(name="ttl_stat_193_to_224_req_delta_use")
    private Boolean ttlStat193To224ReqDeltaUse;
    @Column(name="ttl_stat_193_to_224_req_delta_level1")
    private Integer ttlStat193To224ReqDeltaLevel1;
    @Column(name="ttl_stat_193_to_224_req_delta_level2")
    private Integer ttlStat193To224ReqDeltaLevel2;
    @Column(name="ttl_stat_193_to_224_req_delta_level3")
    private Integer ttlStat193To224ReqDeltaLevel3;
    @Column(name="ttl_stat_193_to_224_req_delta_level4")
    private Integer ttlStat193To224ReqDeltaLevel4;
    @Column(name="ttl_stat_193_to_224_req_delta_level5")
    private Integer ttlStat193To224ReqDeltaLevel5;

    @Column(name="ttl_stat_225_to_255_req_delta_use")
    private Boolean ttlStat225To255ReqDeltaUse;
    @Column(name="ttl_stat_225_to_255_req_delta_level1")
    private Integer ttlStat225To255ReqDeltaLevel1;
    @Column(name="ttl_stat_225_to_255_req_delta_level2")
    private Integer ttlStat225To255ReqDeltaLevel2;
    @Column(name="ttl_stat_225_to_255_req_delta_level3")
    private Integer ttlStat225To255ReqDeltaLevel3;
    @Column(name="ttl_stat_225_to_255_req_delta_level4")
    private Integer ttlStat225To255ReqDeltaLevel4;
    @Column(name="ttl_stat_225_to_255_req_delta_level5")
    private Integer ttlStat225To255ReqDeltaLevel5;

    @Column(name="ttl_stat_1_res_delta_use")
    private Boolean ttlStat1ResDeltaUse;
    @Column(name="ttl_stat_1_res_delta_level1")
    private Integer ttlStat1ResDeltaLevel1;
    @Column(name="ttl_stat_1_res_delta_level2")
    private Integer ttlStat1ResDeltaLevel2;
    @Column(name="ttl_stat_1_res_delta_level3")
    private Integer ttlStat1ResDeltaLevel3;
    @Column(name="ttl_stat_1_res_delta_level4")
    private Integer ttlStat1ResDeltaLevel4;
    @Column(name="ttl_stat_1_res_delta_level5")
    private Integer ttlStat1ResDeltaLevel5;

    @Column(name="ttl_stat_2_to_5_res_delta_use")
    private Boolean ttlStat2To5ResDeltaUse;
    @Column(name="ttl_stat_2_to_5_res_delta_level1")
    private Integer ttlStat2To5ResDeltaLevel1;
    @Column(name="ttl_stat_2_to_5_res_delta_level2")
    private Integer ttlStat2To5ResDeltaLevel2;
    @Column(name="ttl_stat_2_to_5_res_delta_level3")
    private Integer ttlStat2To5ResDeltaLevel3;
    @Column(name="ttl_stat_2_to_5_res_delta_level4")
    private Integer ttlStat2To5ResDeltaLevel4;
    @Column(name="ttl_stat_2_to_5_res_delta_level5")
    private Integer ttlStat2To5ResDeltaLevel5;

    @Column(name="ttl_stat_6_to_32_res_delta_use")
    private Boolean ttlStat6To32ResDeltaUse;
    @Column(name="ttl_stat_6_to_32_res_delta_level1")
    private Integer ttlStat6To32ResDeltaLevel1;
    @Column(name="ttl_stat_6_to_32_res_delta_level2")
    private Integer ttlStat6To32ResDeltaLevel2;
    @Column(name="ttl_stat_6_to_32_res_delta_level3")
    private Integer ttlStat6To32ResDeltaLevel3;
    @Column(name="ttl_stat_6_to_32_res_delta_level4")
    private Integer ttlStat6To32ResDeltaLevel4;
    @Column(name="ttl_stat_6_to_32_res_delta_level5")
    private Integer ttlStat6To32ResDeltaLevel5;

    @Column(name="ttl_stat_33_to_64_res_delta_use")
    private Boolean ttlStat33To64ResDeltaUse;
    @Column(name="ttl_stat_33_to_64_res_delta_level1")
    private Integer ttlStat33To64ResDeltaLevel1;
    @Column(name="ttl_stat_33_to_64_res_delta_level2")
    private Integer ttlStat33To64ResDeltaLevel2;
    @Column(name="ttl_stat_33_to_64_res_delta_level3")
    private Integer ttlStat33To64ResDeltaLevel3;
    @Column(name="ttl_stat_33_to_64_res_delta_level4")
    private Integer ttlStat33To64ResDeltaLevel4;
    @Column(name="ttl_stat_33_to_64_res_delta_level5")
    private Integer ttlStat33To64ResDeltaLevel5;

    @Column(name="ttl_stat_65_to_96_res_delta_use")
    private Boolean ttlStat65To96ResDeltaUse;
    @Column(name="ttl_stat_65_to_96_res_delta_level1")
    private Integer ttlStat65To96ResDeltaLevel1;
    @Column(name="ttl_stat_65_to_96_res_delta_level2")
    private Integer ttlStat65To96ResDeltaLevel2;
    @Column(name="ttl_stat_65_to_96_res_delta_level3")
    private Integer ttlStat65To96ResDeltaLevel3;
    @Column(name="ttl_stat_65_to_96_res_delta_level4")
    private Integer ttlStat65To96ResDeltaLevel4;
    @Column(name="ttl_stat_65_to_96_res_delta_level5")
    private Integer ttlStat65To96ResDeltaLevel5;

    @Column(name="ttl_stat_97_to_128_res_delta_use")
    private Boolean ttlStat97To128ResDeltaUse;
    @Column(name="ttl_stat_97_to_128_res_delta_level1")
    private Integer ttlStat97To128ResDeltaLevel1;
    @Column(name="ttl_stat_97_to_128_res_delta_level2")
    private Integer ttlStat97To128ResDeltaLevel2;
    @Column(name="ttl_stat_97_to_128_res_delta_level3")
    private Integer ttlStat97To128ResDeltaLevel3;
    @Column(name="ttl_stat_97_to_128_res_delta_level4")
    private Integer ttlStat97To128ResDeltaLevel4;
    @Column(name="ttl_stat_97_to_128_res_delta_level5")
    private Integer ttlStat97To128ResDeltaLevel5;

    @Column(name="ttl_stat_129_to_160_res_delta_use")
    private Boolean ttlStat129To160ResDeltaUse;
    @Column(name="ttl_stat_129_to_160_res_delta_level1")
    private Integer ttlStat129To160ResDeltaLevel1;
    @Column(name="ttl_stat_129_to_160_res_delta_level2")
    private Integer ttlStat129To160ResDeltaLevel2;
    @Column(name="ttl_stat_129_to_160_res_delta_level3")
    private Integer ttlStat129To160ResDeltaLevel3;
    @Column(name="ttl_stat_129_to_160_res_delta_level4")
    private Integer ttlStat129To160ResDeltaLevel4;
    @Column(name="ttl_stat_129_to_160_res_delta_level5")
    private Integer ttlStat129To160ResDeltaLevel5;

    @Column(name="ttl_stat_161_to_192_res_delta_use")
    private Boolean ttlStat161To192ResDeltaUse;
    @Column(name="ttl_stat_161_to_192_res_delta_level1")
    private Integer ttlStat161To192ResDeltaLevel1;
    @Column(name="ttl_stat_161_to_192_res_delta_level2")
    private Integer ttlStat161To192ResDeltaLevel2;
    @Column(name="ttl_stat_161_to_192_res_delta_level3")
    private Integer ttlStat161To192ResDeltaLevel3;
    @Column(name="ttl_stat_161_to_192_res_delta_level4")
    private Integer ttlStat161To192ResDeltaLevel4;
    @Column(name="ttl_stat_161_to_192_res_delta_level5")
    private Integer ttlStat161To192ResDeltaLevel5;

    @Column(name="ttl_stat_193_to_224_res_delta_use")
    private Boolean ttlStat193To224ResDeltaUse;
    @Column(name="ttl_stat_193_to_224_res_delta_level1")
    private Integer ttlStat193To224ResDeltaLevel1;
    @Column(name="ttl_stat_193_to_224_res_delta_level2")
    private Integer ttlStat193To224ResDeltaLevel2;
    @Column(name="ttl_stat_193_to_224_res_delta_level3")
    private Integer ttlStat193To224ResDeltaLevel3;
    @Column(name="ttl_stat_193_to_224_res_delta_level4")
    private Integer ttlStat193To224ResDeltaLevel4;
    @Column(name="ttl_stat_193_to_224_res_delta_level5")
    private Integer ttlStat193To224ResDeltaLevel5;

    @Column(name="ttl_stat_225_to_255_res_delta_use")
    private Boolean ttlStat225To255ResDeltaUse;
    @Column(name="ttl_stat_225_to_255_res_delta_level1")
    private Integer ttlStat225To255ResDeltaLevel1;
    @Column(name="ttl_stat_225_to_255_res_delta_level2")
    private Integer ttlStat225To255ResDeltaLevel2;
    @Column(name="ttl_stat_225_to_255_res_delta_level3")
    private Integer ttlStat225To255ResDeltaLevel3;
    @Column(name="ttl_stat_225_to_255_res_delta_level4")
    private Integer ttlStat225To255ResDeltaLevel4;
    @Column(name="ttl_stat_225_to_255_res_delta_level5")
    private Integer ttlStat225To255ResDeltaLevel5;

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

}
