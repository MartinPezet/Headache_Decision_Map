package com.example.DecisionMapServlet;

import DecisionMap.DecisionMaking;
import Exceptions.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "NoNodeServlet", value = "/NoNodeServlet")
public class NoNodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Adding header to allow for a request to be sent (localhost:3000)
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // Sets the content of the response
        response.setContentType("text/plain");

        // System.out.println("--------- NoNode ----------"); // Test for checking backend behaviour

        // Get request data
        String data = request.getParameter("num");

        // Checks if there is data within query
        if (data != null) {
            try {
                int currentNodeID = Integer.parseInt(data);
                // System.out.println("Current Node: " + currentNodeID); // Test for checking backend behaviour

                // Get No node and handle exception
                DecisionMaking make = new DecisionMaking();
                try {
                    response.getWriter().print(make.MapNo(currentNodeID).getData());
                } catch (CustomNullException ne) {
                    response.getWriter().print(ne.getMessage());
                }

            } catch(NumberFormatException nfe) {
                response.getWriter().print("No number for next node");
            }
        // if no data in query
        } else {
            response.getWriter().print("No data inputted");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Adding header to allow for a request to be sent (localhost:3000)
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // Sets the content of the response
        response.setContentType("text/plain");

        // System.out.println("--------- NoNode ----------"); // Test for checking backend behaviour

        // Get request data
        String data = request.getParameter("num");

        // Checks if there is data within query
        if (data != null) {
            try {
                int currentNodeID = Integer.parseInt(data);
                // System.out.println("Current Node: " + currentNodeID); // Test for checking backend behaviour

                // Get No node and handle exception
                DecisionMaking make = new DecisionMaking();
                try {
                    response.getWriter().print(make.MapNo(currentNodeID).getData());
                } catch (CustomNullException ne) {
                    response.getWriter().print(ne.getMessage());
                }

            } catch(NumberFormatException nfe) {
                response.getWriter().print("No number for next node");
            }
            // if no data in query
        } else {
            response.getWriter().print("No data inputted");
        }
    // The code needs to be repeated because if it is in a method the repsonse.getWriter() throws a IOException which is not thrown when in a get or post method. Therefore the repeated code is worth not dealing with the handler
    }
}
