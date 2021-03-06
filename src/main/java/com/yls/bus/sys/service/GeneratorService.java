/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;

/**
 * @author Lian Shan Yang
 *
 */
public interface GeneratorService {

	List<Map<String, Object>> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	/**
	 * ���ɴ���
	 */
	byte[] generatorCode(String[] tableNames);
}
