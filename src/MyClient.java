
import java.io.*;
import java.net.*;

public class MyClient implements Runnable{
	
	/**
	 * @param args
	 */
	
	
	
	public void run()
	{
		DataInputStream dis;
		String str;
		try{
			InetAddress addr = InetAddress.getLocalHost();
		      System.out.println("Local HostAddress: "+addr.getHostAddress());
		      String hostname = addr.getHostName();
		      System.out.println("Local host name: "+hostname);
		    //for(int i =0; i < 6; i++) {  
			Socket s=new Socket("127.0.0.1",6099);
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
			dout.writeUTF("216.58.194.206");  
			dout.flush();
			dis = new DataInputStream(s.getInputStream());
			str = (String)dis.readUTF();
			System.out.println(str);
			dout.writeUTF("end");  
			dout.flush();
			dout.close();  
			s.close();
		//}
			}catch(Exception e){System.out.println(e);} 
	}
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		//for(int i=0;i<6;i++)
		//{
			MyClient mc = new MyClient();
			mc.run();
		//}
		 

	}

}
