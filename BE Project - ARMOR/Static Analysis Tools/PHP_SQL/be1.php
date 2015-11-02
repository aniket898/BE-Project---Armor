<?php 

    #Ensure that the client has provided a value for "search_nameToSearch" 
   if (isset($_POST["name"]) && $_POST["name"] != "")
   { 
        #Setup variables 
        $search_name = $_POST["name"]; 
        #Connect to Database 
        $con = mysqli_connect("localhost","root","sushah", "project1"); 
        #Check connection 
        if (mysqli_connect_errno()) 
		{ 
            echo 'Database connection error: ' . mysqli_connect_error(); 
            exit(); 
        } 
        #Escape special characters to avoid SQL injection attacks 
        $search_name = mysqli_real_escape_string($con, $search_name); 
        $query="SELECT * FROM permissiondb WHERE name = '$search_name'";
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
            ); 
        #Output the JSON data 
        echo json_encode($result_data);  
    }
	else
	{ 
        echo "Could not complete query. Missing parameter";  
    } 
?>