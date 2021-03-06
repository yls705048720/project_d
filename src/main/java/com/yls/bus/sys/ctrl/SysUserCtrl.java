/**
 * 
 */
package com.yls.bus.sys.ctrl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yls.bus.sys.dao.entity.SysUser;
import com.yls.bus.sys.service.SysUserService;
import com.yls.freamwork.common.AbstractCtrl;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsResultForGrid;
import com.yls.freamwork.utils.YlsShiroUtils;

/**
 * @author YLS
 *
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserCtrl extends AbstractCtrl {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 获取当前登陆用户信息
	 * @return
	 */
	@RequestMapping(value="/info",method=RequestMethod.GET)
	public YlsResult userInfo(){
		return YlsResult.ok().put("user", YlsShiroUtils.getUserEntity());
	}
	
	/**
	 * 修改密码
	 * @param password
	 * @param newPassword
	 * @return
	 */

	@RequestMapping(value="/password", method=RequestMethod.POST)
	public YlsResult  cPassword(String password ,String newPassword){
		if(sysUserService.updatePassword(YlsShiroUtils.getUserId(), password, newPassword) == 1){
			return YlsResult.ok();
		}else{
			return YlsResult.error();
		}
	}
	

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public YlsResult save(@RequestBody SysUser sysUser){
		sysUserService.save(sysUser);
		return YlsResult.ok();
	}
	

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public YlsResult update(@RequestBody SysUser sysUser){
		sysUserService.update(sysUser);
		return YlsResult.ok();
	}
	

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public YlsResult delete(@RequestBody String [] userIds){
		sysUserService.deleteBatch(userIds);
		return YlsResult.ok();
	}
	

	@RequestMapping(value="/info/{userId}", method=RequestMethod.GET)
	public YlsResult info(@PathVariable String userId){
		return YlsResult.ok().put("user", sysUserService.queryObject(userId));
	}
	

	@RequestMapping(value="/list", method=RequestMethod.GET)
	public YlsResult list(@RequestParam Map<String, String> param){
		return YlsResult.ok().put("page", new YlsResultForGrid<>(sysUserService.queryList(param)));
	}
	
	
}
