package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table (name = "tbl_info_band_port")
public class InfoBandPort {

	@Id
	@SequenceGenerator(name="tbl_info_band_port_seq", sequenceName="tbl_info_band_port_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_band_port_seq")
   	@Column(name = "id")
	private Integer id;

   	@Column(name = "rule_id")
	private Integer ruleId;

   	@Column(name = "is_source_destination")
	private Integer isSourceDestination;

   	@Column(name = "type")
	private Integer type;

   	@Column(name = "sourceport_start")
	private String sourceportStart;

   	@Column(name = "sourceport_end")
	private String sourceportEnd;


}
