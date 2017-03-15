/**
 * 
 */
package com.yls.bus.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import com.yls.bus.sys.dao.entity.SysMenu;
import com.yls.bus.sys.dao.entity.SysMenuExample;
import com.yls.bus.sys.dao.mapper.SysMenuMapper;
import com.yls.bus.sys.service.SysMenuService;
import com.yls.bus.sys.service.SysUserService;
import com.yls.freamwork.utils.YlsIdGenerator;

import freemarker.core.ReturnInstruction.Return;

/**
 * @author YLS
 *
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysUserService sysUserService;
		
	public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList) {
		// TODO Auto-generated method stub
		SysMenuExample sysMenuExample = new SysMenuExample();
		SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<SysMenu> menuList=sysMenuMapper.selectByExample(sysMenuExample);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenu> userMenuList = new ArrayList<>();
		for(SysMenu sysMenu: menuList){
			if(menuIdList.contains(sysMenu.getMenuId())){
				userMenuList.add(sysMenu);
			}
		}
		return userMenuList;
	}

	public List<SysMenu> queryNotButtonList() {
		// TODO Auto-generated method stub
		SysMenuExample sysMenuExample = new SysMenuExample();
		SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
		criteria.andTypeNotEqualTo("2");
		return sysMenuMapper.selectByExample(sysMenuExample);
	}

	public List<SysMenu> getUserMenuList(String userId) {
		// TODO Auto-generated method stub
		if(userId != null){
			if(userId.equals("1")){
				return getAllMenuList(null);
			}else{
				List<String> menuIdList = sysUserService.queryAllMenuId(userId);
				return getAllMenuList(menuIdList);
			}
		}
		return null;
	}

	public SysMenu queryObject(String menuId) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectByPrimaryKey(menuId);
	}

	public List<SysMenu> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
	   int row = 10;
	   int page = 0;
	   if(map.get("row") != null){
		  row = Integer.parseInt( map.get("row"));
		}
	   if( map.get("page") != null){
		  page = Integer.parseInt(map.get("page"));
		}
		SysMenuExample sysMenuExample = new SysMenuExample();
		SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
		if(map.get("type") != null){
			criteria.andTypeEqualTo(map.get("type"));
		}
		  
		PageHelper.startPage(page, row);
		return sysMenuMapper.selectByExample(sysMenuExample);
	}

	public int queryTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	public void save(SysMenu menu) {
		// TODO Auto-generated method stub
		menu.setMenuId(YlsIdGenerator.getUUID());
		sysMenuMapper.insert(menu);
	}
	@Transactional
	public void update(SysMenu menu) {
		// TODO Auto-generated method stub
		sysMenuMapper.updateByPrimaryKey(menu);
	}
	@Transactional
	public void deleteBatch(String[] menuIds) {
		// TODO Auto-generated method stub
		for(String menuId: menuIds){
			sysMenuMapper.deleteByPrimaryKey(menuId);
		}
	}
	
	/**
	 * 获取所以菜单列表
	 * @param menuIdList
	 * @return
	 */
	private List<SysMenu> getAllMenuList(List<String> menuIdList){
		List<SysMenu> menuList = queryListParentId("0", menuIdList);
		getMenuTreeList(menuList, menuIdList);
		return menuList;
	}
	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<String> menuIdList){
		List<SysMenu> subMenuList = new ArrayList<>();
		for (SysMenu sysMenu: menuList){
			if(sysMenu.getType().equals("0")){
					sysMenu.setChildernList(getMenuTreeList(queryListParentId(sysMenu.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(sysMenu);
		}
		return subMenuList;
	}
}
