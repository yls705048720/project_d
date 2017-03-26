/**
 * 
 */
package com.yls.bus.sys.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import com.yls.bus.sys.service.GeneratorService;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsResultForGrid;

/**
 * @author Lian Shan Yang
 *
 */
@Controller
@RequestMapping(value="/generator")
public class GeneratorCtrl {

	@Autowired
	private GeneratorService generatorService;
	
	/**
	 * �б�
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:generator:list")
	public YlsResult list(String tableName, Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableName);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//��ѯ�б�����
		List<Map<String, Object>> list = generatorService.queryList(map);
//		int total = generatorService.queryTotal(map);
		
//		PageUtils pageUtil = new PageUtils(list, total, limit, page);
		
		return YlsResult.ok().put("page", new YlsResultForGrid<>(list));
	}
	
	/**
	 * ���ɴ���
	 */
	@RequestMapping("/code")
	@RequiresPermissions("sys:generator:code")
	public void code(String tables, HttpServletResponse response) throws IOException{
		String[] tableNames = new String[]{};
		tableNames = JSON.parseArray(tables).toArray(tableNames);
		
		byte[] data = generatorService.generatorCode(tableNames);
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"renren.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
	
}
