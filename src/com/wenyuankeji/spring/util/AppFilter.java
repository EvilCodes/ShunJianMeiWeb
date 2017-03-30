package com.wenyuankeji.spring.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oruit.oruitkey.OruitKey;
import com.wenyuankeji.spring.service.IUtilService;

@Controller
public class AppFilter extends OncePerRequestFilter {

	
	@Autowired
	private IUtilService utilservice;
	
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

		if (null != uris && uris.length > 0) {
			String appInterface = uris[uris.length - 1];
			// 请求的URL中有接口方法时候，才进行过滤
			if ("appInterface".equals(appInterface)) {
				doFilter = true;
			}
		}
		

		if (doFilter) {// 执行过滤

			Map<String, Object> outJsonMap = new HashMap<String, Object>();

			// 取得接受参数start
			Map<String, Object> inputMap = null;
			StringBuilder json = new StringBuilder();
			BufferedReader in;
			try {
				in = new BufferedReader(new InputStreamReader(
						request.getInputStream(), "UTF-8"));

				String inputLine = null;
				while ((inputLine = in.readLine()) != null) {
					json.append(inputLine);
				}
				in.close();

				if (json.length() > 0) {
					
					StringBuffer items = new StringBuffer(json);
					
					request.setAttribute("inputJsonInfo", items.toString());

					
					inputMap = new HashMap<String, Object>();
					
					inputMap = JSON.parseObject(json.toString()); 

				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				outJsonMap.put(Constant.CODE, Constant.CODE_1105);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0);
				ShunJianMeiUtil.outputJson(request, response, outJsonMap);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				outJsonMap.put(Constant.CODE, Constant.CODE_1105);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0);
				ShunJianMeiUtil.outputJson(request, response, outJsonMap);
				return;
			}
			// 取得接受参数end

			if (null != inputMap) {

				// 验证必须参数start
				if (ShunJianMeiUtil.checkNull(inputMap.get("Method"))
						|| ShunJianMeiUtil
								.checkNull(inputMap.get("Infversion"))
						|| ShunJianMeiUtil.checkNull(inputMap.get("UID"))
						|| ShunJianMeiUtil.checkNull(inputMap.get("Key"))) {

					outJsonMap.put(Constant.CODE, Constant.CODE_1102);
					outJsonMap.put(Constant.MSG, Constant.MSG_0_0);

					// 输出流
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}
				// 验证必须参数end

				String method = inputMap.get("Method").toString();

				// 检查版本号start
				if (!Constant.METHOD_MAP.get(method).equals(
						inputMap.get("Infversion"))) {

					outJsonMap.put(Constant.CODE, Constant.CODE_1104);
					outJsonMap.put(Constant.MSG, Constant.MSG_0_0);

					// 输出流
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}
				// 检查版本号end

				// 检查key start
				String Key = inputMap.get("Key").toString();
				String UID = inputMap.get("UID").toString();
				if (!Key.equals(OruitKey.encrypt(UID, method))) {
					
					outJsonMap.put(Constant.CODE, Constant.CODE_1101);
					outJsonMap.put(Constant.MSG, Constant.MSG_0_0);

					// 输出流
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}
				// 检查key end
				
				// 检查请求日志 start
				/*String Key = inputMap.get("Key").toString();
				if (!Key.equals(OruitKey.encrypt(UID, method))) {
					
					outJsonMap.put(Constant.CODE, Constant.CODE_1101);
					outJsonMap.put(Constant.MSG, Constant.MSG_0_0);

					// 输出流
					ShunJianMeiUtil.outputJson(request, response, outJsonMap);
					return;
				}*/
				// 检查请求日志 end

				request.setAttribute("inputJsonMap", inputMap);
				request.getRequestDispatcher(method).forward(request, response);
			} else {
				outJsonMap.put(Constant.CODE, Constant.CODE_1102);
				outJsonMap.put(Constant.MSG, Constant.MSG_0_0);
				ShunJianMeiUtil.outputJson(request, response, outJsonMap);
				return;
			}

		} else {
			// 如果不执行过滤，则继续
			filterChain.doFilter(request, response);
		}
	}
	
/*	public static void main(String[] args) {
		System.out.print(OruitKey.encrypt("1","StoreDetail"));
		
	}*/
}