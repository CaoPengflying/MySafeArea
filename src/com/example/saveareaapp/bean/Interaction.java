package com.example.saveareaapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Interaction implements PojoModel{
	
int a,b,c,d;
public final static int photoType=4;
private Integer id;//主键
private String title;//标题
private String content;//内容
private List<String> photos=new ArrayList<String>(0);//图片集合
private Date releaseTime;//发布时间
private int agreeNum;//点赞数
private Boolean isDelete;//是否已经删除
private List<Reply> replies=new ArrayList<Reply>(0);
private Integer releaseID;
private String releaseName;
private List<Agree> agrees=new ArrayList<Agree>(0);


public int getA() {
	return a;
}

public void setA(int a) {
	this.a = a;
}

public int getB() {
	return b;
}

public void setB(int b) {
	this.b = b;
}

public int getC() {
	return c;
}

public void setC(int c) {
	this.c = c;
}

public int getD() {
	return d;
}

public void setD(int d) {
	this.d = d;
}

public void setImage(int a,int b,int c, int d){
	this.a = a;
	this.b = b;
	this.c = c;
	this.d = d;
}

public Interaction(){
}
public Interaction(String context){
	this.content = context;
}
public Integer getReleaseID() {
	return releaseID;
}
public void setReleaseID(Integer releaseID) {
	this.releaseID = releaseID;
}
public String getReleaseName() {
	return releaseName;
}
public void setReleaseName(String releaseName) {
	this.releaseName = releaseName;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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

public List<String> getPhotos() {
	return photos;
}
public void setPhotos(List<String> photos) {
	this.photos = photos;
}
public Date getReleaseTime() {
	return releaseTime;
}
public void setReleaseTime(Date releaseTime) {
	this.releaseTime = releaseTime;
}
public int getAgreeNum() {
	return agreeNum;
}
public void setAgreeNum(int agreeNum) {
	this.agreeNum = agreeNum;
}
public Boolean getIsDelete() {
	return isDelete;
}
public void setIsDelete(Boolean isDelete) {
	this.isDelete = isDelete;
}
public List<Reply> getReplies() {
	return replies;
}
public void setReplies(List<Reply> replies) {
	this.replies = replies;
}
public List<Agree> getAgrees() {
	return agrees;
}
public void setAgrees(List<Agree> agrees) {
	this.agrees = agrees;
}


}
