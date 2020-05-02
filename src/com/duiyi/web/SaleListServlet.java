package com.duiyi.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.SaleInfo;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.OrderService;

public class SaleListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ѯ���۰�
		OrderService service = BasicFactory.getFactory().getService(OrderService.class);
		List<SaleInfo> saleList = service.getSaleList();
		// ��saleList��֯��csv��ʽ���ļ�
		StringBuilder builder = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		builder.append("��Ʒ����,��Ʒ����,��������" + lineSeparator);
		for (SaleInfo item : saleList) {
			builder.append(item.getProductName() + ",");
			builder.append(item.getCategory() + ",");
			builder.append(item.getSaleNum() + lineSeparator);
		}
		// �ṩ���۰�����
		String fileName = "���۰�" + new Date().toLocaleString() + ".csv";
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		response.setContentType(this.getServletContext().getMimeType(fileName));
		response.getWriter().write(builder.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
