package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="tbl_info_company")
public class Company {

    @Id
    @SequenceGenerator(name="tbl_info_company_seq", sequenceName="tbl_info_company_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_company_seq")
    @Column(name="company_id", unique=true, nullable=false)
    private Integer companyId;

    @Column(name="company_name")
    private String companyName;

    @Column(name="description")
    private String description;

    @Column(name="reg_date")
    private LocalDateTime regDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;
}
