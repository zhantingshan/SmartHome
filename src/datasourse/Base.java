package datasourse;

import java.sql.Connection;


/**
 * 
 * 2008-12-6
 * ���Դԭ���?��mydatasource
 * @author <a href="mailto:liyongibm@hotmail.com">zhantingshan</a>
 * 
 */
public class Base {

	/**
	 * @param args
	 * @throws Exception
	 */
	

	public static void main(String[] args) throws Exception {
		
		for (int i = 0; i < 10; i++) {
			
			 Connection conn = JdbcUtils.getConnection();
			 System.out.println(conn);
			 conn.close();
		}
		
	}
}
