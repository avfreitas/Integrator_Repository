package br.com.qualitsys.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {
	
	@GET
    @Produces(MediaType.TEXT_HTML)
	 
    public String helloJersey() {
		 return "<html><title>Hello</title><body><h1>Hello Jersey!  JAX-RS Implementation!!!!</h1><body></html>";
    }

}