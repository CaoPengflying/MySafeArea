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

public class DetailNewActivity extends BaseActivity implements OnClickListener {
	private ImageView iv_back;
	private TextView tv_word_AtDetailNew,tv_title_AtDetailNew;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_detail_new);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_detail_new);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtDetailNew);
		tv_word_AtDetailNew = (TextView) findViewById(R.id.tv_word_AtDetailNew);
		tv_title_AtDetailNew = (TextView) findViewById(R.id.tv_title_AtDetailNew);
		tv_word_AtDetailNew.setText("       5月31日下午，副市长、公安局长张荣先深入八里湖分局七里湖派出所调研指导工作。在居民小区物业星级化管理办公室，张荣先副市长饶有兴趣地听完派出所负责人对居民小区物业星级化管理的实施意义、考评标准和开展情况。张荣先副市长指出：开展居民小区物业星级化管理工作是八里湖分局抓住了开发区独有特点而打造的一项创新工作，是弥补“警力有限”，发挥“民力无穷”的一种新模式，分局一定要真抓实干，将此项工作长期抓下去。同时，要不断优化工作方案，改进考评标准，要将维护安防措施完好情况（如监控探头的完好率）纳入考评标准。");
		tv_title_AtDetailNew.setText("副市长、公安局长张荣先深入七里湖派出所调研指导物业星级化管理工作 ");
		iv_back.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View v) {
		this.finish();
		
	}

}
