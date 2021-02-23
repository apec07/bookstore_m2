package idv.cm.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;

import idv.cm.db.BookVO;
import idv.cm.db.ConnectionFactory;
import idv.cm.db.OrderedListDTO;
import idv.cm.db.OrderedListVO;
import idv.cm.db.UserVO;
import idv.cm.listener.Log4jServletContextListener;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/orderedlist")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private Gson gson = null;
	private Logger logger ;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// DB connection init()
		System.out.println("Shopping Servlet init()!");
		// from listener
		logger = (Logger)getServletContext().getAttribute("log4j2");
		
		
		gson = new Gson();
		// DB connection init()
		ConnectionFactory factory = ConnectionFactory.getInstance();
		try {
			con = factory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// DB connection close()
		System.out.println("Shopping Servlet destory()!");
		// release connection
		con = null;
		gson = null;
	}

	private List<OrderedListVO> getOrderList(HttpServletRequest req,UserVO user) {
		
		OrderedListDTO dto = new OrderedListDTO();
		List<OrderedListVO> orderList = dto.findOrderDetail(con, user);
		return orderList;
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		logger.entry();
		UserVO user = (UserVO) request.getSession().getAttribute("user");
		PrintWriter out = response.getWriter();
		out.println("Hi, "+user.getName()+" ,Here is yor ordered list - ");
		out.println(getOrderList(request,user));
		logger.info("test LOGGER");
		logger.info(gson.toJson(getOrderList(request,user)));
		request.getServletContext().log("----------------------------------");
		request.getServletContext().log(gson.toJson(getOrderList(request,user)));
		logger.exit();
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

}
