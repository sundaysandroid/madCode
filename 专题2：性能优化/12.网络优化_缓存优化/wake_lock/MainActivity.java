package com.dn.ricky.performance.wake_lock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView wakelock_text;
    PowerManager pw;
    PowerManager.WakeLock mWakelock;
    private ComponentName serviceComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wakelock_text = (TextView) findViewById(R.id.wakelock_text);
        pw = (PowerManager) getSystemService(POWER_SERVICE);
        mWakelock = pw.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "mywakelock");
        serviceComponent = new ComponentName(this,MyJobService.class);
    }

    public void execut(View view) {
        wakelock_text.setText("正在下载....");
//        for (int i = 0; i < 500; i++) {
//            mWakelock.acquire();//唤醒CPU
//            wakelock_text.append(i+"连接中……");
////            wakelock_text.append("");
//            //下载
//            if (isNetWorkConnected()) {
//                new SimpleDownloadTask().execute();
//            } else {
//                wakelock_text.append("没有网络连接。");
//            }
//        }

        //优化
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        for (int i = 0; i < 500; i++) {
            JobInfo jobInfo = new JobInfo.Builder(i,serviceComponent)
                    .setMinimumLatency(5000)//5秒 最小延时、
                    .setOverrideDeadline(60000)//maximum最多执行时间
//                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)//免费的网络---wifi 蓝牙 USB
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)//任意网络---
            /**
             设置重试/退避策略，当一个任务调度失败的时候执行什么样的测量采取重试。
             initialBackoffMillis:第一次尝试重试的等待时间间隔ms
             *backoffPolicy:对应的退避策略。比如等待的间隔呈指数增长。
             */
//                    .setBackoffCriteria(long initialBackoffMillis, int backoffPolicy)
                    .setBackoffCriteria(JobInfo.MAX_BACKOFF_DELAY_MILLIS, JobInfo.BACKOFF_POLICY_LINEAR)
//                    .setPeriodic (long intervalMillis)//设置执行周期，每隔一段时间间隔任务最多可以执行一次。
//                    .setPeriodic(long intervalMillis,long flexMillis)//在周期执行的末端有一个flexMiliis长度的窗口期，任务就可以在这个窗口期执行。
                    //设置设备重启后，这个任务是否还要保留。需要权限：RECEIVE_BOOT_COMPLETED //ctrl+shift+y/u x
//                    .setPersisted(boolean isPersisted);
//                    .setRequiresCharging(boolean )//是否需要充电
//                    .setRequiresDeviceIdle(boolean)//是否需要等设备出于空闲状态的时候
//                    .addTriggerContentUri(uri)//监听uri对应的数据发生改变，就会触发任务的执行。
//                    .setTriggerContentMaxDelay(long duration)//设置Content发生变化一直到任务被执行中间的最大延迟时间
                    //设置Content发生变化一直到任务被执行中间的延迟。如果在这个延迟时间内content发生了改变，延迟时间会重写计算。
//                    .setTriggerContentUpdateDelay(long durationMilimms)
                    .BUILD();


            jobScheduler.schedule(jobInfo);
        }

    }

    private boolean isNetWorkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

    /**
     * Uses AsyncTask to create a task away from the main UI thread. This task creates a
     * HTTPUrlConnection, and then downloads the contents of the webpage as an InputStream.
     * The InputStream is then converted to a String, which is displayed in the UI by the
     * onPostExecute() method.
     */
    private static final String LOG_TAG = "ricky";
    private int index=0;

    private class SimpleDownloadTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(Void... params) {
            try {
                // Only display the first 50 characters of the retrieved web page content.
                int len = 50;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                URL url = new URL("https://www.google.com");
                URL url = new URL("https://www.baidu.com");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000); // 10 seconds
                conn.setConnectTimeout(15000); // 15 seconds
                conn.setRequestMethod("GET");
                //Starts the query
                conn.connect();
                int response = conn.getResponseCode();
                index++;
                Log.d(LOG_TAG,  index+"The response is: " + response);
                InputStream is = conn.getInputStream();

                // Convert the input stream to a string
                Reader reader = new InputStreamReader(is, "UTF-8");
                char[] buffer = new char[len];
                reader.read(buffer);
                return new String(buffer);

            } catch (IOException e) {
                return "Unable to retrieve web page.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            wakelock_text.append("\n" + result + "\n");
            releaseWakeLock();
        }
    }

    private void releaseWakeLock() {
        if (mWakelock.isHeld()) {
            mWakelock.release();//记得释放CPU锁
            wakelock_text.append("释放锁！");
        }
    }

}

