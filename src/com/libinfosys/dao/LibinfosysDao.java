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

        bookList=(ArrayList<Book>)GetDB().queryForList("getBookList",null,page,10);
        
        return bookList;
    }
    
}
