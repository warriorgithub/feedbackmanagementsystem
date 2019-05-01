package com.target.feedback.serviceImpl;

import org.springframework.stereotype.Service;

import com.target.feedback.analysis.FeedbackAnalysis;
import com.target.feedback.service.FeedbackService;
@Service
public class FeedbackServiceImpl  implements FeedbackService {

	@Override
	public String validateComment(String comment) {
		return FeedbackAnalysis.validateComment(comment);
	}
	
}
