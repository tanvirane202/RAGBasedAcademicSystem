package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RAGBasedSystem.EdugenaiRAGSystem.services.EmbeddingService;

@RestController
public class EmbeddingController {

    @Autowired
    private EmbeddingService embeddingService;

    @GetMapping("/embedding")
    public float[] getEmbedding(@RequestParam String text) {

        return embeddingService.createEmbedding(text);
    }
}

