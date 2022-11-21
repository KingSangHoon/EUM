package com.sysone.eumaiwacs.entity.realtime;

import com.sysone.eumaiwacs.common.Util;

public interface ApplicaionInfoAndMappingSimpleINF {
    String getIp();
    String getMac();
    String getStartPort();
    String getEndPort();
    String getOrgTitle();

    default String getTitle(){
        return Util.latin1ToUtf8(getOrgTitle());
    }

}
