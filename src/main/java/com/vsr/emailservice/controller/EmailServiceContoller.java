/**
 * 
 */
package com.vsr.emailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsr.emailservice.exception.EmailException;
import com.vsr.emailservice.service.IEmailServiceBO;
import com.vsr.emailservice.utils.Constants;
import com.vsr.emailservice.vo.EmailStatusVO;
import com.vsr.emailservice.vo.EmailVO;
import com.vsr.emailservice.vo.EmailVO.EmailVOValidation;
import com.vsr.emailservice.vo.MultiEmailVO;
import com.vsr.emailservice.vo.MultiEmailVO.MultiEmailVOValidation;

/**
 * 
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1")
public class EmailServiceContoller {
	
	@Autowired
	IEmailServiceBO emailService;
	
	@PostMapping("/send/email")
	public ResponseEntity<EmailStatusVO> sendEmail(@RequestHeader HttpHeaders headers, @Validated(EmailVOValidation.class) @RequestBody EmailVO emailDetails) throws EmailException {
		EmailStatusVO emailStatus = emailService.sendMail(emailDetails);
		if (Constants.SUCCESS_STATUS.equals(emailStatus.getStatus())) {
			return new ResponseEntity<>(emailStatus, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(emailStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/send/multi")
	public ResponseEntity<EmailStatusVO> sendMultipleEmail(@RequestHeader HttpHeaders headers, @Validated(MultiEmailVOValidation.class) @RequestBody MultiEmailVO emailDetails) throws EmailException {
		EmailStatusVO emailStatus = emailService.sendMultiMail(emailDetails);
		if (Constants.SUCCESS_STATUS.equals(emailStatus.getStatus())) {
			return new ResponseEntity<>(emailStatus, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(emailStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
