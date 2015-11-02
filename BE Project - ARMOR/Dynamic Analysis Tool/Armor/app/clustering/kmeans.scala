package clustering

import classification.knn_entity
import scala.collection.mutable.ListBuffer
import classification.centroid

class kmeans {
	/**************** CORE K-MEANS ENGINE **********************/
  
  def calculate_kmeans (body : String) : Integer = {
     
    var app_classify : knn_entity = new knn_entity;
    
    // 2 clusters and their 2 centroids as k = 2
    var cluster1 = new ListBuffer[knn_entity]()
    var cluster2 = new ListBuffer[knn_entity]()
    
    var centroid1 : centroid = new centroid
    var centroid2 : centroid = new centroid
    
    //var oldcentroid1 : centroid = new centroid
    //var oldcentroid2 : centroid = new centroid
    
    var applist = new ListBuffer[knn_entity]()
    
    var cmpcluster1 = new ListBuffer[knn_entity]()
    var cmpcluster2 = new ListBuffer[knn_entity]()
    
    var cluster1_pos = 0
    var cluster1_neg = 0
    var cluster2_pos = 0
    var cluster2_neg = 0
    
    /*********BUILDING TRAINING MODEL************/
    /*val benign_file = "C:/be_project/datasets/tobeused/benign.txt"
    appset = readFile(appset,benign_file)
    println(appset)
    val malware_file = "C:/be_project/datasets/tobeused/malware.txt"
    appset = readFile(appset,malware_file)
    println(appset)*/
     
    for(line <- scala.io.Source.fromFile("public/datasets/permissions.txt").getLines())
    	{
    		var i = 0
    		var app : knn_entity = new knn_entity;
    		val chars = line.split(",");
    		app.count = chars(0).toInt
    		app.package_name = chars(1)
    		app.is_malware = chars(2).toInt
    		
    		for(i <- 0 to 143)
    		{
    		  app.attributes(i) = chars(i+3).toInt
    		}
    		
    		applist += app
    		
    		
    	}
    	//println(applist(0).attributes(10))
    
    
    //set the centroids for the 2 at random
    for(i <- 0 to 143)
    	centroid1.attributes(i) = applist(5).attributes(i)
    centroid1.is_malware = 0
    
    for(i <- 0 to 143)
    	centroid2.attributes(i) = applist(288).attributes(i)
    centroid2.is_malware = 1
    var check = 0
    
    //oldcentroid1 = centroid1
    //oldcentroid2 = centroid2
    
    //1st run
    applist.foreach{app =>
      					var i = 0
      					var distance1 : Double = 0
      					var distance2 : Double = 0
      					for(i <- 0 to 143)
      					{
      						distance1 += Math.pow((centroid1.attributes(i) - app.attributes(i)), 2)    					
      					}
      					distance1 = Math.sqrt(distance1)
      					for(i <- 0 to 143)
      					{
      						distance2 += Math.pow((centroid2.attributes(i) - app.attributes(i)), 2)    					
      					}
      					distance2 = Math.sqrt(distance2)
      					if(distance1>distance2)
      					  {
      						cluster1 += app
      						//println("App "+check+" belongs to cluster 1")
      					  }
      					else if(distance2>distance1)
      					{
      					  cluster2 += app
      					  //println("App "+check+"belongs to cluster 2")
      					}  
      				}
    
     //Update centroid of cluster 1
     centroid1.is_malware = 0
     for(i <- 0 to 143)
      {
      	centroid1.attributes(i) = 0
      }
      cluster1.foreach{app =>
        					var i = 0
        					for(i <- 0 to 143)
        					{
        						centroid1.attributes(i) += app.attributes(i)
        						//println(centroid1.attributes(i))
        					}
        					if(app.is_malware == 1)
        						cluster1_pos +=1
        					else if(app.is_malware == 0)
        					  cluster1_neg +=1
        			 }
      if(cluster1_pos > cluster1_neg)
        centroid1.is_malware = 1
      else(cluster1_pos < cluster1_neg)
          centroid1.is_malware = 0
      
      for(i <- 0 to 143)
      {
        centroid1.attributes(i) = centroid1.attributes(i) / cluster1.size  
      }
      
      
      //Update centroid of cluster 2
      centroid2.is_malware = 0
     for(i <- 0 to 143)
      {
      	centroid2.attributes(i) = 0
      }
      cluster2.foreach{app =>
        					var i = 0
        					for(i <- 0 to 143)
        					{
        						centroid2.attributes(i) += app.attributes(i)
        						//println(centroid1.attributes(i))
        					}
        					if(app.is_malware == 1)
        						cluster2_pos +=1
        					else if(app.is_malware == 0)
        					  cluster2_neg +=1
        			 }
      if(cluster2_pos > cluster2_neg)
        centroid2.is_malware = 1
      else(cluster2_pos < cluster2_neg)
          centroid2.is_malware = 0
      
      for(i <- 0 to 143)
      {
        centroid2.attributes(i) = centroid2.attributes(i) / cluster2.size  
      }      
      
      
      /*val temp1: Boolean = (oldcentroid1==centroid1)
      val temp2: Boolean = (oldcentroid2==centroid2)
      if(temp1 && temp2)
      println("centroids remains same ??????????????")
      */
      
      //Cluster process starts till the elements in both the clusters remain the same.
    while(cluster1!=cmpcluster1 && cluster2!=cmpcluster2)
    {
      check += 1
      println(check)
      cmpcluster1 = cluster1
      cmpcluster2 = cluster2
      
      cluster1.foreach{app =>
      					var i = 0
      					var distance1 : Double = 0
      					var distance2 : Double = 0
      					for(i <- 0 to 143)
      					{
      						distance1 += Math.pow((centroid1.attributes(i) - app.attributes(i)), 2)    					
      					}
      					distance1 = Math.sqrt(distance1)
      					for(i <- 0 to 143)
      					{
      						distance2 += Math.pow((centroid2.attributes(i) - app.attributes(i)), 2)    					
      					}
      					distance2 = Math.sqrt(distance2)
      					if(distance2>distance1)
      					{
      					  cluster2 += app
      					  cluster1 -= app
      					  //println("App "+check+"belongs to cluster 2")
      					}  
      				}
      cluster2.foreach{app =>
      					var i = 0
      					var distance1 : Double = 0
      					var distance2 : Double = 0
      					for(i <- 0 to 143)
      					{
      						distance1 += Math.pow((centroid1.attributes(i) - app.attributes(i)), 2)    					
      					}
      					distance1 = Math.sqrt(distance1)
      					for(i <- 0 to 143)
      					{
      						distance2 += Math.pow((centroid2.attributes(i) - app.attributes(i)), 2)    					
      					}
      					distance2 = Math.sqrt(distance2)
      					if(distance1>distance2)
      					  {
      						cluster1 += app
      						cluster2 -= app
      						//println("App "+check+" belongs to cluster 1")
      					  }
      				}
    
    
      //Update centroid of cluster 1
     cluster1_pos = 0
     cluster1_neg = 0
      centroid1.is_malware = 0
     for(i <- 0 to 143)
      {
      	centroid1.attributes(i) = 0
      }
      cluster1.foreach{app =>
        					var i = 0
        					for(i <- 0 to 143)
        					{
        						centroid1.attributes(i) += app.attributes(i)
        						//println(centroid1.attributes(i))
        					}
        					if(app.is_malware == 1)
        						cluster1_pos +=1
        					else if(app.is_malware == 0)
        					  cluster1_neg +=1
        			 }
      if(cluster1_pos > cluster1_neg)
        centroid1.is_malware = 1
      else(cluster1_pos < cluster1_neg)
          centroid1.is_malware = 0
      
      for(i <- 0 to 143)
      {
        centroid1.attributes(i) = centroid1.attributes(i) / cluster1.size  
      }
      
      
      //Update centroid of cluster 2
      cluster2_pos = 0
      cluster2_neg = 0
      centroid2.is_malware = 0
     for(i <- 0 to 143)
      {
      	centroid2.attributes(i) = 0
      }
      cluster2.foreach{app =>
        					var i = 0
        					for(i <- 0 to 143)
        					{
        						centroid2.attributes(i) += app.attributes(i)
        						//println(centroid1.attributes(i))
        					}
        					if(app.is_malware == 1)
        						cluster2_pos +=1
        					else if(app.is_malware == 0)
        					  cluster2_neg +=1
        			 }
      if(cluster2_pos > cluster2_neg)
        centroid2.is_malware = 1
      else(cluster2_pos < cluster2_neg)
          centroid2.is_malware = 0
      
      for(i <- 0 to 143)
      {
        centroid2.attributes(i) = centroid2.attributes(i) / cluster2.size  
      }      
      
      
      if(cluster1==cmpcluster1)
        println("clusters 1 are the same")
      if(cluster2==cmpcluster2)
        println("clusters2 2 are the same")
    }
    
    
    println(cluster1.size)
    println(cluster2.size)
    
    
    
    
    
    /***********CLASSIFICATION************/
    
    		var i = 0;
    		val chars = body.split(",");
    		app_classify.count = chars(0).toInt
    		app_classify.package_name = chars(1)
    		app_classify.is_malware = chars(2).toInt
    		for(i <- 0 to 143 )
    		{
    		  app_classify.attributes(i) = chars(i+3).toInt
    		}		
    	
    var classify_distance1 : Double = 0
    var classify_distance2 : Double = 0
    var classify_ismalware = 0
    var positive = 0 //positively identified as malware
    var negative = 0
    for(i <- 0 to 143)
      					{
      						classify_distance1 += Math.pow((centroid1.attributes(i) - app_classify.attributes(i)), 2)    					
      					}
      					classify_distance1 = Math.sqrt(classify_distance1)
      					for(i <- 0 to 143)
      					{
      						classify_distance2 += Math.pow((centroid2.attributes(i) - app_classify.attributes(i)), 2)    					
      					}
      					classify_distance2 = Math.sqrt(classify_distance2)
      					if(classify_distance1>classify_distance2)
      					{
      					  cluster1.foreach{app =>
      					  						if(app.is_malware == 0)
      					  						  negative += 1
      					  						else if(app.is_malware == 1)
      					  						  positive += 1
      					  						if(negative > positive)
      					  						  classify_ismalware = 0
      					  						else
      					  						  classify_ismalware = 1
      					  				}
      					}
      					else if(classify_distance1<classify_distance2)
      					{
      					  cluster2.foreach{app =>
      					  						if(app.is_malware == 0)
      					  						  negative += 1
      					  						else if(app.is_malware == 1)
      					  						  positive += 1
      					  						if(negative > positive)
      					  						  classify_ismalware = 0
      					  						else
      					  						  classify_ismalware = 1
      					  				}
      					}
      return classify_ismalware					
   
  	}
}