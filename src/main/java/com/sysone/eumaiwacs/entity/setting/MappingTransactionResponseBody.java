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
@Table(name="tbl_mapping_transaction_response_body")
public class MappingTransactionResponseBody {

    @Id
    @SequenceGenerator(name="tbl_mapping_transaction_response_body_seq", sequenceName="tbl_mapping_transaction_response_body_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_transaction_response_body_seq")
    @Column(name="mapping_id", unique=true, nullable=false)
    private Integer mappingId;

    @Column(name="url_id")
    private Integer urlId;

    @Column(name="is_value_pattern")
    private Boolean isValuePattern;

    @Column(name="http_response_body_key")
    private String httpResponseBodyKey;

    @Column(name="http_response_body_value")
    private String httpResponseBodyValue;

    @Column(name="write_http_response_body")
    private Boolean writeHttpCookie;

    @Column(name="custom_program_filename")
    private String customProgramFilename;
}
