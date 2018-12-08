package org.simplesupermarket.web.app.domain;

import org.simplesupermarket.web.app.domain.annotation.FromDb;
import org.simplesupermarket.web.db.model.Provider;
import org.simplesupermarket.web.db.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @Time 2018/12/5 23:33
 */
public class ProviderView {
    private Long id;

    private String code;

    private String name;

    private String desc;

    private String contact;

    private String phone;

    private String address;

    private String fax;
    @FromDb
    private User createdby;

    private String creationdate;

    protected final DateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时");
    public ProviderView(Provider provider){
        BeanUtils.copyProperties(provider,this);
        this.setCreationdate(format.format(provider.getCreationdate()));
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
