package com.example.saveareaapp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.adapter.AreaSpinnerAdapter;
import com.example.saveareaapp.adapter.PopulationInfoAdapter;
import com.example.saveareaapp.adapter.SpinnerAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.bean.House;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.config.Newconfig;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.FastJsonTools;
import com.example.saveareaapp.util.GetUser;
import com.example.saveareaapp.util.PullEvenText;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Spinner;

public class SearchPopulationInfoActivity extends BaseActivity implements OnClickListener, OnPullEventListener<ListView>,OnRefreshListener<ListView>, OnItemSelectedListener {
	private User user;
	private int areaID;
	private int houseType=-1;//房屋类型0为自用。1为出租，2为闲置。
	private int page=1;//第几页
	private String condition="";//查询关键字
	
	private ImageView iv_back;
	private ImageView im_search_AtSearchPopu;//放大镜查询按钮
	private Spinner sp_selectArea_AtSearchPopuInfo;
	private Spinner sp_inputarea_AtSearchPopuInfo;//按房屋类型查询
	private PullToRefreshListView lv_populationInfo_AtSearchPopuInfo;
	private EditText ed_inputname_AtSearchPopuInfo;
	
	
	private PopulationInfoAdapter adapter;
	private List<House> houses = new ArrayList<House>();
	private List<Area> newAreas = new ArrayList<Area>();
	private final Context context = this;

	private boolean isUpPull=false;
	private boolean isfist = true;
	
	private AsyncTaskHelper getPopuHelper;//异步加载网络
	private OnDataDownloadListener getpopuDataDownloadListener;//异步加载网络监听器
	private List<Area> areas = new ArrayList<Area>();
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_search_population_info);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_search_population_info);
		init();
		setRequse();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	
	/**
	 * 发送网络请求
	 * @param condition 查询条件
	 * @param houseType 按房屋类型查询
	 */
	private void setRequse(){

getpopuDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				//String jsonString = "{\"datas\":[{\"ID\":1,\"areaID\":1,\"address\":\"102街道23号\",\"type\":0,\"owner\":\"杨\",\"pinYin\":\"p\",\"ownerCardID\":\"123234566545454321\",\"phone\":\"32432\",\"adderID\":1,\"CreateDate\":\"2012-01-23\",\"isDelete\":false,\"areaName\":\"\",\"adderName\":\"\"}],\"success\":true,";
				String jsonString = new String(result);
				Map<String, Object> map = new HashMap<String, Object>();
				map = FastJsonTools.getMap(jsonString);
				String dataString = map.get("datas").toString();
				List<House>newHouses = FastJsonTools.getBeans(dataString, House.class);
				if(isfist){
					houses = newHouses;
					adapter.SetLists(houses);
					isfist = false;
				}else{
				if(isUpPull){
					if(newHouses.size()>0)
					{
						houses.addAll(newHouses);
					}
					isUpPull = false;
					lv_populationInfo_AtSearchPopuInfo.onRefreshComplete(); // 列表刷新完成恢复原样
					
				}
				}
			}
		};
		
		user = new GetUser(this).getinfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		if(condition!=""){
			map.put("condition", condition);
		}
		
		if(houseType>=0){
			map.put("houseType", houseType);
		}

		map.put("page", page);
		map.put("areaID", newAreas.get(areaID).getId());
		map.put("pageSize", 20);
		
		String params = JSON.toJSONString(map);
		Newconfig popuconfig = new Newconfig(3);
		popuconfig.setParams(params);

		String url = Myconfig.ADRESS + "HouseServlet";
		getPopuHelper.downloadData(context, url, popuconfig.getParamters(), getpopuDataDownloadListener);
		
	}
	private void init() {
		sp_inputarea_AtSearchPopuInfo = (Spinner) findViewById(R.id.sp_inputarea_AtSearchPopuInfo);
		sp_selectArea_AtSearchPopuInfo = (Spinner) findViewById(R.id.sp_selectArea_AtSearchPopuInfo);
		iv_back = (ImageView) findViewById(R.id.iv_back_AtSearchPopuInfo);
		im_search_AtSearchPopu = (ImageView) findViewById(R.id.im_search_AtSearchPopu);
		
		ed_inputname_AtSearchPopuInfo = (EditText) findViewById(R.id.ed_inputname_AtSearchPopuInfo);
		sp_selectArea_AtSearchPopuInfo = (Spinner) findViewById(R.id.sp_selectArea_AtSearchPopuInfo);
		lv_populationInfo_AtSearchPopuInfo = (PullToRefreshListView) findViewById(R.id.lv_populationInfo_AtSearchPopuInfo);
		getPopuHelper = new AsyncTaskHelper();
		adapter = new PopulationInfoAdapter(this);
		adapter.SetLists(houses);
		lv_populationInfo_AtSearchPopuInfo.setAdapter(adapter);
		// 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
				// 如果需要两种模式，必须要调用setMode方法设置
		lv_populationInfo_AtSearchPopuInfo.setMode(Mode.PULL_UP_TO_REFRESH);
				// 设置下拉刷新和上拉加载的监听器
		lv_populationInfo_AtSearchPopuInfo.setOnPullEventListener(this);
		lv_populationInfo_AtSearchPopuInfo.setOnRefreshListener(this);
		
		iv_back.setOnClickListener(this);
		im_search_AtSearchPopu.setOnClickListener(this);
		
		lv_populationInfo_AtSearchPopuInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, DetailInfoPopulationActivity.class);
				
				intent.putExtra("houseID", houses.get(position-1).getId());
				startActivity(intent);
				
			}
		});
		
		Intent intent = getIntent();
		areas.addAll( (List<Area>) intent.getSerializableExtra("areas"));
		
		int position = intent.getIntExtra("position", 0);
		
		 newAreas = new ArrayList<Area>();
		
		newAreas.add(areas.get(position));
		for(int i=0;i<areas.size();i++){
			if(i!=position){
			newAreas.add(areas.get(i));
			}
		}
		AreaSpinnerAdapter adapter = new AreaSpinnerAdapter(this, newAreas);
		sp_selectArea_AtSearchPopuInfo.setAdapter(adapter);
		sp_selectArea_AtSearchPopuInfo.setOnItemSelectedListener(this);
		sp_inputarea_AtSearchPopuInfo.setOnItemSelectedListener(this);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.im_search_AtSearchPopu:
			condition = ed_inputname_AtSearchPopuInfo.getText().toString();
			
			page=1;
			isfist = true;
			setRequse();
			break;
		case R.id.iv_back_AtSearchPopuInfo:
			
			this.finish();
			break;
		}
		
	}
	@Override
	public void onPullEvent(PullToRefreshBase<ListView> refreshView,
			State state, Mode direction) {

		PullEvenText.SetpullEcen(refreshView, state, direction,this);
		
	}
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		page++;
		isUpPull = true;
		setRequse();
		
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		if(parent.getId()==R.id.sp_inputarea_AtSearchPopuInfo){
			houseType = position-1;
			page=1;
			setRequse();
		}else{
			areaID = position;
			page=1;
			isfist = true;
			setRequse();
			
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

}
