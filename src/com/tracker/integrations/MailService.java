package com.tracker.integrations;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.tracker.web.models.Checklist;
import com.tracker.web.models.Event;

@Service
@Transactional
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
		List<Checklist> checklists=new ArrayList<Checklist>(event.sortedChecklist());
		String template=
				"<h2>Event Name:"+ event.getName()+"</h2>"+
				"<div>List of Items to do in this Event:</div>";
						for(Checklist checklist:checklists)
						{
							if(("Y").equals(checklist.getCompleted()))
								template+="<div style='color:green'><strike>"+checklist.getItem_order()+". "+checklist.getName()+"</strike></div>";
							else if(("N").equals(checklist.getCompleted()))
								template+="<div style='color:red'><strike>"+checklist.getItem_order()+". "+checklist.getName()+"</strike></div>";
							else
								template+="<div>"+checklist.getItem_order()+". "+checklist.getName()+"</div>";
						}

			template+="<div>"+
						"<h3> Event Details </h3>"+
						  "<table border='1' style='border-collapse: collapse'>"+
						    "<tbody>"+
						      "<tr>"+
						        "<td>Event Description </td>"+
						        "<td>"+event.getDescription()+"</td>"+
						      "</tr>"+
						      "<tr>"+
						        "<td>Created_by </td>"+
						        "<td>"+event.getCreator().fullname()+"</td>"+
						      "</tr>"+
						      "<tr>"+
						        "<td>Environment </td>"+
						        "<td>"+event.getEnvironment()+"</td>"+
						      "</tr>"+
						      "<tr>"+
						        "<td>Expected start </td>"+
						        "<td>"+event.getExpected_start()+"</td>"+
						      "</tr>";

						      if(event.getExpected_end() !=null)
						      {
							      template+="<tr>"+
										      	"<td>Expected end </td>"+
										      	"<td>"+event.getExpected_end()+"</td>"+
										     "</tr>";
						      }
						      else
						      {
						    	  template+="<tr>"+
									      	"<td>Expected end </td>"+
									      	"<td> N/A</td>"+
									     "</tr>";
						      }

						      if(event.getActual_start() !=null)
						      {
							      template+="<tr>"+
										      	"<td>Actual start </td>"+
										      	"<td>"+event.getActual_start()+"</td>"+
										     "</tr>";
						      }
						      else
						      {
						    	  template+="<tr>"+
									      	"<td>Actual start </td>"+
									      	"<td> N/A</td>"+
									     "</tr>";
						      }
						      if(event.getActual_end() !=null)
						      {
							      template+="<tr>"+
										      	"<td>Actual end </td>"+
										      	"<td>"+event.getActual_end()+"</td>"+
										     "</tr>";
						      }
						      else
						      {
						    	  template+="<tr>"+
									      	"<td>Actual end </td>"+
									      	"<td> N/A</td>"+
									     "</tr>";
						      }
				template+="</tbody>"+
			  			"</table>"+
					"</div>";
						
		return template;
	}
}
