import java.util.*;
import java.io.*;
import java.lang.*;
import java.net.*;
class tcpechoserver1
{
    public static void main(String args[]) throws IOException
    {
        ServerSocket s = new ServerSocket(156);
        Socket obj = s.accept();
        DataInputStream din = new DataInputStream(obj.getInputStream());
        DataOutputStream dout = new DataOutputStream(obj.getOutputStream());
        System.out.println("Server is Running...");
           String str=din.readLine();
            System.out.println("Client: " + str);
            dout.writeBytes(str+"\n");
        }
    }
