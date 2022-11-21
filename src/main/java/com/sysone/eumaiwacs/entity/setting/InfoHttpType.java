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
@Table(name="tbl_info_http_type")
public class InfoHttpType {

    @Id
    @SequenceGenerator(name="tbl_info_http_type_seq", sequenceName="tbl_info_http_type_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_http_type_seq")
    @Column(name="id",unique=true,nullable=false)
    private Integer id;

    @Column(name="type")
    private String type;

    @Column(name="sub_type")
    private String subType;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="description1")
    private String description1;

    @Column(name="description2")
    private String description2;
}
