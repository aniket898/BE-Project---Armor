/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scripts;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Samkit
 */
@WebServlet(name = "signup", urlPatterns = {"/signup"})
public class signup extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
             String Fname=request.getParameter("F_name");
        String Lname=request.getParameter("L_name");
        
          
        String user=request.getParameter("mail_id");
        
        String passwd=request.getParameter("pass_key");
        String gender=request.getParameter("Gender");
        if(validate.checkUser(user))
        {
           
            
            RequestDispatcher rs=request.getRequestDispatcher("index.jsp");
            rs.include(request, response);
            
           out.println("<script>document.getElementById('notification_1').innerHTML='*Username already exists'</script>");
             
        }
        else
        {
            
            if(validate.addUser(Fname, Lname, user, passwd, gender)==1)
            {
                RequestDispatcher rs=request.getRequestDispatcher("home.jsp");
                rs.forward(request, response);
            }
            else
            {
                RequestDispatcher rs=request.getRequestDispatcher("index.jsp");
                rs.include(request, response);
            
                out.println("<script>document.getElementById('notification_1').innerHTML='*Failed to add a new user'</script>");
            }
            }    
        }
        
        finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
        //PrintWriter out=response.getWriter();
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
