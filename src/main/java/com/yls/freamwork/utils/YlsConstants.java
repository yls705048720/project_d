/**
 * 
 */
package com.yls.freamwork.utils;

/**
 * @author YLS
 *
 */
public interface YlsConstants {

	/** ʱ���ʽ(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** ʱ���ʽ(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * �˵�����ö��
	 * @author YLS
	 *
	 */
    public enum MenuType {
        /**
         * Ŀ¼
         */
    	CATALOG("0"),
        /**
         * �˵�
         */
        MENU("1"),
        /**
         * ��ť
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
     * ��ʱ����״̬
     * @author Lian Shan Yang
     *
     */
    public enum ScheduleStatus {
        /**
         * ����
         */
    	NORMAL("0"),
        /**
         * ��ͣ
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
