package com.tokmak.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import eu.bitwalker.useragentutils.UserAgent;

@Service
public class EmailService {
	@Autowired
	private EmailHtmlSender mailSender;
	
	@Autowired
	private PropertyReader propertyReader;

	public EmailStatus send(String argMailType, String argLanguage, String argName, String argMailAddress,
			String argUserAgent) {
		if (EmailContants.PASSWORD_RESET.equals(argMailType)) {
			return this.sendPasswordReset(argName, argMailAddress, UserAgent.parseUserAgentString(argUserAgent));
		} else if (EmailContants.WELLCOME.equals(argMailType)) {
			return this.sendWelcome(argName, argMailAddress);
		} else if (EmailContants.INVOICE.equals(argMailType)) {
			return this.sendInvoice(argName, argMailAddress);
		}
		return null;
	}

	private EmailStatus sendPasswordReset(String argName, String argEmail, UserAgent argUserAgent) {
		Context context = new Context();
		context.setVariable("name", argName);
		context.setVariable("action_url", this.propertyReader.getActionUrl());
		context.setVariable("browser_name",
				argUserAgent.getBrowser().getName() + EmailContants.SPACE + argUserAgent.getBrowserVersion());
		context.setVariable("operating_system", argUserAgent.getOperatingSystem().getName());
		context.setVariable("support_url", this.propertyReader.getSupportUrl());

		return this.mailSender.send(argEmail, this.propertyReader.getPasswordResetTitle(),
				this.propertyReader.getEmailRootFolder() + EmailContants.SEPERATOR + EmailContants.PASSWORD_RESET, context);
	}

	private EmailStatus sendWelcome(String argName, String argEmail) {
		Context context = new Context();
		context.setVariable("name", argName);

		return this.mailSender.send(argEmail, this.propertyReader.getWellcomeTitle(),
				this.propertyReader.getEmailRootFolder() + EmailContants.SEPERATOR + EmailContants.WELLCOME, context);
	}

	private EmailStatus sendInvoice(String argName, String argEmail) {
		Context context = new Context();
		context.setVariable("name", argName);

		return this.mailSender.send(argEmail, this.propertyReader.getInvoiceTitle(),
				this.propertyReader.getEmailRootFolder() + EmailContants.SEPERATOR + EmailContants.INVOICE, context);
	}
}
