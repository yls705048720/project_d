/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;


import com.yls.bus.sys.dao.entity.SysRole;

/**
 * @author YLS
 *
 */
public interface SysRoleService {
	
	SysRole queryObject(String roleId);
	
	List<SysRole> queryList(Map<String, String> map);
	
	int queryTotal(Map<String, String> map);
	
	void save(SysRole role);
	
	void update(SysRole role);
	
	void deleteBatch(String[] roleIds);
}
