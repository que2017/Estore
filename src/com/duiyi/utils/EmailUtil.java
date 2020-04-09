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
	 * ���ͼ����ʼ�
	 *
	 * @param username �û���
	 * @param address �����ַ
	 * @return ������
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
		msg.setFrom(new InternetAddress("xxx@163.com")); // ��ҵ�����ַ
		msg.setRecipient(RecipientType.TO, new InternetAddress(address));
		msg.setSubject(username + "����,����estore�ļ����ʼ�");
		msg.setText(username
			+ "�����������Ӽ����˻�,������ܵ���븴�Ƶ��������ַ�����ʣ�\nhttp://www.estore.com/ActiveServlet?activecode="
			+ activeCode);
		Transport trans = session.getTransport();
		trans.connect("xxx", "ppp"); // ������ҵ������û�������
		trans.sendMessage(msg, msg.getAllRecipients());
	}
	
	private EmailUtil() {
	}
}
