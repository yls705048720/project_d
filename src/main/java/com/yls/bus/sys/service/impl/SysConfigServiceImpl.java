/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yls.bus.sys.dao.entity.SysConfig;
import com.yls.bus.sys.dao.entity.SysConfigExample;
import com.yls.bus.sys.dao.mapper.SysConfigMapper;
import com.yls.bus.sys.service.SysConfigService;

/**
 * @author YLS
 *
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
	
	@Autowired
	private SysConfigMapper sysConfigMapper;
	
	private SysConfigExample sysConfigExample = new SysConfigExample();

	public void save(SysConfig config) {
		// TODO Auto-generated method stub
		
	}

	public void update(SysConfig config) {
		// TODO Auto-generated method stub
		
	}

	public void updateValueByKey(String key, String value) {
		// TODO Auto-generated method stub
		
	}

	public void deleteBatch(Long[] ids) {
		// TODO Auto-generated method stub
		
	}

	public List<SysConfig> queryList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	public int queryTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public SysConfig queryObject(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getValue(String key, String defaultValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T getConfigObject(String key, Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
