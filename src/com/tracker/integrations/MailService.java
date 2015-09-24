package com.tracker.integrations;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MailService {

	private JavaMailSender mailSender;

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void sendEmail(String[] to, String subject, String content) throws MessagingException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		helper.setTo(to);
		helper.setFrom("lokesh.cherukuri8@gmail.com");
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(mimeMessage);
	}
	
}
