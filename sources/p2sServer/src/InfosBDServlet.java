/*
 * InfosBDServlet.java
 *
 * Created on 13 mars 2005, 23:10
 */

import java.io.*;
import java.util.Properties;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 *
 * @author Fabien
 * @version
 */
public class InfosBDServlet extends HttpServlet {
    
    public static Properties InfosBD;
    
    /** Initializes the servlet.
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        try{
            InfosBD = new Properties();
            InputStream in = new FileInputStream(getServletContext().getRealPath("/ConnexionBD.properties"));
            InfosBD.load(in);
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /** Destroys the servlet.
     */
    public void destroy() {
        
    }
    
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        /* TODO output your page here
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet</title>");
        out.println("</head>");
        out.println("<body>");
         
        out.println("</body>");
        out.println("</html>");
         */
        out.close();
    }
    
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    
}
