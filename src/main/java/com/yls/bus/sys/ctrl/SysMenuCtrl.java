/**
 * 
 */
package com.yls.bus.sys.ctrl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yls.bus.sys.dao.entity.SysMenu;
import com.yls.bus.sys.service.SysMenuService;
import com.yls.freamwork.common.AbstractCtrl;
import com.yls.freamwork.utils.YlsConstants;
import com.yls.freamwork.utils.YlsRRException;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsShiroUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author YLS
 *
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuCtrl extends AbstractCtrl {

	@Autowired
	private SysMenuService sysMenuService;
	
	@ApiOperation(value="��ȡ�˵��б�", notes="��ҳ�����˵������νṹ����")
	@ResponseBody
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public YlsResult getUserMenu(){
		
		return YlsResult.ok()
								.put("menuList", sysMenuService.getUserMenuList(YlsShiroUtils.getUserId()));
	}
	
	@ApiOperation(value="�˵��б� ��ҳ", notes="��ҳ")
	@ApiImplicitParam(name="param", value="��ҳ��ѯ�������", required=true, dataType="Map")
	@RequiresPermissions("sys:menu:list")
	@ResponseBody
	@RequestMapping(value="list",method=RequestMethod.GET)
	public YlsResult list(@RequestParam Map<String, String> param){
		
		return YlsResult.ok()
									.put("page", sysMenuService.queryList(param));
	}
	
	@ApiOperation(value="ɾ���˵�", notes="ϵͳ�˵�����ɾ��")
	@ApiImplicitParam(name="menuIds", value="�˵�Id", required=true, dataType="Array")
	@RequiresPermissions("sys:menu:delete")
	@ResponseBody
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public YlsResult delete(@RequestBody String [] menuIds){
		
		for(String menuId: menuIds){
			if(StringUtils.isNumeric(menuId)&&Integer.parseInt(menuId)<28){
				return YlsResult.error("ϵͳ�˵�����ɾ��");
			}
		}
		sysMenuService.deleteBatch(menuIds);
		return YlsResult.ok();
	}
	
	@ApiOperation(value="��ȡzTree��������", notes="")
	@RequiresPermissions("sys:menu:select")
	@ResponseBody
	@RequestMapping(value="/select",method=RequestMethod.GET)
	public YlsResult forTree(){
		
		List<SysMenu> list = sysMenuService.queryNotButtonList();
		//��Ӷ����˵�
		SysMenu sysMenu = new SysMenu();
		sysMenu.setMenuId("0");
		sysMenu.setParentId("-1");
		//sysMenu.setType("0");
		sysMenu.setName("һ���˵�");
		sysMenu.setOpen(true);
		
		list.add(sysMenu);
		return YlsResult.ok().put("menuList", list);
	}
	
	@ApiOperation(value="��ȡ�˵���ϸ��Ϣ", notes="")
	@ApiImplicitParam(name="menuId", value="�˵�Id", required=true, dataType="String")
	@RequiresPermissions("sys:menu:info")
	@ResponseBody
	@RequestMapping(value="/info/{menuId}",method=RequestMethod.GET)
	public YlsResult getMune(@PathVariable String menuId){
		
		return YlsResult.ok().put("menu", sysMenuService.queryObject(menuId));
	}
	
	@ApiOperation(value="��Ӳ˵�", notes="")
	@ApiImplicitParam(name="sysMenu", value="�˵�ʵ��",required=true,dataType="com.yls.bus.sys.dao.entity.SysMenu")
	@RequiresPermissions("sys:menu:save")
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public YlsResult save(@RequestBody SysMenu sysMenu){
		
		validate(sysMenu);
		sysMenuService.save(sysMenu);
		return YlsResult.ok();
	}
	
	@ApiOperation(value="���²˵�", notes="")
	@ApiImplicitParam(name="sysMenu", value="�˵�ʵ��",required=true,dataType="com.yls.bus.sys.dao.entity.SysMenu")	
	@RequiresPermissions("sys:menu:update")
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public YlsResult update(@RequestBody SysMenu sysMenu){
		
		validate(sysMenu);
		sysMenuService.update(sysMenu);
		return YlsResult.ok();
	}
	
	/**
	 * У������
	 */
	private void validate(SysMenu sysMenu){
		if(StringUtils.isBlank(sysMenu.getName())){
			throw new YlsRRException("�˵����ֲ���Ϊ��");
		}
		
		if(sysMenu.getParentId() == null){
			throw new YlsRRException("�ϼ��˵�����Ϊ��");
		}

		//�˵�
		if(StringUtils.equals(sysMenu.getType(), YlsConstants.MenuType.MENU.getValue())){
			if(StringUtils.isBlank(sysMenu.getUrl())){
				throw new YlsRRException("�˵�URL����Ϊ��");
			}
		}
		
		//�ϼ��˵�����
		String parentType = YlsConstants.MenuType.CATALOG.getValue();
		if(!StringUtils.equals(sysMenu.getParentId(), "0")){
			SysMenu parentMenu = sysMenuService.queryObject(sysMenu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//Ŀ¼���˵�
		if(StringUtils.equals(sysMenu.getType() , YlsConstants.MenuType.CATALOG.getValue())
						|| StringUtils.equals( sysMenu.getType(), YlsConstants.MenuType.MENU.getValue())){
			
			if(!StringUtils.equals(parentType , YlsConstants.MenuType.CATALOG.getValue())){
				throw new YlsRRException("�ϼ��˵�ֻ��ΪĿ¼����");
			}
			return ;
		}
		
		//��ť
		if(StringUtils.equals(sysMenu.getType(), YlsConstants.MenuType.BUTTON.getValue())){
			if(!StringUtils.equals(parentType , YlsConstants.MenuType.MENU.getValue())){
				throw new YlsRRException("�ϼ��˵�ֻ��Ϊ�˵�����");
			}
			return ;
		}
	}
}
