/**
 * 
 */
package com.yls.freamwork.utils;

/**
 * @author YLS
 *
 */
public interface YlsConstants {

	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 菜单类型枚举
	 * @author YLS
	 *
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG("0"),
        /**
         * 菜单
         */
        MENU("1"),
        /**
         * 按钮
         */
        BUTTON("2");

        private String value;

        private MenuType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 定时任务状态
     * @author Lian Shan Yang
     *
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL("0"),
        /**
         * 暂停
         */
    	PAUSE("1");

        private String value;

        private ScheduleStatus(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
}
