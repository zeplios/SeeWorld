package edu.tju.ina.seeworld.logic.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.util.RedirectUtils;

public class URLStrategyImpl implements IURLRedirectStrategy {
	/**
	 * 后台登录页面地址
	 */
	private String adminTargetURL;
	/**
	 * 前台登录页面地址
	 */
	private String frontTargetURL;

	public void setAdminTargetURL(String adminTargetURL) {
		this.adminTargetURL = adminTargetURL;
	}

	public void setFrontTargetURL(String frontTargetURL) {
		this.frontTargetURL = frontTargetURL;
	}

	public void sendRedirect(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		RedirectUtils.sendRedirect(request, response, getTargetUrl(request),
				false);
	}

	public String getTargetUrl(HttpServletRequest request) {
		String targetUrl = null;
		String uri = request.getRequestURI();
		if (uri.indexOf("admin") != -1) {
			targetUrl = adminTargetURL;
		} else {
			targetUrl = frontTargetURL;
		}
		return targetUrl;
	}
}
