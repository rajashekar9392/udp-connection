import java.io.*;
import java.net.*;
import java.util.Scanner;

class Clientall
   {
	
	   public static void main(String[] args)
	   {
			 DatagramSocket socket = null;
			 DatagramPacket inpacket = null;
			 DatagramPacket outpacket = null;
			 byte[] inBuf, outBuf;
			 final int PORT = 5027;
			 String msg = null;
			 Scanner src = new Scanner(System.in);
			 
			 try
			 {

				 //InetAddress address = InetAddress.getLocalHost();
				 InetAddress address = InetAddress.getByName("192.168.56.1");
				 socket = new DatagramSocket();
				 
				 msg = "";
				 outBuf = msg.getBytes();
				 outpacket = new DatagramPacket(outBuf,0,outBuf.length,address,PORT);
				 socket.send(outpacket);
				 
				 inBuf = new byte[65535];
				 inpacket= new DatagramPacket(inBuf, inBuf.length);
				 socket.receive(inpacket);
				 
				 String data = new String(inpacket.getData(),0,inpacket.getLength());
				 
				 //print file list
				 System.out.println(data);
				 
				 //send file name
				 String filename=src.nextLine();
				 outBuf = filename.getBytes();
				 outpacket = new DatagramPacket(outBuf,0,outBuf.length,address,PORT);
				 socket.send(outpacket);
				 
				 //Receive file
				 inBuf = new byte[100000];
				 inpacket = new DatagramPacket(inBuf, inBuf.length);
				 socket.receive(inpacket);
				 
				 data = new String(inpacket.getData(),0,inpacket.getLength());
				 if(data.endsWith("ERROR"))
				 {
					 System.out.println("File doesn't exist.\n");
					 socket.close();
					 
					 
				 }
				 else
				 {
					 try
					 {
						 BufferedWriter pw =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)));
						 pw.write(data);
						 //Force write buffer to File
						 pw.close();
						  
						 System.out.println("File Write Successful. Closing socket.");
						 socket.close();
						 
						 
					 }
					 catch(IOException ioe)
					 {
						 System.out.println("File Error\n");
						 socket.close();
					 }
				 }
			 }
			 catch(Exception e)
			 {
				 System.out.println("\nNetwork error. please try again.\n");
				 
			 }
	   }
}