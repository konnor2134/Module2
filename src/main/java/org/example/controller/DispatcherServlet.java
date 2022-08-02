package org.example.controller;

import org.example.entities.ProcessorResult;
import org.example.processors.Processor;
import org.example.processors.ProcessorEnterName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet extends HttpServlet {

    private List<Processor> processorList = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        super.init();
        processorList.add( new ProcessorEnterName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procces(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        procces(req, resp);
    }

    private void procces(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        for(Processor processor:processorList){
            if(processor.canProcess(action)){
                ProcessorResult result = processor.process(request);
                if(result.getIncludedPage() != null){
                    request.getSession().setAttribute("includedJspPage", result.getIncludedPage());
                }
                if(result.isForward()){
                    RequestDispatcher dispatcher = request.getRequestDispatcher(result.getUrl());
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect(result.getUrl());
                }
            }
        }
    }

}
