package com.example.saveareaapp.util;

import java.util.Map;

public class MapToString {
	/** 
	 * ��mapת����url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByMap(Map<String, Object> map) {  
	    if (map == null) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for (Map.Entry<String, Object> entry : map.entrySet()) {  
	        sb.append(entry.getKey() + "=" + entry.getValue());  
	        sb.append("&");  
	    }  
	    String s = sb.toString();  
	    if (s.endsWith("&")) {  
	        //s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");  
	    }  
	    return s;  
	}  
}
