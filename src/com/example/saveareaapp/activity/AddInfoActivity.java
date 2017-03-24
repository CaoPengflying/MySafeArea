package com.example.saveareaapp.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.adapter.AreaAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.AreaBean;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.config.Newconfig;
import com.example.saveareaapp.fragment.FistPageFragment;
import com.example.saveareaapp.queryparams.AreaQueryParams;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;
import com.example.saveareaapp.util.FastJsonTools;
import com.example.saveareaapp.util.GetUser;
import com.example.saveareaapp.util.MapToString;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AddInfoActivity extends BaseActivity implements OnClickListener {
	
	private static int type=0;
	private static float x=0;
	private static float y=0;
	
	private User user;
	
	private ImageView iv_back_AtAddInfo;
	private ImageView iv_select_AtAddInfo;
	private ImageView iv_add_AtAddInfo;
	
	private View v1,v2,v3;
	
	private TextView tv_populationInfo_AtAddInfo;
	private TextView tv_shopInfo_AtAddInfo;
	private TextView tv_carInfo_AtAddInfo;
	
	private GridView gv_areaName_AtAddInfo;
	private ArrayList<AreaBean> iconlist=null;
	private AreaAdapter adapter=null;
	
	private AsyncTaskHelper getAllAreaHelper;//异步加载网络
	
	private OnDataDownloadListener getAllAreaListener;//异步加载网络监听器
	List<Area> areas = new ArrayList<Area>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_add_info);
		super.onCreate(savedInstanceState);

		init();
		tv_populationInfo_AtAddInfo.setTextColor(Color.BLUE);
		showAreaGrid();
	}
	
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}

	/**
	 * 显示小区和图标
	 */
	private void showAreaGrid() {

		String reString = "{\"datas\":[{\"ID\":1,\"name\":\"鹤问湖一号\",\"introduction\":\"小区始建于2010年，开发商为华丰建筑公司。小区环境优美，风景怡人。被评为先进小区\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":2,\"name\":\"鹤问湖公馆\",\"introduction\":\"小区始建于2011年，开发商为华丰建筑公司。小区位于七里湖\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":3,\"name\":\"江南府邸\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":4,\"name\":\"沿浔安置小区\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":5,\"name\":\"中星宜景湾\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":6,\"name\":\"天翼景城\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":7,\"name\":\"观澜盛世南区\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":8,\"name\":\"观澜盛世北区\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":9,\"name\":\"观天下\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":10,\"name\":\"诚盛御庭\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":11,\"name\":\"银星花园\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":12,\"name\":\"曼城\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":13,\"name\":\"宿舍小区\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":14,\"name\":\"正大安置小区\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":15,\"name\":\"亿志蓝湾\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":16,\"name\":\"杨光天下红屋顶\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":17,\"name\":\"九龙世纪城\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":18,\"name\":\"锦江国际\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":19,\"name\":\"阳光福邸\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":20,\"name\":\"首府\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false},{\"ID\":20,\"name\":\"壹品湾\",\"introduction\":\"小区始建于2001年，开发商为华丰建筑公司。小区风格良好，有一个公园，是一个养老的好地方\",\"policeStation\":\"七里湖派出所\",\"isDelete\":false}],\"success\":true,\"msg\":\"OK\"}";
		Map<String, Object> map = FastJsonTools.getMap(reString);
		String datas = map.get("datas").toString();
		areas = FastJsonTools.getBeans(datas, Area.class);
		System.out.println(JSON.toJSONString("areas="+areas));
		
		 adapter = new AreaAdapter(areas,this);
		 gv_areaName_AtAddInfo.setAdapter(adapter);
		
	}

	private void init() {
		
		gv_areaName_AtAddInfo = (GridView) findViewById(R.id.gv_areaName_AtAddInfo);
		iv_back_AtAddInfo = (ImageView) findViewById(R.id.iv_back_AtAddInfo);
		iv_select_AtAddInfo = (ImageView) findViewById(R.id.iv_select_AtAddInfo);
		iv_add_AtAddInfo = (ImageView) findViewById(R.id.iv_add_AtAddInfo);
		tv_populationInfo_AtAddInfo = (TextView) findViewById(R.id.tv_populationInfo_AtAddInfo);
		tv_shopInfo_AtAddInfo = (TextView) findViewById(R.id.tv_shopInfo_AtAddInfo);
		tv_carInfo_AtAddInfo = (TextView) findViewById(R.id.tv_carInfo_AtAddInfo);
		v1 = (View) findViewById(R.id.v1);
		v2 = (View) findViewById(R.id.v2);
		v3 = (View) findViewById(R.id.v3);

		tv_carInfo_AtAddInfo.setOnClickListener(this);
		tv_shopInfo_AtAddInfo.setOnClickListener(this);
		tv_populationInfo_AtAddInfo.setOnClickListener(this);
		iv_add_AtAddInfo.setOnClickListener(this);
		iv_select_AtAddInfo.setOnClickListener(this);
		iv_back_AtAddInfo.setOnClickListener(this);
		
		//初始化asyncktaskhelp
		getAllAreaHelper = new AsyncTaskHelper();
		
		getAllArea();
		/**
		 * 设置GridView的左划右边划切换查询，车辆，商铺，人口信息
		 */
		
		gv_areaName_AtAddInfo.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					x=event.getX();
					break;
				case MotionEvent.ACTION_MOVE:
					break;
				case MotionEvent.ACTION_UP:
					y=event.getX();

					if(x-y>100){
						type = (type+1)%3;
						noSelect();
						if(type==0){
							tv_populationInfo_AtAddInfo.setTextColor(Color.BLUE);
							v1.setBackgroundColor(Color.BLUE);
						}else if(type==1){
							tv_carInfo_AtAddInfo.setTextColor(Color.BLUE);
							v2.setBackgroundColor(Color.BLUE);
						}else{
							tv_shopInfo_AtAddInfo.setTextColor(Color.BLUE);
							v3.setBackgroundColor(Color.BLUE);
						}
					}else if(y-x>100){
						type = (type+2)%3;
						noSelect();
						if(type==0){
							tv_populationInfo_AtAddInfo.setTextColor(Color.BLUE);
							v1.setBackgroundColor(Color.BLUE);
						}else if(type==1){
							tv_carInfo_AtAddInfo.setTextColor(Color.BLUE);
							v2.setBackgroundColor(Color.BLUE);
						}else{
							tv_shopInfo_AtAddInfo.setTextColor(Color.BLUE);
							v3.setBackgroundColor(Color.BLUE);
						}
					}
					x=0;y=0;
					break;
				}
				
				return false;
			}
		});
		
		onClickGridtView();
		
	}
	/**
	 * 获取所有的小区
	 */
	private void getAllArea() {
		
		getAllAreaListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				String reString = "{\"datas\":[{\"id\":1,\"name\":\"阳光小区\",\"introduction\":\"小区始建于2010年，开发商为华丰建筑公司。小区环境优美，风景怡人。被评为先进小区\",\"policeStation\":\"七里湖派出所\",\"photos\":[{\"id\":1,\"objId\":1,\"path\":\"d:/\",\"type\":1}],\"isDelete\":false}],\"success\":true,\"msg\":\"OK\"}";
				Map<String, Object> map = FastJsonTools.getMap(reString);
				String datas = map.get("datas").toString();
				areas = FastJsonTools.getBeans(datas, Area.class);
				System.out.println(JSON.toJSONString("areas="+areas));
				showAreaGrid();
			}
		};
		
		
		user = new User();
		user = new GetUser(this).getinfo();
		String placeStation = "七里湖派出所";
		Map<String,Object> map = new HashMap<String,Object>();
		
		
		map.put("user", user);
		map.put("placeStation",placeStation);
		Newconfig newconfig = new Newconfig(3);
		String params = JSON.toJSONString(map);
		
		newconfig.setParams(params);
		
		System.out.println(newconfig.getParamters());
		String url = Myconfig.ADRESS + "AreaServlet";
		
		getAllAreaHelper.downloadData(AddInfoActivity.this, url, newconfig.getParamters(), getAllAreaListener);
		
	}
	private final Context context = this;
	
	
	/**
	 * 点击GridView 进入查询界面
	 */
	private void onClickGridtView() {

		gv_areaName_AtAddInfo.setOnItemClickListener(new OnItemClickListener() {

			Intent intent = new Intent();
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				switch(type){
				case 0:
					intent.setClass(context, SearchPopulationInfoActivity.class);
					break;
				case 1:
					intent.setClass(context, SearchCarInfoActivity.class);
					break;
				case 2:
					intent.setClass(context, SearchShopInfoActivity.class);
					break;
				}
				intent.putExtra("areas", (Serializable)areas);
				intent.putExtra("position", position);
				startActivity(intent);
				
			}
		});
		
	}

	/**
	 * 设置未选中
	 */
	private void noSelect(){
		tv_carInfo_AtAddInfo.setTextColor(Color.BLACK);
		tv_shopInfo_AtAddInfo.setTextColor(Color.BLACK);
		tv_populationInfo_AtAddInfo.setTextColor(Color.BLACK);
		v1.setBackgroundColor(Color.WHITE);
		v2.setBackgroundColor(Color.WHITE);
		v3.setBackgroundColor(Color.WHITE);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.iv_back_AtAddInfo:
			this.finish();
			break;
		case R.id.tv_carInfo_AtAddInfo:
			type=1;
			noSelect();
			tv_carInfo_AtAddInfo.setTextColor(Color.BLUE);
			v2.setBackgroundColor(Color.BLUE);
			break;
		case R.id.tv_shopInfo_AtAddInfo:
			type=2;
			noSelect();
			tv_shopInfo_AtAddInfo.setTextColor(Color.BLUE);
			v3.setBackgroundColor(Color.BLUE);
			break;
		case R.id.tv_populationInfo_AtAddInfo:
			type=0;
			noSelect();
			tv_populationInfo_AtAddInfo.setTextColor(Color.BLUE);
			v1.setBackgroundColor(Color.BLUE);
			break;
		case R.id.iv_add_AtAddInfo:
			intoAddInfo();
			break;
		case R.id.iv_select_AtAddInfo:
			intoSelectInfo();
			break;
		}
		
	}

	/**
	 * 进入查询界面
	 */
	private void intoSelectInfo() {
		
		Intent intent = null;
		
		switch(type){
		case 0:
			intent = new Intent(this, SearchPopulationInfoActivity.class);
			
			break;
		case 1:
			intent = new Intent(this, SearchCarInfoActivity.class);
			break;
		case 2:
			intent = new Intent(this, SearchShopInfoActivity.class);
			break;
		}
		intent.putExtra("areas", (Serializable)areas);
		intent.putExtra("position", 0);
		startActivity(intent);
		
	}

	/**
	 * 进入添加界面
	 */
	private void intoAddInfo() {
		
		Intent intent = null;
		
		switch(type){
		case 0:
			intent = new Intent(this, AddPopulationInfoActivity.class);
			break;
		case 1:
			intent = new Intent(this, AddCarInfoActivity.class);
			break;
		case 2:
			intent = new Intent(this, AddShopInfoActivity.class);
			break;
		}
		intent.putExtra("areas", (Serializable)areas);
		startActivity(intent);
		
	}


}
