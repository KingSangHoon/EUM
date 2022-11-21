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
@Table (name = "tbl_mapping_alarm")
public class MappingAlarm {

	@Id
	@SequenceGenerator(name="tbl_mapping_alarm_seq", sequenceName="tbl_mapping_alarm_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_alarm_seq")
   	@Column(name = "mapping_id")
	private Integer mappingId;

   	@Column(name = "alarm_id")
	private Integer alarmId;

   	@Column(name = "l3_ip_id")
	private Integer l3IpId;

   	@Column(name = "l4_tcp_id")
	private Integer l4TcpId;

   	@Column(name = "l4_tcp_error_id")
	private Integer l4TcpErrorId;

   	@Column(name = "l4_udp_id")
	private Integer l4UdpId;

   	@Column(name = "traffic_id")
	private Integer trafficId;

   	@Column(name = "transaction_id")
	private Integer transactionId;

   	@Column(name = "uri_id")
	private Integer uriId;


}
