
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class upload extends HttpServlet 
{
    String toprint="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          toprint=  "<html xmlns=\"http://www.w3.org/1999/xhtml\">"+
            "<head>"+
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />"+
            "<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">"+
            "<META HTTP-EQUIV=\"Expires\" CONTENT=\"-1\">"+
            "<title>Armor</title>"+
            "<link href=\"aarmor.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />"+
                  "</head>"+"<body>"+"<div id=\"header\">"+"<div id=\"left\" style=\"width:0%;margin-top:-2.7%;\">"+
                  "<h1><a href=\"#\"><img src=\"images/armor_logo_1.jpg\" alt=\"Return to the homepage\" /></a></h1>"+
            "</div>"+ 
                "<div id=\"right\" style=\"float:right;width:70%;margin-left:1%;\">"+
            "<hr style=\"height:50px;float:left;width:0px;margin-bottom:0px;margin-left:0%;\" />"+
            "<ul id=\"categories\" style=\"padding-top:2%;padding-left: -2% \">  "+
             "<li><a href=\"home.jsp\" >Home</a></li>"+
              "<li><a href=\"upload\" class=\"activetab\">Upload</a></li>"+
              "<li><a href=\"result\">Results</a></li>"+
            "</ul>"+
            "</div>"+
          "</div>"+               
           " <hr>"+
             " <div id=\"content\">"+" </div>"+"<BR><BR><BR>hi";
            String saveFile = "";
            String contentType = request.getContentType();
            if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
            DataInputStream in = new DataInputStream(request.getInputStream());
            int formDataLength = request.getContentLength();
            byte dataBytes[] = new byte[formDataLength];
            int byteRead = 0;
            int totalBytesRead = 0;
            while (totalBytesRead < formDataLength) {
                  byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                  totalBytesRead += byteRead;
            }
            String file = new String(dataBytes);
            saveFile = file.substring(file.indexOf("filename=\"") + 10);
            saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
            saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1, saveFile.indexOf("\""));
            int lastIndex = contentType.lastIndexOf("=");
            String boundary = contentType.substring(lastIndex + 1, contentType.length());
            int pos;
            pos = file.indexOf("filename=\"");
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            pos = file.indexOf("\n", pos) + 1;
            int boundaryLocation = file.indexOf(boundary, pos) - 4;
            int startPos = ((file.substring(0, pos)).getBytes()).length;
            int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
            String str1=saveFile;
            int x=str1.indexOf(".");
            String typ=str1.substring(x+1);
            if(!typ.equals("zip") && !typ.equals("apk"))
             {
               toprint=toprint+
                "<Br><table border=\"2\">"+
                "<tr>"+
               " <td><b>You Can upload only zip / apk files .....</b></td>"+
                 "</tr>"+
               "<tr>"+
                   "<td><b><a href=\"upload1.html\">Go Back !!!</a>"+
                   "</b></td>"+
               "</tr>"+
               "</table>";  
            }
            else
            {
            saveFile = "F:/Temp/" + saveFile;
            File ff = new File(saveFile);
            FileOutputStream fileOut = new FileOutputStream(ff);
            fileOut.write(dataBytes, startPos, (endPos - startPos));
            fileOut.flush();
            fileOut.close();
            }    
              toprint=toprint+    "<hr>"+
            "<div id=\"footer\">"+
             " Developed by JARS"+
            "</div>"+
                "<!--script type=\"application/dart\" src=\"aarmor.dart\"></script>"+
                "<script src=\"packages/browser/dart.js\"></script-->"+
            "</body>"; 
      }
     } 
        finally
        {    
             out.println(toprint);
            out.close();
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }// </editor-fold>
}
