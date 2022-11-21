package com.sysone.eumaiwacs.service.common;

import com.sysone.eumaiwacs.entity.setting.Test;

import java.util.List;
import java.util.Map;

public interface SearchService {

    List<Object> searchHttpPageCategory(Map<String, Object> param);
    List<Object> searchHttpUriCategory(Map<String, Object> param);

    List<Test> test();

    void enumTest();
}
