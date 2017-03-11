/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;

import com.yls.bus.sys.dao.entity.SysConfig;

/**
 * @author YLS
 *
 */
public interface SysConfigService {
	/**
	 * ����������Ϣ
	 */
	public void save(SysConfig config);
	
	/**
	 * ����������Ϣ
	 */
	public void update(SysConfig config);
	
	/**
	 * ����key������value
	 */
	public void updateValueByKey(String key, String value);
	
	
	/**
	 * ɾ��������Ϣ
	 */
	public void deleteBatch(Long[] ids);
	
	/**
	 * ��ȡList�б�
	 */
	public List<SysConfig> queryList(Map<String, Object> map);
	/**
	 * ��ȡ�ܼ�¼��
	 */
	public int queryTotal(Map<String, Object> map);
	
	public SysConfig queryObject(Long id);
	
	/**
	 * ����key����ȡ���õ�valueֵ
	 * 
	 * @param key           key
	 * @param defaultValue  ȱʡֵ
	 */
	public String getValue(String key, String defaultValue);
	
	/**
	 * ����key����ȡvalue��Object����
	 * @param key    key
	 * @param clazz  Object����
	 */
	public <T> T getConfigObject(String key, Class<T> clazz) throws Exception;
}
