package com.sysone.eumaiwacs.entity.realtime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="tbl_eum_rad_flowstat_l4_udp")
public class FlowstatL4Udp {

    @Id
    private FlowstatL4UdpKey flowstatL4UdpKey;

    @Column(name="src_port")
    private Integer srcPort;
    @Column(name="dst_port")
    private Integer dstPort;

    @Column(name="len_pdu_req_tot")
    private Integer lenPduReqTot;
    @Column(name="len_pdu_res_tot")
    private Integer lenPduResTot;

    @Column(name="pkts_pdu_req_tot")
    private Integer pktsPduReqTot;
    @Column(name="pkts_pdu_res_tot")
    private Integer pktsPduResTot;

    @Column(name="len_pdu_req_per_sec")
    private Integer lenPduReqPerSec;
    @Column(name="len_pdu_res_per_sec")
    private Integer lenPduResPerSec;

    @Column(name="pkts_pdu_req_per_sec")
    private Integer pktsPduReqPerSec;
    @Column(name="pkts_pdu_res_per_sec")
    private Integer pktsPduResPerSec;

    @Column(name="len_pdu_req_delta")
    private Integer lenPduReqDelta;
    @Column(name="len_pdu_res_delta")
    private Integer lenPduResDelta;

    @Column(name="pkts_pdu_req_delta")
    private Integer pktsPduReqDelta;
    @Column(name="pkts_pdu_res_delta")
    private Integer pktsPduResDelta;

    @Column(name="l7proto")
    private Integer l7proto;

    @Column(name="application_id")
    private Integer applicationId;
    @Column(name="application_rip_id")
    private Integer applicationRipId;
    @Column(name="application_vip_id")
    private Integer applicationVipId;
    @Column(name="application_name")
    private String applicationName;
    @Column(name="application_rip_begin")
    private String applicationRipBegin;
    @Column(name="application_rip_end")
    private String applicationRipEnd;
    @Column(name="application_rport_begin")
    private Integer applicationRportBegin;
    @Column(name="application_rport_end")
    private Integer applicationRportEnd;
    @Column(name="application_vip")
    private String applicationVip;
    @Column(name="application_vport")
    private Integer applicationVport;
    @Column(name="application_vhost_id")
    private Integer applicationVhostId;
    @Column(name="application_vhost")
    private String applicationVhost;
    @Column(name="application_direction")
    private Integer applicationDirection;
}
