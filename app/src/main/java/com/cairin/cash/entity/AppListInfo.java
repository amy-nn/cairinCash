package com.cairin.cash.entity;

import com.google.gson.Gson;

public class AppListInfo {

    /**
     * app_name : com.miui.translation.kingsoft
     * bundle_id : com.miui.translation.kingsoft
     * versionName : 1.0
     * appTag : 8928837
     * installTime : 1527089616000
     * updateTime : 1527089616000
     * systemAppFlag : true
     * versionCode : 1
     */

    private String app_name;
    private String bundle_id;
    private String versionName;
    private int appTag;
    private long installTime;
    private long updateTime;
    private boolean systemAppFlag;
    private int versionCode;

    public static AppListInfo objectFromData(String str) {

        return new Gson().fromJson(str, AppListInfo.class);
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getBundle_id() {
        return bundle_id;
    }

    public void setBundle_id(String bundle_id) {
        this.bundle_id = bundle_id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getAppTag() {
        return appTag;
    }

    public void setAppTag(int appTag) {
        this.appTag = appTag;
    }

    public long getInstallTime() {
        return installTime;
    }

    public void setInstallTime(long installTime) {
        this.installTime = installTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isSystemAppFlag() {
        return systemAppFlag;
    }

    public void setSystemAppFlag(boolean systemAppFlag) {
        this.systemAppFlag = systemAppFlag;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }
}
