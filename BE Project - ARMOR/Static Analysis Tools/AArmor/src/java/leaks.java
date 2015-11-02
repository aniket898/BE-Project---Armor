import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "leaks", urlPatterns = {"/leaks"})
public class leaks extends HttpServlet 
{
    String toprint="";
    List<String> allsources=new LinkedList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception 
    {
        
        
        CapDiag obj=new CapDiag();
        List<String> filenamelist = new LinkedList();
        filenamelist.add("testing.java");
        filenamelist.add("Category.java");
        filenamelist.add("OptionsHandler.java");
        
        String tt1="";
        
        for(int i=0;i<filenamelist.size();i++)
         {
             obj.filename=filenamelist.get(i);
             obj.cap_main();
             allsources.add(obj.sourcecode); 
             tt1=tt1+"data_121["+i+"]='"+obj.sourcecode+"';";
             
         }

        StringBuilder sb1= new StringBuilder();
            sb1.append("<script>var data = new Array();var chart;google.load('visualization', '1', {packages:['orgchart']});google.setOnLoadCallback(drawChart);function drawChart(){");
            sb1.append(obj.sbfinal.toString()+"chart = new google.visualization.OrgChart(document.getElementById('chart_div_2'));chart.draw(data[0], {allowHtml:true});}</script>");
            StringBuilder sb2 = new StringBuilder();
            for(int i=0;i<obj.orgchart99.size();i++)
            {
                sb2.append("<h3 id='"+i+"'>Section "+(i+1)+"</h3>");
                sb2.append("<div id=\"data_set\"></div>");
            }
             StringBuilder sb4 = new StringBuilder();
             for(int i=0;i<filenamelist.size();i++)
            {
                sb4.append("<h3 id='"+i+"'>Section "+(i+1)+"</h3>");
                sb4.append("<div id=\"data_set\"></div>");
            }
            
             StringBuilder sb3= new StringBuilder();
            sb3.append("<script>var data_121 = new Array();");
           sb3.append(tt1+"</script>");
            
        HttpSession session = request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
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
                   sb1.toString()+
                  sb3.toString()+
                    " <script>   "+
                    "  $(function() {"+         
                    "   $( '#accordion' ).accordion();"+
                    " $( '#accordion' ).on( 'accordionactivate', function( event, ui ) "+
                    "{"+
                    "var id=$(ui.newHeader).attr('id');"+
                    "document.getElementById('chart_div_sec1').innerHTML=data_121[id];"+              
                    "}"+
                    ");"+
                    "document.getElementById('chart_div_sec1').innerHTML=data_121[0];"+ 
                    " });"+
                    "$(function() {"+         
                    "   $( '#accordion_2' ).accordion();"+
                    " $( '#accordion_2' ).on( 'accordionactivate', function( event, ui ) "+
                    "{"+
                    "var id=$(ui.newHeader).attr('id');"+
                    "chart.draw(data[id], {allowHtml:true});"+              
                    "}"+ 
                    ");"+
                    " });"+
                    "$(function() {"+
                    " $( '#main_accordion' ).accordion()({"+        
                    "        collapsible: true,"+
                    "       active:false,"+
                    "      height:'content'"+
                    "}); "+
                    "});"+
                       
                    "  </script> ";
                    toprint1=toprint1+ 
                             "</head>"+

                   "<body>"+
                    " <div id='header'>"+
                     "<div id='left' style='width:0%;margin-top:-2.7%;'> "+
                     "<h1><a href='#'><img src='images/armor_logo_1.jpg' alt='Return to the homepage' /></a></h1>"+
                     "</div>"+

                         "<div id='right' style='float:right;width:80%;margin-left:1%;'>"+
                     "<hr style='height:50px;float:left;width:0px;margin-bottom:0px;margin-left:0%;' />"+

                      "<ul id='categories' style='padding-top:0%;padding-left: -2% '>  "+
                       "<li><a href='activity' >Activity</a></li>"+
                       "<li><a href='service'>Service</a></li>"+
                       "<li><a href='leaks' class=\"activetab\">Capability Leak</a></li>"+
                       "<li><a href='permissiont'>Permission</a></li>"+
                        "<li><a href='index.jsp'><font color='red'>Log-Out</font></a></li>"+
                    " </ul>"+

                     "</div>"+
                   "</div>"+
                    " <hr>"+
                     "    <div id='content' style='margin: 1%;'>"+
                     "<div id='main_accordion' >"+

                     "<h3>Section 1</h3>"+
                     "<div id='graph_container' style='border:1px solid #555555 ;overflow : hidden ; height: 500px'>"+
                             "<div id='chart_div_sec1' style='height:400px;overflow:auto;padding-top : 3%;padding-left:3%;float:left;width:70%;'>"+
                              
                             "</div>"+
                   "<div id='accordion' style='height:400px;overflow:auto;float:right;width:20%;padding-top:0%;'>"+
                           sb4.toString()+
                             
                   "  </div>"+
                              " </div>"+

                   "<h3>Section 2</h3>"+
                     "<div id='graph_container' style='border:1px solid #555555 ;overflow : hidden ; height: 500px'>"+
                             "<div id='chart_div_2' style='height:400px;overflow:auto;padding-top : 3%;padding-left:3%;float:left;width:70%;'>"+

                             "</div>"+
                   "<div id='accordion_2' style='height:400px;overflow:auto;float:right;width:20%;padding-top:0%;'>"+
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
        } finally {            
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(activity.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(activity.class.getName()).log(Level.SEVERE, null, ex);
        }
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

