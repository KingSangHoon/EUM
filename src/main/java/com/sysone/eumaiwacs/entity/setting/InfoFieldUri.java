package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="tbl_info_field_uri")
public class InfoFieldUri {

    @Id
    @SequenceGenerator(name="tbl_info_field_uri_seq", sequenceName="tbl_info_field_uri_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_field_uri_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="field_name")
    private String fieldName;

    @Column(name="field_view_name")
    private String fieldViewName;

}
