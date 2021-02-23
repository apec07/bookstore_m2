package idv.cm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderListDAO implements OrderListDAOImp{

	@Override
	public int insertOrderList(Connection con, OrderListVO orderList) {
		PreparedStatement pstmt;
		int updateNum=0;
		System.out.println(orderList.getUser_name());
		try {
			pstmt = con.prepareStatement(INSERT_ORDER_LIST_STMT);
			pstmt.setString(1, orderList.getOrder_no());
			pstmt.setString(2, orderList.getUser_name());
			pstmt.setString(3, orderList.getOrder_time().toString());
			updateNum = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return updateNum;
	}

}
