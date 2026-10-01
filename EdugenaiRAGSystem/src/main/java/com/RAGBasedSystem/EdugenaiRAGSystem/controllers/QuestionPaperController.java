package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.RAGBasedSystem.EdugenaiRAGSystem.dtos.QuestionPaperRequest;
import com.RAGBasedSystem.EdugenaiRAGSystem.dtos.QuestionPaperResponse;
import com.RAGBasedSystem.EdugenaiRAGSystem.services.QuestionPaperService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/question-paper")

public class QuestionPaperController {

	
	@Autowired
	private QuestionPaperService questionPaperService;

	@PostMapping("/generate")
	public QuestionPaperResponse generateQuestionPaper(
	        @RequestBody QuestionPaperRequest request) {

	    return questionPaperService.generateQuestionPaper(request);
	}
	
	
}


