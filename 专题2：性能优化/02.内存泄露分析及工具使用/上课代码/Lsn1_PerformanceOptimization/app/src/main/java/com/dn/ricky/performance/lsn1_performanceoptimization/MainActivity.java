package com.dn.ricky.performance.lsn1_performanceoptimization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        CommUtil commUtil = CommUtil.getInstance(this);
        //解决办法：使用Application的上下文
        //CommonUtil生命周期跟MainActivity不一致，而是跟Application进程同生同死。
//        CommUtil commUtil = CommUtil.getInstance(getApplicationContext());


        MyView myView = new MyView(this);
        setContentView(myView);

    }

    @Override
    protected void onStop() {
        super.onStop();
        ListenerCollector.clearListeners();
    }
}
