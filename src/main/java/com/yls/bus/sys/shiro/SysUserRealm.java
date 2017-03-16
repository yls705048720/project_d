/**
 * 
 */
package com.yls.bus.sys.shiro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.druid.util.StringUtils;
import com.yls.bus.sys.dao.entity.SysUser;
import com.yls.bus.sys.service.SysMenuService;
import com.yls.bus.sys.service.SysUserService;
import com.yls.freamwork.utils.YlsShiroUtils;

/**
 * @author YLS
 *
 */
public class SysUserRealm extends AuthorizingRealm{

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		SysUser sysUser = (SysUser)principals.getPrimaryPrincipal();
		String userId = sysUser.getUserId();
		List<String> permsList = null;
		if(userId.equals("1")){
			permsList= sysMenuService.getAdminMenuList()
														.stream().map(sysMenu->sysMenu.getPerms())
														.collect(Collectors.toList());
		}else{
			permsList = sysUserService.queryAllPerms(userId);
		}
		Set<String> permsSet = permsList.stream().filter(perms->!StringUtils.isEmpty(perms))
															.flatMap(perms->Arrays.asList(perms.trim().split(",")).stream())
															.collect(Collectors.toSet());
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName = (String)token.getPrincipal();
//		System.out.println(token.getCredentials());
		token.getCredentials();
		String password = new String((char[])token.getCredentials());
		
		SysUser sysUser = sysUserService.queryByUserName(userName);
		
		if(userName == null){
			throw new UnknownAccountException("账号或密码不正确");
		}
		
		if(!password.equals(sysUser.getPassword())){
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
		
		if(sysUser.getStatus().equals("0")){
			throw new LockedAccountException("账号已经锁定，请联系管理员");
		}
		YlsShiroUtils.getSession().setAttribute("user", sysUser);
		return new SimpleAuthenticationInfo(userName,password,getName());
	}

}
