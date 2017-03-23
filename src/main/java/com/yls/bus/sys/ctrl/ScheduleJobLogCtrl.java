/**
 * 
 */
package com.yls.bus.sys.ctrl;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.yls.bus.sys.service.ScheduleJobLogService;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsResultForGrid;


/**
 * @author Lian Shan Yang
 *
 */
@RestController
@RequestMapping("/sysScheduleLog")
public class ScheduleJobLogCtrl {

	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	@RequiresPermissions("sys:schedule:log")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public YlsResult list(@RequestParam Map<String, String> params){
		return YlsResult.ok()
				.put("page", new YlsResultForGrid<>(scheduleJobLogService.queryList(params)));
	}
	
	@RequiresPermissions("sys:schedule:info")
	@RequestMapping(value="/info/{logId}", method= RequestMethod.GET)
	public YlsResult info(@PathVariable String logId){
		return YlsResult.ok().put("scheduleLog", scheduleJobLogService.queryObject(logId));
	}
}
