<?php
error_reporting(0);
$status=$_REQUEST['status'];
if ($status == "logout")
{
    session_start();
    session_unset();
    session_destroy();
	header("location:../login/login.php");
}
?>
<?php
include("../common/menu.php");
	
?>


    <style>
#right
{
	
float:right;	
color:#333;
font-size:12px;
}
</style>

<style>
#right
{
	
float:right;	
color:#333;
font-size:12px;
}
.userd
{
color:#333;	
}
.red
{
background:#FFECF4;
padding:10px;	
}


#right
{
	
float:right;	
color:#333;
font-size:12px;
}
.userd
{
color:#333;	
}
.red
{
background:#F36;
padding:10px;	
}
.table
{
margin-bottom:10px;
background:#E6F4FF;	
}
.sep
{
height:30px;
background:#666;	
}
</style>
       


        <div class="content" >
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                                             
                            </div>
                            <div class="content all-icons">
                                <div class="row" style=" /*background-image: url(boutique.jpg);*/ ">
                                                            
                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../tbl_user/select.php">    <div class="font-icon-detail"><i class="pe-7s-users"></i>
                                      <input type="text" value="CUSTOMER">
                                    </div></a>
                                  </div>
                                        

                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../tbl_owner/select.php">    <div class="font-icon-detail"><i class="pe-7s-ticket"></i>
                                      <input type="text" value="Landowners">
                                    </div></a>
                                  </div>

                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../tbl_parking_areas/select.php">    <div class="font-icon-detail"><i class="pe-7s-ticket"></i>
                                      <input type="text" value="Parking Areas">
                                    </div></a>
                                  </div>   

                                <!--  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                 <a href="../tbl_user/select.php">    <div class="font-icon-detail"><i class="pe-7s-ticket"></i>
                                      <input type="text" value="Users">
                                    </div></a>
                                  </div>    
								-->

                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../tbl_booking/select.php">    <div class="font-icon-detail"><i class="pe-7s-ticket"></i>
                                      <input type="text" value="Bookings">
                                    </div></a>
                                  </div>   

                                <!--   <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../tbl_driver/select.php">    <div class="font-icon-detail"><i class="pe-7s-menu"></i>
                                      <input type="text" value="DRIVERS">
                                    </div></a>
                                  </div>
                                                         
                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../tbl_request/select.php">    <div class="font-icon-detail"><i class="pe-7s-menu"></i>
                                      <input type="text" value="request">
                                    </div></a>
                                  </div>

                                  <div class="font-icon-list col-lg-2 col-md-3 col-sm-4 col-xs-6 col-xs-6">
                                <a href="../tbl_vehicle_type/select.php">    <div class="font-icon-detail"><i class="pe-7s-cart"></i>
                                      <input type="text" value="vehicle_type">
                                    </div></a>
                                  </div> -->

                                </div>

                            </div>
                        </div>
                    </div>

                </div>
                
            </div>
        </div>
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
