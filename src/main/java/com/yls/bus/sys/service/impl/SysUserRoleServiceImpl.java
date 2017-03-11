/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yls.bus.sys.dao.entity.SysUserRole;
import com.yls.bus.sys.dao.entity.SysUserRoleExample;
import com.yls.bus.sys.dao.mapper.SysUserRoleMapper;
import com.yls.bus.sys.service.SysUserRoleService;
import com.yls.freamwork.utils.YlsIdGenerator;

/**
 * @author YLS
 *
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	private SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
	
	@Transactional
	public void saveOrUpdate(String userId, List<String> roleIdList) {
		// TODO Auto-generated method stub
		
		SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		sysUserRoleMapper.deleteByExample(sysUserRoleExample);
		
		for(String roleId: roleIdList){
			SysUserRole sysUserRole =new SysUserRole();
			sysUserRole.setId(YlsIdGenerator.getUUID());
			sysUserRole.setUserId(userId);
			sysUserRole.setRoleId(roleId);
			sysUserRoleMapper.insert(sysUserRole);
		}
	}

	public List<String> queryRoleIdList(String userId) {
		// TODO Auto-generated method stub
		SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return sysUserRoleMapper.selectByExample(sysUserRoleExample)
												.stream().map(userRole->userRole.getRoleId())
												.collect(Collectors.toList());
	}
	
	@Transactional
	public void delete(String userId) {
		// TODO Auto-generated method stub
		SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		sysUserRoleMapper.deleteByExample(sysUserRoleExample);
	}

}
