package com.example.saveareaapp.bean;

import java.util.Date;

public class Shop implements PojoModel{
private Integer id;//主键
private String name;//名称
private String address;//地址
private String businessScope;//经营范围
private String owner;//业主姓名
private String pinYin;//业主姓名拼音
private String cardID;//身份证号
private String phone;//联系电话
private Integer areaID;//小区ID
private Integer adderID;//采集人ID
private String createDate;//采集时间
private Boolean isDelete;//是否删除
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public String areaName;//小区名
public String adderName;//采集人姓名


public Shop(){
	
}
public Shop(String name,String address,String owner,String cardID,String phone){
	this.name = name;
	this.address = address;
	this.owner = owner;
	this.cardID = cardID;
	this.phone = phone;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getBusinessScope() {
	return businessScope;
}
public void setBusinessScope(String businessScope) {
	this.businessScope = businessScope;
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


}
