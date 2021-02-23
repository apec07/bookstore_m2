package idv.cm.db;

import java.sql.Connection;
import java.util.Hashtable;

public interface UserDAOImp {
	
	static final String INSERT_STMT="insert into B_USER(USER,PASSWORD,EMAIL) values(?,?,?)";
	static final String GET_ALL_STMT="select * from B_USER order by ID";
	static final String GET_ONE_STMT="select USER,PASSWORD,EMAIL from B_USER where user = ?";
	static final String DELETE_STMT="delete from B_USER where ID =?";
//	static final String UPDATE_STMT="update ACCOUNT SET USER=?, PASSWORD=?,NOTE=? WHERE ID=?";

	int insert(Connection con, UserVO user);
	int update(Connection con,int id, UserVO user);
	int delete(Connection con,int id);
	UserVO findByIndex(Connection con,String userName);
	Hashtable<Integer,UserVO> findAll(Connection con);
}
