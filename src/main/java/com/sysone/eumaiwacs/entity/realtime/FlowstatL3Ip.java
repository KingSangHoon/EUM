package com.sysone.eumaiwacs.entity.realtime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="tbl_eum_rad_flowstat_l3_ip")
public class FlowstatL3Ip {

    @Id
    private FlowstatL3IpKey flowstatL3IpKey;

    @Column(name="src_ip")
    private String srcIp;
    @Column(name="dst_ip")
    private String dstIp;
    @Column(name="src_tos")
    private Integer srcTos;
    @Column(name="dst_tos")
    private Integer dstTos;
    @Column(name="ipproto")
    private Integer ipproto;

    @Column(name="frag_pkts_tot_req")
    private BigInteger fragPktsTotReq;
    @Column(name="frag_pkts_tot_res")
    private BigInteger fragPktsTotRes;

    @Column(name="frag_pkts_per_sec_req")
    private BigDecimal fragPktsPerSecReq;
    @Column(name="frag_pkts_per_sec_res")
    private BigDecimal fragPktsPerSecRes;

    @Column(name="frag_pkts_delta_req")
    private BigInteger fragPktsDeltaReq;
    @Column(name="frag_pkts_delta_res")
    private BigInteger fragPktsDeltaRes;

    @Column(name="ttl_min_req")
    private Integer ttlMinReq;
    @Column(name="ttl_min_res")
    private Integer ttlMinRes;
    @Column(name="ttl_max_req")
    private Integer ttlMaxReq;
    @Column(name="ttl_max_res")
    private Integer ttlMaxRes;

    @Column(name="ttl_stat_1_req_tot")
    private BigInteger ttlStat1ReqTot;
    @Column(name="ttl_stat_2_to_5_req_tot")
    private BigInteger ttlStat2To5ReqTot;
    @Column(name="ttl_stat_6_to_32_req_tot")
    private BigInteger ttlStat6To32ReqTot;
    @Column(name="ttl_stat_33_to_64_req_tot")
    private BigInteger ttlStat33To64ReqTot;
    @Column(name="ttl_stat_65_to_96_req_tot")
    private BigInteger ttlStat65To96ReqTot;
    @Column(name="ttl_stat_97_to_128_req_tot")
    private BigInteger ttlStat97To128ReqTot;
    @Column(name="ttl_stat_129_to_160_req_tot")
    private BigInteger ttlStat129To160ReqTot;
    @Column(name="ttl_stat_161_to_192_req_tot")
    private BigInteger ttlStat161To192ReqTot;
    @Column(name="ttl_stat_193_to_224_req_tot")
    private BigInteger ttlStat193To224ReqTot;
    @Column(name="ttl_stat_225_to_255_req_tot")
    private BigInteger ttlStat225To255ReqTot;

    @Column(name="ttl_stat_1_res_tot")
    private BigInteger ttlStat1ResTot;
    @Column(name="ttl_stat_2_to_5_res_tot")
    private BigInteger ttlStat2To5ResTot;
    @Column(name="ttl_stat_6_to_32_res_tot")
    private BigInteger ttlStat6To32ResTot;
    @Column(name="ttl_stat_33_to_64_res_tot")
    private BigInteger ttlStat33To64ResTot;
    @Column(name="ttl_stat_65_to_96_res_tot")
    private BigInteger ttlStat65To96ResTot;
    @Column(name="ttl_stat_97_to_128_res_tot")
    private BigInteger ttlStat97To128ResTot;
    @Column(name="ttl_stat_129_to_160_res_tot")
    private BigInteger ttlStat129To160ResTot;
    @Column(name="ttl_stat_161_to_192_res_tot")
    private BigInteger ttlStat161To192ResTot;
    @Column(name="ttl_stat_193_to_224_res_tot")
    private BigInteger ttlStat193To224ResTot;
    @Column(name="ttl_stat_225_to_255_res_tot")
    private BigInteger ttlStat225To255ResTot;

