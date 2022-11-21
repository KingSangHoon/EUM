package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table(name="tbl_ndpi_protocol")
public class NdpiProtocol {

    @Id
    @SequenceGenerator(name="tbl_ndpi_protocol_seq",sequenceName="tbl_ndpi_protocol_seq",allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_ndpi_protocol_seq")
    @Column(name="id",unique=true,nullable=false)
    private Integer id;

    @Column(name="ndpi_protocol_number")
    private Integer ndpiProtocolNumber;

    @Column(name="ndpi_protocol_name")
    private String ndpiProtocolName;

    @Column(name="ndpi_protocol_name_code")
    private String ndpiProtocolNameCode;

    @Column(name="update_date")
    private LocalDateTime updateDate;
}
