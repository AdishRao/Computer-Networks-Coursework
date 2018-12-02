set ns [new Simulator]
set val(nn) 5 

set topo [new Topography]
$topo load_flatgrid 750 750

set namfile [open out4.nam w]
$ns namtrace-all-wireless $namfile 750 750
set tracefile [open out4.tr w]
$ns trace-all $tracefile

set god_ [create-god $val(nn)]

#14 parameters
$ns node-config -adhocRouting AODV -llType LL -macType Mac/802_11 -ifqType Queue/DropTail/PriQueue -channelType Channel/WirelessChannel -antType Antenna/OmniAntenna -propType Propagation/TwoRayGround -phyType Phy/WirelessPhy -ifqLen 50 -topoInstance $topo -agentTrace ON -routerTrace ON -macTrace OFF -movementTrace ON

for {set i 0} {$i<$val(nn)} {incr i} {
 set n($i) [$ns node]
}
for {set i 0} {$i<$val(nn)} {incr i} {
 set XX [expr rand()*750]
 set YY [expr rand()*750]
 $n($i) set X_ $XX
 $n($i) set Y_ $YY
}
for {set i 0} {$i<$val(nn)} {incr i} {
 $ns initial_node_pos $n($i) 30
}

set tcp1 [new Agent/TCP]
$ns attach-agent $n(1) $tcp1
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp1
set sink1 [new Agent/TCPSink]
$ns attach-agent $n(3) $sink1
$ns connect $tcp1 $sink1

$ns at 0.0 "destination"
proc destination {} {
 global ns val n
 set now [$ns now]
 set time 5.0
 for {set i 0} {$i < $val(nn)} {incr i} {
  set XX [expr rand()*750]
  set YY [expr rand()*750]
  $ns at [expr $now+$time] "$n($i) setdest $XX $YY 20.0"
 }
 $ns at [expr $now+$time] "destination"
}

for {set i 0} {$i<$val(nn)} {incr i} {
$ns at 100 "$n($i) reset"
}

$ns at 5.0 "$ftp1 start"
$ns at 100 "$ns nam-end-wireless 100"
$ns at 100 "stop"


proc stop {} {
global namfile tracefile ns
$ns flush-trace
close $namfile
close $tracefile
exec nam out4.nam &
}
$ns run