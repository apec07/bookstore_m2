package idv.cm.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestConnection {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		
		//ConnectionFactory factory =ConnectionFactory.getInstance();
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db_morgan?"+"autoReconnect=true&useSSL=false"; 
		String userName = "root";
		String password = "1234";
		
		Class.forName(driverClassName);
		Connection con = DriverManager.getConnection(url,userName,password);
		UserDao dao = new UserDao();
		UserVO user = dao.findByIndex(con, "morgan");
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		//user = gson.fromJson(user.toString(), user.getClass());
		System.out.println(gson.toJson(user));
		System.out.println("--------------");
		System.out.println(user);
		//Hashtable<Integer, UserVO> table = dao.findAll(con);
		//Set<Integer> set = table.keySet();
		//set.forEach(k->System.out.println(table.get(k)));
	}

}
