package com.duiyi.web;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duiyi.domain.ResultCodeData;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.OrderService;
import com.duiyi.utils.Constants;
import com.duiyi.utils.JSONUtil;
import com.duiyi.utils.PaymentUtil;

public class Callback extends HttpServlet {
	private static final String BROWSER_RETURN = "1";
	
	private static final String PAYCOMPANY_RETURN = "2";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得回调的所有数据
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order"); // 订单号
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType"); // 返回类型，浏览器返回还是支付公司服务器返回
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");

		// 校验是不是支付公司返回的数据
		String hmac = request.getParameter("hmac");
		boolean verifyCallback = PaymentUtil.verifyCallback(hmac, p1_MerId,
				r0_Cmd, r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType,
				ResourceBundle.getBundle("merchantInfo").getString("keyValue"));
		if (verifyCallback) {
			// 数据是支付公司通知的
			if (BROWSER_RETURN.equals(r9_BType)) {
				// 浏览器返回支付成功，不可信，可通知浏览器支付成功，但不修改数据库
				ResultCodeData result = new ResultCodeData(Constants.SUCCESS, Constants.RESULT_SUCCESS);
				response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
				return;
			} else if (PAYCOMPANY_RETURN.equals(r9_BType)) {
				// 支付公司返回支付成功，可信，修改数据库，并通知支付公司服务器已收到
				OrderService service = BasicFactory.getFactory().getService(OrderService.class);
				service.changeOrderState(r6_Order, Constants.ORDER_PAYED);
				response.getWriter().write("SUCCESS");
			}
		} else {
			// 数据不是支付公司通知的，说明被篡改过
			ResultCodeData result = new ResultCodeData(Constants.FAIL, Constants.RESULT_FAIL);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
