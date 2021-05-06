package com.ricky.scenetransitionanimation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.View;
import android.view.Window;

public class SecondActivity extends AppCompatActivity {

	private Toolbar toolbar;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//设置允许使用转场动画
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
//		Slide slide = new Slide();
//		slide.setDuration(300);
//		getWindow().setExitTransition(slide);//出去的动画
//		getWindow().setEnterTransition(slide);//进来的动画
		
		
//		Explode explode = new Explode();
//		explode.setDuration(1000);
//		getWindow().setExitTransition(explode);//出去的动画
//		getWindow().setEnterTransition(explode);//进来的动画
		
		Fade fade = new Fade();
		fade.setDuration(1000);
		getWindow().setExitTransition(fade);//出去的动画
		getWindow().setEnterTransition(fade);//进来的动画
		
	}
	
	public void back(View v){
		onBackPressed();
//		finishAfterTransition();
	}
	
//	@Override
//	public void onBackPressed() {
//		// TODO Auto-generated method stub
//		super.onBackPressed();
//	}
}
