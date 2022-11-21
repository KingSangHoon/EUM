package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="tbl_info_mime_type")
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
public class InfoMimeType {

    @Id
    @SequenceGenerator(name="tbl_info_mime_type_seq", sequenceName="tbl_info_mime_type_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_mime_type_seq")
    @Column(name="id",unique=true,nullable=false)
    private Integer id;

    @Column(name="type")
    private String type;

    @Column(name="name")
    private String name;

    @Column(name="template")
    private String template;

}
