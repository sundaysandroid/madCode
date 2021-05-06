package com.dn.ricky.performance.lsn1_performanceoptimization;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ricky on 2016/11/4.
 */

public class MyView extends View{
    public  MyView(Context context){
        super(context);
        init();
    }

//    public MyView(Context context, AttributeSet attrs, MyListener myListener) {
//        super(context, attrs);
//        this.myListener = myListener;
//    }

    public interface MyListener{
        public void myListenerCallback();
    }

    private void init(){
        ListenerCollector collector = new ListenerCollector();
        collector.setsListener(this,myListener);
    }

    private MyListener myListener = new MyListener() {
        @Override
        public void myListenerCallback() {
            System.out.print("有被调用");
        }
    };

}
