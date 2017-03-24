package com.example.saveareaapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.saveareaapp.R;

public class FistActivity extends BaseActivity {
	

	private CountDownTimer timer ;//时间计算器
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.fist_activity);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.fist_activity);
		init();
		

	}
	
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}

	private void init() {
		
		final Intent intent = new Intent();
		intent.setClass(FistActivity.this,MainActivity.class);

		timer = new CountDownTimer(3000, 100) {  
			public void onTick(long millisUntilFinished) {
				 try {

		             Thread.sleep(1001);

		          } catch (InterruptedException e) {

		              e.printStackTrace();

		          }
		    }
		        @Override
		    public void onFinish() {
		    	FistActivity.this.startActivity(intent);
		    	FistActivity.this.finish();
		    }
		};
		timer.start();
		
	}



}
