package logreg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.DaoFactory;
import jdbc.User;
import jdbc.UserDao;
import net.sf.json.JSONArray;

public class getrname extends HttpServlet {

    /**
	 * 从客户端取得name（用户名），查询该用户所有房间名，以json格式发送给客户端
	 * 例如[{"roomname":"房间1"},{"roomname":"房间2"},{"roomname":"房间3"}]
	 *  
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
          
          //需要接受的参数
          String name = req.getParameter("name");
          
          PrintWriter out = resp.getWriter();
          
          UserDao userDao = DaoFactory.getInstance().getUserDao();
          User user = new User();
  		  JSONArray rname = new JSONArray();
  		  user.setName(name);

		  rname=userDao.getRoomname(user);
          
          out.print(rname);
         
    	
    	
    	
        
    }

}


