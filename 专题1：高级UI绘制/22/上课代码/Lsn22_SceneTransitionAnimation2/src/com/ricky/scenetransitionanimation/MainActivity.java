package com.ricky.scenetransitionanimation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

	private ImageView iv1;
	private Button bt;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//设置允许使用转场动画
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		iv1 = (ImageView)findViewById(R.id.iv1);
		bt = (Button)findViewById(R.id.bt);
	}
	
	@SuppressLint("NewApi")
	public void jump(View v){
//		startActivity(new Intent(this, SecondActivity.class));
//		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
		
//		ActivityOptions
//		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//				.makeSceneTransitionAnimation(
//						activity, //当前的Activity
//						sharedElement,//共享元素---哪一个View
//						sharedElementName)//共享元素的名称 android:transitionName="iv_meinv3"
//		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, iv1, "iv_meinv3");
//		Intent intent = new Intent(this, SecondActivity.class);
//		startActivity(intent, optionsCompat.toBundle());
		
		//如何处理多个共享元素呢？
//		new Pair<>(first, second)
//		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//				.makeSceneTransitionAnimation(this, Pair.create((View)iv1, "iv1"),Pair.create((View)bt, "bt"));
//		Intent intent = new Intent(this, SecondActivity.class);
//		startActivity(intent, optionsCompat.toBundle());
		
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
		//如果有共享元素，可以设置共享元素，那么它就会按照共享元素动画执行，其他的子view就会按照Fade动画执行。
		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this);
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent, optionsCompat.toBundle());
		
		
	}
	
}
