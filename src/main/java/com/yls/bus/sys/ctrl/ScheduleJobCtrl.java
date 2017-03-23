/**
 * 
 */
package com.yls.bus.sys.ctrl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yls.bus.sys.dao.entity.ScheduleJob;
import com.yls.bus.sys.service.ScheduleJobService;
import com.yls.freamwork.utils.YlsRRException;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsResultForGrid;

/**
 * @author Lian Shan Yang
 *
 */
@RestController
@RequestMapping("/sysSchedule")
public class ScheduleJobCtrl {

	@Autowired
	private ScheduleJobService scheduleJobService;
	
	@RequiresPermissions("sys:schedule:list")
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public YlsResult list(@RequestParam Map<String, String> params){
		return YlsResult.ok()
				.put("page", new YlsResultForGrid<>(scheduleJobService.queryList(params)));
	}
	
	@RequiresPermissions("sys:schedule:info")
	@RequestMapping(value="/info/{jobId}", method=RequestMethod.GET)
	public YlsResult info(@PathVariable("jobId") String jobId){
		return YlsResult.ok().put("schedule", scheduleJobService.queryObject(jobId));
	}
	
	@RequestMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	public YlsResult save(@RequestBody ScheduleJob scheduleJob){
		verifyForm(scheduleJob);
		scheduleJobService.save(scheduleJob);
		return YlsResult.ok();
	}
	/**
	 * 修改定时任务
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	public YlsResult update(@RequestBody ScheduleJob scheduleJob){
		//数据校验
		verifyForm(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return YlsResult.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	public YlsResult delete(@RequestBody String[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return YlsResult.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@RequestMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	public YlsResult run(@RequestBody String[] jobIds){
		scheduleJobService.run(jobIds);
		
		return YlsResult.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@RequestMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	public YlsResult pause(@RequestBody String[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return YlsResult.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@RequestMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	public YlsResult resume(@RequestBody String[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return YlsResult.ok();
	}
	
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(ScheduleJob scheduleJob){
		if(StringUtils.isBlank(scheduleJob.getBeanName())){
			throw new YlsRRException("bean名称不能为空");
		}
		
		if(StringUtils.isBlank(scheduleJob.getMethodName())){
			throw new YlsRRException("方法名称不能为空");
		}
		
		if(StringUtils.isBlank(scheduleJob.getCronExpression())){
			throw new YlsRRException("cron表达式不能为空");
		}
	}
}
