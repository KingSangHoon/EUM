package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tbl_info_continent")
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
public class InfoContinent {

    @Id
    @SequenceGenerator(name="tbl_info_continent_seq", sequenceName="tbl_info_continent_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_continent_seq")
    @Column(name="continent_id",unique=true,nullable=false)
    private Integer continentId;
    @Column(name="continent_code")
    private String continentCode;
    @Column(name="continent_name")
    private String continentName;
    @Column(name="modify_flag")
    private Boolean modifyFlag;
}
