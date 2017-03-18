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
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;

import com.yls.bus.sys.dao.entity.SysMenu;
import com.yls.bus.sys.dao.entity.SysMenuExample;
import com.yls.bus.sys.dao.entity.SysMenuViwe;
import com.yls.bus.sys.dao.entity.SysMenuViweExample;
import com.yls.bus.sys.dao.mapper.SysMenuMapper;
import com.yls.bus.sys.dao.mapper.SysMenuViweMapper;
import com.yls.bus.sys.service.SysMenuService;
import com.yls.bus.sys.service.SysUserService;
import com.yls.freamwork.utils.YlsIdGenerator;
import com.yls.freamwork.utils.YlsResultForGrid;

/**
 * @author YLS
 *
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysMenuViweMapper sysMenuViweMapper;
	
	@Autowired
	private SysUserService sysUserService;
	
	//OK
	@Override
	public List<SysMenu> queryListParentId(String parentId, List<String> menuIdList) {
		// TODO Auto-generated method stub
		//每次都有新创建一个对象  要不条件都是一样的不覆盖
		SysMenuExample sysMenuExample = new SysMenuExample();
		SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//排序
		sysMenuExample.setOrderByClause("order_num asc");
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
	
	//OK
	@Override 
	public List<SysMenu> queryNotButtonList() {
		// TODO Auto-generated method stub
		SysMenuExample sysMenuExample = new SysMenuExample();
		SysMenuExample.Criteria criteria = sysMenuExample.createCriteria();
		criteria.andTypeNotEqualTo("2");
		sysMenuExample.setOrderByClause("order_num  asc");
		return sysMenuMapper.selectByExample(sysMenuExample);
	}

	//OK
	@Override
	public List<SysMenu> getAdminMenuList() {
		// TODO Auto-generated method stub
		SysMenuExample sysMenuExample =new SysMenuExample();
		return sysMenuMapper.selectByExample(sysMenuExample);
	}
	
	//OK
	@Override
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
	
	//OK
	@Override
	public SysMenu queryObject(String menuId) {
		// TODO Auto-generated method stub
		return sysMenuMapper.selectByPrimaryKey(menuId);
	}
	
    @Override
	public YlsResultForGrid<SysMenuViwe> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
    	
	   int pageSize = 10;
	   int pageNum = 0;
	   
	   if(map.get("row") != null){
		   pageSize = Integer.parseInt( map.get("row"));
		}
	   if( map.get("page") != null){
		   pageNum = Integer.parseInt(map.get("page"));
		}
	   
	   //条件查询
		SysMenuViweExample sysMenuViweExample = new SysMenuViweExample();
		SysMenuViweExample.Criteria criteria = sysMenuViweExample.createCriteria();
	
		if(map.get("type") != null){
			criteria.andTypeEqualTo(map.get("type"));
		}
		
		//sidx order   设定排序 jqGrid
		if(!StringUtils.isEmpty(map.get("sidx"))){
			String order = map.get("sidx");
			if("parentName".equals(order)){
				order="parent_id";
			}
			if("orderNum".equals(order)){
				order="order_num";
			}
			sysMenuViweExample.setOrderByClause(order+" "+map.get("order"));
		}
		
		PageHelper.startPage(pageNum, pageSize);
		
		return new YlsResultForGrid<SysMenuViwe>(sysMenuViweMapper.selectByExample(sysMenuViweExample));
	}

    @Override
	public int queryTotal(Map<String, String> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	@Override
	public void save(SysMenu menu) {
		// TODO Auto-generated method stub
		menu.setMenuId(YlsIdGenerator.getUUID());
		sysMenuMapper.insert(menu);
	}
	@Transactional
	@Override
	public void update(SysMenu menu) {
		// TODO Auto-generated method stub
		sysMenuMapper.updateByPrimaryKey(menu);
	}
	//OK
	@Transactional
	@Override
	public void deleteBatch(String[] menuIds) {
		// TODO Auto-generated method stub
		for(String menuId: menuIds){
			sysMenuMapper.deleteByPrimaryKey(menuId);
		}
	}
	
	/**
	 * 获取所以菜单列表
	 * @param menuIdList 用户菜单列表
	 * @return 用户菜单
	 */
	private List<SysMenu> getAllMenuList(List<String> menuIdList){
		List<SysMenu> menuList = queryListParentId("0", menuIdList);
		getMenuTreeList(menuList, menuIdList);
		return menuList;
	}
	
	/**
	 * 递归获取树形菜单
	 * @param menuList 父菜单列表
	 * @param menuIdList 用户菜单列表
	 * @return 
	 */
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
