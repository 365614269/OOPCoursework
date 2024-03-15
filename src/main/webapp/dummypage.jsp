<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Patient</title>
  <link rel="stylesheet" href="css/dummypage.css">
</head>
<body>
<div class="main">
  <h1>Edit Patient Details</h1>
  <%
    String patientID = (String) request.getParameter("patientID");
    List<String> patientDetails = (List<String>) request.getAttribute("patientDetails");
    ArrayList<String> columnNames = (ArrayList<String>) request.getAttribute("columnNames");

    if (patientID != null && !patientID.isEmpty() && patientDetails != null && !patientDetails.isEmpty() && columnNames != null && !columnNames.isEmpty()) {
      out.println("<form action='/changeDetails.html?patientID=" + patientID + "' method='POST'>");
      out.println("<h2>Patient Details for: " + patientID + "</h2>");
      out.println("<input type='hidden' name='patientID' value='" + patientID + "'>");

      for (int i = 0; i < patientDetails.size(); i++) {
        String detailName = columnNames.get(i);
        String detailValue = patientDetails.get(i);
        out.println("<label for='" + detailName + "'>" + detailName + ":</label>");
        out.println("<input type='text' id='" + detailName + "' name='" + detailName + "' value='" + detailValue + "'><br><br>");
      }
      out.println("<input type='submit' value='Save'>");
      out.println("</form>");
    } else {
      out.println("<h2>No patient selected or details available.</h2>");
    }
  %>
</div>
</body>
</html>
