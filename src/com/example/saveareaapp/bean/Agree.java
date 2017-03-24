package com.example.saveareaapp.bean;

import java.util.Date;

//互动点赞
public class Agree {
private Integer id;
private Integer interactionID;//
private Integer agreeID;//点赞的用户
private Date agreeTime;//点赞的时间
private Boolean isDelete;//是否删除
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getInteractionID() {
	return interactionID;
}
public void setInteractionID(Integer interactionID) {
	this.interactionID = interactionID;
}
public Integer getAgreeID() {
	return agreeID;
}
public void setAgreeID(Integer agreeID) {
	this.agreeID = agreeID;
}
public Date getAgreeTime() {
	return agreeTime;
}
public void setAgreeTime(Date agreeTime) {
	this.agreeTime = agreeTime;
}
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}

}
