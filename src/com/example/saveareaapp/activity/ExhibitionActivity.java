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

public class ExhibitionActivity extends BaseActivity implements OnClickListener {
	private ImageView iv_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_exhibition);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_exhibition);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtExhibition);
		
		iv_back.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		this.finish();
		
	}
}
