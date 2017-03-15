/**
 * 
 */
package com.yls.bus.sys.shiro;

import org.apache.shiro.SecurityUtils;



/**
 * Shiro Ȩ�ޱ�ǩ��Velocity�棩
 * @author Lian Shan Yang
 *
 */
public class VelocityShiro {
	
	public boolean hasPermission(String permission){
		org.apache.shiro.subject.Subject subject= SecurityUtils.getSubject();
		return subject != null && subject.isPermitted(permission);
	}
}
