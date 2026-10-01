package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RAGBasedSystem.EdugenaiRAGSystem.services.RetrivalService;

import org.springframework.ai.document.Document;

@RestController
public class RetrivalController {

	
	   @Autowired
	    private RetrivalService retrievalService;

	    @GetMapping("/retrival/search")
	    public List<Document> search(@RequestParam String query) {

	        return retrievalService.search(query);
	    }
	    
	    @GetMapping("/retrival/search/subject")
	    public List<Document> searchBySubject(
	            @RequestParam String query,
	            @RequestParam String subject) {

	        return retrievalService.searchBySubject(query, subject);
	    }
	    
	    @GetMapping("/retrival/advanced-search")
	    public List<Document> advancedSearch(
	            @RequestParam String query) {

	        return retrievalService.advancedSearch(query);
	    }
}
