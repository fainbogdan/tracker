package com.tracker.integrations.queueListeners;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tracker.integrations.EmailMessage;
import com.tracker.integrations.MailService;

@Component
public class EmailQueueListener{
	
	private MailService mailService;
	
	@Autowired
	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}
	
	public void handleMessage(EmailMessage message) {
		try 
		{
			mailService.sendEmail(message.getRecievers(), message.getSubject(), message.getContent());
		} 
		catch (MessagingException e) {
			e.printStackTrace();
		}
    }

}
