package com.sysone.eumaiwacs.entity.realtime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@SuppressWarnings("serial")
@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class RealtimeUriKey implements Serializable {

    @Column(name="src_ip")
    private String srcIp;
    @Column(name="dst_ip")
    private String dstIp;
    @Column(name="src_port")
    private Integer srcPort;
    @Column(name="dst_port")
    private Integer dstPort;
    @Column(name="ts_frame_arrival")
    private BigDecimal tsFrameArrival;
    @Column(name="ts_frame_landoff")
    private BigDecimal tsFrameLandOff;
    @Column(name="page_idx")
    private BigInteger pageIdx;
    @Column(name="uri_idx")
    private BigInteger uriIdx;
    @Column(name="sensor_device_id")
    private Integer sensorDeviceId;
}
