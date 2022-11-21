package com.sysone.eumaiwacs.entity.setting;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table (name = "tbl_info_application")
public class InfoApplication {

	@Id
	@SequenceGenerator(name="tbl_info_application_seq", sequenceName="tbl_info_application_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_application_seq")
   	@Column(name = "id")
	private Integer id;

   	@Column(name = "title")
	private String title;

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
