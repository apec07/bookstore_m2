package idv.cm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class OrderedListDTO implements OrderedListDTOImp {

	@Override
	public List<OrderedListVO> findOrderDetail(Connection con, UserVO user) {
		List<OrderedListVO> list = new ArrayList<>();
		
		PreparedStatement pstm=null;
		try {
			pstm = con.prepareStatement(QUERY_ORDER_STMT);
			pstm.setString(1, user.getName());
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				OrderedListVO orderList = new OrderedListVO();
				
				String orderNumber = rs.getString(1);
				String bookName = rs.getString(2);
				String bookAuthor = rs.getString(3);
				int bookPrice = rs.getInt(4);
				int orderQuantity = rs.getInt(5);
				Timestamp orderTime = rs.getTimestamp(6);
				orderList.setOrderNumber(orderNumber);
				orderList.setBookName(bookName);
				orderList.setBookAuthor(bookAuthor);
				orderList.setBookPrice(bookPrice);
				orderList.setOrderQuantity(orderQuantity);
				orderList.setOrderTime(orderTime);
				list.add(orderList);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("list.size() - "+list.size() );
		return list;
	}
	
	

}
