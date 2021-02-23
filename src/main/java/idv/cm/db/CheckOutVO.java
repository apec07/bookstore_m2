package idv.cm.db;

import java.sql.Date;
import java.util.List;

public class CheckOutVO implements java.io.Serializable {
	
	private String uId;
	private UserVO user; 
	private List<BookVO> list;
	private Date orderDate;
	
	public CheckOutVO() {}
	
	public CheckOutVO(UserVO user,List<BookVO> list) {
		long currentStamp = System.currentTimeMillis();
		this.uId = user.getName()+"-"+currentStamp;
		this.user = user;
		this.list = list;
		this.orderDate = new Date(currentStamp);
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public List<BookVO> getList() {
		return list;
	}

	public void setList(List<BookVO> list) {
		this.list = list;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	

}
