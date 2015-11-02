/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package A.DAO;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import A.bean.*;

public class SignUpDAO 
{
	public Connection getConnection()
	{
	Connection con = null;
	try
	{
	Class.forName("com.mysql.jdbc.Driver");
	String strURL = "jdbc:mysql://localhost:3306/armor";
						
	con = DriverManager.getConnection(strURL,"root","root");
	System.out.println("Successfully Connected!! "+con);
	}
	catch(Exception e)
	{
	System.out.println(e);
	}
	return con;
	}
	public void insertLoginTable(login bean)
	{
	Connection con = this.getConnection();
	try
	{
	String query ="insert into LoginTable values (?,?,?)";
	PreparedStatement ps = (PreparedStatement)con.prepareStatement(query);
						
	    ps.setString(1,bean.getUsern());
	    ps.setString(2,bean.getPass());
		ps.setString(3, bean.getEmail());
		
		
		System.out.println("Table affected!");
						
		ps.executeUpdate();
						
		ps.close();
		con.close();
		}
		catch(Exception e)
		{
		System.out.println(e);
		}
	}
}
