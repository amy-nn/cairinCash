package com.cairin.cash.entity;

import com.google.gson.Gson;

public class RequestResult {

    /**
     * code : 100000
     * message : 处理成功
     * data : {"deviceId":"b5acecb0-6cf2-11ea-ba73-e3603a9f3673","deviceToken":"be033bbc-6cf2-11ea-af59-e79d12002b48","config":"系统配置 AES 密文"}
     */

    private String code;
    private String message;
    private Data data;

    public static RequestResult objectFromData(String str) {

        return new Gson().fromJson(str, RequestResult.class);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * deviceId : b5acecb0-6cf2-11ea-ba73-e3603a9f3673
         * deviceToken : be033bbc-6cf2-11ea-af59-e79d12002b48
         * config : 系统配置 AES 密文
         */

        private String deviceId;
        private String deviceToken;
        private String config;

        public static Data objectFromData(String str) {

            return new Gson().fromJson(str, Data.class);
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

        public String getConfig() {
            return config;
        }

        public void setConfig(String config) {
            this.config = config;
        }
    }
}
