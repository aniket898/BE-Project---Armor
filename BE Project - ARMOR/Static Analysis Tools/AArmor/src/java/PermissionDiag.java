/* Result in table1 , chart1 */
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class PermissionDiag 
{
    int tryin;
    String toprint;
    String appname;
    String INPUT_ZIP_FILE;
    String basepath;
    List<String> usesPermission = new LinkedList();
    StringBuilder sbfinal;
    String table1;
    String chart1;
void funcmain() throws Exception
    {
        table1="";
        chart1="";
        sbfinal=new StringBuilder();
        toprint="";
        appname=null;
        basepath = "F:\\Temp\\beproj1";
        func1();
    }
  void func1() throws IOException, ParserConfigurationException, SAXException, IllegalAccessException, InstantiationException, ClassNotFoundException, SQLException
    {
        String AndManfile=basepath+"\\"+"AndroidManifest.xml";
        toprint=toprint+"<BR>"+("<BR><BR>Starting with the detection of privilege escalation attack....<BR>");
        File fXmlFile = new File(AndManfile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
         /*
         * 
         * Application tag parsing....
         * 
         */
        {
        NodeList nList=doc.getElementsByTagName("application");
        for (int temp = 0; temp < nList.getLength(); temp++) 
                 {
                    Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                    {
                            Element eElement = (Element) nNode;
                            toprint=toprint+"<BR>"+("<BR><BR><B>Application / Package name : " + eElement.getAttribute("android:name")+"</B>");
                            appname=eElement.getAttribute("android:name");
                    }
                 }      
        }
        /*
        * 
        * 
        * DATA BASE CONNECTION .....
        */
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "sushah");
        properties.put("characterEncoding", "ISO-8859-1");
        properties.put("useUnicode", "true");
        String url = "jdbc:mysql://localhost/project1";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection c = DriverManager.getConnection(url, properties);
        Statement stmt = null;
        ResultSet rs = null;
        stmt = c.createStatement(); 
        /*
         * uses-permission tag parsing...
         * 
         */
        {
        List<Integer> perdbid=new LinkedList();
        List<Integer> perdblevel=new LinkedList();
        List<String> perdbdes=new LinkedList();
        List<Integer> perdbsev=new LinkedList();
        toprint=toprint+"<BR>"+("<BR><BR><B>------------ uses-permission Tag .....-------------</B>");
        NodeList nList = doc.getElementsByTagName("uses-permission");
	for (int temp = 0; temp < nList.getLength(); temp++) 
        {
		Node nNode = nList.item(temp);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {
			Element eElement = (Element) nNode;  
                        String s1=eElement.getAttribute("android:name");
                        if(s1.contains("android.permission."))
                        {
                            usesPermission.add(eElement.getAttribute("android:name"));
                            String dbq=eElement.getAttribute("android:name").substring(19);
                            rs = stmt.executeQuery("SELECT * FROM permissiondb where name='"+dbq+"'");
                            // toprint=toprint+"<BR>"+("<br>Executuing query as "+"SELECT * FROM permissiondb where name='"+dbq+"'");
                            rs = stmt.getResultSet();
                            rs.first();
                            perdbid.add(rs.getInt("id"));
                            perdblevel.add(rs.getInt("level"));
                            perdbsev.add(rs.getInt("severity"));
                            perdbdes.add(rs.getString("description"));   
                        }
		}
	}
        //now query the database to get all the level....
        rs = stmt.executeQuery("SELECT * FROM  level_tab");
        rs = stmt.getResultSet();
        rs.first();
        List<String> alllevels=new LinkedList();
        List<Integer> levelcount=new LinkedList();
        List<Float> severitylist=new LinkedList();
        do
         {
             alllevels.add(rs.getString("name"));
         }while(rs.next());
         for(int i=0;i<alllevels.size();i++)
         {
             levelcount.add(0);
             severitylist.add(0.0F);
         }
        //display all the uses permission in tabular format
        String try1="";
        for(int i=0;i<perdbid.size();i++)
        {
            int t=perdblevel.get(i)-1;
            int x=levelcount.get(t) +1;
            float f1=severitylist.get(t);
            f1=f1+perdbsev.get(i);
            severitylist.set(t, f1);
            levelcount.set(t,x);
            try1=try1+"["+perdbid.get(i)+",'"+usesPermission.get(i)+"','"+alllevels.get(perdblevel.get(i)-1)+
                  "',"+perdbsev.get(i)+",'"+perdbdes.get(i)+"'],";
        }
        try1=try1.substring(0, try1.length()-1);
        String s2= " <script type=\"text/javascript\" src=\"//www.google.com/jsapi\"></script>"+
                "<script type=\"text/javascript\">"+
                "google.load('visualization', '1', {packages: ['table']});"+
                "</script>"+
                "<script type=\"text/javascript\">"+
                "function drawVisualization() {"+
                "   var data = google.visualization.arrayToDataTable(["+
                "       ['id', 'name', 'level','severity','des'],"+
                try1+
                "     ]);"+
                "   visualization = new google.visualization.Table(document.getElementById('table_per'));"+
                "   visualization.draw(data, null); "+
                " } "+
                "google.setOnLoadCallback(drawVisualization);"+
                "</script>"+
                "<div id=\"table_per\"></div>";
      toprint=toprint+"<BR>"+(s2);
      table1=s2;
      String t2="";
      for(int i=0;i<levelcount.size();i++)
      {
            t2=t2+"['"+alllevels.get(i)+"',"+levelcount.get(i)+","+severitylist.get(i)/levelcount.get(i)+"],";
      }
      t2=t2.substring(0,t2.length()-1);
      String de="<script type=\"text/javascript\" src=\"https://www.google.com/jsapi\"></script>"+
                "  <script type=\"text/javascript\">"+
                "   google.load(\"visualization\", \"1\", {packages:[\"corechart\"]});"+
                "  google.setOnLoadCallback(drawChart90);"+
                " function drawChart90() {"+
                "  var data = google.visualization.arrayToDataTable(["+
                "   ['Type','Number','severity/3'],"+
                t2+
                "]);"+
                "var options = {"+
                " title: 'Permission Level Analyser',"+
                " vAxis: {title: 'Classes of Permissions',  titleTextStyle: {color: 'blue'}} ,"+
                "hAxis: {title: 'Count of Permissions',  titleTextStyle: {color: 'blue'}}"+
                "};"+
                "var chart = new google.visualization.BarChart(document.getElementById('chart_per98'));"+
                "chart.draw(data, options);"+
                "}"+
                "</script>"+
                "<div id=\"chart_per98\" style=\"width: 900px; height: 500px;\"></div>";
                toprint=toprint+"<BR>"+(de);
                chart1=de;
            }     
        }
}
