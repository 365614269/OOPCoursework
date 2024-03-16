<link rel="stylesheet" href="css/patientList.css">

<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Patients:</h2>
  <ul>
    <%
      List<String> patientIDs = (List<String>) request.getAttribute("patientIDs");
      ArrayList<String> patientNames = (ArrayList<String>) request.getAttribute("patientNames");
      for (int i = 0; i < patientIDs.size(); i++)
      {
        String href = "patientDetails.html?patientID=" + URLEncoder.encode(patientIDs.get(i), "UTF-8");
    %>
    <li><a href="<%=href%>"><%=patientNames.get(i)%></a>
    </li>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
