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
		
		// �Ѿ�� �Ķ���Ͱ� �ִٸ� 
		if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));  
        }// �ش� �Ķ���͸� int������ ĳ������ ������ �����մϴ�.
	
		/*DB query*/
		ArrayList<Book> bookList = LibinfosysDao.getInstance().getBookList(page);  
        request.setAttribute("bookList", bookList);   // ���õ� ����Ʈ�� �信 �������մϴ�.
        request.setAttribute("page", page); // ��������ȣ�� �信�� �������� ǥ���մϴ�.

		return "list.jsp";
	}
}
