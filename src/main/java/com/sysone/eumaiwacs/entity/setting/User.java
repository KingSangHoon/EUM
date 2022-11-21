package com.sysone.eumaiwacs.entity.setting;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_user")
public class User {

    @Id
    @SequenceGenerator(name="tbl_user_seq", sequenceName="tbl_user_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_user_seq")
    @Column(name="user_id", unique=true, nullable=false)
    private Integer userId;
    @Column(name="company_id", nullable=false)
    private Integer companyId;
    @Column(name="login_id", nullable=false)
    private String loginId;
    @Column(name="user_name")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password", nullable=false)
    @JsonIgnore
    private String password;

    @Column(name="role")
    private String role;
    @Column(name="lang")
    private String lang;
    @Column(name="level")
    private String level;
    @Column(name="reg_date")
    private LocalDateTime regDate;
    @Column(name="modify_date")
    private LocalDateTime modifyDate;
    @Column(name="active")
    private Boolean active;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="deleted")
    private Boolean deleted;

}
