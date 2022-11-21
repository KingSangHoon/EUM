package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tbl_mapping_continent_country_geo")
@Getter @Setter
@DynamicInsert @DynamicUpdate
@NoArgsConstructor
public class MappingContinentCountryGeo {

    @Id
    @SequenceGenerator(name="tbl_mapping_continent_country_geo_seq", sequenceName="tbl_mapping_continent_country_geo_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_continent_country_geo_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    @Column(name="custom_continent_id")
    private Integer customContinentId;
    @Column(name="custom_country_id")
    private Integer customCountryId;
    @Column(name="continent_id")
    private Integer continentId;
    @Column(name="country_id")
    private Integer countryId;
}
