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
@Table(name="tbl_eum_rad_flowstat_l3_ethernet")
public class FlowstatL3Ethernet {

    @Id
    private FlowstatL3EthernetKey flowstatL3EthernetKey;

    @Column(name="ethertype")
    private Integer ethertype;
}
