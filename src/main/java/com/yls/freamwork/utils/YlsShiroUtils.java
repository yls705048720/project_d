/**
 * 
 */
package com.yls.freamwork.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yls.bus.sys.dao.entity.SysUser;

/**
 * shiro 工具类
 * @author YLS
 *
 */
public interface YlsShiroUtils {
	
	/**
	 * 获取当前session
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * 获取Subject
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * 获取当前用户
	 * @return
	 */
	public static SysUser getUserEntity() {
		return (SysUser)SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * 获取用户Id
	 * @return
	 */
	public static String getUserId() {
		return getUserEntity().getUserId();
	}

	/**
	 * 在session中存放参数
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * 获取session中的参数
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * 判断用户是否登入
	 * @return
	 */
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	/**
	 * 登出
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 获取验证码
	 * @param key
	 * @return
	 */
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
}
