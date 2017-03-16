/**
 * 
 */
package com.yls.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yls.bus.sys.ctrl.SysLoginCtrl;

/**
 * @author Lian Shan Yang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/spring-project_d.xml","classpath*:/spring-mvc.xml"})
public class loginTest {
	//Ä£Äârequest  response
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	//×¢ÈëCtrl Àà
	@Autowired
	private SysLoginCtrl sysLoginCtrl;
	
	@Before
	public void setUp(){
		request = new MockHttpServletRequest();
		request.setCharacterEncoding("UTF-8");
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void testLogin(){
//		request.setParameter("username", "admin");
//		request.setParameter("password", "2");
//		sysLoginCtrl.login(username, password, captcha)
	}
	
}
