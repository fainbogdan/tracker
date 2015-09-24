package com.tracker.integrations;

import java.io.Serializable;

public class EmailMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	private String[] recievers;
	private String subject;
	
	public EmailMessage(String content,String[] recievers, String subject) {

		this.content = content;
		this.recievers = recievers;
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getRecievers() {
		return recievers;
	}

	public void setRecievers(String[] recievers) {
		this.recievers = recievers;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
