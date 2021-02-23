package idv.cm.listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import idv.cm.db.*;

public class DbConnection implements ServletContextListener{
	
	Connection con = null;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		try {
			con = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(con!=null) {
			sce.getServletContext().log("Connection connected!");
			sce.getServletContext().setAttribute("sqlconnection", con);
		}else {
			sce.getServletContext().log("Connection not connectioned!");
		}
//		ServletContextListener.super.contextInitialized(sce);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if(con!=null) {
			sce.getServletContext().log("Connection destory!");
			sce.getServletContext().removeAttribute("sqlconnection");
			con=null;
		}
//		ServletContextListener.super.contextDestroyed(sce);
	}


	
	
}
