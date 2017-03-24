package com.example.saveareaapp.activity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.adapter.AreaAdapter;
import com.example.saveareaapp.adapter.CariInfoAdapter;
import com.example.saveareaapp.adapter.PopulationInfoAdapter;
import com.example.saveareaapp.adapter.ShopInfoAdapter;
import com.example.saveareaapp.adapter.ViewPageAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.bean.House;
import com.example.saveareaapp.bean.Shop;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.FastJsonTools;
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
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class AddInfoPropertyActivity extends BaseActivity implements OnClickListener, OnItemSelectedListener, OnPullEventListener<ListView>, OnRefreshListener<ListView> {
	private ViewPager vPager_DetailInteraction_pro;
	private  TextView textView1,textView2,textView3;
	private ImageView iv_back_AtAddInfo_pro,iv_select_AtAddInfo_pro;
	
	private PullToRefreshListView lv_populationInfo_pagePopuInfo;
	private PullToRefreshListView lv_inputname_pageCarInfo_pro;
	private PullToRefreshListView lv_shopinfos_pageShopInfo_pro;
	
	//房屋
	private Spinner sp_inputarea_pagePopuInfo_pro;//输入房屋类型
	private ImageView iv_search_pagePopuInfo_pro;//点击查询按钮
	private EditText ed_infos_pagePopuInfo_pro;//输入查询框
	private AsyncTaskHelper getPopuHelper;//异步加载网络
	private OnDataDownloadListener getPopuDataDownloadListener;//异步加载网络监听器
	private int pagePopu = 0;
	private String conditionPopu="";
	private int houseType = 3;
	
	//车辆
	private ImageView im_search_pageCarInfo_pro;//点击查询按钮
	private EditText ed_inputname_pageCarInfo_pro;//输入查询框
	private AsyncTaskHelper getCarHelper;//异步加载网络
	private OnDataDownloadListener getCarDataDownloadListener;//异步加载网络监听器
	private int pageCar = 0;
	private String conditionCar="";
	
	//商铺
	private ImageView im_search_pageShopInfo_pro;//点击查询按钮
	private EditText ed_inputname_pageShopInfo_pro;//输入查询框
	private AsyncTaskHelper getShopHelper;//异步加载网络
	private OnDataDownloadListener getShopDataDownloadListener;//异步加载网络监听器
	private int pageShop = 0;
	private String conditionShop="";
	
	
	List<Shop> shops = new ArrayList<Shop>();
	List<House> houses = new ArrayList<House>();
	List<Car> cars = new ArrayList<Car>();
	CariInfoAdapter caradapter;
	ShopInfoAdapter shopAdapter;
	PopulationInfoAdapter popuAdapter;
	
	
	Context context = this;
	
	private static int infoType=0;
	List<Area> areas = new ArrayList<Area>();
	
	private ViewPageAdapter vpAdapter;
	private ArrayList<View> viewLists = new ArrayList<View>();
	

	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		setLayoutId(R.layout.activity_add_info_property);
		super.onCreate(savedInstanceState);
		init();
		getarea();
	}
	
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	private void getarea() {

		String reString = "{\"datas\":[{\"id\":1,\"name\":\"阳光小区\",\"introduction\":\"小区始建于2010年，开发商为华丰建筑公司。小区环境优美，风景怡人。被评为先进小区\",\"policeStation\":\"七里湖派出所\",\"photos\":[{\"id\":1,\"objId\":1,\"path\":\"d:/\",\"type\":1}],\"isDelete\":false}],\"success\":true,\"msg\":\"OK\"}";
		Map<String, Object> map = FastJsonTools.getMap(reString);
		String datas = map.get("datas").toString();
		areas = FastJsonTools.getBeans(datas, Area.class);
		
	}
	private void init() {
		vPager_DetailInteraction_pro = (ViewPager) findViewById(R.id.vPager_DetailInteraction_pro);
		textView1 = (TextView) findViewById(R.id.tv_populationInfo_AtAddInfo_pro);
		
		textView1.setTextColor(getResources().getColor(R.color.policebule));
		
		textView2 = (TextView) findViewById(R.id.tv_carInfo_AtAddInfo_pro);
		textView3 = (TextView) findViewById(R.id.tv_shopInfo_AtAddInfo_pro);
		iv_back_AtAddInfo_pro = (ImageView) findViewById(R.id.iv_back_AtAddInfo_pro);
		iv_select_AtAddInfo_pro = (ImageView) findViewById(R.id.iv_select_AtAddInfo_pro);
		
		LayoutInflater li = getLayoutInflater();
		View  viewpopu = li.inflate(R.layout.page_population_property, null);
		View  viewcar = li.inflate(R.layout.page_car_property, null);
		View  viewshop = li.inflate(R.layout.page_shop_property, null);
		viewLists.add(viewpopu);
		viewLists.add(viewcar);
		viewLists.add(viewshop);
		
		vpAdapter = new ViewPageAdapter(viewLists);
		vPager_DetailInteraction_pro.setAdapter(vpAdapter);
		vPager_DetailInteraction_pro.setOnPageChangeListener(new MyOnPageChangeListener());
		
		iv_back_AtAddInfo_pro.setOnClickListener(this);
		iv_select_AtAddInfo_pro.setOnClickListener(this);

		textView1.setOnClickListener(new MyOnClickListener(0));
		textView2.setOnClickListener(new MyOnClickListener(1));
		textView3.setOnClickListener(new MyOnClickListener(2));
		infoType=0;

		
		lv_populationInfo_pagePopuInfo = (PullToRefreshListView) viewpopu.findViewById(R.id.lv_populationInfo_pagePopuInfo);
		lv_inputname_pageCarInfo_pro = (PullToRefreshListView) viewcar.findViewById(R.id.lv_inputname_pageCarInfo_pro);
		lv_shopinfos_pageShopInfo_pro = (PullToRefreshListView) viewshop.findViewById(R.id.lv_shopinfos_pageShopInfo_pro);
		
		// 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
		// 如果需要两种模式，必须要调用setMode方法设置
		lv_populationInfo_pagePopuInfo.setMode(Mode.PULL_UP_TO_REFRESH);
		// 设置下拉刷新和上拉加载的监听器
		lv_populationInfo_pagePopuInfo.setOnPullEventListener(this);
		lv_populationInfo_pagePopuInfo.setOnRefreshListener(this);
		
		// 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
		// 如果需要两种模式，必须要调用setMode方法设置
		lv_inputname_pageCarInfo_pro.setMode(Mode.PULL_UP_TO_REFRESH);
		// 设置下拉刷新和上拉加载的监听器
		lv_inputname_pageCarInfo_pro.setOnPullEventListener(this);
		lv_inputname_pageCarInfo_pro.setOnRefreshListener(this);
		
		
		// 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
		// 如果需要两种模式，必须要调用setMode方法设置
		lv_shopinfos_pageShopInfo_pro.setMode(Mode.PULL_UP_TO_REFRESH);
		// 设置下拉刷新和上拉加载的监听器
		lv_shopinfos_pageShopInfo_pro.setOnPullEventListener(this);
		lv_shopinfos_pageShopInfo_pro.setOnRefreshListener(this);
		
		sp_inputarea_pagePopuInfo_pro = (Spinner) viewshop.findViewById(R.id.sp_inputarea_pagePopuInfo_pro);
		iv_search_pagePopuInfo_pro = (ImageView) viewshop.findViewById(R.id.iv_search_pagePopuInfo_pro);
		ed_infos_pagePopuInfo_pro = (EditText) viewshop.findViewById(R.id.ed_infos_pagePopuInfo_pro);
		
		im_search_pageCarInfo_pro = (ImageView) viewshop.findViewById(R.id.im_search_pageCarInfo_pro);
		ed_inputname_pageCarInfo_pro = (EditText) viewshop.findViewById(R.id.ed_inputname_pageCarInfo_pro);
		
		im_search_pageShopInfo_pro = (ImageView) viewshop.findViewById(R.id.im_search_pageShopInfo_pro);
		ed_inputname_pageShopInfo_pro = (EditText) viewshop.findViewById(R.id.ed_inputname_pageShopInfo_pro);
		//实例化
		getShopHelper = new AsyncTaskHelper();
		getCarHelper = new AsyncTaskHelper();
		getPopuHelper = new AsyncTaskHelper();
		
		caradapter = new CariInfoAdapter(this);
		shopAdapter = new ShopInfoAdapter(this);
		popuAdapter = new PopulationInfoAdapter(this);
		
		getdata();
		setonclick();
		
		iv_search_pagePopuInfo_pro.setOnClickListener(this);
		sp_inputarea_pagePopuInfo_pro.setOnItemSelectedListener(this);
		im_search_pageCarInfo_pro.setOnClickListener(this);
		im_search_pageShopInfo_pro.setOnClickListener(this);
		
		
		
		
		
		
		
	}
	
	/**
	 * 设置listView的点击事件
	 */

	private void setonclick() {
		lv_populationInfo_pagePopuInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, DetailInfoPopulationActivity.class);
				
				intent.putExtra("houseID", houses.get(position).getId());
				startActivity(intent);
				
			}
		});
		lv_inputname_pageCarInfo_pro.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, DetailInfoCarActivity.class);
				Car car1 = new Car();
				car1 = cars.get(position);
				intent.putExtra("carInfo", JSON.toJSONString(car1));
				startActivity(intent);
				
			}
		});
		lv_shopinfos_pageShopInfo_pro.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(context, DetailInfoShopActivity.class);
				Shop shop = new Shop();
				shop = shops.get(position);
				intent.putExtra("shopInfo", JSON.toJSONString(shop));
				startActivity(intent);
				
			}
		});
		
	}

	/**
	 * 获取信息并设置适配器
	 */
	private void getdata() {
		String jsonString = "{\"datas\":[{\"id\":1,\"plateNum\":\"赣G3221\",\"brand\":\"奔驰\",\"color\":\"红色\",\"carNum\":\"1232131\",\"owner\":\"张三\",\"pinYin\":\"\",\"cardID\":\"123123112121234\",\"phone\":\"13241322314\",\"areaID\":1,\"adderID\":1,\"createDate\":\"2017-03-12\",\"isDelete\":false,\"areaName\":\"\",\"adderName\":\"\"},{\"id\":2,\"plateNum\":\"赣G4321\",\"brand\":\"宝马\",\"color\":\"黑色\",\"carNum\":\"1233254131\",\"owner\":\"李四\",\"pinYin\":\"\",\"cardID\":\"8732112121234\",\"phone\":\"13434436578\",\"areaID\":1,\"adderID\":1,\"createDate\":\"2017-03-12\",\"isDelete\":false,\"areaName\":\"\",\"adderName\":\"\"}],\"success\":true,\"msg\":\"OK\"}";
		Map<String , Object> map = FastJsonTools.getMap(jsonString);
		String carString = map.get("datas").toString();
		cars = FastJsonTools.getBeans(carString, Car.class);

		caradapter.SetLists(cars);
		
		String popjsonString = "{\"datas\":[{\"ID\":1,\"areaID\":1,\"address\":\"102街道23号\",\"type\":0,\"owner\":\"杨\",\"pinYin\":\"p\",\"ownerCardID\":\"123234566545454321\",\"phone\":\"32432\",\"adderID\":1,\"CreateDate\":\"2012-01-23\",\"isDelete\":false,\"areaName\":\"\",\"adderName\":\"\"}],\"success\":true}";
		Map<String, Object> popmap = new HashMap<String, Object>();
		popmap = FastJsonTools.getMap(popjsonString);
		String dataString = popmap.get("datas").toString();
		houses = FastJsonTools.getBeans(dataString, House.class);
		System.out.println("houses="+JSON.toJSONString(houses));
		popuAdapter.SetLists(houses);
		
		String shopjsonString = "{\"datas\":[{\"id\":1,\"name\":\"便利店\",\"address\":\"103街道43号\",\"businessScope\":\"生活常用品\",\"owner\":\"张老板\",\"pinYin\":\"z\",\"cardID\":\"134345677545654321\",\"phone\":\"34543234567\",\"areaID\":1,\"adderID\":1,\"createdate\":\"2010-09-08\",\"isDelete\":false}],\"success\":true,\"msg\":\"OK\"}";
		Map<String, Object> shopmap = new HashMap<String, Object>();
		shopmap = FastJsonTools.getMap(shopjsonString);
		String shopString = shopmap.get("datas").toString();
		shops = FastJsonTools.getBeans(shopString, Shop.class);
		shopAdapter.SetLists(shops);
		
		lv_populationInfo_pagePopuInfo.setAdapter(popuAdapter);
		lv_inputname_pageCarInfo_pro.setAdapter(caradapter);
		lv_shopinfos_pageShopInfo_pro.setAdapter(shopAdapter);
		
	}

	/** 
	 *     
	 * 头标点击监听 3 */
	private class MyOnClickListener implements OnClickListener{
       private int index=0;
       public MyOnClickListener(int i){
			System.out.println("858index="+infoType);
       	index=i;
        infoType=i;
       }
		public void onClick(View v) {

			textView1.setTextColor(getResources().getColor(R.color.black));
			textView2.setTextColor(getResources().getColor(R.color.black));
			textView3.setTextColor(getResources().getColor(R.color.black));
			vPager_DetailInteraction_pro.setCurrentItem(index);
			switch(index){
			case 0:
				textView1.setTextColor(getResources().getColor(R.color.policebule));
				break;
			case 1:
				textView2.setTextColor(getResources().getColor(R.color.policebule));
				break;
			case 2:
				textView3.setTextColor(getResources().getColor(R.color.policebule));
				break;
			}
		}
		
	}
	public class MyOnPageChangeListener implements OnPageChangeListener{

    	
		public void onPageScrollStateChanged(int arg0) {
			
			
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
			
		}

		public void onPageSelected(int arg0) {
			infoType = arg0;
			System.out.println("onPageSelected index="+infoType);
			textView1.setTextColor(getResources().getColor(R.color.black));
			textView2.setTextColor(getResources().getColor(R.color.black));
			textView3.setTextColor(getResources().getColor(R.color.black));
			switch(arg0){
			case 0:
				textView1.setTextColor(getResources().getColor(R.color.policebule));
				break;
			case 1:
				textView2.setTextColor(getResources().getColor(R.color.policebule));
				break;
			case 2:
				textView3.setTextColor(getResources().getColor(R.color.policebule));
				break;
			}
		}
    	
    }
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_back_AtAddInfo_pro:
			this.finish();
			break;
		case R.id.iv_select_AtAddInfo_pro:
			Intent intent=null;
			System.out.println("11index="+infoType);
			switch(infoType){
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
			break;
		case R.id.iv_search_pagePopuInfo_pro:
			conditionPopu = ed_infos_pagePopuInfo_pro.getText().toString();
			pagePopu=0;
			getPopuData();
			break;
		case R.id.im_search_pageCarInfo_pro:
			conditionCar = ed_inputname_pageCarInfo_pro.getText().toString();
			pageCar = 0;
			getCarData();
			break;
		case R.id.im_search_pageShopInfo_pro:
			conditionShop = ed_inputname_pageShopInfo_pro.getText().toString();
			pageShop = 0;
			getShopData();
			break;
		}
	}

	/**
	 * 获取商铺信息
	 */
	private void getShopData() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 获取车辆信息
	 */
	private void getCarData() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 获取人口信息
	 */
	private void getPopuData() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 房屋类型选择
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		houseType = (position+3)%4;
		pagePopu=0;
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPullEvent(PullToRefreshBase<ListView> refreshView,
			State state, Mode direction) {

		PullEvenText.SetpullEcen(refreshView, state, direction,this);
		
	}

}
