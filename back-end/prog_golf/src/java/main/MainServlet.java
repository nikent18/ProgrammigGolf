package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class for processing of HTTP requests
 */
public class MainServlet extends HttpServlet{

    /**
     * Constructor of the class MainServlet
     */
    public MainServlet() {
        super();
    }
    
    /**
    * Function for processing of HTTP (POST) request
    * @param req an HttpServletRequest object that contains the request the client has made of the servlet
    * @param res an HttpServletResponse object that contains the response the servlet sends to the client 
    * @throws javax.servlet.ServletException if the request for the POST could not be handled
    * @throws java.io.IOException if an input or output error is detected when the servlet handles the request
    */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        // parameters of HTTP request
        String code = req.getParameter("code");
        String lang = req.getParameter("lang");
        String id_task = req.getParameter("id_task");
        String id_user = req.getParameter("id_user");
        
        // determine character encoding specification
        res.setContentType("text/html;charset=utf-8");
        
        // Create program file
        FileWorker file;
        try {
            file = new FileWorker(code, lang, id_user, id_task);
        } catch (IOException ex) {
            PrintWriter pw = res.getWriter();
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<title>");
            pw.println("Programming Golf");
            pw.println("</title>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("Error! Can't create/find program file on server side");
            pw.println("</body>");
            pw.println("</html>");
            return;
        }
        String log = file.compileFile();
        
        // generate new HTML page as a response
        PrintWriter pw = res.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>");
        pw.println("Programming Golf");
        pw.println("</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("Code in file: " + file.getFullFileName());
        pw.println("<br>");
        pw.println("User: " + id_user);
        pw.println("<br>");
        pw.println("Task: " + id_task);
        pw.println("<br>");
        pw.println("Symbols in code: " + code.length());
        pw.println("<br>");
        pw.println("Language: " + lang);
        pw.println("<br>");
        pw.println("Log: ");
        if(log.isEmpty())
            pw.println("Compile done!");
        else
            pw.println(log);
        pw.println("</body>");
        pw.println("</html>");
    }
    
}
