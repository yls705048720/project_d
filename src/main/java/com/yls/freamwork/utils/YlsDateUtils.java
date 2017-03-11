/**
 * 
 */
package com.yls.freamwork.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author YLS
 *
 */
public interface YlsDateUtils {
	
	public static String format(Date date) {
        return format(date, YlsConstants.DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    
}
