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
@Table(name="tbl_info_threshold_l4_udp")
public class InfoThresholdL4Udp {

    @Id
    @SequenceGenerator(name="tbl_info_threshold_l4_udp_seq", sequenceName="tbl_info_threshold_l4_udp_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_threshold_l4_udp_seq")
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

    @Column(name="len_pdu_req_tot_use")
    private Boolean lenPduReqTotUse;
    @Column(name="len_pdu_req_tot_level1")
    private Integer lenPduReqTotLevel1;
    @Column(name="len_pdu_req_tot_level2")
    private Integer lenPduReqTotLevel2;
    @Column(name="len_pdu_req_tot_level3")
    private Integer lenPduReqTotLevel3;
    @Column(name="len_pdu_req_tot_level4")
    private Integer lenPduReqTotLevel4;
    @Column(name="len_pdu_req_tot_level5")
    private Integer lenPduReqTotLevel5;

    @Column(name="len_pdu_res_tot_use")
    private Boolean lenPduResTotUse;
    @Column(name="len_pdu_res_tot_level1")
    private Integer lenPduResTotLevel1;
    @Column(name="len_pdu_res_tot_level2")
    private Integer lenPduResTotLevel2;
    @Column(name="len_pdu_res_tot_level3")
    private Integer lenPduResTotLevel3;
    @Column(name="len_pdu_res_tot_level4")
    private Integer lenPduResTotLevel4;
    @Column(name="len_pdu_res_tot_level5")
    private Integer lenPduResTotLevel5;

    @Column(name="pkts_pdu_req_tot_use")
    private Boolean pktsPduReqTotUse;
    @Column(name="pkts_pdu_req_tot_level1")
    private Integer pktsPduReqTotLevel1;
    @Column(name="pkts_pdu_req_tot_level2")
    private Integer pktsPduReqTotLevel2;
    @Column(name="pkts_pdu_req_tot_level3")
    private Integer pktsPduReqTotLevel3;
    @Column(name="pkts_pdu_req_tot_level4")
    private Integer pktsPduReqTotLevel4;
    @Column(name="pkts_pdu_req_tot_level5")
    private Integer pktsPduReqTotLevel5;

    @Column(name="pkts_pdu_res_tot_use")
    private Boolean pktsPduResTotUse;
    @Column(name="pkts_pdu_res_tot_level1")
    private Integer pktsPduResTotLevel1;
    @Column(name="pkts_pdu_res_tot_level2")
    private Integer pktsPduResTotLevel2;
    @Column(name="pkts_pdu_res_tot_level3")
    private Integer pktsPduResTotLevel3;
    @Column(name="pkts_pdu_res_tot_level4")
    private Integer pktsPduResTotLevel4;
    @Column(name="pkts_pdu_res_tot_level5")
    private Integer pktsPduResTotLevel5;

    @Column(name="len_pdu_req_per_sec_use")
    private Boolean lenPduReqPerSecUse;
    @Column(name="len_pdu_req_per_sec_level1")
    private Integer lenPduReqPerSecLevel1;
    @Column(name="len_pdu_req_per_sec_level2")
    private Integer lenPduReqPerSecLevel2;
    @Column(name="len_pdu_req_per_sec_level3")
    private Integer lenPduReqPerSecLevel3;
    @Column(name="len_pdu_req_per_sec_level4")
    private Integer lenPduReqPerSecLevel4;
    @Column(name="len_pdu_req_per_sec_level5")
    private Integer lenPduReqPerSecLevel5;

    @Column(name="len_pdu_res_per_sec_use")
    private Boolean lenPduResPerSecUse;
    @Column(name="len_pdu_res_per_sec_level1")
    private Integer lenPduResPerSecLevel1;
    @Column(name="len_pdu_res_per_sec_level2")
    private Integer lenPduResPerSecLevel2;
    @Column(name="len_pdu_res_per_sec_level3")
    private Integer lenPduResPerSecLevel3;
    @Column(name="len_pdu_res_per_sec_level4")
    private Integer lenPduResPerSecLevel4;
    @Column(name="len_pdu_res_per_sec_level5")
    private Integer lenPduResPerSecLevel5;

