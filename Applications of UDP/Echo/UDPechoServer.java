import java.io.*;
import java.net.*;
class UDPechoServer{
 public static void main(String args[]) throws Exception {
  DatagramSocket serverSocket = new DatagramSocket(9876);
  byte[] receiveData = new byte[1024];
  byte[] sendData = new byte[1024];
  do{
   DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
   serverSocket.receive(receivePacket);
   String sentence = new String( receivePacket.getData());
   System.out.println("Client: " + sentence);
   InetAddress IPAddress = receivePacket.getAddress();
   int port = receivePacket.getPort();
   String capitalizedSentence = sentence.toUpperCase();
   sendData = capitalizedSentence.getBytes();
   DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
   serverSocket.send(sendPacket);
  }while(false);
 }
}
