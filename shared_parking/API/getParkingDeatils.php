<?php

include("connection.php");

$rating = 0;
$start = 0;

$pid = $_POST['pid'];
$type = $_POST['type'];
$uid = $_POST['uid'];

//$pid = "6";
//$type = "4 Wheeler";
//$uid = "1";

$sql = "SELECT v.vehicle_number, v.vehicle_type FROM tbl_user as u INNER JOIN tb_user_vehicles as v WHERE u.id = v.uid AND u.id = '$uid' AND v.vehicle_type = '$type'";
//echo $sql;
$result = mysqli_query($con,$sql);
if(mysqli_num_rows($result) > 0)
{
	while($row = mysqli_fetch_assoc($result))
		$data["data"][] = $row;
	// echo json_encode($data);	 
}
else{
	echo "failed";
}

$sql = "SELECT p.id, p.oid, p.name, p.location, p.from_time, p.to_time, s.slots, s.price FROM tbl_parking_areas as p INNER JOIN tbl_slots as s WHERE p.id = '$pid' AND s.type = '$type' AND s.pid = '$pid'";
//echo $sql;
$result = mysqli_query($con,$sql);
if(mysqli_num_rows($result) > 0)
{
	while($row = mysqli_fetch_assoc($result))
	{
		$sqll = " SELECT * FROM tbl_owners WHERE id = '$row[oid]' ";
		$res = mysqli_query($con,$sqll);
		$roww = mysqli_fetch_assoc($res);

		$sql = " SELECT * FROM tbl_rating WHERE pid = '$row[oid]' ";
		$res = mysqli_query($con,$sql);
		$count = mysqli_num_rows($res);
		while($rows = mysqli_fetch_assoc($res))
		{
			$rating = $rating + $rows['rating_value'];
		}


		if($rating != 0)
			$stars = $rating / $count;
		else 
			$stars = 0;
		
		$data["parking"][] = array('id' => $row['id'], 'name' => $row['name'], 'location' => $row['location'], 'slots' => $row['slots'], 
			'price' => $row['price'], 'from_time' => $row['from_time'], 'to_time' => $row['to_time'], 'oname' => $roww['name'], 'ophone' => $roww['phone'], 'rating' => $stars);

		$rating = 0;
		$stars = 0;
	}
	echo json_encode($data);	 
}
else{
	echo "failed";
}

?>