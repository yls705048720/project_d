/**
 * 
 */
package com.yls.bus.sys.dao.mapper;

import java.util.List;
import java.util.Map;

import com.yls.bus.sys.dao.entity.Users;

/**
 * @author YLS
 *
 */
public interface UsersMapper {
	/**
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void add(Users user) throws Exception;
	/**
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void update(Users user) throws Exception;
	/**
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id) throws Exception;
	/**
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteByIds(List<String> ids) throws Exception;
	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Users getById(int id) throws Exception;
	/**
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Users getByName(String name) throws Exception;
	/**
	 * 
	 * @param paramers
	 * @return
	 * @throws Exception
	 */
	public List<Users> getPage(Map<String ,Object> paramers) throws Exception;
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int count() throws Exception;
}
