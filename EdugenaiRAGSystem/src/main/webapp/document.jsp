<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>




<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>EduGenAI - Documents</title>

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


/* ================= MAIN ================= */

.main {
    width: 1100px;
    margin: 50px auto;
}


/* ================= CARD ================= */

.card {
    background: white;
    padding: 35px;
    border-radius: 18px;

    box-shadow:
        0 10px 30px rgba(0,0,0,0.08);
}




.page-header {
    display: flex;
    justify-content: space-between;
    align-items: center;

    margin-bottom: 25px;
}

.title-section h1 {
    margin: 0;
    color: #1e293b;
}

.title-section p {
    margin-top: 7px;
    color: #64748b;
}




.upload-btn {
    text-decoration: none;

    background: #2563eb;
    color: white;

    padding: 12px 18px;

    border-radius: 8px;

    font-weight: bold;
}

.upload-btn:hover {
    background: #1d4ed8;
}


.back-btn {
    display: inline-block;

    text-decoration: none;

    background: #e2e8f0;
    color: #334155;

    padding: 10px 18px;

    border-radius: 8px;

    font-weight: bold;

    margin-bottom: 25px;
}

.back-btn:hover {
    background: #cbd5e1;
}




.search-box {
    display: flex;

    gap: 10px;

    margin-bottom: 30px;
}

.search-input {
    flex: 1;

    padding: 13px 15px;

    border: 1px solid #cbd5e1;

    border-radius: 8px;

    font-size: 14px;

    outline: none;
}

.search-input:focus {
    border-color: #2563eb;
}


.search-btn {
    padding: 13px 22px;

    border: none;

    border-radius: 8px;

    background: #475569;

    color: white;

    font-weight: bold;

    cursor: pointer;
}

.search-btn:hover {
    background: #334155;
}


.clear-btn {
    display: flex;

    align-items: center;

    text-decoration: none;

    padding: 13px 18px;

    border-radius: 8px;

    background: #e2e8f0;

    color: #334155;

    font-weight: bold;
}

.clear-btn:hover {
    background: #cbd5e1;
}




.table-container {
    overflow-x: auto;
}

table {
    width: 100%;

    border-collapse: collapse;
}

thead {
    background: #f1f5f9;
}

th {
    color: #334155;

    font-size: 14px;

    font-weight: bold;
}

th,
td {
    border-bottom: 1px solid #e2e8f0;

    padding: 15px;

    text-align: left;
}

tbody tr:hover {
    background: #f8fafc;
}



.download-btn {
    text-decoration: none;

    background: #16a34a;

    color: white;

    padding: 8px 14px;

    border-radius: 6px;

    font-size: 13px;

    font-weight: bold;
}

.download-btn:hover {
    background: #15803d;
}



.empty {
    text-align: center;

    padding: 40px;

    color: #64748b;
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


        

        <div class="page-header">

            <div class="title-section">

                <h1>
                    Uploaded Documents
                </h1>

                <p>
                    Manage and search your academic study materials
                </p>

            </div>


            <a
                href="/upload"
                class="upload-btn">

                + Upload Document

            </a>

        </div>



        
        <a
            href="/upload"
            class="back-btn">

            ← Back to Upload

        </a>



        

        <form
            action="/search"
            method="get"
            class="search-box">


            <input
                type="text"
                name="keyword"
                class="search-input"
                placeholder="Search by file name..."
                required>


            <button
                type="submit"
                class="search-btn">

                🔍 Search

            </button>


            <a
                href="/documents"
                class="clear-btn">

                Clear

            </a>

        </form>



        

        <div class="table-container">

            <table>


                <thead>

                    <tr>

                        <th>
                            ID
                        </th>

                        <th>
                            File Name
                        </th>

                        <th>
                            File Type
                        </th>

                        <th>
                            Subject
                        </th>

                        <th>
                            Version
                        </th>

                        <th>
                            Upload Date
                        </th>

                        <th>
                            Action
                        </th>

                    </tr>

                </thead>



                <tbody>


                    <c:choose>


                        <c:when test="${empty documents}">

                            <tr>

                                <td
                                    colspan="7"
                                    class="empty">

                                    📂 No documents found.

                                </td>

                            </tr>

                        </c:when>



                        <c:otherwise>


                            <c:forEach
                                var="doc"
                                items="${documents}">


                                <tr>


                                    <td>
                                        ${doc.id}
                                    </td>


                                    <td>
                                        ${doc.fileName}
                                    </td>


                                    <td>
                                        ${doc.fileType}
                                    </td>


                                    <td>
                                        ${doc.subject}
                                    </td>


                                    <td>
                                        v${doc.version}
                                    </td>


                                    <td>
                                        ${doc.uploadDate}
                                    </td>


                                    <td>

                                        <a
                                            href="/download?id=${doc.id}"
                                            class="download-btn">

                                            Download

                                        </a>

                                    </td>


                                </tr>


                            </c:forEach>


                        </c:otherwise>


                    </c:choose>


                </tbody>


            </table>

        </div>


    </div>



    

    <div class="footer">

        EduGenAI • RAG Based Academic Assistant

    </div>


</div>


</body>

</html>

