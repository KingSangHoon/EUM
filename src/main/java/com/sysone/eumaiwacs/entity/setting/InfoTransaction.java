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
@Table(name="tbl_info_transaction")
public class InfoTransaction {

    @Id
    @SequenceGenerator(name="tbl_info_transaction_seq", sequenceName="tbl_info_transaction_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_transaction_seq")
    @Column(name="url_id", unique=true, nullable=false)
    private Integer urlId;

    @Column(name="url_name")
    private String urlName;

    @Column(name="url_alias")
    private String urlAlias;

    @Column(name="first_writer")
    private String firstWriter;

    @Column(name="last_writer")
    private String lastWriter;

    @Column(name="reg_date")
    private LocalDateTime regDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;

    @Column(name="deleted")
    private Boolean deleted;

    @Column(name="is_pattern")
    private Boolean isPattern;

    @Column(name="is_argument")
    private Boolean isArgument;

    @Column(name="is_argument_pattern")
    private Boolean isArgumentPattern;

    @Column(name="argument")
    private String argument;

    @Column(name="write_http_argument")
    private String writeHttpArgument;

    @Column(name="http_method")
    private String httpMethod;

    @Column(name="http_body_type")
    private Integer httpBodyType;

    @Column(name="is_uri_chk")
    private Boolean isUriChk;

    @Column(name="is_request_header")
    private Boolean isRequestHeader;

    @Column(name="is_response_header")
    private Boolean isResponseHeader;

    @Column(name="is_response_body")
    private Boolean isResponseBody;

    @Column(name="is_cookie")
    private Boolean isCookie;

    @Column(name="transaction_threshold_type")
    private Integer transactionThresholdType;

    @Column(name="transaction_threshold_id")
    private Integer transactionThresholdId;

    @Column(name="uri_threshold_type")
    private Integer uriThresholdType;

    @Column(name="uri_threshold_id")
    private Integer uriThresholdId;

    @Column(name="request_header_alias")
    private String requestHeaderAlias;

    @Column(name="response_header_alias")
    private String responseHeaderAlias;

    @Column(name="response_body_alias")
    private String responseBodyAlias;

    @Column(name="cookie_alias")
    private String cookieAlias;

}
