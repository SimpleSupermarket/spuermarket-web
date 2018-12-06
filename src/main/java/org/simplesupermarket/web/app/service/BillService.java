package org.simplesupermarket.web.app.service;

import java.util.List;

public interface BillService {

    List getList(String goodsNameStrm, Integer isPayment, String providerName);
}
