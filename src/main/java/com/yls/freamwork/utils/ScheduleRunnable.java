/**
 * 
 */
package com.yls.freamwork.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;


/**
 * 获取目标类的方法
 * 执行目标方法
 * @author Lian Shan Yang
 *
 */
public class ScheduleRunnable implements Runnable {
	
	/**
	 * 目标类
	 */
	private Object target;
	/**
	 * 目标方法
	 */
	private Method method;
	/**
	 * 执行方法时的参数
	 */
	private String params;
	
	/**
	 * 通过反射机制获取目标类的目标方法
	 * @param beanName
	 * @param methodName
	 * @param params
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
		
		this.target = YlsSpringConextUtils.getBean(beanName);
		
		if(StringUtils.isNotBlank(params)){
			this.method = target.getClass().getDeclaredMethod(methodName, String.class);
		}else{
			this.method = target.getClass().getDeclaredMethod(methodName);
		}
				
		this.params = params;
	
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//获得执行权限
			ReflectionUtils.makeAccessible(method);
			//执行方法
			if(StringUtils.isNotBlank(params)){
				method.invoke(target, params);
			}else{
				method.invoke(target);
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	

}
