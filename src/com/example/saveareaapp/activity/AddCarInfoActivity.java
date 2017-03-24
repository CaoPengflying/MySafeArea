package com.example.saveareaapp.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//测试
import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.adapter.AreaSpinnerAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Car;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.config.Newconfig;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.FastJsonTools;
import com.example.saveareaapp.util.GetUser;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddCarInfoActivity extends BaseActivity implements OnClickListener, OnItemSelectedListener {
	private ImageView iv_back;
	private User user = new User();
	private boolean success=false;
	private Context context = this;
	private List<Area> areas = new ArrayList<Area>();
	private int areaID = 0;
	
	private EditText et_cardnum_AtAddCarInfo,et_brand_AtAddCarInfo,et_carColor_AtAddCarInfo
	,et_engineNum_AtAddCarInfo,et_frameNum_AtAddCarInfo,et_name_AtAddCarInfo,et_idNum_AtAddPopulationInfo,et_phoneNum_AtAddPopulationInfo;
	private Button bt_save_AddCarInfo;

	private AsyncTaskHelper getCarHelper;//异步加载网络
	private OnDataDownloadListener getCarDataDownloadListener;//异步加载网络监听器
	
	private Spinner sp_selectArea_AtAddCarInfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_add_car_info);
		super.onCreate(savedInstanceState);
		init();
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtAddCarInfo);
		sp_selectArea_AtAddCarInfo = (Spinner) findViewById(R.id.sp_selectArea_AtAddCar);
		bt_save_AddCarInfo = (Button) findViewById(R.id.bt_save_AddCarInfo);
		
		et_cardnum_AtAddCarInfo = (EditText) findViewById(R.id.et_cardnum_AtAddCarInfo);
		et_carColor_AtAddCarInfo = (EditText) findViewById(R.id.et_carColor_AtAddCarInfo);
		et_brand_AtAddCarInfo = (EditText) findViewById(R.id.et_brand_AtAddCarInfo);
		et_engineNum_AtAddCarInfo = (EditText) findViewById(R.id.et_engineNum_AtAddCarInfo);
		et_name_AtAddCarInfo = (EditText) findViewById(R.id.et_name_AtAddCarInfo);
		et_phoneNum_AtAddPopulationInfo = (EditText) findViewById(R.id.et_phoneNum_AtAddPopulationInfo);
		et_idNum_AtAddPopulationInfo = (EditText) findViewById(R.id.tv_idNum_AtAddPopulationInfo);
		getCarHelper = new AsyncTaskHelper();
		
		
		iv_back.setOnClickListener(this);
		bt_save_AddCarInfo.setOnClickListener(this);
		


		Intent intent = getIntent();
		areas.addAll( (List<Area>) intent.getSerializableExtra("areas"));

		AreaSpinnerAdapter adapter = new AreaSpinnerAdapter(this, areas);
		sp_selectArea_AtAddCarInfo.setAdapter(adapter);
		sp_selectArea_AtAddCarInfo.setOnItemSelectedListener(this);
		//send();
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_save_AddCarInfo:
			send();
			System.out.println("点击了保存");
			break;

		default:
			break;
		}

	}
	/**
	 * 发送数据
	 */
	private void send() {
		if(et_cardnum_AtAddCarInfo.getText().toString().equals(""))
		{
			Toast.makeText(AddCarInfoActivity.this, "请输入车牌号", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_carColor_AtAddCarInfo.getText().toString().equals(""))
		{
			Toast.makeText(AddCarInfoActivity.this, "请输入车颜色", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_brand_AtAddCarInfo.getText().toString().equals(""))
		{
			Toast.makeText(AddCarInfoActivity.this, "请输入车品牌", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_engineNum_AtAddCarInfo.getText().toString().equals(""))
		{
			Toast.makeText(AddCarInfoActivity.this, "请输入车发动机号码", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_name_AtAddCarInfo.getText().toString().equals(""))
		{
			Toast.makeText(AddCarInfoActivity.this, "请输入车主名", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_phoneNum_AtAddPopulationInfo.getText().toString().equals(""))
		{
			Toast.makeText(AddCarInfoActivity.this, "请输入车主联系方式", Toast.LENGTH_SHORT).show();
			return;
		}
		if(et_idNum_AtAddPopulationInfo.getText().toString().equals(""))
		{
			Toast.makeText(AddCarInfoActivity.this, "请输入车主身份证号", Toast.LENGTH_SHORT).show();
			return;
		}
		
		getCarDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				//String jsonString = "{\"success\":true,\"msg\":\"OK\"}";
				String jsonString = new String(result);
				Map<String , Object> map = FastJsonTools.getMap(jsonString);
				success = (Boolean) map.get("success");
				if(success){
					Toast.makeText(AddCarInfoActivity.this, "添加成功", Toast.LENGTH_SHORT)
					.show();
					AddCarInfoActivity.this.finish();
				}else{
					Toast.makeText(AddCarInfoActivity.this, "添加失败", Toast.LENGTH_SHORT)
					.show();
				}
			}
		};
		
		Car car = new Car();
		car.setPlateNum(et_cardnum_AtAddCarInfo.getText().toString());
		car.setColor(et_carColor_AtAddCarInfo.getText().toString());
		car.setBrand(et_brand_AtAddCarInfo.getText().toString());
		car.setCarNum(et_engineNum_AtAddCarInfo.getText().toString());
		car.setOwner(et_name_AtAddCarInfo.getText().toString());
		car.setCardID(et_idNum_AtAddPopulationInfo.getText().toString());
		car.setPhone(et_phoneNum_AtAddPopulationInfo.getText().toString());
		car.setAdderID(user.getId());
		car.setAdderName(user.getName());
		car.setAreaID(areas.get(areaID).getId());
		car.setAreaName(areas.get(areaID).getName());
		car.setIsDelete(false);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		car.setCreateDate(sdf.format(date));
		user = new GetUser(this).getinfo();
		car.setAdderID(user.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("car", car);
		String params = JSON.toJSONString(map);
		Newconfig carsconfig = new Newconfig(0);
		carsconfig.setParams(params);

		String url = Myconfig.ADRESS + "CarServlet";
		
		getCarHelper.downloadData(this, url, carsconfig.getParamters(), getCarDataDownloadListener);
		
		
		
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		areaID = position;
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

}
