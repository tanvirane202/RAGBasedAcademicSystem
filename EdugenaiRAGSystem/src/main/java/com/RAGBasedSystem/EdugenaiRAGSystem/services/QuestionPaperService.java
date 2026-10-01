package com.RAGBasedSystem.EdugenaiRAGSystem.services;



import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RAGBasedSystem.EdugenaiRAGSystem.dtos.QuestionPaperRequest;
import com.RAGBasedSystem.EdugenaiRAGSystem.dtos.QuestionPaperResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuestionPaperService {

    @Autowired
    private ChatClient.Builder chatClientBuilder;

    @Autowired
    private RetrivalService retrievalService;

    public QuestionPaperResponse generateQuestionPaper(
            QuestionPaperRequest request) {

        ChatClient chatClient = chatClientBuilder.build();

        ObjectMapper objectMapper = new ObjectMapper();

      
        List<Document> results =
                retrievalService.search(request.getTopic());

        String context = "";

        for (Document doc : results) {
            context = context + doc.getText() + "\n";
        }

        
        String prompt = """

                You are EduGenAI, an academic question paper generator.

                Generate questions ONLY from the provided study material.

                Do not use information that is not present in the study material.

                Do not invent or add topics from your own knowledge.

                Subject: %s

                Topic: %s

                Difficulty: %s

                Number of Questions: %d

                Question Type: %s

                Study Material:

                %s

                Generate exactly %d questions.

                Return the response in this exact JSON format:

                {
                  "questions": [
                    "Question 1",
                    "Question 2",
                    "Question 3"
                  ]
                }

                Do not add any explanation outside the JSON.

                """.formatted(

                request.getSubject(),
                request.getTopic(),
                request.getDifficulty(),
                request.getNumberOfQuestions(),
                request.getQuestionType(),
                context,
                request.getNumberOfQuestions()
        );

        
        String result = chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();

        try {

           
            QuestionPaperResponse llmResponse =
                    objectMapper.readValue(
                            result,
                            QuestionPaperResponse.class
                    );

           
            return new QuestionPaperResponse(
                    request.getSubject(),
                    request.getTopic(),
                    llmResponse.getQuestions()
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse question paper JSON",
                    e
            );
        }
    }
}

