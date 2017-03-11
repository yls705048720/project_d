/**
 * 
 */
package com.yls.freamwork.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;

import com.alibaba.fastjson.JSON;


/**
 * @author YLS
 *
 */
public class YlsRRExceptionHandler extends HandlerExceptionResolverComposite {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
																HttpServletResponse response, Object handler, Exception ex) {
		YlsResult r = new YlsResult();
		
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			
			if (ex instanceof YlsRRException) {
				r.put("code", ((YlsRRException) ex).getCode());
				r.put("msg", ((YlsRRException) ex).getMessage());
			}else if(ex instanceof DuplicateKeyException){
				r = YlsResult.error("数据库中已存在该记录");
			}else if(ex instanceof AuthorizationException){
				r = YlsResult.error("没有权限，请联系管理员授权");
			}else{
				r = YlsResult.error();
			}
			//记录异常日志
			logger.error(ex.getMessage(), ex);
			String json = JSON.toJSONString(r);
			response.getWriter().print(json);
		} catch (Exception e) {
			logger.error("RRExceptionHandler 异常处理失败", e);
		}
		return new ModelAndView();
	}
}
