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
@Table (name = "tbl_info_critical_default")
public class InfoCriticalDefault {

	@Id
	@SequenceGenerator(name="tbl_info_critical_default_seq",sequenceName="tbl_info_critical_default_seq",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tbl_info_critical_default_seq")
   	@Column(name = "id")
	private Integer id;

   	@Column(name = "resource_type")
	private Integer resourceType;

   	@Column(name = "resource_id")
	private Integer resourceId;

   	@Column(name = "reg_date")
	private LocalDateTime regDate;


}
