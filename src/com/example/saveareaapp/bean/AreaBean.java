package com.example.saveareaapp.bean;

public class AreaBean {
	private int iconId;
	private String areaName;
	
	public AreaBean(){
		
	}
	public AreaBean(int iconId,String areaName){
		this.areaName = areaName;
		this.iconId = iconId;
	}
	public int getIconId() {
		return iconId;
	}
	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
	
	
	
	
}
