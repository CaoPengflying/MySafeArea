package com.example.saveareaapp.bean;

import java.util.Date;


public class Car implements PojoModel{
private Integer id;//主键
private String plateNum;//车牌号
private String  color;//车颜色
private String brand;//品牌
private String carNum;//发动机号|车架号
private String owner;//车主|使用者姓名
private String pinYin;//姓名的拼音
private String cardID;//身份证号码
private String phone;//电话
private Integer areaID;//小区ID
private Integer adderID;//采集人ID
private String createDate;//采集时间
private Boolean isDelete;//是否已经删除

public String areaName;//小区名称
public String adderName;//采集人姓名



public Car(){
	
}
public Car(String brand,String color,String owner,String phone,String plateNum){
	this.brand = brand;
	this.color = color;
	this.owner = owner;
	this.phone = phone;
	this.plateNum = plateNum;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getAreaName() {
	return areaName;
}
public void setAreaName(String areaName) {
	this.areaName = areaName;
}

public String getAdderName() {
	return adderName;
}
public void setAdderName(String adderName) {
	this.adderName = adderName;
}
public String getPlateNum() {
	return plateNum;
}
public void setPlateNum(String plateNum) {
	this.plateNum = plateNum;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getCarNum() {
	return carNum;
}
public void setCarNum(String carNum) {
	this.carNum = carNum;
}
public String getOwner() {
	return owner;
}
public void setOwner(String owner) {
	this.owner = owner;
}
public String getPinYin() {
	return pinYin;
}
public void setPinYin(String pinYin) {
	this.pinYin = pinYin;
}
public String getCardID() {
	return cardID;
}
public void setCardID(String cardID) {
	this.cardID = cardID;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Integer getAreaID() {
	return areaID;
}
public void setAreaID(Integer areaID) {
	this.areaID = areaID;
}
public Integer getAdderID() {
	return adderID;
}
public void setAdderID(Integer adderID) {
	this.adderID = adderID;
}
public String getCreateDate() {
	return createDate;
}
public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}




}