    @Column(name="ttl_stat_1_req_per_sec")
    private BigDecimal ttlStat1ReqPerSec;
    @Column(name="ttl_stat_2_to_5_req_per_sec")
    private BigDecimal ttlStat2To5ReqPerSec;
    @Column(name="ttl_stat_6_to_32_req_per_sec")
    private BigDecimal ttlStat6To32ReqPerSec;
    @Column(name="ttl_stat_33_to_64_req_per_sec")
    private BigDecimal ttlStat33To64ReqPerSec;
    @Column(name="ttl_stat_65_to_96_req_per_sec")
    private BigDecimal ttlStat65To96ReqPerSec;
    @Column(name="ttl_stat_97_to_128_req_per_sec")
    private BigDecimal ttlStat97To128ReqPerSec;
    @Column(name="ttl_stat_129_to_160_req_per_sec")
    private BigDecimal ttlStat129To160ReqPerSec;
    @Column(name="ttl_stat_161_to_192_req_per_sec")
    private BigDecimal ttlStat161To192ReqPerSec;
    @Column(name="ttl_stat_193_to_224_req_per_sec")
    private BigDecimal ttlStat193To224ReqPerSec;
    @Column(name="ttl_stat_225_to_255_req_per_sec")
    private BigDecimal ttlStat225To255ReqPerSec;

    @Column(name="ttl_stat_1_res_per_sec")
    private BigDecimal ttlStat1ResPerSec;
    @Column(name="ttl_stat_2_to_5_res_per_sec")
    private BigDecimal ttlStat2To5ResPerSec;
    @Column(name="ttl_stat_6_to_32_res_per_sec")
    private BigDecimal ttlStat6To32ResPerSec;
    @Column(name="ttl_stat_33_to_64_res_per_sec")
    private BigDecimal ttlStat33To64ResPerSec;
    @Column(name="ttl_stat_65_to_96_res_per_sec")
    private BigDecimal ttlStat65To96ResPerSec;
    @Column(name="ttl_stat_97_to_128_res_per_sec")
    private BigDecimal ttlStat97To128ResPerSec;
    @Column(name="ttl_stat_129_to_160_res_per_sec")
    private BigDecimal ttlStat129To160ResPerSec;
    @Column(name="ttl_stat_161_to_192_res_per_sec")
    private BigDecimal ttlStat161To192ResPerSec;
    @Column(name="ttl_stat_193_to_224_res_per_sec")
    private BigDecimal ttlStat193To224ResPerSec;
    @Column(name="ttl_stat_225_to_255_res_per_sec")
    private BigDecimal ttlStat225To255ResPerSec;

    @Column(name="ttl_stat_1_req_delta")
    private BigInteger ttlStat1ReqDelta;
    @Column(name="ttl_stat_2_to_5_req_delta")
    private BigInteger ttlStat2To5ReqDelta;
    @Column(name="ttl_stat_6_to_32_req_delta")
    private BigInteger ttlStat6To32ReqDelta;
    @Column(name="ttl_stat_33_to_64_req_delta")
    private BigInteger ttlStat33To64ReqDelta;
    @Column(name="ttl_stat_65_to_96_req_delta")
    private BigInteger ttlStat65To96ReqDelta;
    @Column(name="ttl_stat_97_to_128_req_delta")
    private BigInteger ttlStat97To128ReqDelta;
    @Column(name="ttl_stat_129_to_160_req_delta")
    private BigInteger ttlStat129To160ReqDelta;
    @Column(name="ttl_stat_161_to_192_req_delta")
    private BigInteger ttlStat161To192ReqDelta;
    @Column(name="ttl_stat_193_to_224_req_delta")
    private BigInteger ttlStat193To224ReqDelta;
    @Column(name="ttl_stat_225_to_255_req_delta")
    private BigInteger ttlStat225To255ReqDelta;

