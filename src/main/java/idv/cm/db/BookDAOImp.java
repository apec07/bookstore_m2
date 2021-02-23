package idv.cm.db;

import java.sql.Connection;
import java.util.Hashtable;

public interface BookDAOImp {
	
	//static final String INSERT_STMT="insert into B_USER(USER,PASSWORD,EMAIL) values(?,?,?)";
	//static final String GET_ONE_STMT="select USER,PASSWORD,EMAIL from B_USER where ID = ?";
	//static final String DELETE_STMT="delete from B_USER where ID =?";
	
	static final String GET_ALL_STMT="select BOOKS.Id,BOOKS.NAME,BOOKS.AUTHOR,STORES.NAME as 'store name',BOOKS.PRICE,QUANS.QUANTITY as 'store in quantity'" + 
			"from B_BOOK as BOOKS " + 
			"inner join B_PUBLISHER as STORES " + 
			"on BOOKS.PUBLISHER_Id=STORES.Id " + 
			"inner join b_quantity as QUANS " + 
			"on BOOKS.QUANTITY_Id = QUANS.Id;";

	//int insert(Connection con, BookVO book);
	//int update(Connection con,int id, BookVO book);
	//int delete(Connection con,int id);
	
	BookVO findByIndex(Connection con,int id);
	Hashtable<String,BookVO> findAll(Connection con);

}
