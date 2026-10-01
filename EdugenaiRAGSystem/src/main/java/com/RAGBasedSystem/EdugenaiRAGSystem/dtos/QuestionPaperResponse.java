package com.RAGBasedSystem.EdugenaiRAGSystem.dtos;




import java.util.List;

public class QuestionPaperResponse {

    private String subject;
    private String topic;
    private List<String> questions;

    public QuestionPaperResponse() {
    }

    public QuestionPaperResponse(String subject, String topic, List<String> questions) {
        this.subject = subject;
        this.topic = topic;
        this.questions = questions;
    }

    public String getSubject() {
        return subject;
    }

    public String getTopic() {
        return topic;
    }

    public List<String> getQuestions() {
        return questions;
    }
}