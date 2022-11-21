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
@Table(name="tbl_info_threshold_common")
public class InfoThresholdCommon {

    @Id
    @SequenceGenerator(name="tbl_info_threshold_common_seq", sequenceName="tbl_info_threshold_common_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_threshold_common_seq")
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


    @Column(name="len_req_tot_use")
    private Boolean lenReqTotUse;
    @Column(name="len_req_tot_level1")
    private Integer lenReqTotLevel1;
    @Column(name="len_req_tot_level2")
    private Integer lenReqTotLevel2;
    @Column(name="len_req_tot_level3")
    private Integer lenReqTotLevel3;
    @Column(name="len_req_tot_level4")
    private Integer lenReqTotLevel4;
    @Column(name="len_req_tot_level5")
    private Integer lenReqTotLevel5;

    @Column(name="len_res_tot_use")
    private Boolean lenResTotUse;
    @Column(name="len_res_tot_level1")
    private Integer lenResTotLevel1;
    @Column(name="len_res_tot_level2")
    private Integer lenResTotLevel2;
    @Column(name="len_res_tot_level3")
    private Integer lenResTotLevel3;
    @Column(name="len_res_tot_level4")
    private Integer lenResTotLevel4;
    @Column(name="len_res_tot_level5")
    private Integer lenResTotLevel5;

    @Column(name="pkts_req_tot_use")
    private Boolean pktsReqTotUse;
    @Column(name="pkts_req_tot_level1")
    private Integer pktsReqTotLevel1;
    @Column(name="pkts_req_tot_level2")
    private Integer pktsReqTotLevel2;
    @Column(name="pkts_req_tot_level3")
    private Integer pktsReqTotLevel3;
    @Column(name="pkts_req_tot_level4")
    private Integer pktsReqTotLevel4;
    @Column(name="pkts_req_tot_level5")
    private Integer pktsReqTotLevel5;

    @Column(name="pkts_res_tot_use")
    private Boolean pktsResTotUse;
    @Column(name="pkts_res_tot_level1")
    private Integer pktsResTotLevel1;
    @Column(name="pkts_res_tot_level2")
    private Integer pktsResTotLevel2;
    @Column(name="pkts_res_tot_level3")
    private Integer pktsResTotLevel3;
    @Column(name="pkts_res_tot_level4")
    private Integer pktsResTotLevel4;
    @Column(name="pkts_res_tot_level5")
    private Integer pktsResTotLevel5;

    @Column(name="len_req_per_sec_use")
    private Boolean lenReqPerSecUse;
    @Column(name="len_req_per_sec_level1")
    private Integer lenReqPerSecLevel1;
    @Column(name="len_req_per_sec_level2")
    private Integer lenReqPerSecLevel2;
    @Column(name="len_req_per_sec_level3")
    private Integer lenReqPerSecLevel3;
    @Column(name="len_req_per_sec_level4")
    private Integer lenReqPerSecLevel4;
    @Column(name="len_req_per_sec_level5")
    private Integer lenReqPerSecLevel5;

    @Column(name="len_res_per_sec_use")
    private Boolean lenResPerSecUse;
    @Column(name="len_res_per_sec_level1")
    private Integer lenResPerSecLevel1;
    @Column(name="len_res_per_sec_level2")
    private Integer lenResPerSecLevel2;
    @Column(name="len_res_per_sec_level3")
    private Integer lenResPerSecLevel3;
    @Column(name="len_res_per_sec_level4")
    private Integer lenResPerSecLevel4;
    @Column(name="len_res_per_sec_level5")
    private Integer lenResPerSecLevel5;

    @Column(name="pkts_req_per_sec_use")
    private Boolean pktsReqPerSecUse;
    @Column(name="pkts_req_per_sec_level1")
    private Integer pktsReqPerSecLevel1;
    @Column(name="pkts_req_per_sec_level2")
    private Integer pktsReqPerSecLevel2;
    @Column(name="pkts_req_per_sec_level3")
    private Integer pktsReqPerSecLevel3;
    @Column(name="pkts_req_per_sec_level4")
    private Integer pktsReqPerSecLevel4;
    @Column(name="pkts_req_per_sec_level5")
    private Integer pktsReqPerSecLevel5;

    @Column(name="pkts_res_per_sec_use")
    private Boolean pktsResPerSecUse;
    @Column(name="pkts_res_per_sec_level1")
    private Integer pktsResPerSecLevel1;
    @Column(name="pkts_res_per_sec_level2")
    private Integer pktsResPerSecLevel2;
    @Column(name="pkts_res_per_sec_level3")
    private Integer pktsResPerSecLevel3;
    @Column(name="pkts_res_per_sec_level4")
    private Integer pktsResPerSecLevel4;
    @Column(name="pkts_res_per_sec_level5")
    private Integer pktsResPerSecLevel5;

    @Column(name="len_req_delta_use")
    private Boolean lenReqDeltaUse;
    @Column(name="len_req_delta_level1")
    private Integer lenReqDeltaLevel1;
    @Column(name="len_req_delta_level2")
    private Integer lenReqDeltaLevel2;
    @Column(name="len_req_delta_level3")
    private Integer lenReqDeltaLevel3;
    @Column(name="len_req_delta_level4")
    private Integer lenReqDeltaLevel4;
    @Column(name="len_req_delta_level5")
    private Integer lenReqDeltaLevel5;

