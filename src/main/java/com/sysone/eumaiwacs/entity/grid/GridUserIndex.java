package com.sysone.eumaiwacs.entity.grid;

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
@Table(name="tbl_info_grid_user_index")
public class GridUserIndex {

    @Id
    @Column(name="user_id", unique=true, nullable=false)
    private Integer userId;

    @Column(name="login_id")
    private Integer loginId;

    @Column(name="user_name")
    private Integer username;

    @Column(name="email")
    private Integer email;

    @Column(name="phone_number")
    private Integer phoneNumber;

    @Column(name="role")
    private Integer role;

    @Column(name="reg_date")
    private Integer regDate;

    @Column(name="modify_date")
    private Integer modifyDate;

    @Column(name="active")
    private Integer active;

}
