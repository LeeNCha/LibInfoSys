package com.libinfosys.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.libinfosys.beans.Book;
import com.libinfosys.dao.CommonDao;

public class LibinfosysDao extends CommonDao{
	 
    public static LibinfosysDao getInstance() {
 
        LibinfosysDao _instance = new LibinfosysDao();
        _instance.SetDB(); 	
        return _instance;
 
    }
    
 
	public ArrayList<Book> getBookList(int page) throws SQLException {
    	
    	ArrayList<Book> bookList=null;

        bookList=(ArrayList<Book>)GetDB().queryForList("getBookList",null,(page-1)*10,10);
        
        return bookList;
    }
	
	public int getBooksTotal() throws SQLException{
		
		int total = 0;
		
		total=(int) GetDB().queryForObject("getBookTotal",null);
		
		return total;
	}
    
}
