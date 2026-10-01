<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>EduGenAI - Upload Study Material</title>

<style>

* {
    box-sizing: border-box;
}

body {
    margin: 0;
    font-family: Arial, Helvetica, sans-serif;
    background: linear-gradient(135deg, #eef2ff, #f8fafc);
    min-height: 100vh;
}

/* Navbar */

.navbar {
    background: #1e293b;
    color: white;
    padding: 18px 60px;
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.logo {
    font-size: 24px;
    font-weight: bold;
}

.nav-text {
    color: #cbd5e1;
    font-size: 14px;
}



.main {
    width: 650px;
    margin: 60px auto;
}


.card {
    background: white;
    padding: 40px;
    border-radius: 18px;
    box-shadow: 0 10px 30px rgba(0,0,0,0.08);
}

.title {
    text-align: center;
    margin-bottom: 8px;
    color: #1e293b;
}

.subtitle {
    text-align: center;
    color: #64748b;
    margin-bottom: 35px;
}



.form-group {
    margin-bottom: 22px;
}

label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    color: #334155;
}

input[type="text"],
input[type="file"] {
    width: 100%;
    padding: 13px;
    border: 1px solid #cbd5e1;
    border-radius: 9px;
    font-size: 14px;
    background: #f8fafc;
}

input:focus {
    outline: none;
    border-color: #2563eb;
}



.upload-btn {
    width: 100%;
    padding: 14px;
    border: none;
    border-radius: 9px;
    background: #2563eb;
    color: white;
    font-size: 16px;
    font-weight: bold;
    cursor: pointer;
}

.upload-btn:hover {
    background: #1d4ed8;
}



.documents-btn {
    display: block;
    text-align: center;
    text-decoration: none;
    margin-top: 15px;
    padding: 13px;
    border-radius: 9px;
    background: #16a34a;
    color: white;
    font-weight: bold;
}

.documents-btn:hover {
    background: #15803d;
}



.back-btn {
    display: block;
    text-align: center;
    text-decoration: none;
    margin-top: 12px;
    padding: 12px;
    border-radius: 9px;
    background: #e2e8f0;
    color: #334155;
    font-weight: bold;
}

.back-btn:hover {
    background: #cbd5e1;
}


.info {
    margin-top: 25px;
    padding: 15px;
    background: #eff6ff;
    border-left: 4px solid #2563eb;
    border-radius: 6px;
    color: #475569;
    font-size: 14px;
}



.footer {
    text-align: center;
    margin-top: 25px;
    color: #94a3b8;
    font-size: 13px;
}

</style>

</head>

<body>



<div class="navbar">

    <div class="logo">
        EduGenAI
    </div>

    <div class="nav-text">
        AI-Powered Academic Assistant
    </div>

</div>




<div class="main">

    <div class="card">

        <h1 class="title">
            Upload Study Material
        </h1>

        <p class="subtitle">
            Add academic documents to your EduGenAI knowledge base
        </p>


        <form action="/upload"
              method="post"
              enctype="multipart/form-data">


           

            <div class="form-group">

                <label for="subject">
                    Subject
                </label>

                <input
                    type="text"
                    id="subject"
                    name="subject"
                    placeholder="Example: DBMS"
                    required>

            </div>


          

            <div class="form-group">

                <label for="file">
                    Study Material
                </label>

                <input
                    type="file"
                    id="file"
                    name="file"
                    accept=".pdf,.doc,.docx,.ppt,.pptx"
                    required>

            </div>


            

            <button
                type="submit"
                class="upload-btn">

                Upload Document

            </button>

        </form>


        

        <a
            href="/documents"
            class="documents-btn">

            📚 View Uploaded Documents

        </a>


      
        <a
            href="/"
            class="back-btn">

            ← Back

        </a>


       

        <div class="info">

            Supported formats:
            PDF, DOC, DOCX, PPT and PPTX

        </div>

    </div>


    <div class="footer">

        EduGenAI • RAG Based Academic Assistant

    </div>

</div>

</body>

</html>
