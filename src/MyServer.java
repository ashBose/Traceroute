
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Date;



public class MyServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss=new ServerSocket(6099);
		int count = 0;
		String fileName = "/home/ritu/LOG/tracemsg.txt";
		FileWriter fw = new FileWriter(fileName);
		
	while (true) {	
		try {
		//ServerSocket ss=new ServerSocket(6098);  
		Socket s=ss.accept();//establishes connection
		ServerMultithreaded smp = new ServerMultithreaded(s, count++,fw);
		smp.run();
		}
	catch(Exception e)
	{
		System.out.println(e);
		 
	}
		finally {
			fw.close();
		}
  }
	
}

}