    @Column(name="len_res_delta_use")
    private Boolean lenResDeltaUse;
    @Column(name="len_res_delta_level1")
    private Integer lenResDeltaLevel1;
    @Column(name="len_res_delta_level2")
    private Integer lenResDeltaLevel2;
    @Column(name="len_res_delta_level3")
    private Integer lenResDeltaLevel3;
    @Column(name="len_res_delta_level4")
    private Integer lenResDeltaLevel4;
    @Column(name="len_res_delta_level5")
    private Integer lenResDeltaLevel5;

    @Column(name="pkts_req_delta_use")
    private Boolean pktsReqDeltaUse;
    @Column(name="pkts_req_delta_level1")
    private Integer pktsReqDeltaLevel1;
    @Column(name="pkts_req_delta_level2")
    private Integer pktsReqDeltaLevel2;
    @Column(name="pkts_req_delta_level3")
    private Integer pktsReqDeltaLevel3;
    @Column(name="pkts_req_delta_level4")
    private Integer pktsReqDeltaLevel4;
    @Column(name="pkts_req_delta_level5")
    private Integer pktsReqDeltaLevel5;

    @Column(name="pkts_res_delta_use")
    private Boolean pktsResDeltaUse;
    @Column(name="pkts_res_delta_level1")
    private Integer pktsResDeltaLevel1;
    @Column(name="pkts_res_delta_level2")
    private Integer pktsResDeltaLevel2;
    @Column(name="pkts_res_delta_level3")
    private Integer pktsResDeltaLevel3;
    @Column(name="pkts_res_delta_level4")
    private Integer pktsResDeltaLevel4;
    @Column(name="pkts_res_delta_level5")
    private Integer pktsResDeltaLevel5;

    @Column(name="pkt_len_min_req_use")
    private Boolean pktLenMinReqUse;
    @Column(name="pkt_len_min_req_level1")
    private Integer pktLenMinReqLevel1;
    @Column(name="pkt_len_min_req_level2")
    private Integer pktLenMinReqLevel2;
    @Column(name="pkt_len_min_req_level3")
    private Integer pktLenMinReqLevel3;
    @Column(name="pkt_len_min_req_level4")
    private Integer pktLenMinReqLevel4;
    @Column(name="pkt_len_min_req_level5")
    private Integer pktLenMinReqLevel5;

    @Column(name="pkt_len_min_res_use")
    private Boolean pktLenMinResUse;
    @Column(name="pkt_len_min_res_level1")
    private Integer pktLenMinResLevel1;
    @Column(name="pkt_len_min_res_level2")
    private Integer pktLenMinResLevel2;
    @Column(name="pkt_len_min_res_level3")
    private Integer pktLenMinResLevel3;
    @Column(name="pkt_len_min_res_level4")
    private Integer pktLenMinResLevel4;
    @Column(name="pkt_len_min_res_level5")
    private Integer pktLenMinResLevel5;

    @Column(name="pkt_len_max_req_use")
    private Boolean pktLenMaxReqUse;
    @Column(name="pkt_len_max_req_level1")
    private Integer pktLenMaxReqLevel1;
    @Column(name="pkt_len_max_req_level2")
    private Integer pktLenMaxReqLevel2;
    @Column(name="pkt_len_max_req_level3")
    private Integer pktLenMaxReqLevel3;
    @Column(name="pkt_len_max_req_level4")
    private Integer pktLenMaxReqLevel4;
    @Column(name="pkt_len_max_req_level5")
    private Integer pktLenMaxReqLevel5;

    @Column(name="pkt_len_max_res_use")
    private Boolean pktLenMaxResUse;
    @Column(name="pkt_len_max_res_level1")
    private Integer pktLenMaxResLevel1;
    @Column(name="pkt_len_max_res_level2")
    private Integer pktLenMaxResLevel2;
    @Column(name="pkt_len_max_res_level3")
    private Integer pktLenMaxResLevel3;
    @Column(name="pkt_len_max_res_level4")
    private Integer pktLenMaxResLevel4;
    @Column(name="pkt_len_max_res_level5")
    private Integer pktLenMaxResLevel5;

    @Column(name="pkt_len_stat_1_to_128_req_tot_use")
    private Boolean pktLenStat1To128ReqTotUse;
    @Column(name="pkt_len_stat_1_to_128_req_tot_level1")
    private Integer pktLenStat1To128ReqTotLevel1;
    @Column(name="pkt_len_stat_1_to_128_req_tot_level2")
    private Integer pktLenStat1To128ReqTotLevel2;
    @Column(name="pkt_len_stat_1_to_128_req_tot_level3")
    private Integer pktLenStat1To128ReqTotLevel3;
    @Column(name="pkt_len_stat_1_to_128_req_tot_level4")
    private Integer pktLenStat1To128ReqTotLevel4;
    @Column(name="pkt_len_stat_1_to_128_req_tot_level5")
    private Integer pktLenStat1To128ReqTotLevel5;

