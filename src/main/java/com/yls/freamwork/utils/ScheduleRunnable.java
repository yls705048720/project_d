/**
 * 
 */
package com.yls.freamwork.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;


/**
 * ��ȡĿ����ķ���
 * ִ��Ŀ�귽��
 * @author Lian Shan Yang
 *
 */
public class ScheduleRunnable implements Runnable {
	
	/**
	 * Ŀ����
	 */
	private Object target;
	/**
	 * Ŀ�귽��
	 */
	private Method method;
	/**
	 * ִ�з���ʱ�Ĳ���
	 */
	private String params;
	
	/**
	 * ͨ��������ƻ�ȡĿ�����Ŀ�귽��
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
			//���ִ��Ȩ��
			ReflectionUtils.makeAccessible(method);
			//ִ�з���
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
