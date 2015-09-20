package main;

import java.io.File;
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
        
        // generate new HTML page as a response
        PrintWriter pw = res.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>");
        pw.println("Programming Golf");
        pw.println("</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("Code in file: " + codeToFile(code, lang, id_user, id_task));
        pw.println("<br>");
        pw.println("User: " + id_user);
        pw.println("<br>");
        pw.println("Task: " + id_task);
        pw.println("<br>");
        pw.println("Symbols in code: " + code.length());
        pw.println("<br>");
        pw.println("Language: " + lang);
        pw.println("</body>");
        pw.println("</html>");
    }
    
    /**
     * Function for save code in file to compile
     * @param code contents of the file
     * @param lang programming language
     * @param id_user user identifier
     * @param id_task task identifier
     * @return file name (with path)
     */
    private String codeToFile(String code, String lang, String id_user, String id_task) {
        
        // create file name
        String filepath = "C:\\";
        String filename = filepath + id_user + "_" + id_task + fileExtansion(lang);
        
        // create file
        File file = new File(filename);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        // write code to file
        try (PrintWriter pw = new PrintWriter(file.getAbsoluteFile())) {
            pw.print(code);
            pw.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error: File " + filename + " is not found.");
        }
        
        return filename;
    }
    
    /**
     * Function for determine the file extension by programming language
     * @param lang programming language
     * @return file extension
     */
    private String fileExtansion(String lang) {
        switch (lang) {
            case "C" : return ".c";
            case "C++" : return ".cpp";
            case "Java" : return ".java";
            case "Python" : return ".py";
        }
        return "";
    }
    
}
