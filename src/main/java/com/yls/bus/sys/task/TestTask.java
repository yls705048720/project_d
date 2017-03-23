/**
 * 
 */
package com.yls.bus.sys.task;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yls.bus.sys.dao.entity.SysUser;
import com.yls.bus.sys.service.SysUserService;

/**
 * @author Lian Shan Yang
 * ע����Ҫ������Service bean�����Ʒ����ѯ
 */

@Component("testTask")
public class TestTask {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SysUserService sysUserService;
	
	public void test(String params){
		logger.info("���Ǵ�������test���������ڱ�ִ�У�����Ϊ��" + params);
		
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		SysUser user = sysUserService.queryObject("1");
		System.out.println(ToStringBuilder.reflectionToString(user));
		
	}
	
	public void test2(){
		logger.info("���ǲ���������test2���������ڱ�ִ��");
	}
}
