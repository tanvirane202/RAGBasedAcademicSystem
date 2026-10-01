package com.RAGBasedSystem.EdugenaiRAGSystem.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmbeddingService {

    @Autowired
    private EmbeddingModel embeddingModel;

    public float[] createEmbedding(String text) {

        return embeddingModel.embed(text);
    }

    public List<float[]> createEmbeddings(List<String> chunks) {

        List<float[]> embeddings = new ArrayList<>();

        for (String chunk : chunks) {
            embeddings.add(embeddingModel.embed(chunk));
        }

        return embeddings;
    }
}

