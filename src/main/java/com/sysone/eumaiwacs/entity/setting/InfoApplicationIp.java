package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table(name = "tbl_info_application_ip")
public class InfoApplicationIp {

    @Id
    @SequenceGenerator(name="tbl_info_application_rip_seq", sequenceName="tbl_info_application_rip_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_application_rip_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "ip_addr")
    private String ipAddr;

    @Column(name = "ip_mac")
    private String ipMac;

    @Column(name = "type")
    private String type;

    @Column(name = "protocol")
    private String protocol;

    @Column(name = "port_type_is_range")
    private String portTypeIsRange;

    @Column(name = "start_port")
    private Integer startPort;

    @Column(name = "end_port")
    private Integer endPort;

    @Column(name = "is_v_host")
    private Boolean isVHost;

    @Column(name = "ip_addr_num")
    private long ipAddrNum;
}
