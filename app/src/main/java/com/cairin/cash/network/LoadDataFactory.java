package com.cairin.cash.network;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;

import com.cairin.cash.utils.Constant;
import com.cairin.cash.entity.BodyInfo;
import com.cairin.cash.entity.DeviceCrawlData;
import com.cairin.cash.entity.DeviceInfo;
import com.cairin.cash.entity.DeviceInfos;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.SENSOR_SERVICE;
import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;

public class LoadDataFactory {

    private static volatile LoadDataFactory loadDataFactory = null;
    public TelephonyManager tManager;
    public ActivityManager.MemoryInfo outInfo;
    public Gson gson;
    public Context context;
    public DeviceInfo deviceInfo;
    public DeviceInfos deviceInfos;
    public BodyInfo bodyInfo;
    public String bodyJson;

    private LoadDataFactory(Context context) {
        this.context = context;
        gson = new Gson();
    }

    public static LoadDataFactory getInstance(Context context) {
        if (loadDataFactory == null) {
            synchronized (LoadDataFactory.class) {
                if (loadDataFactory == null) {
                    loadDataFactory = new LoadDataFactory(context);
                }
            }
        }
        return loadDataFactory;
    }

    /**
     * 初始化数据
     * @return
     */
    public String initData() {

        tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        outInfo = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(outInfo);
        long availMem = outInfo.availMem;
        long totalMem = outInfo.totalMem;

        String sBaseData = initBaseData();
        if(sBaseData != null && !sBaseData.equals(""))
        {
            bodyJson = sBaseData;
        }
        else
        {
            deviceInfos = new DeviceInfos();
            deviceInfo = new DeviceInfo();
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            deviceInfo.setAndroidId(androidId);
            deviceInfo.setAvailMemory("683MB");//(availMem+"");
            deviceInfo.setCodeName(Build.VERSION.CODENAME);
            deviceInfo.setCpu(Build.CPU_ABI);
            deviceInfo.setCpuInfo("AArch64 Processor rev 2 (aarch64)");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED)
                deviceInfo.setDeviceSoftwareVersion("78");
            else
                deviceInfo.setDeviceSoftwareVersion(tManager.getDeviceSoftwareVersion());

            deviceInfo.setDisplay(getDisplayInfo());
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                deviceInfo.setGsmCellLocation("0|0");
                deviceInfos.setLongitude("0");
                deviceInfos.setLatitude("0");
            }
            else {
                GsmCellLocation location = (GsmCellLocation) tManager.getCellLocation();
                deviceInfo.setGsmCellLocation(location.getCid() + "|" + location.getLac());
                deviceInfos.setLongitude(location.getCid()+"");
                deviceInfos.setLatitude(location.getLac()+"");
            }
            deviceInfo.setHardware(Build.HARDWARE);

            deviceInfo.setImei("869159029897588");//(getDeviceId(this));
//        deviceInfo.setImsi(getDeviceId(this));//(tManager.getSubscriberId());
            deviceInfo.setLanguage(Locale.getDefault().getLanguage());
            deviceInfo.setManufacturer(Build.MANUFACTURER);
            deviceInfo.setModel(Build.MODEL);
            deviceInfo.setNetworkOperator(tManager.getNetworkOperator());
            deviceInfo.setNetworkOperatorName(tManager.getNetworkOperatorName());
            deviceInfo.setNetworkType(tManager.getNetworkType()+"");
            deviceInfo.setProduct(Build.PRODUCT);
            deviceInfo.setRadioVersion(Build.getRadioVersion());
            deviceInfo.setRelease(Build.VERSION.RELEASE);
            deviceInfo.setSdkVersion(Build.VERSION.SDK_INT+"");
            deviceInfo.setSerialNumber(Build.SERIAL);
            deviceInfo.setTotalMemory("1.83 GB");//(totalMem+"");
            deviceInfo.setUuid(UUID.randomUUID().toString());
            deviceInfo.setMacAddress("20:82:c0:b1:bd:35");

            deviceInfos.setAppVersion("1");
            deviceInfos.setBundleid("com.cairin.cash");//(context.getPackageName());
            deviceInfos.setFirstOpen("1");
            deviceInfos.setDeviceId("");
            deviceInfos.setUserAgent(getUserAgent(context));
            deviceInfos.setNetworkAc(tManager.getNetworkType()+"");
            deviceInfos.setDevicePlatform("0");
            deviceInfos.setDeviceManufacturer(Build.MANUFACTURER);
            deviceInfos.setDeviceType(Build.MODEL);
            deviceInfos.setDeviceVersion(Build.VERSION.SDK_INT+"");
            deviceInfos.setScreenResolution(getDisplayInfo());
//        deviceInfos.setIsroot(1+"");//(isRoot());
//        deviceInfos.setIsreal(1+"");//(notHasLightSensorManager(this));
            String deviceInfoJson = gson.toJson(deviceInfo, DeviceInfo.class);
            deviceInfos.setDeviceInfo(deviceInfoJson);

