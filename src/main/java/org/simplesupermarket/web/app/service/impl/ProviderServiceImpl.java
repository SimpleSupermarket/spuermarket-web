package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.ProviderView;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.ProviderService;
import org.simplesupermarket.web.app.domain.annotation.ViewClass;
import org.simplesupermarket.web.db.mapper.ProviderMapper;
import org.simplesupermarket.web.db.mapper.UserMapper;
import org.simplesupermarket.web.db.model.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Time 2018/12/3 23:09
 */
@Service
public class ProviderServiceImpl extends AbstractSuperServiceImpl<Provider> implements ProviderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);
    @Autowired
    private ProviderMapper providerMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    @ViewClass(ProviderView.class)
    public List getDbData(Map<String, String> sd) {
        String providerName = sd.get("provider");
        if (StringUtils.isEmpty(providerName) || providerName.length() > 100) {
            providerName = null;
        }
        return getList(providerName);

    }

    public List getList(String providerName) {

        List<ProviderView> list = new Vector<>();
        List<Provider> providerList = providerMapper.selectAll(providerName);
        if(providerList==null || providerList.isEmpty())return new ArrayList();
        Map<Long, ProviderView> map = new ConcurrentHashMap();
        Map<Long, Long> mapUser = new ConcurrentHashMap();
        providerList.forEach(provider -> {
            ProviderView billView = new ProviderView();
            BeanUtils.copyProperties(provider, billView);
            billView.setCreationdate(super.format.format(provider.getCreationdate()));
            mapUser.put(provider.getCreatedby(), provider.getId());
            map.put(billView.getId(), billView);
            list.add(billView);
        });

        userMapper.selectByIds(
                Arrays.asList(mapUser.keySet().toArray(new Long[0])))
                .forEach(user -> {
                    Long mapId = mapUser.get(user.getId());
                    map.get(mapId).setCreatedby(user);
                });
        return new ArrayList(map.values());
    }

}
