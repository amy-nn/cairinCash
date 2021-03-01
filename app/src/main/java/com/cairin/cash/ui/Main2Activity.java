package com.cairin.cash.ui;

import android.Manifest;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.cairin.cash.R;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main2Activity extends AppCompatActivity {

    String file = Environment.getExternalStorageDirectory().getPath()+"/Pictures/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActivityCompat.requestPermissions(
                this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},200);

        File file1 = new File(file);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream inputStream = new FileOutputStream(new File(file1,"a.txt"));
            inputStream.write("你好".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void read(View view) {
        ExclusionStrategy myExclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fa) {
                if("isSuccess".equals(fa.getName()))
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

        User user = new User();
        user.name = "aaa";
        user.isSuccess = false;
        user.mobile = "aa";
        String json = gson.toJson(user);

        Log.d("amy", "read: "+json);
     }

     class User{
        boolean isSuccess;
        String name;
        String mobile;
     }
}
