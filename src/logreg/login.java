package logreg;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datasourse.JdbcUtils;


public class login extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        ServletOutputStream out = resp.getOutputStream();

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
                	 System.out.println("4");
                    boolean flag = sqlD.InsertData(name, password,mail,tel);
                    System.out.println("3");
                    if (flag) {
                    	out.write("sign-up".getBytes());
                    } else {
                    	out.write("fsign-up".getBytes());
                
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
            	out.write("can't be enpty".getBytes());
            }

        } else { // ��¼
            String name = req.getParameter("name");
            String password = req.getParameter("password");
           // System.out.println(name);
            try {
                boolean flag = sqlD.LoginCorrect(name, password);
                System.out.println(flag);
                if (flag) {
                	out.write("success".getBytes()); 
                	//out.print("��ӭ��:" + name );
                } else {
                	out.write("fail".getBytes());
                	//out.print("<h1>SOrry������󣡵������<a href='login.html'>��¼</a></h1>");

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

    // �鿴��¼�û���������Ƿ���ȷ
    public boolean LoginCorrect(String name, String password) throws Exception {
        String sql = "select * from user where name = '" + name + "'";
        ResultSet rs = statement.executeQuery(sql);
        // �鿴�Ƿ�Ϸ�
        while (rs.next()) {
        	
        	String nname=rs.getString("name");
        	String passwordf=rs.getString("password");
            if (name.equals(nname) && password.equals(passwordf)){
                return true;}
        }
        rs.close();
        return false;
    }

    // �������
    public boolean InsertData(String name, String password,String mail,String tel) throws Exception {
        String sql = "insert into user values (null,'" + name + "','"
                + password+"','"+mail+"','"+tel+ "')";
        String isAgainSql = "select name from user where name = '" + name
                + "'";
        ResultSet rs = statement.executeQuery(isAgainSql);
        // �鿴�Ƿ��û����ظ�
        while (rs.next()) {
            if (rs.getString("name").equals(name)) {
            	 System.out.println("2");
                return false;
            }
        }
        
        System.out.println("1");

        statement.executeUpdate(sql);
        rs.close();
        return true;
    }

    

}
