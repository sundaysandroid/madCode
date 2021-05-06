package com.dn.ricky.performance.lsn1_performanceoptimization;

import android.view.View;

import java.util.WeakHashMap;

/**
 * Created by ricky on 2016/11/4.
 */

public class ListenerCollector {
    static private WeakHashMap<View,MyView.MyListener> sListener = new WeakHashMap<>();
    public void setsListener(View view, MyView.MyListener listener){ sListener.put(view,listener);}
    public static void clearListeners(){
        //移除所有监听。
        sListener.clear();
    };
}
