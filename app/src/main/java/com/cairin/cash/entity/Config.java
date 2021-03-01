package com.cairin.cash.entity;

import com.google.gson.Gson;

import java.util.List;

public class Config {

    /**
     * appInfo : {"tabList":[],"surface":"A","entrypoint":"https://static.aiyun.asia/static/daichaoh5/index.html#/home","showTitle":false,"disablePhysicalKey":false}
     * extendList : [{"extendCode":"ld_api_secret_key","extendValue":"b2476f40f6cb2775"},{"extendCode":"ld_api_access_key","extendValue":"c922eed2128c6ba1"}]
     */

    private AppInfo appInfo;
    private List<ExtendList> extendList;

    public static Config objectFromData(String str) {

        return new Gson().fromJson(str, Config.class);
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public List<ExtendList> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<ExtendList> extendList) {
        this.extendList = extendList;
    }

    public static class AppInfo {
        /**
         * tabList : []
         * surface : A
         * entrypoint : https://static.aiyun.asia/static/daichaoh5/index.html#/home
         * showTitle : false
         * disablePhysicalKey : false
         */

        private String surface;
        private String entrypoint;
        private boolean showTitle;
        private boolean disablePhysicalKey;
        private List<?> tabList;

        public static AppInfo objectFromData(String str) {

            return new Gson().fromJson(str, AppInfo.class);
        }

        public String getSurface() {
            return surface;
        }

        public void setSurface(String surface) {
            this.surface = surface;
        }

        public String getEntrypoint() {
            return entrypoint;
        }

        public void setEntrypoint(String entrypoint) {
            this.entrypoint = entrypoint;
        }

        public boolean isShowTitle() {
            return showTitle;
        }

        public void setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
        }

        public boolean isDisablePhysicalKey() {
            return disablePhysicalKey;
        }

        public void setDisablePhysicalKey(boolean disablePhysicalKey) {
            this.disablePhysicalKey = disablePhysicalKey;
        }

        public List<?> getTabList() {
            return tabList;
        }

        public void setTabList(List<?> tabList) {
            this.tabList = tabList;
        }
    }

    public static class ExtendList {
        /**
         * extendCode : ld_api_secret_key
         * extendValue : b2476f40f6cb2775
         */

        private String extendCode;
        private String extendValue;

        public static ExtendList objectFromData(String str) {

            return new Gson().fromJson(str, ExtendList.class);
        }

        public String getExtendCode() {
            return extendCode;
        }

        public void setExtendCode(String extendCode) {
            this.extendCode = extendCode;
        }

        public String getExtendValue() {
            return extendValue;
        }

        public void setExtendValue(String extendValue) {
            this.extendValue = extendValue;
        }
    }
}
