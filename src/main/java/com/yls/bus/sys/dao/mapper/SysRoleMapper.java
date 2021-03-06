package com.yls.bus.sys.dao.mapper;

import com.yls.bus.sys.dao.entity.SysRole;
import com.yls.bus.sys.dao.entity.SysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	long countByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int deleteByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int deleteByPrimaryKey(String roleId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int insert(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int insertSelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	List<SysRole> selectByExample(SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	SysRole selectByPrimaryKey(String roleId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int updateByPrimaryKeySelective(SysRole record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_role
	 * @mbg.generated  Thu Mar 09 22:12:03 CST 2017
	 */
	int updateByPrimaryKey(SysRole record);
}