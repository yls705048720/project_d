/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yls.bus.sys.dao.entity.SysRoleMenu;
import com.yls.bus.sys.dao.entity.SysRoleMenuExample;
import com.yls.bus.sys.dao.mapper.SysRoleMenuMapper;
import com.yls.bus.sys.service.SysRoleMenuService;
import com.yls.freamwork.utils.YlsIdGenerator;

/**
 * @author YLS
 *
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	
	private SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
	
 	@Transactional 
	public void saveOrUpdate(String roleId, List<String> menuIdList) {
		// TODO Auto-generated method stub
 		
 		SysRoleMenuExample.Criteria criteria = sysRoleMenuExample.createCriteria();
 		criteria.andRoleIdEqualTo(roleId);
 		sysRoleMenuMapper.deleteByExample(sysRoleMenuExample);
 		
		for(String menuId: menuIdList){
			SysRoleMenu sysRoleMenu = new SysRoleMenu();
			sysRoleMenu.setId(YlsIdGenerator.getUUID());
			sysRoleMenu.setRoleId(roleId);
			sysRoleMenu.setMenuId(menuId);
			sysRoleMenuMapper.insert(sysRoleMenu);
		}
	}

	public List<String> queryMenuIdList(String roleId) {
		// TODO Auto-generated method stub
		SysRoleMenuExample.Criteria criteria = sysRoleMenuExample.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return sysRoleMenuMapper.selectByExample(sysRoleMenuExample)
													.stream().map(roleMenu->roleMenu.getMenuId())
													.collect(Collectors.toList());
	}

}
