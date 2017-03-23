/**
 * 
 */
package com.yls.freamwork.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * Spring Context ������
 * @author Lian Shan Yang
 *
 */
public class YlsSpringConextUtils implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		YlsSpringConextUtils.applicationContext =  applicationContext;
	}
	/**
	 * ��ȡContext�е�Bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		System.out.println(name);
		return applicationContext.getBean(name);
	}
	/**
	 * ��ȡContext�е�Bean ������
	 * @param name
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}
	/**
	 * Context���Ƿ����Bean
	 * @param name
	 * @return
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}
	/**
	 * �ж�Bean�Ƿ��ǵ���
	 * @param name
	 * @return
	 */
	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}
	/**
	 * ��ȡBean������
	 * @param name
	 * @return
	 */
	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}
	
}
