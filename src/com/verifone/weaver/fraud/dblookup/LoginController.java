package com.verifone.weaver.fraud.dblookup;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Util;
import com.verifone.weaver.fraud.dblookup.service.DBAccessor;
import com.verifone.weaver.fraud.dblookup.service.Utils;

public class LoginController extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		 boolean error=false;
		
		HttpSession session=request.getSession();
		if(session.getAttribute("user")!=null)
		{
			Map values=DBAccessor.getTableDetails();
			
			request.setAttribute("data",values);
		}
		else
		{
			request.setAttribute("error","User Session Closed ");
			error=true;
		}
		
		dispatchRequest(request, response, error);
	}
	
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		System.err.println(request.getParameter("username")+"\t"+request.getParameter("password"));
		
		 boolean error=false;
		if(Utils.get("app_user")!=null && Utils.get("app_pwd")!=null)
		{
			String user=request.getParameter("username");
			String pass=request.getParameter("password");
			
			if(Utils.get("app_user").equals(user) && Utils.get("app_pwd").equals(pass))
			{
				Map values=DBAccessor.getTableDetails();
				
				request.setAttribute("data",values);
				request.getSession().setAttribute("user",user);
			}
			
			else
			{
				error=true;
				request.setAttribute("error","Login Failed (Username/Password Invalid)");
			}
			
		}
		else
		{
			error=true;
			request.setAttribute("error","Application is not Configured Properly");
		}
		
		dispatchRequest(request, response, error);
	}

	private void dispatchRequest(HttpServletRequest request,
			HttpServletResponse response, boolean error) {
		RequestDispatcher dispatcher=null;
		
		if(error)
		{
			dispatcher=request.getRequestDispatcher("error.jsp");
		}
		else
		{
			dispatcher=request.getRequestDispatcher("table.jsp");
		}
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	

}
