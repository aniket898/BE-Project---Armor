<?php 

    #Ensure that the client has provided a value for "search_idToSearch" 
   if (isset($_POST["id"]) && $_POST["id"] != "")
   { 
        #Setup variables 
        $search_id = $_POST["id"]; 
        #Connect to Database 
        $con = mysqli_connect("localhost","root","sushah", "project1"); 
        #Check connection 
        if (mysqli_connect_errno()) 
		{ 
            echo 'Database connection error: ' . mysqli_connect_error(); 
            exit(); 
        } 
        #Escape special characters to avoid SQL injection attacks 
        $search_id = mysqli_real_escape_string($con, $search_id); 
        $query="SELECT * FROM permissiondb WHERE id = '$search_id'";
		#print $query;
        #Query the database to get the user details. 
        $userdetails = mysqli_query($con,$query ); 
        #If no data was returned, check for any SQL errors 
        if (!$userdetails) 
		{ 
            echo 'Could not run query: ' . mysqli_error($con); 
            exit; 
        } 
        #Get the first row of the results 
        $row = mysqli_fetch_row($userdetails); 
        #Build the result array (Assign keys to the values) 
		$cid=$row[0]; 
        $result_data = array( 
			'id' => $row[0],
            'name' => $row[1], 
			'description' => $row[2],
            );
		  
		#print "got result " . $row[0] ."  ". $row[1];
        #Output the JSON data 
        echo json_encode($result_data);  
    }
	else
	{ 
        echo "Could not complete query. Missing parameter";  
    } 
?>