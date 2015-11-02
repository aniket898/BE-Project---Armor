package controllers

import play.api._
import play.api.mvc._
import classification.knn_entity
import classification.knn
import clustering.kmeans
import play.api.db._
import play.api.Play.current
import java.sql.DriverManager

object Application extends Controller {

  def index (body : String) = Action {
    
	  			var msg1 = ""
	  			var msg2 = ""  
	  			val knnobj = new knn  
	  			val result1 = knnobj.calculate_nearest(body);
	  			if(result1 == 1)
	  				 msg1 = "KNN : The application is possibly a malware ! Please use it at your own risk."
	  			else if(result1 == 0)
	  				 msg1 = "KNN : The application is safe to use !"
	  			val kmeansobj = new kmeans
	  			val result2 = kmeansobj.calculate_kmeans(body)
	  			if(result2 == 1)
	  				 msg2 = "KMEANS : The application is possibly a malware ! Please use it at your own risk."
	  			else if(result2 == 0)
	  				 msg2 = "KMEANS : The application is safe to use !"
	  		    Ok(msg1+msg2)
  }
}