package logreg;

import java.io.IOException;

import socketway.Soc;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class controller extends HttpServlet {

    /**
	 * 从客户端取得name（用户名）和rname（房间名），查询该用户该房间的所有电器名，以json格式发送给客户端
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	     this.doPost(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	  resp.setContentType("text/html;charset=utf-8");
          req.setCharacterEncoding("utf-8");
          ServletOutputStream out = resp.getOutputStream();
          
          //需要接受的参数
          String rname = req.getParameter("rname");   
          String name = req.getParameter("name");
          String type = req.getParameter("type");
          String hname = req.getParameter("hname");
          String getorder = req.getParameter("order"  );
          String starTime = req.getParameter("startime");
          String endTime = req.getParameter("endtime");
          
          String order =name+rname+type+hname+getorder ;
          
          String ip ="192.168.4.1";
          String port ="5000";
          
          Soc soc=new Soc();
          try {
			String result=soc.coll(ip,port,order);
			out.write(result.getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
        
        
    	
    	
        
    }

}