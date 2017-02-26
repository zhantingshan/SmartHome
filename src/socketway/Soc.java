package socketway;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Soc 
{
	public String coll(String ip,String port,String order)throws Exception 
	{
		int portint = Integer.parseInt(port); 
		Socket s = new Socket(ip,portint);
		
		
		OutputStream out = s.getOutputStream();  

		out.write(order.getBytes());

		
		InputStream in = s.getInputStream();

		byte[] buf = new byte[1024];

		int len = in.read(buf);

		System.out.println(new String(buf,0,len));
		s.close();
		String se="success";
		
		return se;
	}
}

