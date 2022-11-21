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
@Table (name = "tbl_info_band_mac")
public class InfoBandMac {

	@Id
	@SequenceGenerator(name="tbl_info_band_mac_seq", sequenceName="tbl_info_band_mac_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_band_mac_seq")
   	@Column(name = "id")
	private Integer id;

   	@Column(name = "rule_id")
	private Integer ruleId;

   	@Column(name = "is_source_destination")
	private Integer isSourceDestination;

   	@Column(name = "source_mac")
	private String sourceMac;


}
