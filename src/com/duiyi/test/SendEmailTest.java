package com.duiyi.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class SendEmailTest {
	@Test
	public void test() throws AddressException, MessagingException {
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp"); // 协议
		prop.setProperty("mail.smtp.host", "smtp.163.com"); // 主机名
		prop.setProperty("mail.smtp.auth", "true"); // 是否开启权限控制
		prop.setProperty("mail.debug", "true"); // 如果设置为true则在发送邮件时会打印发送时的信息
		
		// 创建程序到邮件服务器之间的一次会话
		Session session = Session.getInstance(prop);
		// 获取邮件对象
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("xxx@163.com"));
		msg.setRecipients(RecipientType.TO, new InternetAddress[]{new InternetAddress("yyy@qq.com")});
//		msg.setRecipients(RecipientType.CC, new InternetAddress[]{new InternetAddress("xxx@163.com")});
		msg.setSubject("test email");
		msg.setText("this is text");
		// 发送
		Transport trans = session.getTransport();
		trans.connect("xxx", "......");
		trans.sendMessage(msg, msg.getAllRecipients());
	}
}
