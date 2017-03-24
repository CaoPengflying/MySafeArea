package com.example.saveareaapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class News implements PojoModel{
	public static final int photoType=3;
	private Integer id;//主键
	private String title;//标题
	private String content;//内容（html）
	private Date releaseTime;//发布时间
	private Integer releaseID;//发布人ID
	private Boolean headlineMarker;//头条标记（最多五个）
	private Boolean isDelete;//是否已经删除
	private List<String> photos=new ArrayList<String>(0);//新闻中包含的图片
	
	public String releaseName;//发布人姓名
	
	
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
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
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
	public List<String> getPhotos() {
		return photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	
	

}
