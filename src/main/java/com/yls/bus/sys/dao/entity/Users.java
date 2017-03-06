/**
 * 
 */
package com.yls.bus.sys.dao.entity;

/**
 * @author YLS
 *  user information
 */
public class Users {
	
	private String id;
	
	private String name;
	
	private String password;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param id
	 * @param name
	 * @param password
	 */
	public Users(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	/**
	 * 
	 */
	public Users() {
		super();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
}