    @Column(name="pkt_len_stat_129_to_256_req_tot_use")
    private Boolean pktLenStat129To256ReqTotUse;
    @Column(name="pkt_len_stat_129_to_256_req_tot_level1")
    private Integer pktLenStat129To256ReqTotLevel1;
    @Column(name="pkt_len_stat_129_to_256_req_tot_level2")
    private Integer pktLenStat129To256ReqTotLevel2;
    @Column(name="pkt_len_stat_129_to_256_req_tot_level3")
    private Integer pktLenStat129To256ReqTotLevel3;
    @Column(name="pkt_len_stat_129_to_256_req_tot_level4")
    private Integer pktLenStat129To256ReqTotLevel4;
    @Column(name="pkt_len_stat_129_to_256_req_tot_level5")
    private Integer pktLenStat129To256ReqTotLevel5;

    @Column(name="pkt_len_stat_257_to_512_req_tot_use")
    private Boolean pktLenStat257To512ReqTotUse;
    @Column(name="pkt_len_stat_257_to_512_req_tot_level1")
    private Integer pktLenStat257To512ReqTotLevel1;
    @Column(name="pkt_len_stat_257_to_512_req_tot_level2")
    private Integer pktLenStat257To512ReqTotLevel2;
    @Column(name="pkt_len_stat_257_to_512_req_tot_level3")
    private Integer pktLenStat257To512ReqTotLevel3;
    @Column(name="pkt_len_stat_257_to_512_req_tot_level4")
    private Integer pktLenStat257To512ReqTotLevel4;
    @Column(name="pkt_len_stat_257_to_512_req_tot_level5")
    private Integer pktLenStat257To512ReqTotLevel5;

    @Column(name="pkt_len_stat_513_to_1024_req_tot_use")
    private Boolean pktLenStat513To1024ReqTotUse;
    @Column(name="pkt_len_stat_513_to_1024_req_tot_level1")
    private Integer pktLenStat513To1024ReqTotLevel1;
    @Column(name="pkt_len_stat_513_to_1024_req_tot_level2")
    private Integer pktLenStat513To1024ReqTotLevel2;
    @Column(name="pkt_len_stat_513_to_1024_req_tot_level3")
    private Integer pktLenStat513To1024ReqTotLevel3;
    @Column(name="pkt_len_stat_513_to_1024_req_tot_level4")
    private Integer pktLenStat513To1024ReqTotLevel4;
    @Column(name="pkt_len_stat_513_to_1024_req_tot_level5")
    private Integer pktLenStat513To1024ReqTotLevel5;

    @Column(name="pkt_len_stat_1025_to_1514_req_tot_use")
    private Boolean pktLenStat1025To1514ReqTotUse;
    @Column(name="pkt_len_stat_1025_to_1514_req_tot_level1")
    private Integer pktLenStat1025To1514ReqTotLevel1;
    @Column(name="pkt_len_stat_1025_to_1514_req_tot_level2")
    private Integer pktLenStat1025To1514ReqTotLevel2;
    @Column(name="pkt_len_stat_1025_to_1514_req_tot_level3")
    private Integer pktLenStat1025To1514ReqTotLevel3;
    @Column(name="pkt_len_stat_1025_to_1514_req_tot_level4")
    private Integer pktLenStat1025To1514ReqTotLevel4;
    @Column(name="pkt_len_stat_1025_to_1514_req_tot_level5")
    private Integer pktLenStat1025To1514ReqTotLevel5;

    @Column(name="pkt_len_stat_jumbo_req_tot_use")
    private Boolean pktLenStatJumboReqTotUse;
    @Column(name="pkt_len_stat_jumbo_req_tot_level1")
    private Integer pktLenStatJumboReqTotLevel1;
    @Column(name="pkt_len_stat_jumbo_req_tot_level2")
    private Integer pktLenStatJumboReqTotLevel2;
    @Column(name="pkt_len_stat_jumbo_req_tot_level3")
    private Integer pktLenStatJumboReqTotLevel3;
    @Column(name="pkt_len_stat_jumbo_req_tot_level4")
    private Integer pktLenStatJumboReqTotLevel4;
    @Column(name="pkt_len_stat_jumbo_req_tot_level5")
    private Integer pktLenStatJumboReqTotLevel5;

    @Column(name="pkt_len_stat_1_to_128_res_tot_use")
    private Boolean pktLenStat1To128ResTotUse;
    @Column(name="pkt_len_stat_1_to_128_res_tot_level1")
    private Integer pktLenStat1To128ResTotLevel1;
    @Column(name="pkt_len_stat_1_to_128_res_tot_level2")
    private Integer pktLenStat1To128ResTotLevel2;
    @Column(name="pkt_len_stat_1_to_128_res_tot_level3")
    private Integer pktLenStat1To128ResTotLevel3;
    @Column(name="pkt_len_stat_1_to_128_res_tot_level4")
    private Integer pktLenStat1To128ResTotLevel14;
    @Column(name="pkt_len_stat_1_to_128_res_tot_level5")
    private Integer pktLenStat1To128ResTotLevel5;

