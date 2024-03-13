<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>A Patient</title>
</head>
<body>
<div class="main">
  <h1>Patient Details</h1>
  <%
  String patientID = (String) request.getParameter("patientID");
  List<String> patientDetails = (List<String>) request.getAttribute("patientDetails");

  if (patientID != null && !patientID.isEmpty() && patientDetails != null && !patientDetails.isEmpty()) {
  out.println("<h2>Patient Details for: " + patientID + "</h2>");
  out.println("<ul>");
    int columnCnt = 0;
    ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");

      for (String detail : patientDetails) {
      out.println("<li>" + columnNames.get(columnCnt) + ": " + detail + "</li>");
      columnCnt++;
      }
      out.println("</ul>");
  } else {
  out.println("<h2>No patient selected or details available.</h2>");
  }
  %>
</div>
</body>
</html>
