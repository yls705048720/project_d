package com.yls.bus.sys.dao.mapper;

import com.yls.bus.sys.dao.entity.SysUserMenuView;
import com.yls.bus.sys.dao.entity.SysUserMenuViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserMenuViewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_sys_user_menu
     *
     * @mbg.generated Sat Mar 18 09:49:29 CST 2017
     */
    long countByExample(SysUserMenuViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_sys_user_menu
     *
     * @mbg.generated Sat Mar 18 09:49:29 CST 2017
     */
    int deleteByExample(SysUserMenuViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_sys_user_menu
     *
     * @mbg.generated Sat Mar 18 09:49:29 CST 2017
     */
    int insert(SysUserMenuView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_sys_user_menu
     *
     * @mbg.generated Sat Mar 18 09:49:29 CST 2017
     */
    int insertSelective(SysUserMenuView record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_sys_user_menu
     *
     * @mbg.generated Sat Mar 18 09:49:29 CST 2017
     */
    List<SysUserMenuView> selectByExample(SysUserMenuViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_sys_user_menu
     *
     * @mbg.generated Sat Mar 18 09:49:29 CST 2017
     */
    int updateByExampleSelective(@Param("record") SysUserMenuView record, @Param("example") SysUserMenuViewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table v_sys_user_menu
     *
     * @mbg.generated Sat Mar 18 09:49:29 CST 2017
     */
    int updateByExample(@Param("record") SysUserMenuView record, @Param("example") SysUserMenuViewExample example);
}