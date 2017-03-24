package com.example.saveareaapp.bean;

import java.util.Date;

import android.graphics.Bitmap;
import android.provider.ContactsContract.Data;

public class Resident implements PojoModel{
public static final int photoType=0;
private Integer id;//主键
private String cardID;//身份证号码
private String name;//居住人姓名
private String address;//地址
private String birthday;//出生日期

public String getBirthday() {
	return birthday;
}
public void setBirthday(String birthday) {
	this.birthday = birthday;
}
private String sex;
private String pinYin;//居住人姓名拼音
private String nation;//民族
private String phone;//手机号
private Integer houseID;//房屋ID
private Integer adderID;//采集人ID
private String createDate;//采集时间
private Boolean isDelete;//是否已经删除
private String header;//头像
private Bitmap photo;//接收读取的身份证图

public Bitmap getPhoto() {
	return photo;
}
public void setPhoto(Bitmap photo) {
	this.photo = photo;
}
public String houseName;//房屋名
public String adderName;//采集人姓名



public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}

public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getHouseName() {
	return houseName;
}
public void setHouseName(String houseName) {
	this.houseName = houseName;
}
public String getAdderName() {
	return adderName;
}
public void setAdderName(String adderName) {
	this.adderName = adderName;
}
public String getCardID() {
	return cardID;
}
public void setCardID(String cardID) {
	this.cardID = cardID;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getPinYin() {
	return pinYin;
}
public void setPinYin(String pinYin) {
	this.pinYin = pinYin;
}
public String getNation() {
	return nation;
}
public void setNation(String nation) {
	this.nation = nation;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Integer getHouseID() {
	return houseID;
}
public void setHouseID(Integer houseID) {
	this.houseID = houseID;
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
public String getHeader() {
	return header;
}
public void setHeader(String header) {
	this.header = header;
}

}
