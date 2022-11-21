package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;
import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table (name = "tbl_info_band_rule")
public class InfoBandRule {

	@Id
	@SequenceGenerator(name="tbl_info_band_rule_seq", sequenceName="tbl_info_band_rule_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_band_rule_seq")
   	@Column(name = "rule_id")
	private Integer ruleId;

   	@Column(name = "title")
	private String title;

   	@Column(name = "is_type")
	private Integer isType;

   	@Column(name = "applicationid")
	private Integer applicationid;

   	@Column(name = "applicationid_sub")
	private Integer applicationidSub;

   	@Column(name = "ip_flow")
	private Integer ipFlow;

   	@Column(name = "destinationip_type")
	private Integer destinationipType;

   	@Column(name = "destinationip_start")
	private String destinationipStart;

   	@Column(name = "destinationip_end")
	private String destinationipEnd;

   	@Column(name = "destinationport_type")
	private Integer destinationportType;

   	@Column(name = "destinationport_start")
	private String destinationportStart;

   	@Column(name = "destinationport_end")
	private String destinationportEnd;

   	@Column(name = "mac_flow")
	private Integer macFlow;

   	@Column(name = "destinationmac")
	private String destinationmac;

   	@Column(name = "portocol_type")
	private Integer portocolType;

   	@Column(name = "port_flow")
	private Integer portFlow;

   	@Column(name = "is_isp")
	private Boolean isIsp;

   	@Column(name = "isp_id")
	private String ispId;

   	@Column(name = "is_idc")
	private Boolean isIdc;

   	@Column(name = "idc_id")
	private String idcId;

   	@Column(name = "is_continent")
	private Boolean isContinent;

   	@Column(name = "continent_id")
	private String continentId;

   	@Column(name = "is_country")
	private Boolean isCountry;

   	@Column(name = "country_id")
	private String countryId;

   	@Column(name = "is_domestic")
	private Boolean isDomestic;

   	@Column(name = "primary_id")
	private String primaryId;

   	@Column(name = "sub1_id")
	private String sub1Id;

   	@Column(name = "sub2_id")
	private String sub2Id;

   	@Column(name = "first_writer")
	private String firstWriter;

   	@Column(name = "reg_date")
	private LocalDateTime regDate;

   	@Column(name = "last_writer")
	private String lastWriter;

   	@Column(name = "modify_date")
	private LocalDateTime modifyDate;

   	@Column(name = "deleted")
	private Boolean deleted;


}
