package com.RAGBasedSystem.EdugenaiRAGSystem.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RAGBasedSystem.EdugenaiRAGSystem.entities.Document;


public interface DocumentRepository extends JpaRepository<Document,Long> {
	  List<Document> findBySubject(String subject);
	  List<Document> findByFileNameContainingIgnoreCase(String fileName);
}
