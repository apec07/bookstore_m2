package idv.cm.db;

import java.sql.Connection;

public interface OrderListDAOImp {
	
	static final String GET_ORDER_LIST_STMT="";
	static final String INSERT_ORDER_LIST_STMT="insert into b_order_list(Order_No,User_Name,Order_Time) values(?,?,?)";
	
	int insertOrderList(Connection con,OrderListVO orderList);
	

}
