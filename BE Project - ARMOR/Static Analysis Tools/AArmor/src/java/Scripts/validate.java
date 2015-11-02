/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Scripts;

import java.sql.*;
import java.sql.DriverManager;

/**
 *
 * @author Samkit
 */
public class validate 
{
    public static boolean checkUser(String user,String pass,String user_name)
    {
        boolean result=false;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/armor","root","sushah");
            PreparedStatement ps=con.prepareStatement("select * from user where email=? and password=?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs=ps.executeQuery();
            result=rs.next();
            if(result)
                user_name=new String(rs.getString("FirstName"));
            con.close();
            //System.out.println("");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return(result);
    }
    public static boolean checkUser(String user)
    {
        boolean result=false;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/armor","root","sushah");
            PreparedStatement ps=con.prepareStatement("select * from user where email=?");
            ps.setString(1, user);
            ResultSet rs=ps.executeQuery();
            result=rs.next();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return(result);
    }
    public static int addUser(String Fname,String Lname,String Uname,String Password,String Gender)
    {
         int result=0;
          
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/armor","root","sushah");
            PreparedStatement ps=con.prepareStatement("insert into user values (?,?,?,?,?)");
            ps.setString(1, Fname.toString());
            ps.setString(2, Lname.toString());
            ps.setString(3, Uname.toString());
            ps.setString(4, Password.toString());
            ps.setString(5, Gender.toString());
            /*
            ps.setString(1, "Aniket");
            ps.setString(2,"Thigale");
            ps.setString(3,"samshah1993@gmail.com");
            ps.setString(4,"sushah");
            ps.setString(5,"male");*/
            result=ps.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    
}
