<?php

include("connection.php");

$lat = $_POST['lat'];
$lon = $_POST['lon'];
$vehicleType = $_POST['vehicleType'];



$sql = "SELECT p.id, p.name, p.latitude, p.longitude, SQRT( POW(69.1 * (p.latitude - '$lat'), 2) + POW(69.1 * ('$lon' - p.longitude) * COS(p.latitude / 57.3), 2)) AS distance FROM tbl_parking_areas as p INNER JOIN tbl_slots as s WHERE p.id = s.pid AND s.type = '$vehicleType'  HAVING distance < 1 ORDER BY distance DESC";

// echo $sql;

	$result = mysqli_query($con,$sql);
	if(mysqli_num_rows($result) > 0)
	{
		while($row = mysqli_fetch_assoc($result))
			$data["data"][] = $row;
		echo$lat =  json_encode($data);	 
	}
	else{
		echo "failed";
	}

?>