    @Column(name="pkts_pdu_req_per_sec_use")
    private Boolean pktsPduReqPerSecUse;
    @Column(name="pkts_pdu_req_per_sec_level1")
    private Integer pktsPduReqPerSecLevel1;
    @Column(name="pkts_pdu_req_per_sec_level2")
    private Integer pktsPduReqPerSecLevel2;
    @Column(name="pkts_pdu_req_per_sec_level3")
    private Integer pktsPduReqPerSecLevel3;
    @Column(name="pkts_pdu_req_per_sec_level4")
    private Integer pktsPduReqPerSecLevel4;
    @Column(name="pkts_pdu_req_per_sec_level5")
    private Integer pktsPduReqPerSecLevel5;

    @Column(name="pkts_pdu_res_per_sec_use")
    private Boolean pktsPduResPerSecUse;
    @Column(name="pkts_pdu_res_per_sec_level1")
    private Integer pktsPduResPerSecLevel1;
    @Column(name="pkts_pdu_res_per_sec_level2")
    private Integer pktsPduResPerSecLevel2;
    @Column(name="pkts_pdu_res_per_sec_level3")
    private Integer pktsPduResPerSecLevel3;
    @Column(name="pkts_pdu_res_per_sec_level4")
    private Integer pktsPduResPerSecLevel4;
    @Column(name="pkts_pdu_res_per_sec_level5")
    private Integer pktsPduResPerSecLevel5;

    @Column(name="len_pdu_req_delta_use")
    private Boolean lenPduReqDeltaUse;
    @Column(name="len_pdu_req_delta_level1")
    private Integer lenPduReqDeltaLevel1;
    @Column(name="len_pdu_req_delta_level2")
    private Integer lenPduReqDeltaLevel2;
    @Column(name="len_pdu_req_delta_level3")
    private Integer lenPduReqDeltaLevel3;
    @Column(name="len_pdu_req_delta_level4")
    private Integer lenPduReqDeltaLevel4;
    @Column(name="len_pdu_req_delta_level5")
    private Integer lenPduReqDeltaLevel5;

    @Column(name="len_pdu_res_delta_use")
    private Boolean lenPduResDeltaUse;
    @Column(name="len_pdu_res_delta_level1")
    private Integer lenPduResDeltaLevel1;
    @Column(name="len_pdu_res_delta_level2")
    private Integer lenPduResDeltaLevel2;
    @Column(name="len_pdu_res_delta_level3")
    private Integer lenPduResDeltaLevel3;
    @Column(name="len_pdu_res_delta_level4")
    private Integer lenPduResDeltaLevel4;
    @Column(name="len_pdu_res_delta_level5")
    private Integer lenPduResDeltaLevel5;

    @Column(name="pkts_pdu_req_delta_use")
    private Boolean pktsPduReqDeltaUse;
    @Column(name="pkts_pdu_req_delta_level1")
    private Integer pktsPduReqDeltaLevel1;
    @Column(name="pkts_pdu_req_delta_level2")
    private Integer pktsPduReqDeltaLevel2;
    @Column(name="pkts_pdu_req_delta_level3")
    private Integer pktsPduReqDeltaLevel3;
    @Column(name="pkts_pdu_req_delta_level4")
    private Integer pktsPduReqDeltaLevel4;
    @Column(name="pkts_pdu_req_delta_level5")
    private Integer pktsPduReqDeltaLevel5;

    @Column(name="pkts_pdu_res_delta_use")
    private Boolean pktsPduResDeltaUse;
    @Column(name="pkts_pdu_res_delta_level1")
    private Integer pktsPduResDeltaLevel1;
    @Column(name="pkts_pdu_res_delta_level2")
    private Integer pktsPduResDeltaLevel2;
    @Column(name="pkts_pdu_res_delta_level3")
    private Integer pktsPduResDeltaLevel3;
    @Column(name="pkts_pdu_res_delta_level4")
    private Integer pktsPduResDeltaLevel4;
    @Column(name="pkts_pdu_res_delta_level5")
    private Integer pktsPduResDeltaLevel5;
}
