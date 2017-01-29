package com.tokmak.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "application.properties")
public class PropertyReader {
	@Value("${email.root.folder}")
	private String emailRootFolder;

	@Value("${application.support.url}")
	private String supportUrl;

	@Value("${application.action.url}")
	private String actionUrl;

	@Value("${email.password-reset.title}")
	private String passwordResetTitle;

	@Value("${email.wellcome.title}")
	private String wellcomeTitle;

	@Value("${email.invoice.title}")
	private String invoiceTitle;

	public String getEmailRootFolder() {
		return this.emailRootFolder;
	}

	public String getSupportUrl() {
		return this.supportUrl;
	}

	public String getActionUrl() {
		return this.actionUrl;
	}

	public String getPasswordResetTitle() {
		return this.passwordResetTitle;
	}

	public String getWellcomeTitle() {
		return this.wellcomeTitle;
	}

	public String getInvoiceTitle() {
		return this.invoiceTitle;
	}
}
