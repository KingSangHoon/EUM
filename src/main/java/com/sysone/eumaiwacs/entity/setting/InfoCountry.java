package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tbl_info_country")
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
public class InfoCountry {

    @Id
    @SequenceGenerator(name="tbl_info_country_seq",sequenceName="tbl_info_country_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_info_country_seq")
    @Column(name="country_id")
    private Integer countryId;

    @Column(name="country_code")
    private String countryCode;

    @Column(name="country_name")
    private String countryName;

    @Column(name="continent_name")
    private String continentName;

    @Column(name="continent_code")
    private String continentCode;

    @Column(name="modify_flag")
    private Boolean modifyFlag;

    @Column(name="geo_x")
    private String geoX;

    @Column(name="geo_y")
    private String geoY;

}