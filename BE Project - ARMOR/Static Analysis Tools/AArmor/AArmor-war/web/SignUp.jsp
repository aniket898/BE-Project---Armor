<html>
<head>
<title>Form Validation</title><body>
<link href="aarmor.css" rel="stylesheet" type="text/css" media="screen" />
<div id="header">
  <h1><a href="#"><img src="images/armor.png" alt="Return to the homepage" /></a></h1>
  <hr style="height:50px;float:left;width:0px;margin-bottom:0px;margin-left:2%;" />
  <ul id="categories">  
    <li><a href="index.jsp">Home</a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
    <li><a href="#"></a></li>
  </ul></div><br><br>
  <hr>
  <div id="container">
     <form action="/cgi-bin/test.cgi" name="myForm"  onsubmit="return(validate());" align="center">
         <form action="SignUpServlet">
 <table cellspacing="2" cellpadding="2" color="black" size="4" >
 <tr>
   <td align="right">Choose your username : </td>
   <td><input type="text" name="Name" /></td>
 </tr>
 <tr>
   <td align="right">Create your password : </td>
   <td><input type="password" name="Pass" /></td>
 </tr>
 <tr>
   <td align="right">Confirm your password : </td>
   <td><input type="password" name="Pass1" /></td>
 </tr>
 <tr>
   <td align="right">Your current EMail address : </td>
   <td><input type="text" name="EMail" /></td>
 </tr>
 
 
 <tr>
 <td align="right"></td><h3>
 <td><input type="submit" value="Submit" /></td></h3>
 </tr>
 </table>
 </form>          
  </div>
<script type="text/javascript">
<!--

function validateEmail()
{
 
   var emailID = document.myForm.EMail.value;
   atpos = emailID.indexOf("@");
   dotpos = emailID.lastIndexOf(".");
   if (atpos < 1 || ( dotpos - atpos < 2 )) 
   {
       alert("Please enter correct email ID")
       document.myForm.EMail.focus() ;
       return false;
   }
   return( true );
} 
function validate()
{
   if( document.myForm.Name.value == "" )
   {
     alert( "Please provide your username!" );
     document.myForm.Name.focus() ;
     return false;
   }
   if(document.myForm.Name.value.toString().length < 8)
       {
     alert( "Please provide your username atleast 8 characters long!" );
     document.myForm.Name.focus() ;
     return false;
   }
   if(document.myForm.Pass.value.toString() !=  document.myForm.Pass1.value.toString())
       {
     alert( "Passwords do not match" );
     document.myForm.Name.focus() ;
     return false;
   }
   if( document.myForm.Pass.value == "" )
   {
     alert( "Please provide your Password!" );
     document.myForm.Pass.focus() ;
     return false;
   }
   if(document.myForm.Pass.value.toString().length < 8)
       {
     alert( "Please provide your password atleast 8 characters long!" );
     document.myForm.Name.focus() ;
     return false;
   }
   if( document.myForm.EMail.value == "" )
   {
     alert( "Please provide your Email!" );
     document.myForm.EMail.focus() ;
      return false;
   }
     else{
     // Put extra check for data format
     var ret = validateEmail();
     if( ret == false )
     {
          return false;
     }
   }
    
   
   return( true );
}
//-->
</script>
</head>
    <hr><br><br>
<div id="footer">
  Developed by JARS!!
 
</div>
    <script type="application/dart" src="aarmor.dart"></script>
    <script src="packages/browser/dart.js"></script>

 </body>
 </html>

