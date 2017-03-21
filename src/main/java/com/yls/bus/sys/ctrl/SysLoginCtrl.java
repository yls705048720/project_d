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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 登入相关
 * @author YLS
 *
 */
@Controller
public class SysLoginCtrl {

	@Autowired
	private Producer producer;

	@ApiOperation(value="获取验证码",notes="")
	@RequestMapping(value="/captcha",method=RequestMethod.GET)
	public void captcha(HttpServletResponse response) throws SecurityException, IOException{
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		YlsShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "jpg", outputStream);
	}
	
	@ApiOperation(value="用户登录",notes="")
	@ApiImplicitParams
		({
			@ApiImplicitParam(name = "username", value = "userName", required = true, dataType = "String"),
			@ApiImplicitParam(name = "password", value = "password", required = true, dataType = "String"),
			@ApiImplicitParam(name = "captcha", value = "验证码", required = true, dataType = "String")
		})
	@ResponseBody
	@RequestMapping(value = "sys/login", method = RequestMethod.POST)
	public YlsResult login(String username, String password, String captcha)throws IOException{
		String kaptcha = YlsShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			//判断验证码测试不输入
//		if (captcha == null || !captcha.equals(kaptcha)) {
//			return YlsResult.error("验证码不正确");
//		}
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
	
	@ApiOperation(value="用户登出",notes="")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(){
		YlsShiroUtils.logout();
		return "redirect:login.html";
	}

}
