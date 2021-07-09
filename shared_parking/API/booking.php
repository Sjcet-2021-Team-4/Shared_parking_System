<?php

include("connection.php");

date_default_timezone_set("Asia/kolkata");
$cdate = date("d-m-Y"); 

$uid = $_POST['uid']; 
$pid = $_POST['pid'];
$vehicleType = $_POST['vehicleType'];
$vehicleNumber = $_POST['vehicleNumber'];
$bdate = $_POST['bdate']; 
$btime = $_POST['btime']; 
$duration = $_POST['duration']; 
$amount = round($_POST['amount'], 2);  

$d=$uid."-".$pid."-".$bdate.$pid."-".$amount;
$userinfo = md5($d);
$date = date('Y-m-d');
include("simplechain.php");
$file="block.txt";
$trans=fopen($file,"r");
$tran=fread($trans,filesize($file));
fclose($trans);

$sel = "SELECT * FROM tbl_slots WHERE pid = '$pid' AND type = '$vehicleType' ";
$res = mysqli_query($con,$sel);
$row = mysqli_fetch_assoc($res);
$count = $row['slots'] - 1;
// echo $count;

$sql = " INSERT INTO tbl_booking(uid, pid, vehicle_type, vehicle_number, bdate, btime, bduration, amount, block_key, status) VALUES 
         ('$uid','$pid','$vehicleType','$vehicleNumber','$cdate','$btime','$duration','$amount','$tran','booked') ";

if(mysqli_query($con,$sql))
   {
        $update = "UPDATE tbl_slots SET slots = '$count' WHERE pid = '$pid' AND type = '$vehicleType'";
        mysqli_query($con,$update);
		echo "success";
   }
else
   echo "Failed";


 
/*$sql = " INSERT INTO tbl_booking(uid, pid, vehicle_type, vehicle_number, bdate, bduration, amount, status) VALUES ('$uid','$pid','$vehicleType','$vehicleNumber','$cdate','$duration','$amount', 'booked') ";

if(mysqli_query($con,$sql))
	echo "success";
else
	echo "Failed";*/
 
?>