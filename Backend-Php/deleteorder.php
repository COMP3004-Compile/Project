<?php
    if (isset($_POST['username']))
    {
		//Post Variables
		$postUsername 		= $_POST["username"];
		
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
		//Sanitize user data
		
		$postUsername = $conn->real_escape_string($postUsername);
		//1st Query - Delete orders based off usernames
		$sql1 = 'DELETE FROM Orders WHERE username="'.$postUsername.'"';
		// Execute 1st Query
		$result1 = $conn->query($sql1);

		$json = array();
		$json['message'] = "Order Deleted.";
		echo json_encode($json);
		$conn->close();

    }

?>