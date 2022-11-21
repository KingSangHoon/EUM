package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table (name = "tbl_mapping_critical_value")
public class MappingCriticalValue {

	@Id
	@SequenceGenerator(name="tbl_mapping_critical_value_seq", sequenceName="tbl_mapping_critical_value_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_critical_value_seq")
   	@Column(name = "id")
	private Integer id;

   	@Column(name = "default_id")
	private Integer defaultId;

   	@Column(name = "resource_type")
	private Integer resourceType;

   	@Column(name = "resource_info")
	private Integer resourceInfo;

   	@Column(name = "resource_warning")
	private Integer resourceWarning;

   	@Column(name = "resource_danger")
	private Integer resourceDanger;

   	@Column(name = "resource_fatal")
	private Integer resourceFatal;

	@Column(name = "resource_info_duration")
	private Integer resourceInfoDuration;

	@Column(name = "resource_warning_duration")
	private Integer resourceWarningDuration;

	@Column(name = "resource_danger_duration")
	private Integer resourceDangerDuration;

	@Column(name = "resource_fatal_duration")
	private Integer resourceFatalDuration;
}
