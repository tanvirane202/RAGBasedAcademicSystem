package com.RAGBasedSystem.EdugenaiRAGSystem.dtos;



public class ChatBotResponse {

	
	    private String question;
	    private String answer;

	    public ChatBotResponse(String question, String answer) {
	        this.question = question;
	        this.answer = answer;
	    }

	    public String getQuestion() {
	        return question;
	    }

	    public String getAnswer() {
	        return answer;
	    }
}
