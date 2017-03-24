package com.example.saveareaapp.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.saveareaapp.R;

public class RegisterActivity extends BaseActivity implements OnClickListener {
	private ImageView iv_back_AtRegister;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_register);
		super.onCreate(savedInstanceState);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	private void init() {
		iv_back_AtRegister = (ImageView) findViewById(R.id.iv_back_AtRegister);
		iv_back_AtRegister.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		this.finish();
		
	}
}
