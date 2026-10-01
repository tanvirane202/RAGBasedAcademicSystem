package com.RAGBasedSystem.EdugenaiRAGSystem.services;



import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RAGBasedSystem.EdugenaiRAGSystem.entities.Document;
import com.RAGBasedSystem.EdugenaiRAGSystem.repositories.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository docRepo;

    @Autowired
    private TextExtractionService textExtractionService;

    public List<Document> getAllDocuments() {

        List<Document> list = docRepo.findAll();

        return list;
    }

    public String uploadDocument(
            MultipartFile file,
            String subject) throws IOException {

        String fileName = file.getOriginalFilename();

        Path uploadPath = Paths.get("uploads", subject);

        Files.createDirectories(uploadPath);

        Path filePath = uploadPath.resolve(fileName);

        try {

            Files.copy(
                    file.getInputStream(),
                    filePath
            );

        } catch (FileAlreadyExistsException e) {

            return "File already exists";
        }

        List<Document> documents =
                docRepo.findBySubject(subject);

        int version = 1;

        for (Document d : documents) {

            if (d.getVersion() != null
                    && d.getVersion() >= version) {

                version = d.getVersion() + 1;
            }
        }

        Document doc = new Document(
                fileName,
                file.getContentType(),
                LocalDateTime.now(),
                subject,
                version
        );

      
        docRepo.save(doc);

        System.out.println(
                "Document saved in MySQL. ID = "
                + doc.getId()
        );

       
        
        try {

            textExtractionService.extractText(
                    doc.getId()
            );

            System.out.println(
                    "Document processed and added to Qdrant."
            );

        } catch (Exception e) {

            System.out.println(
                    "Error while processing document: "
                    + e.getMessage()
            );

            throw new IOException(
                    "Document uploaded but processing failed.",
                    e
            );
        }

        System.out.println(
                "File name :- " + fileName
        );

        System.out.println(
                "Subject :- " + subject
        );

        return "File uploaded successfully";
    }

    public List<Document> searchDocuments(
            String keyword) {

        return docRepo
                .findByFileNameContainingIgnoreCase(keyword);
    }

    public List<Document> getAllDocuments1() {

        return docRepo.findAll();
    }

    public Document getDocumentById(Long id) {

        return docRepo
                .findById(id)
                .orElse(null);
    }
}

