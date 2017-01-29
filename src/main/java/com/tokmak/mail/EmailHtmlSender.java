package com.tokmak.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailHtmlSender {

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public EmailStatus send(String to, String subject, String templateName, Context context) {
		return this.emailSender.sendHtml(to, subject, this.templateEngine.process(templateName, context));
	}
}
