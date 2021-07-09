<?php

include("connection.php");

$pid = $_POST['pid'];
$user_id = $_POST['user_id'];
$review = $_POST['review'];
$rating_value = $_POST['rating_value'];
 
$sql = " INSERT INTO tbl_rating(pid, user_id, rating_value, review) VALUES ('$pid','$user_id','$rating_value', '$review') ";
 
if(mysqli_query($con,$sql))
	echo "success";
else
	echo "Failed";

?>