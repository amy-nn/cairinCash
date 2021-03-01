package com.cairin.cash.ui;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.abroad.crawllibrary.main.CrawlMainHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cairin.cash.R;
import com.cairin.cash.entity.AppInfo;
import com.cairin.cash.entity.AppListInfo;
import com.cairin.cash.entity.BodyInfo;
import com.cairin.cash.entity.Config;
import com.cairin.cash.entity.ContactsInfo;
import com.cairin.cash.entity.DeviceInfo;
import com.cairin.cash.network.IRequest_Result;
import com.cairin.cash.network.LoadDataFactory;
import com.cairin.cash.network.NetFactory;
import com.cairin.cash.utils.Constant;
import com.cairin.cash.utils.EnvermentUtil;
import com.cairin.cash.utils.PhotoUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ai.advance.liveness.lib.GuardianLivenessDetectionSDK;
import ai.advance.liveness.lib.LivenessResult;
import ai.advance.liveness.lib.Market;
import ai.advance.liveness.sdk.activity.LivenessActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.support.v4.content.PermissionChecker.PERMISSION_GRANTED;


public class MainActivity extends AppCompatActivity implements IRequest_Result {

    private WebView webview;
    private String configJson;
    private String entrypoint;
    private Gson gson;
    private String data_send;
    private String callback_send;
    private int version_send;
    private Config config;
    private String accessKey = "";
    private String secretKey = "";
    private String upload_bitmap_url = "";
    private String upload_bitmap_key = "";
    private String livenessId;// 本次活体id
    private String transactionId;
    private Uri imageUri;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;
    private ValueCallback<Uri> mUploadMessage;
    private String deviceInfoUrl;
    private String deviceInfoKey;
    private LoadDataFactory factory;
    private int waitTime;
    private String upUrl;
    private String key;
    private boolean isGoBack;
    private Uri uri;
    private String upDeviceCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview = findViewById(R.id.wv_main);
        factory = LoadDataFactory.getInstance(this);