    @Column(name="pkt_len_stat_129_to_256_res_tot_use")
    private Boolean pktLenStat129To256ResTotUse;
    @Column(name="pkt_len_stat_129_to_256_res_tot_level1")
    private Integer pktLenStat129To256ResTotLevel1;
    @Column(name="pkt_len_stat_129_to_256_res_tot_level2")
    private Integer pktLenStat129To256ResTotLevel2;
    @Column(name="pkt_len_stat_129_to_256_res_tot_level3")
    private Integer pktLenStat129To256ResTotLevel3;
    @Column(name="pkt_len_stat_129_to_256_res_tot_level4")
    private Integer pktLenStat129To256ResTotLevel4;
    @Column(name="pkt_len_stat_129_to_256_res_tot_level5")
    private Integer pktLenStat129To256ResTotLevel5;

    @Column(name="pkt_len_stat_257_to_512_res_tot_use")
    private Boolean pktLenStat257To512ResTotUse;
    @Column(name="pkt_len_stat_257_to_512_res_tot_level1")
    private Integer pktLenStat257To512ResTotLevel1;
    @Column(name="pkt_len_stat_257_to_512_res_tot_level2")
    private Integer pktLenStat257To512ResTotLevel2;
    @Column(name="pkt_len_stat_257_to_512_res_tot_level3")
    private Integer pktLenStat257To512ResTotLevel3;
    @Column(name="pkt_len_stat_257_to_512_res_tot_level4")
    private Integer pktLenStat257To512ResTotLevel4;
    @Column(name="pkt_len_stat_257_to_512_res_tot_level5")
    private Integer pktLenStat257To512ResTotLevel5;

    @Column(name="pkt_len_stat_513_to_1024_res_tot_use")
    private Boolean pktLenStat513To1024ResTotUse;
    @Column(name="pkt_len_stat_513_to_1024_res_tot_level1")
    private Integer pktLenStat513To1024ResTotLevel1;
    @Column(name="pkt_len_stat_513_to_1024_res_tot_level2")
    private Integer pktLenStat513To1024ResTotLevel2;
    @Column(name="pkt_len_stat_513_to_1024_res_tot_level3")
    private Integer pktLenStat513To1024ResTotLevel3;
    @Column(name="pkt_len_stat_513_to_1024_res_tot_level4")
    private Integer pktLenStat513To1024ResTotLevel4;
    @Column(name="pkt_len_stat_513_to_1024_res_tot_level5")
    private Integer pktLenStat513To1024ResTotLevel5;

    @Column(name="pkt_len_stat_1025_to_1514_res_tot_use")
    private Boolean pktLenStat1025To1514ResTotUse;
    @Column(name="pkt_len_stat_1025_to_1514_res_tot_level1")
    private Integer pktLenStat1025To1514ResTotLevel1;
    @Column(name="pkt_len_stat_1025_to_1514_res_tot_level2")
    private Integer pktLenStat1025To1514ResTotLevel2;
    @Column(name="pkt_len_stat_1025_to_1514_res_tot_level3")
    private Integer pktLenStat1025To1514ResTotLevel3;
    @Column(name="pkt_len_stat_1025_to_1514_res_tot_level4")
    private Integer pktLenStat1025To1514ResTotLevel4;
    @Column(name="pkt_len_stat_1025_to_1514_res_tot_level5")
    private Integer pktLenStat1025To1514ResTotLevel5;

    @Column(name="pkt_len_stat_jumbo_res_tot_use")
    private Boolean pktLenStatJumboResTotUse;
    @Column(name="pkt_len_stat_jumbo_res_tot_level1")
    private Integer pktLenStatJumboResTotLevel1;
    @Column(name="pkt_len_stat_jumbo_res_tot_level2")
    private Integer pktLenStatJumboResTotLevel2;
    @Column(name="pkt_len_stat_jumbo_res_tot_level3")
    private Integer pktLenStatJumboResTotLevel3;
    @Column(name="pkt_len_stat_jumbo_res_tot_level4")
    private Integer pktLenStatJumboResTotLevel4;
    @Column(name="pkt_len_stat_jumbo_res_tot_level5")
    private Integer pktLenStatJumboResTotLevel5;

    @Column(name="pkt_len_stat_1_to_128_req_per_sec_use")
    private Boolean pktLenStat1To128ReqPerSecUse;
    @Column(name="pkt_len_stat_1_to_128_req_per_sec_level1")
    private Integer pktLenStat1To128ReqPerSecLevel1;
    @Column(name="pkt_len_stat_1_to_128_req_per_sec_level2")
    private Integer pktLenStat1To128ReqPerSecLevel2;
    @Column(name="pkt_len_stat_1_to_128_req_per_sec_level3")
    private Integer pktLenStat1To128ReqPerSecLevel3;
    @Column(name="pkt_len_stat_1_to_128_req_per_sec_level4")
    private Integer pktLenStat1To128ReqPerSecLevel4;
    @Column(name="pkt_len_stat_1_to_128_req_per_sec_level5")
    private Integer pktLenStat1To128ReqPerSecLevel5;

