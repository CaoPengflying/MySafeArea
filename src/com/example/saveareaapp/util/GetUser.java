package com.example.saveareaapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.saveareaapp.bean.User;

public class GetUser {
	private Context context;

	private SharedPreferences sp; 

	public GetUser(Context context){
		this.context = context;
	}
	
	public User getinfo(){

		sp = context.getSharedPreferences("user",context.MODE_PRIVATE);
		User user = new User();
		user.setAreaID(sp.getInt("areaID", 0));
		user.setAreaName(sp.getString("areaName", null));
		user.setId(sp.getInt("id", 0));
		user.setName(sp.getString("name", null));
		user.setPassword(sp.getString("password", null));
		user.setPhone(sp.getString("phone", null));
		user.setType(sp.getInt("type", 0));
		user.setHeader(sp.getString("header", null));
		return user;
		
	}
			
}
