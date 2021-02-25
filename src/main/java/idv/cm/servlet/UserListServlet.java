package idv.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import idv.cm.db.UserDao;
import idv.cm.db.UserVO;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/users.html")
public class UserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;   
	private Gson gson = null;
	private Logger logger =null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	@Override
	public void destroy() {
		//release resources
	    con = null;
	    logger = null;
	    gson = null;
	}



	@Override
	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("sqlconnection");
		logger = LogManager.getLogger(); 
		gson = new Gson();
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    PrintWriter out = response.getWriter();
		logger.traceEntry();
		logger.info("call UserListServlet doGet");
		// retrieve all users
		UserDao dao = new UserDao();
		Hashtable<Integer, UserVO> table = dao.findAll(con);
		Collection<UserVO> users = table.values();
		
		logger.traceExit();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
