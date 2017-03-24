package com.example.saveareaapp.config;

public class Newconfig {
	private int method;//方法名
	private String params;//参数
	
	public Newconfig(){
		
	}
	public Newconfig(int method){
		this.method = method;
	}
	
	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	
	public void setParams(String params) {
		this.params = params;
	}
	public String getParamters() {
		
		return "method="+method+"&params="+params;
	}
	
	
	
}
