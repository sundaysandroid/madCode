package com.example.android.mobileperf.compute;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.util.Arrays;


public class DataStructuresActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_structures);

        Button dumpCountriesButton = (Button) findViewById(R.id.ds_button_dostuff);
        dumpCountriesButton.setText("Dump popular numbers to log");

        dumpCountriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dumpPopularRandomNumbersByRank();
            }
        });

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl("file:///android_asset/shiver_me_timbers.gif");
    }

    /**
     * 打印数字在数组中的索引位置
     */
    public void dumpPopularRandomNumbersByRank() {
        //随机数组中包含“受欢迎程度”，对数组进行排序
        Integer[] sortedNumbers = SampleData.coolestRandomNumbers.clone();
        Arrays.sort(sortedNumbers);

        //排序之后获取数值在原数组中的索引位置
        for (int i = 0; i < sortedNumbers.length; i++) {
            Integer currentNumber = sortedNumbers[i];
            for (int j = 0; j < SampleData.coolestRandomNumbers.length; j++) {
                if (currentNumber.compareTo(SampleData.coolestRandomNumbers[j]) == 0) {
                    Log.i("Popularity Dump", currentNumber + ": #" + j);
                }
            }
        }
    }
}
