package udpconnection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {

	public static void main(String[] args) throws Exception
	{
		DatagramSocket serversocket = new DatagramSocket(55523);
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{	
				
	      byte[] sendbuffer = new byte[1024];
	      byte[] receivebuffer = new byte[1024];
		
		
		DatagramPacket receivepacket = new DatagramPacket(receivebuffer, receivebuffer.length);
		serversocket.receive(receivepacket);
		int portno = receivepacket.getPort();
		
		InetAddress ia = InetAddress.getLocalHost();
		String clientdata = new String(receivepacket.getData());
        System.out.println("\nClient : "+ clientdata);
        System.out.print("\nServer : ");
       
        String serverdata = sc.nextLine();
        
        sendbuffer = serverdata.getBytes();
		
		
		
		DatagramPacket sendpacket = new DatagramPacket(sendbuffer,sendbuffer.length,ia, portno);
		serversocket.send(sendpacket);
		 if(serverdata.equalsIgnoreCase("bye"))
         {
             System.out.println("connection ended by server");
             break;
         }
         
         
		        }
		serversocket.close();
		}

	  }

	   
