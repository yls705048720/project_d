/**
 * 
 */
package com.yls.bus.sys.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

/**
 * @author Lian Shan Yang
 * ����WEN-INF ·�����ļ�
 */
@Controller
public class SysPageCtrl {
	
	@ApiOperation(value="������תWEN-INF�µ��ļ�", notes="")
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}
}
