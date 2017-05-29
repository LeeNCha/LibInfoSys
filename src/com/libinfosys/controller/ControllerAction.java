package com.libinfosys.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ControllerAction extends HttpServlet {
	private Map commandMap=new HashMap();
	
	public void init(ServletConfig config) throws ServletException{
		loadProperties("com/libinfosys/properties/Command"); //properties ���� �ҷ�����
	}
	
	
	/* property ���� ��θ� �޾� �ҷ����� �Լ�*/
	private void loadProperties(String path){
		ResourceBundle rbHome=ResourceBundle.getBundle(path); //http://jangsalt.tistory.com/entry/Java-ResourceBundle%EC%9D%98-%ED%99%9C%EC%9A%A9
		
		Enumeration<String> actionEnumHome=rbHome.getKeys();
		while(actionEnumHome.hasMoreElements()){
			String command=actionEnumHome.nextElement();
			String className=rbHome.getString(command);
			try{
				Class commandClass=Class.forName(className); //�ش� ���ڿ��� Ŭ������ �����
				Object commandInstance=commandClass.newInstance();//�ش� Ŭ������ ��ü�� ����
				commandMap.put(command, commandInstance);//Map ��ü�� commandMap�� ��ü ����
				
			}catch(ClassNotFoundException e){
				continue;
			}catch(InstantiationException e){
				e.printStackTrace();
			}catch(IllegalAccessException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	/*Get��İ� Post��� ��� requestPro�� ó��*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	
	
	/*������� ��û URL�� �м��Ͽ� ���ҽ� ���鿡 ����� �ش� �׼� ��ü�� ����*/
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