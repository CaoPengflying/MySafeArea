package com.example.saveareaapp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.example.saveareaapp.R;
import com.example.saveareaapp.R.layout;
import com.example.saveareaapp.bean.User;
import com.example.saveareaapp.config.Myconfig;
import com.example.saveareaapp.config.Newconfig;
import com.example.saveareaapp.util.AsyncTaskHelper;
import com.example.saveareaapp.util.AsyncTaskHelper.OnDataDownloadListener;
import com.example.saveareaapp.util.FastJsonTools;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LOGINActivity extends BaseActivity implements OnClickListener {
	private User user;
	private boolean into=false;
	
	private ImageView Im_back_AtLogin;
	private EditText et_phone_AtLogin;
	private AsyncTaskHelper loginHelper;//异步加载网络
	private OnDataDownloadListener loginDataDownloadListener;//异步加载网络监听器
	
	private TextView tv_enter_AtLogin;
	private TextView tv_register_AtLogin;
	private EditText et_password_AtLogin;
	private SharedPreferences sp; 
	private Editor editor; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setLayoutId(R.layout.activity_login);
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_login);
		init();
		sp = getSharedPreferences("user", MODE_PRIVATE);
		test();
	}
	private void test() {
		String jsonString = "{\"datas\":[{\"id\":1,\"phone\":\"18323234567\",\"name\":\"杨干警\",\"password\":\"000000\",\"type\":2,\"areaID\":1,\"isDelete\":false,\"header\":\"www.adas.png\",\"areaName\":\"阳光小区\"}],\"success\":true,\"msg\":\"OK\"}";
		Map<String, Object> map = new HashMap<String, Object>();
		map = FastJsonTools.getMap(jsonString);
		into = (Boolean) map.get("success");
		if(into){
			String reString = map.get("datas").toString();
			List<User> users = new ArrayList<User>();
			
			users = FastJsonTools.getBeans(reString, User.class);
			user = users.get(0);
			System.out.println("user:"+JSON.toJSONString(user));

			editor = sp.edit();
			//editor.putInt("type", user.getType());
			editor.putInt("areaID", user.getAreaID());
			editor.putInt("id", user.getId());
			editor.putString("areaName",user.getAreaName());
			editor.putString("name", user.getName());
			editor.putString("password", user.getPassword());
			editor.putString("phone", user.getPhone());
			editor.putString("header", user.getHeader());
			editor.commit();
		}
		
	}
	@Override
	public void setLayoutId(int layoutId) {
		super.setLayoutId(layoutId);
	}
	private void init() {
		Im_back_AtLogin = (ImageView) findViewById(R.id.Im_back_AtLogin);
		et_phone_AtLogin = (EditText) findViewById(R.id.et_phone_AtLogin);
		tv_enter_AtLogin = (TextView) findViewById(R.id.tv_enter_AtLogin);
		tv_register_AtLogin = (TextView) findViewById(R.id.tv_register_AtLogin);
		et_password_AtLogin = (EditText) findViewById(R.id.et_password_AtLogin);
		loginHelper = new AsyncTaskHelper();
		Im_back_AtLogin.setOnClickListener(this);
		tv_enter_AtLogin.setOnClickListener(this);
		tv_register_AtLogin.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.Im_back_AtLogin:
			this.finish();
			break;
		case R.id.tv_enter_AtLogin:
			send();
			String type = et_phone_AtLogin.getText().toString();
			if(type.equals("0")||type.equals("1")||type.equals("2")){
				int inttpe = Integer.parseInt(type);
				editor.putInt("type", inttpe);
				editor.commit();
				Intent intent = new Intent(this,MainActivity.class);
				startActivity(intent);
				this.finish();
			}else{
				Toast.makeText(this, "请输入0  位游客，1为物业，2为民警登录，", Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.tv_register_AtLogin:
			Intent intent = new Intent(this,RegisterActivity.class);
			startActivity(intent);
			break;
			
		}
		
	}
	private void send() {
		
		loginDataDownloadListener = new OnDataDownloadListener() {
			
			@Override
			public void onDataDownload(byte[] result) {
				String jsonString = "{\"datas\":[{\"id\":1,\"phone\":\"18323234567\",\"name\":\"杨干警\",\"password\":\"000000\",\"type\":2,\"areaID\":1,\"isDelete\":false,\"header\":\"www.adas.png\",\"areaName\":\"\"}],\"success\":true,\"msg\":\"OK\"}";
				Map<String, Object> map = new HashMap<String, Object>();
				map = FastJsonTools.getMap(jsonString);
				into = (Boolean) map.get("success");
				if(into){
					String reString = map.get("datas").toString();
					user = FastJsonTools.getBean(reString, User.class);

					editor = sp.edit();
					editor.putInt("type", user.getType());
					editor.putInt("areaID", user.getAreaID());
					editor.putInt("id", user.getId());
					editor.putString("areaName",user.getAreaName());
					editor.putString("name", user.getName());
					editor.putString("password", user.getPassword());
					editor.putString("phone", user.getPhone());
					editor.putString("header", user.getHeader());
					editor.commit();
				}
			}
		};
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password",et_password_AtLogin.getText().toString() );
		map.put("phone",et_phone_AtLogin.getText().toString() );
		String params = JSON.toJSONString(map);
		Newconfig carsconfig = new Newconfig(3);
		carsconfig.setParams(params);

		String url = Myconfig.ADRESS + "UserServlet";
		loginHelper.downloadData(this, url, carsconfig.getParamters(), loginDataDownloadListener);
		
		
	}


}
