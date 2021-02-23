package idv.cm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDatailDAO implements OrderDetailDAOImp {

	@Override
	public int insertOrderDetail(Connection con, ArrayList<OrderDetailVO> orderDetailArr,int numDetail) {
		System.out.println("numDetail - "+numDetail);
		int[] updateNum = new int[numDetail];
		
		try {
			//con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement(INSERT_ORDER_DETAIL_STMT);
			int i=0;
			while(i<numDetail) {
				pstmt.setString(1,orderDetailArr.get(i).getOrder_no());
				pstmt.setInt(2, orderDetailArr.get(i).getBook_id());
				pstmt.setInt(3, orderDetailArr.get(i).getOrder_quantity());
				System.out.println("SQL quantity "+ orderDetailArr.get(i).getOrder_no());
				System.out.println("SQL quantity "+ orderDetailArr.get(i).getBook_id());
				System.out.println("SQL quantity "+ orderDetailArr.get(i).getOrder_quantity());
				updateNum[i] = pstmt.executeUpdate();
				i++;
			}
			
			//updateNum = pstmt.executeBatch();
			
				//con.commit();
				//con.setAutoCommit(true);
				System.out.println("end of detail");
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num=0;
		for(int index=0;index<updateNum.length;index++) {
			System.out.println("updateNum[index]"+updateNum[index]);
			num+=updateNum[index];
		}
//		for(int index :updateNum) {
//			num=updateNum[index];
//			index++;
//		}
		
		return num;
	}

}
