package com.wenyuankeji.spring.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 不过滤的uri
		// String[] notFilter = new String[] { "guoyeInfo", "test" };
		// 请求的uri
		String[] uris = request.getRequestURI().split("/");
		String uri = request.getRequestURI();

		// 是否过滤
		boolean doFilter = false;

		if (uri.indexOf(".") != -1) {
			// css不进行过滤
			doFilter = false;
		}
		
		if (uris != null && uris.length > 0) {
			String checkSession = uris[uris.length - 1];
			// 判断请示是否需要检查登录
			if (Constant.CHECK_SESSION_MAP.get(checkSession) != null) {
				doFilter = Constant.CHECK_SESSION_MAP.get(checkSession);
			}
		}
		
		if (doFilter) {
			//需要验证
			Object loginSession = request.getSession().getAttribute("Login");
			if (loginSession != null) {
				//已登录，继续执行
				filterChain.doFilter(request, response);
			}else {
				//跳转首页
				request.getRequestDispatcher("index").forward(request, response);
			}
		}
		
		// 如果不执行过滤，则继续
		filterChain.doFilter(request, response);

	}
}
