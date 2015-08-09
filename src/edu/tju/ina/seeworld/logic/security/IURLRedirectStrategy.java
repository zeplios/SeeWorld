package edu.tju.ina.seeworld.logic.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IURLRedirectStrategy {
	/**
	 * 根据不同请求，定位到相应的登陆页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	void sendRedirect(HttpServletRequest request, HttpServletResponse response) throws IOException;
	String getTargetUrl(HttpServletRequest request);
}
