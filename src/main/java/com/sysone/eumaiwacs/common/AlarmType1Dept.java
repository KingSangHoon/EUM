package com.sysone.eumaiwacs.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum AlarmType1Dept {

    HTTP_TRANSACTION("HTTP-Transaction","httpTransaction",1),
    HTTP_URI("HTTP-URI","HttpURI",2),
    L4_TCP("L4-TCP","l4TCP",3),
    L4_UDP("L4-UDP","l4UDP",4),
    L3_IP("L3-IP","l3IP",5),
    TRAFFIC("Traffic","traffic",6);

    private static final Map<Object, AlarmType1Dept> BY_DEPT1_INFO = new HashMap<>();

    static {
        for (AlarmType1Dept e : values()) {
            BY_DEPT1_INFO.put(e.getName(), e);
            BY_DEPT1_INFO.put(e.getCodeName(), e);
            BY_DEPT1_INFO.put(e.getIndexNum(), e);
        }
    }

    public static AlarmType1Dept valueOfName(String name) { return BY_DEPT1_INFO.get(name); }
    public static AlarmType1Dept valueOfNameCode(String codeName) { return BY_DEPT1_INFO.get(codeName); }
    public static AlarmType1Dept valueOfIndexNum(Integer indexNum) { return BY_DEPT1_INFO.get(indexNum); }

    @Getter
    private String name;
    @Getter
    private String codeName;
    @Getter
    private Integer indexNum;

    AlarmType1Dept(String name, String codeName, Integer indexNum){
        this.name = name;
        this.codeName = codeName;
        this.indexNum = indexNum;
    }
}
