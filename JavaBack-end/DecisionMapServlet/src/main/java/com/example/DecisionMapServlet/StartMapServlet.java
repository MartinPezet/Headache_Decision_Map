package com.example.DecisionMapServlet;

import DecisionMap.DecisionMaking;
import DecisionMap.DecisionNode;
import Exceptions.CustomNullException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "StartMapServlet", value = "/StartMapServlet")
public class StartMapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Adding header to allow for a request to be sent (localhost:3000)
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // Sets the content of the response
        response.setContentType("text/plain");

        // System.out.println("Start map"); //Test for checking backend behaviour

        // Getting the entryNode of the map and handling the null exception
        DecisionMaking make = new DecisionMaking();
        try{
            DecisionNode node = make.StartMap();
            response.getWriter().print(node.getData());
        } catch(CustomNullException ne){
            response.getWriter().print(ne.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Adding header to allow for a request to be sent (localhost:3000)
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        // Sets the content of the response
        response.setContentType("text/plain");

        // System.out.println("Start map"); //Test for checking backend behaviour

        // Getting the entryNode of the map and handling the null exception
        DecisionMaking make = new DecisionMaking();
        try{
            DecisionNode node = make.StartMap();
            response.getWriter().print(node.getData());
        } catch(CustomNullException ne){
            response.getWriter().print(ne.getMessage());
        }
    }
    // The code needs to be repeated because if it is in a method the response.getWriter() throws a IOException which is not thrown when in a get or post method. Therefore the repeated code is worth not dealing with the handler
}
