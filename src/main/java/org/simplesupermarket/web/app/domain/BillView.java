package org.simplesupermarket.web.app.domain;
import org.simplesupermarket.web.app.domain.annotation.FromDb;
import org.simplesupermarket.web.db.model.Goods;
import org.simplesupermarket.web.db.model.User;

/**
 *
 * @date 2018年12月06日
 * @version 1.0
 */
public class BillView {
    private Long id;
    private String code;
    @FromDb
    private Goods goods;
    private Integer goodscount;
    private String totalprice;
    private String ispayment;
    @FromDb
    private User createdby;
    private String creationdate;

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
