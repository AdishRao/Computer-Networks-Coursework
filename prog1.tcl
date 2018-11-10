set ns [new Simulator]
set tr [open out.tr w]
set nf [open out.nam w]
$ns trace-all $tr
$ns namtrace-all $nf

proc finish {} {
 global ns tr nf
 $ns flush-trace
 close $tr
 close $nf
 exec nam out.nam &
 exit 0
}
#create nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
#label them
$n0 label "TCP"
$n1 label "UDP"
$n2 label "DEST"
$ns color 1 "Red"
$ns color 2 "Green"
#set 2 links and limit
$ns duplex-link $n0 $n1 1.00Mb 10ms DropTail
$ns duplex-link $n1 $n2 1.00Mb 20ms DropTail
$ns queue-limit $n1 $n2 20
$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n1 $n2 orient down

set tcp0 [new Agent/TCP]
$ns attach-agent $n0 $tcp0
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0

set sink3 [new Agent/TCPSink]
$ns attach-agent $n2 $sink3

$ns connect $tcp0 $sink3

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1

set null3 [new Agent/Null]
$ns attach-agent $n2 $null3

$ns connect $udp1 $null3

$tcp0 set class_ 1
$udp1 set class_ 2

#maxPkts_
$ftp0 set maxPkts_ 1000
#packetSize_ interval_
$cbr1 set packetSize_ 250
$cbr1 set interval_ 0.002

$ns at 0.5 "$cbr1 start"
$ns at 1.0 "$ftp0 start"
$ns at 4.0 "$cbr1 stop"
$ns at 4.5 "$ftp0 stop"
$ns at 5.0 "finish"
$ns run

















