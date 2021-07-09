<?php

include("connection.php");

$uid = $_POST['uid'];
//$uid = "13";

date_default_timezone_set("Asia/kolkata");
$d = date("d-m-Y"); 
 
$sql = "SELECT b.id, b.uid, b.pid, p.name, b.vehicle_type, b.vehicle_number, b.bdate, b.bduration, b.amount FROM tbl_booking as b INNER JOIN tbl_parking_areas as p WHERE b.pid = p.id AND b.uid = '$uid' AND b.status = 'booked' AND b.bdate = '$d'";
//echo $sql;
$res = mysqli_query($con,$sql);
if(mysqli_num_rows($res) > 0)
{
	while($row = mysqli_fetch_assoc($res))
	{
		$data["data"][] = $row; 
	}
	echo json_encode($data);
}
else
	echo "Failed";
 
?>