<?php
$server1 = '10.10.1.3';
$username1 = 'pdelogin';
$password1 = 'readonly';
$database1 = 'sik';
$connection1 = mysql_connect($server1, $username1, $password1);

if(!$connection1)
die("Couldn't connect to Mysql DB1<br />");

if (!mysql_select_db($database1, $connection1))
die('Unable to select Mysql database!');
?>