<?php
include('inc/config.inc.php');
include('atas.php');

if (isset($_GET['bor']))
{
		include "bortoi.php";
}

if (isset($_GET['los']))
{
		include "los.php";
}

if (isset($_GET['gdr']))
{
		include "gdr.php";
}

include('bawah.php');
?>