import java.net.ServerSocket;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerMultithreaded implements Runnable{
	Socket s;
	int count;
	FileWriter fw = null;
	BufferedWriter bw = null;
	
	ServerMultithreaded(Socket sk, int c, FileWriter fw) {
		s = sk;
		count = c;
		this.fw = fw;
	}
	
	public  synchronized void fileWriter(String str) throws IOException
	{
		bw = new BufferedWriter(fw);
		bw.write(str);
	}
	
	private String executeCommand(String command) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}


	
    public void run(){
    	String str;
    	IPValidator ipAddress = new IPValidator();
        while(true) {
        	DataInputStream dis;
			try {
				dis = new DataInputStream(s.getInputStream());
				str = (String)dis.readUTF();
				String res = " Invalid ip";
				String log = "trace route not executed as ip is invalid";
				if( ipAddress.validate(str)) {
					//res = "valid ip"	
					res = executeCommand("traceroute " + str); 
					log = " traceroute executed for " + str;
					
				}
				fileWriter(log);
					
    		    //System.out.println("message= ritu "+str); 
    		    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
    		    dout.writeUTF("echo " + res + " " + String.valueOf(count)); 
    		    dout.flush();
    		    if ( str.equals("end")) {
				    s.close();
				    break;
    		    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }
    }


  

}
