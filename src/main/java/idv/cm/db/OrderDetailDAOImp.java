package idv.cm.db;

import java.sql.Connection;
import java.util.ArrayList;

public interface OrderDetailDAOImp {
	
	static final String GET_ORDER_DETAIL_STMT="";
	// --數個detail 對應不同書籍
	static final String INSERT_ORDER_DETAIL_STMT="insert into b_order_detail(Order_No,Book_Id,Order_Quantity) values(?,?,?)";
	// --建議執行batch
	int insertOrderDetail(Connection con,ArrayList<OrderDetailVO> orderDetailArr,int numDetail);
	
}
