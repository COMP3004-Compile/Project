<?php
    if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['email']) )
    {
		//Post Variables
		$postUsername 	= $_POST["username"];
		$postPassword	= $_POST["password"];
		$postEmail		= $_POST["email"];
		
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
		$postUsername 	= $conn->real_escape_string($postUsername);
		$postPassword	= $conn->real_escape_string($postPassword);
		$postEmail		= $conn->real_escape_string($postEmail);
		
		//First Query: Check if username exists
		$sql = 'SELECT username FROM Users WHERE username="'.$postUsername.'"';
		
		//Execute First Query
		$result = $conn->query($sql);
		
		//If no results, username is not taken
		if ($result->num_rows == 0) {
			//2nd Query, insert the given post information into the database
			$sql2 = 'INSERT INTO Users (Username, Password, Email) VALUES("'.$postUsername.'","'.$postPassword.'","'.$postEmail.'")';
			//Execute 2nd Query
			$result2 = $conn->query($sql2);
			//Return result to user
			$json = array();
			$json['message'] = "Signup Successful.";
			echo json_encode($json);
		} 
		
		//If there is results, username taken
		else {
			//Return result to user
			$json = array();
			$json['message'] = "Signup Failed. Username Taken.";
			echo json_encode($json);
		}
		
$conn->close();

    }

?>