    @Column(name="pkt_len_stat_129_to_256_req_per_sec_use")
    private Boolean pktLenStat129To256ReqPerSecUse;
    @Column(name="pkt_len_stat_129_to_256_req_per_sec_level1")
    private Integer pktLenStat129To256ReqPerSecLevel1;
    @Column(name="pkt_len_stat_129_to_256_req_per_sec_level2")
    private Integer pktLenStat129To256ReqPerSecLevel2;
    @Column(name="pkt_len_stat_129_to_256_req_per_sec_level3")
    private Integer pktLenStat129To256ReqPerSecLevel3;
    @Column(name="pkt_len_stat_129_to_256_req_per_sec_level4")
    private Integer pktLenStat129To256ReqPerSecLevel4;
    @Column(name="pkt_len_stat_129_to_256_req_per_sec_level5")
    private Integer pktLenStat129To256ReqPerSecLevel5;

    @Column(name="pkt_len_stat_257_to_512_req_per_sec_use")
    private Boolean pktLenStat257To512ReqPerSecUse;
    @Column(name="pkt_len_stat_257_to_512_req_per_sec_level1")
    private Integer pktLenStat257To512ReqPerSecLevel1;
    @Column(name="pkt_len_stat_257_to_512_req_per_sec_level2")
    private Integer pktLenStat257To512ReqPerSecLevel2;
    @Column(name="pkt_len_stat_257_to_512_req_per_sec_level3")
    private Integer pktLenStat257To512ReqPerSecLevel3;
    @Column(name="pkt_len_stat_257_to_512_req_per_sec_level4")
    private Integer pktLenStat257To512ReqPerSecLevel4;
    @Column(name="pkt_len_stat_257_to_512_req_per_sec_level5")
    private Integer pktLenStat257To512ReqPerSecLevel5;

    @Column(name="pkt_len_stat_513_to_1024_req_per_sec_use")
    private Boolean pktLenStat513To1024ReqPerSecUse;
    @Column(name="pkt_len_stat_513_to_1024_req_per_sec_level1")
    private Integer pktLenStat513To1024ReqPerSecLevel1;
    @Column(name="pkt_len_stat_513_to_1024_req_per_sec_level2")
    private Integer pktLenStat513To1024ReqPerSecLevel2;
    @Column(name="pkt_len_stat_513_to_1024_req_per_sec_level3")
    private Integer pktLenStat513To1024ReqPerSecLevel3;
    @Column(name="pkt_len_stat_513_to_1024_req_per_sec_level4")
    private Integer pktLenStat513To1024ReqPerSecLevel4;
    @Column(name="pkt_len_stat_513_to_1024_req_per_sec_level5")
    private Integer pktLenStat513To1024ReqPerSecLevel5;

    @Column(name="pkt_len_stat_1025_to_1514_req_per_sec_use")
    private Boolean pktLenStat1025To1514ReqPerSecUse;
    @Column(name="pkt_len_stat_1025_to_1514_req_per_sec_level1")
    private Integer pktLenStat1025To1514ReqPerSecLevel1;
    @Column(name="pkt_len_stat_1025_to_1514_req_per_sec_level2")
    private Integer pktLenStat1025To1514ReqPerSecLevel2;
    @Column(name="pkt_len_stat_1025_to_1514_req_per_sec_level3")
    private Integer pktLenStat1025To1514ReqPerSecLevel3;
    @Column(name="pkt_len_stat_1025_to_1514_req_per_sec_level4")
    private Integer pktLenStat1025To1514ReqPerSecLevel4;
    @Column(name="pkt_len_stat_1025_to_1514_req_per_sec_level5")
    private Integer pktLenStat1025To1514ReqPerSecLevel5;

    @Column(name="pkt_len_stat_jumbo_req_per_sec_use")
    private Boolean pktLenStatJumboReqPerSecUse;
    @Column(name="pkt_len_stat_jumbo_req_per_sec_level1")
    private Integer pktLenStatJumboReqPerSecLevel1;
    @Column(name="pkt_len_stat_jumbo_req_per_sec_level2")
    private Integer pktLenStatJumboReqPerSecLevel2;
    @Column(name="pkt_len_stat_jumbo_req_per_sec_level3")
    private Integer pktLenStatJumboReqPerSecLevel3;
    @Column(name="pkt_len_stat_jumbo_req_per_sec_level4")
    private Integer pktLenStatJumboReqPerSecLevel4;
    @Column(name="pkt_len_stat_jumbo_req_per_sec_level5")
    private Integer pktLenStatJumboReqPerSecLevel5;

    @Column(name="pkt_len_stat_1_to_128_res_per_sec_use")
    private Boolean pktLenStat1To128ResPerSecUse;
    @Column(name="pkt_len_stat_1_to_128_res_per_sec_level1")
    private Integer pktLenStat1To128ResPerSecLevel1;
    @Column(name="pkt_len_stat_1_to_128_res_per_sec_level2")
    private Integer pktLenStat1To128ResPerSecLevel2;
    @Column(name="pkt_len_stat_1_to_128_res_per_sec_level3")
    private Integer pktLenStat1To128ResPerSecLevel3;
    @Column(name="pkt_len_stat_1_to_128_res_per_sec_level4")
    private Integer pktLenStat1To128ResPerSecLevel14;
    @Column(name="pkt_len_stat_1_to_128_res_per_sec_level5")
    private Integer pktLenStat1To128ResPerSecLevel5;

