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
		//��������ʹ��ת������
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		
//		Slide slide = new Slide();
//		slide.setDuration(300);
//		getWindow().setExitTransition(slide);//��ȥ�Ķ���
//		getWindow().setEnterTransition(slide);//�����Ķ���
		
		
//		Explode explode = new Explode();
//		explode.setDuration(1000);
//		getWindow().setExitTransition(explode);//��ȥ�Ķ���
//		getWindow().setEnterTransition(explode);//�����Ķ���
		
		Fade fade = new Fade();
		fade.setDuration(1000);
		getWindow().setExitTransition(fade);//��ȥ�Ķ���
		getWindow().setEnterTransition(fade);//�����Ķ���
		
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