            bodyInfo = new BodyInfo();
            bodyInfo.setCountry("+62");
            bodyInfo.setPlatform("android");
            bodyInfo.setDeviceId("");//第一次传空，服务器返回后一直保存在本地，每次请求都使用
            bodyInfo.setDeviceToken("");///每次打开App时候传 ""，服务器返回后保存，本次生命周期内一直使用
            bodyInfo.setPackageName("com.cairin.cash");//(context.getPackageName());//
            bodyInfo.setVersion("2");
            bodyInfo.setVersionKey("2.0.0");
            bodyInfo.setUid("");//未登录用户传 ``,h5登录成功后会通过scheme回调给客户端，保存并一直使用，退出后删除本地存储
            bodyInfo.setChannelKey("16");

            String deviceInfosJson = gson.toJson(deviceInfos, DeviceInfos.class);
            bodyInfo.setDeviceInfo(deviceInfosJson);

            bodyJson = gson.toJson(bodyInfo, BodyInfo.class);
            saveBaseData(bodyJson);
        }
        return bodyJson;
    }

    /**
     * 获得设备唯一ID
     * @param context
     * @return
     */
    public  String getDeviceId(Context context) {
        final int targetSdkVersion = context.getApplicationInfo().targetSdkVersion;
        if (targetSdkVersion > Build.VERSION_CODES.P && Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            return getUniqueID(context);
        } else {
            return getTelId(context);
        }
    }

    @SuppressLint("MissingPermission")
    private  String getTelId(Context context) {
        final TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return manager.getDeviceId();
    }

    private  String getUniqueID(Context context) {
        String id = null;
        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!TextUtils.isEmpty(androidId) && !"9774d56d682e549c".equals(androidId)) {
            try {
                UUID uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                id = uuid.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (TextUtils.isEmpty(id)) {
            id = getUUID();
        }

        return TextUtils.isEmpty(id) ? UUID.randomUUID().toString() : id;
    }

    @SuppressLint("MissingPermission")
    private  String getUUID() {
        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                ((null != Build.CPU_ABI) ? Build.CPU_ABI.length() : 0) % 10 +

                Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10 +

                Build.HOST.length() % 10 + Build.ID.length() % 10 +

                Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 +

                Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10 +

                Build.TYPE.length() % 10 + Build.USER.length() % 10; //13 位

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    serial = Build.getSerial();
                } else {
                    serial = Build.SERIAL;
                }
                //API>=9 使用serial号
                return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
            } catch (Exception exception) {
                serial = "serial" + UUID.randomUUID().toString(); // 随便一个初始化
            }
        } else {
            serial = Build.UNKNOWN + UUID.randomUUID().toString(); // 随便一个初始化
        }

        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 获得UserAgent
     * @param context
     * @return
     */
    public  String getUserAgent(Context context) {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(context);
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }




    /**
     * 判断设备是否被ROOT
     * @return
     */
    public boolean isRoot() {

        boolean root = false;
        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                root = false;
            } else {
                root = true;
            }

        } catch (Exception e) {
        }
        return root;
    }

    /**
     * 判断是否存在光传感器来判断是否为模拟器
     * @param context
     * @return
     */
    public boolean notHasLightSensorManager(Context context) {

        SensorManager sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);

        Sensor sensor8 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT); //光

        if (null == sensor8) {

            return true;

        } else {

            return false;

        }
    }

    /**
     * 读取基础数据
     */
    private String initBaseData(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("base_info",MODE_PRIVATE);

        /**
         * 读取Uid
         */
        String bodyInfo = sharedPreferences.getString("bodyInfo","");
        if(bodyInfo != null && !bodyInfo.isEmpty()){
            return bodyInfo;
        }
        else {
            return "";
        }
    }

    /**
     * 存通用数据
     * @param bodyJson
     */
    private void saveBaseData(String bodyJson){

        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.BASE_INFO_PATH,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("bodyInfo",bodyJson);
        edit.commit();
    }

    /**
     * 存Config文件内容
     * @param configJson
     */
    public void saveInitConfig(String configJson){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.INIT_CONFIG_PATH,MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("config",configJson);
        edit.commit();
    }

    /**
     * 存储config文件
     * @return
     */
    public String readConfig(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constant.INIT_CONFIG_PATH,MODE_PRIVATE);
        String config = sharedPreferences.getString("config", "");
        return config;

    }

    /**
     * 获取屏幕宽高
     * @return
     */
    public String getDisplayInfo(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Activity activity = (Activity)context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        String display =  displayMetrics.widthPixels + "|" + displayMetrics.heightPixels;
        return display;
    }

    /**
     * 检查权限
     * @param permissions
     * @return
     */
    public boolean checkPermissions(String[] permissions){
        Activity activity = (Activity)context;
        for(String per :permissions){
            if(ActivityCompat.checkSelfPermission(context,per) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断权限申请结果
     * @param grantResults
     * @return
     */
    public boolean permissionResult(int[] grantResults){
        for (int result : grantResults){
            if(result != PERMISSION_GRANTED){
               return false;
            }
        }
        return true;
    }

}
