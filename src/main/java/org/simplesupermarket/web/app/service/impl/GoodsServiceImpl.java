package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.GoodsService;
import org.simplesupermarket.web.db.model.Goods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Time 2018/12/3 23:09
 */
@Service
public class GoodsServiceImpl extends AbstractSuperServiceImpl<Goods> implements GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);



}
