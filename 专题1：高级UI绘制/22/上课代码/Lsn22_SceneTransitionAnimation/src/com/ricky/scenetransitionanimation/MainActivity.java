package com.ricky.scenetransitionanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
		ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
				.makeSceneTransitionAnimation(this, Pair.create((View)iv1, "iv1"),Pair.create((View)bt, "bt"));
		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent, optionsCompat.toBundle());
		
	}
	
}
