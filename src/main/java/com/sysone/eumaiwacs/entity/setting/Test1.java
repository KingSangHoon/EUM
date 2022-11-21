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
public class Test1 {

    @Id
    @SequenceGenerator(name="tbl_info_domestic_sub1_seq", sequenceName="tbl_info_domestic_sub1_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_domestic_sub1_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="primary_id")
    private Integer primaryId;

    @Column(name="name")
    private String name;

    @Column(name="name_var")
    private String nameVar;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub1_id")
    private List<Test2> sub2List = new ArrayList<Test2>();

    public Integer getId(){
        return id;
    }

    public Integer getPrimaryId(){
        return primaryId;
    }

    public String getName(){ return Util.latin1ToUtf8(name); }

    public String getNameVar(){ return Util.latin1ToUtf8(nameVar); }

    public List<Test2> getSub2List(){ return sub2List; }

}
