package uk.ac.ucl.servlets;

import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// The servlet invoked to display a list of patients. Note that this data is just example data,
// you replace it with your data.
// The url http://localhost:8080/patientList.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet(urlPatterns = {"/patientDetails.html", "/search.html"})
public class ViewPatientDetailsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        try {
            String patientID = (String) request.getParameter("patientID");
            ArrayList<String> columnNames = model.getColumnNames();
            ArrayList<String> patientDetails = model.getPatientDetails(patientID);
            // Then add the data to the request object that will be sent to the Java Server Page, so that
            // the JSP can access the data (a Java data structure).
            request.setAttribute("patientDetails", patientDetails);
            request.setAttribute("columnNames", columnNames);

            // Invoke the JSP.
            // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
            ServletContext context = getServletContext();
            RequestDispatcher dispatch = context.getRequestDispatcher("/dummypage.jsp");
            dispatch.forward(request, response);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
