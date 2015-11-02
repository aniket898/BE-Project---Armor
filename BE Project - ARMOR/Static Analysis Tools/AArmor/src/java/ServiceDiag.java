// tabular result in strtab1
// orgchart result in orgchart1
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ServiceDiag
{
    List<String> fileList;
    int tryin;
    String toprint;
    String appname;
    String basepath;
    int nn;
    List<String> usesPermission = new LinkedList();
    List<String> newPermission = new LinkedList();
    List<String> activity=new LinkedList();
    List<String> service = new LinkedList();
    List<String> funclist = new LinkedList();
    List<String> funcname = new LinkedList();
    List<String> funcorder1 = new LinkedList();
    List<String> funcorder2 = new LinkedList();
    List<String> callorder= new LinkedList();
    List<String> intentextra= new LinkedList();
    List<String> taintvars= new LinkedList();
    List<String> orgchart1=new LinkedList();
    String strtab1="";
    StringBuilder sbfinal;
    List<String> act_name= new LinkedList();
    List<String> act_exp= new LinkedList();
    List<String> act_perm= new LinkedList();
    List<String> act_int= new LinkedList();
    List<String> act_list= new LinkedList();
 
 void funcmain() throws Exception
 {
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
        }//end of application tag parsing.....
        /*
         * 
         * Service Tag Parsing......
         * 
         */
         
         { 
	toprint=toprint+"<BR>"+("<BR>------------ Service .....-------------");
        NodeList nList = doc.getElementsByTagName("service");
        List<String> actnamel= new LinkedList();
        List<Boolean> exp= new LinkedList();
        List<Boolean> perproc= new LinkedList();
        List<Boolean> intentfil= new LinkedList();     
        List<String> leveluse= new LinkedList();
        List<Boolean> safety = new LinkedList();
        toprint=toprint+"<BR>"+("<hr/>");
        nn=nList.getLength();
        for (int temp = 0; temp<nList.getLength(); temp++) 
        {
            String try1="";
            try1=
            "data["+temp+"] = new google.visualization.DataTable();"+
            "data["+temp+"].addColumn('string', 'Name');"+
            "data["+temp+"].addColumn('string', 'head');"+
            "data["+temp+"].addColumn('string', 'ToolTip');"+
            "data["+temp+"].addRows([";
            String diagno=String.valueOf(temp+1);
            try1=try1+"['"+diagno+":"+appname+"','','Application Name'],";
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) 
            {
			Element eElement = (Element) nNode;
                        try1=try1+"['"+diagno+":"+eElement.getAttribute("android:name")+"','"+diagno+":"+appname+"','Activity Name'],";
                        actnamel.add(eElement.getAttribute("android:name"));
                        String actname=eElement.getAttribute("android:name");
                        if(eElement.getAttribute("android:exported").equals(""))                        
                        {
                          act_exp.add("NULL");
                          exp.add(true);
                          try1=try1+"['"+diagno+":"+"Exported : Undetected','"+diagno+":"+actname+"','Exported Tag'],";
                        }
                        else
                        {
                           if(eElement.getAttribute("android:exported").toString().equalsIgnoreCase("false"))
                           {
                                exp.add(false);
                           }
                           else
                                exp.add(true);
                            act_exp.add(eElement.getAttribute("android:exported").toString());
                            try1=try1+"['"+diagno+":Exported : "+eElement.getAttribute("android:exported").toString()+"','"+diagno+":"+actname+"','Exported Tag'],";
                        }
                        if(eElement.getAttribute("android:permission").equals(""))
                        {
                            perproc.add(false);
                            act_perm.add("NULL");
                            try1=try1+"['"+diagno+":"+"Permission : Undetected','"+diagno+":"+actname+"','Permission Attribute'],";
                        }
                        else
                        {
                           perproc.add(true);
                           act_perm.add(eElement.getAttribute("android:permission"));
                           try1=try1+"['"+diagno+":Permission : "+eElement.getAttribute("android:permission").toString()+"','"+diagno+":"+actname+"','Permission Attribute'],";
                        }
                        /* Parsing the sub -tags */
                        if(nNode.hasChildNodes())
                        {
                            NodeList childNodes = nNode.getChildNodes();
                            String intf="";
                            int intflag=0;
                            for (int j = 0; j < childNodes.getLength(); j++) 
                            {
                            Node cNode = childNodes.item(j);
                            if(cNode instanceof Element) 
                            {
                                //String content = cNode.getLastChild().getTextContent().trim();
                                if (cNode.getNodeName().equals("intent-filter")) 
                                {
                                    intflag=1;
                                    intentfil.add(true);
                                    NodeList childNodes2=cNode.getChildNodes();
                                    for (int k = 0; k < childNodes2.getLength(); k++) 
                                    {
                                        Node c2Node = childNodes2.item(k);
                                        if(c2Node instanceof Element) 
                                        {
                                            Element eElement2 = (Element) c2Node;
                                            if (c2Node.getNodeName().equals("action")) 
                                            {
                                             //  toprint=toprint+"<BR>"+("<BR>Action Name is : " + eElement2.getAttribute("android:name"));
                                               intf=intf+"Action: "+ eElement2.getAttribute("android:name");
                                               try1=try1+"['"+diagno+":"+eElement2.getAttribute("android:name").toString()+"','"+diagno+":"+actname+"','Action Tag'],";
                                            }
                                            else if (c2Node.getNodeName().equals("category")) 
                                            {
                                              // toprint=toprint+"<BR>"+("<BR>Category Name is : " + eElement2.getAttribute("android:name"));
                                               intf=intf+"Category: "+ eElement2.getAttribute("android:name");
                                               try1=try1+"['"+diagno+":"+eElement2.getAttribute("android:name").toString()+"','"+diagno+":"+actname+"','Category Tag'],";
                                            }
                                            

                                        }
                                    }
                                }
                             }
                         }
                            act_int.add(intf);
                            if(intflag==0)
                                intentfil.add(false);
                        }
                        else
                            intentfil.add(false);       
                }
              
           try1=try1.substring(0, try1.length()-1);
           try1=try1+ "]);";
           sbfinal.append(try1+"\n\n");
	}
          for(int i=0;i<actnamel.size();i++)
          {
              if(perproc.get(i)==true)
                  safety.add(true);
              else
              {
                if(exp.get(i)==true)
                    safety.add(false);
                               else if(exp.get(i)==false && intentfil.get(i)==false)
                                   safety.add(true);
                else
                    safety.add(false);
              }   
          }
        String try2="";
        for(int m=0;m<actnamel.size();m++)
        {
            String add="";
            if(safety.get(m)==false)
               add="<font color=\"red\">" ;
               try2=try2+"['"+add+actnamel.get(m)+"</font>'"+
                    ","+exp.get(m).toString()+
                    ","+perproc.get(m)+
                    ","+intentfil.get(m)+
                    ",'',"+
                    safety.get(m).toString()+
                    "],";
        }
        try2=try2.substring(0,try2.length()-1);
       strtab1= "<script type='text/javascript' src='https://www.google.com/jsapi'></script>"+
                " <script type='text/javascript'>"+
                "  google.load('visualization', '1', {packages:['table']});"+
                " google.setOnLoadCallback(drawTable);"+
                "function drawTable() {"+
                " var data = new google.visualization.DataTable();"+
                "data.addColumn('string', 'Name');"+
                "data.addColumn('boolean', 'Exported');"+
                "data.addColumn('boolean', 'Permission Protection');"+
                "data.addColumn('boolean', 'Intent-filter');"+
                "data.addColumn('string', 'LevelUse..');"+
                "data.addColumn('boolean', 'Safety');"+
                "data.addRows(["+try2+ "]);"+
                "var table = new google.visualization.Table(document.getElementById('table_div88'));"+
                "table.draw(data, {allowHtml : true});"+
                " }"+
                "</script>"+
                " <div id='table_div88'></div>";
      toprint=toprint+"<BR>"+(strtab1);     
      }
      for(int i=0;i<orgchart1.size();i++)
      {
          toprint=toprint+"<BR>"+("<BR><BR>"+orgchart1.get(i));
          toprint=toprint+"<BR>"+("<hr/>");   
      }
   }
}
