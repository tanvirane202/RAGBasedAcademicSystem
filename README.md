# RAGBasedAcademicSystem

## EduGenAI

EduGenAI is an AI-powered academic assistant built using **Java, Spring Boot and Spring AI**.

The project allows students to upload and interact with academic study material using **RAG**. Faculty and Admin users can also generate AI-based question papers.

---

## Tech Stack

* Java 21
* Spring Boot 4.1.1
* Spring AI 2.0.1
* Spring Security
* JWT Authentication
* BCrypt
* Spring Data JPA / Hibernate
* MySQL
* Apache Tika
* Qdrant Vector Database
* Gemini Embeddings
* Groq LLM
* REST APIs
* Postman

---

# Modules

## 1. Authentication & Role-Based Access

Implemented:

* Student, Faculty and Admin roles
* User registration and login
* JWT authentication
* BCrypt password hashing
* JWT authentication filter
* Role information through JWT

Examples:

```text
POST /auth/register
POST /auth/login
```

---

## 2. Study Material Management

Implemented:

* PDF upload
* DOCX upload
* PPTX upload
* Subject-wise storage
* Document versioning
* MySQL document records
* Document search
* Document download

Uploaded files are stored according to subject.

Example:

```text
uploads/
 ├── DBMS/
 ├── ML/
 └── ...
```

Document information is stored in MySQL.

---

## 3. Document Processing

Apache Tika is used to extract text from uploaded documents.

Processing flow:

```text
Document
   ↓
Apache Tika
   ↓
Text Extraction
   ↓
Chunking
   ↓
Embedding
   ↓
Qdrant
```

Chunking:

```text
Chunk size: 500 characters
Overlap: 100 characters
```

---

## 4. RAG Student Chatbot

The chatbot uses:

* Gemini for embeddings
* Qdrant for vector storage and similarity search
* Groq LLM for answer generation

RAG flow:

```text
Question
   ↓
Embedding
   ↓
Qdrant Similarity Search
   ↓
Relevant Chunks
   ↓
Context
   ↓
Groq LLM
   ↓
Answer
```

Student chatbot endpoint:

```text
GET /chatbot/ask?question=What is DBMS
```

Example tested question:

```text
What is DBMS?
```

The chatbot retrieves the relevant DBMS study material and generates an answer using the retrieved context.

---

# 5. Advanced RAG

Advanced RAG features are implemented on top of the basic RAG pipeline.

### Metadata-Aware Retrieval

Each document chunk contains metadata:

```text
documentId
subject
fileName
fileType
version
```

This metadata is stored along with the vectors in Qdrant.

Retrieval can also filter results according to document metadata such as subject.

### Grounded Response

The chatbot is instructed to answer only from the retrieved study material.

For example, when asked:

```text
What is normalization?
```

if the uploaded DBMS study material does not contain information about normalization, the chatbot returns:

```text
I could not find this information in the uploaded study material.
```

This helps prevent unsupported answers when the required information is not available in the uploaded material.

### Conversation Context

The chatbot maintains previous questions and answers during the conversation.

Example:

```text
Question 1:
What is DBMS?

Question 2:
What are advantages of DBMS over file system?
```

The second question can use the previous conversation context while generating the answer.

---

# 6. AI Question Paper Generator

Faculty and Admin users can generate question papers using Groq LLM.

Endpoint:

```text
POST /question-paper/generate
```

Request:

```json
{
  "subject": "DBMS",
  "topic": "Schema",
  "difficulty": "Low",
  "numberOfQuestions": 3,
  "questionType": "Small Answers"
}
```

The API generates questions according to the requested:

* Subject
* Topic
* Difficulty
* Number of questions
* Question type

The questions are generated using the retrieved study material as context.

---

# RAG Components

## Gemini Embeddings

Gemini is used to convert document chunks into vector embeddings.

Embedding dimension:

```text
3072
```

## Qdrant

Qdrant is used as the vector database.

Collection:

```text
edugenai_documents
```

Similarity search is used to retrieve relevant document chunks.

## Groq

Groq is used as the LLM for:

* Student chatbot responses
* AI question paper generation

## Apache Tika

Apache Tika extracts text from:

```text
PDF
DOCX
PPTX
```

---

# Database

MySQL is used for application data and document metadata.

Document information includes:

```text
id
file_name
file_type
upload_date
subject
version
```

---

# Security

* JWT-based authentication
* BCrypt password hashing
* JWT authentication filter
* Role information through JWT authorities
* Spring Security configuration
* API credentials removed from public source code

---

# Project Flow

```text
                  EduGenAI
                     |
        ┌────────────┴────────────┐
        ↓                         ↓
 Authentication              Study Material
   & RBAC                    Management
        |                         |
       JWT                      MySQL
                                  |
                                  ↓
                           Apache Tika
                                  |
                                  ↓
                              Chunking
                                  |
                                  ↓
                         Gemini Embeddings
                                  |
                                  ↓
                              Qdrant
                                  |
                                  ↓
                         Similarity Search
                                  |
                                  ↓
                       Retrieved Context
                                  |
                    ┌─────────────┴─────────────┐
                    ↓                           ↓
              Groq LLM                    Grounding
                    ↓                           ↓
             Chatbot Answer              No-answer handling

Faculty/Admin
      |
      ↓
Question Paper Request
      |
      ↓
Study Material Retrieval
      |
      ↓
Groq LLM
      |
      ↓
Generated Questions
```

---

# API Testing

REST APIs were tested using **Postman**.

Testing includes:

* User registration
* User login
* JWT authentication
* Document upload
* Document search
* Document download
* Text extraction
* Embedding generation
* Qdrant similarity search
* RAG chatbot responses
* Metadata-aware retrieval
* Grounded no-answer handling
* Conversation context
* AI question-paper generation

Some tested APIs:

```text
POST /auth/register
POST /auth/login

GET /embedding?text=DBMS

GET /retrival/search?query=DBMS

GET /chatbot/ask?question=What is DBMS

POST /question-paper/generate
```

I also tested questions that were available in the uploaded study material and questions whose information was not available in the uploaded material.

---

# Sample Study Material

Sample DBMS documents are included for testing the RAG pipeline.

```text
uploads/
 └── DBMS/
      ├── DBMS_advc.docx
      ├── dbms_notes1.docx
      └── DBMS_syllabus.docx
```

These documents can be processed and stored in Qdrant for testing retrieval and chatbot responses.

---

# Project Status

Implemented and tested:

* Authentication and JWT security
* User registration and login
* Study Material Management
* PDF/DOCX/PPTX processing
* Text extraction using Apache Tika
* Chunking
* Gemini embeddings
* Qdrant vector search
* Basic RAG
* Metadata-aware RAG
* Grounded responses
* No-answer handling
* Conversation context
* AI Question Paper Generator
* Document upload, search and download
* REST API testing using Postman

---

## Main RAG Flow

```text
Academic Document
       ↓
Apache Tika
       ↓
Text Extraction
       ↓
Chunking
       ↓
Gemini Embeddings
       ↓
Qdrant
       ↓
Similarity Search
       ↓
Relevant Context + Metadata
       ↓
Groq LLM
       ↓
Grounded Answer
```
