package com.example.DecisionMapServlet;

import DecisionMap.DecisionMaking;
import Exceptions.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

@WebServlet(name = "YesNodeServlet", value = "/YesNodeServlet")
public class YesNodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Adding header to allow for a request to be sent (localhost:3000)
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // Sets the content of the response
        response.setContentType("text/plain");

        //System.out.println("--------- YesNode ----------"); // Test for checking backend behaviour

        //Get the request data
        String data = request.getParameter("num");

        // Makes sure there is a query of num
        if (data != null) {
            // The parseInt can throw this error so just being careful
            try {
                int currentNodeID = Integer.parseInt(data);

                // System.out.println("Current Node: " + currentNodeID); // Tes for checking backend behaviour

                // Get Yes node and handle the exception
                DecisionMaking make = new DecisionMaking();
                try {
                    response.getWriter().print(make.MapYes(currentNodeID).getData());
                } catch (CustomNullException ne) {
                    response.getWriter().print(ne.getMessage());
                }
            // If the parseInt fails
            } catch(NumberFormatException nfe) {
                response.getWriter().print(nfe.getMessage());
            }
        // If there is no data in the query
        } else {
            response.getWriter().print("No num data inputted");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Adding header to allow for a request to be sent (localhost:3000)
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // Sets the content of the response
        response.setContentType("text/plain");

        //System.out.println("--------- YesNode ----------"); // Test for checking backend behaviour

        //Get the request data
        String data = request.getParameter("num");

        // Makes sure there is a query of num
        if (data != null) {
            // The parseInt can throw this error so just being careful
            try {
                int currentNodeID = Integer.parseInt(data);

                // System.out.println("Current Node: " + currentNodeID); // Test for checking backend behaviour

                // Get Yes node and handle the exception
                DecisionMaking make = new DecisionMaking();
                try {
                    response.getWriter().print(make.MapYes(currentNodeID).getData());
                } catch (CustomNullException ne) {
                    response.getWriter().print(ne.getMessage());
                }
                // If the parseInt fails
            } catch(NumberFormatException nfe) {
                response.getWriter().print(nfe.getMessage());
            }
            // If there is no data in the query
        } else {
            response.getWriter().print("No num data inputted");
        }
    }
// The code needs to be repeated because if it is in a method the repsonse.getWriter() throws a IOException which is not thrown when in a get or post method. Therefore the repeated code is worth not dealing with the handler
}

