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
@Table(name="tbl_ssl_policy")
public class SslPolicy {

    @Id
    @SequenceGenerator(name="tbl_ssl_policy_seq", sequenceName="tbl_ssl_policy_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_ssl_policy_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="start_ip")
    private String startIp;

    @Column(name="end_ip")
    private String endIp;

    @Column(name="start_ip_num")
    private Long startIpNum;

    @Column(name="end_ip_num")
    private Long endIpNum;

    @Column(name="start_port")
    private Integer startPort;

    @Column(name="end_port")
    private Integer endPort;

    @Column(name="password")
    private String password;

    @Column(name="ssl_key_file")
    private String sslKeyFile;

    @Column(name="ssl_key_stored_file")
    private String sslKeyStoredFile;

    @Column(name="reg_date")
    private LocalDateTime regDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;

    @Column(name="first_writer")
    private String firstWriter;

    @Column(name="last_writer")
    private String lastWriter;

    @Column(name="deleted")
    private Boolean deleted;

}
