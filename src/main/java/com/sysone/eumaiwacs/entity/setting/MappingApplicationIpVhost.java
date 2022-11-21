package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter @Setter
@NoArgsConstructor
@DynamicInsert @DynamicUpdate
@Table (name = "tbl_mapping_application_ip_vhost")
public class MappingApplicationIpVhost {

	@Id
	@SequenceGenerator(name="tbl_mapping_application_ip_vhost_seq", sequenceName="tbl_mapping_application_ip_vhost_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_application_ip_vhost_seq")
   	@Column(name = "mapping_id")
	private Integer mappingId;

   	@Column(name = "ip_id")
	private Integer ipId;

   	@Column(name = "application_id")
	private Integer applicationId;

   	@Column(name = "v_host")
	private String vHost;
}
