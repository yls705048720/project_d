/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yls.bus.sys.dao.entity.SysRole;
import com.yls.bus.sys.dao.entity.SysRoleExample;
import com.yls.bus.sys.dao.entity.SysRoleMenu;
import com.yls.bus.sys.dao.entity.SysRoleMenuExample;
import com.yls.bus.sys.dao.mapper.SysRoleMapper;
import com.yls.bus.sys.dao.mapper.SysRoleMenuMapper;
import com.yls.bus.sys.service.SysRoleService;
import com.yls.freamwork.utils.YlsIdGenerator;

/**
 * @author YLS
 *
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
		
	@Override
	public SysRole queryObject(String roleId) {
		// TODO Auto-generated method stub
		SysRole sysRole=sysRoleMapper.selectByPrimaryKey(roleId);
		sysRole.setMenuIdList(queryMenuIdList(roleId));
		return sysRole;
	}

	@Override
	public List<SysRole> queryList(Map<String, String> map){
		// TODO Auto-generated method stub
	   int rows = 10;
	   int page = 0;
	   
	   if(StringUtils.isNotEmpty(map.get("rows"))){
		   rows = Integer.parseInt( map.get("rows"));
	   }
	   if( StringUtils.isNotEmpty(map.get("page"))){
		   page = Integer.parseInt(map.get("page"));
	   }
	   
	   SysRoleExample sysRoleExample = new SysRoleExample();
	   SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
	   
	   
	   if(StringUtils.isNotEmpty(map.get("roleName"))){
		   criteria.andRoleNameLike("%"+map.get("roleName")+"%");
	   }
	   
	   PageHelper.startPage(page,rows);
	   return sysRoleMapper.selectByExample(sysRoleExample);
	}

	@Override
	public int queryTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public List<SysRole> selectAll() {
		// TODO Auto-generated method stub
		return sysRoleMapper.selectByExample(null);
	}
	
	
	@Transactional 
	@Override
	public void save(SysRole role) {
		// TODO Auto-generated method stub
		role.setRoleId(YlsIdGenerator.getUUID());
		//添加角色
		sysRoleMapper.insert(role);
		//更新角色菜单关系
		saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Transactional
	@Override
	public void update(SysRole role) {
		// TODO Auto-generated method stub
		sysRoleMapper.updateByPrimaryKey(role);
		saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Transactional
	@Override
	public void deleteBatch(String[] roleIds) {
		// TODO Auto-generated method stub
		for(String roleId: roleIds){
			sysRoleMapper.deleteByPrimaryKey(roleId);
			//同时删除角色菜单表中的数据
			SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
			SysRoleMenuExample.Criteria criteria = sysRoleMenuExample.createCriteria();
			criteria.andRoleIdEqualTo(roleId);
			sysRoleMenuMapper.deleteByExample(sysRoleMenuExample);
		}
	}
	
	
	/**
	 * 更新角色和菜单的关系
	 * @param roleId
	 * @param menuIdList
	 */
	public void saveOrUpdate(String roleId, List<String> menuIdList) {
		// TODO Auto-generated method stub
 		SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
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
 	
 	/**
 	 * 根据角色Id查询角色拥有的菜单
 	 * @param roleId
 	 * @return
 	 */
	public List<String> queryMenuIdList(String roleId) {
		// TODO Auto-generated method stub
		SysRoleMenuExample sysRoleMenuExample = new SysRoleMenuExample();
		SysRoleMenuExample.Criteria criteria = sysRoleMenuExample.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		return sysRoleMenuMapper.selectByExample(sysRoleMenuExample)
													.stream().map(roleMenu->roleMenu.getMenuId())
													.collect(Collectors.toList());
	}


}
