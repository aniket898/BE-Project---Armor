import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "activity", urlPatterns = {"/activity"})
public class activity extends HttpServlet 
{
    String toprint="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception 
    {
        ActivityDiag obj=new ActivityDiag();
        obj.funcmain();
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            StringBuilder sb1= new StringBuilder();
            sb1.append("<script>var data = new Array();var chart;google.load('visualization', '1', {packages:['orgchart']});google.setOnLoadCallback(drawChart);function drawChart(){");
            sb1.append(obj.sbfinal.toString()+"chart = new google.visualization.OrgChart(document.getElementById('chart_div'));chart.draw(data[0], {allowHtml:true});}</script>");
            StringBuilder sb2 = new StringBuilder();
            for(int i=0;i<obj.actcount;i++)
            {
                sb2.append("<h3 id='"+i+"'>Section "+(i+1)+"</h3>");
                sb2.append("<div id=\"data_set\"></div>");
            }
            /* TODO output your page here. You may use following sample code. */
          String  toprint1="<!DOCTYPE html>"+
            "<html xmlns='http://www.w3.org/1999/xhtml'>"+
            "<head>"+
            "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />"+
            "<META HTTP-EQUIV='Pragma' CONTENT='no-cache'/>"+
            "<META HTTP-EQUIV='Expires' CONTENT='-1'>"+
             "   <script src='j_scripts/jquery-1.9.1.js'></script>   "+
              "  <script src='j_scripts/jquery-ui.js'></script> "+
               " <link rel='stylesheet' href='j_scripts/jquery-ui.css'>"+
                "<script type='text/javascript' src='https://www.google.com/jsapi'></script>	"+
                "<title>Armor</title>"+
                "<link href='aarmor.css' rel='stylesheet' type='text/css' media='screen' />"+
                 " <script>   "+
                "  $(function() {"+

                 "   $( '#accordion' ).accordion();"+
                        " $( '#accordion' ).on( 'accordionactivate', function( event, ui ) "+
                         "{"+
                        "var id=$(ui.newHeader).attr('id');"+
                          "chart.draw(data[id], {allowHtml:true});"+

                "		}"+ 
                "	 );"+
                " });"+
                 "$(function() {"+
                  " $( '#main_accordion' ).accordion()({"+

                   "        collapsible: true,"+
                    "       active:false,"+
                     "      height:'content'"+
                     "}); "+
               "});"+
                "  </script> "+
                 sb1.toString()+ 
             
                "</head>"+
                "<body>"+
                 " <div id='header'>"+
                  "<div id='left' style='width:0%;margin-top:-2.7%;'> "+
                  "<h1><a href='#'><img src='images/armor_logo_1.jpg' alt='Return to the homepage' /></a></h1>"+
                  "</div>"+
                    "<div id='right' style='float:right;width:70%;margin-left:1%;'>"+
                "<hr style='height:50px;float:left;width:0px;margin-bottom:0px;margin-left:0%;' />"+

                 "<ul id='categories' style='padding-top:2%;padding-left: -2% '>  "+
                "<li><a href='activity' class=\"activetab\">Activity</a></li>"+
                  "<li><a href='service'>Service</a></li>"+
                  "<li><a href='leaks'>Capability Leak</a></li>"+
                  "<li><a href='permissiont'>Permission</a></li>"+
                   "<li><a href='index.jsp'><font color='red'>Log-Out</font></a></li>"+
                " </ul>"+
                 "</div>"+
               "</div>"+
                " <hr>"+
                "    <div id='content' style='margin: 1%;'>"+
                "<div id='main_accordion' >"+
                "<h3 >Section 1</h3>";
                toprint1=toprint1+"<div id='graph_container' style='border:1px solid #555555 ;height: 500px'>     ";
                toprint1=toprint1+obj.strtab1+
                "</div>"+
                "<h3>Section 2</h3>"+
                "<div id='graph_container' style='border:1px solid #555555 ;overflow : hidden ; height: 500px'>"+
                        "<div id='chart_div' style='height:400px;overflow:auto;padding-top : 3%;padding-left:3%;float:left;width:70%;'>"+
                "</div>"+
                "<div id='accordion' style='height:400px;overflow:auto;float:right;width:20%;padding-top:0%;'>"+
                sb2.toString()+
                "  </div>"+
                 " </div>"+ 
                "</div>"+" </div>"+
                "<hr>"+
                "<div id='footer'>"+

                 " Developed by JARS"+
                "</div>"+
                 "   <!--script type='application/dart' src='aarmor.dart'></script>"+
                  "  <script src='packages/browser/dart.js'></script-->"+
                "</body>"+
                "</html>";
 
           out.println(toprint1);
        } finally 
        {            
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(activity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
