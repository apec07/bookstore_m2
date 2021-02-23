package idv.cm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import idv.cm.db.BookDAO;
import idv.cm.db.BookVO;
import idv.cm.db.ConnectionFactory;


/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;

	public Logger getLogger(String name, String msg) {
		// consloe log
		Logger log = Logger.getLogger(name);
		log.setLevel(Level.INFO);
		log.info(msg);

		return log;
	}

	@Override
	public void init() throws ServletException {
		// DB connection init()
//		getInitParameter("aa");
//		getServletContext().getRealPath("");
		ConnectionFactory factory = ConnectionFactory.getInstance();
		try {
			con = factory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 	response.setContentType("text/html; charset=UTF-8");
	        response.setCharacterEncoding("utf-8");
			if(con==null) {
				getLogger(this.getServletInfo().toString(),"\nbooking List - "+"no connection");
				return;
			}
			BookDAO book = new BookDAO();
			Hashtable<String,BookVO> books = book.findAll(con);
//			JSONObject responseJSONObject = new JSONObject(books);
			PrintWriter out = response.getWriter();
			request.setAttribute("storeBooks", books);
		 
		 
	 }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		processRequest(request,response);
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		// below NOT EXECUTED!!!!!
      	//getLogger(this.getServletInfo().toString(),books.forEach((Integer key,BookVO b)->{return key+"-"+b}));
		
		
	/**
		out.println("<html><head><meta charset='utf-8'><title>Morgan's Book</title></head><body>");
		out.println("<table border='1'>");
		out.print("<TR><th width='200'><div align='center'></div>BookName</th>");
		out.print("<th width='200'><div align='center'></div>Author</th>");
		out.print("<th width='200'><div align='center'></div>Publisher</th>");
		out.print("<th width='200'><div align='center'></div>Price</th>");
		out.print("<th width='200'><div align='center'></div>Quantity</th></TR>");
		for(BookVO b : listBook) {
			String bookName = b.getName();
			String author = b.getAuthor();
			String publisher = b.getPublisher();
			double price = b.getPrice();
			int quantity = b.getQuantity();
			out.print("<tr>"
					+ "<td width='200'><div align='center'>"+bookName+"</div></td>"
					+ "<td width='200'><div align='center'>"+author+"</div></td>"
					+ "<td width='200'><div align='center'>"+publisher+"</div></td>"
					+ "<td width='200'><div align='center'>"+price+"</div></td>"
					+ "<td width='200'><div align='center'>"+quantity+"</div></td>"
					+ "</tr>");
			getLogger(this.getServletInfo().toString(),"\nbooking List - "+bookName+","+author);
		}
		out.println("</table>");
		out.println("</body></html>");
		**/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	public void destroy() {
		// release connection
		con = null;
	}

}
