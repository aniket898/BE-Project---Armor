import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CapDiag 
{
    StringBuilder sbfinal;
        final String downa ="&#9660&#9660&#9660";
        final String sidea="&#9658";
        int lineno;
        String toprint="";
        /*  result vars */
        String sourcecode="";
        String taintedtable=""; //has tainted vars tab
        List<String> orgchart99=new LinkedList(); //has func charts
        String filename;
        List<String> funclist = new LinkedList();
        List<String> funcname = new LinkedList();
        List<String> funcorder1 = new LinkedList();
        List<String> funcorder2 = new LinkedList();
        List<String> callorder= new LinkedList();
        List<String> intentextra= new LinkedList();
        List<String> taintvars= new LinkedList();
        List<String> stack1=new LinkedList();
        List<String> namesCovered=new LinkedList();
        List<Integer> nameocc=new LinkedList();
        List<String> funcbody=new LinkedList(); //with /n
        List<String> funcbody2=new LinkedList();// with <br>
        List<String> taint1=new LinkedList();
        List<String> taint2 = new LinkedList();
        List<Integer> numlist =new LinkedList();
        List<String> alltainted =new LinkedList();
        List<String> allclasses =new LinkedList();
        int loc,occ;
        void cap_main() throws Exception
        {
             sbfinal=new StringBuilder();
            toprint="";
            cap_leak(); 
        }
        public void cap_leak() throws Exception
        {
         setup_intent();
         int i,j,k;
         BufferedReader br=null;
         filename="F:\\Temp\\beproj1\\source\\"+filename;
         String everything=null;
         String sCurrentLine;
         br = new BufferedReader(new FileReader(filename));
         StringBuilder stringBuilder = new StringBuilder();
         /*
          * 
          * REGEX for function name declaration...
          */   
         String modifier1="(public|private|protected|void)";
         String modifier2="((static) )*";
         String returntype="(.[^ ])*";
         String name=".*[(].*[)]";
         String parameters ="([^){\n])*";
         String whitespace =" ";
         String open="[(]";
         String closed ="[)]";
         String pattern="";
         pattern=modifier1+" ";
         pattern=pattern+modifier2;
         pattern=pattern+returntype+" ";
         pattern=pattern+name;
         Pattern r = Pattern.compile(pattern);
         String func="";
         while ((sCurrentLine = br.readLine()) != null) 
         {
                Matcher m = r.matcher(sCurrentLine);
                func="Name: ";
                if (m.find( )) 
                {
                    func=func+m.group();
                    while(!(sCurrentLine = br.readLine()).contains("}"))
                    {
                        func=func+"\n"+sCurrentLine;
                    }
                    funclist.add(func+"\n"+"}");
                }
                stringBuilder.append(sCurrentLine);
                stringBuilder.append("\n");
	 }
         everything=stringBuilder.toString();
         br.close();
         br = new BufferedReader(new FileReader(filename));
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
        String patim="import ([^;]*);";
        Pattern rim = Pattern.compile(patim);
        /* Import group 
        while ((sCurrentLine = br.readLine()) != null) 
         {
             Matcher m = rim.matcher(sCurrentLine);
             if(m.find())
             {
                // toprint=toprint+"<BR>Got import group as "+m.group(1);
                  String hh=m.group(1).substring(8);
                   if(hh.contains("."))
                    {
                         hh=hh.substring(0, hh.indexOf(".") ); 
                    }
                    String qq="SELECT * FROM class_api where name LIKE '%"+hh+"%'";
                         //toprint=toprint+"<BR>"+qq;
                         stmt = c.createStatement();
                         rs=stmt.executeQuery(qq); 
                 while(rs.next())
                 {
                    //toprint=toprint+"<BR><B>NOT NULL</B>";
                  //toprint=toprint+" , ( "+rs.getString("keywords")+","+rs.getString("severity")+")";
                 }
                 
             }
         }
         br.close();
         */
         for(k=0;k<funclist.size();k++)
         {
             //toprint=toprint+"<BR>"+("\n\n\n"+funclist.get(k));
         }
         /*
          * now that it is an activity arrange the functions
          * in order of standard activity lifecycle....
          onCreate(1)--onStart(2)-(onResume(3)--onStop(4))--(onPause(5))--(onRestart(6))--onDestroy(7) 
          */
        for(i=0;i<7;i++)
         {
            funcorder1.add(i,"NULL");
         }
         // set up the order of std functions in activity
         {
            funcname.add("onCreate");
            funcname.add("onStart");
            funcname.add("onResume");
            funcname.add("onStop");
            funcname.add("onPause");
            funcname.add("onRestart");
            funcname.add("onDestroy");
         }
         for(i=0;i<funclist.size();i++)
         {
             if(funclist.get(i).contains("Name: "))
             {
                 if(funclist.get(i).contains("onCreate(Bundle savedInstanceState"))
                     funcorder1.set(0,funclist.get(i));
                 else if(funclist.get(i).contains("onStart()"))
                     funcorder1.set(1,funclist.get(i));
                 else if(funclist.get(i).contains("onResume()"))
                     funcorder1.set(2,funclist.get(i));
                 else if(funclist.get(i).contains("onStop()"))
                     funcorder1.set(3,funclist.get(i));
                 else if(funclist.get(i).contains("onPause()"))
                     funcorder1.set(4,funclist.get(i));
                 else if(funclist.get(i).contains("onRestart()"))
                     funcorder1.set(5,funclist.get(i));
                 else if(funclist.get(i).contains("onDestroy()"))
                     funcorder1.set(6,funclist.get(i));
                 else
                 {
                      String pat2="[. ]*([a-zA-Z0-9_]+)[(].*[)]";
                      Pattern p2= Pattern.compile(pat2);
                      Matcher m2 = p2.matcher(funclist.get(i));
                      if(m2.find()) 
                        {
                            String funccallwhole=m2.group();
                            String funcnameonly=m2.group(1).trim();
                            funcorder2.add(funclist.get(i));
                            funcname.add(funcnameonly);
                        }
                }
             }
         }
         //toprint=toprint+"<BR>"+("So the orders of std funcs are :<BR><BR>"+funcorder1.size());
         for(i=0;i<funcorder1.size();i++)
         {
             //toprint=toprint+"<BR>"+(i+funcorder1.get(i));
         }
         //toprint=toprint+"<BR>"+("So the orders of new funcs are<BR><BR> ");
         for(i=0;i<funcorder2.size();i++)
         {
             //toprint=toprint+"<BR>"+(funcorder2.get(i));
         }
         for(i=0;i<funcname.size();i++)
         {
             //toprint=toprint+"<BR>"+("names"+funcname.get(i));
         }       
         //creation of CFG
         //toprint=toprint+"<BR>"+("Starting with creation of CFG!!!");
         for(i=0;i<funcorder1.size();i++)
         {
              String mainstr=funcname.get(i);
              String temp1=funcorder1.get(i);
              create_order_funcs(mainstr,temp1);
         }
          //listing the order....
          //toprint=toprint+"<BR><BR><BR>"+("Listing order of functions");
          for(i=0;i<callorder.size();i++)
          {
              //toprint=toprint+"<BR>"+(callorder.get(i));
          }
          //make an org chart out of the callorder...
          String t1="";
          String tn="";
          stack1.add("");
          for(i=0;i<callorder.size();i++)
          {
              if(callorder.get(i).contains("st."))
              {
                  loc=-1;occ=-1;
                  getlococc(callorder.get(i).substring(3));
                  if(loc==-1)
                  {
                      if(namesCovered.isEmpty())
                          loc=0;
                      else
                          loc=namesCovered.size();
                       namesCovered.add(callorder.get(i).substring(3));
                       nameocc.add(1);
                       occ=1;     
                  }
                  else
                  {
                     nameocc.set(loc, nameocc.get(loc)+1);
                     occ=occ+1;
                  }
                  t1=t1+"['"+occ+":"+callorder.get(i).substring(3)+"','"+stack1.get(stack1.size()-1)+"',''],";
                  stack1.add(occ+":"+callorder.get(i).substring(3));
                  tn=tn+search2(callorder.get(i).substring(3));
                 tn=tn+"<BR><BR>";   
              }
              if(callorder.get(i).contains("en."))
              {
                  stack1.remove(stack1.size()-1);
              }
              if(stack1.size()==1)
              {
                  t1=t1.substring(0, t1.length()-1);
                  orgchart99.add(t1);
                  funcbody.add(tn);
                  t1="";
                  tn="";
                  namesCovered.clear();
                  nameocc.clear();  
              }
          }
          for(i=0;i<funcbody.size();i++)
          {
              String t11=funcbody.get(i);
              String t12="";
              int l=0;
              for(j=0;j<t11.length();j++)
              {
                  
                  if(t11.charAt(j)=='\n')
                  {
                      l=l+4;
                      t12=t12+"<BR>";
                  }
                  else
                  {
                     t12=t12+t11.charAt(j);
                  }
              }
              funcbody2.add(t12);
          }
          //toprint=toprint+"<BR><BR>*********** Starting taint analysis ********<BR><BR>";
         toprint="";
         List<Integer> taintedp=new LinkedList();
         lineno=1;
          for(i=0;i<funcorder1.size();i++)
          {
              taintedp.clear();
              if(!(funcorder1.get(i).equals("NULL")))
              {
              new_taint(funcorder1.get(i),taintedp);
               toprint=toprint+"<BR>--------------------------------------------------------------------<BR>";
              }
          }
          sourcecode=toprint;
          /*****************************************************/
          String temptab="";
          for(int mm=0;mm<alltainted.size();mm++)
          {
              temptab=temptab+"['"+alltainted.get(mm)+"',"+numlist.get(mm)+"],";
          }
          temptab=temptab.substring(0,temptab.length()-1);
           String strtab1= "<script type='text/javascript' src='https://www.google.com/jsapi'></script>"+
                            " <script type='text/javascript'>"+
                            "  google.load('visualization', '1', {packages:['table']});"+
                            " google.setOnLoadCallback(drawTable);"+
                            "function drawTable() {"+
                            " var data = new google.visualization.DataTable();"+
                            "data.addColumn('string', 'Name');"+
                            "data.addColumn('number', 'Location');"+
                            "data.addRows(["+temptab+ "]);"+
                            "var table = new google.visualization.Table(document.getElementById('table_tainted90'));"+
                            "table.draw(data, {allowHtml : true});"+
                            " }"+
                            "</script>"+ " <div id='table_tainted90'></div>";
           taintedtable=strtab1;
          // toprint=toprint+strtab1;
          /***************************Generation of charts...**************************/
           String tt="";
          for(i=0;i<orgchart99.size();i++)
          {
              tt=tt+
                        "data["+i+"] = new google.visualization.DataTable();"+
                        " data["+i+"].addColumn('string', 'Name');"+
                        " data["+i+"].addColumn('string', 'Manager');"+
                        " data["+i+"].addColumn('string', 'ToolTip');"+
                        " data["+i+"].addRows(["+orgchart99.get(i)+
                        "]);";
          }
          sbfinal.append(tt);
    }//end of cap_leak func
     
void new_taint(String mainfunc,List<Integer> taintedp)
{
         List<String> tainted =new LinkedList();
         List<String> tillnow =new LinkedList();
         String font1="<font size=\"3\" color=\"red\">";
         int i,j,k;
         int printflag=0;
         List<String> funclines=new LinkedList();
         /********* step 1 : read line by line the main func */
         funclines.clear();
         String t12="";
         int fnameloc=0;
         /* seperate out new lines in mainfunc */
         for(j=0;j<mainfunc.length();j++)
         {
            if(mainfunc.charAt(j)=='\n')
            {
               funclines.add(t12);
               t12="";
            } 
            else
               t12=t12+mainfunc.charAt(j);
         }
              /****************read it line by line parsing for function *********/
         /*   pattern detection for sinks.... */
        tainted.clear();
        tillnow.clear();
        if(funclines.size()>0)
        {
           String pat22="[. ]*([a-zA-Z0-9_]+)[(](.*)[)]";
           Pattern p22= Pattern.compile(pat22);
           Matcher m22 = p22.matcher(funclines.get(0));
           if(m22.find()) 
           {
             String funccallwhole=m22.group();
             String funcnameonly=m22.group(1).trim();
             toprint=toprint+"<BR><BR>"+(lineno++)+" "+"<font color=\"blue\">"+downa+"CALL TO"+downa+funcnameonly+"</font>";
             String param=m22.group(2);
             int ll=0,countp=0,ptr=0,curr=-1;
             String tp="";
             for(ll=0;ll<param.length() && ptr<taintedp.size() && !param.equals("");ll++)
                   {
                       curr=taintedp.get(ptr);
                       if(param.charAt(ll)==','||param.charAt(ll)==')')
                       {
                           if(curr==ptr)
                           {
                                if(tp.contains(" "))
                                 {
                                    int z=tp.indexOf(" ");
                                    tp=tp.substring(z+1);
                                 }
                               tp=tp.trim();
                               tainted.add(tp);
                               numlist.add(lineno);
                               alltainted.add(tp);
                               tillnow.add(tp);
                           }
                          
                           ptr++;
                           tp="";
                       }
                       else
                           tp=tp+param.charAt(ll);
                       
                   }
                   if(curr!=-1 && tp!="")
                    {
                        int z=tp.indexOf(" ");
                        tp=tp.substring(z+1);
                        tp=tp.trim();
                        tainted.add(tp);
                        numlist.add(lineno);
                        alltainted.add(tp);
                        tillnow.add(tp);
                   }
                }
           }
          if(taintedp.size()>0 && funclines.size()>0)
          {
              toprint=toprint+"<BR><BR>"+lineno+ " "+funclines.get(0);
              toprint=toprint+"<BR>"+font1+sidea+" TAINTED "+sidea+"   ";
              for(int y23=0;y23<tillnow.size();y23++)
              {
                  toprint=toprint+" ( "+tillnow.get(y23)+" ) ";
              }
              toprint=toprint+"</font>";
              tillnow.clear();
          }
          else if(funclines.size()>0)
          {
              toprint=toprint+"<BR><BR>"+lineno+" "+funclines.get(0);
          }
          toprint=toprint+"<BR>"+(++lineno)+" "+"{";
          for(int m=1;m<funclines.size();m++)
          {
            
              printflag=0;
              fnameloc=0;  
              /* Detection of tainted variables */
              String pat1="(.*)[=](.*)[(].*[)]";
              Pattern p1=Pattern.compile(pat1);
              Matcher m1=p1.matcher(funclines.get(m));
              int tyflag=0;
              while(m1.find())
              {
                 String tname=m1.group(1);
                 String tintent=m1.group(2);
                 if(tintent.contains("getIntent")||tintent.contains("Extra"))
                  {
                      tyflag=1;
                      if(tname.contains(" "))
                      {
                         int z=tname.indexOf(" ");
                         tname=tname.substring(z+1);
                      }
                      printflag=1;
                      toprint=toprint+"<BR> "+lineno+" "+funclines.get(m);
                      tname=tname.trim();
                      tainted.add(tname);
                      numlist.add(lineno);
                      alltainted.add(tname);
                      toprint=toprint+""+font1+sidea+" TAINTED "+sidea+"   "+tname+"</font>";  
                  }
                  else
                  { 
                      int s1=search5(funclines.get(m));
                      if(s1==1)
                      {
                          tyflag=1;
                          toprint=toprint+"<BR> "+lineno+" "+funclines.get(m);
                          tname=tname.trim();
                          tainted.add(tname);
                          numlist.add(lineno);
                          alltainted.add(tname);
                          toprint=toprint+""+font1+sidea+" TAINTED "+sidea+"   "+tname+"</font>";   
                      } 
                  }   
              }
              /*   pattern detection for sinks.... */
              String pat2="[. ]*([a-zA-Z0-9_]+)[(](.*)[)]";
              Pattern p2= Pattern.compile(pat2);
              Matcher m2 = p2.matcher(funclines.get(m));
              int res=0;
              if(m2.find() && tyflag==0) 
                {
                    List<String> newtainted= new LinkedList();
                    List<Integer> newtaintedp= new LinkedList();
                    String funccallwhole=m2.group();
                    String funcnameonly=m2.group(1).trim();
                    String param=m2.group(2);
                    res=search4(funcnameonly);
                    if(!param.equals("") && res>=0 && !funclines.get(fnameloc).contains(funcnameonly))
                    {
                     int ll=0,countp=0;
                     String tp="";
                     while(ll<param.length())
                     {
                         if(param.charAt(ll)==',' && param.charAt(ll)!=')')
                         {
                            tp=tp.trim();
                            int z=search_tainted(tp,tainted);
                            if(z==1)
                            {
                                newtaintedp.add(countp);
                            }
                            else
                            {
                            }
                            tp="";
                            countp++;
                         }
                         else
                         {
                             tp=tp+param.charAt(ll);
                         }
                         ll++;
                     }
                      tp=tp.trim();
                      int z=search_tainted(tp,tainted);
                      if(z==1)
                      {
                        newtaintedp.add(countp++);
                      }
                      printflag=1;
                      if(newtaintedp.size()>0)
                       toprint=toprint+"<BR><B>"+(lineno)+" "+funclines.get(m)+"</B>";
                      else
                           toprint=toprint+"<BR>"+(lineno)+" "+funclines.get(m);
                     new_taint(search2(funcnameonly),newtaintedp);  
                   }
                   else
                   {
                       int ll=0,countp=0;
                       String tp="";
                       while(ll<param.length())
                        {
                         if(param.charAt(ll)==',' && param.charAt(ll)!=')')
                         {
                            tp=tp.trim();
                            int z=search_tainted(tp,tainted);
                            if(z==1)
                            {
                                tillnow.add(tp);
                            }
                            else
                            {
                            }
                            tp="";
                             countp++;
                         }
                         else
                         {
                             tp=tp+param.charAt(ll);
                         }
                         ll++;
                     }
                     if(tillnow.size()>0)
                     {
                        toprint=toprint+"<BR>"+lineno+" <font color=\"green\">SINK : "+funclines.get(m)+"</font>";
                        toprint=toprint+"  "+font1+sidea+" TAINTED "+sidea+"   ";
                        for(int y223=0;y223<tillnow.size();y223++)
                        {
                            toprint=toprint+" ( "+tillnow.get(y223)+" ) ";
                        }
                        toprint=toprint+"</font>";
                        tillnow.clear();
                     }
                     else
                        toprint=toprint+"<BR>"+lineno+" "+funclines.get(m);
                       
                    }  
                }
              lineno++;
         }//end of main for
         toprint=toprint+"<BR>}";
         
     }
     
int search_tainted(String str,List<String> tainted)
     {
         //toprint=toprint+"<BR><BR> Searching tainted for "+str+" "+tainted.size();
         int i;
         for(i=0;i<tainted.size();i++)
         {
            //toprint=toprint+"<BR>Comparing "+str+"&"+tainted.get(i)+"#";
             if(str.contains(tainted.get(i))|| str.equals(tainted.get(i)))
                 return 1;
         }
         return 0;
     }
     
void getlococc(String x)
     {
         int i;
         for(i=0;i<namesCovered.size();i++)
         {
             if(x.equals(namesCovered.get(i)))
             {
                 loc=i;
                 occ=nameocc.get(i);
             }
         }
     }
void create_order_funcs(String mainstr,String temp1)
    {
         int i,j;
         if(!temp1.equalsIgnoreCase("null"))
         {   
            //toprint=toprint+"<BR>"+(mainstr+"..............Starting");
            callorder.add("st."+mainstr);
            String pat2="[. ]*([a-zA-Z0-9_]+)[(].*[)]";
            Pattern p2= Pattern.compile(pat2);
            Matcher m2 = p2.matcher(temp1);
            while(m2.find()) 
            {
                    String funccallwhole=m2.group();
                    String funcnameonly=m2.group(1).trim();
                    if(funcnameonly.charAt(0)=='.')
                    {
                        funcnameonly=funcnameonly.substring(1);
                    }
                    j=0;
                    if(!mainstr.equals(funcnameonly))
                    {
                        j=search1(funcnameonly); 
                    }  
                    if(j==1)
                    {
                        String cx=search2(funcnameonly);
                        create_order_funcs(funcnameonly,cx); 
                    }
                }
                callorder.add("en."+mainstr);
                
            }
         }

int search1(String str)
    {
        int i;
        for(i=0;i<funcname.size();i++)
        {
             String st=(String) funcname.get(i);
             if(st.contains(str))
             { 
                //toprint=toprint+"<BR>"+("Got a Success in search ...!!");
                return 1;
             }
        }
        return 0;
    }
int search4(String str)
    {
        int i;
        for(i=0;i<funcname.size();i++)
        {
            if(str.equals(funcname.get(i)))
            return i;
        }
        return -1;
    }
int search5(String str)
    {
        int i;
        for(i=0;i<taint1.size();i++)
        {
            if(str.contains(taint1.get(i)))
            return 1;
        }
        return 0;
    }
    
String search2(String str)
{
        int i=0;
        for(i=0;i<funcname.size();i++)
        {
            if(str.equals(funcname.get(i)))
            {
                if(i>=0 && i<=6)
                {
                    return funcorder1.get(i);
                }
                else if (i>=7)
                {
                    return funcorder2.get(i-7);
                }
            }
        }
        return "null";
}
    
void setup_intent()
{
        intentextra.add("getBooleanArrayExtra");
        intentextra.add("getBooleanExtra");
        
        intentextra.add("getByteArrayExtra");
        intentextra.add("getByteExtra");
        
        intentextra.add("getCharArrayExtra");
        intentextra.add("getCharExtra");
        
        intentextra.add("getDoubleArrayExtra");
        intentextra.add("getDoubleExtra");
        
        intentextra.add("getFloatArrayExtra");
        intentextra.add("getFloatExtra");
        
        intentextra.add("getIntArrayExtra");
        intentextra.add("getIntExtra");
        
        intentextra.add("getShortArrayExtra");
        intentextra.add("getShortExtra");
        
        intentextra.add("getStringArrayExtra");
        intentextra.add("getStringExtra");
        
        intentextra.add("getLongArrayExtra");
        intentextra.add("getLongExtra");
        
        intentextra.add("getBundleExtra");
        intentextra.add("getCharSequenceArrayListExtra");
          
        intentextra.add("getExtras");   
}
    
int search3(String x)
{
        int i;
        for(i=0;i<intentextra.size();i++)
        {
           // toprint=toprint+"<BR>"+("Comparing ...."+intentextra.get(i)+" "+x);
            if(x.contains(intentextra.get(i)))
            {
                return i;
            }
        }
        return -1;
}
    

}
