package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Time 2018/12/3 23:09
 */
public class ProviderServiceImpl extends AbstractSuperServiceImpl implements ProviderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderServiceImpl.class);

    @Override
    public List getList() {
        return null;
    }
}
