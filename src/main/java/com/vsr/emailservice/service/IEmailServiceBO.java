package com.vsr.emailservice.service;

import com.vsr.emailservice.exception.EmailException;
import com.vsr.emailservice.vo.EmailStatusVO;
import com.vsr.emailservice.vo.EmailVO;
import com.vsr.emailservice.vo.MultiEmailVO;

public interface IEmailServiceBO {
	
	EmailStatusVO sendMail(EmailVO emailDetails) throws EmailException;
	
	EmailStatusVO sendMultiMail(MultiEmailVO emailDetails) throws EmailException;

}
