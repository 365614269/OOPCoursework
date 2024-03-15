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
import java.util.List;
@WebServlet("/rundelete.html")
public class DeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Use the model to do the search and put the results into the request object sent to the
        // Java Server Page used to display the results.
        Model model = ModelFactory.getModel();
        String patientID = request.getParameter("patientID");

        model.deleteRow(patientID);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/deleteResult.jsp");
        dispatch.forward(request, response);
    }
}
