/**
 * 
 */
package com.yls.freamwork.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * ���ؽ��
 * @author YLS
 *
 */
public class YlsResult extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public YlsResult() {
		put("code", 0);
	}
	
	public static YlsResult error() {
		return error(500, "δ֪�쳣������ϵ����Ա");
	}
	
	public static YlsResult error(String msg) {
		return error(500, msg);
	}
	
	public static YlsResult error(int code, String msg) {
		YlsResult r = new YlsResult();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static YlsResult ok(String msg) {
		YlsResult r = new YlsResult();
		r.put("msg", msg);
		return r;
	}
	
	public static YlsResult ok(Map<String, Object> map) {
		YlsResult r = new YlsResult();
		r.putAll(map);
		return r;
	}
	
	public static YlsResult ok() {
		return new YlsResult();
	}

	public YlsResult put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
