package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RAGBasedSystem.EdugenaiRAGSystem.services.LlmService;


@RestController
public class LlmController {

	
	
	
	
	 @Autowired
	    private LlmService llmService;

	    @GetMapping("/ask")
	    public String ask(@RequestParam String question) {
	        return llmService.ask(question);
	    }
}
