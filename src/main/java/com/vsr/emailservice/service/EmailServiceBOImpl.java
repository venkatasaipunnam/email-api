/**
 * 
 */
package com.vsr.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vsr.emailservice.exception.EmailException;
import com.vsr.emailservice.utils.Constants;
import com.vsr.emailservice.vo.EmailStatusVO;
import com.vsr.emailservice.vo.EmailVO;
import com.vsr.emailservice.vo.MultiEmailVO;

/**
 * 
 */
@Component
public class EmailServiceBOImpl implements IEmailServiceBO {
	
	@Autowired
	EmailSender emailSender;

	@Override
	public EmailStatusVO sendMail(EmailVO emailDetails) throws EmailException {
		EmailStatusVO emailStatus = new EmailStatusVO();
		try {
			emailSender.sendEMail(emailDetails);
			emailStatus.setStatus(Constants.SUCCESS_STATUS);
			emailStatus.setMessage(Constants.SUCCESS_MESSAGE);
		} catch (EmailException exp) {
			emailStatus.setErrorMessage(Constants.ERROR_MESSAGE);
			emailStatus.setStatus(Constants.FAIL_STATUS);
		}
		return emailStatus;
	}

	@Override
	public EmailStatusVO sendMultiMail(MultiEmailVO emailDetails) throws EmailException {
		EmailStatusVO emailStatus = new EmailStatusVO();
		try {
			emailSender.sendEMailMultiple(emailDetails);
			emailStatus.setStatus(Constants.SUCCESS_STATUS);
			emailStatus.setMessage(Constants.SUCCESS_MESSAGE);
		} catch (EmailException exp) {
			emailStatus.setErrorMessage(Constants.ERROR_MESSAGE);
			emailStatus.setStatus(Constants.FAIL_STATUS);
		}
		return emailStatus;
	}
	
	

}
