import java.io.*;
import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.SocketException; 
import java.awt.Desktop;
import java.net.URI;
import java.util.Scanner;
public class UDPserverdns{ 
public static void main(String[] args) throws IOException 
{ 
  try{
  int i,flag=0;
	String[][] a=new String[5][5];
	a[0][0]="www.google.com";
  a[0][1]="172.217.11.174";
	a[1][0]="www.w3schools.com";
	a[1][1]="66.29.212.110";
	a[2][0]="www.Dell.com";
	a[2][1]="143.166.83.38";
	a[3][0]="www.quora.com";
	a[3][1]="3.212.29.26";
	a[4][0]="www.comlaude.com";
	a[4][1]="213.212.81.71";
  DatagramSocket ds = new DatagramSocket(1234); 
  byte[] receive = new byte[65535]; 
  DatagramPacket DpReceive = null; 
  System.out.println("Server starts Working");
  DpReceive = new DatagramPacket(receive, receive.length); 
  ds.receive(DpReceive); 
  String b=data(receive).toString();
  System.out.println("Client  "+b);
  	for(i=0;i<4;i++)
  	{
  		if(b.equals(a[i][0]))
  		{
  			System.out.println("Server  " + a[i][1]); 
  			Desktop d=Desktop.getDesktop();
        d.browse(new URI(b));
        flag=1;
  		}
  		else if(b.equals(a[i][1]))
  		{
  			System.out.println("Server  " + a[i][0]);
  			Desktop d=Desktop.getDesktop();
        d.browse(new URI(a[i][0]));
        flag=1;
  		}
}
  		if(flag==0)
  		{
  			System.out.println("Entered ip address or url is wrong");
  		}
      receive = new byte[65535]; 
    } 
 catch(Exception E){
        System.err.println("Exp : "+E.getMessage());
        }
    }
    public static StringBuilder data(byte[] a) 
    { 
        if (a == null) 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) 
        { 
            ret.append((char) a[i]); 
            i++; 
        } 
        return ret; 
    } 
}
