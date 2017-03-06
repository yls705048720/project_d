/**
 * 
 */
package com.yls.test.users;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.yls.bus.sys.dao.entity.Users;
import com.yls.bus.sys.dao.mapper.UsersMapper;
import com.yls.freamwork.utils.MybatisSessonUtil;

/**
 * @author YLS
 *
 */
public class UserTest {
	
	SqlSession session=MybatisSessonUtil.getSession();
	UsersMapper mapper=session.getMapper(UsersMapper.class);
	
	@Test
	public void insert(){
		Users users = new Users("yls","yls","yls");
		try {
			mapper.add(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
