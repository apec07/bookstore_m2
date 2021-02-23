package idv.cm.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionFactory {
	
	private static ConnectionFactory connectionFactory=null;
	private Connection con;
	javax.naming.Context ctx= null;
	DataSource ds = null;
	private ConnectionFactory() {
		
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/TestSQL");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ConnectionFactory getInstance() {
		if(connectionFactory==null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
	
	public Connection getConnection() throws SQLException {
		
		if(con==null) {
			if(ds!=null) {
				con = ds.getConnection();
			}else {
				System.out.println("ds??");
			}
			
		}
		return con;
	}
	
}
