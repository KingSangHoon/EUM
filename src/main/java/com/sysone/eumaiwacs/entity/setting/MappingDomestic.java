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
@Table(name="tbl_mapping_domestic_server")
public class MappingDomestic {

    @Id
    @SequenceGenerator(name="tbl_mapping_domestic_server_seq", sequenceName="tbl_mapping_domestic_server_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_domestic_server_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="primary_id")
    private Integer primaryId;

    @Column(name="sub1_id")
    private Integer sub1Id;

    @Column(name="sub2_id")
    private Integer sub2Id;

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

    @Column(name="country")
    private String country;

    @Column(name="code")
    private String code;

    @Column(name="net")
    private String net;

    @Column(name="ip_lat")
    private String ip_lat;

    @Column(name="ip_long")
    private String ipLong;

    @Column(name="org")
    private String org;

    @Column(name="primary_name")
    private String primaryName;

    @Column(name="r1_name")
    private String r1Name;

    @Column(name="r2_name")
    private String r2Name;

    @Column(name="type")
    private String type;
}
