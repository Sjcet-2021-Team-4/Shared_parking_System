<?php

include("connection.php");

date_default_timezone_set("Asia/kolkata");

$uid = $_POST['uid']; 
$pid = $_POST['pid'];
$vehicleType = $_POST['vehicleType'];
  
// $uid = "1"; 
// $pid = "1";
// $vehicleType = "2 Wheeler";

$date = date("H:i"); 
$cdate = date("d-m-Y"); 

$sql = "SELECT * FROM tbl_slots WHERE pid = '$pid' AND type = '$vehicleType'";
$result = mysqli_query($con,$sql);
if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	$price = $row['price'];
	$slots = $row['slots'];
	$slot_id = $row['id'];
}

$sql = "SELECT * FROM tbl_booking WHERE uid = '$uid' AND pid = '$pid' AND vehicle_type = '$vehicleType' AND bdate ='$cdate' AND status = 'booked'";
// echo $sql;
$result = mysqli_query($con,$sql);
if(mysqli_num_rows($result) > 0)
{
	$row = mysqli_fetch_assoc($result);
	$bid = $row['id'];

	$sql = "SELECT * FROM tbl_parking_log WHERE uid = '$uid' AND pid = '$pid' AND vehicle_type = '$vehicleType' AND status = 'Entered' ";
	$result = mysqli_query($con,$sql);
	if(mysqli_num_rows($result) > 0)
	{
		$row = mysqli_fetch_assoc($result);
		$sql = "UPDATE tbl_parking_log SET out_time = '$date', status = 'Exited' WHERE id = '$row[id]' ";
		$in_time = $row['in_time'];
		$out_time = $date;

		$time1 = strtotime($in_time);
		$time2 = strtotime($out_time);

		$hr = (($time2 - $time1)/60)/60;
		$hr = round($hr,1);
		$hr = Ceil($hr * 2) / 2;
		if($hr < 0.5)
			$hr = 0.5;

		$rate = $hr * $price;
		$data = array('bid' => $bid, 'lid' => $row['id'], 'pid' => $pid, 'vehicle_type' => $vehicleType, 'rate' => $rate, 'time' => $hr, 'date' => $cdate);
		echo json_encode($data);
		mysqli_query($con,$sql);
	}
	else{
		
		$sql = " INSERT INTO tbl_parking_log(uid, pid, vehicle_type, in_time, status) VALUES ('$uid','$pid','$vehicleType','$date','Entered')";
		if(mysqli_query($con,$sql)){
			echo "Entered";

			$s = $slots - 1;
		    $sql = "UPDATE tbl_slots SET slots = '$s' WHERE id = '$slot_id' ";
		    mysqli_query($con,$sql);

		}
		else
			echo "Failed";
	}
}
else{
	echo "No Booking";
}

?>