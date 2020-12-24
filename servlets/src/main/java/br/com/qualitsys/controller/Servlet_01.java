package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet_01
 */
@WebServlet("/s1")
public class Servlet_01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet_01() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		String titulo = "Servlet com Maven - Atributos do Request";
		PrintWriter out = response.getWriter();
		out.println(
		        "<HTML>\n" +    
		        "<HEAD><TITLE>" + titulo + "</TITLE></HEAD>\n" +
		        "<BODY BGCOLOR=\"#6699FF\">\n" +
		        "<H1 ALIGN=center>" + titulo + "</H1>\n" +
		        "<OL>" + "<HR SIZE=4 WIDTH=50%>" + 
		        
		        "Execução em: " + Servlet_01.getDateTime() + "<br>" +
		        "<br>" +
		        
		        "Contexto do Request: " + request.getContextPath()  + "</br>"+
		        "Request URI: " + request.getRequestURI() + "</br>"+
		        "Request URL: " + request.getRequestURL() + "</br>"+
		        "Servlet Path: " + request.getServletPath() + "</br>"+
		        
		        "Servlet Mapping: " + request.getHttpServletMapping() + "</br>"+
		        "</br>" +
		        
		        "Method: " + request.getMethod()  + "</br>"+
		        "Server Name: " + request.getServerName()  + "</br>"+
		        "IP: " + request.getLocalAddr() + "</br>"+
		        
		        
  				"</br>" +  "ServletName: " + request.getDateHeader(getServletName()) + "<br>" +
  
		        			
				"Path Info: " + request.getPathInfo() + "</br>"+
				"Protocol: " + request.getProtocol() + "</br>"+
 			
 				"Remote Port: " + request.getRemotePort()+ "</br>"+
 				"Query String: " + request.getQueryString() + "</br>"+
 				
 				"User Principal: " + request.getUserPrincipal() + "</br>"+
 				"Content Length: " + request.getContentLength() + "</br>"+
 				"Locale: " + request.getLocale()+ "</br>"+
	
				
				"Scheme: " + request.getScheme()  + "</br>"+
				"Local Name: " + request.getLocalName() + "</br>"+
				"Local Port: " + request.getLocalPort()  + "</br>"+
			
				"Remote Host: " + request.getRemoteHost() + "</br>"+
				"Session: " + request.getSession()  + "</br>"+
				"Server Port : " + request.getServerPort()  +  "</br>"+
				"Auth Type: " + request.getAuthType()  + "</br>"    ); 
				 
	}

	public static  String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
