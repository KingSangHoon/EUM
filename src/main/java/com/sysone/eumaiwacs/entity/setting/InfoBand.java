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
@Table (name = "tbl_info_band")
public class InfoBand {

	@Id
	@SequenceGenerator(name="tbl_info_band_seq", sequenceName="tbl_info_band_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_info_band_seq")
   	@Column(name = "band_id")
	private Integer bandId;

   	@Column(name = "title")
	private String title;

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
