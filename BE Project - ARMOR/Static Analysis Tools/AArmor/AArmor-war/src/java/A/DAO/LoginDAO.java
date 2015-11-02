
package A.DAO;
import java.text.*;
import java.util.*;
import java.sql.*;

import A.bean.*;
public class LoginDAO 
{
public static void main(String [] a)
	{
		login( new login());
	}
	public static boolean login(login b)
	{
		Statement stmt = null;
		Connection Con = null;
		ResultSet rs =null;  
		String id=b.getUsern();
		String pw=b.getPass();
		b.setValid(true);
		try 
                {
	      
                   
		   Class.forName("com.mysql.jdbc.Driver");
		   String strurl="jdbc:mysql://localhost:3306/armor";
		   Con = DriverManager.getConnection(strurl,"root","root"); 
                   System.out.println("Successfully Connected!! "+Con);
	       stmt=Con.createStatement();
	       	rs=stmt.executeQuery("select * from login");
	       while(rs .next())
	       {
	            String username=rs.getString(1);
	            String password=rs.getString(2);
	            if(id.equals(username) && pw.equals(password))
	            {
	            	
	               return true;
	              
	            }

	      }
	       
	} 
	catch (Exception ex) 
	   {
	      System.out.println("Log In failed: An Exception has occurred! " + ex);
	   } 
	 finally 
	   {
	      if (rs != null)	
	      {
	         try 
	         {
	            rs.close();
	         } 
	         catch (Exception e) 
	         {
	        	 
	         }
	            rs = null;
	       }
		  if (stmt != null) 
	      {
	         try 
	         {
	            stmt.close();
	         } catch (Exception e) 
	         {
	        	 
	         }
	            stmt = null;
	       }
		
	      if (Con != null) 
	      {
	         try 
	         {
	            Con.close();
	         }
	         catch (Exception e) 
	         {
	         }

	         Con = null;
	      }
	   }
	 return false;
	}
    
    
    
    
    
    
    
    
    
    
}
