/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.yls.bus.sys.dao.entity.SysUser;
import com.yls.bus.sys.dao.entity.SysUserExample;
import com.yls.bus.sys.dao.entity.SysUserMenuViewExample;
import com.yls.bus.sys.dao.mapper.SysUserMapper;
import com.yls.bus.sys.dao.mapper.SysUserMenuViewMapper;
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
	private SysUserExample sysUserExample = new SysUserExample();
	
	@Autowired
	private SysUserMenuViewMapper userMenuViewMapper;
	private SysUserMenuViewExample userMenuViewExample = new SysUserMenuViewExample();
	

	public List<String> queryAllPerms(String userId) {
		// TODO Auto-generated method stub
		SysUserMenuViewExample.Criteria criteria = userMenuViewExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userMenuViewMapper.selectByExample(userMenuViewExample)
													.stream().map(userMenuView->userMenuView.getMenuPerms())
													.collect(Collectors.toList());
	}

	public List<String> queryAllMenuId(String userId) {
		// TODO Auto-generated method stub
		SysUserMenuViewExample.Criteria criteria = userMenuViewExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userMenuViewMapper.selectByExample(userMenuViewExample)
													.stream().map(userMenuView->userMenuView.getMenuId())
													.collect(Collectors.toList());
	}

	public SysUser queryByUserName(String username) {
		// TODO Auto-generated method stub
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

	public SysUser queryObject(String userId) {
		// TODO Auto-generated method stub
		if(userId != null){
			return sysUserMapper.selectByPrimaryKey(userId);
		}else{
			return null;
		}
	}

	public List<SysUser> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
	   int row = 10;
	   int page = 0;
	   if(map.get("row") != null){
		   row = Integer.parseInt( map.get("row"));
	   }
	   if( map.get("page") != null){
		   page = Integer.parseInt(map.get("page"));
	   }
	   //条件查询
	   SysUserExample.Criteria criteria = sysUserExample.createCriteria();
	   if(map.get("name") != null){
		   criteria.andUsernameLike("%"+map.get("name")+"%");
	   }
	   
	   PageHelper.startPage(page,row);
	   return sysUserMapper.selectByExample(sysUserExample);
	}

	public int queryTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean checkUserName(String name) {
		// TODO Auto-generated method stub
		if(queryByUserName(name) == null){
			return false;
		}else{
			return true;
		}
	}
	
	@Transactional
	public void save(SysUser user) {
		// TODO Auto-generated method stub
		user.setUserId(YlsIdGenerator.getUUID());
		user.setCreateTime(new Date());
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		sysUserMapper.insert(user);
	}

	@Transactional
	public void update(SysUser user) {
		// TODO Auto-generated method stub
		//没有填写的不更新
		sysUserMapper.updateByPrimaryKey(user);
	}

	@Transactional
	public void deleteBatch(String[] userIds) {
		// TODO Auto-generated method stub
		for(String id: userIds){
			sysUserMapper.deleteByPrimaryKey(id);
		}
	}
	
	@Transactional
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
	
}