    @Column(name="pkt_len_stat_129_to_256_res_per_sec_use")
    private Boolean pktLenStat129To256ResPerSecUse;
    @Column(name="pkt_len_stat_129_to_256_res_per_sec_level1")
    private Integer pktLenStat129To256ResPerSecLevel1;
    @Column(name="pkt_len_stat_129_to_256_res_per_sec_level2")
    private Integer pktLenStat129To256ResPerSecLevel2;
    @Column(name="pkt_len_stat_129_to_256_res_per_sec_level3")
    private Integer pktLenStat129To256ResPerSecLevel3;
    @Column(name="pkt_len_stat_129_to_256_res_per_sec_level4")
    private Integer pktLenStat129To256ResPerSecLevel4;
    @Column(name="pkt_len_stat_129_to_256_res_per_sec_level5")
    private Integer pktLenStat129To256ResPerSecLevel5;

    @Column(name="pkt_len_stat_257_to_512_res_per_sec_use")
    private Boolean pktLenStat257To512ResPerSecUse;
    @Column(name="pkt_len_stat_257_to_512_res_per_sec_level1")
    private Integer pktLenStat257To512ResPerSecLevel1;
    @Column(name="pkt_len_stat_257_to_512_res_per_sec_level2")
    private Integer pktLenStat257To512ResPerSecLevel2;
    @Column(name="pkt_len_stat_257_to_512_res_per_sec_level3")
    private Integer pktLenStat257To512ResPerSecLevel3;
    @Column(name="pkt_len_stat_257_to_512_res_per_sec_level4")
    private Integer pktLenStat257To512ResPerSecLevel4;
    @Column(name="pkt_len_stat_257_to_512_res_per_sec_level5")
    private Integer pktLenStat257To512ResPerSecLevel5;

    @Column(name="pkt_len_stat_513_to_1024_res_per_sec_use")
    private Boolean pktLenStat513To1024ResPerSecUse;
    @Column(name="pkt_len_stat_513_to_1024_res_per_sec_level1")
    private Integer pktLenStat513To1024ResPerSecLevel1;
    @Column(name="pkt_len_stat_513_to_1024_res_per_sec_level2")
    private Integer pktLenStat513To1024ResPerSecLevel2;
    @Column(name="pkt_len_stat_513_to_1024_res_per_sec_level3")
    private Integer pktLenStat513To1024ResPerSecLevel3;
    @Column(name="pkt_len_stat_513_to_1024_res_per_sec_level4")
    private Integer pktLenStat513To1024ResPerSecLevel4;
    @Column(name="pkt_len_stat_513_to_1024_res_per_sec_level5")
    private Integer pktLenStat513To1024ResPerSecLevel5;

    @Column(name="pkt_len_stat_1025_to_1514_res_per_sec_use")
    private Boolean pktLenStat1025To1514ResPerSecUse;
    @Column(name="pkt_len_stat_1025_to_1514_res_per_sec_level1")
    private Integer pktLenStat1025To1514ResPerSecLevel1;
    @Column(name="pkt_len_stat_1025_to_1514_res_per_sec_level2")
    private Integer pktLenStat1025To1514ResPerSecLevel2;
    @Column(name="pkt_len_stat_1025_to_1514_res_per_sec_level3")
    private Integer pktLenStat1025To1514ResPerSecLevel3;
    @Column(name="pkt_len_stat_1025_to_1514_res_per_sec_level4")
    private Integer pktLenStat1025To1514ResPerSecLevel4;
    @Column(name="pkt_len_stat_1025_to_1514_res_per_sec_level5")
    private Integer pktLenStat1025To1514ResPerSecLevel5;

    @Column(name="pkt_len_stat_jumbo_res_per_sec_use")
    private Boolean pktLenStatJumboResPerSecUse;
    @Column(name="pkt_len_stat_jumbo_res_per_sec_level1")
    private Integer pktLenStatJumboResPerSecLevel1;
    @Column(name="pkt_len_stat_jumbo_res_per_sec_level2")
    private Integer pktLenStatJumboResPerSecLevel2;
    @Column(name="pkt_len_stat_jumbo_res_per_sec_level3")
    private Integer pktLenStatJumboResPerSecLevel3;
    @Column(name="pkt_len_stat_jumbo_res_per_sec_level4")
    private Integer pktLenStatJumboResPerSecLevel4;
    @Column(name="pkt_len_stat_jumbo_res_per_sec_level5")
    private Integer pktLenStatJumboResPerSecLevel5;

