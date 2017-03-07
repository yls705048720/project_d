/**
 * 
 */
package com.yls.bus.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yls.bus.sys.dao.entity.Users;
import com.yls.bus.sys.dao.mapper.UsersMapper;
import com.yls.bus.sys.service.UsersService;
import com.yls.freamwork.common.AbstractService;

/**
 * @author YLS
 *
 */
@Service
public class UsersServiceImpl extends AbstractService implements UsersService{

	@Autowired
	private UsersMapper usersMapper;
	
	public void addUser(Users users) {
		// TODO Auto-generated method stub
		try {
			usersMapper.add(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
