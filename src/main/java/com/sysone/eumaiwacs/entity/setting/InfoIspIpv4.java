package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table(name="tbl_info_isp_ipv4")
public class InfoIspIpv4 {

    @Id
    @SequenceGenerator(name="tbl_info_isp_ipv4_seq", sequenceName="tbl_info_isp_ipv4_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="tbl_info_isp_ipv4_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="name_eng")
    private String nameEng;

    @Column(name="modify_flag")
    private Boolean modifyFlag;

    @Column(name="last_update_date")
    private LocalDateTime lastUpdateDate;
}
