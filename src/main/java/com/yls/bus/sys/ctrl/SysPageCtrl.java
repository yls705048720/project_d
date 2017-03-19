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
 * 访问WEN-INF 路径下文件
 */
@Controller
public class SysPageCtrl {
	
	@ApiOperation(value="控制跳转WEN-INF下的文件", notes="")
	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}
}
