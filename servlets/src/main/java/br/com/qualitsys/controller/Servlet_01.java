package br.com.qualitsys.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		        "Context Path: " + request.getContextPath()  + "</br>"+
		        "Request URI: " + request.getRequestURI() + "</br>"+
		        "Servlet Mapping: " + request.getHttpServletMapping() + "</br>"+
				"Path Info: " + request.getPathInfo() + "</br>"+
				"Protocol: " + request.getProtocol() + "</br>"+
 				"IP: " + request.getLocalAddr() + "</br>"+
 				"Remote Port: " + request.getRemotePort()+ "</br>"+
 				"Query String: " + request.getQueryString() + "</br>"+
 				"Request URL: " + request.getRequestURL() + "</br>"+
 				"User Principal: " + request.getUserPrincipal() + "</br>"+
 				"Content Length: " + request.getContentLength() + "</br>"+
 				"Locale: " + request.getLocale()+ "</br>"+
				"Server Name: " + request.getServerName()  + "</br>"+
				"Raiz do Projeto: " + request.getContextPath()  + "</br>"+
				"Scheme: " + request.getScheme()  + "</br>"+
				"Local Name: " + request.getLocalName() + "</br>"+
				"Local Port: " + request.getLocalPort()  + "</br>"+
				"Method: " + request.getMethod()  + "</br>"+
				"Remote Host: " + request.getRemoteHost() + "</br>"+
				"Session: " + request.getSession()  + "</br>"+
				"Server Port : " + request.getServerPort()  +  "</br>"+
				"Server Name: " + request.getServerName()  +  "</br>"+
				"Content Length : " + request.getContentLength()  +  "</br>"+
				"Auth Type: " + request.getAuthType()  + "</br>"    ); 
		        
		        
 
					 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
