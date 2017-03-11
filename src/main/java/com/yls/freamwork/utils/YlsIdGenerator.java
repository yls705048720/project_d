/**
 * 
 */
package com.yls.freamwork.utils;

import java.util.UUID;

/**
 * @author YLS
 *
 */
public interface YlsIdGenerator {
	
	/**
	 * �������UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString()
							.replace("-", "");
	}
	
	/**
	 * �����ַ��������̶�UUID
	 * @param name
	 * @return
	 */
	public static String getUUID(String name){
		return UUID.nameUUIDFromBytes(name.getBytes())
							.toString().replace("-", "");
	}
}
