package com.sysone.eumaiwacs.service.setting;

import com.sysone.eumaiwacs.entity.setting.InfoHttpType;
import com.sysone.eumaiwacs.entity.setting.InfoMimeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CodeMimeService {
    ArrayList findInfoMimeType();
    List<InfoMimeType> findMimeTypeByName(String type);
    InfoMimeType findMimeDetailById(Integer id);
    void createMimeDetail(Map<String, Object> map);
    void updateMimeDetail(Integer id, Map<String, Object> map);
    void detailMimes(Set<Integer> idSet);
}
