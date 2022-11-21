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
@Table(name="tbl_info_domestic_sub2")
public class Test2 {

    @Id
    @SequenceGenerator(name="tbl_info_domestic_sub2_seq", sequenceName="tbl_info_domestic_sub2_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_domestic_sub2_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="primary_id")
    private Integer primaryId;

    @Column(name="sub1_id")
    private Integer sub1Id;

    @Column(name="name")
    private String name;

    public Integer getId(){ return id; }

    public Integer getPrimaryId(){ return primaryId; }

    public String getName(){ return Util.latin1ToUtf8(name); }

}
