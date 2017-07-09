<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"  pageEncoding="EUC-KR"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%!
	public Integer toInt(String x){
		int a = 0;
		try{
			a = Integer.parseInt(x);
		}catch(Exception e){}
		return a;
	}
%>


<%
	int pageno = toInt(request.getParameter("page"));
	String tot = request.getParameter("totnum"); 
	int totalno=toInt(tot);
	int totalpg=0;
	if(totalno%10==0){
		totalpg=totalno/10;
	}else{
		totalpg=totalno/10+1;
	}
	
	
	
	if(pageno<1){//현재 페이지
		pageno = 1;
	}
	
	//현재 페이지에 띄워줄 페이지네이션 시작과 끝 정해주기
	int page_s, page_e;
	if(pageno%5!=0){
		page_s=5*(pageno/5)+1;
		page_e=page_s+4;
	}else if((totalpg/5)*5<pageno&&pageno<totalpg){
		page_s=(totalpg/5)*5+1;
		page_e=totalpg;
	}else{
		page_e=(pageno/5)*5;
		page_s=page_e-4;
	}
	
	
	//Prev 동작
	int page_prev;
	if(page_s==1){
		page_prev=pageno;
	}else{
		page_prev=page_s-1;
	}
	
	//Next 동작
	int page_next;	
	page_next=page_e+1;
	if(totalpg<page_next){
		page_next=pageno;
	}
	
	
%>
<div>
	<nav class="center-block pg-nav">
		<ul class="pagination">
		
	    	<li <% if(page_s==1){ %> class="disabled"<%} %>>
				<a href="?page=<%=page_prev%>" aria-label="Previous">
				  <span aria-hidden="true">&laquo;</span>
				</a>
	    	</li>
	    	<% for (int a= page_s; a<=page_e; a++){ 
	    		if(pageno==a){%>
				<li class="active"><a href="?page=<%=a %>" ><%=a %></a></li>
				<%}else{%>
				<li><a href="?page=<%=a %>" ><%=a %></a></li>
					
				<%}
			} %>
	    	<li <% if(totalpg<page_e+1){ %> class="disabled"<%} %>>
				<a href="?page=<%=page_next %>" aria-label="Next">
				  <span aria-hidden="true">&raquo;</span>
				</a>
	    	</li>
	  	</ul>
	</nav>
</div>