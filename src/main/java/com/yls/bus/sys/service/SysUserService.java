/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;

import com.yls.bus.sys.dao.entity.SysUser;

/**
 * @author YLS
 *
 */
public interface SysUserService {

	/**
	 * ��ѯ�û�������Ȩ��
	 * @param userId  �û�ID
	 */
	List<String> queryAllPerms(String userId);
	
	/**
	 * ��ѯ�û������в˵�ID
	 */
	List<String> queryAllMenuId(String userId);
	
	/**
	 * �����û�������ѯϵͳ�û�
	 */
	SysUser queryByUserName(String username);
	
	/**
	 * �����û�ID����ѯ�û�
	 * @param userId
	 * @return
	 */
	SysUser queryObject(String userId);
	
	/**
	 * ��ѯ�û��б�
	 */
	List<SysUser> queryList(Map<String, String> map);
	
	/**
	 * ��ѯ����
	 */
	int queryTotal(Map<String, String> map);
	
	/**
	 * ����û���Ψһ��
	 */
	boolean checkUserName(String name);
	
	/**
	 * �����û�
	 */
	void save(SysUser user);
	
	/**
	 * �޸��û�
	 */
	void update(SysUser user);
	
	/**
	 * ɾ���û�
	 */
	void deleteBatch(String[] userIds);
	
	/**
	 * �޸�����
	 * @param userId       �û�ID
	 * @param password     ԭ����
	 * @param newPassword  ������
	 */
	int updatePassword(String userId, String password, String newPassword);
}
