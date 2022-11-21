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
@Table(name="tbl_mapping_transaction_cookie")
public class MappingTransactionCookie {

    @Id
    @SequenceGenerator(name="tbl_mapping_transaction_cookie_seq", sequenceName="tbl_mapping_transaction_cookie_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_transaction_cookie_seq")
    @Column(name="mapping_id", unique=true, nullable=false)
    private Integer mappingId;

    @Column(name="url_id")
    private Integer urlId;

    @Column(name="is_value_pattern")
    private Boolean isValuePattern;

    @Column(name="http_cookie_key")
    private String httpCookieKey;

    @Column(name="http_cookie_value")
    private String httpCookieValue;

    @Column(name="write_http_cookie")
    private Boolean writeHttpCookie;
}
