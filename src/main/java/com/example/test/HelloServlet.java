package com.example.test;

import com.example.test.ejb.WelcomeMsg;

import java.io.*;
import javax.ejb.EJB;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    //Injecting an enterprise bean by using the @EJB annotation. The beanName attribute is being used to specify which
    // exact implementation of the bean to use.
    //Notice that we are not initializing the EJB here, we are simply injecting it. The task of initializing the bean is
    // being completed by the EJB container.
    @EJB (beanName = "WelcomeMsgBean2")
    WelcomeMsg welcomeMsg;
    private String message;

    public void init() {
        message = welcomeMsg.returnWelcomeMsg();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}