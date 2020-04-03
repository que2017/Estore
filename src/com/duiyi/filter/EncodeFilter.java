package com.duiyi.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodeFilter implements Filter {
	private String encode = "UTF-8";
	
	public void init(FilterConfig config) throws ServletException {
		encode = config.getServletContext().getInitParameter("encode");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 解决响应乱码
		response.setContentType("text/html;charset=" + encode);
		// 解决请求乱码：利用ServletRequest的装饰类实现
		chain.doFilter(new MyServletRequest((HttpServletRequest) request), response);
	}

	public void destroy() {
	}
	
	private class MyServletRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request = null;
		
		private boolean isNotEncode = true;

		public MyServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			if ("POST".equalsIgnoreCase(request.getMethod())) {
				try {
					request.setCharacterEncoding(encode);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
				return request.getParameterMap();
			} else if ("GET".equalsIgnoreCase(request.getMethod())) {
				Map<String, String[]> map = request.getParameterMap();
				if (!isNotEncode) {
					return map;
				}
				for (Map.Entry<String, String[]> entry : map.entrySet()){
					String[] values = entry.getValue();
					for (int i = 0; i < values.length; i++) {
						try {
							values[i] = new String(values[i].getBytes("iso8859-1"), encode);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
							throw new RuntimeException(e);
						}
					}
				}
				isNotEncode = false;
				return map;
			}
			return request.getParameterMap();
		}

		@Override
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}

		@Override
		public String getParameter(String name) {
			String[] params = getParameterValues(name);
			return params == null ? null : params[0];
		}
	}
}
