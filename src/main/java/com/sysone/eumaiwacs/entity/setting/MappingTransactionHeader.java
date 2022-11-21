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
@Table(name="tbl_mapping_transaction_header")
public class MappingTransactionHeader {

    @Id
    @SequenceGenerator(name="tbl_mapping_transaction_header_seq", sequenceName="tbl_mapping_transaction_header_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_transaction_header_seq")
    @Column(name="mapping_id", unique=true, nullable=false)
    private Integer mappingId;

    @Column(name="url_id")
    private Integer urlId;

    @Column(name="header_type")
    private String headerType;

    @Column(name="type")
    private Integer type;

    @Column(name="is_value_pattern")
    private Boolean isValuePattern;

    @Column(name="http_header_name")
    private String httpHeaderName;

    @Column(name="http_header_key")
    private String httpHeaderKey;

    @Column(name="http_header_value")
    private String httpHeaderValue;

    @Column(name="http_header_separate")
    private String httpHeaderSeparate;

    @Column(name="write_http_header")
    private Boolean writeHttpHeader;

}
