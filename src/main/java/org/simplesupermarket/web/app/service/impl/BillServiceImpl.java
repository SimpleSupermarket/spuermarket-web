package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.BillView;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.BillService;
import org.simplesupermarket.web.db.mapper.BillMapper;
import org.simplesupermarket.web.db.mapper.GoodsMapper;
import org.simplesupermarket.web.db.mapper.UserMapper;
import org.simplesupermarket.web.db.model.Bill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @date 2018年12月03日
 */
@Service
public class BillServiceImpl extends AbstractSuperServiceImpl<Bill> implements BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);
    @Autowired
    private BillMapper billMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 参数验证
     */
    @Override
    public List getList(Map<String, String> sd) {
        String goodsNameStr = sd.get("goodsName");
        String isPaymentStr = sd.get("isPayment");
        String providerStr = sd.get("provider");
        if (StringUtils.isEmpty(goodsNameStr) || goodsNameStr.length() > 100) {
            goodsNameStr = null;
        }
        if (StringUtils.isEmpty(providerStr) || providerStr.length() > 100) {
            providerStr = null;
        }
        Integer isPayment;
        if (isPaymentStr == null || isPaymentStr.isEmpty()) {
            isPayment = null;
        } else {
            isPaymentStr = isPaymentStr.trim();
            try {
                isPayment = Integer.parseInt(isPaymentStr);
            } catch (Exception e) {
                LOGGER.info("付款状态不正确，isPaymentStr:{}", isPaymentStr);
                isPayment = null;
            }
        }
        return this.getList(goodsNameStr, isPayment, providerStr);
    }

    //
    public List getList(String goodsNameStr, Integer isPayment, String providerName) {
        List<BillView> list = new Vector<>();
        List<Bill> billList = billMapper.selectAll(isPayment, goodsNameStr, providerName);
        Map<Long, BillView> map = new ConcurrentHashMap();
        Map<Long, Long> mapGoods = new ConcurrentHashMap();
        Map<Long, Long> mapUser = new ConcurrentHashMap();
        billList.forEach(bill -> {
            BillView billView = new BillView();
            BeanUtils.copyProperties(bill, billView);
            billView.setIspayment(PAYMENT.getPayment(bill.getIspayment()));
            billView.setTotalprice(bill.getTotalprice().setScale(2).toString());
            billView.setCreationdate(super.format.format(bill.getCreationdate()));
            mapGoods.put(bill.getGoodsId(), bill.getId());
            mapUser.put(bill.getCreatedby(), bill.getId());
            map.put(billView.getId(), billView);
            list.add(billView);
        });
        goodsMapper.selectByIds(
                Arrays.asList(mapGoods.keySet().toArray(new Long[0])))
                .forEach(goods -> {
                    Long mapId = mapGoods.get(goods.getId());
                    map.get(mapId).setGoods(goods);
                });
        userMapper.selectByIds(
                Arrays.asList(mapUser.keySet().toArray(new Long[0])))
                .forEach(user -> {
                    Long mapId = mapUser.get(user.getId());
                    map.get(mapId).setCreatedby(user);
                });
        return new ArrayList(map.values());
    }

    public static class PAYMENT {
        public static final int YESPAY = 2;
        public static final int NOPAY = 1;

        public static String getPayment(int s) {
            switch (s) {
                case 1:
                    return "未支付";
                case 2:
                    return "已支付";
                default:
                    return "未知";
            }
        }
    }

}