    @Column(name="ttl_stat_1_res_delta")
    private BigInteger ttlStat1ResDelta;
    @Column(name="ttl_stat_2_to_5_res_delta")
    private BigInteger ttlStat2To5ResDelta;
    @Column(name="ttl_stat_6_to_32_res_delta")
    private BigInteger ttlStat6To32ResDelta;
    @Column(name="ttl_stat_33_to_64_res_delta")
    private BigInteger ttlStat33To64ResDelta;
    @Column(name="ttl_stat_65_to_96_res_delta")
    private BigInteger ttlStat65To96ResDelta;
    @Column(name="ttl_stat_97_to_128_res_delta")
    private BigInteger ttlStat97To128ResDelta;
    @Column(name="ttl_stat_129_to_160_res_delta")
    private BigInteger ttlStat129To160ResDelta;
    @Column(name="ttl_stat_161_to_192_res_delta")
    private BigInteger ttlStat161To192ResDelta;
    @Column(name="ttl_stat_193_to_224_res_delta")
    private BigInteger ttlStat193To224ResDelta;
    @Column(name="ttl_stat_225_to_255_res_delta")
    private BigInteger ttlStat225To255ResDelta;

    @Column(name="overlap_cnt_req_tot")
    private BigInteger overlapCntReqTot;
    @Column(name="overlap_cnt_res_tot")
    private BigInteger overlapCntResTot;
    @Column(name="overlap_len_req_tot")
    private BigInteger overlapLenReqTot;
    @Column(name="overlap_len_res_tot")
    private BigInteger overlapLenResTot;

    @Column(name="overlap_attack_cnt_req_tot")
    private BigInteger overlapAttackCntReqTot;
    @Column(name="overlap_attack_cnt_res_tot")
    private BigInteger overlapAttackCntResTot;
    @Column(name="overlap_attack_len_req_tot")
    private BigInteger overlapAttackLenReqTot;
    @Column(name="overlap_attack_len_res_tot")
    private BigInteger overlapAttackLenResTot;

    @Column(name="overlap_cnt_req_per_sec")
    private BigDecimal overlapCntReqPerSec;
    @Column(name="overlap_cnt_res_per_sec")
    private BigDecimal overlapCntResPerSec;
    @Column(name="overlap_len_req_per_sec")
    private BigDecimal overlapLenReqPerSec;
    @Column(name="overlap_len_res_per_sec")
    private BigDecimal overlapLenResPerSec;

    @Column(name="overlap_attack_cnt_req_per_sec")
    private BigDecimal overlapAttackCntReqPerSec;
    @Column(name="overlap_attack_cnt_res_per_sec")
    private BigDecimal overlapAttackCntResPerSec;
    @Column(name="overlap_attack_len_req_per_sec")
    private BigDecimal overlapAttackLenReqPerSec;
    @Column(name="overlap_attack_len_res_per_sec")
    private BigDecimal overlapAttackLenResPerSec;

    @Column(name="overlap_cnt_req_delta")
    private BigInteger overlapCntReqDelta;
    @Column(name="overlap_cnt_res_delta")
    private BigInteger overlapCntResDelta;
    @Column(name="overlap_len_req_delta")
    private BigInteger overlapLenReqDelta;
    @Column(name="overlap_len_res_delta")
    private BigInteger overlapLenResDelta;

    @Column(name="overlap_attack_cnt_req_delta")
    private BigInteger overlapAttackCntReqDelta;
    @Column(name="overlap_attack_cnt_res_delta")
    private BigInteger overlapAttackCntResDelta;
    @Column(name="overlap_attack_len_req_delta")
    private BigInteger overlapAttackLenReqDelta;
    @Column(name="overlap_attack_len_res_delta")
    private BigInteger overlapAttackLenResDelta;

    @Column(name="ndpi_protocol_app")
    private String ndpiProtocolApp;
    @Column(name="ndpi_protocol_master")
    private String ndpiProtocolMaster;
    @Column(name="ndpi_is_guessed")
    private Integer ndpiIsGuessed;
    @Column(name="ndpi_protocol_app_id")
    private Integer ndpiProtocolAppId;
    @Column(name="ndpi_protocol_master_id")
    private Integer ndpiProtocolMasterId;
    @Column(name="ndpi_protocol_is_white_list")
    private Boolean ndpiProtocolIsWhiteList;

