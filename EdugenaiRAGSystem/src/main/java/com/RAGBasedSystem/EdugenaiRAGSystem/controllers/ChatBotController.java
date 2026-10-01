package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RAGBasedSystem.EdugenaiRAGSystem.dtos.ChatBotResponse;
import com.RAGBasedSystem.EdugenaiRAGSystem.services.LlmService;


@RestController
@RequestMapping("/chatbot")
public class ChatBotController {

	
	@Autowired
    private LlmService llmService;

    @GetMapping("/ask")
    public ChatBotResponse  ask(@RequestParam String question) {
    	String answer = llmService.ask(question);

        return new ChatBotResponse(question, answer);
    }
}
