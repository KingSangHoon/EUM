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
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table(name="tbl_info_domestic_primary")
public class Test {

    @Id
    @SequenceGenerator(name="tbl_info_domestic_primary_seq", sequenceName="tbl_info_domestic_primary_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_domestic_primary_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_id")
    private List<Test1> sub1List = new ArrayList<Test1>();

    public Integer getId(){ return id; }

    public String getName(){ return Util.latin1ToUtf8(name); }

    public List<Test1> getSub1List(){ return sub1List; }

}
