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
@Table (name = "tbl_mapping_band_rule_ip_port_mac")
public class MappingBandRuleIpPortMac {

	@Id
	@SequenceGenerator(name="tbl_mapping_band_rule_ip_port_mac_seq", sequenceName="tbl_mapping_band_rule_ip_port_mac_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_band_rule_ip_port_mac_seq")
   	@Column(name = "id")
	private Integer id;

   	@Column(name = "rule_id")
	private Integer ruleId;

   	@Column(name = "ip_id")
	private Integer ipId;

   	@Column(name = "mac_id")
	private Integer macId;

   	@Column(name = "port_id")
	private Integer portId;


}
