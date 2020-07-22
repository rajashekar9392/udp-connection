package udpconnection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

	public static void main(String[] args) throws Exception
	{
		Scanner sc= new Scanner(System.in);
		
		DatagramSocket clientsocket = new DatagramSocket();
		
		InetAddress ia = InetAddress.getLocalHost();		
		while(true)    
		{

		byte[] sendbuffer = new byte[1024];
	      byte[] receivebuffer = new byte[1024];
	      System.out.print("\nClient: ");
	      String clientData = sc.nextLine();
	      sendbuffer = clientData.getBytes();
	      
		
		DatagramPacket sendpacket = new DatagramPacket(sendbuffer,sendbuffer.length,ia,55523);
		clientsocket.send(sendpacket); 
		
		 if(clientData.equalsIgnoreCase("bye"))
	      {
	          System.out.println("Connection ended by client");
	          break;
	      }
		
		DatagramPacket receivepacket = new DatagramPacket(receivebuffer, receivebuffer.length);
		clientsocket.receive(receivepacket);
		
		String serverData = new String(receivepacket.getData());
		System.out.println("\nServer: " + serverData);
		         }
		clientsocket.close();
		}
}


