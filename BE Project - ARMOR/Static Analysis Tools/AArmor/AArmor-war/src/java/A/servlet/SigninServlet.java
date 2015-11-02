/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package A.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import A.bean.*;
import A.DAO.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
@WebServlet(name = "SigninServlet", urlPatterns = {"/SigninServlet"})
public class SigninServlet extends HttpServlet
{
        public SigninServlet()
        {
        super();
    
        }
        
        @Override
         public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
        {

             boolean b1 = false;
             
                     String error="";
             {
		 login b=new login();
         String s=request.getParameter("username");
         String User=request.getParameter("username");
         String Pass=request.getParameter("password");
         
              b.setUsern(User);
              b.setPass(Pass);
              b1= LoginDAO.login(b);
              
		 if (b1)
                 {   
                    HttpSession session = request.getSession();
                    session.setAttribute("Ar",s);
            
                    RequestDispatcher rd=request.getRequestDispatcher("Home.jsp");
			rd.forward(request,response);

	     }
	     else
             {
               error="Username and password does not match";
               HttpSession session = request.getSession();
               session.setAttribute("error",error);
               response.sendRedirect("Sign_In.jsp");} //error page
	     }
 }
}

