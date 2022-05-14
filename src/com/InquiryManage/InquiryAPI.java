package com.InquiryManage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


	@WebServlet("/InquiryAPI")
	public class InquiryAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Inquiry inqObj = new Inquiry();

	public InquiryAPI() {
	super();
	}

	// Convert request parameters to a Map
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

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String output = inqObj.insertInquiry(request.getParameter("PersonName"),
					request.getParameter("Area"),
			        request.getParameter("Date"),
			        request.getParameter("Reason"));
					response.getWriter().write(output); 

		}

		protected void doPut(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = inqObj.updateInquiry(paras.get("hidInquiryIDSave").toString(),
				 paras.get("PersonName").toString(),
				 paras.get("Area").toString(),
				 paras.get("Date").toString(),
				 paras.get("Reason").toString());
				 response.getWriter().write(output);
				} 

		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
				 throws ServletException, IOException
				{
				 Map paras = getParasMap(request);
				 String output = inqObj.deleteInquiry(paras.get("inqID").toString());
				response.getWriter().write(output);
				}

	}
