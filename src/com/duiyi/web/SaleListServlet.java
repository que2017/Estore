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
		// 查询销售榜单
		OrderService service = BasicFactory.getFactory().getService(OrderService.class);
		List<SaleInfo> saleList = service.getSaleList();
		// 将saleList组织成csv格式的文件
		StringBuilder builder = new StringBuilder();
		String lineSeparator = System.getProperty("line.separator");
		builder.append("商品名称,商品分类,销售数量" + lineSeparator);
		for (SaleInfo item : saleList) {
			builder.append(item.getProductName() + ",");
			builder.append(item.getCategory() + ",");
			builder.append(item.getSaleNum() + lineSeparator);
		}
		// 提供销售榜单下载
		String fileName = "销售榜单" + new Date().toLocaleString() + ".csv";
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		response.setContentType(this.getServletContext().getMimeType(fileName));
		response.getWriter().write(builder.toString());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
