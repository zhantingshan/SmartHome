package logreg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import datasourse.JdbcUtils;


public class login extends HttpServlet {
    sqlDemo sqlD = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            sqlD = new sqlDemo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        PrintWriter out = resp.getWriter();

        // 注册
        if (!action.equals("login")) {
            String name = req.getParameter("sname");
            String password = req.getParameter("spassword1");
            String mail = req.getParameter("mail");
            String tel = req.getParameter("tel");
          
            
            boolean isEmpty = (name != null && !name.equals("")
                    && password != null && !password.equals("")
                   );
            
            
            System.out.println(isEmpty);
            if (isEmpty) {
                try {
                    boolean flag = sqlD.InsertData(name, password,mail,tel);
                    if (flag) {
                        out.println("<h1>恭喜你" + name
                                + ":注册成功点击<a href='login.html'>登录</a></h1>");
                    } else {
                        out.println("<h1>Soory"
                                + name
                                + ":注册失败,该用户已经存在,点击<a href='Register.html'>重新注册</a></h1>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                out.println("<h1>所以选项均不可以为空！！！点击<a href='Register.html'>重新注册</h1>");
            }

        } else { // 登录
            String name = req.getParameter("name");
            String password = req.getParameter("password");

            try {
                boolean flag = sqlD.LoginCorrect(name, password);
                if (flag) {
                    out.println("<h1>欢迎你:" + name + "</h1>");
                } else {
                    out.println("<h1>SOrry密码错误！点击重新<a href='login.html'>登录</a></h1>");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }

}

class sqlDemo {

    private java.sql.Connection conn = null;
    private java.sql.Statement statement = null;

    public sqlDemo() throws Exception {
        conn = JdbcUtils.getConnection();
        statement = conn.createStatement();
    }

    // 查看登录用户名和密码是否正确
    public boolean LoginCorrect(String name, String password) throws Exception {
        String sql = "select * from user where name = '" + name + "'";
        ResultSet rs = statement.executeQuery(sql);
        // 查看是否合法
        while (rs.next()) {
            if (name.equals(rs.getString("name"))
                    && password.equals(rs.getString("password")))
                return true;
        }
        rs.close();
        return false;
    }

    // 插入数据
    public boolean InsertData(String name, String password,String mail,String tel) throws Exception {
        String sql = "insert into user values (null,'" + name + "','"
                + password+"','"+mail+"','"+tel+ "')";
        String isAgainSql = "select name from user where name = '" + name
                + "'";
        ResultSet rs = statement.executeQuery(isAgainSql);
        // 查看是否用户名重复
        while (rs.next()) {
            if (rs.getString("name").equals(name)) {
                return false;
            }
        }

        statement.executeUpdate(sql);
        rs.close();
        return true;
    }

    

}
