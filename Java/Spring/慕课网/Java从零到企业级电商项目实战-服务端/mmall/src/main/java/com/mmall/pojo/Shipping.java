package com.mmall.pojo;

import java.util.Date;

public class Shipping {
    private Integer id;

    private Integer userId;

    private String receiveName;

    private String receivePhone;

    private String receiveMobile;

    private String receiveProvince;

    private String receiveCity;

    private String receiveDistrict;

    private String receiveAddress;

    private String receiveZip;

    private Date createTime;

    private Date updateTime;

    public Shipping(Integer id, Integer userId, String receiveName, String receivePhone, String receiveMobile, String receiveProvince, String receiveCity, String receiveDistrict, String receiveAddress, String receiveZip, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.receiveName = receiveName;
        this.receivePhone = receivePhone;
        this.receiveMobile = receiveMobile;
        this.receiveProvince = receiveProvince;
        this.receiveCity = receiveCity;
        this.receiveDistrict = receiveDistrict;
        this.receiveAddress = receiveAddress;
        this.receiveZip = receiveZip;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Shipping() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName == null ? null : receiveName.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile == null ? null : receiveMobile.trim();
    }

    public String getReceiveProvince() {
        return receiveProvince;
    }

    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince == null ? null : receiveProvince.trim();
    }

    public String getReceiveCity() {
        return receiveCity;
    }

    public void setReceiveCity(String receiveCity) {
        this.receiveCity = receiveCity == null ? null : receiveCity.trim();
    }

    public String getReceiveDistrict() {
        return receiveDistrict;
    }

    public void setReceiveDistrict(String receiveDistrict) {
        this.receiveDistrict = receiveDistrict == null ? null : receiveDistrict.trim();
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress == null ? null : receiveAddress.trim();
    }

    public String getReceiveZip() {
        return receiveZip;
    }

    public void setReceiveZip(String receiveZip) {
        this.receiveZip = receiveZip == null ? null : receiveZip.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}