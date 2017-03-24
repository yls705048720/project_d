/**
 * 
 */
package com.yls.freamwork.generator;

import java.util.List;

/**
 * @author Lian Shan Yang
 *
 */
public class TableEntity {
	//�������
	private String tableName;
	//��ı�ע
	private String comments;
	//�������
	private ColumnEntity pk;
	//�������(����������)
	private List<ColumnEntity> columns;
	
	//����(��һ����ĸ��д)���磺sys_user => SysUser
	private String className;
	//����(��һ����ĸСд)���磺sys_user => sysUser
	private String classname;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public ColumnEntity getPk() {
		return pk;
	}
	public void setPk(ColumnEntity pk) {
		this.pk = pk;
	}
	public List<ColumnEntity> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
}
