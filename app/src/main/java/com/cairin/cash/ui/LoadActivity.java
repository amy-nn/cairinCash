package com.cairin.cash.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import com.cairin.cash.R;
import com.cairin.cash.entity.BodyInfo;
import com.cairin.cash.entity.RequestResult;
import com.cairin.cash.network.LoadDataFactory;
import com.cairin.cash.utils.Constant;
import java.util.ArrayList;
import java.util.List;
import go.Seq;
import goutil.Goutil;


public class LoadActivity extends AppCompatActivity {

    private LoadDataFactory factory;
    private String[] allPermissions;
    private List<String> mPermissionList = new ArrayList<>();
    private String configJson;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
//        initPermission();
        getRequestData();
    }

    /**
     * 动态申请权限
     */
    private void initPermission() {

        allPermissions = new String[]{
                Manifest.permission.INTERNET,
//                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.READ_SMS,
//                Manifest.permission.SEND_SMS,
                Manifest.permission.READ_PHONE_NUMBERS,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.CHANGE_WIFI_MULTICAST_STATE,
//                Manifest.permission.WRITE_CONTACTS,
//                Manifest.permission.WRITE_SETTINGS,
//                Manifest.permission.CHANGE_CONFIGURATION,
//                Manifest.permission.CONTROL_LOCATION_UPDATES,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.READ_CONTACTS,
//                Manifest.permission.ACCESS_NETWORK_STATE,
//                Manifest.permission.ACCESS_WIFI_STATE,
//                Manifest.permission.CHANGE_WIFI_STATE,
//                Manifest.permission.CAMERA,
//                Manifest.permission.CALL_PHONE
        };

        int index = 0;
        if ((index =checkPermission()) > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, allPermissions, Constant.PERMISSIONS_INIT);
            Log.d("amy", "initPermission: "+index);
        }
        else {
            Log.d("amy", "initPermission: "+index);
            startMainActivity(configJson);
        }
    }

    /**
     * 检查权限
     */
    private int checkPermission(){
        mPermissionList.clear();
        for (int i = 0; i < allPermissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, allPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(allPermissions[i]);//添加还未授予的权限
            }
        }
        return mPermissionList.size();
    }

    /**
     * 获得请求数据
     */
    private void getRequestData(){

        factory = LoadDataFactory.getInstance(this);
        String bodyJson = factory.initData();
        BodyInfo bodyInfo = factory.gson.fromJson(bodyJson, BodyInfo.class);
        String dev = Goutil.sendRequestWithTimeout("dev", getPackageName(), "a_a_0", bodyJson, "{}", 120000);
        RequestResult requestResult = factory.gson.fromJson(dev, RequestResult.class);
        if(requestResult != null && requestResult.getCode().equals("100000")){
            try {
                String config = "{\"packageName\":\""+bodyInfo.getPackageName()+"\",\"version\":\""+bodyInfo.getVersion()+"\",\"channelKey\":\""+bodyInfo.getChannelKey()+"\",\"versionKey\":\""+bodyInfo.getVersionKey()+"\"}";
                byte[] decrypt = Goutil.decrypt(config, requestResult.getData().getConfig());
                configJson = new String(decrypt);
                factory.saveInitConfig(configJson);
//                Log.d("amy", "configJson: "+configJson);
                /**
                 * 存服务器传回来的DeviceId和DeviceToken
                 */
                updateDeviceID(requestResult.getData().getDeviceId(),requestResult.getData().getDeviceToken());
                initPermission();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            //没有拿到config数据
            Log.d("amy", "config is null ");
        }
    }

    private void startMainActivity(String configJson){
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("configJson",configJson);
        startActivity(intent);
        finish();
    }


    /**
     * 更新DeviceId和DeviceToken
     * @param newId
     * @param newToken
     */
    private void updateDeviceID(String newId,String newToken){

        SharedPreferences sharedPreferences = getSharedPreferences("base_info",MODE_PRIVATE);
        String bodyJson = sharedPreferences.getString("bodyInfo","");
        BodyInfo bodyInfo = factory.gson.fromJson(bodyJson, BodyInfo.class);
        bodyInfo.setDeviceId(newId);
        bodyInfo.setDeviceToken(newToken);
        String newJson = factory.gson.toJson(bodyInfo);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("bodyInfo",newJson);
        edit.commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermissionDismiss = false;//有权限没有通过
        if(requestCode == Constant.PERMISSIONS_INIT){

            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermissionDismiss = true;
                }
            }
            //如果有权限没有被允许
            if (hasPermissionDismiss) {
                startMainActivity(configJson);
            }else{
                //全部权限通过，可以进行下一步操作。。。
                startMainActivity(configJson);
            }

        }
    }
}
