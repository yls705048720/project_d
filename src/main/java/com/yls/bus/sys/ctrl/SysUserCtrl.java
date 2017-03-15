/**
 * 
 */
package com.yls.bus.sys.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.yls.bus.sys.service.SysUserService;
import com.yls.freamwork.common.AbstractCtrl;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsShiroUtils;

/**
 * @author YLS
 *
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserCtrl extends AbstractCtrl {
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 获取当前登陆用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/info")
	public YlsResult userInfo(){
		return YlsResult.ok().put("user", YlsShiroUtils.getUserEntity());
	}
	
	/**
	 * 修改验证码
	 * @param password
	 * @param newPassword
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/password")
	public YlsResult  cPassword(String password ,String newPassword){
		if(sysUserService.updatePassword(YlsShiroUtils.getUserId(), password, newPassword) == 1){
			return YlsResult.ok();
		}else{
			return YlsResult.error();
		}
	}
}
