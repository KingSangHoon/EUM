package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table(name="tbl_info_domestic_primary")
public class InfoDomesticPrimary {

    @Id
    @SequenceGenerator(name="tbl_info_domestic_primary_seq", sequenceName="tbl_info_domestic_primary_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_domestic_primary_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="modify_flag")
    private Boolean modifyFlag;

    @Column(name="geo_x")
    private String geoX;

    @Column(name="geo_y")
    private String geoY;

    @Column(name="geo_key")
    private String geoKey;
}
