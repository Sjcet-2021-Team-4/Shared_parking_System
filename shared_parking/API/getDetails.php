<?php

include("connection.php");

$uid = $_POST['uid'];
//$type = $_POST['type'];
//$uid = "1";
 
 
$sql = "SELECT * FROM tbl_user WHERE id = '$uid'";
$res = mysqli_query($con,$sql);
$row = mysqli_fetch_assoc($res);
$data["data"][] = $row;

//$sql = "SELECT * FROM tb_user_vehicles WHERE uid = '$uid' AND vehicle_type = '$type' ";
$sql = "SELECT * FROM tb_user_vehicles WHERE uid = '$uid' ";

$res = mysqli_query($con,$sql);
while($row = mysqli_fetch_assoc($res))
	$data["vehicles"][] = $row;

echo json_encode($data);
 
  
?>