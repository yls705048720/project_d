/**
 * 
 */
package com.yls.bus.sys.ctrl;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.yls.freamwork.utils.YlsResult;
import com.yls.freamwork.utils.YlsShiroUtils;

/**
 * 登入相关
 * @author YLS
 *
 */
@Controller
public class SysLoginCtrl {

	@Autowired
	private Producer producer;
	/**
	 * 生成验证码
	 * @param response
	 * @throws SecurityException
	 * @throws IOException
	 */
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws SecurityException, IOException{
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		YlsShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
//		System.out.print(YlsShiroUtils.getSessionAttribute(Constants.KAPTCHA_SESSION_KEY));
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "jpg", outputStream);
	}
	
	/**
	 * 登入
	 * @param username
	 * @param password
	 * @param captcha
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "sys/login", method = RequestMethod.POST)
	public YlsResult login(String username, String password, String captcha)throws IOException{
		String kaptcha = YlsShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if (captcha == null || !captcha.equals(kaptcha)) {
			return YlsResult.error("验证码不正确");
		}
		
		try {
			Subject subject = YlsShiroUtils.getSubject();
			//sha256加密
			password = new Sha256Hash(password).toHex();
			UsernamePasswordToken token =  new UsernamePasswordToken(username, password);
			subject.login(token);
		} catch (UnknownAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  YlsResult.error(e.getMessage());
		} catch(IncorrectCredentialsException exception){
			exception.printStackTrace();
			return YlsResult.error(exception.getMessage());
		} catch(LockedAccountException exception){
			exception.printStackTrace();
			return YlsResult.error(exception.getMessage());
		} catch(AuthenticationException exception){
			exception.printStackTrace();
			return YlsResult.error(exception.getMessage());
		}
		
		return YlsResult.ok();
	}
	
	/**
	 * 退出
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(){
		YlsShiroUtils.logout();
		return "redirect:login.html";
	}

}
