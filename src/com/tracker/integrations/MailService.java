package com.tracker.integrations;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

@Service
@Transactional
public class MailService {

	private JavaMailSender mailSender;
	private TemplateEngine templateEngine;

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	public void setTemplateEngine(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	
	public void sendEmail(String to, String subject, WebContext context,String template) throws MessagingException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		helper.setTo(to);
		helper.setFrom("lokesh.cherukuri8@gmail.com");
		helper.setSubject(subject);
		String content=templateEngine.process(template, context);
		helper.setText(content, true);
		mailSender.send(mimeMessage);
	}
	
}
