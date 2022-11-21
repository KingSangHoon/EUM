package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.entity.setting.InfoHttpType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CodeHttpService {
    ArrayList findInfoHttpType();
    List<InfoHttpType> findHttpTypeByName(String type);
    InfoHttpType findHttpDetailById(Integer id);
    void createHttpDetail(Map<String, Object> map);
    void updateHttpDetail(Integer id, Map<String, Object> map);
    void detailHttps(Set<Integer> idSet);
}
