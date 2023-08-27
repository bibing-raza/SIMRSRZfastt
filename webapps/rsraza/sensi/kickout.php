<?php
session_start();
if(session_destroy()){
 header("Location: nigol.php?action=isi&info=out");
}
?>