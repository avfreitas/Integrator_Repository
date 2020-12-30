package br.com.qualitsys.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/boot1")
public class boot1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public boot1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		//*--------------------------------------------------------------------------------------
		
 		String execucaoem =  "Execução em: " + boot1.getDateTime() ; 
 		String contextoRequest = "Contexto do Request: " + request.getContextPath()  ;
 		String requestURI = "Request URI: " + request.getRequestURI() ;
	    String requestURL = "Request URL: " + request.getRequestURL() ;
	    String servletPath = "Servlet Path: " + request.getServletPath() ;
	    String servletMapping = "Servlet Mapping: " + request.getHttpServletMapping()  ;
	    String method = "Method: " + request.getMethod()  ;
	    String serverName =     "Server Name: " + request.getServerName()  ;
	    String ip =    "IP: " + request.getLocalAddr() ;
	    String servletName = "ServletName: " + request.getDateHeader(getServletName()) ;
		String pathInfo = "Path Info: " + request.getPathInfo() ;
		String protocol = "Protocol: " + request.getProtocol() ;
		String remotePort = "Remote Port: " + request.getRemotePort() ;
		String queryString = "Query String: " + request.getQueryString() ;
		String userPrincipal = "User Principal: " + request.getUserPrincipal() ;
		String contentLength = "Content Length: " + request.getContentLength() ;
		String locale = "Locale: " + request.getLocale() ;
		String scheme = "Scheme: " + request.getScheme()  ;
		String localName = "Local Name: " + request.getLocalName() ;
		String portLocal = "Local Port: " + request.getLocalPort()  ;
		String remoteHost = "Remote Host: " + request.getRemoteHost() ;
		String session = "Session: " + request.getSession()  ;
		String serverPort = "Server Port : " + request.getServerPort() ;
		String authType = "Auth Type: " + request.getAuthType()  ;
		
		
		 request.setAttribute("execucaoem", execucaoem);
		 request.setAttribute("contextoRequest", contextoRequest);
		 request.setAttribute("requestURI", requestURI);
		 request.setAttribute("requestURL", requestURL);
		 request.setAttribute("servletPath", servletPath);
		 request.setAttribute("servletMapping", servletMapping);
		 request.setAttribute("method", method);
		 request.setAttribute("serverName", serverName);
		 request.setAttribute("ip", ip);
		 request.setAttribute("servletName", servletName);
		 request.setAttribute("pathInfo", pathInfo);
		 request.setAttribute("protocol", protocol);
		 request.setAttribute("remotePort", remotePort);
		 request.setAttribute("queryString", queryString);
		 request.setAttribute("userPrincipal", userPrincipal);
		 request.setAttribute("contentLength", contentLength);
		 request.setAttribute("locale", locale);
		 request.setAttribute("scheme", scheme);
		 request.setAttribute("localName", localName);
		 request.setAttribute("portLocal", portLocal);
		 request.setAttribute("remoteHost", remoteHost);
		 request.setAttribute("session", session);
		 request.setAttribute("serverPort", serverPort);
		 request.setAttribute("authType", authType);
	
         String url = "/jspboot1.jsp";   
         getServletContext().getRequestDispatcher(url).forward(request, response);
				 
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
