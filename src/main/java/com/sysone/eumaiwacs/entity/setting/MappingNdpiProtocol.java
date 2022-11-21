package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table(name="tbl_mapping_ndpi_protocol")
public class MappingNdpiProtocol {

    @Id
    @SequenceGenerator(name="tbl_mapping_ndpi_protocol_seq",sequenceName="tbl_mapping_ndpi_protocol_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_mapping_ndpi_protocol_seq")
    @Column(name="mapping_id",unique=true,nullable=false)
    private Integer mappingId;

    @Column(name="ndpi_protocol_app_number")
    private Integer ndpiProtocolAppNumber;

    @Column(name="ndpi_protocol_master_number")
    private Integer ndpiProtocolMasterNumber;

    @Column(name="is_white_list")
    private Boolean isWhiteList;

    @Column(name="update_date")
    private LocalDateTime updateDate;

}