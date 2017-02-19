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
          String id = req.getParameter("id");
          PrintWriter out = resp.getWriter();
          
          UserDao userDao = DaoFactory.getInstance().getUserDao();
          User user = new User();
  		JSONArray rname = new JSONArray();
  		int i=Integer.parseInt(id);
  		user.setId(i);

		 rname=userDao.getRoomname(user);

          out.print(rname);
         
    	
    	
    	
        
    }

}


