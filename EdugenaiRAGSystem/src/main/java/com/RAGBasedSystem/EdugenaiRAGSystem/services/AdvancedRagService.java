package com.RAGBasedSystem.EdugenaiRAGSystem.services;



import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RAGBasedSystem.EdugenaiRAGSystem.dtos.AdvcRAGResponse;


@Service
public class AdvancedRagService {

    @Autowired
    private RetrivalService retrievalService;

    @Autowired
    private ChatClient.Builder chatClientBuilder;

    private List<String> conversationHistory = new ArrayList<>();

    public AdvcRAGResponse ask(String question) {

        
        List<Document> results =
                retrievalService.search(question);

       
        if (results == null || results.isEmpty()) {

            return new AdvcRAGResponse(
                    question,
                    "Content not found in the uploaded study material.",
                    List.of()
            );
        }

        StringBuilder context =
                new StringBuilder();

        Set<String> references =
                new LinkedHashSet<>();

       
        for (Document doc : results) {

            String subject =
                    String.valueOf(
                            doc.getMetadata().get("subject")
                    );

            String fileName =
                    String.valueOf(
                            doc.getMetadata().get("fileName")
                    );

            String documentId =
                    String.valueOf(
                            doc.getMetadata().get("documentId")
                    );

            String version =
                    String.valueOf(
                            doc.getMetadata().get("version")
                    );

            context.append(
                    "Subject: "
                    + subject
                    + "\n"
            );

            context.append(
                    "File: "
                    + fileName
                    + "\n"
            );

            context.append(
                    "Document ID: "
                    + documentId
                    + "\n"
            );

            context.append(
                    "Version: "
                    + version
                    + "\n"
            );

            context.append(
                    "Content: "
                    + doc.getText()
                    + "\n\n"
            );

            
            if (!"null".equals(fileName)) {

                references.add(
                        "File: " + fileName
                );
            }

            if (!"null".equals(subject)) {

                references.add(
                        "Subject: " + subject
                );
            }

            if (!"null".equals(documentId)) {

                references.add(
                        "Document ID: " + documentId
                );
            }

            if (!"null".equals(version)) {

                references.add(
                        "Version: " + version
                );
            }
        }

       
        String history =
                String.join(
                        "\n\n",
                        conversationHistory
                );

        
        String prompt = """
                You are EduGenAI Advanced RAG Assistant.

                You must answer ONLY from the retrieved study material.

                IMPORTANT RULES:

                1. Do NOT use outside knowledge.

                2. Do NOT invent information.

                3. Use previous conversation only to understand
                   the context of a follow-up question.

                4. The actual answer must still come from the
                   retrieved study material.

                5. If the retrieved study material does not contain
                   enough information to answer the question,
                   respond exactly:

                Content not found in the uploaded study material.

                6. Do not answer from your general knowledge.

                7. Give a clear and student-friendly answer.

                Previous Conversation:
                %s

                Retrieved Study Material:
                %s

                Current Question:
                %s
                """.formatted(
                        history,
                        context,
                        question
                );

       
        ChatClient chatClient =
                chatClientBuilder.build();

        String answer =
                chatClient
                        .prompt()
                        .user(prompt)
                        .call()
                        .content();

       
        conversationHistory.add(
                "Student: "
                + question
                + "\nEduGenAI: "
                + answer
        );

        
        return new AdvcRAGResponse(
                question,
                answer,
                new ArrayList<>(references)
        );
    }
}


