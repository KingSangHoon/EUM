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
@Table(name="tbl_mapping_transaction_uri")
public class MappingTransactionUri {

    @Id
    @SequenceGenerator(name="tbl_mapping_transaction_uri_seq", sequenceName="tbl_mapping_transaction_uri_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_transaction_uri_seq")
    @Column(name="mapping_id", unique=true, nullable=false)
    private Integer mappingId;

    @Column(name="url_id")
    private Integer urlId;

    @Column(name="uri")
    private String uri;
}
