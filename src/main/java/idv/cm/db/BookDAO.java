package idv.cm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class BookDAO implements BookDAOImp {

	@Override
	public BookVO findByIndex(Connection con, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hashtable<String, BookVO> findAll(Connection con) {
		Hashtable<String,BookVO> table = new Hashtable<>();
		try {
			PreparedStatement pstm = con.prepareStatement(GET_ALL_STMT);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				String index = rs.getString(1);
				String name = rs.getString(2);
				String author = rs.getString(3);
				String storeName = rs.getString(4);
				double price = rs.getDouble(5);
				int quantity = rs.getInt(6);
				book.setId(Integer.valueOf(index));
				book.setName(name);
				book.setAuthor(author);
				book.setPublisher(storeName);
				book.setPrice(price);
				book.setQuantity(quantity);
				table.put(index,book);
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return table;
	}

}
