package com.web.utils;

import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * javaMail的邮件工具类
 * 
 * @author wangXgnaw
 *
 */
public class MailUtil {

	/**
	 * 使用加密的方式,利用465端口进行传输邮件,开启ssl
	 * 
	 * @param to
	 *            为收件人邮箱
	 * @param message
	 *            发送的消息
	 */
	public static void sendEmil(String to, String subject, String message) {
		message = message.replace("{sendtime}",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			// 设置邮件会话参数
			Properties props = new Properties();
			// 邮箱的发送服务器地址
			props.setProperty("mail.smtp.host", "smtp.163.com");
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			// 邮箱发送服务器端口,这里设置为465端口
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.auth", "true");
			final String username = "wubo2788";
			final String password = "qwe123";
			// 获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			// 通过会话,得到一个邮件,用于发送
			Message msg = new MimeMessage(session);
			// 设置发件人昵称
			String nick = "";
			try {
				nick = javax.mail.internet.MimeUtility.encodeText("云笔记官方");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// 设置发件人
			msg.setFrom(new InternetAddress(nick+" <"+"wubo2788@163.com"+">"));
			// 设置收件人,to为收件人,cc为抄送,bcc为密送
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(to, false));
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(to, false));
			msg.setSubject(subject);
			// 设置发送的日期
			msg.setSentDate(new Date());
			// 发送普通邮件消息
			// msg.setText(message);
			// 以下设置发送网页信息
			Multipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(message, "text/html;charset=utf-8");
			mainPart.addBodyPart(html);
			msg.setContent(mainPart);
			// 调用Transport的send方法去发送邮件
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}