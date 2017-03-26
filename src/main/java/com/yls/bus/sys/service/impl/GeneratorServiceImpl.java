/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yls.bus.sys.dao.mapper.GeneratorMapper;
import com.yls.bus.sys.service.GeneratorService;
import com.yls.freamwork.generator.GenUtils;

/**
 * @author Lian Shan Yang
 *
 */
@Service
public class GeneratorServiceImpl implements GeneratorService{

	@Autowired
	private GeneratorMapper generatorMapper;
	
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return generatorMapper.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return generatorMapper.queryTotal(map);
	}

	@Override
	public Map<String, String> queryTable(String tableName) {
		// TODO Auto-generated method stub
		return generatorMapper.queryTable(tableName);
	}

	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		// TODO Auto-generated method stub
		return generatorMapper.queryColumns(tableName);
	}

	@Override
	public byte[] generatorCode(String[] tableNames) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
