package logreg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.DaoFactory;
import jdbc.User;
import jdbc.UserDao;

public class insertrname extends HttpServlet {

    /**
	 * 取得客户端中username（客户名），rname（新增加的房间名）插入数据库，
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	     
    	     
    	     resp.setContentType("text/html;charset=utf-8");
             req.setCharacterEncoding("utf-8");
             ServletOutputStream out = resp.getOutputStream();
             
             //需要接受的参数
             String username = req.getParameter("username");
             String rname = req.getParameter("rname");
             System.out.println(username);
             System.out.println(rname);
             
             UserDao userDao = DaoFactory.getInstance().getUserDao();
             User user = new User();
     		  user.setName(username);
     		  //����
     		  userDao.insertrname(user,rname);

     		  out.write("success".getBytes()); 

    	     
    	     
    	     
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	    
    	this.doPost(req, resp);
    		    		        
    }

}

