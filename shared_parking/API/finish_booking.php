<?php

include("connection.php");

date_default_timezone_set("Asia/kolkata");

$bid = $_POST['bid']; 
// $uid = $_POST['uid'];
  
$ctime = date("H:i"); 
$cdate = date("d-m-Y"); 

$sql = "SELECT * FROM tbl_booking WHERE id = '$bid'";
$result = mysqli_query($con,$sql);
if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	$pid = $row['pid'];
	$btime = $row['btime'];
	$vehicleType = $row['vehicle_type'];
}

$sql = "SELECT * FROM tbl_slots WHERE pid = '$pid' AND type = '$vehicleType'";
$result = mysqli_query($con,$sql);
if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	$price = $row['price'];
	$slots = $row['slots'];
	$slot_id = $row['id'];
}

/*$s = $slots + 1;
$sql = "UPDATE tbl_slots SET slots = '$s' WHERE id = '$slot_id' ";
mysqli_query($con,$sql);*/

$time1 = strtotime($btime);
$time2 = strtotime($ctime);

$hr = (($time2 - $time1)/60)/60;
$hr = round($hr,1);
$hr = Ceil($hr * 2) / 2;
if($hr < 0.5)
	$hr = 0.5;

$rate = $hr * $price;
// echo $rate."  ".$hr." ".$price." ".$time1." ".$time2." ".$btime." \n";
$data = array('bid' => $bid, 'pid' => $pid, 'vehicle_type' => $vehicleType, 'rate' => $rate, 'time' => $hr, 'date' => $cdate);
echo json_encode($data);


?>