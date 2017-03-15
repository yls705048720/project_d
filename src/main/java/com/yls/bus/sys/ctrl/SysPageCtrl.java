/**
 * 
 */
package com.yls.bus.sys.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Lian Shan Yang
 * 访问WEN-INF 路径下文件
 */
@Controller
public class SysPageCtrl {

	@RequestMapping("sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "sys/" + url + ".html";
	}
}
