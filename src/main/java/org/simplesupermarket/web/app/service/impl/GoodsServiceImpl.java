package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.GoodsView;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.GoodsService;
import org.simplesupermarket.web.app.domain.annotation.ViewClass;
import org.simplesupermarket.web.db.mapper.GoodsMapper;
import org.simplesupermarket.web.db.mapper.ProviderMapper;
import org.simplesupermarket.web.db.mapper.UserMapper;
import org.simplesupermarket.web.db.model.Goods;
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
public class GoodsServiceImpl extends AbstractSuperServiceImpl<Goods> implements GoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProviderMapper providerMapper;


    @Override
    @ViewClass(GoodsView.class)
    public List getDbData(Map<String, String> sd) {
        String goodsNameStr = sd.get("goodsName");
        if (StringUtils.isEmpty(goodsNameStr) || goodsNameStr.length()>100 ) {
            goodsNameStr = null;
        }
        return getList(goodsNameStr,null);
    }

    @Override
    public List getList(String goodsNameStr, String providerName) {

        List<GoodsView> list = new Vector<>();
        List<Goods> goodsList = goodsMapper.selectAll(goodsNameStr,providerName);
        if(goodsList==null || goodsList.isEmpty())return new ArrayList();
        Map<Long, GoodsView> map = new ConcurrentHashMap();
        Map<Long, Long> mapProvider = new ConcurrentHashMap();
        Map<Long, Long> mapUser = new ConcurrentHashMap();
        goodsList.forEach(goods -> {
            GoodsView billView = new GoodsView();
            BeanUtils.copyProperties(goods, billView);
            billView.setPrice(goods.getPrice().setScale(2).toString());
            billView.setCreationdate(super.format.format(goods.getCreationdate()));
            mapProvider.put(goods.getProviderId(), goods.getId());
            mapUser.put(goods.getCreatedby(), goods.getId());
            map.put(billView.getId(), billView);
            list.add(billView);
        });
        providerMapper.selectByIds(
                Arrays.asList(mapProvider.keySet().toArray(new Long[0])))
                .forEach(goods -> {
                    Long mapId = mapProvider.get(goods.getId());
                    map.get(mapId).setProvider(goods);
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
