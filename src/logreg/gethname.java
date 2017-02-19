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

public class gethname extends HttpServlet {

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
          String name = req.getParameter("name");
          PrintWriter out = resp.getWriter();
          
          UserDao userDao = DaoFactory.getInstance().getUserDao();
          User user = new User();
  		JSONArray hname = new JSONArray();
  		user.setId(5);
  		hname=userDao.getHname(user,name);
          
          out.print(hname);
         
    	
    	
    	
        
    }

}


