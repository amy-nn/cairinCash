package com.cairin.cash.entity;

import com.google.gson.Gson;

public class AppInfo {


    /**
     * packageName : com.memo.cash
     * bundle_id : com.memo.cash
     * channelKey : 16
     * channel_code : 16
     * channel_name : google-play
     * version : 1
     * platform : android
     * deviceId : 61e288d2-b460-4e19-8d4a-3e6455da2078
     * deviceToken : 9ba170c0-41fc-4884-bd6e-75d7a04e34ed
     * packageType : gp
     * deviceInfo : {"appVersion":"1","bundleid":"com.memo.cash","deviceId":"61e288d2-b460-4e19-8d4a-3e6455da2078","deviceInfo":"{\"androidId\":\"36ebb88e052a4184\",\"availMemory\":\"476 MB\",\"codeName\":\"REL\",\"cpu\":\"arm64-v8a\",\"cpuInfo\":\"AArch64 Processor rev 2 (aarch64) \",\"deviceSoftwareVersion\":\"78\",\"display\":\"1080|1920\",\"gsmCellLocation\":\"34390|4335\",\"hardware\":\"mt6795\",\"imei\":\"869159029897588\",\"language\":\"zh\",\"macAddress\":\"20:82:c0:b1:bd:35\",\"manufacturer\":\"Xiaomi\",\"model\":\"Redmi Note 2\",\"networkOperator\":\"\",\"networkOperatorName\":\"\",\"networkType\":\"0\",\"product\":\"hermes\",\"radioVersion\":\"MOLY.LR9.W1423.MD.LWTG.LCSH6795.LWT.L.SP.V1.P1, 2018/03/09 15:04\",\"release\":\"5.0.2\",\"sdkVersion\":\"21\",\"serialNumber\":\"RC6PVCIJ99999999\",\"totalMemory\":\"1.83 GB\",\"uuid\":\"7b5cceee-e0cd-33c7-9e5e-caeed12d35e9\"}","deviceManufacturer":"Xiaomi","devicePlatform":"0","deviceType":"Redmi Note 2","deviceVersion":"21","firstOpen":"1","latitude":"","longitude":"","networkAc":"0","screenResolution":"1080|1920","userAgent":"Dalvik/2.1.0 (Linux; U; Android 5.0.2; Redmi Note 2 MIUI/8.4.26)"}
     * country : +62
     * versionName : 1.000.1
     * local : zh-CN
     * terminal_name : Ayo Cash
     */

    private String packageName;
    private String bundle_id;
    private String channelKey;
    private String channel_code;
    private String channel_name;
    private int version;
    private String platform;
    private String deviceId;
    private String deviceToken;
    private String packageType;
    private String deviceInfo;
    private String country;
    private String versionName;
    private String local;
    private String terminal_name;

    public static AppInfo objectFromData(String str) {

        return new Gson().fromJson(str, AppInfo.class);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getBundle_id() {
        return bundle_id;
    }

    public void setBundle_id(String bundle_id) {
        this.bundle_id = bundle_id;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    public String getChannel_code() {
        return channel_code;
    }

    public void setChannel_code(String channel_code) {
        this.channel_code = channel_code;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTerminal_name() {
        return terminal_name;
    }

    public void setTerminal_name(String terminal_name) {
        this.terminal_name = terminal_name;
    }
}
