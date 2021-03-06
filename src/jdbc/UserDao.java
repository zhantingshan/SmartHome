package jdbc;

import net.sf.json.JSONArray;


/**
 * �ӿ�
 * 2008-12-6
 * 
 * @author <a href="mailto:liyongibm@gmail.com">liyong</a>
 * 
 */
public interface UserDao {
	public void addUser(User user);

	public User getUser(int userId);

	public User findUser(String loginName, String password);

	public void update(User user);

	public void delete(User user);

	public JSONArray getRoomname(User user);
	
	public JSONArray getHname(User user,String name);
	
	public void inserthname(User user,String rname,String hname);
	
	public void insertrname(User user,String rname);
	
	public void deleteroom(User user,String rname);
	
	public void deletehname(User user,String rname,String hname);
}
