package com.hussar.app.common.email;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class SendMail {
	
	
	private Logger logger = Logger.getLogger(this.getClass());
	private String host = "smtp.163.com"; // smtp服务器 smtp.163.com
	private static String user = "sailor207@163.com"; // 用户名
	private String pwd = "Jz2014"; // 密码
	private String from = user; // 发件人地址
	private String to = ""; // 收件人地址
	private String subject = ""; // 邮件标题
	
	public static void send(String to, String subject, String content){
		SendMail mail = new SendMail(to, subject);
		mail.send(content);
	}
	
	public SendMail(String to, String subject) {
		this.to = to;
		this.subject = subject;
	}

	public void send(String txt) {
		Properties props = new Properties();
		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", host);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put("mail.smtp.auth", "true");
		// 用刚刚设置好的props对象构建一个session
		
		// 判断是否需要身份认证   
	    MyAuthenticator authenticator = new MyAuthenticator(user, pwd);  ;   
	    
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session   
	    Session session = Session.getDefaultInstance(props, authenticator);   
	      
		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(true);
		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(from));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 加载标题
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
//			contentPart.setText(txt);
		      // 设置HTML内容    
			contentPart.setContent(txt, "text/html; charset=utf-8");    
			
			multipart.addBodyPart(contentPart);

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect();
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void sendNow(){
		Properties mailpro = new Properties();
		mailpro.setProperty("mail.smtp.host", "east.exch024.serverdata.net");
		Session session = Session.getDefaultInstance(mailpro);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		try{
			msg.setFrom(new InternetAddress("lizongbo@gmail.com"));
			msg.setRecipient(Message.RecipientType.TO,
			new InternetAddress("yi.xiao@mavenir.com"));
			msg.setSubject("测试免认证方式发送邮件！！！");
			msg.setText("测试一下，邮件来自 http://www.donews.net/lizongbo ");
			Transport.send(msg);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String getContent() {
		StringBuilder content = new StringBuilder();
		content.append("肖毅").append(": ").append("<br>");
		content.append("&nbsp;&nbsp;").append("你好！您今天订餐已下单。如需修改或者取消请进入到<a href=\"http://10.2.2.188/\">10.2.2.188<a>修改，或者进入到您的手机客户端查看和修改。<br>");
		content.append("&nbsp;&nbsp;如果您对任何建议请发邮件至<a href=\"mailto:yi.xiao@mavenir.com\">yi.xiao@mavenir.com</a><br>");
		content.append("谢谢！");
		return content.toString();
    }
	
	public static void main(String[] args) {
		SendMail cn = new SendMail("yi.xiao@mavenir.com", "测试" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		cn.send(getContent());
	}
}