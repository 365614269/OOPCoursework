<link rel="stylesheet" href="searchResult.css">

<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h1>Search Result</h1>
  <%
    List<String> patients = (List<String>) request.getAttribute("result");
    if (patients.size() !=0)
    {
  %>
  <ul>
    <%
      for (String patient : patients)
      {
        String href = "patientDetails.html?patientID=" + URLEncoder.encode(patient, "UTF-8");
    %>
    <li><a href="<%=href%>"><%=patient%></a>
    <% }
    } else
    {%>
    <p>Nothing found</p>
    <%}%>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>