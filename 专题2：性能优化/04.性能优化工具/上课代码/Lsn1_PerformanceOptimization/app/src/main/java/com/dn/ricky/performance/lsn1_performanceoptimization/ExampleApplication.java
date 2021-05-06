package com.dn.ricky.performance.lsn1_performanceoptimization;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;


/**
 * Created by ricky on 2016/11/9.
 */

public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
        LeakCanary.install(this);
    }
}
