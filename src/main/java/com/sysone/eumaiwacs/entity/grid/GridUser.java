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
@Table(name="tbl_info_grid_user")
public class GridUser {

    @Id
    @Column(name="user_id", unique=true, nullable=false)
    private Integer userId;

    @Column(name="login_id")
    private Boolean loginId;

    @Column(name="user_name")
    private Boolean username;

    @Column(name="email")
    private Boolean email;

    @Column(name="phone_number")
    private Boolean phoneNumber;

    @Column(name="role")
    private Boolean role;

    @Column(name="reg_date")
    private Boolean regDate;

    @Column(name="modify_date")
    private Boolean modifyDate;

    @Column(name="active")
    private Boolean active;
}
