package com.example.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CtwingInfo implements Serializable {
    private Integer id;

    private String deviceId;

    private String imei;

    private String imsi;

    private String tenantId;

    private String productId;

    private String deviceType;

    private String assocAssetid;

    private Integer upPacketSn;

    private Integer upDataSn;

    private String protocol;

    private Integer rsrp;

    private Integer sinr;

    private Integer pci;

    private Integer ecl;

    private Integer cellId;

    private Integer latitude;

    private Integer longitude;

    private Integer altitude;

    private String addresss;

    private Integer batteryVol;

    private String wifiMac;

    private Integer sleepTime;

    private String wakeupTime;

    private String online;

    private String remake;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null ? null : tenantId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getAssocAssetid() {
        return assocAssetid;
    }

    public void setAssocAssetid(String assocAssetid) {
        this.assocAssetid = assocAssetid == null ? null : assocAssetid.trim();
    }

    public Integer getUpPacketSn() {
        return upPacketSn;
    }

    public void setUpPacketSn(Integer upPacketSn) {
        this.upPacketSn = upPacketSn;
    }

    public Integer getUpDataSn() {
        return upDataSn;
    }

    public void setUpDataSn(Integer upDataSn) {
        this.upDataSn = upDataSn;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    public Integer getRsrp() {
        return rsrp;
    }

    public void setRsrp(Integer rsrp) {
        this.rsrp = rsrp;
    }

    public Integer getSinr() {
        return sinr;
    }

    public void setSinr(Integer sinr) {
        this.sinr = sinr;
    }

    public Integer getPci() {
        return pci;
    }

    public void setPci(Integer pci) {
        this.pci = pci;
    }

    public Integer getEcl() {
        return ecl;
    }

    public void setEcl(Integer ecl) {
        this.ecl = ecl;
    }

    public Integer getCellId() {
        return cellId;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public String getAddresss() {
        return addresss;
    }

    public void setAddresss(String addresss) {
        this.addresss = addresss == null ? null : addresss.trim();
    }

    public Integer getBatteryVol() {
        return batteryVol;
    }

    public void setBatteryVol(Integer batteryVol) {
        this.batteryVol = batteryVol;
    }

    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac == null ? null : wifiMac.trim();
    }

    public Integer getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getWakeupTime() {
        return wakeupTime;
    }

    public void setWakeupTime(String wakeupTime) {
        this.wakeupTime = wakeupTime == null ? null : wakeupTime.trim();
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online == null ? null : online.trim();
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake == null ? null : remake.trim();
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", imei=").append(imei);
        sb.append(", imsi=").append(imsi);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", productId=").append(productId);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", assocAssetid=").append(assocAssetid);
        sb.append(", upPacketSn=").append(upPacketSn);
        sb.append(", upDataSn=").append(upDataSn);
        sb.append(", protocol=").append(protocol);
        sb.append(", rsrp=").append(rsrp);
        sb.append(", sinr=").append(sinr);
        sb.append(", pci=").append(pci);
        sb.append(", ecl=").append(ecl);
        sb.append(", cellId=").append(cellId);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", altitude=").append(altitude);
        sb.append(", addresss=").append(addresss);
        sb.append(", batteryVol=").append(batteryVol);
        sb.append(", wifiMac=").append(wifiMac);
        sb.append(", sleepTime=").append(sleepTime);
        sb.append(", wakeupTime=").append(wakeupTime);
        sb.append(", online=").append(online);
        sb.append(", remake=").append(remake);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}