package com.example.saveareaapp.activity;

import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailAnnunciateActivity extends BaseActivity implements OnClickListener {
	private ImageView iv_back;
	private TextView tv_word_AtDetailann;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_detail_annunciate);
		super.onCreate(savedInstanceState);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtAddAnnunciate);
		tv_word_AtDetailann = (TextView) findViewById(R.id.tv_word_AtDetailann);
		tv_word_AtDetailann.setText("小区公告：明天将会大面积停水，各户居民不要惊慌，属于正常情况，不要听信谣言，也不要传播谣言");
		
		iv_back.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		this.finish();
		
	}

}
