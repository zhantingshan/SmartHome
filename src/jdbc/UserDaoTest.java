package jdbc;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



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
		//����		
		/*User user = new User();
		user.setPassword("12ddd3");
		user.setName("dao name3");
		user.setEmail("1222@1.com");
		user.setTel("12223455");
		user.setEmail("1222@1.com");
		//����
		userDao.addUser(user);
		System.out.println(user.getId());
		*/
		//��id
//		User u = userDao.findUser(user.getName(), null);
//		System.out.println(u.getId());

		//����
//		User u = userDao.getUser(3);
//		u.setMoney(1000.1f);
//		userDao.update(u);

		 //ɾ��
//		 User u1 = userDao.getUser(4);
//		 System.out.println(u1);
//		 userDao.delete(u1);
//    
		
/*		 //�õ�������Ϣ
		User user = new User();
		JSONArray rname = new JSONArray();
		user.setName("hubby");
		//����
		rname=userDao.getRoomname(user);
		System.out.println(rname);
*/
	
/*		
		User user = new User();
		JSONArray hname = new JSONArray();
		user.setName("hubby");
		String name="客厅";
		//����
		hname=userDao.getHname(user,name);
		System.out.println(hname);

*/		
	/*	
		User user = new User();
		user.setName("hubby");
		String hname="电热水壶";
		String rname="客厅";
		//����
		userDao.inserthname(user,rname,hname);
		System.out.println(hname);
	*/	
		
		/*
		User user = new User();
		user.setName("詹婷珊");
		String rname="客厅12";
		//����
		userDao.insertrname(user,rname);
		System.out.println(rname);
		*/
		
		User user = new User();
		user.setName("hubby");
		String rname="客厅";
		String hanme="空调";
		//����
		userDao.deletehname(user,rname,hanme);
		System.out.println(rname);
		
		
	}

}
