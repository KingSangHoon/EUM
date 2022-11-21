package com.sysone.eumaiwacs.serviceImpl.setting;

import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoHttpType;
import com.sysone.eumaiwacs.entity.setting.InfoMimeType;
import com.sysone.eumaiwacs.repository.setting.InfoHttpTypeRepository;
import com.sysone.eumaiwacs.repository.setting.InfoMimeTypeRepository;
import com.sysone.eumaiwacs.service.setting.CodeMimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CodeMimeServiceImpl implements CodeMimeService {

    @Autowired
    InfoMimeTypeRepository infoMimeTypeRepository;

    @Override
    public ArrayList findInfoMimeType() {
        ArrayList result = new ArrayList();
        List<Object[]> findHttpTypeList = infoMimeTypeRepository.findType();
        for(Object[] item : findHttpTypeList){
            Map<String, Object> itemMap = new HashMap<String, Object>();
            itemMap.put("type", item[0]);

            result.add(itemMap);
        }
        return result;
    }

    @Override
    public List<InfoMimeType> findMimeTypeByName(String type) {
        return infoMimeTypeRepository.findMimeTypeByName(type);
    }

    @Override
    public InfoMimeType findMimeDetailById(Integer id) {
        return infoMimeTypeRepository.findMimeTypeByid(id);
    }

    @Override
    public void createMimeDetail(Map<String, Object> map) {
        InfoMimeType infoMimeItem = new InfoMimeType();
        infoMimeItem.setName(Util.utf8ToLatin1((String)map.get("name")));
        infoMimeItem.setType((String) map.get("type"));
        infoMimeItem.setTemplate(Util.utf8ToLatin1((String) map.get("template")));

        infoMimeTypeRepository.save(infoMimeItem);
    }

    @Override
    public void updateMimeDetail(Integer id, Map<String, Object> map) {
        InfoMimeType infoMimeItem = new InfoMimeType();
        infoMimeItem.setId((Integer) map.get("id"));
        infoMimeItem.setName(Util.utf8ToLatin1((String)map.get("name")));
        infoMimeItem.setTemplate((String) map.get("template"));
        infoMimeItem.setType((String) map.get("type"));

        infoMimeTypeRepository.saveAndFlush(infoMimeItem);
    }

    @Override
    public void detailMimes(Set<Integer> idSet) {
        infoMimeTypeRepository.deleteMime(idSet);
    }
}
