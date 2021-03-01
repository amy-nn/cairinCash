package com.cairin.cash.utils;

import android.os.Environment;

import com.cairin.cash.entity.DeviceCrawlData;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EnvermentUtil {


    private static volatile EnvermentUtil environment  = null;
    private Gson gson;

    public static EnvermentUtil getInstance(){
        if(environment == null){
            synchronized (Environment.class){
                if (environment == null){
                    environment = new EnvermentUtil();
                }
            }
        }
        return  environment;
    }

    private EnvermentUtil(){
        gson = new Gson();
    }

    /**
     * 存轮渡信息
     */
    public void saveDeviceInfo(String result){

        /**
         * 先解析过滤application节点，然后再存文件
         */
        DeviceCrawlData deviceCrawlData = gson.fromJson(result, DeviceCrawlData.class);
        String afterJson = filterJson(deviceCrawlData);

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            String filePath = Environment.getExternalStorageDirectory().getPath();
            File file = new File(filePath, Constant.CASH_FILE_PATH);
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(afterJson.getBytes());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    /**
     * 过滤Json
     */
    private String filterJson(DeviceCrawlData data){

        ExclusionStrategy myExclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                if("application".equals(fa.getName()))
                    return true;
                return false;
            }
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

        };

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(myExclusionStrategy)
                .create();
        String json = gson.toJson(data);
        return json;
    }


}
