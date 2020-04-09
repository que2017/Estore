package com.duiyi.utils;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	/**
	 * 发送激活邮件
	 *
	 * @param username 用户名
	 * @param address 邮箱地址
	 * @return 激活码
	 * @throws MessagingException
	 */
	public static void sendActiveEmail(String username, String address, String activeCode) throws MessagingException {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.host", "localhost");
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.debug", "true");
		Session session = Session.getInstance(prop);
		
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("xxx@163.com")); // 企业邮箱地址
		msg.setRecipient(RecipientType.TO, new InternetAddress(address));
		msg.setSubject(username + "您好,来自estore的激活邮件");
		msg.setText(username
			+ "请点击如下连接激活账户,如果不能点击请复制到浏览器地址栏访问：\nhttp://www.estore.com/ActiveServlet?activecode="
			+ activeCode);
		Transport trans = session.getTransport();
		trans.connect("xxx", "ppp"); // 输入企业邮箱的用户名密码
		trans.sendMessage(msg, msg.getAllRecipients());
	}
	
	private EmailUtil() {
	}
}
