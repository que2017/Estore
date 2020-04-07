package com.duiyi.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.dbutils.DbUtils;

import com.duiyi.dao.UserDao;
import com.duiyi.domain.User;
import com.duiyi.factory.BasicFactory;
import com.duiyi.utils.Constants;
import com.duiyi.utils.DaoUtil;
import com.duiyi.utils.JSONUtil;

public class UserServiceImpl implements UserService {
	private UserDao dao = BasicFactory.getFactory().getInstance(UserDao.class);

	public User findUser(String tagName, String tagValue) {
		return dao.findUser(tagName, tagValue);
	}

	public int registUser(User user) {
		// 检查用户名是否被注册过
		if (dao.findUser("username", user.getUsername()) != null) {
			return Constants.USERNAME_ALREADY_REGISTED;
		}
		// 检查邮箱是否被注册过
		if (dao.findUser("email", user.getEmail()) != null) {
			return Constants.EMAIL_ALREADY_REGISTED;
		}
		// 准备数据
		user.setRole("user");
		user.setState(0);
		user.setActivecode(UUID.randomUUID().toString());
		// 注册用户并发送激活邮件
		Connection conn = null;
		try {
			conn = DaoUtil.getConnection();
			conn.setAutoCommit(false);
			dao.addUser(user, conn);
			
			// 发送激活邮件
			/*Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.host", "localhost");
			prop.setProperty("mail.smtp.auth", "true");
			prop.setProperty("mail.debug", "true");
			Session session = Session.getInstance(prop);
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("xxx@163.com")); // 企业邮箱地址
			msg.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
			msg.setSubject(user.getUsername() + "您好,来自estore的激活邮件");
			msg.setText(user.getUsername()
				+ "请点击如下连接激活账户,如果不能点击请复制到浏览器地址栏访问：\nhttp://www.estore.com/ActiveServlet?activecode="
				+ user.getActivecode());
			Transport trans = session.getTransport();
			trans.connect("xxx", "ppp"); // 输入企业邮箱的用户名密码
			trans.sendMessage(msg, msg.getAllRecipients());*/
			
			DbUtils.commitAndCloseQuietly(conn);
		} catch (Exception e) {
			DbUtils.rollbackAndCloseQuietly(conn);
			e.printStackTrace();
			return Constants.OTHER_RESON;
		}
		
		return Constants.RESULT_SUCCESS;
	}

}
