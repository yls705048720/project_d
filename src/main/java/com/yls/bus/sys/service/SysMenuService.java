/**
 * 
 */
package com.yls.bus.sys.service;

import java.util.List;
import java.util.Map;

import com.yls.bus.sys.dao.entity.SysMenu;



/**
 * @author YLS
 *
 */
public interface SysMenuService {
	/**
	 * ���ݸ��˵�����ѯ�Ӳ˵�
	 * @param parentId ���˵�ID
	 * @param menuIdList  �û��˵�ID
	 */
	List<SysMenu> queryListParentId(String parentId, List<String> menuIdList);
	
	/**
	 * ��ȡ��������ť�Ĳ˵��б�
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * ��ȡ�û��˵��б�
	 */
	List<SysMenu> getUserMenuList(String userId);
	
	
	/**
	 * ��ѯ�˵�
	 */
	SysMenu queryObject(String menuId);
	
	/**
	 * ��ѯ�˵��б�
	 */
	List<SysMenu> queryList(Map<String, String> map);
	
	/**
	 * ��ѯ����
	 */
	int queryTotal(Map<String, String> map);
	
	/**
	 * ����˵�
	 */
	void save(SysMenu menu);
	
	/**
	 * �޸�
	 */
	void update(SysMenu menu);
	
	/**
	 * ɾ��
	 */
	void deleteBatch(String[] menuIds);
}
