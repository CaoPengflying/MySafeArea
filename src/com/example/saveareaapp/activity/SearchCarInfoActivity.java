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
import com.example.saveareaapp.adapter.CariInfoAdapter;
import com.example.saveareaapp.adapter.SpinnerAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Car;
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
import android.text.format.DateUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SearchCarInfoActivity extends BaseActivity implements
		OnClickListener, OnRefreshListener<ListView>,
		OnPullEventListener<ListView>, OnItemSelectedListener {
	private ImageView iv_back;
	private Spinner sp_selectArea_AtSearchCarInfo;
	private PullToRefreshListView lv_populationInfo_AtSearchCarInfo;
	private CariInfoAdapter adapter;
	private EditText et_search;
	private ImageView iv_search;
	private User user = new User();
	private AsyncTaskHelper getCarHelper;// 异步加载网络
	private OnDataDownloadListener getCarDataDownloadListener;// 异步加载网络监听器
	private List<Area> areas = new ArrayList<Area>();
	private List<Area> newAreas = new ArrayList<Area>();
	private int areaID = 0;
	private int page = 1;
	private String condition = "";

	private boolean isUpPull = false;
	private boolean isfist = true;
	private List<Car> cars = new ArrayList<Car>();

	private final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_search_car_info);
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_search_car_info);
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
	public void setRequse() {

		getCarDataDownloadListener = new OnDataDownloadListener() {

			@Override
			public void onDataDownload(byte[] result) {
				// String jsonString =
				// "{\"datas\":[{\"id\":1,\"plateNum\":\"赣G3221\",\"brand\":\"奔驰\",\"color\":\"红色\",\"carNum\":\"1232131\",\"owner\":\"张三\",\"pinYin\":\"\",\"cardID\":\"123123112121234\",\"phone\":\"13241322314\",\"areaID\":1,\"adderID\":1,\"createDate\":\"2017-03-12\",\"isDelete\":false,\"areaName\":\"\",\"adderName\":\"\"},{\"id\":2,\"plateNum\":\"赣G4321\",\"brand\":\"宝马\",\"color\":\"黑色\",\"carNum\":\"1233254131\",\"owner\":\"李四\",\"pinYin\":\"\",\"cardID\":\"8732112121234\",\"phone\":\"13434436578\",\"areaID\":1,\"adderID\":1,\"createDate\":\"2017-03-12\",\"isDelete\":false,\"areaName\":\"\",\"adderName\":\"\"}],\"success\":true,\"msg\":\"OK\"}";
				String jsonString = new String(result);
				Map<String, Object> map = FastJsonTools.getMap(jsonString);
				String carString = map.get("datas").toString();
				List<Car> newcars = FastJsonTools.getBeans(carString, Car.class);
				
				if (isfist) {
					cars = newcars;
					adapter.SetLists(cars);
					isfist = false;
				} else {
					if (isUpPull) {
						if(newcars.size()>0)
						{
						cars.addAll(newcars);
						}
						isUpPull = false;
						lv_populationInfo_AtSearchCarInfo.onRefreshComplete(); // 列表刷新完成恢复原样
					}
				}

			}
		};

		user = new GetUser(this).getinfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("areaID", newAreas.get(areaID).getId());
		if (!condition.equals("")) {
			map.put("condition", condition);
		}
		map.put("page", page);
		map.put("pageSize", 20);

		String params = JSON.toJSONString(map);
		Newconfig carsconfig = new Newconfig(3);
		carsconfig.setParams(params);

		String url = Myconfig.ADRESS + "CarServlet";
		getCarHelper.downloadData(this, url, carsconfig.getParamters(),
				getCarDataDownloadListener);
	}

	@SuppressWarnings("unchecked")
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtSearchCarInfo);
		iv_search = (ImageView) findViewById(R.id.iv_search_AtSearchCar);
		sp_selectArea_AtSearchCarInfo = (Spinner) findViewById(R.id.sp_selectArea_AtSearchCarInfo);
		et_search = (EditText) findViewById(R.id.ed_inputname_AtSearchCarInfo);

		lv_populationInfo_AtSearchCarInfo = (PullToRefreshListView) findViewById(R.id.lv_populationInfo_AtSearchCarInfo);

		iv_back.setOnClickListener(this);
		iv_search.setOnClickListener(this);

		getCarHelper = new AsyncTaskHelper();
		adapter = new CariInfoAdapter(this);

		lv_populationInfo_AtSearchCarInfo.setAdapter(adapter);

		// 设置下拉刷新和上拉加载的模式：默认只有下拉刷新一种模式
		// 如果需要两种模式，必须要调用setMode方法设置
		lv_populationInfo_AtSearchCarInfo.setMode(Mode.PULL_UP_TO_REFRESH);
		// 设置下拉刷新和上拉加载的监听器
		lv_populationInfo_AtSearchCarInfo.setOnPullEventListener(this);
		lv_populationInfo_AtSearchCarInfo.setOnRefreshListener(this);

		lv_populationInfo_AtSearchCarInfo
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						Intent intent = new Intent(context,
								DetailInfoCarActivity.class);
						Car car1 = new Car();
						car1 = cars.get(position - 1);
						intent.putExtra("carInfo", JSON.toJSONString(car1));
						startActivity(intent);

					}
				});

		Intent intent = getIntent();
		areas.addAll((List<Area>) intent.getSerializableExtra("areas"));

		int position = intent.getIntExtra("position", 0);

		newAreas = new ArrayList<Area>();

		newAreas.add(areas.get(position));
		for (int i = 0; i < areas.size(); i++) {
			if (i != position) {
				newAreas.add(areas.get(i));
			}
		}

		AreaSpinnerAdapter adapter = new AreaSpinnerAdapter(this, newAreas);

		sp_selectArea_AtSearchCarInfo.setAdapter(adapter);
		// 监控所选的小区
		sp_selectArea_AtSearchCarInfo.setOnItemSelectedListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_back_AtSearchCarInfo:
			this.finish();
			break;
		case R.id.iv_search_AtSearchCar:
			condition = et_search.getText().toString();
			page = 1;
			isfist = true;
			setRequse();
			
			break;
		}

	}

	@Override
	public void onPullEvent(PullToRefreshBase<ListView> refreshView,
			State state, Mode direction) {
		PullEvenText.SetpullEcen(refreshView, state, direction, this);

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

		areaID = position;
		isfist = true;
		setRequse();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}

}
