package com.sysone.eumaiwacs.serviceImpl.setting;

import com.sysone.eumaiwacs.common.Util;
import com.sysone.eumaiwacs.entity.setting.InfoHttpType;
import com.sysone.eumaiwacs.repository.setting.InfoHttpTypeRepository;
import com.sysone.eumaiwacs.service.setting.CodeHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CodeHttpServiceImpl implements CodeHttpService {

    @Autowired InfoHttpTypeRepository infoHttpTypeRepository;

    @Override
    public ArrayList findInfoHttpType() {
        ArrayList<Object> result = new ArrayList();
        List<Object[]> findHttpTypeList = infoHttpTypeRepository.findType();
        for(Object[] item : findHttpTypeList){
            Map<String, Object> itemMap = new HashMap<String, Object>();
            itemMap.put("type", item[0]);

            result.add(itemMap);
        }
        return result;
    }

    @Override
    public List<InfoHttpType> findHttpTypeByName(String type) {
        List<InfoHttpType> infoHttpTypeList = infoHttpTypeRepository.findHttpTypeByName(type);

        for(InfoHttpType infoHttpType : infoHttpTypeList) {
            infoHttpType.setDescription1(Util.latin1ToUtf8(infoHttpType.getDescription1()));
            infoHttpType.setDescription2(Util.latin1ToUtf8(infoHttpType.getDescription2()));
        }

        return infoHttpTypeList;
    }

    @Override
    public InfoHttpType findHttpDetailById(Integer id) {
        return infoHttpTypeRepository.findHttpTypeByid(id);
    }

    @Override
    public void createHttpDetail(Map<String, Object> map) {
        InfoHttpType infoHttpItem = new InfoHttpType();
        infoHttpItem.setName(Util.utf8ToLatin1((String)map.get("name")));
        infoHttpItem.setType((String) map.get("type"));
        infoHttpItem.setSubType(Util.utf8ToLatin1((String) map.get("subType")));
        infoHttpItem.setDescription(Util.utf8ToLatin1((String) map.get("description")));

        infoHttpTypeRepository.save(infoHttpItem);
    }

    @Override
    public void updateHttpDetail(Integer id, Map<String, Object> map) {

        InfoHttpType infoHttpItem = new InfoHttpType();
        infoHttpItem.setId((Integer) map.get("id"));
        infoHttpItem.setName(Util.utf8ToLatin1((String)map.get("name")));
        infoHttpItem.setSubType((String) map.get("subType"));
        infoHttpItem.setDescription((String) map.get("description"));
        infoHttpItem.setType((String) map.get("type"));

        infoHttpTypeRepository.saveAndFlush(infoHttpItem);
    }

    @Override
    public void detailHttps(Set<Integer> idSet) {
        infoHttpTypeRepository.deleteHttp(idSet);
    }
}
