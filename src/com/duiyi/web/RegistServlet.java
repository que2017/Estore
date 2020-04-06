package com.duiyi.web;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("password2"));
		System.out.println(request.getParameter("nickname"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("validateStr"));
		Map<String, String[]> map = request.getParameterMap();
		Set<Map.Entry<String, String[]>> entrySet = map.entrySet();
		for (Map.Entry<String, String[]> entry : entrySet) {
			System.out.println(entry.getKey().toString() + ": " + entry.getValue());
		}
		response.getWriter().write("haha");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
