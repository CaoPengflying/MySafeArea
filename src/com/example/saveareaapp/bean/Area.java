package com.example.saveareaapp.bean;

import java.util.ArrayList;
import java.util.List;

public class Area implements PojoModel{
public static final int photoType=1;
private Integer id;//小区ID
private String name;//小区名称
private String introduction;//简介
private String policeStation;//所属派出所
private Boolean isDelete;//是否已经删除
private List<String> photos=new ArrayList<String>();

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getIntroduction() {
	return introduction;
}
public void setIntroduction(String introduction) {
	this.introduction = introduction;
}
public String getPoliceStation() {
	return policeStation;
}
public void setPoliceStation(String policeStation) {
	this.policeStation = policeStation;
}
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public List<String> getPhotos() {
	return photos;
}
public void setPhotos(List<String> photos) {
	this.photos = photos;
}




}
