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
@Table(name="tbl_info_geoip_server")
public class InfoGeoipServer {

    @Id
    @SequenceGenerator(name="tbl_info_geoip_server_seq", sequenceName="tbl_info_geoip_server_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_geoip_server_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="country_code")
    private String countryCode;

    @Column(name="country_name")
    private String countryName;

    @Column(name="start_ip")
    private String startIp;

    @Column(name="end_ip")
    private String endIp;

    @Column(name="start_ip_num")
    private Long startIpNum;

    @Column(name="end_ip_num")
    private Long endIpNum;

    @Column(name="modify_flag")
    private Boolean modifyFlag;

}
