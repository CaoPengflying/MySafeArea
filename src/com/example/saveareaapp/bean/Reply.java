package com.example.saveareaapp.bean;

import java.util.Date;

public class Reply implements PojoModel{
private Integer id;//主键
private Integer interactionID;//互动ID
private String content;//评论内容
private Integer replyerID;//评论人|回复人
private String replyerName;//评论人姓名
private Photo  replyHeader;//评论人头像
private Date replyTime;//评论时间
private Integer type;//类型 0：评论1：回复（民警）
private Boolean isDelete;//是否已经删除

public Integer getInteractionID() {
	return interactionID;
}

public void setInteractionID(Integer interactionID) {
	this.interactionID = interactionID;
}

public String getContent() {
	return content;
}

public void setContent(String content) {
	this.content = content;
}



public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Integer getReplyerID() {
	return replyerID;
}

public void setReplyerID(Integer replyerID) {
	this.replyerID = replyerID;
}


public Date getReplyTime() {
	return replyTime;
}

public void setReplyTime(Date replyTime) {
	this.replyTime = replyTime;
}

public Integer getType() {
	return type;
}

public void setType(Integer type) {
	this.type = type;
}

public Boolean getIsDelete() {
	return isDelete;
}

public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}

public String getReplyerName() {
	return replyerName;
}

public void setReplyerName(String replyerName) {
	this.replyerName = replyerName;
}

public Photo getReplyHeader() {
	return replyHeader;
}

public void setReplyHeader(Photo replyHeader) {
	this.replyHeader = replyHeader;
}


}
