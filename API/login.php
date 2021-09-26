<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include 'config.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $email = $_POST['email'];
 $password = $_POST['password'];
 $hash = md5($password); 

 $Sql_Query = "select * from users_112209 where email = '$email' ";

 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));


 if($check>0){

$hashed_pwd = $check['user_password'];
 
 if($hashed_pwd == $hash){
 
 echo "Data Matched";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 
 }else{
 echo "Check Again";
 }
mysqli_close($con);

}

?>