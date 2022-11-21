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
@Table(name="tbl_info_threshold_used")
public class InfoThresholdUsed {

    @Id
    @SequenceGenerator(name="tbl_info_threshold_used_seq", sequenceName="tbl_info_threshold_used_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_threshold_used_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="type")
    private String type;

    @Column(name="threshold_id")
    private Integer thresholdId;

    @Column(name="field_id")
    private Integer fieldId;
}
