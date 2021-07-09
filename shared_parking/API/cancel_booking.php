<?php

include("connection.php");

$bid = $_POST['bid'];
  
$sql = " DELETE FROM tbl_booking WHERE id = '$bid' ";

if(mysqli_query($con,$sql))
	echo "success";
else
	echo "Failed";
 
?>