package com.target.feedback.controller;

import org.feedback.service.api.dto.GenericResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.target.feedback.service.FeedbackService;


@RestController
public class FeedbackController {
	private static final String POSITIVE_FEEDBACK=" Positive";
	
	@Autowired FeedbackService feedBackService;
	
	@GetMapping("/feedback")
	public String validateFeedback(@RequestParam("comment") String comment) {
		if(feedBackService.validateComment(comment).equalsIgnoreCase(POSITIVE_FEEDBACK)) {
			return GenericResponseDto.ResponseEnum.POSITIVE_COMMENT.toString();
		}return GenericResponseDto.ResponseEnum.NEGATIVE_COMMENT.toString();
	}

}
