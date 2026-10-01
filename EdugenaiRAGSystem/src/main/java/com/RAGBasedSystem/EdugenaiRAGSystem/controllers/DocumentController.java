package com.RAGBasedSystem.EdugenaiRAGSystem.controllers;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RAGBasedSystem.EdugenaiRAGSystem.entities.Document;
import com.RAGBasedSystem.EdugenaiRAGSystem.services.DocumentService;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private DocumentService docServ;

    // ================= GET ALL DOCUMENTS =================

    @GetMapping("/documents")
    public List<Document> getAllDocuments() {

        return docServ.getAllDocuments();
    }

    // ================= SEARCH DOCUMENTS =================

    @PostMapping("/search")
    public List<Document> searchDocuments(
            @RequestBody Map<String, String> request) {

        String keyword = request.get("keyword");

        return docServ.searchDocuments(keyword);
    }

    // ================= UPLOAD DOCUMENT =================

    @PostMapping("/upload")
    public String uploadDocuments(
            @RequestParam("file") MultipartFile file,
            @RequestParam("subject") String subject)
            throws IOException {

        System.out.println(
                "File Name: " + file.getOriginalFilename()
        );

        System.out.println(
                "Subject: " + subject
        );

        return docServ.uploadDocument(file, subject);
    }

    // ================= DOWNLOAD DOCUMENT =================

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadDocument(
            @RequestParam("id") Long id)
            throws IOException {

        System.out.println("Download ID = " + id);

        Document doc = docServ.getDocumentById(id);

        if (doc == null) {
            return ResponseEntity.notFound().build();
        }

        System.out.println(
                "File Name = " + doc.getFileName()
        );

        System.out.println(
                "Subject = " + doc.getSubject()
        );

        System.out.println(
                "File Type = " + doc.getFileType()
        );

        Path filePath = Paths.get(
                "uploads",
                doc.getSubject(),
                doc.getFileName()
        );

        System.out.println(
                "File Path = "
                + filePath.toAbsolutePath()
        );

        if (!Files.exists(filePath)) {

            System.out.println(
                    "FILE NOT FOUND: "
                    + filePath.toAbsolutePath()
            );

            return ResponseEntity.notFound().build();
        }

        Resource resource =
                new FileSystemResource(filePath);

        String contentType =
                Files.probeContentType(filePath);

        if (contentType == null) {

            contentType =
                    MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return ResponseEntity.ok()
                .contentType(
                        MediaType.parseMediaType(contentType)
                )
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                        + doc.getFileName()
                        + "\""
                )
                .contentLength(
                        Files.size(filePath)
                )
                .body(resource);
    }
}



