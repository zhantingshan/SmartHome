package jdbc;

/**
 * 
 * 2008-12-6
 * bean锟侥硷拷
 * @author <a href="mailto:liyongibm@gmail.com">liyong</a>
 * 
 */
public class User {
	private int id;
	private String name;
	private String  password;
	private String  email;
	private String  Tel;
	private String  roomname;
	private String  hname;
	
	public User() {

	}

	public User(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "id=" + this.id + " name=" + this.name + " email="
				+ this.email + " tel=" + this.Tel;
	}

	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTel() {
		return Tel;
	}

	public void setTel(String Tel) {
		this.Tel = Tel;
	}
	
/*	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	
*/
	
}
