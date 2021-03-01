package com.cairin.cash.network;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import com.cairin.cash.utils.Constant;
import org.jetbrains.annotations.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetFactory {
    private IRequest_Result request_result;

    private static volatile NetFactory factory = null;


    private NetFactory(IRequest_Result request_result){
        this.request_result = request_result;
    }

    public static NetFactory getInstance(IRequest_Result request_result){
        if(factory == null){
            synchronized (NetFactory.class){
                if(factory == null){
                    factory = new NetFactory(request_result);
                }
            }
        }
        return factory;
    }


    public void uploadBitmap(String uploadUrl, Bitmap bitmap, String key, String value) {

        MediaType mediaType = MediaType.parse("application/octet-stream;charset=UTF-8");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        RequestBody body = RequestBody.create(data, mediaType);

        final Request request = new Request.Builder()
                .url(uploadUrl)
                .addHeader(key, value)
                .put(body)
                .build();

        getOkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        request_result.success(Constant.UPLOAD_BITMAP);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        if (response.isSuccessful()) {
                            request_result.success(Constant.UPLOAD_BITMAP);
                        } else {
                            request_result.fail(Constant.UPLOAD_BITMAP);
                        }
                    }
                });
    }

    private OkHttpClient getOkHttpClient(){
        //修改各种 Timeout
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS) // 设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS) // 设置写的超时时间
                .connectTimeout(10, TimeUnit.SECONDS) // 设置连接超时时间
                .build();
        return client;
    }


    public void uploadDevice(String uploadUrl, String filePath, String key, String value) {

        MediaType mediaType = MediaType.parse("application/octet-stream;charset=UTF-8");

        File file = new File(filePath);
        RequestBody body = RequestBody.create(file, mediaType);

        final Request request = new Request.Builder()
                .url(uploadUrl)
                .addHeader(key, value)
                .put(body)
                .build();

        getOkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        request_result.fail(Constant.UPLOAD_DEVICE_DATA);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        if (response.isSuccessful()) {
                            request_result.success(Constant.UPLOAD_DEVICE_DATA);
                        } else {
                            request_result.fail(Constant.UPLOAD_DEVICE_DATA);
                        }
                    }
         });
    }

    /**
     * 上传通讯录
     *
     * @param uploadUrl
     * @param jsonStr
     */
    public void getDataAsync(String uploadUrl, String jsonStr, String key, String value) {

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonStr, mediaType);

        final Request request = new Request.Builder()
                .url(uploadUrl)
                .addHeader(key, value)
                .put(body)
                .build();

        getOkHttpClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        request_result.fail(Constant.UPLOAD_CONTACTS);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        if (response.isSuccessful()) {
                            request_result.success(Constant.UPLOAD_CONTACTS);
                        } else {
                            request_result.fail(Constant.UPLOAD_CONTACTS);
                            try {
                                throw new IOException("Unexpected code " + response);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

}
