package com.example.saveareaapp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.adapter.ManOfLiveAdapter;
import com.example.saveareaapp.bean.House;
import com.example.saveareaapp.bean.Resident;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.config.Newconfig;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.FastJsonTools;
import com.example.saveareaapp.util.GetUser;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DetailInfoPopulationActivity extends BaseActivity implements OnClickListener {
	private User user;
	private List<House> house = new ArrayList<House>() ;
	private List<Resident> residents = new ArrayList<Resident>();
	
	private ImageView iv_back;
	private int houseID;
	private TextView tv_name_AtDetailPopuInfo;
	private TextView tv_address_AtDetailPopuInfo;
	private TextView tv_idNum_AtDetailPopuInfo;
	private TextView tv_phone_AtDetailPopuInfo;
	private TextView tv_use_AtDetailPopuInfo;
	private ListView lv_peopleInfo_AtDetailPopuInfo;
	private AsyncTaskHelper getPopuHelper;//异步加载网络
	private OnDataDownloadListener getpopuDataDownloadListener;//异步加载网络监听器
	
	private ManOfLiveAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_detail_info_population);
		super.onCreate(savedInstanceState);
		init();
		getdata();
	}
	
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}

	private void getdata() {
		getpopuDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				//String jsonString = "{\"datas\":[{\"ID\":1,\"areaID\":1,\"address\":\"102街道23号\",\"type\":0,\"owner\":\"杨\",\"pinYin\":\"p\",\"ownerCardID\":\"123234566545454321\",\"phone\":\"32432\",\"adderID\":1,\"CreateDate\":\"2012-01-23\",\"isDelete\":false,\"areaName\":\"\",\"adderName\":\"\",\"residents\":[{\"id\":1,\"cardID\":\"103987443929392039\",\"name\":\"张居住人\",\"pinYin\":\"zh\",\"nation\":\"汉\",\"phone\":\"1829388433\",\"houseID\":1,\"adderID\":1,\"createDate\":\"2018-02-23\",\"isDelete\":\"false\",\"header\":{\"id\":1,\"objId\":1,\"path\":\"d:/\",\"type\":0},\"houseName\":\"521\",\"adderName\":\"张干警\"}]}],\"success\":true,\"msg\":\"OK\"}";
				String jsonString = new String(result);
				Map< String, Object> map  = new HashMap<String, Object>();
				map = FastJsonTools.getMap(jsonString);
				String houseString = map.get("datas").toString();
				house = FastJsonTools.getBeans(houseString, House.class);
				setview();
			}

		};
		user = new GetUser(this).getinfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("houseID", houseID);
		
		String params = JSON.toJSONString(map);
		Newconfig popuconfig = new Newconfig(4);
		popuconfig.setParams(params);

		String url = Myconfig.ADRESS + "HouseServlet";
		System.out.println("人口的详细信息："+url+"?" + popuconfig.getParamters());
		getPopuHelper.downloadData(this, url, popuconfig.getParamters(), getpopuDataDownloadListener);
	}
	public void setview() {
		mAdapter.SetLists(house.get(0).getResidents());
		lv_peopleInfo_AtDetailPopuInfo.setAdapter(mAdapter);

		tv_name_AtDetailPopuInfo.setText(house.get(0).getOwner());
		tv_address_AtDetailPopuInfo.setText(house.get(0).getAddress());
		tv_idNum_AtDetailPopuInfo.setText(house.get(0).getOwnerCardID());
		tv_phone_AtDetailPopuInfo.setText(house.get(0).getPhone());
		String type;
		if(house.get(0).getType()==0){
			type = "自住";
		}else if(house.get(0).getType()==1){

			type = "出租";
		}else{

			type = "闲置";
		}
		tv_use_AtDetailPopuInfo.setText(type);
		mAdapter.SetLists(house.get(0).getResidents());
		lv_peopleInfo_AtDetailPopuInfo.setAdapter(mAdapter);
		
	}
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtDetailPopuInfo);
		tv_name_AtDetailPopuInfo = (TextView) findViewById(R.id.tv_name_AtDetailPopuInfo);
		tv_address_AtDetailPopuInfo = (TextView) findViewById(R.id.tv_address_AtDetailPopuInfo);
		tv_idNum_AtDetailPopuInfo = (TextView) findViewById(R.id.tv_idNum_AtDetailPopuInfo);
		tv_phone_AtDetailPopuInfo = (TextView) findViewById(R.id.tv_phone_AtDetailPopuInfo);
		tv_use_AtDetailPopuInfo = (TextView) findViewById(R.id.tv_use_AtDetailPopuInfo);
		lv_peopleInfo_AtDetailPopuInfo = (ListView) findViewById(R.id.lv_peopleInfo_AtDetailPopuInfo);
		
		mAdapter = new ManOfLiveAdapter(this);
		
		Intent intent = getIntent();
		houseID = intent.getIntExtra("houseID", 0);
		iv_back.setOnClickListener(this);
		getPopuHelper = new AsyncTaskHelper();
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back_AtDetailPopuInfo:
			this.finish();
			break;

		}
	}

}
