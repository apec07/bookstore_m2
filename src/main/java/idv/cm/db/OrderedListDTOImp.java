package idv.cm.db;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface OrderedListDTOImp {
	
	static final String QUERY_ORDER_STMT=
			 " select o_detail.Order_No,b.name,b.author,b.price as 'BookPrice',o_detail.Order_Quantity,o_list.order_time"+ 
	         " from b_order_list as o_list"+
	         " inner join b_order_detail as o_detail"+
	         " on o_list.Order_No = o_detail.Order_No"+
	         " inner join b_book as b "+
	         " on o_detail.book_Id = b.id"+
	         " inner join b_user"+
	         " on o_list.user_name = b_user.user"+
	         " where b_user.user= ?";
	
	List<OrderedListVO> findOrderDetail(Connection con,UserVO user);

}
