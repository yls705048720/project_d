/**
 * 
 */
package com.yls.freamwork.generator;

/**
 * @author Lian Shan Yang
 *
 */
public class ColumnEntity {
	//����
    private String columnName;
    //��������
    private String dataType;
    //������ע
    private String comments;
    
    //��������(��һ����ĸ��д)���磺user_name => UserName
    private String attrName;
    //��������(��һ����ĸСд)���磺user_name => userName
    private String attrname;
    //��������
    private String attrType;
    //auto_increment
    private String extra;
    
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getAttrname() {
		return attrname;
	}
	public void setAttrname(String attrname) {
		this.attrname = attrname;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
}
