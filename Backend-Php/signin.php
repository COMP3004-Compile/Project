<?php
    if (isset($_POST['username']) && isset($_POST['password']))
    {
		//Post Variables
		$postUsername 	= $_POST["username"];
		$postPassword	= $_POST["password"];
		
		//Database Variables
		$servername = "testdbinstance.ctau9v0c7d2t.us-east-1.rds.amazonaws.com";
		$username 	= "testuser";
		$password 	= "testuser1";
		$dbname 	= "FeedMeFeedYou";
		
		// Create connection
		$conn = new mysqli($servername, $username, $password, $dbname);
		// Check connection
		if ($conn->connect_error) {
			die("Connection failed: " . $conn->connect_error);
		}
		//Sanitize Data from User
		$postUsername   = $conn->real_escape_string($postUsername);
		$postPassword   = $conn->real_escape_string($postPassword);
		
		//First Query: Check if username exists
		$sql = 'SELECT username FROM Users WHERE username="'.$postUsername.'" AND password="'.$postPassword.'"';
	
		//Execute First Query
		$result = $conn->query($sql);
		
		//If no results, invalid username/password
		if ($result->num_rows == 0) {
			$json = array();
			$json['message'] = "Login Failed.";
			echo json_encode($json);
		} 
		
		//If there is results, username/password correct
		else {
			$json = array();
			$json['message'] = "Login Successful.";
			echo json_encode($json);
		}
		
$conn->close();

    }

?>