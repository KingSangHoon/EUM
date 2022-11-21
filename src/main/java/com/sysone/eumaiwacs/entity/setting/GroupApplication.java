package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tbl_group_application")
@Getter
@Setter
@NoArgsConstructor
public class GroupApplication {

    @Id
    @SequenceGenerator(name="tbl_group_application_seq", sequenceName="tbl_group_application_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_group_application_seq")
    @Column(name="group_application_id", unique=true, nullable=false)
    private Integer groupApplicationId;

    @Column(name="application_name")
    private String applicationName;

    @Column(name="reg_date")
    private LocalDateTime regDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;

    @Column(name="deleted")
    private Boolean deleted;


}
