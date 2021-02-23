package idv.cm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.config.Configuration;
//import org.apache.logging.log4j.web.Log4jWebSupport;
//import org.apache.logging.log4j.web.WebLoggerContextUtils;
//
//import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import idv.cm.db.ConnectionFactory;
import idv.cm.db.UserDao;
import idv.cm.db.UserVO;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	
//	private static Logger logger = Logger.getLogger("LoginServlet");
	
	
	public Logger getLogger(String name,String msg) {
		//consloe log
		Logger log = Logger.getLogger(name);
		log.setLevel(Level.INFO);
		log.info(msg);
		
		return log;
	}
	@Override
	public void init() throws ServletException {
		// DB connection init()
		/**
		ConnectionFactory factory = ConnectionFactory.getInstance();
		try {
			con = factory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext ctx = config.getServletContext();
		con = (Connection)ctx.getAttribute("sqlconnection");
		//super.init(config);
	}
	
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserVO user = getUser(request);
		if(con==null) {
			System.out.println("no Connection");
			return;
		}
		UserDao dao = new UserDao();
		Hashtable<Integer, UserVO> table = dao.findAll(con);
		Collection<UserVO> users = table.values();
		boolean isUser = users.contains(user); //overwrite UserVO equals method...
		getLogger(this.getClass().getSimpleName(),"isUser = "+isUser);
//		System.out.println("isUser = "+isUser);
//		users.forEach(p->System.out.println(p));
		if(isUser) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			System.out.println("correct");
			response.sendRedirect(request.getContextPath()+"/redirect.jsp");
		}else {
			System.out.println("wrong");
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
	}
	
	private UserVO getUser(HttpServletRequest req) {
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		System.out.println("name "+name);
		System.out.println("pass "+pass);
		UserVO user = new UserVO();
		user.setName(name);
		user.setPassword(pass);
		//System.out.println(getGson(user));
		//getLogger(this.getClass().getSimpleName(),user);
		return user;
	}

	@Override
	public void destroy() {
		//release connection
		con = null;
	}
	
}
