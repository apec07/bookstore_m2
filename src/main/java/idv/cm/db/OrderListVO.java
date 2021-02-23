package idv.cm.db;

import java.sql.Timestamp;

public class OrderListVO implements java.io.Serializable {
	
	private int id;
	private String order_no;
	private Timestamp order_time;
	private String user_name;
	
	public OrderListVO() {
		
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public Timestamp getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	@Override
	public String toString() {
		return "OrderListVO [order_no=" + order_no + ", order_time=" + order_time + ", user_name=" + user_name + "]";
	}

	
	

}
