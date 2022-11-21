package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlType;

import com.sysone.eumaiwacs.common.Util;
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
@Table (name = "tbl_info_alarm")
public class InfoAlarm {

	@Id
	@SequenceGenerator(name="tbl_group_application_seq", sequenceName="tbl_group_application_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_group_application_seq")
   	@Column(name = "alarm_id")
	private Integer alarmId;

   	@Column(name = "alarm_title")
	private String alarmTitle;

   	@Column(name = "first_write")
	private String firstWrite;

   	@Column(name = "reg_date")
	private LocalDateTime regDate;

   	@Column(name = "last_write")
	private String lastWrite;

   	@Column(name = "modify_date")
	private LocalDateTime modifyDate;

   	@Column(name = "deleted")
	private Boolean deleted;

}
