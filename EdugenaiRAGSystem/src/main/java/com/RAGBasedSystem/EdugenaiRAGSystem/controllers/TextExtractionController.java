package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RAGBasedSystem.EdugenaiRAGSystem.services.ChunkingService;
import com.RAGBasedSystem.EdugenaiRAGSystem.services.TextExtractionService;



@RestController
public class TextExtractionController {
@Autowired
	private TextExtractionService textServ;
@Autowired
private ChunkingService chunkServ;
@GetMapping("/extract")
public String extractText(@RequestParam Long id) throws Exception {
	return textServ.extractText(id);
}

}
