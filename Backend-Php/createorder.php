<?php
    if (isset($_POST['username']) && isset($_POST['city']) && isset($_POST['longitude']) && isset($_POST['latitude']) && isset($_POST['foodtype']) && isset($_POST['timetoorder']) && isset($_POST['datetoorder']) && isset($_POST['phonenumber']))
    {
		//Post Variables
		$postUsername 		= $_POST["username"];
		$postCity			= $_POST["city"];
		$postLongitude		= $_POST["longitude"];
		$postLatitude		= $_POST["latitude"];
		$postFoodType		= $_POST["foodtype"];
		$postTimeToOrder	= $_POST["timetoorder"];
		$postDateToOrder	= $_POST["datetoorder"];
		$postPhoneNumber	= $_POST["phonenumber"];
		
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
		//Sanitize data from user
		$postUsername 		= $conn->real_escape_string($postUsername);
		$postCity			= $conn->real_escape_string($postCity);
		$postLongitude		= $conn->real_escape_string($postLongitude);
		$postLatitude		= $conn->real_escape_string($postLatitude);
		$postFoodType		= $conn->real_escape_string($postFoodType);
		$postTimeToOrder	= $conn->real_escape_string($postTimeToOrder);
		$postDateToOrder	= $conn->real_escape_string($postDateToOrder);
		$postPhoneNumber	= $conn->real_escape_string($postPhoneNumber);
		
		//1st Query - Delete old orders based off usernames
		$sql1 = 'DELETE FROM Orders WHERE username="'.$postUsername.'"';
		// Execute 1st Query
		$result1 = $conn->query($sql1);
		
		
		//2nd Query - Delete old orders based off date, 3days prior is old.
		$oldDate = $postDateToOrder - 3;
		
		$sql2 = 'DELETE FROM Orders WHERE datetoorder <="'.$oldDate.'"';
		//Execute 2nd Query
		$result2 = $conn->query($sql2);
		
		
		//3rd Query - Create order
		$sql3 = 'INSERT INTO Orders (Username, City, Longitude, Latitude, FoodType, TimeToOrder, DateToOrder, PhoneNumber) VALUES("'.$postUsername.'","'.$postCity.'","'.$postLongitude.'","'.$postLatitude.'","'.$postFoodType.'","'.$postTimeToOrder.'","'.$postDateToOrder.'","'.$postPhoneNumber.'")';
	
		//Execute Third Query
		$result3 = $conn->query($sql3);

		$json = array();
		$json['message'] = "Announcement Created.";
		echo json_encode($json);
		$conn->close();

    }

?>