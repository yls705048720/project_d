package com.yls.bus.sys.dao.entity;

import java.util.List;

public class SysMenu {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.menu_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String menuId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.parent_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String parentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.name
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String name;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.url
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String url;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.perms
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String perms;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.type
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String type;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.icon
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String icon;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_menu.order_num
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String orderNum;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.menu_id
	 * @return  the value of sys_menu.menu_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.menu_id
	 * @param menuId  the value for sys_menu.menu_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.parent_id
	 * @return  the value of sys_menu.parent_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.parent_id
	 * @param parentId  the value for sys_menu.parent_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.name
	 * @return  the value of sys_menu.name
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.name
	 * @param name  the value for sys_menu.name
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.url
	 * @return  the value of sys_menu.url
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.url
	 * @param url  the value for sys_menu.url
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.perms
	 * @return  the value of sys_menu.perms
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getPerms() {
		return perms;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.perms
	 * @param perms  the value for sys_menu.perms
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setPerms(String perms) {
		this.perms = perms;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.type
	 * @return  the value of sys_menu.type
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.type
	 * @param type  the value for sys_menu.type
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.icon
	 * @return  the value of sys_menu.icon
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.icon
	 * @param icon  the value for sys_menu.icon
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_menu.order_num
	 * @return  the value of sys_menu.order_num
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_menu.order_num
	 * @param orderNum  the value for sys_menu.order_num
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	/**
	 * �Ӳ˵��б�
	 */
	private List<SysMenu> childernList;

	public List<SysMenu> getChildernList() {
		return childernList;
	}

	public void setChildernList(List<SysMenu> childernList) {
		this.childernList = childernList;
	}
	
	
}