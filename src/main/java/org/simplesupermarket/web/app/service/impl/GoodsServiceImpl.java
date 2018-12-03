package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @Time 2018/12/3 23:09
 */
public class GoodsServiceImpl extends AbstractSuperServiceImpl implements GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Override
    public List getList() {
        return null;
    }
}
