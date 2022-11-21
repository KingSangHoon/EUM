package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="tbl_mapping_ssl_policy_sensor")
public class MappingSslPolicySensor {

    @Id
    @SequenceGenerator(name="tbl_mapping_ssl_policy_sensor_seq", sequenceName="tbl_mapping_ssl_policy_sensor_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_mapping_ssl_policy_sensor_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="ssl_policy_id")
    private Integer sslPolicyId;

    @Column(name="sensor_id")
    private Integer sensorId;
}
