import java.io.*;
import java.net.*;

class Serverall
{
	 public static void main(String[] args)
	 {
		 DatagramSocket socket = null;
		 DatagramPacket inpacket = null;
		 DatagramPacket outpacket = null;
		 byte[] inBuf, outBuf;
		 String msg;
		 final int PORT = 5027;
		 
		 try
		 {
			 socket = new DatagramSocket(PORT);
			      while(true)
			      {
			    	  System.out.println("\nRunning...\n");
			    	  
			    	  inBuf = new byte[100];
			    	  inpacket = new DatagramPacket(inBuf, inBuf.length);
			    	  socket.receive(inpacket);
			    	  
			    	  int source_port = inpacket.getPort();
			    	  InetAddress source_address = inpacket.getAddress();
			    	  msg = new String(inpacket.getData(),0,inpacket.getLength());
			    	  System.out.println("Client: "+source_address + ":" + source_port);
			    	  
			    	  String dirname="R:\\cnproject";
			    	  File f1=new File(dirname);
			    	  File fl[]=f1.listFiles();
			    	  
			    	  StringBuilder sb=new StringBuilder("\n");
			    	  int c=0;
			    	  
			    	  for(int i=0;i<fl.length;i++)
			    	  {
			    		  
			    		      if(fl[i].canRead())
			    		    	  c++;
			    		      
			    	  }
			    	  
			    	  sb.append(c+"files found.\n\n");
			    	  
			    	  for(int i=0;i<fl.length;i++)
			    		      sb.append(fl[i].getName()+" "+fl[i].length()+" Bytes\n");
			    	  sb.append("\nEnter file name for download: ");
			    	  outBuf = (sb.toString()).getBytes();
			    	  outpacket = new DatagramPacket(outBuf,0,outBuf.length,source_address, source_port);
			    	  socket.send(outpacket);
			    	  
			    	  inBuf = new byte[100];
			    	  inpacket = new DatagramPacket(inBuf,inBuf.length);
			    	  socket.receive(inpacket);
			    	  String filename=new String(inpacket.getData(),0,inpacket.getLength());
			    	  
			    	  System.out.println("Requested File: "+filename);
			    	  
			    	  boolean flis= false;
			    	  int index=-1;
			    	  sb=new StringBuilder("");
			    	  for(int i=0;i<fl.length;i++)
			    	  { 
			    		  		
			    		  		if(((fl[i].getName()).toString()).equalsIgnoreCase(filename))
			    		  		{				 
			    		  						index=i;
			    		  						flis=true;
			    		  						
			    		  		}
			    	  }
			    	  
			    	  if(!flis)
			    	
			    	  {
			    	  
			    	      System.out.println("ERROR");
			    	      sb.append("ERROR");
			    	      outBuf = (sb.toString()).getBytes();
			    	      outpacket = new DatagramPacket(outBuf,0,outBuf.length,source_address,source_port);
			    	      socket.send(outpacket);
			    	  
			      }
			      else
			      {
			    	      try
			    	      {  
			    	    	  //File Send Process, Independent
			    	    	  File ff=new File(fl[index].getAbsolutePath());
			    	    	  FileReader fr=new FileReader(ff);
			    	    	  BufferedReader brf=new BufferedReader(fr);
			    	    	  String s=null;
			    	    	  sb=new StringBuilder();
			    	    	  
			    	    	  while((s=brf.readLine())!=null)
			    	    	  {
			    	    		  	sb.append(s);
			    	    		  	
			    	    	  }
			    	    	  if(brf.readLine()==null)
			    	    		  System.out.println("File Read Successful.closing socket.");
			    	    	  
			    	    	  outBuf=new byte[100000];
			    	    	  outBuf = (sb.toString()).getBytes();
			    	    	  outpacket = new DatagramPacket(outBuf,0,outBuf.length,source_address,source_port);
			    	    	  socket.send(outpacket);
			 
			    	     	  
			    	      }
			    	      catch(IOException ioe)
			    	      {
			    	    	  System.out.println(ioe);
			    	      }
			      }
		}
		
		 
	    }
		catch(Exception e)
		{
			System.out.println("Error/n");
			
		}
		       
}
	 }
