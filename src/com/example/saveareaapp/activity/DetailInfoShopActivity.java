package com.example.saveareaapp.activity;

import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.bean.Shop;
import com.example.saveareaapp.util.FastJsonTools;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailInfoShopActivity extends BaseActivity implements OnClickListener {

	private Shop shopInfo=null;
	private ImageView iv_back;
	private TextView tv_shopname_AtDetailInfoShop;
	private TextView tv_address_AtDetailInfoShop;
	private TextView tv_fanwei_AtDetailInfoShop;
	private TextView tv_name_AtDetailInfoShop;
	private TextView tv_phone_AtDetailInfoShop;
	private TextView tv_idNum_AtDetailInfoShop;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_detail_info_shop);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_detail_info_shop);
		init();
		show();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	
	private void show() {
		tv_shopname_AtDetailInfoShop.setText(shopInfo.getName());
		tv_address_AtDetailInfoShop.setText(shopInfo.getAddress());
		tv_fanwei_AtDetailInfoShop.setText(shopInfo.getBusinessScope());
		tv_name_AtDetailInfoShop.setText(shopInfo.getOwner());
		tv_phone_AtDetailInfoShop.setText(shopInfo.getPhone());
		tv_idNum_AtDetailInfoShop.setText(shopInfo.getCardID());
		
	}
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtDetailInfoShop);
		tv_shopname_AtDetailInfoShop = (TextView) findViewById(R.id.tv_shopname_AtDetailInfoShop);
		tv_address_AtDetailInfoShop = (TextView) findViewById(R.id.tv_address_AtDetailInfoShop);
		tv_fanwei_AtDetailInfoShop = (TextView) findViewById(R.id.tv_fanwei_AtDetailInfoShop);
		tv_name_AtDetailInfoShop = (TextView) findViewById(R.id.tv_name_AtDetailInfoShop);
		tv_phone_AtDetailInfoShop = (TextView) findViewById(R.id.tv_phone_AtDetailInfoShop);
		tv_idNum_AtDetailInfoShop = (TextView) findViewById(R.id.tv_idNum_AtDetailInfoShop);
		
		iv_back.setOnClickListener(this);
		Intent intent = getIntent();
		String InfoString = intent.getStringExtra("shopInfo");
		shopInfo = FastJsonTools.getBean(InfoString, Shop.class);
		
	}
	@Override
	public void onClick(View v) {
		this.finish();
		
	}

}
