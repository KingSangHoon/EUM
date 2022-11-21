package com.sysone.eumaiwacs.entity.setting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name="tbl_sensor_device")
public class SensorDevice {

    @Id
    @SequenceGenerator(name="tbl_sensor_device_seq", sequenceName="tbl_sensor_device_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tbl_sensor_device_seq")
    @Column(name="sensor_id", unique=true, nullable=false)
    private Integer sensorId;

    @Column(name="sensor_name")
    private String sensorName;

    @Column(name="sensor_alias")
    private String sensorAlias;

    @Column(name="reg_date")
    private LocalDateTime regDate;

    @Column(name="modify_date")
    private LocalDateTime modifyDate;

    @Column(name="active")
    private Boolean active;

    @Column(name="deleted")
    private Boolean deleted;

    @Column(name="device_activated")
    private Boolean deviceActivated;

    @Column(name="device_activated_true_date")
    private LocalDateTime deviceActivatedTrueDate;

    @Column(name="device_activated_false_date")
    private LocalDateTime deviceActivatedFalseDate;

    @Column(name="last_connect_date")
    private LocalDateTime lastConnectDate;

    @Column(name="description")
    private String description;

    @Column(name="first_writer")
    private String firstWriter;

    @Column(name="last_writer")
    private String lastWriter;

    @Column(name="sensor_os")
    private Integer sensorOs;

    @Column(name="sensor_version")
    private String sensorVersion;

    @Column(name="bpf_filter")
    private String bpfFilter;
}
