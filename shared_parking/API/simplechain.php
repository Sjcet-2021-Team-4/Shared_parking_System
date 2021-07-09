<?php
set_time_limit(0);
require_once('blockchain.php');

/*
Set up a simple chain and mine two blocks.
*/

$testCoin = new BlockChain();

//echo "<br>mining block 1...\n";
$data=$userinfo.$date;
$testCoin->push(new Block(1, strtotime("now"),"$data"));
//$amount="500";
//echo "<br>mining block 2...\n";
//$testCoin->push(new Block(2, strtotime("now")," $amount"));

//$rr=json_encode($testCoin, JSON_PRETTY_PRINT);
