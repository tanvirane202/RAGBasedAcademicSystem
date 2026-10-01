package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RAGBasedSystem.EdugenaiRAGSystem.dtos.AdvcRAGResponse;
import com.RAGBasedSystem.EdugenaiRAGSystem.services.AdvancedRagService;

@RestController
@RequestMapping("/advanced-rag")
public class AdvancedRagController {

    @Autowired
    private AdvancedRagService advancedRagService;

    @GetMapping("/ask")
    public AdvcRAGResponse ask(
            @RequestParam String question) {

        return advancedRagService.ask(question);
    }
}