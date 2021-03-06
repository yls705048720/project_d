package com.yls.bus.sys.dao.entity;


import java.util.Date;
import java.util.List;

public class SysRole {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.role_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String roleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.role_name
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String roleName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.remark
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private String remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.create_time
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	private Date createTime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.role_id
	 * @return  the value of sys_role.role_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getRoleId() {
		return roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.role_id
	 * @param roleId  the value for sys_role.role_id
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.role_name
	 * @return  the value of sys_role.role_name
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.role_name
	 * @param roleName  the value for sys_role.role_name
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.remark
	 * @return  the value of sys_role.remark
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.remark
	 * @param remark  the value for sys_role.remark
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.create_time
	 * @return  the value of sys_role.create_time
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.create_time
	 * @param createTime  the value for sys_role.create_time
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 角色对应菜单Id列表
	 */
	private List<String> menuIdList;

	/**
	 * @return the menuIdList
	 */
	public List<String> getMenuIdList() {
		return menuIdList;
	}

	/**
	 * @param menuIdList the menuIdList to set
	 */
	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}

}