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
@Table (name = "tbl_mapping_band_rule")
public class MappingBandRule {

	@Id
	@SequenceGenerator(name="tbl_mapping_band_rule_seq", sequenceName="tbl_mapping_band_rule_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_band_rule_seq")
   	@Column(name = "id")
	private Integer id;

   	@Column(name = "band_id")
	private Integer bandId;

   	@Column(name = "rule_id")
	private Integer ruleId;


}
