<?php

include("connection.php");

$uid = $_POST['uid']; 
$vnumber = $_POST['vnumber'];
$vtype = $_POST['vtype']; 

$sql = " INSERT INTO tb_user_vehicles(uid, vehicle_number, vehicle_type) VALUES ('$uid','$vnumber','$vtype')";
//echo $sql;
if(mysqli_query($con,$sql))
{
	echo "success"; 
}
else
	echo "Failed";
 
?>