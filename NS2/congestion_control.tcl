# create sim object
set ns [new Simulator]
set nr [open output.tr w]
$ns trace-all $nr

#nam file 
set nf [open output.nam w]
$ns namtrace-all $nf
$ns color 1 Blue
$ns color 2 Red

# finish proc
proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam output.nam &
	exit 0
}

# creating nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

# links
$ns duplex-link $n0 $n1 10Mb 10ms DropTail
$ns duplex-link $n1 $n2 2Mb 10ms DropTail

# queue topology
$ns duplex-link-op $n0 $n1 orient left
$ns duplex-link-op $n1 $n2 orient left

# queue limit
$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10



# TCP connection
set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp  

set sink [new Agent/TCPSink/DelAck] 
#delay per ack
$ns attach-agent $n2 $sink

$ns connect $tcp $sink

$tcp set fid_ 1 # flow id
$tcp set window_ 8000 # window size
$tcp set packetSize_ 500 

# FTP over TCP
set ftp [new Application/FTP]
$ftp attach-agent $tcp
#$ftp set type_ FTP # channel type


# UDP connection
set udp [new Agent/UDP]
$ns attach-agent $n1 $udp  

set null [new Agent/Null] 
#delay per ack
$ns attach-agent $n2 $null

$ns connect $udp $null

$udp set fid_ 2 # flow id
$udp set window_ 8000 # window size
$udp set packetSize_ 500 

# ConstantBitRate over TCP for traffic source behaviour of packets
set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp

# start and stop ftp
$ns at 0.2 "$ftp start"
$ns at 0.4 "$cbr start"
$ns at 2.0 "$ftp stop"
$ns at 2.5 "$cbr stop"
#Call the finish procedure after 5 seconds of simulation time
$ns at 2.5 "finish"

# run sim
$ns run



