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
@Table(name="tbl_audit_history")
public class AuditHistory {

    @Id
    @SequenceGenerator(name="tbl_audit_history_seq", sequenceName="tbl_audit_history_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_audit_history_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="user_id")
    private Integer userId;
    @Column(name="login_id")
    private String loginId;

    @Column(name="action")
    private String action;

    @Column(name="menu_depth1")
    private String menuDepth1;
    @Column(name="menu_depth2")
    private String menuDepth2;
    @Column(name="menu_depth3")
    private String menuDepth3;
    @Column(name="menu_depth4")
    private String menuDepth4;

    @Column(name="target")
    private String target;

    @Column(name="page_url")
    private String pageUrl;
    @Column(name="action_url")
    private String actionUrl;

    @Column(name="date")
    private LocalDateTime date;
    @Column(name="ip")
    private String ip;
}
