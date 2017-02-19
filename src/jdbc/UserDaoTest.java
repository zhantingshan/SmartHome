package jdbc;

import net.sf.json.JSONArray;



/**
 * 
 * 2008-12-6
 * 
 * @author <a href="mailto:liyongibm@gmail.com">liyong</a>
 * 
 */
public class UserDaoTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UserDao userDao = DaoFactory.getInstance().getUserDao();
		// UserDao userDao = new UserDaoJdbcImpl();
		// System.out.println(userDao);
		//²åÈë		
		/*User user = new User();
		user.setPassword("12ddd3");
		user.setName("dao name3");
		user.setEmail("1222@1.com");
		user.setTel("12223455");
		user.setEmail("1222@1.com");
		//ï¿½ï¿½ï¿½ï¿½
		userDao.addUser(user);
		System.out.println(user.getId());
		*/
		//ï¿½ï¿½id
//		User u = userDao.findUser(user.getName(), null);
//		System.out.println(u.getId());

		//ï¿½ï¿½ï¿½ï¿½
//		User u = userDao.getUser(3);
//		u.setMoney(1000.1f);
//		userDao.update(u);

		 //É¾ï¿½ï¿½
//		 User u1 = userDao.getUser(4);
//		 System.out.println(u1);
//		 userDao.delete(u1);
//         
		 //µÃµ½·¿¼äÐÅÏ¢
	/*	User user = new User();
		JSONArray rname = new JSONArray();
		user.setId(5);
		//ï¿½ï¿½ï¿½ï¿½
		rname=userDao.getRoomname(user);
		System.out.println(rname);
*/
		User user = new User();
		JSONArray hname = new JSONArray();
		user.setId(5);
		String name="¿ÍÌü";
		//ï¿½ï¿½ï¿½ï¿½
		hname=userDao.getHname(user,name);
		System.out.println(hname);

	
	}

}
