package com.sysone.eumaiwacs.entity.grid;

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
@Table(name="tbl_info_grid_group")
public class GridGroup {

    @Id
    @SequenceGenerator(name="tbl_info_grid_group_seq", sequenceName="tbl_info_grid_group_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_grid_group_seq")
    @Column(name="grid_group_id", unique=true, nullable=false)
    private Integer gridGroupId;

    @Column(name="grid_group_name")
    private String gridGroupName;

    @Column(name="description")
    private String description;

    @Column(name="reg_date")
    private LocalDateTime regDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;

    @Column(name="first_writer")
    private String firstWriter;

    @Column(name="last_writer")
    private String lastWriter;
}
