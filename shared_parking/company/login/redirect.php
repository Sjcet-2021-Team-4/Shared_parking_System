<?php
session_start();
include('../db/connectionI.php');
//$db_name="music"; // Database name 
//$tbl_name="employee"; // Table name 

// Connect to server and select databse.


// username and password sent from form 
$myusername=$_POST['UserName']; 
$mypassword=$_POST['Password']; 


if(isset($_POST['login']))
{

$sql = mysqli_query($con,"SELECT * FROM tbl_owners where username='$myusername' AND password='$mypassword'");
$num=mysqli_num_rows($sql);
if($num!=0)
{
	$row=mysqli_fetch_array($sql);
	
	$_SESSION['id']=$row['id'];		
	$_SESSION['type']="company";
	$_SESSION['login']="1";
	header("location:../dashboard/dashboard.php");
	
}
else
{

echo "<script language=\"JavaScript\">\n"; 
echo "alert('Username or Password was incorrect!');\n";

echo "window.location='login.php'";

echo "</script>";
 //header("location: login.php?a-1");
}


}

?>
 
 



