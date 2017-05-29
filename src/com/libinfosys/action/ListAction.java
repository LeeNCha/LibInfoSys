package com.libinfosys.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.libinfosys.controller.CommandAction;
import com.libinfosys.dao.LibinfosysDao;
import com.libinfosys.beans.Book;

public class ListAction implements CommandAction{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int page = 0;
		
		// 넘어온 파라미터가 있다면 
		if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));  
        }// 해당 파라미터를 int형으로 캐스팅후 변수에 대입합니다.
	
		/*DB query*/
		ArrayList<Book> bookList = LibinfosysDao.getInstance().getBookList(page);  
        request.setAttribute("bookList", bookList);   // 셋팅된 리스트를 뷰에 포워드합니다.
        request.setAttribute("page", page); // 페이지번호를 뷰에서 보기위해 표시합니다.

		return "list.jsp";
	}
}
