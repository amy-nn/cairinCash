package com.cairin.cash;

import android.app.Application;
import com.abroad.crawllibrary.main.CrawlMainHandler;

import go.Seq;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Seq.setContext(getApplicationContext());

        CrawlMainHandler.init(this);
    }
}
