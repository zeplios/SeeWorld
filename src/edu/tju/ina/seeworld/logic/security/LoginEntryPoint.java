package edu.tju.ina.seeworld.logic.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.AuthenticationEntryPoint;
import org.springframework.util.Assert;

/**
 * 
 * 根据不同的用户请求（前台或者后台），将登陆的用户重定向到相应的登录页面
 * 
 * @author Uranus
 * 
 */
public class LoginEntryPoint implements AuthenticationEntryPoint,
		InitializingBean {
	private IURLRedirectStrategy urlStrategy;

	public IURLRedirectStrategy getUrlStrategy() {
		return urlStrategy;
	}

	public void setUrlStrategy(IURLRedirectStrategy urlStrategy) {
		this.urlStrategy = urlStrategy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.ui.AuthenticationEntryPoint#commence(javax
	 * .servlet.ServletRequest, javax.servlet.ServletResponse,
	 * org.springframework.security.AuthenticationException)
	 */
	public void commence(ServletRequest request, ServletResponse response,
			AuthenticationException authException) throws IOException,
			ServletException {
		urlStrategy.sendRedirect((HttpServletRequest) request,(HttpServletResponse) response);
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(urlStrategy, "urlStrategy must be specified！");
	}

}
