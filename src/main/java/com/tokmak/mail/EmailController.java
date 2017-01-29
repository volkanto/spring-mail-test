package com.tokmak.mail;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/send")
	public ResponseEntity<?> sendMail(@RequestParam(value = "type") String argMailType,
			@RequestParam(value = "language") String argLanguage, HttpServletRequest argRequest,
			@RequestBody EmailSendDto argEmailSendDto) {

		EmailStatus status = this.emailService.send(argMailType, argLanguage, argEmailSendDto.getName(),
				argEmailSendDto.getAddress(), argRequest.getHeader(EmailContants.USER_AGENT));

		return status.isSuccess() ? new ResponseEntity<>(HttpStatus.OK)
				: new ResponseEntity<>(status.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
}
