package idv.cm.db;

import java.sql.Timestamp;

public class OrderedListVO implements java.io.Serializable {
	
	private String orderNumber;
	private String bookName;
	private String bookAuthor;
	private int bookPrice;
	private int orderQuantity;
	private Timestamp orderTime;
	
	public OrderedListVO() {
		
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}

	@Override
	public String toString() {
		return "OrderedListVO [orderNumber=" + orderNumber + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor
				+ ", bookPrice=" + bookPrice + ", orderQuantity=" + orderQuantity + ", orderTime=" + orderTime + "]";
	}

	
	
	
	

}
