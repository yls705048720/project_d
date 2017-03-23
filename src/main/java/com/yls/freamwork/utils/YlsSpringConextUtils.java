/**
 * 
 */
package com.yls.freamwork.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * Spring Context 工具类
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
	 * 获取Context中的Bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
		System.out.println(name);
		return applicationContext.getBean(name);
	}
	/**
	 * 获取Context中的Bean 按类型
	 * @param name
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}
	/**
	 * Context中是否包含Bean
	 * @param name
	 * @return
	 */
	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}
	/**
	 * 判断Bean是否是单例
	 * @param name
	 * @return
	 */
	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}
	/**
	 * 获取Bean的类型
	 * @param name
	 * @return
	 */
	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}
	
}
