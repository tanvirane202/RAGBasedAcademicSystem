package com.RAGBasedSystem.EdugenaiRAGSystem.dtos;



import java.util.List;

public class AdvcRAGResponse {

    private String question;
    private String answer;
    private List<String> references;

    public AdvcRAGResponse() {
    }

    public AdvcRAGResponse(
            String question,
            String answer,
            List<String> references) {

        this.question = question;
        this.answer = answer;
        this.references = references;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }
}


