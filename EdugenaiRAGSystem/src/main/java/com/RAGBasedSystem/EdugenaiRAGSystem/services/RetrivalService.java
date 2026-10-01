package com.RAGBasedSystem.EdugenaiRAGSystem.services;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrivalService {

    @Autowired
    private VectorStore vectorStore;

   
    public List<Document> search(String query) {

        return vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(3)
                        .build()
        );
    }

    
    public List<Document> searchBySubject(
            String query,
            String subject) {

        return vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(query)
                        .topK(3)
                        .filterExpression(
                                "subject == '" + subject + "'"
                        )
                        .build()
        );
    }

  
    public List<Document> advancedSearch(String query) {

        List<String> queries = new ArrayList<>();

        queries.add(query);

        
        queries.add(query + " concepts");
        queries.add(query + " explanation");
        queries.add(query + " important points");

       
        Map<String, Document> uniqueDocuments =
                new LinkedHashMap<>();

        for (String searchQuery : queries) {

            List<Document> results =
                    vectorStore.similaritySearch(
                            SearchRequest.builder()
                                    .query(searchQuery)
                                    .topK(3)
                                    .build()
                    );

            for (Document document : results) {

                String id = document.getId();

                if (id == null) {
                    id = document.getText();
                }

                uniqueDocuments.put(id, document);
            }
        }

       
        return new ArrayList<>(
                uniqueDocuments.values()
        ).stream()
         .limit(5)
         .toList();
    }
}

