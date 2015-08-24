package com.tracker.integrations;

import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

@Service
public class MailService {

	private JavaMailSender mailSender;

	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendEmail(String to, String subject, Event event) throws MessagingException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		helper.setTo(to);
		helper.setFrom("lokesh.cherukuri8@gmail.com");
		helper.setSubject(subject);
		mimeMessage.setContent(createTemplate(event), "text/html");
		mailSender.send(mimeMessage);
	}
	
	public String createTemplate(Event event) {
		List<Checklist> checklists=new ArrayList<Checklist>(event.getSortedChecklist());
		String template=
				"<h2>Event Name:"+ event.getName()+"</h2>"+
				"<div>List of Items to do in this Event:</div>";
						for(Checklist checklist:checklists)
						{
							if(checklist.getCompleted()=="Y")
								template+="<strike>"+checklist.getItem_order()+". "+checklist.getName()+"</strike>";
							else if(checklist.getCompleted()=="N")
								template+="<strike>"+checklist.getItem_order()+". "+checklist.getName()+"</strike>";
							else
							{
								template+="<strike>"+checklist.getItem_order()+". "+checklist.getName()+"</strike>";
							}
						}
						
		return template;
	}
}
