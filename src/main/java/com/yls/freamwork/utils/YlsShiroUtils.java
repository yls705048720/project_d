/**
 * 
 */
package com.yls.freamwork.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.yls.bus.sys.dao.entity.SysUser;

/**
 * shiro ������
 * @author YLS
 *
 */
public interface YlsShiroUtils {
	
	/**
	 * ��ȡ��ǰsession
	 * @return
	 */
	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}
	
	/**
	 * ��ȡSubject
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * ��ȡ��ǰ�û�
	 * @return
	 */
	public static SysUser getUserEntity() {
		return (SysUser)SecurityUtils.getSubject().getPrincipal();
	}

	/**
	 * ��ȡ�û�Id
	 * @return
	 */
	public static String getUserId() {
		return getUserEntity().getUserId();
	}

	/**
	 * ��session�д�Ų���
	 * @param key
	 * @param value
	 */
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	/**
	 * ��ȡsession�еĲ���
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	/**
	 * �ж��û��Ƿ����
	 * @return
	 */
	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	/**
	 * �ǳ�
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	/**
	 * ��ȡ��֤��
	 * @param key
	 * @return
	 */
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
}
