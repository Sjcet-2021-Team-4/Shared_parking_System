<?php
session_start();
/*session_unset();
session_destroy();*/

if($_SESSION['type']=="")
{
header("location:../login/login.php");
}
$title="";
?><!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="assets/img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title></title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="../assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="../assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="../assets/css/demo.css" rel="stylesheet" />


    <!--     Fonts and icons     -->
    <link href="../common/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="../assets/css/pe-icon-7-stroke.css" rel="stylesheet" />

</head>
<body>

<div class="wrapper">

<div class="sidebar" data-color="yh">

    	<div class="sidebar-wrapper">

            <div class="logo">
			<h3 style="color:#000000">NearByMe<h3>		
            
               <a href="../dashboard/dashboard.php"><img src="../common/core-img/logo1.png" width="250" height="auto"></a> 
                 
            </div>

            <ul class="nav">
                
                
                <li >
                    <a href="../dashboard/dashboard.php">
                        <i class="pe-7s-home"></i>
                        <p>Home</p>
                    </a>
                </li>
                
              <!--   <li>
                    <a href="../tbl_customer/select.php">
                        <i class="pe-7s-angle-right-circle"></i>
                        <p>CUSTOMER</p>
                    </a>
                </li>
                
                <li>
                    <a href="../tbl_vendor/select.php">
                        <i class="pe-7s-angle-right-circle"></i>
                        <p>VENDOR</p>
                    </a>
                </li>
                
                <li>
                    <a href="../tbl_item/select.php">
                        <i class="pe-7s-angle-right-circle"></i>
                        <p>ITEMS</p>
                    </a>
                </li>
                
                <li>
                    <a href="../tbl_order/select.php">
                        <i class="pe-7s-angle-right-circle"></i>
                        <p>ORDERS</p>
                    </a>
                </li>
                
                </li> -->


                 <li>
                    <a href="../login/login.php?status=logout">
                        <i class="pe-7s-bell"></i>
                        <p>Log Out</p>
                    </a>
                </li>
               				
            </ul>
    	</div>
    </div>
    <div class="main-panel" style=" background-image: url(bg4.jpg);   background-size: cover;">

    <nav class="">
            <div class="container-fluid">
                <div class="navbar-header">
                           <div style="padding-left:50px;padding-right:50px"> 
							<h3 style="color:#FFFFFF">Welcome Landowner<h3>						   
<!--  <font face="Trebuchet MS, Arial, Helvetica, sans-serif" size="+3" color="#fbb808">  <font color="#000000">Welcome </font>You <font color="#000000">To</font> Grocery Connection<font color="#000000"></font></font>
</marquee>-->
                  
                </div>
                
            </div>
        </nav>