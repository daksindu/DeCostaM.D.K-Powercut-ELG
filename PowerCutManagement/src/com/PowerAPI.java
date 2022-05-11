package com;

import java.io.IOException;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/PowerAPI")

public class PowerAPI extends HttpServlet {
	
	Power PowerObj = new Power();
	
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 public PowerAPI() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 * 
		 */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			String output = PowerObj.insertPower(request.getParameter("date"), 
					 request.getParameter("time"), 
					request.getParameter("area"), 
					request.getParameter("duration"));
					response.getWriter().write(output);
		}
		
		/**
		 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
		 */
		
		// Convert request parameters to a Map
		
			 
		protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			Map paras = getParasMap(request); 
			 String output = PowerObj.updatePower(paras.get("hidPowerIDSave").toString(), 
			 paras.get("date").toString(), 
			 paras.get("time").toString(), 
			 paras.get("area").toString(), 
			paras.get("duration").toString());  
			response.getWriter().write(output);
			
		}
		/**
		 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
		 */
		protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			
			Map paras = getParasMap(request); 
			 String output = PowerObj.deletePower(paras.get("id").toString()); 
			response.getWriter().write(output);
		}
		private static Map getParasMap(HttpServletRequest request) 
		{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 {
			 String[] p = param.split("=");
			 map.put(p[0], p[1]); 
			 } 
			 } 
			catch (Exception e) 
			 { 
			 } 
			return map; 
			}


}
