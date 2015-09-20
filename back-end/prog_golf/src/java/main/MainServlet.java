package main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet{

    public MainServlet() {
        super();
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
        
        String code = req.getParameter("code");
        String lang = req.getParameter("lang");
        
        res.setContentType("text/html;charset=utf-8");
        PrintWriter pw = res.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>");
        pw.println("Programming Golf");
        pw.println("</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("Code: " + code);
        pw.println("<br>");
        pw.println("Language: " + lang);
        pw.println("</body>");
        pw.println("</html>");
    }
}
