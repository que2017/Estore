package com.duiyi.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.Order;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.OrderService;
import com.duiyi.utils.JSONUtil;
import com.duiyi.utils.PaymentUtil;

public class PayOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取浏览器传入的orderId，并从orders表中查出订单金额
		String orderId = request.getParameter("orderId");
		OrderService service = BasicFactory.getFactory().getService(OrderService.class);
		Order order = service.findOrderById(orderId); 
		// 2.构造易宝参数
		ResourceBundle bundle = ResourceBundle.getBundle("merchantInfo"); // 获取merchantInfo.properties文件数据
		String p0_Cmd = "Buy";
		String p1_MerId = bundle.getString("p1_MerId");
		String p2_Order = orderId;
		String p3_Amt = String.valueOf(order.getMoney());
		String p4_Cur = "CNY";
		String p5_Pid = "";
		String p6_Pcat = "";
		String p7_Pdesc = "";
		String p8_Url = "http://localhost:8082/Estore/servlet/Callback"; // 支付结果的回调
		String p9_SAF = "0";
		String pa_MP  = "";
		String pd_FrpId = request.getParameter("pd_FrpId"); // 支付银行
		String pr_NeedResponse = "1";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur,
				p5_Pid, p6_Pcat, p7_Pdesc,
				p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, bundle.getString("keyValue"));
		// 3.将易宝参数构造成JSON，返回给浏览器
		String params = "\'p0_Cmd\':\'" + p0_Cmd
			+ "\',\'p1_MerId\':\'" + p1_MerId
			+ "\',\'p2_Order\':\'" + p2_Order
			+ "\',\'p3_Amt\':\'" + p3_Amt
			+ "\',\'p4_Cur\':\'" + p4_Cur
			+ "\',\'p5_Pid\':\'" + p5_Pid
			+ "\',\'p6_Pcat\':\'" + p6_Pcat
			+ "\',\'p7_Pdesc\':\'" + p7_Pdesc
			+ "\',\'p8_Url\':\'" + p8_Url
			+ "\',\'p9_SAF\':\'" + p9_SAF
			+ "\',\'pa_MP\':\'" + pa_MP
			+ "\',\'pd_FrpId\':\'" + pd_FrpId
			+ "\',\'pr_NeedResponse\':\'" + pr_NeedResponse
			+ "\',\'hmac\':\'" + hmac + "\'";
		response.getWriter().write(JSONUtil.buildJsonString(params));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
