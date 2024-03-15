<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Patient Data App</title>
  <link rel="stylesheet" href="css/searchResult.css">
  <script type="text/javascript">
    function confirmDelete(patientID) {
      var confirmAction = confirm("Are you sure you want to delete this patient?");
      if (confirmAction) {
        // Call a server-side method to delete the patient
        window.location.href = 'rundelete.html?patientID=' + encodeURIComponent(patientID);
        alert("Patient " + patientID + " deletion confirmed.");
      } else {
        // If cancelled, do nothing
        alert("Deletion cancelled.");
      }
    }
  </script>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h1>Search Result</h1>
  <%
    List<String> patients = (List<String>) request.getAttribute("result");
    if (patients != null && patients.size() != 0)
    {
  %>
  <ul>
    <%
      for (String patient : patients)
      {
        String patientID = URLEncoder.encode(patient, "UTF-8");
        String href = "patientDetails.html?patientID=" + patientID;
    %>
    <li>
      <a href="<%=href%>"><%=patient%></a>
      <!-- Adding a delete button for each patient -->
      <button type="button" onclick="confirmDelete('<%=patientID%>')">Delete</button>
    </li>
    <% }
    } else { %>
    <p>Nothing found</p>
    <% } %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>