    @Column(name="geoip_id_req")
    private Integer geoipIdReq;
    @Column(name="geoip_id_res")
    private Integer geoipIdRes;

    @Column(name="country_id_req")
    private Integer countryIdReq;
    @Column(name="country_id_res")
    private Integer countryIdRes;

    @Column(name="continent_id_req")
    private Integer continentIdReq;
    @Column(name="continent_id_res")
    private Integer continentIdRes;

    @Column(name="city_id_req")
    private Integer cityIdReq;
    @Column(name="city_id_res")
    private Integer cityIdRes;

    @Column(name="city_country_id_req")
    private Integer cityCountryIdReq;
    @Column(name="city_country_id_res")
    private Integer cityCountryIdRes;

    @Column(name="domestic_primary_id_req")
    private Integer domesticPrimaryIdReq;
    @Column(name="domestic_primary_id_res")
    private Integer domesticPrimaryIdRes;

    @Column(name="domestic_sub1_id_req")
    private Integer domesticSub1IdReq;
    @Column(name="domestic_sub1_id_res")
    private Integer domesticSub1IdRes;

    @Column(name="domestic_sub2_id_req")
    private Integer domesticSub2IdReq;
    @Column(name="domestic_sub2_id_res")
    private Integer domesticSub2IdRes;

    @Column(name="isp_id_req")
    private Integer ispIdReq;
    @Column(name="isp_id_res")
    private Integer ispIdRes;

    @Column(name="idc_id_req")
    private Integer idcIdReq;
    @Column(name="idc_id_res")
    private Integer idcIdRes;

    @Column(name="as_id_req")
    private Integer asIdReq;
    @Column(name="as_id_res")
    private Integer asIdRes;

    @Column(name="country_name_req")
    private String countryNameReq;
    @Column(name="country_name_res")
    private String countryNameRes;

    @Column(name="continent_name_req")
    private String continentNameReq;
    @Column(name="continent_name_res")
    private String continentNameRes;

    @Column(name="city_name_req")
    private String cityNameReq;
    @Column(name="city_name_res")
    private String cityNameRes;

    @Column(name="city_country_name_req")
    private String cityCountryNameReq;
    @Column(name="city_country_name_res")
    private String cityCountryNameRes;

    @Column(name="domestic_latitude_req")
    private String domesticLatitudeReq;
    @Column(name="domestic_latitude_res")
    private String domesticLatitudeRes;

    @Column(name="domestic_longitude_req")
    private String domesticLongitudeReq;
    @Column(name="domestic_longitude_res")
    private String domesticLongitudeRes;

    @Column(name="domestic_primary_name_req")
    private String domesticPrimaryNameReq;
    @Column(name="domestic_primary_name_res")
    private String domesticPrimaryNameRes;

    @Column(name="domestic_sub1_name_req")
    private String domesticSub1NameReq;
    @Column(name="domestic_sub1_name_res")
    private String domesticSub1NameRes;

    @Column(name="domestic_sub2_name_req")
    private String domesticSub2NameReq;
    @Column(name="domestic_sub2_name_res")
    private String domesticSub2NameRes;

    @Column(name="isp_name_req")
    private String ispNameReq;
    @Column(name="isp_name_res")
    private String ispNameRes;

    @Column(name="isp_name_eng_req")
    private String ispNameEngReq;
    @Column(name="isp_name_eng_res")
    private String ispNameEngRes;

    @Column(name="idc_name_req")
    private String idcNameReq;
    @Column(name="idc_name_res")
    private String idcNameRes;

    @Column(name="idc_name_eng_req")
    private String idcNameEngReq;
    @Column(name="idc_name_eng_res")
    private String idcNameEngRes;

    @Column(name="as_number_req")
    private BigInteger asNumberReq;
    @Column(name="as_number_res")
    private BigInteger asNumberRes;

    @Column(name="as_name_req")
    private String asNameReq;
    @Column(name="as_name_res")
    private String asNameRes;

    @Column(name="as_name_eng_req")
    private String asNameEngReq;
    @Column(name="as_name_eng_res")
    private String asNameEngRes;
}
