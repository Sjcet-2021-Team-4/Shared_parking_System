<?php

include("connection.php");

$phno = $_POST['phone'];
$pwd = $_POST['pwd'];

$sql = "SELECT * FROM tbl_user WHERE phone='$phno' && password='$pwd'";

$result = mysqli_query($con,$sql);

if(mysqli_num_rows($result) > 0)
{
	while($row = mysqli_fetch_assoc($result))
	{
		$data['data'][] = $row;
	}
	echo json_encode($data);
}
else{
echo "Failed";	
}


?>