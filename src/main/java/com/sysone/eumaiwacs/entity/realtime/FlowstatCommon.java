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
@Table(name="tbl_eum_rad_flowstat_common")
public class FlowstatCommon {

    @Id
    private FlowstatCommonKey flowstatCommonKey;

    @Column(name="ts_first_req")
    private BigDecimal tsFirstReq;
    @Column(name="ts_first_res")
    private BigDecimal tsFirstRes;
    @Column(name="ts_last_req")
    private BigDecimal tsLastReq;
    @Column(name="ts_last_res")
    private BigDecimal tsLastRes;

    @Column(name="len_req_tot")
    private BigInteger lenReqTot;
    @Column(name="len_res_tot")
    private BigInteger lenResTot;
    @Column(name="pkts_req_tot")
    private BigInteger pktsReqTot;
    @Column(name="pkts_res_tot")
    private BigInteger pktsResTot;

    @Column(name="len_req_per_sec")
    private BigDecimal lenReqPerSec;
    @Column(name="len_res_per_sec")
    private BigDecimal lenResPerSec;
    @Column(name="pkts_req_per_sec")
    private BigDecimal pktsReqPerSec;
    @Column(name="pkts_res_per_sec")
    private BigDecimal pktsResPerSec;

    @Column(name="len_req_delta")
    private BigInteger lenReqDelta;
    @Column(name="len_res_delta")
    private BigInteger lenResDelta;
    @Column(name="pkts_req_delta")
    private BigInteger pktsReqDelta;
    @Column(name="pkts_res_delta")
    private BigInteger pktsResDelta;

    @Column(name="pkt_len_min_req")
    private Integer pktLenMinReq;
    @Column(name="pkt_len_min_res")
    private Integer pktLenMinRes;
    @Column(name="pkt_len_max_req")
    private Integer pktLenMaxReq;
    @Column(name="pkt_len_max_res")
    private Integer pktLenMaxRes;

    @Column(name="pkt_len_stat_1_to_128_req_tot")
    private BigInteger pktLenStat1To128ReqTot;
    @Column(name="pkt_len_stat_129_to_256_req_tot")
    private BigInteger pktLenStat129To256ReqTot;
    @Column(name="pkt_len_stat_257_to_512_req_tot")
    private BigInteger pktLenStat257To512ReqTot;
    @Column(name="pkt_len_stat_513_to_1024_req_tot")
    private BigInteger pktLenStat513To1024ReqTot;
    @Column(name="pkt_len_stat_1025_to_1514_req_tot")
    private BigInteger pktLenStat1025To1514ReqTot;
    @Column(name="pkt_len_stat_jumbo_req_tot")
    private BigInteger pktLenStatJumboReqTot;

    @Column(name="pkt_len_stat_1_to_128_res_tot")
    private BigInteger pktLenStat1To128ResTot;
    @Column(name="pkt_len_stat_129_to_256_res_tot")
    private BigInteger pktLenStat129To256ResTot;
    @Column(name="pkt_len_stat_257_to_512_res_tot")
    private BigInteger pktLenStat257To512ResTot;
    @Column(name="pkt_len_stat_513_to_1024_res_tot")
    private BigInteger pktLenStat513To1024ResTot;
    @Column(name="pkt_len_stat_1025_to_1514_res_tot")
    private BigInteger pktLenStat1025To1514ResTot;
    @Column(name="pkt_len_stat_jumbo_res_tot")
    private BigInteger pktLenStatJumboResTot;

    @Column(name="pkt_len_stat_1_to_128_req_per_sec")
    private BigDecimal pktLenStat1To128ReqPerSec;
    @Column(name="pkt_len_stat_129_to_256_req_per_sec")
    private BigDecimal pktLenStat129To256ReqPerSec;
    @Column(name="pkt_len_stat_257_to_512_req_per_sec")
    private BigDecimal pktLenStat257To512ReqPerSec;
    @Column(name="pkt_len_stat_513_to_1024_req_per_sec")
    private BigDecimal pktLenStat513To1024ReqPerSec;
    @Column(name="pkt_len_stat_1025_to_1514_req_per_sec")
    private BigDecimal pktLenStat1025To1514ReqPerSec;
    @Column(name="pkt_len_stat_jumbo_req_per_sec")
    private BigDecimal pktLenStatJumboReqPerSec;

    @Column(name="pkt_len_stat_1_to_128_res_per_sec")
    private BigDecimal pktLenStat1To128ResPerSec;
    @Column(name="pkt_len_stat_129_to_256_res_per_sec")
    private BigDecimal pktLenStat129To256ResPerSec;
    @Column(name="pkt_len_stat_257_to_512_res_per_sec")
    private BigDecimal pktLenStat257To512ResPerSec;
    @Column(name="pkt_len_stat_513_to_1024_res_per_sec")
    private BigDecimal pktLenStat513To1024ResPerSec;
    @Column(name="pkt_len_stat_1025_to_1514_res_per_sec")
    private BigDecimal pktLenStat1025To1514ResPerSec;
    @Column(name="pkt_len_stat_jumbo_res_per_sec")
    private BigDecimal pktLenStatJumboResPerSec;

    @Column(name="pkt_len_stat_1_to_128_req_delta")
    private BigInteger pktLenStat1To128ReqDelta;
    @Column(name="pkt_len_stat_129_to_256_req_delta")
    private BigInteger pktLenStat129To256ReqDelta;
    @Column(name="pkt_len_stat_257_to_512_req_delta")
    private BigInteger pktLenStat257To512ReqDelta;
    @Column(name="pkt_len_stat_513_to_1024_req_delta")
    private BigInteger pktLenStat513To1024ReqDelta;
    @Column(name="pkt_len_stat_1025_to_1514_req_delta")
    private BigInteger pktLenStat1025To1514ReqDelta;
    @Column(name="pkt_len_stat_jumbo_req_delta")
    private BigInteger pktLenStatJumboReqDelta;

    @Column(name="pkt_len_stat_1_to_128_res_delta")
    private BigInteger pktLenStat1To128ResDelta;
    @Column(name="pkt_len_stat_129_to_256_res_delta")
    private BigInteger pktLenStat129To256ResDelta;
    @Column(name="pkt_len_stat_257_to_512_res_delta")
    private BigInteger pktLenStat257To512ResDelta;
    @Column(name="pkt_len_stat_513_to_1024_res_delta")
    private BigInteger pktLenStat513To1024ResDelta;
    @Column(name="pkt_len_stat_1025_to_1514_res_delta")
    private BigInteger pktLenStat1025To1514ResDelta;
    @Column(name="pkt_len_stat_jumbo_res_delta")
    private BigInteger pktLenStatJumboResDelta;

    @Column(name="l2_proto")
    private BigInteger l2Proto;
    @Column(name="l3_proto")
    private BigInteger l3Proto;
    @Column(name="l4_proto")
    private BigInteger l4Proto;

    @Column(name="tunneled_src_mac")
    private String tunneledSrcMac;
    @Column(name="tunneled_dst_mac")
    private String tunneledDstMac;
    @Column(name="tunneled_src_ip")
    private String tunneledSrcIp;
    @Column(name="tunneled_dst_ip")
    private String tunneledDstIp;

    @Column(name="tunneled_ip_version")
    private Integer tunneledIpVersion;
    @Column(name="tunneled_src_port")
    private Integer tunneledSrcPort;
    @Column(name="tunneled_dst_port")
    private Integer tunneledDstPort;
    @Column(name="tunneled_proto_outer")
    private BigInteger tunneledProtoOuter;
}
