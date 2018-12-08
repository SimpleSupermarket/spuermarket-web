package org.simplesupermarket.web.app.domain;

import org.simplesupermarket.web.app.domain.annotation.FromDb;
import org.simplesupermarket.web.db.model.Bill;
import org.simplesupermarket.web.db.model.Goods;
import org.simplesupermarket.web.db.model.User;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @version 1.0
 * @date 2018年12月06日
 */
public class BillView {
    private Long id;
    private String code;
    @FromDb("goodsId")
    private Goods goods;
    private Integer goodscount;
    private String totalprice;
    private String ispayment;
    @FromDb
    private User createdby;
    private String creationdate;

    protected final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时");

    public BillView(Bill bill) {
        BeanUtils.copyProperties(bill,this);
        goodscount = bill.getGoodsCount();
        this.setIspayment(PAYMENT.getPayment(bill.getIsPayment()));
        this.setTotalprice(bill.getTotalPrice().setScale(2).toString());
        this.setCreationdate(format.format(bill.getCreationdate()));
    }

    private static class PAYMENT {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(Integer goodscount) {
        this.goodscount = goodscount;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getIspayment() {
        return ispayment;
    }

    public void setIspayment(String ispayment) {
        this.ispayment = ispayment;
    }

    public User getCreatedby() {
        return createdby;
    }

    public void setCreatedby(User createdby) {
        this.createdby = createdby;
    }

    public String getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(String creationdate) {
        this.creationdate = creationdate;
    }
}
