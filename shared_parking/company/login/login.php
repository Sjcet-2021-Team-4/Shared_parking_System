<!doctype html>
<?php

if(isset($_REQUEST['status']))
{
  $status=$_REQUEST['status'];
  if ($status == "logout")
  {
      session_start();
      session_unset();
      session_destroy();
      header("location:../login/login.php");
      echo "string";
  }
}

?>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="icon" type="image/png" href="assets/img/favicon.ico">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>NearByMe</title>

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
<style>
@media (max-width:767px){
	.mhide
	{
	display:none;	
	}
}

</style>
<body>

<div class="wrapper">

<div class="col-md-6 mhide" data-color="blue" style="background:#fff;height:100%;">
<center> <br><br>
<h1>NearByMe</h1><br><br>
<img src="logo1.jpg" height="450px">
</center>
    	
    </div>
    
    
        <div class="col-md-6" style="background:#ccc;height:100%;">
        
        <br><br><br><br><br><br><br><br><br>
        
        
        <form action="redirect.php" method="post">
        
        <div class="col-md-1 mhide" style="height:100%;"></div>
        
         <div class="col-md-10" style="background:#FFF;border:1px solid #ccc;opacity:0.95;">
         <h2>Landowner Login</h2>
        
         <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Username</label>
                                                <input type="text" class="form-control" name="UserName" placeholder="Enter Your UserName" required />
                                            </div>
        </div>
    
    
        <div class="col-md-12">
                                            <div class="form-group">
                                                <label for="exampleInputEmail1">Password</label>
                                                <input type="password" class="form-control" placeholder="Enter your Password" name="Password" required />
                                            </div>
        </div>
        
        
            <div class="col-md-12">
                                            <div class="form-group">
                                            
                                            
                                             <div class='col-md-6' style="float:right;">             
											 <div class='form-group'>
											<input type='submit' value='Sign In' name='login' class='form-control btn btn-primary' style="background:#24a0ed;color:#fff;">
                                            
                                            
                                            <br>
        <br>
      <br>

                                            
                                            </div></div>
                                            
                                         
                                            
                                           
                                            </div>
        </div>

</form>
        <br>
        <br>
      <br>
    </div>
    
    
</div>


</body>

    <!--   Core JS Files   -->
    <script src="../assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="../assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="../assets/js/bootstrap-checkbox-radio-switch.js"></script>

	<!--  Charts Plugin -->
	<script src="../assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="../assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>

    <!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
	<script src="../assets/js/light-bootstrap-dashboard.js"></script>

	<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
	<script src="../assets/js/demo.js"></script>

	

</html>