        initHomePath();
        initView();
    }

    /**
     * 解析访问路径
     */
    private void initHomePath() {
        gson = new Gson();
        configJson = factory.readConfig();//getIntent().getStringExtra("configJson");
        if(!configJson.isEmpty())
        {
            config = gson.fromJson(configJson, Config.class);
            entrypoint = config.getAppInfo().getEntrypoint();

            List<Config.ExtendList> extendList = config.getExtendList();
            for (Config.ExtendList extendInfo : extendList) {

                if (extendInfo.getExtendCode().equals("ld_api_secret_key")) {
                    secretKey = extendInfo.getExtendValue();
                } else if (extendInfo.getExtendCode().equals("ld_api_access_key")) {
                    accessKey = extendInfo.getExtendValue();
                }
            }
        }
    }

    private void wewViewSettings() {
        WebSettings settings = webview.getSettings();

        settings.setJavaScriptEnabled(true);

        settings.setLoadWithOverviewMode(true); // 设置可以加载更多格式页面
        settings.setUseWideViewPort(true); // 使用广泛的视窗
        settings.setDomStorageEnabled(true); // 启用Dom storage api
        settings.setJavaScriptCanOpenWindowsAutomatically(true); // 自动打开窗口
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS); // 排版适应屏幕
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        settings.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        settings.setAllowFileAccessFromFileURLs(true);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        settings.setAllowUniversalAccessFromFileURLs(true);
        //开启JavaScript支持
        settings.setJavaScriptEnabled(true);
        // 支持缩放
        settings.setSupportZoom(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    private void checkBackKey(final boolean isGoBack) {
        webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (KeyEvent.KEYCODE_BACK == keyCode && 0 == event.getRepeatCount() && KeyEvent.ACTION_DOWN == event.getAction()) {

                    if (!isGoBack) {
                        //如果不禁用返回键，则正常返回
                        if (webview != null && webview.canGoBack()) {
                            webview.goBack();
                        }
                    } else {
                        //禁用返回键，则什么都不做
                    }
                }
                return false;
            }
        });
    }

    private void initView() {

        wewViewSettings();
        WebChromeClient webChromeClient = new WebChromeClient() {
            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }

            // For Android 3.0-
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},Constant.PERMISSIONS_CAMERA);
                    return;
                }
                showOptions(MainActivity.this);
            }

            // For Android 3.0+
            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},Constant.PERMISSIONS_CAMERA);
                    return;
                }
                showOptions(MainActivity.this);
            }

            //For Android 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},Constant.PERMISSIONS_CAMERA);
                    return;
                }
                showOptions(MainActivity.this);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

                mUploadCallbackAboveL = filePathCallback;
                if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},Constant.PERMISSIONS_CAMERA);
                }else {
                    showOptions(MainActivity.this);
                }
                return true;
            }
        };
        webview.setWebChromeClient(webChromeClient);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {

                    uri = Uri.parse(url);
                    String scheme = uri.getScheme();
                    String host = uri.getHost();
                    Log.d("amy", "host: " + host);
                    if (scheme.equals("wode-schema")) {
                        String data = uri.getQueryParameter("data");
                        final String callback = uri.getQueryParameter("callback");
                        final int version = Build.VERSION.SDK_INT;

                        data_send = data;
                        callback_send = callback;
                        version_send = version;

                        if (host.equals("appInfo")) {
                            //处理AppInfo的业务逻辑

                            AppInfo appInfo = new AppInfo();
                            appInfo.setPackageName(getPackageName());
                            appInfo.setBundle_id(getPackageName());
                            appInfo.setChannelKey(Constant.CHANNEL_KEY);
                            appInfo.setChannel_code(Constant.CHANNEL_KEY);
                            appInfo.setChannel_name(Constant.CHANNEL_NAME);
                            appInfo.setVersion(Integer.parseInt(Constant.VERSION));
                            appInfo.setPlatform(Constant.PLATFORM);
                            appInfo.setPackageType("gp");
                            if (!factory.bodyJson.isEmpty()) {
                                BodyInfo bodyInfo = gson.fromJson(factory.bodyJson, BodyInfo.class);
                                appInfo.setDeviceInfo(bodyInfo.getDeviceInfo());
                                if (bodyInfo.getDeviceId().isEmpty() || bodyInfo.getDeviceToken().isEmpty()) {
                                    appInfo.setDeviceId("4b141e2e-f138-42a7-9261-bee25e1773ca");
                                    appInfo.setDeviceToken("fdea234c-58a8-422f-b426-b22a87b12549");
                                } else {
                                    appInfo.setDeviceId(bodyInfo.getDeviceId());
                                    appInfo.setDeviceToken(bodyInfo.getDeviceToken());
                                }
                            } else {
                                appInfo.setDeviceInfo("");
                                appInfo.setDeviceId("");
                                appInfo.setDeviceToken("");
                            }
                            appInfo.setCountry(Constant.COUNTRY);
                            appInfo.setVersionName(Constant.VERSION_NAME);
                            appInfo.setLocal("id-ID");
                            appInfo.setTerminal_name(Constant.APPLICATION_NAME);

                            String appInfoJson = gson.toJson(appInfo, AppInfo.class);

                            sendData(appInfoJson);
                        } else if (host.equals("updateUid")) {
                            //处理更新uid的业务逻辑
                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            String uid = (String) responseDataObject.get("uid");
                            if (!uid.equals("")) {
                                /**
                                 * 存通用数据
                                 */
                                updateUID(uid);
                            } else {
                                //执行退出操作，清理缓存(回到首页）
                                clearShared();
                            }
                        } else if (host.equals("reload")) {
                            webview.reload();
                        } else if (host.equals("closeNewWebview") || host.equals("goHome")) {
                            webview.loadUrl(entrypoint);
                            finish();
                        } else if (host.equals("jumpUrlInner")) {

                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            String innerUrl = responseDataObject.getString("url");
                            innerUrl = java.net.URLDecoder.decode(innerUrl, "utf-8");
                            boolean isShowTitleBar = responseDataObject.getBoolean("titleBar"); //是否显示TitleBar
                            final boolean isGoBack = responseDataObject.getBoolean("disabledGoBack"); //是否禁用返回键
//
//                            if (isShowTitleBar) {
//                                //显示标题栏
//                                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
//                            } else {
//                                //隐藏标题栏
//                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//显示状态栏
//                            }
//
//                            checkBackKey(isGoBack);

                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            intent.putExtra("innerUrl",innerUrl);
                            intent.putExtra("isShowTitleBar",isShowTitleBar);
                            intent.putExtra("isGoBack",isGoBack);
                            intent.putExtra("data_send",data_send);
                            intent.putExtra("callback_send",callback_send);
                            intent.putExtra("version_send",version_send);
                            startActivity(intent);
                        } else if (host.equals("getAL")) {

                            ArrayList<AppListInfo> list = new ArrayList<>();
                            PackageManager pManager = getPackageManager();
                            //获取手机内所有应用
                            List<PackageInfo> packageList = pManager.getInstalledPackages(0);
                            for (int i = 0; i < packageList.size(); i++) {
                                PackageInfo pInfo = (PackageInfo) packageList.get(i);

                                AppListInfo listInfo = new AppListInfo();
                                listInfo.setApp_name(pInfo.applicationInfo.loadLabel(pManager).toString());
                                listInfo.setBundle_id(pInfo.packageName);
                                listInfo.setInstallTime(pInfo.firstInstallTime);
                                listInfo.setUpdateTime(pInfo.lastUpdateTime);
                                listInfo.setVersionName(pInfo.versionName);
                                listInfo.setVersionCode(pInfo.versionCode);

                                if ((ApplicationInfo.FLAG_SYSTEM & pInfo.applicationInfo.flags) != 0) {
                                    listInfo.setSystemAppFlag(true);
                                }
                                else
                                    listInfo.setSystemAppFlag(false);
                                listInfo.setAppTag(pInfo.applicationInfo.flags);
                                list.add(listInfo);
                            }
                            String paramJson = gson.toJson(list);

                            if (version < 18) {
                                webview.loadUrl("javascript: " + callback + "(" + paramJson + ")");
                            } else {
                                webview.evaluateJavascript("javascript: " + callback + "(" + paramJson + ")", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String value) {
                                        //此处为 js 返回的结果
                                    }
                                });
                            }
                         } else if (host.equals("getGPS")) {

                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            waitTime = responseDataObject.getInteger("waitTime");

                            String[] permissStr = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
                            if (factory.checkPermissions(permissStr)) {
                                getLocation(waitTime, callback, version);
                            } else {
                                //申请权限
                                ActivityCompat.requestPermissions(MainActivity.this, permissStr, Constant.PERMISSIONS_GPS);
                            }
                        } else if (host.equals("uploadAB")) {

                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            upUrl = responseDataObject.getString("url");
                            key = responseDataObject.getString("key");

                            String[] perLoadAB = new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,
                            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            if (factory.checkPermissions(perLoadAB)) {
                                String contactsInfo = getContacts();
                                NetFactory.getInstance(MainActivity.this).getDataAsync(upUrl, contactsInfo, key, getBodyInfo().getUid());
                            } else {
                                ActivityCompat.requestPermissions(MainActivity.this, perLoadAB, Constant.PERMISSIONS_CONTRACT);
                            }
                        } else if (host.equals("huoti")) {
                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            upload_bitmap_url = responseDataObject.getString("url");
                            upload_bitmap_key = responseDataObject.getString("key");

                            checkHuoTi(accessKey, secretKey);
                        } else if (host.equals("disabledGoBack")) {
                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            boolean upUrl = responseDataObject.getBoolean("disabled");

                        } else if (host.equals("exitApp")) {
                            finish();
                        } else if (host.equals("jumpSetting")) {
                            toSelfSetting(MainActivity.this);
                        } else if (host.equals("jumpUrlOuter")) {

                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            String jumpJrl = responseDataObject.getString("url");
                            jumpJrl = java.net.URLDecoder.decode(jumpJrl, "utf-8");
                            Uri uriOuter = Uri.parse(jumpJrl);
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            intent.setData(uriOuter);
                            startActivity(intent);
                        } else if (host.equals("close")) {

                        } else if (host.equals("applyPermission")) {
                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            String permissions = responseDataObject.getString("permissions");
                            String[] split = permissions.split(",");
                            String[] permissionNames = new String[split.length];
                            for (int i = 0; i < split.length; i++) {
                                String permission = split[i];
                                if (permission.equals("gps")) {
                                    permissionNames[i] = Manifest.permission.ACCESS_FINE_LOCATION;
                                } else if (permission.equals("readPhoneState")) {
                                    permissionNames[i] = Manifest.permission.READ_PHONE_STATE;
                                } else if (permission.equals("camera")) {
                                    permissionNames[i] = Manifest.permission.CAMERA;
                                } else if (permission.equals("contacts")) {
                                    permissionNames[i] = Manifest.permission.READ_CONTACTS;
                                } else if (permission.equals("write")) {
                                    permissionNames[i] = Manifest.permission.WRITE_EXTERNAL_STORAGE;
                                }
                            }

                            if (permissionNames.length > 0) {
                                boolean result = true;
                                for (int i = 0; i < permissionNames.length; i++) {
                                    if (ContextCompat.checkSelfPermission(MainActivity.this, permissionNames[i]) == PERMISSION_GRANTED) {
                                        result = false;
                                        break;
                                    }
                                }
                                if (!result) {
                                    ActivityCompat.requestPermissions(MainActivity.this, permissionNames, Constant.PERMISSION_REQUEST_CODE);
                                } else {
                                    String resultJson = "{\"isSuccess\":true}";
                                    sendData(resultJson);
                                }
                            }

                        } else if (host.equals("applyCamera")) {

                            String[] permissions = new String[]{
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            boolean isGranted = true;
                            for (String permission : permissions) {
                                if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PERMISSION_GRANTED) {
                                    isGranted = false;
                                    break;
                                }
                            }

                            if (!isGranted) {
                                ActivityCompat.requestPermissions(MainActivity.this, permissions, Constant.PERMISSION_CAMERA_FILE_CODE);
                            } else {

                            }
                        } else if (host.equals("chooseLinkman")) {

                            String[] per_contacts = new String[]{Manifest.permission.READ_CONTACTS};
                            if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                               ActivityCompat.requestPermissions(MainActivity.this,per_contacts,Constant.PERMISSIONS_READ_CONTRACT);
                            }
                            else {
                                openContacts();
                            }

                        } else if (host.equals("uploadDeviceInfo")) {
                            JSONObject responseDataObject = JSONObject.parseObject(data);
                            deviceInfoUrl = responseDataObject.getString("url");
                            deviceInfoKey = responseDataObject.getString("key");
                            Log.d("amy", "uploadDeviceInfo: "+callback_send);
                            saveDeviceInfo(callback_send);
                            new MyAsyncTask().execute();
                        }
                        return true;
                    } else {
                        if (!TextUtils.isEmpty(host) && host.equals("play.google.com")) {
                            //host为play.google.com的,因Firebase统计归因的问题用外部浏览器打开
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(url));
                            startActivity(intent);
                            return true;
                        } else {
                            webview.loadUrl(url);
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null){
            String innerUrl = intent.getStringExtra("innerUrl");
            if(innerUrl != null && !innerUrl.isEmpty()){
                webview.loadUrl(innerUrl);

                boolean isShowTitleBar = intent.getBooleanExtra("isShowTitleBar",false); //是否显示TitleBar
                isGoBack = intent.getBooleanExtra("isGoBack",false); //是否禁用返回键
                callback_send = intent.getStringExtra("callback_send");
                version_send = intent.getIntExtra("version_send",0);
                data_send = intent.getStringExtra("data_send");

                if (isShowTitleBar) {
                    //显示标题栏
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
                } else {
                    //隐藏标题栏
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);//显示状态栏
                }

                checkBackKey(isGoBack);
            }
            else
                webview.loadUrl(entrypoint);
        }
        else
            webview.loadUrl(entrypoint);

    }

    /**
     * 保存上传设备地址callback
     */
    private void saveDeviceInfo(String callback_send){
        SharedPreferences sharedPreferences = getSharedPreferences("callback",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("call",callback_send);
        editor.commit();
    }

    /**
     * 读取设备callback
     * @return
     */
    private String readDeviceInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("callback",MODE_PRIVATE);
        String call = sharedPreferences.getString("call", "");
        return call;
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (KeyEvent.KEYCODE_BACK == keyCode && 0 == event.getRepeatCount() && KeyEvent.ACTION_DOWN == event.getAction()) {
//
//            if (!isGoBack) {
//                //如果不禁用返回键，则正常返回
//                if (webview != null && webview.canGoBack()) {
//                    webview.goBack();
//                }
//            } else {
//                //禁用返回键，则什么都不做
//            }
//        }
//        return false;
//    }

    /**
     * 获得AppInfo信息
     *
     * @return
     */
    private void updateUID(String newUID) {

        SharedPreferences sharedPreferences = getSharedPreferences("base_info", MODE_PRIVATE);
        String bodyJson = sharedPreferences.getString("bodyInfo", "");
        if (bodyJson != null && !bodyJson.isEmpty()) {
            BodyInfo bodyInfo = gson.fromJson(bodyJson, BodyInfo.class);
            bodyInfo.setUid(newUID);
            String newJson = gson.toJson(bodyInfo);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("bodyInfo", newJson);
            edit.commit();
        }
    }

    /**
     * 退出时清空数据
     */
    private void clearShared() {
        SharedPreferences sharedPreferences = getSharedPreferences("base_info", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.clear();
        edit.commit();
    }

    private BodyInfo getBodyInfo() {
        BodyInfo bodyInfo = null;
        SharedPreferences sharedPreferences = getSharedPreferences("base_info", MODE_PRIVATE);
        String bodyJson = sharedPreferences.getString("bodyInfo", "");
        if (bodyJson != null && !bodyJson.isEmpty()) {
            bodyInfo = gson.fromJson(bodyJson, BodyInfo.class);
            return bodyInfo;
        }
        else {
            if(factory != null){
                bodyInfo = gson.fromJson(factory.initData(), BodyInfo.class);
            }
        }
        return bodyInfo;
    }

    private void getLocation(int time, String callback, int version) {

        boolean isSuccess = false;
        boolean isgpsState = false;
        String longitude = 0 + "";
        String latitude = 0 + "";

        LocationListener mLocationListener01 = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                }
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        };

        //获取定位服务
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //判断是否开启GPS定位功能
        //boolean isGpsEnabled = locationManager.isProviderEnabled("android.location.LocationManager.GPS_PROVIDER");
        //定位类型：GPS
        String locateType = locationManager.GPS_PROVIDER;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            String paramJson = "{\"isSuccess\":" + false + ",\"gpsState\":" + false + ",\"longitude\":\"" + 0 + "\",\"latitude\":\"" + 0 + "\"}";
            sendData(paramJson);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10,
                mLocationListener01);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10,
                mLocationListener01);

        Location location = locationManager.getLastKnownLocation(locateType); // 通过GPS获取位置
        if (location != null) {

            isSuccess = true;
            isgpsState = true;
            longitude = location.getLongitude() + "";
            latitude = location.getLatitude() + "";
        } else {

            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            if (location != null) {

                isSuccess = true;
                isgpsState = true;
                longitude = location.getLongitude() + "";
                latitude = location.getLatitude() + "";
            }
        }

        String paramJson = "{\"isSuccess\":" + isSuccess + ",\"gpsState\":" + isgpsState + ",\"longitude\":\"" + longitude + "\",\"latitude\":\"" + latitude + "\"}";
        sendData(paramJson);
    }

    /**
     * 获得手机联系人
     *
     * @return
     */
    public String getContacts() {

        ContactsInfo info;
        ContactsInfo.Contacts contactBean;
        List<ContactsInfo.Contacts> list;

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            String abJson = "{\"isSuccess\":false}";
            sendData(abJson);
        }

        info = new ContactsInfo();
        list = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();
        //查找所有的联系人ID
        Cursor cursor = contentResolver.query(android.provider.ContactsContract.Contacts.CONTENT_URI,
                new String[]{android.provider.ContactsContract.Contacts._ID}, null, null, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {

                String id = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));

                //根据id查联系人的信息
                Cursor contactInfoCursor = contentResolver.query(
                        ContactsContract.Data.CONTENT_URI,
                        new String[]{}, ContactsContract.Data.CONTACT_ID + "=" + id, null, null);

                contactBean = new ContactsInfo.Contacts();
                contactBean.setContactId(id);

                if (null != contactInfoCursor) {
                    while (contactInfoCursor.moveToNext()) {

                        String mimetype = contactInfoCursor.getString(
                                contactInfoCursor.getColumnIndex(ContactsContract.Data.MIMETYPE));

                        if (TextUtils.isEmpty(mimetype)) {
                            contactInfoCursor.close();
                            break;
                        }
                        //根据mimetype的类型查询相应的数据
                        if (mimetype.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
                            if (!contactInfoCursor.isNull(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE))) {
                                //部分mimetype下的数据还有根据具体的type来区分数据的类型
                                int phoneType = contactInfoCursor.getInt(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));

                                if (!contactInfoCursor.isNull(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))) {
                                    String phone = contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                    //此处只做示例，未列举完全
                                    if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                                        contactBean.setMobile(phone);

                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_HOME) {
                                        contactBean.setHomeNum(phone);
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_WORK) {
                                        contactBean.setJobNum(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_FAX_WORK) {
                                        contactBean.setWorkFax(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_FAX_HOME) {
                                        contactBean.setHomeFax(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_PAGER) {
                                        contactBean.setPager(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_CALLBACK) {
                                        contactBean.setQuickNum(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_COMPANY_MAIN) {
                                        contactBean.setJobTel(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_CAR) {
                                        contactBean.setCarNum(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_ISDN) {
                                        contactBean.setIsdn(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MAIN) {
                                        contactBean.setTel(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_RADIO) {
                                        contactBean.setWirelessDev(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_TELEX) {
                                        contactBean.setTelegram(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_TTY_TDD) {
                                        contactBean.setTty_tdd(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_WORK_MOBILE) {
                                        contactBean.setJobMobile(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_WORK_PAGER) {
                                        contactBean.setJobPager(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_ASSISTANT) {
                                        contactBean.setAssistantNum(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_MMS) {
                                        contactBean.setMms(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_OTHER) {
                                        contactBean.setOtherPhone(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    } else if (phoneType == ContactsContract.CommonDataKinds.Phone.TYPE_OTHER_FAX) {
                                        contactBean.setOtherFax(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                                    }
                                }
                            }

                        } else if (mimetype.equals(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)) {
                            contactBean.setDisplayName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME)));
                            contactBean.setLastName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME)));
                            contactBean.setLastUpdatedTimestamp(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.CONTACT_LAST_UPDATED_TIMESTAMP)));
                            contactBean.setPrefix(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PREFIX)));
                            contactBean.setFirstName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME)));
                            contactBean.setMiddleName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME)));
                            contactBean.setSuffix(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.SUFFIX)));
                            contactBean.setPhoneticFirstName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_FAMILY_NAME)));
                            contactBean.setPhoneticMiddleName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_MIDDLE_NAME)));
                            contactBean.setPhoneticLastName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_GIVEN_NAME)));

                        } else if (mimetype.equals(ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)) {
                            contactBean.setOrg(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY)));
                            contactBean.setTitle(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE)));
                            contactBean.setDepartment(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DEPARTMENT)));
                        } else if (mimetype.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
                            int emailType = contactInfoCursor.getInt(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                            if (emailType == ContactsContract.CommonDataKinds.Email.TYPE_WORK) {
                                contactBean.setWorkEmail(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                            } else if (emailType == ContactsContract.CommonDataKinds.Email.TYPE_CUSTOM) {
                                contactBean.setCustomEmail(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                            } else if (emailType == ContactsContract.CommonDataKinds.Email.TYPE_MOBILE) {
                                contactBean.setMobileEmail(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                            } else if (emailType == ContactsContract.CommonDataKinds.Email.TYPE_HOME) {
                                contactBean.setHomeEmail(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                            } else if (emailType == ContactsContract.CommonDataKinds.Email.TYPE_OTHER) {
                                contactBean.setOtherEmail(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)));
                            }

                        } else if (mimetype.equals(ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)) {
                            contactBean.setNote(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE)));
                        } else if (ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE.equals(mimetype)) {
                            int eventType = contactInfoCursor.getInt(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.TYPE));
                            if (eventType == ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY) {
                                contactBean.setBirthday(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE)));
                            } else if (eventType == ContactsContract.CommonDataKinds.Event.TYPE_ANNIVERSARY) {
                                contactBean.setAnniversary(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE)));
                            } else if (eventType == ContactsContract.CommonDataKinds.Event.TYPE_OTHER) {
                                contactBean.setOther(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE)));
                            }

                        } else if (ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE.equals(mimetype)) {
                            int imProtocol = contactInfoCursor.getInt(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.PROTOCOL));
                            if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_AIM) {
                                contactBean.setAim(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_MSN) {
                                contactBean.setMsn(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_YAHOO) {
                                contactBean.setYahoo(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_SKYPE) {
                                contactBean.setSkype(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_QQ) {
                                contactBean.setQq(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_GOOGLE_TALK) {
                                contactBean.setGoogleTalk(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_ICQ) {
                                contactBean.setIcq(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_JABBER) {
                                contactBean.setJabber(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else if (imProtocol == ContactsContract.CommonDataKinds.Im.PROTOCOL_NETMEETING) {
                                contactBean.setNetmeeting(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            } else {
                                contactBean.setOtherIm(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                            }

                        } else if (ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE.equals(mimetype)) {
                            contactBean.setNickName(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.NAME)));

                        } else if (ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE.equals(mimetype)) {
                            int webSiteType = contactInfoCursor.getInt(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.TYPE));
                            if (webSiteType == ContactsContract.CommonDataKinds.Website.TYPE_CUSTOM) {
                                contactBean.setCustomPage(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                            } else if (webSiteType == ContactsContract.CommonDataKinds.Website.TYPE_HOME) {
                                contactBean.setHome(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                            } else if (webSiteType == ContactsContract.CommonDataKinds.Website.TYPE_HOMEPAGE) {
                                contactBean.setHomePage(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                            } else if (webSiteType == ContactsContract.CommonDataKinds.Website.TYPE_WORK) {
                                contactBean.setWorkPage(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                            } else if (webSiteType == ContactsContract.CommonDataKinds.Website.TYPE_FTP) {
                                contactBean.setFtpPage(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                            } else if (webSiteType == ContactsContract.CommonDataKinds.Website.TYPE_OTHER) {
                                contactBean.setOtherPage(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                            }

                        } else if (ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE.equals(mimetype)) {
                            int structureType = contactInfoCursor.getInt(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));
                            if (structureType == ContactsContract.CommonDataKinds.StructuredPostal.TYPE_WORK) {
                                contactBean.setWorkStreet(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET)));
                                contactBean.setWorkCity(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY)));
                                contactBean.setWorkPobox(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX)));
                                contactBean.setWorkNeighborhood(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD)));
                                contactBean.setWorkRegion(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION)));
                                contactBean.setWorkPostcode(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE)));
                                contactBean.setWorkCountry(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY)));
                                contactBean.setWorkFormattedAddress(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS)));

                            } else if (structureType == ContactsContract.CommonDataKinds.StructuredPostal.TYPE_HOME) {
                                contactBean.setHomeStreet(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET)));
                                contactBean.setHomeCity(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY)));
                                contactBean.setHomePobox(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX)));
                                contactBean.setHomeNeighborhood(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD)));
                                contactBean.setHomeRegion(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION)));
                                contactBean.setHomePostcode(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE)));
                                contactBean.setHomeCountry(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY)));
                                contactBean.setHomeFormattedAddress(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS)));

                            } else if (structureType == ContactsContract.CommonDataKinds.StructuredPostal.TYPE_OTHER) {
                                contactBean.setOtherStreet(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET)));
                                contactBean.setOtherCity(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY)));
                                contactBean.setOtherPobox(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX)));
                                contactBean.setOtherNeighborhood(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD)));
                                contactBean.setOtherRegion(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION)));
                                contactBean.setOtherPostcode(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE)));
                                contactBean.setOtherCountry(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY)));
                                contactBean.setOtherFormattedAddress(contactInfoCursor.getString(contactInfoCursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS)));
                            }

                        }
                    }
                    list.add(contactBean);
                    contactInfoCursor.close();
                }
            }
            cursor.close();
        }
        info.setContacts(list);
        Gson gson = new Gson();
        String json = gson.toJson(info, ContactsInfo.class);
        Log.d("amy", "json: " + json);
        return json;
    }

    /**
     * 跳转到应用设置界面
     * @param context
     */
    public static void toSelfSetting(Context context) {

         Intent mIntent = new Intent();
         mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         if (Build.VERSION.SDK_INT >= 9) {
             mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
             mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
         } else if (Build.VERSION.SDK_INT <= 8) {
             mIntent.setAction(Intent.ACTION_VIEW);
             mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
             mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
         }
         context.startActivity(mIntent);

    }



    /**
     * 发送数据
     * @param mapJson
     */
    private void sendData(final String mapJson){
        runOnUiThread(new Thread(){
            @Override
            public void run() {
                super.run();
                if (version_send < 18) {
                    webview.loadUrl("javascript: " + callback_send + "(" + mapJson + ")");
                } else {
                    if(webview == null){
                        webview = findViewById(R.id.wv_main);
                    }
                    Log.d("amy", "run: "+uri);
                    webview.evaluateJavascript("javascript: " + callback_send + "(" + mapJson + ")", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //此处为 js 返回的结果
                        }
                    });
                }
            }
        });
    }

    private void sendDataForUploadDevice(final String mapJson, final String callBack){
        runOnUiThread(new Thread(){
            @Override
            public void run() {
                super.run();
                if (version_send < 18) {
                    webview.loadUrl("javascript: " + callBack + "(" + mapJson + ")");
                } else {
                    if(webview == null){
                        webview = findViewById(R.id.wv_main);
                    }
                    webview.evaluateJavascript("javascript: " + callBack + "(" + mapJson + ")", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //此处为 js 返回的结果
                        }
                    });
                }
            }
        });
    }

    /**
     * 执行活体操作
     */
    private void checkHuoTi(String accessKey, String secretKey){
        if (accessKey == null || secretKey == null) {
            new AlertDialog.Builder(MainActivity.this).setMessage("请在 MainActivity 中给 Key 赋值").setPositiveButton("确定", null).create().show();
        }else {
            GuardianLivenessDetectionSDK.init(getApplication(), accessKey, secretKey, Market.Indonesia);
            checkPermissions();
        }
    }

    /**
     * Detect camera authorization
     */
    public void checkPermissions() {
        if (allPermissionsGranted()) {
            onPermissionGranted();
        } else {
            ActivityCompat.requestPermissions(this, getRequiredPermissions(), Constant.PERMISSIONS_REQUEST_CODE);
        }
    }

    private boolean allPermissionsGranted() {
        for (String permission : getRequiredPermissions()) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public String[] getRequiredPermissions() {
        return new String[]{Manifest.permission.CAMERA};
    }

    /**
     * Got camera permissions
     */
    public void onPermissionGranted() {
        Intent intent = new Intent(this, LivenessActivity.class);
        startActivityForResult(intent, Constant.REQUEST_CODE_LIVENESS);
    }

    /**
     * Denied camera permissions
     */
    public void onPermissionRefused() {
        new AlertDialog.Builder(this).setMessage(getString(R.string.liveness_no_camera_permission)).setPositiveButton(getString(R.string.liveness_perform), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).create().show();
    }

    private boolean allGranted(int[] grantResults) {
        boolean hasPermission = true;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false;
            }
        }
        return hasPermission;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == Constant.PERMISSION_REQUEST_CODE){
            String[] permission_new = new String[permissions.length];
            for (int i = 0;i<permissions.length;i++){
                if(permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)){
                    permission_new[i] = "gps";
                }else if(permissions[i].equals(Manifest.permission.READ_PHONE_STATE)){
                    permission_new[i] = "readPhoneState";
                } else if(permissions[i].equals(Manifest.permission.CAMERA)){
                    permission_new[i] = "camera";
                }else if(permissions[i].equals(Manifest.permission.READ_CONTACTS)){
                    permission_new[i] = "contacts";
                }else if(permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    permission_new[i] = "write";
                }
            }
            if(permission_new != null && permission_new.length>0){
                Map<String, String> map = new HashMap<String, String>();
                for(int i = 0;i<permission_new.length;i++){
                    String key = permission_new[i];
                    String value = grantResults[i]+"";
                    if( key != null && !key.isEmpty()){
                        if(value != null && !value.isEmpty()){
                            map.put(key, value);
                        }
                    }
                }
                String mapJson = JSON.toJSONString(map);
                sendData(mapJson);
            }
        }
        else if(requestCode == Constant.PERMISSION_CAMERA_FILE_CODE){
            boolean grant = true;
            if(grantResults != null){

                grant = factory.permissionResult(grantResults);
                if(grant){
                    sendData("{\"isSuccess\":true}");
                }
                else {
                    sendData("{\"isSuccess\":false}");
                }
            }
            else {
                sendData("{\"isSuccess\":false}");
            }

        }else if (requestCode == Constant.PERMISSIONS_REQUEST_CODE) {
            //已授权
            if (allGranted(grantResults)) {
                onPermissionGranted();
            } else {
                onPermissionRefused();
            }
        }else if(requestCode == Constant.PERMISSIONS_GPS){
            boolean result = factory.permissionResult(grantResults);
            if(result){
                getLocation(waitTime, callback_send, version_send);
            }
            else {
                String paramJson = "{\"isSuccess\":" + false + ",\"gpsState\":" + false + ",\"longitude\":\"" + 0 + "\",\"latitude\":\"" + 0 + "\"}";
                sendData(paramJson);
            }
        }else if(requestCode == Constant.PERMISSIONS_CONTRACT){
            boolean result = factory.permissionResult(grantResults);
            if(result){
                String contactsInfo = getContacts();
                NetFactory.getInstance(MainActivity.this).getDataAsync(upUrl, contactsInfo, key, getBodyInfo().getUid());
            }else {
                String abJson = "{\"isSuccess\":false}";
                sendData(abJson);
            }
        }else if(requestCode == Constant.PERMISSIONS_CAMERA){
            boolean result = factory.permissionResult(grantResults);
            if(result){
                showOptions(this);
            }
            else {
                cancelTakePicture();
            }
        }else if(requestCode == Constant.PERMISSIONS_READ_CONTRACT){
            boolean result = factory.permissionResult(grantResults);
            if(result){
                openContacts();
            }
        }
    }

    /**
     * 包含拍照和相册选择
     */
    public void showOptions(final Activity act) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(act);
        alertDialog.setOnCancelListener(new ReOnCancelListener());
        alertDialog.setTitle("pilihan");
        alertDialog.setItems(new CharSequence[]{"kamera", "album"},
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            takePicture();
                        } else {
                            Intent i = new Intent(
                                    Intent.ACTION_PICK,
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// 调用android的图库
                            act.startActivityForResult(i,
                                    Constant.TYPE_GALLERY);
                        }
                    }
                });
        alertDialog.show();
    }

    /**
     * 获取检测结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Cursor c = null;
        if (requestCode == Constant.REQUEST_CODE_LIVENESS) {
            if (LivenessResult.isSuccess()) {// 活体检测成功
                 livenessId = LivenessResult.getLivenessId();// 本次活体id
                 transactionId = LivenessResult.getTransactionId();
                Bitmap livenessBitmap = LivenessResult.getLivenessBitmap();// 本次活体图片
                Log.d("amy", "onActivityResult:success ");
                //上传服务器
                if(livenessBitmap != null)
                {
                    NetFactory factory = NetFactory.getInstance(this);
                    factory.uploadBitmap(upload_bitmap_url,livenessBitmap,upload_bitmap_key,getBodyInfo().getUid());
                }

            } else {// 活体检测失败
                String errorCode = LivenessResult.getErrorCode();// 失败错误码
                String errorMsg = LivenessResult.getErrorMsg();// 失败原因
                Log.d("amy", "onActivityResult: "+errorCode+"----"+"errorMsg:"+errorMsg);
                String json = "{\"isSuccess\":false,\"livenessId\":\"\",\"transactionId\":\"\"}";
                sendData(json);
            }
        }
        else if(requestCode == Constant.PHOTO_REQUEST){
            if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (mUploadCallbackAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (mUploadMessage != null) {
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }else if (requestCode == Constant.TYPE_GALLERY) {// 相册选择
            if (mUploadCallbackAboveL != null) {
                mUploadCallbackAboveL.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
                mUploadCallbackAboveL = null;
            } else if (mUploadMessage != null) {
                Uri result = data == null || resultCode != Activity.RESULT_OK ? null : data.getData();
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
            }
        }
        else if (requestCode == Constant.TYPE_CONTACTS) {// 电话本
            //联系人名字
            String displayName = "";
            String json = "{\"isSuccess\":false,\"name\":\""+"" +"\",\"mobile\":\""+ ""+"\"}";
            try {
                if (data != null) {
                    c = getContentResolver().query(data.getData(), null, null,
                            null, null);
                    if (c != null) {
                        c.moveToFirst();
                        for (int i = 0; i < c.getColumnCount(); i++) {
                            String name = c.getColumnName(i);
                            displayName = c.getString(i);
                            if (name.equalsIgnoreCase("display_name")) {
                                Log.d("amy", "displayName: "+displayName);
                                break;
                            }
                        }
                        //电话号码
                        String contactPhone = getContactPhone(c);
                        Log.d("amy", "number: "+contactPhone);
                        c.close();

                        json = "{\"isSuccess\":true,\"name\":\""+displayName +"\",\"mobile\":\""+ contactPhone+"\"}";
                        sendData(json);

                    } else{

                        sendData(json);
                    }
                }
                else {
                    sendData(json);
                }

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    /**
     * 读取选择人的电话号码
     * @param cursor
     * @return
     */
    private String getContactPhone(Cursor cursor) {
        int phoneColumn = cursor
                .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
        int phoneNum = cursor.getInt(phoneColumn);
        String phoneResult = "";
        if (phoneNum > 0) {
            // 获得联系人的ID号
            int idColumn = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            String contactId = cursor.getString(idColumn);
            // 获得联系人的电话号码的cursor;
            Cursor phones = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                            + contactId, null, null);
            if (phones.moveToFirst()) {
                // 遍历所有的电话号码
                for (; !phones.isAfterLast(); phones.moveToNext()) {
                    int index = phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    int typeindex = phones
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE);
                    int phone_type = phones.getInt(typeindex);
                    String phoneNumber = phones.getString(index);
                    switch (phone_type) {
                        case 2:
                            phoneResult = phoneNumber;
                            break;
                    }
                }
                if (!phones.isClosed()) {
                    phones.close();
                }
            }
        }
        return phoneResult;
    }

    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != Constant.PHOTO_REQUEST || mUploadCallbackAboveL == null) {
            return;
        }
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                results = new Uri[]{imageUri};
            } else {
                String dataString = data.getDataString();
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }

                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
    }

    private void takePicture(){
        File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/" + SystemClock.currentThreadTimeMillis() + ".jpg");
        imageUri = Uri.fromFile(fileUri);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(MainActivity.this, "com.cairin.cash" + ".fileprovider", fileUri);//通过FileProvider创建一个content类型的Uri
        }
        PhotoUtils.takePicture(MainActivity.this, imageUri, Constant.PHOTO_REQUEST);
    }

    /**
     * 打开手机通记录
     */
    private void openContacts(){
//        Intent contacts = new Intent(Intent.ACTION_PICK,
//                android.provider.Contacts.People.CONTENT_URI);
//        startActivityForResult(contacts, Constant.TYPE_CONTACTS);

        //小米可以上传成功
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivityForResult(intent, Constant.TYPE_CONTACTS);

        //小米的无法上传成功
//        Intent intent2 = new Intent(Intent.ACTION_PICK,
//                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
//        startActivityForResult(intent2, Constant.TYPE_CONTACTS);

    }

    /**
     * 点击取消的回调
     */
    private class ReOnCancelListener implements
            DialogInterface.OnCancelListener {

        @Override
        public void onCancel(DialogInterface dialogInterface) {
            cancelTakePicture();
        }
    }

    /**
     * 取消调用相机
     */
    private void cancelTakePicture(){
        if (mUploadMessage != null) {
            mUploadMessage.onReceiveValue(null);
            mUploadMessage = null;
        }
        if (mUploadCallbackAboveL != null) {
            mUploadCallbackAboveL.onReceiveValue(null);
            mUploadCallbackAboveL = null;
        }
    }

    @Override
    public void success(String message) {
        switch (message){
            case Constant.UPLOAD_BITMAP:
                Log.d("amy", "UPLOAD_BITMAP: "+callback_send);
                String bitmapJson = "{\"isSuccess\":true,\"livenessId\":\""+ livenessId+"\",\"transactionId\":\""+ transactionId+"\"}";
                sendData(bitmapJson);
                break;
            case Constant.UPLOAD_DEVICE_DATA:

                String deviceJson = "{\"isSuccess\":true}";
                String callBack = readDeviceInfo();
                Log.d("amy", "UPLOAD_DEVICE_DATA: "+callBack);
                sendDataForUploadDevice(deviceJson,callBack);
                break;
            case Constant.UPLOAD_CONTACTS:
                Log.d("amy", "UPLOAD_CONTACTS: "+callback_send);
                String contactJson = "{\"isSuccess\":true}";
                sendData(contactJson);
                Log.d("amy", "success: UPLOAD_CONTACTS");
                break;
        }
    }

    @Override
    public void fail(String message) {
        switch (message){
            case Constant.UPLOAD_BITMAP:
                String bitmapJson = "{\"isSuccess\":false,\"livenessId\":\"\",\"transactionId\":\"\"}";
                sendData(bitmapJson);
                break;
            case Constant.UPLOAD_DEVICE_DATA:
                String deviceJson = "{\"isSuccess\":false}";
                String callBack = readDeviceInfo();
                sendDataForUploadDevice(deviceJson,callBack);
                break;
            case Constant.UPLOAD_CONTACTS:
                String contactJson = "{\"isSuccess\":false}";
                sendData(contactJson);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webview != null) {
            webview.setWebViewClient(null);
            webview.setWebChromeClient(null);
            webview.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webview.clearHistory();
            webview.destroy();
            webview = null;
        }
    }


    class MyAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            try {
                String result = CrawlMainHandler.getDeviceInfo();
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                //下载的信息存文件，然后再传给服务器
                EnvermentUtil.getInstance().saveDeviceInfo(s);
                String filePath = Environment.getExternalStorageDirectory()+"/"+Constant.CASH_FILE_PATH;
                Log.d("amy", "onPostExecute: "+filePath);
                //异步上传设备信息
                NetFactory.getInstance(MainActivity.this).uploadDevice(deviceInfoUrl,filePath,deviceInfoKey,getBodyInfo().getUid());
                Log.d("amy", "onPostExecute: "+callback_send);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
