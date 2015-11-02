<%-- 
    Document   : Sign_In
    Created on : 12 Mar, 2014, 12:14:18 PM
    Author     : Rutugandha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head><link href="aarmor.css" rel="stylesheet" type="text/css" media="screen" />
<div id="header">
  <h1><a href="#"><img src="images/armor.png" alt="Return to the homepage" /></a></h1>
  <hr style="height:50px;float:left;width:0px;margin-bottom:0px;margin-left:2%;" />
  <ul id="categories">  
    <li><a href="index.jsp">Home</a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
  </ul></div><br><br>
    <body><hr> 
              <form action="SigninServlet"><font color="black" size="4">
 <center>
		<table><td>Username:</td>
    <td><input type="text" name="username"></td></tr>
  	<tr><td>Password:</td>
    <td><input type="password" name="password"></td>
    </tr></table><table>
    <td><font color="red">${error}</tr></td></font></table>
    <input name="Button" type=submit  value="SUBMIT"></td>
    <table>
    <tr><td><a href="SignUp.jsp"> Sign Up?</a></td></tr>
  	</table>
        
        
        </head>
    <hr><br><br></center>
    
<div id="footer">
  Developed by JARS!!
 
</div>
    <script type="application/dart" src="aarmor.dart"></script>
    <script src="packages/browser/dart.js"></script>

 </body>
        
        
        
    </body>
</html>
