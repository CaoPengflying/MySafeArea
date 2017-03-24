package com.example.saveareaapp.activity;

import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.util.FastJsonTools;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailInfoCarActivity extends BaseActivity implements OnClickListener {
	private ImageView iv_back;
	private TextView tv_carNum_detailCarInfo;
	private TextView tv_brand_detailCarInfo;
	private TextView tv_color_detailCarInfo;
	private TextView tv_frameNum_detailCarInfo;
	private TextView tv_engineNum_AtAddPopulationInfo;
	private TextView tv_carOwer_AtAddPopulationInfo;
	private TextView tv_idNum_AtAddPopulationInfo;
	private TextView tv_phone_AtAddPopulationInfo;
	private Car carInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_detail_info_car);
		super.onCreate(savedInstanceState); 
		init();
		setinfo();
	}
	
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	private void setinfo() {

		tv_carNum_detailCarInfo.setText(carInfo.getPlateNum());
		tv_brand_detailCarInfo.setText(carInfo.getBrand());
		tv_color_detailCarInfo.setText(carInfo.getColor());
		tv_frameNum_detailCarInfo.setText(carInfo.getCarNum()); 
		tv_engineNum_AtAddPopulationInfo.setText(carInfo.getCarNum()); 
		tv_carOwer_AtAddPopulationInfo.setText(carInfo.getOwner());
		tv_idNum_AtAddPopulationInfo.setText(carInfo.getCardID()); 
		tv_phone_AtAddPopulationInfo.setText(carInfo.getPhone());
		
	}
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_detailCarInfo);
		tv_carNum_detailCarInfo = (TextView) findViewById(R.id.tv_carNum_detailCarInfo);
		tv_brand_detailCarInfo = (TextView) findViewById(R.id.tv_brand_detailCarInfo);
		tv_color_detailCarInfo = (TextView) findViewById(R.id.tv_color_detailCarInfo);
		tv_frameNum_detailCarInfo = (TextView) findViewById(R.id.tv_frameNum_detailCarInfo);
		tv_engineNum_AtAddPopulationInfo = (TextView) findViewById(R.id.tv_engineNum_AtAddPopulationInfo);
		tv_carOwer_AtAddPopulationInfo = (TextView) findViewById(R.id.tv_carOwer_AtAddPopulationInfo);
		tv_idNum_AtAddPopulationInfo = (TextView) findViewById(R.id.tv_idNum_AtAddPopulationInfo);
		tv_phone_AtAddPopulationInfo = (TextView) findViewById(R.id.tv_phone_AtAddPopulationInfo);
		Intent intent = getIntent();
		String carInfoString = intent.getStringExtra("carInfo");
		carInfo = FastJsonTools.getBean(carInfoString, Car.class);
		iv_back.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		this.finish();
		
	}


}