    @Column(name="pkt_len_stat_1_to_128_req_delta_use")
    private Boolean pktLenStat1To128ReqDeltaUse;
    @Column(name="pkt_len_stat_1_to_128_req_delta_level1")
    private Integer pktLenStat1To128ReqDeltaLevel1;
    @Column(name="pkt_len_stat_1_to_128_req_delta_level2")
    private Integer pktLenStat1To128ReqDeltaLevel2;
    @Column(name="pkt_len_stat_1_to_128_req_delta_level3")
    private Integer pktLenStat1To128ReqDeltaLevel3;
    @Column(name="pkt_len_stat_1_to_128_req_delta_level4")
    private Integer pktLenStat1To128ReqDeltaLevel4;
    @Column(name="pkt_len_stat_1_to_128_req_delta_level5")
    private Integer pktLenStat1To128ReqDeltaLevel5;

    @Column(name="pkt_len_stat_129_to_256_req_delta_use")
    private Boolean pktLenStat129To256ReqDeltaUse;
    @Column(name="pkt_len_stat_129_to_256_req_delta_level1")
    private Integer pktLenStat129To256ReqDeltaLevel1;
    @Column(name="pkt_len_stat_129_to_256_req_delta_level2")
    private Integer pktLenStat129To256ReqDeltaLevel2;
    @Column(name="pkt_len_stat_129_to_256_req_delta_level3")
    private Integer pktLenStat129To256ReqDeltaLevel3;
    @Column(name="pkt_len_stat_129_to_256_req_delta_level4")
    private Integer pktLenStat129To256ReqDeltaLevel4;
    @Column(name="pkt_len_stat_129_to_256_req_delta_level5")
    private Integer pktLenStat129To256ReqDeltaLevel5;

    @Column(name="pkt_len_stat_257_to_512_req_delta_use")
    private Boolean pktLenStat257To512ReqDeltaUse;
    @Column(name="pkt_len_stat_257_to_512_req_delta_level1")
    private Integer pktLenStat257To512ReqDeltaLevel1;
    @Column(name="pkt_len_stat_257_to_512_req_delta_level2")
    private Integer pktLenStat257To512ReqDeltaLevel2;
    @Column(name="pkt_len_stat_257_to_512_req_delta_level3")
    private Integer pktLenStat257To512ReqDeltaLevel3;
    @Column(name="pkt_len_stat_257_to_512_req_delta_level4")
    private Integer pktLenStat257To512ReqDeltaLevel4;
    @Column(name="pkt_len_stat_257_to_512_req_delta_level5")
    private Integer pktLenStat257To512ReqDeltaLevel5;

    @Column(name="pkt_len_stat_513_to_1024_req_delta_use")
    private Boolean pktLenStat513To1024ReqDeltaUse;
    @Column(name="pkt_len_stat_513_to_1024_req_delta_level1")
    private Integer pktLenStat513To1024ReqDeltaLevel1;
    @Column(name="pkt_len_stat_513_to_1024_req_delta_level2")
    private Integer pktLenStat513To1024ReqDeltaLevel2;
    @Column(name="pkt_len_stat_513_to_1024_req_delta_level3")
    private Integer pktLenStat513To1024ReqDeltaLevel3;
    @Column(name="pkt_len_stat_513_to_1024_req_delta_level4")
    private Integer pktLenStat513To1024ReqDeltaLevel4;
    @Column(name="pkt_len_stat_513_to_1024_req_delta_level5")
    private Integer pktLenStat513To1024ReqDeltaLevel5;

    @Column(name="pkt_len_stat_1025_to_1514_req_delta_use")
    private Boolean pktLenStat1025To1514ReqDeltaUse;
    @Column(name="pkt_len_stat_1025_to_1514_req_delta_level1")
    private Integer pktLenStat1025To1514ReqDeltaLevel1;
    @Column(name="pkt_len_stat_1025_to_1514_req_delta_level2")
    private Integer pktLenStat1025To1514ReqDeltaLevel2;
    @Column(name="pkt_len_stat_1025_to_1514_req_delta_level3")
    private Integer pktLenStat1025To1514ReqDeltaLevel3;
    @Column(name="pkt_len_stat_1025_to_1514_req_delta_level4")
    private Integer pktLenStat1025To1514ReqDeltaLevel4;
    @Column(name="pkt_len_stat_1025_to_1514_req_delta_level5")
    private Integer pktLenStat1025To1514ReqDeltaLevel5;

    @Column(name="pkt_len_stat_jumbo_req_delta_use")
    private Boolean pktLenStatJumboReqDeltaUse;
    @Column(name="pkt_len_stat_jumbo_req_delta_level1")
    private Integer pktLenStatJumboReqDeltaLevel1;
    @Column(name="pkt_len_stat_jumbo_req_delta_level2")
    private Integer pktLenStatJumboReqDeltaLevel2;
    @Column(name="pkt_len_stat_jumbo_req_delta_level3")
    private Integer pktLenStatJumboReqDeltaLevel3;
    @Column(name="pkt_len_stat_jumbo_req_delta_level4")
    private Integer pktLenStatJumboReqDeltaLevel4;
    @Column(name="pkt_len_stat_jumbo_req_delta_level5")
    private Integer pktLenStatJumboReqDeltaLevel5;

