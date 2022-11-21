package com.sysone.eumaiwacs.entity.setting;

import com.sysone.eumaiwacs.common.Util;
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
@Table(name="tbl_info_domestic_sub1")
public class InfoDomesticSub1 {

    @Id
    @SequenceGenerator(name="tbl_info_domestic_sub1_seq", sequenceName="tbl_info_domestic_sub1_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_domestic_sub1_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="primary_id")
    private Integer primaryId;

    @Column(name="primary_code")
    private String primaryCode;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="modify_flag")
    private Boolean modifyFlag;

    @Column(name="name_var")
    private String nameVar;

    @Column(name="geo_x")
    private String geoX;

    @Column(name="geo_y")
    private String geoY;

    @Column(name="geo_key")
    private String geoKey;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub1_id")
    private List<InfoDomesticSub2> sub2List = new ArrayList<InfoDomesticSub2>();

}
