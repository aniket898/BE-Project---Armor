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

@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet
{
public SignUpServlet() 
 {
		super();
 }
public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	
	{
            SignUpDAO a=new SignUpDAO();   
            String user=request.getParameter("Name");
		String password=request.getParameter("Pass");
		String emailid=request.getParameter("EMail"); 
                login bean=new login(user,password,emailid);
                
                a.insertLoginTable(bean);
            HttpSession session = request.getSession();
            response.sendRedirect("Home.jsp");
            
            
            
            
            
            
        }
}
