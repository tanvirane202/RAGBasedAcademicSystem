package com.RAGBasedSystem.EdugenaiRAGSystem.services;




import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RAGBasedSystem.EdugenaiRAGSystem.repositories.DocumentRepository;

@Service
public class TextExtractionService {

    @Autowired
    private DocumentRepository docRepo;

    @Autowired
    private ChunkingService chunkServ;

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    private EmbeddingService embeddingService;

    private final Tika tika = new Tika();

    
    public String extractText(String filePath)
            throws Exception {

        String text =
                tika.parseToString(
                        new File(filePath)
                );

        List<String> chunks =
                chunkServ.createChunks(text);

        List<Document> documents =
                new ArrayList<>();

        for (String chunk : chunks) {

            System.out.println(
                    "CHUNK: " + chunk
            );

            Document document =
                    new Document(chunk);

            documents.add(document);
        }

        vectorStore.add(documents);

        return text;
    }

   
    public String extractText(Long id)
            throws Exception {

        com.RAGBasedSystem.EdugenaiRAGSystem.entities.Document doc =
                docRepo.findById(id)
                        .orElseThrow();

        String filePath =
                "uploads/"
                + doc.getSubject()
                + "/"
                + doc.getFileName();

        System.out.println(
                "Processing file: "
                + filePath
        );

        String text =
                tika.parseToString(
                        new File(filePath)
                );

        List<String> chunks =
                chunkServ.createChunks(text);

        List<Document> documents =
                new ArrayList<>();

        for (String chunk : chunks) {

            System.out.println(
                    "CHUNK: " + chunk
            );

            Document document =
                    new Document(chunk);

           
            document.getMetadata().put(
                    "documentId",
                    doc.getId()
            );

            document.getMetadata().put(
                    "subject",
                    doc.getSubject()
            );

            document.getMetadata().put(
                    "fileName",
                    doc.getFileName()
            );

            document.getMetadata().put(
                    "fileType",
                    doc.getFileType()
            );

            document.getMetadata().put(
                    "version",
                    doc.getVersion()
            );

            documents.add(document);
        }

       
        vectorStore.add(documents);

        System.out.println(
                "Total chunks stored in Qdrant: "
                + documents.size()
        );

        return text;
    }
}