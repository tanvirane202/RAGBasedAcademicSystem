package com.RAGBasedSystem.EdugenaiRAGSystem.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LlmService {

    @Autowired
    private RetrivalService retrievalService;

    @Autowired
    private ChatClient.Builder chatClientBuilder;

   
    private List<String> conversationHistory = new ArrayList<>();

    public String ask(String question) {

        List<Document> results = retrievalService.search(question);

        String context = "";

        for (Document doc : results) {

            context = context
                    + "Subject: " + doc.getMetadata().get("subject") + "\n"
                    + "File: " + doc.getMetadata().get("fileName") + "\n"
                    + "Content: " + doc.getText() + "\n\n";
        }

        
        String history = String.join("\n\n", conversationHistory);

        ChatClient chatClient = chatClientBuilder.build();

        String answer = chatClient
                .prompt()
                .user("""
                        You are EduGenAI, an academic assistant.

                        Answer the student's question using the provided
                        study material and conversation context.

                        If the answer is not present in the study material,
                        say:
                        "I could not find this information in the uploaded study material."

                        Give the answer in a clear and student-friendly way.

                        Previous Conversation:
                        %s

                        Current Study Material Context:
                        %s

                        Current Question:
                        %s
                        """.formatted(history, context, question))
                .call()
                .content();

       
        conversationHistory.add(
                "Student: " + question + "\n"
                + "EduGenAI: " + answer
        );

        return answer;
    }
}