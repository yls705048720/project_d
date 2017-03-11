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
	 * 随机生成UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString()
							.replace("-", "");
	}
	
	/**
	 * 根据字符串生产固定UUID
	 * @param name
	 * @return
	 */
	public static String getUUID(String name){
		return UUID.nameUUIDFromBytes(name.getBytes())
							.toString().replace("-", "");
	}
}
