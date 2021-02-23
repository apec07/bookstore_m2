package idv.cm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import idv.cm.db.ConnectionFactory;
import idv.cm.db.UserDao;
import idv.cm.db.UserVO;

/**
 * Register Servlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	
	@Override
	public void destroy() {
		//release connection
		con = null;
	}

	@Override
	public void init() throws ServletException {
		// DB connection init()
		ConnectionFactory factory = ConnectionFactory.getInstance();
		try {
			con = factory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
		super.log(msg);
	}


	private UserVO getUser(HttpServletRequest req) {
//		Enumeration<String> attNames = req.getAttributeNames();
//		while(attNames.hasMoreElements()) {
//			log("-------------------");
//			log(attNames.nextElement());
//			log("-------------------");
//			log(req.getAttribute(attNames.nextElement()).toString());
//		}
		String name = req.getParameter("new_name");
		String pass = req.getParameter("new_password");
		String mail = req.getParameter("new_email");
		System.out.println("new_name " + name);
		System.out.println("new_password " + pass);
		System.out.println("new_email " + mail);
		UserVO user = new UserVO();
		user.setName(name);
		user.setPassword(pass);
		user.setEmail(mail);
		return user;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserVO user = getUser(request);
		System.out.println(getGson(user));
		
		if(con==null) {
			System.out.println("no Connection");
			return;
		}
		UserDao dao = new UserDao();
		int updateNum = dao.insert(con, user);
		request.setAttribute("updateNum", updateNum);
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private String getGson(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		obj = gson.fromJson(obj.toString(), obj.getClass());
		return gson.toJson(obj);
	}

	
}
