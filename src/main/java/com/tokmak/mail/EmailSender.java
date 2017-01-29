package com.tokmak.mail;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	@Autowired
	private Logger logger;

	@Autowired
	private JavaMailSender javaMailSender;

	public EmailStatus sendPlainText(String to, String subject, String text) {
		return this.sendMail(to, subject, text, false);
	}

	public EmailStatus sendHtml(String to, String subject, String htmlBody) {
		return this.sendMail(to, subject, htmlBody, true);
	}

	private EmailStatus sendMail(String to, String subject, String text, Boolean isHtml) {
		try {
			MimeMessage mail = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, isHtml);
			this.javaMailSender.send(mail);
			this.logger.info("Send email '{}' to: {}", subject, to);
			return new EmailStatus(to, subject, text).success();
		} catch (Exception e) {
			this.logger.error(String.format("Problem with sending email to: {}, error message: {}", to, e.getMessage()));
			return new EmailStatus(to, subject, text).error(e.getMessage());
		}
	}
}