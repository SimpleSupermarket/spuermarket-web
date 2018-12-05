package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.ProviderView;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.ProviderService;
import org.simplesupermarket.web.db.model.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Time 2018/12/3 23:09
 */
@Service
public class ProviderServiceImpl extends AbstractSuperServiceImpl<Provider> implements ProviderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);


    @Override
    public List<ProviderView>  getList(Map md){


        List<ProviderView> list = new ArrayList<>(10);
        for (Provider provider : super.mapper.selectAll()) {
            ProviderView providerView = new ProviderView();
            BeanUtils.copyProperties(provider,providerView);
            Date time = provider.getCreationdate();
            providerView.setCreationdate( super.format.format(time));
            list.add(providerView);
        }
        return list;
    }
}
