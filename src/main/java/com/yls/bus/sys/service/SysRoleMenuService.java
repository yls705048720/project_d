/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;

/**
 * @author YLS
 *
 */
public interface SysRoleMenuService {
	
	void saveOrUpdate(String roleId, List<String> menuIdList);
	
	/**
	 * ���ݽ�ɫID����ȡ�˵�ID�б�
	 */
	List<String> queryMenuIdList(String roleId);
}
