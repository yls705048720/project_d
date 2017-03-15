/**
 * 
 */
package com.yls.bus.sys.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yls.bus.sys.service.SysMenuService;
import com.yls.freamwork.common.AbstractCtrl;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsShiroUtils;

/**
 * @author YLS
 *
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuCtrl extends AbstractCtrl {

	@Autowired
	private SysMenuService sysMenuService;
	
	@ResponseBody
	@RequestMapping("/user")
	public YlsResult getUserMenu(){
		return YlsResult.ok().put("menuList", sysMenuService.getUserMenuList(YlsShiroUtils.getUserId()));
	}
}
