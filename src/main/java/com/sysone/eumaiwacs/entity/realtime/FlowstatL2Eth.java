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
@Table(name="tbl_eum_rad_flowstat_l2_eth")
public class FlowstatL2Eth {

    @Id
    private FlowstatL2EthKey flowstatL2EthKey;

    @Column(name="src_mac")
    private String srcMac;

    @Column(name="dst_mac")
    private String dstMac;

    @Column(name="vlan_id")
    private Integer vlanId;

    @Column(name="ethertype")
    private String ethertype;

}
