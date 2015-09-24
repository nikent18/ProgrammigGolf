package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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
        
        // prepare response message for client
        Map<String, String> respFromServer = new HashMap<>();
        req.setAttribute("response", respFromServer);
        
        // create program file
        FileWorker file;
        try {
            file = new FileWorker(code, lang, id_user, id_task);
        } catch (IOException ex) {
            // create response message for client
            respFromServer.put("log", "Error! Can't create/find program file on server side");
            // forward request to basic page
            forwardResToClient(req, res);
            return;
        }
        
        // compile file
        String log;
        try {
            log = file.compileFile();
        } catch (InterruptedException | IOException ex) {
            // create response message for client
            respFromServer.put("log", "Error! Unknown problem of compiling of program file.");
            // forward request to basic page
            forwardResToClient(req, res);
            return;
        }
                
        // create response message for client
        respFromServer.put("filename", file.getFullFileName());
        respFromServer.put("lang", lang);
        respFromServer.put("symb", String.valueOf(code.length()));
        if(log.isEmpty())
            respFromServer.put("log", "Compile done!");
        else
            respFromServer.put("log", log);
        
        // forward request to basic page
        forwardResToClient(req, res);
        
    }
    
    /**
     * Function to forward response to basic page.
     * @param req an HttpServletRequest object that contains the request the client has made of the servlet
     * @param res an HttpServletResponse object that contains the response the servlet sends to the client 
     * @throws javax.servlet.ServletException if the request for the POST could not be handled
     * @throws java.io.IOException if an input or output error is detected when the servlet handles the request 
     */
    private void forwardResToClient(HttpServletRequest req, HttpServletResponse res) 
            throws IOException, ServletException {
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        if(rd != null)
            rd.forward(req, res);
        else
            res.sendError(HttpServletResponse.SC_NO_CONTENT);
    }
    
}
