/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;

import com.yls.bus.sys.dao.entity.SysMenu;
import com.yls.freamwork.utils.YlsResultForGrid;



/**
 * @author YLS
 *
 */
public interface SysMenuService {
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenu> queryListParentId(String parentId, List<String> menuIdList);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenu> getUserMenuList(String userId);
	
	/**
	 * 获取超级管理员权限
	 * @return
	 */
	List<SysMenu> getAdminMenuList();
	
	/**
	 * 查询菜单
	 */
	SysMenu queryObject(String menuId);
	
	/**
	 * 查询菜单列表
	 */
	YlsResultForGrid<SysMenu> queryList(Map<String, String> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, String> map);
	
	/**
	 * 保存菜单
	 */
	void save(SysMenu menu);
	
	/**
	 * 修改
	 */
	void update(SysMenu menu);
	
	/**
	 * 删除
	 */
	void deleteBatch(String[] menuIds);
}
