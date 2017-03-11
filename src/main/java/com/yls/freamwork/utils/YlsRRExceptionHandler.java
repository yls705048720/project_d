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
				r = YlsResult.error("���ݿ����Ѵ��ڸü�¼");
			}else if(ex instanceof AuthorizationException){
				r = YlsResult.error("û��Ȩ�ޣ�����ϵ����Ա��Ȩ");
			}else{
				r = YlsResult.error();
			}
			//��¼�쳣��־
			logger.error(ex.getMessage(), ex);
			String json = JSON.toJSONString(r);
			response.getWriter().print(json);
		} catch (Exception e) {
			logger.error("RRExceptionHandler �쳣����ʧ��", e);
		}
		return new ModelAndView();
	}
}
