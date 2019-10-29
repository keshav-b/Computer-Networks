#LINK STATE
set ns [new Simulator]
set nr [open link.tr w]
$ns trace-all $nr
set nf [open link.nam w]
$ns namtrace-all $nf 
proc finish {} {
global ns nr nf
$ns flush-trace 
close $nf
close $nr
exec nam link.nam & 
exit 0
}
for { set i 0 } { $i < 5} { incr i 1 } { set n($i) [$ns node]}
for {set i 0} {$i < 2} {incr i} {
$ns duplex-link $n($i) $n([expr $i+1]) 1Mb 10ms DropTail}
$ns duplex-link $n(0) $n(3) 1Mb 10ms DropTail
$ns duplex-link $n(1) $n(4) 1Mb 10ms DropTail
$ns duplex-link $n(0) $n(2) 1Mb 10ms DropTail
$ns duplex-link $n(2) $n(4) 1Mb 10ms DropTail
$ns duplex-link-op $n(0) $n(3) orient right
$ns duplex-link-op $n(0) $n(1) orient right-down
$ns duplex-link-op $n(1) $n(4) orient down
$ns duplex-link-op $n(0) $n(2) orient up
$ns duplex-link-op $n(2) $n(4) orient right-up
$ns duplex-link-op $n(2) $n(4) orient right-down
set udp0 [new Agent/UDP]
$ns attach-agent $n(0) $udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.005
$cbr0 attach-agent $udp0
set null0 [new Agent/Null]
$ns attach-agent $n(4) $null0
$ns connect $udp0 $null0 
set udp1 [new Agent/UDP]
$ns attach-agent $n(1) $udp1
set cbr1 [new Application/Traffic/CBR]
$cbr1 set packetSize_ 500
$cbr1 set interval_ 0.005
$cbr1 attach-agent $udp1 
set null0 [new Agent/Null]
$ns attach-agent $n(4) $null0
$ns connect $udp1 $null0
$ns rtproto LS
$ns rtmodel-at 2.0 down $n(1) $n(4)
$ns rtmodel-at 1.5 down $n(0) $n(2)
$ns rtmodel-at 3.0 up $n(0) $n(1)
$ns rtmodel-at 2.0 up $n(0) $n(3)
$udp0 set fid_ 1
$udp1 set fid_ 2
$ns color 1 Red
$ns color 2 Green
$ns at 1.0 "$cbr0 start"
$ns at 2.0 "$cbr1 start"
$ns at 4 "finish"
$ns run
