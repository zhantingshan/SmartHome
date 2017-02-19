package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import datasourse.JdbcUtils;





/**
 * 
 * 2008-12-6
 * 
 * @author <a href="mailto:liyongibm@gmail.com">liyong</a>
 * 
 */
public class UserDaoJdbcImpl implements UserDao {

	public void addUser(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into user(name,password,email,Tel) values (?,?,?,?) ";
			ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getTel());
			ps.executeUpdate();

			rs = ps.getGeneratedKeys();//闁岸鏁撻弬銈嗗閹笛囨晸閸欘偉鎻幏锟絊tatement 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚归柨鐔烘畷鐠佽瀚归柨鐔告灮閹峰嘲鍋﹂崟鐔兼晸閺傘倖瀚�ResultSet 闁跨喐鏋婚幏鐑芥晸閺傘倖瀚�
			                           //閸掆晝鏁DBC閻ㄥ埀etGeneratedKeys閼惧嘲绶盜NSERT閹绘帒鍙嗛崥搴ｆ晸閹存劗娈戞稉濠氭暛ID
			if (rs.next())
				user.setId(rs.getInt(1));
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	public void delete(User user) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();//1
			st = conn.createStatement();
			String sql = "delete from user where id=" + user.getId();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, st, conn);
		}

	}

	public User findUser(String loginName, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id, name, password, email, Tel, power  from user where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginName);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = mappingUser(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}
	
	public JSONArray getRoomname(User user){
		JSONArray roomname = new JSONArray();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "SELECT r.roomname FROM user.user u,user.room r where r.id=u.id and r.id=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());			
			rs=ps.executeQuery();
			try {
				roomname=getJson(rs) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		
		
		return roomname;
	}
    
	public JSONArray getHname(User user,String name){
		JSONArray Hname = new JSONArray();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "SELECT h.hname FROM user.household h ,user.user u,user.room r where r.id=u.id and h.id=? and r.roomname=? ";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());	
			ps.setString(2, name);
			rs=ps.executeQuery();
			try {
				Hname=getJson(rs) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		
		
		return Hname;
	}
	
	public User getUser(int userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select id, name, password, email, Tel, power  from user where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = mappingUser(rs);
			}
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return user;
	}

	private User mappingUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setTel(rs.getString("Tel"));
		return user;
	}

	public void update(User user) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "update user set name=?, password=?, email=? , Tel=?, where id=? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getTel());

			ps.setInt(6, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
	
	public static JSONArray getJson(ResultSet rs) throws Exception {
        ResultSetMetaData md = rs.getMetaData();// 閼惧嘲褰囩悰銊х波閺嬶拷
        int num = md.getColumnCount();// 瀵版鍩岀悰宀�畱閹粯鏆�
        JSONArray array = new JSONArray();// json閺佹壆绮嶉敍灞剧壌閹诡喕绗呴弽鍥ㄥ閸婄》绱盵{name1:wp},{name2:{name3:'ww'}}]name娑撶皝ey閸婄》绱漺p娑撶皭alue閸婏拷
        // JSONArray array=JSONArray.fromObject(String);鐏忓摖tring鏉烆剚宕叉稉绡擲ONArray閺嶇厧绱�
        while (rs.next()) {// 婵″倹鐏夌紒鎾寸亯闂嗗棔鑵戦張澶婏拷
            JSONObject mapOfColValues = new JSONObject();// 閸掓稑缂搄son鐎电钖勭亸杈ㄦЦ娑擄拷閲渰name:wp}
            for (int i = 1; i <= num; i++) {
                mapOfColValues.put(md.getColumnName(i), rs.getObject(i));// 濞ｈ濮為柨顔硷拷鐎电櫢绱濆В鏂款洤鐠囩name:Wp}闁俺绻僴ame閹垫儳鍩寃p
            }
            array.add(mapOfColValues);
        }
        return array;
    }


}