    @Column(name="pkt_len_stat_1_to_128_res_delta_use")
    private Boolean pktLenStat1To128ResDeltaUse;
    @Column(name="pkt_len_stat_1_to_128_res_delta_level1")
    private Integer pktLenStat1To128ResDeltaLevel1;
    @Column(name="pkt_len_stat_1_to_128_res_delta_level2")
    private Integer pktLenStat1To128ResDeltaLevel2;
    @Column(name="pkt_len_stat_1_to_128_res_delta_level3")
    private Integer pktLenStat1To128ResDeltaLevel3;
    @Column(name="pkt_len_stat_1_to_128_res_delta_level4")
    private Integer pktLenStat1To128ResDeltaLevel4;
    @Column(name="pkt_len_stat_1_to_128_res_delta_level5")
    private Integer pktLenStat1To128ResDeltaLevel5;

    @Column(name="pkt_len_stat_129_to_256_res_delta_use")
    private Boolean pktLenStat129To256ResDeltaUse;
    @Column(name="pkt_len_stat_129_to_256_res_delta_level1")
    private Integer pktLenStat129To256ResDeltaLevel1;
    @Column(name="pkt_len_stat_129_to_256_res_delta_level2")
    private Integer pktLenStat129To256ResDeltaLevel2;
    @Column(name="pkt_len_stat_129_to_256_res_delta_level3")
    private Integer pktLenStat129To256ResDeltaLevel3;
    @Column(name="pkt_len_stat_129_to_256_res_delta_level4")
    private Integer pktLenStat129To256ResDeltaLevel4;
    @Column(name="pkt_len_stat_129_to_256_res_delta_level5")
    private Integer pktLenStat129To256ResDeltaLevel5;

    @Column(name="pkt_len_stat_257_to_512_res_delta_use")
    private Boolean pktLenStat257To512ResDeltaUse;
    @Column(name="pkt_len_stat_257_to_512_res_delta_level1")
    private Integer pktLenStat257To512ResDeltaLevel1;
    @Column(name="pkt_len_stat_257_to_512_res_delta_level2")
    private Integer pktLenStat257To512ResDeltaLevel2;
    @Column(name="pkt_len_stat_257_to_512_res_delta_level3")
    private Integer pktLenStat257To512ResDeltaLevel3;
    @Column(name="pkt_len_stat_257_to_512_res_delta_level4")
    private Integer pktLenStat257To512ResDeltaLevel4;
    @Column(name="pkt_len_stat_257_to_512_res_delta_level5")
    private Integer pktLenStat257To512ResDeltaLevel5;

    @Column(name="pkt_len_stat_513_to_1024_res_delta_use")
    private Boolean pktLenStat513To1024ResDeltaUse;
    @Column(name="pkt_len_stat_513_to_1024_res_delta_level1")
    private Integer pktLenStat513To1024ResDeltaLevel1;
    @Column(name="pkt_len_stat_513_to_1024_res_delta_level2")
    private Integer pktLenStat513To1024ResDeltaLevel2;
    @Column(name="pkt_len_stat_513_to_1024_res_delta_level3")
    private Integer pktLenStat513To1024ResDeltaLevel3;
    @Column(name="pkt_len_stat_513_to_1024_res_delta_level4")
    private Integer pktLenStat513To1024ResDeltaLevel4;
    @Column(name="pkt_len_stat_513_to_1024_res_delta_level5")
    private Integer pktLenStat513To1024ResDeltaLevel5;

    @Column(name="pkt_len_stat_1025_to_1514_res_delta_use")
    private Boolean pktLenStat1025To1514ResDeltaUse;
    @Column(name="pkt_len_stat_1025_to_1514_res_delta_level1")
    private Integer pktLenStat1025To1514ResDeltaLevel1;
    @Column(name="pkt_len_stat_1025_to_1514_res_delta_level2")
    private Integer pktLenStat1025To1514ResDeltaLevel2;
    @Column(name="pkt_len_stat_1025_to_1514_res_delta_level3")
    private Integer pktLenStat1025To1514ResDeltaLevel3;
    @Column(name="pkt_len_stat_1025_to_1514_res_delta_level4")
    private Integer pktLenStat1025To1514ResDeltaLevel4;
    @Column(name="pkt_len_stat_1025_to_1514_res_delta_level5")
    private Integer pktLenStat1025To1514ResDeltaLevel5;

    @Column(name="pkt_len_stat_jumbo_res_delta_use")
    private Boolean pktLenStatJumboResDeltaUse;
    @Column(name="pkt_len_stat_jumbo_res_delta_level1")
    private Integer pktLenStatJumboResDeltaLevel1;
    @Column(name="pkt_len_stat_jumbo_res_delta_level2")
    private Integer pktLenStatJumboResDeltaLevel2;
    @Column(name="pkt_len_stat_jumbo_res_delta_level3")
    private Integer pktLenStatJumboResDeltaLevel3;
    @Column(name="pkt_len_stat_jumbo_res_delta_level4")
    private Integer pktLenStatJumboResDeltaLevel4;
    @Column(name="pkt_len_stat_jumbo_res_delta_level5")
    private Integer pktLenStatJumboResDeltaLevel5;


}
