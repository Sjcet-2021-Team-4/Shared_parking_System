<?php

include("connection.php");

$fname = $_POST['fname']; 
$phone = $_POST['phone'];
$email = $_POST['email'];
$aadharNo = $_POST['aadharNo'];
$vno = $_POST['vno'];
$vtype = $_POST['vtype'];
$password = $_POST['password'];
 
 
$sql = " INSERT INTO tbl_user(name, phone, email, aadhar_no, password) VALUES ('$fname','$phone','$email','$aadharNo','$password') ";

if(mysqli_query($con,$sql))
{
	echo "success";
	$id = mysqli_insert_id($con);
	$sql = "INSERT INTO tb_user_vehicles(uid, vehicle_number, vehicle_type) VALUES ('$id','$vno','$vtype')";
	mysqli_query($con,$sql);
}
else
	echo "Failed";
 
?>