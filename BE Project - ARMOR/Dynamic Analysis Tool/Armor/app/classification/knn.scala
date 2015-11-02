package classification

import scala.collection.mutable.ListBuffer
import java.math
import java.util.List
import java.util.ArrayList
import scala.collection.mutable.ListBuffer
import scala.collection.immutable.Map
import java.util.Scanner
import java.io.File
import scala.io.Source
import java.io.FileWriter
import play.api.db.DB
import java.sql.DriverManager
import sun.jdbc.odbc.ee.DataSource
import play.api.Play.current
class knn {

  def calculate_nearest(body : String): Integer = {
    
    var app_classify : knn_entity = new knn_entity;
    var apps = new ListBuffer[knn_entity]();
    var distance_list = new ListBuffer[Double]() 
    var distance_map = Map[Integer,Double]()
    var kset : Set[Integer] = Set()
    var mal_cnt = 0
    var ben_cnt = 0
    val ds = DB.getDataSource("default")  
    val connection = DB.getConnection()

    		var i = 0;
    		val chars = body.split(",");
    		app_classify.count = chars(0).toInt
    		app_classify.package_name = chars(1)
    		app_classify.is_malware = chars(2).toInt
    		for(i <- 0 to 143 )
    		{
    		  app_classify.attributes(i) = chars(i+3).toInt
    		}		
    		
    val statement1 = connection.createStatement()
	val resultSet2 = statement1.executeQuery("SELECT id FROM malwaredb.check WHERE pkg_name = '"+chars(1)+"';")
	if(resultSet2.next())
		{
			println("found entry in malware db")
			connection.close()
			return 1
		}	
    for(line <- scala.io.Source.fromFile("public/datasets/permissions.txt").getLines())
    	{
    		var i = 0
    		//var k = 0;
    		var app : knn_entity = new knn_entity;
    		var distance : Double = 0;
    		val chars = line.split(",");
    		var temp : knn_entity = new knn_entity()
    		
    		temp.count = chars(0).toInt
    		//println(temp.count)
    		app.count = chars(0).toInt
    		
    		temp.package_name = chars(1)
    		app.package_name = chars(1)
    		
    		temp.is_malware = chars(2).toInt
    		app.is_malware = chars(2).toInt
    		
    		/*if(app.is_malware==1)
    		{
    		val statementtemp = connection.createStatement()
    		val resultSettemp = statementtemp.executeUpdate("INSERT INTO `malwaredb`.`check` (`id`, `pkg_name`) VALUES (NULL, '"+chars(1)+"');")
    		}*/
    		
    		for(i <- 0 to 143)
    		{
    		  //println(i+"  "+k);
    		  //k += 1
    		  temp.attributes(i) = chars(i+3).toInt
    		  app.attributes(i) = chars(i+3).toInt
    		}
    		
    		for(i <- 0 to 143)
    		{
    			distance += Math.pow((app_classify.attributes(i) - temp.attributes(i)), 2)    					
       		}
    		distance = Math.sqrt(distance)
    		distance_map += temp.count -> distance
    		apps += app
    		//distance_list += distance
    		
    	}	
    	
    val list1 = distance_map.toList.sortBy(_._2)
    
    //Declare value of k factor
    var k = 5
    
    /*list1.foreach{ case (c, n) =>
        				println(n+" "+c)
      }*/
    var l = 0;
    while(k!=0)
    {
      kset += list1(l)._1
      //println(list1(l)._1)
      l += 1
      k = k - 1
    }
    
    //println(kset)
    kset.foreach{kval =>
      var cnt = 0
      while(apps(cnt).count != kval)
       {
         cnt += 1
       }
      if(apps(cnt).is_malware==1)
        mal_cnt += 1
      else
    	ben_cnt += 1  
    }
    val fw = new FileWriter("public/datasets/permissions.txt", true)
    var strwrite = ""
    
  
    if(mal_cnt>ben_cnt)
    	{
    		strwrite = "\n" + chars(0)+","+chars(1)+",1"
    		for(i <- 0 to 143)
    		  strwrite += ","+chars(i+3)
    		fw.write(strwrite)
    		fw.close()
    		val statement2 = connection.createStatement()
			val resultSet = statement2.executeUpdate("INSERT INTO `malwaredb`.`check` (`id`, `pkg_name`) VALUES (NULL, '"+chars(1)+"');")
			connection.close()
    		return 1
      
    	}
    else
    {
      strwrite = "\n"+chars(0)+","+chars(1)+",0"
      for(i <- 0 to 143)
    	 strwrite += ","+chars(i+3)
      fw.write(strwrite)
      fw.close()
      return 0
    }
  }

}