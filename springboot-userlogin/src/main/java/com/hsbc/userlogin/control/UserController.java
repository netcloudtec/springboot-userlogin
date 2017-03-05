package com.hsbc.userlogin.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.userlogin.domain.User;
import com.hsbc.userlogin.response.ReturnValue;
import com.hsbc.userlogin.security.SysUrl;
import com.hsbc.userlogin.utils.Constant;
/**
 * 用户登录认证
 * @author Administrator
 *
 */
@RequestMapping(value = "/user")
@Controller
public class UserController {
	/**
	 * 用户认证成功
	 */
	@RequestMapping(value = "/face_auth_success")
	public String  authSuccess(HttpServletRequest request) {
	   User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	   request.getSession().setAttribute(Constant.SESSION_USER, user);
	   return "welcome";
	}

	/**
	 * 用户认证失败
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/face_auth_failed")
	@ResponseBody
	public ReturnValue authFailed(HttpServletRequest request) {
		ReturnValue retValue = new ReturnValue();
		retValue.setData(false);
		retValue.setCode(500);
		retValue.setMessage("login failed");

		return retValue;
	}

	/**
	 * session被踢出
	 * 
	 */
	@RequestMapping(value = "/session/kicked")
	@ResponseBody
	public ReturnValue sessionKicked(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ReturnValue retValue = new ReturnValue();
		
		if (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
			response.setHeader("Session-Status", "Session-Out"); // 在响应头设置session状态
			response.setHeader("Redirect-Url", SysUrl.INDEX_URL); // 在响应头设置跳转URL
			response.setContentType("application/json;charset=UTF-8");
		} else {
			response.setContentType("text/html"); // 如果为空,则进行未登录处理
			response.sendRedirect(SysUrl.INDEX_URL); // 未登录跳转到登录页面
		}

		return retValue;
	}

	
}
