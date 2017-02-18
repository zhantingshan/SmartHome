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

        // ע��
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
                        out.println("<h1>��ϲ��" + name
                                + ":ע��ɹ����<a href='login.html'>��¼</a></h1>");
                    } else {
                        out.println("<h1>Soory"
                                + name
                                + ":ע��ʧ��,���û��Ѿ�����,���<a href='Register.html'>����ע��</a></h1>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                out.println("<h1>����ѡ���������Ϊ�գ��������<a href='Register.html'>����ע��</h1>");
            }

        } else { // ��¼
            String name = req.getParameter("name");
            String password = req.getParameter("password");

            try {
                boolean flag = sqlD.LoginCorrect(name, password);
                if (flag) {
                    out.println("<h1>��ӭ��:" + name + "</h1>");
                } else {
                    out.println("<h1>SOrry������󣡵������<a href='login.html'>��¼</a></h1>");

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

    // �鿴��¼�û����������Ƿ���ȷ
    public boolean LoginCorrect(String name, String password) throws Exception {
        String sql = "select * from user where name = '" + name + "'";
        ResultSet rs = statement.executeQuery(sql);
        // �鿴�Ƿ�Ϸ�
        while (rs.next()) {
            if (name.equals(rs.getString("name"))
                    && password.equals(rs.getString("password")))
                return true;
        }
        rs.close();
        return false;
    }

    // ��������
    public boolean InsertData(String name, String password,String mail,String tel) throws Exception {
        String sql = "insert into user values (null,'" + name + "','"
                + password+"','"+mail+"','"+tel+ "')";
        String isAgainSql = "select name from user where name = '" + name
                + "'";
        ResultSet rs = statement.executeQuery(isAgainSql);
        // �鿴�Ƿ��û����ظ�
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
