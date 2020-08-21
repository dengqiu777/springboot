package com.example.entity;

/**
 * Author DQ
 * Date 2020/6/24
 **/
public class DataReport<T> {
    private int  upPacketSN;
    private int  upDataSN;
    private String topic;
    private int  timestamp;
    private String tenantId;
    private int serviceId;
    private String  protocol;
    private String productId;
    private String payload;
    private String messageType;
    private String deviceType;
    private String deviceId;
    private String assocAssetId;
    private String IMSI;
    private String IMEI;

    public int getUpPacketSN() {
        return upPacketSN;
    }

    public void setUpPacketSN(int upPacketSN) {
        this.upPacketSN = upPacketSN;
    }

    public int getUpDataSN() {
        return upDataSN;
    }

    public void setUpDataSN(int upDataSN) {
        this.upDataSN = upDataSN;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAssocAssetId() {
        return assocAssetId;
    }

    public void setAssocAssetId(String assocAssetId) {
        this.assocAssetId = assocAssetId;
    }

    public String getIMSI() {
        return IMSI;
    }

    public void setIMSI(String IMSI) {
        this.IMSI = IMSI;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    @Override
    public String toString() {
        return "DataReport{" +
                "upPacketSN=" + upPacketSN +
                ", upDataSN=" + upDataSN +
                ", topic='" + topic + '\'' +
                ", timestamp=" + timestamp +
                ", tenantId='" + tenantId + '\'' +
                ", serviceId=" + serviceId +
                ", protocol='" + protocol + '\'' +
                ", productId='" + productId + '\'' +
                ", payload=" + payload +
                ", messageType='" + messageType + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", assocAssetId='" + assocAssetId + '\'' +
                ", IMSI='" + IMSI + '\'' +
                ", IMEI='" + IMEI + '\'' +
                '}';
    }
}
