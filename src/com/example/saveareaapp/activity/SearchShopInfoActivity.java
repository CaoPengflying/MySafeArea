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
import com.example.saveareaapp.adapter.ShopInfoAdapter;
import com.example.saveareaapp.adapter.SpinnerAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.bean.Shop;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class SearchShopInfoActivity extends BaseActivity implements OnClickListener, OnRefreshListener<ListView>, OnPullEventListener<ListView>, OnItemSelectedListener {
	private User user;
	private int areaID=0;
	private int page=1;
	private String condition="";
	
	private ImageView iv_back;
	private ImageView iv_search_AtSearchShopInfo;
	private Spinner sp_selectArea_AtSearchShopInfo;
	private EditText ed_inputname_AtSearchShopInfo;
	private PullToRefreshListView  lv_shopInfo_AtSearchShopInfo;
	
	private ShopInfoAdapter adapter;
	private List<Shop> shops = new ArrayList<Shop>();
	private AsyncTaskHelper getShopHelper;//异步加载网络
	private OnDataDownloadListener getShopDataDownloadListener;//异步加载网络监听器
	private List<Area> areas = new ArrayList<Area>();
	private List<Area> newAreas = new ArrayList<Area>();

	private boolean isUpPull=false;
	private boolean isfist = true;
	private final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_search_shop_info);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_search_shop_info);
		init();
		setRequse();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}


	/**
	 * 发送网络请求
	 */
	private void setRequse(){

getShopDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				//String jsonString = "{\"datas\":[{\"id\":1,\"name\":\"便利店\",\"adress\":\"103街道43号\",\"businessScope\":\"生活常用品\",\"owner\":\"张老板\",\"pinYin\":\"z\",\"cardID\":\"134345677545654321\",\"phone\":\"34543234567\",\"areaID\":1,\"adderID\":1,\"createdate\":\"2010-09-08\",\"isDelete\":false}],\"success\":true,\"msg\":\"OK\"}";
				String jsonString = new String(result);
				Map<String, Object> map = new HashMap<String, Object>();
				map = FastJsonTools.getMap(jsonString);
				String shopString = map.get("datas").toString();
				List<Shop> newShops = FastJsonTools.getBeans(shopString, Shop.class);
				if(isfist){
					shops = newShops;
					adapter.SetLists(shops);
					isfist = false;
				}else{
				if(isUpPull){
					if(newShops.size()>0){
						shops.addAll(newShops);
					}
					isUpPull = false;
					lv_shopInfo_AtSearchShopInfo.onRefreshComplete(); // 列表刷新完成恢复原样
					
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
		map.put("page", page);
		
		map.put("areaID", newAreas.get(areaID).getId());
		map.put("pageSize", 20);
		
		String params = JSON.toJSONString(map);
		Newconfig shopConfig = new Newconfig(3);
		shopConfig.setParams(params);

		String url = Myconfig.ADRESS + "ShopServlet";
		String paramters = shopConfig.getParamters();
		getShopHelper.downloadData(context, url, paramters, getShopDataDownloadListener);
		
	}
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtSearchShopInfo);
		iv_search_AtSearchShopInfo = (ImageView) findViewById(R.id.iv_search_AtSearchShopInfo);
		sp_selectArea_AtSearchShopInfo = (Spinner) findViewById(R.id.sp_selectArea_AtSearchShopInfo);
		lv_shopInfo_AtSearchShopInfo = (PullToRefreshListView) findViewById(R.id.lv_shopInfo_AtSearchShopInfo);
		ed_inputname_AtSearchShopInfo = (EditText) findViewById(R.id.ed_inputname_AtSearchShopInfo);
		getShopHelper = new AsyncTaskHelper();
		
		iv_back.setOnClickListener(this);
		iv_search_AtSearchShopInfo.setOnClickListener(this);
		lv_shopInfo_AtSearchShopInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, DetailInfoShopActivity.class);
				Shop shop = new Shop();
				shop = shops.get(position-1);
				intent.putExtra("shopInfo", JSON.toJSONString(shop));
				startActivity(intent);
				
			}
		});

		
		adapter = new ShopInfoAdapter(this);
		lv_shopInfo_AtSearchShopInfo.setAdapter(adapter);
		// 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
				// 如果需要两种模式，必须要调用setMode方法设置
		lv_shopInfo_AtSearchShopInfo.setMode(Mode.PULL_UP_TO_REFRESH);
				// 设置下拉刷新和上拉加载的监听器
		lv_shopInfo_AtSearchShopInfo.setOnPullEventListener(this);
		lv_shopInfo_AtSearchShopInfo.setOnRefreshListener(this);
		
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
		sp_selectArea_AtSearchShopInfo.setAdapter(adapter);
		sp_selectArea_AtSearchShopInfo.setOnItemSelectedListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.iv_search_AtSearchShopInfo:
			condition = ed_inputname_AtSearchShopInfo.getText().toString();
			page=1;
			isfist = true;
			setRequse();
			break;
		case R.id.iv_back_AtSearchShopInfo:
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
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		areaID = position;
		page=1;
		isfist = true;
		setRequse();
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
		
	}
	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		page++;
		isUpPull = true;
		setRequse();
		
	}

}
