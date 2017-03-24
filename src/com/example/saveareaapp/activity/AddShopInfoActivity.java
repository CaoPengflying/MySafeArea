package com.example.saveareaapp.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.R.menu;
import com.example.saveareaapp.adapter.AreaSpinnerAdapter;
import com.example.saveareaapp.adapter.SpinnerAdapter;
import com.example.saveareaapp.bean.Area;
import com.example.saveareaapp.bean.Shop;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.config.Newconfig;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.FastJsonTools;
import com.example.saveareaapp.util.GetUser;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddShopInfoActivity extends BaseActivity implements OnClickListener, OnItemSelectedListener {
	private User user;
	
	private ImageView iv_back;
	private Spinner sp_selectArea_AtAddShopInfo;
	private EditText et_shopName_AtAddShopInfo;
	private EditText et_shopAddress_AtAddShopInfo;
	private EditText et_businessScope_AtAddShopInfo;
	private EditText et_owerName_AtAddShopInfo;
	private EditText et_phone_AtAddShopInfo;
	private EditText tv_IdNum_AtAddPopulationInfo;
	private Button bt_save_AddShopInfo;
	private AsyncTaskHelper getShopHelper;//异步加载网络
	private OnDataDownloadListener getShopDataDownloadListener;//异步加载网络监听器
	
	private boolean success=false;
	private Context context = this;
	
	private List<Area> areas = new ArrayList<Area>();
	private int areaID = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_add_shop_info);
		super.onCreate(savedInstanceState);
		init();
	}
	
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	private void init() {
		iv_back = (ImageView) findViewById(R.id.iv_back_AtAddShopInfo);
		et_shopName_AtAddShopInfo = (EditText) findViewById(R.id.et_shopName_AtAddShopInfo);
		et_shopAddress_AtAddShopInfo = (EditText) findViewById(R.id.et_shopAddress_AtAddShopInfo);
		et_businessScope_AtAddShopInfo = (EditText) findViewById(R.id.et_businessScope_AtAddShopInfo);
		et_owerName_AtAddShopInfo = (EditText) findViewById(R.id.et_owerName_AtAddShopInfo);
		et_phone_AtAddShopInfo = (EditText) findViewById(R.id.et_phone_AtAddShopInfo);
		tv_IdNum_AtAddPopulationInfo = (EditText) findViewById(R.id.tv_IdNum_AtAddPopulationInfo);
		bt_save_AddShopInfo = (Button) findViewById(R.id.bt_save_AddShopInfo);
		bt_save_AddShopInfo.setOnClickListener(this);
		
		sp_selectArea_AtAddShopInfo = (Spinner) findViewById(R.id.sp_selectArea_AtAddShopInfo);
		
		iv_back.setOnClickListener(this);
		

		Intent intent = getIntent();
		areas.addAll( (List<Area>) intent.getSerializableExtra("areas"));

		AreaSpinnerAdapter adapter = new AreaSpinnerAdapter(this, areas);
		
		sp_selectArea_AtAddShopInfo.setAdapter(adapter);
		sp_selectArea_AtAddShopInfo.setOnItemSelectedListener(this);
		
		getShopHelper = new AsyncTaskHelper();
		
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.bt_save_AddShopInfo){
			send();
		}else{
		this.finish();
		}
		
	}
	private void send() {
		
		if(et_shopName_AtAddShopInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入商铺名称", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(et_shopAddress_AtAddShopInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入商铺地址", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(et_businessScope_AtAddShopInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入经营范围", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(et_owerName_AtAddShopInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入业主姓名", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(et_phone_AtAddShopInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入联系方式", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(tv_IdNum_AtAddPopulationInfo.getText().toString().equals("")) {
			Toast.makeText(context, "请输入身份证号", Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		
		getShopDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				//String jsonString = "{\"success\":true,\"msg\":\"OK\"}";
				String jsonString = new String(result);
				Map<String , Object> map = FastJsonTools.getMap(jsonString);
				success = (Boolean) map.get("success");
				if(success){
					Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT)
					.show();
					AddShopInfoActivity.this.finish();
				}else{

					Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT)
					.show();
				}
				
			}
		};
		
		
		Shop shop = new Shop();
		user = new GetUser(this).getinfo();

		shop.setAreaName(areas.get(areaID).getName());
		shop.setAdderID(user.getId());
		shop.setAdderName(user.getName());
		shop.setAreaID(areas.get(areaID).getId());
		shop.setName(et_shopName_AtAddShopInfo.getText().toString());
		shop.setAddress(et_shopAddress_AtAddShopInfo.getText().toString());
		shop.setBusinessScope(et_businessScope_AtAddShopInfo.getText().toString());
		shop.setOwner(et_owerName_AtAddShopInfo.getText().toString());
		shop.setPhone(et_phone_AtAddShopInfo.getText().toString());
		shop.setCardID(tv_IdNum_AtAddPopulationInfo.getText().toString());
		shop.setIsDelete(false);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		shop.setCreateDate(sdf.format(date));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("shop", shop);
		String params = JSON.toJSONString(map);
		Newconfig shopconfig = new Newconfig(0);
		shopconfig.setParams(params);

		String url = Myconfig.ADRESS + "ShopServlet";
		getShopHelper.downloadData(this, url, shopconfig.getParamters(), getShopDataDownloadListener);
		
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		areaID = position;
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}


}
