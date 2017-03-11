/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yls.bus.sys.dao.entity.SysRole;
import com.yls.bus.sys.dao.entity.SysRoleExample;
import com.yls.bus.sys.dao.mapper.SysRoleMapper;
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
	
	private SysRoleExample sysRoleExample = new SysRoleExample();

	public SysRole queryObject(String roleId) {
		// TODO Auto-generated method stub
		return sysRoleMapper.selectByPrimaryKey(roleId);
	}

	public List<SysRole> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
	   int row = 10;
	   int page = 0;
	   if(map.get("row") != null){
		   row = Integer.parseInt( map.get("row"));
	   }
	   if( map.get("page") != null){
		   page = Integer.parseInt(map.get("page"));
	   }
	   SysRoleExample.Criteria criteria = sysRoleExample.createCriteria();
	   if(map.get("role") != null){
		   criteria.andRoleNameLike("%"+map.get("role")+"%");
	   }
	   
	   PageHelper.startPage(page,row);
	   return sysRoleMapper.selectByExample(sysRoleExample);
	}

	public int queryTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Transactional 
	public void save(SysRole role) {
		// TODO Auto-generated method stub
		role.setRoleId(YlsIdGenerator.getUUID());
		sysRoleMapper.insert(role);
	}

	@Transactional
	public void update(SysRole role) {
		// TODO Auto-generated method stub
		sysRoleMapper.updateByPrimaryKey(role);
	}

	@Transactional 
	public void deleteBatch(String[] roleIds) {
		// TODO Auto-generated method stub
		for(String roleId: roleIds){
			sysRoleMapper.deleteByPrimaryKey(roleId);
		}
	}

}
