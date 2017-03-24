package com.example.saveareaapp.bean;

public class Photo implements PojoModel{
private Integer id;//主键
private Integer objId;//外键：图片所属对象的外键

private String path;//路径 相对于WebRootPath的相对路径
private int type;//类型：0：居住人身份证头像 1：小区图片 2：用户头像 3：新闻图片 4:interaction图片




public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}

public Integer getObjId() {
	return objId;
}
public void setObjId(Integer objId) {
	this.objId = objId;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}


}
