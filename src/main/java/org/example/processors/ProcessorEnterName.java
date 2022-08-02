package org.example.processors;

import org.example.entities.ProcessorResult;

import javax.servlet.http.HttpServletRequest;

public class ProcessorEnterName extends Processor{

    public ProcessorEnterName(){
        super();
        actionToPerform = "enterName";
    }

    @Override
    public ProcessorResult process(HttpServletRequest request) {
        String name = request.getParameter("name ");
        request.getSession().setAttribute("name", name);
        return new ProcessorResult("pages/question.jsp", null, true);
    }

}
