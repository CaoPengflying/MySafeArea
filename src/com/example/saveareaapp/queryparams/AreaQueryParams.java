package com.example.saveareaapp.queryparams;

import com.example.saveareaapp.bean.User;

public class AreaQueryParams {
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPlaceStation() {
		return placeStation;
	}
	public void setPlaceStation(String placeStation) {
		this.placeStation = placeStation;
	}
	private User user;
	private String placeStation;
}
