package com.libinfosys.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerAction extends HttpServlet {
	private Map commandMap=new HashMap();
	
	public void init(ServletConfig config) throws ServletException{
		loadProperties("com/libinfosys/properties/Command"); //properties 파일 불러오기
	}
	
	
	/* property 파일 경로를 받아 불러오는 함수*/
	private void loadProperties(String path){
		ResourceBundle rbHome=ResourceBundle.getBundle(path); //http://jangsalt.tistory.com/entry/Java-ResourceBundle%EC%9D%98-%ED%99%9C%EC%9A%A9
		
		Enumeration<String> actionEnumHome=rbHome.getKeys();
		while(actionEnumHome.hasMoreElements()){
			String command=actionEnumHome.nextElement();
			String className=rbHome.getString(command);
			try{
				Class commandClass=Class.forName(className); //해당 문자열을 클래스로 만든다
				Object commandInstance=commandClass.newInstance();//해당 클래스의 객체를 새성
				commandMap.put(command, commandInstance);//Map 객체인 commandMap에 객체 저장
				
			}catch(ClassNotFoundException e){
				continue;
			}catch(InstantiationException e){
				e.printStackTrace();
			}catch(IllegalAccessException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	/*Get방식과 Post방식 모두 requestPro로 처리*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	
	
	/*사용자의 요청 URL을 분석하여 리소스 번들에 저장된 해당 액션 객체를 실행*/
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view=null;
		CommandAction com=null;
		
		try{
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0){
				command=command.substring(request.getContextPath().length());
			}
			
			com=(CommandAction) commandMap.get(command);
			
			if(com==null){
				System.out.println("not found:"+command);
				
				return;
			}
			
			
			view = com.requestPro(request, response);
			
			
			if(view==null){
				return;
			}
			
		} catch(Throwable e){
			throw new ServletException(e);
		}
		
		if(view==null){
			return;
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		
		dispatcher.forward(request, response);
	}

}
