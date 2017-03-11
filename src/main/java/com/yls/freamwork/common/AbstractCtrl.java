/**
 * 
 */
package com.yls.freamwork.common;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.yls.freamwork.utils.YlsConstants;

/**
 * @author YLS
 *
 */
public abstract class AbstractCtrl {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport(){
			/* (non-Javadoc)
			 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
			 */
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				// TODO Auto-generated method stub
				SimpleDateFormat format = new SimpleDateFormat(YlsConstants.DATE_TIME_PATTERN);
				Date date = null;
				try{
					date = format.parse(text);
				} catch (ParseException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				setValue(date);
			}
		});
	}

}
