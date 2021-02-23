package idv.cm.db;

public class OrderDetailVO implements java.io.Serializable{
	
	private int id;
	private String order_no;
	private int book_id;
	private int order_quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	@Override
	public String toString() {
		return "OrderDetailVO [order_no=" + order_no + ", book_id=" + book_id + ", order_quantity=" + order_quantity
				+ "]";
	}
	
	

}
