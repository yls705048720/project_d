/**
 * 
 */
package com.yls.bus.sys.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yls.bus.sys.dao.entity.Users;
import com.yls.bus.sys.service.UsersService;

/**
 * @author YLS
 *
 */
@Controller
@RequestMapping("/user")
public class UsersCtrl {
	
	@Autowired 
	private UsersService usersService;
	
	@RequestMapping("/register")
	@ResponseBody
	public String register( Users user){
		usersService.addUser(user);
		return user.toString();
	}
}
