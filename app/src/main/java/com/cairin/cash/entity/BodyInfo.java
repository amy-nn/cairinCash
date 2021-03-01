package com.cairin.cash.entity;

import com.cairin.cash.utils.Constant;

public class BodyInfo {

    private String platform;
    private String deviceId;
    private String deviceToken;
    private String packageName;
    private String version;
    private String versionKey;
    private String uid;
    private String channelKey;
    private String deviceInfo;
    private String country;

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getDeviceInfo(){
        return this.deviceInfo;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionKey() {
        return versionKey;
    }

    public void setVersionKey(String versionKey) {
        this.versionKey = versionKey;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



}
