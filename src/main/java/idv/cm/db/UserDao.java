package idv.cm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Hashtable;

public class UserDao implements UserDAOImp {

	@Override
	public int insert(Connection con, UserVO user) {
		PreparedStatement pstmt;
		int updateNum=0;
		
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, user.getName()); //user
			pstmt.setString(2, user.getPassword());    //pass
			pstmt.setString(3, user.getEmail());    //email
			updateNum = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateNum;
	}

	@Override
	public int update(Connection con, int id, UserVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Connection con, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserVO findByIndex(Connection con, String userName) {
		UserVO user = new UserVO();
		PreparedStatement pstm =null;
		try {
			pstm = con.prepareStatement(GET_ONE_STMT);
			pstm.setString(1, userName);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				
				String name = rs.getString(1);
				String pass = rs.getString(2);
				String email = rs.getString(3);
				user.setName(name);
				user.setPassword(pass);
				user.setEmail(email);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return user;
	}

	@Override
	public Hashtable<Integer, UserVO> findAll(Connection con) {
		Hashtable<Integer,UserVO> table = new Hashtable<>();
		try {
		PreparedStatement pstm = con.prepareStatement(GET_ALL_STMT);
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			UserVO user = new UserVO();
			int index = rs.getInt(1);
			String name = rs.getString(2);
			String pass = rs.getString(3);
			String email = rs.getString(4);
			user.setName(name);
			user.setPassword(pass);
			user.setEmail(email);
			table.put(index,user);
			//System.out.println(user);
		}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return table;
	}

}
