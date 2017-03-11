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
	 * 根据角色ID，获取菜单ID列表
	 */
	List<String> queryMenuIdList(String roleId);
}
