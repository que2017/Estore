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
		prop.setProperty("mail.transport.protocol", "smtp"); // Э��
		prop.setProperty("mail.smtp.host", "smtp.163.com"); // ������
		prop.setProperty("mail.smtp.auth", "true"); // �Ƿ���Ȩ�޿���
		prop.setProperty("mail.debug", "true"); // �������Ϊtrue���ڷ����ʼ�ʱ���ӡ����ʱ����Ϣ
		
		// ���������ʼ�������֮���һ�λỰ
		Session session = Session.getInstance(prop);
		// ��ȡ�ʼ�����
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("xxx@163.com"));
		msg.setRecipients(RecipientType.TO, new InternetAddress[]{new InternetAddress("yyy@qq.com")});
//		msg.setRecipients(RecipientType.CC, new InternetAddress[]{new InternetAddress("xxx@163.com")});
		msg.setSubject("test email");
		msg.setText("this is text");
		// ����
		Transport trans = session.getTransport();
		trans.connect("xxx", "......");
		trans.sendMessage(msg, msg.getAllRecipients());
	}
}
