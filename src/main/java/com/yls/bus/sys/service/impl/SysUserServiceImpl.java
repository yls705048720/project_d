/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yls.bus.sys.dao.entity.SysUser;
import com.yls.bus.sys.dao.entity.SysUserExample;
import com.yls.bus.sys.dao.entity.SysUserMenuViewExample;
import com.yls.bus.sys.dao.entity.SysUserRole;
import com.yls.bus.sys.dao.entity.SysUserRoleExample;
import com.yls.bus.sys.dao.mapper.SysUserMapper;
import com.yls.bus.sys.dao.mapper.SysUserMenuViewMapper;
import com.yls.bus.sys.dao.mapper.SysUserRoleMapper;
import com.yls.bus.sys.service.SysUserService;
import com.yls.freamwork.utils.YlsIdGenerator;

/**
 * @author YLS
 *
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysUserMenuViewMapper userMenuViewMapper;
	

	@Override
	public List<String> queryAllPerms(String userId) {
		// TODO Auto-generated method stub
		SysUserMenuViewExample userMenuViewExample = new SysUserMenuViewExample();
		SysUserMenuViewExample.Criteria criteria = userMenuViewExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userMenuViewMapper.selectByExample(userMenuViewExample)
													.stream().map(userMenuView->userMenuView.getMenuPerms())
													.collect(Collectors.toList());
	}

	@Override
	public List<String> queryAllMenuId(String userId) {
		// TODO Auto-generated method stub
		SysUserMenuViewExample userMenuViewExample = new SysUserMenuViewExample();
		SysUserMenuViewExample.Criteria criteria = userMenuViewExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userMenuViewMapper.selectByExample(userMenuViewExample)
													.stream().map(userMenuView->userMenuView.getMenuId())
													.collect(Collectors.toList());
	}

	@Override
	public SysUser queryByUserName(String username) {
		// TODO Auto-generated method stub
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
		if(username != null){
			criteria.andUsernameEqualTo(username);
		}
		List<SysUser> list=sysUserMapper.selectByExample(sysUserExample);
		if(list !=null &&list.size() == 1){
			return list.get(0);
		}else{
			return null;
		}
	}

	@Override
	public SysUser queryObject(String userId) {
		// TODO Auto-generated method stub
		if(userId != null){
			SysUser sysUser=sysUserMapper.selectByPrimaryKey(userId);
			sysUser.setRoleIdList(queryRoleIdList(userId));
			return sysUser;
		}else{
			return null;
		}
	}

	@Override
	public List<SysUser> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
	   int rows = 10;
	   int page = 0;
	   if(map.get("rows") != null){
		   rows = Integer.parseInt( map.get("rows"));
	   }
	   if( map.get("page") != null){
		   page = Integer.parseInt(map.get("page"));
	   }
	   //条件查询
	   SysUserExample sysUserExample = new SysUserExample();
	   SysUserExample.Criteria criteria = sysUserExample.createCriteria();
	   if(StringUtils.isNotEmpty(map.get("username"))){
		   criteria.andUsernameLike("%"+map.get("username")+"%");
	   }
	   
	   PageHelper.startPage(page,rows);
	   return sysUserMapper.selectByExample(sysUserExample);
	}

	public int queryTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkUserName(String name) {
		// TODO Auto-generated method stub
		if(queryByUserName(name) == null){
			return true;
		}else{
			return false;
		}
	}
	
	@Transactional
	@Override
	public void save(SysUser user) {
		// TODO Auto-generated method stub
		user.setUserId(YlsIdGenerator.getUUID());
		user.setCreateTime(new Date());
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		sysUserMapper.insert(user);
		saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Transactional
	public void update(SysUser user) {
		// TODO Auto-generated method stub
		//没有填写的不更新
		if(StringUtils.isNotEmpty(user.getPassword())){
			user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		}
		sysUserMapper.updateByPrimaryKey(user);
		saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Transactional
	@Override
	public void deleteBatch(String[] userIds) {
		// TODO Auto-generated method stub
		for(String id: userIds){
			sysUserMapper.deleteByPrimaryKey(id);
			//删除用户和角色关系
			delete(id);
		}
	}
	
	@Transactional
	@Override
	public int updatePassword(String userId, String password, String newPassword) {
		// TODO Auto-generated method stub
		SysUser  sysUser = sysUserMapper.selectByPrimaryKey(userId);
		
		if(sysUser.getPassword().equals(new Sha256Hash(password).toHex())){
			sysUser.setPassword(new Sha256Hash(newPassword ).toHex());
			sysUserMapper.updateByPrimaryKey(sysUser);
			return 1;
		}else{
			return -1;
		}
	}
	
	/**
	 * 添加更新角色列表
	 * @param userId
	 * @param roleIdList
	 */
	@Transactional
	private void saveOrUpdate(String userId, List<String> roleIdList) {
		// TODO Auto-generated method stub
		SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
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
	
	/**
	 * 获取用户角色列表
	 * @param userId
	 * @return
	 */
	private List<String> queryRoleIdList(String userId) {
		// TODO Auto-generated method stub
		SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return sysUserRoleMapper.selectByExample(sysUserRoleExample)
												.stream().map(userRole->userRole.getRoleId())
												.collect(Collectors.toList());
	}
	
	/**
	 * 删除用户与角色关系
	 * @param userId
	 */
	private void delete(String userId) {
		// TODO Auto-generated method stub
		SysUserRoleExample sysUserRoleExample = new SysUserRoleExample();
		SysUserRoleExample.Criteria criteria = sysUserRoleExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		sysUserRoleMapper.deleteByExample(sysUserRoleExample);
	}
	
}
