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
# start and stop ftp
$ns at 0.1 "$ftp start"
$ns at 4.5 "$ftp stop"
#Call the finish procedure after 5 seconds of simulation time
$ns at 5.0 "finish"

# run sim
$ns run



