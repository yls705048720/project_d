/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;

/**
 * @author YLS
 *
 */
public interface SysUserRoleService {
	
	void saveOrUpdate(String userId, List<String> roleIdList);
	
	/**
	 * �����û�ID����ȡ��ɫID�б�
	 */
	List<String> queryRoleIdList(String userId);
	
	void delete(String userId);
}
