package idv.cm.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import idv.cm.db.BookVO;
import idv.cm.db.ConnectionFactory;
import idv.cm.db.OrderDatailDAO;
import idv.cm.db.OrderDetailVO;
import idv.cm.db.OrderListDAO;
import idv.cm.db.OrderListVO;
import idv.cm.db.UserVO;

/**
 * Servlet implementation class ShoppingServlet
 */
@WebServlet("/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	
	@Override
	public void init() throws ServletException {
		// DB connection init()
		System.out.println("Shopping Servlet init()!");
		// DB connection init()	
				ConnectionFactory factory = ConnectionFactory.getInstance();
				try {
					con = factory.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
	}

	private BookVO getBookVO(HttpServletRequest req) {
		
		String name = req.getParameter("name");
		System.out.println("name - "+ name);
		String[] nameValues = req.getParameterValues("name");
		for(String s :nameValues) {
			System.out.println("names[] - "+ s);
		}
		String id = req.getParameter("id");
		String author = req.getParameter("author");
		String publisher = req.getParameter("publisher");
		String price = req.getParameter("price");
		String quanitiy = req.getParameter("order_quantity");

		BookVO book = new BookVO();
		book.setId(Integer.parseInt(id));
		book.setName(name);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setPrice(Double.valueOf(price));
		book.setQuantity(Integer.valueOf(quanitiy));
		return book;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShoppingServlet doGet");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("ShoppingServlet doPost");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		List<BookVO> bookList = (List<BookVO>) session.getAttribute("shoppingcart");
		String action = request.getParameter("action");
		System.out.println("action " + action);

		switch (action) {
		case "DEL":
			// delete cart
			String removeIndex = request.getParameter("delIndex");
			System.out.println("removed index " + removeIndex);
			bookList.remove(new Integer(removeIndex).intValue());
			break;
		case "ADD":
			// Add cart
			BookVO book = getBookVO(request);
			System.out.println("book " + book);
			if (bookList == null) {
				// no list in cart
				System.out.println("no list");
				bookList = new LinkedList<BookVO>();
				bookList.add(book);
			} else {

				boolean isSameBook = false;
				int bookIndex = 0;
				for (int i = 0; i < bookList.size(); i++) {
					if (bookList.get(i).getName().contains(book.getName())) {
						isSameBook = true;
						bookIndex = i;
					}
				}

				if (isSameBook) {
					// the same book in cart
					System.out.println("the same");
					int newQuan = book.getQuantity() + bookList.get(bookIndex).getQuantity();
					bookList.get(bookIndex).setQuantity(newQuan);
				} else {
					// not the same book in cart
					System.out.println("not the same");
					bookList.add(book);
				}

			}
			break;
		case "CHECKOUT":
			Double total = 0.0;
			Iterator<BookVO> it = bookList.iterator();
			while(it.hasNext()) {
				book = it.next();
				total+=book.getPrice()*book.getQuantity();
			}
			System.out.println("total - "+total);
			request.setAttribute("amount", total);
			session.setAttribute("shoppingcart", bookList);
			break;
			
		case "BUY":
			//Generate order list associate with user
			UserVO user = (UserVO) session.getAttribute("user");
			long currentNow = System.currentTimeMillis();
			OrderListVO orderList = orderList(user,currentNow);
			ArrayList<OrderDetailVO> orderDetailArr = orderDetail(request,user,currentNow);
			if(con==null) {
				System.out.println("no Connection");
				return;
			}
			OrderListDAO listDAO = new OrderListDAO();
			OrderDatailDAO DetailDAO = new OrderDatailDAO();
			int listCount = listDAO.insertOrderList(con, orderList);
			int detailCount = DetailDAO.insertOrderDetail(con, orderDetailArr, orderDetailArr.size());
			System.out.println("list count "+listCount);
			System.out.println("detail count "+detailCount);
			
			break;

		default:
			System.out.println("action not defined - " + action);
			
		}
		if(action.equals("CHECKOUT")) {
			RequestDispatcher rd = request.getRequestDispatcher("/checkout.jsp");
			rd.forward(request, response);
		}else {
			session.setAttribute("shoppingcart", bookList);
			RequestDispatcher rd = request.getRequestDispatcher("/redirect.jsp");
			rd.forward(request, response);
		}
		
	}
	private Timestamp genOrderNo() {
		long current = System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		Date date = new Date(current);
		String currentStamp = sdf.format(date);
		System.out.println(currentStamp);
		System.out.println("Timestamp--------------");
		System.out.println(Timestamp.valueOf(LocalDateTime.now()));
		return Timestamp.valueOf(LocalDateTime.now());
	}

	private OrderListVO orderList(UserVO user,long currentNow) {
		String orderNo = user.getName()+currentNow;
		String userName = user.getName();
		Timestamp orderTime = genOrderNo();
		System.out.println("orderNo "+orderNo);
		System.out.println("userName "+userName);
		System.out.println("orderTime "+orderTime);
		OrderListVO orderList = new OrderListVO();
		orderList.setOrder_no(orderNo);
		orderList.setUser_name(userName);
		orderList.setOrder_time(orderTime);
		return orderList;
	}
	
	private ArrayList<OrderDetailVO> orderDetail(HttpServletRequest req,UserVO user,long currentNow) {
		String orderNo = user.getName()+currentNow;
		String[] bookId = req.getParameterValues("order_bId");
		String[] orderQuantity = req.getParameterValues("order_quantity");
		ArrayList<OrderDetailVO> orderDetailArr = new ArrayList<>();
		System.out.println("id length - "+bookId.length);
		for(int i=0;i<bookId.length;i++) {
			OrderDetailVO orderDetail = new OrderDetailVO();
			orderDetail.setBook_id(Integer.valueOf(bookId[i]));
			orderDetail.setOrder_no(orderNo);
			orderDetail.setOrder_quantity(Integer.valueOf(orderQuantity[i]));
			orderDetailArr.add(orderDetail);
		}
		
		//orderDetailArr.add()
		return orderDetailArr;
	}
	
	@Override
	public void destroy() {
		// DB connection close()
		System.out.println("Shopping Servlet destory()!");
		//release connection
				con = null;
	}

}
