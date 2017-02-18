package jdbc;



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
		//		
		User user = new User();
		user.setPassword("12ddd3");
		user.setName("dao name3");
		user.setEmail("1222@1.com");
		user.setTel("12223455");
		user.setEmail("1222@1.com");
		//����
		userDao.addUser(user);
		System.out.println(user.getId());
		
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
		 



	
	}

}
