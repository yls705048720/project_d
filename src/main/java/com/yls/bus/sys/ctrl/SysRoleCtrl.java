package com.yls.bus.sys.ctrl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yls.bus.sys.dao.entity.SysRole;
import com.yls.bus.sys.service.SysRoleService;
import com.yls.freamwork.common.AbstractCtrl;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsResultForGrid;

/**
 * 
 * @author YLS
 *
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleCtrl extends AbstractCtrl {

	@Autowired 
	private SysRoleService sysRoleService;
	

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public YlsResult add(@RequestBody SysRole sysRole){
		sysRole.setCreateTime(new Date());
		sysRoleService.save(sysRole);
		return YlsResult.ok();
	}
	

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public YlsResult update(@RequestBody SysRole sysRole){
		sysRoleService.update(sysRole);
		return YlsResult.ok();
	}
	

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public YlsResult delete(@RequestBody String [] roleIds){
		sysRoleService.deleteBatch(roleIds);
		return YlsResult.ok();
	}
	

	@RequestMapping(value="/info/{roleId}", method=RequestMethod.GET)
	public YlsResult info(@PathVariable String roleId){
		return YlsResult.ok()
								.put("role", sysRoleService.queryObject(roleId));
	}
	

	@RequestMapping(value="list", method=RequestMethod.GET)
	public YlsResult list(@RequestParam Map<String, String> param) throws UnsupportedEncodingException{
		return YlsResult.ok()
					.put("page", new YlsResultForGrid<>(sysRoleService.queryList(param)));
	}
	

	@RequestMapping(value="/select", method=RequestMethod.GET)
	public YlsResult select(){
		return YlsResult.ok().put("list", sysRoleService.selectAll());
	}
}
