<?php
    if (isset($_POST['username']) && isset($_POST['city']) && isset($_POST['longitude']) && isset($_POST['latitude']) && isset($_POST['datetoorder']))
    {
		//Post Variables
		$postUsername 		= $_POST["username"];
		$postCity			= $_POST["city"];
		$postLongitude		= $_POST["longitude"];
		$postLatitude		= $_POST["latitude"];
		$postDateToOrder	= $_POST["datetoorder"];
		
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
		//Sanitize Data from user
		$postUsername 		= $conn->real_escape_string($postUsername);
		$postCity			= $conn->real_escape_string($postCity);
		$postLongitude		= $conn->real_escape_string($postLongitude);
		$postLatitude		= $conn->real_escape_string($postLatitude);
		$postDateToOrder	= $conn->real_escape_string($postDateToOrder);
		
		//1st Query - Find all orders in the same city
		$sql1 = 'SELECT * FROM Orders WHERE city="'.$postCity.'"';
		// Execute 1st Query
		$result1 = $conn->query($sql1);
		//If no results, no orders
		if ($result1->num_rows == 0) {
				$json = array();
				$json['message'] = "No Orders.";
				echo json_encode($json);
		} 
		
		//If there is results, compute and return
		$validOrders = 0;
		if($result1->num_rows >= 1) {
			while($row = $result1->fetch_array()){
				$rows[] = $row;
			}

			foreach($rows as $row){
				$distance = distance($postLatitude, $postLongitude, $row['Latitude'], $row['Longitude']);
				//only send within 3kms, not the same user, date given to order -1, to find results for yesterday and on, handles midnight case.
				if($distance <= 3 && $postUsername != $row['Username'] && ($postDateToOrder-1) <= $row['DateToOrder']){
					$validOrders = $validOrders + 1;
					$json = array();
					$json['message'] = "Found Order.";
					$json['username'] = $row['Username'];
					$json['datetoorder'] = $row['DateToOrder'];
					$json['timetoorder'] = $row['TimeToOrder'];
					$json['foodtype'] = $row['FoodType'];
					$json['phonenumber'] = $row['PhoneNumber'];
					$json['distance'] = $distance;
					echo json_encode($json);
				}
				

			}
		}
		//If no orders matched dispite finding results of city in DB.
		if($validOrders == 0 ){
			$json = array();
			$json['message'] = "No Orders.";
			echo json_encode($json);
		}

		
		
    }
	// Return the distance between two longtitude and latitude points in Kms.
	function distance($lat1, $lon1, $lat2, $lon2) {

		$theta = $lon1 - $lon2;
		$dist = sin(deg2rad($lat1)) * sin(deg2rad($lat2)) +  cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * cos(deg2rad($theta));
		$dist = acos($dist);
		$dist = rad2deg($dist);
		$miles = $dist * 60 * 1.1515;
		return ($miles * 1.609344);
	}
?>