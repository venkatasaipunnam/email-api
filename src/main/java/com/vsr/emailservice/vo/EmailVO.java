/**
 * 
 */
package com.vsr.emailservice.vo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 
 */
@Data
public class EmailVO {
	
	@NotBlank(message = "Please enter To Email Address", groups = { EmailVOValidation.class })
	private String emailTo;
	
	@NotBlank(message = "Please enter From Email Address", groups = { EmailVOValidation.class })
	private String emailFrom;
	
	@NotBlank(message = "Please enter Name for From Email Address", groups = { EmailVOValidation.class })
	private String emailFromName;
	
	@NotBlank(message = "Please enter Subject for the Email", groups = { EmailVOValidation.class })
	private String subject;
	
	@NotBlank(message = "Please enter the Message of the Email", groups = { EmailVOValidation.class })
	private String message;
	
	private String replyTo;
	
	public interface EmailVOValidation {
		
	}

}
