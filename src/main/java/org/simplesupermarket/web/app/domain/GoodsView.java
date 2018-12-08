package org.simplesupermarket.web.app.domain;
import org.simplesupermarket.web.app.domain.annotation.FromDb;
import org.simplesupermarket.web.db.model.Goods;
import org.simplesupermarket.web.db.model.Provider;
import org.simplesupermarket.web.db.model.User;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @date 2018年12月06日
 * @version 1.0
 */
public class GoodsView {
    private Long id;

    private String code;

    private String name;

    private String price;

    private String unit;
    @FromDb
    private Provider provider;

    private Integer stock;

    private User createdby;

    private String creationdate;

    protected final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时");

    public GoodsView(Goods goods){
        BeanUtils.copyProperties(goods,this);
        this.setPrice(goods.getPrice().setScale(2).toString());
        this.setCreationdate(format.format(goods.getCreationdate()));
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
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
