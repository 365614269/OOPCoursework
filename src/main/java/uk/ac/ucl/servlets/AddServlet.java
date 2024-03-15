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
@WebServlet("/runadd.html")
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // Use the model to do the search and put the results into the request object sent to the
        // Java Server Page used to display the results.
        Model model = ModelFactory.getModel();
        ArrayList<String> newPatient = new ArrayList<>();
        ArrayList<String> columnNames = model.getColumnNames();

        for (int i = 1; i <= columnNames.size(); i++) {
            String param = (String) request.getParameter("param" + i);

            if (param == null) {
                param = "";
            }

            newPatient.add(param);
        }

        model.addValue(newPatient);


        // Invoke the JSP page.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/addResult.jsp");
        dispatch.forward(request, response);
    }
}
