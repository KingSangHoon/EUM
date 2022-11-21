package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table (name = "tbl_mapping_application_vip_rip")
public class MappingApplicationVipRip {

	@Id
	@SequenceGenerator(name="tbl_mapping_application_vip_rip_seq", sequenceName="tbl_mapping_application_vip_rip_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_application_vip_rip_seq")
   	@Column(name = "mapping_id")
	private Integer mappingId;

   	@Column(name = "application_id")
	private Integer applicationId;

   	@Column(name = "rip_id")
	private Integer ripId;

   	@Column(name = "vip_id")
	private Integer vipId;

}
