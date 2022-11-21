package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tbl_mapping_domestic_geo")
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
public class MappingDomesticGeo {

    @Id
    @SequenceGenerator(name="tbl_mapping_domestic_geo_seq", sequenceName="tbl_mapping_domestic_geo_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="tbl_mapping_domestic_geo_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="custom_primary_id")
    private Integer customPrimaryId;

    @Column(name="custom_sub1_id")
    private Integer customSub1Id;

    @Column(name="custom_sub2_id")
    private Integer customSub2Id;

    @Column(name="primary_id")
    private Integer primaryId;

    @Column(name="sub1_id")
    private Integer sub1Id;

    @Column(name="sub2_id")
    private Integer sub2Id;

}
