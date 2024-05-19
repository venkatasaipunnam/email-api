/**
 * 
 */
package com.vsr.emailservice.vo;

import java.util.ArrayList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 
 */
@Data
public class MultiEmailVO {
	
	@NotNull(message = "Please enter the list of To Email Address", groups = { MultiEmailVOValidation.class })
	private ArrayList<String> emailTo;
	
	private ArrayList<String> emailCc;
	
	private ArrayList<String> emailBcc;
	
	@NotBlank(message = "Please enter From Email Address", groups = { MultiEmailVOValidation.class })
	private String emailFrom;
	
	@NotBlank(message = "Please enter Name for From Email Address", groups = { MultiEmailVOValidation.class })
	private String emailFromName;
	
	@NotBlank(message = "Please enter Subject for the Email", groups = { MultiEmailVOValidation.class })
	private String subject;
	
	@NotBlank(message = "Please enter the Message of the Email", groups = { MultiEmailVOValidation.class })
	private String message;
	
	private String replyTo;
	
	public interface MultiEmailVOValidation {
		
	}

}
