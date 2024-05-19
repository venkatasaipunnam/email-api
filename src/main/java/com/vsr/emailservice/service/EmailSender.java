package com.vsr.emailservice.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import com.vsr.emailservice.exception.EmailException;
import com.vsr.emailservice.vo.EmailVO;
import com.vsr.emailservice.vo.MultiEmailVO;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class EmailSender {

	@Autowired
	JavaMailSenderImpl mailer;

	@Autowired
	TemplateEngine templateEngine;

	public void sendEMail(EmailVO emailVO) throws EmailException {
		MimeMessage mimeMessage = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		try {
            helper.setFrom(emailVO.getEmailFrom(), emailVO.getEmailFromName());
            helper.setTo(emailVO.getEmailTo());
			helper.setSubject(emailVO.getSubject());
			helper.setText(emailVO.getMessage(), true);
			if (StringUtils.isNotBlank(emailVO.getReplyTo())) {
				helper.setReplyTo(emailVO.getReplyTo());
			}
			
			mailer.send(mimeMessage);
		} catch (MessagingException exp) {
			log.error("Error with message : \n {} \n with root cause:\n {}", exp.getMessage(), exp.getCause());
			throw new EmailException("Failed to Send Email", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception exp) {
			log.error("UnExpected Exception : \n {} \n with root cause:\n {}", exp.getMessage(), exp.getCause());
			throw new EmailException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void sendEMailMultiple(MultiEmailVO emailVO) throws EmailException {
		MimeMessage mimeMessage = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		try {
			helper.setFrom(new InternetAddress(emailVO.getEmailFrom(), emailVO.getEmailFromName()));
            helper.setTo(emailVO.getEmailTo().toArray(new String[0]));
            helper.setSubject(emailVO.getSubject());
			helper.setText(emailVO.getMessage(), true);
			if (StringUtils.isNotBlank(emailVO.getReplyTo())) {
				helper.setReplyTo(emailVO.getReplyTo());
			}
			if (!(emailVO.getEmailCc() == null || emailVO.getEmailCc().isEmpty() )) {
				helper.setCc(emailVO.getEmailCc().toArray(new String[0]));
			}
			if (!(emailVO.getEmailBcc() == null || emailVO.getEmailBcc().isEmpty() )) {
				helper.setBcc(emailVO.getEmailBcc().toArray(new String[0]));
			}
			mailer.send(mimeMessage);
		} catch (MessagingException exp) {
			log.error("Error with message : \n {} \n with root cause:\n {}", exp.getMessage(), exp.getCause());
			throw new EmailException("Failed to Send Email", HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception exp) {
			log.error("UnExpected Exception : \n {} \n with root cause:\n {}", exp.getMessage(), exp.getCause());
			throw new EmailException(exp.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
