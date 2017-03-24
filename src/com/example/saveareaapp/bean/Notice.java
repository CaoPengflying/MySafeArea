package com.example.saveareaapp.bean;

import java.util.Date;

public class Notice implements PojoModel{
private Integer id;//主键
private String title;//标题
private String content;//内容
private Integer releaseID;//发布人ID
private Boolean headlineMarker;
private Boolean isDelete;
private Date releaseTime;//发布时间

public String releaseName;//发布人姓名

public Notice(){
	
}
public Notice(String title){
	this.title = title;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getReleaseName() {
	return releaseName;
}
public void setReleaseName(String releaseName) {
	this.releaseName = releaseName;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Integer getReleaseID() {
	return releaseID;
}
public void setReleaseID(Integer releaseID) {
	this.releaseID = releaseID;
}
public Boolean getHeadlineMarker() {
	return headlineMarker;
}
public void setHeadlineMarker(Boolean headlineMarker) {
	this.headlineMarker = headlineMarker;
}
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public Date getReleaseTime() {
	return releaseTime;
}
public void setReleaseTime(Date releaseTime) {
	this.releaseTime = releaseTime;
}


}
