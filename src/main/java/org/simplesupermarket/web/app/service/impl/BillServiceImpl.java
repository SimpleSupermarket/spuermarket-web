package org.simplesupermarket.web.app.service.impl;

import org.simplesupermarket.web.app.domain.BillView;
import org.simplesupermarket.web.app.service.AbstractSuperServiceImpl;
import org.simplesupermarket.web.app.service.BillService;
import org.simplesupermarket.web.app.domain.annotation.ViewClass;
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
    @ViewClass(BillView.class)
    public List getDbData(Map<String, String> sd) {
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
          return billMapper.selectAll(isPayment, goodsNameStr, providerName);


    }


}
