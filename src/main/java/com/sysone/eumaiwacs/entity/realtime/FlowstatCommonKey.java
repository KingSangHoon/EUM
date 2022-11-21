package com.sysone.eumaiwacs.entity.realtime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class FlowstatCommonKey implements Serializable {

    @Column(name="sensor_device_id")
    private Integer sensorDeviceId;
    @Column(name="flow_identifier")
    private String flowIdentifier;
    @Column(name="ts_sample_begin")
    private BigDecimal tsSampleBegin;
    @Column(name="ts_sample_end")
    private BigDecimal tsSampleEnd;
    @Column(name="expired")
    private Integer expired;
}
